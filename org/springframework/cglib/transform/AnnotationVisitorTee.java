/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.transform;

import org.springframework.asm.AnnotationVisitor;
import org.springframework.cglib.core.Constants;

public class AnnotationVisitorTee
extends AnnotationVisitor {
    private AnnotationVisitor av1;
    private AnnotationVisitor av2;

    public static AnnotationVisitor getInstance(AnnotationVisitor av1, AnnotationVisitor av2) {
        if (av1 == null) {
            return av2;
        }
        if (av2 == null) {
            return av1;
        }
        return new AnnotationVisitorTee(av1, av2);
    }

    public AnnotationVisitorTee(AnnotationVisitor av1, AnnotationVisitor av2) {
        super(Constants.ASM_API);
        this.av1 = av1;
        this.av2 = av2;
    }

    public void visit(String name, Object value) {
        this.av2.visit(name, value);
        this.av2.visit(name, value);
    }

    public void visitEnum(String name, String desc, String value) {
        this.av1.visitEnum(name, desc, value);
        this.av2.visitEnum(name, desc, value);
    }

    public AnnotationVisitor visitAnnotation(String name, String desc) {
        return AnnotationVisitorTee.getInstance(this.av1.visitAnnotation(name, desc), this.av2.visitAnnotation(name, desc));
    }

    public AnnotationVisitor visitArray(String name) {
        return AnnotationVisitorTee.getInstance(this.av1.visitArray(name), this.av2.visitArray(name));
    }

    public void visitEnd() {
        this.av1.visitEnd();
        this.av2.visitEnd();
    }
}

