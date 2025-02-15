/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.convert.support;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import org.springframework.core.CollectionFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.core.convert.support.ConversionUtils;
import org.springframework.lang.Nullable;

final class MapToMapConverter
implements ConditionalGenericConverter {
    private final ConversionService conversionService;

    public MapToMapConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public Set<GenericConverter.ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new GenericConverter.ConvertiblePair(Map.class, Map.class));
    }

    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return this.canConvertKey(sourceType, targetType) && this.canConvertValue(sourceType, targetType);
    }

    @Override
    @Nullable
    public Object convert(@Nullable Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        boolean copyRequired;
        if (source == null) {
            return null;
        }
        Map sourceMap = (Map)source;
        boolean bl = copyRequired = !targetType.getType().isInstance(source);
        if (!copyRequired && sourceMap.isEmpty()) {
            return sourceMap;
        }
        TypeDescriptor keyDesc = targetType.getMapKeyTypeDescriptor();
        TypeDescriptor valueDesc = targetType.getMapValueTypeDescriptor();
        ArrayList<MapEntry> targetEntries = new ArrayList<MapEntry>(sourceMap.size());
        for (Map.Entry entry : sourceMap.entrySet()) {
            Object sourceKey = entry.getKey();
            Object sourceValue = entry.getValue();
            Object targetKey = this.convertKey(sourceKey, sourceType, keyDesc);
            Object targetValue = this.convertValue(sourceValue, sourceType, valueDesc);
            targetEntries.add(new MapEntry(targetKey, targetValue));
            if (sourceKey == targetKey && sourceValue == targetValue) continue;
            copyRequired = true;
        }
        if (!copyRequired) {
            return sourceMap;
        }
        Map<Object, Object> targetMap = CollectionFactory.createMap(targetType.getType(), keyDesc != null ? keyDesc.getType() : null, sourceMap.size());
        for (MapEntry entry : targetEntries) {
            entry.addToMap(targetMap);
        }
        return targetMap;
    }

    private boolean canConvertKey(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return ConversionUtils.canConvertElements(sourceType.getMapKeyTypeDescriptor(), targetType.getMapKeyTypeDescriptor(), this.conversionService);
    }

    private boolean canConvertValue(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return ConversionUtils.canConvertElements(sourceType.getMapValueTypeDescriptor(), targetType.getMapValueTypeDescriptor(), this.conversionService);
    }

    @Nullable
    private Object convertKey(Object sourceKey, TypeDescriptor sourceType, @Nullable TypeDescriptor targetType) {
        if (targetType == null) {
            return sourceKey;
        }
        return this.conversionService.convert(sourceKey, sourceType.getMapKeyTypeDescriptor(sourceKey), targetType);
    }

    @Nullable
    private Object convertValue(Object sourceValue, TypeDescriptor sourceType, @Nullable TypeDescriptor targetType) {
        if (targetType == null) {
            return sourceValue;
        }
        return this.conversionService.convert(sourceValue, sourceType.getMapValueTypeDescriptor(sourceValue), targetType);
    }

    private static class MapEntry {
        @Nullable
        private final Object key;
        @Nullable
        private final Object value;

        public MapEntry(@Nullable Object key, @Nullable Object value) {
            this.key = key;
            this.value = value;
        }

        public void addToMap(Map<Object, Object> map) {
            map.put(this.key, this.value);
        }
    }
}

