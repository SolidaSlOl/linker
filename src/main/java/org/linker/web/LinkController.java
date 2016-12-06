package org.linker.web;

import javax.validation.Valid;
import org.linker.model.domain.Link;
import org.linker.service.ConverterService;
import org.linker.service.LinkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LinkController {
    @Autowired
    private LinkerService linkerService;
    @Autowired
    private ConverterService converterService;

    @GetMapping(value = "/{shorten}")
    public String redirectToActualLink(@PathVariable("shorten") String shortLink){
        Integer linkId = this.converterService.decode(shortLink);
        Link link = this.linkerService.findLink(linkId);
        link.addClick();
        this.linkerService.updateLink(link);
        return "redirect:" + link.getOriginal();
    }

    @GetMapping(value = "/links/{id}")
    public ModelAndView findLink(@PathVariable("id") Integer id){
        ModelAndView mav = new ModelAndView("links/linkDetails");
        mav.addObject("link", this.linkerService.findLink(id));
        return mav;
    }

    @GetMapping(value = "/links/new")
    public String initLinkCreateForm(final Model model) {
        final Link link = new Link();
        model.addAttribute("link", link);
        return "links/createOrUpdateLinkForm";
    }

    @PostMapping(value = "/links/new")
    public String processLinkCreateForm(@Valid Link link, BindingResult result,
        final Model model) {
        if (result.hasErrors()) {
            return "links/createOrUpdateLinkForm";
        }
        this.linkerService.saveLink(link);
        return "redirect:/links/" + link.getId();
    }

    @GetMapping(value = "/links/{id}/edit")
    public String initLinkUpdateForm(@PathVariable("id") Integer id, Model model) {
        Link link = this.linkerService.findLink(id);
        model.addAttribute("link", link);
        return "links/createOrUpdateLinkForm";
    }

    @PostMapping(value = "/links/{id}/edit")
    public String processLinkUpdateForm(@Valid Link link, BindingResult result,
        @PathVariable("id") Integer id) {
        if (result.hasErrors()) {
            return "links/createOrUpdateLinkForm";
        }
        link.setId(id);
        this.linkerService.saveLink(link);
        return "redirect:/links/" + id;
    }
}
