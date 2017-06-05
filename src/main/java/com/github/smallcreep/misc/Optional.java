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

package com.github.smallcreep.misc;

import com.jcabi.aspects.Immutable;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Replacement a nullable T reference with a non-null value.
 * <p>
 * <p>All implementations of this interface must be immutable and thread-safe.
 *
 * @param <T> Type of item
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@Immutable
public interface Optional<T> {

    /**
     * Returns the contained instance.
     *
     * @return Instance
     */
    T get();

    /**
     * Returns true if contains instance.
     *
     * @return True if present
     */
    boolean has();

    /**
     * Holder for a single element only.
     *
     * <p>The class is immutable and thread-safe.</p>
     * @param <T> Type of item
     */
    @EqualsAndHashCode(of = {"origin"})
    @ToString(of = {"origin"})
    final class Single<T> implements Optional<T> {

        /**
         * Origin.
         */
        private final T origin;

        /**
         * Ctor.
         *
         * @param origin Origin
         */
        public Single(final T origin) {
            this.origin = origin;
        }

        @Override
        public T get() {
            return this.origin;
        }

        @Override
        public boolean has() {
            return true;
        }
    }

    /**
     * Empty instance.
     *
     * <p>The class is immutable and thread-safe.</p>
     * @param <T> Type of item
     */
    final class Empty<T> implements Optional<T> {

        @Override
        public T get() {
            throw new UnsupportedOperationException(
                    "there is nothing here, use has() first, to check"
            );
        }

        @Override
        public boolean has() {
            return false;
        }
    }
}
