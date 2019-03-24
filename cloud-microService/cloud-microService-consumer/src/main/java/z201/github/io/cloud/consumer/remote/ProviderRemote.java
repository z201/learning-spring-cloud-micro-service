package z201.github.io.cloud.consumer.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@FeignClient(name = "lookbi-cloud-microService-provider")
public interface ProviderRemote {

	@RequestMapping(value = "/provider/{name}", method = RequestMethod.GET)
	public Object say(@PathVariable("name") String name);

}
