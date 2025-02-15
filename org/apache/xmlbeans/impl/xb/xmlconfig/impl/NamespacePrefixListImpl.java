/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xmlconfig.impl;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlListImpl;
import org.apache.xmlbeans.impl.xb.xmlconfig.NamespacePrefixList;

public class NamespacePrefixListImpl
extends XmlListImpl
implements NamespacePrefixList {
    private static final long serialVersionUID = 1L;

    public NamespacePrefixListImpl(SchemaType sType) {
        super(sType, false);
    }

    protected NamespacePrefixListImpl(SchemaType sType, boolean b) {
        super(sType, b);
    }
}

