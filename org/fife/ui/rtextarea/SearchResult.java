/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rtextarea;

import org.fife.ui.rsyntaxtextarea.DocumentRange;

public class SearchResult
implements Comparable<SearchResult> {
    private DocumentRange matchRange;
    private int count;
    private int markedCount;
    private boolean wrapped;

    public SearchResult() {
        this(null, 0, 0);
    }

    public SearchResult(DocumentRange range, int count, int markedCount) {
        this.matchRange = range;
        this.count = count;
        this.markedCount = markedCount;
    }

    @Override
    public int compareTo(SearchResult other) {
        if (other == null) {
            return 1;
        }
        if (other == this) {
            return 0;
        }
        int diff = this.count - other.count;
        if (diff != 0) {
            return diff;
        }
        diff = this.markedCount - other.markedCount;
        if (diff != 0) {
            return diff;
        }
        if (this.matchRange == null) {
            return other.matchRange == null ? 0 : -1;
        }
        return this.matchRange.compareTo(other.matchRange);
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof SearchResult) {
            return this.compareTo((SearchResult)other) == 0;
        }
        return false;
    }

    public int getCount() {
        return this.count;
    }

    public int getMarkedCount() {
        return this.markedCount;
    }

    public DocumentRange getMatchRange() {
        return this.matchRange;
    }

    public int hashCode() {
        int hash = this.count + this.markedCount;
        if (this.matchRange != null) {
            hash += this.matchRange.hashCode();
        }
        return hash;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setMarkedCount(int markedCount) {
        this.markedCount = markedCount;
    }

    public void setMatchRange(DocumentRange range) {
        this.matchRange = range;
    }

    public void setWrapped(boolean wrapped) {
        this.wrapped = wrapped;
    }

    public boolean isWrapped() {
        return this.wrapped;
    }

    public String toString() {
        return "[SearchResult: count=" + this.getCount() + ", markedCount=" + this.getMarkedCount() + ", matchRange=" + this.getMatchRange() + "]";
    }

    public boolean wasFound() {
        return this.getCount() > 0;
    }
}

