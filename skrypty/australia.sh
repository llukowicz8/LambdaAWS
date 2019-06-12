#!/bin/bash
while true; do

curl -s -i -X POST -H "Content-Type: application/json" -d "{\"openSkyLink\":\"https://opensky-network.org/api/states/all?lamin=-39.0&lomin=113.0&lamax=-11.0&lomax=153.0\",\"region\":\"Australia\"}"   https://87bfsu9y5c.execute-api.eu-central-1.amazonaws.com/prod/lambdafinal

sleep 10
done