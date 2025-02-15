/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.inst2xsd.util;

import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.impl.common.PrefixResolver;
import org.apache.xmlbeans.impl.inst2xsd.util.Attribute;
import org.apache.xmlbeans.impl.inst2xsd.util.Element;
import org.apache.xmlbeans.impl.values.XmlQNameImpl;

public class Type {
    private QName _name;
    private int _kind = 1;
    public static final int SIMPLE_TYPE_SIMPLE_CONTENT = 1;
    public static final int COMPLEX_TYPE_SIMPLE_CONTENT = 2;
    public static final int COMPLEX_TYPE_COMPLEX_CONTENT = 3;
    public static final int COMPLEX_TYPE_MIXED_CONTENT = 4;
    public static final int COMPLEX_TYPE_EMPTY_CONTENT = 5;
    private int _topParticleForComplexOrMixedContent = 1;
    public static final int PARTICLE_SEQUENCE = 1;
    public static final int PARTICLE_CHOICE_UNBOUNDED = 2;
    private List _elements;
    private List _attributes;
    private Type _extensionType;
    private boolean _isGlobal = false;
    private List _enumerationValues;
    private boolean _acceptsEnumerationValue = true;
    private List _enumerationQNames;

    protected Type() {
    }

    public static Type createNamedType(QName name, int contentType) {
        assert (name != null);
        Type type = new Type();
        type.setName(name);
        type.setContentType(contentType);
        return type;
    }

    public static Type createUnnamedType(int contentType) {
        assert (contentType == 1 || contentType == 2 || contentType == 3 || contentType == 4 || contentType == 5) : "Unknown contentType: " + contentType;
        Type type = new Type();
        type.setContentType(contentType);
        return type;
    }

    public QName getName() {
        return this._name;
    }

    public void setName(QName name) {
        this._name = name;
    }

    public int getContentType() {
        return this._kind;
    }

    public void setContentType(int kind) {
        this._kind = kind;
    }

    public List getElements() {
        this.ensureElements();
        return this._elements;
    }

    public void addElement(Element element) {
        this.ensureElements();
        this._elements.add(element);
    }

    public void setElements(List elements) {
        this.ensureElements();
        this._elements.clear();
        this._elements.addAll(elements);
    }

    private void ensureElements() {
        if (this._elements == null) {
            this._elements = new ArrayList();
        }
    }

    public List getAttributes() {
        this.ensureAttributes();
        return this._attributes;
    }

    public void addAttribute(Attribute attribute) {
        this.ensureAttributes();
        this._attributes.add(attribute);
    }

    public Attribute getAttribute(QName name) {
        for (int i = 0; i < this._attributes.size(); ++i) {
            Attribute attribute = (Attribute)this._attributes.get(i);
            if (!attribute.getName().equals(name)) continue;
            return attribute;
        }
        return null;
    }

    private void ensureAttributes() {
        if (this._attributes == null) {
            this._attributes = new ArrayList();
        }
    }

    public boolean isComplexType() {
        return this._kind == 3 || this._kind == 4 || this._kind == 2;
    }

    public boolean hasSimpleContent() {
        return this._kind == 1 || this._kind == 2;
    }

    public int getTopParticleForComplexOrMixedContent() {
        return this._topParticleForComplexOrMixedContent;
    }

    public void setTopParticleForComplexOrMixedContent(int topParticleForComplexOrMixedContent) {
        this._topParticleForComplexOrMixedContent = topParticleForComplexOrMixedContent;
    }

    public boolean isGlobal() {
        return this._isGlobal;
    }

    public void setGlobal(boolean isGlobal) {
        assert (isGlobal && this.getName() != null);
        this._isGlobal = isGlobal;
    }

    public Type getExtensionType() {
        return this._extensionType;
    }

    public void setExtensionType(Type extendedType) {
        assert (this._kind == 2) : "Extension used only for type which are COMPLEX_TYPE_SIMPLE_CONTENT";
        assert (extendedType != null && extendedType.getName() != null) : "Extended type must be a named type.";
        this._extensionType = extendedType;
    }

    public List getEnumerationValues() {
        this.ensureEnumerationValues();
        return this._enumerationValues;
    }

    public List getEnumerationQNames() {
        this.ensureEnumerationValues();
        return this._enumerationQNames;
    }

    public void addEnumerationValue(String enumerationValue, final XmlCursor xc) {
        assert (this._kind == 1 || this._kind == 2) : "Enumerations possible only on simple content";
        this.ensureEnumerationValues();
        if (this._acceptsEnumerationValue && !this._enumerationValues.contains(enumerationValue)) {
            this._enumerationValues.add(enumerationValue);
            if (this._name.equals(XmlQName.type.getName())) {
                PrefixResolver prefixResolver = new PrefixResolver(){

                    @Override
                    public String getNamespaceForPrefix(String prefix) {
                        return xc.namespaceForPrefix(prefix);
                    }
                };
                QName qname = XmlQNameImpl.validateLexical(enumerationValue, null, prefixResolver);
                assert (qname != null) : "The check for QName should allready have happened.";
                this._enumerationQNames.add(qname);
            }
        }
    }

    private void ensureEnumerationValues() {
        if (this._enumerationValues == null) {
            this._enumerationValues = new ArrayList();
            this._enumerationQNames = new ArrayList();
        }
    }

    public boolean isEnumeration() {
        return this._acceptsEnumerationValue && this._enumerationValues != null && this._enumerationValues.size() > 1;
    }

    public boolean isQNameEnumeration() {
        return this.isEnumeration() && this._name.equals(XmlQName.type.getName()) && this._enumerationQNames != null && this._enumerationQNames.size() > 1;
    }

    public void closeEnumeration() {
        this._acceptsEnumerationValue = false;
    }

    public String toString() {
        return "Type{_name = " + this._name + ", _extensionType = " + this._extensionType + ", _kind = " + this._kind + ", _elements = " + this._elements + ", _attributes = " + this._attributes + "}";
    }

    public void addAllEnumerationsFrom(Type from) {
        assert (this._kind == 1 || this._kind == 2) : "Enumerations possible only on simple content";
        this.ensureEnumerationValues();
        if (this._name.equals(XmlQName.type.getName()) && from._name.equals(XmlQName.type.getName())) {
            for (int i = 0; i < from.getEnumerationValues().size(); ++i) {
                String enumValue = (String)from.getEnumerationValues().get(i);
                QName enumQNameValue = (QName)from.getEnumerationQNames().get(i);
                if (!this._acceptsEnumerationValue || this._enumerationQNames.contains(enumQNameValue)) continue;
                this._enumerationValues.add(enumValue);
                this._enumerationQNames.add(enumQNameValue);
            }
        } else {
            for (int i = 0; i < from.getEnumerationValues().size(); ++i) {
                String enumValue = (String)from.getEnumerationValues().get(i);
                if (!this._acceptsEnumerationValue || this._enumerationValues.contains(enumValue)) continue;
                this._enumerationValues.add(enumValue);
            }
        }
    }
}

