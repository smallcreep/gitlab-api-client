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

import com.github.smallcreep.gitlab.mk.MkGitlab;
import com.jcabi.http.mock.MkAnswer;
import com.jcabi.http.mock.MkContainer;
import com.jcabi.http.mock.MkGrizzlyContainer;
import com.jcabi.http.request.ApacheRequest;

import java.net.HttpURLConnection;
import java.net.URI;

import javax.json.Json;
import javax.json.JsonObject;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * Test case for {@link RtVersion}.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class RtVersionTest {

    /**
     * RtVersion can fetch version Gitlab API.
     *
     * @throws Exception if there is any problem
     */
    @Test
    public void fetchVersion() throws Exception {
        final JsonObject expected = Json.createObjectBuilder()
                .add(
                        "body",
                        "hi"
                ).build();
        final MkContainer container = new MkGrizzlyContainer().next(
                new MkAnswer.Simple(
                        HttpURLConnection.HTTP_OK,
                        expected.toString()
                )
        ).start();
        MatcherAssert.assertThat(
                new RtVersion(
                        new MkGitlab(),
                        new ApacheRequest(container.home())
                ).json(),
                CoreMatchers.equalTo(
                        expected
                )
        );
        container.stop();
        MatcherAssert.assertThat(
                container.take().uri(),
                CoreMatchers.equalTo(
                        new URI("/version")
                )
        );
    }
}
