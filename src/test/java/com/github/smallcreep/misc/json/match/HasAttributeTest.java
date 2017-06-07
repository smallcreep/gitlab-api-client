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

import com.github.smallcreep.misc.match.Assert;
import com.github.smallcreep.misc.match.core.HasElement;
import com.github.smallcreep.misc.match.core.HasLocalizedMessage;
import com.github.smallcreep.misc.match.core.HasMatch;
import com.github.smallcreep.misc.match.core.IsHasReturn;
import com.jcabi.immutable.ArrayMap;
import java.util.HashMap;
import javax.json.Json;
import org.junit.Test;

/**
 * Test Case for {@link HasAttribute}.
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class HasAttributeTest {

    /**
     * Check empty if json has element.
     * @throws Exception If fails
     */
    @Test
    public void emptyIfElementHas() throws Exception {
        new Assert.That<>(
            new HasAttribute(
                "first"
            ),
            new HasMatch(
                Json.createObjectBuilder(
                    new ArrayMap<>(
                        new HashMap<String, Object>() {
                            {
                                put("first", "1");
                                put("second", "2");
                            }
                        }
                    )
                ).build(),
                new IsHasReturn<>(
                    false
                )
            )
        ).truth();
    }

    /**
     * Check error if json hasn't element.
     * @throws Exception If fails
     */
    @Test
    public void errorIfElementHasNot() throws Exception {
        new Assert.That<>(
            new HasAttribute(
                "third"
            ),
            new HasMatch(
                Json.createObjectBuilder(
                    new ArrayMap<>(
                        new HashMap<String, Object>() {
                            {
                                put("first", "1");
                                put("second", "2");
                            }
                        }
                    )
                ).build(),
                new HasElement<>(
                    new HasLocalizedMessage<>(
                        "\nExpected: <Map attribute <{third}>>"
                            + "\n     but: was "
                            + "<{\"first\":\"1\",\"second\":\"2\"}>"
                    )
                )
            )
        ).truth();
    }
}
