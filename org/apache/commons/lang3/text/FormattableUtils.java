/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.text;

import java.util.Formattable;
import java.util.Formatter;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.Validate;

@Deprecated
public class FormattableUtils {
    private static final String SIMPLEST_FORMAT = "%s";

    public static String toString(Formattable formattable) {
        return String.format(SIMPLEST_FORMAT, formattable);
    }

    public static Formatter append(CharSequence seq, Formatter formatter, int flags, int width, int precision) {
        return FormattableUtils.append(seq, formatter, flags, width, precision, ' ', null);
    }

    public static Formatter append(CharSequence seq, Formatter formatter, int flags, int width, int precision, char padChar) {
        return FormattableUtils.append(seq, formatter, flags, width, precision, padChar, null);
    }

    public static Formatter append(CharSequence seq, Formatter formatter, int flags, int width, int precision, CharSequence ellipsis) {
        return FormattableUtils.append(seq, formatter, flags, width, precision, ' ', ellipsis);
    }

    public static Formatter append(CharSequence seq, Formatter formatter, int flags, int width, int precision, char padChar, CharSequence ellipsis) {
        Validate.isTrue(ellipsis == null || precision < 0 || ellipsis.length() <= precision, "Specified ellipsis '%1$s' exceeds precision of %2$s", ellipsis, precision);
        StringBuilder buf = new StringBuilder(seq);
        if (precision >= 0 && precision < seq.length()) {
            CharSequence _ellipsis = ObjectUtils.defaultIfNull(ellipsis, "");
            buf.replace(precision - _ellipsis.length(), seq.length(), _ellipsis.toString());
        }
        boolean leftJustify = (flags & 1) == 1;
        for (int i = buf.length(); i < width; ++i) {
            buf.insert(leftJustify ? i : 0, padChar);
        }
        formatter.format(buf.toString(), new Object[0]);
        return formatter;
    }
}

