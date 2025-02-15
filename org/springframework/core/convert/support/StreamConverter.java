/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.convert.support;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.lang.Nullable;

class StreamConverter
implements ConditionalGenericConverter {
    private static final TypeDescriptor STREAM_TYPE = TypeDescriptor.valueOf(Stream.class);
    private static final Set<GenericConverter.ConvertiblePair> CONVERTIBLE_TYPES = StreamConverter.createConvertibleTypes();
    private final ConversionService conversionService;

    public StreamConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public Set<GenericConverter.ConvertiblePair> getConvertibleTypes() {
        return CONVERTIBLE_TYPES;
    }

    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (sourceType.isAssignableTo(STREAM_TYPE)) {
            return this.matchesFromStream(sourceType.getElementTypeDescriptor(), targetType);
        }
        if (targetType.isAssignableTo(STREAM_TYPE)) {
            return this.matchesToStream(targetType.getElementTypeDescriptor(), sourceType);
        }
        return false;
    }

    public boolean matchesFromStream(@Nullable TypeDescriptor elementType, TypeDescriptor targetType) {
        TypeDescriptor collectionOfElement = TypeDescriptor.collection(Collection.class, elementType);
        return this.conversionService.canConvert(collectionOfElement, targetType);
    }

    public boolean matchesToStream(@Nullable TypeDescriptor elementType, TypeDescriptor sourceType) {
        TypeDescriptor collectionOfElement = TypeDescriptor.collection(Collection.class, elementType);
        return this.conversionService.canConvert(sourceType, collectionOfElement);
    }

    @Override
    @Nullable
    public Object convert(@Nullable Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (sourceType.isAssignableTo(STREAM_TYPE)) {
            return this.convertFromStream((Stream)source, sourceType, targetType);
        }
        if (targetType.isAssignableTo(STREAM_TYPE)) {
            return this.convertToStream(source, sourceType, targetType);
        }
        throw new IllegalStateException("Unexpected source/target types");
    }

    @Nullable
    private Object convertFromStream(@Nullable Stream<?> source, TypeDescriptor streamType, TypeDescriptor targetType) {
        List content = source != null ? source.collect(Collectors.toList()) : Collections.emptyList();
        TypeDescriptor listType = TypeDescriptor.collection(List.class, streamType.getElementTypeDescriptor());
        return this.conversionService.convert(content, listType, targetType);
    }

    private Object convertToStream(@Nullable Object source, TypeDescriptor sourceType, TypeDescriptor streamType) {
        TypeDescriptor targetCollection = TypeDescriptor.collection(List.class, streamType.getElementTypeDescriptor());
        List target = (List)this.conversionService.convert(source, sourceType, targetCollection);
        if (target == null) {
            target = Collections.emptyList();
        }
        return target.stream();
    }

    private static Set<GenericConverter.ConvertiblePair> createConvertibleTypes() {
        HashSet<GenericConverter.ConvertiblePair> convertiblePairs = new HashSet<GenericConverter.ConvertiblePair>();
        convertiblePairs.add(new GenericConverter.ConvertiblePair(Stream.class, Collection.class));
        convertiblePairs.add(new GenericConverter.ConvertiblePair(Stream.class, Object[].class));
        convertiblePairs.add(new GenericConverter.ConvertiblePair(Collection.class, Stream.class));
        convertiblePairs.add(new GenericConverter.ConvertiblePair(Object[].class, Stream.class));
        return convertiblePairs;
    }
}

