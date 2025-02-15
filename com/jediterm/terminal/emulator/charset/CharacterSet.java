/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.emulator.charset;

import com.jediterm.terminal.emulator.charset.CharacterSets;

public enum CharacterSet {
    ASCII(new int[]{66}){

        @Override
        public int map(int index) {
            return -1;
        }
    }
    ,
    BRITISH(new int[]{65}){

        @Override
        public int map(int index) {
            if (index == 3) {
                return 163;
            }
            return -1;
        }
    }
    ,
    DANISH(new int[]{69, 54}){

        @Override
        public int map(int index) {
            switch (index) {
                case 32: {
                    return 196;
                }
                case 59: {
                    return 198;
                }
                case 60: {
                    return 216;
                }
                case 61: {
                    return 197;
                }
                case 62: {
                    return 220;
                }
                case 64: {
                    return 228;
                }
                case 91: {
                    return 230;
                }
                case 92: {
                    return 248;
                }
                case 93: {
                    return 229;
                }
                case 94: {
                    return 252;
                }
            }
            return -1;
        }
    }
    ,
    DEC_SPECIAL_GRAPHICS(new int[]{48, 50}){

        @Override
        public int map(int index) {
            if (index >= 64 && index < 96) {
                return ((Character)CharacterSets.DEC_SPECIAL_CHARS[index - 64][0]).charValue();
            }
            return -1;
        }
    }
    ,
    DEC_SUPPLEMENTAL(new int[]{85, 60}){

        @Override
        public int map(int index) {
            if (index >= 0 && index < 64) {
                return index + 160;
            }
            return -1;
        }
    }
    ,
    DUTCH(new int[]{52}){

        @Override
        public int map(int index) {
            switch (index) {
                case 3: {
                    return 163;
                }
                case 32: {
                    return 190;
                }
                case 59: {
                    return 307;
                }
                case 60: {
                    return 189;
                }
                case 61: {
                    return 124;
                }
                case 91: {
                    return 168;
                }
                case 92: {
                    return 402;
                }
                case 93: {
                    return 188;
                }
                case 94: {
                    return 180;
                }
            }
            return -1;
        }
    }
    ,
    FINNISH(new int[]{67, 53}){

        @Override
        public int map(int index) {
            switch (index) {
                case 59: {
                    return 196;
                }
                case 60: {
                    return 212;
                }
                case 61: {
                    return 197;
                }
                case 62: {
                    return 220;
                }
                case 64: {
                    return 233;
                }
                case 91: {
                    return 228;
                }
                case 92: {
                    return 246;
                }
                case 93: {
                    return 229;
                }
                case 94: {
                    return 252;
                }
            }
            return -1;
        }
    }
    ,
    FRENCH(new int[]{82}){

        @Override
        public int map(int index) {
            switch (index) {
                case 3: {
                    return 163;
                }
                case 32: {
                    return 224;
                }
                case 59: {
                    return 176;
                }
                case 60: {
                    return 231;
                }
                case 61: {
                    return 166;
                }
                case 91: {
                    return 233;
                }
                case 92: {
                    return 249;
                }
                case 93: {
                    return 232;
                }
                case 94: {
                    return 168;
                }
            }
            return -1;
        }
    }
    ,
    FRENCH_CANADIAN(new int[]{81}){

        @Override
        public int map(int index) {
            switch (index) {
                case 32: {
                    return 224;
                }
                case 59: {
                    return 226;
                }
                case 60: {
                    return 231;
                }
                case 61: {
                    return 234;
                }
                case 62: {
                    return 238;
                }
                case 91: {
                    return 233;
                }
                case 92: {
                    return 249;
                }
                case 93: {
                    return 232;
                }
                case 94: {
                    return 251;
                }
            }
            return -1;
        }
    }
    ,
    GERMAN(new int[]{75}){

        @Override
        public int map(int index) {
            switch (index) {
                case 32: {
                    return 167;
                }
                case 59: {
                    return 196;
                }
                case 60: {
                    return 214;
                }
                case 61: {
                    return 220;
                }
                case 91: {
                    return 228;
                }
                case 92: {
                    return 246;
                }
                case 93: {
                    return 252;
                }
                case 94: {
                    return 223;
                }
            }
            return -1;
        }
    }
    ,
    ITALIAN(new int[]{89}){

        @Override
        public int map(int index) {
            switch (index) {
                case 3: {
                    return 163;
                }
                case 32: {
                    return 167;
                }
                case 59: {
                    return 186;
                }
                case 60: {
                    return 231;
                }
                case 61: {
                    return 233;
                }
                case 91: {
                    return 224;
                }
                case 92: {
                    return 242;
                }
                case 93: {
                    return 232;
                }
                case 94: {
                    return 236;
                }
            }
            return -1;
        }
    }
    ,
    SPANISH(new int[]{90}){

        @Override
        public int map(int index) {
            switch (index) {
                case 3: {
                    return 163;
                }
                case 32: {
                    return 167;
                }
                case 59: {
                    return 161;
                }
                case 60: {
                    return 209;
                }
                case 61: {
                    return 191;
                }
                case 91: {
                    return 176;
                }
                case 92: {
                    return 241;
                }
                case 93: {
                    return 231;
                }
            }
            return -1;
        }
    }
    ,
    SWEDISH(new int[]{72, 55}){

        @Override
        public int map(int index) {
            switch (index) {
                case 32: {
                    return 201;
                }
                case 59: {
                    return 196;
                }
                case 60: {
                    return 214;
                }
                case 61: {
                    return 197;
                }
                case 62: {
                    return 220;
                }
                case 64: {
                    return 233;
                }
                case 91: {
                    return 228;
                }
                case 92: {
                    return 246;
                }
                case 93: {
                    return 229;
                }
                case 94: {
                    return 252;
                }
            }
            return -1;
        }
    }
    ,
    SWISS(new int[]{61}){

        @Override
        public int map(int index) {
            switch (index) {
                case 3: {
                    return 249;
                }
                case 32: {
                    return 224;
                }
                case 59: {
                    return 233;
                }
                case 60: {
                    return 231;
                }
                case 61: {
                    return 234;
                }
                case 62: {
                    return 238;
                }
                case 63: {
                    return 232;
                }
                case 64: {
                    return 244;
                }
                case 91: {
                    return 228;
                }
                case 92: {
                    return 246;
                }
                case 93: {
                    return 252;
                }
                case 94: {
                    return 251;
                }
            }
            return -1;
        }
    };

    private final int[] myDesignations;

    private CharacterSet(int ... designations) {
        this.myDesignations = designations;
    }

    public static CharacterSet valueOf(char designation) {
        for (CharacterSet csn : CharacterSet.values()) {
            if (!csn.isDesignation(designation)) continue;
            return csn;
        }
        return ASCII;
    }

    public abstract int map(int var1);

    private boolean isDesignation(char designation) {
        for (int myDesignation : this.myDesignations) {
            if (myDesignation != designation) continue;
            return true;
        }
        return false;
    }
}

