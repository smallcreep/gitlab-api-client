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

package com.github.smallcreep.misc.match.core;

import com.github.smallcreep.misc.match.AbstractTypeSafeMatcher;
import com.github.smallcreep.misc.match.Matcher;
import com.github.smallcreep.misc.match.Optional;
import java.io.IOException;

/**
 * Matcher match return {@link Matcher#match(Object)}.
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class HasMatch extends AbstractTypeSafeMatcher<Matcher> {

    /**
     * Fake actual object.
     */
    private final Object fake;

    /**
     * Error Matcher.
     */
    private final Matcher<Optional<AssertionError>> matcher;

    /**
     * Public Ctor.
     * @param fake Fake actual object
     * @param matcher Error Matcher
     */
    public HasMatch(final Object fake,
                    final Matcher<Optional<AssertionError>> matcher) {
        super();
        this.fake = fake;
        this.matcher = matcher;
    }

    @Override
    protected Optional<AssertionError> matchSafely(final Matcher actual)
        throws IOException {
        return this.matcher.match(
            actual.match(this.fake)
        );
    }
}
