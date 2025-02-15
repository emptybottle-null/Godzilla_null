/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.validator;

import java.math.BigDecimal;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.GDate;
import org.apache.xmlbeans.GDuration;
import org.apache.xmlbeans.SchemaAttributeModel;
import org.apache.xmlbeans.SchemaLocalAttribute;
import org.apache.xmlbeans.SchemaLocalElement;
import org.apache.xmlbeans.SchemaParticle;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.validator.ValidatingXMLStreamReader;

public class ValidatingInfoXMLStreamReader
extends ValidatingXMLStreamReader
implements XMLStreamReader {
    private int _attCount = -1;
    private int _attIndex = 0;

    public int nextWithAttributes() throws XMLStreamException {
        if (this._attIndex < this._attCount) {
            this.validate_attribute(this._attIndex);
            ++this._attIndex;
            return 10;
        }
        return this.next();
    }

    @Override
    protected void validate_attributes(int attCount) {
        this._attCount = attCount;
        this._attIndex = 0;
    }

    public SchemaType getCurrentElementSchemaType() {
        return this._validator == null ? null : this._validator.getCurrentElementSchemaType();
    }

    public SchemaLocalElement getCurrentElement() {
        return this._validator == null ? null : this._validator.getCurrentElement();
    }

    public SchemaParticle getCurrentWildcardElement() {
        return this._validator == null ? null : this._validator.getCurrentWildcardElement();
    }

    public SchemaLocalAttribute getCurrentAttribute() {
        return this._validator == null ? null : this._validator.getCurrentAttribute();
    }

    public SchemaAttributeModel getCurrentWildcardAttribute() {
        return this._validator == null ? null : this._validator.getCurrentWildcardAttribute();
    }

    public String getStringValue() {
        return this._validator == null ? null : this._validator.getStringValue();
    }

    public BigDecimal getDecimalValue() {
        return this._validator == null ? null : this._validator.getDecimalValue();
    }

    public boolean getBooleanValue() {
        return this._validator == null ? false : this._validator.getBooleanValue();
    }

    public float getFloatValue() {
        return this._validator == null ? 0.0f : this._validator.getFloatValue();
    }

    public double getDoubleValue() {
        return this._validator == null ? 0.0 : this._validator.getDoubleValue();
    }

    public QName getQNameValue() {
        return this._validator == null ? null : this._validator.getQNameValue();
    }

    public GDate getGDateValue() {
        return this._validator == null ? null : this._validator.getGDateValue();
    }

    public GDuration getGDurationValue() {
        return this._validator == null ? null : this._validator.getGDurationValue();
    }

    public byte[] getByteArrayValue() {
        return this._validator == null ? null : this._validator.getByteArrayValue();
    }

    public List getListValue() {
        return this._validator == null ? null : this._validator.getListValue();
    }

    public List getListTypes() {
        return this._validator == null ? null : this._validator.getListTypes();
    }

    public SchemaType getUnionType() {
        return this._validator == null ? null : this._validator.getUnionType();
    }
}

