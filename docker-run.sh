# shellcheck disable=SC2164
cd /app/

mvn clean install

java -jar -Dspring.profiles.active=local /app/web/target/web-0.0.1-SNAPSHOT.jar
