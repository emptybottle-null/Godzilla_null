/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.asm.commons;

import org.springframework.asm.AnnotationVisitor;
import org.springframework.asm.Attribute;
import org.springframework.asm.ClassVisitor;
import org.springframework.asm.FieldVisitor;
import org.springframework.asm.Label;
import org.springframework.asm.MethodVisitor;

public class EmptyVisitor
implements ClassVisitor,
FieldVisitor,
MethodVisitor,
AnnotationVisitor {
    public void visit(int n, int n2, String string, String string2, String string3, String[] stringArray) {
    }

    public void visitSource(String string, String string2) {
    }

    public void visitOuterClass(String string, String string2, String string3) {
    }

    public AnnotationVisitor visitAnnotation(String string, boolean bl) {
        return this;
    }

    public void visitAttribute(Attribute attribute) {
    }

    public void visitInnerClass(String string, String string2, String string3, int n) {
    }

    public FieldVisitor visitField(int n, String string, String string2, String string3, Object object) {
        return this;
    }

    public MethodVisitor visitMethod(int n, String string, String string2, String string3, String[] stringArray) {
        return this;
    }

    public void visitEnd() {
    }

    public AnnotationVisitor visitAnnotationDefault() {
        return this;
    }

    public AnnotationVisitor visitParameterAnnotation(int n, String string, boolean bl) {
        return this;
    }

    public void visitCode() {
    }

    public void visitInsn(int n) {
    }

    public void visitIntInsn(int n, int n2) {
    }

    public void visitVarInsn(int n, int n2) {
    }

    public void visitTypeInsn(int n, String string) {
    }

    public void visitFieldInsn(int n, String string, String string2, String string3) {
    }

    public void visitMethodInsn(int n, String string, String string2, String string3) {
    }

    public void visitJumpInsn(int n, Label label) {
    }

    public void visitLabel(Label label) {
    }

    public void visitLdcInsn(Object object) {
    }

    public void visitIincInsn(int n, int n2) {
    }

    public void visitTableSwitchInsn(int n, int n2, Label label, Label[] labelArray) {
    }

    public void visitLookupSwitchInsn(Label label, int[] nArray, Label[] labelArray) {
    }

    public void visitMultiANewArrayInsn(String string, int n) {
    }

    public void visitTryCatchBlock(Label label, Label label2, Label label3, String string) {
    }

    public void visitLocalVariable(String string, String string2, String string3, Label label, Label label2, int n) {
    }

    public void visitLineNumber(int n, Label label) {
    }

    public void visitMaxs(int n, int n2) {
    }

    public void visit(String string, Object object) {
    }

    public void visitEnum(String string, String string2, String string3) {
    }

    public AnnotationVisitor visitAnnotation(String string, String string2) {
        return this;
    }

    public AnnotationVisitor visitArray(String string) {
        return this;
    }
}

