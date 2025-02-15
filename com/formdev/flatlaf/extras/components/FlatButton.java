/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.extras.components;

import com.formdev.flatlaf.extras.components.FlatComponentExtension;
import javax.swing.JButton;

public class FlatButton
extends JButton
implements FlatComponentExtension {
    public ButtonType getButtonType() {
        return this.getClientPropertyEnumString("JButton.buttonType", ButtonType.class, null, ButtonType.none);
    }

    public void setButtonType(ButtonType buttonType) {
        if (buttonType == ButtonType.none) {
            buttonType = null;
        }
        this.putClientPropertyEnumString("JButton.buttonType", buttonType);
    }

    public boolean isSquareSize() {
        return this.getClientPropertyBoolean((Object)"JButton.squareSize", false);
    }

    public void setSquareSize(boolean squareSize) {
        this.putClientPropertyBoolean("JButton.squareSize", squareSize, false);
    }

    public int getMinimumWidth() {
        return this.getClientPropertyInt((Object)"JComponent.minimumWidth", "Button.minimumWidth");
    }

    public void setMinimumWidth(int minimumWidth) {
        this.putClientProperty("JComponent.minimumWidth", minimumWidth >= 0 ? Integer.valueOf(minimumWidth) : null);
    }

    public int getMinimumHeight() {
        return this.getClientPropertyInt((Object)"JComponent.minimumHeight", 0);
    }

    public void setMinimumHeight(int minimumHeight) {
        this.putClientProperty("JComponent.minimumHeight", minimumHeight >= 0 ? Integer.valueOf(minimumHeight) : null);
    }

    public Object getOutline() {
        return this.getClientProperty("JComponent.outline");
    }

    public void setOutline(Object outline) {
        this.putClientProperty("JComponent.outline", outline);
    }

    public static enum ButtonType {
        none,
        square,
        roundRect,
        tab,
        help,
        toolBarButton;

    }
}

