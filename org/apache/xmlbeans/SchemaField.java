/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans;

import java.math.BigInteger;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;

public interface SchemaField {
    public QName getName();

    public boolean isAttribute();

    public boolean isNillable();

    public SchemaType getType();

    public BigInteger getMinOccurs();

    public BigInteger getMaxOccurs();

    public String getDefaultText();

    public XmlAnySimpleType getDefaultValue();

    public boolean isDefault();

    public boolean isFixed();

    public Object getUserData();
}

