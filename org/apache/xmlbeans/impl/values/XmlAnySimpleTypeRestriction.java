/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlAnySimpleTypeImpl;

public class XmlAnySimpleTypeRestriction
extends XmlAnySimpleTypeImpl {
    private SchemaType _schemaType;

    public XmlAnySimpleTypeRestriction(SchemaType type, boolean complex) {
        this._schemaType = type;
        this.initComplexType(complex, false);
    }

    @Override
    public SchemaType schemaType() {
        return this._schemaType;
    }
}

