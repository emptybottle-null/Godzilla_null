/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.xmlpull.v1.XmlPullParser
 *  org.xmlpull.v1.XmlPullParserException
 *  org.xmlpull.v1.XmlPullParserFactory
 */
package org.dom4j.io;

import java.io.BufferedReader;
import java.io.CharArrayReader;
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
import org.dom4j.QName;
import org.dom4j.io.DispatchHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class XPP3Reader {
    private DocumentFactory factory;
    private XmlPullParser xppParser;
    private XmlPullParserFactory xppFactory;
    private DispatchHandler dispatchHandler;

    public XPP3Reader() {
    }

    public XPP3Reader(DocumentFactory factory) {
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
        this.getXPPParser().setInput((Reader)new CharArrayReader(text));
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
        this.xppFactory.setNamespaceAware(true);
        return this.xppFactory;
    }

    public void setXPPFactory(XmlPullParserFactory xPPfactory) {
        this.xppFactory = xPPfactory;
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
        DocumentFactory df = this.getDocumentFactory();
        Document document = df.createDocument();
        Node parent = null;
        XmlPullParser pp = this.getXPPParser();
        pp.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
        while (true) {
            int type = pp.nextToken();
            switch (type) {
                case 8: {
                    String text = pp.getText();
                    int loc = text.indexOf(" ");
                    if (loc >= 0) {
                        String target = text.substring(0, loc);
                        String txt = text.substring(loc + 1);
                        document.addProcessingInstruction(target, txt);
                        break;
                    }
                    document.addProcessingInstruction(text, "");
                    break;
                }
                case 9: {
                    if (parent != null) {
                        parent.addComment(pp.getText());
                        break;
                    }
                    document.addComment(pp.getText());
                    break;
                }
                case 5: {
                    if (parent != null) {
                        parent.addCDATA(pp.getText());
                        break;
                    }
                    String msg = "Cannot have text content outside of the root document";
                    throw new DocumentException(msg);
                }
                case 6: {
                    break;
                }
                case 1: {
                    return document;
                }
                case 2: {
                    int i;
                    QName qname = pp.getPrefix() == null ? df.createQName(pp.getName(), pp.getNamespace()) : df.createQName(pp.getName(), pp.getPrefix(), pp.getNamespace());
                    Element newElement = df.createElement(qname);
                    int nsStart = pp.getNamespaceCount(pp.getDepth() - 1);
                    int nsEnd = pp.getNamespaceCount(pp.getDepth());
                    for (i = nsStart; i < nsEnd; ++i) {
                        if (pp.getNamespacePrefix(i) == null) continue;
                        newElement.addNamespace(pp.getNamespacePrefix(i), pp.getNamespaceUri(i));
                    }
                    for (i = 0; i < pp.getAttributeCount(); ++i) {
                        QName qa = pp.getAttributePrefix(i) == null ? df.createQName(pp.getAttributeName(i)) : df.createQName(pp.getAttributeName(i), pp.getAttributePrefix(i), pp.getAttributeNamespace(i));
                        newElement.addAttribute(qa, pp.getAttributeValue(i));
                    }
                    if (parent != null) {
                        parent.add(newElement);
                    } else {
                        document.add(newElement);
                    }
                    parent = newElement;
                    break;
                }
                case 3: {
                    if (parent == null) break;
                    parent = parent.getParent();
                    break;
                }
                case 4: {
                    String text = pp.getText();
                    if (parent != null) {
                        parent.addText(text);
                        break;
                    }
                    String msg = "Cannot have text content outside of the root document";
                    throw new DocumentException(msg);
                }
            }
        }
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

