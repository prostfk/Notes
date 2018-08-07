package by.prostrmk.notes.model.repository;

import by.prostrmk.notes.model.entity.Token;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TokenRepository extends MongoRepository<Token, String> {

    Token findTokenById(String id);
    List<Token> findTokensByUserId(String id);

}
