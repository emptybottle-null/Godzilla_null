/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Map;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.Ordered;
import org.springframework.core.ResolvableType;
import org.springframework.core.annotation.AnnotationFilter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.AnnotationsProcessor;
import org.springframework.core.annotation.AttributeMethods;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.lang.Nullable;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;

abstract class AnnotationsScanner {
    private static final Annotation[] NO_ANNOTATIONS = new Annotation[0];
    private static final Method[] NO_METHODS = new Method[0];
    private static final Map<AnnotatedElement, Annotation[]> declaredAnnotationCache = new ConcurrentReferenceHashMap<AnnotatedElement, Annotation[]>(256);
    private static final Map<Class<?>, Method[]> baseTypeMethodsCache = new ConcurrentReferenceHashMap(256);

    private AnnotationsScanner() {
    }

    @Nullable
    static <C, R> R scan(C context, AnnotatedElement source, MergedAnnotations.SearchStrategy searchStrategy, AnnotationsProcessor<C, R> processor) {
        R result = AnnotationsScanner.process(context, source, searchStrategy, processor);
        return processor.finish(result);
    }

    @Nullable
    private static <C, R> R process(C context, AnnotatedElement source, MergedAnnotations.SearchStrategy searchStrategy, AnnotationsProcessor<C, R> processor) {
        if (source instanceof Class) {
            return AnnotationsScanner.processClass(context, (Class)source, searchStrategy, processor);
        }
        if (source instanceof Method) {
            return AnnotationsScanner.processMethod(context, (Method)source, searchStrategy, processor);
        }
        return AnnotationsScanner.processElement(context, source, processor);
    }

    @Nullable
    private static <C, R> R processClass(C context, Class<?> source, MergedAnnotations.SearchStrategy searchStrategy, AnnotationsProcessor<C, R> processor) {
        switch (searchStrategy) {
            case DIRECT: {
                return AnnotationsScanner.processElement(context, source, processor);
            }
            case INHERITED_ANNOTATIONS: {
                return AnnotationsScanner.processClassInheritedAnnotations(context, source, searchStrategy, processor);
            }
            case SUPERCLASS: {
                return AnnotationsScanner.processClassHierarchy(context, source, processor, false, false);
            }
            case TYPE_HIERARCHY: {
                return AnnotationsScanner.processClassHierarchy(context, source, processor, true, false);
            }
            case TYPE_HIERARCHY_AND_ENCLOSING_CLASSES: {
                return AnnotationsScanner.processClassHierarchy(context, source, processor, true, true);
            }
        }
        throw new IllegalStateException("Unsupported search strategy " + (Object)((Object)searchStrategy));
    }

    @Nullable
    private static <C, R> R processClassInheritedAnnotations(C context, Class<?> source, MergedAnnotations.SearchStrategy searchStrategy, AnnotationsProcessor<C, R> processor) {
        try {
            if (AnnotationsScanner.isWithoutHierarchy(source, searchStrategy)) {
                return AnnotationsScanner.processElement(context, source, processor);
            }
            Annotation[] relevant = null;
            int remaining = Integer.MAX_VALUE;
            int aggregateIndex = 0;
            Class<?> root = source;
            while (source != null && source != Object.class && remaining > 0 && !AnnotationsScanner.hasPlainJavaAnnotationsOnly(source)) {
                R result = processor.doWithAggregate(context, aggregateIndex);
                if (result != null) {
                    return result;
                }
                Annotation[] declaredAnnotations = AnnotationsScanner.getDeclaredAnnotations(source, true);
                if (relevant == null && declaredAnnotations.length > 0) {
                    relevant = root.getAnnotations();
                    remaining = relevant.length;
                }
                for (int i = 0; i < declaredAnnotations.length; ++i) {
                    if (declaredAnnotations[i] == null) continue;
                    boolean isRelevant = false;
                    for (int relevantIndex = 0; relevantIndex < relevant.length; ++relevantIndex) {
                        if (relevant[relevantIndex] == null || declaredAnnotations[i].annotationType() != relevant[relevantIndex].annotationType()) continue;
                        isRelevant = true;
                        relevant[relevantIndex] = null;
                        --remaining;
                        break;
                    }
                    if (isRelevant) continue;
                    declaredAnnotations[i] = null;
                }
                result = processor.doWithAnnotations(context, aggregateIndex, source, declaredAnnotations);
                if (result != null) {
                    return result;
                }
                source = source.getSuperclass();
                ++aggregateIndex;
            }
        } catch (Throwable ex) {
            AnnotationUtils.handleIntrospectionFailure(source, ex);
        }
        return null;
    }

