/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.ui.component.model;

import java.text.DecimalFormat;
import util.functions;

public class FileInfo {
    private static final String[] ShowSize = new String[]{"KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB"};
    private long size;

    public FileInfo(String size) {
        this.size = functions.stringToLong(size, 0L);
    }

    public long getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String toString() {
        int em = -1;
        float tmp = this.size;
        float lastTmp = 0.0f;
        if (this.size >= 1024L) {
            while (true) {
                float f;
                tmp /= 1024.0f;
                if (!(f >= 1.0f)) break;
                ++em;
                lastTmp = tmp;
            }
            return new DecimalFormat(".00").format(lastTmp) + ShowSize[em];
        }
        return Long.toString(this.size);
    }
}

