package pl.flez.reactapi.services;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import pl.flez.reactapi.data.User;
import pl.flez.reactapi.repositories.UserRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
}
