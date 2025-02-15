/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.core.annotation.AliasFor;
import org.springframework.core.annotation.AnnotationConfigurationException;
import org.springframework.core.annotation.AnnotationTypeMappings;
import org.springframework.core.annotation.AnnotationsScanner;
import org.springframework.core.annotation.AttributeMethods;
import org.springframework.core.annotation.TypeMappedAnnotation;
import org.springframework.core.annotation.ValueExtractor;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

final class AnnotationTypeMapping {
    private static final MirrorSets.MirrorSet[] EMPTY_MIRROR_SETS = new MirrorSets.MirrorSet[0];
    @Nullable
    private final AnnotationTypeMapping source;
    private final AnnotationTypeMapping root;
    private final int distance;
    private final Class<? extends Annotation> annotationType;
    private final List<Class<? extends Annotation>> metaTypes;
    @Nullable
    private final Annotation annotation;
    private final AttributeMethods attributes;
    private final MirrorSets mirrorSets;
    private final int[] aliasMappings;
    private final int[] conventionMappings;
    private final int[] annotationValueMappings;
    private final AnnotationTypeMapping[] annotationValueSource;
    private final Map<Method, List<Method>> aliasedBy;
    private final boolean synthesizable;
    private final Set<Method> claimedAliases = new HashSet<Method>();

    AnnotationTypeMapping(@Nullable AnnotationTypeMapping source, Class<? extends Annotation> annotationType, @Nullable Annotation annotation) {
        this.source = source;
        this.root = source != null ? source.getRoot() : this;
        this.distance = source == null ? 0 : source.getDistance() + 1;
        this.annotationType = annotationType;
        this.metaTypes = AnnotationTypeMapping.merge(source != null ? source.getMetaTypes() : null, annotationType);
        this.annotation = annotation;
        this.attributes = AttributeMethods.forAnnotationType(annotationType);
        this.mirrorSets = new MirrorSets();
        this.aliasMappings = AnnotationTypeMapping.filledIntArray(this.attributes.size());
        this.conventionMappings = AnnotationTypeMapping.filledIntArray(this.attributes.size());
        this.annotationValueMappings = AnnotationTypeMapping.filledIntArray(this.attributes.size());
        this.annotationValueSource = new AnnotationTypeMapping[this.attributes.size()];
        this.aliasedBy = this.resolveAliasedForTargets();
        this.processAliases();
        this.addConventionMappings();
        this.addConventionAnnotationValues();
        this.synthesizable = this.computeSynthesizableFlag();
    }

    private static <T> List<T> merge(@Nullable List<T> existing, T element) {
        if (existing == null) {
            return Collections.singletonList(element);
        }
        ArrayList<T> merged = new ArrayList<T>(existing.size() + 1);
        merged.addAll(existing);
        merged.add(element);
        return Collections.unmodifiableList(merged);
    }

    private Map<Method, List<Method>> resolveAliasedForTargets() {
        HashMap<Method, List> aliasedBy = new HashMap<Method, List>();
        for (int i = 0; i < this.attributes.size(); ++i) {
            Method attribute = this.attributes.get(i);
            AliasFor aliasFor = AnnotationsScanner.getDeclaredAnnotation(attribute, AliasFor.class);
            if (aliasFor == null) continue;
            Method target = this.resolveAliasTarget(attribute, aliasFor);
            aliasedBy.computeIfAbsent(target, key -> new ArrayList()).add(attribute);
        }
        return Collections.unmodifiableMap(aliasedBy);
    }

    private Method resolveAliasTarget(Method attribute, AliasFor aliasFor) {
        return this.resolveAliasTarget(attribute, aliasFor, true);
    }

