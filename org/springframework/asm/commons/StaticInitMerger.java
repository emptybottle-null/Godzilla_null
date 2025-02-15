/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.asm.commons;

import org.springframework.asm.ClassAdapter;
import org.springframework.asm.ClassVisitor;
import org.springframework.asm.MethodVisitor;

public class StaticInitMerger
extends ClassAdapter {
    private String name;
    private MethodVisitor clinit;
    private String prefix;
    private int counter;

    public StaticInitMerger(String string, ClassVisitor classVisitor) {
        super(classVisitor);
        this.prefix = string;
    }

    public void visit(int n, int n2, String string, String string2, String string3, String[] stringArray) {
        this.cv.visit(n, n2, string, string2, string3, stringArray);
        this.name = string;
    }

    public MethodVisitor visitMethod(int n, String string, String string2, String string3, String[] stringArray) {
        MethodVisitor methodVisitor;
        if (string.equals("<clinit>")) {
            int n2 = 10;
            String string4 = this.prefix + this.counter++;
            methodVisitor = this.cv.visitMethod(n2, string4, string2, string3, stringArray);
            if (this.clinit == null) {
                this.clinit = this.cv.visitMethod(n2, string, string2, null, null);
            }
            this.clinit.visitMethodInsn(184, this.name, string4, string2);
        } else {
            methodVisitor = this.cv.visitMethod(n, string, string2, string3, stringArray);
        }
        return methodVisitor;
    }

    public void visitEnd() {
        if (this.clinit != null) {
            this.clinit.visitInsn(177);
            this.clinit.visitMaxs(0, 0);
        }
        this.cv.visitEnd();
    }
}

