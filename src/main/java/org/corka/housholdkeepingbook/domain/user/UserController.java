package org.corka.housholdkeepingbook.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final String USERS = "users";

    @Autowired
    private UserService userService;

    @GetMapping
    public String viewUsers(Model model) {
        model.addAttribute("userList", this.userService.getAllActiveUsers());
        model.addAttribute("newUser", new User());
        return USERS;
    }

    @PostMapping
    public String addUser(@ModelAttribute User newUser, Model model) {
        this.userService.addUser(newUser);
        return this.viewUsers(model);
    }

    @GetMapping("/{id}/delete")
    public String deleteCategory(@PathVariable long id) {
        this.userService.deleteUser(id);
        return "redirect:..";
    }
}
