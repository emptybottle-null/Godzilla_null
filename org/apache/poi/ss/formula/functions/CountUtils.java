/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.ThreeDEval;
import org.apache.poi.ss.formula.TwoDEval;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.ValueEval;

final class CountUtils {
    private CountUtils() {
    }

    public static int countMatchingCellsInArea(ThreeDEval areaEval, I_MatchPredicate criteriaPredicate) {
        int result = 0;
        int firstSheetIndex = areaEval.getFirstSheetIndex();
        int lastSheetIndex = areaEval.getLastSheetIndex();
        for (int sIx = firstSheetIndex; sIx <= lastSheetIndex; ++sIx) {
            int height = areaEval.getHeight();
            int width = areaEval.getWidth();
            for (int rrIx = 0; rrIx < height; ++rrIx) {
                for (int rcIx = 0; rcIx < width; ++rcIx) {
                    I_MatchAreaPredicate areaPredicate;
                    ValueEval ve = areaEval.getValue(sIx, rrIx, rcIx);
                    if (criteriaPredicate instanceof I_MatchAreaPredicate && !(areaPredicate = (I_MatchAreaPredicate)criteriaPredicate).matches(areaEval, rrIx, rcIx) || !criteriaPredicate.matches(ve)) continue;
                    ++result;
                }
            }
        }
        return result;
    }

    public static int countMatchingCellsInRef(RefEval refEval, I_MatchPredicate criteriaPredicate) {
        int result = 0;
        int firstSheetIndex = refEval.getFirstSheetIndex();
        int lastSheetIndex = refEval.getLastSheetIndex();
        for (int sIx = firstSheetIndex; sIx <= lastSheetIndex; ++sIx) {
            ValueEval ve = refEval.getInnerValueEval(sIx);
            if (!criteriaPredicate.matches(ve)) continue;
            ++result;
        }
        return result;
    }

    public static int countArg(ValueEval eval, I_MatchPredicate criteriaPredicate) {
        if (eval == null) {
            throw new IllegalArgumentException("eval must not be null");
        }
        if (eval instanceof ThreeDEval) {
            return CountUtils.countMatchingCellsInArea((ThreeDEval)eval, criteriaPredicate);
        }
        if (eval instanceof TwoDEval) {
            throw new IllegalArgumentException("Count requires 3D Evals, 2D ones aren't supported");
        }
        if (eval instanceof RefEval) {
            return CountUtils.countMatchingCellsInRef((RefEval)eval, criteriaPredicate);
        }
        return criteriaPredicate.matches(eval) ? 1 : 0;
    }

    public static interface I_MatchAreaPredicate
    extends I_MatchPredicate {
        public boolean matches(TwoDEval var1, int var2, int var3);
    }

    public static interface I_MatchPredicate {
        public boolean matches(ValueEval var1);
    }
}

