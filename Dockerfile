FROM  maven:3.6-jdk-8-alpine

RUN mkdir -p /app/
COPY ./ /app/
RUN cd /app/ && mvn clean install
RUN cp /app/web/target/*.jar /app/

CMD ["java","-jar","-Dspring.profiles.active=local","*.jar"]