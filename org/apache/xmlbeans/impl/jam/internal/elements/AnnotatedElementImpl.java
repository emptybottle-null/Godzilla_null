/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.internal.elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.xmlbeans.impl.jam.JAnnotation;
import org.apache.xmlbeans.impl.jam.JAnnotationValue;
import org.apache.xmlbeans.impl.jam.JComment;
import org.apache.xmlbeans.impl.jam.annotation.AnnotationProxy;
import org.apache.xmlbeans.impl.jam.internal.elements.AnnotationImpl;
import org.apache.xmlbeans.impl.jam.internal.elements.CommentImpl;
import org.apache.xmlbeans.impl.jam.internal.elements.ElementContext;
import org.apache.xmlbeans.impl.jam.internal.elements.ElementImpl;
import org.apache.xmlbeans.impl.jam.mutable.MAnnotatedElement;
import org.apache.xmlbeans.impl.jam.mutable.MAnnotation;
import org.apache.xmlbeans.impl.jam.mutable.MComment;

public abstract class AnnotatedElementImpl
extends ElementImpl
implements MAnnotatedElement {
    private Map mName2Annotation = null;
    private MComment mComment = null;
    private List mAllAnnotations = null;

    protected AnnotatedElementImpl(ElementContext ctx) {
        super(ctx);
    }

    protected AnnotatedElementImpl(ElementImpl parent) {
        super(parent);
    }

    @Override
    public JAnnotation[] getAnnotations() {
        return this.getMutableAnnotations();
    }

    @Override
    public JAnnotation getAnnotation(Class proxyClass) {
        return this.getMutableAnnotation(proxyClass.getName());
    }

    @Override
    public JAnnotation getAnnotation(String named) {
        return this.getMutableAnnotation(named);
    }

    @Override
    public JAnnotationValue getAnnotationValue(String valueId) {
        if (this.mName2Annotation == null) {
            return null;
        }
        int delim = (valueId = valueId.trim()).indexOf(64);
        if (delim == -1 || delim == valueId.length() - 1) {
            JAnnotation ann = this.getAnnotation(valueId);
            if (ann == null) {
                return null;
            }
            return ann.getValue("value");
        }
        JAnnotation ann = this.getAnnotation(valueId.substring(0, delim));
        if (ann == null) {
            return null;
        }
        return ann.getValue(valueId.substring(delim + 1));
    }

    @Override
    public Object getAnnotationProxy(Class proxyClass) {
        return this.getEditableProxy(proxyClass);
    }

    @Override
    public JComment getComment() {
        return this.getMutableComment();
    }

    @Override
    public JAnnotation[] getAllJavadocTags() {
        if (this.mAllAnnotations == null) {
            return NO_ANNOTATION;
        }
        JAnnotation[] out = new JAnnotation[this.mAllAnnotations.size()];
        this.mAllAnnotations.toArray(out);
        return out;
    }

    public AnnotationProxy getEditableProxy(Class proxyClass) {
        if (this.mName2Annotation == null) {
            return null;
        }
        MAnnotation out = this.getMutableAnnotation(proxyClass.getName());
        return out == null ? null : (AnnotationProxy)out.getProxy();
    }

    public void removeAnnotation(MAnnotation ann) {
        if (this.mName2Annotation != null) {
            this.mName2Annotation.values().remove(ann);
        }
    }

    @Override
    public MAnnotation[] getMutableAnnotations() {
        if (this.mName2Annotation == null) {
            return new MAnnotation[0];
        }
        MAnnotation[] out = new MAnnotation[this.mName2Annotation.values().size()];
        this.mName2Annotation.values().toArray(out);
        return out;
    }

    @Override
    public MAnnotation getMutableAnnotation(String named) {
        if (this.mName2Annotation == null) {
            return null;
        }
        named = named.trim();
        return (MAnnotation)this.mName2Annotation.get(named);
    }

    @Override
    public MAnnotation findOrCreateAnnotation(String annotationName) {
        MAnnotation ann = this.getMutableAnnotation(annotationName);
        if (ann != null) {
            return ann;
        }
        AnnotationProxy proxy = this.getContext().createAnnotationProxy(annotationName);
        ann = new AnnotationImpl(this.getContext(), proxy, annotationName);
        if (this.mName2Annotation == null) {
            this.mName2Annotation = new HashMap();
        }
        this.mName2Annotation.put(ann.getQualifiedName(), ann);
        return ann;
    }

    @Override
    public MAnnotation addLiteralAnnotation(String annName) {
        if (annName == null) {
            throw new IllegalArgumentException("null tagname");
        }
        annName = annName.trim();
        AnnotationProxy proxy = this.getContext().createAnnotationProxy(annName);
        AnnotationImpl ann = new AnnotationImpl(this.getContext(), proxy, annName);
        if (this.mAllAnnotations == null) {
            this.mAllAnnotations = new ArrayList();
        }
        this.mAllAnnotations.add(ann);
        if (this.getMutableAnnotation(annName) == null) {
            if (this.mName2Annotation == null) {
                this.mName2Annotation = new HashMap();
            }
            this.mName2Annotation.put(annName, ann);
        }
        return ann;
    }

    @Override
    public MComment getMutableComment() {
        return this.mComment;
    }

    @Override
    public MComment createComment() {
        this.mComment = new CommentImpl(this);
        return this.mComment;
    }

    @Override
    public void removeComment() {
        this.mComment = null;
    }

    protected void addAnnotation(JAnnotation ann) {
        if (this.mName2Annotation == null) {
            this.mName2Annotation = new HashMap();
            this.mName2Annotation.put(ann.getQualifiedName(), ann);
        } else if (this.mName2Annotation.get(ann.getQualifiedName()) == null) {
            this.mName2Annotation.put(ann.getQualifiedName(), ann);
        }
        if (this.mAllAnnotations == null) {
            this.mAllAnnotations = new ArrayList();
        }
        this.mAllAnnotations.add(ann);
    }

    public MAnnotation addAnnotationForProxy(Class proxyClass, AnnotationProxy proxy) {
        String annotationName = proxyClass.getName();
        MAnnotation ann = this.getMutableAnnotation(annotationName);
        if (ann != null) {
            return ann;
        }
        ann = new AnnotationImpl(this.getContext(), proxy, annotationName);
        if (this.mName2Annotation == null) {
            this.mName2Annotation = new HashMap();
        }
        this.mName2Annotation.put(ann.getQualifiedName(), ann);
        return ann;
    }
}

