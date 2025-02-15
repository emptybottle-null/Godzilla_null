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
import org.apache.commons.math3.optimization.linear.Relationship;

@Deprecated
public class LinearConstraint
implements Serializable {
    private static final long serialVersionUID = -764632794033034092L;
    private final transient RealVector coefficients;
    private final Relationship relationship;
    private final double value;

    public LinearConstraint(double[] coefficients, Relationship relationship, double value) {
        this(new ArrayRealVector(coefficients), relationship, value);
    }

    public LinearConstraint(RealVector coefficients, Relationship relationship, double value) {
        this.coefficients = coefficients;
        this.relationship = relationship;
        this.value = value;
    }

    public LinearConstraint(double[] lhsCoefficients, double lhsConstant, Relationship relationship, double[] rhsCoefficients, double rhsConstant) {
        double[] sub = new double[lhsCoefficients.length];
        for (int i = 0; i < sub.length; ++i) {
            sub[i] = lhsCoefficients[i] - rhsCoefficients[i];
        }
        this.coefficients = new ArrayRealVector(sub, false);
        this.relationship = relationship;
        this.value = rhsConstant - lhsConstant;
    }

    public LinearConstraint(RealVector lhsCoefficients, double lhsConstant, Relationship relationship, RealVector rhsCoefficients, double rhsConstant) {
        this.coefficients = lhsCoefficients.subtract(rhsCoefficients);
        this.relationship = relationship;
        this.value = rhsConstant - lhsConstant;
    }

    public RealVector getCoefficients() {
        return this.coefficients;
    }

    public Relationship getRelationship() {
        return this.relationship;
    }

    public double getValue() {
        return this.value;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof LinearConstraint) {
            LinearConstraint rhs = (LinearConstraint)other;
            return this.relationship == rhs.relationship && this.value == rhs.value && this.coefficients.equals(rhs.coefficients);
        }
        return false;
    }

    public int hashCode() {
        return this.relationship.hashCode() ^ Double.valueOf(this.value).hashCode() ^ this.coefficients.hashCode();
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

