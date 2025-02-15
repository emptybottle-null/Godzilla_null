/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.pattern;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.helpers.Loader;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.pattern.ClassNamePatternConverter;
import org.apache.log4j.pattern.DatePatternConverter;
import org.apache.log4j.pattern.FileDatePatternConverter;
import org.apache.log4j.pattern.FileLocationPatternConverter;
import org.apache.log4j.pattern.FormattingInfo;
import org.apache.log4j.pattern.FullLocationPatternConverter;
import org.apache.log4j.pattern.IntegerPatternConverter;
import org.apache.log4j.pattern.LevelPatternConverter;
import org.apache.log4j.pattern.LineLocationPatternConverter;
import org.apache.log4j.pattern.LineSeparatorPatternConverter;
import org.apache.log4j.pattern.LiteralPatternConverter;
import org.apache.log4j.pattern.LoggerPatternConverter;
import org.apache.log4j.pattern.MessagePatternConverter;
import org.apache.log4j.pattern.MethodLocationPatternConverter;
import org.apache.log4j.pattern.NDCPatternConverter;
import org.apache.log4j.pattern.PatternConverter;
import org.apache.log4j.pattern.PropertiesPatternConverter;
import org.apache.log4j.pattern.RelativeTimePatternConverter;
import org.apache.log4j.pattern.SequenceNumberPatternConverter;
import org.apache.log4j.pattern.ThreadPatternConverter;
import org.apache.log4j.pattern.ThrowableInformationPatternConverter;

public final class PatternParser {
    private static final char ESCAPE_CHAR = '%';
    private static final int LITERAL_STATE = 0;
    private static final int CONVERTER_STATE = 1;
    private static final int DOT_STATE = 3;
    private static final int MIN_STATE = 4;
    private static final int MAX_STATE = 5;
    private static final Map PATTERN_LAYOUT_RULES;
    private static final Map FILENAME_PATTERN_RULES;

    private PatternParser() {
    }

    public static Map getPatternLayoutRules() {
        return PATTERN_LAYOUT_RULES;
    }

    public static Map getFileNamePatternRules() {
        return FILENAME_PATTERN_RULES;
    }

    private static int extractConverter(char lastChar, String pattern, int i, StringBuffer convBuf, StringBuffer currentLiteral) {
        convBuf.setLength(0);
        if (!Character.isUnicodeIdentifierStart(lastChar)) {
            return i;
        }
        convBuf.append(lastChar);
        while (i < pattern.length() && Character.isUnicodeIdentifierPart(pattern.charAt(i))) {
            convBuf.append(pattern.charAt(i));
            currentLiteral.append(pattern.charAt(i));
            ++i;
        }
        return i;
    }

    private static int extractOptions(String pattern, int i, List options) {
        int end;
        while (i < pattern.length() && pattern.charAt(i) == '{' && (end = pattern.indexOf(125, i)) != -1) {
            String r = pattern.substring(i + 1, end);
            options.add(r);
            i = end + 1;
        }
        return i;
    }

