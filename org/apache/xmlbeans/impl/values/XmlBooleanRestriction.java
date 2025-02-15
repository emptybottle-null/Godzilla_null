/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.impl.values.JavaBooleanHolderEx;

public class XmlBooleanRestriction
extends JavaBooleanHolderEx
implements XmlBoolean {
    public XmlBooleanRestriction(SchemaType type, boolean complex) {
        super(type, complex);
    }
}

