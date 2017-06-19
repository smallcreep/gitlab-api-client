/**
 * The MIT License (MIT)
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
 * The above copyright notice and this permission notice shall be included
 *  in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.smallcreep.gitlab.mk.storage;

import com.github.smallcreep.gitlab.mk.MkStorage;
import com.jcabi.aspects.Immutable;
import com.jcabi.aspects.Loggable;
import com.jcabi.xml.XML;
import java.io.IOException;
import lombok.EqualsAndHashCode;
import org.xembly.Directive;

/**
 * Syncronized Storage.
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@Immutable
@EqualsAndHashCode(of = {"origin", "lock"})
@Loggable(Loggable.DEBUG)
public final class StSynced implements MkStorage {

    /**
     * Original storage.
     */
    private final MkStorage origin;
    /**
     * Lock object.
     */
    private final ImmutableReentrantLock lock =
        new ImmutableReentrantLock();

    /**
     * Public ctor.
     * @param storage Original
     */
    public StSynced(final MkStorage storage) {
        this.origin = storage;
    }

    @Override
    public String toString() {
        return this.origin.toString();
    }

    @Override
    public XML xml() throws IOException {
        return this.origin.xml();
    }

    @Override
    public void apply(final Iterable<Directive> dirs) throws IOException {
        this.origin.apply(dirs);
    }

    @Override
    public void lock() throws IOException {
        this.lock.lock();
    }

    @Override
    public void unlock() throws IOException {
        this.lock.unlock();
    }
}
