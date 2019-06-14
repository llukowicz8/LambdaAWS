#!/bin/bash
while true; do

curl -s -i -X POST -H "Content-Type: application/json" -d "{\"openSkyLink\":\"https://opensky-network.org/api/states/all?lamin=49.2&lomin=14.6&lamax=54.9&lomax=23.6\",\"region\":\"Polska\",\"influxUrl\":\"http://ec2-3-121-41-245.eu-central-1.compute.amazonaws.com:8086/write?db=testDb\"}"   https://87bfsu9y5c.execute-api.eu-central-1.amazonaws.com/prod/lambdafinal

sleep 10
done