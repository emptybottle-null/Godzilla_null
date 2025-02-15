/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlUnionImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLang;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLangCode;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STString;

public class STLangImpl
extends XmlUnionImpl
implements STLang,
STLangCode,
STString {
    private static final long serialVersionUID = 1L;

    public STLangImpl(SchemaType schemaType) {
        super(schemaType, false);
    }

    protected STLangImpl(SchemaType schemaType, boolean bl) {
        super(schemaType, bl);
    }
}

