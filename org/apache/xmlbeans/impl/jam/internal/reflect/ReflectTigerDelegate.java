/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.internal.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.apache.xmlbeans.impl.jam.internal.TigerDelegate;
import org.apache.xmlbeans.impl.jam.internal.elements.ElementContext;
import org.apache.xmlbeans.impl.jam.internal.reflect.ReflectClassBuilder;
import org.apache.xmlbeans.impl.jam.mutable.MClass;
import org.apache.xmlbeans.impl.jam.mutable.MConstructor;
import org.apache.xmlbeans.impl.jam.mutable.MField;
import org.apache.xmlbeans.impl.jam.mutable.MMember;
import org.apache.xmlbeans.impl.jam.mutable.MParameter;
import org.apache.xmlbeans.impl.jam.provider.JamLogger;

public abstract class ReflectTigerDelegate
extends TigerDelegate {
    private static final String IMPL_NAME = "org.apache.xmlbeans.impl.jam.internal.reflect.ReflectTigerDelegateImpl_150";

    public static ReflectTigerDelegate create(JamLogger logger) {
        if (!ReflectTigerDelegate.isTigerReflectionAvailable(logger)) {
            return null;
        }
        try {
            ReflectTigerDelegate out = (ReflectTigerDelegate)Class.forName(IMPL_NAME).newInstance();
            out.init(logger);
            return out;
        } catch (ClassNotFoundException e) {
            ReflectTigerDelegate.issue14BuildWarning(e, logger);
        } catch (IllegalAccessException e) {
            logger.error(e);
        } catch (InstantiationException e) {
            logger.error(e);
        }
        return null;
    }

    public static ReflectTigerDelegate create(ElementContext ctx) {
        if (!ReflectTigerDelegate.isTigerReflectionAvailable(ctx.getLogger())) {
            return null;
        }
        try {
            ReflectTigerDelegate out = (ReflectTigerDelegate)Class.forName(IMPL_NAME).newInstance();
            out.init(ctx);
            return out;
        } catch (ClassNotFoundException e) {
            ReflectTigerDelegate.issue14BuildWarning(e, ctx.getLogger());
        } catch (IllegalAccessException e) {
            ctx.getLogger().error(e);
        } catch (InstantiationException e) {
            ctx.getLogger().error(e);
        }
        return null;
    }

    protected ReflectTigerDelegate() {
    }

    public abstract void populateAnnotationTypeIfNecessary(Class var1, MClass var2, ReflectClassBuilder var3);

    public abstract boolean isEnum(Class var1);

    public abstract Constructor getEnclosingConstructor(Class var1);

    public abstract Method getEnclosingMethod(Class var1);

    public abstract void extractAnnotations(MMember var1, Method var2);

    public abstract void extractAnnotations(MConstructor var1, Constructor var2);

    public abstract void extractAnnotations(MField var1, Field var2);

    public abstract void extractAnnotations(MClass var1, Class var2);

    public abstract void extractAnnotations(MParameter var1, Method var2, int var3);

    public abstract void extractAnnotations(MParameter var1, Constructor var2, int var3);
}

