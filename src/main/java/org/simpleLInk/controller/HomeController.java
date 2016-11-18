package org.simpleLInk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @RequestMapping(value = "/simpleLink", method = RequestMethod.GET)
    public ModelAndView getHome() {
        ModelAndView mav = new ModelAndView("home");
        mav.addObject("title", "Сокращатель ссылок");
        return mav;
    }
}
