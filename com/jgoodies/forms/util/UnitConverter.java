/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jgoodies.forms.util;

import java.awt.Component;

public interface UnitConverter {
    public int inchAsPixel(double var1, Component var3);

    public int millimeterAsPixel(double var1, Component var3);

    public int centimeterAsPixel(double var1, Component var3);

    public int pointAsPixel(int var1, Component var2);

    public int dialogUnitXAsPixel(int var1, Component var2);

    public int dialogUnitYAsPixel(int var1, Component var2);
}

