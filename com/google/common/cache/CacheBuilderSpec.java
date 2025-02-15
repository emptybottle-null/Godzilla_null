/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.cache;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LocalCache;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtIncompatible
public final class CacheBuilderSpec {
    private static final Splitter KEYS_SPLITTER = Splitter.on(',').trimResults();
    private static final Splitter KEY_VALUE_SPLITTER = Splitter.on('=').trimResults();
    private static final ImmutableMap<String, ValueParser> VALUE_PARSERS = ImmutableMap.builder().put("initialCapacity", new InitialCapacityParser()).put("maximumSize", (InitialCapacityParser)((Object)new MaximumSizeParser())).put("maximumWeight", (InitialCapacityParser)((Object)new MaximumWeightParser())).put("concurrencyLevel", (InitialCapacityParser)((Object)new ConcurrencyLevelParser())).put("weakKeys", (InitialCapacityParser)((Object)new KeyStrengthParser(LocalCache.Strength.WEAK))).put("softValues", (InitialCapacityParser)((Object)new ValueStrengthParser(LocalCache.Strength.SOFT))).put("weakValues", (InitialCapacityParser)((Object)new ValueStrengthParser(LocalCache.Strength.WEAK))).put("recordStats", (InitialCapacityParser)((Object)new RecordStatsParser())).put("expireAfterAccess", (InitialCapacityParser)((Object)new AccessDurationParser())).put("expireAfterWrite", (InitialCapacityParser)((Object)new WriteDurationParser())).put("refreshAfterWrite", (InitialCapacityParser)((Object)new RefreshDurationParser())).put("refreshInterval", (InitialCapacityParser)((Object)new RefreshDurationParser())).build();
    @VisibleForTesting
    @MonotonicNonNull Integer initialCapacity;
    @VisibleForTesting
    @MonotonicNonNull Long maximumSize;
    @VisibleForTesting
    @MonotonicNonNull Long maximumWeight;
    @VisibleForTesting
    @MonotonicNonNull Integer concurrencyLevel;
    @VisibleForTesting
     @MonotonicNonNull LocalCache.Strength keyStrength;
    @VisibleForTesting
     @MonotonicNonNull LocalCache.Strength valueStrength;
    @VisibleForTesting
    @MonotonicNonNull Boolean recordStats;
    @VisibleForTesting
    long writeExpirationDuration;
    @VisibleForTesting
    @MonotonicNonNull TimeUnit writeExpirationTimeUnit;
    @VisibleForTesting
    long accessExpirationDuration;
    @VisibleForTesting
    @MonotonicNonNull TimeUnit accessExpirationTimeUnit;
    @VisibleForTesting
    long refreshDuration;
    @VisibleForTesting
    @MonotonicNonNull TimeUnit refreshTimeUnit;
    private final String specification;

    private CacheBuilderSpec(String specification) {
        this.specification = specification;
    }

    public static CacheBuilderSpec parse(String cacheBuilderSpecification) {
        CacheBuilderSpec spec = new CacheBuilderSpec(cacheBuilderSpecification);
        if (!cacheBuilderSpecification.isEmpty()) {
            for (String keyValuePair : KEYS_SPLITTER.split(cacheBuilderSpecification)) {
                ImmutableList<String> keyAndValue = ImmutableList.copyOf(KEY_VALUE_SPLITTER.split(keyValuePair));
                Preconditions.checkArgument(!keyAndValue.isEmpty(), "blank key-value pair");
                Preconditions.checkArgument(keyAndValue.size() <= 2, "key-value pair %s with more than one equals sign", (Object)keyValuePair);
                String key = (String)keyAndValue.get(0);
                ValueParser valueParser = VALUE_PARSERS.get(key);
                Preconditions.checkArgument(valueParser != null, "unknown key %s", (Object)key);
                String value = keyAndValue.size() == 1 ? null : (String)keyAndValue.get(1);
                valueParser.parse(spec, key, value);
            }
        }
        return spec;
    }

    public static CacheBuilderSpec disableCaching() {
        return CacheBuilderSpec.parse("maximumSize=0");
    }

    CacheBuilder<Object, Object> toCacheBuilder() {
        CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder();
        if (this.initialCapacity != null) {
            builder.initialCapacity(this.initialCapacity);
        }
        if (this.maximumSize != null) {
            builder.maximumSize(this.maximumSize);
        }
        if (this.maximumWeight != null) {
            builder.maximumWeight(this.maximumWeight);
        }
        if (this.concurrencyLevel != null) {
            builder.concurrencyLevel(this.concurrencyLevel);
        }
        if (this.keyStrength != null) {
            switch (this.keyStrength) {
                case WEAK: {
                    builder.weakKeys();
                    break;
                }
                default: {
                    throw new AssertionError();
                }
            }
        }
        if (this.valueStrength != null) {
            switch (this.valueStrength) {
                case SOFT: {
                    builder.softValues();
                    break;
                }
                case WEAK: {
                    builder.weakValues();
                    break;
                }
                default: {
                    throw new AssertionError();
                }
            }
        }
        if (this.recordStats != null && this.recordStats.booleanValue()) {
            builder.recordStats();
        }
        if (this.writeExpirationTimeUnit != null) {
            builder.expireAfterWrite(this.writeExpirationDuration, this.writeExpirationTimeUnit);
        }
        if (this.accessExpirationTimeUnit != null) {
            builder.expireAfterAccess(this.accessExpirationDuration, this.accessExpirationTimeUnit);
        }
        if (this.refreshTimeUnit != null) {
            builder.refreshAfterWrite(this.refreshDuration, this.refreshTimeUnit);
        }
        return builder;
    }

