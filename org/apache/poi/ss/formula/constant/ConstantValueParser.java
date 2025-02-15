/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.constant;

import org.apache.poi.ss.formula.constant.ErrorConstant;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.StringUtil;

public final class ConstantValueParser {
    private static final int TYPE_EMPTY = 0;
    private static final int TYPE_NUMBER = 1;
    private static final int TYPE_STRING = 2;
    private static final int TYPE_BOOLEAN = 4;
    private static final int TYPE_ERROR_CODE = 16;
    private static final int TRUE_ENCODING = 1;
    private static final int FALSE_ENCODING = 0;
    private static final Object EMPTY_REPRESENTATION = null;

    private ConstantValueParser() {
    }

    public static Object[] parse(LittleEndianInput in, int nValues) {
        Object[] result = new Object[nValues];
        for (int i = 0; i < result.length; ++i) {
            result[i] = ConstantValueParser.readAConstantValue(in);
        }
        return result;
    }

    private static Object readAConstantValue(LittleEndianInput in) {
        byte grbit = in.readByte();
        switch (grbit) {
            case 0: {
                in.readLong();
                return EMPTY_REPRESENTATION;
            }
            case 1: {
                return in.readDouble();
            }
            case 2: {
                return StringUtil.readUnicodeString(in);
            }
            case 4: {
                return ConstantValueParser.readBoolean(in);
            }
            case 16: {
                int errCode = in.readUShort();
                in.readUShort();
                in.readInt();
                return ErrorConstant.valueOf(errCode);
            }
        }
        throw new RuntimeException("Unknown grbit value (" + grbit + ")");
    }

    private static Object readBoolean(LittleEndianInput in) {
        byte val = (byte)in.readLong();
        switch (val) {
            case 0: {
                return Boolean.FALSE;
            }
            case 1: {
                return Boolean.TRUE;
            }
        }
        throw new RuntimeException("unexpected boolean encoding (" + val + ")");
    }

    public static int getEncodedSize(Object[] values) {
        int result = values.length * 1;
        for (Object value : values) {
            result += ConstantValueParser.getEncodedSize(value);
        }
        return result;
    }

    private static int getEncodedSize(Object object) {
        if (object == EMPTY_REPRESENTATION) {
            return 8;
        }
        Class<?> cls = object.getClass();
        if (cls == Boolean.class || cls == Double.class || cls == ErrorConstant.class) {
            return 8;
        }
        String strVal = (String)object;
        return StringUtil.getEncodedSize(strVal);
    }

    public static void encode(LittleEndianOutput out, Object[] values) {
        for (Object value : values) {
            ConstantValueParser.encodeSingleValue(out, value);
        }
    }

    private static void encodeSingleValue(LittleEndianOutput out, Object value) {
        if (value == EMPTY_REPRESENTATION) {
            out.writeByte(0);
            out.writeLong(0L);
            return;
        }
        if (value instanceof Boolean) {
            Boolean bVal = (Boolean)value;
            out.writeByte(4);
            long longVal = bVal != false ? 1L : 0L;
            out.writeLong(longVal);
            return;
        }
        if (value instanceof Double) {
            Double dVal = (Double)value;
            out.writeByte(1);
            out.writeDouble(dVal);
            return;
        }
        if (value instanceof String) {
            String val = (String)value;
            out.writeByte(2);
            StringUtil.writeUnicodeString(out, val);
            return;
        }
        if (value instanceof ErrorConstant) {
            ErrorConstant ecVal = (ErrorConstant)value;
            out.writeByte(16);
            long longVal = ecVal.getErrorCode();
            out.writeLong(longVal);
            return;
        }
        throw new IllegalStateException("Unexpected value type (" + value.getClass().getName() + "'");
    }
}

