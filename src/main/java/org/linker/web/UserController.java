package org.linker.web;

import org.linker.model.domain.User;
import org.linker.service.LinkerService;
import org.linker.service.SecurityService;
import org.linker.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

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
    public String processRegistrationForm(
        @ModelAttribute("userForm") User userForm, BindingResult result,
        Model model) {
        this.userValidator.validate(userForm, result);
        if (result.hasErrors()) {
            return "users/registration";
        }
        this.linkerService.saveUser(userForm);
        this.securityService.autologin(userForm.getUsername(),
            userForm.getPasswordConfirm());
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

    @GetMapping(value = "/users/my_links")
    public ModelAndView findLInksByUserId() {
        ModelAndView mav = new ModelAndView("links/linkList");
        mav.addObject("links", this.linkerService.findLinksByUser());
        return mav;
    }
}
