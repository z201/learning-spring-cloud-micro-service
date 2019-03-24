export JAVA_HOME=/opt/JavaHome/jdk1.8.0_151
export PATH=$PATH:.
echo "start-up cloud-service-zuul :"
nohup java  -jar cloud-service/cloud-service-zuul/target/cloud.service.zuul.jar >> /opt/logs/microService/lookbi-cloud-microService/cloud-service-zuul.log &

#nohup java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=17788,suspend=n api-gateway/api-gateway-fat.jar -cluster -conf api-gateway/local.conf  >> stdout.log &

