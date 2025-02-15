/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.transform;

import org.springframework.cglib.transform.AbstractClassFilterTransformer;
import org.springframework.cglib.transform.ClassFilter;
import org.springframework.cglib.transform.ClassTransformer;

public class ClassFilterTransformer
extends AbstractClassFilterTransformer {
    private ClassFilter filter;

    public ClassFilterTransformer(ClassFilter filter, ClassTransformer pass) {
        super(pass);
        this.filter = filter;
    }

    protected boolean accept(int version, int access, String name, String signature, String superName, String[] interfaces) {
        return this.filter.accept(name.replace('/', '.'));
    }
}

