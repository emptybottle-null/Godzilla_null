/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.util;

import java.util.Arrays;
import java.util.List;
import org.springframework.asm.ClassVisitor;
import org.springframework.asm.Label;
import org.springframework.asm.Type;
import org.springframework.cglib.core.AbstractClassGenerator;
import org.springframework.cglib.core.ClassEmitter;
import org.springframework.cglib.core.CodeEmitter;
import org.springframework.cglib.core.EmitUtils;
import org.springframework.cglib.core.KeyFactory;
import org.springframework.cglib.core.ObjectSwitchCallback;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.cglib.core.Signature;
import org.springframework.cglib.core.TypeUtils;

public abstract class StringSwitcher {
    private static final Type STRING_SWITCHER = TypeUtils.parseType("org.springframework.cglib.util.StringSwitcher");
    private static final Signature INT_VALUE = TypeUtils.parseSignature("int intValue(String)");
    private static final StringSwitcherKey KEY_FACTORY = (StringSwitcherKey)((Object)KeyFactory.create(StringSwitcherKey.class));

    public static StringSwitcher create(String[] strings, int[] ints, boolean fixedInput) {
        Generator gen = new Generator();
        gen.setStrings(strings);
        gen.setInts(ints);
        gen.setFixedInput(fixedInput);
        return gen.create();
    }

    protected StringSwitcher() {
    }

    public abstract int intValue(String var1);

    public static class Generator
    extends AbstractClassGenerator {
        private static final AbstractClassGenerator.Source SOURCE = new AbstractClassGenerator.Source(StringSwitcher.class.getName());
        private String[] strings;
        private int[] ints;
        private boolean fixedInput;

        public Generator() {
            super(SOURCE);
        }

        public void setStrings(String[] strings) {
            this.strings = strings;
        }

        public void setInts(int[] ints) {
            this.ints = ints;
        }

        public void setFixedInput(boolean fixedInput) {
            this.fixedInput = fixedInput;
        }

        protected ClassLoader getDefaultClassLoader() {
            return this.getClass().getClassLoader();
        }

        public StringSwitcher create() {
            this.setNamePrefix(StringSwitcher.class.getName());
            Object key = KEY_FACTORY.newInstance(this.strings, this.ints, this.fixedInput);
            return (StringSwitcher)super.create(key);
        }

        public void generateClass(ClassVisitor v) throws Exception {
            ClassEmitter ce = new ClassEmitter(v);
            ce.begin_class(52, 1, this.getClassName(), STRING_SWITCHER, null, "<generated>");
            EmitUtils.null_constructor(ce);
            final CodeEmitter e = ce.begin_method(1, INT_VALUE, null);
            e.load_arg(0);
            final List<String> stringList = Arrays.asList(this.strings);
            int style = this.fixedInput ? 2 : 1;
            EmitUtils.string_switch(e, this.strings, style, new ObjectSwitchCallback(){

                public void processCase(Object key, Label end) {
                    e.push(Generator.this.ints[stringList.indexOf(key)]);
                    e.return_value();
                }

                public void processDefault() {
                    e.push(-1);
                    e.return_value();
                }
            });
            e.end_method();
            ce.end_class();
        }

        protected Object firstInstance(Class type) {
            return (StringSwitcher)ReflectUtils.newInstance(type);
        }

        protected Object nextInstance(Object instance) {
            return instance;
        }
    }

    static interface StringSwitcherKey {
        public Object newInstance(String[] var1, int[] var2, boolean var3);
    }
}

