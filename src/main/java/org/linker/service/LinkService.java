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
package org.linker.service;

import java.util.List;
import org.linker.model.domain.Link;
import org.linker.model.domain.User;

/**
 * Link service.
 *
 * @since 1.0
 * @author Mikita Herasiutsin (mikita.herasiutsin@gmail.com)
 * @version $Id$
 */
public interface LinkService {

    /**
     * Find links by user.
     * @return Links found
     */
    List<Link> findLinksByUser(final User user);

    /**
     * Find links by tag's name.
     * @param tagName Tag's name
     * @return Links
     */
    List<Link> findLinksByTagName(final String tagName);

    /**
     * Find link by id.
     * @param id Id
     * @return Link
     */
    Link findLink(final Integer id);

    /**
     * Find most recently added 10 links.
     * @return links
     */
    List<Link> findLastTenLinks();

    /**
     * Save link.
     * @param link Link
     */
    void saveLink(final Link link, final User user);

    /**
     * Redirect.
     * @param shorten Short link
     * @return Actual link
     */
    String redirect(final String shorten);
}
