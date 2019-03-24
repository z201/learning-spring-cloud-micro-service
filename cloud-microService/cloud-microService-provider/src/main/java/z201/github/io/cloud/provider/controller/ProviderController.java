package z201.github.io.cloud.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/provider")
public class ProviderController {
	

	@GetMapping(value="/{name}")
	public Object say(@PathVariable("name") String name) {
		return  System.currentTimeMillis() + "  -  " + name;
	}

}
