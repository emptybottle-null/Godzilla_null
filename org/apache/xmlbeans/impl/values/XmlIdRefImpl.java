/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlIDREF;
import org.apache.xmlbeans.impl.values.JavaStringHolderEx;

public class XmlIdRefImpl
extends JavaStringHolderEx
implements XmlIDREF {
    public XmlIdRefImpl() {
        super(XmlIDREF.type, false);
    }

    public XmlIdRefImpl(SchemaType type, boolean complex) {
        super(type, complex);
    }
}

