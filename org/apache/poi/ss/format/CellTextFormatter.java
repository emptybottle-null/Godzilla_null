/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.format;

import java.util.Locale;
import org.apache.poi.ss.format.CellFormatPart;
import org.apache.poi.ss.format.CellFormatType;
import org.apache.poi.ss.format.CellFormatter;

public class CellTextFormatter
extends CellFormatter {
    private final int[] textPos;
    private final String desc;
    static final CellFormatter SIMPLE_TEXT = new CellTextFormatter("@");

    public CellTextFormatter(String format) {
        super(format);
        int[] numPlaces = new int[1];
        this.desc = CellFormatPart.parseFormat(format, CellFormatType.TEXT, (m, part, type, desc) -> {
            if (part.equals("@")) {
                numPlaces[0] = numPlaces[0] + 1;
                return "\u0000";
            }
            return null;
        }).toString();
        this.textPos = new int[numPlaces[0]];
        int pos = this.desc.length() - 1;
        for (int i = 0; i < this.textPos.length; ++i) {
            this.textPos[i] = this.desc.lastIndexOf("\u0000", pos);
            pos = this.textPos[i] - 1;
        }
    }

    @Override
    public void formatValue(StringBuffer toAppendTo, Object obj) {
        int start = toAppendTo.length();
        String text = obj.toString();
        if (obj instanceof Boolean) {
            text = text.toUpperCase(Locale.ROOT);
        }
        toAppendTo.append(this.desc);
        for (int textPo : this.textPos) {
            int pos = start + textPo;
            toAppendTo.replace(pos, pos + 1, text);
        }
    }

    @Override
    public void simpleValue(StringBuffer toAppendTo, Object value) {
        SIMPLE_TEXT.formatValue(toAppendTo, value);
    }
}

