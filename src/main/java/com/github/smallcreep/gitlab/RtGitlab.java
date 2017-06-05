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

import com.jcabi.aspects.Immutable;
import com.jcabi.aspects.Loggable;
import com.jcabi.http.Request;
import com.jcabi.http.request.ApacheRequest;
import com.jcabi.http.wire.AutoRedirectingWire;
import com.jcabi.manifests.Manifests;
import java.net.URI;
import javax.ws.rs.core.HttpHeaders;

/**
 * Gitlab API client, starting point to the entire library.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@Immutable
@Loggable(Loggable.DEBUG)
public final class RtGitlab implements Gitlab {

    /**
     * Version of us.
     */
    private static final String USER_AGENT = String.format(
            "gitlab-api-client %s %s %s",
            Manifests.read("Gilatb-Version"),
            Manifests.read("Gitlab-Build"),
            Manifests.read("Gitlab-Date")
    );

    /**
     * Default req to start with.
     */
    private static final Request REQUEST =
            new ApacheRequest("https://gitlab.com");

    /**
     * REST req.
     */
    private final Request req;

    /**
     * Public ctor, for anonymous access to Gitlab.
     */
    public RtGitlab() {
        this(RtGitlab.REQUEST);
    }

    /**
     * Public ctor, for anonymous access to external Gitlab.
     *
     * @param uri URI external Gitlab
     */
    public RtGitlab(final URI uri) {
        this(
                new ApacheRequest(uri)
        );
    }

    /**
     * Public ctor, for authentication with private token.
     *
     * @param token Private token
     */
    public RtGitlab(
            final String token) {
        this(
                RtGitlab.REQUEST.header(
                        "PRIVATE-TOKEN",
                        token
                )
        );
    }

    /**
     * Public ctor, for authentication with private token external Gitlab.
     *
     * @param uri URI external Gitlab
     * @param token Private token
     */
    public RtGitlab(
            final URI uri,
            final String token) {
        this(
                new ApacheRequest(uri)
                        .header(
                                "PRIVATE-TOKEN",
                                token
                        )
        );
    }

    /**
     * Public ctor, for authentication with OAuth2 token.
     *
     * @param token OAuth token
     * @param type Token type
     */
    public RtGitlab(
            final String token,
            final String type) {
        this(
                RtGitlab.REQUEST.header(
                        HttpHeaders.AUTHORIZATION,
                        String.format("%s %s", type, token)
                )
        );
    }

    /**
     * Public ctor, for authentication with OAuth2 token external Gitlab.
     *
     * @param uri URI external Gitlab
     * @param token OAuth token
     * @param type Token type
     */
    public RtGitlab(
            final URI uri,
            final String token,
            final String type) {
        this(
                new ApacheRequest(uri).header(
                        HttpHeaders.AUTHORIZATION,
                        String.format("%s %s", type, token)
                )
        );
    }

    /**
     * Public ctor, with a custom req.
     *
     * @param req Request to start from
     */
    public RtGitlab(
            final Request req) {
        this.req = req.header(HttpHeaders.USER_AGENT, RtGitlab.USER_AGENT)
                .through(AutoRedirectingWire.class)
                .uri()
                .path("/api/v4")
                .back();
    }

    @Override
    public Request entry() {
        return this.req;
    }

    @Override
    public Version version() {
        return new RtVersion(this);
    }

    @Override
    public Projects projects() {
        return null;
    }

    @Override
    public Users users() {
        return null;
    }

    @Override
    public User self() {
        return new RtUser(this);
    }
}
