/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.format;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.ss.format.CellFormatPart;
import org.apache.poi.ss.format.CellFormatType;
import org.apache.poi.ss.format.CellFormatter;

public class CellElapsedFormatter
extends CellFormatter {
    private final List<TimeSpec> specs = new ArrayList<TimeSpec>();
    private TimeSpec topmost;
    private final String printfFmt;
    private static final Pattern PERCENTS = Pattern.compile("%");
    private static final double HOUR__FACTOR = 0.041666666666666664;
    private static final double MIN__FACTOR = 6.944444444444444E-4;
    private static final double SEC__FACTOR = 1.1574074074074073E-5;

    public CellElapsedFormatter(String pattern) {
        super(pattern);
        StringBuffer desc = CellFormatPart.parseFormat(pattern, CellFormatType.ELAPSED, new ElapsedPartHandler());
        ListIterator<TimeSpec> it = this.specs.listIterator(this.specs.size());
        while (it.hasPrevious()) {
            TimeSpec spec = it.previous();
            desc.replace(spec.pos, spec.pos + spec.len, "%0" + spec.len + "d");
            if (spec.type == this.topmost.type) continue;
            spec.modBy = CellElapsedFormatter.modFor(spec.type, spec.len);
        }
        this.printfFmt = desc.toString();
    }

    private TimeSpec assignSpec(char type, int pos, int len) {
        TimeSpec spec = new TimeSpec(type, pos, len, CellElapsedFormatter.factorFor(type, len));
        this.specs.add(spec);
        return spec;
    }

    private static double factorFor(char type, int len) {
        switch (type) {
            case 'h': {
                return 0.041666666666666664;
            }
            case 'm': {
                return 6.944444444444444E-4;
            }
            case 's': {
                return 1.1574074074074073E-5;
            }
            case '0': {
                return 1.1574074074074073E-5 / Math.pow(10.0, len);
            }
        }
        throw new IllegalArgumentException("Uknown elapsed time spec: " + type);
    }

    private static double modFor(char type, int len) {
        switch (type) {
            case 'h': {
                return 24.0;
            }
            case 'm': 
            case 's': {
                return 60.0;
            }
            case '0': {
                return Math.pow(10.0, len);
            }
        }
        throw new IllegalArgumentException("Uknown elapsed time spec: " + type);
    }

    @Override
    public void formatValue(StringBuffer toAppendTo, Object value) {
        double elapsed = ((Number)value).doubleValue();
        if (elapsed < 0.0) {
            toAppendTo.append('-');
            elapsed = -elapsed;
        }
        Object[] parts = new Long[this.specs.size()];
        for (int i = 0; i < this.specs.size(); ++i) {
            parts[i] = this.specs.get(i).valueFor(elapsed);
        }
        try (Formatter formatter = new Formatter(toAppendTo, Locale.ROOT);){
            formatter.format(this.printfFmt, parts);
        }
    }

    @Override
    public void simpleValue(StringBuffer toAppendTo, Object value) {
        this.formatValue(toAppendTo, value);
    }

    private class ElapsedPartHandler
    implements CellFormatPart.PartHandler {
        private ElapsedPartHandler() {
        }

        @Override
        public String handlePart(Matcher m, String part, CellFormatType type, StringBuffer desc) {
            int pos = desc.length();
            char firstCh = part.charAt(0);
            switch (firstCh) {
                case '[': {
                    if (part.length() < 3) break;
                    if (CellElapsedFormatter.this.topmost != null) {
                        throw new IllegalArgumentException("Duplicate '[' times in format");
                    }
                    part = part.toLowerCase(Locale.ROOT);
                    int specLen = part.length() - 2;
                    CellElapsedFormatter.this.topmost = CellElapsedFormatter.this.assignSpec(part.charAt(1), pos, specLen);
                    return part.substring(1, 1 + specLen);
                }
                case '0': 
                case 'h': 
                case 'm': 
                case 's': {
                    part = part.toLowerCase(Locale.ROOT);
                    CellElapsedFormatter.this.assignSpec(part.charAt(0), pos, part.length());
                    return part;
                }
                case '\n': {
                    return "%n";
                }
                case '\"': {
                    part = part.substring(1, part.length() - 1);
                    break;
                }
                case '\\': {
                    part = part.substring(1);
                    break;
                }
                case '*': {
                    if (part.length() <= 1) break;
                    part = CellFormatPart.expandChar(part);
                    break;
                }
                case '_': {
                    return null;
                }
            }
            return PERCENTS.matcher(part).replaceAll("%%");
        }
    }

    private static class TimeSpec {
        final char type;
        final int pos;
        final int len;
        final double factor;
        double modBy;

        public TimeSpec(char type, int pos, int len, double factor) {
            this.type = type;
            this.pos = pos;
            this.len = len;
            this.factor = factor;
            this.modBy = 0.0;
        }

        public long valueFor(double elapsed) {
            double val = this.modBy == 0.0 ? elapsed / this.factor : elapsed / this.factor % this.modBy;
            if (this.type == '0') {
                return Math.round(val);
            }
            return (long)val;
        }
    }
}