    public String toParsableString() {
        return this.specification;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).addValue(this.toParsableString()).toString();
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.initialCapacity, this.maximumSize, this.maximumWeight, this.concurrencyLevel, this.keyStrength, this.valueStrength, this.recordStats, CacheBuilderSpec.durationInNanos(this.writeExpirationDuration, this.writeExpirationTimeUnit), CacheBuilderSpec.durationInNanos(this.accessExpirationDuration, this.accessExpirationTimeUnit), CacheBuilderSpec.durationInNanos(this.refreshDuration, this.refreshTimeUnit)});
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CacheBuilderSpec)) {
            return false;
        }
        CacheBuilderSpec that = (CacheBuilderSpec)obj;
        return Objects.equal(this.initialCapacity, that.initialCapacity) && Objects.equal(this.maximumSize, that.maximumSize) && Objects.equal(this.maximumWeight, that.maximumWeight) && Objects.equal(this.concurrencyLevel, that.concurrencyLevel) && Objects.equal((Object)this.keyStrength, (Object)that.keyStrength) && Objects.equal((Object)this.valueStrength, (Object)that.valueStrength) && Objects.equal(this.recordStats, that.recordStats) && Objects.equal(CacheBuilderSpec.durationInNanos(this.writeExpirationDuration, this.writeExpirationTimeUnit), CacheBuilderSpec.durationInNanos(that.writeExpirationDuration, that.writeExpirationTimeUnit)) && Objects.equal(CacheBuilderSpec.durationInNanos(this.accessExpirationDuration, this.accessExpirationTimeUnit), CacheBuilderSpec.durationInNanos(that.accessExpirationDuration, that.accessExpirationTimeUnit)) && Objects.equal(CacheBuilderSpec.durationInNanos(this.refreshDuration, this.refreshTimeUnit), CacheBuilderSpec.durationInNanos(that.refreshDuration, that.refreshTimeUnit));
    }

    private static @Nullable Long durationInNanos(long duration, @Nullable TimeUnit unit) {
        return unit == null ? null : Long.valueOf(unit.toNanos(duration));
    }

    private static String format(String format, Object ... args) {
        return String.format(Locale.ROOT, format, args);
    }

    static class RefreshDurationParser
    extends DurationParser {
        RefreshDurationParser() {
        }

        @Override
        protected void parseDuration(CacheBuilderSpec spec, long duration, TimeUnit unit) {
            Preconditions.checkArgument(spec.refreshTimeUnit == null, "refreshAfterWrite already set");
            spec.refreshDuration = duration;
            spec.refreshTimeUnit = unit;
        }
    }

    static class WriteDurationParser
    extends DurationParser {
        WriteDurationParser() {
        }

        @Override
        protected void parseDuration(CacheBuilderSpec spec, long duration, TimeUnit unit) {
            Preconditions.checkArgument(spec.writeExpirationTimeUnit == null, "expireAfterWrite already set");
            spec.writeExpirationDuration = duration;
            spec.writeExpirationTimeUnit = unit;
        }
    }

    static class AccessDurationParser
    extends DurationParser {
        AccessDurationParser() {
        }

        @Override
        protected void parseDuration(CacheBuilderSpec spec, long duration, TimeUnit unit) {
            Preconditions.checkArgument(spec.accessExpirationTimeUnit == null, "expireAfterAccess already set");
            spec.accessExpirationDuration = duration;
            spec.accessExpirationTimeUnit = unit;
        }
    }

    static abstract class DurationParser
    implements ValueParser {
        DurationParser() {
        }

        protected abstract void parseDuration(CacheBuilderSpec var1, long var2, TimeUnit var4);

        @Override
        public void parse(CacheBuilderSpec spec, String key, String value) {
            Preconditions.checkArgument(value != null && !value.isEmpty(), "value of key %s omitted", (Object)key);
            try {
                TimeUnit timeUnit;
                char lastChar = value.charAt(value.length() - 1);
                switch (lastChar) {
                    case 'd': {
                        timeUnit = TimeUnit.DAYS;
                        break;
                    }
                    case 'h': {
                        timeUnit = TimeUnit.HOURS;
                        break;
                    }
                    case 'm': {
                        timeUnit = TimeUnit.MINUTES;
                        break;
                    }
                    case 's': {
                        timeUnit = TimeUnit.SECONDS;
                        break;
                    }
                    default: {
                        throw new IllegalArgumentException(CacheBuilderSpec.format("key %s invalid format.  was %s, must end with one of [dDhHmMsS]", new Object[]{key, value}));
                    }
                }
                long duration = Long.parseLong(value.substring(0, value.length() - 1));
                this.parseDuration(spec, duration, timeUnit);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(CacheBuilderSpec.format("key %s value set to %s, must be integer", new Object[]{key, value}));
            }
        }
    }

    static class RecordStatsParser
    implements ValueParser {
        RecordStatsParser() {
        }

        @Override
        public void parse(CacheBuilderSpec spec, String key, @Nullable String value) {
            Preconditions.checkArgument(value == null, "recordStats does not take values");
            Preconditions.checkArgument(spec.recordStats == null, "recordStats already set");
            spec.recordStats = true;
        }
    }

    static class ValueStrengthParser
    implements ValueParser {
        private final LocalCache.Strength strength;

        public ValueStrengthParser(LocalCache.Strength strength) {
            this.strength = strength;
        }

        @Override
        public void parse(CacheBuilderSpec spec, String key, @Nullable String value) {
            Preconditions.checkArgument(value == null, "key %s does not take values", (Object)key);
            Preconditions.checkArgument(spec.valueStrength == null, "%s was already set to %s", (Object)key, (Object)spec.valueStrength);
            spec.valueStrength = this.strength;
        }
    }

    static class KeyStrengthParser
    implements ValueParser {
        private final LocalCache.Strength strength;

        public KeyStrengthParser(LocalCache.Strength strength) {
            this.strength = strength;
        }

        @Override
        public void parse(CacheBuilderSpec spec, String key, @Nullable String value) {
            Preconditions.checkArgument(value == null, "key %s does not take values", (Object)key);
            Preconditions.checkArgument(spec.keyStrength == null, "%s was already set to %s", (Object)key, (Object)spec.keyStrength);
            spec.keyStrength = this.strength;
        }
    }

    static class ConcurrencyLevelParser
    extends IntegerParser {
        ConcurrencyLevelParser() {
        }

        @Override
        protected void parseInteger(CacheBuilderSpec spec, int value) {
            Preconditions.checkArgument(spec.concurrencyLevel == null, "concurrency level was already set to ", (Object)spec.concurrencyLevel);
            spec.concurrencyLevel = value;
        }
    }

    static class MaximumWeightParser
    extends LongParser {
        MaximumWeightParser() {
        }

        @Override
        protected void parseLong(CacheBuilderSpec spec, long value) {
            Preconditions.checkArgument(spec.maximumWeight == null, "maximum weight was already set to ", (Object)spec.maximumWeight);
            Preconditions.checkArgument(spec.maximumSize == null, "maximum size was already set to ", (Object)spec.maximumSize);
            spec.maximumWeight = value;
        }
    }

    static class MaximumSizeParser
    extends LongParser {
        MaximumSizeParser() {
        }

        @Override
        protected void parseLong(CacheBuilderSpec spec, long value) {
            Preconditions.checkArgument(spec.maximumSize == null, "maximum size was already set to ", (Object)spec.maximumSize);
            Preconditions.checkArgument(spec.maximumWeight == null, "maximum weight was already set to ", (Object)spec.maximumWeight);
            spec.maximumSize = value;
        }
    }

    static class InitialCapacityParser
    extends IntegerParser {
        InitialCapacityParser() {
        }

        @Override
        protected void parseInteger(CacheBuilderSpec spec, int value) {
            Preconditions.checkArgument(spec.initialCapacity == null, "initial capacity was already set to ", (Object)spec.initialCapacity);
            spec.initialCapacity = value;
        }
    }

    static abstract class LongParser
    implements ValueParser {
        LongParser() {
        }

        protected abstract void parseLong(CacheBuilderSpec var1, long var2);

        @Override
        public void parse(CacheBuilderSpec spec, String key, String value) {
            Preconditions.checkArgument(value != null && !value.isEmpty(), "value of key %s omitted", (Object)key);
            try {
                this.parseLong(spec, Long.parseLong(value));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(CacheBuilderSpec.format("key %s value set to %s, must be integer", new Object[]{key, value}), e);
            }
        }
    }

    static abstract class IntegerParser
    implements ValueParser {
        IntegerParser() {
        }

        protected abstract void parseInteger(CacheBuilderSpec var1, int var2);

        @Override
        public void parse(CacheBuilderSpec spec, String key, String value) {
            Preconditions.checkArgument(value != null && !value.isEmpty(), "value of key %s omitted", (Object)key);
            try {
                this.parseInteger(spec, Integer.parseInt(value));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(CacheBuilderSpec.format("key %s value set to %s, must be integer", new Object[]{key, value}), e);
            }
        }
    }

    private static interface ValueParser {
        public void parse(CacheBuilderSpec var1, String var2, @Nullable String var3);
    }
}

