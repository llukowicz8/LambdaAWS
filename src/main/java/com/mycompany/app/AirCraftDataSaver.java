package com.mycompany.app;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class AirCraftDataSaver implements RequestHandler<CountryRequest, CountryResponse> {

    @Override
    public CountryResponse handleRequest(CountryRequest input, Context context) {
        URL url, fluxUrl;
        int samolotyWPowietrzu = 0;
        int samolotyNaZiemi = 0;
        float topSpeed = 0.0f;
        float topAltitude = 0.0f;
        CountryResponse countryResponse = new CountryResponse();
        try {
            url = new URL("https://opensky-network.org/api/states/all");
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

                for (Object airplane : statesArray) {
                    JSONArray values = (JSONArray) airplane;
                    if (values.getBoolean(8)) {
                        samolotyNaZiemi++;
                    } else {
                        samolotyWPowietrzu++;
                    }
                    try {
                        float predkosc = values.getFloat(9);
                        if (predkosc > topSpeed) {
                            topSpeed = predkosc;
                        }

                    } catch (Exception e) {

                    }
                    try {
                        float wysokosc = values.getFloat(13);
                        if (wysokosc > topAltitude) {
                            topAltitude = wysokosc;
                        }

                    } catch (Exception e) {
                        //e.printStackTrace();
                    }

                }

            }


        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //in.close();
        }


        try {
            fluxUrl = new URL("http://ec2-54-93-232-40.eu-central-1.compute.amazonaws.com:8086/write?db=testDb");
            HttpURLConnection con2 = (HttpURLConnection) fluxUrl.openConnection();
            con2.setDoOutput(true);
            con2.setRequestMethod("POST");

            float topSpeedKmH = (topSpeed/10)*36;

            // Send post request
            OutputStream os = con2.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write("samoloty,region=swiat naZiemi=" + samolotyNaZiemi+",wPowietrzu="+samolotyWPowietrzu+",topSpeed="+topSpeedKmH+",topAltitude="+topAltitude);
            osw.flush();
            osw.close();
            os.close();  //don't forget to close the OutputStream
            con2.connect();
            int responseCode = con2.getResponseCode();
            String responseMessage = con2.getResponseMessage();
            System.out.println("Response code  "+responseCode);
            System.out.println("Response msg  "+responseMessage);

            countryResponse.setMessage(responseMessage);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return countryResponse;

    }
}
