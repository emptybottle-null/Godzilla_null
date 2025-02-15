/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.bytecode.annotation;

import javassist.bytecode.annotation.AnnotationMemberValue;
import javassist.bytecode.annotation.ArrayMemberValue;
import javassist.bytecode.annotation.BooleanMemberValue;
import javassist.bytecode.annotation.ByteMemberValue;
import javassist.bytecode.annotation.CharMemberValue;
import javassist.bytecode.annotation.ClassMemberValue;
import javassist.bytecode.annotation.DoubleMemberValue;
import javassist.bytecode.annotation.EnumMemberValue;
import javassist.bytecode.annotation.FloatMemberValue;
import javassist.bytecode.annotation.IntegerMemberValue;
import javassist.bytecode.annotation.LongMemberValue;
import javassist.bytecode.annotation.ShortMemberValue;
import javassist.bytecode.annotation.StringMemberValue;

public interface MemberValueVisitor {
    public void visitAnnotationMemberValue(AnnotationMemberValue var1);

    public void visitArrayMemberValue(ArrayMemberValue var1);

    public void visitBooleanMemberValue(BooleanMemberValue var1);

    public void visitByteMemberValue(ByteMemberValue var1);

    public void visitCharMemberValue(CharMemberValue var1);

    public void visitDoubleMemberValue(DoubleMemberValue var1);

    public void visitEnumMemberValue(EnumMemberValue var1);

    public void visitFloatMemberValue(FloatMemberValue var1);

    public void visitIntegerMemberValue(IntegerMemberValue var1);

    public void visitLongMemberValue(LongMemberValue var1);

    public void visitShortMemberValue(ShortMemberValue var1);

    public void visitStringMemberValue(StringMemberValue var1);

    public void visitClassMemberValue(ClassMemberValue var1);
}

