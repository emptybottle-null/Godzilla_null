/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.internal.elements;

import java.util.ArrayList;
import java.util.List;
import org.apache.xmlbeans.impl.jam.JClass;
import org.apache.xmlbeans.impl.jam.internal.elements.AnnotatedElementImpl;
import org.apache.xmlbeans.impl.jam.internal.elements.ElementContext;
import org.apache.xmlbeans.impl.jam.mutable.MClass;
import org.apache.xmlbeans.impl.jam.mutable.MPackage;
import org.apache.xmlbeans.impl.jam.visitor.JVisitor;
import org.apache.xmlbeans.impl.jam.visitor.MVisitor;

public class PackageImpl
extends AnnotatedElementImpl
implements MPackage {
    private List mRootClasses = new ArrayList();
    private String mName;

    public PackageImpl(ElementContext ctx, String name) {
        super(ctx);
        this.mName = name;
        int lastDot = this.mName.lastIndexOf(46);
        this.setSimpleName(lastDot == -1 ? this.mName : this.mName.substring(lastDot + 1));
    }

    @Override
    public String getQualifiedName() {
        return this.mName;
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
    public JClass[] getClasses() {
        JClass[] out = new JClass[this.mRootClasses.size()];
        this.mRootClasses.toArray(out);
        return out;
    }

    @Override
    public MClass[] getMutableClasses() {
        MClass[] out = new MClass[this.mRootClasses.size()];
        this.mRootClasses.toArray(out);
        return out;
    }
}

