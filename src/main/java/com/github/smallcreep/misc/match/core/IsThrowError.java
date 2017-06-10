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

import com.github.smallcreep.misc.match.Assertion;
import com.github.smallcreep.misc.match.IsType;
import com.github.smallcreep.misc.match.Matcher;
import com.github.smallcreep.misc.match.Optional;
import com.github.smallcreep.misc.match.SimpleErrorAsText;
import com.github.smallcreep.misc.match.TextAsError;
import java.io.IOException;

/**
 * Matcher match wrapped matcher throw Exception.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @param <T> Type wrapped matcher
 * @since 0.1
 */
public final class IsThrowError<T> implements Matcher<T> {

    /**
     * Expected exception type.
     */
    private final Class<?> exception;

    /**
     * Expected exception message matcher.
     */
    private final Matcher<String> msg;

    /**
     * Throwable matcher.
     */
    private final Matcher<T> matcher;

    /**
     * Public Ctor.
     * @param exception Expected exception type
     * @param msg Expected exception message
     * @param matcher Throwable matcher
     */
    public IsThrowError(final Class<?> exception,
                        final String msg,
                        final Matcher<T> matcher) {
        this(exception, new IsEqualTo<>(msg), matcher);
    }

    /**
     * Public Ctor.
     * @param exception Expected exception type
     * @param msg Expected exception message matcher
     * @param matcher Throwable matcher
     */
    public IsThrowError(final Class<?> exception,
                        final Matcher<String> msg,
                        final Matcher<T> matcher) {
        this.exception = exception;
        this.msg = msg;
        this.matcher = matcher;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Optional<Assertion> match(final Object actual)
        throws IOException {
        Optional<Assertion> error = new TextAsError(
            new SimpleErrorAsText(
                "Matcher throw " + this.exception,
                "not throw any exception"
            )
        ).asValue();
        try {
            this.matcher.match(actual);
            // @checkstyle IllegalCatchCheck (1 line)
        } catch (final Throwable thr) {
            error = new HasAllOf<>(
                new IsType<>(
                    this.exception
                ),
                new HasLocalizedMessage(
                    this.msg
                )
            ).match(thr);
        }
        return error;
    }

    @Override
    public String toString() {
        return String.format(
            "Matcher %s throw exception %s, after call function match(Object), and exception message is <%s>",
            this.matcher, this.exception, this.msg
        );
    }
}
