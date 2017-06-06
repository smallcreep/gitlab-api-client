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

import org.junit.Test;

/**
 * Test Case for {@link IsThrowError}.
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (150 lines)
 */
public final class IsThrowErrorTest {

    /**
     * Check if error doesn't happened, than Matcher return error.
     * @throws Exception If fails
     */
    @Test
    public void errorIfErrorNotHappened() throws Exception {
        final String expected = "test";
        new Assert.That<>(
            new IsThrowError<>(
                AssertionError.class,
                "",
                new IsEqualTo<>(
                    expected
                )
            ),
            new HasMatch<>(
                expected,
                new HasElement<>(
                    new HasLocalizedMessage(
                        "\nExpected: <throw class java.lang.AssertionError>"
                            + "\n     but: was <not throw any exception>"
                    )
                )
            )
        ).truth();
    }

    /**
     * Check Matcher return error if
     * expected error has another type
     * and another msg.
     * @throws Exception If fails
     */
    @Test
    public void errorIfAnotherError() throws Exception {
        new Assert.That<>(
            new IsThrowError<>(
                NullPointerException.class,
                "actual",
                new FakeMatcher<>()
            ),
            new HasMatch<>(
                "",
                new HasElement<>(
                    new HasLocalizedMessage(
                        "\nExpected: <class java.lang.NullPointerException>"
                            + "\n     but: was "
                            + "<class java.lang.UnsupportedOperationException>"
                            + "\nAnd"
                            + "\nExpected: <actual>"
                            + "\n     but: was "
                            + "<Method match(Object) not supported>"
                    )
                )
            )
        ).truth();
    }

    /**
     * Check Matcher doesn't return error if
     * expected expected error equals actual error.
     * @throws Exception If fails
     */
    @Test
    public void emptyIfEqualsError() throws Exception {
        new Assert.That<>(
            new IsThrowError<>(
                UnsupportedOperationException.class,
                "Method match(Object) not supported",
                new FakeMatcher<>()
            ),
            new HasMatch<>(
                "",
                new IsHasReturn(
                    false
                )
            )
        ).truth();
    }
}
