/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.core;

import java.lang.reflect.Method;
import java.security.ProtectionDomain;
import java.util.Collections;
import java.util.List;
import org.springframework.asm.ClassVisitor;
import org.springframework.asm.Label;
import org.springframework.asm.Type;
import org.springframework.cglib.core.AbstractClassGenerator;
import org.springframework.cglib.core.ClassEmitter;
import org.springframework.cglib.core.CodeEmitter;
import org.springframework.cglib.core.Constants;
import org.springframework.cglib.core.Customizer;
import org.springframework.cglib.core.EmitUtils;
import org.springframework.cglib.core.FieldTypeCustomizer;
import org.springframework.cglib.core.HashCodeCustomizer;
import org.springframework.cglib.core.KeyFactoryCustomizer;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.cglib.core.Signature;
import org.springframework.cglib.core.TypeUtils;
import org.springframework.cglib.core.internal.CustomizerRegistry;

public abstract class KeyFactory {
    private static final Signature GET_NAME = TypeUtils.parseSignature("String getName()");
    private static final Signature GET_CLASS = TypeUtils.parseSignature("Class getClass()");
    private static final Signature HASH_CODE = TypeUtils.parseSignature("int hashCode()");
    private static final Signature EQUALS = TypeUtils.parseSignature("boolean equals(Object)");
    private static final Signature TO_STRING = TypeUtils.parseSignature("String toString()");
    private static final Signature APPEND_STRING = TypeUtils.parseSignature("StringBuffer append(String)");
    private static final Type KEY_FACTORY = TypeUtils.parseType("org.springframework.cglib.core.KeyFactory");
    private static final Signature GET_SORT = TypeUtils.parseSignature("int getSort()");
    private static final int[] PRIMES = new int[]{11, 73, 179, 331, 521, 787, 1213, 1823, 2609, 3691, 5189, 7247, 10037, 13931, 19289, 26627, 36683, 50441, 69403, 95401, 131129, 180179, 247501, 340057, 467063, 641371, 880603, 1209107, 1660097, 2279161, 3129011, 4295723, 5897291, 8095873, 11114263, 15257791, 20946017, 28754629, 39474179, 54189869, 74391461, 102123817, 140194277, 192456917, 264202273, 362693231, 497900099, 683510293, 938313161, 1288102441, 1768288259};
    public static final Customizer CLASS_BY_NAME = new Customizer(){

        @Override
        public void customize(CodeEmitter e, Type type) {
            if (type.equals(Constants.TYPE_CLASS)) {
                e.invoke_virtual(Constants.TYPE_CLASS, GET_NAME);
            }
        }
    };
    public static final FieldTypeCustomizer STORE_CLASS_AS_STRING = new FieldTypeCustomizer(){

        @Override
        public void customize(CodeEmitter e, int index, Type type) {
            if (type.equals(Constants.TYPE_CLASS)) {
                e.invoke_virtual(Constants.TYPE_CLASS, GET_NAME);
            }
        }

        @Override
        public Type getOutType(int index, Type type) {
            if (type.equals(Constants.TYPE_CLASS)) {
                return Constants.TYPE_STRING;
            }
            return type;
        }
    };
    public static final HashCodeCustomizer HASH_ASM_TYPE = new HashCodeCustomizer(){

        @Override
        public boolean customize(CodeEmitter e, Type type) {
            if (Constants.TYPE_TYPE.equals(type)) {
                e.invoke_virtual(type, GET_SORT);
                return true;
            }
            return false;
        }
    };
    @Deprecated
    public static final Customizer OBJECT_BY_CLASS = new Customizer(){

        @Override
        public void customize(CodeEmitter e, Type type) {
            e.invoke_virtual(Constants.TYPE_OBJECT, GET_CLASS);
        }
    };

    protected KeyFactory() {
    }

    public static KeyFactory create(Class keyInterface) {
        return KeyFactory.create(keyInterface, null);
    }

    public static KeyFactory create(Class keyInterface, Customizer customizer) {
        return KeyFactory.create(keyInterface.getClassLoader(), keyInterface, customizer);
    }

    public static KeyFactory create(Class keyInterface, KeyFactoryCustomizer first, List<KeyFactoryCustomizer> next) {
        return KeyFactory.create(keyInterface.getClassLoader(), keyInterface, first, next);
    }

    public static KeyFactory create(ClassLoader loader, Class keyInterface, Customizer customizer) {
        return KeyFactory.create(loader, keyInterface, customizer, Collections.emptyList());
    }

    public static KeyFactory create(ClassLoader loader, Class keyInterface, KeyFactoryCustomizer customizer, List<KeyFactoryCustomizer> next) {
        Generator gen = new Generator();
        gen.setInterface(keyInterface);
        gen.setContextClass(keyInterface);
        if (customizer != null) {
            gen.addCustomizer(customizer);
        }
        if (next != null && !next.isEmpty()) {
            for (KeyFactoryCustomizer keyFactoryCustomizer : next) {
                gen.addCustomizer(keyFactoryCustomizer);
            }
        }
        gen.setClassLoader(loader);
        return gen.create();
    }

