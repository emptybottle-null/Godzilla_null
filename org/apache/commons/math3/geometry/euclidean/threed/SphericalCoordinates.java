/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.euclidean.threed;

import java.io.Serializable;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.util.FastMath;

public class SphericalCoordinates
implements Serializable {
    private static final long serialVersionUID = 20130206L;
    private final Vector3D v;
    private final double r;
    private final double theta;
    private final double phi;
    private double[][] jacobian;
    private double[][] rHessian;
    private double[][] thetaHessian;
    private double[][] phiHessian;

    public SphericalCoordinates(Vector3D v) {
        this.v = v;
        this.r = v.getNorm();
        this.theta = v.getAlpha();
        this.phi = FastMath.acos(v.getZ() / this.r);
    }

    public SphericalCoordinates(double r, double theta, double phi) {
        double cosTheta = FastMath.cos(theta);
        double sinTheta = FastMath.sin(theta);
        double cosPhi = FastMath.cos(phi);
        double sinPhi = FastMath.sin(phi);
        this.r = r;
        this.theta = theta;
        this.phi = phi;
        this.v = new Vector3D(r * cosTheta * sinPhi, r * sinTheta * sinPhi, r * cosPhi);
    }

    public Vector3D getCartesian() {
        return this.v;
    }

    public double getR() {
        return this.r;
    }

    public double getTheta() {
        return this.theta;
    }

    public double getPhi() {
        return this.phi;
    }

    public double[] toCartesianGradient(double[] sGradient) {
        this.computeJacobian();
        return new double[]{sGradient[0] * this.jacobian[0][0] + sGradient[1] * this.jacobian[1][0] + sGradient[2] * this.jacobian[2][0], sGradient[0] * this.jacobian[0][1] + sGradient[1] * this.jacobian[1][1] + sGradient[2] * this.jacobian[2][1], sGradient[0] * this.jacobian[0][2] + sGradient[2] * this.jacobian[2][2]};
    }

    public double[][] toCartesianHessian(double[][] sHessian, double[] sGradient) {
        this.computeJacobian();
        this.computeHessians();
        double[][] hj = new double[3][3];
        double[][] cHessian = new double[3][3];
        hj[0][0] = sHessian[0][0] * this.jacobian[0][0] + sHessian[1][0] * this.jacobian[1][0] + sHessian[2][0] * this.jacobian[2][0];
        hj[0][1] = sHessian[0][0] * this.jacobian[0][1] + sHessian[1][0] * this.jacobian[1][1] + sHessian[2][0] * this.jacobian[2][1];
        hj[0][2] = sHessian[0][0] * this.jacobian[0][2] + sHessian[2][0] * this.jacobian[2][2];
        hj[1][0] = sHessian[1][0] * this.jacobian[0][0] + sHessian[1][1] * this.jacobian[1][0] + sHessian[2][1] * this.jacobian[2][0];
        hj[1][1] = sHessian[1][0] * this.jacobian[0][1] + sHessian[1][1] * this.jacobian[1][1] + sHessian[2][1] * this.jacobian[2][1];
        hj[2][0] = sHessian[2][0] * this.jacobian[0][0] + sHessian[2][1] * this.jacobian[1][0] + sHessian[2][2] * this.jacobian[2][0];
        hj[2][1] = sHessian[2][0] * this.jacobian[0][1] + sHessian[2][1] * this.jacobian[1][1] + sHessian[2][2] * this.jacobian[2][1];
        hj[2][2] = sHessian[2][0] * this.jacobian[0][2] + sHessian[2][2] * this.jacobian[2][2];
        cHessian[0][0] = this.jacobian[0][0] * hj[0][0] + this.jacobian[1][0] * hj[1][0] + this.jacobian[2][0] * hj[2][0];
        cHessian[1][0] = this.jacobian[0][1] * hj[0][0] + this.jacobian[1][1] * hj[1][0] + this.jacobian[2][1] * hj[2][0];
        cHessian[2][0] = this.jacobian[0][2] * hj[0][0] + this.jacobian[2][2] * hj[2][0];
        cHessian[1][1] = this.jacobian[0][1] * hj[0][1] + this.jacobian[1][1] * hj[1][1] + this.jacobian[2][1] * hj[2][1];
        cHessian[2][1] = this.jacobian[0][2] * hj[0][1] + this.jacobian[2][2] * hj[2][1];
        cHessian[2][2] = this.jacobian[0][2] * hj[0][2] + this.jacobian[2][2] * hj[2][2];
        double[] dArray = cHessian[0];
        dArray[0] = dArray[0] + (sGradient[0] * this.rHessian[0][0] + sGradient[1] * this.thetaHessian[0][0] + sGradient[2] * this.phiHessian[0][0]);
        double[] dArray2 = cHessian[1];
        dArray2[0] = dArray2[0] + (sGradient[0] * this.rHessian[1][0] + sGradient[1] * this.thetaHessian[1][0] + sGradient[2] * this.phiHessian[1][0]);
        double[] dArray3 = cHessian[2];
        dArray3[0] = dArray3[0] + (sGradient[0] * this.rHessian[2][0] + sGradient[2] * this.phiHessian[2][0]);
        double[] dArray4 = cHessian[1];
        dArray4[1] = dArray4[1] + (sGradient[0] * this.rHessian[1][1] + sGradient[1] * this.thetaHessian[1][1] + sGradient[2] * this.phiHessian[1][1]);
        double[] dArray5 = cHessian[2];
        dArray5[1] = dArray5[1] + (sGradient[0] * this.rHessian[2][1] + sGradient[2] * this.phiHessian[2][1]);
        double[] dArray6 = cHessian[2];
        dArray6[2] = dArray6[2] + (sGradient[0] * this.rHessian[2][2] + sGradient[2] * this.phiHessian[2][2]);
        cHessian[0][1] = cHessian[1][0];
        cHessian[0][2] = cHessian[2][0];
        cHessian[1][2] = cHessian[2][1];
        return cHessian;
    }

    private void computeJacobian() {
        if (this.jacobian == null) {
            double x = this.v.getX();
            double y = this.v.getY();
            double z = this.v.getZ();
            double rho2 = x * x + y * y;
            double rho = FastMath.sqrt(rho2);
            double r2 = rho2 + z * z;
            this.jacobian = new double[3][3];
            this.jacobian[0][0] = x / this.r;
            this.jacobian[0][1] = y / this.r;
            this.jacobian[0][2] = z / this.r;
            this.jacobian[1][0] = -y / rho2;
            this.jacobian[1][1] = x / rho2;
            this.jacobian[2][0] = x * z / (rho * r2);
            this.jacobian[2][1] = y * z / (rho * r2);
            this.jacobian[2][2] = -rho / r2;
        }
    }

    private void computeHessians() {
        if (this.rHessian == null) {
            double x = this.v.getX();
            double y = this.v.getY();
            double z = this.v.getZ();
            double x2 = x * x;
            double y2 = y * y;
            double z2 = z * z;
            double rho2 = x2 + y2;
            double rho = FastMath.sqrt(rho2);
            double r2 = rho2 + z2;
            double xOr = x / this.r;
            double yOr = y / this.r;
            double zOr = z / this.r;
            double xOrho2 = x / rho2;
            double yOrho2 = y / rho2;
            double xOr3 = xOr / r2;
            double yOr3 = yOr / r2;
            double zOr3 = zOr / r2;
            this.rHessian = new double[3][3];
            this.rHessian[0][0] = y * yOr3 + z * zOr3;
            this.rHessian[1][0] = -x * yOr3;
            this.rHessian[2][0] = -z * xOr3;
            this.rHessian[1][1] = x * xOr3 + z * zOr3;
            this.rHessian[2][1] = -y * zOr3;
            this.rHessian[2][2] = x * xOr3 + y * yOr3;
            this.rHessian[0][1] = this.rHessian[1][0];
            this.rHessian[0][2] = this.rHessian[2][0];
            this.rHessian[1][2] = this.rHessian[2][1];
            this.thetaHessian = new double[2][2];
            this.thetaHessian[0][0] = 2.0 * xOrho2 * yOrho2;
            this.thetaHessian[1][0] = yOrho2 * yOrho2 - xOrho2 * xOrho2;
            this.thetaHessian[1][1] = -2.0 * xOrho2 * yOrho2;
            this.thetaHessian[0][1] = this.thetaHessian[1][0];
            double rhor2 = rho * r2;
            double rho2r2 = rho * rhor2;
            double rhor4 = rhor2 * r2;
            double rho3r4 = rhor4 * rho2;
            double r2P2rho2 = 3.0 * rho2 + z2;
            this.phiHessian = new double[3][3];
            this.phiHessian[0][0] = z * (rho2r2 - x2 * r2P2rho2) / rho3r4;
            this.phiHessian[1][0] = -x * y * z * r2P2rho2 / rho3r4;
            this.phiHessian[2][0] = x * (rho2 - z2) / rhor4;
            this.phiHessian[1][1] = z * (rho2r2 - y2 * r2P2rho2) / rho3r4;
            this.phiHessian[2][1] = y * (rho2 - z2) / rhor4;
            this.phiHessian[2][2] = 2.0 * rho * zOr3 / this.r;
            this.phiHessian[0][1] = this.phiHessian[1][0];
            this.phiHessian[0][2] = this.phiHessian[2][0];
            this.phiHessian[1][2] = this.phiHessian[2][1];
        }
    }

    private Object writeReplace() {
        return new DataTransferObject(this.v.getX(), this.v.getY(), this.v.getZ());
    }

    private static class DataTransferObject
    implements Serializable {
        private static final long serialVersionUID = 20130206L;
        private final double x;
        private final double y;
        private final double z;

        DataTransferObject(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        private Object readResolve() {
            return new SphericalCoordinates(new Vector3D(this.x, this.y, this.z));
        }
    }
}

