#!/bin/sh -l
export JAVA_HOME=/opt/JavaHome/jdk1.8.0_151
export PATH=$PATH:.
echo "start-up cloud-service-zipkin :"
nohup java  -jar cloud-service/cloud-service-zipkin/target/cloud.service.zipkin.jar >> /opt/logs/microService/lookbi-cloud-microService/cloud-service-zipkin.log &

#nohup java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=17788,suspend=n api-gateway/api-gateway-fat.jar -cluster -conf api-gateway/local.conf  >> stdout.log &

