/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.type.classreading;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.springframework.asm.Type;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.ObjectUtils;

@Deprecated
abstract class AnnotationReadingVisitorUtils {
    AnnotationReadingVisitorUtils() {
    }

    public static AnnotationAttributes convertClassValues(Object annotatedElement, @Nullable ClassLoader classLoader, AnnotationAttributes original, boolean classValuesAsString) {
        AnnotationAttributes result = new AnnotationAttributes(original);
        AnnotationUtils.postProcessAnnotationAttributes(annotatedElement, result, classValuesAsString);
        for (Map.Entry entry : result.entrySet()) {
            try {
                int i;
                Object value = entry.getValue();
                if (value instanceof AnnotationAttributes) {
                    value = AnnotationReadingVisitorUtils.convertClassValues(annotatedElement, classLoader, (AnnotationAttributes)value, classValuesAsString);
                } else if (value instanceof AnnotationAttributes[]) {
                    AnnotationAttributes[] values = (AnnotationAttributes[])value;
                    for (int i2 = 0; i2 < values.length; ++i2) {
                        values[i2] = AnnotationReadingVisitorUtils.convertClassValues(annotatedElement, classLoader, values[i2], classValuesAsString);
                    }
                    value = values;
                } else if (value instanceof Type) {
                    value = classValuesAsString ? ((Type)value).getClassName() : ClassUtils.forName(((Type)value).getClassName(), classLoader);
                } else if (value instanceof Type[]) {
                    Type[] array = (Type[])value;
                    Object[] convArray = classValuesAsString ? new String[array.length] : new Class[array.length];
                    for (i = 0; i < array.length; ++i) {
                        convArray[i] = classValuesAsString ? array[i].getClassName() : ClassUtils.forName(array[i].getClassName(), classLoader);
                    }
                    value = convArray;
                } else if (classValuesAsString) {
                    if (value instanceof Class) {
                        value = ((Class)value).getName();
                    } else if (value instanceof Class[]) {
                        Class[] clazzArray = (Class[])value;
                        String[] newValue = new String[clazzArray.length];
                        for (i = 0; i < clazzArray.length; ++i) {
                            newValue[i] = clazzArray[i].getName();
                        }
                        value = newValue;
                    }
                }
                entry.setValue(value);
            } catch (Throwable ex) {
                result.put(entry.getKey(), ex);
            }
        }
        return result;
    }

    @Nullable
    public static AnnotationAttributes getMergedAnnotationAttributes(LinkedMultiValueMap<String, AnnotationAttributes> attributesMap, Map<String, Set<String>> metaAnnotationMap, String annotationName) {
        Object attributesList = attributesMap.get(annotationName);
        if (CollectionUtils.isEmpty(attributesList)) {
            return null;
        }
        AnnotationAttributes result = new AnnotationAttributes((AnnotationAttributes)attributesList.get(0));
        HashSet overridableAttributeNames = new HashSet(result.keySet());
        overridableAttributeNames.remove("value");
        ArrayList annotationTypes = new ArrayList(attributesMap.keySet());
        Collections.reverse(annotationTypes);
        annotationTypes.remove(annotationName);
        for (String currentAnnotationType : annotationTypes) {
            Set<String> metaAnns;
            Object currentAttributesList = attributesMap.get(currentAnnotationType);
            if (ObjectUtils.isEmpty(currentAttributesList) || (metaAnns = metaAnnotationMap.get(currentAnnotationType)) == null || !metaAnns.contains(annotationName)) continue;
            AnnotationAttributes currentAttributes = (AnnotationAttributes)currentAttributesList.get(0);
            for (String overridableAttributeName : overridableAttributeNames) {
                Object value = currentAttributes.get(overridableAttributeName);
                if (value == null) continue;
                result.put(overridableAttributeName, value);
            }
        }
        return result;
    }
}

