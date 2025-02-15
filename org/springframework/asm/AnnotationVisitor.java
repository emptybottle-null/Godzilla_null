/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.asm;

public abstract class AnnotationVisitor {
    protected final int api;
    protected AnnotationVisitor av;

    public AnnotationVisitor(int api) {
        this(api, null);
    }

    public AnnotationVisitor(int api, AnnotationVisitor annotationVisitor) {
        if (api != 589824 && api != 524288 && api != 458752 && api != 393216 && api != 327680 && api != 262144 && api != 0x10A0000) {
            throw new IllegalArgumentException("Unsupported api " + api);
        }
        this.api = api;
        this.av = annotationVisitor;
    }

    public void visit(String name, Object value) {
        if (this.av != null) {
            this.av.visit(name, value);
        }
    }

    public void visitEnum(String name, String descriptor, String value) {
        if (this.av != null) {
            this.av.visitEnum(name, descriptor, value);
        }
    }

    public AnnotationVisitor visitAnnotation(String name, String descriptor) {
        if (this.av != null) {
            return this.av.visitAnnotation(name, descriptor);
        }
        return null;
    }

    public AnnotationVisitor visitArray(String name) {
        if (this.av != null) {
            return this.av.visitArray(name);
        }
        return null;
    }

    public void visitEnd() {
        if (this.av != null) {
            this.av.visitEnd();
        }
    }
}

