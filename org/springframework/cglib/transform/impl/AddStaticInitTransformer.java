/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.transform.impl;

import java.lang.reflect.Method;
import org.springframework.asm.Type;
import org.springframework.cglib.core.CodeEmitter;
import org.springframework.cglib.core.Constants;
import org.springframework.cglib.core.EmitUtils;
import org.springframework.cglib.core.MethodInfo;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.cglib.core.TypeUtils;
import org.springframework.cglib.transform.ClassEmitterTransformer;

public class AddStaticInitTransformer
extends ClassEmitterTransformer {
    private MethodInfo info;

    public AddStaticInitTransformer(Method classInit) {
        this.info = ReflectUtils.getMethodInfo(classInit);
        if (!TypeUtils.isStatic(this.info.getModifiers())) {
            throw new IllegalArgumentException(classInit + " is not static");
        }
        Type[] types = this.info.getSignature().getArgumentTypes();
        if (types.length != 1 || !types[0].equals(Constants.TYPE_CLASS) || !this.info.getSignature().getReturnType().equals(Type.VOID_TYPE)) {
            throw new IllegalArgumentException(classInit + " illegal signature");
        }
    }

    protected void init() {
        if (!TypeUtils.isInterface(this.getAccess())) {
            CodeEmitter e = this.getStaticHook();
            EmitUtils.load_class_this(e);
            e.invoke(this.info);
        }
    }
}