    @Nullable
    private static <C, R> R processClassHierarchy(C context, Class<?> source, AnnotationsProcessor<C, R> processor, boolean includeInterfaces, boolean includeEnclosing) {
        return AnnotationsScanner.processClassHierarchy(context, new int[]{0}, source, processor, includeInterfaces, includeEnclosing);
    }

    @Nullable
    private static <C, R> R processClassHierarchy(C context, int[] aggregateIndex, Class<?> source, AnnotationsProcessor<C, R> processor, boolean includeInterfaces, boolean includeEnclosing) {
        block11: {
            try {
                R superclassResult;
                Class<?> superclass;
                R result = processor.doWithAggregate(context, aggregateIndex[0]);
                if (result != null) {
                    return result;
                }
                if (AnnotationsScanner.hasPlainJavaAnnotationsOnly(source)) {
                    return null;
                }
                Annotation[] annotations = AnnotationsScanner.getDeclaredAnnotations(source, false);
                result = processor.doWithAnnotations(context, aggregateIndex[0], source, annotations);
                if (result != null) {
                    return result;
                }
                aggregateIndex[0] = aggregateIndex[0] + 1;
                if (includeInterfaces) {
                    for (Class<?> interfaceType : source.getInterfaces()) {
                        R interfacesResult = AnnotationsScanner.processClassHierarchy(context, aggregateIndex, interfaceType, processor, true, includeEnclosing);
                        if (interfacesResult == null) continue;
                        return interfacesResult;
                    }
                }
                if ((superclass = source.getSuperclass()) != Object.class && superclass != null && (superclassResult = AnnotationsScanner.processClassHierarchy(context, aggregateIndex, superclass, processor, includeInterfaces, includeEnclosing)) != null) {
                    return superclassResult;
                }
                if (!includeEnclosing) break block11;
                try {
                    R enclosingResult;
                    Class<?> enclosingClass = source.getEnclosingClass();
                    if (enclosingClass != null && (enclosingResult = AnnotationsScanner.processClassHierarchy(context, aggregateIndex, enclosingClass, processor, includeInterfaces, true)) != null) {
                        return enclosingResult;
                    }
                } catch (Throwable ex) {
                    AnnotationUtils.handleIntrospectionFailure(source, ex);
                }
            } catch (Throwable ex) {
                AnnotationUtils.handleIntrospectionFailure(source, ex);
            }
        }
        return null;
    }

    @Nullable
    private static <C, R> R processMethod(C context, Method source, MergedAnnotations.SearchStrategy searchStrategy, AnnotationsProcessor<C, R> processor) {
        switch (searchStrategy) {
            case DIRECT: 
            case INHERITED_ANNOTATIONS: {
                return AnnotationsScanner.processMethodInheritedAnnotations(context, source, processor);
            }
            case SUPERCLASS: {
                return AnnotationsScanner.processMethodHierarchy(context, new int[]{0}, source.getDeclaringClass(), processor, source, false);
            }
            case TYPE_HIERARCHY: 
            case TYPE_HIERARCHY_AND_ENCLOSING_CLASSES: {
                return AnnotationsScanner.processMethodHierarchy(context, new int[]{0}, source.getDeclaringClass(), processor, source, true);
            }
        }
        throw new IllegalStateException("Unsupported search strategy " + (Object)((Object)searchStrategy));
    }

    @Nullable
    private static <C, R> R processMethodInheritedAnnotations(C context, Method source, AnnotationsProcessor<C, R> processor) {
        try {
            R result = processor.doWithAggregate(context, 0);
            return result != null ? result : AnnotationsScanner.processMethodAnnotations(context, 0, source, processor);
        } catch (Throwable ex) {
            AnnotationUtils.handleIntrospectionFailure(source, ex);
            return null;
        }
    }

