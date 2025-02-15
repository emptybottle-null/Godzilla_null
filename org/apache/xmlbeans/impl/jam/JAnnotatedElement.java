/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam;

import org.apache.xmlbeans.impl.jam.JAnnotation;
import org.apache.xmlbeans.impl.jam.JAnnotationValue;
import org.apache.xmlbeans.impl.jam.JComment;
import org.apache.xmlbeans.impl.jam.JElement;

public interface JAnnotatedElement
extends JElement {
    public JAnnotation[] getAnnotations();

    public JAnnotation getAnnotation(Class var1);

    public Object getAnnotationProxy(Class var1);

    public JAnnotation getAnnotation(String var1);

    public JAnnotationValue getAnnotationValue(String var1);

    public JComment getComment();

    public JAnnotation[] getAllJavadocTags();
}

