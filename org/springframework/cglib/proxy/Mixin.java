/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.proxy;

import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.springframework.asm.ClassVisitor;
import org.springframework.cglib.core.AbstractClassGenerator;
import org.springframework.cglib.core.ClassesKey;
import org.springframework.cglib.core.KeyFactory;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.cglib.proxy.MixinBeanEmitter;
import org.springframework.cglib.proxy.MixinEmitter;
import org.springframework.cglib.proxy.MixinEverythingEmitter;

public abstract class Mixin {
    private static final MixinKey KEY_FACTORY = (MixinKey)((Object)KeyFactory.create(MixinKey.class, KeyFactory.CLASS_BY_NAME));
    private static final Map ROUTE_CACHE = Collections.synchronizedMap(new HashMap());
    public static final int STYLE_INTERFACES = 0;
    public static final int STYLE_BEANS = 1;
    public static final int STYLE_EVERYTHING = 2;

    public abstract Mixin newInstance(Object[] var1);

    public static Mixin create(Object[] delegates) {
        Generator gen = new Generator();
        gen.setDelegates(delegates);
        return gen.create();
    }

    public static Mixin create(Class[] interfaces, Object[] delegates) {
        Generator gen = new Generator();
        gen.setClasses(interfaces);
        gen.setDelegates(delegates);
        return gen.create();
    }

    public static Mixin createBean(Object[] beans) {
        return Mixin.createBean(null, beans);
    }

    public static Mixin createBean(ClassLoader loader, Object[] beans) {
        Generator gen = new Generator();
        gen.setStyle(1);
        gen.setDelegates(beans);
        gen.setClassLoader(loader);
        return gen.create();
    }

    public static Class[] getClasses(Object[] delegates) {
        return (Class[])Mixin.route(delegates).classes.clone();
    }

    private static Route route(Object[] delegates) {
        Object key = ClassesKey.create(delegates);
        Route route = (Route)ROUTE_CACHE.get(key);
        if (route == null) {
            route = new Route(delegates);
            ROUTE_CACHE.put(key, route);
        }
        return route;
    }

    private static class Route {
        private Class[] classes;
        private int[] route;

        Route(Object[] delegates) {
            HashMap<Class, Integer> map = new HashMap<Class, Integer>();
            ArrayList collect = new ArrayList();
            for (int i = 0; i < delegates.length; ++i) {
                Class<?> delegate = delegates[i].getClass();
                collect.clear();
                ReflectUtils.addAllInterfaces(delegate, collect);
                for (Class iface : collect) {
                    if (map.containsKey(iface)) continue;
                    map.put(iface, new Integer(i));
                }
            }
            this.classes = new Class[map.size()];
            this.route = new int[map.size()];
            int index = 0;
            Iterator it = map.keySet().iterator();
            while (it.hasNext()) {
                Class key;
                this.classes[index] = key = (Class)it.next();
                this.route[index] = (Integer)map.get(key);
                ++index;
            }
        }
    }

    public static class Generator
    extends AbstractClassGenerator {
        private static final AbstractClassGenerator.Source SOURCE = new AbstractClassGenerator.Source(Mixin.class.getName());
        private Class[] classes;
        private Object[] delegates;
        private int style = 0;
        private int[] route;

        public Generator() {
            super(SOURCE);
        }

        protected ClassLoader getDefaultClassLoader() {
            return this.classes[0].getClassLoader();
        }

        protected ProtectionDomain getProtectionDomain() {
            return ReflectUtils.getProtectionDomain(this.classes[0]);
        }

        public void setStyle(int style) {
            switch (style) {
                case 0: 
                case 1: 
                case 2: {
                    this.style = style;
                    break;
                }
                default: {
                    throw new IllegalArgumentException("Unknown mixin style: " + style);
                }
            }
        }

        public void setClasses(Class[] classes) {
            this.classes = classes;
        }

        public void setDelegates(Object[] delegates) {
            this.delegates = delegates;
        }

        public Mixin create() {
            if (this.classes == null && this.delegates == null) {
                throw new IllegalStateException("Either classes or delegates must be set");
            }
            switch (this.style) {
                case 0: {
                    if (this.classes != null) break;
                    Route r = Mixin.route(this.delegates);
                    this.classes = r.classes;
                    this.route = r.route;
                    break;
                }
                case 1: 
                case 2: {
                    if (this.classes == null) {
                        this.classes = ReflectUtils.getClasses(this.delegates);
                        break;
                    }
                    if (this.delegates == null) break;
                    Class[] temp = ReflectUtils.getClasses(this.delegates);
                    if (this.classes.length != temp.length) {
                        throw new IllegalStateException("Specified classes are incompatible with delegates");
                    }
                    for (int i = 0; i < this.classes.length; ++i) {
                        if (this.classes[i].isAssignableFrom(temp[i])) continue;
                        throw new IllegalStateException("Specified class " + this.classes[i] + " is incompatible with delegate class " + temp[i] + " (index " + i + ")");
                    }
                    break;
                }
            }
            this.setNamePrefix(this.classes[ReflectUtils.findPackageProtected(this.classes)].getName());
            return (Mixin)super.create(KEY_FACTORY.newInstance(this.style, ReflectUtils.getNames(this.classes), this.route));
        }

        public void generateClass(ClassVisitor v) {
            switch (this.style) {
                case 0: {
                    new MixinEmitter(v, this.getClassName(), this.classes, this.route);
                    break;
                }
                case 1: {
                    new MixinBeanEmitter(v, this.getClassName(), this.classes);
                    break;
                }
                case 2: {
                    new MixinEverythingEmitter(v, this.getClassName(), this.classes);
                }
            }
        }

        protected Object firstInstance(Class type) {
            return ((Mixin)ReflectUtils.newInstance(type)).newInstance(this.delegates);
        }

        protected Object nextInstance(Object instance) {
            return ((Mixin)instance).newInstance(this.delegates);
        }
    }

    static interface MixinKey {
        public Object newInstance(int var1, String[] var2, int[] var3);
    }
}

