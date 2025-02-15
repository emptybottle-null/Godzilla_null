/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.schema;

import java.util.ArrayList;
import java.util.List;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;

public class XmlValueRef {
    XmlAnySimpleType _obj;
    SchemaType.Ref _typeref;
    Object _initVal;

    public XmlValueRef(XmlAnySimpleType xobj) {
        if (xobj == null) {
            throw new IllegalArgumentException();
        }
        this._obj = xobj;
    }

    XmlValueRef(SchemaType.Ref typeref, Object initVal) {
        if (typeref == null) {
            throw new IllegalArgumentException();
        }
        this._typeref = typeref;
        this._initVal = initVal;
    }

    synchronized XmlAnySimpleType get() {
        if (this._obj == null) {
            SchemaType type = this._typeref.get();
            if (type.getSimpleVariety() != 3) {
                this._obj = type.newValue(this._initVal);
            } else {
                ArrayList<XmlAnySimpleType> actualVals = new ArrayList<XmlAnySimpleType>();
                for (XmlValueRef ref : (List)this._initVal) {
                    actualVals.add(ref.get());
                }
                this._obj = type.newValue(actualVals);
            }
        }
        return this._obj;
    }
}

