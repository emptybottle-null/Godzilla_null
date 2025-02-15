/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.BuiltinSchemaTypeSystem;
import org.apache.xmlbeans.impl.values.XmlQNameImpl;

public abstract class JavaNotationHolder
extends XmlQNameImpl {
    @Override
    public SchemaType schemaType() {
        return BuiltinSchemaTypeSystem.ST_NOTATION;
    }
}

