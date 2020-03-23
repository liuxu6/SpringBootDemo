# SpringBootDemo
## How to setup server locally

1. MySQL setup
    ```bash
    # run the command in dev folder
    docker pull mysql:5.7
    ```

2. Redis setup

    ```bash
    docker pull redis:5.0
    docker run -itd --name redis -p 6379:6379 docker.io/redis:5.0
    docker exec -it redis /bin/bash
    redis-cli
   
3. install jar file
   ``` bash
   # under root folder
   mvn clean install
   ```
4. run jar file
   ``` bash
   # find jar file under target folder
   java -jar -Dspring.profiles.active=local xxx.jar
   ```
