/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula;

public interface IStabilityClassifier {
    public static final IStabilityClassifier TOTALLY_IMMUTABLE = new IStabilityClassifier(){

        @Override
        public boolean isCellFinal(int sheetIndex, int rowIndex, int columnIndex) {
            return true;
        }
    };

    public boolean isCellFinal(int var1, int var2, int var3);
}

