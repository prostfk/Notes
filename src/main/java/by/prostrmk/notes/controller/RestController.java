package by.prostrmk.notes.controller;

import by.prostrmk.notes.model.entity.Note;
import by.prostrmk.notes.model.entity.User;
import by.prostrmk.notes.model.repository.NoteRepository;
import by.prostrmk.notes.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/rest")
public class RestController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NoteRepository noteRepository;


    @GetMapping(value = "/users", produces = "application/json")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping(value = "/myNotes", produces = "application/json")
    public List<Note> getNotes(@RequestBody User user){
        if (user!=null){
            User userByUsername = userRepository.findUserByUsername(user.getUsername());
            return noteRepository.findNotesByUser(userByUsername);
        }
        return Collections.emptyList();
    }



}
