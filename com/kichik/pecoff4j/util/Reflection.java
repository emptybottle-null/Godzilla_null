/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Reflection {
    public static String toString(Object o) {
        Field[] fields;
        StringBuilder sb = new StringBuilder();
        for (Field f : fields = o.getClass().getDeclaredFields()) {
            if (Modifier.isStatic(f.getModifiers())) continue;
            f.setAccessible(true);
            sb.append(f.getName());
            sb.append(": ");
            try {
                Object val = f.get(o);
                if (val instanceof Integer) {
                    sb.append(f.get(o));
                    sb.append(" (0x");
                    sb.append(Integer.toHexString((Integer)val));
                    sb.append(")");
                } else if (val instanceof Long) {
                    sb.append(f.get(o));
                    sb.append(" (0x");
                    sb.append(Long.toHexString((Long)val));
                    sb.append(")");
                } else if (val != null && val.getClass().isArray()) {
                    int i;
                    Object[] arr;
                    if (val instanceof int[]) {
                        arr = (int[])val;
                        for (i = 0; i < arr.length && i < 10; ++i) {
                            if (i != 0) {
                                sb.append(", ");
                            }
                            sb.append(arr[i]);
                        }
                    } else if (val instanceof byte[]) {
                        arr = (byte[])val;
                        for (i = 0; i < arr.length && i < 10; ++i) {
                            if (i != 0) {
                                sb.append(", ");
                            }
                            sb.append(Integer.toHexString(arr[i] & 0xFF));
                        }
                    } else {
                        arr = (Object[])val;
                        for (i = 0; i < arr.length && i < 10; ++i) {
                            if (i != 0) {
                                sb.append(", ");
                            }
                            sb.append((Object)arr[i]);
                        }
                    }
                } else {
                    sb.append(f.get(o));
                }
            } catch (Exception e) {
                sb.append(e.getMessage());
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static String getConstantName(Class clazz, int value) throws Exception {
        Field[] fields = clazz.getDeclaredFields();
        Integer valObj = value;
        for (int i = 0; i < fields.length; ++i) {
            Field f = fields[i];
            if (!Modifier.isStatic(f.getModifiers()) || !Modifier.isPublic(f.getModifiers()) || !f.get(null).equals(valObj)) continue;
            return f.getName();
        }
        return null;
    }

    public static void println(Object o) {
        System.out.println(Reflection.toString(o));
    }
}

