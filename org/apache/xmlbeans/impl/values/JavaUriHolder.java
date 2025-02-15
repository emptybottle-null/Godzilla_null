/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.common.ValidationContext;
import org.apache.xmlbeans.impl.schema.BuiltinSchemaTypeSystem;
import org.apache.xmlbeans.impl.values.NamespaceManager;
import org.apache.xmlbeans.impl.values.XmlObjectBase;

public abstract class JavaUriHolder
extends XmlObjectBase {
    private String _value;

    @Override
    public SchemaType schemaType() {
        return BuiltinSchemaTypeSystem.ST_ANY_URI;
    }

    @Override
    public String compute_text(NamespaceManager nsm) {
        return this._value == null ? "" : this._value;
    }

    @Override
    protected void set_text(String s) {
        if (this._validateOnSet()) {
            JavaUriHolder.validateLexical(s, _voorVc);
        }
        this._value = s;
    }

    public static void validateLexical(String v, ValidationContext context) {
        if (v.startsWith("##")) {
            context.invalid("anyURI", new Object[]{v});
        }
    }

    @Override
    protected void set_nil() {
        this._value = null;
    }

    @Override
    protected boolean equal_to(XmlObject obj) {
        return this._value.equals(((XmlAnyURI)obj).getStringValue());
    }

    @Override
    protected int value_hash_code() {
        return this._value.hashCode();
    }
}

