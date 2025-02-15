/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optimization.linear;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealVector;

@Deprecated
public class LinearObjectiveFunction
implements Serializable {
    private static final long serialVersionUID = -4531815507568396090L;
    private final transient RealVector coefficients;
    private final double constantTerm;

    public LinearObjectiveFunction(double[] coefficients, double constantTerm) {
        this(new ArrayRealVector(coefficients), constantTerm);
    }

    public LinearObjectiveFunction(RealVector coefficients, double constantTerm) {
        this.coefficients = coefficients;
        this.constantTerm = constantTerm;
    }

    public RealVector getCoefficients() {
        return this.coefficients;
    }

    public double getConstantTerm() {
        return this.constantTerm;
    }

    public double getValue(double[] point) {
        return this.coefficients.dotProduct(new ArrayRealVector(point, false)) + this.constantTerm;
    }

    public double getValue(RealVector point) {
        return this.coefficients.dotProduct(point) + this.constantTerm;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof LinearObjectiveFunction) {
            LinearObjectiveFunction rhs = (LinearObjectiveFunction)other;
            return this.constantTerm == rhs.constantTerm && this.coefficients.equals(rhs.coefficients);
        }
        return false;
    }

    public int hashCode() {
        return Double.valueOf(this.constantTerm).hashCode() ^ this.coefficients.hashCode();
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        MatrixUtils.serializeRealVector(this.coefficients, oos);
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        MatrixUtils.deserializeRealVector(this, "coefficients", ois);
    }
}

