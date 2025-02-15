/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.format;

import java.util.Locale;
import org.apache.poi.ss.format.CellDateFormatter;
import org.apache.poi.ss.format.CellElapsedFormatter;
import org.apache.poi.ss.format.CellFormatter;
import org.apache.poi.ss.format.CellGeneralFormatter;
import org.apache.poi.ss.format.CellNumberFormatter;
import org.apache.poi.ss.format.CellTextFormatter;

public enum CellFormatType {
    GENERAL{

        @Override
        boolean isSpecial(char ch) {
            return false;
        }

        @Override
        CellFormatter formatter(String pattern) {
            return new CellGeneralFormatter();
        }

        @Override
        CellFormatter formatter(Locale locale, String pattern) {
            return new CellGeneralFormatter(locale);
        }
    }
    ,
    NUMBER{

        @Override
        boolean isSpecial(char ch) {
            return false;
        }

        @Override
        CellFormatter formatter(String pattern) {
            return new CellNumberFormatter(pattern);
        }

        @Override
        CellFormatter formatter(Locale locale, String pattern) {
            return new CellNumberFormatter(locale, pattern);
        }
    }
    ,
    DATE{

        @Override
        boolean isSpecial(char ch) {
            return ch == '\'' || ch <= '\u007f' && Character.isLetter(ch);
        }

        @Override
        CellFormatter formatter(String pattern) {
            return new CellDateFormatter(pattern);
        }

        @Override
        CellFormatter formatter(Locale locale, String pattern) {
            return new CellDateFormatter(locale, pattern);
        }
    }
    ,
    ELAPSED{

        @Override
        boolean isSpecial(char ch) {
            return false;
        }

        @Override
        CellFormatter formatter(String pattern) {
            return new CellElapsedFormatter(pattern);
        }

        @Override
        CellFormatter formatter(Locale locale, String pattern) {
            return new CellElapsedFormatter(pattern);
        }
    }
    ,
    TEXT{

        @Override
        boolean isSpecial(char ch) {
            return false;
        }

        @Override
        CellFormatter formatter(String pattern) {
            return new CellTextFormatter(pattern);
        }

        @Override
        CellFormatter formatter(Locale locale, String pattern) {
            return new CellTextFormatter(pattern);
        }
    };


    abstract boolean isSpecial(char var1);

    abstract CellFormatter formatter(String var1);

    abstract CellFormatter formatter(Locale var1, String var2);
}

