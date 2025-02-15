/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlGMonthDay;
import org.apache.xmlbeans.impl.values.JavaGDateHolderEx;

public class XmlGMonthDayImpl
extends JavaGDateHolderEx
implements XmlGMonthDay {
    public XmlGMonthDayImpl() {
        super(XmlGMonthDay.type, false);
    }

    public XmlGMonthDayImpl(SchemaType type, boolean complex) {
        super(type, complex);
    }
}

