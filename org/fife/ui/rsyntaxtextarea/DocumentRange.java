/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

public class DocumentRange
implements Comparable<DocumentRange> {
    private int startOffs;
    private int endOffs;

    public DocumentRange(int startOffs, int endOffs) {
        this.set(startOffs, endOffs);
    }

    @Override
    public int compareTo(DocumentRange other) {
        if (other == null) {
            return 1;
        }
        int diff = this.startOffs - other.startOffs;
        if (diff != 0) {
            return diff;
        }
        return this.endOffs - other.endOffs;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof DocumentRange) {
            return this.compareTo((DocumentRange)other) == 0;
        }
        return false;
    }

    public int getEndOffset() {
        return this.endOffs;
    }

    public int getStartOffset() {
        return this.startOffs;
    }

    public int hashCode() {
        return this.startOffs + this.endOffs;
    }

    public boolean isZeroLength() {
        return this.startOffs == this.endOffs;
    }

    public void set(int start, int end) {
        if (start < 0 || end < 0) {
            throw new IllegalArgumentException("start and end must be >= 0 (" + start + "-" + end + ")");
        }
        if (end < start) {
            throw new IllegalArgumentException("'end' cannot be less than 'start' (" + start + "-" + end + ")");
        }
        this.startOffs = start;
        this.endOffs = end;
    }

    public String toString() {
        return "[DocumentRange: " + this.startOffs + "-" + this.endOffs + "]";
    }

    public DocumentRange translate(int amount) {
        this.startOffs += amount;
        this.endOffs += amount;
        return this;
    }
}

