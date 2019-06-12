#!/bin/bash
while true; do

curl -s -i -X POST -H "Content-Type: application/json" -d "{\"openSkyLink\":\"https://opensky-network.org/api/states/all?lamin=25.0&lomin=-124.0&lamax=49.0&lomax=-69.0\",\"region\":\"USA\"}"   https://87bfsu9y5c.execute-api.eu-central-1.amazonaws.com/prod/lambdafinal

sleep 10
done