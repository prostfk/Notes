package by.prostrmk.notes.controller;

import by.prostrmk.notes.model.entity.User;
import by.prostrmk.notes.model.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/registration")
    public ModelAndView registration(){
        return new ModelAndView("registration", "user", new User());
    }

    @PostMapping(value = "/registration")
    public String postRegistration(User user){
        if (user.getPassword().length() > 2 && user.getUsername().length() > 4 && userRepository.findUserByUsername(user.getUsername())==null){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return "redirect:/";
        }
        return "redirect:/registration";
    }

    @GetMapping(value = "/auth")
    public ModelAndView getAuth(){
        return new ModelAndView("auth","user", new User());
    }

    @PostMapping(value = "/auth")
    public String postAuth(User user, HttpSession session){
        User userByUsername = userRepository.findUserByUsername(user.getUsername());
        if (userByUsername!=null){
            if (passwordEncoder.encode(user.getPassword()).equals(userByUsername.getPassword())){
                session.setAttribute("user", userByUsername);
                return "redirect:/";
            }
        }
        return "redirect:/auth";
    }

    @GetMapping(value = "logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/auth";
    }

}
