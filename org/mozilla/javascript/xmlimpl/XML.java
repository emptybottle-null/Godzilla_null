/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.xmlimpl;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.xml.XMLObject;
import org.mozilla.javascript.xmlimpl.Namespace;
import org.mozilla.javascript.xmlimpl.QName;
import org.mozilla.javascript.xmlimpl.XMLLibImpl;
import org.mozilla.javascript.xmlimpl.XMLList;
import org.mozilla.javascript.xmlimpl.XMLName;
import org.mozilla.javascript.xmlimpl.XMLObjectImpl;
import org.mozilla.javascript.xmlimpl.XmlNode;
import org.w3c.dom.Node;

class XML
extends XMLObjectImpl {
    static final long serialVersionUID = -630969919086449092L;
    private XmlNode node;

    XML(XMLLibImpl lib, Scriptable scope, XMLObject prototype, XmlNode node) {
        super(lib, scope, prototype);
        this.initialize(node);
    }

    void initialize(XmlNode node) {
        this.node = node;
        this.node.setXml(this);
    }

    @Override
    final XML getXML() {
        return this;
    }

    void replaceWith(XML value) {
        if (this.node.parent() != null) {
            this.node.replaceWith(value.node);
        } else {
            this.initialize(value.node);
        }
    }

    XML makeXmlFromString(XMLName name, String value) {
        try {
            return this.newTextElementXML(this.node, name.toQname(), value);
        } catch (Exception e) {
            throw ScriptRuntime.typeError(e.getMessage());
        }
    }

    XmlNode getAnnotation() {
        return this.node;
    }

    @Override
    public Object get(int index, Scriptable start) {
        if (index == 0) {
            return this;
        }
        return Scriptable.NOT_FOUND;
    }

    @Override
    public boolean has(int index, Scriptable start) {
        return index == 0;
    }

    @Override
    public void put(int index, Scriptable start, Object value) {
        throw ScriptRuntime.typeError("Assignment to indexed XML is not allowed");
    }

    @Override
    public Object[] getIds() {
        if (this.isPrototype()) {
            return new Object[0];
        }
        return new Object[]{0};
    }

    @Override
    public void delete(int index) {
        if (index == 0) {
            this.remove();
        }
    }

    @Override
    boolean hasXMLProperty(XMLName xmlName) {
        return this.getPropertyList(xmlName).length() > 0;
    }

    @Override
    Object getXMLProperty(XMLName xmlName) {
        return this.getPropertyList(xmlName);
    }

    XmlNode.QName getNodeQname() {
        return this.node.getQname();
    }

    XML[] getChildren() {
        if (!this.isElement()) {
            return null;
        }
        XmlNode[] children = this.node.getMatchingChildren(XmlNode.Filter.TRUE);
        XML[] rv = new XML[children.length];
        for (int i = 0; i < rv.length; ++i) {
            rv[i] = this.toXML(children[i]);
        }
        return rv;
    }

    XML[] getAttributes() {
        XmlNode[] attributes = this.node.getAttributes();
        XML[] rv = new XML[attributes.length];
        for (int i = 0; i < rv.length; ++i) {
            rv[i] = this.toXML(attributes[i]);
        }
        return rv;
    }

    XMLList getPropertyList(XMLName name) {
        return name.getMyValueOn(this);
    }

    @Override
    void deleteXMLProperty(XMLName name) {
        XMLList list = this.getPropertyList(name);
        for (int i = 0; i < list.length(); ++i) {
            list.item((int)i).node.deleteMe();
        }
    }

    @Override
    void putXMLProperty(XMLName xmlName, Object value) {
        if (!this.isPrototype()) {
            xmlName.setMyValueOn(this, value);
        }
    }

    @Override
    boolean hasOwnProperty(XMLName xmlName) {
        String property;
        boolean hasProperty = false;
        hasProperty = this.isPrototype() ? 0 != this.findPrototypeId(property = xmlName.localName()) : this.getPropertyList(xmlName).length() > 0;
        return hasProperty;
    }

    @Override
    protected Object jsConstructor(Context cx, boolean inNewExpr, Object[] args) {
        if (args.length == 0 || args[0] == null || args[0] == Undefined.instance) {
            args = new Object[]{""};
        }
        XML toXml = this.ecmaToXml(args[0]);
        if (inNewExpr) {
            return toXml.copy();
        }
        return toXml;
    }

    @Override
    public Scriptable getExtraMethodSource(Context cx) {
        if (this.hasSimpleContent()) {
            String src = this.toString();
            return ScriptRuntime.toObjectOrNull(cx, src);
        }
        return null;
    }

    void removeChild(int index) {
        this.node.removeChild(index);
    }

    @Override
    void normalize() {
        this.node.normalize();
    }

    private XML toXML(XmlNode node) {
        if (node.getXml() == null) {
            node.setXml(this.newXML(node));
        }
        return node.getXml();
    }

    void setAttribute(XMLName xmlName, Object value) {
        if (!this.isElement()) {
            throw new IllegalStateException("Can only set attributes on elements.");
        }
        if (xmlName.uri() == null && xmlName.localName().equals("*")) {
            throw ScriptRuntime.typeError("@* assignment not supported.");
        }
        this.node.setAttribute(xmlName.toQname(), ScriptRuntime.toString(value));
    }

    void remove() {
        this.node.deleteMe();
    }

    @Override
    void addMatches(XMLList rv, XMLName name) {
        name.addMatches(rv, this);
    }

    @Override
    XMLList elements(XMLName name) {
        XMLList rv = this.newXMLList();
        rv.setTargets(this, name.toQname());
        XmlNode[] elements = this.node.getMatchingChildren(XmlNode.Filter.ELEMENT);
        for (int i = 0; i < elements.length; ++i) {
            if (!name.matches(this.toXML(elements[i]))) continue;
            rv.addToList(this.toXML(elements[i]));
        }
        return rv;
    }

    @Override
    XMLList child(XMLName xmlName) {
        XMLList rv = this.newXMLList();
        XmlNode[] elements = this.node.getMatchingChildren(XmlNode.Filter.ELEMENT);
        for (int i = 0; i < elements.length; ++i) {
            if (!xmlName.matchesElement(elements[i].getQname())) continue;
            rv.addToList(this.toXML(elements[i]));
        }
        rv.setTargets(this, xmlName.toQname());
        return rv;
    }

    XML replace(XMLName xmlName, Object xml) {
        this.putXMLProperty(xmlName, xml);
        return this;
    }

    @Override
    XMLList children() {
        XMLList rv = this.newXMLList();
        XMLName all = XMLName.formStar();
        rv.setTargets(this, all.toQname());
        XmlNode[] children = this.node.getMatchingChildren(XmlNode.Filter.TRUE);
        for (int i = 0; i < children.length; ++i) {
            rv.addToList(this.toXML(children[i]));
        }
        return rv;
    }

    @Override
    XMLList child(int index) {
        XMLList result = this.newXMLList();
        result.setTargets(this, null);
        if (index >= 0 && index < this.node.getChildCount()) {
            result.addToList(this.getXmlChild(index));
        }
        return result;
    }

    XML getXmlChild(int index) {
        XmlNode child = this.node.getChild(index);
        if (child.getXml() == null) {
            child.setXml(this.newXML(child));
        }
        return child.getXml();
    }

    XML getLastXmlChild() {
        int pos = this.node.getChildCount() - 1;
        if (pos < 0) {
            return null;
        }
        return this.getXmlChild(pos);
    }

    int childIndex() {
        return this.node.getChildIndex();
    }

    @Override
    boolean contains(Object xml) {
        if (xml instanceof XML) {
            return this.equivalentXml(xml);
        }
        return false;
    }

    @Override
    boolean equivalentXml(Object target) {
        boolean result = false;
        if (target instanceof XML) {
            return this.node.toXmlString(this.getProcessor()).equals(((XML)target).node.toXmlString(this.getProcessor()));
        }
        if (target instanceof XMLList) {
            XMLList otherList = (XMLList)target;
            if (otherList.length() == 1) {
                result = this.equivalentXml(otherList.getXML());
            }
        } else if (this.hasSimpleContent()) {
            String otherStr = ScriptRuntime.toString(target);
            result = this.toString().equals(otherStr);
        }
        return result;
    }

    @Override
    XMLObjectImpl copy() {
        return this.newXML(this.node.copy());
    }

    @Override
    boolean hasSimpleContent() {
        if (this.isComment() || this.isProcessingInstruction()) {
            return false;
        }
        if (this.isText() || this.node.isAttributeType()) {
            return true;
        }
        return !this.node.hasChildElement();
    }

    @Override
    boolean hasComplexContent() {
        return !this.hasSimpleContent();
    }

    @Override
    int length() {
        return 1;
    }

    boolean is(XML other) {
        return this.node.isSameNode(other.node);
    }

    Object nodeKind() {
        return this.ecmaClass();
    }

    @Override
    Object parent() {
        XmlNode parent = this.node.parent();
        if (parent == null) {
            return null;
        }
        return this.newXML(this.node.parent());
    }

    @Override
    boolean propertyIsEnumerable(Object name) {
        double x;
        boolean result = name instanceof Integer ? (Integer)name == 0 : (name instanceof Number ? (x = ((Number)name).doubleValue()) == 0.0 && 1.0 / x > 0.0 : ScriptRuntime.toString(name).equals("0"));
        return result;
    }

    @Override
    Object valueOf() {
        return this;
    }

    @Override
    XMLList comments() {
        XMLList rv = this.newXMLList();
        this.node.addMatchingChildren(rv, XmlNode.Filter.COMMENT);
        return rv;
    }

    @Override
    XMLList text() {
        XMLList rv = this.newXMLList();
        this.node.addMatchingChildren(rv, XmlNode.Filter.TEXT);
        return rv;
    }

    @Override
    XMLList processingInstructions(XMLName xmlName) {
        XMLList rv = this.newXMLList();
        this.node.addMatchingChildren(rv, XmlNode.Filter.PROCESSING_INSTRUCTION(xmlName));
        return rv;
    }

    private XmlNode[] getNodesForInsert(Object value) {
        if (value instanceof XML) {
            return new XmlNode[]{((XML)value).node};
        }
        if (value instanceof XMLList) {
            XMLList list = (XMLList)value;
            XmlNode[] rv = new XmlNode[list.length()];
            for (int i = 0; i < list.length(); ++i) {
                rv[i] = list.item((int)i).node;
            }
            return rv;
        }
        return new XmlNode[]{XmlNode.createText(this.getProcessor(), ScriptRuntime.toString(value))};
    }

    XML replace(int index, Object xml) {
        XMLList xlChildToReplace = this.child(index);
        if (xlChildToReplace.length() > 0) {
            XML childToReplace = xlChildToReplace.item(0);
            this.insertChildAfter(childToReplace, xml);
            this.removeChild(index);
        }
        return this;
    }

    XML prependChild(Object xml) {
        if (this.node.isParentType()) {
            this.node.insertChildrenAt(0, this.getNodesForInsert(xml));
        }
        return this;
    }

    XML appendChild(Object xml) {
        if (this.node.isParentType()) {
            XmlNode[] nodes = this.getNodesForInsert(xml);
            this.node.insertChildrenAt(this.node.getChildCount(), nodes);
        }
        return this;
    }

    private int getChildIndexOf(XML child) {
        for (int i = 0; i < this.node.getChildCount(); ++i) {
            if (!this.node.getChild(i).isSameNode(child.node)) continue;
            return i;
        }
        return -1;
    }

    XML insertChildBefore(XML child, Object xml) {
        if (child == null) {
            this.appendChild(xml);
        } else {
            XmlNode[] toInsert = this.getNodesForInsert(xml);
            int index = this.getChildIndexOf(child);
            if (index != -1) {
                this.node.insertChildrenAt(index, toInsert);
            }
        }
        return this;
    }

    XML insertChildAfter(XML child, Object xml) {
        if (child == null) {
            this.prependChild(xml);
        } else {
            XmlNode[] toInsert = this.getNodesForInsert(xml);
            int index = this.getChildIndexOf(child);
            if (index != -1) {
                this.node.insertChildrenAt(index + 1, toInsert);
            }
        }
        return this;
    }

    XML setChildren(Object xml) {
        if (!this.isElement()) {
            return this;
        }
        while (this.node.getChildCount() > 0) {
            this.node.removeChild(0);
        }
        XmlNode[] toInsert = this.getNodesForInsert(xml);
        this.node.insertChildrenAt(0, toInsert);
        return this;
    }

    private void addInScopeNamespace(Namespace ns) {
        if (!this.isElement()) {
            return;
        }
        if (ns.prefix() != null) {
            if (ns.prefix().length() == 0 && ns.uri().length() == 0) {
                return;
            }
            if (this.node.getQname().getNamespace().getPrefix().equals(ns.prefix())) {
                this.node.invalidateNamespacePrefix();
            }
        } else {
            return;
        }
        this.node.declareNamespace(ns.prefix(), ns.uri());
    }

    Namespace[] inScopeNamespaces() {
        XmlNode.Namespace[] inScope = this.node.getInScopeNamespaces();
        return this.createNamespaces(inScope);
    }

    private XmlNode.Namespace adapt(Namespace ns) {
        if (ns.prefix() == null) {
            return XmlNode.Namespace.create(ns.uri());
        }
        return XmlNode.Namespace.create(ns.prefix(), ns.uri());
    }

    XML removeNamespace(Namespace ns) {
        if (!this.isElement()) {
            return this;
        }
        this.node.removeNamespace(this.adapt(ns));
        return this;
    }

    XML addNamespace(Namespace ns) {
        this.addInScopeNamespace(ns);
        return this;
    }

    QName name() {
        if (this.isText() || this.isComment()) {
            return null;
        }
        if (this.isProcessingInstruction()) {
            return this.newQName("", this.node.getQname().getLocalName(), null);
        }
        return this.newQName(this.node.getQname());
    }

    Namespace[] namespaceDeclarations() {
        XmlNode.Namespace[] declarations = this.node.getNamespaceDeclarations();
        return this.createNamespaces(declarations);
    }

    Namespace namespace(String prefix) {
        if (prefix == null) {
            return this.createNamespace(this.node.getNamespaceDeclaration());
        }
        return this.createNamespace(this.node.getNamespaceDeclaration(prefix));
    }

    String localName() {
        if (this.name() == null) {
            return null;
        }
        return this.name().localName();
    }

    void setLocalName(String localName) {
        if (this.isText() || this.isComment()) {
            return;
        }
        this.node.setLocalName(localName);
    }

    void setName(QName name) {
        if (this.isText() || this.isComment()) {
            return;
        }
        if (this.isProcessingInstruction()) {
            this.node.setLocalName(name.localName());
            return;
        }
        this.node.renameNode(name.getDelegate());
    }

    void setNamespace(Namespace ns) {
        if (this.isText() || this.isComment() || this.isProcessingInstruction()) {
            return;
        }
        this.setName(this.newQName(ns.uri(), this.localName(), ns.prefix()));
    }

    final String ecmaClass() {
        if (this.node.isTextType()) {
            return "text";
        }
        if (this.node.isAttributeType()) {
            return "attribute";
        }
        if (this.node.isCommentType()) {
            return "comment";
        }
        if (this.node.isProcessingInstructionType()) {
            return "processing-instruction";
        }
        if (this.node.isElementType()) {
            return "element";
        }
        throw new RuntimeException("Unrecognized type: " + this.node);
    }

    @Override
    public String getClassName() {
        return "XML";
    }

    private String ecmaValue() {
        return this.node.ecmaValue();
    }

    private String ecmaToString() {
        if (this.isAttribute() || this.isText()) {
            return this.ecmaValue();
        }
        if (this.hasSimpleContent()) {
            StringBuilder rv = new StringBuilder();
            for (int i = 0; i < this.node.getChildCount(); ++i) {
                XmlNode child = this.node.getChild(i);
                if (child.isProcessingInstructionType() || child.isCommentType()) continue;
                XML x = new XML(this.getLib(), this.getParentScope(), (XMLObject)this.getPrototype(), child);
                rv.append(x.toString());
            }
            return rv.toString();
        }
        return this.toXMLString();
    }

    @Override
    public String toString() {
        return this.ecmaToString();
    }

    @Override
    String toSource(int indent) {
        return this.toXMLString();
    }

    @Override
    String toXMLString() {
        return this.node.ecmaToXMLString(this.getProcessor());
    }

    final boolean isAttribute() {
        return this.node.isAttributeType();
    }

    final boolean isComment() {
        return this.node.isCommentType();
    }

    final boolean isText() {
        return this.node.isTextType();
    }

    final boolean isElement() {
        return this.node.isElementType();
    }

    final boolean isProcessingInstruction() {
        return this.node.isProcessingInstructionType();
    }

    Node toDomNode() {
        return this.node.toDomNode();
    }
}

