/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.transform.impl;

import org.springframework.asm.Type;
import org.springframework.cglib.core.CodeEmitter;
import org.springframework.cglib.core.Constants;
import org.springframework.cglib.core.Signature;
import org.springframework.cglib.core.TypeUtils;
import org.springframework.cglib.transform.ClassEmitterTransformer;

public class AccessFieldTransformer
extends ClassEmitterTransformer {
    private Callback callback;

    public AccessFieldTransformer(Callback callback) {
        this.callback = callback;
    }

    public void declare_field(int access, String name, Type type, Object value) {
        super.declare_field(access, name, type, value);
        String property = TypeUtils.upperFirst(this.callback.getPropertyName(this.getClassType(), name));
        if (property != null) {
            CodeEmitter e = this.begin_method(1, new Signature("get" + property, type, Constants.TYPES_EMPTY), null);
            e.load_this();
            e.getfield(name);
            e.return_value();
            e.end_method();
            e = this.begin_method(1, new Signature("set" + property, Type.VOID_TYPE, new Type[]{type}), null);
            e.load_this();
            e.load_arg(0);
            e.putfield(name);
            e.return_value();
            e.end_method();
        }
    }

    public static interface Callback {
        public String getPropertyName(Type var1, String var2);
    }
}

