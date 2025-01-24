#!/bin/bash

# Number of concurrent requests
REQUESTS=100

IP="172.20.0.1"
BASE_URL="http://${IP}:8080/address"

# Loop to send concurrent requests
for ((i=1; i<=REQUESTS; i++))
do
  # Send the request in the background
  addressId=$(expr 1 + ${i} % 9)
  curl -s -X POST "$BASE_URL/${addressId}/modify/-1" &
done

# Wait for all background jobs to finish
wait

echo "All $REQUESTS requests completed."