    private Method resolveAliasTarget(Method attribute, AliasFor aliasFor, boolean checkAliasPair) {
        Method mirror;
        AliasFor targetAliasFor;
        Method target;
        String targetAttributeName;
        if (StringUtils.hasText(aliasFor.value()) && StringUtils.hasText(aliasFor.attribute())) {
            throw new AnnotationConfigurationException(String.format("In @AliasFor declared on %s, attribute 'attribute' and its alias 'value' are present with values of '%s' and '%s', but only one is permitted.", AttributeMethods.describe(attribute), aliasFor.attribute(), aliasFor.value()));
        }
        Class<? extends Annotation> targetAnnotation = aliasFor.annotation();
        if (targetAnnotation == Annotation.class) {
            targetAnnotation = this.annotationType;
        }
        if (!StringUtils.hasLength(targetAttributeName = aliasFor.attribute())) {
            targetAttributeName = aliasFor.value();
        }
        if (!StringUtils.hasLength(targetAttributeName)) {
            targetAttributeName = attribute.getName();
        }
        if ((target = AttributeMethods.forAnnotationType(targetAnnotation).get(targetAttributeName)) == null) {
            if (targetAnnotation == this.annotationType) {
                throw new AnnotationConfigurationException(String.format("@AliasFor declaration on %s declares an alias for '%s' which is not present.", AttributeMethods.describe(attribute), targetAttributeName));
            }
            throw new AnnotationConfigurationException(String.format("%s is declared as an @AliasFor nonexistent %s.", StringUtils.capitalize(AttributeMethods.describe(attribute)), AttributeMethods.describe(targetAnnotation, targetAttributeName)));
        }
        if (target.equals(attribute)) {
            throw new AnnotationConfigurationException(String.format("@AliasFor declaration on %s points to itself. Specify 'annotation' to point to a same-named attribute on a meta-annotation.", AttributeMethods.describe(attribute)));
        }
        if (!this.isCompatibleReturnType(attribute.getReturnType(), target.getReturnType())) {
            throw new AnnotationConfigurationException(String.format("Misconfigured aliases: %s and %s must declare the same return type.", AttributeMethods.describe(attribute), AttributeMethods.describe(target)));
        }
        if (this.isAliasPair(target) && checkAliasPair && (targetAliasFor = target.getAnnotation(AliasFor.class)) != null && !(mirror = this.resolveAliasTarget(target, targetAliasFor, false)).equals(attribute)) {
            throw new AnnotationConfigurationException(String.format("%s must be declared as an @AliasFor %s, not %s.", StringUtils.capitalize(AttributeMethods.describe(target)), AttributeMethods.describe(attribute), AttributeMethods.describe(mirror)));
        }
        return target;
    }

    private boolean isAliasPair(Method target) {
        return this.annotationType == target.getDeclaringClass();
    }

    private boolean isCompatibleReturnType(Class<?> attributeType, Class<?> targetType) {
        return attributeType == targetType || attributeType == targetType.getComponentType();
    }

    private void processAliases() {
        ArrayList<Method> aliases = new ArrayList<Method>();
        for (int i = 0; i < this.attributes.size(); ++i) {
            aliases.clear();
            aliases.add(this.attributes.get(i));
            this.collectAliases(aliases);
            if (aliases.size() <= 1) continue;
            this.processAliases(i, aliases);
        }
    }

    private void collectAliases(List<Method> aliases) {
        AnnotationTypeMapping mapping = this;
        while (mapping != null) {
            int size = aliases.size();
            for (int j = 0; j < size; ++j) {
                List<Method> additional = mapping.aliasedBy.get(aliases.get(j));
                if (additional == null) continue;
                aliases.addAll(additional);
            }
            mapping = mapping.source;
        }
    }

    private void processAliases(int attributeIndex, List<Method> aliases) {
        int rootAttributeIndex = this.getFirstRootAttributeIndex(aliases);
        AnnotationTypeMapping mapping = this;
        while (mapping != null) {
            if (rootAttributeIndex != -1 && mapping != this.root) {
                for (int i = 0; i < mapping.attributes.size(); ++i) {
                    if (!aliases.contains(mapping.attributes.get(i))) continue;
                    mapping.aliasMappings[i] = rootAttributeIndex;
                }
            }
            mapping.mirrorSets.updateFrom(aliases);
            mapping.claimedAliases.addAll(aliases);
            if (mapping.annotation != null) {
                int[] resolvedMirrors = mapping.mirrorSets.resolve(null, mapping.annotation, ReflectionUtils::invokeMethod);
                for (int i = 0; i < mapping.attributes.size(); ++i) {
                    if (!aliases.contains(mapping.attributes.get(i))) continue;
                    this.annotationValueMappings[attributeIndex] = resolvedMirrors[i];
                    this.annotationValueSource[attributeIndex] = mapping;
                }
            }
            mapping = mapping.source;
        }
    }

    private int getFirstRootAttributeIndex(Collection<Method> aliases) {
        AttributeMethods rootAttributes = this.root.getAttributes();
        for (int i = 0; i < rootAttributes.size(); ++i) {
            if (!aliases.contains(rootAttributes.get(i))) continue;
            return i;
        }
        return -1;
    }

