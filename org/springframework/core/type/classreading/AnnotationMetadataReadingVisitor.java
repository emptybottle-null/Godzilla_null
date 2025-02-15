/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.type.classreading;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import org.springframework.asm.AnnotationVisitor;
import org.springframework.asm.MethodVisitor;
import org.springframework.asm.Type;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.AnnotationAttributesReadingVisitor;
import org.springframework.core.type.classreading.AnnotationReadingVisitorUtils;
import org.springframework.core.type.classreading.ClassMetadataReadingVisitor;
import org.springframework.core.type.classreading.MethodMetadataReadingVisitor;
import org.springframework.lang.Nullable;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Deprecated
public class AnnotationMetadataReadingVisitor
extends ClassMetadataReadingVisitor
implements AnnotationMetadata {
    @Nullable
    protected final ClassLoader classLoader;
    protected final Set<String> annotationSet = new LinkedHashSet<String>(4);
    protected final Map<String, Set<String>> metaAnnotationMap = new LinkedHashMap<String, Set<String>>(4);
    protected final LinkedMultiValueMap<String, AnnotationAttributes> attributesMap = new LinkedMultiValueMap(3);
    protected final Set<MethodMetadata> methodMetadataSet = new LinkedHashSet<MethodMetadata>(4);

    public AnnotationMetadataReadingVisitor(@Nullable ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public MergedAnnotations getAnnotations() {
        throw new UnsupportedOperationException();
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        if ((access & 0x40) != 0) {
            return super.visitMethod(access, name, desc, signature, exceptions);
        }
        return new MethodMetadataReadingVisitor(name, access, this.getClassName(), Type.getReturnType(desc).getClassName(), this.classLoader, this.methodMetadataSet);
    }

    @Override
    @Nullable
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        if (!visible) {
            return null;
        }
        String className = Type.getType(desc).getClassName();
        if (AnnotationUtils.isInJavaLangAnnotationPackage(className)) {
            return null;
        }
        this.annotationSet.add(className);
        return new AnnotationAttributesReadingVisitor(className, this.attributesMap, this.metaAnnotationMap, this.classLoader);
    }

    @Override
    public Set<String> getAnnotationTypes() {
        return this.annotationSet;
    }

    @Override
    public Set<String> getMetaAnnotationTypes(String annotationName) {
        Set<String> metaAnnotationTypes = this.metaAnnotationMap.get(annotationName);
        return metaAnnotationTypes != null ? metaAnnotationTypes : Collections.emptySet();
    }

    @Override
    public boolean hasMetaAnnotation(String metaAnnotationType) {
        if (AnnotationUtils.isInJavaLangAnnotationPackage(metaAnnotationType)) {
            return false;
        }
        Collection<Set<String>> allMetaTypes = this.metaAnnotationMap.values();
        for (Set<String> metaTypes : allMetaTypes) {
            if (!metaTypes.contains(metaAnnotationType)) continue;
            return true;
        }
        return false;
    }

    @Override
    public boolean isAnnotated(String annotationName) {
        return !AnnotationUtils.isInJavaLangAnnotationPackage(annotationName) && this.attributesMap.containsKey(annotationName);
    }

    @Override
    public boolean hasAnnotation(String annotationName) {
        return this.getAnnotationTypes().contains(annotationName);
    }

    @Nullable
    public AnnotationAttributes getAnnotationAttributes(String annotationName, boolean classValuesAsString) {
        AnnotationAttributes raw = AnnotationReadingVisitorUtils.getMergedAnnotationAttributes(this.attributesMap, this.metaAnnotationMap, annotationName);
        if (raw == null) {
            return null;
        }
        return AnnotationReadingVisitorUtils.convertClassValues("class '" + this.getClassName() + "'", this.classLoader, raw, classValuesAsString);
    }

    @Override
    @Nullable
    public MultiValueMap<String, Object> getAllAnnotationAttributes(String annotationName, boolean classValuesAsString) {
        LinkedMultiValueMap<String, Object> allAttributes = new LinkedMultiValueMap<String, Object>();
        Object attributes = this.attributesMap.get(annotationName);
        if (attributes == null) {
            return null;
        }
        String annotatedElement = "class '" + this.getClassName() + "'";
        Iterator iterator = attributes.iterator();
        while (iterator.hasNext()) {
            AnnotationAttributes raw = (AnnotationAttributes)iterator.next();
            for (Map.Entry entry : AnnotationReadingVisitorUtils.convertClassValues(annotatedElement, this.classLoader, raw, classValuesAsString).entrySet()) {
                allAttributes.add((String)entry.getKey(), entry.getValue());
            }
        }
        return allAttributes;
    }

    @Override
    public boolean hasAnnotatedMethods(String annotationName) {
        for (MethodMetadata methodMetadata : this.methodMetadataSet) {
            if (!methodMetadata.isAnnotated(annotationName)) continue;
            return true;
        }
        return false;
    }

    @Override
    public Set<MethodMetadata> getAnnotatedMethods(String annotationName) {
        LinkedHashSet<MethodMetadata> annotatedMethods = new LinkedHashSet<MethodMetadata>(4);
        for (MethodMetadata methodMetadata : this.methodMetadataSet) {
            if (!methodMetadata.isAnnotated(annotationName)) continue;
            annotatedMethods.add(methodMetadata);
        }
        return annotatedMethods;
    }
}

