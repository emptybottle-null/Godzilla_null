/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.extras.components;

import com.formdev.flatlaf.extras.components.FlatComponentExtension;
import javax.swing.JTextField;

public class FlatTextField
extends JTextField
implements FlatComponentExtension {
    public String getPlaceholderText() {
        return (String)this.getClientProperty("JTextField.placeholderText");
    }

    public void setPlaceholderText(String placeholderText) {
        this.putClientProperty("JTextField.placeholderText", placeholderText);
    }

    public SelectAllOnFocusPolicy getSelectAllOnFocusPolicy() {
        return this.getClientPropertyEnumString("JTextField.selectAllOnFocusPolicy", SelectAllOnFocusPolicy.class, "TextComponent.selectAllOnFocusPolicy", SelectAllOnFocusPolicy.once);
    }

    public void setSelectAllOnFocusPolicy(SelectAllOnFocusPolicy selectAllOnFocusPolicy) {
        this.putClientPropertyEnumString("JTextField.selectAllOnFocusPolicy", selectAllOnFocusPolicy);
    }

    public int getMinimumWidth() {
        return this.getClientPropertyInt((Object)"JComponent.minimumWidth", "Component.minimumWidth");
    }

    public void setMinimumWidth(int minimumWidth) {
        this.putClientProperty("JComponent.minimumWidth", minimumWidth >= 0 ? Integer.valueOf(minimumWidth) : null);
    }

    public boolean isRoundRect() {
        return this.getClientPropertyBoolean((Object)"JComponent.roundRect", false);
    }

    public void setRoundRect(boolean roundRect) {
        this.putClientPropertyBoolean("JComponent.roundRect", roundRect, false);
    }

    public Object getOutline() {
        return this.getClientProperty("JComponent.outline");
    }

    public void setOutline(Object outline) {
        this.putClientProperty("JComponent.outline", outline);
    }

    public static enum SelectAllOnFocusPolicy {
        never,
        once,
        always;

    }
}

