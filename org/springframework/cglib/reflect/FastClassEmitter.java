/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.reflect;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.asm.ClassVisitor;
import org.springframework.asm.Label;
import org.springframework.asm.Type;
import org.springframework.cglib.core.Block;
import org.springframework.cglib.core.ClassEmitter;
import org.springframework.cglib.core.CodeEmitter;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.cglib.core.Constants;
import org.springframework.cglib.core.DuplicatesPredicate;
import org.springframework.cglib.core.EmitUtils;
import org.springframework.cglib.core.MethodInfo;
import org.springframework.cglib.core.MethodInfoTransformer;
import org.springframework.cglib.core.ObjectSwitchCallback;
import org.springframework.cglib.core.ProcessSwitchCallback;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.cglib.core.Signature;
import org.springframework.cglib.core.Transformer;
import org.springframework.cglib.core.TypeUtils;
import org.springframework.cglib.core.VisibilityPredicate;

class FastClassEmitter
extends ClassEmitter {
    private static final Signature CSTRUCT_CLASS = TypeUtils.parseConstructor("Class");
    private static final Signature METHOD_GET_INDEX = TypeUtils.parseSignature("int getIndex(String, Class[])");
    private static final Signature SIGNATURE_GET_INDEX = new Signature("getIndex", Type.INT_TYPE, new Type[]{Constants.TYPE_SIGNATURE});
    private static final Signature TO_STRING = TypeUtils.parseSignature("String toString()");
    private static final Signature CONSTRUCTOR_GET_INDEX = TypeUtils.parseSignature("int getIndex(Class[])");
    private static final Signature INVOKE = TypeUtils.parseSignature("Object invoke(int, Object, Object[])");
    private static final Signature NEW_INSTANCE = TypeUtils.parseSignature("Object newInstance(int, Object[])");
    private static final Signature GET_MAX_INDEX = TypeUtils.parseSignature("int getMaxIndex()");
    private static final Signature GET_SIGNATURE_WITHOUT_RETURN_TYPE = TypeUtils.parseSignature("String getSignatureWithoutReturnType(String, Class[])");
    private static final Type FAST_CLASS = TypeUtils.parseType("org.springframework.cglib.reflect.FastClass");
    private static final Type ILLEGAL_ARGUMENT_EXCEPTION = TypeUtils.parseType("IllegalArgumentException");
    private static final Type INVOCATION_TARGET_EXCEPTION = TypeUtils.parseType("java.lang.reflect.InvocationTargetException");
    private static final Type[] INVOCATION_TARGET_EXCEPTION_ARRAY = new Type[]{INVOCATION_TARGET_EXCEPTION};
    private static final int TOO_MANY_METHODS = 100;

    public FastClassEmitter(ClassVisitor v, String className, Class type) {
        super(v);
        Type base = Type.getType(type);
        this.begin_class(52, 1, className, FAST_CLASS, null, "<generated>");
        CodeEmitter e = this.begin_method(1, CSTRUCT_CLASS, null);
        e.load_this();
        e.load_args();
        e.super_invoke_constructor(CSTRUCT_CLASS);
        e.return_value();
        e.end_method();
        VisibilityPredicate vp = new VisibilityPredicate(type, false);
        List methods = ReflectUtils.addAllMethods(type, new ArrayList());
        CollectionUtils.filter(methods, vp);
        CollectionUtils.filter(methods, new DuplicatesPredicate());
        ArrayList constructors = new ArrayList(Arrays.asList(type.getDeclaredConstructors()));
        CollectionUtils.filter(constructors, vp);
        this.emitIndexBySignature(methods);
        this.emitIndexByClassArray(methods);
        e = this.begin_method(1, CONSTRUCTOR_GET_INDEX, null);
        e.load_args();
        List info = CollectionUtils.transform(constructors, MethodInfoTransformer.getInstance());
        EmitUtils.constructor_switch(e, info, new GetIndexCallback(e, info));
        e.end_method();
        e = this.begin_method(1, INVOKE, INVOCATION_TARGET_EXCEPTION_ARRAY);
        e.load_arg(1);
        e.checkcast(base);
        e.load_arg(0);
        FastClassEmitter.invokeSwitchHelper(e, methods, 2, base);
        e.end_method();
        e = this.begin_method(1, NEW_INSTANCE, INVOCATION_TARGET_EXCEPTION_ARRAY);
        e.new_instance(base);
        e.dup();
        e.load_arg(0);
        FastClassEmitter.invokeSwitchHelper(e, constructors, 1, base);
        e.end_method();
        e = this.begin_method(1, GET_MAX_INDEX, null);
        e.push(methods.size() - 1);
        e.return_value();
        e.end_method();
        this.end_class();
    }

    private void emitIndexBySignature(List methods) {
        CodeEmitter e = this.begin_method(1, SIGNATURE_GET_INDEX, null);
        List signatures = CollectionUtils.transform(methods, new Transformer(){

            public Object transform(Object obj) {
                return ReflectUtils.getSignature((Method)obj).toString();
            }
        });
        e.load_arg(0);
        e.invoke_virtual(Constants.TYPE_OBJECT, TO_STRING);
        this.signatureSwitchHelper(e, signatures);
        e.end_method();
    }

    private void emitIndexByClassArray(List methods) {
        CodeEmitter e = this.begin_method(1, METHOD_GET_INDEX, null);
        if (methods.size() > 100) {
            List signatures = CollectionUtils.transform(methods, new Transformer(){

                public Object transform(Object obj) {
                    String s = ReflectUtils.getSignature((Method)obj).toString();
                    return s.substring(0, s.lastIndexOf(41) + 1);
                }
            });
            e.load_args();
            e.invoke_static(FAST_CLASS, GET_SIGNATURE_WITHOUT_RETURN_TYPE);
            this.signatureSwitchHelper(e, signatures);
        } else {
            e.load_args();
            List info = CollectionUtils.transform(methods, MethodInfoTransformer.getInstance());
            EmitUtils.method_switch(e, info, new GetIndexCallback(e, info));
        }
        e.end_method();
    }

    private void signatureSwitchHelper(final CodeEmitter e, final List signatures) {
        ObjectSwitchCallback callback = new ObjectSwitchCallback(){

            public void processCase(Object key, Label end) {
                e.push(signatures.indexOf(key));
                e.return_value();
            }

            public void processDefault() {
                e.push(-1);
                e.return_value();
            }
        };
        EmitUtils.string_switch(e, signatures.toArray(new String[signatures.size()]), 1, callback);
    }

    private static void invokeSwitchHelper(final CodeEmitter e, List members, final int arg, final Type base) {
        final List info = CollectionUtils.transform(members, MethodInfoTransformer.getInstance());
        final Label illegalArg = e.make_label();
        Block block = e.begin_block();
        e.process_switch(FastClassEmitter.getIntRange(info.size()), new ProcessSwitchCallback(){

            public void processCase(int key, Label end) {
                MethodInfo method = (MethodInfo)info.get(key);
                Type[] types = method.getSignature().getArgumentTypes();
                for (int i = 0; i < types.length; ++i) {
                    e.load_arg(arg);
                    e.aaload(i);
                    e.unbox(types[i]);
                }
                e.invoke(method, base);
                if (!TypeUtils.isConstructor(method)) {
                    e.box(method.getSignature().getReturnType());
                }
                e.return_value();
            }

            public void processDefault() {
                e.goTo(illegalArg);
            }
        });
        block.end();
        EmitUtils.wrap_throwable(block, INVOCATION_TARGET_EXCEPTION);
        e.mark(illegalArg);
        e.throw_exception(ILLEGAL_ARGUMENT_EXCEPTION, "Cannot find matching method/constructor");
    }

    private static int[] getIntRange(int length) {
        int[] range = new int[length];
        for (int i = 0; i < length; ++i) {
            range[i] = i;
        }
        return range;
    }

    private static class GetIndexCallback
    implements ObjectSwitchCallback {
        private CodeEmitter e;
        private Map indexes = new HashMap();

        public GetIndexCallback(CodeEmitter e, List methods) {
            this.e = e;
            int index = 0;
            Iterator it = methods.iterator();
            while (it.hasNext()) {
                this.indexes.put(it.next(), new Integer(index++));
            }
        }

        public void processCase(Object key, Label end) {
            this.e.push((Integer)this.indexes.get(key));
            this.e.return_value();
        }

        public void processDefault() {
            this.e.push(-1);
            this.e.return_value();
        }
    }
}

