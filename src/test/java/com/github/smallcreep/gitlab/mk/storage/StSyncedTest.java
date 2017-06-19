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
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * Test Case for {@link StSynced}.
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class StSyncedTest {

    /**
     * MkStorage can lock and unlock files.
     * @throws Exception If some problem inside
     */
    @Test
    public void locksAndUnlocks() throws Exception {
        final MkStorage storage = new StSynced(new StFile());
        final ExecutorService executor = Executors.newSingleThreadExecutor();
        final Runnable second = new Runnable() {
            @Override
            public void run() {
                try {
                    storage.lock();
                } catch (final IOException ex) {
                    throw new IllegalStateException(ex);
                }
            }
        };
        storage.lock();
        Future<?> future = executor.submit(second);
        try {
            future.get(1L, TimeUnit.SECONDS);
            MatcherAssert.assertThat("timeout SHOULD happen", false);
        } catch (final TimeoutException ex) {
            future.cancel(true);
        } finally {
            storage.unlock();
        }
        future = executor.submit(second);
        try {
            future.get(1L, TimeUnit.SECONDS);
        } catch (final TimeoutException ex) {
            MatcherAssert.assertThat("timeout SHOULD NOT happen", false);
            future.cancel(true);
        }
        executor.shutdown();
    }

}
