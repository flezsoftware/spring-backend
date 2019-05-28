package pl.flez.reactapi.services;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.flez.reactapi.repositories.UserRepository;
import reactor.core.publisher.Mono;
@Service
@RequiredArgsConstructor
public class UserSecurityService implements ReactiveUserDetailsService {

	private final UserRepository repository;
	
	@Override
	public Mono<UserDetails> findByUsername(String username) {
		return repository.findByUsername(username)
//				.switchIfEmpty(Mono.defer(() -> Mono.error(new BadCredentialsException("Invalid Credentials")))).
//				filter(u->u.isEnabled()).switchIfEmpty(Mono.defer(() -> Mono.error(new DisabledException("Account is disabled")))).
//				filter(u->u.isAccountNonExpired()).switchIfEmpty(Mono.defer(() -> Mono.error(new AccountExpiredException("Account is expired")))).
//				filter(u->u.isAccountNonLocked()).switchIfEmpty(Mono.defer(() -> Mono.error(new LockedException("Account is locked")))).
//				filter(u->u.isCredentialsNonExpired()).switchIfEmpty(Mono.defer(() -> Mono.error(new CredentialsExpiredException("Credentials Expired"))))
				.map(UserDetails.class::cast);
	}

}
