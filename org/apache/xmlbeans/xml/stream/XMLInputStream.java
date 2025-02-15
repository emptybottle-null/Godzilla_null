/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.xml.stream;

import org.apache.xmlbeans.xml.stream.ReferenceResolver;
import org.apache.xmlbeans.xml.stream.XMLEvent;
import org.apache.xmlbeans.xml.stream.XMLName;
import org.apache.xmlbeans.xml.stream.XMLStreamException;

public interface XMLInputStream {
    public XMLEvent next() throws XMLStreamException;

    public boolean hasNext() throws XMLStreamException;

    public void skip() throws XMLStreamException;

    public void skipElement() throws XMLStreamException;

    public XMLEvent peek() throws XMLStreamException;

    public boolean skip(int var1) throws XMLStreamException;

    public boolean skip(XMLName var1) throws XMLStreamException;

    public boolean skip(XMLName var1, int var2) throws XMLStreamException;

    public XMLInputStream getSubStream() throws XMLStreamException;

    public void close() throws XMLStreamException;

    public ReferenceResolver getReferenceResolver();

    public void setReferenceResolver(ReferenceResolver var1);
}

