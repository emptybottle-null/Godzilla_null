/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.gjt.xpp.XmlEndTag
 *  org.gjt.xpp.XmlPullParser
 *  org.gjt.xpp.XmlPullParserException
 *  org.gjt.xpp.XmlPullParserFactory
 *  org.gjt.xpp.XmlStartTag
 */
package org.dom4j.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.ElementHandler;
import org.dom4j.Node;
import org.dom4j.io.DispatchHandler;
import org.dom4j.xpp.ProxyXmlStartTag;
import org.gjt.xpp.XmlEndTag;
import org.gjt.xpp.XmlPullParser;
import org.gjt.xpp.XmlPullParserException;
import org.gjt.xpp.XmlPullParserFactory;
import org.gjt.xpp.XmlStartTag;

public class XPPReader {
    private DocumentFactory factory;
    private XmlPullParser xppParser;
    private XmlPullParserFactory xppFactory;
    private DispatchHandler dispatchHandler;

    public XPPReader() {
    }

    public XPPReader(DocumentFactory factory) {
        this.factory = factory;
    }

    public Document read(File file) throws DocumentException, IOException, XmlPullParserException {
        String systemID = file.getAbsolutePath();
        return this.read(new BufferedReader(new FileReader(file)), systemID);
    }

    public Document read(URL url) throws DocumentException, IOException, XmlPullParserException {
        String systemID = url.toExternalForm();
        return this.read(this.createReader(url.openStream()), systemID);
    }

    public Document read(String systemID) throws DocumentException, IOException, XmlPullParserException {
        if (systemID.indexOf(58) >= 0) {
            return this.read(new URL(systemID));
        }
        return this.read(new File(systemID));
    }

    public Document read(InputStream in) throws DocumentException, IOException, XmlPullParserException {
        return this.read(this.createReader(in));
    }

    public Document read(Reader reader) throws DocumentException, IOException, XmlPullParserException {
        this.getXPPParser().setInput(reader);
        return this.parseDocument();
    }

    public Document read(char[] text) throws DocumentException, IOException, XmlPullParserException {
        this.getXPPParser().setInput(text);
        return this.parseDocument();
    }

    public Document read(InputStream in, String systemID) throws DocumentException, IOException, XmlPullParserException {
        return this.read(this.createReader(in), systemID);
    }

    public Document read(Reader reader, String systemID) throws DocumentException, IOException, XmlPullParserException {
        Document document = this.read(reader);
        document.setName(systemID);
        return document;
    }

    public XmlPullParser getXPPParser() throws XmlPullParserException {
        if (this.xppParser == null) {
            this.xppParser = this.getXPPFactory().newPullParser();
        }
        return this.xppParser;
    }

    public XmlPullParserFactory getXPPFactory() throws XmlPullParserException {
        if (this.xppFactory == null) {
            this.xppFactory = XmlPullParserFactory.newInstance();
        }
        return this.xppFactory;
    }

    public void setXPPFactory(XmlPullParserFactory xPPFactory) {
        this.xppFactory = xPPFactory;
    }

    public DocumentFactory getDocumentFactory() {
        if (this.factory == null) {
            this.factory = DocumentFactory.getInstance();
        }
        return this.factory;
    }

    public void setDocumentFactory(DocumentFactory documentFactory) {
        this.factory = documentFactory;
    }

    public void addHandler(String path, ElementHandler handler) {
        this.getDispatchHandler().addHandler(path, handler);
    }

    public void removeHandler(String path) {
        this.getDispatchHandler().removeHandler(path);
    }

    public void setDefaultHandler(ElementHandler handler) {
        this.getDispatchHandler().setDefaultHandler(handler);
    }

    protected Document parseDocument() throws DocumentException, IOException, XmlPullParserException {
        byte type;
        Document document = this.getDocumentFactory().createDocument();
        Node parent = null;
        XmlPullParser parser = this.getXPPParser();
        parser.setNamespaceAware(true);
        ProxyXmlStartTag startTag = new ProxyXmlStartTag();
        XmlEndTag endTag = this.xppFactory.newEndTag();
        block6: while (true) {
            type = parser.next();
            switch (type) {
                case 1: {
                    return document;
                }
                case 2: {
                    parser.readStartTag((XmlStartTag)startTag);
                    Element newElement = startTag.getElement();
                    if (parent != null) {
                        parent.add(newElement);
                    } else {
                        document.add(newElement);
                    }
                    parent = newElement;
                    continue block6;
                }
                case 3: {
                    parser.readEndTag(endTag);
                    if (parent == null) continue block6;
                    parent = parent.getParent();
                    continue block6;
                }
                case 4: {
                    String text = parser.readContent();
                    if (parent != null) {
                        parent.addText(text);
                        continue block6;
                    }
                    String msg = "Cannot have text content outside of the root document";
                    throw new DocumentException(msg);
                }
            }
            break;
        }
        throw new DocumentException("Error: unknown type: " + type);
    }

    protected DispatchHandler getDispatchHandler() {
        if (this.dispatchHandler == null) {
            this.dispatchHandler = new DispatchHandler();
        }
        return this.dispatchHandler;
    }

    protected void setDispatchHandler(DispatchHandler dispatchHandler) {
        this.dispatchHandler = dispatchHandler;
    }

    protected Reader createReader(InputStream in) throws IOException {
        return new BufferedReader(new InputStreamReader(in));
    }
}

