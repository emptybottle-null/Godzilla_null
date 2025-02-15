/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.builder;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import org.apache.commons.lang3.ArraySorter;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.builder.ToStringSummary;

public class ReflectionToStringBuilder
extends ToStringBuilder {
    private boolean appendStatics;
    private boolean appendTransients;
    private boolean excludeNullValues;
    protected String[] excludeFieldNames;
    private Class<?> upToClass;

    public static String toString(Object object) {
        return ReflectionToStringBuilder.toString(object, null, false, false, null);
    }

    public static String toString(Object object, ToStringStyle style) {
        return ReflectionToStringBuilder.toString(object, style, false, false, null);
    }

    public static String toString(Object object, ToStringStyle style, boolean outputTransients) {
        return ReflectionToStringBuilder.toString(object, style, outputTransients, false, null);
    }

    public static String toString(Object object, ToStringStyle style, boolean outputTransients, boolean outputStatics) {
        return ReflectionToStringBuilder.toString(object, style, outputTransients, outputStatics, null);
    }

    public static <T> String toString(T object, ToStringStyle style, boolean outputTransients, boolean outputStatics, Class<? super T> reflectUpToClass) {
        return new ReflectionToStringBuilder(object, style, null, reflectUpToClass, outputTransients, outputStatics).toString();
    }

    public static <T> String toString(T object, ToStringStyle style, boolean outputTransients, boolean outputStatics, boolean excludeNullValues, Class<? super T> reflectUpToClass) {
        return new ReflectionToStringBuilder(object, style, null, reflectUpToClass, outputTransients, outputStatics, excludeNullValues).toString();
    }

    public static String toStringExclude(Object object, Collection<String> excludeFieldNames) {
        return ReflectionToStringBuilder.toStringExclude(object, ReflectionToStringBuilder.toNoNullStringArray(excludeFieldNames));
    }

    static String[] toNoNullStringArray(Collection<String> collection) {
        if (collection == null) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        return ReflectionToStringBuilder.toNoNullStringArray(collection.toArray());
    }

    static String[] toNoNullStringArray(Object[] array) {
        ArrayList<String> list = new ArrayList<String>(array.length);
        for (Object e : array) {
            if (e == null) continue;
            list.add(e.toString());
        }
        return list.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
    }

    public static String toStringExclude(Object object, String ... excludeFieldNames) {
        return new ReflectionToStringBuilder(object).setExcludeFieldNames(excludeFieldNames).toString();
    }

    private static Object checkNotNull(Object obj) {
        return Validate.notNull(obj, "obj", new Object[0]);
    }

    public ReflectionToStringBuilder(Object object) {
        super(ReflectionToStringBuilder.checkNotNull(object));
    }

    public ReflectionToStringBuilder(Object object, ToStringStyle style) {
        super(ReflectionToStringBuilder.checkNotNull(object), style);
    }

    public ReflectionToStringBuilder(Object object, ToStringStyle style, StringBuffer buffer) {
        super(ReflectionToStringBuilder.checkNotNull(object), style, buffer);
    }

    public <T> ReflectionToStringBuilder(T object, ToStringStyle style, StringBuffer buffer, Class<? super T> reflectUpToClass, boolean outputTransients, boolean outputStatics) {
        super(ReflectionToStringBuilder.checkNotNull(object), style, buffer);
        this.setUpToClass(reflectUpToClass);
        this.setAppendTransients(outputTransients);
        this.setAppendStatics(outputStatics);
    }

    public <T> ReflectionToStringBuilder(T object, ToStringStyle style, StringBuffer buffer, Class<? super T> reflectUpToClass, boolean outputTransients, boolean outputStatics, boolean excludeNullValues) {
        super(ReflectionToStringBuilder.checkNotNull(object), style, buffer);
        this.setUpToClass(reflectUpToClass);
        this.setAppendTransients(outputTransients);
        this.setAppendStatics(outputStatics);
        this.setExcludeNullValues(excludeNullValues);
    }

    protected boolean accept(Field field) {
        if (field.getName().indexOf(36) != -1) {
            return false;
        }
        if (Modifier.isTransient(field.getModifiers()) && !this.isAppendTransients()) {
            return false;
        }
        if (Modifier.isStatic(field.getModifiers()) && !this.isAppendStatics()) {
            return false;
        }
        if (this.excludeFieldNames != null && Arrays.binarySearch(this.excludeFieldNames, field.getName()) >= 0) {
            return false;
        }
        return !field.isAnnotationPresent(ToStringExclude.class);
    }

    protected void appendFieldsIn(Class<?> clazz) {
        if (clazz.isArray()) {
            this.reflectionAppendArray(this.getObject());
            return;
        }
        AccessibleObject[] fields = ArraySorter.sort(clazz.getDeclaredFields(), Comparator.comparing(Field::getName));
        AccessibleObject.setAccessible(fields, true);
        for (AccessibleObject field : fields) {
            String fieldName = ((Field)field).getName();
            if (!this.accept((Field)field)) continue;
            try {
                Object fieldValue = this.getValue((Field)field);
                if (this.excludeNullValues && fieldValue == null) continue;
                this.append(fieldName, fieldValue, !field.isAnnotationPresent(ToStringSummary.class));
            } catch (IllegalAccessException ex) {
                throw new InternalError("Unexpected IllegalAccessException: " + ex.getMessage());
            }
        }
    }

    public String[] getExcludeFieldNames() {
        return (String[])this.excludeFieldNames.clone();
    }

    public Class<?> getUpToClass() {
        return this.upToClass;
    }

    protected Object getValue(Field field) throws IllegalAccessException {
        return field.get(this.getObject());
    }

    public boolean isAppendStatics() {
        return this.appendStatics;
    }

    public boolean isAppendTransients() {
        return this.appendTransients;
    }

    public boolean isExcludeNullValues() {
        return this.excludeNullValues;
    }

    public ReflectionToStringBuilder reflectionAppendArray(Object array) {
        this.getStyle().reflectionAppendArrayDetail(this.getStringBuffer(), null, array);
        return this;
    }

    public void setAppendStatics(boolean appendStatics) {
        this.appendStatics = appendStatics;
    }

    public void setAppendTransients(boolean appendTransients) {
        this.appendTransients = appendTransients;
    }

    public void setExcludeNullValues(boolean excludeNullValues) {
        this.excludeNullValues = excludeNullValues;
    }

    public ReflectionToStringBuilder setExcludeFieldNames(String ... excludeFieldNamesParam) {
        this.excludeFieldNames = excludeFieldNamesParam == null ? null : ArraySorter.sort(ReflectionToStringBuilder.toNoNullStringArray(excludeFieldNamesParam));
        return this;
    }

    public void setUpToClass(Class<?> clazz) {
        Object object;
        if (clazz != null && (object = this.getObject()) != null && !clazz.isInstance(object)) {
            throw new IllegalArgumentException("Specified class is not a superclass of the object");
        }
        this.upToClass = clazz;
    }

    @Override
    public String toString() {
        Class<?> clazz;
        if (this.getObject() == null) {
            return this.getStyle().getNullText();
        }
        this.appendFieldsIn(clazz);
        for (clazz = this.getObject().getClass(); clazz.getSuperclass() != null && clazz != this.getUpToClass(); clazz = clazz.getSuperclass()) {
            this.appendFieldsIn(clazz);
        }
        return super.toString();
    }
}

