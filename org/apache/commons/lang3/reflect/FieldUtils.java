/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.JavaVersion;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.reflect.MemberUtils;

public class FieldUtils {
    public static Field getField(Class<?> cls, String fieldName) {
        Field field = FieldUtils.getField(cls, fieldName, false);
        MemberUtils.setAccessibleWorkaround(field);
        return field;
    }

    public static Field getField(Class<?> cls, String fieldName, boolean forceAccess) {
        Validate.notNull(cls, "cls", new Object[0]);
        Validate.isTrue(StringUtils.isNotBlank(fieldName), "The field name must not be blank/empty", new Object[0]);
        for (Class<?> acls = cls; acls != null; acls = acls.getSuperclass()) {
            try {
                Field field = acls.getDeclaredField(fieldName);
                if (!Modifier.isPublic(field.getModifiers())) {
                    if (!forceAccess) continue;
                    field.setAccessible(true);
                }
                return field;
            } catch (NoSuchFieldException noSuchFieldException) {
                // empty catch block
            }
        }
        Field match = null;
        for (Class<?> class1 : ClassUtils.getAllInterfaces(cls)) {
            try {
                Field test = class1.getField(fieldName);
                Validate.isTrue(match == null, "Reference to field %s is ambiguous relative to %s; a matching field exists on two or more implemented interfaces.", fieldName, cls);
                match = test;
            } catch (NoSuchFieldException noSuchFieldException) {}
        }
        return match;
    }

    public static Field getDeclaredField(Class<?> cls, String fieldName) {
        return FieldUtils.getDeclaredField(cls, fieldName, false);
    }

    public static Field getDeclaredField(Class<?> cls, String fieldName, boolean forceAccess) {
        Validate.notNull(cls, "cls", new Object[0]);
        Validate.isTrue(StringUtils.isNotBlank(fieldName), "The field name must not be blank/empty", new Object[0]);
        try {
            Field field = cls.getDeclaredField(fieldName);
            if (!MemberUtils.isAccessible(field)) {
                if (forceAccess) {
                    field.setAccessible(true);
                } else {
                    return null;
                }
            }
            return field;
        } catch (NoSuchFieldException noSuchFieldException) {
            return null;
        }
    }

    public static Field[] getAllFields(Class<?> cls) {
        List<Field> allFieldsList = FieldUtils.getAllFieldsList(cls);
        return allFieldsList.toArray(ArrayUtils.EMPTY_FIELD_ARRAY);
    }

    public static List<Field> getAllFieldsList(Class<?> cls) {
        Validate.notNull(cls, "cls", new Object[0]);
        ArrayList<Field> allFields = new ArrayList<Field>();
        for (Class<?> currentClass = cls; currentClass != null; currentClass = currentClass.getSuperclass()) {
            Field[] declaredFields = currentClass.getDeclaredFields();
            Collections.addAll(allFields, declaredFields);
        }
        return allFields;
    }

    public static Field[] getFieldsWithAnnotation(Class<?> cls, Class<? extends Annotation> annotationCls) {
        List<Field> annotatedFieldsList = FieldUtils.getFieldsListWithAnnotation(cls, annotationCls);
        return annotatedFieldsList.toArray(ArrayUtils.EMPTY_FIELD_ARRAY);
    }

    public static List<Field> getFieldsListWithAnnotation(Class<?> cls, Class<? extends Annotation> annotationCls) {
        Validate.notNull(annotationCls, "annotationCls", new Object[0]);
        List<Field> allFields = FieldUtils.getAllFieldsList(cls);
        ArrayList<Field> annotatedFields = new ArrayList<Field>();
        for (Field field : allFields) {
            if (field.getAnnotation(annotationCls) == null) continue;
            annotatedFields.add(field);
        }
        return annotatedFields;
    }

    public static Object readStaticField(Field field) throws IllegalAccessException {
        return FieldUtils.readStaticField(field, false);
    }

    public static Object readStaticField(Field field, boolean forceAccess) throws IllegalAccessException {
        Validate.notNull(field, "field", new Object[0]);
        Validate.isTrue(Modifier.isStatic(field.getModifiers()), "The field '%s' is not static", field.getName());
        return FieldUtils.readField(field, null, forceAccess);
    }

    public static Object readStaticField(Class<?> cls, String fieldName) throws IllegalAccessException {
        return FieldUtils.readStaticField(cls, fieldName, false);
    }

    public static Object readStaticField(Class<?> cls, String fieldName, boolean forceAccess) throws IllegalAccessException {
        Field field = FieldUtils.getField(cls, fieldName, forceAccess);
        Validate.notNull(field, "Cannot locate field '%s' on %s", fieldName, cls);
        return FieldUtils.readStaticField(field, false);
    }

    public static Object readDeclaredStaticField(Class<?> cls, String fieldName) throws IllegalAccessException {
        return FieldUtils.readDeclaredStaticField(cls, fieldName, false);
    }

    public static Object readDeclaredStaticField(Class<?> cls, String fieldName, boolean forceAccess) throws IllegalAccessException {
        Field field = FieldUtils.getDeclaredField(cls, fieldName, forceAccess);
        Validate.notNull(field, "Cannot locate declared field %s.%s", cls.getName(), fieldName);
        return FieldUtils.readStaticField(field, false);
    }

    public static Object readField(Field field, Object target) throws IllegalAccessException {
        return FieldUtils.readField(field, target, false);
    }

    public static Object readField(Field field, Object target, boolean forceAccess) throws IllegalAccessException {
        Validate.notNull(field, "field", new Object[0]);
        if (forceAccess && !field.isAccessible()) {
            field.setAccessible(true);
        } else {
            MemberUtils.setAccessibleWorkaround(field);
        }
        return field.get(target);
    }

