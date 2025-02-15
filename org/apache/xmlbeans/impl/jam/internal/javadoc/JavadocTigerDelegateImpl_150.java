/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.sun.javadoc.ClassDoc
 *  com.sun.javadoc.ExecutableMemberDoc
 *  com.sun.javadoc.Parameter
 *  com.sun.javadoc.ProgramElementDoc
 */
package org.apache.xmlbeans.impl.jam.internal.javadoc;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.ExecutableMemberDoc;
import com.sun.javadoc.Parameter;
import com.sun.javadoc.ProgramElementDoc;
import org.apache.xmlbeans.impl.jam.internal.elements.ElementContext;
import org.apache.xmlbeans.impl.jam.internal.javadoc.JavadocClassBuilder;
import org.apache.xmlbeans.impl.jam.internal.javadoc.JavadocTigerDelegate;
import org.apache.xmlbeans.impl.jam.mutable.MAnnotatedElement;
import org.apache.xmlbeans.impl.jam.mutable.MClass;
import org.apache.xmlbeans.impl.jam.provider.JamLogger;

public final class JavadocTigerDelegateImpl_150
extends JavadocTigerDelegate {
    @Override
    public void init(ElementContext ctx) {
    }

    @Override
    public void init(JamLogger logger) {
    }

    @Override
    public void populateAnnotationTypeIfNecessary(ClassDoc cd, MClass clazz, JavadocClassBuilder builder) {
    }

    @Override
    public void extractAnnotations(MAnnotatedElement dest, ProgramElementDoc src) {
    }

    @Override
    public void extractAnnotations(MAnnotatedElement dest, ExecutableMemberDoc method, Parameter src) {
    }

    @Override
    public boolean isEnum(ClassDoc cd) {
        return false;
    }
}

