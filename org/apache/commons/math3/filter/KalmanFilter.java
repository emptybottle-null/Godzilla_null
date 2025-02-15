/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.filter;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.filter.MeasurementModel;
import org.apache.commons.math3.filter.ProcessModel;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.CholeskyDecomposition;
import org.apache.commons.math3.linear.MatrixDimensionMismatchException;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.NonSquareMatrixException;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.SingularMatrixException;
import org.apache.commons.math3.util.MathUtils;

public class KalmanFilter {
    private final ProcessModel processModel;
    private final MeasurementModel measurementModel;
    private RealMatrix transitionMatrix;
    private RealMatrix transitionMatrixT;
    private RealMatrix controlMatrix;
    private RealMatrix measurementMatrix;
    private RealMatrix measurementMatrixT;
    private RealVector stateEstimation;
    private RealMatrix errorCovariance;

    public KalmanFilter(ProcessModel process, MeasurementModel measurement) throws NullArgumentException, NonSquareMatrixException, DimensionMismatchException, MatrixDimensionMismatchException {
        MathUtils.checkNotNull(process);
        MathUtils.checkNotNull(measurement);
        this.processModel = process;
        this.measurementModel = measurement;
        this.transitionMatrix = this.processModel.getStateTransitionMatrix();
        MathUtils.checkNotNull(this.transitionMatrix);
        this.transitionMatrixT = this.transitionMatrix.transpose();
        this.controlMatrix = this.processModel.getControlMatrix() == null ? new Array2DRowRealMatrix() : this.processModel.getControlMatrix();
        this.measurementMatrix = this.measurementModel.getMeasurementMatrix();
        MathUtils.checkNotNull(this.measurementMatrix);
        this.measurementMatrixT = this.measurementMatrix.transpose();
        RealMatrix processNoise = this.processModel.getProcessNoise();
        MathUtils.checkNotNull(processNoise);
        RealMatrix measNoise = this.measurementModel.getMeasurementNoise();
        MathUtils.checkNotNull(measNoise);
        this.stateEstimation = this.processModel.getInitialStateEstimate() == null ? new ArrayRealVector(this.transitionMatrix.getColumnDimension()) : this.processModel.getInitialStateEstimate();
        if (this.transitionMatrix.getColumnDimension() != this.stateEstimation.getDimension()) {
            throw new DimensionMismatchException(this.transitionMatrix.getColumnDimension(), this.stateEstimation.getDimension());
        }
        this.errorCovariance = this.processModel.getInitialErrorCovariance() == null ? processNoise.copy() : this.processModel.getInitialErrorCovariance();
        if (!this.transitionMatrix.isSquare()) {
            throw new NonSquareMatrixException(this.transitionMatrix.getRowDimension(), this.transitionMatrix.getColumnDimension());
        }
        if (this.controlMatrix != null && this.controlMatrix.getRowDimension() > 0 && this.controlMatrix.getColumnDimension() > 0 && this.controlMatrix.getRowDimension() != this.transitionMatrix.getRowDimension()) {
            throw new MatrixDimensionMismatchException(this.controlMatrix.getRowDimension(), this.controlMatrix.getColumnDimension(), this.transitionMatrix.getRowDimension(), this.controlMatrix.getColumnDimension());
        }
        MatrixUtils.checkAdditionCompatible(this.transitionMatrix, processNoise);
        if (this.measurementMatrix.getColumnDimension() != this.transitionMatrix.getRowDimension()) {
            throw new MatrixDimensionMismatchException(this.measurementMatrix.getRowDimension(), this.measurementMatrix.getColumnDimension(), this.measurementMatrix.getRowDimension(), this.transitionMatrix.getRowDimension());
        }
        if (measNoise.getRowDimension() != this.measurementMatrix.getRowDimension()) {
            throw new MatrixDimensionMismatchException(measNoise.getRowDimension(), measNoise.getColumnDimension(), this.measurementMatrix.getRowDimension(), measNoise.getColumnDimension());
        }
    }

    public int getStateDimension() {
        return this.stateEstimation.getDimension();
    }

    public int getMeasurementDimension() {
        return this.measurementMatrix.getRowDimension();
    }

    public double[] getStateEstimation() {
        return this.stateEstimation.toArray();
    }

    public RealVector getStateEstimationVector() {
        return this.stateEstimation.copy();
    }

    public double[][] getErrorCovariance() {
        return this.errorCovariance.getData();
    }

    public RealMatrix getErrorCovarianceMatrix() {
        return this.errorCovariance.copy();
    }

    public void predict() {
        this.predict((RealVector)null);
    }

    public void predict(double[] u) throws DimensionMismatchException {
        this.predict(new ArrayRealVector(u, false));
    }

    public void predict(RealVector u) throws DimensionMismatchException {
        if (u != null && u.getDimension() != this.controlMatrix.getColumnDimension()) {
            throw new DimensionMismatchException(u.getDimension(), this.controlMatrix.getColumnDimension());
        }
        this.stateEstimation = this.transitionMatrix.operate(this.stateEstimation);
        if (u != null) {
            this.stateEstimation = this.stateEstimation.add(this.controlMatrix.operate(u));
        }
        this.errorCovariance = this.transitionMatrix.multiply(this.errorCovariance).multiply(this.transitionMatrixT).add(this.processModel.getProcessNoise());
    }

    public void correct(double[] z) throws NullArgumentException, DimensionMismatchException, SingularMatrixException {
        this.correct(new ArrayRealVector(z, false));
    }

    public void correct(RealVector z) throws NullArgumentException, DimensionMismatchException, SingularMatrixException {
        MathUtils.checkNotNull(z);
        if (z.getDimension() != this.measurementMatrix.getRowDimension()) {
            throw new DimensionMismatchException(z.getDimension(), this.measurementMatrix.getRowDimension());
        }
        RealMatrix s = this.measurementMatrix.multiply(this.errorCovariance).multiply(this.measurementMatrixT).add(this.measurementModel.getMeasurementNoise());
        RealVector innovation = z.subtract(this.measurementMatrix.operate(this.stateEstimation));
        RealMatrix kalmanGain = new CholeskyDecomposition(s).getSolver().solve(this.measurementMatrix.multiply(this.errorCovariance.transpose())).transpose();
        this.stateEstimation = this.stateEstimation.add(kalmanGain.operate(innovation));
        RealMatrix identity = MatrixUtils.createRealIdentityMatrix(kalmanGain.getRowDimension());
        this.errorCovariance = identity.subtract(kalmanGain.multiply(this.measurementMatrix)).multiply(this.errorCovariance);
    }
}

