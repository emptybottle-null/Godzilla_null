/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula;

import java.util.Arrays;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LittleEndianByteArrayInputStream;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public class Formula {
    private static final int MAX_ENCODED_LEN = 100000;
    private static final Formula EMPTY = new Formula(new byte[0], 0);
    private final byte[] _byteEncoding;
    private final int _encodedTokenLen;

    public Formula(Formula other) {
        this._byteEncoding = other._byteEncoding == null ? null : (byte[])other._byteEncoding.clone();
        this._encodedTokenLen = other._encodedTokenLen;
    }

    private Formula(byte[] byteEncoding, int encodedTokenLen) {
        this._byteEncoding = (byte[])byteEncoding.clone();
        this._encodedTokenLen = encodedTokenLen;
    }

    public static Formula read(int encodedTokenLen, LittleEndianInput in) {
        return Formula.read(encodedTokenLen, in, encodedTokenLen);
    }

    public static Formula read(int encodedTokenLen, LittleEndianInput in, int totalEncodedLen) {
        byte[] byteEncoding = IOUtils.safelyAllocate(totalEncodedLen, 100000);
        in.readFully(byteEncoding);
        return new Formula(byteEncoding, encodedTokenLen);
    }

    public Ptg[] getTokens() {
        LittleEndianByteArrayInputStream in = new LittleEndianByteArrayInputStream(this._byteEncoding);
        return Ptg.readTokens(this._encodedTokenLen, in);
    }

    public void serialize(LittleEndianOutput out) {
        out.writeShort(this._encodedTokenLen);
        out.write(this._byteEncoding);
    }

    public void serializeTokens(LittleEndianOutput out) {
        out.write(this._byteEncoding, 0, this._encodedTokenLen);
    }

    public void serializeArrayConstantData(LittleEndianOutput out) {
        int len = this._byteEncoding.length - this._encodedTokenLen;
        out.write(this._byteEncoding, this._encodedTokenLen, len);
    }

    public int getEncodedSize() {
        return 2 + this._byteEncoding.length;
    }

    public int getEncodedTokenSize() {
        return this._encodedTokenLen;
    }

    public static Formula create(Ptg[] ptgs) {
        if (ptgs == null || ptgs.length < 1) {
            return EMPTY;
        }
        int totalSize = Ptg.getEncodedSize(ptgs);
        byte[] encodedData = new byte[totalSize];
        Ptg.serializePtgs(ptgs, encodedData, 0);
        int encodedTokenLen = Ptg.getEncodedSizeWithoutArrayData(ptgs);
        return new Formula(encodedData, encodedTokenLen);
    }

    public static Ptg[] getTokens(Formula formula) {
        if (formula == null) {
            return null;
        }
        return formula.getTokens();
    }

    public Formula copy() {
        return this;
    }

    public CellReference getExpReference() {
        byte[] data = this._byteEncoding;
        if (data.length != 5) {
            return null;
        }
        switch (data[0]) {
            case 1: {
                break;
            }
            case 2: {
                break;
            }
            default: {
                return null;
            }
        }
        int firstRow = LittleEndian.getUShort(data, 1);
        int firstColumn = LittleEndian.getUShort(data, 3);
        return new CellReference(firstRow, firstColumn);
    }

    public boolean isSame(Formula other) {
        return Arrays.equals(this._byteEncoding, other._byteEncoding);
    }
}

