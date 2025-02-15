/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

import org.apache.poi.util.Internal;

public interface ClientAnchor {
    public short getCol1();

    public void setCol1(int var1);

    public short getCol2();

    public void setCol2(int var1);

    public int getRow1();

    public void setRow1(int var1);

    public int getRow2();

    public void setRow2(int var1);

    public int getDx1();

    public void setDx1(int var1);

    public int getDy1();

    public void setDy1(int var1);

    public int getDy2();

    public void setDy2(int var1);

    public int getDx2();

    public void setDx2(int var1);

    public void setAnchorType(AnchorType var1);

    public AnchorType getAnchorType();

    public static enum AnchorType {
        MOVE_AND_RESIZE(0),
        DONT_MOVE_DO_RESIZE(1),
        MOVE_DONT_RESIZE(2),
        DONT_MOVE_AND_RESIZE(3);

        public final short value;

        private AnchorType(int value) {
            this.value = (short)value;
        }

        @Internal
        public static AnchorType byId(int value) {
            return AnchorType.values()[value];
        }
    }
}

