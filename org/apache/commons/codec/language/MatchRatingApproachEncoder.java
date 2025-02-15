/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.codec.language;

import java.util.Locale;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;

public class MatchRatingApproachEncoder
implements StringEncoder {
    private static final String SPACE = " ";
    private static final String EMPTY = "";
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final int SIX = 6;
    private static final int SEVEN = 7;
    private static final int ELEVEN = 11;
    private static final int TWELVE = 12;
    private static final String PLAIN_ASCII = "AaEeIiOoUuAaEeIiOoUuYyAaEeIiOoUuYyAaOoNnAaEeIiOoUuYyAaCcOoUu";
    private static final String UNICODE = "\u00c0\u00e0\u00c8\u00e8\u00cc\u00ec\u00d2\u00f2\u00d9\u00f9\u00c1\u00e1\u00c9\u00e9\u00cd\u00ed\u00d3\u00f3\u00da\u00fa\u00dd\u00fd\u00c2\u00e2\u00ca\u00ea\u00ce\u00ee\u00d4\u00f4\u00db\u00fb\u0176\u0177\u00c3\u00e3\u00d5\u00f5\u00d1\u00f1\u00c4\u00e4\u00cb\u00eb\u00cf\u00ef\u00d6\u00f6\u00dc\u00fc\u0178\u00ff\u00c5\u00e5\u00c7\u00e7\u0150\u0151\u0170\u0171";
    private static final String[] DOUBLE_CONSONANT = new String[]{"BB", "CC", "DD", "FF", "GG", "HH", "JJ", "KK", "LL", "MM", "NN", "PP", "QQ", "RR", "SS", "TT", "VV", "WW", "XX", "YY", "ZZ"};

    String cleanName(String name) {
        String[] charsToTrim;
        String upperName = name.toUpperCase(Locale.ENGLISH);
        for (String str : charsToTrim = new String[]{"\\-", "[&]", "\\'", "\\.", "[\\,]"}) {
            upperName = upperName.replaceAll(str, EMPTY);
        }
        upperName = this.removeAccents(upperName);
        upperName = upperName.replaceAll("\\s+", EMPTY);
        return upperName;
    }

    @Override
    public final Object encode(Object pObject) throws EncoderException {
        if (!(pObject instanceof String)) {
            throw new EncoderException("Parameter supplied to Match Rating Approach encoder is not of type java.lang.String");
        }
        return this.encode((String)pObject);
    }

    @Override
    public final String encode(String name) {
        if (name == null || EMPTY.equalsIgnoreCase(name) || SPACE.equalsIgnoreCase(name) || name.length() == 1) {
            return EMPTY;
        }
        name = this.cleanName(name);
        name = this.removeVowels(name);
        name = this.removeDoubleConsonants(name);
        name = this.getFirst3Last3(name);
        return name;
    }

    String getFirst3Last3(String name) {
        int nameLength = name.length();
        if (nameLength > 6) {
            String firstThree = name.substring(0, 3);
            String lastThree = name.substring(nameLength - 3, nameLength);
            return firstThree + lastThree;
        }
        return name;
    }

    int getMinRating(int sumLength) {
        int minRating = 0;
        minRating = sumLength <= 4 ? 5 : (sumLength <= 7 ? 4 : (sumLength <= 11 ? 3 : (sumLength == 12 ? 2 : 1)));
        return minRating;
    }

    public boolean isEncodeEquals(String name1, String name2) {
        if (name1 == null || EMPTY.equalsIgnoreCase(name1) || SPACE.equalsIgnoreCase(name1)) {
            return false;
        }
        if (name2 == null || EMPTY.equalsIgnoreCase(name2) || SPACE.equalsIgnoreCase(name2)) {
            return false;
        }
        if (name1.length() == 1 || name2.length() == 1) {
            return false;
        }
        if (name1.equalsIgnoreCase(name2)) {
            return true;
        }
        name1 = this.cleanName(name1);
        name2 = this.cleanName(name2);
        name1 = this.removeVowels(name1);
        name2 = this.removeVowels(name2);
        name1 = this.removeDoubleConsonants(name1);
        name2 = this.removeDoubleConsonants(name2);
        name1 = this.getFirst3Last3(name1);
        name2 = this.getFirst3Last3(name2);
        if (Math.abs(name1.length() - name2.length()) >= 3) {
            return false;
        }
        int sumLength = Math.abs(name1.length() + name2.length());
        int minRating = 0;
        minRating = this.getMinRating(sumLength);
        int count = this.leftToRightThenRightToLeftProcessing(name1, name2);
        return count >= minRating;
    }

    boolean isVowel(String letter) {
        return letter.equalsIgnoreCase("E") || letter.equalsIgnoreCase("A") || letter.equalsIgnoreCase("O") || letter.equalsIgnoreCase("I") || letter.equalsIgnoreCase("U");
    }

    int leftToRightThenRightToLeftProcessing(String name1, String name2) {
        char[] name1Char = name1.toCharArray();
        char[] name2Char = name2.toCharArray();
        int name1Size = name1.length() - 1;
        int name2Size = name2.length() - 1;
        String name1LtRStart = EMPTY;
        String name1LtREnd = EMPTY;
        String name2RtLStart = EMPTY;
        String name2RtLEnd = EMPTY;
        for (int i = 0; i < name1Char.length && i <= name2Size; ++i) {
            name1LtRStart = name1.substring(i, i + 1);
            name1LtREnd = name1.substring(name1Size - i, name1Size - i + 1);
            name2RtLStart = name2.substring(i, i + 1);
            name2RtLEnd = name2.substring(name2Size - i, name2Size - i + 1);
            if (name1LtRStart.equals(name2RtLStart)) {
                name1Char[i] = 32;
                name2Char[i] = 32;
            }
            if (!name1LtREnd.equals(name2RtLEnd)) continue;
            name1Char[name1Size - i] = 32;
            name2Char[name2Size - i] = 32;
        }
        String strA = new String(name1Char).replaceAll("\\s+", EMPTY);
        String strB = new String(name2Char).replaceAll("\\s+", EMPTY);
        if (strA.length() > strB.length()) {
            return Math.abs(6 - strA.length());
        }
        return Math.abs(6 - strB.length());
    }

    String removeAccents(String accentedWord) {
        if (accentedWord == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int n = accentedWord.length();
        for (int i = 0; i < n; ++i) {
            char c = accentedWord.charAt(i);
            int pos = UNICODE.indexOf(c);
            if (pos > -1) {
                sb.append(PLAIN_ASCII.charAt(pos));
                continue;
            }
            sb.append(c);
        }
        return sb.toString();
    }

    String removeDoubleConsonants(String name) {
        String replacedName = name.toUpperCase(Locale.ENGLISH);
        for (String dc : DOUBLE_CONSONANT) {
            if (!replacedName.contains(dc)) continue;
            String singleLetter = dc.substring(0, 1);
            replacedName = replacedName.replace(dc, singleLetter);
        }
        return replacedName;
    }

    String removeVowels(String name) {
        String firstLetter = name.substring(0, 1);
        name = name.replaceAll("A", EMPTY);
        name = name.replaceAll("E", EMPTY);
        name = name.replaceAll("I", EMPTY);
        name = name.replaceAll("O", EMPTY);
        name = name.replaceAll("U", EMPTY);
        name = name.replaceAll("\\s{2,}\\b", SPACE);
        if (this.isVowel(firstLetter)) {
            return firstLetter + name;
        }
        return name;
    }
}

