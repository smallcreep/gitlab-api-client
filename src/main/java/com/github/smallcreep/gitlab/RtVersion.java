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

import java.io.IOException;
import javax.json.JsonObject;

/**
 * Version Gitlab API.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @see <a href="https://gitlab.com/help/api/version.md">Version API</a>
 * @since 0.1
 */
@Immutable
@Loggable(Loggable.DEBUG)
final class RtVersion implements Version {

    /**
     * RESTful request.
     */
    private final Request request;

    /**
     * Gitlab owner.
     */
    private final Gitlab gitlab;

    /**
     * Public ctor.
     *
     * @param gitlab Gitlab owner
     */
    RtVersion(final Gitlab gitlab) {
        this(gitlab, gitlab.entry());
    }

    /**
     * Public ctor.
     *
     * @param gitlab Gitlab owner
     * @param req RESTful request
     */
    RtVersion(final Gitlab gitlab,
              final Request req) {
        this.gitlab = gitlab;
        this.request = req.uri()
                .path("/version")
                .back();
    }

    @Override
    public JsonObject json() throws IOException {
        return new RtJson(this.request).fetch();
    }

    @Override
    public Gitlab gitlab() {
        return this.gitlab;
    }
}
