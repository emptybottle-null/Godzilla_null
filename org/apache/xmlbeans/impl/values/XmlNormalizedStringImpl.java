/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlNormalizedString;
import org.apache.xmlbeans.impl.values.JavaStringHolderEx;

public class XmlNormalizedStringImpl
extends JavaStringHolderEx
implements XmlNormalizedString {
    public XmlNormalizedStringImpl() {
        super(XmlNormalizedString.type, false);
    }

    public XmlNormalizedStringImpl(SchemaType type, boolean complex) {
        super(type, complex);
    }
}

