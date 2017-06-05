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

package com.github.smallcreep.misc.json.match;

import com.github.smallcreep.misc.Optional;
import com.github.smallcreep.misc.match.AbstractTypeSafeMatcher;
import com.github.smallcreep.misc.match.HasAllOf;
import com.github.smallcreep.misc.match.Matcher;
import com.jcabi.immutable.Array;
import java.io.IOException;
import javax.json.JsonObject;
import org.cactoos.list.ArrayAsIterable;
import org.cactoos.list.TransformedIterable;

/**
 * Matcher Json has attributes.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class HasAttributes extends AbstractTypeSafeMatcher<JsonObject> {

    /**
     * Expected matcher.
     */
    private final Matcher<JsonObject> matcher;

    /**
     * Public Ctor.
     *
     * @param attributes Expected Attributes
     */
    public HasAttributes(final String... attributes) {
        this(new ArrayAsIterable<>(attributes));
    }

    /**
     * Public Ctor.
     *
     * @param attributes Expected Attributes
     */
    public HasAttributes(final Iterable<String> attributes) {
        this(
            new HasAllOf<>(
                new TransformedIterable<>(
                    new Array<>(attributes),
                    input -> new HasAttribute(input)
                )
            )
        );
    }

    /**
     * Private Ctor.
     * @param matcher Expected matcher
     */
    private HasAttributes(final Matcher<JsonObject> matcher) {
        super(JsonObject.class);
        this.matcher = matcher;
    }

    @Override
    protected Optional<AssertionError> matchSafely(final JsonObject actual)
        throws IOException {
        return matcher.match(actual);
    }
}
