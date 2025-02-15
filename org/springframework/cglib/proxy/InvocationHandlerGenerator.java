/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.proxy;

import java.util.List;
import org.springframework.asm.Type;
import org.springframework.cglib.core.Block;
import org.springframework.cglib.core.ClassEmitter;
import org.springframework.cglib.core.CodeEmitter;
import org.springframework.cglib.core.EmitUtils;
import org.springframework.cglib.core.MethodInfo;
import org.springframework.cglib.core.Signature;
import org.springframework.cglib.core.TypeUtils;
import org.springframework.cglib.proxy.CallbackGenerator;

class InvocationHandlerGenerator
implements CallbackGenerator {
    public static final InvocationHandlerGenerator INSTANCE = new InvocationHandlerGenerator();
    private static final Type INVOCATION_HANDLER = TypeUtils.parseType("org.springframework.cglib.proxy.InvocationHandler");
    private static final Type UNDECLARED_THROWABLE_EXCEPTION = TypeUtils.parseType("org.springframework.cglib.proxy.UndeclaredThrowableException");
    private static final Type METHOD = TypeUtils.parseType("java.lang.reflect.Method");
    private static final Signature INVOKE = TypeUtils.parseSignature("Object invoke(Object, java.lang.reflect.Method, Object[])");

    InvocationHandlerGenerator() {
    }

    public void generate(ClassEmitter ce, CallbackGenerator.Context context, List methods) {
        for (MethodInfo method : methods) {
            Signature impl = context.getImplSignature(method);
            ce.declare_field(26, impl.getName(), METHOD, null);
            CodeEmitter e = context.beginMethod(ce, method);
            Block handler = e.begin_block();
            context.emitCallback(e, context.getIndex(method));
            e.load_this();
            e.getfield(impl.getName());
            e.create_arg_array();
            e.invoke_interface(INVOCATION_HANDLER, INVOKE);
            e.unbox(method.getSignature().getReturnType());
            e.return_value();
            handler.end();
            EmitUtils.wrap_undeclared_throwable(e, handler, method.getExceptionTypes(), UNDECLARED_THROWABLE_EXCEPTION);
            e.end_method();
        }
    }

    public void generateStatic(CodeEmitter e, CallbackGenerator.Context context, List methods) {
        for (MethodInfo method : methods) {
            EmitUtils.load_method(e, method);
            e.putfield(context.getImplSignature(method).getName());
        }
    }
}

