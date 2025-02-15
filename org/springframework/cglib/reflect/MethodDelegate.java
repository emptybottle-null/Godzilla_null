/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.reflect;

import java.lang.reflect.Method;
import java.security.ProtectionDomain;
import org.springframework.asm.ClassVisitor;
import org.springframework.asm.Type;
import org.springframework.cglib.core.AbstractClassGenerator;
import org.springframework.cglib.core.ClassEmitter;
import org.springframework.cglib.core.CodeEmitter;
import org.springframework.cglib.core.Constants;
import org.springframework.cglib.core.EmitUtils;
import org.springframework.cglib.core.KeyFactory;
import org.springframework.cglib.core.MethodInfo;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.cglib.core.Signature;
import org.springframework.cglib.core.TypeUtils;

public abstract class MethodDelegate {
    private static final MethodDelegateKey KEY_FACTORY = (MethodDelegateKey)((Object)KeyFactory.create(MethodDelegateKey.class, KeyFactory.CLASS_BY_NAME));
    protected Object target;
    protected String eqMethod;

    public static MethodDelegate createStatic(Class targetClass, String methodName, Class iface) {
        Generator gen = new Generator();
        gen.setTargetClass(targetClass);
        gen.setMethodName(methodName);
        gen.setInterface(iface);
        return gen.create();
    }

    public static MethodDelegate create(Object target, String methodName, Class iface) {
        Generator gen = new Generator();
        gen.setTarget(target);
        gen.setMethodName(methodName);
        gen.setInterface(iface);
        return gen.create();
    }

    public boolean equals(Object obj) {
        MethodDelegate other = (MethodDelegate)obj;
        return other != null && this.target == other.target && this.eqMethod.equals(other.eqMethod);
    }

    public int hashCode() {
        return this.target.hashCode() ^ this.eqMethod.hashCode();
    }

    public Object getTarget() {
        return this.target;
    }

    public abstract MethodDelegate newInstance(Object var1);

    public static class Generator
    extends AbstractClassGenerator {
        private static final AbstractClassGenerator.Source SOURCE = new AbstractClassGenerator.Source(MethodDelegate.class.getName());
        private static final Type METHOD_DELEGATE = TypeUtils.parseType("org.springframework.cglib.reflect.MethodDelegate");
        private static final Signature NEW_INSTANCE = new Signature("newInstance", METHOD_DELEGATE, new Type[]{Constants.TYPE_OBJECT});
        private Object target;
        private Class targetClass;
        private String methodName;
        private Class iface;

        public Generator() {
            super(SOURCE);
        }

        public void setTarget(Object target) {
            this.target = target;
            this.targetClass = target.getClass();
        }

        public void setTargetClass(Class targetClass) {
            this.targetClass = targetClass;
        }

        public void setMethodName(String methodName) {
            this.methodName = methodName;
        }

        public void setInterface(Class iface) {
            this.iface = iface;
        }

        protected ClassLoader getDefaultClassLoader() {
            return this.targetClass.getClassLoader();
        }

        protected ProtectionDomain getProtectionDomain() {
            return ReflectUtils.getProtectionDomain(this.targetClass);
        }

        public MethodDelegate create() {
            this.setNamePrefix(this.targetClass.getName());
            Object key = KEY_FACTORY.newInstance(this.targetClass, this.methodName, this.iface);
            return (MethodDelegate)super.create(key);
        }

        protected Object firstInstance(Class type) {
            return ((MethodDelegate)ReflectUtils.newInstance(type)).newInstance(this.target);
        }

        protected Object nextInstance(Object instance) {
            return ((MethodDelegate)instance).newInstance(this.target);
        }

        public void generateClass(ClassVisitor v) throws NoSuchMethodException {
            MethodInfo methodInfo;
            boolean isStatic;
            Method proxy = ReflectUtils.findInterfaceMethod(this.iface);
            Method method = this.targetClass.getMethod(this.methodName, proxy.getParameterTypes());
            if (!proxy.getReturnType().isAssignableFrom(method.getReturnType())) {
                throw new IllegalArgumentException("incompatible return types");
            }
            if (this.target == null ^ (isStatic = TypeUtils.isStatic((methodInfo = ReflectUtils.getMethodInfo(method)).getModifiers()))) {
                throw new IllegalArgumentException("Static method " + (isStatic ? "not " : "") + "expected");
            }
            ClassEmitter ce = new ClassEmitter(v);
            ce.begin_class(52, 1, this.getClassName(), METHOD_DELEGATE, new Type[]{Type.getType(this.iface)}, "<generated>");
            ce.declare_field(26, "eqMethod", Constants.TYPE_STRING, null);
            EmitUtils.null_constructor(ce);
            MethodInfo proxied = ReflectUtils.getMethodInfo(this.iface.getDeclaredMethods()[0]);
            int modifiers = 1;
            if ((proxied.getModifiers() & 0x80) == 128) {
                modifiers |= 0x80;
            }
            CodeEmitter e = EmitUtils.begin_method(ce, proxied, modifiers);
            e.load_this();
            e.super_getfield("target", Constants.TYPE_OBJECT);
            e.checkcast(methodInfo.getClassInfo().getType());
            e.load_args();
            e.invoke(methodInfo);
            e.return_value();
            e.end_method();
            e = ce.begin_method(1, NEW_INSTANCE, null);
            e.new_instance_this();
            e.dup();
            e.dup2();
            e.invoke_constructor_this();
            e.getfield("eqMethod");
            e.super_putfield("eqMethod", Constants.TYPE_STRING);
            e.load_arg(0);
            e.super_putfield("target", Constants.TYPE_OBJECT);
            e.return_value();
            e.end_method();
            e = ce.begin_static();
            e.push(methodInfo.getSignature().toString());
            e.putfield("eqMethod");
            e.return_value();
            e.end_method();
            ce.end_class();
        }
    }

    static interface MethodDelegateKey {
        public Object newInstance(Class var1, String var2, Class var3);
    }
}

