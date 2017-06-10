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

import com.github.smallcreep.misc.match.core.HasAllOf;
import com.github.smallcreep.misc.match.core.HasElement;
import com.github.smallcreep.misc.match.core.HasError;
import com.github.smallcreep.misc.match.core.HasLocalizedMessage;
import com.github.smallcreep.misc.match.core.HasValue;
import com.github.smallcreep.misc.match.core.IsHasReturn;
import org.cactoos.text.StringAsText;
import org.junit.Test;

/**
 * Test Case for {@link ErrorIf}.
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (150 lines)
 */
public final class ErrorIfTest {

    /**
     * Check doesn't return error
     * if apply return true.
     * @throws Exception If fails
     */
    @Test
    public void emptyIfApplyTrue() throws Exception {
        new Assert.That<>(
            new ErrorIf(
                new StringAsText(
                    "error"
                ),
                () -> true
            ),
            new HasValue<>(
                new IsHasReturn<>(
                    false
                )
            )
        ).truth();
    }

    /**
     * Check return error
     * if apply return false.
     * @throws Exception If fails
     */
    @Test
    public void errorIfApplyFalse() throws Exception {
        new Assert.That<>(
            new ErrorIf(
                new StringAsText(
                    "error"
                ),
                () -> false
            ),
            new HasValue<>(
                new HasAllOf<Optional<Assertion>>(
                    new IsHasReturn<>(
                        true
                    ),
                    new HasElement<>(
                        new HasError(
                            new HasLocalizedMessage<>(
                                "error"
                            )
                        )
                    )
                )
            )
        ).truth();
    }
}
