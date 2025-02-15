/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.store;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Source;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlRuntimeException;
import org.apache.xmlbeans.impl.common.XMLChar;
import org.apache.xmlbeans.impl.soap.Detail;
import org.apache.xmlbeans.impl.soap.DetailEntry;
import org.apache.xmlbeans.impl.soap.Name;
import org.apache.xmlbeans.impl.soap.Node;
import org.apache.xmlbeans.impl.soap.SOAPBody;
import org.apache.xmlbeans.impl.soap.SOAPBodyElement;
import org.apache.xmlbeans.impl.soap.SOAPElement;
import org.apache.xmlbeans.impl.soap.SOAPEnvelope;
import org.apache.xmlbeans.impl.soap.SOAPException;
import org.apache.xmlbeans.impl.soap.SOAPFault;
import org.apache.xmlbeans.impl.soap.SOAPHeader;
import org.apache.xmlbeans.impl.soap.SOAPHeaderElement;
import org.apache.xmlbeans.impl.soap.SOAPPart;
import org.apache.xmlbeans.impl.store.CharUtil;
import org.apache.xmlbeans.impl.store.Cur;
import org.apache.xmlbeans.impl.store.Cursor;
import org.apache.xmlbeans.impl.store.Jsr173;
import org.apache.xmlbeans.impl.store.Locale;
import org.apache.xmlbeans.impl.store.Xobj;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.w3c.dom.UserDataHandler;

final class DomImpl {
    static final int ELEMENT = 1;
    static final int ATTR = 2;
    static final int TEXT = 3;
    static final int CDATA = 4;
    static final int ENTITYREF = 5;
    static final int ENTITY = 6;
    static final int PROCINST = 7;
    static final int COMMENT = 8;
    static final int DOCUMENT = 9;
    static final int DOCTYPE = 10;
    static final int DOCFRAG = 11;
    static final int NOTATION = 12;
    public static NodeList _emptyNodeList = new EmptyNodeList();

    DomImpl() {
    }

    static Dom parent(Dom d) {
        return DomImpl.node_getParentNode(d);
    }

    static Dom firstChild(Dom d) {
        return DomImpl.node_getFirstChild(d);
    }

    static Dom nextSibling(Dom d) {
        return DomImpl.node_getNextSibling(d);
    }

    static Dom prevSibling(Dom d) {
        return DomImpl.node_getPreviousSibling(d);
    }

    public static Dom append(Dom n, Dom p) {
        return DomImpl.node_insertBefore(p, n, null);
    }

    public static Dom insert(Dom n, Dom b) {
        assert (b != null);
        return DomImpl.node_insertBefore(DomImpl.parent(b), n, b);
    }

    public static Dom remove(Dom n) {
        Dom p = DomImpl.parent(n);
        if (p != null) {
            DomImpl.node_removeChild(p, n);
        }
        return n;
    }

    static String nodeKindName(int t) {
        switch (t) {
            case 2: {
                return "attribute";
            }
            case 4: {
                return "cdata section";
            }
            case 8: {
                return "comment";
            }
            case 11: {
                return "document fragment";
            }
            case 9: {
                return "document";
            }
            case 10: {
                return "document type";
            }
            case 1: {
                return "element";
            }
            case 6: {
                return "entity";
            }
            case 5: {
                return "entity reference";
            }
            case 12: {
                return "notation";
            }
            case 7: {
                return "processing instruction";
            }
            case 3: {
                return "text";
            }
        }
        throw new RuntimeException("Unknown node type");
    }

    private static String isValidChild(Dom parent, Dom child) {
        int pk = parent.nodeType();
        int ck = child.nodeType();
        switch (pk) {
            case 9: {
                switch (ck) {
                    case 1: {
                        if (DomImpl.document_getDocumentElement(parent) != null) {
                            return "Documents may only have a maximum of one document element";
                        }
                        return null;
                    }
                    case 10: {
                        if (DomImpl.document_getDoctype(parent) != null) {
                            return "Documents may only have a maximum of one document type node";
                        }
                        return null;
                    }
                    case 7: 
                    case 8: {
                        return null;
                    }
                }
                break;
            }
            case 2: {
                if (ck != 3 && ck != 5) break;
                return null;
            }
            case 1: 
            case 5: 
            case 6: 
            case 11: {
                switch (ck) {
                    case 1: 
                    case 3: 
                    case 4: 
                    case 5: 
                    case 7: 
                    case 8: {
                        return null;
                    }
                }
                break;
            }
            case 3: 
            case 4: 
            case 7: 
            case 8: 
            case 10: 
            case 12: {
                return DomImpl.nodeKindName(pk) + " nodes may not have any children";
            }
        }
        return DomImpl.nodeKindName(pk) + " nodes may not have " + DomImpl.nodeKindName(ck) + " nodes as children";
    }

    private static void validateNewChild(Dom parent, Dom child) {
        String msg = DomImpl.isValidChild(parent, child);
        if (msg != null) {
            throw new HierarchyRequestErr(msg);
        }
        if (parent == child) {
            throw new HierarchyRequestErr("New child and parent are the same node");
        }
        while ((parent = DomImpl.parent(parent)) != null) {
            if (child.nodeType() == 5) {
                throw new NoModificationAllowedErr("Entity reference trees may not be modified");
            }
            if (child != parent) continue;
            throw new HierarchyRequestErr("New child is an ancestor node of the parent node");
        }
    }

    private static String validatePrefix(String prefix, String uri, String local, boolean isAttr) {
        DomImpl.validateNcName(prefix);
        if (prefix == null) {
            prefix = "";
        }
        if (uri == null) {
            uri = "";
        }
        if (prefix.length() > 0 && uri.length() == 0) {
            throw new NamespaceErr("Attempt to give a prefix for no namespace");
        }
        if (prefix.equals("xml") && !uri.equals("http://www.w3.org/XML/1998/namespace")) {
            throw new NamespaceErr("Invalid prefix - begins with 'xml'");
        }
        if (isAttr) {
            if (prefix.length() > 0) {
                if (local.equals("xmlns")) {
                    throw new NamespaceErr("Invalid namespace - attr is default namespace already");
                }
                if (Locale.beginsWithXml(local)) {
                    throw new NamespaceErr("Invalid namespace - attr prefix begins with 'xml'");
                }
                if (prefix.equals("xmlns") && !uri.equals("http://www.w3.org/2000/xmlns/")) {
                    throw new NamespaceErr("Invalid namespace - uri is not 'http://www.w3.org/2000/xmlns/;");
                }
            } else if (local.equals("xmlns") && !uri.equals("http://www.w3.org/2000/xmlns/")) {
                throw new NamespaceErr("Invalid namespace - uri is not 'http://www.w3.org/2000/xmlns/;");
            }
        } else if (Locale.beginsWithXml(prefix)) {
            throw new NamespaceErr("Invalid prefix - begins with 'xml'");
        }
        return prefix;
    }

