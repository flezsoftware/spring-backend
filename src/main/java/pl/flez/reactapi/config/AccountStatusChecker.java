package pl.flez.reactapi.config;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;
@Component
public class AccountStatusChecker implements UserDetailsChecker {
	@Override
	public void check(UserDetails toCheck) {
		// TODO Auto-generated method stub
		//System.out.print("check" + toCheck.getUsername());
		if(!toCheck.isEnabled()) {
			throw new DisabledException("Account is disabled");
		}
//		Mono.just(toCheck).filter(u->u.isEnabled()).switchIfEmpty(Mono.defer(() -> Mono.error(new DisabledException("Account is disabled")))).
//		filter(u->u.isAccountNonExpired()).switchIfEmpty(Mono.defer(() -> Mono.error(new AccountExpiredException("Account is expired")))).
//		filter(u->u.isAccountNonLocked()).switchIfEmpty(Mono.defer(() -> Mono.error(new LockedException("Account is locked")))).
//		filter(u->u.isCredentialsNonExpired()).switchIfEmpty(Mono.defer(() -> Mono.error(new CredentialsExpiredException("Credentials Expired"))));
	}

}
