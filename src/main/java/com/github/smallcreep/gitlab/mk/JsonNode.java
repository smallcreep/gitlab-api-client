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

package com.github.smallcreep.gitlab.mk;

import com.jcabi.xml.XML;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import org.w3c.dom.Node;

/**
 * HasJson node in XML.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
final class JsonNode {

    /**
     * XML.
     */
    private final XML xml;

    /**
     * Public ctor.
     *
     * @param src Source
     */
    JsonNode(final XML src) {
        this.xml = src;
    }

    /**
     * Fetch JSON object.
     *
     * @return JSON
     */
    public JsonObject json() {
        final JsonObjectBuilder builder = Json.createObjectBuilder();
        for (final XML child : this.xml.nodes("* ")) {
            final Node node = child.node();
            if (child.nodes("*").isEmpty()) {
                builder.add(node.getNodeName(), node.getTextContent());
            } else {
                builder.add(node.getNodeName(), new JsonNode(child).json());
            }
        }
        return builder.build();
    }
}
