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

import com.github.smallcreep.misc.match.Assert;
import org.cactoos.Scalar;
import org.cactoos.text.StringAsText;
import org.cactoos.text.TextAsLong;
import org.junit.Test;

/**
 * Test Case for {@link HasValue}.
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class HasValueTest {

    /**
     * Check Matcher execute wrapped matcher
     * with actual value is equals
     * return value method {@link Scalar#asValue()} object actual.
     * @throws Exception If fails
     */
    @Test
    public void executeMethodAsValue() throws Exception {
        new Assert.That<>(
            new HasValue<>(
                new IsEqualTo<>(
                    // @checkstyle MagicNumberCheck (1 line)
                    100L
                )
            ),
            new HasMatch(
                new TextAsLong(
                    new StringAsText(
                        "100"
                    )
                ),
                new IsHasReturn<>(
                    false
                )
            )
        ).truth();
    }
}
