/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.xml.stream;

import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;

public interface BufferedXMLInputStream
extends XMLInputStream {
    public void mark() throws XMLStreamException;

    public void reset() throws XMLStreamException;
}

