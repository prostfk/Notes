package by.prostrmk.notes.controller;

import by.prostrmk.notes.model.entity.Note;
import by.prostrmk.notes.model.entity.User;
import by.prostrmk.notes.model.repository.NoteRepository;
import by.prostrmk.notes.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NoteRepository noteRepository;

    @GetMapping(value = "/rest/users", produces = "application/json")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping(value = "/rest/myNotes", produces = "application/json")
    public List<Note> getNotes(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User userByUsername = userRepository.findUserByUsername(name);
        return noteRepository.findNotesByUser(userByUsername);
    }

}
