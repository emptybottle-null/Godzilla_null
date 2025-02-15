/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.utils;

import com.github.javaparser.utils.CodeGenerationUtils;
import java.util.Objects;

public class Pair<A, B> {
    public final A a;
    public final B b;

    public Pair(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Pair pair = (Pair)o;
        if (!Objects.equals(this.a, pair.a)) {
            return false;
        }
        return Objects.equals(this.b, pair.b);
    }

    public int hashCode() {
        int result = this.a != null ? this.a.hashCode() : 0;
        return 31 * result + (this.b != null ? this.b.hashCode() : 0);
    }

    public String toString() {
        return CodeGenerationUtils.f("<%s, %s>", this.a, this.b);
    }
}

