/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.usermodel;

public interface LineDecoration {
    public DecorationShape getHeadShape();

    public DecorationSize getHeadWidth();

    public DecorationSize getHeadLength();

    public DecorationShape getTailShape();

    public DecorationSize getTailWidth();

    public DecorationSize getTailLength();

    public static enum DecorationSize {
        SMALL(0, 1),
        MEDIUM(1, 2),
        LARGE(2, 3);

        public final int nativeId;
        public final int ooxmlId;

        private DecorationSize(int nativeId, int ooxmlId) {
            this.nativeId = nativeId;
            this.ooxmlId = ooxmlId;
        }

        public static DecorationSize fromNativeId(int nativeId) {
            for (DecorationSize ld : DecorationSize.values()) {
                if (ld.nativeId != nativeId) continue;
                return ld;
            }
            return null;
        }

        public static DecorationSize fromOoxmlId(int ooxmlId) {
            for (DecorationSize ds : DecorationSize.values()) {
                if (ds.ooxmlId != ooxmlId) continue;
                return ds;
            }
            return null;
        }
    }

    public static enum DecorationShape {
        NONE(0, 1),
        TRIANGLE(1, 2),
        STEALTH(2, 3),
        DIAMOND(3, 4),
        OVAL(4, 5),
        ARROW(5, 6);

        public final int nativeId;
        public final int ooxmlId;

        private DecorationShape(int nativeId, int ooxmlId) {
            this.nativeId = nativeId;
            this.ooxmlId = ooxmlId;
        }

        public static DecorationShape fromNativeId(int nativeId) {
            for (DecorationShape ld : DecorationShape.values()) {
                if (ld.nativeId != nativeId) continue;
                return ld;
            }
            return null;
        }

        public static DecorationShape fromOoxmlId(int ooxmlId) {
            for (DecorationShape ds : DecorationShape.values()) {
                if (ds.ooxmlId != ooxmlId) continue;
                return ds;
            }
            return null;
        }
    }
}

