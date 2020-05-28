# SpringBootDemo

## 本地环境配置

#### MySQL 配置

1. 下载docker镜像

   ```bash
   docker pull mysql:5.7
   ```

2. 运行MySQL 

   ```bash
   docker run -d --name mysql \
   	-p 3306:3306 \
   	-v /workspace/docker/mysql:/etc/mysql/conf.d \
   	-e MYSQL_ROOT_PASSWORD=000000 \
   	mysql:5.7
   ```

   

#### Redis 配置

1. 下载Redis镜像

   ```bash
   docker pull redis:5.0
   ```

2. 运行Redis 

   ```bash
   docker run -itd --name redis -p 6379:6379 redis:5.0
   ```

3. 查看Redis数据

   ```bash
   docker exec -it redis /bin/bash
   redis-cli
   ```

   

#### RabbitMQ 配置

1. 下载RabbitMQ 镜像

   ```bash
   docker pull rabbitmq:3.8.3-management
   ```

2. 运行RabbitMQ 

   ```bash
   docker run -d \
   	--hostname rabbitmq-server \
      	--name rabbitmq \
   	-p 1883:1883 \
       -p 5672:5672 \
       -p 15672:15672 \
       -e RABBITMQ_DEFAULT_USER=admin \
       -e RABBITMQ_DEFAULT_PASS=000000 \
       rabbitmq:3.8.3-management
   ```

3. 开启mqtt插件

   ```bash
   docker exec -it rabbitmq /bin/bash
   rabbitmq-plugins enable rabbitmq_mqtt
   ```

   

#### 打包

``` bash
mvn clean install
```



#### 启动

``` bash
java -jar -Dspring.profiles.active=local xxx.jar
```