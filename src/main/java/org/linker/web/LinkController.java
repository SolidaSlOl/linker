package org.linker.web;

import org.linker.model.domain.Link;
import org.linker.service.ConverterService;
import org.linker.service.LinkerService;
import org.linker.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class LinkController {
    @Autowired
    LinkerService linkerService;
    @Autowired
    SecurityService securityService;
    @Autowired
    ConverterService converterService;

    @GetMapping(value = "/{shortLink}")
    public String redirectToActualLink(@PathVariable("shortLink") String shortLink){
        Integer linkId = this.converterService.decode(shortLink);
        return "redirect:" + this.linkerService.findLink(linkId).getActual();
    }

    @GetMapping(value = "/links/{id}")
    public ModelAndView findLink(@PathVariable("id") Integer id){
        ModelAndView mav = new ModelAndView("links/linkDetails");
        mav.addObject(linkerService.findLink(id));
        return mav;
    }

    @Secured("USER_ROLE")
    @GetMapping(value = "/links/create")
    public String initRegistrationForm(Model model) {
        model.addAttribute("link", new Link());
        return "links/createLinkForm";
    }

    @PostMapping(value = "/links/create")
    public String processRegistrationForm(@Valid Link link, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "links/createLinkForm";
        }
        linkerService.saveLink(link);
        return "redirect:/welcome";
    }
}