/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.convert.support;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import org.springframework.core.CollectionFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.core.convert.support.ConversionUtils;
import org.springframework.lang.Nullable;

final class CollectionToCollectionConverter
implements ConditionalGenericConverter {
    private final ConversionService conversionService;

    public CollectionToCollectionConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public Set<GenericConverter.ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new GenericConverter.ConvertiblePair(Collection.class, Collection.class));
    }

    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return ConversionUtils.canConvertElements(sourceType.getElementTypeDescriptor(), targetType.getElementTypeDescriptor(), this.conversionService);
    }

    @Override
    @Nullable
    public Object convert(@Nullable Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        boolean copyRequired;
        if (source == null) {
            return null;
        }
        Collection sourceCollection = (Collection)source;
        boolean bl = copyRequired = !targetType.getType().isInstance(source);
        if (!copyRequired && sourceCollection.isEmpty()) {
            return source;
        }
        TypeDescriptor elementDesc = targetType.getElementTypeDescriptor();
        if (elementDesc == null && !copyRequired) {
            return source;
        }
        Collection<Object> target = CollectionFactory.createCollection(targetType.getType(), elementDesc != null ? elementDesc.getType() : null, sourceCollection.size());
        if (elementDesc == null) {
            target.addAll(sourceCollection);
        } else {
            for (Object sourceElement : sourceCollection) {
                Object targetElement = this.conversionService.convert(sourceElement, sourceType.elementTypeDescriptor(sourceElement), elementDesc);
                target.add(targetElement);
                if (sourceElement == targetElement) continue;
                copyRequired = true;
            }
        }
        return copyRequired ? target : source;
    }
}

