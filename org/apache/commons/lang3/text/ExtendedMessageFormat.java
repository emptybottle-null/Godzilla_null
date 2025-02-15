/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.text;

import java.text.Format;
import java.text.MessageFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.lang3.LocaleUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.text.FormatFactory;
import org.apache.commons.lang3.text.StrMatcher;

@Deprecated
public class ExtendedMessageFormat
extends MessageFormat {
    private static final long serialVersionUID = -2362048321261811743L;
    private static final int HASH_SEED = 31;
    private static final String DUMMY_PATTERN = "";
    private static final char START_FMT = ',';
    private static final char END_FE = '}';
    private static final char START_FE = '{';
    private static final char QUOTE = '\'';
    private String toPattern;
    private final Map<String, ? extends FormatFactory> registry;

    public ExtendedMessageFormat(String pattern) {
        this(pattern, Locale.getDefault());
    }

    public ExtendedMessageFormat(String pattern, Locale locale) {
        this(pattern, locale, null);
    }

    public ExtendedMessageFormat(String pattern, Map<String, ? extends FormatFactory> registry) {
        this(pattern, Locale.getDefault(), registry);
    }

    public ExtendedMessageFormat(String pattern, Locale locale, Map<String, ? extends FormatFactory> registry) {
        super(DUMMY_PATTERN);
        this.setLocale(LocaleUtils.toLocale(locale));
        this.registry = registry;
        this.applyPattern(pattern);
    }

    @Override
    public String toPattern() {
        return this.toPattern;
    }

    @Override
    public final void applyPattern(String pattern) {
        if (this.registry == null) {
            super.applyPattern(pattern);
            this.toPattern = super.toPattern();
            return;
        }
        ArrayList<Format> foundFormats = new ArrayList<Format>();
        ArrayList<String> foundDescriptions = new ArrayList<String>();
        StringBuilder stripCustom = new StringBuilder(pattern.length());
        ParsePosition pos = new ParsePosition(0);
        char[] c = pattern.toCharArray();
        int fmtCount = 0;
        block4: while (pos.getIndex() < pattern.length()) {
            switch (c[pos.getIndex()]) {
                case '\'': {
                    this.appendQuotedString(pattern, pos, stripCustom);
                    continue block4;
                }
                case '{': {
                    ++fmtCount;
                    this.seekNonWs(pattern, pos);
                    int start = pos.getIndex();
                    int index = this.readArgumentIndex(pattern, this.next(pos));
                    stripCustom.append('{').append(index);
                    this.seekNonWs(pattern, pos);
                    Format format = null;
                    String formatDescription = null;
                    if (c[pos.getIndex()] == ',' && (format = this.getFormat(formatDescription = this.parseFormatDescription(pattern, this.next(pos)))) == null) {
                        stripCustom.append(',').append(formatDescription);
                    }
                    foundFormats.add(format);
                    foundDescriptions.add(format == null ? null : formatDescription);
                    Validate.isTrue(foundFormats.size() == fmtCount);
                    Validate.isTrue(foundDescriptions.size() == fmtCount);
                    if (c[pos.getIndex()] == '}') break;
                    throw new IllegalArgumentException("Unreadable format element at position " + start);
                }
            }
            stripCustom.append(c[pos.getIndex()]);
            this.next(pos);
        }
        super.applyPattern(stripCustom.toString());
        this.toPattern = this.insertFormats(super.toPattern(), foundDescriptions);
        if (this.containsElements(foundFormats)) {
            Format[] origFormats = this.getFormats();
            int i = 0;
            for (Format f : foundFormats) {
                if (f != null) {
                    origFormats[i] = f;
                }
                ++i;
            }
            super.setFormats(origFormats);
        }
    }

    @Override
    public void setFormat(int formatElementIndex, Format newFormat) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setFormatByArgumentIndex(int argumentIndex, Format newFormat) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setFormats(Format[] newFormats) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setFormatsByArgumentIndex(Format[] newFormats) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (ObjectUtils.notEqual(this.getClass(), obj.getClass())) {
            return false;
        }
        ExtendedMessageFormat rhs = (ExtendedMessageFormat)obj;
        if (ObjectUtils.notEqual(this.toPattern, rhs.toPattern)) {
            return false;
        }
        return !ObjectUtils.notEqual(this.registry, rhs.registry);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Objects.hashCode(this.registry);
        result = 31 * result + Objects.hashCode(this.toPattern);
        return result;
    }

    private Format getFormat(String desc) {
        if (this.registry != null) {
            FormatFactory factory;
            String name = desc;
            String args = null;
            int i = desc.indexOf(44);
            if (i > 0) {
                name = desc.substring(0, i).trim();
                args = desc.substring(i + 1).trim();
            }
            if ((factory = this.registry.get(name)) != null) {
                return factory.getFormat(name, args, this.getLocale());
            }
        }
        return null;
    }

    /*
     * Unable to fully structure code
     */
    private int readArgumentIndex(String pattern, ParsePosition pos) {
        start = pos.getIndex();
        this.seekNonWs(pattern, pos);
        result = new StringBuilder();
        error = false;
        while (!error && pos.getIndex() < pattern.length()) {
            c = pattern.charAt(pos.getIndex());
            if (!Character.isWhitespace(c)) ** GOTO lbl-1000
            this.seekNonWs(pattern, pos);
            c = pattern.charAt(pos.getIndex());
            if (c != ',' && c != '}') {
                error = true;
            } else lbl-1000:
            // 2 sources

            {
                if ((c == ',' || c == '}') && result.length() > 0) {
                    try {
                        return Integer.parseInt(result.toString());
                    } catch (NumberFormatException var7_7) {
                        // empty catch block
                    }
                }
                error = Character.isDigit(c) == false;
                result.append(c);
            }
            this.next(pos);
        }
        if (error) {
            throw new IllegalArgumentException("Invalid format argument index at position " + start + ": " + pattern.substring(start, pos.getIndex()));
        }
        throw new IllegalArgumentException("Unterminated format element at position " + start);
    }

    private String parseFormatDescription(String pattern, ParsePosition pos) {
        int start = pos.getIndex();
        this.seekNonWs(pattern, pos);
        int text = pos.getIndex();
        int depth = 1;
        while (pos.getIndex() < pattern.length()) {
            switch (pattern.charAt(pos.getIndex())) {
                case '{': {
                    ++depth;
                    break;
                }
                case '}': {
                    if (--depth != 0) break;
                    return pattern.substring(text, pos.getIndex());
                }
                case '\'': {
                    this.getQuotedString(pattern, pos);
                    break;
                }
            }
            this.next(pos);
        }
        throw new IllegalArgumentException("Unterminated format element at position " + start);
    }

    private String insertFormats(String pattern, ArrayList<String> customPatterns) {
        if (!this.containsElements(customPatterns)) {
            return pattern;
        }
        StringBuilder sb = new StringBuilder(pattern.length() * 2);
        ParsePosition pos = new ParsePosition(0);
        int fe = -1;
        int depth = 0;
        block5: while (pos.getIndex() < pattern.length()) {
            char c = pattern.charAt(pos.getIndex());
            switch (c) {
                case '\'': {
                    this.appendQuotedString(pattern, pos, sb);
                    break;
                }
                case '{': {
                    String customPattern;
                    sb.append('{').append(this.readArgumentIndex(pattern, this.next(pos)));
                    if (++depth != 1 || (customPattern = customPatterns.get(++fe)) == null) continue block5;
                    sb.append(',').append(customPattern);
                    break;
                }
                case '}': {
                    --depth;
                }
                default: {
                    sb.append(c);
                    this.next(pos);
                }
            }
        }
        return sb.toString();
    }

    private void seekNonWs(String pattern, ParsePosition pos) {
        int len = 0;
        char[] buffer = pattern.toCharArray();
        do {
            len = StrMatcher.splitMatcher().isMatch(buffer, pos.getIndex());
            pos.setIndex(pos.getIndex() + len);
        } while (len > 0 && pos.getIndex() < pattern.length());
    }

    private ParsePosition next(ParsePosition pos) {
        pos.setIndex(pos.getIndex() + 1);
        return pos;
    }

    private StringBuilder appendQuotedString(String pattern, ParsePosition pos, StringBuilder appendTo) {
        assert (pattern.toCharArray()[pos.getIndex()] == '\'') : "Quoted string must start with quote character";
        if (appendTo != null) {
            appendTo.append('\'');
        }
        this.next(pos);
        int start = pos.getIndex();
        char[] c = pattern.toCharArray();
        int lastHold = start;
        for (int i = pos.getIndex(); i < pattern.length(); ++i) {
            if (c[pos.getIndex()] == '\'') {
                this.next(pos);
                return appendTo == null ? null : appendTo.append(c, lastHold, pos.getIndex() - lastHold);
            }
            this.next(pos);
        }
        throw new IllegalArgumentException("Unterminated quoted string at position " + start);
    }

    private void getQuotedString(String pattern, ParsePosition pos) {
        this.appendQuotedString(pattern, pos, null);
    }

    private boolean containsElements(Collection<?> coll) {
        if (coll == null || coll.isEmpty()) {
            return false;
        }
        for (Object name : coll) {
            if (name == null) continue;
            return true;
        }
        return false;
    }
}

