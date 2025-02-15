/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.image;

import java.awt.Dimension;
import java.awt.Rectangle;
import org.apache.poi.util.Internal;

@Internal
public class ImageHeaderPICT {
    public static final int PICT_HEADER_OFFSET = 512;
    public static final double DEFAULT_RESOLUTION = 72.0;
    private static final byte[] V2_HEADER = new byte[]{0, 17, 2, -1, 12, 0, -1, -2, 0, 0};
    private final Rectangle bounds;
    private final double hRes;
    private final double vRes;

    public ImageHeaderPICT(byte[] data, int off) {
        int offset = off;
        int y1 = ImageHeaderPICT.readUnsignedShort(data, offset += 2);
        int x1 = ImageHeaderPICT.readUnsignedShort(data, offset += 2);
        int y2 = ImageHeaderPICT.readUnsignedShort(data, offset += 2);
        int x2 = ImageHeaderPICT.readUnsignedShort(data, offset += 2);
        offset += 2;
        boolean isV2 = true;
        for (byte b : V2_HEADER) {
            if (b == data[offset++]) continue;
            isV2 = false;
            break;
        }
        if (isV2) {
            this.hRes = ImageHeaderPICT.readFixedPoint(data, offset);
            this.vRes = ImageHeaderPICT.readFixedPoint(data, offset += 4);
            offset += 4;
        } else {
            this.hRes = 72.0;
            this.vRes = 72.0;
        }
        this.bounds = new Rectangle(x1, y1, x2 - x1, y2 - y1);
    }

    public Dimension getSize() {
        int height = (int)Math.round((double)this.bounds.height * 72.0 / this.vRes);
        int width = (int)Math.round((double)this.bounds.width * 72.0 / this.hRes);
        return new Dimension(width, height);
    }

    public Rectangle getBounds() {
        return this.bounds;
    }

    private static int readUnsignedShort(byte[] data, int offset) {
        int b0 = data[offset] & 0xFF;
        int b1 = data[offset + 1] & 0xFF;
        return b0 << 8 | b1;
    }

    private static double readFixedPoint(byte[] data, int offset) {
        int b0 = data[offset] & 0xFF;
        int b1 = data[offset + 1] & 0xFF;
        int b2 = data[offset + 2] & 0xFF;
        int b3 = data[offset + 3] & 0xFF;
        int i = b0 << 24 | b1 << 16 | b2 << 8 | b3;
        return (double)i / 65536.0;
    }
}

