/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.type.classreading;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.springframework.asm.AnnotationVisitor;
import org.springframework.asm.MethodVisitor;
import org.springframework.asm.Type;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.AnnotationAttributesReadingVisitor;
import org.springframework.core.type.classreading.AnnotationReadingVisitorUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Deprecated
public class MethodMetadataReadingVisitor
extends MethodVisitor
implements MethodMetadata {
    protected final String methodName;
    protected final int access;
    protected final String declaringClassName;
    protected final String returnTypeName;
    @Nullable
    protected final ClassLoader classLoader;
    protected final Set<MethodMetadata> methodMetadataSet;
    protected final Map<String, Set<String>> metaAnnotationMap = new LinkedHashMap<String, Set<String>>(4);
    protected final LinkedMultiValueMap<String, AnnotationAttributes> attributesMap = new LinkedMultiValueMap(3);

    public MethodMetadataReadingVisitor(String methodName, int access, String declaringClassName, String returnTypeName, @Nullable ClassLoader classLoader, Set<MethodMetadata> methodMetadataSet) {
        super(0x10A0000);
        this.methodName = methodName;
        this.access = access;
        this.declaringClassName = declaringClassName;
        this.returnTypeName = returnTypeName;
        this.classLoader = classLoader;
        this.methodMetadataSet = methodMetadataSet;
    }

    @Override
    public MergedAnnotations getAnnotations() {
        throw new UnsupportedOperationException();
    }

    @Override
    @Nullable
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        if (!visible) {
            return null;
        }
        this.methodMetadataSet.add(this);
        String className = Type.getType(desc).getClassName();
        return new AnnotationAttributesReadingVisitor(className, this.attributesMap, this.metaAnnotationMap, this.classLoader);
    }

    @Override
    public String getMethodName() {
        return this.methodName;
    }

    @Override
    public boolean isAbstract() {
        return (this.access & 0x400) != 0;
    }

    @Override
    public boolean isStatic() {
        return (this.access & 8) != 0;
    }

    @Override
    public boolean isFinal() {
        return (this.access & 0x10) != 0;
    }

    @Override
    public boolean isOverridable() {
        return !this.isStatic() && !this.isFinal() && (this.access & 2) == 0;
    }

    @Override
    public boolean isAnnotated(String annotationName) {
        return this.attributesMap.containsKey(annotationName);
    }

    @Nullable
    public AnnotationAttributes getAnnotationAttributes(String annotationName, boolean classValuesAsString) {
        AnnotationAttributes raw = AnnotationReadingVisitorUtils.getMergedAnnotationAttributes(this.attributesMap, this.metaAnnotationMap, annotationName);
        if (raw == null) {
            return null;
        }
        return AnnotationReadingVisitorUtils.convertClassValues("method '" + this.getMethodName() + "'", this.classLoader, raw, classValuesAsString);
    }

    @Override
    @Nullable
    public MultiValueMap<String, Object> getAllAnnotationAttributes(String annotationName, boolean classValuesAsString) {
        if (!this.attributesMap.containsKey(annotationName)) {
            return null;
        }
        LinkedMultiValueMap<String, Object> allAttributes = new LinkedMultiValueMap<String, Object>();
        Object attributesList = this.attributesMap.get(annotationName);
        if (attributesList != null) {
            String annotatedElement = "method '" + this.getMethodName() + '\'';
            Iterator iterator = attributesList.iterator();
            while (iterator.hasNext()) {
                AnnotationAttributes annotationAttributes = (AnnotationAttributes)iterator.next();
                AnnotationAttributes convertedAttributes = AnnotationReadingVisitorUtils.convertClassValues(annotatedElement, this.classLoader, annotationAttributes, classValuesAsString);
                convertedAttributes.forEach(allAttributes::add);
            }
        }
        return allAttributes;
    }

    @Override
    public String getDeclaringClassName() {
        return this.declaringClassName;
    }

    @Override
    public String getReturnTypeName() {
        return this.returnTypeName;
    }
}

