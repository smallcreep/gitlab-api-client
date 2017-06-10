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
import org.cactoos.Scalar;
import org.cactoos.Text;
import org.cactoos.text.StringAsText;

/**
 * Return error if {@link ErrorIf#func}
 * return true after call apply.
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class ErrorIf implements Scalar<Optional<Assertion>> {

    /**
     * Assertion.
     */
    private final Assertion assertion;

    /**
     * Function.
     */
    private final Func func;

    /**
     * Public Ctor.
     * @param error Error msg
     * @param func Function
     */
    public ErrorIf(final Text error, final Func func) {
        this(
            new Assertion.Simple(
                error,
                new StringAsText("")
            ),
            func
        );
    }

    /**
     * Public Ctor.
     * @param assertion Assertion
     * @param func Function
     */
    public ErrorIf(final Assertion assertion, final Func func) {
        this.assertion = assertion;
        this.func = func;
    }

    @Override
    public Optional<Assertion> asValue() throws IOException {
        final Optional<Assertion> error;
        if (this.func.apply()) {
            error = new Optional.Empty<>();
        } else {
            error = new Optional.Single<>(this.assertion);
        }
        return error;
    }

    /**
     * Function.
     */
    @FunctionalInterface
    public interface Func {

        /**
         * Function method.
         *
         * @return Boolean value, return true if function correct
         * @throws IOException If fails
         */
        boolean apply() throws IOException;
    }
}
