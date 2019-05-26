package pl.flez.reactapi.services;

import java.util.Base64;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Component
public class RestClient {
	//@PostConstruct
	public String testAndGetCookiePage() {
		String url = "http://localhost:8080/user";
		String username = "user";
		String password = "user";
		
		StringBuilder sb = new StringBuilder();
		sb.append(username);
		sb.append(":");
		sb.append(password);			
		String creds = sb.toString();			
		String token = new String(Base64.getEncoder().encode(creds.getBytes()));			
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + token);
		
		ResponseEntity<String> ret = doRequest(url, headers);
		System.out.println(ret.getBody());
		System.out.println(ret.getHeaders());


		ret.getHeaders().entrySet().forEach(e -> System.out.println( e.getKey() +e.getValue()));


		System.out.println();

		String cookie = ret.getHeaders().get("Set-Cookie").stream().collect(Collectors.joining(";"));
		
		String[] cookies = cookie.split(";");
	
		System.out.println(cookies[0]);
		
		headers = new HttpHeaders();
		headers.add("Cookie", cookies[0]);

		ret = doRequest(url, headers);
		System.out.println(ret.getBody());
		System.out.println(ret.getHeaders());		
		return cookies[0] + " - " + ret.getBody();
	}
	
	public ResponseEntity<String> doRequest(String url, HttpHeaders headers) {		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<String> ret = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
		return ret;
	}
}
