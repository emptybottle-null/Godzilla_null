/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.ptg;

import org.apache.poi.ss.formula.function.FunctionMetadata;
import org.apache.poi.ss.formula.function.FunctionMetadataRegistry;
import org.apache.poi.ss.formula.ptg.AbstractFunctionPtg;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public final class FuncPtg
extends AbstractFunctionPtg {
    public static final byte sid = 33;
    public static final int SIZE = 3;

    public static FuncPtg create(LittleEndianInput in) {
        return FuncPtg.create(in.readUShort());
    }

    private FuncPtg(int funcIndex, FunctionMetadata fm) {
        super(funcIndex, fm.getReturnClassCode(), fm.getParameterClassCodes(), fm.getMinParams());
    }

    public static FuncPtg create(int functionIndex) {
        FunctionMetadata fm = FunctionMetadataRegistry.getFunctionByIndex(functionIndex);
        if (fm == null) {
            throw new RuntimeException("Invalid built-in function index (" + functionIndex + ")");
        }
        return new FuncPtg(functionIndex, fm);
    }

    @Override
    public void write(LittleEndianOutput out) {
        out.writeByte(33 + this.getPtgClass());
        out.writeShort(this.getFunctionIndex());
    }

    @Override
    public int getSize() {
        return 3;
    }

    @Override
    public FuncPtg copy() {
        return this;
    }
}

