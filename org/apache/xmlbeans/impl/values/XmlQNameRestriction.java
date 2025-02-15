/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.impl.values.JavaQNameHolderEx;

public class XmlQNameRestriction
extends JavaQNameHolderEx
implements XmlQName {
    public XmlQNameRestriction(SchemaType type, boolean complex) {
        super(type, complex);
    }
}

