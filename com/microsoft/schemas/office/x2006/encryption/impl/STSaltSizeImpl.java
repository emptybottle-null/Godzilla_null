/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.office.x2006.encryption.impl;

import com.microsoft.schemas.office.x2006.encryption.STSaltSize;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaIntHolderEx;

public class STSaltSizeImpl
extends JavaIntHolderEx
implements STSaltSize {
    private static final long serialVersionUID = 1L;

    public STSaltSizeImpl(SchemaType schemaType) {
        super(schemaType, false);
    }

    protected STSaltSizeImpl(SchemaType schemaType, boolean bl) {
        super(schemaType, bl);
    }
}

