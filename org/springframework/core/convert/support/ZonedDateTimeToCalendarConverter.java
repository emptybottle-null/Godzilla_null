/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.convert.support;

import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.springframework.core.convert.converter.Converter;

final class ZonedDateTimeToCalendarConverter
implements Converter<ZonedDateTime, Calendar> {
    ZonedDateTimeToCalendarConverter() {
    }

    @Override
    public Calendar convert(ZonedDateTime source) {
        return GregorianCalendar.from(source);
    }
}

