/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.asm;

import org.springframework.asm.AnnotationVisitor;
import org.springframework.asm.Attribute;
import org.springframework.asm.FieldVisitor;
import org.springframework.asm.MethodVisitor;
import org.springframework.asm.ModuleVisitor;
import org.springframework.asm.RecordComponentVisitor;
import org.springframework.asm.TypePath;

public abstract class ClassVisitor {
    protected final int api;
    protected ClassVisitor cv;

    public ClassVisitor(int api) {
        this(api, null);
    }

    public ClassVisitor(int api, ClassVisitor classVisitor) {
        if (api != 589824 && api != 524288 && api != 458752 && api != 393216 && api != 327680 && api != 262144 && api != 0x10A0000) {
            throw new IllegalArgumentException("Unsupported api " + api);
        }
        this.api = api;
        this.cv = classVisitor;
    }

    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        if (this.api < 524288 && (access & 0x10000) != 0) {
            throw new UnsupportedOperationException("Records requires ASM8");
        }
        if (this.cv != null) {
            this.cv.visit(version, access, name, signature, superName, interfaces);
        }
    }

    public void visitSource(String source, String debug) {
        if (this.cv != null) {
            this.cv.visitSource(source, debug);
        }
    }

    public ModuleVisitor visitModule(String name, int access, String version) {
        if (this.api < 393216) {
            throw new UnsupportedOperationException("Module requires ASM6");
        }
        if (this.cv != null) {
            return this.cv.visitModule(name, access, version);
        }
        return null;
    }

    public void visitNestHost(String nestHost) {
        if (this.api < 458752) {
            throw new UnsupportedOperationException("NestHost requires ASM7");
        }
        if (this.cv != null) {
            this.cv.visitNestHost(nestHost);
        }
    }

    public void visitOuterClass(String owner, String name, String descriptor) {
        if (this.cv != null) {
            this.cv.visitOuterClass(owner, name, descriptor);
        }
    }

    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        if (this.cv != null) {
            return this.cv.visitAnnotation(descriptor, visible);
        }
        return null;
    }

    public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
        if (this.api < 327680) {
            throw new UnsupportedOperationException("TypeAnnotation requires ASM5");
        }
        if (this.cv != null) {
            return this.cv.visitTypeAnnotation(typeRef, typePath, descriptor, visible);
        }
        return null;
    }

    public void visitAttribute(Attribute attribute) {
        if (this.cv != null) {
            this.cv.visitAttribute(attribute);
        }
    }

    public void visitNestMember(String nestMember) {
        if (this.api < 458752) {
            throw new UnsupportedOperationException("NestMember requires ASM7");
        }
        if (this.cv != null) {
            this.cv.visitNestMember(nestMember);
        }
    }

    public void visitPermittedSubclass(String permittedSubclass) {
        if (this.api < 589824) {
            throw new UnsupportedOperationException("PermittedSubclasses requires ASM9");
        }
        if (this.cv != null) {
            this.cv.visitPermittedSubclass(permittedSubclass);
        }
    }

    public void visitInnerClass(String name, String outerName, String innerName, int access) {
        if (this.cv != null) {
            this.cv.visitInnerClass(name, outerName, innerName, access);
        }
    }

    public RecordComponentVisitor visitRecordComponent(String name, String descriptor, String signature) {
        if (this.api < 524288) {
            throw new UnsupportedOperationException("Record requires ASM8");
        }
        if (this.cv != null) {
            return this.cv.visitRecordComponent(name, descriptor, signature);
        }
        return null;
    }

    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        if (this.cv != null) {
            return this.cv.visitField(access, name, descriptor, signature, value);
        }
        return null;
    }

    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        if (this.cv != null) {
            return this.cv.visitMethod(access, name, descriptor, signature, exceptions);
        }
        return null;
    }

    public void visitEnd() {
        if (this.cv != null) {
            this.cv.visitEnd();
        }
    }
}

