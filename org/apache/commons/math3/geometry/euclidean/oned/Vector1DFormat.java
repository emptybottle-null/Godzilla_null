/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.euclidean.oned;

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;
import org.apache.commons.math3.exception.MathParseException;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.VectorFormat;
import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
import org.apache.commons.math3.geometry.euclidean.oned.Vector1D;
import org.apache.commons.math3.util.CompositeFormat;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Vector1DFormat
extends VectorFormat<Euclidean1D> {
    public Vector1DFormat() {
        super("{", "}", "; ", CompositeFormat.getDefaultNumberFormat());
    }

    public Vector1DFormat(NumberFormat format) {
        super("{", "}", "; ", format);
    }

    public Vector1DFormat(String prefix, String suffix) {
        super(prefix, suffix, "; ", CompositeFormat.getDefaultNumberFormat());
    }

    public Vector1DFormat(String prefix, String suffix, NumberFormat format) {
        super(prefix, suffix, "; ", format);
    }

    public static Vector1DFormat getInstance() {
        return Vector1DFormat.getInstance(Locale.getDefault());
    }

    public static Vector1DFormat getInstance(Locale locale) {
        return new Vector1DFormat(CompositeFormat.getDefaultNumberFormat(locale));
    }

    @Override
    public StringBuffer format(Vector<Euclidean1D> vector, StringBuffer toAppendTo, FieldPosition pos) {
        Vector1D p1 = (Vector1D)vector;
        return this.format(toAppendTo, pos, p1.getX());
    }

    public Vector1D parse(String source) throws MathParseException {
        ParsePosition parsePosition = new ParsePosition(0);
        Vector1D result = this.parse(source, parsePosition);
        if (parsePosition.getIndex() == 0) {
            throw new MathParseException(source, parsePosition.getErrorIndex(), Vector1D.class);
        }
        return result;
    }

    public Vector1D parse(String source, ParsePosition pos) {
        double[] coordinates = this.parseCoordinates(1, source, pos);
        if (coordinates == null) {
            return null;
        }
        return new Vector1D(coordinates[0]);
    }
}

