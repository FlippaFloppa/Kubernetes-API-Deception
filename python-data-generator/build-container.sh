#|/bin/bash
echo 'Building python container'

docker build -t unibo-cybersec/python-data-generator .

echo 'Container built'
echo 'Possible environment variables:'
cat .env.template

echo''
echo 'To run docker container run: docker run -d unibo-cybersec/python-data-generator'