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

package com.github.smallcreep.gitlab.mk;

import com.jcabi.aspects.Immutable;
import com.jcabi.xml.XML;
import java.io.IOException;
import org.xembly.Directive;

/**
 * Storage of Gitlab data.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@Immutable
public interface MkStorage {

    /**
     * Get full XML.
     *
     * @return XML
     * @throws IOException If there is any I/O problem, or if the current
     *  storage is locked by another thread.
     */
    XML xml() throws IOException;

    /**
     * Update XML with this directives.
     * @param dirs Directives
     * @throws IOException If there is any I/O problem, or if the current
     *  storage is locked by another thread.
     */
    void apply(
        Iterable<Directive> dirs
    ) throws IOException;

    /**
     * Locks storage to the current thread.
     *
     * <p>If the lock is available, grant it
     * to the calling thread and block all operations from other threads.
     * If not available, wait for the holder of the lock to release it with
     * {@link #unlock()} before any other operations can be performed.
     *
     * <p>Locking behavior is reentrant, which means a thread can invoke
     * {@link #lock()} multiple times, where a hold count is maintained.
     * @throws IOException If there is any I/O problem
     */
    void lock() throws IOException;

    /**
     * Unlock storage.
     *
     * <p>Locking behavior is reentrant, thus if the thread invoked
     * {@link #lock()} multiple times, the hold count is decremented. If the
     * hold count reaches 0, the lock is released.
     *
     * <p>If the current thread does not hold the lock, an
     * {@link IllegalMonitorStateException} will be thrown.
     * @throws IOException If there is any I/O problem
     */
    void unlock() throws IOException;

}
