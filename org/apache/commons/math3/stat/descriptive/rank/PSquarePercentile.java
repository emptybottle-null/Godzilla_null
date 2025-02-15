/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.descriptive.rank;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.interpolation.LinearInterpolator;
import org.apache.commons.math3.analysis.interpolation.NevilleInterpolator;
import org.apache.commons.math3.analysis.interpolation.UnivariateInterpolator;
import org.apache.commons.math3.exception.InsufficientDataException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
import org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class PSquarePercentile
extends AbstractStorelessUnivariateStatistic
implements StorelessUnivariateStatistic,
Serializable {
    private static final int PSQUARE_CONSTANT = 5;
    private static final double DEFAULT_QUANTILE_DESIRED = 50.0;
    private static final long serialVersionUID = 2283912083175715479L;
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("00.00");
    private final List<Double> initialFive = new FixedCapacityList<Double>(5);
    private final double quantile;
    private transient double lastObservation;
    private PSquareMarkers markers = null;
    private double pValue = Double.NaN;
    private long countOfObservations;

    public PSquarePercentile(double p) {
        if (p > 100.0 || p < 0.0) {
            throw new OutOfRangeException((Localizable)LocalizedFormats.OUT_OF_RANGE, (Number)p, 0, 100);
        }
        this.quantile = p / 100.0;
    }

    PSquarePercentile() {
        this(50.0);
    }

    @Override
    public int hashCode() {
        double result = this.getResult();
        result = Double.isNaN(result) ? 37.0 : result;
        double markersHash = this.markers == null ? 0.0 : (double)this.markers.hashCode();
        double[] toHash = new double[]{result, this.quantile, markersHash, this.countOfObservations};
        return Arrays.hashCode(toHash);
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (this == o) {
            result = true;
        } else if (o != null && o instanceof PSquarePercentile) {
            PSquarePercentile that = (PSquarePercentile)o;
            boolean isNotNull = this.markers != null && that.markers != null;
            boolean isNull = this.markers == null && that.markers == null;
            result = isNotNull ? this.markers.equals(that.markers) : isNull;
            result = result && this.getN() == that.getN();
        }
        return result;
    }

    @Override
    public void increment(double observation) {
        ++this.countOfObservations;
        this.lastObservation = observation;
        if (this.markers == null) {
            if (this.initialFive.add(observation)) {
                Collections.sort(this.initialFive);
                this.pValue = this.initialFive.get((int)(this.quantile * (double)(this.initialFive.size() - 1)));
                return;
            }
            this.markers = PSquarePercentile.newMarkers(this.initialFive, this.quantile);
        }
        this.pValue = this.markers.processDataPoint(observation);
    }

    public String toString() {
        if (this.markers == null) {
            return String.format("obs=%s pValue=%s", DECIMAL_FORMAT.format(this.lastObservation), DECIMAL_FORMAT.format(this.pValue));
        }
        return String.format("obs=%s markers=%s", DECIMAL_FORMAT.format(this.lastObservation), this.markers.toString());
    }

    @Override
    public long getN() {
        return this.countOfObservations;
    }

    @Override
    public StorelessUnivariateStatistic copy() {
        PSquarePercentile copy = new PSquarePercentile(100.0 * this.quantile);
        if (this.markers != null) {
            copy.markers = (PSquareMarkers)this.markers.clone();
        }
        copy.countOfObservations = this.countOfObservations;
        copy.pValue = this.pValue;
        copy.initialFive.clear();
        copy.initialFive.addAll(this.initialFive);
        return copy;
    }

    public double quantile() {
        return this.quantile;
    }

    @Override
    public void clear() {
        this.markers = null;
        this.initialFive.clear();
        this.countOfObservations = 0L;
        this.pValue = Double.NaN;
    }

    @Override
    public double getResult() {
        if (Double.compare(this.quantile, 1.0) == 0) {
            this.pValue = this.maximum();
        } else if (Double.compare(this.quantile, 0.0) == 0) {
            this.pValue = this.minimum();
        }
        return this.pValue;
    }

    private double maximum() {
        double val = Double.NaN;
        if (this.markers != null) {
            val = this.markers.height(5);
        } else if (!this.initialFive.isEmpty()) {
            val = this.initialFive.get(this.initialFive.size() - 1);
        }
        return val;
    }

    private double minimum() {
        double val = Double.NaN;
        if (this.markers != null) {
            val = this.markers.height(1);
        } else if (!this.initialFive.isEmpty()) {
            val = this.initialFive.get(0);
        }
        return val;
    }

    public static PSquareMarkers newMarkers(List<Double> initialFive, double p) {
        return new Markers(initialFive, p);
    }

    protected static interface PSquareMarkers
    extends Cloneable {
        public double getPercentileValue();

        public Object clone();

        public double height(int var1);

        public double processDataPoint(double var1);

        public double estimate(int var1);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static class FixedCapacityList<E>
    extends ArrayList<E>
    implements Serializable {
        private static final long serialVersionUID = 2283952083075725479L;
        private final int capacity;

        FixedCapacityList(int fixedCapacity) {
            super(fixedCapacity);
            this.capacity = fixedCapacity;
        }

        @Override
        public boolean add(E e) {
            return this.size() < this.capacity ? super.add(e) : false;
        }

        @Override
        public boolean addAll(Collection<? extends E> collection) {
            boolean isCollectionLess = collection != null && collection.size() + this.size() <= this.capacity;
            return isCollectionLess ? super.addAll(collection) : false;
        }
    }

    private static class Marker
    implements Serializable,
    Cloneable {
        private static final long serialVersionUID = -3575879478288538431L;
        private int index;
        private double intMarkerPosition;
        private double desiredMarkerPosition;
        private double markerHeight;
        private double desiredMarkerIncrement;
        private transient Marker next;
        private transient Marker previous;
        private final UnivariateInterpolator nonLinear = new NevilleInterpolator();
        private transient UnivariateInterpolator linear = new LinearInterpolator();

        private Marker() {
            this.next = this.previous = this;
        }

        private Marker(double heightOfMarker, double makerPositionDesired, double markerPositionIncrement, double markerPositionNumber) {
            this();
            this.markerHeight = heightOfMarker;
            this.desiredMarkerPosition = makerPositionDesired;
            this.desiredMarkerIncrement = markerPositionIncrement;
            this.intMarkerPosition = markerPositionNumber;
        }

        private Marker previous(Marker previousMarker) {
            MathUtils.checkNotNull(previousMarker);
            this.previous = previousMarker;
            return this;
        }

        private Marker next(Marker nextMarker) {
            MathUtils.checkNotNull(nextMarker);
            this.next = nextMarker;
            return this;
        }

        private Marker index(int indexOfMarker) {
            this.index = indexOfMarker;
            return this;
        }

        private void updateDesiredPosition() {
            this.desiredMarkerPosition += this.desiredMarkerIncrement;
        }

        private void incrementPosition(int d) {
            this.intMarkerPosition += (double)d;
        }

        private double difference() {
            return this.desiredMarkerPosition - this.intMarkerPosition;
        }

        private double estimate() {
            boolean isPreviousLower;
            double di = this.difference();
            boolean isNextHigher = this.next.intMarkerPosition - this.intMarkerPosition > 1.0;
            boolean bl = isPreviousLower = this.previous.intMarkerPosition - this.intMarkerPosition < -1.0;
            if (di >= 1.0 && isNextHigher || di <= -1.0 && isPreviousLower) {
                int d = di >= 0.0 ? 1 : -1;
                double[] xval = new double[]{this.previous.intMarkerPosition, this.intMarkerPosition, this.next.intMarkerPosition};
                double[] yval = new double[]{this.previous.markerHeight, this.markerHeight, this.next.markerHeight};
                double xD = this.intMarkerPosition + (double)d;
                UnivariateFunction univariateFunction = this.nonLinear.interpolate(xval, yval);
                this.markerHeight = univariateFunction.value(xD);
                if (this.isEstimateBad(yval, this.markerHeight)) {
                    int delta = xD - xval[1] > 0.0 ? 1 : -1;
                    double[] xBad = new double[]{xval[1], xval[1 + delta]};
                    double[] yBad = new double[]{yval[1], yval[1 + delta]};
                    MathArrays.sortInPlace(xBad, new double[][]{yBad});
                    univariateFunction = this.linear.interpolate(xBad, yBad);
                    this.markerHeight = univariateFunction.value(xD);
                }
                this.incrementPosition(d);
            }
            return this.markerHeight;
        }

        private boolean isEstimateBad(double[] y, double yD) {
            return yD <= y[0] || yD >= y[2];
        }

        public boolean equals(Object o) {
            boolean result = false;
            if (this == o) {
                result = true;
            } else if (o != null && o instanceof Marker) {
                Marker that = (Marker)o;
                result = Double.compare(this.markerHeight, that.markerHeight) == 0;
                result = result && Double.compare(this.intMarkerPosition, that.intMarkerPosition) == 0;
                result = result && Double.compare(this.desiredMarkerPosition, that.desiredMarkerPosition) == 0;
                result = result && Double.compare(this.desiredMarkerIncrement, that.desiredMarkerIncrement) == 0;
                result = result && this.next.index == that.next.index;
                result = result && this.previous.index == that.previous.index;
            }
            return result;
        }

        public int hashCode() {
            return Arrays.hashCode(new double[]{this.markerHeight, this.intMarkerPosition, this.desiredMarkerIncrement, this.desiredMarkerPosition, this.previous.index, this.next.index});
        }

        private void readObject(ObjectInputStream anInstream) throws ClassNotFoundException, IOException {
            anInstream.defaultReadObject();
            this.previous = this.next = this;
            this.linear = new LinearInterpolator();
        }

        public Object clone() {
            return new Marker(this.markerHeight, this.desiredMarkerPosition, this.desiredMarkerIncrement, this.intMarkerPosition);
        }

        public String toString() {
            return String.format("index=%.0f,n=%.0f,np=%.2f,q=%.2f,dn=%.2f,prev=%d,next=%d", this.index, Precision.round(this.intMarkerPosition, 0), Precision.round(this.desiredMarkerPosition, 2), Precision.round(this.markerHeight, 2), Precision.round(this.desiredMarkerIncrement, 2), this.previous.index, this.next.index);
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static class Markers
    implements PSquareMarkers,
    Serializable {
        private static final long serialVersionUID = 1L;
        private static final int LOW = 2;
        private static final int HIGH = 4;
        private final Marker[] markerArray;
        private transient int k = -1;

        private Markers(Marker[] theMarkerArray) {
            MathUtils.checkNotNull(theMarkerArray);
            this.markerArray = theMarkerArray;
            for (int i = 1; i < 5; ++i) {
                this.markerArray[i].previous(this.markerArray[i - 1]).next(this.markerArray[i + 1]).index(i);
            }
            this.markerArray[0].previous(this.markerArray[0]).next(this.markerArray[1]).index(0);
            this.markerArray[5].previous(this.markerArray[4]).next(this.markerArray[5]).index(5);
        }

        private Markers(List<Double> initialFive, double p) {
            this(Markers.createMarkerArray(initialFive, p));
        }

        private static Marker[] createMarkerArray(List<Double> initialFive, double p) {
            int countObserved;
            int n = countObserved = initialFive == null ? -1 : initialFive.size();
            if (countObserved < 5) {
                throw new InsufficientDataException(LocalizedFormats.INSUFFICIENT_OBSERVED_POINTS_IN_SAMPLE, countObserved, 5);
            }
            Collections.sort(initialFive);
            return new Marker[]{new Marker(), new Marker(initialFive.get(0), 1.0, 0.0, 1.0), new Marker(initialFive.get(1), 1.0 + 2.0 * p, p / 2.0, 2.0), new Marker(initialFive.get(2), 1.0 + 4.0 * p, p, 3.0), new Marker(initialFive.get(3), 3.0 + 2.0 * p, (1.0 + p) / 2.0, 4.0), new Marker(initialFive.get(4), 5.0, 1.0, 5.0)};
        }

        public int hashCode() {
            return Arrays.deepHashCode(this.markerArray);
        }

        public boolean equals(Object o) {
            boolean result = false;
            if (this == o) {
                result = true;
            } else if (o != null && o instanceof Markers) {
                Markers that = (Markers)o;
                result = Arrays.deepEquals(this.markerArray, that.markerArray);
            }
            return result;
        }

        @Override
        public double processDataPoint(double inputDataPoint) {
            int kthCell = this.findCellAndUpdateMinMax(inputDataPoint);
            this.incrementPositions(1, kthCell + 1, 5);
            this.updateDesiredPositions();
            this.adjustHeightsOfMarkers();
            return this.getPercentileValue();
        }

        @Override
        public double getPercentileValue() {
            return this.height(3);
        }

        private int findCellAndUpdateMinMax(double observation) {
            this.k = -1;
            if (observation < this.height(1)) {
                this.markerArray[1].markerHeight = observation;
                this.k = 1;
            } else if (observation < this.height(2)) {
                this.k = 1;
            } else if (observation < this.height(3)) {
                this.k = 2;
            } else if (observation < this.height(4)) {
                this.k = 3;
            } else if (observation <= this.height(5)) {
                this.k = 4;
            } else {
                this.markerArray[5].markerHeight = observation;
                this.k = 4;
            }
            return this.k;
        }

        private void adjustHeightsOfMarkers() {
            for (int i = 2; i <= 4; ++i) {
                this.estimate(i);
            }
        }

        @Override
        public double estimate(int index) {
            if (index < 2 || index > 4) {
                throw new OutOfRangeException(index, (Number)2, 4);
            }
            return this.markerArray[index].estimate();
        }

        private void incrementPositions(int d, int startIndex, int endIndex) {
            for (int i = startIndex; i <= endIndex; ++i) {
                this.markerArray[i].incrementPosition(d);
            }
        }

        private void updateDesiredPositions() {
            for (int i = 1; i < this.markerArray.length; ++i) {
                this.markerArray[i].updateDesiredPosition();
            }
        }

        private void readObject(ObjectInputStream anInputStream) throws ClassNotFoundException, IOException {
            anInputStream.defaultReadObject();
            for (int i = 1; i < 5; ++i) {
                this.markerArray[i].previous(this.markerArray[i - 1]).next(this.markerArray[i + 1]).index(i);
            }
            this.markerArray[0].previous(this.markerArray[0]).next(this.markerArray[1]).index(0);
            this.markerArray[5].previous(this.markerArray[4]).next(this.markerArray[5]).index(5);
        }

        @Override
        public double height(int markerIndex) {
            if (markerIndex >= this.markerArray.length || markerIndex <= 0) {
                throw new OutOfRangeException(markerIndex, (Number)1, this.markerArray.length);
            }
            return this.markerArray[markerIndex].markerHeight;
        }

        @Override
        public Object clone() {
            return new Markers(new Marker[]{new Marker(), (Marker)this.markerArray[1].clone(), (Marker)this.markerArray[2].clone(), (Marker)this.markerArray[3].clone(), (Marker)this.markerArray[4].clone(), (Marker)this.markerArray[5].clone()});
        }

        public String toString() {
            return String.format("m1=[%s],m2=[%s],m3=[%s],m4=[%s],m5=[%s]", this.markerArray[1].toString(), this.markerArray[2].toString(), this.markerArray[3].toString(), this.markerArray[4].toString(), this.markerArray[5].toString());
        }
    }
}

