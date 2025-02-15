/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.xmlimpl;

import java.io.Serializable;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.Ref;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.Wrapper;
import org.mozilla.javascript.xml.XMLLib;
import org.mozilla.javascript.xml.XMLObject;
import org.mozilla.javascript.xmlimpl.Namespace;
import org.mozilla.javascript.xmlimpl.QName;
import org.mozilla.javascript.xmlimpl.XML;
import org.mozilla.javascript.xmlimpl.XMLList;
import org.mozilla.javascript.xmlimpl.XMLName;
import org.mozilla.javascript.xmlimpl.XMLObjectImpl;
import org.mozilla.javascript.xmlimpl.XMLWithScope;
import org.mozilla.javascript.xmlimpl.XmlNode;
import org.mozilla.javascript.xmlimpl.XmlProcessor;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public final class XMLLibImpl
extends XMLLib
implements Serializable {
    private static final long serialVersionUID = 1L;
    private Scriptable globalScope;
    private XML xmlPrototype;
    private XMLList xmlListPrototype;
    private Namespace namespacePrototype;
    private QName qnamePrototype;
    private XmlProcessor options = new XmlProcessor();

    public static Node toDomNode(Object xmlObject) {
        if (xmlObject instanceof XML) {
            return ((XML)xmlObject).toDomNode();
        }
        throw new IllegalArgumentException("xmlObject is not an XML object in JavaScript.");
    }

    public static void init(Context cx, Scriptable scope, boolean sealed) {
        XMLLibImpl lib = new XMLLibImpl(scope);
        XMLLib bound = lib.bindToScope(scope);
        if (bound == lib) {
            lib.exportToScope(sealed);
        }
    }

    @Override
    public void setIgnoreComments(boolean b) {
        this.options.setIgnoreComments(b);
    }

    @Override
    public void setIgnoreWhitespace(boolean b) {
        this.options.setIgnoreWhitespace(b);
    }

    @Override
    public void setIgnoreProcessingInstructions(boolean b) {
        this.options.setIgnoreProcessingInstructions(b);
    }

    @Override
    public void setPrettyPrinting(boolean b) {
        this.options.setPrettyPrinting(b);
    }

    @Override
    public void setPrettyIndent(int i) {
        this.options.setPrettyIndent(i);
    }

    @Override
    public boolean isIgnoreComments() {
        return this.options.isIgnoreComments();
    }

    @Override
    public boolean isIgnoreProcessingInstructions() {
        return this.options.isIgnoreProcessingInstructions();
    }

    @Override
    public boolean isIgnoreWhitespace() {
        return this.options.isIgnoreWhitespace();
    }

    @Override
    public boolean isPrettyPrinting() {
        return this.options.isPrettyPrinting();
    }

    @Override
    public int getPrettyIndent() {
        return this.options.getPrettyIndent();
    }

    private XMLLibImpl(Scriptable globalScope) {
        this.globalScope = globalScope;
    }

    @Deprecated
    QName qnamePrototype() {
        return this.qnamePrototype;
    }

    @Deprecated
    Scriptable globalScope() {
        return this.globalScope;
    }

    XmlProcessor getProcessor() {
        return this.options;
    }

    private void exportToScope(boolean sealed) {
        this.xmlPrototype = this.newXML(XmlNode.createText(this.options, ""));
        this.xmlListPrototype = this.newXMLList();
        this.namespacePrototype = Namespace.create(this.globalScope, null, XmlNode.Namespace.GLOBAL);
        this.qnamePrototype = QName.create(this, this.globalScope, null, XmlNode.QName.create(XmlNode.Namespace.create(""), ""));
        this.xmlPrototype.exportAsJSClass(sealed);
        this.xmlListPrototype.exportAsJSClass(sealed);
        this.namespacePrototype.exportAsJSClass(sealed);
        this.qnamePrototype.exportAsJSClass(sealed);
    }

    @Deprecated
    XMLName toAttributeName(Context cx, Object nameValue) {
        if (nameValue instanceof XMLName) {
            return (XMLName)nameValue;
        }
        if (nameValue instanceof QName) {
            return XMLName.create(((QName)nameValue).getDelegate(), true, false);
        }
        if (nameValue instanceof Boolean || nameValue instanceof Number || nameValue == Undefined.instance || nameValue == null) {
            throw XMLLibImpl.badXMLName(nameValue);
        }
        String localName = null;
        localName = nameValue instanceof String ? (String)nameValue : ScriptRuntime.toString(nameValue);
        if (localName != null && localName.equals("*")) {
            localName = null;
        }
        return XMLName.create(XmlNode.QName.create(XmlNode.Namespace.create(""), localName), true, false);
    }

    private static RuntimeException badXMLName(Object value) {
        String msg;
        if (value instanceof Number) {
            msg = "Can not construct XML name from number: ";
        } else if (value instanceof Boolean) {
            msg = "Can not construct XML name from boolean: ";
        } else if (value == Undefined.instance || value == null) {
            msg = "Can not construct XML name from ";
        } else {
            throw new IllegalArgumentException(value.toString());
        }
        return ScriptRuntime.typeError(msg + ScriptRuntime.toString(value));
    }

    XMLName toXMLNameFromString(Context cx, String name) {
        return XMLName.create(this.getDefaultNamespaceURI(cx), name);
    }

    XMLName toXMLName(Context cx, Object nameValue) {
        XMLName result;
        if (nameValue instanceof XMLName) {
            result = (XMLName)nameValue;
        } else if (nameValue instanceof QName) {
            QName qname = (QName)nameValue;
            result = XMLName.formProperty(qname.uri(), qname.localName());
        } else if (nameValue instanceof String) {
            result = this.toXMLNameFromString(cx, (String)nameValue);
        } else {
            if (nameValue instanceof Boolean || nameValue instanceof Number || nameValue == Undefined.instance || nameValue == null) {
                throw XMLLibImpl.badXMLName(nameValue);
            }
            String name = ScriptRuntime.toString(nameValue);
            result = this.toXMLNameFromString(cx, name);
        }
        return result;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    XMLName toXMLNameOrIndex(Context cx, Object value) {
        if (value instanceof XMLName) {
            return (XMLName)value;
        }
        if (value instanceof String) {
            String str = (String)value;
            long test = ScriptRuntime.testUint32String(str);
            if (test < 0L) return this.toXMLNameFromString(cx, str);
            ScriptRuntime.storeUint32Result(cx, test);
            return null;
        }
        if (value instanceof Number) {
            double d = ((Number)value).doubleValue();
            long l = (long)d;
            if ((double)l != d) throw XMLLibImpl.badXMLName(value);
            if (0L > l) throw XMLLibImpl.badXMLName(value);
            if (l > 0xFFFFFFFFL) throw XMLLibImpl.badXMLName(value);
            ScriptRuntime.storeUint32Result(cx, l);
            return null;
        }
        if (value instanceof QName) {
            long test;
            QName qname = (QName)value;
            String uri = qname.uri();
            boolean number = false;
            XMLName result = null;
            if (uri != null && uri.length() == 0 && (test = ScriptRuntime.testUint32String(uri)) >= 0L) {
                ScriptRuntime.storeUint32Result(cx, test);
                number = true;
            }
            if (number) return result;
            return XMLName.formProperty(uri, qname.localName());
        }
        if (value instanceof Boolean) throw XMLLibImpl.badXMLName(value);
        if (value == Undefined.instance) throw XMLLibImpl.badXMLName(value);
        if (value == null) {
            throw XMLLibImpl.badXMLName(value);
        }
        String str = ScriptRuntime.toString(value);
        long test = ScriptRuntime.testUint32String(str);
        if (test < 0L) return this.toXMLNameFromString(cx, str);
        ScriptRuntime.storeUint32Result(cx, test);
        return null;
    }

    Object addXMLObjects(Context cx, XMLObject obj1, XMLObject obj2) {
        XMLList listToAdd = this.newXMLList();
        if (obj1 instanceof XMLList) {
            XMLList list1 = (XMLList)obj1;
            if (list1.length() == 1) {
                listToAdd.addToList(list1.item(0));
            } else {
                listToAdd = this.newXMLListFrom(obj1);
            }
        } else {
            listToAdd.addToList(obj1);
        }
        if (obj2 instanceof XMLList) {
            XMLList list2 = (XMLList)obj2;
            for (int i = 0; i < list2.length(); ++i) {
                listToAdd.addToList(list2.item(i));
            }
        } else if (obj2 instanceof XML) {
            listToAdd.addToList(obj2);
        }
        return listToAdd;
    }

    private Ref xmlPrimaryReference(Context cx, XMLName xmlName, Scriptable scope) {
        XMLObjectImpl xmlObj;
        block2: {
            XMLObjectImpl firstXml = null;
            do {
                if (!(scope instanceof XMLWithScope)) continue;
                xmlObj = (XMLObjectImpl)scope.getPrototype();
                if (xmlObj.hasXMLProperty(xmlName)) break block2;
                if (firstXml != null) continue;
                firstXml = xmlObj;
            } while ((scope = scope.getParentScope()) != null);
            xmlObj = firstXml;
        }
        if (xmlObj != null) {
            xmlName.initXMLObject(xmlObj);
        }
        return xmlName;
    }

    Namespace castToNamespace(Context cx, Object namespaceObj) {
        return this.namespacePrototype.castToNamespace(namespaceObj);
    }

    private String getDefaultNamespaceURI(Context cx) {
        return this.getDefaultNamespace(cx).uri();
    }

    Namespace newNamespace(String uri) {
        return this.namespacePrototype.newNamespace(uri);
    }

    Namespace getDefaultNamespace(Context cx) {
        if (cx == null && (cx = Context.getCurrentContext()) == null) {
            return this.namespacePrototype;
        }
        Object ns = ScriptRuntime.searchDefaultNamespace(cx);
        if (ns == null) {
            return this.namespacePrototype;
        }
        if (ns instanceof Namespace) {
            return (Namespace)ns;
        }
        return this.namespacePrototype;
    }

    Namespace[] createNamespaces(XmlNode.Namespace[] declarations) {
        Namespace[] rv = new Namespace[declarations.length];
        for (int i = 0; i < declarations.length; ++i) {
            rv[i] = this.namespacePrototype.newNamespace(declarations[i].getPrefix(), declarations[i].getUri());
        }
        return rv;
    }

    QName constructQName(Context cx, Object namespace, Object name) {
        return this.qnamePrototype.constructQName(this, cx, namespace, name);
    }

    QName newQName(String uri, String localName, String prefix) {
        return this.qnamePrototype.newQName(this, uri, localName, prefix);
    }

    QName constructQName(Context cx, Object nameValue) {
        return this.qnamePrototype.constructQName(this, cx, nameValue);
    }

    QName castToQName(Context cx, Object qnameValue) {
        return this.qnamePrototype.castToQName(this, cx, qnameValue);
    }

    QName newQName(XmlNode.QName qname) {
        return QName.create(this, this.globalScope, this.qnamePrototype, qname);
    }

    XML newXML(XmlNode node) {
        return new XML(this, this.globalScope, this.xmlPrototype, node);
    }

    final XML newXMLFromJs(Object inputObject) {
        String frag = inputObject == null || inputObject == Undefined.instance ? "" : (inputObject instanceof XMLObjectImpl ? ((XMLObjectImpl)inputObject).toXMLString() : ScriptRuntime.toString(inputObject));
        if (frag.trim().startsWith("<>")) {
            throw ScriptRuntime.typeError("Invalid use of XML object anonymous tags <></>.");
        }
        if (frag.indexOf("<") == -1) {
            return this.newXML(XmlNode.createText(this.options, frag));
        }
        return this.parse(frag);
    }

    private XML parse(String frag) {
        try {
            return this.newXML(XmlNode.createElement(this.options, this.getDefaultNamespaceURI(Context.getCurrentContext()), frag));
        } catch (SAXException e) {
            throw ScriptRuntime.typeError("Cannot parse XML: " + e.getMessage());
        }
    }

    final XML ecmaToXml(Object object) {
        if (object == null || object == Undefined.instance) {
            throw ScriptRuntime.typeError("Cannot convert " + object + " to XML");
        }
        if (object instanceof XML) {
            return (XML)object;
        }
        if (object instanceof XMLList) {
            XMLList list = (XMLList)object;
            if (list.getXML() != null) {
                return list.getXML();
            }
            throw ScriptRuntime.typeError("Cannot convert list of >1 element to XML");
        }
        if (object instanceof Wrapper) {
            object = ((Wrapper)object).unwrap();
        }
        if (object instanceof Node) {
            Node node = (Node)object;
            return this.newXML(XmlNode.createElementFromNode(node));
        }
        String s = ScriptRuntime.toString(object);
        if (s.length() > 0 && s.charAt(0) == '<') {
            return this.parse(s);
        }
        return this.newXML(XmlNode.createText(this.options, s));
    }

    final XML newTextElementXML(XmlNode reference, XmlNode.QName qname, String value) {
        return this.newXML(XmlNode.newElementWithText(this.options, reference, qname, value));
    }

    XMLList newXMLList() {
        return new XMLList(this, this.globalScope, this.xmlListPrototype);
    }

    final XMLList newXMLListFrom(Object inputObject) {
        XMLList rv = this.newXMLList();
        if (inputObject == null || inputObject instanceof Undefined) {
            return rv;
        }
        if (inputObject instanceof XML) {
            XML xml = (XML)inputObject;
            rv.getNodeList().add(xml);
            return rv;
        }
        if (inputObject instanceof XMLList) {
            XMLList xmll = (XMLList)inputObject;
            rv.getNodeList().add(xmll.getNodeList());
            return rv;
        }
        String frag = ScriptRuntime.toString(inputObject).trim();
        if (!frag.startsWith("<>")) {
            frag = "<>" + frag + "</>";
        }
        if (!(frag = "<fragment>" + frag.substring(2)).endsWith("</>")) {
            throw ScriptRuntime.typeError("XML with anonymous tag missing end anonymous tag");
        }
        frag = frag.substring(0, frag.length() - 3) + "</fragment>";
        XML orgXML = this.newXMLFromJs(frag);
        XMLList children = orgXML.children();
        for (int i = 0; i < children.getNodeList().length(); ++i) {
            rv.getNodeList().add((XML)children.item(i).copy());
        }
        return rv;
    }

    XmlNode.QName toNodeQName(Context cx, Object namespaceValue, Object nameValue) {
        String localName;
        if (nameValue instanceof QName) {
            QName qname = (QName)nameValue;
            localName = qname.localName();
        } else {
            localName = ScriptRuntime.toString(nameValue);
        }
        XmlNode.Namespace ns = namespaceValue == Undefined.instance ? ("*".equals(localName) ? null : this.getDefaultNamespace(cx).getDelegate()) : (namespaceValue == null ? null : (namespaceValue instanceof Namespace ? ((Namespace)namespaceValue).getDelegate() : this.namespacePrototype.constructNamespace(namespaceValue).getDelegate()));
        if (localName != null && localName.equals("*")) {
            localName = null;
        }
        return XmlNode.QName.create(ns, localName);
    }

    XmlNode.QName toNodeQName(Context cx, String name, boolean attribute) {
        XmlNode.Namespace defaultNamespace = this.getDefaultNamespace(cx).getDelegate();
        if (name != null && name.equals("*")) {
            return XmlNode.QName.create(null, null);
        }
        if (attribute) {
            return XmlNode.QName.create(XmlNode.Namespace.GLOBAL, name);
        }
        return XmlNode.QName.create(defaultNamespace, name);
    }

    XmlNode.QName toNodeQName(Context cx, Object nameValue, boolean attribute) {
        if (nameValue instanceof XMLName) {
            return ((XMLName)nameValue).toQname();
        }
        if (nameValue instanceof QName) {
            QName qname = (QName)nameValue;
            return qname.getDelegate();
        }
        if (nameValue instanceof Boolean || nameValue instanceof Number || nameValue == Undefined.instance || nameValue == null) {
            throw XMLLibImpl.badXMLName(nameValue);
        }
        String local = null;
        local = nameValue instanceof String ? (String)nameValue : ScriptRuntime.toString(nameValue);
        return this.toNodeQName(cx, local, attribute);
    }

    @Override
    public boolean isXMLName(Context _cx, Object nameObj) {
        return XMLName.accept(nameObj);
    }

    @Override
    public Object toDefaultXmlNamespace(Context cx, Object uriValue) {
        return this.namespacePrototype.constructNamespace(uriValue);
    }

    @Override
    public String escapeTextValue(Object o) {
        return this.options.escapeTextValue(o);
    }

    @Override
    public String escapeAttributeValue(Object o) {
        return this.options.escapeAttributeValue(o);
    }

    @Override
    public Ref nameRef(Context cx, Object name, Scriptable scope, int memberTypeFlags) {
        if ((memberTypeFlags & 2) == 0) {
            throw Kit.codeBug();
        }
        XMLName xmlName = this.toAttributeName(cx, name);
        return this.xmlPrimaryReference(cx, xmlName, scope);
    }

    @Override
    public Ref nameRef(Context cx, Object namespace, Object name, Scriptable scope, int memberTypeFlags) {
        XMLName xmlName = XMLName.create(this.toNodeQName(cx, namespace, name), false, false);
        if ((memberTypeFlags & 2) != 0 && !xmlName.isAttributeName()) {
            xmlName.setAttributeName();
        }
        return this.xmlPrimaryReference(cx, xmlName, scope);
    }
}

