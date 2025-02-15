/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.util;

import java.awt.Dimension;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.util.Units;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ImageUtils {
    private static final POILogger logger = POILogFactory.getLogger(ImageUtils.class);
    public static final int PIXEL_DPI = 96;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Unable to fully structure code
     */
    public static Dimension getImageDimension(InputStream is, int type) {
        size = new Dimension();
        switch (type) {
            case 5: 
            case 6: 
            case 7: {
                try {
                    iis = ImageIO.createImageInputStream(is);
                    var4_5 = null;
                    i = ImageIO.getImageReaders(iis);
                    if (i.hasNext()) {
                        r = i.next();
                        try {
                            r.setInput(iis);
                            img = r.read(0);
                            dpi = ImageUtils.getResolution(r);
                            if (dpi[0] == 0) {
                                dpi[0] = 96;
                            }
                            if (dpi[1] == 0) {
                                dpi[1] = 96;
                            }
                            size.width = img.getWidth() * 96 / dpi[0];
                            size.height = img.getHeight() * 96 / dpi[1];
                        } finally {
                            r.dispose();
                        }
                    } else {
                        ImageUtils.logger.log(5, new Object[]{"ImageIO found no images"});
                    }
                    if (iis == null) break;
                    if (var4_5 == null) ** GOTO lbl34
                    try {
                        iis.close();
                    } catch (Throwable var5_7) {
                        var4_5.addSuppressed(var5_7);
                    }
                    break;
lbl34:
                    // 1 sources

                    iis.close();
                    ** break;
                    catch (Throwable var5_8) {
                        try {
                            var4_5 = var5_8;
                            throw var5_8;
                        } catch (Throwable var10_13) {
                            if (iis != null) {
                                if (var4_5 != null) {
                                    try {
                                        iis.close();
                                    } catch (Throwable var11_14) {
                                        var4_5.addSuppressed(var11_14);
                                    }
                                } else {
                                    iis.close();
                                }
                            }
                            throw var10_13;
lbl51:
                            // 1 sources

                        }
                    }
                } catch (IOException e) {
                    ImageUtils.logger.log(5, new Object[]{e});
                }
                break;
            }
            default: {
                ImageUtils.logger.log(5, new Object[]{"Only JPEG, PNG and DIB pictures can be automatically sized"});
            }
        }
        return size;
    }

    public static int[] getResolution(ImageReader r) throws IOException {
        int hdpi = 96;
        int vdpi = 96;
        double mm2inch = 25.4;
        Element node = (Element)r.getImageMetadata(0).getAsTree("javax_imageio_1.0");
        NodeList lst = node.getElementsByTagName("HorizontalPixelSize");
        if (lst != null && lst.getLength() == 1) {
            hdpi = (int)(mm2inch / (double)Float.parseFloat(((Element)lst.item(0)).getAttribute("value")));
        }
        if ((lst = node.getElementsByTagName("VerticalPixelSize")) != null && lst.getLength() == 1) {
            vdpi = (int)(mm2inch / (double)Float.parseFloat(((Element)lst.item(0)).getAttribute("value")));
        }
        return new int[]{hdpi, vdpi};
    }

    public static Dimension setPreferredSize(Picture picture, double scaleX, double scaleY) {
        ClientAnchor anchor = picture.getClientAnchor();
        boolean isHSSF = anchor instanceof HSSFClientAnchor;
        PictureData data = picture.getPictureData();
        Sheet sheet = picture.getSheet();
        Dimension imgSize = ImageUtils.getImageDimension(new ByteArrayInputStream(data.getData()), data.getPictureType());
        Dimension anchorSize = ImageUtils.getDimensionFromAnchor(picture);
        double scaledWidth = scaleX == Double.MAX_VALUE ? imgSize.getWidth() : anchorSize.getWidth() / 9525.0 * scaleX;
        double scaledHeight = scaleY == Double.MAX_VALUE ? imgSize.getHeight() : anchorSize.getHeight() / 9525.0 * scaleY;
        double w = 0.0;
        int col2 = anchor.getCol1();
        int dx2 = 0;
        w = sheet.getColumnWidthInPixels(col2++);
        w = isHSSF ? (w *= 1.0 - (double)anchor.getDx1() / 1024.0) : (w -= (double)anchor.getDx1() / 9525.0);
        while (w < scaledWidth) {
            w += (double)sheet.getColumnWidthInPixels(col2++);
        }
        if (w > scaledWidth) {
            double cw = sheet.getColumnWidthInPixels(--col2);
            double delta = w - scaledWidth;
            dx2 = isHSSF ? (int)((cw - delta) / cw * 1024.0) : (int)((cw - delta) * 9525.0);
            if (dx2 < 0) {
                dx2 = 0;
            }
        }
        anchor.setCol2(col2);
        anchor.setDx2(dx2);
        double h = 0.0;
        int row2 = anchor.getRow1();
        int dy2 = 0;
        h = ImageUtils.getRowHeightInPixels(sheet, row2++);
        h = isHSSF ? (h *= 1.0 - (double)anchor.getDy1() / 256.0) : (h -= (double)anchor.getDy1() / 9525.0);
        while (h < scaledHeight) {
            h += ImageUtils.getRowHeightInPixels(sheet, row2++);
        }
        if (h > scaledHeight) {
            double ch = ImageUtils.getRowHeightInPixels(sheet, --row2);
            double delta = h - scaledHeight;
            dy2 = isHSSF ? (int)((ch - delta) / ch * 256.0) : (int)((ch - delta) * 9525.0);
            if (dy2 < 0) {
                dy2 = 0;
            }
        }
        anchor.setRow2(row2);
        anchor.setDy2(dy2);
        return new Dimension((int)Math.round(scaledWidth * 9525.0), (int)Math.round(scaledHeight * 9525.0));
    }

    public static Dimension getDimensionFromAnchor(Picture picture) {
        ClientAnchor anchor = picture.getClientAnchor();
        boolean isHSSF = anchor instanceof HSSFClientAnchor;
        Sheet sheet = picture.getSheet();
        double w = 0.0;
        int col2 = anchor.getCol1();
        w = sheet.getColumnWidthInPixels(col2++);
        w = isHSSF ? (w *= 1.0 - (double)anchor.getDx1() / 1024.0) : (w -= (double)anchor.getDx1() / 9525.0);
        while (col2 < anchor.getCol2()) {
            w += (double)sheet.getColumnWidthInPixels(col2++);
        }
        w = isHSSF ? (w += (double)anchor.getDx2() / 1024.0 * (double)sheet.getColumnWidthInPixels(col2)) : (w += (double)anchor.getDx2() / 9525.0);
        double h = 0.0;
        int row2 = anchor.getRow1();
        h = ImageUtils.getRowHeightInPixels(sheet, row2++);
        h = isHSSF ? (h *= 1.0 - (double)anchor.getDy1() / 256.0) : (h -= (double)anchor.getDy1() / 9525.0);
        while (row2 < anchor.getRow2()) {
            h += ImageUtils.getRowHeightInPixels(sheet, row2++);
        }
        h = isHSSF ? (h += ImageUtils.getRowHeightInPixels(sheet, row2) * (double)anchor.getDy2() / 256.0) : (h += (double)anchor.getDy2() / 9525.0);
        return new Dimension((int)Math.rint(w *= 9525.0), (int)Math.rint(h *= 9525.0));
    }

    public static double getRowHeightInPixels(Sheet sheet, int rowNum) {
        Row r = sheet.getRow(rowNum);
        double points = r == null ? (double)sheet.getDefaultRowHeightInPoints() : (double)r.getHeightInPoints();
        return (double)Units.toEMU(points) / 9525.0;
    }
}

