/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.beans;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.security.ProtectionDomain;
import org.springframework.asm.ClassVisitor;
import org.springframework.asm.Type;
import org.springframework.cglib.core.AbstractClassGenerator;
import org.springframework.cglib.core.ClassEmitter;
import org.springframework.cglib.core.CodeEmitter;
import org.springframework.cglib.core.EmitUtils;
import org.springframework.cglib.core.MethodInfo;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.cglib.core.Signature;
import org.springframework.cglib.core.TypeUtils;

public class ImmutableBean {
    private static final Type ILLEGAL_STATE_EXCEPTION = TypeUtils.parseType("IllegalStateException");
    private static final Signature CSTRUCT_OBJECT = TypeUtils.parseConstructor("Object");
    private static final Class[] OBJECT_CLASSES = new Class[]{Object.class};
    private static final String FIELD_NAME = "CGLIB$RWBean";

    private ImmutableBean() {
    }

    public static Object create(Object bean) {
        Generator gen = new Generator();
        gen.setBean(bean);
        return gen.create();
    }

    public static class Generator
    extends AbstractClassGenerator {
        private static final AbstractClassGenerator.Source SOURCE = new AbstractClassGenerator.Source(ImmutableBean.class.getName());
        private Object bean;
        private Class target;

        public Generator() {
            super(SOURCE);
        }

        public void setBean(Object bean) {
            this.bean = bean;
            this.target = bean.getClass();
        }

        protected ClassLoader getDefaultClassLoader() {
            return this.target.getClassLoader();
        }

        protected ProtectionDomain getProtectionDomain() {
            return ReflectUtils.getProtectionDomain(this.target);
        }

        public Object create() {
            String name = this.target.getName();
            this.setNamePrefix(name);
            return super.create(name);
        }

        public void generateClass(ClassVisitor v) {
            int i;
            Type targetType = Type.getType(this.target);
            ClassEmitter ce = new ClassEmitter(v);
            ce.begin_class(52, 1, this.getClassName(), targetType, null, "<generated>");
            ce.declare_field(18, ImmutableBean.FIELD_NAME, targetType, null);
            CodeEmitter e = ce.begin_method(1, CSTRUCT_OBJECT, null);
            e.load_this();
            e.super_invoke_constructor();
            e.load_this();
            e.load_arg(0);
            e.checkcast(targetType);
            e.putfield(ImmutableBean.FIELD_NAME);
            e.return_value();
            e.end_method();
            PropertyDescriptor[] descriptors = ReflectUtils.getBeanProperties(this.target);
            Method[] getters = ReflectUtils.getPropertyMethods(descriptors, true, false);
            Method[] setters = ReflectUtils.getPropertyMethods(descriptors, false, true);
            for (i = 0; i < getters.length; ++i) {
                MethodInfo getter = ReflectUtils.getMethodInfo(getters[i]);
                e = EmitUtils.begin_method(ce, getter, 1);
                e.load_this();
                e.getfield(ImmutableBean.FIELD_NAME);
                e.invoke(getter);
                e.return_value();
                e.end_method();
            }
            for (i = 0; i < setters.length; ++i) {
                MethodInfo setter = ReflectUtils.getMethodInfo(setters[i]);
                e = EmitUtils.begin_method(ce, setter, 1);
                e.throw_exception(ILLEGAL_STATE_EXCEPTION, "Bean is immutable");
                e.end_method();
            }
            ce.end_class();
        }

        protected Object firstInstance(Class type) {
            return ReflectUtils.newInstance(type, OBJECT_CLASSES, new Object[]{this.bean});
        }

        protected Object nextInstance(Object instance) {
            return this.firstInstance(instance.getClass());
        }
    }
}

