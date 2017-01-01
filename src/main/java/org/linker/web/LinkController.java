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
import org.linker.service.LinkService;
import org.linker.service.UserService;
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
    private static final String CREATE_OR_UPDATE_LINK_FORM = "links/createOrUpdateLinkForm";

    @Autowired
    private LinkService linkService;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/{shorten}")
    public final String redirectToActualLink(
        @PathVariable("shorten") final String shortLink
    ) {
        return "redirect:" + this.linkService.redirect(shortLink);
    }

    @GetMapping(value = "/links/{id}")
    public final ModelAndView findLink(@PathVariable("id") final Integer id) {
        ModelAndView mav = new ModelAndView("links/linkDetails");
        mav.addObject("link", this.linkService.findLink(id));
        return mav;
    }

    @GetMapping(value = "/links/new")
    public final String initLinkCreateForm(final Model model) {
        final Link link = new Link();
        model.addAttribute("link", link);
        return CREATE_OR_UPDATE_LINK_FORM;
    }

    @PostMapping(value = "/links/new")
    public final String processLinkCreateForm(
        @Valid final Link link,
        final BindingResult result,
        final Model model
    ) {
        if (result.hasErrors()) {
            return CREATE_OR_UPDATE_LINK_FORM;
        }
        this.linkService.saveLink(link, this.userService.findCurrentUser());
        return "redirect:/links/" + link.getId();
    }

    @GetMapping(value = "/links/{id}/edit")
    public final String initLinkUpdateForm(
        @PathVariable("id") final Integer id, final Model model
    ) {
        Link link = this.linkService.findLink(id);
        model.addAttribute("link", link);
        return CREATE_OR_UPDATE_LINK_FORM;
    }

    @PostMapping(value = "/links/{id}/edit")
    public final String processLinkUpdateForm(
        @Valid final Link link,
        final BindingResult result,
        @PathVariable("id") final Integer id
    ) {
        if (result.hasErrors()) {
            return CREATE_OR_UPDATE_LINK_FORM;
        }
        link.setId(id);
        this.linkService.saveLink(link, this.userService.findCurrentUser());
        return "redirect:/links/" + id;
    }

    @GetMapping(value = "/users/my_links")
    public final ModelAndView findLinksByUserId() {
        ModelAndView mav = new ModelAndView("links/linkList");
        mav.addObject("links", this.linkService.findLinksByUser(
                this.userService.findCurrentUser()
            )
        );
        return mav;
    }
}