    private void addConventionMappings() {
        if (this.distance == 0) {
            return;
        }
        AttributeMethods rootAttributes = this.root.getAttributes();
        int[] mappings = this.conventionMappings;
        for (int i = 0; i < mappings.length; ++i) {
            String name = this.attributes.get(i).getName();
            MirrorSets.MirrorSet mirrors = this.getMirrorSets().getAssigned(i);
            int mapped = rootAttributes.indexOf(name);
            if ("value".equals(name) || mapped == -1) continue;
            mappings[i] = mapped;
            if (mirrors == null) continue;
            for (int j = 0; j < mirrors.size(); ++j) {
                mappings[mirrors.getAttributeIndex((int)j)] = mapped;
            }
        }
    }

    private void addConventionAnnotationValues() {
        for (int i = 0; i < this.attributes.size(); ++i) {
            Method attribute = this.attributes.get(i);
            boolean isValueAttribute = "value".equals(attribute.getName());
            AnnotationTypeMapping mapping = this;
            while (mapping != null && mapping.distance > 0) {
                int mapped = mapping.getAttributes().indexOf(attribute.getName());
                if (mapped != -1 && this.isBetterConventionAnnotationValue(i, isValueAttribute, mapping)) {
                    this.annotationValueMappings[i] = mapped;
                    this.annotationValueSource[i] = mapping;
                }
                mapping = mapping.source;
            }
        }
    }

    private boolean isBetterConventionAnnotationValue(int index, boolean isValueAttribute, AnnotationTypeMapping mapping) {
        if (this.annotationValueMappings[index] == -1) {
            return true;
        }
        int existingDistance = this.annotationValueSource[index].distance;
        return !isValueAttribute && existingDistance > mapping.distance;
    }

    private boolean computeSynthesizableFlag() {
        for (int index : this.aliasMappings) {
            if (index == -1) continue;
            return true;
        }
        if (!this.aliasedBy.isEmpty()) {
            return true;
        }
        for (int index : this.conventionMappings) {
            if (index == -1) continue;
            return true;
        }
        if (this.getAttributes().hasNestedAnnotation()) {
            AttributeMethods attributeMethods = this.getAttributes();
            for (int i = 0; i < attributeMethods.size(); ++i) {
                Class<?> annotationType;
                AnnotationTypeMapping mapping;
                Method method = attributeMethods.get(i);
                Class<?> type = method.getReturnType();
                if (!type.isAnnotation() && (!type.isArray() || !type.getComponentType().isAnnotation()) || !(mapping = AnnotationTypeMappings.forAnnotationType(annotationType = type.isAnnotation() ? type : type.getComponentType()).get(0)).isSynthesizable()) continue;
                return true;
            }
        }
        return false;
    }

    void afterAllMappingsSet() {
        this.validateAllAliasesClaimed();
        for (int i = 0; i < this.mirrorSets.size(); ++i) {
            this.validateMirrorSet(this.mirrorSets.get(i));
        }
        this.claimedAliases.clear();
    }

    private void validateAllAliasesClaimed() {
        for (int i = 0; i < this.attributes.size(); ++i) {
            Method attribute = this.attributes.get(i);
            AliasFor aliasFor = AnnotationsScanner.getDeclaredAnnotation(attribute, AliasFor.class);
            if (aliasFor == null || this.claimedAliases.contains(attribute)) continue;
            Method target = this.resolveAliasTarget(attribute, aliasFor);
            throw new AnnotationConfigurationException(String.format("@AliasFor declaration on %s declares an alias for %s which is not meta-present.", AttributeMethods.describe(attribute), AttributeMethods.describe(target)));
        }
    }

    private void validateMirrorSet(MirrorSets.MirrorSet mirrorSet) {
        Method firstAttribute = mirrorSet.get(0);
        Object firstDefaultValue = firstAttribute.getDefaultValue();
        for (int i = 1; i <= mirrorSet.size() - 1; ++i) {
            Method mirrorAttribute = mirrorSet.get(i);
            Object mirrorDefaultValue = mirrorAttribute.getDefaultValue();
            if (firstDefaultValue == null || mirrorDefaultValue == null) {
                throw new AnnotationConfigurationException(String.format("Misconfigured aliases: %s and %s must declare default values.", AttributeMethods.describe(firstAttribute), AttributeMethods.describe(mirrorAttribute)));
            }
            if (ObjectUtils.nullSafeEquals(firstDefaultValue, mirrorDefaultValue)) continue;
            throw new AnnotationConfigurationException(String.format("Misconfigured aliases: %s and %s must declare the same default value.", AttributeMethods.describe(firstAttribute), AttributeMethods.describe(mirrorAttribute)));
        }
    }

