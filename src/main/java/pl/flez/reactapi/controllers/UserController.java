package pl.flez.reactapi.controllers;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.flez.reactapi.data.User;
import pl.flez.reactapi.services.RestClient;
import pl.flez.reactapi.services.UserService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    private final RestClient restClient;

    @GetMapping
    public Flux<User> findAll(){
        return service.findAll();
    }
    @GetMapping("/{id}")
    public Mono<User> findOne(@PathVariable("id") ObjectId id){
        return service.findById(id);
    }
    @PostMapping
    public Mono<User> save(@Valid @RequestBody User user){
        return  service.save(user);
    }
    @DeleteMapping("/{id}")
    public Mono<Void> deleteById(@PathVariable("id") ObjectId id){
        return service.deleteById(id);
    }

    @GetMapping("/principal")
    public Mono<Principal> principal(Mono<Principal> principal){
        return principal;
    }


}
