package pl.flez.reactapi.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

@Controller
public class OauthController {

    @GetMapping("/redirector")
    public Mono<Void> drim(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.SEE_OTHER);
        response.getHeaders().add(HttpHeaders.LOCATION,  exchange.getRequest().getQueryParams().getFirst("redirect_uri"));
        return response.setComplete();
    }

    @GetMapping("/google")
    public Mono<Void> redir(ServerWebExchange exchange) {
      return  exchange.getSession().map(s-> s.getAttributes()).doOnNext(a->a.put("SPRING_SECURITY_SAVED_REQUEST", "/redirector?redirect_uri=" + exchange.getRequest().getQueryParams().getFirst("redirect_uri"))).flatMap(s->{
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.SEE_OTHER);
            response.getHeaders().add(HttpHeaders.LOCATION, "/oauth2/authorization/google");
            return response.setComplete();
        });
    }
}
