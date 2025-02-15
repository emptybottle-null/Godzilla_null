/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.common.ValidationContext;
import org.apache.xmlbeans.impl.schema.BuiltinSchemaTypeSystem;
import org.apache.xmlbeans.impl.values.NamespaceManager;
import org.apache.xmlbeans.impl.values.XmlObjectBase;

public abstract class JavaBooleanHolder
extends XmlObjectBase {
    private boolean _value;

    @Override
    public SchemaType schemaType() {
        return BuiltinSchemaTypeSystem.ST_BOOLEAN;
    }

    @Override
    protected String compute_text(NamespaceManager nsm) {
        return this._value ? "true" : "false";
    }

    @Override
    protected void set_text(String s) {
        this._value = JavaBooleanHolder.validateLexical(s, _voorVc);
    }

    public static boolean validateLexical(String v, ValidationContext context) {
        if (v.equals("true") || v.equals("1")) {
            return true;
        }
        if (v.equals("false") || v.equals("0")) {
            return false;
        }
        context.invalid("boolean", new Object[]{v});
        return false;
    }

    @Override
    protected void set_nil() {
        this._value = false;
    }

    @Override
    public boolean getBooleanValue() {
        this.check_dated();
        return this._value;
    }

    @Override
    protected void set_boolean(boolean f) {
        this._value = f;
    }

    @Override
    protected int compare_to(XmlObject i) {
        return this._value == ((XmlBoolean)i).getBooleanValue() ? 0 : 2;
    }

    @Override
    protected boolean equal_to(XmlObject i) {
        return this._value == ((XmlBoolean)i).getBooleanValue();
    }

    @Override
    protected int value_hash_code() {
        return this._value ? 957379554 : 676335975;
    }
}

