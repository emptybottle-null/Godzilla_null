/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;

public class EnumUtils {
    private static final String NULL_ELEMENTS_NOT_PERMITTED = "null elements not permitted";
    private static final String CANNOT_STORE_S_S_VALUES_IN_S_BITS = "Cannot store %s %s values in %s bits";
    private static final String S_DOES_NOT_SEEM_TO_BE_AN_ENUM_TYPE = "%s does not seem to be an Enum type";
    private static final String ENUM_CLASS_MUST_BE_DEFINED = "EnumClass must be defined.";

    private static <E extends Enum<E>> Class<E> asEnum(Class<E> enumClass) {
        Validate.notNull(enumClass, ENUM_CLASS_MUST_BE_DEFINED, new Object[0]);
        Validate.isTrue(enumClass.isEnum(), S_DOES_NOT_SEEM_TO_BE_AN_ENUM_TYPE, enumClass);
        return enumClass;
    }

    private static <E extends Enum<E>> Class<E> checkBitVectorable(Class<E> enumClass) {
        Enum[] constants = (Enum[])EnumUtils.asEnum(enumClass).getEnumConstants();
        Validate.isTrue(constants.length <= 64, CANNOT_STORE_S_S_VALUES_IN_S_BITS, constants.length, enumClass.getSimpleName(), 64);
        return enumClass;
    }

    @SafeVarargs
    public static <E extends Enum<E>> long generateBitVector(Class<E> enumClass, E ... values) {
        Validate.noNullElements(values);
        return EnumUtils.generateBitVector(enumClass, Arrays.asList(values));
    }

    public static <E extends Enum<E>> long generateBitVector(Class<E> enumClass, Iterable<? extends E> values) {
        EnumUtils.checkBitVectorable(enumClass);
        Validate.notNull(values);
        long total = 0L;
        for (Enum constant : values) {
            Validate.notNull(constant, NULL_ELEMENTS_NOT_PERMITTED, new Object[0]);
            total |= 1L << constant.ordinal();
        }
        return total;
    }

    @SafeVarargs
    public static <E extends Enum<E>> long[] generateBitVectors(Class<E> enumClass, E ... values) {
        EnumUtils.asEnum(enumClass);
        Validate.noNullElements(values);
        EnumSet<Enum> condensed = EnumSet.noneOf(enumClass);
        Collections.addAll(condensed, values);
        long[] result = new long[(((Enum[])enumClass.getEnumConstants()).length - 1) / 64 + 1];
        for (Enum value : condensed) {
            int n = value.ordinal() / 64;
            result[n] = result[n] | 1L << value.ordinal() % 64;
        }
        ArrayUtils.reverse(result);
        return result;
    }

    public static <E extends Enum<E>> long[] generateBitVectors(Class<E> enumClass, Iterable<? extends E> values) {
        EnumUtils.asEnum(enumClass);
        Validate.notNull(values);
        EnumSet<Enum> condensed = EnumSet.noneOf(enumClass);
        for (Enum constant : values) {
            Validate.notNull(constant, NULL_ELEMENTS_NOT_PERMITTED, new Object[0]);
            condensed.add(constant);
        }
        long[] result = new long[(((Enum[])enumClass.getEnumConstants()).length - 1) / 64 + 1];
        for (Enum value : condensed) {
            int n = value.ordinal() / 64;
            result[n] = result[n] | 1L << value.ordinal() % 64;
        }
        ArrayUtils.reverse(result);
        return result;
    }

    public static <E extends Enum<E>> E getEnum(Class<E> enumClass, String enumName) {
        return EnumUtils.getEnum(enumClass, enumName, null);
    }

    public static <E extends Enum<E>> E getEnum(Class<E> enumClass, String enumName, E defaultEnum) {
        if (enumName == null) {
            return defaultEnum;
        }
        try {
            return Enum.valueOf(enumClass, enumName);
        } catch (IllegalArgumentException ex) {
            return defaultEnum;
        }
    }

    public static <E extends Enum<E>> E getEnumIgnoreCase(Class<E> enumClass, String enumName) {
        return EnumUtils.getEnumIgnoreCase(enumClass, enumName, null);
    }

    public static <E extends Enum<E>> E getEnumIgnoreCase(Class<E> enumClass, String enumName, E defaultEnum) {
        if (enumName == null || !enumClass.isEnum()) {
            return defaultEnum;
        }
        for (Enum each : (Enum[])enumClass.getEnumConstants()) {
            if (!each.name().equalsIgnoreCase(enumName)) continue;
            return (E)each;
        }
        return defaultEnum;
    }

    public static <E extends Enum<E>> List<E> getEnumList(Class<E> enumClass) {
        return new ArrayList<E>(Arrays.asList(enumClass.getEnumConstants()));
    }

    public static <E extends Enum<E>> Map<String, E> getEnumMap(Class<E> enumClass) {
        LinkedHashMap<String, Enum> map = new LinkedHashMap<String, Enum>();
        for (Enum e : (Enum[])enumClass.getEnumConstants()) {
            map.put(e.name(), e);
        }
        return map;
    }

    public static <E extends Enum<E>> boolean isValidEnum(Class<E> enumClass, String enumName) {
        return EnumUtils.getEnum(enumClass, enumName) != null;
    }

    public static <E extends Enum<E>> boolean isValidEnumIgnoreCase(Class<E> enumClass, String enumName) {
        return EnumUtils.getEnumIgnoreCase(enumClass, enumName) != null;
    }

    public static <E extends Enum<E>> EnumSet<E> processBitVector(Class<E> enumClass, long value) {
        EnumUtils.checkBitVectorable(enumClass).getEnumConstants();
        return EnumUtils.processBitVectors(enumClass, value);
    }

    public static <E extends Enum<E>> EnumSet<E> processBitVectors(Class<E> enumClass, long ... values) {
        EnumSet<Enum> results = EnumSet.noneOf(EnumUtils.asEnum(enumClass));
        long[] lvalues = ArrayUtils.clone(Validate.notNull(values));
        ArrayUtils.reverse(lvalues);
        for (Enum constant : (Enum[])enumClass.getEnumConstants()) {
            int block = constant.ordinal() / 64;
            if (block >= lvalues.length || (lvalues[block] & 1L << constant.ordinal() % 64) == 0L) continue;
            results.add(constant);
        }
        return results;
    }
}

