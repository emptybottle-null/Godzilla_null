/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.springframework.asm.Type;
import org.springframework.cglib.core.Signature;
import org.springframework.cglib.reflect.FastClass;
import org.springframework.cglib.reflect.FastMember;

public class FastMethod
extends FastMember {
    FastMethod(FastClass fc, Method method) {
        super(fc, method, FastMethod.helper(fc, method));
    }

    private static int helper(FastClass fc, Method method) {
        int index = fc.getIndex(new Signature(method.getName(), Type.getMethodDescriptor(method)));
        if (index < 0) {
            Class<?>[] types = method.getParameterTypes();
            System.err.println("hash=" + method.getName().hashCode() + " size=" + types.length);
            for (int i = 0; i < types.length; ++i) {
                System.err.println("  types[" + i + "]=" + types[i].getName());
            }
            throw new IllegalArgumentException("Cannot find method " + method);
        }
        return index;
    }

    public Class getReturnType() {
        return ((Method)this.member).getReturnType();
    }

    public Class[] getParameterTypes() {
        return ((Method)this.member).getParameterTypes();
    }

    public Class[] getExceptionTypes() {
        return ((Method)this.member).getExceptionTypes();
    }

    public Object invoke(Object obj, Object[] args) throws InvocationTargetException {
        return this.fc.invoke(this.index, obj, args);
    }

    public Method getJavaMethod() {
        return (Method)this.member;
    }
}

