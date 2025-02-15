/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.convert.support;

import java.time.ZoneId;
import java.util.TimeZone;
import org.springframework.core.convert.converter.Converter;

final class ZoneIdToTimeZoneConverter
implements Converter<ZoneId, TimeZone> {
    ZoneIdToTimeZoneConverter() {
    }

    @Override
    public TimeZone convert(ZoneId source) {
        return TimeZone.getTimeZone(source);
    }
}

