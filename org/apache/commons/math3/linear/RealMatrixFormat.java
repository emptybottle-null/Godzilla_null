/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.linear;

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.commons.math3.exception.MathParseException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.util.CompositeFormat;

public class RealMatrixFormat {
    private static final String DEFAULT_PREFIX = "{";
    private static final String DEFAULT_SUFFIX = "}";
    private static final String DEFAULT_ROW_PREFIX = "{";
    private static final String DEFAULT_ROW_SUFFIX = "}";
    private static final String DEFAULT_ROW_SEPARATOR = ",";
    private static final String DEFAULT_COLUMN_SEPARATOR = ",";
    private final String prefix;
    private final String suffix;
    private final String rowPrefix;
    private final String rowSuffix;
    private final String rowSeparator;
    private final String columnSeparator;
    private final NumberFormat format;

    public RealMatrixFormat() {
        this("{", "}", "{", "}", ",", ",", CompositeFormat.getDefaultNumberFormat());
    }

    public RealMatrixFormat(NumberFormat format) {
        this("{", "}", "{", "}", ",", ",", format);
    }

    public RealMatrixFormat(String prefix, String suffix, String rowPrefix, String rowSuffix, String rowSeparator, String columnSeparator) {
        this(prefix, suffix, rowPrefix, rowSuffix, rowSeparator, columnSeparator, CompositeFormat.getDefaultNumberFormat());
    }

    public RealMatrixFormat(String prefix, String suffix, String rowPrefix, String rowSuffix, String rowSeparator, String columnSeparator, NumberFormat format) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.rowPrefix = rowPrefix;
        this.rowSuffix = rowSuffix;
        this.rowSeparator = rowSeparator;
        this.columnSeparator = columnSeparator;
        this.format = format;
        this.format.setGroupingUsed(false);
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

    public String getRowPrefix() {
        return this.rowPrefix;
    }

    public String getRowSuffix() {
        return this.rowSuffix;
    }

    public String getRowSeparator() {
        return this.rowSeparator;
    }

    public String getColumnSeparator() {
        return this.columnSeparator;
    }

    public NumberFormat getFormat() {
        return this.format;
    }

    public static RealMatrixFormat getInstance() {
        return RealMatrixFormat.getInstance(Locale.getDefault());
    }

    public static RealMatrixFormat getInstance(Locale locale) {
        return new RealMatrixFormat(CompositeFormat.getDefaultNumberFormat(locale));
    }

    public String format(RealMatrix m) {
        return this.format(m, new StringBuffer(), new FieldPosition(0)).toString();
    }

    public StringBuffer format(RealMatrix matrix, StringBuffer toAppendTo, FieldPosition pos) {
        pos.setBeginIndex(0);
        pos.setEndIndex(0);
        toAppendTo.append(this.prefix);
        int rows = matrix.getRowDimension();
        for (int i = 0; i < rows; ++i) {
            toAppendTo.append(this.rowPrefix);
            for (int j = 0; j < matrix.getColumnDimension(); ++j) {
                if (j > 0) {
                    toAppendTo.append(this.columnSeparator);
                }
                CompositeFormat.formatDouble(matrix.getEntry(i, j), this.format, toAppendTo, pos);
            }
            toAppendTo.append(this.rowSuffix);
            if (i >= rows - 1) continue;
            toAppendTo.append(this.rowSeparator);
        }
        toAppendTo.append(this.suffix);
        return toAppendTo;
    }

    public RealMatrix parse(String source) {
        ParsePosition parsePosition = new ParsePosition(0);
        RealMatrix result = this.parse(source, parsePosition);
        if (parsePosition.getIndex() == 0) {
            throw new MathParseException(source, parsePosition.getErrorIndex(), Array2DRowRealMatrix.class);
        }
        return result;
    }

    public RealMatrix parse(String source, ParsePosition pos) {
        int initialIndex = pos.getIndex();
        String trimmedPrefix = this.prefix.trim();
        String trimmedSuffix = this.suffix.trim();
        String trimmedRowPrefix = this.rowPrefix.trim();
        String trimmedRowSuffix = this.rowSuffix.trim();
        String trimmedColumnSeparator = this.columnSeparator.trim();
        String trimmedRowSeparator = this.rowSeparator.trim();
        CompositeFormat.parseAndIgnoreWhitespace(source, pos);
        if (!CompositeFormat.parseFixedstring(source, trimmedPrefix, pos)) {
            return null;
        }
        ArrayList matrix = new ArrayList();
        ArrayList<Number> rowComponents = new ArrayList<Number>();
        boolean loop = true;
        while (loop) {
            if (!rowComponents.isEmpty()) {
                CompositeFormat.parseAndIgnoreWhitespace(source, pos);
                if (!CompositeFormat.parseFixedstring(source, trimmedColumnSeparator, pos)) {
                    if (trimmedRowSuffix.length() != 0 && !CompositeFormat.parseFixedstring(source, trimmedRowSuffix, pos)) {
                        return null;
                    }
                    CompositeFormat.parseAndIgnoreWhitespace(source, pos);
                    if (CompositeFormat.parseFixedstring(source, trimmedRowSeparator, pos)) {
                        matrix.add(rowComponents);
                        rowComponents = new ArrayList();
                        continue;
                    }
                    loop = false;
                }
            } else {
                CompositeFormat.parseAndIgnoreWhitespace(source, pos);
                if (trimmedRowPrefix.length() != 0 && !CompositeFormat.parseFixedstring(source, trimmedRowPrefix, pos)) {
                    return null;
                }
            }
            if (!loop) continue;
            CompositeFormat.parseAndIgnoreWhitespace(source, pos);
            Number component = CompositeFormat.parseNumber(source, this.format, pos);
            if (component != null) {
                rowComponents.add(component);
                continue;
            }
            if (rowComponents.isEmpty()) {
                loop = false;
                continue;
            }
            pos.setIndex(initialIndex);
            return null;
        }
        if (!rowComponents.isEmpty()) {
            matrix.add(rowComponents);
        }
        CompositeFormat.parseAndIgnoreWhitespace(source, pos);
        if (!CompositeFormat.parseFixedstring(source, trimmedSuffix, pos)) {
            return null;
        }
        if (matrix.isEmpty()) {
            pos.setIndex(initialIndex);
            return null;
        }
        double[][] data = new double[matrix.size()][];
        int row = 0;
        for (List list : matrix) {
            data[row] = new double[list.size()];
            for (int i = 0; i < list.size(); ++i) {
                data[row][i] = ((Number)list.get(i)).doubleValue();
            }
            ++row;
        }
        return MatrixUtils.createRealMatrix(data);
    }
}

