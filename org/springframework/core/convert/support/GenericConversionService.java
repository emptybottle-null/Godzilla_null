/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.convert.support;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CopyOnWriteArraySet;
import org.springframework.core.DecoratingProxy;
import org.springframework.core.ResolvableType;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.ConverterNotFoundException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalConverter;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.core.convert.support.ConversionUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.StringUtils;

public class GenericConversionService
implements ConfigurableConversionService {
    private static final GenericConverter NO_OP_CONVERTER = new NoOpConverter("NO_OP");
    private static final GenericConverter NO_MATCH = new NoOpConverter("NO_MATCH");
    private final Converters converters = new Converters();
    private final Map<ConverterCacheKey, GenericConverter> converterCache = new ConcurrentReferenceHashMap<ConverterCacheKey, GenericConverter>(64);

    @Override
    public void addConverter(Converter<?, ?> converter) {
        ResolvableType[] typeInfo = this.getRequiredTypeInfo(converter.getClass(), Converter.class);
        if (typeInfo == null && converter instanceof DecoratingProxy) {
            typeInfo = this.getRequiredTypeInfo(((DecoratingProxy)((Object)converter)).getDecoratedClass(), Converter.class);
        }
        if (typeInfo == null) {
            throw new IllegalArgumentException("Unable to determine source type <S> and target type <T> for your Converter [" + converter.getClass().getName() + "]; does the class parameterize those types?");
        }
        this.addConverter(new ConverterAdapter(converter, typeInfo[0], typeInfo[1]));
    }

    @Override
    public <S, T> void addConverter(Class<S> sourceType, Class<T> targetType, Converter<? super S, ? extends T> converter) {
        this.addConverter(new ConverterAdapter(converter, ResolvableType.forClass(sourceType), ResolvableType.forClass(targetType)));
    }

    @Override
    public void addConverter(GenericConverter converter) {
        this.converters.add(converter);
        this.invalidateCache();
    }

    @Override
    public void addConverterFactory(ConverterFactory<?, ?> factory) {
        ResolvableType[] typeInfo = this.getRequiredTypeInfo(factory.getClass(), ConverterFactory.class);
        if (typeInfo == null && factory instanceof DecoratingProxy) {
            typeInfo = this.getRequiredTypeInfo(((DecoratingProxy)((Object)factory)).getDecoratedClass(), ConverterFactory.class);
        }
        if (typeInfo == null) {
            throw new IllegalArgumentException("Unable to determine source type <S> and target type <T> for your ConverterFactory [" + factory.getClass().getName() + "]; does the class parameterize those types?");
        }
        this.addConverter(new ConverterFactoryAdapter(factory, new GenericConverter.ConvertiblePair(typeInfo[0].toClass(), typeInfo[1].toClass())));
    }

    @Override
    public void removeConvertible(Class<?> sourceType, Class<?> targetType) {
        this.converters.remove(sourceType, targetType);
        this.invalidateCache();
    }

    @Override
    public boolean canConvert(@Nullable Class<?> sourceType, Class<?> targetType) {
        Assert.notNull(targetType, "Target type to convert to cannot be null");
        return this.canConvert(sourceType != null ? TypeDescriptor.valueOf(sourceType) : null, TypeDescriptor.valueOf(targetType));
    }

    @Override
    public boolean canConvert(@Nullable TypeDescriptor sourceType, TypeDescriptor targetType) {
        Assert.notNull((Object)targetType, "Target type to convert to cannot be null");
        if (sourceType == null) {
            return true;
        }
        GenericConverter converter = this.getConverter(sourceType, targetType);
        return converter != null;
    }

    public boolean canBypassConvert(@Nullable TypeDescriptor sourceType, TypeDescriptor targetType) {
        Assert.notNull((Object)targetType, "Target type to convert to cannot be null");
        if (sourceType == null) {
            return true;
        }
        GenericConverter converter = this.getConverter(sourceType, targetType);
        return converter == NO_OP_CONVERTER;
    }

    @Override
    @Nullable
    public <T> T convert(@Nullable Object source, Class<T> targetType) {
        Assert.notNull(targetType, "Target type to convert to cannot be null");
        return (T)this.convert(source, TypeDescriptor.forObject(source), TypeDescriptor.valueOf(targetType));
    }

    @Override
    @Nullable
    public Object convert(@Nullable Object source, @Nullable TypeDescriptor sourceType, TypeDescriptor targetType) {
        Assert.notNull((Object)targetType, "Target type to convert to cannot be null");
        if (sourceType == null) {
            Assert.isTrue(source == null, "Source must be [null] if source type == [null]");
            return this.handleResult(null, targetType, this.convertNullSource(null, targetType));
        }
        if (source != null && !sourceType.getObjectType().isInstance(source)) {
            throw new IllegalArgumentException("Source to convert from must be an instance of [" + sourceType + "]; instead it was a [" + source.getClass().getName() + "]");
        }
        GenericConverter converter = this.getConverter(sourceType, targetType);
        if (converter != null) {
            Object result = ConversionUtils.invokeConverter(converter, source, sourceType, targetType);
            return this.handleResult(sourceType, targetType, result);
        }
        return this.handleConverterNotFound(source, sourceType, targetType);
    }

    @Nullable
    public Object convert(@Nullable Object source, TypeDescriptor targetType) {
        return this.convert(source, TypeDescriptor.forObject(source), targetType);
    }

    public String toString() {
        return this.converters.toString();
    }

    @Nullable
    protected Object convertNullSource(@Nullable TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (targetType.getObjectType() == Optional.class) {
            return Optional.empty();
        }
        return null;
    }

    @Nullable
    protected GenericConverter getConverter(TypeDescriptor sourceType, TypeDescriptor targetType) {
        ConverterCacheKey key = new ConverterCacheKey(sourceType, targetType);
        GenericConverter converter = this.converterCache.get(key);
        if (converter != null) {
            return converter != NO_MATCH ? converter : null;
        }
        converter = this.converters.find(sourceType, targetType);
        if (converter == null) {
            converter = this.getDefaultConverter(sourceType, targetType);
        }
        if (converter != null) {
            this.converterCache.put(key, converter);
            return converter;
        }
        this.converterCache.put(key, NO_MATCH);
        return null;
    }

    @Nullable
    protected GenericConverter getDefaultConverter(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return sourceType.isAssignableTo(targetType) ? NO_OP_CONVERTER : null;
    }

    @Nullable
    private ResolvableType[] getRequiredTypeInfo(Class<?> converterClass, Class<?> genericIfc) {
        ResolvableType resolvableType = ResolvableType.forClass(converterClass).as(genericIfc);
        ResolvableType[] generics = resolvableType.getGenerics();
        if (generics.length < 2) {
            return null;
        }
        Class<?> sourceType = generics[0].resolve();
        Class<?> targetType = generics[1].resolve();
        if (sourceType == null || targetType == null) {
            return null;
        }
        return generics;
    }

    private void invalidateCache() {
        this.converterCache.clear();
    }

    @Nullable
    private Object handleConverterNotFound(@Nullable Object source, @Nullable TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (source == null) {
            this.assertNotPrimitiveTargetType(sourceType, targetType);
            return null;
        }
        if ((sourceType == null || sourceType.isAssignableTo(targetType)) && targetType.getObjectType().isInstance(source)) {
            return source;
        }
        throw new ConverterNotFoundException(sourceType, targetType);
    }

    @Nullable
    private Object handleResult(@Nullable TypeDescriptor sourceType, TypeDescriptor targetType, @Nullable Object result) {
        if (result == null) {
            this.assertNotPrimitiveTargetType(sourceType, targetType);
        }
        return result;
    }

    private void assertNotPrimitiveTargetType(@Nullable TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (targetType.isPrimitive()) {
            throw new ConversionFailedException(sourceType, targetType, null, new IllegalArgumentException("A null value cannot be assigned to a primitive type"));
        }
    }

    private static class NoOpConverter
    implements GenericConverter {
        private final String name;

        public NoOpConverter(String name) {
            this.name = name;
        }

        @Override
        @Nullable
        public Set<GenericConverter.ConvertiblePair> getConvertibleTypes() {
            return null;
        }

        @Override
        @Nullable
        public Object convert(@Nullable Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
            return source;
        }

        public String toString() {
            return this.name;
        }
    }

    private static class ConvertersForPair {
        private final Deque<GenericConverter> converters = new ConcurrentLinkedDeque<GenericConverter>();

        private ConvertersForPair() {
        }

        public void add(GenericConverter converter) {
            this.converters.addFirst(converter);
        }

        @Nullable
        public GenericConverter getConverter(TypeDescriptor sourceType, TypeDescriptor targetType) {
            for (GenericConverter converter : this.converters) {
                if (converter instanceof ConditionalGenericConverter && !((ConditionalGenericConverter)converter).matches(sourceType, targetType)) continue;
                return converter;
            }
            return null;
        }

        public String toString() {
            return StringUtils.collectionToCommaDelimitedString(this.converters);
        }
    }

    private static class Converters {
        private final Set<GenericConverter> globalConverters = new CopyOnWriteArraySet<GenericConverter>();
        private final Map<GenericConverter.ConvertiblePair, ConvertersForPair> converters = new ConcurrentHashMap<GenericConverter.ConvertiblePair, ConvertersForPair>(256);

        private Converters() {
        }

        public void add(GenericConverter converter) {
            Set<GenericConverter.ConvertiblePair> convertibleTypes = converter.getConvertibleTypes();
            if (convertibleTypes == null) {
                Assert.state(converter instanceof ConditionalConverter, "Only conditional converters may return null convertible types");
                this.globalConverters.add(converter);
            } else {
                for (GenericConverter.ConvertiblePair convertiblePair : convertibleTypes) {
                    this.getMatchableConverters(convertiblePair).add(converter);
                }
            }
        }

        private ConvertersForPair getMatchableConverters(GenericConverter.ConvertiblePair convertiblePair) {
            return this.converters.computeIfAbsent(convertiblePair, k -> new ConvertersForPair());
        }

        public void remove(Class<?> sourceType, Class<?> targetType) {
            this.converters.remove(new GenericConverter.ConvertiblePair(sourceType, targetType));
        }

        @Nullable
        public GenericConverter find(TypeDescriptor sourceType, TypeDescriptor targetType) {
            List<Class<?>> sourceCandidates = this.getClassHierarchy(sourceType.getType());
            List<Class<?>> targetCandidates = this.getClassHierarchy(targetType.getType());
            for (Class<?> sourceCandidate : sourceCandidates) {
                for (Class<?> targetCandidate : targetCandidates) {
                    GenericConverter.ConvertiblePair convertiblePair = new GenericConverter.ConvertiblePair(sourceCandidate, targetCandidate);
                    GenericConverter converter = this.getRegisteredConverter(sourceType, targetType, convertiblePair);
                    if (converter == null) continue;
                    return converter;
                }
            }
            return null;
        }

        @Nullable
        private GenericConverter getRegisteredConverter(TypeDescriptor sourceType, TypeDescriptor targetType, GenericConverter.ConvertiblePair convertiblePair) {
            GenericConverter converter;
            ConvertersForPair convertersForPair = this.converters.get(convertiblePair);
            if (convertersForPair != null && (converter = convertersForPair.getConverter(sourceType, targetType)) != null) {
                return converter;
            }
            for (GenericConverter globalConverter : this.globalConverters) {
                if (!((ConditionalConverter)((Object)globalConverter)).matches(sourceType, targetType)) continue;
                return globalConverter;
            }
            return null;
        }

        private List<Class<?>> getClassHierarchy(Class<?> type) {
            ArrayList hierarchy = new ArrayList(20);
            HashSet visited = new HashSet(20);
            this.addToClassHierarchy(0, ClassUtils.resolvePrimitiveIfNecessary(type), false, hierarchy, visited);
            boolean array = type.isArray();
            for (int i = 0; i < hierarchy.size(); ++i) {
                Class<?> candidate = (Class<?>)hierarchy.get(i);
                candidate = array ? candidate.getComponentType() : ClassUtils.resolvePrimitiveIfNecessary(candidate);
                Class<?> superclass = candidate.getSuperclass();
                if (superclass != null && superclass != Object.class && superclass != Enum.class) {
                    this.addToClassHierarchy(i + 1, candidate.getSuperclass(), array, hierarchy, visited);
                }
                this.addInterfacesToClassHierarchy(candidate, array, hierarchy, visited);
            }
            if (Enum.class.isAssignableFrom(type)) {
                this.addToClassHierarchy(hierarchy.size(), Enum.class, array, hierarchy, visited);
                this.addToClassHierarchy(hierarchy.size(), Enum.class, false, hierarchy, visited);
                this.addInterfacesToClassHierarchy(Enum.class, array, hierarchy, visited);
            }
            this.addToClassHierarchy(hierarchy.size(), Object.class, array, hierarchy, visited);
            this.addToClassHierarchy(hierarchy.size(), Object.class, false, hierarchy, visited);
            return hierarchy;
        }

        private void addInterfacesToClassHierarchy(Class<?> type, boolean asArray, List<Class<?>> hierarchy, Set<Class<?>> visited) {
            for (Class<?> implementedInterface : type.getInterfaces()) {
                this.addToClassHierarchy(hierarchy.size(), implementedInterface, asArray, hierarchy, visited);
            }
        }

        private void addToClassHierarchy(int index, Class<?> type, boolean asArray, List<Class<?>> hierarchy, Set<Class<?>> visited) {
            if (asArray) {
                type = Array.newInstance(type, 0).getClass();
            }
            if (visited.add(type)) {
                hierarchy.add(index, type);
            }
        }

        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("ConversionService converters =\n");
            for (String converterString : this.getConverterStrings()) {
                builder.append('\t').append(converterString).append('\n');
            }
            return builder.toString();
        }

        private List<String> getConverterStrings() {
            ArrayList<String> converterStrings = new ArrayList<String>();
            for (ConvertersForPair convertersForPair : this.converters.values()) {
                converterStrings.add(convertersForPair.toString());
            }
            Collections.sort(converterStrings);
            return converterStrings;
        }
    }

    private static final class ConverterCacheKey
    implements Comparable<ConverterCacheKey> {
        private final TypeDescriptor sourceType;
        private final TypeDescriptor targetType;

        public ConverterCacheKey(TypeDescriptor sourceType, TypeDescriptor targetType) {
            this.sourceType = sourceType;
            this.targetType = targetType;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ConverterCacheKey)) {
                return false;
            }
            ConverterCacheKey otherKey = (ConverterCacheKey)other;
            return this.sourceType.equals(otherKey.sourceType) && this.targetType.equals(otherKey.targetType);
        }

        public int hashCode() {
            return this.sourceType.hashCode() * 29 + this.targetType.hashCode();
        }

        public String toString() {
            return "ConverterCacheKey [sourceType = " + this.sourceType + ", targetType = " + this.targetType + "]";
        }

        @Override
        public int compareTo(ConverterCacheKey other) {
            int result = this.sourceType.getResolvableType().toString().compareTo(other.sourceType.getResolvableType().toString());
            if (result == 0) {
                result = this.targetType.getResolvableType().toString().compareTo(other.targetType.getResolvableType().toString());
            }
            return result;
        }
    }

    private final class ConverterFactoryAdapter
    implements ConditionalGenericConverter {
        private final ConverterFactory<Object, Object> converterFactory;
        private final GenericConverter.ConvertiblePair typeInfo;

        public ConverterFactoryAdapter(ConverterFactory<?, ?> converterFactory, GenericConverter.ConvertiblePair typeInfo) {
            this.converterFactory = converterFactory;
            this.typeInfo = typeInfo;
        }

        @Override
        public Set<GenericConverter.ConvertiblePair> getConvertibleTypes() {
            return Collections.singleton(this.typeInfo);
        }

        @Override
        public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
            Converter<Object, ?> converter;
            boolean matches = true;
            if (this.converterFactory instanceof ConditionalConverter) {
                matches = ((ConditionalConverter)((Object)this.converterFactory)).matches(sourceType, targetType);
            }
            if (matches && (converter = this.converterFactory.getConverter(targetType.getType())) instanceof ConditionalConverter) {
                matches = ((ConditionalConverter)((Object)converter)).matches(sourceType, targetType);
            }
            return matches;
        }

        @Override
        @Nullable
        public Object convert(@Nullable Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
            if (source == null) {
                return GenericConversionService.this.convertNullSource(sourceType, targetType);
            }
            return this.converterFactory.getConverter(targetType.getObjectType()).convert(source);
        }

        public String toString() {
            return this.typeInfo + " : " + this.converterFactory;
        }
    }

    private final class ConverterAdapter
    implements ConditionalGenericConverter {
        private final Converter<Object, Object> converter;
        private final GenericConverter.ConvertiblePair typeInfo;
        private final ResolvableType targetType;

        public ConverterAdapter(Converter<?, ?> converter, ResolvableType sourceType, ResolvableType targetType) {
            this.converter = converter;
            this.typeInfo = new GenericConverter.ConvertiblePair(sourceType.toClass(), targetType.toClass());
            this.targetType = targetType;
        }

        @Override
        public Set<GenericConverter.ConvertiblePair> getConvertibleTypes() {
            return Collections.singleton(this.typeInfo);
        }

        @Override
        public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
            if (this.typeInfo.getTargetType() != targetType.getObjectType()) {
                return false;
            }
            ResolvableType rt = targetType.getResolvableType();
            if (!(rt.getType() instanceof Class || rt.isAssignableFrom(this.targetType) || this.targetType.hasUnresolvableGenerics())) {
                return false;
            }
            return !(this.converter instanceof ConditionalConverter) || ((ConditionalConverter)((Object)this.converter)).matches(sourceType, targetType);
        }

        @Override
        @Nullable
        public Object convert(@Nullable Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
            if (source == null) {
                return GenericConversionService.this.convertNullSource(sourceType, targetType);
            }
            return this.converter.convert(source);
        }

        public String toString() {
            return this.typeInfo + " : " + this.converter;
        }
    }
}

