/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.internal;

import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import org.apache.xmlbeans.impl.jam.JClass;
import org.apache.xmlbeans.impl.jam.JPackage;
import org.apache.xmlbeans.impl.jam.JamClassLoader;
import org.apache.xmlbeans.impl.jam.internal.elements.ArrayClassImpl;
import org.apache.xmlbeans.impl.jam.internal.elements.ClassImpl;
import org.apache.xmlbeans.impl.jam.internal.elements.ElementContext;
import org.apache.xmlbeans.impl.jam.internal.elements.PackageImpl;
import org.apache.xmlbeans.impl.jam.internal.elements.PrimitiveClassImpl;
import org.apache.xmlbeans.impl.jam.internal.elements.UnresolvedClassImpl;
import org.apache.xmlbeans.impl.jam.internal.elements.VoidClassImpl;
import org.apache.xmlbeans.impl.jam.mutable.MClass;
import org.apache.xmlbeans.impl.jam.provider.JamClassBuilder;
import org.apache.xmlbeans.impl.jam.visitor.MVisitor;
import org.apache.xmlbeans.impl.jam.visitor.TraversingMVisitor;

public class JamClassLoaderImpl
implements JamClassLoader {
    private Map mName2Package = new HashMap();
    private Map mFd2ClassCache = new HashMap();
    private JamClassBuilder mBuilder;
    private MVisitor mInitializer = null;
    private ElementContext mContext;
    private Stack mInitializeStack = new Stack();
    private boolean mAlreadyInitializing = false;

    public JamClassLoaderImpl(ElementContext context, JamClassBuilder builder, MVisitor initializerOrNull) {
        if (builder == null) {
            throw new IllegalArgumentException("null builder");
        }
        if (context == null) {
            throw new IllegalArgumentException("null builder");
        }
        this.mBuilder = builder;
        this.mInitializer = initializerOrNull == null ? null : new TraversingMVisitor(initializerOrNull);
        this.mContext = context;
        this.initCache();
    }

    @Override
    public final JClass loadClass(String fd) {
        String name;
        String pkg;
        JClass out = this.cacheGet(fd = fd.trim());
        if (out != null) {
            return out;
        }
        if (fd.indexOf(91) != -1) {
            String normalFd = ArrayClassImpl.normalizeArrayName(fd);
            out = this.cacheGet(normalFd);
            if (out == null) {
                out = ArrayClassImpl.createClassForFD(normalFd, this);
                this.cachePut(out, normalFd);
            }
            this.cachePut(out, fd);
            return out;
        }
        int dollar = fd.indexOf(36);
        if (dollar != -1) {
            String outerName = fd.substring(0, dollar);
            ((ClassImpl)this.loadClass(outerName)).ensureLoaded();
            out = this.cacheGet(fd);
            int dot = fd.lastIndexOf(46);
            if (out == null) {
                String name2;
                String pkg2;
                if (dot == -1) {
                    pkg2 = "";
                    name2 = fd;
                } else {
                    pkg2 = fd.substring(0, dot);
                    name2 = fd.substring(dot + 1);
                }
                out = new UnresolvedClassImpl(pkg2, name2, this.mContext);
                this.mContext.warning("failed to resolve class " + fd);
                this.cachePut(out);
            }
            return out;
        }
        int dot = fd.lastIndexOf(46);
        if (dot == -1) {
            pkg = "";
            name = fd;
        } else {
            pkg = fd.substring(0, dot);
            name = fd.substring(dot + 1);
        }
        out = this.mBuilder.build(pkg, name);
        if (out == null) {
            out = new UnresolvedClassImpl(pkg, name, this.mContext);
            this.mContext.warning("failed to resolve class " + fd);
            this.cachePut(out);
            return out;
        }
        this.cachePut(out);
        return out;
    }

    @Override
    public JPackage getPackage(String named) {
        JPackage out = (JPackage)this.mName2Package.get(named);
        if (out == null) {
            out = new PackageImpl(this.mContext, named);
            this.mName2Package.put(named, out);
        }
        return out;
    }

    private void initCache() {
        PrimitiveClassImpl.mapNameToPrimitive(this.mContext, this.mFd2ClassCache);
        this.mFd2ClassCache.put("void", new VoidClassImpl(this.mContext));
    }

    private void cachePut(JClass clazz) {
        this.mFd2ClassCache.put(clazz.getFieldDescriptor().trim(), new WeakReference<JClass>(clazz));
    }

    private void cachePut(JClass clazz, String cachedName) {
        this.mFd2ClassCache.put(cachedName, new WeakReference<JClass>(clazz));
    }

    private JClass cacheGet(String fd) {
        Object out = this.mFd2ClassCache.get(fd.trim());
        if (out == null) {
            return null;
        }
        if (out instanceof JClass) {
            return (JClass)out;
        }
        if (out instanceof WeakReference) {
            if ((out = ((WeakReference)out).get()) == null) {
                this.mFd2ClassCache.remove(fd.trim());
                return null;
            }
            return (JClass)out;
        }
        throw new IllegalStateException();
    }

    public void initialize(ClassImpl out) {
        if (this.mInitializer != null) {
            if (this.mAlreadyInitializing) {
                this.mInitializeStack.push(out);
            } else {
                out.accept(this.mInitializer);
                while (!this.mInitializeStack.isEmpty()) {
                    ClassImpl initme = (ClassImpl)this.mInitializeStack.pop();
                    initme.accept(this.mInitializer);
                }
                this.mAlreadyInitializing = false;
            }
        }
    }

    public Collection getResolvedClasses() {
        return Collections.unmodifiableCollection(this.mFd2ClassCache.values());
    }

    public void addToCache(JClass c) {
        this.cachePut((MClass)c);
    }
}

