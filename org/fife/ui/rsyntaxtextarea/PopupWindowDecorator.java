/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import javax.swing.JWindow;

public abstract class PopupWindowDecorator {
    private static PopupWindowDecorator decorator;

    public abstract void decorate(JWindow var1);

    public static PopupWindowDecorator get() {
        return decorator;
    }

    public static void set(PopupWindowDecorator decorator) {
        PopupWindowDecorator.decorator = decorator;
    }
}

