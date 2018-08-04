package by.prostrmk.notes.model.repository;

import by.prostrmk.notes.model.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User,String> {

    User findUserById(String id);
    List<User> findAll();
    User findUserByUsername(String username);

}
