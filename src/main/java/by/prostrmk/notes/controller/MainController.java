package by.prostrmk.notes.controller;

import by.prostrmk.notes.model.entity.User;
import by.prostrmk.notes.model.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/registration")
    public ModelAndView registration(){
        return new ModelAndView("registration", "user", new User());
    }

    @PostMapping(value = "/registration")
    public String postRegistration(User user){
        if (user.getPassword().length() > 2 && user.getUsername().length() > 4 && userRepository.findUserByUsername(user.getUsername())==null){
            user.setPassword(DigestUtils.md2Hex(user.getPassword()));
            userRepository.save(user);
            return "redirect:/";
        }
        return "redirect:/registration";
    }

}
