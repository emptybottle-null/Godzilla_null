/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

public interface ConditionalFormattingThreshold {
    public RangeType getRangeType();

    public void setRangeType(RangeType var1);

    public String getFormula();

    public void setFormula(String var1);

    public Double getValue();

    public void setValue(Double var1);

    public static enum RangeType {
        NUMBER(1, "num"),
        MIN(2, "min"),
        MAX(3, "max"),
        PERCENT(4, "percent"),
        PERCENTILE(5, "percentile"),
        UNALLOCATED(6, null),
        FORMULA(7, "formula");

        public final int id;
        public final String name;

        public String toString() {
            return this.id + " - " + this.name;
        }

        public static RangeType byId(int id) {
            return RangeType.values()[id - 1];
        }

        public static RangeType byName(String name) {
            for (RangeType t : RangeType.values()) {
                if (!t.name.equals(name)) continue;
                return t;
            }
            return null;
        }

        private RangeType(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}

