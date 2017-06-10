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
import org.cactoos.Func;
import org.cactoos.Scalar;
import org.cactoos.list.FilteredIterable;
import org.cactoos.list.TransformedIterable;

/**
 * Make one Assertion Error from Array Errors.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class ArrayAssertionError
    implements Scalar<Optional<Assertion>> {

    /**
     * Iterable Assertion Error.
     */
    private final Iterable<Optional<Assertion>> errors;

    /**
     * Public Ctor.
     * @param errors Iterable Assertion Error
     */
    public ArrayAssertionError(
        final Iterable<Optional<Assertion>> errors) {
        this.errors = new FilteredIterable<>(
            errors,
            new Func<Optional<Assertion>, Boolean>() {
                @Override
                public Boolean apply(final Optional<Assertion> input)
                    throws IOException {
                    return input.has();
                }
            }
        );
    }

    @Override
    public Optional<Assertion> asValue() throws IOException {
        final Optional<Assertion> error;
        if (errors.iterator().hasNext()) {
            error = new Optional.Single<>(
                new Assertion.FromError(
                    new AssertionError(
                        new TextOfIterable(
                            new TransformedIterable<>(
                                errors,
                                new Func<Optional<Assertion>, String>() {
                                    @Override
                                    public String apply(
                                        final Optional<Assertion> input)
                                        throws IOException {
                                        return input.get().error().getLocalizedMessage();
                                    }
                                }
                            )
                        ).asString()
                    )
                )
            );
        } else {
            error = new Optional.Empty<>();
        }
        return error;
    }
}
