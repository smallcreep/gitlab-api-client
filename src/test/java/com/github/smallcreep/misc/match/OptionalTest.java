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

import com.github.smallcreep.misc.match.core.HasElement;
import com.github.smallcreep.misc.match.core.HasToString;
import com.github.smallcreep.misc.match.core.IsEqualTo;
import com.github.smallcreep.misc.match.core.IsHasReturn;
import com.github.smallcreep.misc.match.core.IsNotNull;
import com.github.smallcreep.misc.match.core.IsSame;
import com.github.smallcreep.misc.match.core.IsThrowError;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

/**
 * Test Case for {@link Optional}.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (200 lines)
 */
public final class OptionalTest {

    /**
     * Check {@link Optional.Single#equals(Object)}
     * and {@link Optional.Single#hashCode()}.
     * @throws Exception If fails
     */
    @Test
    public void equalsAndHasCode() throws Exception {
        EqualsVerifier.forClass(Optional.Single.class)
            .verify();
    }

    /**
     * Check {@link Optional.Single#get()} return the same object.
     * @throws Exception If fails
     */
    @Test
    public void singleGetReturnTheSameObject() throws Exception {
        final String expected = "test";
        new Assert.That<>(
            new Optional.Single<>(
                expected
            ),
            new HasElement<>(
                new IsSame<>(expected)
            )
        ).truth();
    }

    /**
     * Check {@link Optional.Single#has()} return true.
     * @throws Exception If fails
     */
    @Test
    public void singleCallHasReturnTrue() throws Exception {
        new Assert.That<>(
            new Optional.Single<>(
                "test"
            ),
            new IsHasReturn<>(
                true
            )
        ).truth();
    }

    /**
     * Check {@link Optional.Single#toString()}
     * return toString() wrapped object.
     * @throws Exception If fails
     */
    @Test
    public void singleToStringReturnObject() throws Exception {
        new Assert.That<>(
            new Optional.Single<>(
                "test"
            ),
            new HasToString<>(
                new IsEqualTo<>(
                    "Optional.Single(origin=test)"
                )
            )
        ).truth();
    }

    /**
     * Check {@link Optional.Empty#get()}
     * throw {@link UnsupportedOperationException}.
     *
     * @throws Exception If fails
     */
    @Test
    public void emptyThrowUUnsupportedException() throws Exception {
        new Assert.That<>(
            new Optional.Empty<>(),
            new IsThrowError<>(
                UnsupportedOperationException.class,
                "there is nothing here, use has() first, to check",
                new HasElement<>(
                    new IsNotNull<>()
                )
            )
        ).truth();
    }

    /**
     * Check {@link Optional.Empty#has()} return false.
     * @throws Exception If fails
     */
    @Test
    public void emptyCallHasReturnFalse() throws Exception {
        new Assert.That<>(
            new Optional.Empty<>(
            ),
            new IsHasReturn<>(
                false
            )
        ).truth();
    }

}
