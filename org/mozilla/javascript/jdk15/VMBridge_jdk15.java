/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.jdk15;

import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Iterator;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Wrapper;
import org.mozilla.javascript.jdk13.VMBridge_jdk13;

public class VMBridge_jdk15
extends VMBridge_jdk13 {
    public VMBridge_jdk15() throws SecurityException, InstantiationException {
        try {
            Method.class.getMethod("isVarArgs", null);
        } catch (NoSuchMethodException e) {
            throw new InstantiationException(e.getMessage());
        }
    }

    @Override
    public boolean isVarArgs(Member member) {
        if (member instanceof Method) {
            return ((Method)member).isVarArgs();
        }
        if (member instanceof Constructor) {
            return ((Constructor)member).isVarArgs();
        }
        return false;
    }

    @Override
    public Iterator<?> getJavaIterator(Context cx, Scriptable scope, Object obj) {
        if (obj instanceof Wrapper) {
            Object unwrapped = ((Wrapper)obj).unwrap();
            Iterator iterator = null;
            if (unwrapped instanceof Iterator) {
                iterator = (Iterator)unwrapped;
            }
            if (unwrapped instanceof Iterable) {
                iterator = ((Iterable)unwrapped).iterator();
            }
            return iterator;
        }
        return null;
    }
}

