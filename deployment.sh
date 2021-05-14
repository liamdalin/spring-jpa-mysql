#!/bin/sh

# grant execute access before run this shell: chmod u+x deployment.sh
# run shell with BUILD_VERSION argument, such as:  ./deployment.sh 1.0

BUILD_VERSION="$1"
PROJECT_NAME="spring-jpa-mysql"
MYSQL_NETWORK="mysql_db"

echo "$(date "+%Y_%m_%d-%H:%M:%S") create executable jar"
./gradlew clean assemble

echo "$(date "+%Y_%m_%d-%H:%M:%S") remove old jars in docker build folder"
rm -rf docker/spring-data-mysql/*.jar

echo "$(date "+%Y_%m_%d-%H:%M:%S") move jar file to docker build folder"
mv build/libs/*.jar docker/spring-data-mysql/${PROJECT_NAME}.jar

echo "$(date "+%Y_%m_%d-%H:%M:%S") remove current running container"
# docker ps -q --filter ancestor=<image_name:tag>
docker stop $(docker ps -q --filter ancestor=${PROJECT_NAME}:${BUILD_VERSION})
docker rm $(docker ps -a -q --filter ancestor=${PROJECT_NAME}:${BUILD_VERSION})

docker rmi $(docker images -q --filter reference=${PROJECT_NAME}:${BUILD_VERSION})

cd docker/spring-data-mysql

echo "$(date "+%Y_%m_%d-%H:%M:%S") build docker image"
docker build -t ${PROJECT_NAME}:${BUILD_VERSION} .

echo "$(date "+%Y_%m_%d-%H:%M:%S") start docker container"
# in order to connect to Mysql container, must run container with mysql network
# network 'mysql_db' is created in mysql docker-compose.yml
docker run -dp 8090:8090 --network ${MYSQL_NETWORK} ${PROJECT_NAME}:${BUILD_VERSION}