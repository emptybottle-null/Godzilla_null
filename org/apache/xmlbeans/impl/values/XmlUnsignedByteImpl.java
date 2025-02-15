/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlUnsignedByte;
import org.apache.xmlbeans.impl.values.JavaIntHolderEx;

public class XmlUnsignedByteImpl
extends JavaIntHolderEx
implements XmlUnsignedByte {
    public XmlUnsignedByteImpl() {
        super(XmlUnsignedByte.type, false);
    }

    public XmlUnsignedByteImpl(SchemaType type, boolean complex) {
        super(type, complex);
    }
}

