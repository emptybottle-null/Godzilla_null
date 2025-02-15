/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.helpers;

import org.apache.log4j.helpers.FormattingInfo;
import org.apache.log4j.spi.LoggingEvent;

public abstract class PatternConverter {
    public PatternConverter next;
    int min = -1;
    int max = Integer.MAX_VALUE;
    boolean leftAlign = false;
    static String[] SPACES = new String[]{" ", "  ", "    ", "        ", "                ", "                                "};

    protected PatternConverter() {
    }

    protected PatternConverter(FormattingInfo fi) {
        this.min = fi.min;
        this.max = fi.max;
        this.leftAlign = fi.leftAlign;
    }

    protected abstract String convert(LoggingEvent var1);

    public void format(StringBuffer sbuf, LoggingEvent e) {
        String s = this.convert(e);
        if (s == null) {
            if (0 < this.min) {
                this.spacePad(sbuf, this.min);
            }
            return;
        }
        int len = s.length();
        if (len > this.max) {
            sbuf.append(s.substring(len - this.max));
        } else if (len < this.min) {
            if (this.leftAlign) {
                sbuf.append(s);
                this.spacePad(sbuf, this.min - len);
            } else {
                this.spacePad(sbuf, this.min - len);
                sbuf.append(s);
            }
        } else {
            sbuf.append(s);
        }
    }

    public void spacePad(StringBuffer sbuf, int length) {
        while (length >= 32) {
            sbuf.append(SPACES[5]);
            length -= 32;
        }
        for (int i = 4; i >= 0; --i) {
            if ((length & 1 << i) == 0) continue;
            sbuf.append(SPACES[i]);
        }
    }
}

