/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.extras.components;

import com.formdev.flatlaf.extras.components.FlatComponentExtension;
import javax.swing.JProgressBar;

public class FlatProgressBar
extends JProgressBar
implements FlatComponentExtension {
    public boolean isLargeHeight() {
        return this.getClientPropertyBoolean((Object)"JProgressBar.largeHeight", false);
    }

    public void setLargeHeight(boolean largeHeight) {
        this.putClientPropertyBoolean("JProgressBar.largeHeight", largeHeight, false);
    }

    public boolean isSquare() {
        return this.getClientPropertyBoolean((Object)"JProgressBar.square", false);
    }

    public void setSquare(boolean square) {
        this.putClientPropertyBoolean("JProgressBar.square", square, false);
    }
}

