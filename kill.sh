#ps -ef | grep "java -jar api-gateway/api-gateway-fat.jar"| awk '{print $2}'|xargs kill -9
#ps -ef | grep "java -jar versions-service/versions-service-fat.jar " | awk '{print $2}'|xargs kill -9

kill -s 9 `ps -aux | grep " cloud-service/cloud-service-eureka/target/cloud.service.eureka.jar " | awk '{print $2}'`
kill -s 9 `ps -aux | grep " cloud-service/cloud-service-config/target/cloud.service.config.jar " | awk '{print $2}'`
kill -s 9 `ps -aux | grep " cloud-service/cloud-service-zipkin/target/cloud.service.zipkin.jar " | awk '{print $2}'`
kill -s 9 `ps -aux | grep " cloud-service/cloud-service-zuul/target/cloud.service.zuul.jar " | awk '{print $2}'`

