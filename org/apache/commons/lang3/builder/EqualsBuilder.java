/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.builder;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.builder.Builder;
import org.apache.commons.lang3.builder.EqualsExclude;
import org.apache.commons.lang3.builder.IDKey;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.tuple.Pair;

public class EqualsBuilder
implements Builder<Boolean> {
    private static final ThreadLocal<Set<Pair<IDKey, IDKey>>> REGISTRY = new ThreadLocal();
    private boolean isEquals = true;
    private boolean testTransients;
    private boolean testRecursive;
    private List<Class<?>> bypassReflectionClasses = new ArrayList();
    private Class<?> reflectUpToClass;
    private String[] excludeFields;

    static Set<Pair<IDKey, IDKey>> getRegistry() {
        return REGISTRY.get();
    }

    static Pair<IDKey, IDKey> getRegisterPair(Object lhs, Object rhs) {
        IDKey left = new IDKey(lhs);
        IDKey right = new IDKey(rhs);
        return Pair.of(left, right);
    }

    static boolean isRegistered(Object lhs, Object rhs) {
        Set<Pair<IDKey, IDKey>> registry = EqualsBuilder.getRegistry();
        Pair<IDKey, IDKey> pair = EqualsBuilder.getRegisterPair(lhs, rhs);
        Pair<IDKey, IDKey> swappedPair = Pair.of(pair.getRight(), pair.getLeft());
        return registry != null && (registry.contains(pair) || registry.contains(swappedPair));
    }

    private static void register(Object lhs, Object rhs) {
        Set<Pair<IDKey, IDKey>> registry = EqualsBuilder.getRegistry();
        if (registry == null) {
            registry = new HashSet<Pair<IDKey, IDKey>>();
            REGISTRY.set(registry);
        }
        Pair<IDKey, IDKey> pair = EqualsBuilder.getRegisterPair(lhs, rhs);
        registry.add(pair);
    }

    private static void unregister(Object lhs, Object rhs) {
        Set<Pair<IDKey, IDKey>> registry = EqualsBuilder.getRegistry();
        if (registry != null) {
            Pair<IDKey, IDKey> pair = EqualsBuilder.getRegisterPair(lhs, rhs);
            registry.remove(pair);
            if (registry.isEmpty()) {
                REGISTRY.remove();
            }
        }
    }

    public EqualsBuilder() {
        this.bypassReflectionClasses.add(String.class);
    }

    public EqualsBuilder setTestTransients(boolean testTransients) {
        this.testTransients = testTransients;
        return this;
    }

    public EqualsBuilder setTestRecursive(boolean testRecursive) {
        this.testRecursive = testRecursive;
        return this;
    }

    public EqualsBuilder setBypassReflectionClasses(List<Class<?>> bypassReflectionClasses) {
        this.bypassReflectionClasses = bypassReflectionClasses;
        return this;
    }

    public EqualsBuilder setReflectUpToClass(Class<?> reflectUpToClass) {
        this.reflectUpToClass = reflectUpToClass;
        return this;
    }

    public EqualsBuilder setExcludeFields(String ... excludeFields) {
        this.excludeFields = excludeFields;
        return this;
    }

    public static boolean reflectionEquals(Object lhs, Object rhs, Collection<String> excludeFields) {
        return EqualsBuilder.reflectionEquals(lhs, rhs, ReflectionToStringBuilder.toNoNullStringArray(excludeFields));
    }

    public static boolean reflectionEquals(Object lhs, Object rhs, String ... excludeFields) {
        return EqualsBuilder.reflectionEquals(lhs, rhs, false, null, excludeFields);
    }

    public static boolean reflectionEquals(Object lhs, Object rhs, boolean testTransients) {
        return EqualsBuilder.reflectionEquals(lhs, rhs, testTransients, null, new String[0]);
    }

    public static boolean reflectionEquals(Object lhs, Object rhs, boolean testTransients, Class<?> reflectUpToClass, String ... excludeFields) {
        return EqualsBuilder.reflectionEquals(lhs, rhs, testTransients, reflectUpToClass, false, excludeFields);
    }

    public static boolean reflectionEquals(Object lhs, Object rhs, boolean testTransients, Class<?> reflectUpToClass, boolean testRecursive, String ... excludeFields) {
        if (lhs == rhs) {
            return true;
        }
        if (lhs == null || rhs == null) {
            return false;
        }
        return new EqualsBuilder().setExcludeFields(excludeFields).setReflectUpToClass(reflectUpToClass).setTestTransients(testTransients).setTestRecursive(testRecursive).reflectionAppend(lhs, rhs).isEquals();
    }

    public EqualsBuilder reflectionAppend(Object lhs, Object rhs) {
        Class<?> testClass;
        if (!this.isEquals) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null || rhs == null) {
            this.isEquals = false;
            return this;
        }
        Class<?> lhsClass = lhs.getClass();
        Class<?> rhsClass = rhs.getClass();
        if (lhsClass.isInstance(rhs)) {
            testClass = lhsClass;
            if (!rhsClass.isInstance(lhs)) {
                testClass = rhsClass;
            }
        } else if (rhsClass.isInstance(lhs)) {
            testClass = rhsClass;
            if (!lhsClass.isInstance(rhs)) {
                testClass = lhsClass;
            }
        } else {
            this.isEquals = false;
            return this;
        }
        try {
            if (testClass.isArray()) {
                this.append(lhs, rhs);
            } else if (this.bypassReflectionClasses != null && (this.bypassReflectionClasses.contains(lhsClass) || this.bypassReflectionClasses.contains(rhsClass))) {
                this.isEquals = lhs.equals(rhs);
            } else {
                this.reflectionAppend(lhs, rhs, testClass);
                while (testClass.getSuperclass() != null && testClass != this.reflectUpToClass) {
                    testClass = testClass.getSuperclass();
                    this.reflectionAppend(lhs, rhs, testClass);
                }
            }
        } catch (IllegalArgumentException e) {
            this.isEquals = false;
        }
        return this;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void reflectionAppend(Object lhs, Object rhs, Class<?> clazz) {
        if (EqualsBuilder.isRegistered(lhs, rhs)) {
            return;
        }
        try {
            EqualsBuilder.register(lhs, rhs);
            AccessibleObject[] fields = clazz.getDeclaredFields();
            AccessibleObject.setAccessible(fields, true);
            for (int i = 0; i < fields.length && this.isEquals; ++i) {
                AccessibleObject f = fields[i];
                if (ArrayUtils.contains(this.excludeFields, ((Field)f).getName()) || ((Field)f).getName().contains("$") || !this.testTransients && Modifier.isTransient(((Field)f).getModifiers()) || Modifier.isStatic(((Field)f).getModifiers()) || f.isAnnotationPresent(EqualsExclude.class)) continue;
                try {
                    this.append(((Field)f).get(lhs), ((Field)f).get(rhs));
                    continue;
                } catch (IllegalAccessException e) {
                    throw new InternalError("Unexpected IllegalAccessException");
                }
            }
        } finally {
            EqualsBuilder.unregister(lhs, rhs);
        }
    }

    public EqualsBuilder appendSuper(boolean superEquals) {
        if (!this.isEquals) {
            return this;
        }
        this.isEquals = superEquals;
        return this;
    }

    public EqualsBuilder append(Object lhs, Object rhs) {
        if (!this.isEquals) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null || rhs == null) {
            this.setEquals(false);
            return this;
        }
        Class<?> lhsClass = lhs.getClass();
        if (lhsClass.isArray()) {
            this.appendArray(lhs, rhs);
        } else if (this.testRecursive && !ClassUtils.isPrimitiveOrWrapper(lhsClass)) {
            this.reflectionAppend(lhs, rhs);
        } else {
            this.isEquals = lhs.equals(rhs);
        }
        return this;
    }

    private void appendArray(Object lhs, Object rhs) {
        if (lhs.getClass() != rhs.getClass()) {
            this.setEquals(false);
        } else if (lhs instanceof long[]) {
            this.append((long[])lhs, (long[])rhs);
        } else if (lhs instanceof int[]) {
            this.append((int[])lhs, (int[])rhs);
        } else if (lhs instanceof short[]) {
            this.append((short[])lhs, (short[])rhs);
        } else if (lhs instanceof char[]) {
            this.append((char[])lhs, (char[])rhs);
        } else if (lhs instanceof byte[]) {
            this.append((byte[])lhs, (byte[])rhs);
        } else if (lhs instanceof double[]) {
            this.append((double[])lhs, (double[])rhs);
        } else if (lhs instanceof float[]) {
            this.append((float[])lhs, (float[])rhs);
        } else if (lhs instanceof boolean[]) {
            this.append((boolean[])lhs, (boolean[])rhs);
        } else {
            this.append((Object[])lhs, (Object[])rhs);
        }
    }

    public EqualsBuilder append(long lhs, long rhs) {
        if (!this.isEquals) {
            return this;
        }
        this.isEquals = lhs == rhs;
        return this;
    }

    public EqualsBuilder append(int lhs, int rhs) {
        if (!this.isEquals) {
            return this;
        }
        this.isEquals = lhs == rhs;
        return this;
    }

    public EqualsBuilder append(short lhs, short rhs) {
        if (!this.isEquals) {
            return this;
        }
        this.isEquals = lhs == rhs;
        return this;
    }

    public EqualsBuilder append(char lhs, char rhs) {
        if (!this.isEquals) {
            return this;
        }
        this.isEquals = lhs == rhs;
        return this;
    }

    public EqualsBuilder append(byte lhs, byte rhs) {
        if (!this.isEquals) {
            return this;
        }
        this.isEquals = lhs == rhs;
        return this;
    }

    public EqualsBuilder append(double lhs, double rhs) {
        if (!this.isEquals) {
            return this;
        }
        return this.append(Double.doubleToLongBits(lhs), Double.doubleToLongBits(rhs));
    }

    public EqualsBuilder append(float lhs, float rhs) {
        if (!this.isEquals) {
            return this;
        }
        return this.append(Float.floatToIntBits(lhs), Float.floatToIntBits(rhs));
    }

    public EqualsBuilder append(boolean lhs, boolean rhs) {
        if (!this.isEquals) {
            return this;
        }
        this.isEquals = lhs == rhs;
        return this;
    }

    public EqualsBuilder append(Object[] lhs, Object[] rhs) {
        if (!this.isEquals) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null || rhs == null) {
            this.setEquals(false);
            return this;
        }
        if (lhs.length != rhs.length) {
            this.setEquals(false);
            return this;
        }
        for (int i = 0; i < lhs.length && this.isEquals; ++i) {
            this.append(lhs[i], rhs[i]);
        }
        return this;
    }

    public EqualsBuilder append(long[] lhs, long[] rhs) {
        if (!this.isEquals) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null || rhs == null) {
            this.setEquals(false);
            return this;
        }
        if (lhs.length != rhs.length) {
            this.setEquals(false);
            return this;
        }
        for (int i = 0; i < lhs.length && this.isEquals; ++i) {
            this.append(lhs[i], rhs[i]);
        }
        return this;
    }

    public EqualsBuilder append(int[] lhs, int[] rhs) {
        if (!this.isEquals) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null || rhs == null) {
            this.setEquals(false);
            return this;
        }
        if (lhs.length != rhs.length) {
            this.setEquals(false);
            return this;
        }
        for (int i = 0; i < lhs.length && this.isEquals; ++i) {
            this.append(lhs[i], rhs[i]);
        }
        return this;
    }

    public EqualsBuilder append(short[] lhs, short[] rhs) {
        if (!this.isEquals) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null || rhs == null) {
            this.setEquals(false);
            return this;
        }
        if (lhs.length != rhs.length) {
            this.setEquals(false);
            return this;
        }
        for (int i = 0; i < lhs.length && this.isEquals; ++i) {
            this.append(lhs[i], rhs[i]);
        }
        return this;
    }

    public EqualsBuilder append(char[] lhs, char[] rhs) {
        if (!this.isEquals) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null || rhs == null) {
            this.setEquals(false);
            return this;
        }
        if (lhs.length != rhs.length) {
            this.setEquals(false);
            return this;
        }
        for (int i = 0; i < lhs.length && this.isEquals; ++i) {
            this.append(lhs[i], rhs[i]);
        }
        return this;
    }

    public EqualsBuilder append(byte[] lhs, byte[] rhs) {
        if (!this.isEquals) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null || rhs == null) {
            this.setEquals(false);
            return this;
        }
        if (lhs.length != rhs.length) {
            this.setEquals(false);
            return this;
        }
        for (int i = 0; i < lhs.length && this.isEquals; ++i) {
            this.append(lhs[i], rhs[i]);
        }
        return this;
    }

    public EqualsBuilder append(double[] lhs, double[] rhs) {
        if (!this.isEquals) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null || rhs == null) {
            this.setEquals(false);
            return this;
        }
        if (lhs.length != rhs.length) {
            this.setEquals(false);
            return this;
        }
        for (int i = 0; i < lhs.length && this.isEquals; ++i) {
            this.append(lhs[i], rhs[i]);
        }
        return this;
    }

    public EqualsBuilder append(float[] lhs, float[] rhs) {
        if (!this.isEquals) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null || rhs == null) {
            this.setEquals(false);
            return this;
        }
        if (lhs.length != rhs.length) {
            this.setEquals(false);
            return this;
        }
        for (int i = 0; i < lhs.length && this.isEquals; ++i) {
            this.append(lhs[i], rhs[i]);
        }
        return this;
    }

    public EqualsBuilder append(boolean[] lhs, boolean[] rhs) {
        if (!this.isEquals) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null || rhs == null) {
            this.setEquals(false);
            return this;
        }
        if (lhs.length != rhs.length) {
            this.setEquals(false);
            return this;
        }
        for (int i = 0; i < lhs.length && this.isEquals; ++i) {
            this.append(lhs[i], rhs[i]);
        }
        return this;
    }

    public boolean isEquals() {
        return this.isEquals;
    }

    @Override
    public Boolean build() {
        return this.isEquals();
    }

    protected void setEquals(boolean isEquals) {
        this.isEquals = isEquals;
    }

    public void reset() {
        this.isEquals = true;
    }
}

