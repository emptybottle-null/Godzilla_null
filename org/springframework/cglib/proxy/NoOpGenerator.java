/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.proxy;

import java.util.List;
import org.springframework.cglib.core.ClassEmitter;
import org.springframework.cglib.core.CodeEmitter;
import org.springframework.cglib.core.EmitUtils;
import org.springframework.cglib.core.MethodInfo;
import org.springframework.cglib.core.TypeUtils;
import org.springframework.cglib.proxy.CallbackGenerator;

class NoOpGenerator
implements CallbackGenerator {
    public static final NoOpGenerator INSTANCE = new NoOpGenerator();

    NoOpGenerator() {
    }

    public void generate(ClassEmitter ce, CallbackGenerator.Context context, List methods) {
        for (MethodInfo method : methods) {
            if (!TypeUtils.isBridge(method.getModifiers()) && (!TypeUtils.isProtected(context.getOriginalModifiers(method)) || !TypeUtils.isPublic(method.getModifiers()))) continue;
            CodeEmitter e = EmitUtils.begin_method(ce, method);
            e.load_this();
            context.emitLoadArgsAndInvoke(e, method);
            e.return_value();
            e.end_method();
        }
    }

    public void generateStatic(CodeEmitter e, CallbackGenerator.Context context, List methods) {
    }
}

