/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.math;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.math.DoubleUtils;
import com.google.errorprone.annotations.concurrent.LazyInit;

@Beta
@GwtIncompatible
public abstract class LinearTransformation {
    public static LinearTransformationBuilder mapping(double x1, double y1) {
        Preconditions.checkArgument(DoubleUtils.isFinite(x1) && DoubleUtils.isFinite(y1));
        return new LinearTransformationBuilder(x1, y1);
    }

    public static LinearTransformation vertical(double x) {
        Preconditions.checkArgument(DoubleUtils.isFinite(x));
        return new VerticalLinearTransformation(x);
    }

    public static LinearTransformation horizontal(double y) {
        Preconditions.checkArgument(DoubleUtils.isFinite(y));
        double slope = 0.0;
        return new RegularLinearTransformation(slope, y);
    }

    public static LinearTransformation forNaN() {
        return NaNLinearTransformation.INSTANCE;
    }

    public abstract boolean isVertical();

    public abstract boolean isHorizontal();

    public abstract double slope();

    public abstract double transform(double var1);

    public abstract LinearTransformation inverse();

    private static final class NaNLinearTransformation
    extends LinearTransformation {
        static final NaNLinearTransformation INSTANCE = new NaNLinearTransformation();

        private NaNLinearTransformation() {
        }

        @Override
        public boolean isVertical() {
            return false;
        }

        @Override
        public boolean isHorizontal() {
            return false;
        }

        @Override
        public double slope() {
            return Double.NaN;
        }

        @Override
        public double transform(double x) {
            return Double.NaN;
        }

        @Override
        public LinearTransformation inverse() {
            return this;
        }

        public String toString() {
            return "NaN";
        }
    }

    private static final class VerticalLinearTransformation
    extends LinearTransformation {
        final double x;
        @LazyInit
        LinearTransformation inverse;

        VerticalLinearTransformation(double x) {
            this.x = x;
            this.inverse = null;
        }

        VerticalLinearTransformation(double x, LinearTransformation inverse) {
            this.x = x;
            this.inverse = inverse;
        }

        @Override
        public boolean isVertical() {
            return true;
        }

        @Override
        public boolean isHorizontal() {
            return false;
        }

        @Override
        public double slope() {
            throw new IllegalStateException();
        }

        @Override
        public double transform(double x) {
            throw new IllegalStateException();
        }

        @Override
        public LinearTransformation inverse() {
            LinearTransformation result = this.inverse;
            return result == null ? (this.inverse = this.createInverse()) : result;
        }

        public String toString() {
            return String.format("x = %g", this.x);
        }

        private LinearTransformation createInverse() {
            return new RegularLinearTransformation(0.0, this.x, this);
        }
    }

    private static final class RegularLinearTransformation
    extends LinearTransformation {
        final double slope;
        final double yIntercept;
        @LazyInit
        LinearTransformation inverse;

        RegularLinearTransformation(double slope, double yIntercept) {
            this.slope = slope;
            this.yIntercept = yIntercept;
            this.inverse = null;
        }

        RegularLinearTransformation(double slope, double yIntercept, LinearTransformation inverse) {
            this.slope = slope;
            this.yIntercept = yIntercept;
            this.inverse = inverse;
        }

        @Override
        public boolean isVertical() {
            return false;
        }

        @Override
        public boolean isHorizontal() {
            return this.slope == 0.0;
        }

        @Override
        public double slope() {
            return this.slope;
        }

        @Override
        public double transform(double x) {
            return x * this.slope + this.yIntercept;
        }

        @Override
        public LinearTransformation inverse() {
            LinearTransformation result = this.inverse;
            return result == null ? (this.inverse = this.createInverse()) : result;
        }

        public String toString() {
            return String.format("y = %g * x + %g", this.slope, this.yIntercept);
        }

        private LinearTransformation createInverse() {
            if (this.slope != 0.0) {
                return new RegularLinearTransformation(1.0 / this.slope, -1.0 * this.yIntercept / this.slope, this);
            }
            return new VerticalLinearTransformation(this.yIntercept, this);
        }
    }

    public static final class LinearTransformationBuilder {
        private final double x1;
        private final double y1;

        private LinearTransformationBuilder(double x1, double y1) {
            this.x1 = x1;
            this.y1 = y1;
        }

        public LinearTransformation and(double x2, double y2) {
            Preconditions.checkArgument(DoubleUtils.isFinite(x2) && DoubleUtils.isFinite(y2));
            if (x2 == this.x1) {
                Preconditions.checkArgument(y2 != this.y1);
                return new VerticalLinearTransformation(this.x1);
            }
            return this.withSlope((y2 - this.y1) / (x2 - this.x1));
        }

        public LinearTransformation withSlope(double slope) {
            Preconditions.checkArgument(!Double.isNaN(slope));
            if (DoubleUtils.isFinite(slope)) {
                double yIntercept = this.y1 - this.x1 * slope;
                return new RegularLinearTransformation(slope, yIntercept);
            }
            return new VerticalLinearTransformation(this.x1);
        }
    }
}

