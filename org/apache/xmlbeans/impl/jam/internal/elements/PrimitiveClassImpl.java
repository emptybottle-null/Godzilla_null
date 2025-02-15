/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.internal.elements;

import java.util.HashMap;
import java.util.Map;
import org.apache.xmlbeans.impl.jam.JClass;
import org.apache.xmlbeans.impl.jam.internal.elements.BuiltinClassImpl;
import org.apache.xmlbeans.impl.jam.internal.elements.ElementContext;

public final class PrimitiveClassImpl
extends BuiltinClassImpl {
    private static final Object[][] PRIMITIVES = new Object[][]{{"int", "I", Integer.TYPE}, {"long", "J", Long.TYPE}, {"boolean", "Z", Boolean.TYPE}, {"short", "S", Short.TYPE}, {"byte", "B", Byte.TYPE}, {"char", "C", Character.TYPE}, {"float", "F", Float.TYPE}, {"double", "D", Double.TYPE}};
    private static final Map NAME_TO_FD = new HashMap();
    private static final Map NAME_TO_CLASS = new HashMap();

    public static void mapNameToPrimitive(ElementContext ctx, Map out) {
        for (int i = 0; i < PRIMITIVES.length; ++i) {
            PrimitiveClassImpl c = new PrimitiveClassImpl(ctx, (String)PRIMITIVES[i][0]);
            out.put(PRIMITIVES[i][0], c);
            out.put(PRIMITIVES[i][1], c);
        }
    }

    public static String getPrimitiveClassForName(String named) {
        return (String)NAME_TO_FD.get(named);
    }

    public static boolean isPrimitive(String name) {
        return NAME_TO_FD.get(name) != null;
    }

    public static final String getFieldDescriptor(String classname) {
        return (String)NAME_TO_FD.get(classname);
    }

    public static final Class getPrimitiveClass(String classname) {
        return (Class)NAME_TO_CLASS.get(classname);
    }

    private PrimitiveClassImpl(ElementContext ctx, String name) {
        super(ctx);
        if (name == null) {
            throw new IllegalArgumentException("null name");
        }
        if (!NAME_TO_FD.containsKey(name)) {
            throw new IllegalArgumentException("Unknown primitive class '" + name + "'");
        }
        this.reallySetSimpleName(name);
    }

    @Override
    public String getQualifiedName() {
        return this.getSimpleName();
    }

    @Override
    public String getFieldDescriptor() {
        return (String)NAME_TO_FD.get(this.getSimpleName());
    }

    @Override
    public boolean isAssignableFrom(JClass c) {
        return c.isPrimitiveType() && c.getSimpleName().equals(this.getSimpleName());
    }

    @Override
    public boolean isPrimitiveType() {
        return true;
    }

    @Override
    public Class getPrimitiveClass() {
        return (Class)NAME_TO_CLASS.get(this.getSimpleName());
    }

    static {
        for (int i = 0; i < PRIMITIVES.length; ++i) {
            NAME_TO_FD.put(PRIMITIVES[i][0], PRIMITIVES[i][1]);
            NAME_TO_CLASS.put(PRIMITIVES[i][0], PRIMITIVES[i][2]);
        }
    }
}

