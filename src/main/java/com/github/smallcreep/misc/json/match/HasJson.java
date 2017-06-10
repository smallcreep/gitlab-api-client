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

import com.github.smallcreep.misc.json.JsonReadable;
import com.github.smallcreep.misc.match.AbstractTypeSafeMatcher;
import com.github.smallcreep.misc.match.Assertion;
import com.github.smallcreep.misc.match.Optional;
import java.io.IOException;
import javax.json.JsonObject;

/**
 * Hamcrest matcher HasJson.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class HasJson extends AbstractTypeSafeMatcher<JsonReadable> {

    /**
     * JsonObject Matcher.
     */
    private final AbstractTypeSafeMatcher<JsonObject> matcher;

    /**
     * Public Ctor.
     *
     * @param matcher JsonObject Matcher
     */
    public HasJson(final AbstractTypeSafeMatcher<JsonObject> matcher) {
        super(JsonReadable.class);
        this.matcher = matcher;
    }

    @Override
    protected Optional<Assertion> matchSafely(final JsonReadable item)
        throws IOException {
        return this.matcher.match(item.json());
    }
}
