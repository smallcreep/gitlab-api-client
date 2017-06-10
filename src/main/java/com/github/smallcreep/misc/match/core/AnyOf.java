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

import com.github.smallcreep.misc.match.ArrayAssertionError;
import com.github.smallcreep.misc.match.Assertion;
import com.github.smallcreep.misc.match.ErrorIf;
import com.github.smallcreep.misc.match.Matcher;
import com.github.smallcreep.misc.match.Optional;
import com.github.smallcreep.misc.match.SimpleErrorAsText;
import com.jcabi.immutable.Array;
import java.io.IOException;
import lombok.ToString;
import org.cactoos.Func;
import org.cactoos.list.ArrayAsIterable;
import org.cactoos.list.FilteredIterable;
import org.cactoos.list.TransformedIterable;

/**
 * This Matcher match all matchers,
 * and if one of it return empty error,
 * than matcher return empty.
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @param <T> Type matcher
 * @since 0.1
 */
@ToString(of = "matchers")
public final class AnyOf<T> implements Matcher<T> {

    /**
     * Expected matchers.
     */
    private final Iterable<Matcher<T>> matchers;

    /**
     * Public Ctor.
     * @param matchers Expected matchers
     */
    @SafeVarargs
    public AnyOf(final Matcher<T>... matchers) {
        this(new ArrayAsIterable<>(matchers));
    }

    /**
     * Public Ctor.
     * @param matchers Expected matchers
     */
    public AnyOf(final Iterable<Matcher<T>> matchers) {
        this.matchers = new Array<>(matchers);
    }

    @Override
    public Optional<Assertion> match(final Object actual) throws IOException {
        return new ErrorIf(
            new SimpleErrorAsText(
                this.matchers,
                actual
            ),
            () -> new FilteredIterable<>(
                new TransformedIterable<>(
                    matchers,
                    new Func<Matcher<T>, Optional<Assertion>>() {
                        @Override
                        public Optional<Assertion> apply(
                            final Matcher<T> input) throws IOException {
                            return input.match(actual);
                        }
                    }
                ),
                new Func<Optional<Assertion>, Boolean>() {
                    @Override
                    public Boolean apply(final Optional<Assertion> input)
                        throws IOException {
                        return !input.has();
                    }
                }
            ).iterator().hasNext()
        ).asValue();
    }
}
