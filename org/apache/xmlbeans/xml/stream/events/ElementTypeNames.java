/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.xml.stream.events;

public class ElementTypeNames {
    public static String getName(int val) {
        switch (val) {
            case 1: {
                return "XML_EVENT";
            }
            case 2: {
                return "START_ELEMENT";
            }
            case 4: {
                return "END_ELEMENT";
            }
            case 8: {
                return "PROCESSING_INSTRUCTION";
            }
            case 16: {
                return "CHARACTER_DATA";
            }
            case 32: {
                return "COMMENT";
            }
            case 64: {
                return "SPACE";
            }
            case 128: {
                return "NULL_ELEMENT";
            }
            case 256: {
                return "START_DOCUMENT";
            }
            case 512: {
                return "END_DOCUMENT";
            }
            case 1024: {
                return "START_PREFIX_MAPPING";
            }
            case 4096: {
                return "CHANGE_PREFIX_MAPPING";
            }
            case 2048: {
                return "END_PREFIX_MAPPING";
            }
            case 8192: {
                return "ENTITY_REFERENCE";
            }
        }
        return "";
    }

    public static int getType(String val) {
        if (val.equals("XML_EVENT")) {
            return 1;
        }
        if (val.equals("START_ELEMENT")) {
            return 2;
        }
        if (val.equals("END_ELEMENT")) {
            return 4;
        }
        if (val.equals("PROCESSING_INSTRUCTION")) {
            return 8;
        }
        if (val.equals("CHARACTER_DATA")) {
            return 16;
        }
        if (val.equals("COMMENT")) {
            return 32;
        }
        if (val.equals("SPACE")) {
            return 64;
        }
        if (val.equals("NULL_ELEMENT")) {
            return 128;
        }
        if (val.equals("START_DOCUMENT")) {
            return 256;
        }
        if (val.equals("END_DOCUMENT")) {
            return 512;
        }
        if (val.equals("START_PREFIX_MAPPING")) {
            return 1024;
        }
        if (val.equals("CHANGE_PREFIX_MAPPING")) {
            return 4096;
        }
        if (val.equals("ENTITY_REFERENCE")) {
            return 8192;
        }
        if (val.equals("END_PREFIX_MAPPING")) {
            return 2048;
        }
        return 128;
    }
}

