package org.linker.web;

import org.linker.model.domain.User;
import org.linker.service.LinkerService;
import org.linker.service.SecurityService;
import org.linker.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    private UserValidator userValidator;

    @Autowired
    private LinkerService linkerService;

    @Autowired
    private SecurityService securityService;

    @GetMapping(value = "/registration")
    public String initRegistrationForm(Model model) {
        model.addAttribute("userForm", new User());

        return "users/registration";
    }

    @PostMapping(value = "/registration")
    public String processRegistrationForm(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "users/registration";
        }

        linkerService.saveUser(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @GetMapping(value = "/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "users/login";
    }
}