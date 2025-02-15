/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx;
import org.apache.xmlbeans.impl.xb.xsdschema.TypeDerivationControl;

public class TypeDerivationControlImpl
extends JavaStringEnumerationHolderEx
implements TypeDerivationControl {
    private static final long serialVersionUID = 1L;

    public TypeDerivationControlImpl(SchemaType sType) {
        super(sType, false);
    }

    protected TypeDerivationControlImpl(SchemaType sType, boolean b) {
        super(sType, b);
    }
}

