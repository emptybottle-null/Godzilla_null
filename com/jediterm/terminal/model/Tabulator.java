/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.model;

public interface Tabulator {
    public void clearTabStop(int var1);

    public void clearAllTabStops();

    public int getNextTabWidth(int var1);

    public int getPreviousTabWidth(int var1);

    public int nextTab(int var1);

    public int previousTab(int var1);

    public void setTabStop(int var1);

    public void resize(int var1);
}

