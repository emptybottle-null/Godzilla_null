/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.transform;

import org.springframework.asm.Attribute;
import org.springframework.asm.ClassReader;
import org.springframework.asm.ClassVisitor;
import org.springframework.cglib.core.ClassGenerator;

public class ClassReaderGenerator
implements ClassGenerator {
    private final ClassReader r;
    private final Attribute[] attrs;
    private final int flags;

    public ClassReaderGenerator(ClassReader r, int flags) {
        this(r, null, flags);
    }

    public ClassReaderGenerator(ClassReader r, Attribute[] attrs, int flags) {
        this.r = r;
        this.attrs = attrs != null ? attrs : new Attribute[]{};
        this.flags = flags;
    }

    public void generateClass(ClassVisitor v) {
        this.r.accept(v, this.attrs, this.flags);
    }
}

