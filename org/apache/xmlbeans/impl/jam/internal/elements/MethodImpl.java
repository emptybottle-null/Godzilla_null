/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.internal.elements;

import java.io.StringWriter;
import java.lang.reflect.Modifier;
import org.apache.xmlbeans.impl.jam.JClass;
import org.apache.xmlbeans.impl.jam.JParameter;
import org.apache.xmlbeans.impl.jam.internal.classrefs.DirectJClassRef;
import org.apache.xmlbeans.impl.jam.internal.classrefs.JClassRef;
import org.apache.xmlbeans.impl.jam.internal.classrefs.QualifiedJClassRef;
import org.apache.xmlbeans.impl.jam.internal.classrefs.UnqualifiedJClassRef;
import org.apache.xmlbeans.impl.jam.internal.elements.ClassImpl;
import org.apache.xmlbeans.impl.jam.internal.elements.InvokableImpl;
import org.apache.xmlbeans.impl.jam.mutable.MMethod;
import org.apache.xmlbeans.impl.jam.visitor.JVisitor;
import org.apache.xmlbeans.impl.jam.visitor.MVisitor;

public final class MethodImpl
extends InvokableImpl
implements MMethod {
    private JClassRef mReturnTypeRef = null;

    MethodImpl(String simpleName, ClassImpl containingClass) {
        super(containingClass);
        this.setSimpleName(simpleName);
    }

    @Override
    public void setReturnType(String className) {
        this.mReturnTypeRef = QualifiedJClassRef.create(className, (ClassImpl)this.getContainingClass());
    }

    @Override
    public void setUnqualifiedReturnType(String unqualifiedTypeName) {
        this.mReturnTypeRef = UnqualifiedJClassRef.create(unqualifiedTypeName, (ClassImpl)this.getContainingClass());
    }

    @Override
    public void setReturnType(JClass c) {
        this.mReturnTypeRef = DirectJClassRef.create(c);
    }

    @Override
    public JClass getReturnType() {
        if (this.mReturnTypeRef == null) {
            return this.getClassLoader().loadClass("void");
        }
        return this.mReturnTypeRef.getRefClass();
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
    public boolean isAbstract() {
        return Modifier.isAbstract(this.getModifiers());
    }

    @Override
    public boolean isNative() {
        return Modifier.isNative(this.getModifiers());
    }

    @Override
    public boolean isSynchronized() {
        return Modifier.isSynchronized(this.getModifiers());
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
        int i;
        StringWriter sbuf = new StringWriter();
        sbuf.write(Modifier.toString(this.getModifiers()));
        sbuf.write(32);
        JClass returnJClass = this.getReturnType();
        if (returnJClass == null) {
            sbuf.write("void ");
        } else {
            sbuf.write(returnJClass.getQualifiedName());
            sbuf.write(32);
        }
        sbuf.write(this.getSimpleName());
        sbuf.write(40);
        JParameter[] params = this.getParameters();
        if (params != null && params.length > 0) {
            for (i = 0; i < params.length; ++i) {
                sbuf.write(params[i].getType().getQualifiedName());
                if (i >= params.length - 1) continue;
                sbuf.write(44);
            }
        }
        sbuf.write(41);
        JClass[] thrown = this.getExceptionTypes();
        if (thrown != null && thrown.length > 0) {
            sbuf.write(" throws ");
            for (i = 0; i < thrown.length; ++i) {
                sbuf.write(thrown[i].getQualifiedName());
                if (i >= thrown.length - 1) continue;
                sbuf.write(44);
            }
        }
        return sbuf.toString();
    }
}

