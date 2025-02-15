/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xpathgen;

import java.util.Iterator;
import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.xpathgen.XPathGenerationException;

public class XPathGenerator {
    public static String generateXPath(XmlCursor node, XmlCursor context, NamespaceContext nsctx) throws XPathGenerationException {
        if (node == null) {
            throw new IllegalArgumentException("Null node");
        }
        if (nsctx == null) {
            throw new IllegalArgumentException("Null namespace context");
        }
        XmlCursor.TokenType tt = node.currentTokenType();
        if (context != null && node.isAtSamePositionAs(context)) {
            return ".";
        }
        switch (tt.intValue()) {
            case 6: {
                QName name = node.getName();
                node.toParent();
                String pathToParent = XPathGenerator.generateInternal(node, context, nsctx);
                return pathToParent + '/' + '@' + XPathGenerator.qnameToString(name, nsctx);
            }
            case 7: {
                QName name = node.getName();
                node.toParent();
                String pathToParent = XPathGenerator.generateInternal(node, context, nsctx);
                String prefix = name.getLocalPart();
                if (prefix.length() == 0) {
                    return pathToParent + "/@xmlns";
                }
                return pathToParent + "/@xmlns:" + prefix;
            }
            case 1: 
            case 3: {
                return XPathGenerator.generateInternal(node, context, nsctx);
            }
            case 5: {
                int nrOfTextTokens = XPathGenerator.countTextTokens(node);
                node.toParent();
                String pathToParent = XPathGenerator.generateInternal(node, context, nsctx);
                if (nrOfTextTokens == 0) {
                    return pathToParent + "/text()";
                }
                return pathToParent + "/text()[position()=" + nrOfTextTokens + ']';
            }
        }
        throw new XPathGenerationException("Cannot generate XPath for cursor position: " + tt.toString());
    }

    private static String generateInternal(XmlCursor node, XmlCursor context, NamespaceContext nsctx) throws XPathGenerationException {
        if (node.isStartdoc()) {
            return "";
        }
        if (context != null && node.isAtSamePositionAs(context)) {
            return ".";
        }
        assert (node.isStart());
        QName name = node.getName();
        XmlCursor d = node.newCursor();
        if (!node.toParent()) {
            return "/" + name;
        }
        int elemIndex = 0;
        int i = 1;
        node.push();
        if (!node.toChild(name)) {
            throw new IllegalStateException("Must have at least one child with name: " + name);
        }
        do {
            if (node.isAtSamePositionAs(d)) {
                elemIndex = i;
                continue;
            }
            ++i;
        } while (node.toNextSibling(name));
        node.pop();
        d.dispose();
        String pathToParent = XPathGenerator.generateInternal(node, context, nsctx);
        return i == 1 ? pathToParent + '/' + XPathGenerator.qnameToString(name, nsctx) : pathToParent + '/' + XPathGenerator.qnameToString(name, nsctx) + '[' + elemIndex + ']';
    }

    private static String qnameToString(QName qname, NamespaceContext ctx) throws XPathGenerationException {
        String mappedUri;
        String localName = qname.getLocalPart();
        String uri = qname.getNamespaceURI();
        if (uri.length() == 0) {
            return localName;
        }
        String prefix = qname.getPrefix();
        if (prefix != null && prefix.length() > 0 && uri.equals(mappedUri = ctx.getNamespaceURI(prefix))) {
            return prefix + ':' + localName;
        }
        prefix = ctx.getPrefix(uri);
        if (prefix == null) {
            throw new XPathGenerationException("Could not obtain a prefix for URI: " + uri);
        }
        if (prefix.length() == 0) {
            throw new XPathGenerationException("Can not use default prefix in XPath for URI: " + uri);
        }
        return prefix + ':' + localName;
    }

    private static int countTextTokens(XmlCursor c) {
        int k = 0;
        int l = 0;
        XmlCursor d = c.newCursor();
        c.push();
        c.toParent();
        XmlCursor.TokenType tt = c.toFirstContentToken();
        while (!tt.isEnd()) {
            if (tt.isText()) {
                if (c.comparePosition(d) > 0) {
                    ++l;
                } else {
                    ++k;
                }
            } else if (tt.isStart()) {
                c.toEndToken();
            }
            tt = c.toNextToken();
        }
        c.pop();
        return l == 0 ? 0 : k;
    }

    public static void main(String[] args) throws XmlException {
        String xml = "<root>\n<ns:a xmlns:ns=\"http://a.com\"><b foo=\"value\">text1<c/>text2<c/>text3<c>text</c>text4</b></ns:a>\n</root>";
        NamespaceContext ns = new NamespaceContext(){

            @Override
            public String getNamespaceURI(String prefix) {
                if ("ns".equals(prefix)) {
                    return "http://a.com";
                }
                return null;
            }

            @Override
            public String getPrefix(String namespaceUri) {
                return null;
            }

            @Override
            public Iterator getPrefixes(String namespaceUri) {
                return null;
            }
        };
        XmlCursor c = XmlObject.Factory.parse(xml).newCursor();
        c.toFirstContentToken();
        c.toFirstContentToken();
        c.toFirstChild();
        c.toFirstChild();
        c.push();
        System.out.println(XPathGenerator.generateXPath(c, null, ns));
        c.pop();
        c.toNextSibling();
        c.toNextSibling();
        c.push();
        System.out.println(XPathGenerator.generateXPath(c, null, ns));
        c.pop();
        XmlCursor d = c.newCursor();
        d.toParent();
        c.push();
        System.out.println(XPathGenerator.generateXPath(c, d, ns));
        c.pop();
        d.toParent();
        c.push();
        System.out.println(XPathGenerator.generateXPath(c, d, ns));
        c.pop();
        c.toFirstContentToken();
        c.push();
        System.out.println(XPathGenerator.generateXPath(c, d, ns));
        c.pop();
        c.toParent();
        c.toPrevToken();
        c.push();
        System.out.println(XPathGenerator.generateXPath(c, d, ns));
        c.pop();
        c.toParent();
        c.push();
        System.out.println(XPathGenerator.generateXPath(c, d, ns));
        c.pop();
        c.toFirstAttribute();
        c.push();
        System.out.println(XPathGenerator.generateXPath(c, d, ns));
        c.pop();
        c.toParent();
        c.toParent();
        c.toNextToken();
        c.push();
        System.out.println(XPathGenerator.generateXPath(c, d, ns));
        c.pop();
        c.push();
        System.out.println(XPathGenerator.generateXPath(c, null, ns));
        c.pop();
    }
}

