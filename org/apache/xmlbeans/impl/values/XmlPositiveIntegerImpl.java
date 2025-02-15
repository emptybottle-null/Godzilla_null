/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlPositiveInteger;
import org.apache.xmlbeans.impl.values.JavaIntegerHolderEx;

public class XmlPositiveIntegerImpl
extends JavaIntegerHolderEx
implements XmlPositiveInteger {
    public XmlPositiveIntegerImpl() {
        super(XmlPositiveInteger.type, false);
    }

    public XmlPositiveIntegerImpl(SchemaType type, boolean complex) {
        super(type, complex);
    }
}

