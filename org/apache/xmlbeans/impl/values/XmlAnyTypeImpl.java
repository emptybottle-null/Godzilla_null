/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class XmlAnyTypeImpl
extends XmlComplexContentImpl
implements XmlObject {
    public XmlAnyTypeImpl() {
        super(type);
    }

    public XmlAnyTypeImpl(SchemaType type) {
        super(type);
    }
}

