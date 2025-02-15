/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.stream.MemoryCacheImageInputStream;
import org.apache.poi.sl.draw.ImageRenderer;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;

public class BitmapImageRenderer
implements ImageRenderer {
    private static final POILogger LOG = POILogFactory.getLogger(BitmapImageRenderer.class);
    protected BufferedImage img;

    @Override
    public boolean canRender(String contentType) {
        PictureData.PictureType[] pts;
        for (PictureData.PictureType pt : pts = new PictureData.PictureType[]{PictureData.PictureType.JPEG, PictureData.PictureType.PNG, PictureData.PictureType.BMP, PictureData.PictureType.GIF}) {
            if (!pt.contentType.equalsIgnoreCase(contentType)) continue;
            return true;
        }
        return false;
    }

    @Override
    public void loadImage(InputStream data, String contentType) throws IOException {
        this.img = BitmapImageRenderer.readImage(data, contentType);
    }

    @Override
    public void loadImage(byte[] data, String contentType) throws IOException {
        this.img = BitmapImageRenderer.readImage(new ByteArrayInputStream(data), contentType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static BufferedImage readImage(InputStream data, String contentType) throws IOException {
        ByteArrayInputStream bis;
        IOException lastException = null;
        BufferedImage img = null;
        if (data instanceof ByteArrayInputStream) {
            bis = (ByteArrayInputStream)data;
        } else {
            ByteArrayOutputStream bos = new ByteArrayOutputStream(262143);
            IOUtils.copy(data, bos);
            bis = new ByteArrayInputStream(bos.toByteArray());
        }
        try (MemoryCacheImageInputStream iis = new MemoryCacheImageInputStream(bis);){
            Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
            while (img == null && iter.hasNext()) {
                ImageReader reader = iter.next();
                ImageReadParam param = reader.getDefaultReadParam();
                block15: for (int mode = 0; img == null && mode < 3; ++mode) {
                    lastException = null;
                    if (mode > 0) {
                        bis.reset();
                        iis.close();
                        iis = new MemoryCacheImageInputStream(bis);
                    }
                    try {
                        switch (mode) {
                            case 0: {
                                reader.setInput(iis, false, true);
                                img = reader.read(0, param);
                                break;
                            }
                            case 1: {
                                Iterator<ImageTypeSpecifier> imageTypes = reader.getImageTypes(0);
                                while (imageTypes.hasNext()) {
                                    ImageTypeSpecifier imageTypeSpecifier = imageTypes.next();
                                    int bufferedImageType = imageTypeSpecifier.getBufferedImageType();
                                    if (bufferedImageType != 10) continue;
                                    param.setDestinationType(imageTypeSpecifier);
                                    break;
                                }
                                reader.setInput(iis, false, true);
                                img = reader.read(0, param);
                                break;
                            }
                            case 2: {
                                reader.setInput(iis, false, true);
                                int height = reader.getHeight(0);
                                int width = reader.getWidth(0);
                                Iterator<ImageTypeSpecifier> imageTypes = reader.getImageTypes(0);
                                if (!imageTypes.hasNext()) {
                                    lastException = new IOException("unable to load even a truncated version of the image.");
                                    break;
                                }
                                ImageTypeSpecifier imageTypeSpecifier = imageTypes.next();
                                img = imageTypeSpecifier.createBufferedImage(width, height);
                                param.setDestination(img);
                                try {
                                    reader.read(0, param);
                                    continue block15;
                                } finally {
                                    int y;
                                    if (img.getType() != 2 && (y = BitmapImageRenderer.findTruncatedBlackBox(img, width, height)) < height) {
                                        BufferedImage argbImg = new BufferedImage(width, height, 2);
                                        Graphics2D g = argbImg.createGraphics();
                                        g.clipRect(0, 0, width, y);
                                        g.drawImage((Image)img, 0, 0, null);
                                        g.dispose();
                                        img.flush();
                                        img = argbImg;
                                    }
                                }
                            }
                        }
                        continue;
                    } catch (IOException e) {
                        if (mode >= 2) continue;
                        lastException = e;
                        continue;
                    } catch (RuntimeException e) {
                        if (mode >= 2) continue;
                        lastException = new IOException("ImageIO runtime exception - " + (mode == 0 ? "normal" : "fallback"), e);
                    }
                }
                reader.dispose();
            }
        }
        if (img == null) {
            if (lastException != null) {
                throw lastException;
            }
            LOG.log(5, "Content-type: " + contentType + " is not support. Image ignored.");
            return null;
        }
        if (img.getType() != 2) {
            BufferedImage argbImg = new BufferedImage(img.getWidth(), img.getHeight(), 2);
            Graphics g = argbImg.getGraphics();
            g.drawImage(img, 0, 0, null);
            g.dispose();
            return argbImg;
        }
        return img;
    }

    private static int findTruncatedBlackBox(BufferedImage img, int width, int height) {
        for (int h = height - 1; h > 0; --h) {
            for (int w = width - 1; w > 0; w -= width / 10) {
                int p = img.getRGB(w, h);
                if (p == -16777216) continue;
                return h + 1;
            }
        }
        return 0;
    }

    @Override
    public BufferedImage getImage() {
        return this.img;
    }

    @Override
    public BufferedImage getImage(Dimension2D dim) {
        if (this.img == null) {
            return this.img;
        }
        double w_old = this.img.getWidth();
        double h_old = this.img.getHeight();
        double w_new = dim.getWidth();
        double h_new = dim.getHeight();
        if (w_old == w_new && h_old == h_new) {
            return this.img;
        }
        BufferedImage scaled = new BufferedImage((int)w_new, (int)h_new, 2);
        AffineTransform at = new AffineTransform();
        at.scale(w_new / w_old, h_new / h_old);
        AffineTransformOp scaleOp = new AffineTransformOp(at, 2);
        scaleOp.filter(this.img, scaled);
        return scaled;
    }

    @Override
    public Rectangle2D getBounds() {
        return this.img == null ? new Rectangle2D.Double() : new Rectangle2D.Double(0.0, 0.0, this.img.getWidth(), this.img.getHeight());
    }

    @Override
    public void setAlpha(double alpha) {
        this.img = BitmapImageRenderer.setAlpha(this.img, alpha);
    }

    public static BufferedImage setAlpha(BufferedImage image, double alpha) {
        if (image == null) {
            return new BufferedImage(1, 1, 2);
        }
        if (alpha == 0.0) {
            return image;
        }
        float[] scalefactors = new float[]{1.0f, 1.0f, 1.0f, (float)alpha};
        float[] offsets = new float[]{0.0f, 0.0f, 0.0f, 0.0f};
        RescaleOp op = new RescaleOp(scalefactors, offsets, null);
        return op.filter(image, null);
    }

    @Override
    public boolean drawImage(Graphics2D graphics, Rectangle2D anchor) {
        return this.drawImage(graphics, anchor, null);
    }

    @Override
    public boolean drawImage(Graphics2D graphics, Rectangle2D anchor, Insets clip) {
        if (this.img == null) {
            return false;
        }
        boolean isClipped = true;
        if (clip == null) {
            isClipped = false;
            clip = new Insets(0, 0, 0, 0);
        }
        int iw = this.img.getWidth();
        int ih = this.img.getHeight();
        double cw = (double)(100000 - clip.left - clip.right) / 100000.0;
        double ch = (double)(100000 - clip.top - clip.bottom) / 100000.0;
        double sx = anchor.getWidth() / ((double)iw * cw);
        double sy = anchor.getHeight() / ((double)ih * ch);
        double tx = anchor.getX() - (double)iw * sx * (double)clip.left / 100000.0;
        double ty = anchor.getY() - (double)ih * sy * (double)clip.top / 100000.0;
        AffineTransform at = new AffineTransform(sx, 0.0, 0.0, sy, tx, ty);
        Shape clipOld = graphics.getClip();
        if (isClipped) {
            graphics.clip(anchor.getBounds2D());
        }
        graphics.drawRenderedImage(this.img, at);
        graphics.setClip(clipOld);
        return true;
    }

    @Override
    public Rectangle2D getNativeBounds() {
        return new Rectangle2D.Double(0.0, 0.0, this.img.getWidth(), this.img.getHeight());
    }
}