    AnnotationTypeMapping getRoot() {
        return this.root;
    }

    @Nullable
    AnnotationTypeMapping getSource() {
        return this.source;
    }

    int getDistance() {
        return this.distance;
    }

    Class<? extends Annotation> getAnnotationType() {
        return this.annotationType;
    }

    List<Class<? extends Annotation>> getMetaTypes() {
        return this.metaTypes;
    }

    @Nullable
    Annotation getAnnotation() {
        return this.annotation;
    }

    AttributeMethods getAttributes() {
        return this.attributes;
    }

    int getAliasMapping(int attributeIndex) {
        return this.aliasMappings[attributeIndex];
    }

    int getConventionMapping(int attributeIndex) {
        return this.conventionMappings[attributeIndex];
    }

    @Nullable
    Object getMappedAnnotationValue(int attributeIndex, boolean metaAnnotationsOnly) {
        int mappedIndex = this.annotationValueMappings[attributeIndex];
        if (mappedIndex == -1) {
            return null;
        }
        AnnotationTypeMapping source = this.annotationValueSource[attributeIndex];
        if (source == this && metaAnnotationsOnly) {
            return null;
        }
        return ReflectionUtils.invokeMethod(source.attributes.get(mappedIndex), source.annotation);
    }

    boolean isEquivalentToDefaultValue(int attributeIndex, Object value, ValueExtractor valueExtractor) {
        Method attribute = this.attributes.get(attributeIndex);
        return AnnotationTypeMapping.isEquivalentToDefaultValue(attribute, value, valueExtractor);
    }

    MirrorSets getMirrorSets() {
        return this.mirrorSets;
    }

    boolean isSynthesizable() {
        return this.synthesizable;
    }

    private static int[] filledIntArray(int size) {
        int[] array = new int[size];
        Arrays.fill(array, -1);
        return array;
    }

    private static boolean isEquivalentToDefaultValue(Method attribute, Object value, ValueExtractor valueExtractor) {
        return AnnotationTypeMapping.areEquivalent(attribute.getDefaultValue(), value, valueExtractor);
    }

    private static boolean areEquivalent(@Nullable Object value, @Nullable Object extractedValue, ValueExtractor valueExtractor) {
        if (ObjectUtils.nullSafeEquals(value, extractedValue)) {
            return true;
        }
        if (value instanceof Class && extractedValue instanceof String) {
            return AnnotationTypeMapping.areEquivalent((Class)value, (String)extractedValue);
        }
        if (value instanceof Class[] && extractedValue instanceof String[]) {
            return AnnotationTypeMapping.areEquivalent((Class[])value, (String[])extractedValue);
        }
        if (value instanceof Annotation) {
            return AnnotationTypeMapping.areEquivalent((Annotation)value, extractedValue, valueExtractor);
        }
        return false;
    }

    private static boolean areEquivalent(Class<?>[] value, String[] extractedValue) {
        if (value.length != extractedValue.length) {
            return false;
        }
        for (int i = 0; i < value.length; ++i) {
            if (AnnotationTypeMapping.areEquivalent(value[i], extractedValue[i])) continue;
            return false;
        }
        return true;
    }

    private static boolean areEquivalent(Class<?> value, String extractedValue) {
        return value.getName().equals(extractedValue);
    }

    private static boolean areEquivalent(Annotation annotation, @Nullable Object extractedValue, ValueExtractor valueExtractor) {
        AttributeMethods attributes = AttributeMethods.forAnnotationType(annotation.annotationType());
        for (int i = 0; i < attributes.size(); ++i) {
            Object value2;
            Method attribute = attributes.get(i);
            Object value1 = ReflectionUtils.invokeMethod(attribute, annotation);
            if (AnnotationTypeMapping.areEquivalent(value1, value2 = extractedValue instanceof TypeMappedAnnotation ? ((TypeMappedAnnotation)extractedValue).getValue(attribute.getName()).orElse(null) : valueExtractor.extract(attribute, extractedValue), valueExtractor)) continue;
            return false;
        }
        return true;
    }

