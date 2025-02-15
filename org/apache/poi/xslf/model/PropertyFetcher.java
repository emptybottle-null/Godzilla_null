/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.model;

import org.apache.poi.util.Internal;
import org.apache.poi.xslf.usermodel.XSLFShape;

@Internal
public abstract class PropertyFetcher<T> {
    private T _value;

    public abstract boolean fetch(XSLFShape var1);

    public T getValue() {
        return this._value;
    }

    public void setValue(T val) {
        this._value = val;
    }
}

