package pl.flez.reactapi.controllers;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;
import pl.flez.reactapi.data.User;
import pl.flez.reactapi.services.UserService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

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

    @GetMapping("/dwa/")
    public Flux<User> findAll2(){
        return service.findAll();
    }

}
