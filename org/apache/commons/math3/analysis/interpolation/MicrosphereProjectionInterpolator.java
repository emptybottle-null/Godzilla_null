/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere;
import org.apache.commons.math3.analysis.interpolation.MultivariateInterpolator;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.random.UnitSphereRandomVectorGenerator;

public class MicrosphereProjectionInterpolator
implements MultivariateInterpolator {
    private final double exponent;
    private final InterpolatingMicrosphere microsphere;
    private final boolean sharedSphere;
    private final double noInterpolationTolerance;

    public MicrosphereProjectionInterpolator(int dimension, int elements, double maxDarkFraction, double darkThreshold, double background, double exponent, boolean sharedSphere, double noInterpolationTolerance) {
        this(new InterpolatingMicrosphere(dimension, elements, maxDarkFraction, darkThreshold, background, new UnitSphereRandomVectorGenerator(dimension)), exponent, sharedSphere, noInterpolationTolerance);
    }

    public MicrosphereProjectionInterpolator(InterpolatingMicrosphere microsphere, double exponent, boolean sharedSphere, double noInterpolationTolerance) throws NotPositiveException {
        if (exponent < 0.0) {
            throw new NotPositiveException(exponent);
        }
        this.microsphere = microsphere;
        this.exponent = exponent;
        this.sharedSphere = sharedSphere;
        this.noInterpolationTolerance = noInterpolationTolerance;
    }

    public MultivariateFunction interpolate(final double[][] xval, final double[] yval) throws DimensionMismatchException, NoDataException, NullArgumentException {
        if (xval == null || yval == null) {
            throw new NullArgumentException();
        }
        if (xval.length == 0) {
            throw new NoDataException();
        }
        if (xval.length != yval.length) {
            throw new DimensionMismatchException(xval.length, yval.length);
        }
        if (xval[0] == null) {
            throw new NullArgumentException();
        }
        int dimension = this.microsphere.getDimension();
        if (dimension != xval[0].length) {
            throw new DimensionMismatchException(xval[0].length, dimension);
        }
        final InterpolatingMicrosphere m = this.sharedSphere ? this.microsphere : this.microsphere.copy();
        return new MultivariateFunction(){

            public double value(double[] point) {
                return m.value(point, xval, yval, MicrosphereProjectionInterpolator.this.exponent, MicrosphereProjectionInterpolator.this.noInterpolationTolerance);
            }
        };
    }
}

