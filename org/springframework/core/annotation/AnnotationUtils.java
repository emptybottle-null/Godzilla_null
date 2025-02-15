/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.AnnotationConfigurationException;
import org.springframework.core.annotation.AnnotationFilter;
import org.springframework.core.annotation.AnnotationTypeMapping;
import org.springframework.core.annotation.AnnotationTypeMappings;
import org.springframework.core.annotation.AnnotationsScanner;
import org.springframework.core.annotation.AttributeMethods;
import org.springframework.core.annotation.IntrospectionFailureLogger;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotationCollectors;
import org.springframework.core.annotation.MergedAnnotationPredicates;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.annotation.RepeatableContainers;
import org.springframework.core.annotation.SynthesizedAnnotation;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

public abstract class AnnotationUtils {
    public static final String VALUE = "value";
    private static final AnnotationFilter JAVA_LANG_ANNOTATION_FILTER = AnnotationFilter.packages("java.lang.annotation");
    private static final Map<Class<? extends Annotation>, Map<String, DefaultValueHolder>> defaultValuesCache = new ConcurrentReferenceHashMap<Class<? extends Annotation>, Map<String, DefaultValueHolder>>();

    public static boolean isCandidateClass(Class<?> clazz, Collection<Class<? extends Annotation>> annotationTypes) {
        for (Class<? extends Annotation> annotationType : annotationTypes) {
            if (!AnnotationUtils.isCandidateClass(clazz, annotationType)) continue;
            return true;
        }
        return false;
    }

    public static boolean isCandidateClass(Class<?> clazz, Class<? extends Annotation> annotationType) {
        return AnnotationUtils.isCandidateClass(clazz, annotationType.getName());
    }

    public static boolean isCandidateClass(Class<?> clazz, String annotationName) {
        if (annotationName.startsWith("java.")) {
            return true;
        }
        return !AnnotationsScanner.hasPlainJavaAnnotationsOnly(clazz);
    }

    @Nullable
    public static <A extends Annotation> A getAnnotation(Annotation annotation, Class<A> annotationType) {
        if (annotationType.isInstance(annotation)) {
            return (A)AnnotationUtils.synthesizeAnnotation(annotation, annotationType);
        }
        if (AnnotationsScanner.hasPlainJavaAnnotationsOnly(annotation)) {
            return null;
        }
        return (A)((Annotation)MergedAnnotations.from((Object)annotation, new Annotation[]{annotation}, RepeatableContainers.none()).get(annotationType).withNonMergedAttributes().synthesize(AnnotationUtils::isSingleLevelPresent).orElse(null));
    }

    @Nullable
    public static <A extends Annotation> A getAnnotation(AnnotatedElement annotatedElement, Class<A> annotationType) {
        if (AnnotationFilter.PLAIN.matches(annotationType) || AnnotationsScanner.hasPlainJavaAnnotationsOnly(annotatedElement)) {
            return annotatedElement.getAnnotation(annotationType);
        }
        return (A)((Annotation)MergedAnnotations.from(annotatedElement, MergedAnnotations.SearchStrategy.INHERITED_ANNOTATIONS, RepeatableContainers.none()).get(annotationType).withNonMergedAttributes().synthesize(AnnotationUtils::isSingleLevelPresent).orElse(null));
    }

    private static <A extends Annotation> boolean isSingleLevelPresent(MergedAnnotation<A> mergedAnnotation) {
        int distance = mergedAnnotation.getDistance();
        return distance == 0 || distance == 1;
    }

    @Nullable
    public static <A extends Annotation> A getAnnotation(Method method, Class<A> annotationType) {
        Method resolvedMethod = BridgeMethodResolver.findBridgedMethod(method);
        return AnnotationUtils.getAnnotation((AnnotatedElement)resolvedMethod, annotationType);
    }

    @Deprecated
    @Nullable
    public static Annotation[] getAnnotations(AnnotatedElement annotatedElement) {
        try {
            return AnnotationUtils.synthesizeAnnotationArray(annotatedElement.getAnnotations(), annotatedElement);
        } catch (Throwable ex) {
            AnnotationUtils.handleIntrospectionFailure(annotatedElement, ex);
            return null;
        }
    }

    @Deprecated
    @Nullable
    public static Annotation[] getAnnotations(Method method) {
        try {
            return AnnotationUtils.synthesizeAnnotationArray(BridgeMethodResolver.findBridgedMethod(method).getAnnotations(), method);
        } catch (Throwable ex) {
            AnnotationUtils.handleIntrospectionFailure(method, ex);
            return null;
        }
    }