    public static class Generator
    extends AbstractClassGenerator {
        private static final AbstractClassGenerator.Source SOURCE = new AbstractClassGenerator.Source(KeyFactory.class.getName());
        private static final Class[] KNOWN_CUSTOMIZER_TYPES = new Class[]{Customizer.class, FieldTypeCustomizer.class};
        private Class keyInterface;
        private CustomizerRegistry customizers = new CustomizerRegistry(KNOWN_CUSTOMIZER_TYPES);
        private int constant;
        private int multiplier;

        public Generator() {
            super(SOURCE);
        }

        @Override
        protected ClassLoader getDefaultClassLoader() {
            return this.keyInterface.getClassLoader();
        }

        @Override
        protected ProtectionDomain getProtectionDomain() {
            return ReflectUtils.getProtectionDomain(this.keyInterface);
        }

        @Deprecated
        public void setCustomizer(Customizer customizer) {
            this.customizers = CustomizerRegistry.singleton(customizer);
        }

        public void addCustomizer(KeyFactoryCustomizer customizer) {
            this.customizers.add(customizer);
        }

        public <T> List<T> getCustomizers(Class<T> klass) {
            return this.customizers.get(klass);
        }

        public void setInterface(Class keyInterface) {
            this.keyInterface = keyInterface;
        }

        public KeyFactory create() {
            this.setNamePrefix(this.keyInterface.getName());
            return (KeyFactory)super.create(this.keyInterface.getName());
        }

        public void setHashConstant(int constant) {
            this.constant = constant;
        }

        public void setHashMultiplier(int multiplier) {
            this.multiplier = multiplier;
        }

        @Override
        protected Object firstInstance(Class type) {
            return ReflectUtils.newInstance(type);
        }

        @Override
        protected Object nextInstance(Object instance) {
            return instance;
        }

        @Override
        public void generateClass(ClassVisitor v) {
            int i;
            ClassEmitter ce = new ClassEmitter(v);
            Method newInstance = ReflectUtils.findNewInstance(this.keyInterface);
            if (!newInstance.getReturnType().equals(Object.class)) {
                throw new IllegalArgumentException("newInstance method must return Object");
            }
            Type[] parameterTypes = TypeUtils.getTypes(newInstance.getParameterTypes());
            ce.begin_class(52, 1, this.getClassName(), KEY_FACTORY, new Type[]{Type.getType(this.keyInterface)}, "<generated>");
            EmitUtils.null_constructor(ce);
            EmitUtils.factory_method(ce, ReflectUtils.getSignature(newInstance));
            int seed = 0;
            CodeEmitter e = ce.begin_method(1, TypeUtils.parseConstructor(parameterTypes), null);
            e.load_this();
            e.super_invoke_constructor();
            e.load_this();
            List<FieldTypeCustomizer> fieldTypeCustomizers = this.getCustomizers(FieldTypeCustomizer.class);
            for (int i2 = 0; i2 < parameterTypes.length; ++i2) {
                Type parameterType;
                Type fieldType = parameterType = parameterTypes[i2];
                for (FieldTypeCustomizer customizer : fieldTypeCustomizers) {
                    fieldType = customizer.getOutType(i2, fieldType);
                }
                seed += fieldType.hashCode();
                ce.declare_field(18, this.getFieldName(i2), fieldType, null);
                e.dup();
                e.load_arg(i2);
                for (FieldTypeCustomizer customizer : fieldTypeCustomizers) {
                    customizer.customize(e, i2, parameterType);
                }
                e.putfield(this.getFieldName(i2));
            }
            e.return_value();
            e.end_method();
            e = ce.begin_method(1, HASH_CODE, null);
            int hc = this.constant != 0 ? this.constant : PRIMES[Math.abs(seed) % PRIMES.length];
            int hm = this.multiplier != 0 ? this.multiplier : PRIMES[Math.abs(seed * 13) % PRIMES.length];
            e.push(hc);
            for (int i3 = 0; i3 < parameterTypes.length; ++i3) {
                e.load_this();
                e.getfield(this.getFieldName(i3));
                EmitUtils.hash_code(e, parameterTypes[i3], hm, this.customizers);
            }
            e.return_value();
            e.end_method();
            e = ce.begin_method(1, EQUALS, null);
            Label fail = e.make_label();
            e.load_arg(0);
            e.instance_of_this();
            e.if_jump(153, fail);
            for (i = 0; i < parameterTypes.length; ++i) {
                e.load_this();
                e.getfield(this.getFieldName(i));
                e.load_arg(0);
                e.checkcast_this();
                e.getfield(this.getFieldName(i));
                EmitUtils.not_equals(e, parameterTypes[i], fail, this.customizers);
            }
            e.push(1);
            e.return_value();
            e.mark(fail);
            e.push(0);
            e.return_value();
            e.end_method();
            e = ce.begin_method(1, TO_STRING, null);
            e.new_instance(Constants.TYPE_STRING_BUFFER);
            e.dup();
            e.invoke_constructor(Constants.TYPE_STRING_BUFFER);
            for (i = 0; i < parameterTypes.length; ++i) {
                if (i > 0) {
                    e.push(", ");
                    e.invoke_virtual(Constants.TYPE_STRING_BUFFER, APPEND_STRING);
                }
                e.load_this();
                e.getfield(this.getFieldName(i));
                EmitUtils.append_string(e, parameterTypes[i], EmitUtils.DEFAULT_DELIMITERS, this.customizers);
            }
            e.invoke_virtual(Constants.TYPE_STRING_BUFFER, TO_STRING);
            e.return_value();
            e.end_method();
            ce.end_class();
        }

        private String getFieldName(int arg) {
            return "FIELD_" + arg;
        }
    }
}

