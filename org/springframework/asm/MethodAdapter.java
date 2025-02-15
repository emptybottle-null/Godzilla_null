/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.asm;

import org.springframework.asm.AnnotationVisitor;
import org.springframework.asm.Attribute;
import org.springframework.asm.Label;
import org.springframework.asm.MethodVisitor;

public class MethodAdapter
implements MethodVisitor {
    protected MethodVisitor mv;

    public MethodAdapter(MethodVisitor methodVisitor) {
        this.mv = methodVisitor;
    }

    public AnnotationVisitor visitAnnotationDefault() {
        return this.mv.visitAnnotationDefault();
    }

    public AnnotationVisitor visitAnnotation(String string, boolean bl) {
        return this.mv.visitAnnotation(string, bl);
    }

    public AnnotationVisitor visitParameterAnnotation(int n, String string, boolean bl) {
        return this.mv.visitParameterAnnotation(n, string, bl);
    }

    public void visitAttribute(Attribute attribute) {
        this.mv.visitAttribute(attribute);
    }

    public void visitCode() {
        this.mv.visitCode();
    }

    public void visitInsn(int n) {
        this.mv.visitInsn(n);
    }

    public void visitIntInsn(int n, int n2) {
        this.mv.visitIntInsn(n, n2);
    }

    public void visitVarInsn(int n, int n2) {
        this.mv.visitVarInsn(n, n2);
    }

    public void visitTypeInsn(int n, String string) {
        this.mv.visitTypeInsn(n, string);
    }

    public void visitFieldInsn(int n, String string, String string2, String string3) {
        this.mv.visitFieldInsn(n, string, string2, string3);
    }

    public void visitMethodInsn(int n, String string, String string2, String string3) {
        this.mv.visitMethodInsn(n, string, string2, string3);
    }

    public void visitJumpInsn(int n, Label label) {
        this.mv.visitJumpInsn(n, label);
    }

    public void visitLabel(Label label) {
        this.mv.visitLabel(label);
    }

    public void visitLdcInsn(Object object) {
        this.mv.visitLdcInsn(object);
    }

    public void visitIincInsn(int n, int n2) {
        this.mv.visitIincInsn(n, n2);
    }

    public void visitTableSwitchInsn(int n, int n2, Label label, Label[] labelArray) {
        this.mv.visitTableSwitchInsn(n, n2, label, labelArray);
    }

    public void visitLookupSwitchInsn(Label label, int[] nArray, Label[] labelArray) {
        this.mv.visitLookupSwitchInsn(label, nArray, labelArray);
    }

    public void visitMultiANewArrayInsn(String string, int n) {
        this.mv.visitMultiANewArrayInsn(string, n);
    }

    public void visitTryCatchBlock(Label label, Label label2, Label label3, String string) {
        this.mv.visitTryCatchBlock(label, label2, label3, string);
    }

    public void visitLocalVariable(String string, String string2, String string3, Label label, Label label2, int n) {
        this.mv.visitLocalVariable(string, string2, string3, label, label2, n);
    }

    public void visitLineNumber(int n, Label label) {
        this.mv.visitLineNumber(n, label);
    }

    public void visitMaxs(int n, int n2) {
        this.mv.visitMaxs(n, n2);
    }

    public void visitEnd() {
        this.mv.visitEnd();
    }
}

