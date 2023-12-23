#|/bin/bash
echo 'Building python container'

docker build -t unibo-cybersec/python-server-deception:v1 .

echo 'To run docker container run: docker run -d --env SERVER_PORT:"" --env API_SERVER_URL:"" --env API_SERVER_PORT:"" --env API_SERVER_PATH:"" --env CHAR_SPACE:"" unibo-cybersec/python-server-deception'
