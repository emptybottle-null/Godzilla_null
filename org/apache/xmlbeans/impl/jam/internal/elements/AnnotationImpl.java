/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.internal.elements;

import org.apache.xmlbeans.impl.jam.JAnnotationValue;
import org.apache.xmlbeans.impl.jam.JClass;
import org.apache.xmlbeans.impl.jam.annotation.AnnotationProxy;
import org.apache.xmlbeans.impl.jam.internal.elements.ElementContext;
import org.apache.xmlbeans.impl.jam.internal.elements.ElementImpl;
import org.apache.xmlbeans.impl.jam.mutable.MAnnotation;
import org.apache.xmlbeans.impl.jam.visitor.JVisitor;
import org.apache.xmlbeans.impl.jam.visitor.MVisitor;

public final class AnnotationImpl
extends ElementImpl
implements MAnnotation {
    private AnnotationProxy mProxy;
    private Object mAnnotationInstance = null;
    private String mQualifiedName = null;

    AnnotationImpl(ElementContext ctx, AnnotationProxy proxy, String qualifiedName) {
        super(ctx);
        if (proxy == null) {
            throw new IllegalArgumentException("null proxy");
        }
        if (qualifiedName == null) {
            throw new IllegalArgumentException("null qn");
        }
        this.mProxy = proxy;
        this.setSimpleName(qualifiedName.substring(qualifiedName.lastIndexOf(46) + 1));
        this.mQualifiedName = qualifiedName;
    }

    @Override
    public Object getProxy() {
        return this.mProxy;
    }

    @Override
    public JAnnotationValue[] getValues() {
        return this.mProxy.getValues();
    }

    @Override
    public JAnnotationValue getValue(String name) {
        return this.mProxy.getValue(name);
    }

    @Override
    public Object getAnnotationInstance() {
        return this.mAnnotationInstance;
    }

    @Override
    public void setAnnotationInstance(Object o) {
        this.mAnnotationInstance = o;
    }

    @Override
    public void setSimpleValue(String name, Object value, JClass type) {
        if (name == null) {
            throw new IllegalArgumentException("null name");
        }
        if (type == null) {
            throw new IllegalArgumentException("null type");
        }
        if (value == null) {
            throw new IllegalArgumentException("null value");
        }
        this.mProxy.setValue(name, value, type);
    }

    @Override
    public MAnnotation createNestedValue(String name, String annTypeName) {
        if (name == null) {
            throw new IllegalArgumentException("null name");
        }
        if (annTypeName == null) {
            throw new IllegalArgumentException("null typename");
        }
        AnnotationProxy p = this.getContext().createAnnotationProxy(annTypeName);
        AnnotationImpl out = new AnnotationImpl(this.getContext(), p, annTypeName);
        JClass type = this.getContext().getClassLoader().loadClass(annTypeName);
        this.mProxy.setValue(name, out, type);
        return out;
    }

    @Override
    public MAnnotation[] createNestedValueArray(String name, String annComponentTypeName, int dimensions) {
        if (name == null) {
            throw new IllegalArgumentException("null name");
        }
        if (annComponentTypeName == null) {
            throw new IllegalArgumentException("null typename");
        }
        if (dimensions < 0) {
            throw new IllegalArgumentException("dimensions = " + dimensions);
        }
        MAnnotation[] out = new MAnnotation[dimensions];
        for (int i = 0; i < out.length; ++i) {
            AnnotationProxy p = this.getContext().createAnnotationProxy(annComponentTypeName);
            out[i] = new AnnotationImpl(this.getContext(), p, annComponentTypeName);
        }
        JClass type = this.getContext().getClassLoader().loadClass("[L" + annComponentTypeName + ";");
        this.mProxy.setValue(name, out, type);
        return out;
    }

    @Override
    public String getQualifiedName() {
        return this.mQualifiedName;
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

