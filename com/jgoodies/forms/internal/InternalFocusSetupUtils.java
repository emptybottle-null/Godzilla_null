/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jgoodies.forms.internal;

import com.jgoodies.common.base.Preconditions;
import com.jgoodies.forms.util.FocusTraversalType;
import java.awt.Component;
import java.awt.ContainerOrderFocusTraversalPolicy;
import java.awt.FocusTraversalPolicy;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JComponent;
import javax.swing.LayoutFocusTraversalPolicy;

public final class InternalFocusSetupUtils {
    private static final String JGContainerOrderFocusTraversalPolicy_NAME = "com.jgoodies.jsdl.common.focus.JGContainerOrderFocusTraversalPolicy";
    private static final String JGLayoutFocusTraversalPolicy_NAME = "com.jgoodies.jsdl.common.focus.JGLayoutFocusTraversalPolicy";
    private static Constructor<FocusTraversalPolicy> containerOrderFTPConstructor = null;
    private static Constructor<FocusTraversalPolicy> layoutFTPConstructor = null;

    private InternalFocusSetupUtils() {
    }

    public static void checkValidFocusTraversalSetup(FocusTraversalPolicy policy, FocusTraversalType type, Component initialComponent) {
        Preconditions.checkState(policy != null && type == null && initialComponent == null || policy == null, "Either use #focusTraversalPolicy or #focusTraversalType plus optional #initialComponent); don't mix them.");
    }

    public static void setupFocusTraversalPolicyAndProvider(JComponent container, FocusTraversalPolicy policy, FocusTraversalType type, Component initialComponent) {
        container.setFocusTraversalPolicy(InternalFocusSetupUtils.getOrCreateFocusTraversalPolicy(policy, type, initialComponent));
        container.setFocusTraversalPolicyProvider(true);
    }

    public static FocusTraversalPolicy getOrCreateFocusTraversalPolicy(FocusTraversalPolicy policy, FocusTraversalType type, Component initialComponent) {
        if (policy != null) {
            return policy;
        }
        if (type == FocusTraversalType.CONTAINER_ORDER) {
            return InternalFocusSetupUtils.createContainerOrderFocusTraversalPolicy(initialComponent);
        }
        return InternalFocusSetupUtils.createLayoutFocusTraversalPolicy(initialComponent);
    }

    private static FocusTraversalPolicy createContainerOrderFocusTraversalPolicy(Component initialComponent) {
        if (containerOrderFTPConstructor != null) {
            try {
                return containerOrderFTPConstructor.newInstance(initialComponent);
            } catch (IllegalArgumentException ex) {
            } catch (InstantiationException ex) {
            } catch (IllegalAccessException ex) {
            } catch (InvocationTargetException invocationTargetException) {
                // empty catch block
            }
        }
        return new ContainerOrderFocusTraversalPolicy();
    }

    private static FocusTraversalPolicy createLayoutFocusTraversalPolicy(Component initialComponent) {
        if (layoutFTPConstructor != null) {
            try {
                return layoutFTPConstructor.newInstance(initialComponent);
            } catch (IllegalArgumentException ex) {
            } catch (InstantiationException ex) {
            } catch (IllegalAccessException ex) {
            } catch (InvocationTargetException invocationTargetException) {
                // empty catch block
            }
        }
        return new LayoutFocusTraversalPolicy();
    }

    private static Constructor<FocusTraversalPolicy> getContainerOrderFTPConstructor() {
        try {
            return Class.forName(JGContainerOrderFocusTraversalPolicy_NAME).getConstructor(Component.class);
        } catch (SecurityException ex) {
        } catch (NoSuchMethodException ex) {
        } catch (ClassNotFoundException classNotFoundException) {
            // empty catch block
        }
        return null;
    }

    private static Constructor<FocusTraversalPolicy> getLayoutFTPConstructor() {
        try {
            return Class.forName(JGLayoutFocusTraversalPolicy_NAME).getConstructor(Component.class);
        } catch (SecurityException ex) {
        } catch (NoSuchMethodException ex) {
        } catch (ClassNotFoundException classNotFoundException) {
            // empty catch block
        }
        return null;
    }

    static {
        containerOrderFTPConstructor = InternalFocusSetupUtils.getContainerOrderFTPConstructor();
        layoutFTPConstructor = InternalFocusSetupUtils.getLayoutFTPConstructor();
    }
}

