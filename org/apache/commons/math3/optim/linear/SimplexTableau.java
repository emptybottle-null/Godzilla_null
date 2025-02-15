/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optim.linear;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.util.Precision;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
class SimplexTableau
implements Serializable {
    private static final String NEGATIVE_VAR_COLUMN_LABEL = "x-";
    private static final long serialVersionUID = -1369660067587938365L;
    private final LinearObjectiveFunction f;
    private final List<LinearConstraint> constraints;
    private final boolean restrictToNonNegative;
    private final List<String> columnLabels = new ArrayList<String>();
    private transient Array2DRowRealMatrix tableau;
    private final int numDecisionVariables;
    private final int numSlackVariables;
    private int numArtificialVariables;
    private final double epsilon;
    private final int maxUlps;
    private int[] basicVariables;
    private int[] basicRows;

    SimplexTableau(LinearObjectiveFunction f, Collection<LinearConstraint> constraints, GoalType goalType, boolean restrictToNonNegative, double epsilon) {
        this(f, constraints, goalType, restrictToNonNegative, epsilon, 10);
    }

    SimplexTableau(LinearObjectiveFunction f, Collection<LinearConstraint> constraints, GoalType goalType, boolean restrictToNonNegative, double epsilon, int maxUlps) {
        this.f = f;
        this.constraints = this.normalizeConstraints(constraints);
        this.restrictToNonNegative = restrictToNonNegative;
        this.epsilon = epsilon;
        this.maxUlps = maxUlps;
        this.numDecisionVariables = f.getCoefficients().getDimension() + (restrictToNonNegative ? 0 : 1);
        this.numSlackVariables = this.getConstraintTypeCounts(Relationship.LEQ) + this.getConstraintTypeCounts(Relationship.GEQ);
        this.numArtificialVariables = this.getConstraintTypeCounts(Relationship.EQ) + this.getConstraintTypeCounts(Relationship.GEQ);
        this.tableau = this.createTableau(goalType == GoalType.MAXIMIZE);
        this.initializeBasicVariables(this.getSlackVariableOffset());
        this.initializeColumnLabels();
    }

    protected void initializeColumnLabels() {
        int i;
        if (this.getNumObjectiveFunctions() == 2) {
            this.columnLabels.add("W");
        }
        this.columnLabels.add("Z");
        for (i = 0; i < this.getOriginalNumDecisionVariables(); ++i) {
            this.columnLabels.add("x" + i);
        }
        if (!this.restrictToNonNegative) {
            this.columnLabels.add(NEGATIVE_VAR_COLUMN_LABEL);
        }
        for (i = 0; i < this.getNumSlackVariables(); ++i) {
            this.columnLabels.add("s" + i);
        }
        for (i = 0; i < this.getNumArtificialVariables(); ++i) {
            this.columnLabels.add("a" + i);
        }
        this.columnLabels.add("RHS");
    }

    protected Array2DRowRealMatrix createTableau(boolean maximize) {
        int width = this.numDecisionVariables + this.numSlackVariables + this.numArtificialVariables + this.getNumObjectiveFunctions() + 1;
        int height = this.constraints.size() + this.getNumObjectiveFunctions();
        Array2DRowRealMatrix matrix = new Array2DRowRealMatrix(height, width);
        if (this.getNumObjectiveFunctions() == 2) {
            matrix.setEntry(0, 0, -1.0);
        }
        int zIndex = this.getNumObjectiveFunctions() == 1 ? 0 : 1;
        matrix.setEntry(zIndex, zIndex, maximize ? 1.0 : -1.0);
        RealVector objectiveCoefficients = maximize ? this.f.getCoefficients().mapMultiply(-1.0) : this.f.getCoefficients();
        this.copyArray(objectiveCoefficients.toArray(), matrix.getDataRef()[zIndex]);
        matrix.setEntry(zIndex, width - 1, maximize ? this.f.getConstantTerm() : -1.0 * this.f.getConstantTerm());
        if (!this.restrictToNonNegative) {
            matrix.setEntry(zIndex, this.getSlackVariableOffset() - 1, SimplexTableau.getInvertedCoefficientSum(objectiveCoefficients));
        }
        int slackVar = 0;
        int artificialVar = 0;
        for (int i = 0; i < this.constraints.size(); ++i) {
            LinearConstraint constraint = this.constraints.get(i);
            int row = this.getNumObjectiveFunctions() + i;
            this.copyArray(constraint.getCoefficients().toArray(), matrix.getDataRef()[row]);
            if (!this.restrictToNonNegative) {
                matrix.setEntry(row, this.getSlackVariableOffset() - 1, SimplexTableau.getInvertedCoefficientSum(constraint.getCoefficients()));
            }
            matrix.setEntry(row, width - 1, constraint.getValue());
            if (constraint.getRelationship() == Relationship.LEQ) {
                matrix.setEntry(row, this.getSlackVariableOffset() + slackVar++, 1.0);
            } else if (constraint.getRelationship() == Relationship.GEQ) {
                matrix.setEntry(row, this.getSlackVariableOffset() + slackVar++, -1.0);
            }
            if (constraint.getRelationship() != Relationship.EQ && constraint.getRelationship() != Relationship.GEQ) continue;
            matrix.setEntry(0, this.getArtificialVariableOffset() + artificialVar, 1.0);
            matrix.setEntry(row, this.getArtificialVariableOffset() + artificialVar++, 1.0);
            matrix.setRowVector(0, matrix.getRowVector(0).subtract(matrix.getRowVector(row)));
        }
        return matrix;
    }

    public List<LinearConstraint> normalizeConstraints(Collection<LinearConstraint> originalConstraints) {
        ArrayList<LinearConstraint> normalized = new ArrayList<LinearConstraint>(originalConstraints.size());
        for (LinearConstraint constraint : originalConstraints) {
            normalized.add(this.normalize(constraint));
        }
        return normalized;
    }

    private LinearConstraint normalize(LinearConstraint constraint) {
        if (constraint.getValue() < 0.0) {
            return new LinearConstraint(constraint.getCoefficients().mapMultiply(-1.0), constraint.getRelationship().oppositeRelationship(), -1.0 * constraint.getValue());
        }
        return new LinearConstraint(constraint.getCoefficients(), constraint.getRelationship(), constraint.getValue());
    }

    protected final int getNumObjectiveFunctions() {
        return this.numArtificialVariables > 0 ? 2 : 1;
    }

    private int getConstraintTypeCounts(Relationship relationship) {
        int count = 0;
        for (LinearConstraint constraint : this.constraints) {
            if (constraint.getRelationship() != relationship) continue;
            ++count;
        }
        return count;
    }

    protected static double getInvertedCoefficientSum(RealVector coefficients) {
        double sum = 0.0;
        for (double coefficient : coefficients.toArray()) {
            sum -= coefficient;
        }
        return sum;
    }

    protected Integer getBasicRow(int col) {
        int row = this.basicVariables[col];
        return row == -1 ? null : Integer.valueOf(row);
    }

    protected int getBasicVariable(int row) {
        return this.basicRows[row];
    }

    private void initializeBasicVariables(int startColumn) {
        this.basicVariables = new int[this.getWidth() - 1];
        this.basicRows = new int[this.getHeight()];
        Arrays.fill(this.basicVariables, -1);
        for (int i = startColumn; i < this.getWidth() - 1; ++i) {
            Integer row = this.findBasicRow(i);
            if (row == null) continue;
            this.basicVariables[i] = row;
            this.basicRows[row.intValue()] = i;
        }
    }

    private Integer findBasicRow(int col) {
        Integer row = null;
        for (int i = 0; i < this.getHeight(); ++i) {
            double entry = this.getEntry(i, col);
            if (Precision.equals(entry, 1.0, this.maxUlps) && row == null) {
                row = i;
                continue;
            }
            if (Precision.equals(entry, 0.0, this.maxUlps)) continue;
            return null;
        }
        return row;
    }

    protected void dropPhase1Objective() {
        int i;
        if (this.getNumObjectiveFunctions() == 1) {
            return;
        }
        TreeSet<Integer> columnsToDrop = new TreeSet<Integer>();
        columnsToDrop.add(0);
        for (i = this.getNumObjectiveFunctions(); i < this.getArtificialVariableOffset(); ++i) {
            double entry = this.getEntry(0, i);
            if (Precision.compareTo(entry, 0.0, this.epsilon) <= 0) continue;
            columnsToDrop.add(i);
        }
        for (i = 0; i < this.getNumArtificialVariables(); ++i) {
            int col = i + this.getArtificialVariableOffset();
            if (this.getBasicRow(col) != null) continue;
            columnsToDrop.add(col);
        }
        double[][] matrix = new double[this.getHeight() - 1][this.getWidth() - columnsToDrop.size()];
        for (int i2 = 1; i2 < this.getHeight(); ++i2) {
            int col = 0;
            for (int j = 0; j < this.getWidth(); ++j) {
                if (columnsToDrop.contains(j)) continue;
                matrix[i2 - 1][col++] = this.getEntry(i2, j);
            }
        }
        Integer[] drop = columnsToDrop.toArray(new Integer[columnsToDrop.size()]);
        for (int i3 = drop.length - 1; i3 >= 0; --i3) {
            this.columnLabels.remove(drop[i3]);
        }
        this.tableau = new Array2DRowRealMatrix(matrix);
        this.numArtificialVariables = 0;
        this.initializeBasicVariables(this.getNumObjectiveFunctions());
    }

    private void copyArray(double[] src, double[] dest) {
        System.arraycopy(src, 0, dest, this.getNumObjectiveFunctions(), src.length);
    }

    boolean isOptimal() {
        double[] objectiveFunctionRow = this.getRow(0);
        int end = this.getRhsOffset();
        for (int i = this.getNumObjectiveFunctions(); i < end; ++i) {
            double entry = objectiveFunctionRow[i];
            if (Precision.compareTo(entry, 0.0, this.epsilon) >= 0) continue;
            return false;
        }
        return true;
    }

    protected PointValuePair getSolution() {
        int negativeVarColumn = this.columnLabels.indexOf(NEGATIVE_VAR_COLUMN_LABEL);
        Integer negativeVarBasicRow = negativeVarColumn > 0 ? this.getBasicRow(negativeVarColumn) : null;
        double mostNegative = negativeVarBasicRow == null ? 0.0 : this.getEntry(negativeVarBasicRow, this.getRhsOffset());
        HashSet<Integer> usedBasicRows = new HashSet<Integer>();
        double[] coefficients = new double[this.getOriginalNumDecisionVariables()];
        for (int i = 0; i < coefficients.length; ++i) {
            int colIndex = this.columnLabels.indexOf("x" + i);
            if (colIndex < 0) {
                coefficients[i] = 0.0;
                continue;
            }
            Integer basicRow = this.getBasicRow(colIndex);
            if (basicRow != null && basicRow == 0) {
                coefficients[i] = 0.0;
                continue;
            }
            if (usedBasicRows.contains(basicRow)) {
                coefficients[i] = 0.0 - (this.restrictToNonNegative ? 0.0 : mostNegative);
                continue;
            }
            usedBasicRows.add(basicRow);
            coefficients[i] = (basicRow == null ? 0.0 : this.getEntry(basicRow, this.getRhsOffset())) - (this.restrictToNonNegative ? 0.0 : mostNegative);
        }
        return new PointValuePair(coefficients, this.f.value(coefficients));
    }

    protected void performRowOperations(int pivotCol, int pivotRow) {
        double pivotVal = this.getEntry(pivotRow, pivotCol);
        this.divideRow(pivotRow, pivotVal);
        for (int i = 0; i < this.getHeight(); ++i) {
            double multiplier;
            if (i == pivotRow || (multiplier = this.getEntry(i, pivotCol)) == 0.0) continue;
            this.subtractRow(i, pivotRow, multiplier);
        }
        int previousBasicVariable = this.getBasicVariable(pivotRow);
        this.basicVariables[previousBasicVariable] = -1;
        this.basicVariables[pivotCol] = pivotRow;
        this.basicRows[pivotRow] = pivotCol;
    }

    protected void divideRow(int dividendRowIndex, double divisor) {
        double[] dividendRow = this.getRow(dividendRowIndex);
        int j = 0;
        while (j < this.getWidth()) {
            int n = j++;
            dividendRow[n] = dividendRow[n] / divisor;
        }
    }

    protected void subtractRow(int minuendRowIndex, int subtrahendRowIndex, double multiplier) {
        double[] minuendRow = this.getRow(minuendRowIndex);
        double[] subtrahendRow = this.getRow(subtrahendRowIndex);
        for (int i = 0; i < this.getWidth(); ++i) {
            int n = i;
            minuendRow[n] = minuendRow[n] - subtrahendRow[i] * multiplier;
        }
    }

    protected final int getWidth() {
        return this.tableau.getColumnDimension();
    }

    protected final int getHeight() {
        return this.tableau.getRowDimension();
    }

    protected final double getEntry(int row, int column) {
        return this.tableau.getEntry(row, column);
    }

    protected final void setEntry(int row, int column, double value) {
        this.tableau.setEntry(row, column, value);
    }

    protected final int getSlackVariableOffset() {
        return this.getNumObjectiveFunctions() + this.numDecisionVariables;
    }

    protected final int getArtificialVariableOffset() {
        return this.getNumObjectiveFunctions() + this.numDecisionVariables + this.numSlackVariables;
    }

    protected final int getRhsOffset() {
        return this.getWidth() - 1;
    }

    protected final int getNumDecisionVariables() {
        return this.numDecisionVariables;
    }

    protected final int getOriginalNumDecisionVariables() {
        return this.f.getCoefficients().getDimension();
    }

    protected final int getNumSlackVariables() {
        return this.numSlackVariables;
    }

    protected final int getNumArtificialVariables() {
        return this.numArtificialVariables;
    }

    protected final double[] getRow(int row) {
        return this.tableau.getDataRef()[row];
    }

    protected final double[][] getData() {
        return this.tableau.getData();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof SimplexTableau) {
            SimplexTableau rhs = (SimplexTableau)other;
            return this.restrictToNonNegative == rhs.restrictToNonNegative && this.numDecisionVariables == rhs.numDecisionVariables && this.numSlackVariables == rhs.numSlackVariables && this.numArtificialVariables == rhs.numArtificialVariables && this.epsilon == rhs.epsilon && this.maxUlps == rhs.maxUlps && this.f.equals(rhs.f) && this.constraints.equals(rhs.constraints) && this.tableau.equals(rhs.tableau);
        }
        return false;
    }

    public int hashCode() {
        return Boolean.valueOf(this.restrictToNonNegative).hashCode() ^ this.numDecisionVariables ^ this.numSlackVariables ^ this.numArtificialVariables ^ Double.valueOf(this.epsilon).hashCode() ^ this.maxUlps ^ this.f.hashCode() ^ this.constraints.hashCode() ^ this.tableau.hashCode();
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        MatrixUtils.serializeRealMatrix(this.tableau, oos);
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        MatrixUtils.deserializeRealMatrix(this, "tableau", ois);
    }
}

