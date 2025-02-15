/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.internal.elements;

import org.apache.xmlbeans.impl.jam.JClass;
import org.apache.xmlbeans.impl.jam.internal.classrefs.DirectJClassRef;
import org.apache.xmlbeans.impl.jam.internal.classrefs.JClassRef;
import org.apache.xmlbeans.impl.jam.internal.classrefs.QualifiedJClassRef;
import org.apache.xmlbeans.impl.jam.internal.classrefs.UnqualifiedJClassRef;
import org.apache.xmlbeans.impl.jam.internal.elements.ClassImpl;
import org.apache.xmlbeans.impl.jam.internal.elements.InvokableImpl;
import org.apache.xmlbeans.impl.jam.internal.elements.MemberImpl;
import org.apache.xmlbeans.impl.jam.mutable.MParameter;
import org.apache.xmlbeans.impl.jam.visitor.JVisitor;
import org.apache.xmlbeans.impl.jam.visitor.MVisitor;

public class ParameterImpl
extends MemberImpl
implements MParameter {
    private JClassRef mTypeClassRef;

    ParameterImpl(String simpleName, InvokableImpl containingMember, String typeName) {
        super(containingMember);
        this.setSimpleName(simpleName);
        this.setType(typeName);
    }

    @Override
    public String getQualifiedName() {
        return this.getSimpleName();
    }

    @Override
    public void setType(String qcname) {
        if (qcname == null) {
            throw new IllegalArgumentException("null typename");
        }
        this.mTypeClassRef = QualifiedJClassRef.create(qcname, (ClassImpl)this.getContainingClass());
    }

    @Override
    public void setType(JClass qcname) {
        if (qcname == null) {
            throw new IllegalArgumentException("null qcname");
        }
        this.mTypeClassRef = DirectJClassRef.create(qcname);
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
        return this.mTypeClassRef.getRefClass();
    }

    @Override
    public void accept(MVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void accept(JVisitor visitor) {
        visitor.visit(this);
    }
}

