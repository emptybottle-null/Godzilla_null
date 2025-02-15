/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.convert.support;

import java.nio.charset.Charset;
import java.util.Currency;
import java.util.Locale;
import java.util.UUID;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.core.convert.support.ArrayToArrayConverter;
import org.springframework.core.convert.support.ArrayToCollectionConverter;
import org.springframework.core.convert.support.ArrayToObjectConverter;
import org.springframework.core.convert.support.ArrayToStringConverter;
import org.springframework.core.convert.support.ByteBufferConverter;
import org.springframework.core.convert.support.CharacterToNumberFactory;
import org.springframework.core.convert.support.CollectionToArrayConverter;
import org.springframework.core.convert.support.CollectionToCollectionConverter;
import org.springframework.core.convert.support.CollectionToObjectConverter;
import org.springframework.core.convert.support.CollectionToStringConverter;
import org.springframework.core.convert.support.EnumToIntegerConverter;
import org.springframework.core.convert.support.EnumToStringConverter;
import org.springframework.core.convert.support.FallbackObjectToStringConverter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.core.convert.support.IdToEntityConverter;
import org.springframework.core.convert.support.IntegerToEnumConverterFactory;
import org.springframework.core.convert.support.MapToMapConverter;
import org.springframework.core.convert.support.NumberToCharacterConverter;
import org.springframework.core.convert.support.NumberToNumberConverterFactory;
import org.springframework.core.convert.support.ObjectToArrayConverter;
import org.springframework.core.convert.support.ObjectToCollectionConverter;
import org.springframework.core.convert.support.ObjectToObjectConverter;
import org.springframework.core.convert.support.ObjectToOptionalConverter;
import org.springframework.core.convert.support.ObjectToStringConverter;
import org.springframework.core.convert.support.PropertiesToStringConverter;
import org.springframework.core.convert.support.StreamConverter;
import org.springframework.core.convert.support.StringToArrayConverter;
import org.springframework.core.convert.support.StringToBooleanConverter;
import org.springframework.core.convert.support.StringToCharacterConverter;
import org.springframework.core.convert.support.StringToCharsetConverter;
import org.springframework.core.convert.support.StringToCollectionConverter;
import org.springframework.core.convert.support.StringToCurrencyConverter;
import org.springframework.core.convert.support.StringToEnumConverterFactory;
import org.springframework.core.convert.support.StringToLocaleConverter;
import org.springframework.core.convert.support.StringToNumberConverterFactory;
import org.springframework.core.convert.support.StringToPropertiesConverter;
import org.springframework.core.convert.support.StringToTimeZoneConverter;
import org.springframework.core.convert.support.StringToUUIDConverter;
import org.springframework.core.convert.support.ZoneIdToTimeZoneConverter;
import org.springframework.core.convert.support.ZonedDateTimeToCalendarConverter;
import org.springframework.lang.Nullable;

public class DefaultConversionService
extends GenericConversionService {
    @Nullable
    private static volatile DefaultConversionService sharedInstance;

    public DefaultConversionService() {
        DefaultConversionService.addDefaultConverters(this);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static ConversionService getSharedInstance() {
        DefaultConversionService cs = sharedInstance;
        if (cs != null) return cs;
        Class<DefaultConversionService> clazz = DefaultConversionService.class;
        synchronized (DefaultConversionService.class) {
            cs = sharedInstance;
            if (cs != null) return cs;
            sharedInstance = cs = new DefaultConversionService();
            // ** MonitorExit[var1_1] (shouldn't be in output)
            return cs;
        }
    }

    public static void addDefaultConverters(ConverterRegistry converterRegistry) {
        DefaultConversionService.addScalarConverters(converterRegistry);
        DefaultConversionService.addCollectionConverters(converterRegistry);
        converterRegistry.addConverter(new ByteBufferConverter((ConversionService)((Object)converterRegistry)));
        converterRegistry.addConverter(new StringToTimeZoneConverter());
        converterRegistry.addConverter(new ZoneIdToTimeZoneConverter());
        converterRegistry.addConverter(new ZonedDateTimeToCalendarConverter());
        converterRegistry.addConverter(new ObjectToObjectConverter());
        converterRegistry.addConverter(new IdToEntityConverter((ConversionService)((Object)converterRegistry)));
        converterRegistry.addConverter(new FallbackObjectToStringConverter());
        converterRegistry.addConverter(new ObjectToOptionalConverter((ConversionService)((Object)converterRegistry)));
    }

    public static void addCollectionConverters(ConverterRegistry converterRegistry) {
        ConversionService conversionService = (ConversionService)((Object)converterRegistry);
        converterRegistry.addConverter(new ArrayToCollectionConverter(conversionService));
        converterRegistry.addConverter(new CollectionToArrayConverter(conversionService));
        converterRegistry.addConverter(new ArrayToArrayConverter(conversionService));
        converterRegistry.addConverter(new CollectionToCollectionConverter(conversionService));
        converterRegistry.addConverter(new MapToMapConverter(conversionService));
        converterRegistry.addConverter(new ArrayToStringConverter(conversionService));
        converterRegistry.addConverter(new StringToArrayConverter(conversionService));
        converterRegistry.addConverter(new ArrayToObjectConverter(conversionService));
        converterRegistry.addConverter(new ObjectToArrayConverter(conversionService));
        converterRegistry.addConverter(new CollectionToStringConverter(conversionService));
        converterRegistry.addConverter(new StringToCollectionConverter(conversionService));
        converterRegistry.addConverter(new CollectionToObjectConverter(conversionService));
        converterRegistry.addConverter(new ObjectToCollectionConverter(conversionService));
        converterRegistry.addConverter(new StreamConverter(conversionService));
    }

    private static void addScalarConverters(ConverterRegistry converterRegistry) {
        converterRegistry.addConverterFactory(new NumberToNumberConverterFactory());
        converterRegistry.addConverterFactory(new StringToNumberConverterFactory());
        converterRegistry.addConverter(Number.class, String.class, new ObjectToStringConverter());
        converterRegistry.addConverter(new StringToCharacterConverter());
        converterRegistry.addConverter(Character.class, String.class, new ObjectToStringConverter());
        converterRegistry.addConverter(new NumberToCharacterConverter());
        converterRegistry.addConverterFactory(new CharacterToNumberFactory());
        converterRegistry.addConverter(new StringToBooleanConverter());
        converterRegistry.addConverter(Boolean.class, String.class, new ObjectToStringConverter());
        converterRegistry.addConverterFactory(new StringToEnumConverterFactory());
        converterRegistry.addConverter(new EnumToStringConverter((ConversionService)((Object)converterRegistry)));
        converterRegistry.addConverterFactory(new IntegerToEnumConverterFactory());
        converterRegistry.addConverter(new EnumToIntegerConverter((ConversionService)((Object)converterRegistry)));
        converterRegistry.addConverter(new StringToLocaleConverter());
        converterRegistry.addConverter(Locale.class, String.class, new ObjectToStringConverter());
        converterRegistry.addConverter(new StringToCharsetConverter());
        converterRegistry.addConverter(Charset.class, String.class, new ObjectToStringConverter());
        converterRegistry.addConverter(new StringToCurrencyConverter());
        converterRegistry.addConverter(Currency.class, String.class, new ObjectToStringConverter());
        converterRegistry.addConverter(new StringToPropertiesConverter());
        converterRegistry.addConverter(new PropertiesToStringConverter());
        converterRegistry.addConverter(new StringToUUIDConverter());
        converterRegistry.addConverter(UUID.class, String.class, new ObjectToStringConverter());
    }
}

