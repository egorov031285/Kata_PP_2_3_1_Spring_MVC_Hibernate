package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.servis.UserService;

@Controller
@RequestMapping
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public String all(Model model) {
        model.addAttribute("users", userService.findAll());
        return "all";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping("/all")
    public String create(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/all";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam(value = "id", required = true, defaultValue = "") int id, Model model) {
        User user = userService.deleteById(id);
        model.addAttribute("message", (null == user) ?
                "User are not exists!" :
                "User " + user.getName() + " successfully deleted!");
        return "delete";
    }

    @GetMapping("/edit")
    public String edit(@ModelAttribute("user") User user) {
        return "edit";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute("user") User user, Model model) {
        userService.update(user);
        model.addAttribute("message",
                "User " + user.getName() + " successfully update!");
        return "delete";
    }
}
