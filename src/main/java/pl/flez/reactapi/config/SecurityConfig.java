package pl.flez.reactapi.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.InMemoryReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.*;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig  {


    /*@Bean
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .roles("USER")
                .build();
        return new MapReactiveUserDetailsService(user);
    }*/

    private final SuccessAuthenticationHandler successAuthenticationHandler;
    //private final UserDetailsRepositoryReactiveAuthenticationManager authenticationManager;
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {


	         http

            .authorizeExchange().pathMatchers("/login","/logout","/register","/register/activate/{id}","/register/test","/webjars/**" ,"/player" , "/google").permitAll()
                .anyExchange().authenticated()
                .and()
            .formLogin()
           .and().logout() .and()
                .oauth2Login().and().csrf().csrfTokenRepository(CookieServerCsrfTokenRepository.withHttpOnlyFalse());
                     //.clientRegistrationRepository(clientRegistrations())
                    // .authorizedClientService(authorizedClientService());
                //.and()
               //.oauth2ResourceServer()
               // .jwt();


        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Bean
    public ReactiveOAuth2AuthorizedClientService authorizedClientService() {
    return  new InMemoryReactiveOAuth2AuthorizedClientService(clientRegistrations());
    }


    @Bean
    ReactiveClientRegistrationRepository clientRegistrations() {
        ClientRegistration clientRegistration =
                CommonOAuth2Provider.GOOGLE.getBuilder("google")
                        .clientId("657165109801-5ul0g2penvpqud4m4damiu040f3nlfar.apps.googleusercontent.com").clientSecret("KKKTBsm6Xoh9ZuMmdEgi19an").build();
        return new InMemoryReactiveClientRegistrationRepository(clientRegistration);
    }




/*    @Bean
	  public Jackson2RepositoryPopulatorFactoryBean repositoryPopulator(ObjectMapper mapper) throws Exception {
	    Resource sourceData = new ClassPathResource("test-data.json");
	    Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
	    // Set a custom ObjectMapper if Jackson customization is needed
	    factory.setMapper(mapper);
	    factory.setResources(new Resource[] { sourceData });
	    factory.afterPropertiesSet();
	    return factory;
	  }*/
  
}
