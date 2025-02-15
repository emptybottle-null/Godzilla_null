/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.internal.elements;

import java.io.StringWriter;
import java.lang.reflect.Modifier;
import org.apache.xmlbeans.impl.jam.JClass;
import org.apache.xmlbeans.impl.jam.internal.classrefs.DirectJClassRef;
import org.apache.xmlbeans.impl.jam.internal.classrefs.JClassRef;
import org.apache.xmlbeans.impl.jam.internal.classrefs.QualifiedJClassRef;
import org.apache.xmlbeans.impl.jam.internal.classrefs.UnqualifiedJClassRef;
import org.apache.xmlbeans.impl.jam.internal.elements.ClassImpl;
import org.apache.xmlbeans.impl.jam.internal.elements.MemberImpl;
import org.apache.xmlbeans.impl.jam.mutable.MField;
import org.apache.xmlbeans.impl.jam.visitor.JVisitor;
import org.apache.xmlbeans.impl.jam.visitor.MVisitor;

public final class FieldImpl
extends MemberImpl
implements MField {
    private JClassRef mTypeClassRef;

    FieldImpl(String simpleName, ClassImpl containingClass, String qualifiedTypeClassName) {
        super(containingClass);
        super.setSimpleName(simpleName);
        this.mTypeClassRef = QualifiedJClassRef.create(qualifiedTypeClassName, containingClass);
    }

    @Override
    public void setType(JClass type) {
        if (type == null) {
            throw new IllegalArgumentException("null type");
        }
        this.mTypeClassRef = DirectJClassRef.create(type);
    }

    @Override
    public void setType(String qcname) {
        if (qcname == null) {
            throw new IllegalArgumentException("null qcname");
        }
        this.mTypeClassRef = QualifiedJClassRef.create(qcname, (ClassImpl)this.getContainingClass());
    }

    @Override
    public void setUnqualifiedType(String ucname) {
        if (ucname == null) {
            throw new IllegalArgumentException("null ucname");
        }
        this.mTypeClassRef = UnqualifiedJClassRef.create(ucname, (ClassImpl)this.getContainingClass());
    }

    @Override
    public JClass getType() {
        if (this.mTypeClassRef == null) {
            throw new IllegalStateException();
        }
        return this.mTypeClassRef.getRefClass();
    }

    @Override
    public boolean isFinal() {
        return Modifier.isFinal(this.getModifiers());
    }

    @Override
    public boolean isStatic() {
        return Modifier.isStatic(this.getModifiers());
    }

    @Override
    public boolean isVolatile() {
        return Modifier.isVolatile(this.getModifiers());
    }

    @Override
    public boolean isTransient() {
        return Modifier.isTransient(this.getModifiers());
    }

    @Override
    public void accept(MVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void accept(JVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getQualifiedName() {
        StringWriter sbuf = new StringWriter();
        sbuf.write(Modifier.toString(this.getModifiers()));
        sbuf.write(32);
        sbuf.write(this.getType().getQualifiedName());
        sbuf.write(32);
        sbuf.write(this.getContainingClass().getQualifiedName());
        sbuf.write(46);
        sbuf.write(this.getSimpleName());
        return sbuf.toString();
    }
}

