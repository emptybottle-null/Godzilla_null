/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.mutable;

import org.apache.xmlbeans.impl.jam.JAnnotatedElement;
import org.apache.xmlbeans.impl.jam.mutable.MAnnotation;
import org.apache.xmlbeans.impl.jam.mutable.MComment;
import org.apache.xmlbeans.impl.jam.mutable.MElement;

public interface MAnnotatedElement
extends MElement,
JAnnotatedElement {
    public MAnnotation findOrCreateAnnotation(String var1);

    public MAnnotation[] getMutableAnnotations();

    public MAnnotation getMutableAnnotation(String var1);

    public MAnnotation addLiteralAnnotation(String var1);

    public MComment getMutableComment();

    public MComment createComment();

    public void removeComment();
}

