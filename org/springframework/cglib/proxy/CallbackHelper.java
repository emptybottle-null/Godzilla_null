/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.proxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.CallbackFilter;
import org.springframework.cglib.proxy.Enhancer;

public abstract class CallbackHelper
implements CallbackFilter {
    private Map methodMap = new HashMap();
    private List callbacks = new ArrayList();

    public CallbackHelper(Class superclass, Class[] interfaces) {
        ArrayList methods = new ArrayList();
        Enhancer.getMethods(superclass, interfaces, methods);
        HashMap<Object, Integer> indexes = new HashMap<Object, Integer>();
        int size = methods.size();
        for (int i = 0; i < size; ++i) {
            Method method = (Method)methods.get(i);
            Object callback = this.getCallback(method);
            if (callback == null) {
                throw new IllegalStateException("getCallback cannot return null");
            }
            boolean isCallback = callback instanceof Callback;
            if (!isCallback && !(callback instanceof Class)) {
                throw new IllegalStateException("getCallback must return a Callback or a Class");
            }
            if (i > 0 && this.callbacks.get(i - 1) instanceof Callback ^ isCallback) {
                throw new IllegalStateException("getCallback must return a Callback or a Class consistently for every Method");
            }
            Integer index = (Integer)indexes.get(callback);
            if (index == null) {
                index = new Integer(this.callbacks.size());
                indexes.put(callback, index);
            }
            this.methodMap.put(method, index);
            this.callbacks.add(callback);
        }
    }

    protected abstract Object getCallback(Method var1);

    public Callback[] getCallbacks() {
        if (this.callbacks.size() == 0) {
            return new Callback[0];
        }
        if (this.callbacks.get(0) instanceof Callback) {
            return this.callbacks.toArray(new Callback[this.callbacks.size()]);
        }
        throw new IllegalStateException("getCallback returned classes, not callbacks; call getCallbackTypes instead");
    }

    public Class[] getCallbackTypes() {
        if (this.callbacks.size() == 0) {
            return new Class[0];
        }
        if (this.callbacks.get(0) instanceof Callback) {
            return ReflectUtils.getClasses(this.getCallbacks());
        }
        return this.callbacks.toArray(new Class[this.callbacks.size()]);
    }

    public int accept(Method method) {
        return (Integer)this.methodMap.get(method);
    }

    public int hashCode() {
        return this.methodMap.hashCode();
    }

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof CallbackHelper)) {
            return false;
        }
        return this.methodMap.equals(((CallbackHelper)o).methodMap);
    }
}

