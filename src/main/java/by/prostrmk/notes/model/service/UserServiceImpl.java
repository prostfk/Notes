package by.prostrmk.notes.model.service;

import by.prostrmk.notes.model.entity.User;
import by.prostrmk.notes.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceImpl implements UserService {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserRepository repository;

    @Override
    public User getUser(String username) {
        return repository.findUserByUsername(username);
    }
}
