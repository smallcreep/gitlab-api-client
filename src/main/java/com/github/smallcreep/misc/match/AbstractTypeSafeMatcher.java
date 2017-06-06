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
import org.hamcrest.internal.ReflectiveTypeFinder;

/**
 * Convenient base class for Matcher that require a non-null value
 * of a specific type.
 * <p>This simply implements the null check,
 * checks the type and then casts.</p>
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @param <T> Specific type
 * @since 0.1
 */
public abstract class AbstractTypeSafeMatcher<T> implements Matcher<T> {

    /**
     * Default type finder.
     */
    private static final ReflectiveTypeFinder TYPE_FINDER =
        new ReflectiveTypeFinder("matchSafely", 1, 0);

    /**
     * Specific type.
     */
    private final Class<?> type;

    /**
     * The default constructor for simple sub types.
     */
    protected AbstractTypeSafeMatcher() {
        this(TYPE_FINDER);
    }

    /**
     * Ctor for finder type.
     * @param finder Finder
     */
    protected AbstractTypeSafeMatcher(final ReflectiveTypeFinder finder) {
        this.type = finder.findExpectedType(getClass());
    }

    /**
     * Protected Ctor.
     * @param type Specific type
     */
    protected AbstractTypeSafeMatcher(final Class<T> type) {
        this.type = type;
    }

    @Override
    @SuppressWarnings("unchecked")
    public final Optional<AssertionError> match(final Object actual)
        throws IOException {
        Optional<AssertionError> result = new AndOf<>(
            new IsNotNull<>(),
            new IsType<>(this.type)
        ).match(actual);
        if (!result.has()) {
            result = matchSafely((T) actual);
        }
        return result;
    }

    /**
     * Subclasses should implement this.
     * The item will already have been checked for
     * the specific type and will never be null.
     * @param actual Actual item
     * @return Optional, if optional has, than match fail
     * @throws IOException if there is any problem
     */
    protected abstract Optional<AssertionError> matchSafely(T actual)
        throws IOException;
}
