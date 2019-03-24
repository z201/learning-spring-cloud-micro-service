#!/bin/sh -l
export JAVA_HOME=/opt/JavaHome/jdk1.8.0_151
export PATH=$PATH:.
echo "start-up cloud-microService-provider :"
nohup java -jar cloud-microService/cloud-microService-provider/target/cloud.microService.provider.jar >> /opt/logs/microService/lookbi-cloud-microService/cloud-microService-provider.log &

#nohup java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=17788,suspend=n api-gateway/api-gateway-fat.jar -cluster -conf api-gateway/local.conf  >> stdout.log &

