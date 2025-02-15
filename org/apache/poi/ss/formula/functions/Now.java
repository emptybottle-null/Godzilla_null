/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import java.util.Date;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.Fixed0ArgFunction;
import org.apache.poi.ss.usermodel.DateUtil;

public final class Now
extends Fixed0ArgFunction {
    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex) {
        Date now = new Date(System.currentTimeMillis());
        return new NumberEval(DateUtil.getExcelDate(now));
    }
}

