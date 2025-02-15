/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlUnsignedLong;
import org.apache.xmlbeans.impl.values.JavaIntegerHolderEx;

public class XmlUnsignedLongImpl
extends JavaIntegerHolderEx
implements XmlUnsignedLong {
    public XmlUnsignedLongImpl() {
        super(XmlUnsignedLong.type, false);
    }

    public XmlUnsignedLongImpl(SchemaType type, boolean complex) {
        super(type, complex);
    }
}

