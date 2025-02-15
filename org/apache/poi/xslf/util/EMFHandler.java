/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.util;

import java.awt.Graphics2D;
import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.sl.draw.BitmapImageRenderer;
import org.apache.poi.sl.draw.DrawPictureShape;
import org.apache.poi.sl.draw.EmbeddedExtractor;
import org.apache.poi.sl.draw.ImageRenderer;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.util.Internal;
import org.apache.poi.xslf.util.MFProxy;
import org.apache.poi.xslf.util.PPTX2PNG;

@Internal
class EMFHandler
extends MFProxy {
    private ImageRenderer imgr = null;
    private InputStream is;

    EMFHandler() {
    }

    @Override
    public void parse(File file) throws IOException {
        this.is = file.toURI().toURL().openStream();
        this.parse(this.is);
    }

    @Override
    public void parse(InputStream is) throws IOException {
        this.imgr = DrawPictureShape.getImageRenderer(null, this.getContentType());
        if (this.imgr instanceof BitmapImageRenderer) {
            throw new PPTX2PNG.NoScratchpadException();
        }
        this.imgr.loadImage(is, this.getContentType());
        if (this.ignoreParse) {
            try {
                this.imgr.getDimension();
            } catch (Exception exception) {
                // empty catch block
            }
        }
    }

    protected String getContentType() {
        return PictureData.PictureType.EMF.contentType;
    }

    @Override
    public Dimension2D getSize() {
        return this.imgr.getDimension();
    }

    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public void draw(Graphics2D ctx) {
        Dimension2D dim = this.getSize();
        this.imgr.drawImage(ctx, new Rectangle2D.Double(0.0, 0.0, dim.getWidth(), dim.getHeight()));
    }

    @Override
    public void close() throws IOException {
        if (this.is != null) {
            try {
                this.is.close();
            } finally {
                this.is = null;
            }
        }
    }

    @Override
    public GenericRecord getRoot() {
        return this.imgr.getGenericRecord();
    }

    @Override
    public Iterable<EmbeddedExtractor.EmbeddedPart> getEmbeddings(int slideNo) {
        return this.imgr instanceof EmbeddedExtractor ? ((EmbeddedExtractor)((Object)this.imgr)).getEmbeddings() : Collections.emptyList();
    }
}

