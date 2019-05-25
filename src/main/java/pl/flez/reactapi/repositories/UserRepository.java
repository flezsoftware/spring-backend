package pl.flez.reactapi.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import pl.flez.reactapi.data.User;
@Repository
public interface UserRepository extends ReactiveMongoRepository<User, ObjectId> {

}
