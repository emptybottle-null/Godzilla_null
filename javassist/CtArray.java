/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtMethod;
import javassist.NotFoundException;

final class CtArray
extends CtClass {
    protected ClassPool pool;
    private CtClass[] interfaces = null;

    CtArray(String name, ClassPool cp) {
        super(name);
        this.pool = cp;
    }

    @Override
    public ClassPool getClassPool() {
        return this.pool;
    }

    @Override
    public boolean isArray() {
        return true;
    }

    @Override
    public int getModifiers() {
        int mod = 16;
        try {
            mod |= this.getComponentType().getModifiers() & 7;
        } catch (NotFoundException notFoundException) {
            // empty catch block
        }
        return mod;
    }

    @Override
    public CtClass[] getInterfaces() throws NotFoundException {
        if (this.interfaces == null) {
            Class<?>[] intfs = Object[].class.getInterfaces();
            this.interfaces = new CtClass[intfs.length];
            for (int i = 0; i < intfs.length; ++i) {
                this.interfaces[i] = this.pool.get(intfs[i].getName());
            }
        }
        return this.interfaces;
    }

    @Override
    public boolean subtypeOf(CtClass clazz) throws NotFoundException {
        if (super.subtypeOf(clazz)) {
            return true;
        }
        String cname = clazz.getName();
        if (cname.equals("java.lang.Object")) {
            return true;
        }
        CtClass[] intfs = this.getInterfaces();
        for (int i = 0; i < intfs.length; ++i) {
            if (!intfs[i].subtypeOf(clazz)) continue;
            return true;
        }
        return clazz.isArray() && this.getComponentType().subtypeOf(clazz.getComponentType());
    }

    @Override
    public CtClass getComponentType() throws NotFoundException {
        String name = this.getName();
        return this.pool.get(name.substring(0, name.length() - 2));
    }

    @Override
    public CtClass getSuperclass() throws NotFoundException {
        return this.pool.get("java.lang.Object");
    }

    @Override
    public CtMethod[] getMethods() {
        try {
            return this.getSuperclass().getMethods();
        } catch (NotFoundException e) {
            return super.getMethods();
        }
    }

    @Override
    public CtMethod getMethod(String name, String desc) throws NotFoundException {
        return this.getSuperclass().getMethod(name, desc);
    }

    @Override
    public CtConstructor[] getConstructors() {
        try {
            return this.getSuperclass().getConstructors();
        } catch (NotFoundException e) {
            return super.getConstructors();
        }
    }
}