    private static void validateName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name is null");
        }
        if (name.length() == 0) {
            throw new IllegalArgumentException("Name is empty");
        }
        if (!XMLChar.isValidName(name)) {
            throw new InvalidCharacterError("Name has an invalid character");
        }
    }

    private static void validateNcName(String name) {
        if (name != null && name.length() > 0 && !XMLChar.isValidNCName(name)) {
            throw new InvalidCharacterError();
        }
    }

    private static void validateQualifiedName(String name, String uri, boolean isAttr) {
        String local;
        int i;
        assert (name != null);
        if (uri == null) {
            uri = "";
        }
        if ((i = name.indexOf(58)) < 0) {
            local = name;
            DomImpl.validateNcName(local);
            if (isAttr && local.equals("xmlns") && !uri.equals("http://www.w3.org/2000/xmlns/")) {
                throw new NamespaceErr("Default xmlns attribute does not have namespace: http://www.w3.org/2000/xmlns/");
            }
        } else {
            if (i == 0) {
                throw new NamespaceErr("Invalid qualified name, no prefix specified");
            }
            String prefix = name.substring(0, i);
            DomImpl.validateNcName(prefix);
            if (uri.length() == 0) {
                throw new NamespaceErr("Attempt to give a prefix for no namespace");
            }
            local = name.substring(i + 1);
            if (local.indexOf(58) >= 0) {
                throw new NamespaceErr("Invalid qualified name, more than one colon");
            }
            DomImpl.validateNcName(local);
            if (prefix.equals("xml") && !uri.equals("http://www.w3.org/XML/1998/namespace")) {
                throw new NamespaceErr("Invalid prefix - begins with 'xml'");
            }
        }
        if (local.length() == 0) {
            throw new NamespaceErr("Invalid qualified name, no local part specified");
        }
    }

    private static void removeNode(Dom n) {
        CharNode fromNodes;
        assert (n.nodeType() != 3 && n.nodeType() != 4);
        Cur cFrom = n.tempCur();
        cFrom.toEnd();
        if (cFrom.next() && (fromNodes = cFrom.getCharNodes()) != null) {
            cFrom.setCharNodes(null);
            Cur cTo = n.tempCur();
            cTo.setCharNodes(CharNode.appendNodes(cTo.getCharNodes(), fromNodes));
            cTo.release();
        }
        cFrom.release();
        Cur.moveNode((Xobj)((Object)n), null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Document _domImplementation_createDocument(Locale l, String u, String n, DocumentType t) {
        if (l.noSync()) {
            l.enter();
            try {
                Document document = DomImpl.domImplementation_createDocument(l, u, n, t);
                return document;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            Document document;
            l.enter();
            try {
                document = DomImpl.domImplementation_createDocument(l, u, n, t);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return document;
        }
    }

    public static Document domImplementation_createDocument(Locale l, String namespaceURI, String qualifiedName, DocumentType doctype) {
        DomImpl.validateQualifiedName(qualifiedName, namespaceURI, false);
        Cur c = l.tempCur();
        c.createDomDocumentRoot();
        Document doc = (Document)((Object)c.getDom());
        c.next();
        c.createElement(l.makeQualifiedQName(namespaceURI, qualifiedName));
        if (doctype != null) {
            throw new RuntimeException("Not impl");
        }
        c.toParent();
        try {
            Locale.autoTypeDocument(c, null, null);
        } catch (XmlException e) {
            throw new XmlRuntimeException(e);
        }
        c.release();
        return doc;
    }

    public static boolean _domImplementation_hasFeature(Locale l, String feature, String version) {
        if (feature == null) {
            return false;
        }
        if (version != null && version.length() > 0 && !version.equals("1.0") && !version.equals("2.0")) {
            return false;
        }
        if (feature.equalsIgnoreCase("core")) {
            return true;
        }
        return feature.equalsIgnoreCase("xml");
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Element _document_getDocumentElement(Dom d) {
        Dom e;
        Locale l = d.locale();
        if (l.noSync()) {
            l.enter();
            try {
                e = DomImpl.document_getDocumentElement(d);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                e = DomImpl.document_getDocumentElement(d);
            } finally {
                l.exit();
            }
        }
        return (Element)((Object)e);
    }

    public static Dom document_getDocumentElement(Dom d) {
        d = DomImpl.firstChild(d);
        while (d != null) {
            if (d.nodeType() == 1) {
                return d;
            }
            d = DomImpl.nextSibling(d);
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static DocumentFragment _document_createDocumentFragment(Dom d) {
        Dom f;
        Locale l = d.locale();
        if (l.noSync()) {
            l.enter();
            try {
                f = DomImpl.document_createDocumentFragment(d);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                f = DomImpl.document_createDocumentFragment(d);
            } finally {
                l.exit();
            }
        }
        return (DocumentFragment)((Object)f);
    }

    public static Dom document_createDocumentFragment(Dom d) {
        Cur c = d.locale().tempCur();
        c.createDomDocFragRoot();
        Dom f = c.getDom();
        c.release();
        return f;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Element _document_createElement(Dom d, String name) {
        Dom e;
        Locale l = d.locale();
        if (l.noSync()) {
            l.enter();
            try {
                e = DomImpl.document_createElement(d, name);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                e = DomImpl.document_createElement(d, name);
            } finally {
                l.exit();
            }
        }
        return (Element)((Object)e);
    }

    public static Dom document_createElement(Dom d, String name) {
        DomImpl.validateName(name);
        Locale l = d.locale();
        Cur c = l.tempCur();
        c.createElement(l.makeQualifiedQName("", name));
        Dom e = c.getDom();
        c.release();
        ((Xobj.ElementXobj)e)._canHavePrefixUri = false;
        return e;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Element _document_createElementNS(Dom d, String uri, String qname) {
        Dom e;
        Locale l = d.locale();
        if (l.noSync()) {
            l.enter();
            try {
                e = DomImpl.document_createElementNS(d, uri, qname);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                e = DomImpl.document_createElementNS(d, uri, qname);
            } finally {
                l.exit();
            }
        }
        return (Element)((Object)e);
    }

    public static Dom document_createElementNS(Dom d, String uri, String qname) {
        DomImpl.validateQualifiedName(qname, uri, false);
        Locale l = d.locale();
        Cur c = l.tempCur();
        c.createElement(l.makeQualifiedQName(uri, qname));
        Dom e = c.getDom();
        c.release();
        return e;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Attr _document_createAttribute(Dom d, String name) {
        Dom a;
        Locale l = d.locale();
        if (l.noSync()) {
            l.enter();
            try {
                a = DomImpl.document_createAttribute(d, name);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                a = DomImpl.document_createAttribute(d, name);
            } finally {
                l.exit();
            }
        }
        return (Attr)((Object)a);
    }

    public static Dom document_createAttribute(Dom d, String name) {
        DomImpl.validateName(name);
        Locale l = d.locale();
        Cur c = l.tempCur();
        c.createAttr(l.makeQualifiedQName("", name));
        Dom e = c.getDom();
        c.release();
        ((Xobj.AttrXobj)e)._canHavePrefixUri = false;
        return e;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Attr _document_createAttributeNS(Dom d, String uri, String qname) {
        Dom a;
        Locale l = d.locale();
        if (l.noSync()) {
            l.enter();
            try {
                a = DomImpl.document_createAttributeNS(d, uri, qname);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                a = DomImpl.document_createAttributeNS(d, uri, qname);
            } finally {
                l.exit();
            }
        }
        return (Attr)((Object)a);
    }

    public static Dom document_createAttributeNS(Dom d, String uri, String qname) {
        DomImpl.validateQualifiedName(qname, uri, true);
        Locale l = d.locale();
        Cur c = l.tempCur();
        c.createAttr(l.makeQualifiedQName(uri, qname));
        Dom e = c.getDom();
        c.release();
        return e;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Comment _document_createComment(Dom d, String data) {
        Dom c;
        Locale l = d.locale();
        if (l.noSync()) {
            l.enter();
            try {
                c = DomImpl.document_createComment(d, data);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                c = DomImpl.document_createComment(d, data);
            } finally {
                l.exit();
            }
        }
        return (Comment)((Object)c);
    }

    public static Dom document_createComment(Dom d, String data) {
        Locale l = d.locale();
        Cur c = l.tempCur();
        c.createComment();
        Dom comment = c.getDom();
        if (data != null) {
            c.next();
            c.insertString(data);
        }
        c.release();
        return comment;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static ProcessingInstruction _document_createProcessingInstruction(Dom d, String target, String data) {
        Dom pi;
        Locale l = d.locale();
        if (l.noSync()) {
            l.enter();
            try {
                pi = DomImpl.document_createProcessingInstruction(d, target, data);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                pi = DomImpl.document_createProcessingInstruction(d, target, data);
            } finally {
                l.exit();
            }
        }
        return (ProcessingInstruction)((Object)pi);
    }

    public static Dom document_createProcessingInstruction(Dom d, String target, String data) {
        if (target == null) {
            throw new IllegalArgumentException("Target is null");
        }
        if (target.length() == 0) {
            throw new IllegalArgumentException("Target is empty");
        }
        if (!XMLChar.isValidName(target)) {
            throw new InvalidCharacterError("Target has an invalid character");
        }
        if (Locale.beginsWithXml(target) && target.length() == 3) {
            throw new InvalidCharacterError("Invalid target - is 'xml'");
        }
        Locale l = d.locale();
        Cur c = l.tempCur();
        c.createProcinst(target);
        Dom pi = c.getDom();
        if (data != null) {
            c.next();
            c.insertString(data);
        }
        c.release();
        return pi;
    }

    public static CDATASection _document_createCDATASection(Dom d, String data) {
        return (CDATASection)((Object)DomImpl.document_createCDATASection(d, data));
    }

    public static Dom document_createCDATASection(Dom d, String data) {
        CdataNode t = d.locale().createCdataNode();
        if (data == null) {
            data = "";
        }
        t.setChars(data, 0, data.length());
        return t;
    }

    public static Text _document_createTextNode(Dom d, String data) {
        return (Text)((Object)DomImpl.document_createTextNode(d, data));
    }

    public static CharNode document_createTextNode(Dom d, String data) {
        TextNode t = d.locale().createTextNode();
        if (data == null) {
            data = "";
        }
        t.setChars(data, 0, data.length());
        return t;
    }

    public static EntityReference _document_createEntityReference(Dom d, String name) {
        throw new RuntimeException("Not implemented");
    }

    public static Element _document_getElementById(Dom d, String elementId) {
        throw new RuntimeException("Not implemented");
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static NodeList _document_getElementsByTagName(Dom d, String name) {
        Locale l = d.locale();
        if (l.noSync()) {
            l.enter();
            try {
                NodeList nodeList = DomImpl.document_getElementsByTagName(d, name);
                return nodeList;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            NodeList nodeList;
            l.enter();
            try {
                nodeList = DomImpl.document_getElementsByTagName(d, name);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return nodeList;
        }
    }

    public static NodeList document_getElementsByTagName(Dom d, String name) {
        return new ElementsByTagNameNodeList(d, name);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static NodeList _document_getElementsByTagNameNS(Dom d, String uri, String local) {
        Locale l = d.locale();
        if (l.noSync()) {
            l.enter();
            try {
                NodeList nodeList = DomImpl.document_getElementsByTagNameNS(d, uri, local);
                return nodeList;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            NodeList nodeList;
            l.enter();
            try {
                nodeList = DomImpl.document_getElementsByTagNameNS(d, uri, local);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return nodeList;
        }
    }

    public static NodeList document_getElementsByTagNameNS(Dom d, String uri, String local) {
        return new ElementsByTagNameNSNodeList(d, uri, local);
    }

    public static DOMImplementation _document_getImplementation(Dom d) {
        return d.locale();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static org.w3c.dom.Node _document_importNode(Dom d, org.w3c.dom.Node n, boolean deep) {
        Dom i;
        Locale l = d.locale();
        if (l.noSync()) {
            l.enter();
            try {
                i = DomImpl.document_importNode(d, n, deep);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                i = DomImpl.document_importNode(d, n, deep);
            } finally {
                l.exit();
            }
        }
        return (org.w3c.dom.Node)((Object)i);
    }

    public static Dom document_importNode(Dom d, org.w3c.dom.Node n, boolean deep) {
        Dom i;
        if (n == null) {
            return null;
        }
        boolean copyChildren = false;
        switch (n.getNodeType()) {
            case 9: {
                throw new NotSupportedError("Document nodes may not be imported");
            }
            case 10: {
                throw new NotSupportedError("Document type nodes may not be imported");
            }
            case 1: {
                String uri;
                String prefix;
                String local = n.getLocalName();
                if (local == null || local.length() == 0) {
                    i = DomImpl.document_createElement(d, n.getNodeName());
                } else {
                    prefix = n.getPrefix();
                    String name = prefix == null || prefix.length() == 0 ? local : prefix + ":" + local;
                    uri = n.getNamespaceURI();
                    i = uri == null || uri.length() == 0 ? DomImpl.document_createElement(d, name) : DomImpl.document_createElementNS(d, uri, name);
                }
                NamedNodeMap attrs = n.getAttributes();
                for (int a = 0; a < attrs.getLength(); ++a) {
                    DomImpl.attributes_setNamedItem(i, DomImpl.document_importNode(d, attrs.item(a), true));
                }
                copyChildren = deep;
                break;
            }
            case 2: {
                String uri;
                String prefix;
                String local = n.getLocalName();
                if (local == null || local.length() == 0) {
                    i = DomImpl.document_createAttribute(d, n.getNodeName());
                } else {
                    prefix = n.getPrefix();
                    String name = prefix == null || prefix.length() == 0 ? local : prefix + ":" + local;
                    uri = n.getNamespaceURI();
                    i = uri == null || uri.length() == 0 ? DomImpl.document_createAttribute(d, name) : DomImpl.document_createAttributeNS(d, uri, name);
                }
                copyChildren = true;
                break;
            }
            case 11: {
                i = DomImpl.document_createDocumentFragment(d);
                copyChildren = deep;
                break;
            }
            case 7: {
                i = DomImpl.document_createProcessingInstruction(d, n.getNodeName(), n.getNodeValue());
                break;
            }
            case 8: {
                i = DomImpl.document_createComment(d, n.getNodeValue());
                break;
            }
            case 3: {
                i = DomImpl.document_createTextNode(d, n.getNodeValue());
                break;
            }
            case 4: {
                i = DomImpl.document_createCDATASection(d, n.getNodeValue());
                break;
            }
            case 5: 
            case 6: 
            case 12: {
                throw new RuntimeException("Not impl");
            }
            default: {
                throw new RuntimeException("Unknown kind");
            }
        }
        if (copyChildren) {
            NodeList children = n.getChildNodes();
            for (int c = 0; c < children.getLength(); ++c) {
                DomImpl.node_insertBefore(i, DomImpl.document_importNode(d, children.item(c), true), null);
            }
        }
        return i;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static DocumentType _document_getDoctype(Dom d) {
        Dom dt;
        Locale l = d.locale();
        if (l.noSync()) {
            l.enter();
            try {
                dt = DomImpl.document_getDoctype(d);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                dt = DomImpl.document_getDoctype(d);
            } finally {
                l.exit();
            }
        }
        return (DocumentType)((Object)dt);
    }

    public static Dom document_getDoctype(Dom d) {
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Document _node_getOwnerDocument(Dom n) {
        Dom d;
        Locale l = n.locale();
        if (l.noSync()) {
            l.enter();
            try {
                d = DomImpl.node_getOwnerDocument(n);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                d = DomImpl.node_getOwnerDocument(n);
            } finally {
                l.exit();
            }
        }
        return (Document)((Object)d);
    }

    public static Dom node_getOwnerDocument(Dom n) {
        if (n.nodeType() == 9) {
            return null;
        }
        Locale l = n.locale();
        if (l._ownerDoc == null) {
            Cur c = l.tempCur();
            c.createDomDocumentRoot();
            l._ownerDoc = c.getDom();
            c.release();
        }
        return l._ownerDoc;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static org.w3c.dom.Node _node_getParentNode(Dom n) {
        Dom p;
        Locale l = n.locale();
        if (l.noSync()) {
            l.enter();
            try {
                p = DomImpl.node_getParentNode(n);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                p = DomImpl.node_getParentNode(n);
            } finally {
                l.exit();
            }
        }
        return (org.w3c.dom.Node)((Object)p);
    }

    public static Dom node_getParentNode(Dom n) {
        Cur c = null;
        switch (n.nodeType()) {
            case 2: 
            case 9: 
            case 11: {
                break;
            }
            case 1: 
            case 7: 
            case 8: {
                c = n.tempCur();
                if (c.toParentRaw()) break;
                c.release();
                c = null;
                break;
            }
            case 3: 
            case 4: {
                c = n.tempCur();
                if (c == null) break;
                c.toParent();
                break;
            }
            case 5: {
                throw new RuntimeException("Not impl");
            }
            case 6: 
            case 10: 
            case 12: {
                throw new RuntimeException("Not impl");
            }
            default: {
                throw new RuntimeException("Unknown kind");
            }
        }
        if (c == null) {
            return null;
        }
        Dom d = c.getDom();
        c.release();
        return d;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static org.w3c.dom.Node _node_getFirstChild(Dom n) {
        Dom fc;
        Locale l = n.locale();
        assert (n instanceof Xobj);
        Xobj node = (Xobj)((Object)n);
        if (!node.isVacant()) {
            if (node.isFirstChildPtrDomUsable()) {
                return (org.w3c.dom.Node)((Object)node._firstChild);
            }
            Xobj lastAttr = node.lastAttr();
            if (lastAttr != null && lastAttr.isNextSiblingPtrDomUsable()) {
                return (Xobj.NodeXobj)lastAttr._nextSibling;
            }
            if (node.isExistingCharNodesValueUsable()) {
                return node._charNodesValue;
            }
        }
        if (l.noSync()) {
            fc = DomImpl.node_getFirstChild(n);
        } else {
            Locale locale = l;
            synchronized (locale) {
                fc = DomImpl.node_getFirstChild(n);
            }
        }
        return (org.w3c.dom.Node)((Object)fc);
    }

    public static Dom node_getFirstChild(Dom n) {
        Dom fc = null;
        switch (n.nodeType()) {
            case 3: 
            case 4: 
            case 7: 
            case 8: {
                break;
            }
            case 5: {
                throw new RuntimeException("Not impl");
            }
            case 6: 
            case 10: 
            case 12: {
                throw new RuntimeException("Not impl");
            }
            case 1: 
            case 2: 
            case 9: 
            case 11: {
                Xobj node = (Xobj)((Object)n);
                node.ensureOccupancy();
                if (node.isFirstChildPtrDomUsable()) {
                    return (Xobj.NodeXobj)node._firstChild;
                }
                Xobj lastAttr = node.lastAttr();
                if (lastAttr != null) {
                    if (lastAttr.isNextSiblingPtrDomUsable()) {
                        return (Xobj.NodeXobj)lastAttr._nextSibling;
                    }
                    if (lastAttr.isCharNodesAfterUsable()) {
                        return lastAttr._charNodesAfter;
                    }
                }
                if (!node.isCharNodesValueUsable()) break;
                return node._charNodesValue;
            }
        }
        return fc;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static org.w3c.dom.Node _node_getLastChild(Dom n) {
        Dom lc;
        Locale l = n.locale();
        if (l.noSync()) {
            l.enter();
            try {
                lc = DomImpl.node_getLastChild(n);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                lc = DomImpl.node_getLastChild(n);
            } finally {
                l.exit();
            }
        }
        return (org.w3c.dom.Node)((Object)lc);
    }

    public static Dom node_getLastChild(Dom n) {
        CharNode nodes;
        switch (n.nodeType()) {
            case 3: 
            case 4: 
            case 7: 
            case 8: {
                return null;
            }
            case 5: {
                throw new RuntimeException("Not impl");
            }
            case 6: 
            case 10: 
            case 12: {
                throw new RuntimeException("Not impl");
            }
        }
        Dom lc = null;
        Cur c = n.tempCur();
        if (c.toLastChild()) {
            lc = c.getDom();
            c.skip();
            nodes = c.getCharNodes();
            if (nodes != null) {
                lc = null;
            }
        } else {
            c.next();
            nodes = c.getCharNodes();
        }
        if (lc == null && nodes != null) {
            while (nodes._next != null) {
                nodes = nodes._next;
            }
            lc = nodes;
        }
        c.release();
        return lc;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static org.w3c.dom.Node _node_getNextSibling(Dom n) {
        Dom ns;
        Locale l = n.locale();
        if (l.noSync()) {
            ns = DomImpl.node_getNextSibling(n);
        } else {
            Locale locale = l;
            synchronized (locale) {
                ns = DomImpl.node_getNextSibling(n);
            }
        }
        return (org.w3c.dom.Node)((Object)ns);
    }

    public static Dom node_getNextSibling(Dom n) {
        Dom ns = null;
        switch (n.nodeType()) {
            case 2: 
            case 9: 
            case 11: {
                break;
            }
            case 3: 
            case 4: {
                CharNode cn = (CharNode)n;
                if (!(cn._src instanceof Xobj)) {
                    return null;
                }
                Xobj src = (Xobj)cn._src;
                src._charNodesAfter = Cur.updateCharNodes(src._locale, src, src._charNodesAfter, src._cchAfter);
                src._charNodesValue = Cur.updateCharNodes(src._locale, src, src._charNodesValue, src._cchValue);
                if (cn._next != null) {
                    ns = cn._next;
                    break;
                }
                boolean isThisNodeAfterText = cn.isNodeAftertext();
                if (isThisNodeAfterText) {
                    ns = (Xobj.NodeXobj)src._nextSibling;
                    break;
                }
                ns = (Xobj.NodeXobj)src._firstChild;
                break;
            }
            case 1: 
            case 7: 
            case 8: {
                assert (n instanceof Xobj) : "PI, Comments and Elements always backed up by Xobj";
                Xobj node = (Xobj)((Object)n);
                node.ensureOccupancy();
                if (node.isNextSiblingPtrDomUsable()) {
                    return (Xobj.NodeXobj)node._nextSibling;
                }
                if (!node.isCharNodesAfterUsable()) break;
                return node._charNodesAfter;
            }
            case 5: 
            case 6: 
            case 10: 
            case 12: {
                throw new RuntimeException("Not implemented");
            }
        }
        return ns;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static org.w3c.dom.Node _node_getPreviousSibling(Dom n) {
        Dom ps;
        Locale l = n.locale();
        if (l.noSync()) {
            ps = DomImpl.node_getPreviousSibling(n);
        } else {
            Locale locale = l;
            synchronized (locale) {
                ps = DomImpl.node_getPreviousSibling(n);
            }
        }
        return (org.w3c.dom.Node)((Object)ps);
    }

    public static Dom node_getPreviousSibling(Dom n) {
        Dom prev = null;
        switch (n.nodeType()) {
            case 3: 
            case 4: {
                assert (n instanceof CharNode) : "Text/CData should be a CharNode";
                Object node = (CharNode)n;
                if (!(((CharNode)node)._src instanceof Xobj)) {
                    return null;
                }
                Xobj src = (Xobj)((CharNode)node)._src;
                src.ensureOccupancy();
                boolean isThisNodeAfterText = ((CharNode)node).isNodeAftertext();
                prev = ((CharNode)node)._prev;
                if (prev != null) break;
                prev = isThisNodeAfterText ? (Dom)((Object)src) : src._charNodesValue;
                break;
            }
            default: {
                assert (n instanceof Xobj);
                Object node = (Xobj)((Object)n);
                prev = (Dom)((Object)((Xobj)node)._prevSibling);
                if (prev != null || ((Xobj)node)._parent == null) break;
                prev = DomImpl.node_getFirstChild((Dom)((Object)((Xobj)node)._parent));
            }
        }
        Dom temp = prev;
        while (temp != null && (temp = DomImpl.node_getNextSibling(temp)) != n) {
            prev = temp;
        }
        return prev;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static boolean _node_hasAttributes(Dom n) {
        Locale l = n.locale();
        if (l.noSync()) {
            l.enter();
            try {
                boolean bl = DomImpl.node_hasAttributes(n);
                return bl;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            boolean bl;
            l.enter();
            try {
                bl = DomImpl.node_hasAttributes(n);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return bl;
        }
    }

    public static boolean node_hasAttributes(Dom n) {
        boolean hasAttrs = false;
        if (n.nodeType() == 1) {
            Cur c = n.tempCur();
            hasAttrs = c.hasAttrs();
            c.release();
        }
        return hasAttrs;
    }

    public static boolean _node_isSupported(Dom n, String feature, String version) {
        return DomImpl._domImplementation_hasFeature(n.locale(), feature, version);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void _node_normalize(Dom n) {
        Locale l = n.locale();
        if (l.noSync()) {
            l.enter();
            try {
                DomImpl.node_normalize(n);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                DomImpl.node_normalize(n);
            } finally {
                l.exit();
            }
        }
    }

    public static void node_normalize(Dom n) {
        switch (n.nodeType()) {
            case 3: 
            case 4: 
            case 7: 
            case 8: {
                return;
            }
            case 5: {
                throw new RuntimeException("Not impl");
            }
            case 6: 
            case 10: 
            case 12: {
                throw new RuntimeException("Not impl");
            }
        }
        Cur c = n.tempCur();
        c.push();
        do {
            c.nextWithAttrs();
            CharNode cn = c.getCharNodes();
            if (cn == null) continue;
            if (!c.isText()) {
                while (cn != null) {
                    cn.setChars(null, 0, 0);
                    cn = CharNode.remove(cn, cn);
                }
            } else if (cn._next != null) {
                while (cn._next != null) {
                    cn.setChars(null, 0, 0);
                    cn = CharNode.remove(cn, cn._next);
                }
                cn._cch = Integer.MAX_VALUE;
            }
            c.setCharNodes(cn);
        } while (!c.isAtEndOfLastPush());
        c.release();
        n.locale().invalidateDomCaches(n);
    }

    public static boolean _node_hasChildNodes(Dom n) {
        return DomImpl._node_getFirstChild(n) != null;
    }

    public static org.w3c.dom.Node _node_appendChild(Dom p, org.w3c.dom.Node newChild) {
        return DomImpl._node_insertBefore(p, newChild, null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static org.w3c.dom.Node _node_replaceChild(Dom p, org.w3c.dom.Node newChild, org.w3c.dom.Node oldChild) {
        Dom d;
        Dom nc;
        Locale l = p.locale();
        if (newChild == null) {
            throw new IllegalArgumentException("Child to add is null");
        }
        if (oldChild == null) {
            throw new NotFoundErr("Child to replace is null");
        }
        if (!(newChild instanceof Dom) || (nc = (Dom)((Object)newChild)).locale() != l) {
            throw new WrongDocumentErr("Child to add is from another document");
        }
        Dom oc = null;
        if (!(oldChild instanceof Dom) || (oc = (Dom)((Object)oldChild)).locale() != l) {
            throw new WrongDocumentErr("Child to replace is from another document");
        }
        if (l.noSync()) {
            l.enter();
            try {
                d = DomImpl.node_replaceChild(p, nc, oc);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                d = DomImpl.node_replaceChild(p, nc, oc);
            } finally {
                l.exit();
            }
        }
        return (org.w3c.dom.Node)((Object)d);
    }

    public static Dom node_replaceChild(Dom p, Dom newChild, Dom oldChild) {
        Dom nextNode = DomImpl.node_getNextSibling(oldChild);
        DomImpl.node_removeChild(p, oldChild);
        try {
            DomImpl.node_insertBefore(p, newChild, nextNode);
        } catch (DOMException e) {
            DomImpl.node_insertBefore(p, oldChild, nextNode);
            throw e;
        }
        return oldChild;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static org.w3c.dom.Node _node_insertBefore(Dom p, org.w3c.dom.Node newChild, org.w3c.dom.Node refChild) {
        Dom d;
        Dom nc;
        Locale l = p.locale();
        if (newChild == null) {
            throw new IllegalArgumentException("Child to add is null");
        }
        if (!(newChild instanceof Dom) || (nc = (Dom)((Object)newChild)).locale() != l) {
            throw new WrongDocumentErr("Child to add is from another document");
        }
        Dom rc = null;
        if (!(refChild == null || refChild instanceof Dom && (rc = (Dom)((Object)refChild)).locale() == l)) {
            throw new WrongDocumentErr("Reference child is from another document");
        }
        if (l.noSync()) {
            l.enter();
            try {
                d = DomImpl.node_insertBefore(p, nc, rc);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                d = DomImpl.node_insertBefore(p, nc, rc);
            } finally {
                l.exit();
            }
        }
        return (org.w3c.dom.Node)((Object)d);
    }

    public static Dom node_insertBefore(Dom p, Dom nc, Dom rc) {
        assert (nc != null);
        if (nc == rc) {
            return nc;
        }
        if (rc != null && DomImpl.parent(rc) != p) {
            throw new NotFoundErr("RefChild is not a child of this node");
        }
        int nck = nc.nodeType();
        if (nck == 11) {
            Dom c = DomImpl.firstChild(nc);
            while (c != null) {
                DomImpl.validateNewChild(p, c);
                c = DomImpl.nextSibling(c);
            }
            c = DomImpl.firstChild(nc);
            while (c != null) {
                Dom n = DomImpl.nextSibling(c);
                if (rc == null) {
                    DomImpl.append(c, p);
                } else {
                    DomImpl.insert(c, rc);
                }
                c = n;
            }
            return nc;
        }
        DomImpl.validateNewChild(p, nc);
        DomImpl.remove(nc);
        int pk = p.nodeType();
        assert (pk == 2 || pk == 11 || pk == 9 || pk == 1);
        switch (nck) {
            case 1: 
            case 7: 
            case 8: {
                if (rc == null) {
                    Cur cTo = p.tempCur();
                    cTo.toEnd();
                    Cur.moveNode((Xobj)((Object)nc), cTo);
                    cTo.release();
                    break;
                }
                int rck = rc.nodeType();
                if (rck == 3 || rck == 4) {
                    ArrayList<Dom> charNodes = new ArrayList<Dom>();
                    while (rc != null && (rc.nodeType() == 3 || rc.nodeType() == 4)) {
                        Dom next = DomImpl.nextSibling(rc);
                        charNodes.add(DomImpl.remove(rc));
                        rc = next;
                    }
                    if (rc == null) {
                        DomImpl.append(nc, p);
                    } else {
                        DomImpl.insert(nc, rc);
                    }
                    rc = DomImpl.nextSibling(nc);
                    for (int i = 0; i < charNodes.size(); ++i) {
                        Dom n = (Dom)charNodes.get(i);
                        if (rc == null) {
                            DomImpl.append(n, p);
                            continue;
                        }
                        DomImpl.insert(n, rc);
                    }
                    break;
                }
                if (rck == 5) {
                    throw new RuntimeException("Not implemented");
                }
                assert (rck == 1 || rck == 7 || rck == 8);
                Cur cTo = rc.tempCur();
                Cur.moveNode((Xobj)((Object)nc), cTo);
                cTo.release();
                break;
            }
            case 3: 
            case 4: {
                CharNode n = (CharNode)nc;
                assert (n._prev == null && n._next == null);
                CharNode refCharNode = null;
                Cur c = p.tempCur();
                if (rc == null) {
                    c.toEnd();
                } else {
                    int rck = rc.nodeType();
                    if (rck == 3 || rck == 4) {
                        refCharNode = (CharNode)rc;
                        c.moveToCharNode(refCharNode);
                    } else {
                        if (rck == 5) {
                            throw new RuntimeException("Not implemented");
                        }
                        c.moveToDom(rc);
                    }
                }
                CharNode nodes = c.getCharNodes();
                nodes = CharNode.insertNode(nodes, n, refCharNode);
                c.insertChars(n._src, n._off, n._cch);
                c.setCharNodes(nodes);
                c.release();
                break;
            }
            case 5: {
                throw new RuntimeException("Not implemented");
            }
            case 10: {
                throw new RuntimeException("Not implemented");
            }
            default: {
                throw new RuntimeException("Unexpected child node type");
            }
        }
        return nc;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static org.w3c.dom.Node _node_removeChild(Dom p, org.w3c.dom.Node child) {
        Dom d;
        Dom c;
        Locale l = p.locale();
        if (child == null) {
            throw new NotFoundErr("Child to remove is null");
        }
        if (!(child instanceof Dom) || (c = (Dom)((Object)child)).locale() != l) {
            throw new WrongDocumentErr("Child to remove is from another document");
        }
        if (l.noSync()) {
            l.enter();
            try {
                d = DomImpl.node_removeChild(p, c);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                d = DomImpl.node_removeChild(p, c);
            } finally {
                l.exit();
            }
        }
        return (org.w3c.dom.Node)((Object)d);
    }

    public static Dom node_removeChild(Dom parent, Dom child) {
        if (DomImpl.parent(child) != parent) {
            throw new NotFoundErr("Child to remove is not a child of given parent");
        }
        switch (child.nodeType()) {
            case 2: 
            case 9: 
            case 11: {
                throw new IllegalStateException();
            }
            case 1: 
            case 7: 
            case 8: {
                DomImpl.removeNode(child);
                break;
            }
            case 3: 
            case 4: {
                Cur c = child.tempCur();
                CharNode nodes = c.getCharNodes();
                CharNode cn = (CharNode)child;
                assert (cn._src instanceof Dom);
                cn.setChars(c.moveChars(null, cn._cch), c._offSrc, c._cchSrc);
                c.setCharNodes(CharNode.remove(nodes, cn));
                c.release();
                break;
            }
            case 5: {
                throw new RuntimeException("Not impl");
            }
            case 6: 
            case 10: 
            case 12: {
                throw new RuntimeException("Not impl");
            }
            default: {
                throw new RuntimeException("Unknown kind");
            }
        }
        return child;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static org.w3c.dom.Node _node_cloneNode(Dom n, boolean deep) {
        Dom c;
        Locale l = n.locale();
        if (l.noSync()) {
            l.enter();
            try {
                c = DomImpl.node_cloneNode(n, deep);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                c = DomImpl.node_cloneNode(n, deep);
            } finally {
                l.exit();
            }
        }
        return (org.w3c.dom.Node)((Object)c);
    }

    public static Dom node_cloneNode(Dom n, boolean deep) {
        Locale l = n.locale();
        Dom clone = null;
        if (!deep) {
            Cur shallow = null;
            switch (n.nodeType()) {
                case 9: {
                    shallow = l.tempCur();
                    shallow.createDomDocumentRoot();
                    break;
                }
                case 11: {
                    shallow = l.tempCur();
                    shallow.createDomDocFragRoot();
                    break;
                }
                case 1: {
                    shallow = l.tempCur();
                    shallow.createElement(n.getQName());
                    Element elem = (Element)((Object)shallow.getDom());
                    NamedNodeMap attrs = ((Element)((Object)n)).getAttributes();
                    for (int i = 0; i < attrs.getLength(); ++i) {
                        elem.setAttributeNodeNS((Attr)attrs.item(i).cloneNode(true));
                    }
                    break;
                }
                case 2: {
                    shallow = l.tempCur();
                    shallow.createAttr(n.getQName());
                    break;
                }
            }
            if (shallow != null) {
                clone = shallow.getDom();
                shallow.release();
            }
        }
        if (clone == null) {
            switch (n.nodeType()) {
                case 1: 
                case 2: 
                case 7: 
                case 8: 
                case 9: 
                case 11: {
                    Cur cClone = l.tempCur();
                    Cur cSrc = n.tempCur();
                    cSrc.copyNode(cClone);
                    clone = cClone.getDom();
                    cClone.release();
                    cSrc.release();
                    break;
                }
                case 3: 
                case 4: {
                    Cur c = n.tempCur();
                    TextNode cn = n.nodeType() == 3 ? l.createTextNode() : l.createCdataNode();
                    cn.setChars(c.getChars(((CharNode)n)._cch), c._offSrc, c._cchSrc);
                    clone = cn;
                    c.release();
                    break;
                }
                case 5: 
                case 6: 
                case 10: 
                case 12: {
                    throw new RuntimeException("Not impl");
                }
                default: {
                    throw new RuntimeException("Unknown kind");
                }
            }
        }
        return clone;
    }

    public static String _node_getLocalName(Dom n) {
        if (!n.nodeCanHavePrefixUri()) {
            return null;
        }
        QName name = n.getQName();
        return name == null ? "" : name.getLocalPart();
    }

    public static String _node_getNamespaceURI(Dom n) {
        if (!n.nodeCanHavePrefixUri()) {
            return null;
        }
        QName name = n.getQName();
        return name == null ? "" : name.getNamespaceURI();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void _node_setPrefix(Dom n, String prefix) {
        Locale l = n.locale();
        if (l.noSync()) {
            l.enter();
            try {
                DomImpl.node_setPrefix(n, prefix);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                DomImpl.node_setPrefix(n, prefix);
            } finally {
                l.exit();
            }
        }
    }

    public static void node_setPrefix(Dom n, String prefix) {
        if (n.nodeType() == 1 || n.nodeType() == 2) {
            Cur c = n.tempCur();
            QName name = c.getName();
            String uri = name.getNamespaceURI();
            String local = name.getLocalPart();
            prefix = DomImpl.validatePrefix(prefix, uri, local, n.nodeType() == 2);
            c.setName(n.locale().makeQName(uri, local, prefix));
            c.release();
        } else {
            DomImpl.validatePrefix(prefix, "", "", false);
        }
    }

    public static String _node_getPrefix(Dom n) {
        if (!n.nodeCanHavePrefixUri()) {
            return null;
        }
        QName name = n.getQName();
        return name == null ? "" : name.getPrefix();
    }

    public static String _node_getNodeName(Dom n) {
        switch (n.nodeType()) {
            case 4: {
                return "#cdata-section";
            }
            case 8: {
                return "#comment";
            }
            case 11: {
                return "#document-fragment";
            }
            case 9: {
                return "#document";
            }
            case 7: {
                return n.getQName().getLocalPart();
            }
            case 3: {
                return "#text";
            }
            case 1: 
            case 2: {
                QName name = n.getQName();
                String prefix = name.getPrefix();
                return prefix.length() == 0 ? name.getLocalPart() : prefix + ":" + name.getLocalPart();
            }
            case 5: 
            case 6: 
            case 10: 
            case 12: {
                throw new RuntimeException("Not impl");
            }
        }
        throw new RuntimeException("Unknown node type");
    }

    public static short _node_getNodeType(Dom n) {
        return (short)n.nodeType();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void _node_setNodeValue(Dom n, String nodeValue) {
        Locale l = n.locale();
        if (l.noSync()) {
            l.enter();
            try {
                DomImpl.node_setNodeValue(n, nodeValue);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                DomImpl.node_setNodeValue(n, nodeValue);
            } finally {
                l.exit();
            }
        }
    }

    public static void node_setNodeValue(Dom n, String nodeValue) {
        if (nodeValue == null) {
            nodeValue = "";
        }
        switch (n.nodeType()) {
            case 3: 
            case 4: {
                CharNode cn = (CharNode)n;
                Cur c = cn.tempCur();
                if (c != null) {
                    c.moveChars(null, cn._cch);
                    cn._cch = nodeValue.length();
                    c.insertString(nodeValue);
                    c.release();
                    break;
                }
                cn.setChars(nodeValue, 0, nodeValue.length());
                break;
            }
            case 2: {
                NodeList children = ((org.w3c.dom.Node)((Object)n)).getChildNodes();
                while (children.getLength() > 1) {
                    DomImpl.node_removeChild(n, (Dom)((Object)children.item(1)));
                }
                if (children.getLength() == 0) {
                    TextNode tn = n.locale().createTextNode();
                    tn.setChars(nodeValue, 0, nodeValue.length());
                    DomImpl.node_insertBefore(n, tn, null);
                } else {
                    assert (children.getLength() == 1);
                    children.item(0).setNodeValue(nodeValue);
                }
                if (!((Xobj.AttrXobj)n).isId()) break;
                Dom d = DomImpl.node_getOwnerDocument(n);
                String val = DomImpl.node_getNodeValue(n);
                if (!(d instanceof Xobj.DocumentXobj)) break;
                ((Xobj.DocumentXobj)d).removeIdElement(val);
                ((Xobj.DocumentXobj)d).addIdElement(nodeValue, DomImpl.attr_getOwnerElement(n));
                break;
            }
            case 7: 
            case 8: {
                Cur c = n.tempCur();
                c.next();
                c.getChars(-1);
                c.moveChars(null, c._cchSrc);
                c.insertString(nodeValue);
                c.release();
                break;
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static String _node_getNodeValue(Dom n) {
        Locale l = n.locale();
        if (l.noSync()) {
            return DomImpl.node_getNodeValue(n);
        }
        Locale locale = l;
        synchronized (locale) {
            return DomImpl.node_getNodeValue(n);
        }
    }

    public static String node_getNodeValue(Dom n) {
        String s = null;
        switch (n.nodeType()) {
            case 2: 
            case 7: 
            case 8: {
                s = ((Xobj)((Object)n)).getValueAsString();
                break;
            }
            case 3: 
            case 4: {
                assert (n instanceof CharNode) : "Text/CData should be a CharNode";
                CharNode node = (CharNode)n;
                if (!(node._src instanceof Xobj)) {
                    s = CharUtil.getString(node._src, node._off, node._cch);
                    break;
                }
                Xobj src = (Xobj)node._src;
                src.ensureOccupancy();
                boolean isThisNodeAfterText = node.isNodeAftertext();
                if (isThisNodeAfterText) {
                    src._charNodesAfter = Cur.updateCharNodes(src._locale, src, src._charNodesAfter, src._cchAfter);
                    s = src.getCharsAfterAsString(node._off, node._cch);
                    break;
                }
                src._charNodesValue = Cur.updateCharNodes(src._locale, src, src._charNodesValue, src._cchValue);
                s = src.getCharsValueAsString(node._off, node._cch);
                break;
            }
        }
        return s;
    }

    public static Object _node_getUserData(Dom n, String key) {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    public static Object _node_setUserData(Dom n, String key, Object data, UserDataHandler handler) {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    public static Object _node_getFeature(Dom n, String feature, String version) {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    public static boolean _node_isEqualNode(Dom n, org.w3c.dom.Node arg) {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    public static boolean _node_isSameNode(Dom n, org.w3c.dom.Node arg) {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    public static String _node_lookupNamespaceURI(Dom n, String prefix) {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    public static boolean _node_isDefaultNamespace(Dom n, String namespaceURI) {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    public static String _node_lookupPrefix(Dom n, String namespaceURI) {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    public static void _node_setTextContent(Dom n, String textContent) {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    public static String _node_getTextContent(Dom n) {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    public static short _node_compareDocumentPosition(Dom n, org.w3c.dom.Node other) {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    public static String _node_getBaseURI(Dom n) {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static org.w3c.dom.Node _childNodes_item(Dom n, int i) {
        Dom d;
        Locale l = n.locale();
        if (i == 0) {
            return DomImpl._node_getFirstChild(n);
        }
        if (l.noSync()) {
            d = DomImpl.childNodes_item(n, i);
        } else {
            Locale locale = l;
            synchronized (locale) {
                d = DomImpl.childNodes_item(n, i);
            }
        }
        return (org.w3c.dom.Node)((Object)d);
    }

    public static Dom childNodes_item(Dom n, int i) {
        if (i < 0) {
            return null;
        }
        switch (n.nodeType()) {
            case 3: 
            case 4: 
            case 7: 
            case 8: {
                return null;
            }
            case 5: {
                throw new RuntimeException("Not impl");
            }
            case 6: 
            case 10: 
            case 12: {
                throw new RuntimeException("Not impl");
            }
        }
        if (i == 0) {
            return DomImpl.node_getFirstChild(n);
        }
        return n.locale().findDomNthChild(n, i);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static int _childNodes_getLength(Dom n) {
        int count;
        Locale l = n.locale();
        assert (n instanceof Xobj);
        Xobj node = (Xobj)((Object)n);
        if (!node.isVacant() && (count = node.getDomZeroOneChildren()) < 2) {
            return count;
        }
        if (l.noSync()) {
            return DomImpl.childNodes_getLength(n);
        }
        Locale locale = l;
        synchronized (locale) {
            return DomImpl.childNodes_getLength(n);
        }
    }

    public static int childNodes_getLength(Dom n) {
        switch (n.nodeType()) {
            case 3: 
            case 4: 
            case 7: 
            case 8: {
                return 0;
            }
            case 5: {
                throw new RuntimeException("Not impl");
            }
            case 6: 
            case 10: 
            case 12: {
                throw new RuntimeException("Not impl");
            }
        }
        assert (n instanceof Xobj);
        Xobj node = (Xobj)((Object)n);
        node.ensureOccupancy();
        int count = node.getDomZeroOneChildren();
        if (count < 2) {
            return count;
        }
        return n.locale().domLength(n);
    }

    public static String _element_getTagName(Dom e) {
        return DomImpl._node_getNodeName(e);
    }

    public static Attr _element_getAttributeNode(Dom e, String name) {
        return (Attr)DomImpl._attributes_getNamedItem(e, name);
    }

    public static Attr _element_getAttributeNodeNS(Dom e, String uri, String local) {
        return (Attr)DomImpl._attributes_getNamedItemNS(e, uri, local);
    }

    public static Attr _element_setAttributeNode(Dom e, Attr newAttr) {
        return (Attr)DomImpl._attributes_setNamedItem(e, newAttr);
    }

    public static Attr _element_setAttributeNodeNS(Dom e, Attr newAttr) {
        return (Attr)DomImpl._attributes_setNamedItemNS(e, newAttr);
    }

    public static String _element_getAttribute(Dom e, String name) {
        org.w3c.dom.Node a = DomImpl._attributes_getNamedItem(e, name);
        return a == null ? "" : a.getNodeValue();
    }

    public static String _element_getAttributeNS(Dom e, String uri, String local) {
        org.w3c.dom.Node a = DomImpl._attributes_getNamedItemNS(e, uri, local);
        return a == null ? "" : a.getNodeValue();
    }

    public static boolean _element_hasAttribute(Dom e, String name) {
        return DomImpl._attributes_getNamedItem(e, name) != null;
    }

    public static boolean _element_hasAttributeNS(Dom e, String uri, String local) {
        return DomImpl._attributes_getNamedItemNS(e, uri, local) != null;
    }

    public static void _element_removeAttribute(Dom e, String name) {
        try {
            DomImpl._attributes_removeNamedItem(e, name);
        } catch (NotFoundErr notFoundErr) {
            // empty catch block
        }
    }

    public static void _element_removeAttributeNS(Dom e, String uri, String local) {
        try {
            DomImpl._attributes_removeNamedItemNS(e, uri, local);
        } catch (NotFoundErr notFoundErr) {
            // empty catch block
        }
    }

    public static Attr _element_removeAttributeNode(Dom e, Attr oldAttr) {
        if (oldAttr == null) {
            throw new NotFoundErr("Attribute to remove is null");
        }
        if (oldAttr.getOwnerElement() != e) {
            throw new NotFoundErr("Attribute to remove does not belong to this element");
        }
        return (Attr)DomImpl._attributes_removeNamedItem(e, oldAttr.getNodeName());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void _element_setAttribute(Dom e, String name, String value) {
        Locale l = e.locale();
        if (l.noSync()) {
            l.enter();
            try {
                DomImpl.element_setAttribute(e, name, value);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                DomImpl.element_setAttribute(e, name, value);
            } finally {
                l.exit();
            }
        }
    }

    public static void element_setAttribute(Dom e, String name, String value) {
        Dom a = DomImpl.attributes_getNamedItem(e, name);
        if (a == null) {
            a = DomImpl.document_createAttribute(DomImpl.node_getOwnerDocument(e), name);
            DomImpl.attributes_setNamedItem(e, a);
        }
        DomImpl.node_setNodeValue(a, value);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void _element_setAttributeNS(Dom e, String uri, String qname, String value) {
        Locale l = e.locale();
        if (l.noSync()) {
            l.enter();
            try {
                DomImpl.element_setAttributeNS(e, uri, qname, value);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                DomImpl.element_setAttributeNS(e, uri, qname, value);
            } finally {
                l.exit();
            }
        }
    }

    public static void element_setAttributeNS(Dom e, String uri, String qname, String value) {
        DomImpl.validateQualifiedName(qname, uri, true);
        QName name = e.locale().makeQualifiedQName(uri, qname);
        String local = name.getLocalPart();
        String prefix = DomImpl.validatePrefix(name.getPrefix(), uri, local, true);
        Dom a = DomImpl.attributes_getNamedItemNS(e, uri, local);
        if (a == null) {
            a = DomImpl.document_createAttributeNS(DomImpl.node_getOwnerDocument(e), uri, local);
            DomImpl.attributes_setNamedItemNS(e, a);
        }
        DomImpl.node_setPrefix(a, prefix);
        DomImpl.node_setNodeValue(a, value);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static NodeList _element_getElementsByTagName(Dom e, String name) {
        Locale l = e.locale();
        if (l.noSync()) {
            l.enter();
            try {
                NodeList nodeList = DomImpl.element_getElementsByTagName(e, name);
                return nodeList;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            NodeList nodeList;
            l.enter();
            try {
                nodeList = DomImpl.element_getElementsByTagName(e, name);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return nodeList;
        }
    }

    public static NodeList element_getElementsByTagName(Dom e, String name) {
        return new ElementsByTagNameNodeList(e, name);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static NodeList _element_getElementsByTagNameNS(Dom e, String uri, String local) {
        Locale l = e.locale();
        if (l.noSync()) {
            l.enter();
            try {
                NodeList nodeList = DomImpl.element_getElementsByTagNameNS(e, uri, local);
                return nodeList;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            NodeList nodeList;
            l.enter();
            try {
                nodeList = DomImpl.element_getElementsByTagNameNS(e, uri, local);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return nodeList;
        }
    }

    public static NodeList element_getElementsByTagNameNS(Dom e, String uri, String local) {
        return new ElementsByTagNameNSNodeList(e, uri, local);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static int _attributes_getLength(Dom e) {
        Locale l = e.locale();
        if (l.noSync()) {
            l.enter();
            try {
                int n = DomImpl.attributes_getLength(e);
                return n;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            int n;
            l.enter();
            try {
                n = DomImpl.attributes_getLength(e);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return n;
        }
    }

    public static int attributes_getLength(Dom e) {
        int n = 0;
        Cur c = e.tempCur();
        while (c.toNextAttr()) {
            ++n;
        }
        c.release();
        return n;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static org.w3c.dom.Node _attributes_setNamedItem(Dom e, org.w3c.dom.Node attr) {
        Dom oldA;
        Dom a;
        Locale l = e.locale();
        if (attr == null) {
            throw new IllegalArgumentException("Attr to set is null");
        }
        if (!(attr instanceof Dom) || (a = (Dom)((Object)attr)).locale() != l) {
            throw new WrongDocumentErr("Attr to set is from another document");
        }
        if (l.noSync()) {
            l.enter();
            try {
                oldA = DomImpl.attributes_setNamedItem(e, a);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                oldA = DomImpl.attributes_setNamedItem(e, a);
            } finally {
                l.exit();
            }
        }
        return (org.w3c.dom.Node)((Object)oldA);
    }

    public static Dom attributes_setNamedItem(Dom e, Dom a) {
        if (DomImpl.attr_getOwnerElement(a) != null) {
            throw new InuseAttributeError();
        }
        if (a.nodeType() != 2) {
            throw new HierarchyRequestErr("Node is not an attribute");
        }
        String name = DomImpl._node_getNodeName(a);
        Dom oldAttr = null;
        Cur c = e.tempCur();
        while (c.toNextAttr()) {
            Dom aa = c.getDom();
            if (!DomImpl._node_getNodeName(aa).equals(name)) continue;
            if (oldAttr == null) {
                oldAttr = aa;
                continue;
            }
            DomImpl.removeNode(aa);
            c.toPrevAttr();
        }
        if (oldAttr == null) {
            c.moveToDom(e);
            c.next();
            Cur.moveNode((Xobj)((Object)a), c);
        } else {
            c.moveToDom(oldAttr);
            Cur.moveNode((Xobj)((Object)a), c);
            DomImpl.removeNode(oldAttr);
        }
        c.release();
        return oldAttr;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static org.w3c.dom.Node _attributes_getNamedItem(Dom e, String name) {
        Dom n;
        Locale l = e.locale();
        if (l.noSync()) {
            l.enter();
            try {
                n = DomImpl.attributes_getNamedItem(e, name);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                n = DomImpl.attributes_getNamedItem(e, name);
            } finally {
                l.exit();
            }
        }
        return (org.w3c.dom.Node)((Object)n);
    }

    public static Dom attributes_getNamedItem(Dom e, String name) {
        Dom a = null;
        Cur c = e.tempCur();
        while (c.toNextAttr()) {
            Dom d = c.getDom();
            if (!DomImpl._node_getNodeName(d).equals(name)) continue;
            a = d;
            break;
        }
        c.release();
        return a;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static org.w3c.dom.Node _attributes_getNamedItemNS(Dom e, String uri, String local) {
        Dom n;
        Locale l = e.locale();
        if (l.noSync()) {
            l.enter();
            try {
                n = DomImpl.attributes_getNamedItemNS(e, uri, local);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                n = DomImpl.attributes_getNamedItemNS(e, uri, local);
            } finally {
                l.exit();
            }
        }
        return (org.w3c.dom.Node)((Object)n);
    }

    public static Dom attributes_getNamedItemNS(Dom e, String uri, String local) {
        if (uri == null) {
            uri = "";
        }
        Dom a = null;
        Cur c = e.tempCur();
        while (c.toNextAttr()) {
            Dom d = c.getDom();
            QName n = d.getQName();
            if (!n.getNamespaceURI().equals(uri) || !n.getLocalPart().equals(local)) continue;
            a = d;
            break;
        }
        c.release();
        return a;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static org.w3c.dom.Node _attributes_removeNamedItem(Dom e, String name) {
        Dom n;
        Locale l = e.locale();
        if (l.noSync()) {
            l.enter();
            try {
                n = DomImpl.attributes_removeNamedItem(e, name);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                n = DomImpl.attributes_removeNamedItem(e, name);
            } finally {
                l.exit();
            }
        }
        return (org.w3c.dom.Node)((Object)n);
    }

    public static Dom attributes_removeNamedItem(Dom e, String name) {
        Dom oldAttr = null;
        Cur c = e.tempCur();
        while (c.toNextAttr()) {
            Dom aa = c.getDom();
            if (!DomImpl._node_getNodeName(aa).equals(name)) continue;
            if (oldAttr == null) {
                oldAttr = aa;
            }
            if (((Xobj.AttrXobj)aa).isId()) {
                Dom d = DomImpl.node_getOwnerDocument(aa);
                String val = DomImpl.node_getNodeValue(aa);
                if (d instanceof Xobj.DocumentXobj) {
                    ((Xobj.DocumentXobj)d).removeIdElement(val);
                }
            }
            DomImpl.removeNode(aa);
            c.toPrevAttr();
        }
        c.release();
        if (oldAttr == null) {
            throw new NotFoundErr("Named item not found: " + name);
        }
        return oldAttr;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static org.w3c.dom.Node _attributes_removeNamedItemNS(Dom e, String uri, String local) {
        Dom n;
        Locale l = e.locale();
        if (l.noSync()) {
            l.enter();
            try {
                n = DomImpl.attributes_removeNamedItemNS(e, uri, local);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                n = DomImpl.attributes_removeNamedItemNS(e, uri, local);
            } finally {
                l.exit();
            }
        }
        return (org.w3c.dom.Node)((Object)n);
    }

    public static Dom attributes_removeNamedItemNS(Dom e, String uri, String local) {
        if (uri == null) {
            uri = "";
        }
        Dom oldAttr = null;
        Cur c = e.tempCur();
        while (c.toNextAttr()) {
            Dom aa = c.getDom();
            QName qn = aa.getQName();
            if (!qn.getNamespaceURI().equals(uri) || !qn.getLocalPart().equals(local)) continue;
            if (oldAttr == null) {
                oldAttr = aa;
            }
            if (((Xobj.AttrXobj)aa).isId()) {
                Dom d = DomImpl.node_getOwnerDocument(aa);
                String val = DomImpl.node_getNodeValue(aa);
                if (d instanceof Xobj.DocumentXobj) {
                    ((Xobj.DocumentXobj)d).removeIdElement(val);
                }
            }
            DomImpl.removeNode(aa);
            c.toPrevAttr();
        }
        c.release();
        if (oldAttr == null) {
            throw new NotFoundErr("Named item not found: uri=" + uri + ", local=" + local);
        }
        return oldAttr;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static org.w3c.dom.Node _attributes_setNamedItemNS(Dom e, org.w3c.dom.Node attr) {
        Dom oldA;
        Dom a;
        Locale l = e.locale();
        if (attr == null) {
            throw new IllegalArgumentException("Attr to set is null");
        }
        if (!(attr instanceof Dom) || (a = (Dom)((Object)attr)).locale() != l) {
            throw new WrongDocumentErr("Attr to set is from another document");
        }
        if (l.noSync()) {
            l.enter();
            try {
                oldA = DomImpl.attributes_setNamedItemNS(e, a);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                oldA = DomImpl.attributes_setNamedItemNS(e, a);
            } finally {
                l.exit();
            }
        }
        return (org.w3c.dom.Node)((Object)oldA);
    }

    public static Dom attributes_setNamedItemNS(Dom e, Dom a) {
        Dom owner = DomImpl.attr_getOwnerElement(a);
        if (owner == e) {
            return a;
        }
        if (owner != null) {
            throw new InuseAttributeError();
        }
        if (a.nodeType() != 2) {
            throw new HierarchyRequestErr("Node is not an attribute");
        }
        QName name = a.getQName();
        Dom oldAttr = null;
        Cur c = e.tempCur();
        while (c.toNextAttr()) {
            Dom aa = c.getDom();
            if (!aa.getQName().equals(name)) continue;
            if (oldAttr == null) {
                oldAttr = aa;
                continue;
            }
            DomImpl.removeNode(aa);
            c.toPrevAttr();
        }
        if (oldAttr == null) {
            c.moveToDom(e);
            c.next();
            Cur.moveNode((Xobj)((Object)a), c);
        } else {
            c.moveToDom(oldAttr);
            Cur.moveNode((Xobj)((Object)a), c);
            DomImpl.removeNode(oldAttr);
        }
        c.release();
        return oldAttr;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static org.w3c.dom.Node _attributes_item(Dom e, int index) {
        Dom a;
        Locale l = e.locale();
        if (l.noSync()) {
            l.enter();
            try {
                a = DomImpl.attributes_item(e, index);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                a = DomImpl.attributes_item(e, index);
            } finally {
                l.exit();
            }
        }
        return (org.w3c.dom.Node)((Object)a);
    }

    public static Dom attributes_item(Dom e, int index) {
        if (index < 0) {
            return null;
        }
        Cur c = e.tempCur();
        Dom a = null;
        while (c.toNextAttr()) {
            if (index-- != 0) continue;
            a = c.getDom();
            break;
        }
        c.release();
        return a;
    }

    public static String _processingInstruction_getData(Dom p) {
        return DomImpl._node_getNodeValue(p);
    }

    public static String _processingInstruction_getTarget(Dom p) {
        return DomImpl._node_getNodeName(p);
    }

    public static void _processingInstruction_setData(Dom p, String data) {
        DomImpl._node_setNodeValue(p, data);
    }

    public static boolean _attr_getSpecified(Dom a) {
        return true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Element _attr_getOwnerElement(Dom a) {
        Dom e;
        Locale l = a.locale();
        if (l.noSync()) {
            l.enter();
            try {
                e = DomImpl.attr_getOwnerElement(a);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                e = DomImpl.attr_getOwnerElement(a);
            } finally {
                l.exit();
            }
        }
        return (Element)((Object)e);
    }

    public static Dom attr_getOwnerElement(Dom n) {
        Cur c = n.tempCur();
        if (!c.toParentRaw()) {
            c.release();
            return null;
        }
        Dom p = c.getDom();
        c.release();
        return p;
    }

    public static void _characterData_appendData(Dom cd, String arg) {
        if (arg != null && arg.length() != 0) {
            DomImpl._node_setNodeValue(cd, DomImpl._node_getNodeValue(cd) + arg);
        }
    }

    public static void _characterData_deleteData(Dom c, int offset, int count) {
        String s = DomImpl._characterData_getData(c);
        if (offset < 0 || offset > s.length() || count < 0) {
            throw new IndexSizeError();
        }
        if (offset + count > s.length()) {
            count = s.length() - offset;
        }
        if (count > 0) {
            DomImpl._characterData_setData(c, s.substring(0, offset) + s.substring(offset + count));
        }
    }

    public static String _characterData_getData(Dom c) {
        return DomImpl._node_getNodeValue(c);
    }

    public static int _characterData_getLength(Dom c) {
        return DomImpl._characterData_getData(c).length();
    }

    public static void _characterData_insertData(Dom c, int offset, String arg) {
        String s = DomImpl._characterData_getData(c);
        if (offset < 0 || offset > s.length()) {
            throw new IndexSizeError();
        }
        if (arg != null && arg.length() > 0) {
            DomImpl._characterData_setData(c, s.substring(0, offset) + arg + s.substring(offset));
        }
    }

    public static void _characterData_replaceData(Dom c, int offset, int count, String arg) {
        String s = DomImpl._characterData_getData(c);
        if (offset < 0 || offset > s.length() || count < 0) {
            throw new IndexSizeError();
        }
        if (offset + count > s.length()) {
            count = s.length() - offset;
        }
        if (count > 0) {
            DomImpl._characterData_setData(c, s.substring(0, offset) + (arg == null ? "" : arg) + s.substring(offset + count));
        }
    }

    public static void _characterData_setData(Dom c, String data) {
        DomImpl._node_setNodeValue(c, data);
    }

    public static String _characterData_substringData(Dom c, int offset, int count) {
        String s = DomImpl._characterData_getData(c);
        if (offset < 0 || offset > s.length() || count < 0) {
            throw new IndexSizeError();
        }
        if (offset + count > s.length()) {
            count = s.length() - offset;
        }
        return s.substring(offset, offset + count);
    }

    public static Text _text_splitText(Dom t, int offset) {
        assert (t.nodeType() == 3);
        String s = DomImpl._characterData_getData(t);
        if (offset < 0 || offset > s.length()) {
            throw new IndexSizeError();
        }
        DomImpl._characterData_deleteData(t, offset, s.length() - offset);
        Dom t2 = (Dom)((Object)DomImpl._document_createTextNode(t, s.substring(offset)));
        Dom p = (Dom)((Object)DomImpl._node_getParentNode(t));
        if (p != null) {
            DomImpl._node_insertBefore(p, (Text)((Object)t2), DomImpl._node_getNextSibling(t));
            t.locale().invalidateDomCaches(p);
        }
        return (Text)((Object)t2);
    }

    public static String _text_getWholeText(Dom t) {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    public static boolean _text_isElementContentWhitespace(Dom t) {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    public static Text _text_replaceWholeText(Dom t, String content) {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static XMLStreamReader _getXmlStreamReader(Dom n) {
        Locale l = n.locale();
        if (l.noSync()) {
            l.enter();
            try {
                XMLStreamReader xMLStreamReader = DomImpl.getXmlStreamReader(n);
                return xMLStreamReader;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            XMLStreamReader xMLStreamReader;
            l.enter();
            try {
                xMLStreamReader = DomImpl.getXmlStreamReader(n);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return xMLStreamReader;
        }
    }

    public static XMLStreamReader getXmlStreamReader(Dom n) {
        XMLStreamReader xs;
        switch (n.nodeType()) {
            case 1: 
            case 2: 
            case 7: 
            case 8: 
            case 9: 
            case 11: {
                Cur c = n.tempCur();
                xs = Jsr173.newXmlStreamReader(c, null);
                c.release();
                break;
            }
            case 3: 
            case 4: {
                CharNode cn = (CharNode)n;
                Cur c = cn.tempCur();
                if (c == null) {
                    c = n.locale().tempCur();
                    xs = Jsr173.newXmlStreamReader(c, cn._src, cn._off, cn._cch);
                } else {
                    xs = Jsr173.newXmlStreamReader(c, c.getChars(cn._cch), c._offSrc, c._cchSrc);
                }
                c.release();
                break;
            }
            case 5: 
            case 6: 
            case 10: 
            case 12: {
                throw new RuntimeException("Not impl");
            }
            default: {
                throw new RuntimeException("Unknown kind");
            }
        }
        return xs;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static XmlCursor _getXmlCursor(Dom n) {
        Locale l = n.locale();
        if (l.noSync()) {
            l.enter();
            try {
                XmlCursor xmlCursor = DomImpl.getXmlCursor(n);
                return xmlCursor;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            XmlCursor xmlCursor;
            l.enter();
            try {
                xmlCursor = DomImpl.getXmlCursor(n);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return xmlCursor;
        }
    }

    public static XmlCursor getXmlCursor(Dom n) {
        Cur c = n.tempCur();
        Cursor xc = new Cursor(c);
        c.release();
        return xc;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static XmlObject _getXmlObject(Dom n) {
        Locale l = n.locale();
        if (l.noSync()) {
            l.enter();
            try {
                XmlObject xmlObject = DomImpl.getXmlObject(n);
                return xmlObject;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            XmlObject xmlObject;
            l.enter();
            try {
                xmlObject = DomImpl.getXmlObject(n);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return xmlObject;
        }
    }

    public static XmlObject getXmlObject(Dom n) {
        Cur c = n.tempCur();
        XmlObject x = c.getObject();
        c.release();
        return x;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static boolean _soapText_isComment(Dom n) {
        Locale l = n.locale();
        org.apache.xmlbeans.impl.soap.Text text = (org.apache.xmlbeans.impl.soap.Text)((Object)n);
        if (l.noSync()) {
            l.enter();
            try {
                boolean bl = l._saaj.soapText_isComment(text);
                return bl;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            boolean bl;
            l.enter();
            try {
                bl = l._saaj.soapText_isComment(text);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return bl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void _soapNode_detachNode(Dom n) {
        Locale l = n.locale();
        Node node = (Node)((Object)n);
        if (l.noSync()) {
            l.enter();
            try {
                l._saaj.soapNode_detachNode(node);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                l._saaj.soapNode_detachNode(node);
            } finally {
                l.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void _soapNode_recycleNode(Dom n) {
        Locale l = n.locale();
        Node node = (Node)((Object)n);
        if (l.noSync()) {
            l.enter();
            try {
                l._saaj.soapNode_recycleNode(node);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                l._saaj.soapNode_recycleNode(node);
            } finally {
                l.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static String _soapNode_getValue(Dom n) {
        Locale l = n.locale();
        Node node = (Node)((Object)n);
        if (l.noSync()) {
            l.enter();
            try {
                String string = l._saaj.soapNode_getValue(node);
                return string;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            String string;
            l.enter();
            try {
                string = l._saaj.soapNode_getValue(node);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return string;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void _soapNode_setValue(Dom n, String value) {
        Locale l = n.locale();
        Node node = (Node)((Object)n);
        if (l.noSync()) {
            l.enter();
            try {
                l._saaj.soapNode_setValue(node, value);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                l._saaj.soapNode_setValue(node, value);
            } finally {
                l.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static SOAPElement _soapNode_getParentElement(Dom n) {
        Locale l = n.locale();
        Node node = (Node)((Object)n);
        if (l.noSync()) {
            l.enter();
            try {
                SOAPElement sOAPElement = l._saaj.soapNode_getParentElement(node);
                return sOAPElement;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            SOAPElement sOAPElement;
            l.enter();
            try {
                sOAPElement = l._saaj.soapNode_getParentElement(node);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return sOAPElement;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void _soapNode_setParentElement(Dom n, SOAPElement p) {
        Locale l = n.locale();
        Node node = (Node)((Object)n);
        if (l.noSync()) {
            l.enter();
            try {
                l._saaj.soapNode_setParentElement(node, p);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                l._saaj.soapNode_setParentElement(node, p);
            } finally {
                l.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void _soapElement_removeContents(Dom d) {
        Locale l = d.locale();
        SOAPElement se = (SOAPElement)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                l._saaj.soapElement_removeContents(se);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                l._saaj.soapElement_removeContents(se);
            } finally {
                l.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static String _soapElement_getEncodingStyle(Dom d) {
        Locale l = d.locale();
        SOAPElement se = (SOAPElement)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                String string = l._saaj.soapElement_getEncodingStyle(se);
                return string;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            String string;
            l.enter();
            try {
                string = l._saaj.soapElement_getEncodingStyle(se);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return string;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void _soapElement_setEncodingStyle(Dom d, String encodingStyle) {
        Locale l = d.locale();
        SOAPElement se = (SOAPElement)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                l._saaj.soapElement_setEncodingStyle(se, encodingStyle);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                l._saaj.soapElement_setEncodingStyle(se, encodingStyle);
            } finally {
                l.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static boolean _soapElement_removeNamespaceDeclaration(Dom d, String prefix) {
        Locale l = d.locale();
        SOAPElement se = (SOAPElement)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                boolean bl = l._saaj.soapElement_removeNamespaceDeclaration(se, prefix);
                return bl;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            boolean bl;
            l.enter();
            try {
                bl = l._saaj.soapElement_removeNamespaceDeclaration(se, prefix);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return bl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Iterator _soapElement_getAllAttributes(Dom d) {
        Locale l = d.locale();
        SOAPElement se = (SOAPElement)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                Iterator iterator = l._saaj.soapElement_getAllAttributes(se);
                return iterator;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            Iterator iterator;
            l.enter();
            try {
                iterator = l._saaj.soapElement_getAllAttributes(se);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return iterator;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Iterator _soapElement_getChildElements(Dom d) {
        Locale l = d.locale();
        SOAPElement se = (SOAPElement)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                Iterator iterator = l._saaj.soapElement_getChildElements(se);
                return iterator;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            Iterator iterator;
            l.enter();
            try {
                iterator = l._saaj.soapElement_getChildElements(se);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return iterator;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Iterator _soapElement_getNamespacePrefixes(Dom d) {
        Locale l = d.locale();
        SOAPElement se = (SOAPElement)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                Iterator iterator = l._saaj.soapElement_getNamespacePrefixes(se);
                return iterator;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            Iterator iterator;
            l.enter();
            try {
                iterator = l._saaj.soapElement_getNamespacePrefixes(se);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return iterator;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static SOAPElement _soapElement_addAttribute(Dom d, Name name, String value) throws SOAPException {
        Locale l = d.locale();
        SOAPElement se = (SOAPElement)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                SOAPElement sOAPElement = l._saaj.soapElement_addAttribute(se, name, value);
                return sOAPElement;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            SOAPElement sOAPElement;
            l.enter();
            try {
                sOAPElement = l._saaj.soapElement_addAttribute(se, name, value);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return sOAPElement;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static SOAPElement _soapElement_addChildElement(Dom d, SOAPElement oldChild) throws SOAPException {
        Locale l = d.locale();
        SOAPElement se = (SOAPElement)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                SOAPElement sOAPElement = l._saaj.soapElement_addChildElement(se, oldChild);
                return sOAPElement;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            SOAPElement sOAPElement;
            l.enter();
            try {
                sOAPElement = l._saaj.soapElement_addChildElement(se, oldChild);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return sOAPElement;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static SOAPElement _soapElement_addChildElement(Dom d, Name name) throws SOAPException {
        Locale l = d.locale();
        SOAPElement se = (SOAPElement)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                SOAPElement sOAPElement = l._saaj.soapElement_addChildElement(se, name);
                return sOAPElement;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            SOAPElement sOAPElement;
            l.enter();
            try {
                sOAPElement = l._saaj.soapElement_addChildElement(se, name);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return sOAPElement;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static SOAPElement _soapElement_addChildElement(Dom d, String localName) throws SOAPException {
        Locale l = d.locale();
        SOAPElement se = (SOAPElement)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                SOAPElement sOAPElement = l._saaj.soapElement_addChildElement(se, localName);
                return sOAPElement;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            SOAPElement sOAPElement;
            l.enter();
            try {
                sOAPElement = l._saaj.soapElement_addChildElement(se, localName);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return sOAPElement;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static SOAPElement _soapElement_addChildElement(Dom d, String localName, String prefix) throws SOAPException {
        Locale l = d.locale();
        SOAPElement se = (SOAPElement)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                SOAPElement sOAPElement = l._saaj.soapElement_addChildElement(se, localName, prefix);
                return sOAPElement;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            SOAPElement sOAPElement;
            l.enter();
            try {
                sOAPElement = l._saaj.soapElement_addChildElement(se, localName, prefix);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return sOAPElement;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static SOAPElement _soapElement_addChildElement(Dom d, String localName, String prefix, String uri) throws SOAPException {
        Locale l = d.locale();
        SOAPElement se = (SOAPElement)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                SOAPElement sOAPElement = l._saaj.soapElement_addChildElement(se, localName, prefix, uri);
                return sOAPElement;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            SOAPElement sOAPElement;
            l.enter();
            try {
                sOAPElement = l._saaj.soapElement_addChildElement(se, localName, prefix, uri);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return sOAPElement;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static SOAPElement _soapElement_addNamespaceDeclaration(Dom d, String prefix, String uri) {
        Locale l = d.locale();
        SOAPElement se = (SOAPElement)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                SOAPElement sOAPElement = l._saaj.soapElement_addNamespaceDeclaration(se, prefix, uri);
                return sOAPElement;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            SOAPElement sOAPElement;
            l.enter();
            try {
                sOAPElement = l._saaj.soapElement_addNamespaceDeclaration(se, prefix, uri);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return sOAPElement;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static SOAPElement _soapElement_addTextNode(Dom d, String data) {
        Locale l = d.locale();
        SOAPElement se = (SOAPElement)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                SOAPElement sOAPElement = l._saaj.soapElement_addTextNode(se, data);
                return sOAPElement;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            SOAPElement sOAPElement;
            l.enter();
            try {
                sOAPElement = l._saaj.soapElement_addTextNode(se, data);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return sOAPElement;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static String _soapElement_getAttributeValue(Dom d, Name name) {
        Locale l = d.locale();
        SOAPElement se = (SOAPElement)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                String string = l._saaj.soapElement_getAttributeValue(se, name);
                return string;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            String string;
            l.enter();
            try {
                string = l._saaj.soapElement_getAttributeValue(se, name);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return string;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Iterator _soapElement_getChildElements(Dom d, Name name) {
        Locale l = d.locale();
        SOAPElement se = (SOAPElement)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                Iterator iterator = l._saaj.soapElement_getChildElements(se, name);
                return iterator;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            Iterator iterator;
            l.enter();
            try {
                iterator = l._saaj.soapElement_getChildElements(se, name);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return iterator;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Name _soapElement_getElementName(Dom d) {
        Locale l = d.locale();
        SOAPElement se = (SOAPElement)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                Name name = l._saaj.soapElement_getElementName(se);
                return name;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            Name name;
            l.enter();
            try {
                name = l._saaj.soapElement_getElementName(se);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return name;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static String _soapElement_getNamespaceURI(Dom d, String prefix) {
        Locale l = d.locale();
        SOAPElement se = (SOAPElement)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                String string = l._saaj.soapElement_getNamespaceURI(se, prefix);
                return string;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            String string;
            l.enter();
            try {
                string = l._saaj.soapElement_getNamespaceURI(se, prefix);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return string;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Iterator _soapElement_getVisibleNamespacePrefixes(Dom d) {
        Locale l = d.locale();
        SOAPElement se = (SOAPElement)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                Iterator iterator = l._saaj.soapElement_getVisibleNamespacePrefixes(se);
                return iterator;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            Iterator iterator;
            l.enter();
            try {
                iterator = l._saaj.soapElement_getVisibleNamespacePrefixes(se);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return iterator;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static boolean _soapElement_removeAttribute(Dom d, Name name) {
        Locale l = d.locale();
        SOAPElement se = (SOAPElement)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                boolean bl = l._saaj.soapElement_removeAttribute(se, name);
                return bl;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            boolean bl;
            l.enter();
            try {
                bl = l._saaj.soapElement_removeAttribute(se, name);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return bl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static SOAPBody _soapEnvelope_addBody(Dom d) throws SOAPException {
        Locale l = d.locale();
        SOAPEnvelope se = (SOAPEnvelope)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                SOAPBody sOAPBody = l._saaj.soapEnvelope_addBody(se);
                return sOAPBody;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            SOAPBody sOAPBody;
            l.enter();
            try {
                sOAPBody = l._saaj.soapEnvelope_addBody(se);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return sOAPBody;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static SOAPBody _soapEnvelope_getBody(Dom d) throws SOAPException {
        Locale l = d.locale();
        SOAPEnvelope se = (SOAPEnvelope)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                SOAPBody sOAPBody = l._saaj.soapEnvelope_getBody(se);
                return sOAPBody;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            SOAPBody sOAPBody;
            l.enter();
            try {
                sOAPBody = l._saaj.soapEnvelope_getBody(se);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return sOAPBody;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static SOAPHeader _soapEnvelope_getHeader(Dom d) throws SOAPException {
        Locale l = d.locale();
        SOAPEnvelope se = (SOAPEnvelope)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                SOAPHeader sOAPHeader = l._saaj.soapEnvelope_getHeader(se);
                return sOAPHeader;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            SOAPHeader sOAPHeader;
            l.enter();
            try {
                sOAPHeader = l._saaj.soapEnvelope_getHeader(se);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return sOAPHeader;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static SOAPHeader _soapEnvelope_addHeader(Dom d) throws SOAPException {
        Locale l = d.locale();
        SOAPEnvelope se = (SOAPEnvelope)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                SOAPHeader sOAPHeader = l._saaj.soapEnvelope_addHeader(se);
                return sOAPHeader;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            SOAPHeader sOAPHeader;
            l.enter();
            try {
                sOAPHeader = l._saaj.soapEnvelope_addHeader(se);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return sOAPHeader;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Name _soapEnvelope_createName(Dom d, String localName) {
        Locale l = d.locale();
        SOAPEnvelope se = (SOAPEnvelope)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                Name name = l._saaj.soapEnvelope_createName(se, localName);
                return name;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            Name name;
            l.enter();
            try {
                name = l._saaj.soapEnvelope_createName(se, localName);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return name;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Name _soapEnvelope_createName(Dom d, String localName, String prefix, String namespaceURI) {
        Locale l = d.locale();
        SOAPEnvelope se = (SOAPEnvelope)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                Name name = l._saaj.soapEnvelope_createName(se, localName, prefix, namespaceURI);
                return name;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            Name name;
            l.enter();
            try {
                name = l._saaj.soapEnvelope_createName(se, localName, prefix, namespaceURI);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return name;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Iterator soapHeader_examineAllHeaderElements(Dom d) {
        Locale l = d.locale();
        SOAPHeader sh = (SOAPHeader)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                Iterator iterator = l._saaj.soapHeader_examineAllHeaderElements(sh);
                return iterator;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            Iterator iterator;
            l.enter();
            try {
                iterator = l._saaj.soapHeader_examineAllHeaderElements(sh);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return iterator;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Iterator soapHeader_extractAllHeaderElements(Dom d) {
        Locale l = d.locale();
        SOAPHeader sh = (SOAPHeader)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                Iterator iterator = l._saaj.soapHeader_extractAllHeaderElements(sh);
                return iterator;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            Iterator iterator;
            l.enter();
            try {
                iterator = l._saaj.soapHeader_extractAllHeaderElements(sh);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return iterator;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Iterator soapHeader_examineHeaderElements(Dom d, String actor) {
        Locale l = d.locale();
        SOAPHeader sh = (SOAPHeader)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                Iterator iterator = l._saaj.soapHeader_examineHeaderElements(sh, actor);
                return iterator;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            Iterator iterator;
            l.enter();
            try {
                iterator = l._saaj.soapHeader_examineHeaderElements(sh, actor);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return iterator;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Iterator soapHeader_examineMustUnderstandHeaderElements(Dom d, String mustUnderstandString) {
        Locale l = d.locale();
        SOAPHeader sh = (SOAPHeader)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                Iterator iterator = l._saaj.soapHeader_examineMustUnderstandHeaderElements(sh, mustUnderstandString);
                return iterator;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            Iterator iterator;
            l.enter();
            try {
                iterator = l._saaj.soapHeader_examineMustUnderstandHeaderElements(sh, mustUnderstandString);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return iterator;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Iterator soapHeader_extractHeaderElements(Dom d, String actor) {
        Locale l = d.locale();
        SOAPHeader sh = (SOAPHeader)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                Iterator iterator = l._saaj.soapHeader_extractHeaderElements(sh, actor);
                return iterator;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            Iterator iterator;
            l.enter();
            try {
                iterator = l._saaj.soapHeader_extractHeaderElements(sh, actor);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return iterator;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static SOAPHeaderElement soapHeader_addHeaderElement(Dom d, Name name) {
        Locale l = d.locale();
        SOAPHeader sh = (SOAPHeader)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                SOAPHeaderElement sOAPHeaderElement = l._saaj.soapHeader_addHeaderElement(sh, name);
                return sOAPHeaderElement;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            SOAPHeaderElement sOAPHeaderElement;
            l.enter();
            try {
                sOAPHeaderElement = l._saaj.soapHeader_addHeaderElement(sh, name);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return sOAPHeaderElement;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static boolean soapBody_hasFault(Dom d) {
        Locale l = d.locale();
        SOAPBody sb = (SOAPBody)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                boolean bl = l._saaj.soapBody_hasFault(sb);
                return bl;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            boolean bl;
            l.enter();
            try {
                bl = l._saaj.soapBody_hasFault(sb);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return bl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static SOAPFault soapBody_addFault(Dom d) throws SOAPException {
        Locale l = d.locale();
        SOAPBody sb = (SOAPBody)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                SOAPFault sOAPFault = l._saaj.soapBody_addFault(sb);
                return sOAPFault;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            SOAPFault sOAPFault;
            l.enter();
            try {
                sOAPFault = l._saaj.soapBody_addFault(sb);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return sOAPFault;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static SOAPFault soapBody_getFault(Dom d) {
        Locale l = d.locale();
        SOAPBody sb = (SOAPBody)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                SOAPFault sOAPFault = l._saaj.soapBody_getFault(sb);
                return sOAPFault;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            SOAPFault sOAPFault;
            l.enter();
            try {
                sOAPFault = l._saaj.soapBody_getFault(sb);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return sOAPFault;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static SOAPBodyElement soapBody_addBodyElement(Dom d, Name name) {
        Locale l = d.locale();
        SOAPBody sb = (SOAPBody)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                SOAPBodyElement sOAPBodyElement = l._saaj.soapBody_addBodyElement(sb, name);
                return sOAPBodyElement;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            SOAPBodyElement sOAPBodyElement;
            l.enter();
            try {
                sOAPBodyElement = l._saaj.soapBody_addBodyElement(sb, name);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return sOAPBodyElement;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static SOAPBodyElement soapBody_addDocument(Dom d, Document document) {
        Locale l = d.locale();
        SOAPBody sb = (SOAPBody)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                SOAPBodyElement sOAPBodyElement = l._saaj.soapBody_addDocument(sb, document);
                return sOAPBodyElement;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            SOAPBodyElement sOAPBodyElement;
            l.enter();
            try {
                sOAPBodyElement = l._saaj.soapBody_addDocument(sb, document);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return sOAPBodyElement;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static SOAPFault soapBody_addFault(Dom d, Name name, String s) throws SOAPException {
        Locale l = d.locale();
        SOAPBody sb = (SOAPBody)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                SOAPFault sOAPFault = l._saaj.soapBody_addFault(sb, name, s);
                return sOAPFault;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            SOAPFault sOAPFault;
            l.enter();
            try {
                sOAPFault = l._saaj.soapBody_addFault(sb, name, s);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return sOAPFault;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static SOAPFault soapBody_addFault(Dom d, Name faultCode, String faultString, java.util.Locale locale) throws SOAPException {
        Locale l = d.locale();
        SOAPBody sb = (SOAPBody)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                SOAPFault sOAPFault = l._saaj.soapBody_addFault(sb, faultCode, faultString, locale);
                return sOAPFault;
            } finally {
                l.exit();
            }
        }
        Locale locale2 = l;
        synchronized (locale2) {
            SOAPFault sOAPFault;
            l.enter();
            try {
                sOAPFault = l._saaj.soapBody_addFault(sb, faultCode, faultString, locale);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return sOAPFault;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void soapFault_setFaultString(Dom d, String faultString) {
        Locale l = d.locale();
        SOAPFault sf = (SOAPFault)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                l._saaj.soapFault_setFaultString(sf, faultString);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                l._saaj.soapFault_setFaultString(sf, faultString);
            } finally {
                l.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void soapFault_setFaultString(Dom d, String faultString, java.util.Locale locale) {
        Locale l = d.locale();
        SOAPFault sf = (SOAPFault)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                l._saaj.soapFault_setFaultString(sf, faultString, locale);
            } finally {
                l.exit();
            }
        }
        Locale locale2 = l;
        synchronized (locale2) {
            l.enter();
            try {
                l._saaj.soapFault_setFaultString(sf, faultString, locale);
            } finally {
                l.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void soapFault_setFaultCode(Dom d, Name faultCodeName) throws SOAPException {
        Locale l = d.locale();
        SOAPFault sf = (SOAPFault)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                l._saaj.soapFault_setFaultCode(sf, faultCodeName);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                l._saaj.soapFault_setFaultCode(sf, faultCodeName);
            } finally {
                l.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void soapFault_setFaultActor(Dom d, String faultActorString) {
        Locale l = d.locale();
        SOAPFault sf = (SOAPFault)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                l._saaj.soapFault_setFaultActor(sf, faultActorString);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                l._saaj.soapFault_setFaultActor(sf, faultActorString);
            } finally {
                l.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static String soapFault_getFaultActor(Dom d) {
        Locale l = d.locale();
        SOAPFault sf = (SOAPFault)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                String string = l._saaj.soapFault_getFaultActor(sf);
                return string;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            String string;
            l.enter();
            try {
                string = l._saaj.soapFault_getFaultActor(sf);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return string;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static String soapFault_getFaultCode(Dom d) {
        Locale l = d.locale();
        SOAPFault sf = (SOAPFault)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                String string = l._saaj.soapFault_getFaultCode(sf);
                return string;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            String string;
            l.enter();
            try {
                string = l._saaj.soapFault_getFaultCode(sf);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return string;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void soapFault_setFaultCode(Dom d, String faultCode) throws SOAPException {
        Locale l = d.locale();
        SOAPFault sf = (SOAPFault)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                l._saaj.soapFault_setFaultCode(sf, faultCode);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                l._saaj.soapFault_setFaultCode(sf, faultCode);
            } finally {
                l.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static java.util.Locale soapFault_getFaultStringLocale(Dom d) {
        Locale l = d.locale();
        SOAPFault sf = (SOAPFault)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                java.util.Locale locale = l._saaj.soapFault_getFaultStringLocale(sf);
                return locale;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            java.util.Locale locale2;
            l.enter();
            try {
                locale2 = l._saaj.soapFault_getFaultStringLocale(sf);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return locale2;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Name soapFault_getFaultCodeAsName(Dom d) {
        Locale l = d.locale();
        SOAPFault sf = (SOAPFault)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                Name name = l._saaj.soapFault_getFaultCodeAsName(sf);
                return name;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            Name name;
            l.enter();
            try {
                name = l._saaj.soapFault_getFaultCodeAsName(sf);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return name;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static String soapFault_getFaultString(Dom d) {
        Locale l = d.locale();
        SOAPFault sf = (SOAPFault)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                String string = l._saaj.soapFault_getFaultString(sf);
                return string;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            String string;
            l.enter();
            try {
                string = l._saaj.soapFault_getFaultString(sf);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return string;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Detail soapFault_addDetail(Dom d) throws SOAPException {
        Locale l = d.locale();
        SOAPFault sf = (SOAPFault)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                Detail detail = l._saaj.soapFault_addDetail(sf);
                return detail;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            Detail detail;
            l.enter();
            try {
                detail = l._saaj.soapFault_addDetail(sf);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return detail;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Detail soapFault_getDetail(Dom d) {
        Locale l = d.locale();
        SOAPFault sf = (SOAPFault)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                Detail detail = l._saaj.soapFault_getDetail(sf);
                return detail;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            Detail detail;
            l.enter();
            try {
                detail = l._saaj.soapFault_getDetail(sf);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return detail;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void soapHeaderElement_setMustUnderstand(Dom d, boolean mustUnderstand) {
        Locale l = d.locale();
        SOAPHeaderElement she = (SOAPHeaderElement)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                l._saaj.soapHeaderElement_setMustUnderstand(she, mustUnderstand);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                l._saaj.soapHeaderElement_setMustUnderstand(she, mustUnderstand);
            } finally {
                l.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static boolean soapHeaderElement_getMustUnderstand(Dom d) {
        Locale l = d.locale();
        SOAPHeaderElement she = (SOAPHeaderElement)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                boolean bl = l._saaj.soapHeaderElement_getMustUnderstand(she);
                return bl;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            boolean bl;
            l.enter();
            try {
                bl = l._saaj.soapHeaderElement_getMustUnderstand(she);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return bl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void soapHeaderElement_setActor(Dom d, String actor) {
        Locale l = d.locale();
        SOAPHeaderElement she = (SOAPHeaderElement)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                l._saaj.soapHeaderElement_setActor(she, actor);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                l._saaj.soapHeaderElement_setActor(she, actor);
            } finally {
                l.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static String soapHeaderElement_getActor(Dom d) {
        Locale l = d.locale();
        SOAPHeaderElement she = (SOAPHeaderElement)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                String string = l._saaj.soapHeaderElement_getActor(she);
                return string;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            String string;
            l.enter();
            try {
                string = l._saaj.soapHeaderElement_getActor(she);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return string;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static DetailEntry detail_addDetailEntry(Dom d, Name name) {
        Locale l = d.locale();
        Detail detail = (Detail)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                DetailEntry detailEntry = l._saaj.detail_addDetailEntry(detail, name);
                return detailEntry;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            DetailEntry detailEntry;
            l.enter();
            try {
                detailEntry = l._saaj.detail_addDetailEntry(detail, name);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return detailEntry;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Iterator detail_getDetailEntries(Dom d) {
        Locale l = d.locale();
        Detail detail = (Detail)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                Iterator iterator = l._saaj.detail_getDetailEntries(detail);
                return iterator;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            Iterator iterator;
            l.enter();
            try {
                iterator = l._saaj.detail_getDetailEntries(detail);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return iterator;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void _soapPart_removeAllMimeHeaders(Dom d) {
        Locale l = d.locale();
        SOAPPart sp = (SOAPPart)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                l._saaj.soapPart_removeAllMimeHeaders(sp);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                l._saaj.soapPart_removeAllMimeHeaders(sp);
            } finally {
                l.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void _soapPart_removeMimeHeader(Dom d, String name) {
        Locale l = d.locale();
        SOAPPart sp = (SOAPPart)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                l._saaj.soapPart_removeMimeHeader(sp, name);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                l._saaj.soapPart_removeMimeHeader(sp, name);
            } finally {
                l.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Iterator _soapPart_getAllMimeHeaders(Dom d) {
        Locale l = d.locale();
        SOAPPart sp = (SOAPPart)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                Iterator iterator = l._saaj.soapPart_getAllMimeHeaders(sp);
                return iterator;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            Iterator iterator;
            l.enter();
            try {
                iterator = l._saaj.soapPart_getAllMimeHeaders(sp);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return iterator;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static SOAPEnvelope _soapPart_getEnvelope(Dom d) {
        Locale l = d.locale();
        SOAPPart sp = (SOAPPart)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                SOAPEnvelope sOAPEnvelope = l._saaj.soapPart_getEnvelope(sp);
                return sOAPEnvelope;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            SOAPEnvelope sOAPEnvelope;
            l.enter();
            try {
                sOAPEnvelope = l._saaj.soapPart_getEnvelope(sp);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return sOAPEnvelope;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Source _soapPart_getContent(Dom d) {
        Locale l = d.locale();
        SOAPPart sp = (SOAPPart)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                Source source = l._saaj.soapPart_getContent(sp);
                return source;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            Source source;
            l.enter();
            try {
                source = l._saaj.soapPart_getContent(sp);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return source;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void _soapPart_setContent(Dom d, Source source) {
        Locale l = d.locale();
        SOAPPart sp = (SOAPPart)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                l._saaj.soapPart_setContent(sp, source);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                l._saaj.soapPart_setContent(sp, source);
            } finally {
                l.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static String[] _soapPart_getMimeHeader(Dom d, String name) {
        Locale l = d.locale();
        SOAPPart sp = (SOAPPart)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                String[] stringArray = l._saaj.soapPart_getMimeHeader(sp, name);
                return stringArray;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            String[] stringArray;
            l.enter();
            try {
                stringArray = l._saaj.soapPart_getMimeHeader(sp, name);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return stringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void _soapPart_addMimeHeader(Dom d, String name, String value) {
        Locale l = d.locale();
        SOAPPart sp = (SOAPPart)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                l._saaj.soapPart_addMimeHeader(sp, name, value);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                l._saaj.soapPart_addMimeHeader(sp, name, value);
            } finally {
                l.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void _soapPart_setMimeHeader(Dom d, String name, String value) {
        Locale l = d.locale();
        SOAPPart sp = (SOAPPart)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                l._saaj.soapPart_setMimeHeader(sp, name, value);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                l._saaj.soapPart_setMimeHeader(sp, name, value);
            } finally {
                l.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Iterator _soapPart_getMatchingMimeHeaders(Dom d, String[] names) {
        Locale l = d.locale();
        SOAPPart sp = (SOAPPart)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                Iterator iterator = l._saaj.soapPart_getMatchingMimeHeaders(sp, names);
                return iterator;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            Iterator iterator;
            l.enter();
            try {
                iterator = l._saaj.soapPart_getMatchingMimeHeaders(sp, names);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return iterator;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Iterator _soapPart_getNonMatchingMimeHeaders(Dom d, String[] names) {
        Locale l = d.locale();
        SOAPPart sp = (SOAPPart)((Object)d);
        if (l.noSync()) {
            l.enter();
            try {
                Iterator iterator = l._saaj.soapPart_getNonMatchingMimeHeaders(sp, names);
                return iterator;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            Iterator iterator;
            l.enter();
            try {
                iterator = l._saaj.soapPart_getNonMatchingMimeHeaders(sp, names);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return iterator;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void saajCallback_setSaajData(Dom d, Object o) {
        Locale l = d.locale();
        if (l.noSync()) {
            l.enter();
            try {
                DomImpl.impl_saajCallback_setSaajData(d, o);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                DomImpl.impl_saajCallback_setSaajData(d, o);
            } finally {
                l.exit();
            }
        }
    }

    public static void impl_saajCallback_setSaajData(Dom d, Object o) {
        Locale l = d.locale();
        Cur c = l.tempCur();
        c.moveToDom(d);
        SaajData sd = null;
        if (o != null) {
            sd = (SaajData)c.getBookmark(SaajData.class);
            if (sd == null) {
                sd = new SaajData();
            }
            sd._obj = o;
        }
        c.setBookmark(SaajData.class, sd);
        c.release();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Object saajCallback_getSaajData(Dom d) {
        Locale l = d.locale();
        if (l.noSync()) {
            l.enter();
            try {
                Object object = DomImpl.impl_saajCallback_getSaajData(d);
                return object;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            Object object;
            l.enter();
            try {
                object = DomImpl.impl_saajCallback_getSaajData(d);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return object;
        }
    }

    public static Object impl_saajCallback_getSaajData(Dom d) {
        Locale l = d.locale();
        Cur c = l.tempCur();
        c.moveToDom(d);
        SaajData sd = (SaajData)c.getBookmark(SaajData.class);
        Object o = sd == null ? null : sd._obj;
        c.release();
        return o;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Element saajCallback_createSoapElement(Dom d, QName name, QName parentName) {
        Dom e;
        Locale l = d.locale();
        if (l.noSync()) {
            l.enter();
            try {
                e = DomImpl.impl_saajCallback_createSoapElement(d, name, parentName);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                e = DomImpl.impl_saajCallback_createSoapElement(d, name, parentName);
            } finally {
                l.exit();
            }
        }
        return (Element)((Object)e);
    }

    public static Dom impl_saajCallback_createSoapElement(Dom d, QName name, QName parentName) {
        Cur c = d.locale().tempCur();
        c.createElement(name, parentName);
        Dom e = c.getDom();
        c.release();
        return e;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Element saajCallback_importSoapElement(Dom d, Element elem, boolean deep, QName parentName) {
        Dom e;
        Locale l = d.locale();
        if (l.noSync()) {
            l.enter();
            try {
                e = DomImpl.impl_saajCallback_importSoapElement(d, elem, deep, parentName);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                e = DomImpl.impl_saajCallback_importSoapElement(d, elem, deep, parentName);
            } finally {
                l.exit();
            }
        }
        return (Element)((Object)e);
    }

    public static Dom impl_saajCallback_importSoapElement(Dom d, Element elem, boolean deep, QName parentName) {
        throw new RuntimeException("Not impl");
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Text saajCallback_ensureSoapTextNode(Dom d) {
        Locale l = d.locale();
        if (l.noSync()) {
            l.enter();
            try {
                Text text = DomImpl.impl_saajCallback_ensureSoapTextNode(d);
                return text;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            Text text;
            l.enter();
            try {
                text = DomImpl.impl_saajCallback_ensureSoapTextNode(d);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return text;
        }
    }

    public static Text impl_saajCallback_ensureSoapTextNode(Dom d) {
        return null;
    }

    private static class SaajData {
        Object _obj;

        private SaajData() {
        }
    }

    static class SaajCdataNode
    extends CdataNode
    implements org.apache.xmlbeans.impl.soap.Text {
        public SaajCdataNode(Locale l) {
            super(l);
        }

        @Override
        public boolean isComment() {
            return DomImpl._soapText_isComment(this);
        }

        @Override
        public void detachNode() {
            DomImpl._soapNode_detachNode(this);
        }

        @Override
        public void recycleNode() {
            DomImpl._soapNode_recycleNode(this);
        }

        @Override
        public String getValue() {
            return DomImpl._soapNode_getValue(this);
        }

        @Override
        public void setValue(String value) {
            DomImpl._soapNode_setValue(this, value);
        }

        @Override
        public SOAPElement getParentElement() {
            return DomImpl._soapNode_getParentElement(this);
        }

        @Override
        public void setParentElement(SOAPElement p) {
            DomImpl._soapNode_setParentElement(this, p);
        }
    }

    static class SaajTextNode
    extends TextNode
    implements org.apache.xmlbeans.impl.soap.Text {
        SaajTextNode(Locale l) {
            super(l);
        }

        @Override
        public boolean isComment() {
            return DomImpl._soapText_isComment(this);
        }

        @Override
        public void detachNode() {
            DomImpl._soapNode_detachNode(this);
        }

        @Override
        public void recycleNode() {
            DomImpl._soapNode_recycleNode(this);
        }

        @Override
        public String getValue() {
            return DomImpl._soapNode_getValue(this);
        }

        @Override
        public void setValue(String value) {
            DomImpl._soapNode_setValue(this, value);
        }

        @Override
        public SOAPElement getParentElement() {
            return DomImpl._soapNode_getParentElement(this);
        }

        @Override
        public void setParentElement(SOAPElement p) {
            DomImpl._soapNode_setParentElement(this, p);
        }
    }

    static class CdataNode
    extends TextNode
    implements CDATASection {
        CdataNode(Locale l) {
            super(l);
        }

        @Override
        public int nodeType() {
            return 4;
        }

        @Override
        public String name() {
            return "#cdata-section";
        }
    }

    static class TextNode
    extends CharNode
    implements Text {
        TextNode(Locale l) {
            super(l);
        }

        @Override
        public int nodeType() {
            return 3;
        }

        public String name() {
            return "#text";
        }

        @Override
        public Text splitText(int offset) {
            return DomImpl._text_splitText(this, offset);
        }

        @Override
        public String getWholeText() {
            return DomImpl._text_getWholeText(this);
        }

        @Override
        public boolean isElementContentWhitespace() {
            return DomImpl._text_isElementContentWhitespace(this);
        }

        @Override
        public Text replaceWholeText(String content) {
            return DomImpl._text_replaceWholeText(this, content);
        }
    }

    static abstract class CharNode
    implements Dom,
    org.w3c.dom.Node,
    CharacterData {
        private Locale _locale;
        CharNode _next;
        CharNode _prev;
        private Object _src;
        int _off;
        int _cch;

        public CharNode(Locale l) {
            assert (l != null);
            this._locale = l;
        }

        @Override
        public QName getQName() {
            return null;
        }

        @Override
        public Locale locale() {
            assert (this.isValid());
            return this._locale == null ? ((Dom)this._src).locale() : this._locale;
        }

        public void setChars(Object src, int off, int cch) {
            assert (CharUtil.isValid(src, off, cch));
            assert (this._locale != null || this._src instanceof Dom);
            if (this._locale == null) {
                this._locale = ((Dom)this._src).locale();
            }
            this._src = src;
            this._off = off;
            this._cch = cch;
        }

        public Dom getDom() {
            assert (this.isValid());
            if (this._src instanceof Dom) {
                return (Dom)this._src;
            }
            return null;
        }

        public void setDom(Dom d) {
            assert (d != null);
            this._src = d;
            this._locale = null;
        }

        @Override
        public Cur tempCur() {
            assert (this.isValid());
            if (!(this._src instanceof Dom)) {
                return null;
            }
            Cur c = this.locale().tempCur();
            c.moveToCharNode(this);
            return c;
        }

        private boolean isValid() {
            if (this._src instanceof Dom) {
                return this._locale == null;
            }
            return this._locale != null;
        }

        public static boolean isOnList(CharNode nodes, CharNode node) {
            assert (node != null);
            CharNode cn = nodes;
            while (cn != null) {
                if (cn == node) {
                    return true;
                }
                cn = cn._next;
            }
            return false;
        }

        public static CharNode remove(CharNode nodes, CharNode node) {
            assert (CharNode.isOnList(nodes, node));
            if (nodes == node) {
                nodes = node._next;
            } else {
                node._prev._next = node._next;
            }
            if (node._next != null) {
                node._next._prev = node._prev;
            }
            node._next = null;
            node._prev = null;
            return nodes;
        }

        public static CharNode insertNode(CharNode nodes, CharNode newNode, CharNode before) {
            assert (!CharNode.isOnList(nodes, newNode));
            assert (before == null || CharNode.isOnList(nodes, before));
            assert (newNode != null);
            assert (newNode._prev == null && newNode._next == null);
            if (nodes == null) {
                assert (before == null);
                nodes = newNode;
            } else if (nodes == before) {
                nodes._prev = newNode;
                newNode._next = nodes;
                nodes = newNode;
            } else {
                CharNode n = nodes;
                while (n._next != before) {
                    n = n._next;
                }
                newNode._next = n._next;
                if (newNode._next != null) {
                    n._next._prev = newNode;
                }
                newNode._prev = n;
                n._next = newNode;
            }
            return nodes;
        }

        public static CharNode appendNode(CharNode nodes, CharNode newNode) {
            return CharNode.insertNode(nodes, newNode, null);
        }

        public static CharNode appendNodes(CharNode nodes, CharNode newNodes) {
            assert (newNodes != null);
            assert (newNodes._prev == null);
            if (nodes == null) {
                return newNodes;
            }
            CharNode n = nodes;
            while (n._next != null) {
                n = n._next;
            }
            n._next = newNodes;
            newNodes._prev = n;
            return nodes;
        }

        public static CharNode copyNodes(CharNode nodes, Object newSrc) {
            TextNode newNodes = null;
            TextNode n = null;
            while (nodes != null) {
                TextNode newNode = nodes instanceof TextNode ? nodes.locale().createTextNode() : nodes.locale().createCdataNode();
                newNode.setChars(newSrc, nodes._off, nodes._cch);
                if (newNodes == null) {
                    newNodes = newNode;
                }
                if (n != null) {
                    n._next = newNode;
                    newNode._prev = n;
                }
                n = newNode;
                nodes = nodes._next;
            }
            return newNodes;
        }

        @Override
        public boolean nodeCanHavePrefixUri() {
            return false;
        }

        public boolean isNodeAftertext() {
            assert (this._src instanceof Xobj) : "this method is to only be used for nodes backed up by Xobjs";
            Xobj src = (Xobj)this._src;
            return src._charNodesValue == null ? true : (src._charNodesAfter == null ? false : CharNode.isOnList(src._charNodesAfter, this));
        }

        @Override
        public void dump(PrintStream o, Object ref) {
            if (this._src instanceof Dom) {
                ((Dom)this._src).dump(o, ref);
            } else {
                o.println("Lonely CharNode: \"" + CharUtil.getString(this._src, this._off, this._cch) + "\"");
            }
        }

        @Override
        public void dump(PrintStream o) {
            this.dump(o, this);
        }

        @Override
        public void dump() {
            this.dump(System.out);
        }

        @Override
        public org.w3c.dom.Node appendChild(org.w3c.dom.Node newChild) {
            return DomImpl._node_appendChild(this, newChild);
        }

        @Override
        public org.w3c.dom.Node cloneNode(boolean deep) {
            return DomImpl._node_cloneNode(this, deep);
        }

        @Override
        public NamedNodeMap getAttributes() {
            return null;
        }

        @Override
        public NodeList getChildNodes() {
            return _emptyNodeList;
        }

        @Override
        public org.w3c.dom.Node getParentNode() {
            return DomImpl._node_getParentNode(this);
        }

        @Override
        public org.w3c.dom.Node removeChild(org.w3c.dom.Node oldChild) {
            return DomImpl._node_removeChild(this, oldChild);
        }

        @Override
        public org.w3c.dom.Node getFirstChild() {
            return null;
        }

        @Override
        public org.w3c.dom.Node getLastChild() {
            return null;
        }

        @Override
        public String getLocalName() {
            return DomImpl._node_getLocalName(this);
        }

        @Override
        public String getNamespaceURI() {
            return DomImpl._node_getNamespaceURI(this);
        }

        @Override
        public org.w3c.dom.Node getNextSibling() {
            return DomImpl._node_getNextSibling(this);
        }

        @Override
        public String getNodeName() {
            return DomImpl._node_getNodeName(this);
        }

        @Override
        public short getNodeType() {
            return DomImpl._node_getNodeType(this);
        }

        @Override
        public String getNodeValue() {
            return DomImpl._node_getNodeValue(this);
        }

        @Override
        public Document getOwnerDocument() {
            return DomImpl._node_getOwnerDocument(this);
        }

        @Override
        public String getPrefix() {
            return DomImpl._node_getPrefix(this);
        }

        @Override
        public org.w3c.dom.Node getPreviousSibling() {
            return DomImpl._node_getPreviousSibling(this);
        }

        @Override
        public boolean hasAttributes() {
            return false;
        }

        @Override
        public boolean hasChildNodes() {
            return false;
        }

        @Override
        public org.w3c.dom.Node insertBefore(org.w3c.dom.Node newChild, org.w3c.dom.Node refChild) {
            return DomImpl._node_insertBefore(this, newChild, refChild);
        }

        @Override
        public boolean isSupported(String feature, String version) {
            return DomImpl._node_isSupported(this, feature, version);
        }

        @Override
        public void normalize() {
            DomImpl._node_normalize(this);
        }

        @Override
        public org.w3c.dom.Node replaceChild(org.w3c.dom.Node newChild, org.w3c.dom.Node oldChild) {
            return DomImpl._node_replaceChild(this, newChild, oldChild);
        }

        @Override
        public void setNodeValue(String nodeValue) {
            DomImpl._node_setNodeValue(this, nodeValue);
        }

        @Override
        public void setPrefix(String prefix) {
            DomImpl._node_setPrefix(this, prefix);
        }

        @Override
        public Object getUserData(String key) {
            return DomImpl._node_getUserData(this, key);
        }

        @Override
        public Object setUserData(String key, Object data, UserDataHandler handler) {
            return DomImpl._node_setUserData(this, key, data, handler);
        }

        @Override
        public Object getFeature(String feature, String version) {
            return DomImpl._node_getFeature(this, feature, version);
        }

        @Override
        public boolean isEqualNode(org.w3c.dom.Node arg) {
            return DomImpl._node_isEqualNode(this, arg);
        }

        @Override
        public boolean isSameNode(org.w3c.dom.Node arg) {
            return DomImpl._node_isSameNode(this, arg);
        }

        @Override
        public String lookupNamespaceURI(String prefix) {
            return DomImpl._node_lookupNamespaceURI(this, prefix);
        }

        @Override
        public String lookupPrefix(String namespaceURI) {
            return DomImpl._node_lookupPrefix(this, namespaceURI);
        }

        @Override
        public boolean isDefaultNamespace(String namespaceURI) {
            return DomImpl._node_isDefaultNamespace(this, namespaceURI);
        }

        @Override
        public void setTextContent(String textContent) {
            DomImpl._node_setTextContent(this, textContent);
        }

        @Override
        public String getTextContent() {
            return DomImpl._node_getTextContent(this);
        }

        @Override
        public short compareDocumentPosition(org.w3c.dom.Node other) {
            return DomImpl._node_compareDocumentPosition(this, other);
        }

        @Override
        public String getBaseURI() {
            return DomImpl._node_getBaseURI(this);
        }

        @Override
        public void appendData(String arg) {
            DomImpl._characterData_appendData(this, arg);
        }

        @Override
        public void deleteData(int offset, int count) {
            DomImpl._characterData_deleteData(this, offset, count);
        }

        @Override
        public String getData() {
            return DomImpl._characterData_getData(this);
        }

        @Override
        public int getLength() {
            return DomImpl._characterData_getLength(this);
        }

        @Override
        public void insertData(int offset, String arg) {
            DomImpl._characterData_insertData(this, offset, arg);
        }

        @Override
        public void replaceData(int offset, int count, String arg) {
            DomImpl._characterData_replaceData(this, offset, count, arg);
        }

        @Override
        public void setData(String data) {
            DomImpl._characterData_setData(this, data);
        }

        @Override
        public String substringData(int offset, int count) {
            return DomImpl._characterData_substringData(this, offset, count);
        }
    }

    private static class ElementsByTagNameNSNodeList
    extends ElementsNodeList {
        private String _uri;
        private String _local;

        ElementsByTagNameNSNodeList(Dom root, String uri, String local) {
            super(root);
            this._uri = uri == null ? "" : uri;
            this._local = local;
        }

        @Override
        protected boolean match(Dom element) {
            if (!this._uri.equals("*") && !DomImpl._node_getNamespaceURI(element).equals(this._uri)) {
                return false;
            }
            return this._local.equals("*") ? true : DomImpl._node_getLocalName(element).equals(this._local);
        }
    }

    private static class ElementsByTagNameNodeList
    extends ElementsNodeList {
        private String _name;

        ElementsByTagNameNodeList(Dom root, String name) {
            super(root);
            this._name = name;
        }

        @Override
        protected boolean match(Dom element) {
            return this._name.equals("*") ? true : DomImpl._node_getNodeName(element).equals(this._name);
        }
    }

    private static abstract class ElementsNodeList
    implements NodeList {
        private Dom _root;
        private Locale _locale;
        private long _version;
        private ArrayList _elements;

        ElementsNodeList(Dom root) {
            assert (root.nodeType() == 9 || root.nodeType() == 1);
            this._root = root;
            this._locale = this._root.locale();
            this._version = 0L;
        }

        @Override
        public int getLength() {
            this.ensureElements();
            return this._elements.size();
        }

        @Override
        public org.w3c.dom.Node item(int i) {
            this.ensureElements();
            return i < 0 || i >= this._elements.size() ? (org.w3c.dom.Node)null : (org.w3c.dom.Node)this._elements.get(i);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        private void ensureElements() {
            if (this._version == this._locale.version()) {
                return;
            }
            this._version = this._locale.version();
            this._elements = new ArrayList();
            Locale l = this._locale;
            if (l.noSync()) {
                l.enter();
                try {
                    this.addElements(this._root);
                } finally {
                    l.exit();
                }
            }
            Locale locale = l;
            synchronized (locale) {
                l.enter();
                try {
                    this.addElements(this._root);
                } finally {
                    l.exit();
                }
            }
        }

        private void addElements(Dom node) {
            Dom c = DomImpl.firstChild(node);
            while (c != null) {
                if (c.nodeType() == 1) {
                    if (this.match(c)) {
                        this._elements.add(c);
                    }
                    this.addElements(c);
                }
                c = DomImpl.nextSibling(c);
            }
        }

        protected abstract boolean match(Dom var1);
    }

    private static final class EmptyNodeList
    implements NodeList {
        private EmptyNodeList() {
        }

        @Override
        public int getLength() {
            return 0;
        }

        @Override
        public org.w3c.dom.Node item(int i) {
            return null;
        }
    }

    static class InvalidCharacterError
    extends DOMException {
        InvalidCharacterError() {
            this("The name contains an invalid character");
        }

        InvalidCharacterError(String message) {
            super((short)5, message);
        }
    }

    static class NotSupportedError
    extends DOMException {
        NotSupportedError() {
            this("This operation is not supported");
        }

        NotSupportedError(String message) {
            super((short)9, message);
        }
    }

    static class IndexSizeError
    extends DOMException {
        IndexSizeError() {
            this("Index Size Error");
        }

        IndexSizeError(String message) {
            super((short)1, message);
        }
    }

    static class InuseAttributeError
    extends DOMException {
        InuseAttributeError() {
            this("Attribute currently in use error");
        }

        InuseAttributeError(String message) {
            super((short)10, message);
        }
    }

    static class NoModificationAllowedErr
    extends DOMException {
        NoModificationAllowedErr() {
            this("No modification allowed error");
        }

        NoModificationAllowedErr(String message) {
            super((short)7, message);
        }
    }

    static class NamespaceErr
    extends DOMException {
        NamespaceErr() {
            this("Namespace error");
        }

        NamespaceErr(String message) {
            super((short)14, message);
        }
    }

    static class NotFoundErr
    extends DOMException {
        NotFoundErr() {
            this("Node not found");
        }

        NotFoundErr(String message) {
            super((short)8, message);
        }
    }

    static class WrongDocumentErr
    extends DOMException {
        WrongDocumentErr() {
            this("Nodes do not belong to the same document");
        }

        WrongDocumentErr(String message) {
            super((short)4, message);
        }
    }

    static class HierarchyRequestErr
    extends DOMException {
        HierarchyRequestErr() {
            this("This node isn't allowed there");
        }

        HierarchyRequestErr(String message) {
            super((short)3, message);
        }
    }

    static interface Dom {
        public Locale locale();

        public int nodeType();

        public Cur tempCur();

        public QName getQName();

        public boolean nodeCanHavePrefixUri();

        public void dump();

        public void dump(PrintStream var1);

        public void dump(PrintStream var1, Object var2);
    }
}

