/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.internal.elements;

import org.apache.xmlbeans.impl.jam.JClass;
import org.apache.xmlbeans.impl.jam.internal.elements.BuiltinClassImpl;
import org.apache.xmlbeans.impl.jam.internal.elements.ElementContext;

public final class VoidClassImpl
extends BuiltinClassImpl {
    private static final String SIMPLE_NAME = "void";

    public static boolean isVoid(String fd) {
        return fd.equals(SIMPLE_NAME);
    }

    public VoidClassImpl(ElementContext ctx) {
        super(ctx);
        super.reallySetSimpleName(SIMPLE_NAME);
    }

    @Override
    public boolean isVoidType() {
        return true;
    }

    @Override
    public boolean isAssignableFrom(JClass c) {
        return false;
    }
}

