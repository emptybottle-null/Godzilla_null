/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlInteger;
import org.apache.xmlbeans.impl.values.JavaIntegerHolderEx;

public class XmlIntegerRestriction
extends JavaIntegerHolderEx
implements XmlInteger {
    public XmlIntegerRestriction(SchemaType type, boolean complex) {
        super(type, complex);
    }
}

