/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.bytecode.annotation;

import java.io.IOException;
import java.lang.reflect.Method;
import javassist.ClassPool;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.ConstPool;
import javassist.bytecode.Descriptor;
import javassist.bytecode.SignatureAttribute;
import javassist.bytecode.annotation.AnnotationsWriter;
import javassist.bytecode.annotation.MemberValue;
import javassist.bytecode.annotation.MemberValueVisitor;

public class ClassMemberValue
extends MemberValue {
    int valueIndex;

    public ClassMemberValue(int index, ConstPool cp) {
        super('c', cp);
        this.valueIndex = index;
    }

    public ClassMemberValue(String className, ConstPool cp) {
        super('c', cp);
        this.setValue(className);
    }

    public ClassMemberValue(ConstPool cp) {
        super('c', cp);
        this.setValue("java.lang.Class");
    }

    @Override
    Object getValue(ClassLoader cl, ClassPool cp, Method m) throws ClassNotFoundException {
        String classname = this.getValue();
        if (classname.equals("void")) {
            return Void.TYPE;
        }
        if (classname.equals("int")) {
            return Integer.TYPE;
        }
        if (classname.equals("byte")) {
            return Byte.TYPE;
        }
        if (classname.equals("long")) {
            return Long.TYPE;
        }
        if (classname.equals("double")) {
            return Double.TYPE;
        }
        if (classname.equals("float")) {
            return Float.TYPE;
        }
        if (classname.equals("char")) {
            return Character.TYPE;
        }
        if (classname.equals("short")) {
            return Short.TYPE;
        }
        if (classname.equals("boolean")) {
            return Boolean.TYPE;
        }
        return ClassMemberValue.loadClass(cl, classname);
    }

    @Override
    Class<?> getType(ClassLoader cl) throws ClassNotFoundException {
        return ClassMemberValue.loadClass(cl, "java.lang.Class");
    }

    public String getValue() {
        String v = this.cp.getUtf8Info(this.valueIndex);
        try {
            return SignatureAttribute.toTypeSignature(v).jvmTypeName();
        } catch (BadBytecode e) {
            throw new RuntimeException(e);
        }
    }

    public void setValue(String newClassName) {
        String setTo = Descriptor.of(newClassName);
        this.valueIndex = this.cp.addUtf8Info(setTo);
    }

    public String toString() {
        return this.getValue().replace('$', '.') + ".class";
    }

    @Override
    public void write(AnnotationsWriter writer) throws IOException {
        writer.classInfoIndex(this.cp.getUtf8Info(this.valueIndex));
    }

    @Override
    public void accept(MemberValueVisitor visitor) {
        visitor.visitClassMemberValue(this);
    }
}

