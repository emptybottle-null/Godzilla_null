/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.convert.support;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.lang.Nullable;

final class ObjectToOptionalConverter
implements ConditionalGenericConverter {
    private final ConversionService conversionService;

    public ObjectToOptionalConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public Set<GenericConverter.ConvertiblePair> getConvertibleTypes() {
        LinkedHashSet<GenericConverter.ConvertiblePair> convertibleTypes = new LinkedHashSet<GenericConverter.ConvertiblePair>(4);
        convertibleTypes.add(new GenericConverter.ConvertiblePair(Collection.class, Optional.class));
        convertibleTypes.add(new GenericConverter.ConvertiblePair(Object[].class, Optional.class));
        convertibleTypes.add(new GenericConverter.ConvertiblePair(Object.class, Optional.class));
        return convertibleTypes;
    }

    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (targetType.getResolvableType().hasGenerics()) {
            return this.conversionService.canConvert(sourceType, new GenericTypeDescriptor(targetType));
        }
        return true;
    }

    @Override
    public Object convert(@Nullable Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (source == null) {
            return Optional.empty();
        }
        if (source instanceof Optional) {
            return source;
        }
        if (targetType.getResolvableType().hasGenerics()) {
            Object target = this.conversionService.convert(source, sourceType, new GenericTypeDescriptor(targetType));
            if (target == null || target.getClass().isArray() && Array.getLength(target) == 0 || target instanceof Collection && ((Collection)target).isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(target);
        }
        return Optional.of(source);
    }

    private static class GenericTypeDescriptor
    extends TypeDescriptor {
        public GenericTypeDescriptor(TypeDescriptor typeDescriptor) {
            super(typeDescriptor.getResolvableType().getGeneric(new int[0]), null, typeDescriptor.getAnnotations());
        }
    }
}

