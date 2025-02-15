/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.yaml.snakeyaml.tokens;

public final class TagTuple {
    private final String handle;
    private final String suffix;

    public TagTuple(String handle, String suffix) {
        if (suffix == null) {
            throw new NullPointerException("Suffix must be provided.");
        }
        this.handle = handle;
        this.suffix = suffix;
    }

    public String getHandle() {
        return this.handle;
    }

    public String getSuffix() {
        return this.suffix;
    }
}

