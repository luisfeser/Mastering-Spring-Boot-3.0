#/bin/bash

# presta atención al authors, con un ":=" y luego el objeto JSON
./httpie_with_token.sh POST :8080/books title="El título del libro 2" authors:='[{"id": 1}]' published=true isbn="123451"