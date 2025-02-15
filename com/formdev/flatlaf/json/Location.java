/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.json;

public class Location {
    public final int offset;
    public final int line;
    public final int column;

    Location(int offset, int line, int column) {
        this.offset = offset;
        this.column = column;
        this.line = line;
    }

    public String toString() {
        return this.line + ":" + this.column;
    }

    public int hashCode() {
        return this.offset;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Location other = (Location)obj;
        return this.offset == other.offset && this.column == other.column && this.line == other.line;
    }
}

