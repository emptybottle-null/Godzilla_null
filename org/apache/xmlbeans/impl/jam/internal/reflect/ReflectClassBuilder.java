/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.internal.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.apache.xmlbeans.impl.jam.internal.elements.ElementContext;
import org.apache.xmlbeans.impl.jam.internal.reflect.ReflectTigerDelegate;
import org.apache.xmlbeans.impl.jam.mutable.MClass;
import org.apache.xmlbeans.impl.jam.mutable.MConstructor;
import org.apache.xmlbeans.impl.jam.mutable.MField;
import org.apache.xmlbeans.impl.jam.mutable.MInvokable;
import org.apache.xmlbeans.impl.jam.mutable.MMethod;
import org.apache.xmlbeans.impl.jam.mutable.MParameter;
import org.apache.xmlbeans.impl.jam.provider.JamClassBuilder;
import org.apache.xmlbeans.impl.jam.provider.JamClassPopulator;

public class ReflectClassBuilder
extends JamClassBuilder
implements JamClassPopulator {
    private ClassLoader mLoader;
    private ReflectTigerDelegate mTigerDelegate = null;

    public ReflectClassBuilder(ClassLoader rcl) {
        if (rcl == null) {
            throw new IllegalArgumentException("null rcl");
        }
        this.mLoader = rcl;
    }

    @Override
    public void init(ElementContext ctx) {
        super.init(ctx);
        this.initDelegate(ctx);
    }

    @Override
    public MClass build(String packageName, String className) {
        Class<?> rclass;
        this.assertInitialized();
        if (this.getLogger().isVerbose(this)) {
            this.getLogger().verbose("trying to build '" + packageName + "' '" + className + "'");
        }
        try {
            String loadme = packageName.trim().length() > 0 ? packageName + '.' + className : className;
            rclass = this.mLoader.loadClass(loadme);
        } catch (ClassNotFoundException cnfe) {
            this.getLogger().verbose(cnfe, (Object)this);
            return null;
        }
        MClass out = this.createClassToBuild(packageName, className, null, this);
        out.setArtifact(rclass);
        return out;
    }

    @Override
    public void populate(MClass dest) {
        Class<?>[] inners;
        Class s;
        this.assertInitialized();
        Class src = (Class)dest.getArtifact();
        dest.setModifiers(src.getModifiers());
        dest.setIsInterface(src.isInterface());
        if (this.mTigerDelegate != null) {
            dest.setIsEnumType(this.mTigerDelegate.isEnum(src));
        }
        if ((s = src.getSuperclass()) != null) {
            dest.setSuperclass(s.getName());
        }
        Class<?>[] ints = src.getInterfaces();
        for (int i = 0; i < ints.length; ++i) {
            dest.addInterface(ints[i].getName());
        }
        Field[] fields = null;
        try {
            fields = src.getFields();
        } catch (Exception ignore) {
            // empty catch block
        }
        if (fields != null) {
            for (int i = 0; i < fields.length; ++i) {
                this.populate(dest.addNewField(), fields[i]);
            }
        }
        Method[] methods = src.getDeclaredMethods();
        for (int i = 0; i < methods.length; ++i) {
            this.populate(dest.addNewMethod(), methods[i]);
        }
        if (this.mTigerDelegate != null) {
            this.mTigerDelegate.populateAnnotationTypeIfNecessary(src, dest, this);
        }
        Constructor<?>[] ctors = src.getDeclaredConstructors();
        for (int i = 0; i < ctors.length; ++i) {
            this.populate(dest.addNewConstructor(), ctors[i]);
        }
        if (this.mTigerDelegate != null) {
            this.mTigerDelegate.extractAnnotations(dest, src);
        }
        if ((inners = src.getDeclaredClasses()) != null) {
            for (int i = 0; i < inners.length; ++i) {
                if (this.mTigerDelegate != null && (this.mTigerDelegate.getEnclosingConstructor(inners[i]) != null || this.mTigerDelegate.getEnclosingMethod(inners[i]) != null)) continue;
                String simpleName = inners[i].getName();
                int lastDollar = simpleName.lastIndexOf(36);
                char first = (simpleName = simpleName.substring(lastDollar + 1)).charAt(0);
                if ('0' <= first && first <= '9') continue;
                MClass inner = dest.addNewInnerClass(simpleName);
                inner.setArtifact(inners[i]);
                this.populate(inner);
            }
        }
    }

    private void initDelegate(ElementContext ctx) {
        this.mTigerDelegate = ReflectTigerDelegate.create(ctx);
    }

    private void populate(MField dest, Field src) {
        dest.setArtifact(src);
        dest.setSimpleName(src.getName());
        dest.setType(src.getType().getName());
        dest.setModifiers(src.getModifiers());
        if (this.mTigerDelegate != null) {
            this.mTigerDelegate.extractAnnotations(dest, src);
        }
    }

    private void populate(MConstructor dest, Constructor src) {
        dest.setArtifact(src);
        dest.setSimpleName(src.getName());
        dest.setModifiers(src.getModifiers());
        Class[] exceptions = src.getExceptionTypes();
        this.addThrows(dest, exceptions);
        Class<?>[] paramTypes = src.getParameterTypes();
        for (int i = 0; i < paramTypes.length; ++i) {
            MParameter p = this.addParameter(dest, i, paramTypes[i]);
            if (this.mTigerDelegate == null) continue;
            this.mTigerDelegate.extractAnnotations(p, src, i);
        }
        if (this.mTigerDelegate != null) {
            this.mTigerDelegate.extractAnnotations(dest, src);
        }
    }

    private void populate(MMethod dest, Method src) {
        dest.setArtifact(src);
        dest.setSimpleName(src.getName());
        dest.setModifiers(src.getModifiers());
        dest.setReturnType(src.getReturnType().getName());
        Class[] exceptions = src.getExceptionTypes();
        this.addThrows(dest, exceptions);
        Class<?>[] paramTypes = src.getParameterTypes();
        for (int i = 0; i < paramTypes.length; ++i) {
            MParameter p = this.addParameter(dest, i, paramTypes[i]);
            if (this.mTigerDelegate == null) continue;
            this.mTigerDelegate.extractAnnotations(p, src, i);
        }
        if (this.mTigerDelegate != null) {
            this.mTigerDelegate.extractAnnotations(dest, src);
        }
    }

    private void addThrows(MInvokable dest, Class[] exceptionTypes) {
        for (int i = 0; i < exceptionTypes.length; ++i) {
            dest.addException(exceptionTypes[i].getName());
        }
    }

    private MParameter addParameter(MInvokable dest, int paramNum, Class paramType) {
        MParameter p = dest.addNewParameter();
        p.setSimpleName("param" + paramNum);
        p.setType(paramType.getName());
        return p;
    }
}

