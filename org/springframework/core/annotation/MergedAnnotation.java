/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.MissingMergedAnnotation;
import org.springframework.core.annotation.TypeMappedAnnotation;
import org.springframework.lang.Nullable;

public interface MergedAnnotation<A extends Annotation> {
    public static final String VALUE = "value";

    public Class<A> getType();

    public boolean isPresent();

    public boolean isDirectlyPresent();

    public boolean isMetaPresent();

    public int getDistance();

    public int getAggregateIndex();

    @Nullable
    public Object getSource();

    @Nullable
    public MergedAnnotation<?> getMetaSource();

    public MergedAnnotation<?> getRoot();

    public List<Class<? extends Annotation>> getMetaTypes();

    public boolean hasNonDefaultValue(String var1);

    public boolean hasDefaultValue(String var1) throws NoSuchElementException;

    public byte getByte(String var1) throws NoSuchElementException;

    public byte[] getByteArray(String var1) throws NoSuchElementException;

    public boolean getBoolean(String var1) throws NoSuchElementException;

    public boolean[] getBooleanArray(String var1) throws NoSuchElementException;

    public char getChar(String var1) throws NoSuchElementException;

    public char[] getCharArray(String var1) throws NoSuchElementException;

    public short getShort(String var1) throws NoSuchElementException;

    public short[] getShortArray(String var1) throws NoSuchElementException;

    public int getInt(String var1) throws NoSuchElementException;

    public int[] getIntArray(String var1) throws NoSuchElementException;

    public long getLong(String var1) throws NoSuchElementException;

    public long[] getLongArray(String var1) throws NoSuchElementException;

    public double getDouble(String var1) throws NoSuchElementException;

    public double[] getDoubleArray(String var1) throws NoSuchElementException;

    public float getFloat(String var1) throws NoSuchElementException;

    public float[] getFloatArray(String var1) throws NoSuchElementException;

    public String getString(String var1) throws NoSuchElementException;

    public String[] getStringArray(String var1) throws NoSuchElementException;

    public Class<?> getClass(String var1) throws NoSuchElementException;

    public Class<?>[] getClassArray(String var1) throws NoSuchElementException;

    public <E extends Enum<E>> E getEnum(String var1, Class<E> var2) throws NoSuchElementException;

    public <E extends Enum<E>> E[] getEnumArray(String var1, Class<E> var2) throws NoSuchElementException;

    public <T extends Annotation> MergedAnnotation<T> getAnnotation(String var1, Class<T> var2) throws NoSuchElementException;

    public <T extends Annotation> MergedAnnotation<T>[] getAnnotationArray(String var1, Class<T> var2) throws NoSuchElementException;

    public Optional<Object> getValue(String var1);

    public <T> Optional<T> getValue(String var1, Class<T> var2);

    public Optional<Object> getDefaultValue(String var1);

    public <T> Optional<T> getDefaultValue(String var1, Class<T> var2);

    public MergedAnnotation<A> filterDefaultValues();

    public MergedAnnotation<A> filterAttributes(Predicate<String> var1);

    public MergedAnnotation<A> withNonMergedAttributes();

    public AnnotationAttributes asAnnotationAttributes(Adapt ... var1);

    public Map<String, Object> asMap(Adapt ... var1);

    public <T extends Map<String, Object>> T asMap(Function<MergedAnnotation<?>, T> var1, Adapt ... var2);

    public A synthesize() throws NoSuchElementException;

    public Optional<A> synthesize(Predicate<? super MergedAnnotation<A>> var1) throws NoSuchElementException;

    public static <A extends Annotation> MergedAnnotation<A> missing() {
        return MissingMergedAnnotation.getInstance();
    }

    public static <A extends Annotation> MergedAnnotation<A> from(A annotation) {
        return MergedAnnotation.from(null, annotation);
    }

    public static <A extends Annotation> MergedAnnotation<A> from(@Nullable Object source, A annotation) {
        return TypeMappedAnnotation.from(source, annotation);
    }

    public static <A extends Annotation> MergedAnnotation<A> of(Class<A> annotationType) {
        return MergedAnnotation.of(null, annotationType, null);
    }

    public static <A extends Annotation> MergedAnnotation<A> of(Class<A> annotationType, @Nullable Map<String, ?> attributes) {
        return MergedAnnotation.of(null, annotationType, attributes);
    }

    public static <A extends Annotation> MergedAnnotation<A> of(@Nullable AnnotatedElement source, Class<A> annotationType, @Nullable Map<String, ?> attributes) {
        return MergedAnnotation.of(null, source, annotationType, attributes);
    }

    public static <A extends Annotation> MergedAnnotation<A> of(@Nullable ClassLoader classLoader, @Nullable Object source, Class<A> annotationType, @Nullable Map<String, ?> attributes) {
        return TypeMappedAnnotation.of(classLoader, source, annotationType, attributes);
    }

    public static enum Adapt {
        CLASS_TO_STRING,
        ANNOTATION_TO_MAP;


        protected final boolean isIn(Adapt ... adaptations) {
            for (Adapt candidate : adaptations) {
                if (candidate != this) continue;
                return true;
            }
            return false;
        }

        public static Adapt[] values(boolean classToString, boolean annotationsToMap) {
            EnumSet<Adapt> result = EnumSet.noneOf(Adapt.class);
            Adapt.addIfTrue(result, CLASS_TO_STRING, classToString);
            Adapt.addIfTrue(result, ANNOTATION_TO_MAP, annotationsToMap);
            return result.toArray(new Adapt[0]);
        }

        private static <T> void addIfTrue(Set<T> result, T value, boolean test) {
            if (test) {
                result.add(value);
            }
        }
    }
}

