#!/bin/bash
while true; do

curl -s -i -X POST -H "Content-Type: application/json" -d "{\"openSkyLink\":\"https://opensky-network.org/api/states/all?lamin=45.8389&lomin=5.9962&lamax=47.8229&lomax=10.5226\",\"region\":\"Szwajcaria\",\"influxUrl\":\"http://ec2-3-121-41-245.eu-central-1.compute.amazonaws.com:8086/write?db=testDb\"}"   https://87bfsu9y5c.execute-api.eu-central-1.amazonaws.com/prod/lambdafinal

sleep 10
done