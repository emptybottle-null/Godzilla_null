/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hpsf;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Variant {
    public static final int VT_EMPTY = 0;
    public static final int VT_NULL = 1;
    public static final int VT_I2 = 2;
    public static final int VT_I4 = 3;
    public static final int VT_R4 = 4;
    public static final int VT_R8 = 5;
    public static final int VT_CY = 6;
    public static final int VT_DATE = 7;
    public static final int VT_BSTR = 8;
    public static final int VT_DISPATCH = 9;
    public static final int VT_ERROR = 10;
    public static final int VT_BOOL = 11;
    public static final int VT_VARIANT = 12;
    public static final int VT_UNKNOWN = 13;
    public static final int VT_DECIMAL = 14;
    public static final int VT_I1 = 16;
    public static final int VT_UI1 = 17;
    public static final int VT_UI2 = 18;
    public static final int VT_UI4 = 19;
    public static final int VT_I8 = 20;
    public static final int VT_UI8 = 21;
    public static final int VT_INT = 22;
    public static final int VT_UINT = 23;
    public static final int VT_VOID = 24;
    public static final int VT_HRESULT = 25;
    public static final int VT_PTR = 26;
    public static final int VT_SAFEARRAY = 27;
    public static final int VT_CARRAY = 28;
    public static final int VT_USERDEFINED = 29;
    public static final int VT_LPSTR = 30;
    public static final int VT_LPWSTR = 31;
    public static final int VT_FILETIME = 64;
    public static final int VT_BLOB = 65;
    public static final int VT_STREAM = 66;
    public static final int VT_STORAGE = 67;
    public static final int VT_STREAMED_OBJECT = 68;
    public static final int VT_STORED_OBJECT = 69;
    public static final int VT_BLOB_OBJECT = 70;
    public static final int VT_CF = 71;
    public static final int VT_CLSID = 72;
    public static final int VT_VERSIONED_STREAM = 73;
    public static final int VT_VECTOR = 4096;
    public static final int VT_ARRAY = 8192;
    public static final int VT_BYREF = 16384;
    public static final int VT_RESERVED = 32768;
    public static final int VT_ILLEGAL = 65535;
    public static final int VT_ILLEGALMASKED = 4095;
    public static final int VT_TYPEMASK = 4095;
    private static final Map<Long, String> numberToName;
    private static final Map<Long, Integer> numberToLength;
    public static final Integer LENGTH_UNKNOWN;
    public static final Integer LENGTH_VARIABLE;
    public static final Integer LENGTH_0;
    public static final Integer LENGTH_2;
    public static final Integer LENGTH_4;
    public static final Integer LENGTH_8;
    private static final Object[][] NUMBER_TO_NAME_LIST;

    public static String getVariantName(long variantType) {
        long vt = variantType;
        String name = "";
        if ((vt & 0x1000L) != 0L) {
            name = "Vector of ";
            vt -= 4096L;
        } else if ((vt & 0x2000L) != 0L) {
            name = "Array of ";
            vt -= 8192L;
        } else if ((vt & 0x4000L) != 0L) {
            name = "ByRef of ";
            vt -= 16384L;
        }
        name = name + numberToName.get(vt);
        return !name.isEmpty() ? name : "unknown variant type";
    }

    public static int getVariantLength(long variantType) {
        Integer length = numberToLength.get(variantType);
        return length != null ? length : LENGTH_UNKNOWN;
    }

    static {
        LENGTH_UNKNOWN = -2;
        LENGTH_VARIABLE = -1;
        LENGTH_0 = 0;
        LENGTH_2 = 2;
        LENGTH_4 = 4;
        LENGTH_8 = 8;
        NUMBER_TO_NAME_LIST = new Object[][]{{0L, "VT_EMPTY", LENGTH_0}, {1L, "VT_NULL", LENGTH_UNKNOWN}, {2L, "VT_I2", LENGTH_2}, {3L, "VT_I4", LENGTH_4}, {4L, "VT_R4", LENGTH_4}, {5L, "VT_R8", LENGTH_8}, {6L, "VT_CY", LENGTH_UNKNOWN}, {7L, "VT_DATE", LENGTH_UNKNOWN}, {8L, "VT_BSTR", LENGTH_UNKNOWN}, {9L, "VT_DISPATCH", LENGTH_UNKNOWN}, {10L, "VT_ERROR", LENGTH_UNKNOWN}, {11L, "VT_BOOL", LENGTH_UNKNOWN}, {12L, "VT_VARIANT", LENGTH_UNKNOWN}, {13L, "VT_UNKNOWN", LENGTH_UNKNOWN}, {14L, "VT_DECIMAL", LENGTH_UNKNOWN}, {16L, "VT_I1", LENGTH_UNKNOWN}, {17L, "VT_UI1", LENGTH_UNKNOWN}, {18L, "VT_UI2", LENGTH_UNKNOWN}, {19L, "VT_UI4", LENGTH_UNKNOWN}, {20L, "VT_I8", LENGTH_UNKNOWN}, {21L, "VT_UI8", LENGTH_UNKNOWN}, {22L, "VT_INT", LENGTH_UNKNOWN}, {23L, "VT_UINT", LENGTH_UNKNOWN}, {24L, "VT_VOID", LENGTH_UNKNOWN}, {25L, "VT_HRESULT", LENGTH_UNKNOWN}, {26L, "VT_PTR", LENGTH_UNKNOWN}, {27L, "VT_SAFEARRAY", LENGTH_UNKNOWN}, {28L, "VT_CARRAY", LENGTH_UNKNOWN}, {29L, "VT_USERDEFINED", LENGTH_UNKNOWN}, {30L, "VT_LPSTR", LENGTH_VARIABLE}, {31L, "VT_LPWSTR", LENGTH_UNKNOWN}, {64L, "VT_FILETIME", LENGTH_8}, {65L, "VT_BLOB", LENGTH_UNKNOWN}, {66L, "VT_STREAM", LENGTH_UNKNOWN}, {67L, "VT_STORAGE", LENGTH_UNKNOWN}, {68L, "VT_STREAMED_OBJECT", LENGTH_UNKNOWN}, {69L, "VT_STORED_OBJECT", LENGTH_UNKNOWN}, {70L, "VT_BLOB_OBJECT", LENGTH_UNKNOWN}, {71L, "VT_CF", LENGTH_UNKNOWN}, {72L, "VT_CLSID", LENGTH_UNKNOWN}};
        HashMap<Long, String> number2Name = new HashMap<Long, String>(NUMBER_TO_NAME_LIST.length, 1.0f);
        HashMap<Long, Integer> number2Len = new HashMap<Long, Integer>(NUMBER_TO_NAME_LIST.length, 1.0f);
        for (Object[] nn : NUMBER_TO_NAME_LIST) {
            number2Name.put((Long)nn[0], (String)nn[1]);
            number2Len.put((Long)nn[0], (Integer)nn[2]);
        }
        numberToName = Collections.unmodifiableMap(number2Name);
        numberToLength = Collections.unmodifiableMap(number2Len);
    }
}

