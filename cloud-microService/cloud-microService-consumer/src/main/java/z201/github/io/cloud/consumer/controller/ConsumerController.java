package z201.github.io.cloud.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import z201.github.io.cloud.consumer.remote.ProviderRemote;

@RestController
@RequestMapping(value = "/consumer")
public class ConsumerController {
	
	@Autowired
	private ProviderRemote providerRemote ;
	
	@GetMapping(value="/provider/{name}")
	public Object router(@PathVariable("name") String name) {
		// 根据应用名称调用服务
		String msg = " consumer --> " + providerRemote.say(name);
		return msg;
	}

}