    @Deprecated
    public static <A extends Annotation> Set<A> getRepeatableAnnotations(AnnotatedElement annotatedElement, Class<A> annotationType) {
        return AnnotationUtils.getRepeatableAnnotations(annotatedElement, annotationType, null);
    }

    @Deprecated
    public static <A extends Annotation> Set<A> getRepeatableAnnotations(AnnotatedElement annotatedElement, Class<A> annotationType, @Nullable Class<? extends Annotation> containerAnnotationType) {
        RepeatableContainers repeatableContainers = containerAnnotationType != null ? RepeatableContainers.of(annotationType, containerAnnotationType) : RepeatableContainers.standardRepeatables();
        return MergedAnnotations.from(annotatedElement, MergedAnnotations.SearchStrategy.SUPERCLASS, repeatableContainers).stream(annotationType).filter(MergedAnnotationPredicates.firstRunOf(MergedAnnotation::getAggregateIndex)).map(MergedAnnotation::withNonMergedAttributes).collect(MergedAnnotationCollectors.toAnnotationSet());
    }

    @Deprecated
    public static <A extends Annotation> Set<A> getDeclaredRepeatableAnnotations(AnnotatedElement annotatedElement, Class<A> annotationType) {
        return AnnotationUtils.getDeclaredRepeatableAnnotations(annotatedElement, annotationType, null);
    }

    @Deprecated
    public static <A extends Annotation> Set<A> getDeclaredRepeatableAnnotations(AnnotatedElement annotatedElement, Class<A> annotationType, @Nullable Class<? extends Annotation> containerAnnotationType) {
        RepeatableContainers repeatableContainers = containerAnnotationType != null ? RepeatableContainers.of(annotationType, containerAnnotationType) : RepeatableContainers.standardRepeatables();
        return MergedAnnotations.from(annotatedElement, MergedAnnotations.SearchStrategy.DIRECT, repeatableContainers).stream(annotationType).map(MergedAnnotation::withNonMergedAttributes).collect(MergedAnnotationCollectors.toAnnotationSet());
    }

    @Nullable
    public static <A extends Annotation> A findAnnotation(AnnotatedElement annotatedElement, @Nullable Class<A> annotationType) {
        if (annotationType == null) {
            return null;
        }
        if (AnnotationFilter.PLAIN.matches(annotationType) || AnnotationsScanner.hasPlainJavaAnnotationsOnly(annotatedElement)) {
            return annotatedElement.getDeclaredAnnotation(annotationType);
        }
        return (A)((Annotation)MergedAnnotations.from(annotatedElement, MergedAnnotations.SearchStrategy.INHERITED_ANNOTATIONS, RepeatableContainers.none()).get(annotationType).withNonMergedAttributes().synthesize(MergedAnnotation::isPresent).orElse(null));
    }

    @Nullable
    public static <A extends Annotation> A findAnnotation(Method method, @Nullable Class<A> annotationType) {
        if (annotationType == null) {
            return null;
        }
        if (AnnotationFilter.PLAIN.matches(annotationType) || AnnotationsScanner.hasPlainJavaAnnotationsOnly(method)) {
            return method.getDeclaredAnnotation(annotationType);
        }
        return (A)((Annotation)MergedAnnotations.from(method, MergedAnnotations.SearchStrategy.TYPE_HIERARCHY, RepeatableContainers.none()).get(annotationType).withNonMergedAttributes().synthesize(MergedAnnotation::isPresent).orElse(null));
    }

    @Nullable
    public static <A extends Annotation> A findAnnotation(Class<?> clazz, @Nullable Class<A> annotationType) {
        if (annotationType == null) {
            return null;
        }
        if (AnnotationFilter.PLAIN.matches(annotationType) || AnnotationsScanner.hasPlainJavaAnnotationsOnly(clazz)) {
            A annotation = clazz.getDeclaredAnnotation(annotationType);
            if (annotation != null) {
                return annotation;
            }
            Class<?> superclass = clazz.getSuperclass();
            if (superclass == null || superclass == Object.class) {
                return null;
            }
            return AnnotationUtils.findAnnotation(superclass, annotationType);
        }
        return (A)((Annotation)MergedAnnotations.from(clazz, MergedAnnotations.SearchStrategy.TYPE_HIERARCHY, RepeatableContainers.none()).get(annotationType).withNonMergedAttributes().synthesize(MergedAnnotation::isPresent).orElse(null));
    }

