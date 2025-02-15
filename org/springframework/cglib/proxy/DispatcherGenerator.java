/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.proxy;

import java.util.List;
import org.springframework.asm.Type;
import org.springframework.cglib.core.ClassEmitter;
import org.springframework.cglib.core.CodeEmitter;
import org.springframework.cglib.core.MethodInfo;
import org.springframework.cglib.core.Signature;
import org.springframework.cglib.core.TypeUtils;
import org.springframework.cglib.proxy.CallbackGenerator;

class DispatcherGenerator
implements CallbackGenerator {
    public static final DispatcherGenerator INSTANCE = new DispatcherGenerator(false);
    public static final DispatcherGenerator PROXY_REF_INSTANCE = new DispatcherGenerator(true);
    private static final Type DISPATCHER = TypeUtils.parseType("org.springframework.cglib.proxy.Dispatcher");
    private static final Type PROXY_REF_DISPATCHER = TypeUtils.parseType("org.springframework.cglib.proxy.ProxyRefDispatcher");
    private static final Signature LOAD_OBJECT = TypeUtils.parseSignature("Object loadObject()");
    private static final Signature PROXY_REF_LOAD_OBJECT = TypeUtils.parseSignature("Object loadObject(Object)");
    private boolean proxyRef;

    private DispatcherGenerator(boolean proxyRef) {
        this.proxyRef = proxyRef;
    }

    public void generate(ClassEmitter ce, CallbackGenerator.Context context, List methods) {
        for (MethodInfo method : methods) {
            if (TypeUtils.isProtected(method.getModifiers())) continue;
            CodeEmitter e = context.beginMethod(ce, method);
            context.emitCallback(e, context.getIndex(method));
            if (this.proxyRef) {
                e.load_this();
                e.invoke_interface(PROXY_REF_DISPATCHER, PROXY_REF_LOAD_OBJECT);
            } else {
                e.invoke_interface(DISPATCHER, LOAD_OBJECT);
            }
            e.checkcast(method.getClassInfo().getType());
            e.load_args();
            e.invoke(method);
            e.return_value();
            e.end_method();
        }
    }

    public void generateStatic(CodeEmitter e, CallbackGenerator.Context context, List methods) {
    }
}

