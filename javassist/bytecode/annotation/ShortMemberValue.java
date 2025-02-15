/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.bytecode.annotation;

import java.io.IOException;
import java.lang.reflect.Method;
import javassist.ClassPool;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.AnnotationsWriter;
import javassist.bytecode.annotation.MemberValue;
import javassist.bytecode.annotation.MemberValueVisitor;

public class ShortMemberValue
extends MemberValue {
    int valueIndex;

    public ShortMemberValue(int index, ConstPool cp) {
        super('S', cp);
        this.valueIndex = index;
    }

    public ShortMemberValue(short s, ConstPool cp) {
        super('S', cp);
        this.setValue(s);
    }

    public ShortMemberValue(ConstPool cp) {
        super('S', cp);
        this.setValue((short)0);
    }

    @Override
    Object getValue(ClassLoader cl, ClassPool cp, Method m) {
        return this.getValue();
    }

    @Override
    Class<?> getType(ClassLoader cl) {
        return Short.TYPE;
    }

    public short getValue() {
        return (short)this.cp.getIntegerInfo(this.valueIndex);
    }

    public void setValue(short newValue) {
        this.valueIndex = this.cp.addIntegerInfo(newValue);
    }

    public String toString() {
        return Short.toString(this.getValue());
    }

    @Override
    public void write(AnnotationsWriter writer) throws IOException {
        writer.constValueIndex(this.getValue());
    }

    @Override
    public void accept(MemberValueVisitor visitor) {
        visitor.visitShortMemberValue(this);
    }
}

