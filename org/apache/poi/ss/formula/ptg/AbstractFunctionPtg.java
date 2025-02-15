/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.ptg;

import java.util.Locale;
import org.apache.poi.ss.formula.function.FunctionMetadata;
import org.apache.poi.ss.formula.function.FunctionMetadataRegistry;
import org.apache.poi.ss.formula.ptg.OperationPtg;

public abstract class AbstractFunctionPtg
extends OperationPtg {
    public static final String FUNCTION_NAME_IF = "IF";
    private static final short FUNCTION_INDEX_EXTERNAL = 255;
    private final byte returnClass;
    private final byte[] paramClass;
    private final int _numberOfArgs;
    private final short _functionIndex;

    protected AbstractFunctionPtg(int functionIndex, int pReturnClass, byte[] paramTypes, int nParams) {
        this._numberOfArgs = nParams;
        if (functionIndex < Short.MIN_VALUE || functionIndex > Short.MAX_VALUE) {
            throw new RuntimeException("functionIndex " + functionIndex + " cannot be cast to short");
        }
        this._functionIndex = (short)functionIndex;
        if (pReturnClass < -128 || pReturnClass > 127) {
            throw new RuntimeException("pReturnClass " + pReturnClass + " cannot be cast to byte");
        }
        this.returnClass = (byte)pReturnClass;
        this.paramClass = paramTypes;
    }

    @Override
    public final boolean isBaseToken() {
        return false;
    }

    @Override
    public final String toString() {
        return this.getClass().getName() + " [" + this.lookupName(this._functionIndex) + " nArgs=" + this._numberOfArgs + "]";
    }

    public final short getFunctionIndex() {
        return this._functionIndex;
    }

    @Override
    public final int getNumberOfOperands() {
        return this._numberOfArgs;
    }

    public final String getName() {
        return this.lookupName(this._functionIndex);
    }

    public final boolean isExternalFunction() {
        return this._functionIndex == 255;
    }

    @Override
    public final String toFormulaString() {
        return this.getName();
    }

    @Override
    public String toFormulaString(String[] operands) {
        StringBuilder buf = new StringBuilder();
        if (this.isExternalFunction()) {
            buf.append(operands[0]);
            AbstractFunctionPtg.appendArgs(buf, 1, operands);
        } else {
            buf.append(this.getName());
            AbstractFunctionPtg.appendArgs(buf, 0, operands);
        }
        return buf.toString();
    }

    private static void appendArgs(StringBuilder buf, int firstArgIx, String[] operands) {
        buf.append('(');
        for (int i = firstArgIx; i < operands.length; ++i) {
            if (i > firstArgIx) {
                buf.append(',');
            }
            buf.append(operands[i]);
        }
        buf.append(")");
    }

    @Override
    public abstract int getSize();

    public static boolean isBuiltInFunctionName(String name) {
        short ix = FunctionMetadataRegistry.lookupIndexByName(name.toUpperCase(Locale.ROOT));
        return ix >= 0;
    }

    protected String lookupName(short index) {
        return this.lookupName(index, false);
    }

    protected final String lookupName(short index, boolean isCetab) {
        if (index == 255) {
            return "#external#";
        }
        FunctionMetadata fm = isCetab ? FunctionMetadataRegistry.getCetabFunctionByIndex(index) : FunctionMetadataRegistry.getFunctionByIndex(index);
        if (fm == null) {
            throw new RuntimeException("bad function index (" + index + ", " + isCetab + ")");
        }
        return fm.getName();
    }

    protected static short lookupIndex(String name) {
        short ix = FunctionMetadataRegistry.lookupIndexByName(name.toUpperCase(Locale.ROOT));
        if (ix < 0) {
            return 255;
        }
        return ix;
    }

    @Override
    public byte getDefaultOperandClass() {
        return this.returnClass;
    }

    public final byte getParameterClass(int index) {
        if (index >= this.paramClass.length) {
            return this.paramClass[this.paramClass.length - 1];
        }
        return this.paramClass[index];
    }
}

