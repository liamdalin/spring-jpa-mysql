FROM openjdk:8-jre-alpine
WORKDIR /
ADD spring-jpa-mysql.jar spring-jpa-mysql.jar
EXPOSE 8090
CMD java -Xmx512m -Xms512m -jar spring-jpa-mysql.jar
