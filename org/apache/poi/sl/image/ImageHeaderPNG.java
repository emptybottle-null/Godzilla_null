/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.image;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.RecordFormatException;

public final class ImageHeaderPNG {
    private static final int MAGIC_OFFSET = 16;
    private byte[] data;

    public ImageHeaderPNG(byte[] data) {
        this.data = data;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public byte[] extractPNG() {
        try (ByteArrayInputStream is = new ByteArrayInputStream(this.data);){
            if (((InputStream)is).skip(16L) != 16L) return this.data;
            if (FileMagic.valueOf(is) != FileMagic.PNG) return this.data;
            byte[] byArray = IOUtils.toByteArray(is);
            return byArray;
        } catch (IOException e) {
            throw new RecordFormatException("Unable to parse PNG header", e);
        }
    }
}