    class MirrorSets {
        private MirrorSet[] mirrorSets;
        private final MirrorSet[] assigned;

        MirrorSets() {
            this.assigned = new MirrorSet[AnnotationTypeMapping.this.attributes.size()];
            this.mirrorSets = EMPTY_MIRROR_SETS;
        }

        void updateFrom(Collection<Method> aliases) {
            MirrorSet mirrorSet = null;
            int size = 0;
            int last = -1;
            for (int i = 0; i < AnnotationTypeMapping.this.attributes.size(); ++i) {
                Method attribute = AnnotationTypeMapping.this.attributes.get(i);
                if (!aliases.contains(attribute)) continue;
                if (++size > 1) {
                    if (mirrorSet == null) {
                        this.assigned[last] = mirrorSet = new MirrorSet();
                    }
                    this.assigned[i] = mirrorSet;
                }
                last = i;
            }
            if (mirrorSet != null) {
                mirrorSet.update();
                LinkedHashSet<MirrorSet> unique = new LinkedHashSet<MirrorSet>(Arrays.asList(this.assigned));
                unique.remove(null);
                this.mirrorSets = unique.toArray(EMPTY_MIRROR_SETS);
            }
        }

        int size() {
            return this.mirrorSets.length;
        }

        MirrorSet get(int index) {
            return this.mirrorSets[index];
        }

        @Nullable
        MirrorSet getAssigned(int attributeIndex) {
            return this.assigned[attributeIndex];
        }

        int[] resolve(@Nullable Object source, @Nullable Object annotation, ValueExtractor valueExtractor) {
            int i;
            int[] result = new int[AnnotationTypeMapping.this.attributes.size()];
            for (i = 0; i < result.length; ++i) {
                result[i] = i;
            }
            for (i = 0; i < this.size(); ++i) {
                MirrorSet mirrorSet = this.get(i);
                int resolved = mirrorSet.resolve(source, annotation, valueExtractor);
                for (int j = 0; j < mirrorSet.size; ++j) {
                    result[((MirrorSet)mirrorSet).indexes[j]] = resolved;
                }
            }
            return result;
        }

        class MirrorSet {
            private int size;
            private final int[] indexes;

            MirrorSet() {
                this.indexes = new int[AnnotationTypeMapping.this.attributes.size()];
            }

            void update() {
                this.size = 0;
                Arrays.fill(this.indexes, -1);
                for (int i = 0; i < MirrorSets.this.assigned.length; ++i) {
                    if (MirrorSets.this.assigned[i] != this) continue;
                    this.indexes[this.size] = i;
                    ++this.size;
                }
            }

            <A> int resolve(@Nullable Object source, @Nullable A annotation, ValueExtractor valueExtractor) {
                int result = -1;
                Object lastValue = null;
                for (int i = 0; i < this.size; ++i) {
                    boolean isDefaultValue;
                    Method attribute = AnnotationTypeMapping.this.attributes.get(this.indexes[i]);
                    Object value = valueExtractor.extract(attribute, annotation);
                    boolean bl = isDefaultValue = value == null || AnnotationTypeMapping.isEquivalentToDefaultValue(attribute, value, valueExtractor);
                    if (isDefaultValue || ObjectUtils.nullSafeEquals(lastValue, value)) {
                        if (result != -1) continue;
                        result = this.indexes[i];
                        continue;
                    }
                    if (lastValue != null && !ObjectUtils.nullSafeEquals(lastValue, value)) {
                        String on = source != null ? " declared on " + source : "";
                        throw new AnnotationConfigurationException(String.format("Different @AliasFor mirror values for annotation [%s]%s; attribute '%s' and its alias '%s' are declared with values of [%s] and [%s].", AnnotationTypeMapping.this.getAnnotationType().getName(), on, AnnotationTypeMapping.this.attributes.get(result).getName(), attribute.getName(), ObjectUtils.nullSafeToString(lastValue), ObjectUtils.nullSafeToString(value)));
                    }
                    result = this.indexes[i];
                    lastValue = value;
                }
                return result;
            }

            int size() {
                return this.size;
            }

            Method get(int index) {
                int attributeIndex = this.indexes[index];
                return AnnotationTypeMapping.this.attributes.get(attributeIndex);
            }

            int getAttributeIndex(int index) {
                return this.indexes[index];
            }
        }
    }
}