    @Nullable
    private static <C, R> R processMethodHierarchy(C context, int[] aggregateIndex, Class<?> sourceClass, AnnotationsProcessor<C, R> processor, Method rootMethod, boolean includeInterfaces) {
        try {
            R superclassResult;
            Class<?> superclass;
            R result = processor.doWithAggregate(context, aggregateIndex[0]);
            if (result != null) {
                return result;
            }
            if (AnnotationsScanner.hasPlainJavaAnnotationsOnly(sourceClass)) {
                return null;
            }
            boolean calledProcessor = false;
            if (sourceClass == rootMethod.getDeclaringClass()) {
                result = AnnotationsScanner.processMethodAnnotations(context, aggregateIndex[0], rootMethod, processor);
                calledProcessor = true;
                if (result != null) {
                    return result;
                }
            } else {
                for (Method method : AnnotationsScanner.getBaseTypeMethods(context, sourceClass)) {
                    if (method == null || !AnnotationsScanner.isOverride(rootMethod, method)) continue;
                    result = AnnotationsScanner.processMethodAnnotations(context, aggregateIndex[0], method, processor);
                    calledProcessor = true;
                    if (result == null) continue;
                    return result;
                }
            }
            if (Modifier.isPrivate(rootMethod.getModifiers())) {
                return null;
            }
            if (calledProcessor) {
                aggregateIndex[0] = aggregateIndex[0] + 1;
            }
            if (includeInterfaces) {
                for (GenericDeclaration genericDeclaration : sourceClass.getInterfaces()) {
                    R interfacesResult = AnnotationsScanner.processMethodHierarchy(context, aggregateIndex, genericDeclaration, processor, rootMethod, true);
                    if (interfacesResult == null) continue;
                    return interfacesResult;
                }
            }
            if ((superclass = sourceClass.getSuperclass()) != Object.class && superclass != null && (superclassResult = AnnotationsScanner.processMethodHierarchy(context, aggregateIndex, superclass, processor, rootMethod, includeInterfaces)) != null) {
                return superclassResult;
            }
        } catch (Throwable ex) {
            AnnotationUtils.handleIntrospectionFailure(rootMethod, ex);
        }
        return null;
    }

    private static <C> Method[] getBaseTypeMethods(C context, Class<?> baseType) {
        if (baseType == Object.class || AnnotationsScanner.hasPlainJavaAnnotationsOnly(baseType)) {
            return NO_METHODS;
        }
        Method[] methods = baseTypeMethodsCache.get(baseType);
        if (methods == null) {
            boolean isInterface = baseType.isInterface();
            methods = isInterface ? baseType.getMethods() : ReflectionUtils.getDeclaredMethods(baseType);
            int cleared = 0;
            for (int i = 0; i < methods.length; ++i) {
                if ((isInterface || !Modifier.isPrivate(methods[i].getModifiers())) && !AnnotationsScanner.hasPlainJavaAnnotationsOnly(methods[i]) && AnnotationsScanner.getDeclaredAnnotations(methods[i], false).length != 0) continue;
                methods[i] = null;
                ++cleared;
            }
            if (cleared == methods.length) {
                methods = NO_METHODS;
            }
            baseTypeMethodsCache.put(baseType, methods);
        }
        return methods;
    }

    private static boolean isOverride(Method rootMethod, Method candidateMethod) {
        return !Modifier.isPrivate(candidateMethod.getModifiers()) && candidateMethod.getName().equals(rootMethod.getName()) && AnnotationsScanner.hasSameParameterTypes(rootMethod, candidateMethod);
    }

    private static boolean hasSameParameterTypes(Method rootMethod, Method candidateMethod) {
        if (candidateMethod.getParameterCount() != rootMethod.getParameterCount()) {
            return false;
        }
        Object[] rootParameterTypes = rootMethod.getParameterTypes();
        Object[] candidateParameterTypes = candidateMethod.getParameterTypes();
        if (Arrays.equals(candidateParameterTypes, rootParameterTypes)) {
            return true;
        }
        return AnnotationsScanner.hasSameGenericTypeParameters(rootMethod, candidateMethod, rootParameterTypes);
    }

    private static boolean hasSameGenericTypeParameters(Method rootMethod, Method candidateMethod, Class<?>[] rootParameterTypes) {
        Class<?> sourceDeclaringClass = rootMethod.getDeclaringClass();
        Class<?> candidateDeclaringClass = candidateMethod.getDeclaringClass();
        if (!candidateDeclaringClass.isAssignableFrom(sourceDeclaringClass)) {
            return false;
        }
        for (int i = 0; i < rootParameterTypes.length; ++i) {
            Class<?> resolvedParameterType = ResolvableType.forMethodParameter(candidateMethod, i, sourceDeclaringClass).resolve();
            if (rootParameterTypes[i] == resolvedParameterType) continue;
            return false;
        }
        return true;
    }

    @Nullable
    private static <C, R> R processMethodAnnotations(C context, int aggregateIndex, Method source, AnnotationsProcessor<C, R> processor) {
        Object[] annotations = AnnotationsScanner.getDeclaredAnnotations(source, false);
        R result = processor.doWithAnnotations(context, aggregateIndex, source, (Annotation[])annotations);
        if (result != null) {
            return result;
        }
        Method bridgedMethod = BridgeMethodResolver.findBridgedMethod(source);
        if (bridgedMethod != source) {
            Annotation[] bridgedAnnotations = AnnotationsScanner.getDeclaredAnnotations(bridgedMethod, true);
            for (int i = 0; i < bridgedAnnotations.length; ++i) {
                if (!ObjectUtils.containsElement(annotations, bridgedAnnotations[i])) continue;
                bridgedAnnotations[i] = null;
            }
            return processor.doWithAnnotations(context, aggregateIndex, source, bridgedAnnotations);
        }
        return null;
    }

