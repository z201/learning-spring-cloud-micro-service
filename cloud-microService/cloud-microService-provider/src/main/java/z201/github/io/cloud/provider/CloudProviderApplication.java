package z201.github.io.cloud.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;


/**
 *
 * @author z201
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CloudProviderApplication {

	public static void main(String[] args) {

		SpringApplication.run(CloudProviderApplication.class, args);
	}
	
}
