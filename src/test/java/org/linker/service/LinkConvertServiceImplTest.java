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

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Test case for {@link LinkConvertServiceImpl}.
 *
 * @since 1.0
 * @author Mikita Herasiutsin (mikita.herasiutsin@gmail.com)
 * @version $Id$
 */
public class LinkConvertServiceImplTest {
    private final LinkConvertServiceImpl converter = new LinkConvertServiceImpl();

    @Test
    public void testEncode_whenIdGreaterThanZero() {
        assertEquals(this.converter.encode(2), "4");
        assertEquals(this.converter.encode(456), "bZ");
        assertEquals(this.converter.encode(159841), "3dv9");
        assertEquals(this.converter.encode(875683234), "4ztrNx");
    }

    @Test
    public void testDecode_whenFormatIsCorrect() {
        assertEquals(this.converter.decode("4"), 2);
        assertEquals(this.converter.decode("bZ"), 456);
        assertEquals(this.converter.decode("3dv9"), 159841);
        assertEquals(this.converter.decode("4ztrNx"), 875683234);
    }

    @Test
    public void testDecode_whenCorrectStringFormat() {
        assertFalse(this.converter.decode("pgK8p") < 0);
        assertFalse(this.converter.decode("3jd_") < 0);
        assertFalse(this.converter.decode("DH8-d") < 0);
        assertFalse(this.converter.decode("cM8") < 0);
        assertFalse(this.converter.decode("37fg9") < 0);
    }

    @Test
    public void testEncode_whenIdNegativeInteger() {
        assertEquals(this.converter.encode(-1), "");
        assertEquals(this.converter.encode(-456), "");
        assertEquals(this.converter.encode(-456789), "");
        assertEquals(this.converter.encode(-678987645), "");
    }

    @Test
    public void testEncode_whenIdNull() {
        assertEquals(this.converter.encode(0), "");
    }

    @Test
    public void testDecode_whenIncorrectStringFormat() {
        assertFalse(this.converter.decode("aUI") > 0);
        assertFalse(this.converter.decode("(sa") > 0);
        assertFalse(this.converter.decode("!49") > 0);
        assertFalse(this.converter.decode("123") > 0);
        assertFalse(this.converter.decode(">?") > 0);
    }

    @Test
    public void testDecode_whenEmptyStrings() {
        assertEquals(this.converter.decode(""), 0);
    }
}
