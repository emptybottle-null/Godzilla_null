/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBase64Binary;
import org.apache.xmlbeans.impl.values.JavaBase64HolderEx;

public class XmlBase64BinaryRestriction
extends JavaBase64HolderEx
implements XmlBase64Binary {
    public XmlBase64BinaryRestriction(SchemaType type, boolean complex) {
        super(type, complex);
    }
}

