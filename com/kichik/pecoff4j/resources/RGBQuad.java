/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.resources;

public class RGBQuad {
    private int blue;
    private int green;
    private int red;
    private int reserved;

    public int getBlue() {
        return this.blue;
    }

    public int getGreen() {
        return this.green;
    }

    public int getRed() {
        return this.red;
    }

    public int getReserved() {
        return this.reserved;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public void setReserved(int reserved) {
        this.reserved = reserved;
    }
}

