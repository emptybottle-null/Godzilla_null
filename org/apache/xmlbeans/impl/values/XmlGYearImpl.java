/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlGYear;
import org.apache.xmlbeans.impl.values.JavaGDateHolderEx;

public class XmlGYearImpl
extends JavaGDateHolderEx
implements XmlGYear {
    public XmlGYearImpl() {
        super(XmlGYear.type, false);
    }

    public XmlGYearImpl(SchemaType type, boolean complex) {
        super(type, complex);
    }
}

