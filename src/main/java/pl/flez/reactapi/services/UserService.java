package pl.flez.reactapi.services;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Service;
import pl.flez.reactapi.data.*;
import pl.flez.reactapi.repositories.UserRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public Flux<User> findAll() {
        return repository.findAll();
    }

    public Mono<User> save(User user) {
        return repository.save(user);
    }

    public Mono<User> findById(ObjectId id) {
        return repository.findById(id);
    }

    public Mono<Void> deleteById(ObjectId id) {
        return repository.deleteById(id);
    }

    public Mono<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public  Mono<User> getUser(){
        return ReactiveSecurityContextHolder.getContext().map(SecurityContext::getAuthentication).map(Authentication::getPrincipal).flatMap(fm ->{
            if(fm.getClass().isAssignableFrom(DefaultOidcUser.class)){
                DefaultOidcUser oidcUser =   ((DefaultOidcUser) fm);
                return createOauth2GooleUser(oidcUser);
            } else
                return Mono.just((User)fm);
        });
    }

    public  Mono<User> getUpdate (){
        return ReactiveSecurityContextHolder.getContext().map(SecurityContext::getAuthentication).map(Authentication::getPrincipal).flatMap(fm ->{
            if(fm.getClass().isAssignableFrom(DefaultOidcUser.class)){
                DefaultOidcUser oidcUser =   ((DefaultOidcUser) fm);
                return updateOauth2GooleUser(oidcUser);
            } else
                return Mono.just((User)fm);
        });
    }

    Mono<User> updateOauth2GooleUser(DefaultOidcUser oidcUser){
        return this.findByEmail(oidcUser.getEmail()).flatMap(u->this.save(defauiltOidUserToUser(oidcUser,u,u.getAuthorities()))); //.switchIfEmpty(this.save(defauiltOidUserToUser(oidcUser)));
    }

    Mono<User> createOauth2GooleUser(DefaultOidcUser oidcUser){
        return this.findByEmail(oidcUser.getEmail()).switchIfEmpty(this.save(defauiltOidUserToUser(oidcUser,new User(), oidcUser.getAuthorities().stream().map(authority->{ Role r = new Role(); r.setAuthority(((GrantedAuthority) authority).getAuthority()); return r; }).collect(Collectors.toSet()))));
    }

    private static User defauiltOidUserToUser(DefaultOidcUser oidcUser,User user,Set<Role> roles) {
        user.setType(UserType.OAUTH2);
        user.setEmail(oidcUser.getEmail());
        user.setUsername(oidcUser.getFullName());
        AdditionalGoogleUserData userData = new AdditionalGoogleUserData();
        userData.setFullName(oidcUser.getFullName());
        userData.setFamilyName(oidcUser.getFamilyName());
        userData.setGivenName(oidcUser.getGivenName());
        userData.setPicture(oidcUser.getPicture());
        userData.setLocale(oidcUser.getLocale());
        userData.setIss((String)oidcUser.getAttributes().get("iss"));
        userData.setAzp((String)oidcUser.getAttributes().get("azp"));
        userData.setSubject(oidcUser.getSubject());
        user.setUserData(userData);
        user.setAuthorities((HashSet<Role>) roles);
        return  user;
    }

}
