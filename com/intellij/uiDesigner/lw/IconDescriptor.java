/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.intellij.uiDesigner.lw;

import javax.swing.Icon;

public class IconDescriptor {
    private String myIconPath;
    private Icon myIcon;

    public IconDescriptor(String iconPath) {
        this.myIconPath = iconPath;
    }

    public String getIconPath() {
        return this.myIconPath;
    }

    public Icon getIcon() {
        return this.myIcon;
    }

    public void setIcon(Icon icon) {
        this.myIcon = icon;
    }
}

