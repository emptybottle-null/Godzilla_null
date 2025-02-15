/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.linear;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.fraction.Fraction;
import org.apache.commons.math3.linear.AnyMatrix;
import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayFieldVector;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.BlockFieldMatrix;
import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.DefaultFieldMatrixPreservingVisitor;
import org.apache.commons.math3.linear.DiagonalMatrix;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.linear.FieldVector;
import org.apache.commons.math3.linear.MatrixDimensionMismatchException;
import org.apache.commons.math3.linear.NonSquareMatrixException;
import org.apache.commons.math3.linear.NonSymmetricMatrixException;
import org.apache.commons.math3.linear.QRDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealMatrixFormat;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.SingularMatrixException;
import org.apache.commons.math3.linear.SingularValueDecomposition;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class MatrixUtils {
    public static final RealMatrixFormat DEFAULT_FORMAT = RealMatrixFormat.getInstance();
    public static final RealMatrixFormat OCTAVE_FORMAT = new RealMatrixFormat("[", "]", "", "", "; ", ", ");

    private MatrixUtils() {
    }

    public static RealMatrix createRealMatrix(int rows, int columns) {
        return rows * columns <= 4096 ? new Array2DRowRealMatrix(rows, columns) : new BlockRealMatrix(rows, columns);
    }

    public static <T extends FieldElement<T>> FieldMatrix<T> createFieldMatrix(Field<T> field, int rows, int columns) {
        return rows * columns <= 4096 ? new Array2DRowFieldMatrix<T>(field, rows, columns) : new BlockFieldMatrix<T>(field, rows, columns);
    }

    public static RealMatrix createRealMatrix(double[][] data) throws NullArgumentException, DimensionMismatchException, NoDataException {
        if (data == null || data[0] == null) {
            throw new NullArgumentException();
        }
        return data.length * data[0].length <= 4096 ? new Array2DRowRealMatrix(data) : new BlockRealMatrix(data);
    }

    public static <T extends FieldElement<T>> FieldMatrix<T> createFieldMatrix(T[][] data) throws DimensionMismatchException, NoDataException, NullArgumentException {
        if (data == null || data[0] == null) {
            throw new NullArgumentException();
        }
        return data.length * data[0].length <= 4096 ? new Array2DRowFieldMatrix(data) : new BlockFieldMatrix(data);
    }

    public static RealMatrix createRealIdentityMatrix(int dimension) {
        RealMatrix m = MatrixUtils.createRealMatrix(dimension, dimension);
        for (int i = 0; i < dimension; ++i) {
            m.setEntry(i, i, 1.0);
        }
        return m;
    }

    public static <T extends FieldElement<T>> FieldMatrix<T> createFieldIdentityMatrix(Field<T> field, int dimension) {
        FieldElement zero = (FieldElement)field.getZero();
        FieldElement one = (FieldElement)field.getOne();
        FieldElement[][] d = (FieldElement[][])MathArrays.buildArray(field, dimension, dimension);
        for (int row = 0; row < dimension; ++row) {
            Object[] dRow = d[row];
            Arrays.fill(dRow, zero);
            dRow[row] = one;
        }
        return new Array2DRowFieldMatrix(field, d, false);
    }

    public static RealMatrix createRealDiagonalMatrix(double[] diagonal) {
        RealMatrix m = MatrixUtils.createRealMatrix(diagonal.length, diagonal.length);
        for (int i = 0; i < diagonal.length; ++i) {
            m.setEntry(i, i, diagonal[i]);
        }
        return m;
    }

    public static <T extends FieldElement<T>> FieldMatrix<T> createFieldDiagonalMatrix(T[] diagonal) {
        FieldMatrix<T> m = MatrixUtils.createFieldMatrix(diagonal[0].getField(), diagonal.length, diagonal.length);
        for (int i = 0; i < diagonal.length; ++i) {
            m.setEntry(i, i, diagonal[i]);
        }
        return m;
    }

    public static RealVector createRealVector(double[] data) throws NoDataException, NullArgumentException {
        if (data == null) {
            throw new NullArgumentException();
        }
        return new ArrayRealVector(data, true);
    }

    public static <T extends FieldElement<T>> FieldVector<T> createFieldVector(T[] data) throws NoDataException, NullArgumentException, ZeroException {
        if (data == null) {
            throw new NullArgumentException();
        }
        if (data.length == 0) {
            throw new ZeroException((Localizable)LocalizedFormats.VECTOR_MUST_HAVE_AT_LEAST_ONE_ELEMENT, new Object[0]);
        }
        return new ArrayFieldVector(data[0].getField(), data, true);
    }

    public static RealMatrix createRowRealMatrix(double[] rowData) throws NoDataException, NullArgumentException {
        if (rowData == null) {
            throw new NullArgumentException();
        }
        int nCols = rowData.length;
        RealMatrix m = MatrixUtils.createRealMatrix(1, nCols);
        for (int i = 0; i < nCols; ++i) {
            m.setEntry(0, i, rowData[i]);
        }
        return m;
    }

    public static <T extends FieldElement<T>> FieldMatrix<T> createRowFieldMatrix(T[] rowData) throws NoDataException, NullArgumentException {
        if (rowData == null) {
            throw new NullArgumentException();
        }
        int nCols = rowData.length;
        if (nCols == 0) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN);
        }
        FieldMatrix<T> m = MatrixUtils.createFieldMatrix(rowData[0].getField(), 1, nCols);
        for (int i = 0; i < nCols; ++i) {
            m.setEntry(0, i, rowData[i]);
        }
        return m;
    }

    public static RealMatrix createColumnRealMatrix(double[] columnData) throws NoDataException, NullArgumentException {
        if (columnData == null) {
            throw new NullArgumentException();
        }
        int nRows = columnData.length;
        RealMatrix m = MatrixUtils.createRealMatrix(nRows, 1);
        for (int i = 0; i < nRows; ++i) {
            m.setEntry(i, 0, columnData[i]);
        }
        return m;
    }

    public static <T extends FieldElement<T>> FieldMatrix<T> createColumnFieldMatrix(T[] columnData) throws NoDataException, NullArgumentException {
        if (columnData == null) {
            throw new NullArgumentException();
        }
        int nRows = columnData.length;
        if (nRows == 0) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_ROW);
        }
        FieldMatrix<T> m = MatrixUtils.createFieldMatrix(columnData[0].getField(), nRows, 1);
        for (int i = 0; i < nRows; ++i) {
            m.setEntry(i, 0, columnData[i]);
        }
        return m;
    }

    private static boolean isSymmetricInternal(RealMatrix matrix, double relativeTolerance, boolean raiseException) {
        int rows = matrix.getRowDimension();
        if (rows != matrix.getColumnDimension()) {
            if (raiseException) {
                throw new NonSquareMatrixException(rows, matrix.getColumnDimension());
            }
            return false;
        }
        for (int i = 0; i < rows; ++i) {
            for (int j = i + 1; j < rows; ++j) {
                double mji;
                double mij = matrix.getEntry(i, j);
                if (!(FastMath.abs(mij - (mji = matrix.getEntry(j, i))) > FastMath.max(FastMath.abs(mij), FastMath.abs(mji)) * relativeTolerance)) continue;
                if (raiseException) {
                    throw new NonSymmetricMatrixException(i, j, relativeTolerance);
                }
                return false;
            }
        }
        return true;
    }

    public static void checkSymmetric(RealMatrix matrix, double eps) {
        MatrixUtils.isSymmetricInternal(matrix, eps, true);
    }

    public static boolean isSymmetric(RealMatrix matrix, double eps) {
        return MatrixUtils.isSymmetricInternal(matrix, eps, false);
    }

    public static void checkMatrixIndex(AnyMatrix m, int row, int column) throws OutOfRangeException {
        MatrixUtils.checkRowIndex(m, row);
        MatrixUtils.checkColumnIndex(m, column);
    }

    public static void checkRowIndex(AnyMatrix m, int row) throws OutOfRangeException {
        if (row < 0 || row >= m.getRowDimension()) {
            throw new OutOfRangeException((Localizable)LocalizedFormats.ROW_INDEX, (Number)row, 0, m.getRowDimension() - 1);
        }
    }

    public static void checkColumnIndex(AnyMatrix m, int column) throws OutOfRangeException {
        if (column < 0 || column >= m.getColumnDimension()) {
            throw new OutOfRangeException((Localizable)LocalizedFormats.COLUMN_INDEX, (Number)column, 0, m.getColumnDimension() - 1);
        }
    }

    public static void checkSubMatrixIndex(AnyMatrix m, int startRow, int endRow, int startColumn, int endColumn) throws NumberIsTooSmallException, OutOfRangeException {
        MatrixUtils.checkRowIndex(m, startRow);
        MatrixUtils.checkRowIndex(m, endRow);
        if (endRow < startRow) {
            throw new NumberIsTooSmallException((Localizable)LocalizedFormats.INITIAL_ROW_AFTER_FINAL_ROW, (Number)endRow, startRow, false);
        }
        MatrixUtils.checkColumnIndex(m, startColumn);
        MatrixUtils.checkColumnIndex(m, endColumn);
        if (endColumn < startColumn) {
            throw new NumberIsTooSmallException((Localizable)LocalizedFormats.INITIAL_COLUMN_AFTER_FINAL_COLUMN, (Number)endColumn, startColumn, false);
        }
    }

    public static void checkSubMatrixIndex(AnyMatrix m, int[] selectedRows, int[] selectedColumns) throws NoDataException, NullArgumentException, OutOfRangeException {
        if (selectedRows == null) {
            throw new NullArgumentException();
        }
        if (selectedColumns == null) {
            throw new NullArgumentException();
        }
        if (selectedRows.length == 0) {
            throw new NoDataException(LocalizedFormats.EMPTY_SELECTED_ROW_INDEX_ARRAY);
        }
        if (selectedColumns.length == 0) {
            throw new NoDataException(LocalizedFormats.EMPTY_SELECTED_COLUMN_INDEX_ARRAY);
        }
        for (int row : selectedRows) {
            MatrixUtils.checkRowIndex(m, row);
        }
        for (int column : selectedColumns) {
            MatrixUtils.checkColumnIndex(m, column);
        }
    }

    public static void checkAdditionCompatible(AnyMatrix left, AnyMatrix right) throws MatrixDimensionMismatchException {
        if (left.getRowDimension() != right.getRowDimension() || left.getColumnDimension() != right.getColumnDimension()) {
            throw new MatrixDimensionMismatchException(left.getRowDimension(), left.getColumnDimension(), right.getRowDimension(), right.getColumnDimension());
        }
    }

    public static void checkSubtractionCompatible(AnyMatrix left, AnyMatrix right) throws MatrixDimensionMismatchException {
        if (left.getRowDimension() != right.getRowDimension() || left.getColumnDimension() != right.getColumnDimension()) {
            throw new MatrixDimensionMismatchException(left.getRowDimension(), left.getColumnDimension(), right.getRowDimension(), right.getColumnDimension());
        }
    }

    public static void checkMultiplicationCompatible(AnyMatrix left, AnyMatrix right) throws DimensionMismatchException {
        if (left.getColumnDimension() != right.getRowDimension()) {
            throw new DimensionMismatchException(left.getColumnDimension(), right.getRowDimension());
        }
    }

    public static Array2DRowRealMatrix fractionMatrixToRealMatrix(FieldMatrix<Fraction> m) {
        FractionMatrixConverter converter = new FractionMatrixConverter();
        m.walkInOptimizedOrder(converter);
        return converter.getConvertedMatrix();
    }

    public static Array2DRowRealMatrix bigFractionMatrixToRealMatrix(FieldMatrix<BigFraction> m) {
        BigFractionMatrixConverter converter = new BigFractionMatrixConverter();
        m.walkInOptimizedOrder(converter);
        return converter.getConvertedMatrix();
    }

    public static void serializeRealVector(RealVector vector, ObjectOutputStream oos) throws IOException {
        int n = vector.getDimension();
        oos.writeInt(n);
        for (int i = 0; i < n; ++i) {
            oos.writeDouble(vector.getEntry(i));
        }
    }

    public static void deserializeRealVector(Object instance, String fieldName, ObjectInputStream ois) throws ClassNotFoundException, IOException {
        try {
            int n = ois.readInt();
            double[] data = new double[n];
            for (int i = 0; i < n; ++i) {
                data[i] = ois.readDouble();
            }
            ArrayRealVector vector = new ArrayRealVector(data, false);
            java.lang.reflect.Field f = instance.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            f.set(instance, vector);
        } catch (NoSuchFieldException nsfe) {
            IOException ioe = new IOException();
            ioe.initCause(nsfe);
            throw ioe;
        } catch (IllegalAccessException iae) {
            IOException ioe = new IOException();
            ioe.initCause(iae);
            throw ioe;
        }
    }

    public static void serializeRealMatrix(RealMatrix matrix, ObjectOutputStream oos) throws IOException {
        int n = matrix.getRowDimension();
        int m = matrix.getColumnDimension();
        oos.writeInt(n);
        oos.writeInt(m);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                oos.writeDouble(matrix.getEntry(i, j));
            }
        }
    }

    public static void deserializeRealMatrix(Object instance, String fieldName, ObjectInputStream ois) throws ClassNotFoundException, IOException {
        try {
            int n = ois.readInt();
            int m = ois.readInt();
            double[][] data = new double[n][m];
            for (int i = 0; i < n; ++i) {
                double[] dataI = data[i];
                for (int j = 0; j < m; ++j) {
                    dataI[j] = ois.readDouble();
                }
            }
            Array2DRowRealMatrix matrix = new Array2DRowRealMatrix(data, false);
            java.lang.reflect.Field f = instance.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            f.set(instance, matrix);
        } catch (NoSuchFieldException nsfe) {
            IOException ioe = new IOException();
            ioe.initCause(nsfe);
            throw ioe;
        } catch (IllegalAccessException iae) {
            IOException ioe = new IOException();
            ioe.initCause(iae);
            throw ioe;
        }
    }

    public static void solveLowerTriangularSystem(RealMatrix rm, RealVector b) throws DimensionMismatchException, MathArithmeticException, NonSquareMatrixException {
        if (rm == null || b == null || rm.getRowDimension() != b.getDimension()) {
            throw new DimensionMismatchException(rm == null ? 0 : rm.getRowDimension(), b == null ? 0 : b.getDimension());
        }
        if (rm.getColumnDimension() != rm.getRowDimension()) {
            throw new NonSquareMatrixException(rm.getRowDimension(), rm.getColumnDimension());
        }
        int rows = rm.getRowDimension();
        for (int i = 0; i < rows; ++i) {
            double diag = rm.getEntry(i, i);
            if (FastMath.abs(diag) < Precision.SAFE_MIN) {
                throw new MathArithmeticException(LocalizedFormats.ZERO_DENOMINATOR, new Object[0]);
            }
            double bi = b.getEntry(i) / diag;
            b.setEntry(i, bi);
            for (int j = i + 1; j < rows; ++j) {
                b.setEntry(j, b.getEntry(j) - bi * rm.getEntry(j, i));
            }
        }
    }

    public static void solveUpperTriangularSystem(RealMatrix rm, RealVector b) throws DimensionMismatchException, MathArithmeticException, NonSquareMatrixException {
        if (rm == null || b == null || rm.getRowDimension() != b.getDimension()) {
            throw new DimensionMismatchException(rm == null ? 0 : rm.getRowDimension(), b == null ? 0 : b.getDimension());
        }
        if (rm.getColumnDimension() != rm.getRowDimension()) {
            throw new NonSquareMatrixException(rm.getRowDimension(), rm.getColumnDimension());
        }
        int rows = rm.getRowDimension();
        for (int i = rows - 1; i > -1; --i) {
            double diag = rm.getEntry(i, i);
            if (FastMath.abs(diag) < Precision.SAFE_MIN) {
                throw new MathArithmeticException(LocalizedFormats.ZERO_DENOMINATOR, new Object[0]);
            }
            double bi = b.getEntry(i) / diag;
            b.setEntry(i, bi);
            for (int j = i - 1; j > -1; --j) {
                b.setEntry(j, b.getEntry(j) - bi * rm.getEntry(j, i));
            }
        }
    }

    public static RealMatrix blockInverse(RealMatrix m, int splitIndex) {
        int n = m.getRowDimension();
        if (m.getColumnDimension() != n) {
            throw new NonSquareMatrixException(m.getRowDimension(), m.getColumnDimension());
        }
        int splitIndex1 = splitIndex + 1;
        RealMatrix a = m.getSubMatrix(0, splitIndex, 0, splitIndex);
        RealMatrix b = m.getSubMatrix(0, splitIndex, splitIndex1, n - 1);
        RealMatrix c = m.getSubMatrix(splitIndex1, n - 1, 0, splitIndex);
        RealMatrix d = m.getSubMatrix(splitIndex1, n - 1, splitIndex1, n - 1);
        SingularValueDecomposition aDec = new SingularValueDecomposition(a);
        DecompositionSolver aSolver = aDec.getSolver();
        if (!aSolver.isNonSingular()) {
            throw new SingularMatrixException();
        }
        RealMatrix aInv = aSolver.getInverse();
        SingularValueDecomposition dDec = new SingularValueDecomposition(d);
        DecompositionSolver dSolver = dDec.getSolver();
        if (!dSolver.isNonSingular()) {
            throw new SingularMatrixException();
        }
        RealMatrix dInv = dSolver.getInverse();
        RealMatrix tmp1 = a.subtract(b.multiply(dInv).multiply(c));
        SingularValueDecomposition tmp1Dec = new SingularValueDecomposition(tmp1);
        DecompositionSolver tmp1Solver = tmp1Dec.getSolver();
        if (!tmp1Solver.isNonSingular()) {
            throw new SingularMatrixException();
        }
        RealMatrix result00 = tmp1Solver.getInverse();
        RealMatrix tmp2 = d.subtract(c.multiply(aInv).multiply(b));
        SingularValueDecomposition tmp2Dec = new SingularValueDecomposition(tmp2);
        DecompositionSolver tmp2Solver = tmp2Dec.getSolver();
        if (!tmp2Solver.isNonSingular()) {
            throw new SingularMatrixException();
        }
        RealMatrix result11 = tmp2Solver.getInverse();
        RealMatrix result01 = aInv.multiply(b).multiply(result11).scalarMultiply(-1.0);
        RealMatrix result10 = dInv.multiply(c).multiply(result00).scalarMultiply(-1.0);
        Array2DRowRealMatrix result = new Array2DRowRealMatrix(n, n);
        result.setSubMatrix(result00.getData(), 0, 0);
        result.setSubMatrix(result01.getData(), 0, splitIndex1);
        result.setSubMatrix(result10.getData(), splitIndex1, 0);
        result.setSubMatrix(result11.getData(), splitIndex1, splitIndex1);
        return result;
    }

    public static RealMatrix inverse(RealMatrix matrix) throws NullArgumentException, SingularMatrixException, NonSquareMatrixException {
        return MatrixUtils.inverse(matrix, 0.0);
    }

    public static RealMatrix inverse(RealMatrix matrix, double threshold) throws NullArgumentException, SingularMatrixException, NonSquareMatrixException {
        MathUtils.checkNotNull(matrix);
        if (!matrix.isSquare()) {
            throw new NonSquareMatrixException(matrix.getRowDimension(), matrix.getColumnDimension());
        }
        if (matrix instanceof DiagonalMatrix) {
            return ((DiagonalMatrix)matrix).inverse(threshold);
        }
        QRDecomposition decomposition = new QRDecomposition(matrix, threshold);
        return decomposition.getSolver().getInverse();
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static class BigFractionMatrixConverter
    extends DefaultFieldMatrixPreservingVisitor<BigFraction> {
        private double[][] data;

        BigFractionMatrixConverter() {
            super(BigFraction.ZERO);
        }

        @Override
        public void start(int rows, int columns, int startRow, int endRow, int startColumn, int endColumn) {
            this.data = new double[rows][columns];
        }

        @Override
        public void visit(int row, int column, BigFraction value) {
            this.data[row][column] = value.doubleValue();
        }

        Array2DRowRealMatrix getConvertedMatrix() {
            return new Array2DRowRealMatrix(this.data, false);
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static class FractionMatrixConverter
    extends DefaultFieldMatrixPreservingVisitor<Fraction> {
        private double[][] data;

        FractionMatrixConverter() {
            super(Fraction.ZERO);
        }

        @Override
        public void start(int rows, int columns, int startRow, int endRow, int startColumn, int endColumn) {
            this.data = new double[rows][columns];
        }

        @Override
        public void visit(int row, int column, Fraction value) {
            this.data[row][column] = value.doubleValue();
        }

        Array2DRowRealMatrix getConvertedMatrix() {
            return new Array2DRowRealMatrix(this.data, false);
        }
    }
}

