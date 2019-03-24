export JAVA_HOME=/opt/JavaHome/jdk1.8.0_151
export PATH=$PATH:.
echo "start-up cloud-microService-consumer :"
nohup java -jar cloud-microService/cloud-microService-consumer/target/cloud.microService.consumer.jar >> /opt/logs/microService/lookbi-cloud-microService/cloud-microService-consumer.log &

#nohup java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=17788,suspend=n api-gateway/api-gateway-fat.jar -cluster -conf api-gateway/local.conf  >> stdout.log &

