/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry;

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;
import org.apache.commons.math3.exception.MathParseException;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.util.CompositeFormat;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class VectorFormat<S extends Space> {
    public static final String DEFAULT_PREFIX = "{";
    public static final String DEFAULT_SUFFIX = "}";
    public static final String DEFAULT_SEPARATOR = "; ";
    private final String prefix;
    private final String suffix;
    private final String separator;
    private final String trimmedPrefix;
    private final String trimmedSuffix;
    private final String trimmedSeparator;
    private final NumberFormat format;

    protected VectorFormat() {
        this(DEFAULT_PREFIX, DEFAULT_SUFFIX, DEFAULT_SEPARATOR, CompositeFormat.getDefaultNumberFormat());
    }

    protected VectorFormat(NumberFormat format) {
        this(DEFAULT_PREFIX, DEFAULT_SUFFIX, DEFAULT_SEPARATOR, format);
    }

    protected VectorFormat(String prefix, String suffix, String separator) {
        this(prefix, suffix, separator, CompositeFormat.getDefaultNumberFormat());
    }

    protected VectorFormat(String prefix, String suffix, String separator, NumberFormat format) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.separator = separator;
        this.trimmedPrefix = prefix.trim();
        this.trimmedSuffix = suffix.trim();
        this.trimmedSeparator = separator.trim();
        this.format = format;
    }

    public static Locale[] getAvailableLocales() {
        return NumberFormat.getAvailableLocales();
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getSuffix() {
        return this.suffix;
    }

    public String getSeparator() {
        return this.separator;
    }

    public NumberFormat getFormat() {
        return this.format;
    }

    public String format(Vector<S> vector) {
        return this.format(vector, new StringBuffer(), new FieldPosition(0)).toString();
    }

    public abstract StringBuffer format(Vector<S> var1, StringBuffer var2, FieldPosition var3);

    protected StringBuffer format(StringBuffer toAppendTo, FieldPosition pos, double ... coordinates) {
        pos.setBeginIndex(0);
        pos.setEndIndex(0);
        toAppendTo.append(this.prefix);
        for (int i = 0; i < coordinates.length; ++i) {
            if (i > 0) {
                toAppendTo.append(this.separator);
            }
            CompositeFormat.formatDouble(coordinates[i], this.format, toAppendTo, pos);
        }
        toAppendTo.append(this.suffix);
        return toAppendTo;
    }

    public abstract Vector<S> parse(String var1) throws MathParseException;

    public abstract Vector<S> parse(String var1, ParsePosition var2);

    protected double[] parseCoordinates(int dimension, String source, ParsePosition pos) {
        int initialIndex = pos.getIndex();
        double[] coordinates = new double[dimension];
        CompositeFormat.parseAndIgnoreWhitespace(source, pos);
        if (!CompositeFormat.parseFixedstring(source, this.trimmedPrefix, pos)) {
            return null;
        }
        for (int i = 0; i < dimension; ++i) {
            CompositeFormat.parseAndIgnoreWhitespace(source, pos);
            if (i > 0 && !CompositeFormat.parseFixedstring(source, this.trimmedSeparator, pos)) {
                return null;
            }
            CompositeFormat.parseAndIgnoreWhitespace(source, pos);
            Number c = CompositeFormat.parseNumber(source, this.format, pos);
            if (c == null) {
                pos.setIndex(initialIndex);
                return null;
            }
            coordinates[i] = c.doubleValue();
        }
        CompositeFormat.parseAndIgnoreWhitespace(source, pos);
        if (!CompositeFormat.parseFixedstring(source, this.trimmedSuffix, pos)) {
            return null;
        }
        return coordinates;
    }
}

