/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2016-2016 Mikita Herasiutsin
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
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

/**
 * User controller.
 *
 * @since 1.0
 * @author Mikita Herasiutsin (mikita.herasiutsin@gmail.com)
 * @version $Id$
 */
@Controller
public class UserController {
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private LinkerService linkerService;
    @Autowired
    private SecurityService securityService;

    @GetMapping(value = "/registration")
    public String initRegistrationForm(final Model model) {
        model.addAttribute("userForm", new User());
        return "users/registration";
    }

    @PostMapping(value = "/registration")
    public String processRegistrationForm(
        @ModelAttribute("userForm") final User userForm,
        final BindingResult result,
        final Model model
    ) {
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
    public String login(
        final Model model, final String error, final String logout
    ) {
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
