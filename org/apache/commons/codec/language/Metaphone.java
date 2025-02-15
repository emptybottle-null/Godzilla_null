/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.codec.language;

import java.util.Locale;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;

public class Metaphone
implements StringEncoder {
    private static final String VOWELS = "AEIOU";
    private static final String FRONTV = "EIY";
    private static final String VARSON = "CSPTG";
    private int maxCodeLen = 4;

    public String metaphone(String txt) {
        int txtLength;
        boolean hard = false;
        if (txt == null || (txtLength = txt.length()) == 0) {
            return "";
        }
        if (txtLength == 1) {
            return txt.toUpperCase(Locale.ENGLISH);
        }
        char[] inwd = txt.toUpperCase(Locale.ENGLISH).toCharArray();
        StringBuilder local = new StringBuilder(40);
        StringBuilder code = new StringBuilder(10);
        switch (inwd[0]) {
            case 'G': 
            case 'K': 
            case 'P': {
                if (inwd[1] == 'N') {
                    local.append(inwd, 1, inwd.length - 1);
                    break;
                }
                local.append(inwd);
                break;
            }
            case 'A': {
                if (inwd[1] == 'E') {
                    local.append(inwd, 1, inwd.length - 1);
                    break;
                }
                local.append(inwd);
                break;
            }
            case 'W': {
                if (inwd[1] == 'R') {
                    local.append(inwd, 1, inwd.length - 1);
                    break;
                }
                if (inwd[1] == 'H') {
                    local.append(inwd, 1, inwd.length - 1);
                    local.setCharAt(0, 'W');
                    break;
                }
                local.append(inwd);
                break;
            }
            case 'X': {
                inwd[0] = 83;
                local.append(inwd);
                break;
            }
            default: {
                local.append(inwd);
            }
        }
        int wdsz = local.length();
        int n = 0;
        while (code.length() < this.getMaxCodeLen() && n < wdsz) {
            char symb = local.charAt(n);
            if (symb != 'C' && this.isPreviousChar(local, n, symb)) {
                ++n;
            } else {
                switch (symb) {
                    case 'A': 
                    case 'E': 
                    case 'I': 
                    case 'O': 
                    case 'U': {
                        if (n != 0) break;
                        code.append(symb);
                        break;
                    }
                    case 'B': {
                        if (this.isPreviousChar(local, n, 'M') && this.isLastChar(wdsz, n)) break;
                        code.append(symb);
                        break;
                    }
                    case 'C': {
                        if (this.isPreviousChar(local, n, 'S') && !this.isLastChar(wdsz, n) && FRONTV.indexOf(local.charAt(n + 1)) >= 0) break;
                        if (this.regionMatch(local, n, "CIA")) {
                            code.append('X');
                            break;
                        }
                        if (!this.isLastChar(wdsz, n) && FRONTV.indexOf(local.charAt(n + 1)) >= 0) {
                            code.append('S');
                            break;
                        }
                        if (this.isPreviousChar(local, n, 'S') && this.isNextChar(local, n, 'H')) {
                            code.append('K');
                            break;
                        }
                        if (this.isNextChar(local, n, 'H')) {
                            if (n == 0 && wdsz >= 3 && this.isVowel(local, 2)) {
                                code.append('K');
                                break;
                            }
                            code.append('X');
                            break;
                        }
                        code.append('K');
                        break;
                    }
                    case 'D': {
                        if (!this.isLastChar(wdsz, n + 1) && this.isNextChar(local, n, 'G') && FRONTV.indexOf(local.charAt(n + 2)) >= 0) {
                            code.append('J');
                            n += 2;
                            break;
                        }
                        code.append('T');
                        break;
                    }
                    case 'G': {
                        if (this.isLastChar(wdsz, n + 1) && this.isNextChar(local, n, 'H') || !this.isLastChar(wdsz, n + 1) && this.isNextChar(local, n, 'H') && !this.isVowel(local, n + 2) || n > 0 && (this.regionMatch(local, n, "GN") || this.regionMatch(local, n, "GNED"))) break;
                        hard = this.isPreviousChar(local, n, 'G');
                        if (!this.isLastChar(wdsz, n) && FRONTV.indexOf(local.charAt(n + 1)) >= 0 && !hard) {
                            code.append('J');
                            break;
                        }
                        code.append('K');
                        break;
                    }
                    case 'H': {
                        if (this.isLastChar(wdsz, n) || n > 0 && VARSON.indexOf(local.charAt(n - 1)) >= 0 || !this.isVowel(local, n + 1)) break;
                        code.append('H');
                        break;
                    }
                    case 'F': 
                    case 'J': 
                    case 'L': 
                    case 'M': 
                    case 'N': 
                    case 'R': {
                        code.append(symb);
                        break;
                    }
                    case 'K': {
                        if (n > 0) {
                            if (this.isPreviousChar(local, n, 'C')) break;
                            code.append(symb);
                            break;
                        }
                        code.append(symb);
                        break;
                    }
                    case 'P': {
                        if (this.isNextChar(local, n, 'H')) {
                            code.append('F');
                            break;
                        }
                        code.append(symb);
                        break;
                    }
                    case 'Q': {
                        code.append('K');
                        break;
                    }
                    case 'S': {
                        if (this.regionMatch(local, n, "SH") || this.regionMatch(local, n, "SIO") || this.regionMatch(local, n, "SIA")) {
                            code.append('X');
                            break;
                        }
                        code.append('S');
                        break;
                    }
                    case 'T': {
                        if (this.regionMatch(local, n, "TIA") || this.regionMatch(local, n, "TIO")) {
                            code.append('X');
                            break;
                        }
                        if (this.regionMatch(local, n, "TCH")) break;
                        if (this.regionMatch(local, n, "TH")) {
                            code.append('0');
                            break;
                        }
                        code.append('T');
                        break;
                    }
                    case 'V': {
                        code.append('F');
                        break;
                    }
                    case 'W': 
                    case 'Y': {
                        if (this.isLastChar(wdsz, n) || !this.isVowel(local, n + 1)) break;
                        code.append(symb);
                        break;
                    }
                    case 'X': {
                        code.append('K');
                        code.append('S');
                        break;
                    }
                    case 'Z': {
                        code.append('S');
                        break;
                    }
                }
                ++n;
            }
            if (code.length() <= this.getMaxCodeLen()) continue;
            code.setLength(this.getMaxCodeLen());
        }
        return code.toString();
    }

    private boolean isVowel(StringBuilder string, int index) {
        return VOWELS.indexOf(string.charAt(index)) >= 0;
    }

    private boolean isPreviousChar(StringBuilder string, int index, char c) {
        boolean matches = false;
        if (index > 0 && index < string.length()) {
            matches = string.charAt(index - 1) == c;
        }
        return matches;
    }

    private boolean isNextChar(StringBuilder string, int index, char c) {
        boolean matches = false;
        if (index >= 0 && index < string.length() - 1) {
            matches = string.charAt(index + 1) == c;
        }
        return matches;
    }

    private boolean regionMatch(StringBuilder string, int index, String test) {
        boolean matches = false;
        if (index >= 0 && index + test.length() - 1 < string.length()) {
            String substring = string.substring(index, index + test.length());
            matches = substring.equals(test);
        }
        return matches;
    }

    private boolean isLastChar(int wdsz, int n) {
        return n + 1 == wdsz;
    }

    @Override
    public Object encode(Object obj) throws EncoderException {
        if (!(obj instanceof String)) {
            throw new EncoderException("Parameter supplied to Metaphone encode is not of type java.lang.String");
        }
        return this.metaphone((String)obj);
    }

    @Override
    public String encode(String str) {
        return this.metaphone(str);
    }

    public boolean isMetaphoneEqual(String str1, String str2) {
        return this.metaphone(str1).equals(this.metaphone(str2));
    }

    public int getMaxCodeLen() {
        return this.maxCodeLen;
    }

    public void setMaxCodeLen(int maxCodeLen) {
        this.maxCodeLen = maxCodeLen;
    }
}

