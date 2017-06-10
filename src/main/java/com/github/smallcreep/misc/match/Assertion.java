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

import lombok.EqualsAndHashCode;
import org.cactoos.Text;
import org.cactoos.text.FormattedText;
import org.cactoos.text.StringAsText;
import org.cactoos.text.UncheckedText;

/**
 * Maker AssertionError.
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface Assertion {

    /**
     * Get Error.
     * @return Error
     */
    AssertionError error();

    /**
     * Add new Expected text to Assertion.
     * @param exp Expected text with conversion
     * @return New Assertion with new expected text.
     */
    Assertion addExpected(String exp);

    /**
     * Add new Actual text tp Assertion.
     * @param act Actual text with conversion
     * @return New Assertion with new expected text.
     */
    Assertion addActual(String act);

    class FromError implements Assertion {

        /**
         * Error.
         */
        private final AssertionError error;

        /**
         * Public ctor.
         * @param error Error.
         */
        public FromError(final AssertionError error) {
            this.error = error;
        }

        @Override
        public AssertionError error() {
            return this.error;
        }

        @Override
        public Assertion addExpected(final String exp) {
            throw new UnsupportedOperationException("addExpected(Text)");
        }

        @Override
        public Assertion addActual(final String act) {
            throw new UnsupportedOperationException("addActual(Text)");
        }
    }

    class Simple implements Assertion {

        private final Text expected;

        private final Text actual;

        public Simple(final Text expected, final Text actual) {
            this.expected = expected;
            this.actual = actual;
        }

        @Override
        public AssertionError error() {
            return new AssertionError(
                new StringAsText(
                    new UncheckedText(this.expected).asString()
                        + new UncheckedText(this.actual).asString()
                ).asString()
            );
        }

        @Override
        public Assertion addExpected(final String exp) {
            return new Simple(
                new FormattedText(
                    exp,
                    new UncheckedText(this.expected).asString()
                ),
                this.actual
            );
        }

        @Override
        public Assertion addActual(final String act) {
            return new Simple(
                this.expected,
                new FormattedText(
                    act,
                    new UncheckedText(this.actual).asString()
                )
            );
        }
    }
}
