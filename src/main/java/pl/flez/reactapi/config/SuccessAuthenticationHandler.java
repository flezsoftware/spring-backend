package pl.flez.reactapi.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import pl.flez.reactapi.data.User;
import pl.flez.reactapi.repositories.UserRepository;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
@Component
@RequiredArgsConstructor
public class SuccessAuthenticationHandler extends RedirectServerAuthenticationSuccessHandler {

	private final UserRepository repository;
	
	@Override
	public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
		return repository.findById(((User)authentication.getPrincipal()).getId())
				.flatMap(u->{u.setLastLoginTime(LocalDateTime.now());
				return Mono.just(u);
		}).flatMap(repository::save).flatMap(u->{
			return super.onAuthenticationSuccess(webFilterExchange, authentication);});
	}

}
