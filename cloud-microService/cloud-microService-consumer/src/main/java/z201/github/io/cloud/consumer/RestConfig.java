package z201.github.io.cloud.consumer;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

    /**
     * @return
     * @LoadBalanced，经过这个修饰的restTemplate，就不是普通的restTemplate了，而是具备负载均衡能力的restTemplate
     *          即每次都会用负载均衡算法，从可用服务列表中，挑一个进行调用。
     */
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
