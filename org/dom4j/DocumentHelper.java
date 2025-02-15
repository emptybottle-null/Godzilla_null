/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.jaxen.VariableContext
 */
package org.dom4j;

import java.io.StringReader;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import org.dom4j.Attribute;
import org.dom4j.Branch;
import org.dom4j.CDATA;
import org.dom4j.Comment;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.Entity;
import org.dom4j.InvalidXPathException;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.NodeFilter;
import org.dom4j.ProcessingInstruction;
import org.dom4j.QName;
import org.dom4j.Text;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;
import org.dom4j.rule.Pattern;
import org.jaxen.VariableContext;
import org.xml.sax.InputSource;

public final class DocumentHelper {
    private DocumentHelper() {
    }

    private static DocumentFactory getDocumentFactory() {
        return DocumentFactory.getInstance();
    }

    public static Document createDocument() {
        return DocumentHelper.getDocumentFactory().createDocument();
    }

    public static Document createDocument(Element rootElement) {
        return DocumentHelper.getDocumentFactory().createDocument(rootElement);
    }

    public static Element createElement(QName qname) {
        return DocumentHelper.getDocumentFactory().createElement(qname);
    }

    public static Element createElement(String name) {
        return DocumentHelper.getDocumentFactory().createElement(name);
    }

    public static Attribute createAttribute(Element owner, QName qname, String value) {
        return DocumentHelper.getDocumentFactory().createAttribute(owner, qname, value);
    }

    public static Attribute createAttribute(Element owner, String name, String value) {
        return DocumentHelper.getDocumentFactory().createAttribute(owner, name, value);
    }

    public static CDATA createCDATA(String text) {
        return DocumentFactory.getInstance().createCDATA(text);
    }

    public static Comment createComment(String text) {
        return DocumentFactory.getInstance().createComment(text);
    }

    public static Text createText(String text) {
        return DocumentFactory.getInstance().createText(text);
    }

    public static Entity createEntity(String name, String text) {
        return DocumentFactory.getInstance().createEntity(name, text);
    }

    public static Namespace createNamespace(String prefix, String uri) {
        return DocumentFactory.getInstance().createNamespace(prefix, uri);
    }

    public static ProcessingInstruction createProcessingInstruction(String pi, String d) {
        return DocumentHelper.getDocumentFactory().createProcessingInstruction(pi, d);
    }

    public static ProcessingInstruction createProcessingInstruction(String pi, Map<String, String> data) {
        return DocumentHelper.getDocumentFactory().createProcessingInstruction(pi, data);
    }

    public static QName createQName(String localName, Namespace namespace) {
        return DocumentHelper.getDocumentFactory().createQName(localName, namespace);
    }

    public static QName createQName(String localName) {
        return DocumentHelper.getDocumentFactory().createQName(localName);
    }

    public static XPath createXPath(String xpathExpression) throws InvalidXPathException {
        return DocumentHelper.getDocumentFactory().createXPath(xpathExpression);
    }

    public static XPath createXPath(String xpathExpression, VariableContext context) throws InvalidXPathException {
        return DocumentHelper.getDocumentFactory().createXPath(xpathExpression, context);
    }

    public static NodeFilter createXPathFilter(String xpathFilterExpression) {
        return DocumentHelper.getDocumentFactory().createXPathFilter(xpathFilterExpression);
    }

    public static Pattern createPattern(String xpathPattern) {
        return DocumentHelper.getDocumentFactory().createPattern(xpathPattern);
    }

    public static List<Node> selectNodes(String xpathFilterExpression, List<Node> nodes) {
        XPath xpath = DocumentHelper.createXPath(xpathFilterExpression);
        return xpath.selectNodes(nodes);
    }

    public static List<Node> selectNodes(String xpathFilterExpression, Node node) {
        XPath xpath = DocumentHelper.createXPath(xpathFilterExpression);
        return xpath.selectNodes(node);
    }

    public static void sort(List<Node> list, String xpathExpression) {
        XPath xpath = DocumentHelper.createXPath(xpathExpression);
        xpath.sort(list);
    }

    public static void sort(List<Node> list, String expression, boolean distinct) {
        XPath xpath = DocumentHelper.createXPath(expression);
        xpath.sort(list, distinct);
    }

    public static Document parseText(String text) throws DocumentException {
        SAXReader reader = SAXReader.createDefault();
        String encoding = DocumentHelper.getEncoding(text);
        InputSource source = new InputSource(new StringReader(text));
        source.setEncoding(encoding);
        Document result = reader.read(source);
        if (result.getXMLEncoding() == null) {
            result.setXMLEncoding(encoding);
        }
        return result;
    }

    private static String getEncoding(String text) {
        String result = null;
        String xml = text.trim();
        if (xml.startsWith("<?xml")) {
            int end = xml.indexOf("?>");
            String sub = xml.substring(0, end);
            StringTokenizer tokens = new StringTokenizer(sub, " =\"'");
            while (tokens.hasMoreTokens()) {
                String token = tokens.nextToken();
                if (!"encoding".equals(token)) continue;
                if (!tokens.hasMoreTokens()) break;
                result = tokens.nextToken();
                break;
            }
        }
        return result;
    }

    public static Element makeElement(Branch source, String path) {
        String name;
        Element parent;
        StringTokenizer tokens = new StringTokenizer(path, "/");
        if (source instanceof Document) {
            Document document = (Document)source;
            parent = document.getRootElement();
            name = tokens.nextToken();
            if (parent == null) {
                parent = document.addElement(name);
            }
        } else {
            parent = (Element)source;
        }
        Element element = null;
        while (tokens.hasMoreTokens()) {
            name = tokens.nextToken();
            element = name.indexOf(58) > 0 ? parent.element(parent.getQName(name)) : parent.element(name);
            if (element == null) {
                element = parent.addElement(name);
            }
            parent = element;
        }
        return element;
    }
}

