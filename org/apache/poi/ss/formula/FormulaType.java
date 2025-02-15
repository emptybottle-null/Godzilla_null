/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula;

import org.apache.poi.util.Internal;

@Internal
public enum FormulaType {
    CELL(true),
    SHARED(true),
    ARRAY(false),
    CONDFORMAT(true),
    NAMEDRANGE(false),
    DATAVALIDATION_LIST(false);

    private final boolean isSingleValue;

    private FormulaType(boolean singleValue) {
        this.isSingleValue = singleValue;
    }

    public boolean isSingleValue() {
        return this.isSingleValue;
    }

    public static FormulaType forInt(int code) {
        if (code >= 0 && code < FormulaType.values().length) {
            return FormulaType.values()[code];
        }
        throw new IllegalArgumentException("Invalid FormulaType code: " + code);
    }
}

