/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlGMonth;
import org.apache.xmlbeans.impl.values.JavaGDateHolderEx;

public class XmlGMonthImpl
extends JavaGDateHolderEx
implements XmlGMonth {
    public XmlGMonthImpl() {
        super(XmlGMonth.type, false);
    }

    public XmlGMonthImpl(SchemaType type, boolean complex) {
        super(type, complex);
    }
}

