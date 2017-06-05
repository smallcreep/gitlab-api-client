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
 * Test Case for {@link NotNull}.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class NotNullTest {

    /**
     * Check {@link NotNull#match(Object)} return error,
     * if actual object is null.
     * @throws Exception If fails
     */
    @Test
    public void errorIfNull() throws Exception {
        new Assert.That<>(
            new NotNull<>(),
            new HasMatch<>(
                null,
                new HasElement<>(
                    new HasLocalizedMessage(
                        "\nExpected: <Not null object>"
                            + "\n     but: was <found null>"
                    )
                )
            )
        ).truth();
    }

    /**
     * Check {@link NotNull#match(Object)} doesn't return error,
     * if actual object isn't null.
     * @throws Exception If fails
     */
    @Test
    public void emptyIfNotNull() throws Exception {
        new Assert.That<>(
            new NotNull<>(),
            new HasMatch<>(
                "actual",
                new IsHasReturn(
                    false
                )
            )
        ).truth();
    }
}
