package by.prostrmk.notes.controller;

import by.prostrmk.notes.model.entity.Note;
import by.prostrmk.notes.model.entity.Token;
import by.prostrmk.notes.model.entity.User;
import by.prostrmk.notes.model.repository.NoteRepository;
import by.prostrmk.notes.model.repository.TokenRepository;
import by.prostrmk.notes.model.repository.UserRepository;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@Secured(value = "ROLE_USER")
@Controller
public class UserController {



    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/addNote")
    public ModelAndView addNote(){
        return new ModelAndView("addNote", "note", new Note());
    }

    @PostMapping(value = "/addNote")
    public String postAddNote(Note note){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByUsername(name);
        note.setUser(user);
        noteRepository.save(note);
        return "redirect:/myNotes";
    }

    @GetMapping(value = "/myNotes")
    public ModelAndView getMyNotes(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByUsername(name);
        List<Note> notesByUser = noteRepository.findNotesByUser(user);
        Collections.reverse(notesByUser);
        return new ModelAndView("myNotes", "notes", notesByUser);
    }

    @GetMapping(value = "/change/{id}")
    public ModelAndView getRemoveNote(@PathVariable String id){
        Note noteById = noteRepository.findNoteById(id);
        return new ModelAndView("changeNote", "note", noteById);
    }

    @PostMapping(value = "/change/{id}")
    public String postChange(@PathVariable String id, Note note){
        if (note.getBody().length()>1&& note.getHead().length()>1){
            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userRepository.findUserByUsername(name);
            note.setUser(user);
            noteRepository.save(note);
        }
        return "redirect:/myNotes";
    }

    @GetMapping(value = "/delete/{id}")
    public String getDeletePage(@PathVariable String id){
        Note noteById = noteRepository.findNoteById(id);
        noteRepository.delete(noteById);
        return "redirect:/myNotes";
    }

    @GetMapping(value = "/search")
    public ModelAndView searchNotes(@RequestParam(value = "searchString") String searchString){
        ModelAndView mav = new ModelAndView("myNotes");
        List<Note> notes = noteRepository.findNotesByHeadIsLikeIgnoreCase(searchString);
        mav.addObject("notes", notes);
        return mav;
    }




}