    public static void parse(String pattern, List patternConverters, List formattingInfos, Map converterRegistry, Map rules) {
        if (pattern == null) {
            throw new NullPointerException("pattern");
        }
        StringBuffer currentLiteral = new StringBuffer(32);
        int patternLength = pattern.length();
        int state = 0;
        int i = 0;
        FormattingInfo formattingInfo = FormattingInfo.getDefault();
        while (i < patternLength) {
            char c = pattern.charAt(i++);
            block0 : switch (state) {
                case 0: {
                    if (i == patternLength) {
                        currentLiteral.append(c);
                        break;
                    }
                    if (c == '%') {
                        switch (pattern.charAt(i)) {
                            case '%': {
                                currentLiteral.append(c);
                                ++i;
                                break block0;
                            }
                        }
                        if (currentLiteral.length() != 0) {
                            patternConverters.add(new LiteralPatternConverter(currentLiteral.toString()));
                            formattingInfos.add(FormattingInfo.getDefault());
                        }
                        currentLiteral.setLength(0);
                        currentLiteral.append(c);
                        state = 1;
                        formattingInfo = FormattingInfo.getDefault();
                        break;
                    }
                    currentLiteral.append(c);
                    break;
                }
                case 1: {
                    currentLiteral.append(c);
                    switch (c) {
                        case '-': {
                            formattingInfo = new FormattingInfo(true, formattingInfo.getMinLength(), formattingInfo.getMaxLength());
                            break block0;
                        }
                        case '.': {
                            state = 3;
                            break block0;
                        }
                    }
                    if (c >= '0' && c <= '9') {
                        formattingInfo = new FormattingInfo(formattingInfo.isLeftAligned(), c - 48, formattingInfo.getMaxLength());
                        state = 4;
                        break;
                    }
                    i = PatternParser.finalizeConverter(c, pattern, i, currentLiteral, formattingInfo, converterRegistry, rules, patternConverters, formattingInfos);
                    state = 0;
                    formattingInfo = FormattingInfo.getDefault();
                    currentLiteral.setLength(0);
                    break;
                }
                case 4: {
                    currentLiteral.append(c);
                    if (c >= '0' && c <= '9') {
                        formattingInfo = new FormattingInfo(formattingInfo.isLeftAligned(), formattingInfo.getMinLength() * 10 + (c - 48), formattingInfo.getMaxLength());
                        break;
                    }
                    if (c == '.') {
                        state = 3;
                        break;
                    }
                    i = PatternParser.finalizeConverter(c, pattern, i, currentLiteral, formattingInfo, converterRegistry, rules, patternConverters, formattingInfos);
                    state = 0;
                    formattingInfo = FormattingInfo.getDefault();
                    currentLiteral.setLength(0);
                    break;
                }
                case 3: {
                    currentLiteral.append(c);
                    if (c >= '0' && c <= '9') {
                        formattingInfo = new FormattingInfo(formattingInfo.isLeftAligned(), formattingInfo.getMinLength(), c - 48);
                        state = 5;
                        break;
                    }
                    LogLog.error("Error occured in position " + i + ".\n Was expecting digit, instead got char \"" + c + "\".");
                    state = 0;
                    break;
                }
                case 5: {
                    currentLiteral.append(c);
                    if (c >= '0' && c <= '9') {
                        formattingInfo = new FormattingInfo(formattingInfo.isLeftAligned(), formattingInfo.getMinLength(), formattingInfo.getMaxLength() * 10 + (c - 48));
                        break;
                    }
                    i = PatternParser.finalizeConverter(c, pattern, i, currentLiteral, formattingInfo, converterRegistry, rules, patternConverters, formattingInfos);
                    state = 0;
                    formattingInfo = FormattingInfo.getDefault();
                    currentLiteral.setLength(0);
                }
            }
        }
        if (currentLiteral.length() != 0) {
            patternConverters.add(new LiteralPatternConverter(currentLiteral.toString()));
            formattingInfos.add(FormattingInfo.getDefault());
        }
    }

    private static PatternConverter createConverter(String converterId, StringBuffer currentLiteral, Map converterRegistry, Map rules, List options) {
        String converterName = converterId;
        Object converterObj = null;
        for (int i = converterId.length(); i > 0 && converterObj == null; --i) {
            converterName = converterName.substring(0, i);
            if (converterRegistry != null) {
                converterObj = converterRegistry.get(converterName);
            }
            if (converterObj != null || rules == null) continue;
            converterObj = rules.get(converterName);
        }
        if (converterObj == null) {
            LogLog.error("Unrecognized format specifier [" + converterId + "]");
            return null;
        }
        Class converterClass = null;
        if (converterObj instanceof Class) {
            converterClass = converterObj;
        } else if (converterObj instanceof String) {
            try {
                converterClass = Loader.loadClass(converterObj);
            } catch (ClassNotFoundException ex) {
                LogLog.warn("Class for conversion pattern %" + converterName + " not found", ex);
                return null;
            }
        } else {
            LogLog.warn("Bad map entry for conversion pattern %" + converterName + ".");
            return null;
        }
        try {
            Method factory = converterClass.getMethod("newInstance", Class.forName("[Ljava.lang.String;"));
            String[] optionsArray = new String[options.size()];
            optionsArray = options.toArray(optionsArray);
            Object newObj = factory.invoke(null, new Object[]{optionsArray});
            if (newObj instanceof PatternConverter) {
                currentLiteral.delete(0, currentLiteral.length() - (converterId.length() - converterName.length()));
                return (PatternConverter)newObj;
            }
            LogLog.warn("Class " + converterClass.getName() + " does not extend PatternConverter.");
        } catch (Exception ex) {
            LogLog.error("Error creating converter for " + converterId, ex);
            try {
                PatternConverter pc = (PatternConverter)converterClass.newInstance();
                currentLiteral.delete(0, currentLiteral.length() - (converterId.length() - converterName.length()));
                return pc;
            } catch (Exception ex2) {
                LogLog.error("Error creating converter for " + converterId, ex2);
            }
        }
        return null;
    }

