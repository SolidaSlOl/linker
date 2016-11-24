package org.linker.web;

import org.linker.service.LinkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("tags/")
public class TagController {
    @GetMapping(value = "{name}")
    public ModelAndView redirectToActualLink(@PathVariable("name") String name){ //todo
        ModelAndView mav = new ModelAndView("links/linkList");
//        mav.addObject(linkerService.findLi(name));
        return mav;
    }

    @Autowired
    LinkerService linkerService;
}
