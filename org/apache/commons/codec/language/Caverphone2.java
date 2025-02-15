/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.codec.language;

import java.util.Locale;
import org.apache.commons.codec.language.AbstractCaverphone;

public class Caverphone2
extends AbstractCaverphone {
    private static final String TEN_1 = "1111111111";

    @Override
    public String encode(String source) {
        String txt = source;
        if (txt == null || txt.length() == 0) {
            return TEN_1;
        }
        txt = txt.toLowerCase(Locale.ENGLISH);
        txt = txt.replaceAll("[^a-z]", "");
        txt = txt.replaceAll("e$", "");
        txt = txt.replaceAll("^cough", "cou2f");
        txt = txt.replaceAll("^rough", "rou2f");
        txt = txt.replaceAll("^tough", "tou2f");
        txt = txt.replaceAll("^enough", "enou2f");
        txt = txt.replaceAll("^trough", "trou2f");
        txt = txt.replaceAll("^gn", "2n");
        txt = txt.replaceAll("mb$", "m2");
        txt = txt.replaceAll("cq", "2q");
        txt = txt.replaceAll("ci", "si");
        txt = txt.replaceAll("ce", "se");
        txt = txt.replaceAll("cy", "sy");
        txt = txt.replaceAll("tch", "2ch");
        txt = txt.replaceAll("c", "k");
        txt = txt.replaceAll("q", "k");
        txt = txt.replaceAll("x", "k");
        txt = txt.replaceAll("v", "f");
        txt = txt.replaceAll("dg", "2g");
        txt = txt.replaceAll("tio", "sio");
        txt = txt.replaceAll("tia", "sia");
        txt = txt.replaceAll("d", "t");
        txt = txt.replaceAll("ph", "fh");
        txt = txt.replaceAll("b", "p");
        txt = txt.replaceAll("sh", "s2");
        txt = txt.replaceAll("z", "s");
        txt = txt.replaceAll("^[aeiou]", "A");
        txt = txt.replaceAll("[aeiou]", "3");
        txt = txt.replaceAll("j", "y");
        txt = txt.replaceAll("^y3", "Y3");
        txt = txt.replaceAll("^y", "A");
        txt = txt.replaceAll("y", "3");
        txt = txt.replaceAll("3gh3", "3kh3");
        txt = txt.replaceAll("gh", "22");
        txt = txt.replaceAll("g", "k");
        txt = txt.replaceAll("s+", "S");
        txt = txt.replaceAll("t+", "T");
        txt = txt.replaceAll("p+", "P");
        txt = txt.replaceAll("k+", "K");
        txt = txt.replaceAll("f+", "F");
        txt = txt.replaceAll("m+", "M");
        txt = txt.replaceAll("n+", "N");
        txt = txt.replaceAll("w3", "W3");
        txt = txt.replaceAll("wh3", "Wh3");
        txt = txt.replaceAll("w$", "3");
        txt = txt.replaceAll("w", "2");
        txt = txt.replaceAll("^h", "A");
        txt = txt.replaceAll("h", "2");
        txt = txt.replaceAll("r3", "R3");
        txt = txt.replaceAll("r$", "3");
        txt = txt.replaceAll("r", "2");
        txt = txt.replaceAll("l3", "L3");
        txt = txt.replaceAll("l$", "3");
        txt = txt.replaceAll("l", "2");
        txt = txt.replaceAll("2", "");
        txt = txt.replaceAll("3$", "A");
        txt = txt.replaceAll("3", "");
        txt = txt + TEN_1;
        return txt.substring(0, TEN_1.length());
    }
}

