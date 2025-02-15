/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.reflect;

import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.asm.ClassVisitor;
import org.springframework.asm.Type;
import org.springframework.cglib.core.AbstractClassGenerator;
import org.springframework.cglib.core.ClassEmitter;
import org.springframework.cglib.core.CodeEmitter;
import org.springframework.cglib.core.Constants;
import org.springframework.cglib.core.EmitUtils;
import org.springframework.cglib.core.Local;
import org.springframework.cglib.core.MethodInfo;
import org.springframework.cglib.core.ProcessArrayCallback;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.cglib.core.Signature;
import org.springframework.cglib.core.TypeUtils;

public abstract class MulticastDelegate
implements Cloneable {
    protected Object[] targets = new Object[0];

    protected MulticastDelegate() {
    }

    public List getTargets() {
        return new ArrayList<Object>(Arrays.asList(this.targets));
    }

    public abstract MulticastDelegate add(Object var1);

    protected MulticastDelegate addHelper(Object target) {
        MulticastDelegate copy = this.newInstance();
        copy.targets = new Object[this.targets.length + 1];
        System.arraycopy(this.targets, 0, copy.targets, 0, this.targets.length);
        copy.targets[this.targets.length] = target;
        return copy;
    }

    public MulticastDelegate remove(Object target) {
        for (int i = this.targets.length - 1; i >= 0; --i) {
            if (!this.targets[i].equals(target)) continue;
            MulticastDelegate copy = this.newInstance();
            copy.targets = new Object[this.targets.length - 1];
            System.arraycopy(this.targets, 0, copy.targets, 0, i);
            System.arraycopy(this.targets, i + 1, copy.targets, i, this.targets.length - i - 1);
            return copy;
        }
        return this;
    }

    public abstract MulticastDelegate newInstance();

    public static MulticastDelegate create(Class iface) {
        Generator gen = new Generator();
        gen.setInterface(iface);
        return gen.create();
    }

    public static class Generator
    extends AbstractClassGenerator {
        private static final AbstractClassGenerator.Source SOURCE = new AbstractClassGenerator.Source(MulticastDelegate.class.getName());
        private static final Type MULTICAST_DELEGATE = TypeUtils.parseType("org.springframework.cglib.reflect.MulticastDelegate");
        private static final Signature NEW_INSTANCE = new Signature("newInstance", MULTICAST_DELEGATE, new Type[0]);
        private static final Signature ADD_DELEGATE = new Signature("add", MULTICAST_DELEGATE, new Type[]{Constants.TYPE_OBJECT});
        private static final Signature ADD_HELPER = new Signature("addHelper", MULTICAST_DELEGATE, new Type[]{Constants.TYPE_OBJECT});
        private Class iface;

        public Generator() {
            super(SOURCE);
        }

        protected ClassLoader getDefaultClassLoader() {
            return this.iface.getClassLoader();
        }

        protected ProtectionDomain getProtectionDomain() {
            return ReflectUtils.getProtectionDomain(this.iface);
        }

        public void setInterface(Class iface) {
            this.iface = iface;
        }

        public MulticastDelegate create() {
            this.setNamePrefix(MulticastDelegate.class.getName());
            return (MulticastDelegate)super.create(this.iface.getName());
        }

        public void generateClass(ClassVisitor cv) {
            MethodInfo method = ReflectUtils.getMethodInfo(ReflectUtils.findInterfaceMethod(this.iface));
            ClassEmitter ce = new ClassEmitter(cv);
            ce.begin_class(52, 1, this.getClassName(), MULTICAST_DELEGATE, new Type[]{Type.getType(this.iface)}, "<generated>");
            EmitUtils.null_constructor(ce);
            this.emitProxy(ce, method);
            CodeEmitter e = ce.begin_method(1, NEW_INSTANCE, null);
            e.new_instance_this();
            e.dup();
            e.invoke_constructor_this();
            e.return_value();
            e.end_method();
            e = ce.begin_method(1, ADD_DELEGATE, null);
            e.load_this();
            e.load_arg(0);
            e.checkcast(Type.getType(this.iface));
            e.invoke_virtual_this(ADD_HELPER);
            e.return_value();
            e.end_method();
            ce.end_class();
        }

        private void emitProxy(ClassEmitter ce, final MethodInfo method) {
            int modifiers = 1;
            if ((method.getModifiers() & 0x80) == 128) {
                modifiers |= 0x80;
            }
            final CodeEmitter e = EmitUtils.begin_method(ce, method, modifiers);
            Type returnType = method.getSignature().getReturnType();
            final boolean returns = returnType != Type.VOID_TYPE;
            Local result = null;
            if (returns) {
                result = e.make_local(returnType);
                e.zero_or_null(returnType);
                e.store_local(result);
            }
            e.load_this();
            e.super_getfield("targets", Constants.TYPE_OBJECT_ARRAY);
            final Local result2 = result;
            EmitUtils.process_array(e, Constants.TYPE_OBJECT_ARRAY, new ProcessArrayCallback(){

                public void processElement(Type type) {
                    e.checkcast(Type.getType(Generator.this.iface));
                    e.load_args();
                    e.invoke(method);
                    if (returns) {
                        e.store_local(result2);
                    }
                }
            });
            if (returns) {
                e.load_local(result);
            }
            e.return_value();
            e.end_method();
        }

        protected Object firstInstance(Class type) {
            return ((MulticastDelegate)ReflectUtils.newInstance(type)).newInstance();
        }

        protected Object nextInstance(Object instance) {
            return ((MulticastDelegate)instance).newInstance();
        }
    }
}

