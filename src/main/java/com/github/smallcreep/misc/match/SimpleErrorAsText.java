/**
 * MIT License
 *
 * Copyright (c) 2017, Ilia Rogozhin (ilia.rogozhin@gmail.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall
 * be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.smallcreep.misc.match;

import java.io.IOException;
import org.cactoos.Text;
import org.cactoos.text.FormattedText;

/**
 * Create origin error, for assert.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class SimpleErrorAsText implements Text {

    /**
     * Origin text.
     */
    private final Text origin;

    /**
     * Public Ctor.
     * @param expected Expected object
     * @param actual Actual object
     */
    public SimpleErrorAsText(final Object expected, final Object actual) {
        this(
            new FormattedText(
                "%nExpected: %s"
                    + "%n     but: was %s",
                expected,
                actual
            )
        );
    }

    /**
     * Private Ctor.
     * @param origin Origin text
     */
    private SimpleErrorAsText(final Text origin) {
        this.origin = origin;
    }

    @Override
    public String asString() throws IOException {
        return this.origin.asString();
    }
}
