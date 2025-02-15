/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.transform;

import org.springframework.asm.ClassVisitor;
import org.springframework.cglib.core.Constants;
import org.springframework.cglib.transform.ClassTransformer;

public abstract class AbstractClassTransformer
extends ClassTransformer {
    protected AbstractClassTransformer() {
        super(Constants.ASM_API);
    }

    public void setTarget(ClassVisitor target) {
        this.cv = target;
    }
}

