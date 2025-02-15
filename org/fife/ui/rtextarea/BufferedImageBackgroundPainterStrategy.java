/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rtextarea;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import org.fife.ui.rtextarea.ImageBackgroundPainterStrategy;
import org.fife.ui.rtextarea.RTextAreaBase;

public class BufferedImageBackgroundPainterStrategy
extends ImageBackgroundPainterStrategy {
    private BufferedImage bgImage;

    public BufferedImageBackgroundPainterStrategy(RTextAreaBase ta) {
        super(ta);
    }

    @Override
    protected void paintImage(Graphics g, int x, int y) {
        if (this.bgImage != null) {
            g.drawImage(this.bgImage, x, y, null);
        }
    }

    @Override
    protected void rescaleImage(int width, int height, int hint) {
        Image master = this.getMasterImage();
        if (master != null) {
            HashMap<RenderingHints.Key, Object> hints = new HashMap<RenderingHints.Key, Object>();
            switch (hint) {
                default: 
            }
            hints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            this.bgImage = this.createAcceleratedImage(width, height);
            Graphics2D g = this.bgImage.createGraphics();
            g.addRenderingHints(hints);
            g.drawImage(master, 0, 0, width, height, null);
            g.dispose();
        } else {
            this.bgImage = null;
        }
    }

    private BufferedImage createAcceleratedImage(int width, int height) {
        GraphicsConfiguration gc = this.getRTextAreaBase().getGraphicsConfiguration();
        BufferedImage image = gc.createCompatibleImage(width, height);
        return image;
    }
}

