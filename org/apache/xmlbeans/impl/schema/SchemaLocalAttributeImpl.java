/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.schema;

import java.math.BigInteger;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaAnnotation;
import org.apache.xmlbeans.SchemaLocalAttribute;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.XmlValueRef;
import org.apache.xmlbeans.impl.values.NamespaceContext;
import org.apache.xmlbeans.soap.SOAPArrayType;
import org.apache.xmlbeans.soap.SchemaWSDLArrayType;

public class SchemaLocalAttributeImpl
implements SchemaLocalAttribute,
SchemaWSDLArrayType {
    private String _defaultText;
    XmlValueRef _defaultValue;
    private boolean _isFixed;
    private boolean _isDefault;
    private QName _xmlName;
    private SchemaType.Ref _typeref;
    private SOAPArrayType _wsdlArrayType;
    private int _use;
    private SchemaAnnotation _annotation;
    protected XmlObject _parseObject;
    private Object _userData;

    public void init(QName name, SchemaType.Ref typeref, int use, String deftext, XmlObject parseObject, XmlValueRef defvalue, boolean isFixed, SOAPArrayType wsdlArray, SchemaAnnotation ann, Object userData) {
        if (this._xmlName != null || this._typeref != null) {
            throw new IllegalStateException("Already initialized");
        }
        this._use = use;
        this._typeref = typeref;
        this._defaultText = deftext;
        this._parseObject = parseObject;
        this._defaultValue = defvalue;
        this._isDefault = deftext != null;
        this._isFixed = isFixed;
        this._xmlName = name;
        this._wsdlArrayType = wsdlArray;
        this._annotation = ann;
        this._userData = userData;
    }

    public boolean isTypeResolved() {
        return this._typeref != null;
    }

    public void resolveTypeRef(SchemaType.Ref typeref) {
        if (this._typeref != null) {
            throw new IllegalStateException();
        }
        this._typeref = typeref;
    }

    @Override
    public int getUse() {
        return this._use;
    }

    @Override
    public QName getName() {
        return this._xmlName;
    }

    @Override
    public String getDefaultText() {
        return this._defaultText;
    }

    @Override
    public boolean isDefault() {
        return this._isDefault;
    }

    @Override
    public boolean isFixed() {
        return this._isFixed;
    }

    @Override
    public boolean isAttribute() {
        return true;
    }

    @Override
    public SchemaAnnotation getAnnotation() {
        return this._annotation;
    }

    @Override
    public SchemaType getType() {
        return this._typeref.get();
    }

    public SchemaType.Ref getTypeRef() {
        return this._typeref;
    }

    @Override
    public BigInteger getMinOccurs() {
        return this._use == 3 ? BigInteger.ONE : BigInteger.ZERO;
    }

    @Override
    public BigInteger getMaxOccurs() {
        return this._use == 1 ? BigInteger.ZERO : BigInteger.ONE;
    }

    @Override
    public boolean isNillable() {
        return false;
    }

    @Override
    public SOAPArrayType getWSDLArrayType() {
        return this._wsdlArrayType;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlAnySimpleType getDefaultValue() {
        if (this._defaultValue != null) {
            return this._defaultValue.get();
        }
        if (this._defaultText != null && XmlAnySimpleType.type.isAssignableFrom(this.getType())) {
            if (this._parseObject != null) {
                try {
                    NamespaceContext.push(new NamespaceContext(this._parseObject));
                    XmlAnySimpleType xmlAnySimpleType = this.getType().newValue(this._defaultText);
                    return xmlAnySimpleType;
                } finally {
                    NamespaceContext.pop();
                }
            }
            return this.getType().newValue(this._defaultText);
        }
        return null;
    }

    public void setDefaultValue(XmlValueRef defaultRef) {
        this._defaultValue = defaultRef;
    }

    @Override
    public Object getUserData() {
        return this._userData;
    }
}

