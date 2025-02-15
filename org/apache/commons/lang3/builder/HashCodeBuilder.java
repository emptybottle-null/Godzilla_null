/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.builder;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.ArraySorter;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.Builder;
import org.apache.commons.lang3.builder.HashCodeExclude;
import org.apache.commons.lang3.builder.IDKey;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class HashCodeBuilder
implements Builder<Integer> {
    private static final int DEFAULT_INITIAL_VALUE = 17;
    private static final int DEFAULT_MULTIPLIER_VALUE = 37;
    private static final ThreadLocal<Set<IDKey>> REGISTRY = new ThreadLocal();
    private final int iConstant;
    private int iTotal;

    static Set<IDKey> getRegistry() {
        return REGISTRY.get();
    }

    static boolean isRegistered(Object value) {
        Set<IDKey> registry = HashCodeBuilder.getRegistry();
        return registry != null && registry.contains(new IDKey(value));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void reflectionAppend(Object object, Class<?> clazz, HashCodeBuilder builder, boolean useTransients, String[] excludeFields) {
        if (HashCodeBuilder.isRegistered(object)) {
            return;
        }
        try {
            HashCodeBuilder.register(object);
            AccessibleObject[] fields = ArraySorter.sort(clazz.getDeclaredFields(), Comparator.comparing(Field::getName));
            AccessibleObject.setAccessible(fields, true);
            for (AccessibleObject field : fields) {
                if (ArrayUtils.contains(excludeFields, ((Field)field).getName()) || ((Field)field).getName().contains("$") || !useTransients && Modifier.isTransient(((Field)field).getModifiers()) || Modifier.isStatic(((Field)field).getModifiers()) || field.isAnnotationPresent(HashCodeExclude.class)) continue;
                try {
                    Object fieldValue = ((Field)field).get(object);
                    builder.append(fieldValue);
                } catch (IllegalAccessException e) {
                    throw new InternalError("Unexpected IllegalAccessException");
                }
            }
        } finally {
            HashCodeBuilder.unregister(object);
        }
    }

    public static int reflectionHashCode(int initialNonZeroOddNumber, int multiplierNonZeroOddNumber, Object object) {
        return HashCodeBuilder.reflectionHashCode(initialNonZeroOddNumber, multiplierNonZeroOddNumber, object, false, null, new String[0]);
    }

    public static int reflectionHashCode(int initialNonZeroOddNumber, int multiplierNonZeroOddNumber, Object object, boolean testTransients) {
        return HashCodeBuilder.reflectionHashCode(initialNonZeroOddNumber, multiplierNonZeroOddNumber, object, testTransients, null, new String[0]);
    }

    public static <T> int reflectionHashCode(int initialNonZeroOddNumber, int multiplierNonZeroOddNumber, T object, boolean testTransients, Class<? super T> reflectUpToClass, String ... excludeFields) {
        Class<?> clazz;
        Validate.notNull(object, "object", new Object[0]);
        HashCodeBuilder builder = new HashCodeBuilder(initialNonZeroOddNumber, multiplierNonZeroOddNumber);
        HashCodeBuilder.reflectionAppend(object, clazz, builder, testTransients, excludeFields);
        for (clazz = object.getClass(); clazz.getSuperclass() != null && clazz != reflectUpToClass; clazz = clazz.getSuperclass()) {
            HashCodeBuilder.reflectionAppend(object, clazz, builder, testTransients, excludeFields);
        }
        return builder.toHashCode();
    }

    public static int reflectionHashCode(Object object, boolean testTransients) {
        return HashCodeBuilder.reflectionHashCode(17, 37, object, testTransients, null, new String[0]);
    }

    public static int reflectionHashCode(Object object, Collection<String> excludeFields) {
        return HashCodeBuilder.reflectionHashCode(object, ReflectionToStringBuilder.toNoNullStringArray(excludeFields));
    }

    public static int reflectionHashCode(Object object, String ... excludeFields) {
        return HashCodeBuilder.reflectionHashCode(17, 37, object, false, null, excludeFields);
    }

    private static void register(Object value) {
        Set<IDKey> registry = HashCodeBuilder.getRegistry();
        if (registry == null) {
            registry = new HashSet<IDKey>();
            REGISTRY.set(registry);
        }
        registry.add(new IDKey(value));
    }

    private static void unregister(Object value) {
        Set<IDKey> registry = HashCodeBuilder.getRegistry();
        if (registry != null) {
            registry.remove(new IDKey(value));
            if (registry.isEmpty()) {
                REGISTRY.remove();
            }
        }
    }

    public HashCodeBuilder() {
        this.iConstant = 37;
        this.iTotal = 17;
    }

    public HashCodeBuilder(int initialOddNumber, int multiplierOddNumber) {
        Validate.isTrue(initialOddNumber % 2 != 0, "HashCodeBuilder requires an odd initial value", new Object[0]);
        Validate.isTrue(multiplierOddNumber % 2 != 0, "HashCodeBuilder requires an odd multiplier", new Object[0]);
        this.iConstant = multiplierOddNumber;
        this.iTotal = initialOddNumber;
    }

    public HashCodeBuilder append(boolean value) {
        this.iTotal = this.iTotal * this.iConstant + (value ? 0 : 1);
        return this;
    }

    public HashCodeBuilder append(boolean[] array) {
        if (array == null) {
            this.iTotal *= this.iConstant;
        } else {
            for (boolean element : array) {
                this.append(element);
            }
        }
        return this;
    }

    public HashCodeBuilder append(byte value) {
        this.iTotal = this.iTotal * this.iConstant + value;
        return this;
    }

    public HashCodeBuilder append(byte[] array) {
        if (array == null) {
            this.iTotal *= this.iConstant;
        } else {
            for (byte element : array) {
                this.append(element);
            }
        }
        return this;
    }

    public HashCodeBuilder append(char value) {
        this.iTotal = this.iTotal * this.iConstant + value;
        return this;
    }

    public HashCodeBuilder append(char[] array) {
        if (array == null) {
            this.iTotal *= this.iConstant;
        } else {
            for (char element : array) {
                this.append(element);
            }
        }
        return this;
    }

    public HashCodeBuilder append(double value) {
        return this.append(Double.doubleToLongBits(value));
    }

    public HashCodeBuilder append(double[] array) {
        if (array == null) {
            this.iTotal *= this.iConstant;
        } else {
            for (double element : array) {
                this.append(element);
            }
        }
        return this;
    }

    public HashCodeBuilder append(float value) {
        this.iTotal = this.iTotal * this.iConstant + Float.floatToIntBits(value);
        return this;
    }

    public HashCodeBuilder append(float[] array) {
        if (array == null) {
            this.iTotal *= this.iConstant;
        } else {
            for (float element : array) {
                this.append(element);
            }
        }
        return this;
    }

    public HashCodeBuilder append(int value) {
        this.iTotal = this.iTotal * this.iConstant + value;
        return this;
    }

    public HashCodeBuilder append(int[] array) {
        if (array == null) {
            this.iTotal *= this.iConstant;
        } else {
            for (int element : array) {
                this.append(element);
            }
        }
        return this;
    }

    public HashCodeBuilder append(long value) {
        this.iTotal = this.iTotal * this.iConstant + (int)(value ^ value >> 32);
        return this;
    }

    public HashCodeBuilder append(long[] array) {
        if (array == null) {
            this.iTotal *= this.iConstant;
        } else {
            for (long element : array) {
                this.append(element);
            }
        }
        return this;
    }

    public HashCodeBuilder append(Object object) {
        if (object == null) {
            this.iTotal *= this.iConstant;
        } else if (object.getClass().isArray()) {
            this.appendArray(object);
        } else {
            this.iTotal = this.iTotal * this.iConstant + object.hashCode();
        }
        return this;
    }

    private void appendArray(Object object) {
        if (object instanceof long[]) {
            this.append((long[])object);
        } else if (object instanceof int[]) {
            this.append((int[])object);
        } else if (object instanceof short[]) {
            this.append((short[])object);
        } else if (object instanceof char[]) {
            this.append((char[])object);
        } else if (object instanceof byte[]) {
            this.append((byte[])object);
        } else if (object instanceof double[]) {
            this.append((double[])object);
        } else if (object instanceof float[]) {
            this.append((float[])object);
        } else if (object instanceof boolean[]) {
            this.append((boolean[])object);
        } else {
            this.append((Object[])object);
        }
    }

    public HashCodeBuilder append(Object[] array) {
        if (array == null) {
            this.iTotal *= this.iConstant;
        } else {
            for (Object element : array) {
                this.append(element);
            }
        }
        return this;
    }

    public HashCodeBuilder append(short value) {
        this.iTotal = this.iTotal * this.iConstant + value;
        return this;
    }

    public HashCodeBuilder append(short[] array) {
        if (array == null) {
            this.iTotal *= this.iConstant;
        } else {
            for (short element : array) {
                this.append(element);
            }
        }
        return this;
    }

    public HashCodeBuilder appendSuper(int superHashCode) {
        this.iTotal = this.iTotal * this.iConstant + superHashCode;
        return this;
    }

    public int toHashCode() {
        return this.iTotal;
    }

    @Override
    public Integer build() {
        return this.toHashCode();
    }

    public int hashCode() {
        return this.toHashCode();
    }
}

