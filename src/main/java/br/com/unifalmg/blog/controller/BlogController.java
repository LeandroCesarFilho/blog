package br.com.unifalmg.blog.controller;

import br.com.unifalmg.blog.entity.User;
import br.com.unifalmg.blog.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@Controller
@AllArgsConstructor // Feito pelo Lombok
public class BlogController {

    private final UserService service;

    @GetMapping("/")
    public String getHome(){
        return "home";
    }

    @GetMapping("/users")
    public String user(Model model){
        List<User> users = service.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/user")
    public String user(User user){
        return "newuser";
    }

    @PostMapping("/user")
    public String newUser(@ModelAttribute("user") User user) {
        log.info("Entrou no cadastro de usuário");
        User addedUser = service.add(user);
        return "redirect:/user/" + addedUser.getId(); // Talvez seja a barra
    }

    @GetMapping("/user/{id}")
    public String showUser(@PathVariable("id") Integer id,
                           Model model) {
        User user = service.findById(id);
        model.addAttribute("user", user);
        return "showUser";
    }

    @GetMapping("/edituser/{id}")
    public String editUser(@PathVariable("id") Integer id,
                           Model model) {
        User user = service.findById(id);
        model.addAttribute("user",user);
        return "editUser";
    }

    @PutMapping("/edituser/{id}")
    public String editUser(@ModelAttribute("user") User user) {
        log.info("Usuário alterado");
        User editedUser = service.edit(user.getId(),user);
        return  "redirect:/user/" + editedUser.getId();
    }
}
