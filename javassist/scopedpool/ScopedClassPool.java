/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.scopedpool;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.security.ProtectionDomain;
import java.util.Map;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.LoaderClassPath;
import javassist.NotFoundException;
import javassist.scopedpool.ScopedClassPoolRepository;
import javassist.scopedpool.SoftValueHashMap;

public class ScopedClassPool
extends ClassPool {
    protected ScopedClassPoolRepository repository;
    protected Reference<ClassLoader> classLoader;
    protected LoaderClassPath classPath;
    protected Map<String, CtClass> softcache = new SoftValueHashMap<String, CtClass>();
    boolean isBootstrapCl = true;

    protected ScopedClassPool(ClassLoader cl, ClassPool src, ScopedClassPoolRepository repository) {
        this(cl, src, repository, false);
    }

    protected ScopedClassPool(ClassLoader cl, ClassPool src, ScopedClassPoolRepository repository, boolean isTemp) {
        super(src);
        this.repository = repository;
        this.classLoader = new WeakReference<ClassLoader>(cl);
        if (cl != null) {
            this.classPath = new LoaderClassPath(cl);
            this.insertClassPath(this.classPath);
        }
        this.childFirstLookup = true;
        if (!isTemp && cl == null) {
            this.isBootstrapCl = true;
        }
    }

    @Override
    public ClassLoader getClassLoader() {
        ClassLoader cl = this.getClassLoader0();
        if (cl == null && !this.isBootstrapCl) {
            throw new IllegalStateException("ClassLoader has been garbage collected");
        }
        return cl;
    }

    protected ClassLoader getClassLoader0() {
        return this.classLoader.get();
    }

    public void close() {
        this.removeClassPath(this.classPath);
        this.classes.clear();
        this.softcache.clear();
    }

    public synchronized void flushClass(String classname) {
        this.classes.remove(classname);
        this.softcache.remove(classname);
    }

    public synchronized void soften(CtClass clazz) {
        if (this.repository.isPrune()) {
            clazz.prune();
        }
        this.classes.remove(clazz.getName());
        this.softcache.put(clazz.getName(), clazz);
    }

    public boolean isUnloadedClassLoader() {
        return false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    protected CtClass getCached(String classname) {
        CtClass clazz = this.getCachedLocally(classname);
        if (clazz == null) {
            boolean isLocal = false;
            ClassLoader dcl = this.getClassLoader0();
            if (dcl != null) {
                int lastIndex = classname.lastIndexOf(36);
                String classResourceName = null;
                classResourceName = lastIndex < 0 ? classname.replaceAll("[\\.]", "/") + ".class" : classname.substring(0, lastIndex).replaceAll("[\\.]", "/") + classname.substring(lastIndex) + ".class";
                boolean bl = isLocal = dcl.getResource(classResourceName) != null;
            }
            if (!isLocal) {
                Map<ClassLoader, ScopedClassPool> registeredCLs;
                Map<ClassLoader, ScopedClassPool> map = registeredCLs = this.repository.getRegisteredCLs();
                synchronized (map) {
                    for (ScopedClassPool pool : registeredCLs.values()) {
                        if (pool.isUnloadedClassLoader()) {
                            this.repository.unregisterClassLoader(pool.getClassLoader());
                            continue;
                        }
                        clazz = pool.getCachedLocally(classname);
                        if (clazz == null) continue;
                        return clazz;
                    }
                }
            }
        }
        return clazz;
    }

    @Override
    protected void cacheCtClass(String classname, CtClass c, boolean dynamic) {
        if (dynamic) {
            super.cacheCtClass(classname, c, dynamic);
        } else {
            if (this.repository.isPrune()) {
                c.prune();
            }
            this.softcache.put(classname, c);
        }
    }

    public void lockInCache(CtClass c) {
        super.cacheCtClass(c.getName(), c, false);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected CtClass getCachedLocally(String classname) {
        CtClass cached = (CtClass)this.classes.get(classname);
        if (cached != null) {
            return cached;
        }
        Map<String, CtClass> map = this.softcache;
        synchronized (map) {
            return this.softcache.get(classname);
        }
    }

    public synchronized CtClass getLocally(String classname) throws NotFoundException {
        this.softcache.remove(classname);
        CtClass clazz = (CtClass)this.classes.get(classname);
        if (clazz == null) {
            clazz = this.createCtClass(classname, true);
            if (clazz == null) {
                throw new NotFoundException(classname);
            }
            super.cacheCtClass(classname, clazz, false);
        }
        return clazz;
    }

    @Override
    public Class<?> toClass(CtClass ct, ClassLoader loader, ProtectionDomain domain) throws CannotCompileException {
        this.lockInCache(ct);
        return super.toClass(ct, this.getClassLoader0(), domain);
    }

    static {
        ClassPool.doPruning = false;
        ClassPool.releaseUnmodifiedClassFile = false;
    }
}