    @Deprecated
    @Nullable
    public static Class<?> findAnnotationDeclaringClass(Class<? extends Annotation> annotationType, @Nullable Class<?> clazz) {
        if (clazz == null) {
            return null;
        }
        return (Class)MergedAnnotations.from(clazz, MergedAnnotations.SearchStrategy.SUPERCLASS).get(annotationType, MergedAnnotation::isDirectlyPresent).getSource();
    }

    @Deprecated
    @Nullable
    public static Class<?> findAnnotationDeclaringClassForTypes(List<Class<? extends Annotation>> annotationTypes, @Nullable Class<?> clazz) {
        if (clazz == null) {
            return null;
        }
        return MergedAnnotations.from(clazz, MergedAnnotations.SearchStrategy.SUPERCLASS).stream().filter(MergedAnnotationPredicates.typeIn(annotationTypes).and(MergedAnnotation::isDirectlyPresent)).map(MergedAnnotation::getSource).findFirst().orElse(null);
    }

    public static boolean isAnnotationDeclaredLocally(Class<? extends Annotation> annotationType, Class<?> clazz) {
        return MergedAnnotations.from(clazz).get(annotationType).isDirectlyPresent();
    }

    @Deprecated
    public static boolean isAnnotationInherited(Class<? extends Annotation> annotationType, Class<?> clazz) {
        return MergedAnnotations.from(clazz, MergedAnnotations.SearchStrategy.INHERITED_ANNOTATIONS).stream(annotationType).filter(MergedAnnotation::isDirectlyPresent).findFirst().orElseGet(MergedAnnotation::missing).getAggregateIndex() > 0;
    }

    @Deprecated
    public static boolean isAnnotationMetaPresent(Class<? extends Annotation> annotationType, @Nullable Class<? extends Annotation> metaAnnotationType) {
        if (metaAnnotationType == null) {
            return false;
        }
        if (AnnotationFilter.PLAIN.matches(metaAnnotationType) || AnnotationsScanner.hasPlainJavaAnnotationsOnly(annotationType)) {
            return annotationType.isAnnotationPresent(metaAnnotationType);
        }
        return MergedAnnotations.from(annotationType, MergedAnnotations.SearchStrategy.INHERITED_ANNOTATIONS, RepeatableContainers.none()).isPresent(metaAnnotationType);
    }

    public static boolean isInJavaLangAnnotationPackage(@Nullable Annotation annotation) {
        return annotation != null && JAVA_LANG_ANNOTATION_FILTER.matches(annotation);
    }

    public static boolean isInJavaLangAnnotationPackage(@Nullable String annotationType) {
        return annotationType != null && JAVA_LANG_ANNOTATION_FILTER.matches(annotationType);
    }

    public static void validateAnnotation(Annotation annotation) {
        AttributeMethods.forAnnotationType(annotation.annotationType()).validate(annotation);
    }

    public static Map<String, Object> getAnnotationAttributes(Annotation annotation) {
        return AnnotationUtils.getAnnotationAttributes(null, annotation);
    }

    public static Map<String, Object> getAnnotationAttributes(Annotation annotation, boolean classValuesAsString) {
        return AnnotationUtils.getAnnotationAttributes(annotation, classValuesAsString, false);
    }

    public static AnnotationAttributes getAnnotationAttributes(Annotation annotation, boolean classValuesAsString, boolean nestedAnnotationsAsMap) {
        return AnnotationUtils.getAnnotationAttributes(null, annotation, classValuesAsString, nestedAnnotationsAsMap);
    }

    public static AnnotationAttributes getAnnotationAttributes(@Nullable AnnotatedElement annotatedElement, Annotation annotation) {
        return AnnotationUtils.getAnnotationAttributes(annotatedElement, annotation, false, false);
    }

    public static AnnotationAttributes getAnnotationAttributes(@Nullable AnnotatedElement annotatedElement, Annotation annotation, boolean classValuesAsString, boolean nestedAnnotationsAsMap) {
        MergedAnnotation.Adapt[] adaptations = MergedAnnotation.Adapt.values(classValuesAsString, nestedAnnotationsAsMap);
        return MergedAnnotation.from(annotatedElement, annotation).withNonMergedAttributes().asMap(mergedAnnotation -> new AnnotationAttributes(mergedAnnotation.getType(), true), adaptations);
    }

    public static void registerDefaultValues(AnnotationAttributes attributes) {
        Class<? extends Annotation> annotationType = attributes.annotationType();
        if (annotationType != null && Modifier.isPublic(annotationType.getModifiers()) && !AnnotationFilter.PLAIN.matches(annotationType)) {
            Map<String, DefaultValueHolder> defaultValues = AnnotationUtils.getDefaultValues(annotationType);
            defaultValues.forEach(attributes::putIfAbsent);
        }
    }