    @Nullable
    private static <C, R> R processElement(C context, AnnotatedElement source, AnnotationsProcessor<C, R> processor) {
        try {
            R result = processor.doWithAggregate(context, 0);
            return result != null ? result : processor.doWithAnnotations(context, 0, source, AnnotationsScanner.getDeclaredAnnotations(source, false));
        } catch (Throwable ex) {
            AnnotationUtils.handleIntrospectionFailure(source, ex);
            return null;
        }
    }

    @Nullable
    static <A extends Annotation> A getDeclaredAnnotation(AnnotatedElement source, Class<A> annotationType) {
        Annotation[] annotations;
        for (Annotation annotation : annotations = AnnotationsScanner.getDeclaredAnnotations(source, false)) {
            if (annotation == null || annotationType != annotation.annotationType()) continue;
            return (A)annotation;
        }
        return null;
    }

    static Annotation[] getDeclaredAnnotations(AnnotatedElement source, boolean defensive) {
        boolean cached = false;
        Annotation[] annotations = declaredAnnotationCache.get(source);
        if (annotations != null) {
            cached = true;
        } else {
            annotations = source.getDeclaredAnnotations();
            if (annotations.length != 0) {
                boolean allIgnored = true;
                for (int i = 0; i < annotations.length; ++i) {
                    Annotation annotation = annotations[i];
                    if (AnnotationsScanner.isIgnorable(annotation.annotationType()) || !AttributeMethods.forAnnotationType(annotation.annotationType()).isValid(annotation)) {
                        annotations[i] = null;
                        continue;
                    }
                    allIgnored = false;
                }
                Annotation[] annotationArray = annotations = allIgnored ? NO_ANNOTATIONS : annotations;
                if (source instanceof Class || source instanceof Member) {
                    declaredAnnotationCache.put(source, annotations);
                    cached = true;
                }
            }
        }
        if (!defensive || annotations.length == 0 || !cached) {
            return annotations;
        }
        return (Annotation[])annotations.clone();
    }

    private static boolean isIgnorable(Class<?> annotationType) {
        return AnnotationFilter.PLAIN.matches(annotationType);
    }

    static boolean isKnownEmpty(AnnotatedElement source, MergedAnnotations.SearchStrategy searchStrategy) {
        if (AnnotationsScanner.hasPlainJavaAnnotationsOnly(source)) {
            return true;
        }
        if (searchStrategy == MergedAnnotations.SearchStrategy.DIRECT || AnnotationsScanner.isWithoutHierarchy(source, searchStrategy)) {
            if (source instanceof Method && ((Method)source).isBridge()) {
                return false;
            }
            return AnnotationsScanner.getDeclaredAnnotations(source, false).length == 0;
        }
        return false;
    }

    static boolean hasPlainJavaAnnotationsOnly(@Nullable Object annotatedElement) {
        if (annotatedElement instanceof Class) {
            return AnnotationsScanner.hasPlainJavaAnnotationsOnly((Class)annotatedElement);
        }
        if (annotatedElement instanceof Member) {
            return AnnotationsScanner.hasPlainJavaAnnotationsOnly(((Member)annotatedElement).getDeclaringClass());
        }
        return false;
    }

    static boolean hasPlainJavaAnnotationsOnly(Class<?> type) {
        return type.getName().startsWith("java.") || type == Ordered.class;
    }

    private static boolean isWithoutHierarchy(AnnotatedElement source, MergedAnnotations.SearchStrategy searchStrategy) {
        if (source == Object.class) {
            return true;
        }
        if (source instanceof Class) {
            boolean noSuperTypes;
            Class sourceClass = (Class)source;
            boolean bl = noSuperTypes = sourceClass.getSuperclass() == Object.class && sourceClass.getInterfaces().length == 0;
            return searchStrategy == MergedAnnotations.SearchStrategy.TYPE_HIERARCHY_AND_ENCLOSING_CLASSES ? noSuperTypes && sourceClass.getEnclosingClass() == null : noSuperTypes;
        }
        if (source instanceof Method) {
            Method sourceMethod = (Method)source;
            return Modifier.isPrivate(sourceMethod.getModifiers()) || AnnotationsScanner.isWithoutHierarchy(sourceMethod.getDeclaringClass(), searchStrategy);
        }
        return true;
    }

    static void clearCache() {
        declaredAnnotationCache.clear();
        baseTypeMethodsCache.clear();
    }
}

