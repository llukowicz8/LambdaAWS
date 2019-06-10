package com.agh.flightApp;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;


public class AirCraftDataSaver implements RequestHandler<Request, Response> {

    static final Logger logger = Logger.getLogger(AirCraftDataSaver.class);

    @Override
    public Response handleRequest(Request input, Context context) {
        BasicConfigurator.configure();
        Response response = new Response();
        URL url, fluxUrl;
        int samolotyWPowietrzu = 0;
        int samolotyNaZiemi = 0;
        float topSpeed = 0.0f;
        float topAltitude = 0.0f;
        try {
            url = new URL(input.getOpenSkyLink());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int status = con.getResponseCode();

            if (status == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();

                JSONObject jsonObj = new JSONObject(content.toString());

                int time = jsonObj.getInt("time");
                System.out.println("Czas: " + time);

                JSONArray statesArray = jsonObj.getJSONArray("states");
                System.out.println("Zaczynam parsowac " + time);
                for (Object airplane : statesArray) {

                    JSONArray values = (JSONArray) airplane;
                    if (values.getBoolean(8)) {
                        samolotyNaZiemi++;
                    } else {
                        samolotyWPowietrzu++;
                    }
                    try {
                        Optional<Float> predkosc = Optional.of(values.getFloat(9));
                        topSpeed = predkosc.isPresent() ? Math.max(predkosc.get(), topSpeed) : topSpeed;

                    } catch (JSONException e) {
                        logger.error("Problem with parse speed " + e.getMessage());
                    }
                    try {
                        Optional<Float> wysokosc = Optional.of(values.getFloat(13));
                        topAltitude = wysokosc.isPresent() ? Math.max(wysokosc.get(), topAltitude) : topAltitude;

                    } catch (JSONException e) {
                        logger.error("Problem with parse altitude " + e.getMessage());
                    }
                }
            }
            fluxUrl = new URL("http://ec2-18-196-182-211.eu-central-1.compute.amazonaws.com:8086/write?db=testDb");
            HttpURLConnection con2 = (HttpURLConnection) fluxUrl.openConnection();
            con2.setDoOutput(true);
            con2.setRequestMethod("POST");

            float topSpeedKmH = (topSpeed / 10) * 36;

            OutputStream os = con2.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write("samoloty,region="+ input.getRegion()+ " naZiemi=" + samolotyNaZiemi + ",wPowietrzu=" + samolotyWPowietrzu + ",topSpeed=" + topSpeedKmH + ",topAltitude=" + topAltitude);
            osw.flush();
            osw.close();
            os.close();
            con2.connect();
            response.setMessage(con2.getResponseMessage());

        } catch (IOException e) {
            logger.error("Problem with connection "+ e.getMessage());
        }

        return response;

    }

}
