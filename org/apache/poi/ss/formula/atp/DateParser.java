/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.atp;

import java.util.Calendar;
import java.util.regex.Pattern;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.util.LocaleUtil;

public class DateParser {
    private DateParser() {
    }

    public static Calendar parseDate(String strVal) throws EvaluationException {
        int f2;
        int f1;
        int f0;
        String[] parts = Pattern.compile("/").split(strVal);
        if (parts.length != 3) {
            throw new EvaluationException(ErrorEval.VALUE_INVALID);
        }
        String part2 = parts[2];
        int spacePos = part2.indexOf(32);
        if (spacePos > 0) {
            part2 = part2.substring(0, spacePos);
        }
        try {
            f0 = Integer.parseInt(parts[0]);
            f1 = Integer.parseInt(parts[1]);
            f2 = Integer.parseInt(part2);
        } catch (NumberFormatException e) {
            throw new EvaluationException(ErrorEval.VALUE_INVALID);
        }
        if (f0 < 0 || f1 < 0 || f2 < 0 || f0 > 12 && f1 > 12 && f2 > 12) {
            throw new EvaluationException(ErrorEval.VALUE_INVALID);
        }
        if (f0 >= 1900 && f0 < 9999) {
            return DateParser.makeDate(f0, f1, f2);
        }
        throw new RuntimeException("Unable to determine date format for text '" + strVal + "'");
    }

    private static Calendar makeDate(int year, int month, int day) throws EvaluationException {
        if (month < 1 || month > 12) {
            throw new EvaluationException(ErrorEval.VALUE_INVALID);
        }
        Calendar cal = LocaleUtil.getLocaleCalendar(year, month - 1, 1, 0, 0, 0);
        if (day < 1 || day > cal.getActualMaximum(5)) {
            throw new EvaluationException(ErrorEval.VALUE_INVALID);
        }
        cal.set(5, day);
        return cal;
    }
}

