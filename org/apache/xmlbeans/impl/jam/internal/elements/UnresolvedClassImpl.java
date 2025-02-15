/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.internal.elements;

import org.apache.xmlbeans.impl.jam.JClass;
import org.apache.xmlbeans.impl.jam.JPackage;
import org.apache.xmlbeans.impl.jam.internal.elements.BuiltinClassImpl;
import org.apache.xmlbeans.impl.jam.internal.elements.ElementContext;

public final class UnresolvedClassImpl
extends BuiltinClassImpl {
    private String mPackageName;

    public UnresolvedClassImpl(String packageName, String simpleName, ElementContext ctx) {
        super(ctx);
        if (packageName == null) {
            throw new IllegalArgumentException("null pkg");
        }
        this.mPackageName = packageName;
        this.reallySetSimpleName(simpleName);
    }

    @Override
    public String getQualifiedName() {
        return (this.mPackageName.length() > 0 ? this.mPackageName + '.' : "") + this.mSimpleName;
    }

    @Override
    public String getFieldDescriptor() {
        return this.getQualifiedName();
    }

    @Override
    public JPackage getContainingPackage() {
        return null;
    }

    @Override
    public boolean isAssignableFrom(JClass c) {
        return false;
    }

    @Override
    public boolean isUnresolvedType() {
        return true;
    }
}

