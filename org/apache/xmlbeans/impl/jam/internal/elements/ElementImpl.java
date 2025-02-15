/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.internal.elements;

import org.apache.xmlbeans.impl.jam.JElement;
import org.apache.xmlbeans.impl.jam.JPackage;
import org.apache.xmlbeans.impl.jam.JProperty;
import org.apache.xmlbeans.impl.jam.JSourcePosition;
import org.apache.xmlbeans.impl.jam.JamClassLoader;
import org.apache.xmlbeans.impl.jam.internal.JamServiceContextImpl;
import org.apache.xmlbeans.impl.jam.internal.elements.AnnotationImpl;
import org.apache.xmlbeans.impl.jam.internal.elements.ClassImpl;
import org.apache.xmlbeans.impl.jam.internal.elements.CommentImpl;
import org.apache.xmlbeans.impl.jam.internal.elements.ConstructorImpl;
import org.apache.xmlbeans.impl.jam.internal.elements.ElementContext;
import org.apache.xmlbeans.impl.jam.internal.elements.FieldImpl;
import org.apache.xmlbeans.impl.jam.internal.elements.MethodImpl;
import org.apache.xmlbeans.impl.jam.internal.elements.ParameterImpl;
import org.apache.xmlbeans.impl.jam.internal.elements.SourcePositionImpl;
import org.apache.xmlbeans.impl.jam.mutable.MElement;
import org.apache.xmlbeans.impl.jam.mutable.MSourcePosition;
import org.apache.xmlbeans.impl.jam.provider.JamLogger;

public abstract class ElementImpl
implements Comparable,
MElement {
    public static final ElementImpl[] NO_NODE = new ElementImpl[0];
    public static final ClassImpl[] NO_CLASS = new ClassImpl[0];
    public static final FieldImpl[] NO_FIELD = new FieldImpl[0];
    public static final ConstructorImpl[] NO_CONSTRUCTOR = new ConstructorImpl[0];
    public static final MethodImpl[] NO_METHOD = new MethodImpl[0];
    public static final ParameterImpl[] NO_PARAMETER = new ParameterImpl[0];
    public static final JPackage[] NO_PACKAGE = new JPackage[0];
    public static final AnnotationImpl[] NO_ANNOTATION = new AnnotationImpl[0];
    public static final CommentImpl[] NO_COMMENT = new CommentImpl[0];
    public static final JProperty[] NO_PROPERTY = new JProperty[0];
    private ElementContext mContext;
    protected String mSimpleName;
    private MSourcePosition mPosition = null;
    private Object mArtifact = null;
    private ElementImpl mParent;

    protected ElementImpl(ElementImpl parent) {
        if (parent == null) {
            throw new IllegalArgumentException("null ctx");
        }
        if (parent == this) {
            throw new IllegalArgumentException("An element cannot be its own parent");
        }
        for (JElement check = parent.getParent(); check != null; check = check.getParent()) {
            if (check != this) continue;
            throw new IllegalArgumentException("cycle detected");
        }
        this.mContext = parent.getContext();
        this.mParent = parent;
    }

    protected ElementImpl(ElementContext ctx) {
        if (ctx == null) {
            throw new IllegalArgumentException("null ctx");
        }
        this.mContext = ctx;
    }

    @Override
    public final JElement getParent() {
        return this.mParent;
    }

    @Override
    public String getSimpleName() {
        return this.mSimpleName;
    }

    @Override
    public JSourcePosition getSourcePosition() {
        return this.mPosition;
    }

    @Override
    public Object getArtifact() {
        return this.mArtifact;
    }

    @Override
    public void setSimpleName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("null name");
        }
        this.mSimpleName = name.trim();
    }

    @Override
    public MSourcePosition createSourcePosition() {
        this.mPosition = new SourcePositionImpl();
        return this.mPosition;
    }

    @Override
    public void removeSourcePosition() {
        this.mPosition = null;
    }

    @Override
    public MSourcePosition getMutableSourcePosition() {
        return this.mPosition;
    }

    @Override
    public void setArtifact(Object artifact) {
        if (artifact == null) {
            // empty if block
        }
        if (this.mArtifact != null) {
            throw new IllegalStateException("artifact already set");
        }
        this.mArtifact = artifact;
    }

    @Override
    public JamClassLoader getClassLoader() {
        return this.mContext.getClassLoader();
    }

    public static String defaultName(int count) {
        return "unnamed_" + count;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ElementImpl)) {
            return false;
        }
        ElementImpl eElement = (ElementImpl)o;
        String qn = this.getQualifiedName();
        if (qn == null) {
            return false;
        }
        String oqn = eElement.getQualifiedName();
        if (oqn == null) {
            return false;
        }
        return qn.equals(oqn);
    }

    public int hashCode() {
        String qn = this.getQualifiedName();
        return qn == null ? 0 : qn.hashCode();
    }

    public ElementContext getContext() {
        return this.mContext;
    }

    @Override
    public String toString() {
        return this.getQualifiedName();
    }

    protected JamLogger getLogger() {
        return ((JamServiceContextImpl)this.mContext).getLogger();
    }

    public int compareTo(Object o) {
        if (!(o instanceof JElement)) {
            return -1;
        }
        return this.getQualifiedName().compareTo(((JElement)o).getQualifiedName());
    }
}