    private static int finalizeConverter(char c, String pattern, int i, StringBuffer currentLiteral, FormattingInfo formattingInfo, Map converterRegistry, Map rules, List patternConverters, List formattingInfos) {
        StringBuffer convBuf = new StringBuffer();
        i = PatternParser.extractConverter(c, pattern, i, convBuf, currentLiteral);
        String converterId = convBuf.toString();
        ArrayList options = new ArrayList();
        i = PatternParser.extractOptions(pattern, i, options);
        PatternConverter pc = PatternParser.createConverter(converterId, currentLiteral, converterRegistry, rules, options);
        if (pc == null) {
            StringBuffer msg;
            if (converterId == null || converterId.length() == 0) {
                msg = new StringBuffer("Empty conversion specifier starting at position ");
            } else {
                msg = new StringBuffer("Unrecognized conversion specifier [");
                msg.append(converterId);
                msg.append("] starting at position ");
            }
            msg.append(Integer.toString(i));
            msg.append(" in conversion pattern.");
            LogLog.error(msg.toString());
            patternConverters.add(new LiteralPatternConverter(currentLiteral.toString()));
            formattingInfos.add(FormattingInfo.getDefault());
        } else {
            patternConverters.add(pc);
            formattingInfos.add(formattingInfo);
            if (currentLiteral.length() > 0) {
                patternConverters.add(new LiteralPatternConverter(currentLiteral.toString()));
                formattingInfos.add(FormattingInfo.getDefault());
            }
        }
        currentLiteral.setLength(0);
        return i;
    }

    static {
        HashMap<String, Class> rules = new HashMap<String, Class>(17);
        rules.put("c", LoggerPatternConverter.class);
        rules.put("logger", LoggerPatternConverter.class);
        rules.put("C", ClassNamePatternConverter.class);
        rules.put("class", ClassNamePatternConverter.class);
        rules.put("d", DatePatternConverter.class);
        rules.put("date", DatePatternConverter.class);
        rules.put("F", FileLocationPatternConverter.class);
        rules.put("file", FileLocationPatternConverter.class);
        rules.put("l", FullLocationPatternConverter.class);
        rules.put("L", LineLocationPatternConverter.class);
        rules.put("line", LineLocationPatternConverter.class);
        rules.put("m", MessagePatternConverter.class);
        rules.put("message", MessagePatternConverter.class);
        rules.put("n", LineSeparatorPatternConverter.class);
        rules.put("M", MethodLocationPatternConverter.class);
        rules.put("method", MethodLocationPatternConverter.class);
        rules.put("p", LevelPatternConverter.class);
        rules.put("level", LevelPatternConverter.class);
        rules.put("r", RelativeTimePatternConverter.class);
        rules.put("relative", RelativeTimePatternConverter.class);
        rules.put("t", ThreadPatternConverter.class);
        rules.put("thread", ThreadPatternConverter.class);
        rules.put("x", NDCPatternConverter.class);
        rules.put("ndc", NDCPatternConverter.class);
        rules.put("X", PropertiesPatternConverter.class);
        rules.put("properties", PropertiesPatternConverter.class);
        rules.put("sn", SequenceNumberPatternConverter.class);
        rules.put("sequenceNumber", SequenceNumberPatternConverter.class);
        rules.put("throwable", ThrowableInformationPatternConverter.class);
        PATTERN_LAYOUT_RULES = new ReadOnlyMap(rules);
        HashMap<String, Class> fnameRules = new HashMap<String, Class>(4);
        fnameRules.put("d", FileDatePatternConverter.class);
        fnameRules.put("date", FileDatePatternConverter.class);
        fnameRules.put("i", IntegerPatternConverter.class);
        fnameRules.put("index", IntegerPatternConverter.class);
        FILENAME_PATTERN_RULES = new ReadOnlyMap(fnameRules);
    }

    private static class ReadOnlyMap
    implements Map {
        private final Map map;

        public ReadOnlyMap(Map src) {
            this.map = src;
        }

        public void clear() {
            throw new UnsupportedOperationException();
        }

        public boolean containsKey(Object key) {
            return this.map.containsKey(key);
        }

        public boolean containsValue(Object value) {
            return this.map.containsValue(value);
        }

        public Set entrySet() {
            return this.map.entrySet();
        }

        public Object get(Object key) {
            return this.map.get(key);
        }

        public boolean isEmpty() {
            return this.map.isEmpty();
        }

        public Set keySet() {
            return this.map.keySet();
        }

        public Object put(Object key, Object value) {
            throw new UnsupportedOperationException();
        }

        public void putAll(Map t) {
            throw new UnsupportedOperationException();
        }

        public Object remove(Object key) {
            throw new UnsupportedOperationException();
        }

        public int size() {
            return this.map.size();
        }

        public Collection values() {
            return this.map.values();
        }
    }
}

