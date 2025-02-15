/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.bytecode.annotation;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import javassist.ClassPool;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.AnnotationsWriter;
import javassist.bytecode.annotation.MemberValue;
import javassist.bytecode.annotation.MemberValueVisitor;

public class ArrayMemberValue
extends MemberValue {
    MemberValue type;
    MemberValue[] values;

    public ArrayMemberValue(ConstPool cp) {
        super('[', cp);
        this.type = null;
        this.values = null;
    }

    public ArrayMemberValue(MemberValue t, ConstPool cp) {
        super('[', cp);
        this.type = t;
        this.values = null;
    }

    @Override
    Object getValue(ClassLoader cl, ClassPool cp, Method method) throws ClassNotFoundException {
        Class<?> clazz;
        if (this.values == null) {
            throw new ClassNotFoundException("no array elements found: " + method.getName());
        }
        int size = this.values.length;
        if (this.type == null) {
            clazz = method.getReturnType().getComponentType();
            if (clazz == null || size > 0) {
                throw new ClassNotFoundException("broken array type: " + method.getName());
            }
        } else {
            clazz = this.type.getType(cl);
        }
        Object a = Array.newInstance(clazz, size);
        for (int i = 0; i < size; ++i) {
            Array.set(a, i, this.values[i].getValue(cl, cp, method));
        }
        return a;
    }

    @Override
    Class<?> getType(ClassLoader cl) throws ClassNotFoundException {
        if (this.type == null) {
            throw new ClassNotFoundException("no array type specified");
        }
        Object a = Array.newInstance(this.type.getType(cl), 0);
        return a.getClass();
    }

    public MemberValue getType() {
        return this.type;
    }

    public MemberValue[] getValue() {
        return this.values;
    }

    public void setValue(MemberValue[] elements) {
        this.values = elements;
        if (elements != null && elements.length > 0) {
            this.type = elements[0];
        }
    }

    public String toString() {
        StringBuffer buf = new StringBuffer("{");
        if (this.values != null) {
            for (int i = 0; i < this.values.length; ++i) {
                buf.append(this.values[i].toString());
                if (i + 1 >= this.values.length) continue;
                buf.append(", ");
            }
        }
        buf.append("}");
        return buf.toString();
    }

    @Override
    public void write(AnnotationsWriter writer) throws IOException {
        int num = this.values == null ? 0 : this.values.length;
        writer.arrayValue(num);
        for (int i = 0; i < num; ++i) {
            this.values[i].write(writer);
        }
    }

    @Override
    public void accept(MemberValueVisitor visitor) {
        visitor.visitArrayMemberValue(this);
    }
}

