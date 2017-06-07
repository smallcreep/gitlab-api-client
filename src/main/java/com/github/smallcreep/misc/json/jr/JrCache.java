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

package com.github.smallcreep.misc.json.jr;

import com.github.smallcreep.misc.json.JsonReadable;
import com.github.smallcreep.misc.match.Optional;
import java.io.IOException;
import javax.json.JsonObject;

/**
 * Cache {@link com.github.smallcreep.misc.json.JsonReadable}.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class JrCache implements JsonReadable {

    /**
     * Origin JsonReadable.
     */
    private final JsonReadable origin;

    /**
     * Cache json.
     */
    private Optional<JsonObject> json = new Optional.Empty<>();

    /**
     * Public Ctor.
     *
     * @param origin Origin JsonReadable
     */
    public JrCache(final JsonReadable origin) {
        this.origin = origin;
    }

    @Override
    public JsonObject json() throws IOException {
        if (!this.json.has()) {
            this.json = new Optional.Single<>(this.origin.json());
        }
        return this.json.get();
    }
}
