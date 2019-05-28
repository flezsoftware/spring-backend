package pl.flez.reactapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;
import pl.flez.reactapi.services.UserSecurityService;

@Configuration
public class AuthConfig {

    @Bean
    UserDetailsRepositoryReactiveAuthenticationManager authManager(AccountStatusChecker authManager, UserSecurityService userDetailsService) {
        UserDetailsRepositoryReactiveAuthenticationManager manager = new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
      //  manager.setPostAuthenticationChecks(authManager);
        return manager;
    }
}
