package pl.flez.reactapi.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationFailureHandler;

import reactor.core.publisher.Mono;

public class FailureAuthenticationHandler extends RedirectServerAuthenticationFailureHandler {

	public FailureAuthenticationHandler(String location) {
		super(location);
	}

	@Override
	public Mono<Void> onAuthenticationFailure(WebFilterExchange webFilterExchange, AuthenticationException exception) {
		return webFilterExchange.getExchange().getSession().flatMap(c-> {				
				c.getAttributes().put("SPRING_SECURITY_LAST_EXCEPTION", exception.getMessage());
				return super.onAuthenticationFailure(webFilterExchange, exception);
			});
	}
}
