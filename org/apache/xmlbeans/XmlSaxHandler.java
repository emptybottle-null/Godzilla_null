/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.xml.sax.ContentHandler;
import org.xml.sax.ext.LexicalHandler;

public interface XmlSaxHandler {
    public ContentHandler getContentHandler();

    public LexicalHandler getLexicalHandler();

    public void bookmarkLastEvent(XmlCursor.XmlBookmark var1);

    public void bookmarkLastAttr(QName var1, XmlCursor.XmlBookmark var2);

    public XmlObject getObject() throws XmlException;
}

