/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import org.springframework.core.annotation.AbstractMergedAnnotation;
import org.springframework.core.annotation.AnnotationTypeMapping;
import org.springframework.core.annotation.AnnotationTypeMappings;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.AttributeMethods;
import org.springframework.core.annotation.IntrospectionFailureLogger;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.SynthesizedAnnotation;
import org.springframework.core.annotation.SynthesizedMergedAnnotationInvocationHandler;
import org.springframework.core.annotation.ValueExtractor;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

final class TypeMappedAnnotation<A extends Annotation>
extends AbstractMergedAnnotation<A> {
    private static final Map<Class<?>, Object> EMPTY_ARRAYS;
    private final AnnotationTypeMapping mapping;
    @Nullable
    private final ClassLoader classLoader;
    @Nullable
    private final Object source;
    @Nullable
    private final Object rootAttributes;
    private final ValueExtractor valueExtractor;
    private final int aggregateIndex;
    private final boolean useMergedValues;
    @Nullable
    private final Predicate<String> attributeFilter;
    private final int[] resolvedRootMirrors;
    private final int[] resolvedMirrors;

    private TypeMappedAnnotation(AnnotationTypeMapping mapping, @Nullable ClassLoader classLoader, @Nullable Object source, @Nullable Object rootAttributes, ValueExtractor valueExtractor, int aggregateIndex) {
        this(mapping, classLoader, source, rootAttributes, valueExtractor, aggregateIndex, null);
    }

    private TypeMappedAnnotation(AnnotationTypeMapping mapping, @Nullable ClassLoader classLoader, @Nullable Object source, @Nullable Object rootAttributes, ValueExtractor valueExtractor, int aggregateIndex, @Nullable int[] resolvedRootMirrors) {
        this.mapping = mapping;
        this.classLoader = classLoader;
        this.source = source;
        this.rootAttributes = rootAttributes;
        this.valueExtractor = valueExtractor;
        this.aggregateIndex = aggregateIndex;
        this.useMergedValues = true;
        this.attributeFilter = null;
        this.resolvedRootMirrors = resolvedRootMirrors != null ? resolvedRootMirrors : mapping.getRoot().getMirrorSets().resolve(source, rootAttributes, this.valueExtractor);
        this.resolvedMirrors = this.getDistance() == 0 ? this.resolvedRootMirrors : mapping.getMirrorSets().resolve(source, this, this::getValueForMirrorResolution);
    }

    private TypeMappedAnnotation(AnnotationTypeMapping mapping, @Nullable ClassLoader classLoader, @Nullable Object source, @Nullable Object rootAnnotation, ValueExtractor valueExtractor, int aggregateIndex, boolean useMergedValues, @Nullable Predicate<String> attributeFilter, int[] resolvedRootMirrors, int[] resolvedMirrors) {
        this.classLoader = classLoader;
        this.source = source;
        this.rootAttributes = rootAnnotation;
        this.valueExtractor = valueExtractor;
        this.mapping = mapping;
        this.aggregateIndex = aggregateIndex;
        this.useMergedValues = useMergedValues;
        this.attributeFilter = attributeFilter;
        this.resolvedRootMirrors = resolvedRootMirrors;
        this.resolvedMirrors = resolvedMirrors;
    }

    @Override
    public Class<A> getType() {
        return this.mapping.getAnnotationType();
    }

    @Override
    public List<Class<? extends Annotation>> getMetaTypes() {
        return this.mapping.getMetaTypes();
    }

    @Override
    public boolean isPresent() {
        return true;
    }

    @Override
    public int getDistance() {
        return this.mapping.getDistance();
    }

    @Override
    public int getAggregateIndex() {
        return this.aggregateIndex;
    }

    @Override
    @Nullable
    public Object getSource() {
        return this.source;
    }

    @Override
    @Nullable
    public MergedAnnotation<?> getMetaSource() {
        AnnotationTypeMapping metaSourceMapping = this.mapping.getSource();
        if (metaSourceMapping == null) {
            return null;
        }
        return new TypeMappedAnnotation<A>(metaSourceMapping, this.classLoader, this.source, this.rootAttributes, this.valueExtractor, this.aggregateIndex, this.resolvedRootMirrors);
    }

    @Override
    public MergedAnnotation<?> getRoot() {
        if (this.getDistance() == 0) {
            return this;
        }
        AnnotationTypeMapping rootMapping = this.mapping.getRoot();
        return new TypeMappedAnnotation<A>(rootMapping, this.classLoader, this.source, this.rootAttributes, this.valueExtractor, this.aggregateIndex, this.resolvedRootMirrors);
    }

    @Override
    public boolean hasDefaultValue(String attributeName) {
        int attributeIndex = this.getAttributeIndex(attributeName, true);
        Object value = this.getValue(attributeIndex, true, false);
        return value == null || this.mapping.isEquivalentToDefaultValue(attributeIndex, value, this.valueExtractor);
    }

    @Override
    public <T extends Annotation> MergedAnnotation<T> getAnnotation(String attributeName, Class<T> type) throws NoSuchElementException {
        int attributeIndex = this.getAttributeIndex(attributeName, true);
        Method attribute = this.mapping.getAttributes().get(attributeIndex);
        Assert.notNull(type, "Type must not be null");
        Assert.isAssignable(type, attribute.getReturnType(), () -> "Attribute " + attributeName + " type mismatch:");
        return (MergedAnnotation)this.getRequiredValue(attributeIndex, attributeName);
    }

    @Override
    public <T extends Annotation> MergedAnnotation<T>[] getAnnotationArray(String attributeName, Class<T> type) throws NoSuchElementException {
        int attributeIndex = this.getAttributeIndex(attributeName, true);
        Method attribute = this.mapping.getAttributes().get(attributeIndex);
        Class<?> componentType = attribute.getReturnType().getComponentType();
        Assert.notNull(type, "Type must not be null");
        Assert.notNull(componentType, () -> "Attribute " + attributeName + " is not an array");
        Assert.isAssignable(type, componentType, () -> "Attribute " + attributeName + " component type mismatch:");
        return (MergedAnnotation[])this.getRequiredValue(attributeIndex, attributeName);
    }

    @Override
    public <T> Optional<T> getDefaultValue(String attributeName, Class<T> type) {
        int attributeIndex = this.getAttributeIndex(attributeName, false);
        if (attributeIndex == -1) {
            return Optional.empty();
        }
        Method attribute = this.mapping.getAttributes().get(attributeIndex);
        return Optional.ofNullable(this.adapt(attribute, attribute.getDefaultValue(), type));
    }

    @Override
    public MergedAnnotation<A> filterAttributes(Predicate<String> predicate) {
        if (this.attributeFilter != null) {
            predicate = this.attributeFilter.and(predicate);
        }
        return new TypeMappedAnnotation<A>(this.mapping, this.classLoader, this.source, this.rootAttributes, this.valueExtractor, this.aggregateIndex, this.useMergedValues, predicate, this.resolvedRootMirrors, this.resolvedMirrors);
    }

    @Override
    public MergedAnnotation<A> withNonMergedAttributes() {
        return new TypeMappedAnnotation<A>(this.mapping, this.classLoader, this.source, this.rootAttributes, this.valueExtractor, this.aggregateIndex, false, this.attributeFilter, this.resolvedRootMirrors, this.resolvedMirrors);
    }

    @Override
    public Map<String, Object> asMap(MergedAnnotation.Adapt ... adaptations) {
        return Collections.unmodifiableMap(this.asMap((MergedAnnotation<?> mergedAnnotation) -> new LinkedHashMap(), adaptations));
    }

    @Override
    public <T extends Map<String, Object>> T asMap(Function<MergedAnnotation<?>, T> factory, MergedAnnotation.Adapt ... adaptations) {
        Map map = (Map)factory.apply(this);
        Assert.state(map != null, "Factory used to create MergedAnnotation Map must not return null");
        AttributeMethods attributes = this.mapping.getAttributes();
        for (int i = 0; i < attributes.size(); ++i) {
            Object value;
            Method attribute = attributes.get(i);
            Object v0 = value = this.isFiltered(attribute.getName()) ? null : this.getValue(i, this.getTypeForMapOptions(attribute, adaptations));
            if (value == null) continue;
            map.put(attribute.getName(), this.adaptValueForMapOptions(attribute, value, map.getClass(), factory, adaptations));
        }
        return (T)map;
    }

    private Class<?> getTypeForMapOptions(Method attribute, MergedAnnotation.Adapt[] adaptations) {
        Class<?> componentType;
        Class<?> attributeType = attribute.getReturnType();
        Class<?> clazz = componentType = attributeType.isArray() ? attributeType.getComponentType() : attributeType;
        if (MergedAnnotation.Adapt.CLASS_TO_STRING.isIn(adaptations) && componentType == Class.class) {
            return attributeType.isArray() ? String[].class : String.class;
        }
        return Object.class;
    }

    private <T extends Map<String, Object>> Object adaptValueForMapOptions(Method attribute, Object value, Class<?> mapType, Function<MergedAnnotation<?>, T> factory, MergedAnnotation.Adapt[] adaptations) {
        if (value instanceof MergedAnnotation) {
            MergedAnnotation annotation = (MergedAnnotation)value;
            return MergedAnnotation.Adapt.ANNOTATION_TO_MAP.isIn(adaptations) ? annotation.asMap(factory, adaptations) : annotation.synthesize();
        }
        if (value instanceof MergedAnnotation[]) {
            MergedAnnotation[] annotations = (MergedAnnotation[])value;
            if (MergedAnnotation.Adapt.ANNOTATION_TO_MAP.isIn(adaptations)) {
                Object result = Array.newInstance(mapType, annotations.length);
                for (int i = 0; i < annotations.length; ++i) {
                    Array.set(result, i, annotations[i].asMap(factory, adaptations));
                }
                return result;
            }
            Object result = Array.newInstance(attribute.getReturnType().getComponentType(), annotations.length);
            for (int i = 0; i < annotations.length; ++i) {
                Array.set(result, i, annotations[i].synthesize());
            }
            return result;
        }
        return value;
    }

    @Override
    protected A createSynthesized() {
        if (this.getType().isInstance(this.rootAttributes) && !this.isSynthesizable()) {
            return (A)((Annotation)this.rootAttributes);
        }
        return SynthesizedMergedAnnotationInvocationHandler.createProxy(this, this.getType());
    }

    private boolean isSynthesizable() {
        if (this.rootAttributes instanceof SynthesizedAnnotation) {
            return false;
        }
        return this.mapping.isSynthesizable();
    }

    @Override
    @Nullable
    protected <T> T getAttributeValue(String attributeName, Class<T> type) {
        int attributeIndex = this.getAttributeIndex(attributeName, false);
        return attributeIndex != -1 ? (T)this.getValue(attributeIndex, type) : null;
    }

    private Object getRequiredValue(int attributeIndex, String attributeName) {
        Object value = this.getValue(attributeIndex, Object.class);
        if (value == null) {
            throw new NoSuchElementException("No element at attribute index " + attributeIndex + " for name " + attributeName);
        }
        return value;
    }

    @Nullable
    private <T> T getValue(int attributeIndex, Class<T> type) {
        Method attribute = this.mapping.getAttributes().get(attributeIndex);
        Object value = this.getValue(attributeIndex, true, false);
        if (value == null) {
            value = attribute.getDefaultValue();
        }
        return this.adapt(attribute, value, type);
    }

    @Nullable
    private Object getValue(int attributeIndex, boolean useConventionMapping, boolean forMirrorResolution) {
        AnnotationTypeMapping mapping = this.mapping;
        if (this.useMergedValues) {
            int mappedIndex = this.mapping.getAliasMapping(attributeIndex);
            if (mappedIndex == -1 && useConventionMapping) {
                mappedIndex = this.mapping.getConventionMapping(attributeIndex);
            }
            if (mappedIndex != -1) {
                mapping = mapping.getRoot();
                attributeIndex = mappedIndex;
            }
        }
        if (!forMirrorResolution) {
            attributeIndex = (mapping.getDistance() != 0 ? this.resolvedMirrors : this.resolvedRootMirrors)[attributeIndex];
        }
        if (attributeIndex == -1) {
            return null;
        }
        if (mapping.getDistance() == 0) {
            Method attribute = mapping.getAttributes().get(attributeIndex);
            Object result = this.valueExtractor.extract(attribute, this.rootAttributes);
            return result != null ? result : attribute.getDefaultValue();
        }
        return this.getValueFromMetaAnnotation(attributeIndex, forMirrorResolution);
    }

    @Nullable
    private Object getValueFromMetaAnnotation(int attributeIndex, boolean forMirrorResolution) {
        Object value = null;
        if (this.useMergedValues || forMirrorResolution) {
            value = this.mapping.getMappedAnnotationValue(attributeIndex, forMirrorResolution);
        }
        if (value == null) {
            Method attribute = this.mapping.getAttributes().get(attributeIndex);
            value = ReflectionUtils.invokeMethod(attribute, this.mapping.getAnnotation());
        }
        return value;
    }

    @Nullable
    private Object getValueForMirrorResolution(Method attribute, Object annotation) {
        int attributeIndex = this.mapping.getAttributes().indexOf(attribute);
        boolean valueAttribute = "value".equals(attribute.getName());
        return this.getValue(attributeIndex, !valueAttribute, true);
    }

    @Nullable
    private <T> T adapt(Method attribute, @Nullable Object value, Class<T> type) {
        if (value == null) {
            return null;
        }
        value = this.adaptForAttribute(attribute, value);
        type = this.getAdaptType(attribute, type);
        if (value instanceof Class && type == String.class) {
            value = ((Class)value).getName();
        } else if (value instanceof String && type == Class.class) {
            value = ClassUtils.resolveClassName((String)value, this.getClassLoader());
        } else if (value instanceof Class[] && type == String[].class) {
            Class[] classes = (Class[])value;
            String[] names = new String[classes.length];
            for (int i = 0; i < classes.length; ++i) {
                names[i] = classes[i].getName();
            }
            value = names;
        } else if (value instanceof String[] && type == Class[].class) {
            String[] names = (String[])value;
            Class[] classes = new Class[names.length];
            for (int i = 0; i < names.length; ++i) {
                classes[i] = ClassUtils.resolveClassName(names[i], this.getClassLoader());
            }
            value = classes;
        } else if (value instanceof MergedAnnotation && type.isAnnotation()) {
            MergedAnnotation annotation = (MergedAnnotation)value;
            value = annotation.synthesize();
        } else if (value instanceof MergedAnnotation[] && type.isArray() && type.getComponentType().isAnnotation()) {
            MergedAnnotation[] annotations = (MergedAnnotation[])value;
            Object array = Array.newInstance(type.getComponentType(), annotations.length);
            for (int i = 0; i < annotations.length; ++i) {
                Array.set(array, i, annotations[i].synthesize());
            }
            value = array;
        }
        if (!type.isInstance(value)) {
            throw new IllegalArgumentException("Unable to adapt value of type " + value.getClass().getName() + " to " + type.getName());
        }
        return (T)value;
    }

    private Object adaptForAttribute(Method attribute, Object value) {
        Class<?> attributeType = ClassUtils.resolvePrimitiveIfNecessary(attribute.getReturnType());
        if (attributeType.isArray() && !value.getClass().isArray()) {
            Object array = Array.newInstance(value.getClass(), 1);
            Array.set(array, 0, value);
            return this.adaptForAttribute(attribute, array);
        }
        if (attributeType.isAnnotation()) {
            return this.adaptToMergedAnnotation(value, attributeType);
        }
        if (attributeType.isArray() && attributeType.getComponentType().isAnnotation()) {
            MergedAnnotation[] result = new MergedAnnotation[Array.getLength(value)];
            for (int i = 0; i < result.length; ++i) {
                result[i] = this.adaptToMergedAnnotation(Array.get(value, i), attributeType.getComponentType());
            }
            return result;
        }
        if (attributeType == Class.class && value instanceof String || attributeType == Class[].class && value instanceof String[] || attributeType == String.class && value instanceof Class || attributeType == String[].class && value instanceof Class[]) {
            return value;
        }
        if (attributeType.isArray() && this.isEmptyObjectArray(value)) {
            return this.emptyArray(attributeType.getComponentType());
        }
        if (!attributeType.isInstance(value)) {
            throw new IllegalStateException("Attribute '" + attribute.getName() + "' in annotation " + this.getType().getName() + " should be compatible with " + attributeType.getName() + " but a " + value.getClass().getName() + " value was returned");
        }
        return value;
    }

    private boolean isEmptyObjectArray(Object value) {
        return value instanceof Object[] && ((Object[])value).length == 0;
    }

    private Object emptyArray(Class<?> componentType) {
        Object result = EMPTY_ARRAYS.get(componentType);
        if (result == null) {
            result = Array.newInstance(componentType, 0);
        }
        return result;
    }

    private MergedAnnotation<?> adaptToMergedAnnotation(Object value, Class<? extends Annotation> annotationType) {
        if (value instanceof MergedAnnotation) {
            return (MergedAnnotation)value;
        }
        AnnotationTypeMapping mapping = AnnotationTypeMappings.forAnnotationType(annotationType).get(0);
        return new TypeMappedAnnotation<A>(mapping, null, this.source, value, this.getValueExtractor(value), this.aggregateIndex);
    }

    private ValueExtractor getValueExtractor(Object value) {
        if (value instanceof Annotation) {
            return ReflectionUtils::invokeMethod;
        }
        if (value instanceof Map) {
            return TypeMappedAnnotation::extractFromMap;
        }
        return this.valueExtractor;
    }

    private <T> Class<T> getAdaptType(Method attribute, Class<T> type) {
        if (type != Object.class) {
            return type;
        }
        Class<?> attributeType = attribute.getReturnType();
        if (attributeType.isAnnotation()) {
            return MergedAnnotation.class;
        }
        if (attributeType.isArray() && attributeType.getComponentType().isAnnotation()) {
            return MergedAnnotation[].class;
        }
        return ClassUtils.resolvePrimitiveIfNecessary(attributeType);
    }

    private int getAttributeIndex(String attributeName, boolean required) {
        int attributeIndex;
        Assert.hasText(attributeName, "Attribute name must not be null");
        int n = attributeIndex = this.isFiltered(attributeName) ? -1 : this.mapping.getAttributes().indexOf(attributeName);
        if (attributeIndex == -1 && required) {
            throw new NoSuchElementException("No attribute named '" + attributeName + "' present in merged annotation " + this.getType().getName());
        }
        return attributeIndex;
    }

    private boolean isFiltered(String attributeName) {
        if (this.attributeFilter != null) {
            return !this.attributeFilter.test(attributeName);
        }
        return false;
    }

    @Nullable
    private ClassLoader getClassLoader() {
        if (this.classLoader != null) {
            return this.classLoader;
        }
        if (this.source != null) {
            if (this.source instanceof Class) {
                return ((Class)this.source).getClassLoader();
            }
            if (this.source instanceof Member) {
                ((Member)this.source).getDeclaringClass().getClassLoader();
            }
        }
        return null;
    }

    static <A extends Annotation> MergedAnnotation<A> from(@Nullable Object source, A annotation) {
        Assert.notNull(annotation, "Annotation must not be null");
        AnnotationTypeMappings mappings = AnnotationTypeMappings.forAnnotationType(annotation.annotationType());
        return new TypeMappedAnnotation<A>(mappings.get(0), null, source, annotation, ReflectionUtils::invokeMethod, 0);
    }

    static <A extends Annotation> MergedAnnotation<A> of(@Nullable ClassLoader classLoader, @Nullable Object source, Class<A> annotationType, @Nullable Map<String, ?> attributes) {
        Assert.notNull(annotationType, "Annotation type must not be null");
        AnnotationTypeMappings mappings = AnnotationTypeMappings.forAnnotationType(annotationType);
        return new TypeMappedAnnotation<A>(mappings.get(0), classLoader, source, attributes, TypeMappedAnnotation::extractFromMap, 0);
    }

    @Nullable
    static <A extends Annotation> TypeMappedAnnotation<A> createIfPossible(AnnotationTypeMapping mapping, MergedAnnotation<?> annotation, IntrospectionFailureLogger logger) {
        if (annotation instanceof TypeMappedAnnotation) {
            TypeMappedAnnotation typeMappedAnnotation = (TypeMappedAnnotation)annotation;
            return TypeMappedAnnotation.createIfPossible(mapping, typeMappedAnnotation.source, typeMappedAnnotation.rootAttributes, typeMappedAnnotation.valueExtractor, typeMappedAnnotation.aggregateIndex, logger);
        }
        return TypeMappedAnnotation.createIfPossible(mapping, annotation.getSource(), annotation.synthesize(), annotation.getAggregateIndex(), logger);
    }

    @Nullable
    static <A extends Annotation> TypeMappedAnnotation<A> createIfPossible(AnnotationTypeMapping mapping, @Nullable Object source, Annotation annotation, int aggregateIndex, IntrospectionFailureLogger logger) {
        return TypeMappedAnnotation.createIfPossible(mapping, source, annotation, ReflectionUtils::invokeMethod, aggregateIndex, logger);
    }

    @Nullable
    private static <A extends Annotation> TypeMappedAnnotation<A> createIfPossible(AnnotationTypeMapping mapping, @Nullable Object source, @Nullable Object rootAttribute, ValueExtractor valueExtractor, int aggregateIndex, IntrospectionFailureLogger logger) {
        try {
            return new TypeMappedAnnotation<A>(mapping, null, source, rootAttribute, valueExtractor, aggregateIndex);
        } catch (Exception ex) {
            AnnotationUtils.rethrowAnnotationConfigurationException(ex);
            if (logger.isEnabled()) {
                String type = mapping.getAnnotationType().getName();
                String item = mapping.getDistance() == 0 ? "annotation " + type : "meta-annotation " + type + " from " + mapping.getRoot().getAnnotationType().getName();
                logger.log("Failed to introspect " + item, source, ex);
            }
            return null;
        }
    }

    @Nullable
    static Object extractFromMap(Method attribute, @Nullable Object map) {
        return map != null ? ((Map)map).get(attribute.getName()) : null;
    }

    static {
        HashMap<Class<Object>, Object[]> emptyArrays = new HashMap<Class<Object>, Object[]>();
        emptyArrays.put(Boolean.TYPE, new boolean[0]);
        emptyArrays.put(Byte.TYPE, new byte[0]);
        emptyArrays.put(Character.TYPE, new char[0]);
        emptyArrays.put(Double.TYPE, new double[0]);
        emptyArrays.put(Float.TYPE, new float[0]);
        emptyArrays.put(Integer.TYPE, new int[0]);
        emptyArrays.put(Long.TYPE, new long[0]);
        emptyArrays.put(Short.TYPE, new short[0]);
        emptyArrays.put(String.class, new String[0]);
        EMPTY_ARRAYS = Collections.unmodifiableMap(emptyArrays);
    }
}

