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
import org.cactoos.text.FormattedText;

/**
 * Matcher object has expected class.
 *
 * <p>Use {@link java.lang.Class#isInstance(Object)}</p>
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @param <T> Matcher type
 * @since 0.1
 */
public final class IsType<T> implements Matcher<T> {

    /**
     * Expected class.
     */
    private final Class<?> type;

    /**
     * Public Ctor.
     * @param type Expected class.
     */
    public IsType(final Class<?> type) {
        this.type = type;
    }

    @Override
    public Optional<Assertion> match(final Object actual)
        throws IOException {
        final Optional<Assertion> assertion;
        if (this.type.isInstance(actual)) {
            assertion = new Optional.Empty<>();
        } else {
            assertion = new Optional.Single<>(
                new Assertion.Simple(
                    new FormattedText("that object has %s", this.type),
                    new FormattedText("actual object has %s", actual.getClass())
                )
            );
        }
        return assertion;
    }
}
