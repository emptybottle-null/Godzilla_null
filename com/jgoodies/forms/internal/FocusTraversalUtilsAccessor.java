/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jgoodies.forms.internal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.swing.AbstractButton;

public final class FocusTraversalUtilsAccessor {
    private static final String FOCUS_TRAVERSAL_UTILS_NAME = "com.jgoodies.jsdl.common.focus.FocusTraversalUtils";
    private static Method groupMethod = null;

    private FocusTraversalUtilsAccessor() {
    }

    public static void tryToBuildAFocusGroup(AbstractButton ... buttons) {
        if (groupMethod == null) {
            return;
        }
        try {
            groupMethod.invoke(null, new Object[]{buttons});
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException invocationTargetException) {
            // empty catch block
        }
    }

    private static Method getGroupMethod() {
        try {
            Class<?> clazz = Class.forName(FOCUS_TRAVERSAL_UTILS_NAME);
            return clazz.getMethod("group", AbstractButton[].class);
        } catch (ClassNotFoundException e) {
        } catch (SecurityException e) {
        } catch (NoSuchMethodException noSuchMethodException) {
            // empty catch block
        }
        return null;
    }

    static {
        groupMethod = FocusTraversalUtilsAccessor.getGroupMethod();
    }
}

