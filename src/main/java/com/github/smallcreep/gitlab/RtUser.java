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

import com.jcabi.http.Request;
import java.io.IOException;
import javax.json.JsonObject;

/**
 * User Gitlab API.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @see <a href="https://gitlab.com/help/api/users.md#user">User API</a>
 * @since 0.1
 */
public final class RtUser implements User {

    /**
     * RESTful request.
     */
    private final Request request;

    /**
     * Gitlab owner.
     */
    private final Gitlab gitlab;

    /**
     * Ctor.
     *
     * @param gitlab Gitlab owner
     */
    RtUser(final Gitlab gitlab) {
        this(
                gitlab,
                gitlab.entry()
                        .uri()
                        .path("/user")
                        .back()
        );
    }

    /**
     * Ctor.
     *
     * @param gitlab Gitlab owner
     * @param request RESTful request
     */
    RtUser(final Gitlab gitlab,
           final Request request) {
        this.gitlab = gitlab;
        this.request = request;
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
