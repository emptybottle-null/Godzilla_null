/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression.common;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.TypeConverter;
import org.springframework.expression.TypedValue;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;

public abstract class ExpressionUtils {
    @Nullable
    public static <T> T convertTypedValue(@Nullable EvaluationContext context, TypedValue typedValue, @Nullable Class<T> targetType) {
        Object value = typedValue.getValue();
        if (targetType == null) {
            return (T)value;
        }
        if (context != null) {
            return (T)context.getTypeConverter().convertValue(value, typedValue.getTypeDescriptor(), TypeDescriptor.valueOf(targetType));
        }
        if (ClassUtils.isAssignableValue(targetType, value)) {
            return (T)value;
        }
        throw new EvaluationException("Cannot convert value '" + value + "' to type '" + targetType.getName() + "'");
    }

    public static int toInt(TypeConverter typeConverter, TypedValue typedValue) {
        return ExpressionUtils.convertValue(typeConverter, typedValue, Integer.class);
    }

    public static boolean toBoolean(TypeConverter typeConverter, TypedValue typedValue) {
        return ExpressionUtils.convertValue(typeConverter, typedValue, Boolean.class);
    }

    public static double toDouble(TypeConverter typeConverter, TypedValue typedValue) {
        return ExpressionUtils.convertValue(typeConverter, typedValue, Double.class);
    }

    public static long toLong(TypeConverter typeConverter, TypedValue typedValue) {
        return ExpressionUtils.convertValue(typeConverter, typedValue, Long.class);
    }

    public static char toChar(TypeConverter typeConverter, TypedValue typedValue) {
        return ExpressionUtils.convertValue(typeConverter, typedValue, Character.class).charValue();
    }

    public static short toShort(TypeConverter typeConverter, TypedValue typedValue) {
        return ExpressionUtils.convertValue(typeConverter, typedValue, Short.class);
    }

    public static float toFloat(TypeConverter typeConverter, TypedValue typedValue) {
        return ExpressionUtils.convertValue(typeConverter, typedValue, Float.class).floatValue();
    }

    public static byte toByte(TypeConverter typeConverter, TypedValue typedValue) {
        return ExpressionUtils.convertValue(typeConverter, typedValue, Byte.class);
    }

    private static <T> T convertValue(TypeConverter typeConverter, TypedValue typedValue, Class<T> targetType) {
        Object result = typeConverter.convertValue(typedValue.getValue(), typedValue.getTypeDescriptor(), TypeDescriptor.valueOf(targetType));
        if (result == null) {
            throw new IllegalStateException("Null conversion result for value [" + typedValue.getValue() + "]");
        }
        return (T)result;
    }
}

