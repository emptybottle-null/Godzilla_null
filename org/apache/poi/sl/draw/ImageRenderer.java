/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw;

import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.util.Dimension2DDouble;

public interface ImageRenderer {
    public boolean canRender(String var1);

    public void loadImage(InputStream var1, String var2) throws IOException;

    public void loadImage(byte[] var1, String var2) throws IOException;

    public Rectangle2D getNativeBounds();

    public Rectangle2D getBounds();

    default public Dimension2D getDimension() {
        Rectangle2D r = this.getBounds();
        return new Dimension2DDouble(Math.abs(r.getWidth()), Math.abs(r.getHeight()));
    }

    public void setAlpha(double var1);

    public BufferedImage getImage();

    public BufferedImage getImage(Dimension2D var1);

    public boolean drawImage(Graphics2D var1, Rectangle2D var2);

    public boolean drawImage(Graphics2D var1, Rectangle2D var2, Insets var3);

    default public GenericRecord getGenericRecord() {
        return null;
    }
}

