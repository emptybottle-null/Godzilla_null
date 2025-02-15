/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlByte;
import org.apache.xmlbeans.impl.values.JavaIntHolderEx;

public class XmlByteImpl
extends JavaIntHolderEx
implements XmlByte {
    public XmlByteImpl() {
        super(XmlByte.type, false);
    }

    public XmlByteImpl(SchemaType type, boolean complex) {
        super(type, complex);
    }
}

