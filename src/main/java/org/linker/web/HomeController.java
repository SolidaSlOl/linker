package org.linker.web;

import org.linker.service.LinkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/", "/welcome"})
public class HomeController {
    @Autowired
    private LinkerService linkerService;

    @GetMapping
    public ModelAndView welcome(Model model) {
        ModelAndView mav = new ModelAndView("home");
        mav.addObject("links", linkerService.findLastTenLinks());
        return mav;
    }
}
