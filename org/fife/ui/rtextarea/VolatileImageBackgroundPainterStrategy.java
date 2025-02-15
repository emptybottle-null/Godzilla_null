/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rtextarea;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.VolatileImage;
import org.fife.ui.rtextarea.ImageBackgroundPainterStrategy;
import org.fife.ui.rtextarea.RTextAreaBase;

public class VolatileImageBackgroundPainterStrategy
extends ImageBackgroundPainterStrategy {
    private VolatileImage bgImage;

    public VolatileImageBackgroundPainterStrategy(RTextAreaBase ta) {
        super(ta);
    }

    @Override
    protected void paintImage(Graphics g, int x, int y) {
        if (this.bgImage != null) {
            do {
                int rc;
                if ((rc = this.bgImage.validate(null)) == 1) {
                    this.renderImage(this.bgImage.getWidth(), this.bgImage.getHeight(), this.getScalingHint());
                }
                g.drawImage(this.bgImage, x, y, null);
            } while (this.bgImage.contentsLost());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void renderImage(int width, int height, int hint) {
        Image master = this.getMasterImage();
        if (master != null) {
            do {
                Image i = master.getScaledInstance(width, height, hint);
                this.tracker.addImage(i, 1);
                try {
                    this.tracker.waitForID(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    this.bgImage = null;
                    return;
                } finally {
                    this.tracker.removeImage(i, 1);
                }
                this.bgImage.getGraphics().drawImage(i, 0, 0, null);
                this.tracker.addImage(this.bgImage, 0);
                try {
                    this.tracker.waitForID(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    this.bgImage = null;
                    return;
                } finally {
                    this.tracker.removeImage(this.bgImage, 0);
                }
            } while (this.bgImage.contentsLost());
        } else {
            this.bgImage = null;
        }
    }

    @Override
    protected void rescaleImage(int width, int height, int hint) {
        this.bgImage = this.getRTextAreaBase().createVolatileImage(width, height);
        if (this.bgImage != null) {
            this.renderImage(width, height, hint);
        }
    }
}

