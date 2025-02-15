/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlGYearMonth;
import org.apache.xmlbeans.impl.values.JavaGDateHolderEx;

public class XmlGYearMonthImpl
extends JavaGDateHolderEx
implements XmlGYearMonth {
    public XmlGYearMonthImpl() {
        super(XmlGYearMonth.type, false);
    }

    public XmlGYearMonthImpl(SchemaType type, boolean complex) {
        super(type, complex);
    }
}

