/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.helpers;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Date;

public class RelativeTimeDateFormat
extends DateFormat {
    private static final long serialVersionUID = 7055751607085611984L;
    protected final long startTime = System.currentTimeMillis();

    public StringBuffer format(Date date, StringBuffer sbuf, FieldPosition fieldPosition) {
        return sbuf.append(date.getTime() - this.startTime);
    }

    public Date parse(String s, ParsePosition pos) {
        return null;
    }
}

