/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.ptg;

import org.apache.poi.ss.formula.function.FunctionMetadata;
import org.apache.poi.ss.formula.function.FunctionMetadataRegistry;
import org.apache.poi.ss.formula.ptg.AbstractFunctionPtg;
import org.apache.poi.ss.formula.ptg.OperationPtg;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public final class FuncVarPtg
extends AbstractFunctionPtg {
    public static final byte sid = 34;
    private static final int SIZE = 4;
    private static final BitField ceFunc = BitFieldFactory.getInstance(61440);
    public static final OperationPtg SUM = FuncVarPtg.create("SUM", 1);
    private final boolean _isCetab;

    private FuncVarPtg(int functionIndex, int returnClass, byte[] paramClasses, int numArgs, boolean isCetab) {
        super(functionIndex, returnClass, paramClasses, numArgs);
        this._isCetab = isCetab;
    }

    public static FuncVarPtg create(LittleEndianInput in) {
        return FuncVarPtg.create(in.readByte(), in.readUShort());
    }

    public static FuncVarPtg create(String pName, int numArgs) {
        return FuncVarPtg.create(numArgs, (int)FuncVarPtg.lookupIndex(pName));
    }

    private static FuncVarPtg create(int numArgs, int functionIndex) {
        FunctionMetadata fm;
        boolean isCetab = ceFunc.isSet(functionIndex);
        if (isCetab) {
            functionIndex = ceFunc.clear(functionIndex);
            fm = FunctionMetadataRegistry.getCetabFunctionByIndex(functionIndex);
        } else {
            fm = FunctionMetadataRegistry.getFunctionByIndex(functionIndex);
        }
        if (fm == null) {
            return new FuncVarPtg(functionIndex, 32, new byte[]{32}, numArgs, isCetab);
        }
        return new FuncVarPtg(functionIndex, fm.getReturnClassCode(), fm.getParameterClassCodes(), numArgs, isCetab);
    }

    @Override
    protected String lookupName(short index) {
        return this.lookupName(index, this._isCetab);
    }

    @Override
    public void write(LittleEndianOutput out) {
        out.writeByte(34 + this.getPtgClass());
        out.writeByte(this.getNumberOfOperands());
        out.writeShort(this.getFunctionIndex());
    }

    @Override
    public int getSize() {
        return 4;
    }

    @Override
    public FuncVarPtg copy() {
        return this;
    }
}

