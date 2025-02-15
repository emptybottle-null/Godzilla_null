/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.interpolation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.random.UnitSphereRandomVectorGenerator;
import org.apache.commons.math3.util.FastMath;

@Deprecated
public class MicrosphereInterpolatingFunction
implements MultivariateFunction {
    private final int dimension;
    private final List<MicrosphereSurfaceElement> microsphere;
    private final double brightnessExponent;
    private final Map<RealVector, Double> samples;

    public MicrosphereInterpolatingFunction(double[][] xval, double[] yval, int brightnessExponent, int microsphereElements, UnitSphereRandomVectorGenerator rand) throws DimensionMismatchException, NoDataException, NullArgumentException {
        int i;
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
        this.dimension = xval[0].length;
        this.brightnessExponent = brightnessExponent;
        this.samples = new HashMap<RealVector, Double>(yval.length);
        for (i = 0; i < xval.length; ++i) {
            double[] xvalI = xval[i];
            if (xvalI == null) {
                throw new NullArgumentException();
            }
            if (xvalI.length != this.dimension) {
                throw new DimensionMismatchException(xvalI.length, this.dimension);
            }
            this.samples.put(new ArrayRealVector(xvalI), yval[i]);
        }
        this.microsphere = new ArrayList<MicrosphereSurfaceElement>(microsphereElements);
        for (i = 0; i < microsphereElements; ++i) {
            this.microsphere.add(new MicrosphereSurfaceElement(rand.nextVector()));
        }
    }

    public double value(double[] point) throws DimensionMismatchException {
        ArrayRealVector p = new ArrayRealVector(point);
        for (MicrosphereSurfaceElement microsphereSurfaceElement : this.microsphere) {
            microsphereSurfaceElement.reset();
        }
        for (Map.Entry entry : this.samples.entrySet()) {
            RealVector diff = ((RealVector)entry.getKey()).subtract(p);
            double diffNorm = diff.getNorm();
            if (FastMath.abs(diffNorm) < FastMath.ulp(1.0)) {
                return (Double)entry.getValue();
            }
            for (MicrosphereSurfaceElement md : this.microsphere) {
                double w = FastMath.pow(diffNorm, -this.brightnessExponent);
                md.store(this.cosAngle(diff, md.normal()) * w, entry);
            }
        }
        double value = 0.0;
        double totalWeight = 0.0;
        for (MicrosphereSurfaceElement md : this.microsphere) {
            double iV = md.illumination();
            Map.Entry<RealVector, Double> sd = md.sample();
            if (sd == null) continue;
            value += iV * sd.getValue();
            totalWeight += iV;
        }
        return value / totalWeight;
    }

    private double cosAngle(RealVector v, RealVector w) {
        return v.dotProduct(w) / (v.getNorm() * w.getNorm());
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static class MicrosphereSurfaceElement {
        private final RealVector normal;
        private double brightestIllumination;
        private Map.Entry<RealVector, Double> brightestSample;

        MicrosphereSurfaceElement(double[] n) {
            this.normal = new ArrayRealVector(n);
        }

        RealVector normal() {
            return this.normal;
        }

        void reset() {
            this.brightestIllumination = 0.0;
            this.brightestSample = null;
        }

        void store(double illuminationFromSample, Map.Entry<RealVector, Double> sample) {
            if (illuminationFromSample > this.brightestIllumination) {
                this.brightestIllumination = illuminationFromSample;
                this.brightestSample = sample;
            }
        }

        double illumination() {
            return this.brightestIllumination;
        }

        Map.Entry<RealVector, Double> sample() {
            return this.brightestSample;
        }
    }
}

