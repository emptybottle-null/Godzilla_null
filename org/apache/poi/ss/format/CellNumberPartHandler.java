/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.format;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import org.apache.poi.ss.format.CellFormatPart;
import org.apache.poi.ss.format.CellFormatType;
import org.apache.poi.ss.format.CellNumberFormatter;
import org.apache.poi.util.Internal;

@Internal
public class CellNumberPartHandler
implements CellFormatPart.PartHandler {
    private char insertSignForExponent;
    private double scale = 1.0;
    private CellNumberFormatter.Special decimalPoint;
    private CellNumberFormatter.Special slash;
    private CellNumberFormatter.Special exponent;
    private CellNumberFormatter.Special numerator;
    private final List<CellNumberFormatter.Special> specials = new LinkedList<CellNumberFormatter.Special>();
    private boolean improperFraction;

    @Override
    public String handlePart(Matcher m, String part, CellFormatType type, StringBuffer descBuf) {
        int pos = descBuf.length();
        char firstCh = part.charAt(0);
        switch (firstCh) {
            case 'E': 
            case 'e': {
                if (this.exponent != null || this.specials.size() <= 0) break;
                this.exponent = new CellNumberFormatter.Special('.', pos);
                this.specials.add(this.exponent);
                this.insertSignForExponent = part.charAt(1);
                return part.substring(0, 1);
            }
            case '#': 
            case '0': 
            case '?': {
                if (this.insertSignForExponent != '\u0000') {
                    this.specials.add(new CellNumberFormatter.Special(this.insertSignForExponent, pos));
                    descBuf.append(this.insertSignForExponent);
                    this.insertSignForExponent = '\u0000';
                    ++pos;
                }
                for (int i = 0; i < part.length(); ++i) {
                    char ch = part.charAt(i);
                    this.specials.add(new CellNumberFormatter.Special(ch, pos + i));
                }
                break;
            }
            case '.': {
                if (this.decimalPoint != null || this.specials.size() <= 0) break;
                this.decimalPoint = new CellNumberFormatter.Special('.', pos);
                this.specials.add(this.decimalPoint);
                break;
            }
            case '/': {
                if (this.slash != null || this.specials.size() <= 0) break;
                this.numerator = this.previousNumber();
                this.improperFraction |= this.numerator == CellNumberPartHandler.firstDigit(this.specials);
                this.slash = new CellNumberFormatter.Special('.', pos);
                this.specials.add(this.slash);
                break;
            }
            case '%': {
                this.scale *= 100.0;
                break;
            }
            default: {
                return null;
            }
        }
        return part;
    }

    public double getScale() {
        return this.scale;
    }

    public CellNumberFormatter.Special getDecimalPoint() {
        return this.decimalPoint;
    }

    public CellNumberFormatter.Special getSlash() {
        return this.slash;
    }

    public CellNumberFormatter.Special getExponent() {
        return this.exponent;
    }

    public CellNumberFormatter.Special getNumerator() {
        return this.numerator;
    }

    public List<CellNumberFormatter.Special> getSpecials() {
        return this.specials;
    }

    public boolean isImproperFraction() {
        return this.improperFraction;
    }

    private CellNumberFormatter.Special previousNumber() {
        ListIterator<CellNumberFormatter.Special> it = this.specials.listIterator(this.specials.size());
        while (it.hasPrevious()) {
            CellNumberFormatter.Special s = it.previous();
            if (!CellNumberPartHandler.isDigitFmt(s)) continue;
            CellNumberFormatter.Special last = s;
            while (it.hasPrevious()) {
                s = it.previous();
                if (last.pos - s.pos > 1 || !CellNumberPartHandler.isDigitFmt(s)) break;
                last = s;
            }
            return last;
        }
        return null;
    }

    private static boolean isDigitFmt(CellNumberFormatter.Special s) {
        return s.ch == '0' || s.ch == '?' || s.ch == '#';
    }

    private static CellNumberFormatter.Special firstDigit(List<CellNumberFormatter.Special> specials) {
        for (CellNumberFormatter.Special s : specials) {
            if (!CellNumberPartHandler.isDigitFmt(s)) continue;
            return s;
        }
        return null;
    }
}

