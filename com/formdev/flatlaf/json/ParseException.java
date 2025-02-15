/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.json;

import com.formdev.flatlaf.json.Location;

public class ParseException
extends RuntimeException {
    private final Location location;

    ParseException(String message, Location location) {
        super(message + " at " + location);
        this.location = location;
    }

    public Location getLocation() {
        return this.location;
    }

    @Deprecated
    public int getOffset() {
        return this.location.offset;
    }

    @Deprecated
    public int getLine() {
        return this.location.line;
    }

    @Deprecated
    public int getColumn() {
        return this.location.column;
    }
}

