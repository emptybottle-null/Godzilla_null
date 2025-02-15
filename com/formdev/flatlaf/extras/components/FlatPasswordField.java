/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.extras.components;

import com.formdev.flatlaf.extras.components.FlatComponentExtension;
import com.formdev.flatlaf.extras.components.FlatTextField;
import javax.swing.JPasswordField;

public class FlatPasswordField
extends JPasswordField
implements FlatComponentExtension {
    public String getPlaceholderText() {
        return (String)this.getClientProperty("JTextField.placeholderText");
    }

    public void setPlaceholderText(String placeholderText) {
        this.putClientProperty("JTextField.placeholderText", placeholderText);
    }

    public FlatTextField.SelectAllOnFocusPolicy getSelectAllOnFocusPolicy() {
        return this.getClientPropertyEnumString("JTextField.selectAllOnFocusPolicy", FlatTextField.SelectAllOnFocusPolicy.class, "TextComponent.selectAllOnFocusPolicy", FlatTextField.SelectAllOnFocusPolicy.once);
    }

    public void setSelectAllOnFocusPolicy(FlatTextField.SelectAllOnFocusPolicy selectAllOnFocusPolicy) {
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
}

