#!/bin/bash

# presta atención al publisher, con un ":=" y luego el objeto JSON
./httpie_with_token.sh POST :8080/authors name="John" biography="la biografía de John" publisher:='{ "id": 1 }'