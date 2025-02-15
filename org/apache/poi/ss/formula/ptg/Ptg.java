/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.ptg;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.ss.formula.ptg.AddPtg;
import org.apache.poi.ss.formula.ptg.Area3DPtg;
import org.apache.poi.ss.formula.ptg.AreaErrPtg;
import org.apache.poi.ss.formula.ptg.AreaNPtg;
import org.apache.poi.ss.formula.ptg.AreaPtg;
import org.apache.poi.ss.formula.ptg.ArrayPtg;
import org.apache.poi.ss.formula.ptg.AttrPtg;
import org.apache.poi.ss.formula.ptg.BoolPtg;
import org.apache.poi.ss.formula.ptg.ConcatPtg;
import org.apache.poi.ss.formula.ptg.DeletedArea3DPtg;
import org.apache.poi.ss.formula.ptg.DeletedRef3DPtg;
import org.apache.poi.ss.formula.ptg.DividePtg;
import org.apache.poi.ss.formula.ptg.EqualPtg;
import org.apache.poi.ss.formula.ptg.ErrPtg;
import org.apache.poi.ss.formula.ptg.ExpPtg;
import org.apache.poi.ss.formula.ptg.FuncPtg;
import org.apache.poi.ss.formula.ptg.FuncVarPtg;
import org.apache.poi.ss.formula.ptg.GreaterEqualPtg;
import org.apache.poi.ss.formula.ptg.GreaterThanPtg;
import org.apache.poi.ss.formula.ptg.IntPtg;
import org.apache.poi.ss.formula.ptg.IntersectionPtg;
import org.apache.poi.ss.formula.ptg.LessEqualPtg;
import org.apache.poi.ss.formula.ptg.LessThanPtg;
import org.apache.poi.ss.formula.ptg.MemAreaPtg;
import org.apache.poi.ss.formula.ptg.MemErrPtg;
import org.apache.poi.ss.formula.ptg.MemFuncPtg;
import org.apache.poi.ss.formula.ptg.MissingArgPtg;
import org.apache.poi.ss.formula.ptg.MultiplyPtg;
import org.apache.poi.ss.formula.ptg.NamePtg;
import org.apache.poi.ss.formula.ptg.NameXPtg;
import org.apache.poi.ss.formula.ptg.NotEqualPtg;
import org.apache.poi.ss.formula.ptg.NumberPtg;
import org.apache.poi.ss.formula.ptg.ParenthesisPtg;
import org.apache.poi.ss.formula.ptg.PercentPtg;
import org.apache.poi.ss.formula.ptg.PowerPtg;
import org.apache.poi.ss.formula.ptg.RangePtg;
import org.apache.poi.ss.formula.ptg.Ref3DPtg;
import org.apache.poi.ss.formula.ptg.RefErrorPtg;
import org.apache.poi.ss.formula.ptg.RefNPtg;
import org.apache.poi.ss.formula.ptg.RefPtg;
import org.apache.poi.ss.formula.ptg.StringPtg;
import org.apache.poi.ss.formula.ptg.SubtractPtg;
import org.apache.poi.ss.formula.ptg.TblPtg;
import org.apache.poi.ss.formula.ptg.UnaryMinusPtg;
import org.apache.poi.ss.formula.ptg.UnaryPlusPtg;
import org.apache.poi.ss.formula.ptg.UnionPtg;
import org.apache.poi.ss.formula.ptg.UnknownPtg;
import org.apache.poi.util.LittleEndianByteArrayOutputStream;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public abstract class Ptg
implements Duplicatable {
    public static final Ptg[] EMPTY_PTG_ARRAY = new Ptg[0];
    public static final byte CLASS_REF = 0;
    public static final byte CLASS_VALUE = 32;
    public static final byte CLASS_ARRAY = 64;
    private byte ptgClass = 0;

    protected Ptg() {
    }

    protected Ptg(Ptg other) {
        this.ptgClass = other.ptgClass;
    }

    public static Ptg[] readTokens(int size, LittleEndianInput in) {
        int pos;
        Ptg ptg;
        ArrayList<Ptg> temp = new ArrayList<Ptg>(4 + size / 2);
        boolean hasArrayPtgs = false;
        for (pos = 0; pos < size; pos += ptg.getSize()) {
            ptg = Ptg.createPtg(in);
            if (ptg instanceof ArrayPtg.Initial) {
                hasArrayPtgs = true;
            }
            temp.add(ptg);
        }
        if (pos != size) {
            throw new RuntimeException("Ptg array size mismatch");
        }
        if (hasArrayPtgs) {
            Ptg[] result = Ptg.toPtgArray(temp);
            for (int i = 0; i < result.length; ++i) {
                if (!(result[i] instanceof ArrayPtg.Initial)) continue;
                result[i] = ((ArrayPtg.Initial)result[i]).finishReading(in);
            }
            return result;
        }
        return Ptg.toPtgArray(temp);
    }

    public static Ptg createPtg(LittleEndianInput in) {
        byte id = in.readByte();
        if (id < 32) {
            return Ptg.createBasePtg(id, in);
        }
        Ptg retval = Ptg.createClassifiedPtg(id, in);
        if (id >= 96) {
            retval.setClass((byte)64);
        } else if (id >= 64) {
            retval.setClass((byte)32);
        } else {
            retval.setClass((byte)0);
        }
        return retval;
    }

    private static Ptg createClassifiedPtg(byte id, LittleEndianInput in) {
        int baseId = id & 0x1F | 0x20;
        switch (baseId) {
            case 32: {
                return new ArrayPtg.Initial(in);
            }
            case 33: {
                return FuncPtg.create(in);
            }
            case 34: {
                return FuncVarPtg.create(in);
            }
            case 35: {
                return new NamePtg(in);
            }
            case 36: {
                return new RefPtg(in);
            }
            case 37: {
                return new AreaPtg(in);
            }
            case 38: {
                return new MemAreaPtg(in);
            }
            case 39: {
                return new MemErrPtg(in);
            }
            case 41: {
                return new MemFuncPtg(in);
            }
            case 42: {
                return new RefErrorPtg(in);
            }
            case 43: {
                return new AreaErrPtg(in);
            }
            case 44: {
                return new RefNPtg(in);
            }
            case 45: {
                return new AreaNPtg(in);
            }
            case 57: {
                return new NameXPtg(in);
            }
            case 58: {
                return new Ref3DPtg(in);
            }
            case 59: {
                return new Area3DPtg(in);
            }
            case 60: {
                return new DeletedRef3DPtg(in);
            }
            case 61: {
                return new DeletedArea3DPtg(in);
            }
        }
        throw new UnsupportedOperationException(" Unknown Ptg in Formula: 0x" + Integer.toHexString(id) + " (" + id + ")");
    }

    private static Ptg createBasePtg(byte id, LittleEndianInput in) {
        switch (id) {
            case 0: {
                return new UnknownPtg(id);
            }
            case 1: {
                return new ExpPtg(in);
            }
            case 2: {
                return new TblPtg(in);
            }
            case 3: {
                return AddPtg.instance;
            }
            case 4: {
                return SubtractPtg.instance;
            }
            case 5: {
                return MultiplyPtg.instance;
            }
            case 6: {
                return DividePtg.instance;
            }
            case 7: {
                return PowerPtg.instance;
            }
            case 8: {
                return ConcatPtg.instance;
            }
            case 9: {
                return LessThanPtg.instance;
            }
            case 10: {
                return LessEqualPtg.instance;
            }
            case 11: {
                return EqualPtg.instance;
            }
            case 12: {
                return GreaterEqualPtg.instance;
            }
            case 13: {
                return GreaterThanPtg.instance;
            }
            case 14: {
                return NotEqualPtg.instance;
            }
            case 15: {
                return IntersectionPtg.instance;
            }
            case 16: {
                return UnionPtg.instance;
            }
            case 17: {
                return RangePtg.instance;
            }
            case 18: {
                return UnaryPlusPtg.instance;
            }
            case 19: {
                return UnaryMinusPtg.instance;
            }
            case 20: {
                return PercentPtg.instance;
            }
            case 21: {
                return ParenthesisPtg.instance;
            }
            case 22: {
                return MissingArgPtg.instance;
            }
            case 23: {
                return new StringPtg(in);
            }
            case 25: {
                return new AttrPtg(in);
            }
            case 28: {
                return ErrPtg.read(in);
            }
            case 29: {
                return BoolPtg.read(in);
            }
            case 30: {
                return new IntPtg(in);
            }
            case 31: {
                return new NumberPtg(in);
            }
        }
        throw new RuntimeException("Unexpected base token id (" + id + ")");
    }

    private static Ptg[] toPtgArray(List<Ptg> l) {
        if (l.isEmpty()) {
            return EMPTY_PTG_ARRAY;
        }
        Ptg[] result = new Ptg[l.size()];
        l.toArray(result);
        return result;
    }

    public static int getEncodedSize(Ptg[] ptgs) {
        int result = 0;
        for (Ptg ptg : ptgs) {
            result += ptg.getSize();
        }
        return result;
    }

    public static int getEncodedSizeWithoutArrayData(Ptg[] ptgs) {
        int result = 0;
        for (Ptg ptg : ptgs) {
            if (ptg instanceof ArrayPtg) {
                result += 8;
                continue;
            }
            result += ptg.getSize();
        }
        return result;
    }

    public static int serializePtgs(Ptg[] ptgs, byte[] array, int offset) {
        LittleEndianByteArrayOutputStream out = new LittleEndianByteArrayOutputStream(array, offset);
        ArrayList<Ptg> arrayPtgs = null;
        for (Ptg ptg : ptgs) {
            ptg.write(out);
            if (!(ptg instanceof ArrayPtg)) continue;
            if (arrayPtgs == null) {
                arrayPtgs = new ArrayList<Ptg>(5);
            }
            arrayPtgs.add(ptg);
        }
        if (arrayPtgs != null) {
            for (Ptg arrayPtg : arrayPtgs) {
                ArrayPtg p = (ArrayPtg)arrayPtg;
                p.writeTokenValueBytes(out);
            }
        }
        return out.getWriteIndex() - offset;
    }

    public abstract int getSize();

    public abstract void write(LittleEndianOutput var1);

    public abstract String toFormulaString();

    public String toString() {
        return this.getClass().toString();
    }

    public final void setClass(byte thePtgClass) {
        if (this.isBaseToken()) {
            throw new RuntimeException("setClass should not be called on a base token");
        }
        this.ptgClass = thePtgClass;
    }

    public final byte getPtgClass() {
        return this.ptgClass;
    }

    public final char getRVAType() {
        if (this.isBaseToken()) {
            return '.';
        }
        switch (this.ptgClass) {
            case 0: {
                return 'R';
            }
            case 32: {
                return 'V';
            }
            case 64: {
                return 'A';
            }
        }
        throw new RuntimeException("Unknown operand class (" + this.ptgClass + ")");
    }

    public abstract byte getDefaultOperandClass();

    public abstract boolean isBaseToken();

    public static boolean doesFormulaReferToDeletedCell(Ptg[] ptgs) {
        for (Ptg ptg : ptgs) {
            if (!Ptg.isDeletedCellRef(ptg)) continue;
            return true;
        }
        return false;
    }

    private static boolean isDeletedCellRef(Ptg ptg) {
        if (ptg == ErrPtg.REF_INVALID) {
            return true;
        }
        if (ptg instanceof DeletedArea3DPtg) {
            return true;
        }
        if (ptg instanceof DeletedRef3DPtg) {
            return true;
        }
        if (ptg instanceof AreaErrPtg) {
            return true;
        }
        return ptg instanceof RefErrorPtg;
    }

    @Override
    public abstract Ptg copy();
}

