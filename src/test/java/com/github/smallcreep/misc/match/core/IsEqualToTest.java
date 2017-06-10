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

import com.github.smallcreep.misc.match.AddOptionalSimpleAssertion;
import com.github.smallcreep.misc.match.Assert;
import org.junit.Test;

/**
 * Test Case for {@link IsEqualTo}.
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class IsEqualToTest {

    /**
     * Check Matcher doesn't return error
     * if actual object equal expected object.
     * @throws Exception If fails
     */
    @Test
    public void emptyIfActualEqualExpected() throws Exception {
        new Assert.That<>(
            new IsEqualTo<>(
                "expected"
            ),
            new HasMatch(
                "expected",
                new IsHasReturn<>(
                    false
                )
            )
        ).truth();
    }

    /**
     * Check Matcher return error
     * if actual object doesn't equal expected object.
     * @throws Exception If fails
     */
    @Test
    public void errorIfActualDoesNotEqualExpected() throws Exception {
        new Assert.That<>(
            new IsEqualTo<>(
                "expected"
            ),
            new HasMatch(
                "actual",
                new AddOptionalSimpleAssertion(
                    new HasElement<>(
                        new HasError(
                            new HasLocalizedMessage<>(
                                "\nExpected: that equals <expected>"
                                    + "\n     but: was <actual>"
                            )
                        )
                    )
                )
            )
        ).truth();
    }
}
