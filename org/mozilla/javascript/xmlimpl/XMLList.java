/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.xmlimpl;

import java.util.ArrayList;
import org.mozilla.javascript.Callable;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.xml.XMLObject;
import org.mozilla.javascript.xmlimpl.XML;
import org.mozilla.javascript.xmlimpl.XMLLibImpl;
import org.mozilla.javascript.xmlimpl.XMLName;
import org.mozilla.javascript.xmlimpl.XMLObjectImpl;
import org.mozilla.javascript.xmlimpl.XmlNode;

class XMLList
extends XMLObjectImpl
implements Function {
    static final long serialVersionUID = -4543618751670781135L;
    private XmlNode.InternalList _annos = new XmlNode.InternalList();
    private XMLObjectImpl targetObject = null;
    private XmlNode.QName targetProperty = null;

    XMLList(XMLLibImpl lib, Scriptable scope, XMLObject prototype) {
        super(lib, scope, prototype);
    }

    XmlNode.InternalList getNodeList() {
        return this._annos;
    }

    void setTargets(XMLObjectImpl object, XmlNode.QName property) {
        this.targetObject = object;
        this.targetProperty = property;
    }

    private XML getXmlFromAnnotation(int index) {
        return this.getXML(this._annos, index);
    }

    @Override
    XML getXML() {
        if (this.length() == 1) {
            return this.getXmlFromAnnotation(0);
        }
        return null;
    }

    private void internalRemoveFromList(int index) {
        this._annos.remove(index);
    }

    void replace(int index, XML xml) {
        if (index < this.length()) {
            XmlNode.InternalList newAnnoList = new XmlNode.InternalList();
            newAnnoList.add(this._annos, 0, index);
            newAnnoList.add(xml);
            newAnnoList.add(this._annos, index + 1, this.length());
            this._annos = newAnnoList;
        }
    }

    private void insert(int index, XML xml) {
        if (index < this.length()) {
            XmlNode.InternalList newAnnoList = new XmlNode.InternalList();
            newAnnoList.add(this._annos, 0, index);
            newAnnoList.add(xml);
            newAnnoList.add(this._annos, index, this.length());
            this._annos = newAnnoList;
        }
    }

    @Override
    public String getClassName() {
        return "XMLList";
    }

    @Override
    public Object get(int index, Scriptable start) {
        if (index >= 0 && index < this.length()) {
            return this.getXmlFromAnnotation(index);
        }
        return Scriptable.NOT_FOUND;
    }

    @Override
    boolean hasXMLProperty(XMLName xmlName) {
        return this.getPropertyList(xmlName).length() > 0;
    }

    @Override
    public boolean has(int index, Scriptable start) {
        return 0 <= index && index < this.length();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    void putXMLProperty(XMLName xmlName, Object value) {
        if (value == null) {
            value = "null";
        } else if (value instanceof Undefined) {
            value = "undefined";
        }
        if (this.length() > 1) {
            throw ScriptRuntime.typeError("Assignment to lists with more than one item is not supported");
        }
        if (this.length() == 0) {
            if (this.targetObject == null || this.targetProperty == null || this.targetProperty.getLocalName() == null || this.targetProperty.getLocalName().length() <= 0) throw ScriptRuntime.typeError("Assignment to empty XMLList without targets not supported");
            XML xmlValue = this.newTextElementXML(null, this.targetProperty, null);
            this.addToList(xmlValue);
            if (xmlName.isAttributeName()) {
                this.setAttribute(xmlName, value);
            } else {
                XML xml = this.item(0);
                xml.putXMLProperty(xmlName, value);
                this.replace(0, this.item(0));
            }
            XMLName name2 = XMLName.formProperty(this.targetProperty.getNamespace().getUri(), this.targetProperty.getLocalName());
            this.targetObject.putXMLProperty(name2, this);
            this.replace(0, this.targetObject.getXML().getLastXmlChild());
            return;
        } else if (xmlName.isAttributeName()) {
            this.setAttribute(xmlName, value);
            return;
        } else {
            XML xml = this.item(0);
            xml.putXMLProperty(xmlName, value);
            this.replace(0, this.item(0));
        }
    }

    @Override
    Object getXMLProperty(XMLName name) {
        return this.getPropertyList(name);
    }

    private void replaceNode(XML xml, XML with) {
        xml.replaceWith(with);
    }

    @Override
    public void put(int index, Scriptable start, Object value) {
        XMLObject xmlValue;
        Object parent = Undefined.instance;
        if (value == null) {
            value = "null";
        } else if (value instanceof Undefined) {
            value = "undefined";
        }
        if (value instanceof XMLObject) {
            xmlValue = (XMLObject)value;
        } else if (this.targetProperty == null) {
            xmlValue = this.newXMLFromJs(value.toString());
        } else {
            xmlValue = this.item(index);
            if (xmlValue == null) {
                XML x = this.item(0);
                xmlValue = x == null ? this.newTextElementXML(null, this.targetProperty, null) : x.copy();
            }
            ((XML)xmlValue).setChildren(value);
        }
        parent = index < this.length() ? this.item(index).parent() : (this.length() == 0 ? (this.targetObject != null ? this.targetObject.getXML() : this.parent()) : this.parent());
        if (parent instanceof XML) {
            XML xmlParent = (XML)parent;
            if (index < this.length()) {
                XMLList list;
                XML xmlNode = this.getXmlFromAnnotation(index);
                if (xmlValue instanceof XML) {
                    this.replaceNode(xmlNode, (XML)xmlValue);
                    this.replace(index, xmlNode);
                } else if (xmlValue instanceof XMLList && (list = (XMLList)xmlValue).length() > 0) {
                    int lastIndexAdded = xmlNode.childIndex();
                    this.replaceNode(xmlNode, list.item(0));
                    this.replace(index, list.item(0));
                    for (int i = 1; i < list.length(); ++i) {
                        xmlParent.insertChildAfter(xmlParent.getXmlChild(lastIndexAdded), list.item(i));
                        ++lastIndexAdded;
                        this.insert(index + i, list.item(i));
                    }
                }
            } else {
                xmlParent.appendChild(xmlValue);
                this.addToList(xmlParent.getLastXmlChild());
            }
        } else if (index < this.length()) {
            XMLList list;
            XML xmlNode = this.getXML(this._annos, index);
            if (xmlValue instanceof XML) {
                this.replaceNode(xmlNode, (XML)xmlValue);
                this.replace(index, xmlNode);
            } else if (xmlValue instanceof XMLList && (list = (XMLList)xmlValue).length() > 0) {
                this.replaceNode(xmlNode, list.item(0));
                this.replace(index, list.item(0));
                for (int i = 1; i < list.length(); ++i) {
                    this.insert(index + i, list.item(i));
                }
            }
        } else {
            this.addToList(xmlValue);
        }
    }

    private XML getXML(XmlNode.InternalList _annos, int index) {
        if (index >= 0 && index < this.length()) {
            return this.xmlFromNode(_annos.item(index));
        }
        return null;
    }

    @Override
    void deleteXMLProperty(XMLName name) {
        for (int i = 0; i < this.length(); ++i) {
            XML xml = this.getXmlFromAnnotation(i);
            if (!xml.isElement()) continue;
            xml.deleteXMLProperty(name);
        }
    }

    @Override
    public void delete(int index) {
        if (index >= 0 && index < this.length()) {
            XML xml = this.getXmlFromAnnotation(index);
            xml.remove();
            this.internalRemoveFromList(index);
        }
    }

    @Override
    public Object[] getIds() {
        Object[] enumObjs;
        if (this.isPrototype()) {
            enumObjs = new Object[]{};
        } else {
            enumObjs = new Object[this.length()];
            for (int i = 0; i < enumObjs.length; ++i) {
                enumObjs[i] = i;
            }
        }
        return enumObjs;
    }

    public Object[] getIdsForDebug() {
        return this.getIds();
    }

    void remove() {
        int nLen = this.length();
        for (int i = nLen - 1; i >= 0; --i) {
            XML xml = this.getXmlFromAnnotation(i);
            if (xml == null) continue;
            xml.remove();
            this.internalRemoveFromList(i);
        }
    }

    XML item(int index) {
        return this._annos != null ? this.getXmlFromAnnotation(index) : this.createEmptyXML();
    }

    private void setAttribute(XMLName xmlName, Object value) {
        for (int i = 0; i < this.length(); ++i) {
            XML xml = this.getXmlFromAnnotation(i);
            xml.setAttribute(xmlName, value);
        }
    }

    void addToList(Object toAdd) {
        this._annos.addToList(toAdd);
    }

    @Override
    XMLList child(int index) {
        XMLList result = this.newXMLList();
        for (int i = 0; i < this.length(); ++i) {
            result.addToList(this.getXmlFromAnnotation(i).child(index));
        }
        return result;
    }

    @Override
    XMLList child(XMLName xmlName) {
        XMLList result = this.newXMLList();
        for (int i = 0; i < this.length(); ++i) {
            result.addToList(this.getXmlFromAnnotation(i).child(xmlName));
        }
        return result;
    }

    @Override
    void addMatches(XMLList rv, XMLName name) {
        for (int i = 0; i < this.length(); ++i) {
            this.getXmlFromAnnotation(i).addMatches(rv, name);
        }
    }

    @Override
    XMLList children() {
        ArrayList<XML> list = new ArrayList<XML>();
        for (int i = 0; i < this.length(); ++i) {
            XML xml = this.getXmlFromAnnotation(i);
            if (xml == null) continue;
            XMLList childList = xml.children();
            int cChildren = childList.length();
            for (int j = 0; j < cChildren; ++j) {
                list.add(childList.item(j));
            }
        }
        XMLList allChildren = this.newXMLList();
        int sz = list.size();
        for (int i = 0; i < sz; ++i) {
            allChildren.addToList(list.get(i));
        }
        return allChildren;
    }

    @Override
    XMLList comments() {
        XMLList result = this.newXMLList();
        for (int i = 0; i < this.length(); ++i) {
            XML xml = this.getXmlFromAnnotation(i);
            result.addToList(xml.comments());
        }
        return result;
    }

    @Override
    XMLList elements(XMLName name) {
        XMLList rv = this.newXMLList();
        for (int i = 0; i < this.length(); ++i) {
            XML xml = this.getXmlFromAnnotation(i);
            rv.addToList(xml.elements(name));
        }
        return rv;
    }

    @Override
    boolean contains(Object xml) {
        boolean result = false;
        for (int i = 0; i < this.length(); ++i) {
            XML member = this.getXmlFromAnnotation(i);
            if (!member.equivalentXml(xml)) continue;
            result = true;
            break;
        }
        return result;
    }

    @Override
    XMLObjectImpl copy() {
        XMLList result = this.newXMLList();
        for (int i = 0; i < this.length(); ++i) {
            XML xml = this.getXmlFromAnnotation(i);
            result.addToList(xml.copy());
        }
        return result;
    }

    @Override
    boolean hasOwnProperty(XMLName xmlName) {
        if (this.isPrototype()) {
            String property = xmlName.localName();
            return this.findPrototypeId(property) != 0;
        }
        return this.getPropertyList(xmlName).length() > 0;
    }

    @Override
    boolean hasComplexContent() {
        boolean complexContent;
        int length = this.length();
        if (length == 0) {
            complexContent = false;
        } else if (length == 1) {
            complexContent = this.getXmlFromAnnotation(0).hasComplexContent();
        } else {
            complexContent = false;
            for (int i = 0; i < length; ++i) {
                XML nextElement = this.getXmlFromAnnotation(i);
                if (!nextElement.isElement()) continue;
                complexContent = true;
                break;
            }
        }
        return complexContent;
    }

    @Override
    boolean hasSimpleContent() {
        if (this.length() == 0) {
            return true;
        }
        if (this.length() == 1) {
            return this.getXmlFromAnnotation(0).hasSimpleContent();
        }
        for (int i = 0; i < this.length(); ++i) {
            XML nextElement = this.getXmlFromAnnotation(i);
            if (!nextElement.isElement()) continue;
            return false;
        }
        return true;
    }

    @Override
    int length() {
        int result = 0;
        if (this._annos != null) {
            result = this._annos.length();
        }
        return result;
    }

    @Override
    void normalize() {
        for (int i = 0; i < this.length(); ++i) {
            this.getXmlFromAnnotation(i).normalize();
        }
    }

    @Override
    Object parent() {
        if (this.length() == 0) {
            return Undefined.instance;
        }
        XML candidateParent = null;
        for (int i = 0; i < this.length(); ++i) {
            Object currParent = this.getXmlFromAnnotation(i).parent();
            if (!(currParent instanceof XML)) {
                return Undefined.instance;
            }
            XML xml = (XML)currParent;
            if (i == 0) {
                candidateParent = xml;
                continue;
            }
            if (candidateParent.is(xml)) continue;
            return Undefined.instance;
        }
        return candidateParent;
    }

    @Override
    XMLList processingInstructions(XMLName xmlName) {
        XMLList result = this.newXMLList();
        for (int i = 0; i < this.length(); ++i) {
            XML xml = this.getXmlFromAnnotation(i);
            result.addToList(xml.processingInstructions(xmlName));
        }
        return result;
    }

    @Override
    boolean propertyIsEnumerable(Object name) {
        long index;
        if (name instanceof Integer) {
            index = ((Integer)name).intValue();
        } else if (name instanceof Number) {
            double x = ((Number)name).doubleValue();
            index = (long)x;
            if ((double)index != x) {
                return false;
            }
            if (index == 0L && 1.0 / x < 0.0) {
                return false;
            }
        } else {
            String s = ScriptRuntime.toString(name);
            index = ScriptRuntime.testUint32String(s);
        }
        return 0L <= index && index < (long)this.length();
    }

    @Override
    XMLList text() {
        XMLList result = this.newXMLList();
        for (int i = 0; i < this.length(); ++i) {
            result.addToList(this.getXmlFromAnnotation(i).text());
        }
        return result;
    }

    @Override
    public String toString() {
        if (this.hasSimpleContent()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < this.length(); ++i) {
                XML next = this.getXmlFromAnnotation(i);
                if (next.isComment() || next.isProcessingInstruction()) continue;
                sb.append(next.toString());
            }
            return sb.toString();
        }
        return this.toXMLString();
    }

    @Override
    String toSource(int indent) {
        return this.toXMLString();
    }

    @Override
    String toXMLString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.length(); ++i) {
            if (this.getProcessor().isPrettyPrinting() && i != 0) {
                sb.append('\n');
            }
            sb.append(this.getXmlFromAnnotation(i).toXMLString());
        }
        return sb.toString();
    }

    @Override
    Object valueOf() {
        return this;
    }

    @Override
    boolean equivalentXml(Object target) {
        XMLList otherList;
        boolean result = false;
        if (target instanceof Undefined && this.length() == 0) {
            result = true;
        } else if (this.length() == 1) {
            result = this.getXmlFromAnnotation(0).equivalentXml(target);
        } else if (target instanceof XMLList && (otherList = (XMLList)target).length() == this.length()) {
            result = true;
            for (int i = 0; i < this.length(); ++i) {
                if (this.getXmlFromAnnotation(i).equivalentXml(otherList.getXmlFromAnnotation(i))) continue;
                result = false;
                break;
            }
        }
        return result;
    }

    private XMLList getPropertyList(XMLName name) {
        XMLList propertyList = this.newXMLList();
        XmlNode.QName qname = null;
        if (!name.isDescendants() && !name.isAttributeName()) {
            qname = name.toQname();
        }
        propertyList.setTargets(this, qname);
        for (int i = 0; i < this.length(); ++i) {
            propertyList.addToList(this.getXmlFromAnnotation(i).getPropertyList(name));
        }
        return propertyList;
    }

    private Object applyOrCall(boolean isApply, Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        String methodName;
        String string = methodName = isApply ? "apply" : "call";
        if (!(thisObj instanceof XMLList) || ((XMLList)thisObj).targetProperty == null) {
            throw ScriptRuntime.typeError1("msg.isnt.function", methodName);
        }
        return ScriptRuntime.applyOrCall(isApply, cx, scope, thisObj, args);
    }

    @Override
    protected Object jsConstructor(Context cx, boolean inNewExpr, Object[] args) {
        if (args.length == 0) {
            return this.newXMLList();
        }
        Object arg0 = args[0];
        if (!inNewExpr && arg0 instanceof XMLList) {
            return arg0;
        }
        return this.newXMLListFrom(arg0);
    }

    @Override
    public Scriptable getExtraMethodSource(Context cx) {
        if (this.length() == 1) {
            return this.getXmlFromAnnotation(0);
        }
        return null;
    }

    @Override
    public Object call(Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        XMLObject xmlObject;
        if (this.targetProperty == null) {
            throw ScriptRuntime.notFunctionError(this);
        }
        String methodName = this.targetProperty.getLocalName();
        boolean isApply = methodName.equals("apply");
        if (isApply || methodName.equals("call")) {
            return this.applyOrCall(isApply, cx, scope, thisObj, args);
        }
        if (!(thisObj instanceof XMLObject)) {
            throw ScriptRuntime.typeError1("msg.incompat.call", methodName);
        }
        Object func = null;
        Scriptable sobj = thisObj;
        while (sobj instanceof XMLObject && (func = (xmlObject = (XMLObject)sobj).getFunctionProperty(cx, methodName)) == Scriptable.NOT_FOUND) {
            sobj = xmlObject.getExtraMethodSource(cx);
            if (sobj == null) continue;
            thisObj = sobj;
            if (sobj instanceof XMLObject) continue;
            func = ScriptableObject.getProperty(sobj, methodName);
        }
        if (!(func instanceof Callable)) {
            throw ScriptRuntime.notFunctionError(thisObj, func, methodName);
        }
        return ((Callable)func).call(cx, scope, thisObj, args);
    }

    @Override
    public Scriptable construct(Context cx, Scriptable scope, Object[] args) {
        throw ScriptRuntime.typeError1("msg.not.ctor", "XMLList");
    }
}

