#!/bin/bash

# Request access token from Keycloak server
#response=$(http --form POST http://localhost:8081/login \
#    grant_type=password \
#    client_id=your-client-id \
#    client_secret=your-client-secret \
#    username=user \
#    password=1234)

# Extract access token from response
#access_token=$(echo $response | jq -r .access_token)

# Save access token to token.txt
#echo $access_token > token.txt

http POST :8080/login username=user password=1234 | jq -r '.access_token' > token.txt