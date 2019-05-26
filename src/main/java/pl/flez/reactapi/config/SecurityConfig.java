package pl.flez.reactapi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;



@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig  {
	

	
	@Bean
    public MapReactiveUserDetailsService userDetailsService() {
       UserDetails user = User.withDefaultPasswordEncoder()
           .username("user")
           .password("user")
            .roles("USER")
           .build();
        return new MapReactiveUserDetailsService(user);
    }
	


    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.csrf().disable()
            .authorizeExchange().pathMatchers("/login","/logout","/register","/register/activate/{id}","/register/test","/webjars/**" ,"/player" , "/video").permitAll()
                .anyExchange().authenticated()
                .and()
     //       .httpBasic().and()
            .formLogin()
        /*    .authenticationFailureHandler(new FailureAuthenticationHandler("/login?error"))
            .authenticationSuccessHandler(successAuthenticationHandler)*/
            .and().logout();
        return http.build();
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
