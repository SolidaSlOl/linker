package org.linker.web;

import org.linker.model.domain.Link;
import org.linker.service.ConverterService;
import org.linker.service.LinkerService;
import org.linker.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(value = "/{shorten}")
    public String redirectToActualLink(@PathVariable("shorten") String shortLink){
        Integer linkId = converterService.decode(shortLink);
        Link link = linkerService.findLink(linkId);
        link.addClick();
        linkerService.updateLink(link);
        return "redirect:" + link.getOriginal();
    }

    @GetMapping(value = "/links/{id}")
    public ModelAndView findLink(@PathVariable("id") Integer id){
        ModelAndView mav = new ModelAndView("links/linkDetails");
        mav.addObject("link", linkerService.findLink(id));
        return mav;
    }

    @GetMapping(value = "/links/create")
    public String initRegistrationForm(Model model) {
        Link link = new Link();
        model.addAttribute("link", link);
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