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

import com.jcabi.http.mock.MkAnswer;
import com.jcabi.http.mock.MkContainer;
import com.jcabi.http.mock.MkGrizzlyContainer;
import com.jcabi.http.request.JdkRequest;
import com.jcabi.manifests.Manifests;
import java.io.IOException;
import java.net.URI;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

/**
 * Test case for {@link RtGitlab}.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class RtGitlabTest {

    /**
     * Mock Container.
     */
    private MkContainer container;

    /**
     * Test case setUp method.
     * <p>
     * <p> Start container
     *
     * @throws IOException if there is any problem
     */
    @Before
    public void setUp() throws IOException {
        container = new MkGrizzlyContainer()
                .next(
                        new MkAnswer.Simple("hello, world!")
                ).start();
    }

    /**
     * RtGitlab return Request with UserAgent.
     *
     * @throws Exception if there is any problem
     */
    @Test
    public void requestUseragent() throws Exception {
        new RtGitlab(
                new JdkRequest(container.home())
        ).entry().fetch();
        container.stop();
        MatcherAssert.assertThat(
                container.take().headers(),
                Matchers.hasEntry(
                        Matchers.equalTo("User-Agent"),
                        Matchers.hasItem(
                                String.format(
                                        "gitlab-api-client %s %s %s",
                                        Manifests.read("Gilatb-Version"),
                                        Manifests.read("Gitlab-Build"),
                                        Manifests.read("Gitlab-Date")
                                )
                        )
                )
        );
    }

    /**
     * RtGitlab return Request with path api-v4.
     *
     * @throws Exception if there is any problem
     */
    @Test
    public void requestPath() throws Exception {
        new RtGitlab(
                new JdkRequest(container.home())
        ).entry().fetch();
        container.stop();
        MatcherAssert.assertThat(
                container.take().uri(),
                Matchers.equalTo(
                        new URI("/api/v4")
                )
        );
    }
}
