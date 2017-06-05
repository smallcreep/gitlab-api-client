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

import com.github.smallcreep.misc.Optional;
import java.io.IOException;

/**
 * Matcher matches actual object with two another matchers.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @param <T> Matcher type
 * @since 0.1
 */
public final class AndOf<T> implements Matcher<T> {

    /**
     * New matcher with two matchers.
     */
    private final Matcher<T> all;

    /**
     * Public Ctor.
     * @param first First Matcher
     * @param second Second Matcher
     */
    @SuppressWarnings("unchecked")
    public AndOf(final Matcher<T> first,
                 final Matcher<T> second) {
        this.all = new HasAllOf<>(first, second);
    }

    @Override
    public Optional<AssertionError> match(final Object actual)
        throws IOException {
        return all.match(actual);
    }
}
