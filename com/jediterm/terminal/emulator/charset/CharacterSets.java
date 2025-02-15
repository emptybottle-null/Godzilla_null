/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.emulator.charset;

import com.jediterm.terminal.emulator.charset.GraphicSet;

public final class CharacterSets {
    private static final int C0_START = 0;
    private static final int C0_END = 31;
    private static final int C1_START = 128;
    private static final int C1_END = 159;
    private static final int GL_START = 32;
    private static final int GL_END = 127;
    private static final int GR_START = 160;
    private static final int GR_END = 255;
    public static final String[] ASCII_NAMES = new String[]{"<nul>", "<soh>", "<stx>", "<etx>", "<eot>", "<enq>", "<ack>", "<bell>", "\b", "\t", "\n", "<vt>", "<ff>", "\r", "<so>", "<si>", "<dle>", "<dc1>", "<dc2>", "<dc3>", "<dc4>", "<nak>", "<syn>", "<etb>", "<can>", "<em>", "<sub>", "<esc>", "<fs>", "<gs>", "<rs>", "<us>", " ", "!", "\"", "#", "$", "%", "&", "'", "(", ")", "*", "+", ",", "-", ".", "/", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ":", ";", "<", "=", ">", "?", "@", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "[", "\\", "]", "^", "_", "`", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "{", "|", "}", "~", "<del>"};
    public static final Object[][] C0_CHARS = new Object[][]{{0, "nul"}, {0, "soh"}, {0, "stx"}, {0, "etx"}, {0, "eot"}, {0, "enq"}, {0, "ack"}, {0, "bel"}, {8, "bs"}, {9, "ht"}, {10, "lf"}, {0, "vt"}, {0, "ff"}, {13, "cr"}, {0, "so"}, {0, "si"}, {0, "dle"}, {0, "dc1"}, {0, "dc2"}, {0, "dc3"}, {0, "dc4"}, {0, "nak"}, {0, "syn"}, {0, "etb"}, {0, "can"}, {0, "em"}, {0, "sub"}, {0, "esq"}, {0, "fs"}, {0, "gs"}, {0, "rs"}, {0, "us"}};
    public static final Object[][] C1_CHARS = new Object[][]{{0, null}, {0, null}, {0, null}, {0, null}, {0, "ind"}, {0, "nel"}, {0, "ssa"}, {0, "esa"}, {0, "hts"}, {0, "htj"}, {0, "vts"}, {0, "pld"}, {0, "plu"}, {0, "ri"}, {0, "ss2"}, {0, "ss3"}, {0, "dcs"}, {0, "pu1"}, {0, "pu2"}, {0, "sts"}, {0, "cch"}, {0, "mw"}, {0, "spa"}, {0, "epa"}, {0, null}, {0, null}, {0, null}, {0, "csi"}, {0, "st"}, {0, "osc"}, {0, "pm"}, {0, "apc"}};
    public static final Object[][] DEC_SPECIAL_CHARS = new Object[][]{{Character.valueOf('\u25c6'), null}, {Character.valueOf('\u2592'), null}, {Character.valueOf('\u2409'), null}, {Character.valueOf('\u240c'), null}, {Character.valueOf('\u240d'), null}, {Character.valueOf('\u240a'), null}, {Character.valueOf('\u00b0'), null}, {Character.valueOf('\u00b1'), null}, {Character.valueOf('\u2424'), null}, {Character.valueOf('\u240b'), null}, {Character.valueOf('\u2518'), Character.valueOf('\u251b')}, {Character.valueOf('\u2510'), Character.valueOf('\u2513')}, {Character.valueOf('\u250c'), Character.valueOf('\u250f')}, {Character.valueOf('\u2514'), Character.valueOf('\u2517')}, {Character.valueOf('\u253c'), Character.valueOf('\u254b')}, {Character.valueOf('\u23ba'), null}, {Character.valueOf('\u23bb'), null}, {Character.valueOf('\u2500'), Character.valueOf('\u2501')}, {Character.valueOf('\u23bc'), null}, {Character.valueOf('\u23bd'), null}, {Character.valueOf('\u251c'), Character.valueOf('\u2523')}, {Character.valueOf('\u2524'), Character.valueOf('\u252b')}, {Character.valueOf('\u2534'), Character.valueOf('\u253b')}, {Character.valueOf('\u252c'), Character.valueOf('\u2533')}, {Character.valueOf('\u2502'), Character.valueOf('\u2503')}, {Character.valueOf('\u2264'), null}, {Character.valueOf('\u2265'), null}, {Character.valueOf('\u03c0'), null}, {Character.valueOf('\u2260'), null}, {Character.valueOf('\u00a3'), null}, {Character.valueOf('\u00b7'), null}, {Character.valueOf(' '), null}};

    public static boolean isDecBoxChar(char c) {
        if (c < '\u2500' || c >= '\u2580') {
            return false;
        }
        for (Object[] o : DEC_SPECIAL_CHARS) {
            if (c != ((Character)o[0]).charValue()) continue;
            return true;
        }
        return false;
    }

    public static char getHeavyDecBoxChar(char c) {
        if (c < '\u2500' || c >= '\u2580') {
            return c;
        }
        for (Object[] o : DEC_SPECIAL_CHARS) {
            if (c != ((Character)o[0]).charValue()) continue;
            return o[1] != null ? ((Character)o[1]).charValue() : c;
        }
        return c;
    }

    private CharacterSets() {
    }

    public static char getChar(char original, GraphicSet gl, GraphicSet gr) {
        Object[] mapping = CharacterSets.getMapping(original, gl, gr);
        int ch = (Integer)mapping[0];
        if (ch > 0) {
            return (char)ch;
        }
        return '\u0000';
    }

    public static String getCharName(char original, GraphicSet gl, GraphicSet gr) {
        Object[] mapping = CharacterSets.getMapping(original, gl, gr);
        String name = (String)mapping[1];
        if (name == null) {
            name = String.format("<%d>", original);
        }
        return name;
    }

    private static Object[] getMapping(char original, GraphicSet gl, GraphicSet gr) {
        int mappedChar = original;
        if (original >= 0 && original <= 31) {
            int idx = original - '\u0000';
            return C0_CHARS[idx];
        }
        if (original >= 128 && original <= 159) {
            int idx = original - 128;
            return C1_CHARS[idx];
        }
        if (original >= 32 && original <= 127) {
            int idx = original - 32;
            mappedChar = gl.map((char)original, idx);
        }
        return new Object[]{mappedChar, null};
    }
}

