/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.pattern;

public abstract class PatternConverter {
    private final String name;
    private final String style;

    protected PatternConverter(String name, String style) {
        this.name = name;
        this.style = style;
    }

    public abstract void format(Object var1, StringBuffer var2);

    public final String getName() {
        return this.name;
    }

    public String getStyleClass(Object e) {
        return this.style;
    }
}

