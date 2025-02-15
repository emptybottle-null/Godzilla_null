/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlDate;
import org.apache.xmlbeans.impl.values.JavaGDateHolderEx;

public class XmlDateImpl
extends JavaGDateHolderEx
implements XmlDate {
    public XmlDateImpl() {
        super(XmlDate.type, false);
    }

    public XmlDateImpl(SchemaType type, boolean complex) {
        super(type, complex);
    }
}

