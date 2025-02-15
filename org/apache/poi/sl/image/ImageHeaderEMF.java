/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.image;

import java.awt.Dimension;
import java.awt.Rectangle;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;

@Internal
public class ImageHeaderEMF {
    private static final POILogger LOG = POILogFactory.getLogger(ImageHeaderEMF.class);
    private static final String EMF_SIGNATURE = " EMF";
    private final Rectangle deviceBounds;

    public ImageHeaderEMF(byte[] data, int off) {
        int offset = off;
        int type = (int)LittleEndian.getUInt(data, offset);
        offset += 4;
        if (type != 1) {
            LOG.log(5, "Invalid EMF picture - invalid type");
            this.deviceBounds = new Rectangle(0, 0, 200, 200);
            return;
        }
        int left = LittleEndian.getInt(data, offset += 4);
        int top = LittleEndian.getInt(data, offset += 4);
        int right = LittleEndian.getInt(data, offset += 4);
        int bottom = LittleEndian.getInt(data, offset += 4);
        offset += 4;
        this.deviceBounds = new Rectangle(left, top, right - left, bottom - top);
        String signature = new String(data, offset += 16, EMF_SIGNATURE.length(), LocaleUtil.CHARSET_1252);
        if (!EMF_SIGNATURE.equals(signature)) {
            LOG.log(5, "Invalid EMF picture - invalid signature");
        }
    }

    public Dimension getSize() {
        return this.deviceBounds.getSize();
    }

    public Rectangle getBounds() {
        return this.deviceBounds;
    }
}

