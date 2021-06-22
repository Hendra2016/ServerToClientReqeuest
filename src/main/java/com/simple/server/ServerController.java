package com.simple.server;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/server")
public class ServerController {

	@Autowired
	private RestTemplate template;

	@RequestMapping("/toclient")
	public Map<String, String> getHeaderClient() {
		Map<String, String> map = new HashMap<String, String>();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> http = new HttpEntity<String>(headers);
		ResponseEntity<String> response = template.exchange("http://localhost:8081/client/toserver", HttpMethod.GET,
				http, new ParameterizedTypeReference<String>() {
				}, String.class);

		map.put("body", response.getBody());
		response.getHeaders().forEach((k,v) -> System.out.println("Key ="+k+", value ="+v));
		return map;
	}

}
