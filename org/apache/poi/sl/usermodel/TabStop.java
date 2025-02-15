/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.usermodel;

public interface TabStop {
    public double getPositionInPoints();

    public void setPositionInPoints(double var1);

    public TabStopType getType();

    public void setType(TabStopType var1);

    public static enum TabStopType {
        LEFT(0, 1),
        CENTER(1, 2),
        RIGHT(2, 3),
        DECIMAL(3, 4);

        public final int nativeId;
        public final int ooxmlId;

        private TabStopType(int nativeId, int ooxmlId) {
            this.nativeId = nativeId;
            this.ooxmlId = ooxmlId;
        }

        public static TabStopType fromNativeId(int nativeId) {
            for (TabStopType tst : TabStopType.values()) {
                if (tst.nativeId != nativeId) continue;
                return tst;
            }
            return null;
        }

        public static TabStopType fromOoxmlId(int ooxmlId) {
            for (TabStopType tst : TabStopType.values()) {
                if (tst.ooxmlId != ooxmlId) continue;
                return tst;
            }
            return null;
        }
    }
}

