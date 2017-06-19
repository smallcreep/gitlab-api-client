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
import com.jcabi.xml.XMLDocument;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.xembly.Directive;
import org.xembly.Xembler;

/**
 * Storage in file.
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@Immutable
@Loggable(Loggable.DEBUG)
public final class StFile implements MkStorage {

    /**
     * File name.
     */
    private final String name;

    /**
     * Public ctor.
     * @throws IOException If there is any I/O problem
     */
    public StFile() throws IOException {
        this(File.createTempFile("gitlab-api-client", ".xml"));
        new File(this.name).deleteOnExit();
    }

    /**
     * Public ctor.
     * @param file File to use
     * @throws IOException If there is any I/O problem
     */
    public StFile(
        final File file
    ) throws IOException {
        FileUtils.write(file, "<gitlab/>");
        this.name = file.getAbsolutePath();
    }

    @Override
    public String toString() {
        try {
            return this.xml().toString();
        } catch (final IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    @Override
    public XML xml() throws IOException {
        synchronized (this.name) {
            return new XMLDocument(
                FileUtils.readFileToString(
                    new File(this.name), Charsets.UTF_8
                )
            );
        }
    }

    @Override
    public void lock() throws IOException {
        // nothing
    }

    @Override
    public void unlock() throws IOException {
        // nothing
    }

    @Override
    public void apply(
        final Iterable<Directive> dirs
    ) throws IOException {
        synchronized (this.name) {
            FileUtils.write(
                new File(this.name),
                new XMLDocument(
                    new Xembler(dirs).applyQuietly(this.xml().node())
                ).toString(),
                Charsets.UTF_8
            );
        }
    }
}
