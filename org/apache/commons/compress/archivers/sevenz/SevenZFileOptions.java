/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.sevenz;

public class SevenZFileOptions {
    private static final int DEFAUL_MEMORY_LIMIT_IN_KB = Integer.MAX_VALUE;
    private static final boolean DEFAULT_USE_DEFAULTNAME_FOR_UNNAMED_ENTRIES = false;
    private final int maxMemoryLimitInKb;
    private final boolean useDefaultNameForUnnamedEntries;
    public static final SevenZFileOptions DEFAULT = new SevenZFileOptions(Integer.MAX_VALUE, false);

    private SevenZFileOptions(int maxMemoryLimitInKb, boolean useDefaultNameForUnnamedEntries) {
        this.maxMemoryLimitInKb = maxMemoryLimitInKb;
        this.useDefaultNameForUnnamedEntries = useDefaultNameForUnnamedEntries;
    }

    public static Builder builder() {
        return new Builder();
    }

    public int getMaxMemoryLimitInKb() {
        return this.maxMemoryLimitInKb;
    }

    public boolean getUseDefaultNameForUnnamedEntries() {
        return this.useDefaultNameForUnnamedEntries;
    }

    public static class Builder {
        private int maxMemoryLimitInKb = Integer.MAX_VALUE;
        private boolean useDefaultNameForUnnamedEntries = false;

        public Builder withMaxMemoryLimitInKb(int maxMemoryLimitInKb) {
            this.maxMemoryLimitInKb = maxMemoryLimitInKb;
            return this;
        }

        public Builder withUseDefaultNameForUnnamedEntries(boolean useDefaultNameForUnnamedEntries) {
            this.useDefaultNameForUnnamedEntries = useDefaultNameForUnnamedEntries;
            return this;
        }

        public SevenZFileOptions build() {
            return new SevenZFileOptions(this.maxMemoryLimitInKb, this.useDefaultNameForUnnamedEntries);
        }
    }
}

