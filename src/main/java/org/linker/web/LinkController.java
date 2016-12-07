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

/**
 * Link controller.
 *
 * @since 1.0
 * @author Mikita Herasiutsin (mikita.herasiutsin@gmail.com)
 * @version $Id$
 */
@Controller
public class LinkController {
    @Autowired
    private LinkerService linkerService;
    @Autowired
    private ConverterService converterService;

    @GetMapping(value = "/{shorten}")
    public String redirectToActualLink(
        @PathVariable("shorten") final String shortLink
    ) {
        Integer linkId = this.converterService.decode(shortLink);
        Link link = this.linkerService.findLink(linkId);
        link.addClick();
        this.linkerService.updateLink(link);
        return "redirect:" + link.getOriginal();
    }

    @GetMapping(value = "/links/{id}")
    public ModelAndView findLink(@PathVariable("id") final Integer id) {
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
    public String processLinkCreateForm(
        @Valid final Link link,
        final BindingResult result,
        final Model model
    ) {
        if (result.hasErrors()) {
            return "links/createOrUpdateLinkForm";
        }
        this.linkerService.saveLink(link);
        return "redirect:/links/" + link.getId();
    }

    @GetMapping(value = "/links/{id}/edit")
    public String initLinkUpdateForm(
        @PathVariable("id") final Integer id, final Model model
    ) {
        Link link = this.linkerService.findLink(id);
        model.addAttribute("link", link);
        return "links/createOrUpdateLinkForm";
    }

    @PostMapping(value = "/links/{id}/edit")
    public String processLinkUpdateForm(
        @Valid final Link link,
        final BindingResult result,
        @PathVariable("id") final Integer id
    ) {
        if (result.hasErrors()) {
            return "links/createOrUpdateLinkForm";
        }
        link.setId(id);
        this.linkerService.saveLink(link);
        return "redirect:/links/" + id;
    }
}
