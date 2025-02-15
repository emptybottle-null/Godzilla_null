/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ooxml.util;

import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.stream.events.Namespace;
import org.apache.poi.util.XMLHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public final class DocumentHelper {
    private static final DocumentBuilder documentBuilderSingleton = DocumentHelper.newDocumentBuilder();

    private DocumentHelper() {
    }

    public static DocumentBuilder newDocumentBuilder() {
        return XMLHelper.newDocumentBuilder();
    }

    public static Document readDocument(InputStream inp) throws IOException, SAXException {
        return DocumentHelper.newDocumentBuilder().parse(inp);
    }

    public static Document readDocument(InputSource inp) throws IOException, SAXException {
        return DocumentHelper.newDocumentBuilder().parse(inp);
    }

    public static Document createDocument() {
        return documentBuilderSingleton.newDocument();
    }

    public static void addNamespaceDeclaration(Element element, String namespacePrefix, String namespaceURI) {
        element.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:" + namespacePrefix, namespaceURI);
    }

    public static void addNamespaceDeclaration(Element element, Namespace namespace) {
        DocumentHelper.addNamespaceDeclaration(element, namespace.getPrefix(), namespace.getNamespaceURI());
    }
}

