/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.util;

import org.jetbrains.annotations.NotNull;

public class Pair<A, B> {
    public final A first;
    public final B second;
    private static final Pair EMPTY = Pair.create(null, null);

    @NotNull
    public static <A, B> Pair<A, B> create(A first, B second) {
        return new Pair<A, B>(first, second);
    }

    public static <T> T getFirst(Pair<T, ?> pair) {
        return pair != null ? (T)pair.first : null;
    }

    public static <T> T getSecond(Pair<?, T> pair) {
        return pair != null ? (T)pair.second : null;
    }

    public static <A, B> Pair<A, B> empty() {
        return EMPTY;
    }

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public final A getFirst() {
        return this.first;
    }

    public final B getSecond() {
        return this.second;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Pair pair = (Pair)o;
        if (this.first != null ? !this.first.equals(pair.first) : pair.first != null) {
            return false;
        }
        return !(this.second != null ? !this.second.equals(pair.second) : pair.second != null);
    }

    public int hashCode() {
        int result = this.first != null ? this.first.hashCode() : 0;
        result = 31 * result + (this.second != null ? this.second.hashCode() : 0);
        return result;
    }

    public String toString() {
        return "<" + this.first + "," + this.second + ">";
    }
}