    private static Map<String, DefaultValueHolder> getDefaultValues(Class<? extends Annotation> annotationType) {
        return defaultValuesCache.computeIfAbsent(annotationType, AnnotationUtils::computeDefaultValues);
    }

    private static Map<String, DefaultValueHolder> computeDefaultValues(Class<? extends Annotation> annotationType) {
        AttributeMethods methods = AttributeMethods.forAnnotationType(annotationType);
        if (!methods.hasDefaultValueMethod()) {
            return Collections.emptyMap();
        }
        LinkedHashMap<String, DefaultValueHolder> result = CollectionUtils.newLinkedHashMap(methods.size());
        if (!methods.hasNestedAnnotation()) {
            for (int i = 0; i < methods.size(); ++i) {
                Method method = methods.get(i);
                Object defaultValue = method.getDefaultValue();
                if (defaultValue == null) continue;
                result.put(method.getName(), new DefaultValueHolder(defaultValue));
            }
        } else {
            AnnotationAttributes attributes = MergedAnnotation.of(annotationType).asMap(annotation -> new AnnotationAttributes(annotation.getType(), true), MergedAnnotation.Adapt.ANNOTATION_TO_MAP);
            for (Map.Entry element : attributes.entrySet()) {
                result.put((String)element.getKey(), new DefaultValueHolder(element.getValue()));
            }
        }
        return result;
    }

    public static void postProcessAnnotationAttributes(@Nullable Object annotatedElement, @Nullable AnnotationAttributes attributes, boolean classValuesAsString) {
        if (attributes == null) {
            return;
        }
        if (!attributes.validated) {
            Class<? extends Annotation> annotationType = attributes.annotationType();
            if (annotationType == null) {
                return;
            }
            AnnotationTypeMapping mapping = AnnotationTypeMappings.forAnnotationType(annotationType).get(0);
            for (int i = 0; i < mapping.getMirrorSets().size(); ++i) {
                AnnotationTypeMapping.MirrorSets.MirrorSet mirrorSet = mapping.getMirrorSets().get(i);
                int resolved = mirrorSet.resolve(attributes.displayName, attributes, AnnotationUtils::getAttributeValueForMirrorResolution);
                if (resolved == -1) continue;
                Method attribute = mapping.getAttributes().get(resolved);
                Object value = attributes.get(attribute.getName());
                for (int j = 0; j < mirrorSet.size(); ++j) {
                    Method mirror = mirrorSet.get(j);
                    if (mirror == attribute) continue;
                    attributes.put(mirror.getName(), AnnotationUtils.adaptValue(annotatedElement, value, classValuesAsString));
                }
            }
        }
        for (Map.Entry attributeEntry : attributes.entrySet()) {
            String attributeName = (String)attributeEntry.getKey();
            Object value = attributeEntry.getValue();
            if (!(value instanceof DefaultValueHolder)) continue;
            value = ((DefaultValueHolder)value).defaultValue;
            attributes.put(attributeName, AnnotationUtils.adaptValue(annotatedElement, value, classValuesAsString));
        }
    }

    private static Object getAttributeValueForMirrorResolution(Method attribute, Object attributes) {
        Object result = ((AnnotationAttributes)attributes).get(attribute.getName());
        return result instanceof DefaultValueHolder ? ((DefaultValueHolder)result).defaultValue : result;
    }

    @Nullable
    private static Object adaptValue(@Nullable Object annotatedElement, @Nullable Object value, boolean classValuesAsString) {
        if (classValuesAsString) {
            if (value instanceof Class) {
                return ((Class)value).getName();
            }
            if (value instanceof Class[]) {
                Class[] classes = (Class[])value;
                String[] names = new String[classes.length];
                for (int i = 0; i < classes.length; ++i) {
                    names[i] = classes[i].getName();
                }
                return names;
            }
        }
        if (value instanceof Annotation) {
            Annotation annotation = (Annotation)value;
            return MergedAnnotation.from(annotatedElement, annotation).synthesize();
        }
        if (value instanceof Annotation[]) {
            Annotation[] annotations = (Annotation[])value;
            Annotation[] synthesized = (Annotation[])Array.newInstance(annotations.getClass().getComponentType(), annotations.length);
            for (int i = 0; i < annotations.length; ++i) {
                synthesized[i] = MergedAnnotation.from(annotatedElement, annotations[i]).synthesize();
            }
            return synthesized;
        }
        return value;
    }

