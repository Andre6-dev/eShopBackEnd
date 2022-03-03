package com.eshop.admin.user.controllers;

import com.eshop.admin.user.services.UserService;
import com.eshop.common.entity.Role;
import com.eshop.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String listAll(Model model) {
        List<User> listUsers = userService.listAll();
        User user = new User();
        user.setEnabled(true);
        List<Role> listRoles = userService.listRoles();
        model.addAttribute("listUsers", listUsers);
        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);
        return "users";
    }

    // This method allow us to send the form data and save it into the database
    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes redirectAttributes) {
        System.out.println(user);
        userService.save(user);

        // Alert to confirm that the user has been saved
        redirectAttributes.addFlashAttribute("message", "The user has been saved successfully");

        return "redirect:/users";
    }


}
