/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.format;

import java.util.Formatter;
import java.util.Locale;
import org.apache.poi.ss.format.CellFormatter;
import org.apache.poi.util.LocaleUtil;

public class CellGeneralFormatter
extends CellFormatter {
    public CellGeneralFormatter() {
        this(LocaleUtil.getUserLocale());
    }

    public CellGeneralFormatter(Locale locale) {
        super(locale, "General");
    }

    @Override
    public void formatValue(StringBuffer toAppendTo, Object value) {
        if (value instanceof Number) {
            String fmt;
            double val = ((Number)value).doubleValue();
            if (val == 0.0) {
                toAppendTo.append('0');
                return;
            }
            double exp = Math.log10(Math.abs(val));
            boolean stripZeros = true;
            if (exp > 10.0 || exp < -9.0) {
                fmt = "%1.5E";
            } else if ((double)((long)val) != val) {
                fmt = "%1.9f";
            } else {
                fmt = "%1.0f";
                stripZeros = false;
            }
            try (Formatter formatter = new Formatter(toAppendTo, this.locale);){
                formatter.format(this.locale, fmt, value);
            }
            if (stripZeros) {
                int removeFrom = fmt.endsWith("E") ? toAppendTo.lastIndexOf("E") - 1 : toAppendTo.length() - 1;
                while (toAppendTo.charAt(removeFrom) == '0') {
                    toAppendTo.deleteCharAt(removeFrom--);
                }
                if (toAppendTo.charAt(removeFrom) == '.') {
                    toAppendTo.deleteCharAt(removeFrom--);
                }
            }
        } else if (value instanceof Boolean) {
            toAppendTo.append(value.toString().toUpperCase(Locale.ROOT));
        } else {
            toAppendTo.append(value);
        }
    }

    @Override
    public void simpleValue(StringBuffer toAppendTo, Object value) {
        this.formatValue(toAppendTo, value);
    }
}

