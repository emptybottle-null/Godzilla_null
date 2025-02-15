/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ui.search;

public class FindReplaceButtonsEnableResult {
    private boolean enable;
    private String error;

    public FindReplaceButtonsEnableResult(boolean enable, String error) {
        this.enable = enable;
        this.error = error;
    }

    public boolean getEnable() {
        return this.enable;
    }

    public String getError() {
        return this.error;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}