    public static Object readField(Object target, String fieldName) throws IllegalAccessException {
        return FieldUtils.readField(target, fieldName, false);
    }

    public static Object readField(Object target, String fieldName, boolean forceAccess) throws IllegalAccessException {
        Validate.notNull(target, "target", new Object[0]);
        Class<?> cls = target.getClass();
        Field field = FieldUtils.getField(cls, fieldName, forceAccess);
        Validate.isTrue(field != null, "Cannot locate field %s on %s", fieldName, cls);
        return FieldUtils.readField(field, target, false);
    }

    public static Object readDeclaredField(Object target, String fieldName) throws IllegalAccessException {
        return FieldUtils.readDeclaredField(target, fieldName, false);
    }

    public static Object readDeclaredField(Object target, String fieldName, boolean forceAccess) throws IllegalAccessException {
        Validate.notNull(target, "target", new Object[0]);
        Class<?> cls = target.getClass();
        Field field = FieldUtils.getDeclaredField(cls, fieldName, forceAccess);
        Validate.isTrue(field != null, "Cannot locate declared field %s.%s", cls, fieldName);
        return FieldUtils.readField(field, target, false);
    }

    public static void writeStaticField(Field field, Object value) throws IllegalAccessException {
        FieldUtils.writeStaticField(field, value, false);
    }

    public static void writeStaticField(Field field, Object value, boolean forceAccess) throws IllegalAccessException {
        Validate.notNull(field, "field", new Object[0]);
        Validate.isTrue(Modifier.isStatic(field.getModifiers()), "The field %s.%s is not static", field.getDeclaringClass().getName(), field.getName());
        FieldUtils.writeField(field, null, value, forceAccess);
    }

    public static void writeStaticField(Class<?> cls, String fieldName, Object value) throws IllegalAccessException {
        FieldUtils.writeStaticField(cls, fieldName, value, false);
    }

    public static void writeStaticField(Class<?> cls, String fieldName, Object value, boolean forceAccess) throws IllegalAccessException {
        Field field = FieldUtils.getField(cls, fieldName, forceAccess);
        Validate.notNull(field, "Cannot locate field %s on %s", fieldName, cls);
        FieldUtils.writeStaticField(field, value, false);
    }

    public static void writeDeclaredStaticField(Class<?> cls, String fieldName, Object value) throws IllegalAccessException {
        FieldUtils.writeDeclaredStaticField(cls, fieldName, value, false);
    }

    public static void writeDeclaredStaticField(Class<?> cls, String fieldName, Object value, boolean forceAccess) throws IllegalAccessException {
        Field field = FieldUtils.getDeclaredField(cls, fieldName, forceAccess);
        Validate.notNull(field, "Cannot locate declared field %s.%s", cls.getName(), fieldName);
        FieldUtils.writeField(field, null, value, false);
    }

    public static void writeField(Field field, Object target, Object value) throws IllegalAccessException {
        FieldUtils.writeField(field, target, value, false);
    }

    public static void writeField(Field field, Object target, Object value, boolean forceAccess) throws IllegalAccessException {
        Validate.notNull(field, "field", new Object[0]);
        if (forceAccess && !field.isAccessible()) {
            field.setAccessible(true);
        } else {
            MemberUtils.setAccessibleWorkaround(field);
        }
        field.set(target, value);
    }

    public static void removeFinalModifier(Field field) {
        FieldUtils.removeFinalModifier(field, true);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Deprecated
    public static void removeFinalModifier(Field field, boolean forceAccess) {
        block7: {
            Validate.notNull(field, "field", new Object[0]);
            try {
                boolean doForceAccess;
                if (!Modifier.isFinal(field.getModifiers())) break block7;
                Field modifiersField = Field.class.getDeclaredField("modifiers");
                boolean bl = doForceAccess = forceAccess && !modifiersField.isAccessible();
                if (doForceAccess) {
                    modifiersField.setAccessible(true);
                }
                try {
                    modifiersField.setInt(field, field.getModifiers() & 0xFFFFFFEF);
                } finally {
                    if (doForceAccess) {
                        modifiersField.setAccessible(false);
                    }
                }
            } catch (IllegalAccessException | NoSuchFieldException e) {
                if (!SystemUtils.isJavaVersionAtLeast(JavaVersion.JAVA_12)) break block7;
                throw new UnsupportedOperationException("In java 12+ final cannot be removed.", e);
            }
        }
    }

    public static void writeField(Object target, String fieldName, Object value) throws IllegalAccessException {
        FieldUtils.writeField(target, fieldName, value, false);
    }

    public static void writeField(Object target, String fieldName, Object value, boolean forceAccess) throws IllegalAccessException {
        Validate.notNull(target, "target", new Object[0]);
        Class<?> cls = target.getClass();
        Field field = FieldUtils.getField(cls, fieldName, forceAccess);
        Validate.isTrue(field != null, "Cannot locate declared field %s.%s", cls.getName(), fieldName);
        FieldUtils.writeField(field, target, value, false);
    }

    public static void writeDeclaredField(Object target, String fieldName, Object value) throws IllegalAccessException {
        FieldUtils.writeDeclaredField(target, fieldName, value, false);
    }

    public static void writeDeclaredField(Object target, String fieldName, Object value, boolean forceAccess) throws IllegalAccessException {
        Validate.notNull(target, "target", new Object[0]);
        Class<?> cls = target.getClass();
        Field field = FieldUtils.getDeclaredField(cls, fieldName, forceAccess);
        Validate.isTrue(field != null, "Cannot locate declared field %s.%s", cls.getName(), fieldName);
        FieldUtils.writeField(field, target, value, false);
    }
}

