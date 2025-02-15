/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.internal.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.apache.xmlbeans.impl.jam.internal.reflect.ReflectClassBuilder;
import org.apache.xmlbeans.impl.jam.internal.reflect.ReflectTigerDelegate;
import org.apache.xmlbeans.impl.jam.mutable.MClass;
import org.apache.xmlbeans.impl.jam.mutable.MConstructor;
import org.apache.xmlbeans.impl.jam.mutable.MField;
import org.apache.xmlbeans.impl.jam.mutable.MMember;
import org.apache.xmlbeans.impl.jam.mutable.MParameter;

public final class ReflectTigerDelegateImpl_150
extends ReflectTigerDelegate {
    @Override
    public void populateAnnotationTypeIfNecessary(Class cd, MClass clazz, ReflectClassBuilder builder) {
    }

    @Override
    public void extractAnnotations(MMember dest, Method src) {
    }

    @Override
    public void extractAnnotations(MConstructor dest, Constructor src) {
    }

    @Override
    public void extractAnnotations(MField dest, Field src) {
    }

    @Override
    public void extractAnnotations(MClass dest, Class src) {
    }

    @Override
    public void extractAnnotations(MParameter dest, Method src, int paramNum) {
    }

    @Override
    public void extractAnnotations(MParameter dest, Constructor src, int paramNum) {
    }

    @Override
    public boolean isEnum(Class clazz) {
        return false;
    }

    @Override
    public Constructor getEnclosingConstructor(Class clazz) {
        return null;
    }

    @Override
    public Method getEnclosingMethod(Class clazz) {
        return null;
    }
}

