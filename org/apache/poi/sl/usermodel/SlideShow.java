/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.usermodel;

import java.awt.Dimension;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import org.apache.poi.common.usermodel.fonts.FontInfo;
import org.apache.poi.extractor.POITextExtractor;
import org.apache.poi.sl.usermodel.MasterSheet;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.Slide;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.sl.usermodel.TextRun;

public interface SlideShow<S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>>
extends Closeable {
    public Slide<S, P> createSlide() throws IOException;

    public List<? extends Slide<S, P>> getSlides();

    public MasterSheet<S, P> createMasterSheet() throws IOException;

    public List<? extends MasterSheet<S, P>> getSlideMasters();

    public Dimension getPageSize();

    public void setPageSize(Dimension var1);

    public List<? extends PictureData> getPictureData();

    public PictureData addPicture(byte[] var1, PictureData.PictureType var2) throws IOException;

    public PictureData addPicture(InputStream var1, PictureData.PictureType var2) throws IOException;

    public PictureData addPicture(File var1, PictureData.PictureType var2) throws IOException;

    public PictureData findPictureData(byte[] var1);

    public void write(OutputStream var1) throws IOException;

    public POITextExtractor getMetadataTextExtractor();

    public Object getPersistDocument();

    public FontInfo addFont(InputStream var1) throws IOException;

    public List<? extends FontInfo> getFonts();
}

