/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.transform;

import org.springframework.asm.ClassReader;
import org.springframework.cglib.core.ClassGenerator;
import org.springframework.cglib.transform.AbstractClassLoader;
import org.springframework.cglib.transform.ClassFilter;
import org.springframework.cglib.transform.ClassTransformer;
import org.springframework.cglib.transform.ClassTransformerFactory;
import org.springframework.cglib.transform.TransformingClassGenerator;

public class TransformingClassLoader
extends AbstractClassLoader {
    private ClassTransformerFactory t;

    public TransformingClassLoader(ClassLoader parent, ClassFilter filter, ClassTransformerFactory t) {
        super(parent, parent, filter);
        this.t = t;
    }

    protected ClassGenerator getGenerator(ClassReader r) {
        ClassTransformer t2 = this.t.newInstance();
        return new TransformingClassGenerator(super.getGenerator(r), t2);
    }
}

