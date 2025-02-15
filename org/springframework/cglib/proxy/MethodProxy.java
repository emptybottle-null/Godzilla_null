/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.springframework.cglib.core.AbstractClassGenerator;
import org.springframework.cglib.core.CodeGenerationException;
import org.springframework.cglib.core.GeneratorStrategy;
import org.springframework.cglib.core.NamingPolicy;
import org.springframework.cglib.core.Signature;
import org.springframework.cglib.proxy.MethodInterceptorGenerator;
import org.springframework.cglib.reflect.FastClass;

public class MethodProxy {
    private Signature sig1;
    private Signature sig2;
    private CreateInfo createInfo;
    private final Object initLock = new Object();
    private volatile FastClassInfo fastClassInfo;

    public static MethodProxy create(Class c1, Class c2, String desc, String name1, String name2) {
        MethodProxy proxy = new MethodProxy();
        proxy.sig1 = new Signature(name1, desc);
        proxy.sig2 = new Signature(name2, desc);
        proxy.createInfo = new CreateInfo(c1, c2);
        return proxy;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void init() {
        if (this.fastClassInfo == null) {
            Object object = this.initLock;
            synchronized (object) {
                if (this.fastClassInfo == null) {
                    CreateInfo ci = this.createInfo;
                    FastClassInfo fci = new FastClassInfo();
                    fci.f1 = MethodProxy.helper(ci, ci.c1);
                    fci.f2 = MethodProxy.helper(ci, ci.c2);
                    fci.i1 = fci.f1.getIndex(this.sig1);
                    fci.i2 = fci.f2.getIndex(this.sig2);
                    this.fastClassInfo = fci;
                    this.createInfo = null;
                }
            }
        }
    }

    private static FastClass helper(CreateInfo ci, Class type) {
        FastClass.Generator g = new FastClass.Generator();
        g.setType(type);
        g.setContextClass(type);
        g.setClassLoader(ci.c2.getClassLoader());
        g.setNamingPolicy(ci.namingPolicy);
        g.setStrategy(ci.strategy);
        g.setAttemptLoad(ci.attemptLoad);
        return g.create();
    }

    private MethodProxy() {
    }

    public Signature getSignature() {
        return this.sig1;
    }

    public String getSuperName() {
        return this.sig2.getName();
    }

    public int getSuperIndex() {
        this.init();
        return this.fastClassInfo.i2;
    }

    FastClass getFastClass() {
        this.init();
        return this.fastClassInfo.f1;
    }

    FastClass getSuperFastClass() {
        this.init();
        return this.fastClassInfo.f2;
    }

    public static MethodProxy find(Class type, Signature sig) {
        try {
            Method m = type.getDeclaredMethod("CGLIB$findMethodProxy", MethodInterceptorGenerator.FIND_PROXY_TYPES);
            return (MethodProxy)m.invoke(null, sig);
        } catch (NoSuchMethodException ex) {
            throw new IllegalArgumentException("Class " + type + " does not use a MethodInterceptor");
        } catch (IllegalAccessException | InvocationTargetException ex) {
            throw new CodeGenerationException(ex);
        }
    }

    public Object invoke(Object obj, Object[] args) throws Throwable {
        try {
            this.init();
            FastClassInfo fci = this.fastClassInfo;
            return fci.f1.invoke(fci.i1, obj, args);
        } catch (InvocationTargetException ex) {
            throw ex.getTargetException();
        } catch (IllegalArgumentException ex) {
            if (this.fastClassInfo.i1 < 0) {
                throw new IllegalArgumentException("Protected method: " + this.sig1);
            }
            throw ex;
        }
    }

    public Object invokeSuper(Object obj, Object[] args) throws Throwable {
        try {
            this.init();
            FastClassInfo fci = this.fastClassInfo;
            return fci.f2.invoke(fci.i2, obj, args);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
    }

    private static class CreateInfo {
        Class c1;
        Class c2;
        NamingPolicy namingPolicy;
        GeneratorStrategy strategy;
        boolean attemptLoad;

        public CreateInfo(Class c1, Class c2) {
            this.c1 = c1;
            this.c2 = c2;
            AbstractClassGenerator fromEnhancer = AbstractClassGenerator.getCurrent();
            if (fromEnhancer != null) {
                this.namingPolicy = fromEnhancer.getNamingPolicy();
                this.strategy = fromEnhancer.getStrategy();
                this.attemptLoad = fromEnhancer.getAttemptLoad();
            }
        }
    }

    private static class FastClassInfo {
        FastClass f1;
        FastClass f2;
        int i1;
        int i2;

        private FastClassInfo() {
        }
    }
}

