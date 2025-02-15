/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.type.classreading;

import java.util.LinkedHashSet;
import java.util.Set;
import org.springframework.asm.AnnotationVisitor;
import org.springframework.asm.Attribute;
import org.springframework.asm.ClassVisitor;
import org.springframework.asm.FieldVisitor;
import org.springframework.asm.MethodVisitor;
import org.springframework.core.type.ClassMetadata;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

@Deprecated
class ClassMetadataReadingVisitor
extends ClassVisitor
implements ClassMetadata {
    private String className = "";
    private boolean isInterface;
    private boolean isAnnotation;
    private boolean isAbstract;
    private boolean isFinal;
    @Nullable
    private String enclosingClassName;
    private boolean independentInnerClass;
    @Nullable
    private String superClassName;
    private String[] interfaces = new String[0];
    private Set<String> memberClassNames = new LinkedHashSet<String>(4);

    public ClassMetadataReadingVisitor() {
        super(0x10A0000);
    }

    @Override
    public void visit(int version, int access, String name, String signature, @Nullable String supername, String[] interfaces) {
        this.className = ClassUtils.convertResourcePathToClassName(name);
        this.isInterface = (access & 0x200) != 0;
        this.isAnnotation = (access & 0x2000) != 0;
        this.isAbstract = (access & 0x400) != 0;
        boolean bl = this.isFinal = (access & 0x10) != 0;
        if (supername != null && !this.isInterface) {
            this.superClassName = ClassUtils.convertResourcePathToClassName(supername);
        }
        this.interfaces = new String[interfaces.length];
        for (int i = 0; i < interfaces.length; ++i) {
            this.interfaces[i] = ClassUtils.convertResourcePathToClassName(interfaces[i]);
        }
    }

    @Override
    public void visitOuterClass(String owner, String name, String desc) {
        this.enclosingClassName = ClassUtils.convertResourcePathToClassName(owner);
    }

    @Override
    public void visitInnerClass(String name, @Nullable String outerName, String innerName, int access) {
        if (outerName != null) {
            String fqName = ClassUtils.convertResourcePathToClassName(name);
            String fqOuterName = ClassUtils.convertResourcePathToClassName(outerName);
            if (this.className.equals(fqName)) {
                this.enclosingClassName = fqOuterName;
                this.independentInnerClass = (access & 8) != 0;
            } else if (this.className.equals(fqOuterName)) {
                this.memberClassNames.add(fqName);
            }
        }
    }

    @Override
    public void visitSource(String source, String debug) {
    }

    @Override
    @Nullable
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        return new EmptyAnnotationVisitor();
    }

    @Override
    public void visitAttribute(Attribute attr) {
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        return new EmptyFieldVisitor();
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        return new EmptyMethodVisitor();
    }

    @Override
    public void visitEnd() {
    }

    @Override
    public String getClassName() {
        return this.className;
    }

    @Override
    public boolean isInterface() {
        return this.isInterface;
    }

    @Override
    public boolean isAnnotation() {
        return this.isAnnotation;
    }

    @Override
    public boolean isAbstract() {
        return this.isAbstract;
    }

    @Override
    public boolean isFinal() {
        return this.isFinal;
    }

    @Override
    public boolean isIndependent() {
        return this.enclosingClassName == null || this.independentInnerClass;
    }

    @Override
    public boolean hasEnclosingClass() {
        return this.enclosingClassName != null;
    }

    @Override
    @Nullable
    public String getEnclosingClassName() {
        return this.enclosingClassName;
    }

    @Override
    @Nullable
    public String getSuperClassName() {
        return this.superClassName;
    }

    @Override
    public String[] getInterfaceNames() {
        return this.interfaces;
    }

    @Override
    public String[] getMemberClassNames() {
        return StringUtils.toStringArray(this.memberClassNames);
    }

    private static class EmptyFieldVisitor
    extends FieldVisitor {
        public EmptyFieldVisitor() {
            super(0x10A0000);
        }
    }

    private static class EmptyMethodVisitor
    extends MethodVisitor {
        public EmptyMethodVisitor() {
            super(0x10A0000);
        }
    }

    private static class EmptyAnnotationVisitor
    extends AnnotationVisitor {
        public EmptyAnnotationVisitor() {
            super(0x10A0000);
        }

        @Override
        public AnnotationVisitor visitAnnotation(String name, String desc) {
            return this;
        }

        @Override
        public AnnotationVisitor visitArray(String name) {
            return this;
        }
    }
}