    @Nullable
    public static Object getValue(Annotation annotation) {
        return AnnotationUtils.getValue(annotation, VALUE);
    }

    @Nullable
    public static Object getValue(@Nullable Annotation annotation, @Nullable String attributeName) {
        if (annotation == null || !StringUtils.hasText(attributeName)) {
            return null;
        }
        try {
            Method method = annotation.annotationType().getDeclaredMethod(attributeName, new Class[0]);
            ReflectionUtils.makeAccessible(method);
            return method.invoke(annotation, new Object[0]);
        } catch (NoSuchMethodException ex) {
            return null;
        } catch (InvocationTargetException ex) {
            AnnotationUtils.rethrowAnnotationConfigurationException(ex.getTargetException());
            throw new IllegalStateException("Could not obtain value for annotation attribute '" + attributeName + "' in " + annotation, ex);
        } catch (Throwable ex) {
            AnnotationUtils.handleIntrospectionFailure(annotation.getClass(), ex);
            return null;
        }
    }

    static void rethrowAnnotationConfigurationException(Throwable ex) {
        if (ex instanceof AnnotationConfigurationException) {
            throw (AnnotationConfigurationException)ex;
        }
    }

    static void handleIntrospectionFailure(@Nullable AnnotatedElement element, Throwable ex) {
        AnnotationUtils.rethrowAnnotationConfigurationException(ex);
        IntrospectionFailureLogger logger = IntrospectionFailureLogger.INFO;
        boolean meta = false;
        if (element instanceof Class && Annotation.class.isAssignableFrom((Class)element)) {
            logger = IntrospectionFailureLogger.DEBUG;
            meta = true;
        }
        if (logger.isEnabled()) {
            String message = meta ? "Failed to meta-introspect annotation " : "Failed to introspect annotations on ";
            logger.log(message + element + ": " + ex);
        }
    }

    @Nullable
    public static Object getDefaultValue(Annotation annotation) {
        return AnnotationUtils.getDefaultValue(annotation, VALUE);
    }

    @Nullable
    public static Object getDefaultValue(@Nullable Annotation annotation, @Nullable String attributeName) {
        return annotation != null ? AnnotationUtils.getDefaultValue(annotation.annotationType(), attributeName) : null;
    }

    @Nullable
    public static Object getDefaultValue(Class<? extends Annotation> annotationType) {
        return AnnotationUtils.getDefaultValue(annotationType, VALUE);
    }

    @Nullable
    public static Object getDefaultValue(@Nullable Class<? extends Annotation> annotationType, @Nullable String attributeName) {
        if (annotationType == null || !StringUtils.hasText(attributeName)) {
            return null;
        }
        return MergedAnnotation.of(annotationType).getDefaultValue(attributeName).orElse(null);
    }

    public static <A extends Annotation> A synthesizeAnnotation(A annotation, @Nullable AnnotatedElement annotatedElement) {
        if (annotation instanceof SynthesizedAnnotation || AnnotationFilter.PLAIN.matches(annotation)) {
            return annotation;
        }
        return MergedAnnotation.from(annotatedElement, annotation).synthesize();
    }

    public static <A extends Annotation> A synthesizeAnnotation(Class<A> annotationType) {
        return AnnotationUtils.synthesizeAnnotation(Collections.emptyMap(), annotationType, null);
    }

    public static <A extends Annotation> A synthesizeAnnotation(Map<String, Object> attributes, Class<A> annotationType, @Nullable AnnotatedElement annotatedElement) {
        try {
            return MergedAnnotation.of(annotatedElement, annotationType, attributes).synthesize();
        } catch (IllegalStateException | NoSuchElementException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    static Annotation[] synthesizeAnnotationArray(Annotation[] annotations, AnnotatedElement annotatedElement) {
        if (AnnotationsScanner.hasPlainJavaAnnotationsOnly(annotatedElement)) {
            return annotations;
        }
        Annotation[] synthesized = (Annotation[])Array.newInstance(annotations.getClass().getComponentType(), annotations.length);
        for (int i = 0; i < annotations.length; ++i) {
            synthesized[i] = AnnotationUtils.synthesizeAnnotation(annotations[i], annotatedElement);
        }
        return synthesized;
    }

    public static void clearCache() {
        AnnotationTypeMappings.clearCache();
        AnnotationsScanner.clearCache();
    }

    private static class DefaultValueHolder {
        final Object defaultValue;

        public DefaultValueHolder(Object defaultValue) {
            this.defaultValue = defaultValue;
        }

        public String toString() {
            return "*" + this.defaultValue;
        }
    }
}

