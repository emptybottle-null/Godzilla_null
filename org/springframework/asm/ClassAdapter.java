/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.asm;

import org.springframework.asm.AnnotationVisitor;
import org.springframework.asm.Attribute;
import org.springframework.asm.ClassVisitor;
import org.springframework.asm.FieldVisitor;
import org.springframework.asm.MethodVisitor;

public class ClassAdapter
implements ClassVisitor {
    protected ClassVisitor cv;

    public ClassAdapter(ClassVisitor classVisitor) {
        this.cv = classVisitor;
    }

    public void visit(int n, int n2, String string, String string2, String string3, String[] stringArray) {
        this.cv.visit(n, n2, string, string2, string3, stringArray);
    }

    public void visitSource(String string, String string2) {
        this.cv.visitSource(string, string2);
    }

    public void visitOuterClass(String string, String string2, String string3) {
        this.cv.visitOuterClass(string, string2, string3);
    }

    public AnnotationVisitor visitAnnotation(String string, boolean bl) {
        return this.cv.visitAnnotation(string, bl);
    }

    public void visitAttribute(Attribute attribute) {
        this.cv.visitAttribute(attribute);
    }

    public void visitInnerClass(String string, String string2, String string3, int n) {
        this.cv.visitInnerClass(string, string2, string3, n);
    }

    public FieldVisitor visitField(int n, String string, String string2, String string3, Object object) {
        return this.cv.visitField(n, string, string2, string3, object);
    }

    public MethodVisitor visitMethod(int n, String string, String string2, String string3, String[] stringArray) {
        return this.cv.visitMethod(n, string, string2, string3, stringArray);
    }

    public void visitEnd() {
        this.cv.visitEnd();
    }
}

