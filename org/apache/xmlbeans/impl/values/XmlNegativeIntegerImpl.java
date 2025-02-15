/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlNegativeInteger;
import org.apache.xmlbeans.impl.values.JavaIntegerHolderEx;

public class XmlNegativeIntegerImpl
extends JavaIntegerHolderEx
implements XmlNegativeInteger {
    public XmlNegativeIntegerImpl() {
        super(XmlNegativeInteger.type, false);
    }

    public XmlNegativeIntegerImpl(SchemaType type, boolean complex) {
        super(type, complex);
    }
}

