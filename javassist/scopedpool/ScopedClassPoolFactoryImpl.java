/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.scopedpool;

import javassist.ClassPool;
import javassist.scopedpool.ScopedClassPool;
import javassist.scopedpool.ScopedClassPoolFactory;
import javassist.scopedpool.ScopedClassPoolRepository;

public class ScopedClassPoolFactoryImpl
implements ScopedClassPoolFactory {
    @Override
    public ScopedClassPool create(ClassLoader cl, ClassPool src, ScopedClassPoolRepository repository) {
        return new ScopedClassPool(cl, src, repository, false);
    }

    @Override
    public ScopedClassPool create(ClassPool src, ScopedClassPoolRepository repository) {
        return new ScopedClassPool(null, src, repository, true);
    }
}

