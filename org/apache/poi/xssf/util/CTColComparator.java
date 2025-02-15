/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.util;

import java.util.Comparator;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCol;

public class CTColComparator {
    public static final Comparator<CTCol> BY_MAX = new Comparator<CTCol>(){

        @Override
        public int compare(CTCol col1, CTCol col2) {
            long col1max = col1.getMax();
            long col2max = col2.getMax();
            return Long.compare(col1max, col2max);
        }
    };
    public static final Comparator<CTCol> BY_MIN_MAX = new Comparator<CTCol>(){

        @Override
        public int compare(CTCol col1, CTCol col2) {
            long col2min;
            long col11min = col1.getMin();
            return col11min < (col2min = col2.getMin()) ? -1 : (col11min > col2min ? 1 : BY_MAX.compare(col1, col2));
        }
    };

    private CTColComparator() {
    }
}

