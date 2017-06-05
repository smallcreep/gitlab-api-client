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

package com.github.smallcreep.gitlab;

import com.github.smallcreep.misc.json.match.HasAttributes;
import com.github.smallcreep.misc.json.match.HasJson;
import com.github.smallcreep.misc.match.Assert;
import java.net.URI;
import javax.json.Json;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assume;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Integration case for {@link Gitlab}.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class RtGitlabITCase {

    /**
     * RtGitlab can connect anonymously.
     *
     * @throws Exception If some problem inside
     * @todo #2:15m/DEV Rewrite this test. Use any anonymous data.
     */
    @Test
    @Ignore
    public void connectsAnonymously() throws Exception {
        final Gitlab gitlab = new RtGitlab();
        MatcherAssert.assertThat(
            gitlab.version().json(),
            Matchers.equalTo(
                Json.createObjectBuilder()
                    .add("message", "401 Unauthorized")
                    .build()
            )
        );
    }

    /**
     * RtGitlab can connect anonymously to external gitlab.
     *
     * @throws Exception If some problem inside
     * @todo #2:15m/DEV Rewrite this test. Use any anonymous data.
     */
    @Test
    @Ignore
    public void connectsAnonymouslyExternal() throws Exception {
        final Gitlab gitlab = new RtGitlab(new URI("https://gitlab.com"));
        MatcherAssert.assertThat(
            gitlab.version().json(),
            Matchers.equalTo(
                Json.createObjectBuilder()
                    .add("message", "401 Unauthorized")
                    .build()
            )
        );
    }

    /**
     * RtGitlab can authenticate itself.
     *
     * @throws Exception If some problem inside
     */
    @Test
    public void authenticatesItself() throws Exception {
        new Assert.That<>(
            RtGitlabITCase.gitlab().self(),
            new HasJson(
                new HasAttributes(
                    "name",
                    "username",
                    "id"
                )
            )
        ).truth();
    }

    /**
     * Create and return repo to test by private token.
     *
     * @return Repo
     *
     * @throws Exception If some problem inside
     */
    private static Gitlab gitlab() throws Exception {
        final String key = System.getProperty("failsafe.gitlab.key");
        Assume.assumeThat(key, Matchers.notNullValue());
        return new RtGitlab(key);
    }
}
