/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.time;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.function.FailableBiConsumer;
import org.apache.commons.lang3.math.NumberUtils;

public class DurationUtils {
    static final Range<Long> LONG_TO_INT_RANGE = Range.between(NumberUtils.LONG_INT_MIN_VALUE, NumberUtils.LONG_INT_MAX_VALUE);

    public static <T extends Throwable> void accept(FailableBiConsumer<Long, Integer, T> consumer, Duration duration) throws T {
        if (consumer != null && duration != null) {
            consumer.accept(duration.toMillis(), DurationUtils.getNanosOfMiili(duration));
        }
    }

    public static int getNanosOfMiili(Duration duration) {
        return duration.getNano() % 1000000;
    }

    public static boolean isPositive(Duration duration) {
        return !duration.isNegative() && !duration.isZero();
    }

    static ChronoUnit toChronoUnit(TimeUnit timeUnit) {
        switch (Objects.requireNonNull(timeUnit)) {
            case NANOSECONDS: {
                return ChronoUnit.NANOS;
            }
            case MICROSECONDS: {
                return ChronoUnit.MICROS;
            }
            case MILLISECONDS: {
                return ChronoUnit.MILLIS;
            }
            case SECONDS: {
                return ChronoUnit.SECONDS;
            }
            case MINUTES: {
                return ChronoUnit.MINUTES;
            }
            case HOURS: {
                return ChronoUnit.HOURS;
            }
            case DAYS: {
                return ChronoUnit.DAYS;
            }
        }
        throw new IllegalArgumentException(timeUnit.toString());
    }

    public static Duration toDuration(long amount, TimeUnit timeUnit) {
        return Duration.of(amount, DurationUtils.toChronoUnit(timeUnit));
    }

    public static int toMillisInt(Duration duration) {
        Objects.requireNonNull(duration, "duration");
        return LONG_TO_INT_RANGE.fit(duration.toMillis()).intValue();
    }

    public static Duration zeroIfNull(Duration duration) {
        return ObjectUtils.defaultIfNull(duration, Duration.ZERO);
    }
}

