/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.internal.classrefs;

import org.apache.xmlbeans.impl.jam.JClass;
import org.apache.xmlbeans.impl.jam.internal.classrefs.JClassRef;

public class DirectJClassRef
implements JClassRef {
    private JClass mClass;

    public static JClassRef create(JClass clazz) {
        if (clazz instanceof JClassRef) {
            return (JClassRef)((Object)clazz);
        }
        return new DirectJClassRef(clazz);
    }

    private DirectJClassRef(JClass clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("null clazz");
        }
        this.mClass = clazz;
    }

    @Override
    public JClass getRefClass() {
        return this.mClass;
    }

    @Override
    public String getQualifiedName() {
        return this.mClass.getQualifiedName();
    }
}

