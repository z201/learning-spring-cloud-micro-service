export JAVA_HOME=/opt/JavaHome/jdk1.8.0_151
export PATH=$PATH:.
echo "start-up cloud-service-config :"
nohup java  -jar cloud-service/cloud-service-config/target/cloud.service.config.jar >> /opt/logs/microService/lookbi-cloud-microService/cloud-service-config.log &

#nohup java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=17788,suspend=n api-gateway/api-gateway-fat.jar -cluster -conf api-gateway/local.conf  >> stdout.log &

