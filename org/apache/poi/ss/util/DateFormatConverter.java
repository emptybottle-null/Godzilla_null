/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.poi.util.LocaleID;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;

public final class DateFormatConverter {
    private static POILogger logger = POILogFactory.getLogger(DateFormatConverter.class);
    private static Map<String, String> tokenConversions = DateFormatConverter.prepareTokenConversions();

    private DateFormatConverter() {
    }

    private static Map<String, String> prepareTokenConversions() {
        HashMap<String, String> result = new HashMap<String, String>();
        result.put("EEEE", "dddd");
        result.put("EEE", "ddd");
        result.put("EE", "ddd");
        result.put("E", "d");
        result.put("Z", "");
        result.put("z", "");
        result.put("a", "am/pm");
        result.put("A", "AM/PM");
        result.put("K", "H");
        result.put("KK", "HH");
        result.put("k", "h");
        result.put("kk", "hh");
        result.put("S", "0");
        result.put("SS", "00");
        result.put("SSS", "000");
        return result;
    }

    public static String getPrefixForLocale(Locale locale) {
        String languageTag = locale.toLanguageTag();
        if ("".equals(languageTag)) {
            return "[$-0409]";
        }
        LocaleID loc = LocaleID.lookupByLanguageTag(languageTag);
        if (loc == null) {
            String cmpTag = languageTag.indexOf(95) > -1 ? languageTag.replace('_', '-') : languageTag;
            int idx = languageTag.length();
            while (loc == null && (idx = cmpTag.lastIndexOf(45, idx - 1)) > 0) {
                loc = LocaleID.lookupByLanguageTag(languageTag.substring(0, idx));
            }
            if (loc == null) {
                logger.log(7, "Unable to find prefix for Locale '" + languageTag + "' or its parent locales.");
                return "";
            }
        }
        return String.format(Locale.ROOT, "[$-%04X]", loc.getLcid());
    }

    public static String convert(Locale locale, DateFormat df) {
        String ptrn = ((SimpleDateFormat)df).toPattern();
        return DateFormatConverter.convert(locale, ptrn);
    }

    public static String convert(Locale locale, String format) {
        String token;
        StringBuilder result = new StringBuilder();
        result.append(DateFormatConverter.getPrefixForLocale(locale));
        DateFormatTokenizer tokenizer = new DateFormatTokenizer(format);
        while ((token = tokenizer.getNextToken()) != null) {
            if (token.startsWith("'")) {
                result.append(token.replaceAll("'", "\""));
                continue;
            }
            if (!Character.isLetter(token.charAt(0))) {
                result.append(token);
                continue;
            }
            String mappedToken = tokenConversions.get(token);
            result.append(mappedToken == null ? token : mappedToken);
        }
        result.append(";@");
        return result.toString().trim();
    }

    public static String getJavaDatePattern(int style, Locale locale) {
        DateFormat df = DateFormat.getDateInstance(style, locale);
        if (df instanceof SimpleDateFormat) {
            return ((SimpleDateFormat)df).toPattern();
        }
        switch (style) {
            case 3: {
                return "d/MM/yy";
            }
            case 1: {
                return "MMMM d, yyyy";
            }
            case 0: {
                return "dddd, MMMM d, yyyy";
            }
        }
        return "MMM d, yyyy";
    }

    public static String getJavaTimePattern(int style, Locale locale) {
        DateFormat df = DateFormat.getTimeInstance(style, locale);
        if (df instanceof SimpleDateFormat) {
            return ((SimpleDateFormat)df).toPattern();
        }
        switch (style) {
            case 3: {
                return "h:mm a";
            }
        }
        return "h:mm:ss a";
    }

    public static String getJavaDateTimePattern(int style, Locale locale) {
        DateFormat df = DateFormat.getDateTimeInstance(style, style, locale);
        if (df instanceof SimpleDateFormat) {
            return ((SimpleDateFormat)df).toPattern();
        }
        switch (style) {
            case 3: {
                return "M/d/yy h:mm a";
            }
            case 1: {
                return "MMMM d, yyyy h:mm:ss a";
            }
            case 0: {
                return "dddd, MMMM d, yyyy h:mm:ss a";
            }
        }
        return "MMM d, yyyy h:mm:ss a";
    }

    public static class DateFormatTokenizer {
        String format;
        int pos;

        public DateFormatTokenizer(String format) {
            this.format = format;
        }

        public String getNextToken() {
            if (this.pos >= this.format.length()) {
                return null;
            }
            int subStart = this.pos;
            char curChar = this.format.charAt(this.pos);
            ++this.pos;
            if (curChar == '\'') {
                while (this.pos < this.format.length() && this.format.charAt(this.pos) != '\'') {
                    ++this.pos;
                }
                if (this.pos < this.format.length()) {
                    ++this.pos;
                }
            } else {
                while (this.pos < this.format.length() && this.format.charAt(this.pos) == curChar) {
                    ++this.pos;
                }
            }
            return this.format.substring(subStart, this.pos);
        }

        public static String[] tokenize(String format) {
            String token;
            ArrayList<String> result = new ArrayList<String>();
            DateFormatTokenizer tokenizer = new DateFormatTokenizer(format);
            while ((token = tokenizer.getNextToken()) != null) {
                result.add(token);
            }
            return result.toArray(new String[0]);
        }

        public String toString() {
            String token;
            StringBuilder result = new StringBuilder();
            DateFormatTokenizer tokenizer = new DateFormatTokenizer(this.format);
            while ((token = tokenizer.getNextToken()) != null) {
                if (result.length() > 0) {
                    result.append(", ");
                }
                result.append("[").append(token).append("]");
            }
            return result.toString();
        }
    }
}

