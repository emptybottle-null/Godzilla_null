/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.sevenz;

import java.util.Objects;
import org.apache.commons.compress.archivers.sevenz.Coders;
import org.apache.commons.compress.archivers.sevenz.SevenZMethod;

public class SevenZMethodConfiguration {
    private final SevenZMethod method;
    private final Object options;

    public SevenZMethodConfiguration(SevenZMethod method) {
        this(method, null);
    }

    public SevenZMethodConfiguration(SevenZMethod method, Object options) {
        this.method = method;
        this.options = options;
        if (options != null && !Coders.findByMethod(method).canAcceptOptions(options)) {
            throw new IllegalArgumentException("The " + (Object)((Object)method) + " method doesn't support options of type " + options.getClass());
        }
    }

    public SevenZMethod getMethod() {
        return this.method;
    }

    public Object getOptions() {
        return this.options;
    }

    public int hashCode() {
        return this.method == null ? 0 : this.method.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        SevenZMethodConfiguration other = (SevenZMethodConfiguration)obj;
        return Objects.equals((Object)this.method, (Object)other.method) && Objects.equals(this.options, other.options);
    }
}

