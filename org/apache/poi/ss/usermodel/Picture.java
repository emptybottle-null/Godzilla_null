/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

import java.awt.Dimension;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Shape;
import org.apache.poi.ss.usermodel.Sheet;

public interface Picture
extends Shape {
    public void resize();

    public void resize(double var1);

    public void resize(double var1, double var3);

    public ClientAnchor getPreferredSize();

    public ClientAnchor getPreferredSize(double var1, double var3);

    public Dimension getImageDimension();

    public PictureData getPictureData();

    public ClientAnchor getClientAnchor();

    public Sheet getSheet();
}

