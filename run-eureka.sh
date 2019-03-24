#!/usr/bin/env bash
export JAVA_HOME=/opt/JavaHome/jdk1.8.0_151
export PATH=$PATH:.
echo "start-up cloud-service-eureka1 :"
nohup java  -jar cloud-service/cloud-service-eureka/target/cloud.service.eureka.jar --spring.profiles.active=eureka1 >> /opt/logs/microService/lookbi-cloud-microService/cloud-service-eureka1.log &
echo "start-up cloud-service-eureka2 :"
nohup java  -jar cloud-service/cloud-service-eureka/target/cloud.service.eureka.jar --spring.profiles.active=eureka2 >> /opt/logs/microService/lookbi-cloud-microService/cloud-service-eureka2.log &
echo "start-up cloud-service-eureka3 :"
nohup java  -jar cloud-service/cloud-service-eureka/target/cloud.service.eureka.jar --spring.profiles.active=eureka3 >> /opt/logs/microService/lookbi-cloud-microService/cloud-service-eureka3.log &
echo "start-up cloud-service-eureka4 :"
nohup java  -jar cloud-service/cloud-service-eureka/target/cloud.service.eureka.jar --spring.profiles.active=eureka4 >> /opt/logs/microService/lookbi-cloud-microService/cloud-service-eureka4.log &
#nohup java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=17788,suspend=n api-gateway/api-gateway-fat.jar -cluster -conf api-gateway/local.conf  >> stdout.log &

