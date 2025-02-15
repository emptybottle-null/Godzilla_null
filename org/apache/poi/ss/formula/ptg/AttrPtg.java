/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.ptg;

import org.apache.poi.ss.formula.ptg.ControlPtg;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public final class AttrPtg
extends ControlPtg {
    public static final byte sid = 25;
    private static final int SIZE = 4;
    private static final BitField semiVolatile = BitFieldFactory.getInstance(1);
    private static final BitField optiIf = BitFieldFactory.getInstance(2);
    private static final BitField optiChoose = BitFieldFactory.getInstance(4);
    private static final BitField optiSkip = BitFieldFactory.getInstance(8);
    private static final BitField optiSum = BitFieldFactory.getInstance(16);
    private static final BitField baxcel = BitFieldFactory.getInstance(32);
    private static final BitField space = BitFieldFactory.getInstance(64);
    public static final AttrPtg SUM = new AttrPtg(16, 0, null, -1);
    private final byte _options;
    private final short _data;
    private final int[] _jumpTable;
    private final int _chooseFuncOffset;

    public AttrPtg(LittleEndianInput in) {
        this._options = in.readByte();
        this._data = in.readShort();
        if (this.isOptimizedChoose()) {
            int[] jumpTable = new int[this._data];
            for (int i = 0; i < jumpTable.length; ++i) {
                jumpTable[i] = in.readUShort();
            }
            this._jumpTable = jumpTable;
            this._chooseFuncOffset = in.readUShort();
        } else {
            this._jumpTable = null;
            this._chooseFuncOffset = -1;
        }
    }

    private AttrPtg(int options, int data, int[] jt, int chooseFuncOffset) {
        this._options = (byte)options;
        this._data = (short)data;
        this._jumpTable = jt;
        this._chooseFuncOffset = chooseFuncOffset;
    }

    public static AttrPtg createSpace(int type, int count) {
        int data = type & 0xFF | count << 8 & 0xFFFF;
        return new AttrPtg(space.set(0), data, null, -1);
    }

    public static AttrPtg createIf(int dist) {
        return new AttrPtg(optiIf.set(0), dist, null, -1);
    }

    public static AttrPtg createSkip(int dist) {
        return new AttrPtg(optiSkip.set(0), dist, null, -1);
    }

    public static AttrPtg getSumSingle() {
        return new AttrPtg(optiSum.set(0), 0, null, -1);
    }

    public boolean isSemiVolatile() {
        return semiVolatile.isSet(this._options);
    }

    public boolean isOptimizedIf() {
        return optiIf.isSet(this._options);
    }

    public boolean isOptimizedChoose() {
        return optiChoose.isSet(this._options);
    }

    public boolean isSum() {
        return optiSum.isSet(this._options);
    }

    public boolean isSkip() {
        return optiSkip.isSet(this._options);
    }

    private boolean isBaxcel() {
        return baxcel.isSet(this._options);
    }

    public boolean isSpace() {
        return space.isSet(this._options);
    }

    public short getData() {
        return this._data;
    }

    public int[] getJumpTable() {
        return (int[])this._jumpTable.clone();
    }

    public int getChooseFuncOffset() {
        if (this._jumpTable == null) {
            throw new IllegalStateException("Not tAttrChoose");
        }
        return this._chooseFuncOffset;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append(this.getClass().getName()).append(" [");
        if (this.isSemiVolatile()) {
            sb.append("volatile ");
        }
        if (this.isSpace()) {
            sb.append("space count=").append(this._data >> 8 & 0xFF);
            sb.append(" type=").append(this._data & 0xFF).append(" ");
        }
        if (this.isOptimizedIf()) {
            sb.append("if dist=").append(this._data);
        } else if (this.isOptimizedChoose()) {
            sb.append("choose nCases=").append(this._data);
        } else if (this.isSkip()) {
            sb.append("skip dist=").append(this._data);
        } else if (this.isSum()) {
            sb.append("sum ");
        } else if (this.isBaxcel()) {
            sb.append("assign ");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public void write(LittleEndianOutput out) {
        out.writeByte(25 + this.getPtgClass());
        out.writeByte(this._options);
        out.writeShort(this._data);
        int[] jt = this._jumpTable;
        if (jt != null) {
            for (int i = 0; i < jt.length; ++i) {
                out.writeShort(jt[i]);
            }
            out.writeShort(this._chooseFuncOffset);
        }
    }

    @Override
    public int getSize() {
        if (this._jumpTable != null) {
            return 4 + (this._jumpTable.length + 1) * 2;
        }
        return 4;
    }

    public String toFormulaString(String[] operands) {
        if (space.isSet(this._options)) {
            return operands[0];
        }
        if (optiIf.isSet(this._options)) {
            return this.toFormulaString() + "(" + operands[0] + ")";
        }
        if (optiSkip.isSet(this._options)) {
            return this.toFormulaString() + operands[0];
        }
        return this.toFormulaString() + "(" + operands[0] + ")";
    }

    public int getNumberOfOperands() {
        return 1;
    }

    public int getType() {
        return -1;
    }

    @Override
    public String toFormulaString() {
        if (semiVolatile.isSet(this._options)) {
            return "ATTR(semiVolatile)";
        }
        if (optiIf.isSet(this._options)) {
            return "IF";
        }
        if (optiChoose.isSet(this._options)) {
            return "CHOOSE";
        }
        if (optiSkip.isSet(this._options)) {
            return "";
        }
        if (optiSum.isSet(this._options)) {
            return "SUM";
        }
        if (baxcel.isSet(this._options)) {
            return "ATTR(baxcel)";
        }
        if (space.isSet(this._options)) {
            return "";
        }
        return "UNKNOWN ATTRIBUTE";
    }

    @Override
    public AttrPtg copy() {
        return this;
    }

    public static final class SpaceType {
        public static final int SPACE_BEFORE = 0;
        public static final int CR_BEFORE = 1;
        public static final int SPACE_BEFORE_OPEN_PAREN = 2;
        public static final int CR_BEFORE_OPEN_PAREN = 3;
        public static final int SPACE_BEFORE_CLOSE_PAREN = 4;
        public static final int CR_BEFORE_CLOSE_PAREN = 5;
        public static final int SPACE_AFTER_EQUALITY = 6;

        private SpaceType() {
        }
    }
}

