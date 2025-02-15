/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

abstract class AbstractMergedAnnotation<A extends Annotation>
implements MergedAnnotation<A> {
    @Nullable
    private volatile A synthesizedAnnotation;

    AbstractMergedAnnotation() {
    }

    @Override
    public boolean isDirectlyPresent() {
        return this.isPresent() && this.getDistance() == 0;
    }

    @Override
    public boolean isMetaPresent() {
        return this.isPresent() && this.getDistance() > 0;
    }

    @Override
    public boolean hasNonDefaultValue(String attributeName) {
        return !this.hasDefaultValue(attributeName);
    }

    @Override
    public byte getByte(String attributeName) {
        return this.getRequiredAttributeValue(attributeName, Byte.class);
    }

    @Override
    public byte[] getByteArray(String attributeName) {
        return this.getRequiredAttributeValue(attributeName, byte[].class);
    }

    @Override
    public boolean getBoolean(String attributeName) {
        return this.getRequiredAttributeValue(attributeName, Boolean.class);
    }

    @Override
    public boolean[] getBooleanArray(String attributeName) {
        return this.getRequiredAttributeValue(attributeName, boolean[].class);
    }

    @Override
    public char getChar(String attributeName) {
        return this.getRequiredAttributeValue(attributeName, Character.class).charValue();
    }

    @Override
    public char[] getCharArray(String attributeName) {
        return this.getRequiredAttributeValue(attributeName, char[].class);
    }

    @Override
    public short getShort(String attributeName) {
        return this.getRequiredAttributeValue(attributeName, Short.class);
    }

    @Override
    public short[] getShortArray(String attributeName) {
        return this.getRequiredAttributeValue(attributeName, short[].class);
    }

    @Override
    public int getInt(String attributeName) {
        return this.getRequiredAttributeValue(attributeName, Integer.class);
    }

    @Override
    public int[] getIntArray(String attributeName) {
        return this.getRequiredAttributeValue(attributeName, int[].class);
    }

    @Override
    public long getLong(String attributeName) {
        return this.getRequiredAttributeValue(attributeName, Long.class);
    }

    @Override
    public long[] getLongArray(String attributeName) {
        return this.getRequiredAttributeValue(attributeName, long[].class);
    }

    @Override
    public double getDouble(String attributeName) {
        return this.getRequiredAttributeValue(attributeName, Double.class);
    }

    @Override
    public double[] getDoubleArray(String attributeName) {
        return this.getRequiredAttributeValue(attributeName, double[].class);
    }

    @Override
    public float getFloat(String attributeName) {
        return this.getRequiredAttributeValue(attributeName, Float.class).floatValue();
    }

    @Override
    public float[] getFloatArray(String attributeName) {
        return this.getRequiredAttributeValue(attributeName, float[].class);
    }

    @Override
    public String getString(String attributeName) {
        return this.getRequiredAttributeValue(attributeName, String.class);
    }

    @Override
    public String[] getStringArray(String attributeName) {
        return this.getRequiredAttributeValue(attributeName, String[].class);
    }

    @Override
    public Class<?> getClass(String attributeName) {
        return this.getRequiredAttributeValue(attributeName, Class.class);
    }

    @Override
    public Class<?>[] getClassArray(String attributeName) {
        return this.getRequiredAttributeValue(attributeName, Class[].class);
    }

    @Override
    public <E extends Enum<E>> E getEnum(String attributeName, Class<E> type) {
        Assert.notNull(type, "Type must not be null");
        return (E)((Enum)this.getRequiredAttributeValue(attributeName, type));
    }

    @Override
    public <E extends Enum<E>> E[] getEnumArray(String attributeName, Class<E> type) {
        Assert.notNull(type, "Type must not be null");
        Class<?> arrayType = Array.newInstance(type, 0).getClass();
        return (Enum[])this.getRequiredAttributeValue(attributeName, arrayType);
    }

    @Override
    public Optional<Object> getValue(String attributeName) {
        return this.getValue(attributeName, Object.class);
    }

    @Override
    public <T> Optional<T> getValue(String attributeName, Class<T> type) {
        return Optional.ofNullable(this.getAttributeValue(attributeName, type));
    }

    @Override
    public Optional<Object> getDefaultValue(String attributeName) {
        return this.getDefaultValue(attributeName, Object.class);
    }

    @Override
    public MergedAnnotation<A> filterDefaultValues() {
        return this.filterAttributes(this::hasNonDefaultValue);
    }

    @Override
    public AnnotationAttributes asAnnotationAttributes(MergedAnnotation.Adapt ... adaptations) {
        return this.asMap((MergedAnnotation<?> mergedAnnotation) -> new AnnotationAttributes(mergedAnnotation.getType()), adaptations);
    }

    @Override
    public Optional<A> synthesize(Predicate<? super MergedAnnotation<A>> condition) throws NoSuchElementException {
        return condition.test(this) ? Optional.of(this.synthesize()) : Optional.empty();
    }

    @Override
    public A synthesize() {
        if (!this.isPresent()) {
            throw new NoSuchElementException("Unable to synthesize missing annotation");
        }
        A synthesized = this.synthesizedAnnotation;
        if (synthesized == null) {
            this.synthesizedAnnotation = synthesized = this.createSynthesized();
        }
        return synthesized;
    }

    private <T> T getRequiredAttributeValue(String attributeName, Class<T> type) {
        T value = this.getAttributeValue(attributeName, type);
        if (value == null) {
            throw new NoSuchElementException("No attribute named '" + attributeName + "' present in merged annotation " + this.getType().getName());
        }
        return value;
    }

    @Nullable
    protected abstract <T> T getAttributeValue(String var1, Class<T> var2);

    protected abstract A createSynthesized();
}

