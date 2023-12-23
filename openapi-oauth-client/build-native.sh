#|/bin/bash
echo 'Building native image'
./mvnw package -Dnative

echo 'Building docker container'
docker build -f src/main/docker/Dockerfile.native-micro -t unibo-cybersec/openapi-oauth-client .

echo 'To run docker container run: docker run -i --rm -p 8080:8080 unibo-cybersec/openapi-oauth-client'