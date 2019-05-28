package pl.flez.reactapi.controllers;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;
import pl.flez.reactapi.data.User;
import pl.flez.reactapi.services.UserService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping
    public Flux<User> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Mono<User> findOne(@PathVariable("id") ObjectId id) {
        return service.findById(id);
    }

    @PostMapping
    public Mono<User> save(@Valid @RequestBody User user) {
        return service.save(user);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteById(@PathVariable("id") ObjectId id) {
        return service.deleteById(id);
    }

    @GetMapping("/principal/pure")
    public Mono<Principal> principal(Mono<Principal> principal) {
        return principal;
    }

    @GetMapping("/principal")
    public Mono<User> user() {
        return service.getUser();
    }

    @GetMapping("/principal/update")
    public Mono<User> userUpdate() {
        return service.getUpdate();
    }

}
