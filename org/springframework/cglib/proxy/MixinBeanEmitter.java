/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.proxy;

import java.lang.reflect.Method;
import org.springframework.asm.ClassVisitor;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.cglib.proxy.MixinEmitter;

class MixinBeanEmitter
extends MixinEmitter {
    public MixinBeanEmitter(ClassVisitor v, String className, Class[] classes) {
        super(v, className, classes, null);
    }

    protected Class[] getInterfaces(Class[] classes) {
        return null;
    }

    protected Method[] getMethods(Class type) {
        return ReflectUtils.getPropertyMethods(ReflectUtils.getBeanProperties(type), true, true);
    }
}

