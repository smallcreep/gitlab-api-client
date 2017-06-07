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

/**
 * This is assert for test.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface Assert {

    /**
     * Check assert.
     *
     * @throws IOException If some problem inside
     */
    void truth() throws IOException;

    /**
     * This is simple assert.
     * <p>
     * <p>Use:</p>
     * <p><code>
     *     new Assert.That(
     *      new Object(),
     *      new IsEqualTo(new Object())
     *     ).truth();
     * </code></p>
     * @param <T> Type Actual object and Matcher
     */
    final class That<T> implements Assert {

        /**
         * Actual object.
         */
        private final T obj;
        /**
         * Matcher.
         */
        private final Matcher<T> matcher;

        /**
         * Public Ctor.
         * @param obj Actual object
         * @param matcher Matcher
         */
        public That(final T obj, final Matcher<T> matcher) {
            this.obj = obj;
            this.matcher = matcher;
        }

        @Override
        public void truth() throws IOException {
            final Optional<AssertionError> error = this.matcher.match(obj);
            if (error.has()) {
                throw error.get();
            }
        }
    }
}
