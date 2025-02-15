/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.util;

import java.awt.geom.Dimension2D;

public class Dimension2DDouble
extends Dimension2D {
    double width;
    double height;

    public Dimension2DDouble() {
        this.width = 0.0;
        this.height = 0.0;
    }

    public Dimension2DDouble(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double getWidth() {
        return this.width;
    }

    @Override
    public double getHeight() {
        return this.height;
    }

    @Override
    public void setSize(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Dimension2DDouble) {
            Dimension2DDouble other = (Dimension2DDouble)obj;
            return this.width == other.width && this.height == other.height;
        }
        return false;
    }

    public int hashCode() {
        double sum = this.width + this.height;
        return (int)Math.ceil(sum * (sum + 1.0) / 2.0 + this.width);
    }

    public String toString() {
        return "Dimension2DDouble[" + this.width + ", " + this.height + "]";
    }
}

