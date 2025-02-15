/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.linear;

import java.io.Serializable;
import java.util.Arrays;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.AbstractRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.MatrixDimensionMismatchException;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealMatrixChangingVisitor;
import org.apache.commons.math3.linear.RealMatrixPreservingVisitor;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

public class BlockRealMatrix
extends AbstractRealMatrix
implements Serializable {
    public static final int BLOCK_SIZE = 52;
    private static final long serialVersionUID = 4991895511313664478L;
    private final double[][] blocks;
    private final int rows;
    private final int columns;
    private final int blockRows;
    private final int blockColumns;

    public BlockRealMatrix(int rows, int columns) throws NotStrictlyPositiveException {
        super(rows, columns);
        this.rows = rows;
        this.columns = columns;
        this.blockRows = (rows + 52 - 1) / 52;
        this.blockColumns = (columns + 52 - 1) / 52;
        this.blocks = BlockRealMatrix.createBlocksLayout(rows, columns);
    }

    public BlockRealMatrix(double[][] rawData) throws DimensionMismatchException, NotStrictlyPositiveException {
        this(rawData.length, rawData[0].length, BlockRealMatrix.toBlocksLayout(rawData), false);
    }

    public BlockRealMatrix(int rows, int columns, double[][] blockData, boolean copyArray) throws DimensionMismatchException, NotStrictlyPositiveException {
        super(rows, columns);
        this.rows = rows;
        this.columns = columns;
        this.blockRows = (rows + 52 - 1) / 52;
        this.blockColumns = (columns + 52 - 1) / 52;
        this.blocks = copyArray ? (Object)new double[this.blockRows * this.blockColumns][] : blockData;
        int index = 0;
        for (int iBlock = 0; iBlock < this.blockRows; ++iBlock) {
            int iHeight = this.blockHeight(iBlock);
            int jBlock = 0;
            while (jBlock < this.blockColumns) {
                if (blockData[index].length != iHeight * this.blockWidth(jBlock)) {
                    throw new DimensionMismatchException(blockData[index].length, iHeight * this.blockWidth(jBlock));
                }
                if (copyArray) {
                    this.blocks[index] = (double[])blockData[index].clone();
                }
                ++jBlock;
                ++index;
            }
        }
    }

    public static double[][] toBlocksLayout(double[][] rawData) throws DimensionMismatchException {
        int rows = rawData.length;
        int columns = rawData[0].length;
        int blockRows = (rows + 52 - 1) / 52;
        int blockColumns = (columns + 52 - 1) / 52;
        for (int i = 0; i < rawData.length; ++i) {
            int length = rawData[i].length;
            if (length == columns) continue;
            throw new DimensionMismatchException(columns, length);
        }
        double[][] blocks = new double[blockRows * blockColumns][];
        int blockIndex = 0;
        for (int iBlock = 0; iBlock < blockRows; ++iBlock) {
            int pStart = iBlock * 52;
            int pEnd = FastMath.min(pStart + 52, rows);
            int iHeight = pEnd - pStart;
            for (int jBlock = 0; jBlock < blockColumns; ++jBlock) {
                int qStart = jBlock * 52;
                int qEnd = FastMath.min(qStart + 52, columns);
                int jWidth = qEnd - qStart;
                double[] block = new double[iHeight * jWidth];
                blocks[blockIndex] = block;
                int index = 0;
                for (int p = pStart; p < pEnd; ++p) {
                    System.arraycopy(rawData[p], qStart, block, index, jWidth);
                    index += jWidth;
                }
                ++blockIndex;
            }
        }
        return blocks;
    }

    public static double[][] createBlocksLayout(int rows, int columns) {
        int blockRows = (rows + 52 - 1) / 52;
        int blockColumns = (columns + 52 - 1) / 52;
        double[][] blocks = new double[blockRows * blockColumns][];
        int blockIndex = 0;
        for (int iBlock = 0; iBlock < blockRows; ++iBlock) {
            int pStart = iBlock * 52;
            int pEnd = FastMath.min(pStart + 52, rows);
            int iHeight = pEnd - pStart;
            for (int jBlock = 0; jBlock < blockColumns; ++jBlock) {
                int qStart = jBlock * 52;
                int qEnd = FastMath.min(qStart + 52, columns);
                int jWidth = qEnd - qStart;
                blocks[blockIndex] = new double[iHeight * jWidth];
                ++blockIndex;
            }
        }
        return blocks;
    }

    public BlockRealMatrix createMatrix(int rowDimension, int columnDimension) throws NotStrictlyPositiveException {
        return new BlockRealMatrix(rowDimension, columnDimension);
    }

    public BlockRealMatrix copy() {
        BlockRealMatrix copied = new BlockRealMatrix(this.rows, this.columns);
        for (int i = 0; i < this.blocks.length; ++i) {
            System.arraycopy(this.blocks[i], 0, copied.blocks[i], 0, this.blocks[i].length);
        }
        return copied;
    }

    public BlockRealMatrix add(RealMatrix m) throws MatrixDimensionMismatchException {
        try {
            return this.add((BlockRealMatrix)m);
        } catch (ClassCastException cce) {
            MatrixUtils.checkAdditionCompatible(this, m);
            BlockRealMatrix out = new BlockRealMatrix(this.rows, this.columns);
            int blockIndex = 0;
            for (int iBlock = 0; iBlock < out.blockRows; ++iBlock) {
                for (int jBlock = 0; jBlock < out.blockColumns; ++jBlock) {
                    double[] outBlock = out.blocks[blockIndex];
                    double[] tBlock = this.blocks[blockIndex];
                    int pStart = iBlock * 52;
                    int pEnd = FastMath.min(pStart + 52, this.rows);
                    int qStart = jBlock * 52;
                    int qEnd = FastMath.min(qStart + 52, this.columns);
                    int k = 0;
                    for (int p = pStart; p < pEnd; ++p) {
                        for (int q = qStart; q < qEnd; ++q) {
                            outBlock[k] = tBlock[k] + m.getEntry(p, q);
                            ++k;
                        }
                    }
                    ++blockIndex;
                }
            }
            return out;
        }
    }

    public BlockRealMatrix add(BlockRealMatrix m) throws MatrixDimensionMismatchException {
        MatrixUtils.checkAdditionCompatible(this, m);
        BlockRealMatrix out = new BlockRealMatrix(this.rows, this.columns);
        for (int blockIndex = 0; blockIndex < out.blocks.length; ++blockIndex) {
            double[] outBlock = out.blocks[blockIndex];
            double[] tBlock = this.blocks[blockIndex];
            double[] mBlock = m.blocks[blockIndex];
            for (int k = 0; k < outBlock.length; ++k) {
                outBlock[k] = tBlock[k] + mBlock[k];
            }
        }
        return out;
    }

    public BlockRealMatrix subtract(RealMatrix m) throws MatrixDimensionMismatchException {
        try {
            return this.subtract((BlockRealMatrix)m);
        } catch (ClassCastException cce) {
            MatrixUtils.checkSubtractionCompatible(this, m);
            BlockRealMatrix out = new BlockRealMatrix(this.rows, this.columns);
            int blockIndex = 0;
            for (int iBlock = 0; iBlock < out.blockRows; ++iBlock) {
                for (int jBlock = 0; jBlock < out.blockColumns; ++jBlock) {
                    double[] outBlock = out.blocks[blockIndex];
                    double[] tBlock = this.blocks[blockIndex];
                    int pStart = iBlock * 52;
                    int pEnd = FastMath.min(pStart + 52, this.rows);
                    int qStart = jBlock * 52;
                    int qEnd = FastMath.min(qStart + 52, this.columns);
                    int k = 0;
                    for (int p = pStart; p < pEnd; ++p) {
                        for (int q = qStart; q < qEnd; ++q) {
                            outBlock[k] = tBlock[k] - m.getEntry(p, q);
                            ++k;
                        }
                    }
                    ++blockIndex;
                }
            }
            return out;
        }
    }

    public BlockRealMatrix subtract(BlockRealMatrix m) throws MatrixDimensionMismatchException {
        MatrixUtils.checkSubtractionCompatible(this, m);
        BlockRealMatrix out = new BlockRealMatrix(this.rows, this.columns);
        for (int blockIndex = 0; blockIndex < out.blocks.length; ++blockIndex) {
            double[] outBlock = out.blocks[blockIndex];
            double[] tBlock = this.blocks[blockIndex];
            double[] mBlock = m.blocks[blockIndex];
            for (int k = 0; k < outBlock.length; ++k) {
                outBlock[k] = tBlock[k] - mBlock[k];
            }
        }
        return out;
    }

    public BlockRealMatrix scalarAdd(double d) {
        BlockRealMatrix out = new BlockRealMatrix(this.rows, this.columns);
        for (int blockIndex = 0; blockIndex < out.blocks.length; ++blockIndex) {
            double[] outBlock = out.blocks[blockIndex];
            double[] tBlock = this.blocks[blockIndex];
            for (int k = 0; k < outBlock.length; ++k) {
                outBlock[k] = tBlock[k] + d;
            }
        }
        return out;
    }

    public RealMatrix scalarMultiply(double d) {
        BlockRealMatrix out = new BlockRealMatrix(this.rows, this.columns);
        for (int blockIndex = 0; blockIndex < out.blocks.length; ++blockIndex) {
            double[] outBlock = out.blocks[blockIndex];
            double[] tBlock = this.blocks[blockIndex];
            for (int k = 0; k < outBlock.length; ++k) {
                outBlock[k] = tBlock[k] * d;
            }
        }
        return out;
    }

    public BlockRealMatrix multiply(RealMatrix m) throws DimensionMismatchException {
        try {
            return this.multiply((BlockRealMatrix)m);
        } catch (ClassCastException cce) {
            MatrixUtils.checkMultiplicationCompatible(this, m);
            BlockRealMatrix out = new BlockRealMatrix(this.rows, m.getColumnDimension());
            int blockIndex = 0;
            for (int iBlock = 0; iBlock < out.blockRows; ++iBlock) {
                int pStart = iBlock * 52;
                int pEnd = FastMath.min(pStart + 52, this.rows);
                for (int jBlock = 0; jBlock < out.blockColumns; ++jBlock) {
                    int qStart = jBlock * 52;
                    int qEnd = FastMath.min(qStart + 52, m.getColumnDimension());
                    double[] outBlock = out.blocks[blockIndex];
                    for (int kBlock = 0; kBlock < this.blockColumns; ++kBlock) {
                        int kWidth = this.blockWidth(kBlock);
                        double[] tBlock = this.blocks[iBlock * this.blockColumns + kBlock];
                        int rStart = kBlock * 52;
                        int k = 0;
                        for (int p = pStart; p < pEnd; ++p) {
                            int lStart = (p - pStart) * kWidth;
                            int lEnd = lStart + kWidth;
                            for (int q = qStart; q < qEnd; ++q) {
                                double sum = 0.0;
                                int r = rStart;
                                for (int l = lStart; l < lEnd; ++l) {
                                    sum += tBlock[l] * m.getEntry(r, q);
                                    ++r;
                                }
                                int n = k++;
                                outBlock[n] = outBlock[n] + sum;
                            }
                        }
                    }
                    ++blockIndex;
                }
            }
            return out;
        }
    }

    public BlockRealMatrix multiply(BlockRealMatrix m) throws DimensionMismatchException {
        MatrixUtils.checkMultiplicationCompatible(this, m);
        BlockRealMatrix out = new BlockRealMatrix(this.rows, m.columns);
        int blockIndex = 0;
        for (int iBlock = 0; iBlock < out.blockRows; ++iBlock) {
            int pStart = iBlock * 52;
            int pEnd = FastMath.min(pStart + 52, this.rows);
            for (int jBlock = 0; jBlock < out.blockColumns; ++jBlock) {
                int jWidth = out.blockWidth(jBlock);
                int jWidth2 = jWidth + jWidth;
                int jWidth3 = jWidth2 + jWidth;
                int jWidth4 = jWidth3 + jWidth;
                double[] outBlock = out.blocks[blockIndex];
                for (int kBlock = 0; kBlock < this.blockColumns; ++kBlock) {
                    int kWidth = this.blockWidth(kBlock);
                    double[] tBlock = this.blocks[iBlock * this.blockColumns + kBlock];
                    double[] mBlock = m.blocks[kBlock * m.blockColumns + jBlock];
                    int k = 0;
                    for (int p = pStart; p < pEnd; ++p) {
                        int lStart = (p - pStart) * kWidth;
                        int lEnd = lStart + kWidth;
                        for (int nStart = 0; nStart < jWidth; ++nStart) {
                            double sum = 0.0;
                            int l = lStart;
                            int n = nStart;
                            while (l < lEnd - 3) {
                                sum += tBlock[l] * mBlock[n] + tBlock[l + 1] * mBlock[n + jWidth] + tBlock[l + 2] * mBlock[n + jWidth2] + tBlock[l + 3] * mBlock[n + jWidth3];
                                l += 4;
                                n += jWidth4;
                            }
                            while (l < lEnd) {
                                sum += tBlock[l++] * mBlock[n];
                                n += jWidth;
                            }
                            int n2 = k++;
                            outBlock[n2] = outBlock[n2] + sum;
                        }
                    }
                }
                ++blockIndex;
            }
        }
        return out;
    }

    public double[][] getData() {
        double[][] data = new double[this.getRowDimension()][this.getColumnDimension()];
        int lastColumns = this.columns - (this.blockColumns - 1) * 52;
        for (int iBlock = 0; iBlock < this.blockRows; ++iBlock) {
            int pStart = iBlock * 52;
            int pEnd = FastMath.min(pStart + 52, this.rows);
            int regularPos = 0;
            int lastPos = 0;
            for (int p = pStart; p < pEnd; ++p) {
                double[] dataP = data[p];
                int blockIndex = iBlock * this.blockColumns;
                int dataPos = 0;
                for (int jBlock = 0; jBlock < this.blockColumns - 1; ++jBlock) {
                    System.arraycopy(this.blocks[blockIndex++], regularPos, dataP, dataPos, 52);
                    dataPos += 52;
                }
                System.arraycopy(this.blocks[blockIndex], lastPos, dataP, dataPos, lastColumns);
                regularPos += 52;
                lastPos += lastColumns;
            }
        }
        return data;
    }

    public double getNorm() {
        double[] colSums = new double[52];
        double maxColSum = 0.0;
        for (int jBlock = 0; jBlock < this.blockColumns; ++jBlock) {
            int jWidth = this.blockWidth(jBlock);
            Arrays.fill(colSums, 0, jWidth, 0.0);
            for (int iBlock = 0; iBlock < this.blockRows; ++iBlock) {
                int iHeight = this.blockHeight(iBlock);
                double[] block = this.blocks[iBlock * this.blockColumns + jBlock];
                int j = 0;
                while (j < jWidth) {
                    double sum = 0.0;
                    for (int i = 0; i < iHeight; ++i) {
                        sum += FastMath.abs(block[i * jWidth + j]);
                    }
                    int n = j++;
                    colSums[n] = colSums[n] + sum;
                }
            }
            for (int j = 0; j < jWidth; ++j) {
                maxColSum = FastMath.max(maxColSum, colSums[j]);
            }
        }
        return maxColSum;
    }

    public double getFrobeniusNorm() {
        double sum2 = 0.0;
        for (int blockIndex = 0; blockIndex < this.blocks.length; ++blockIndex) {
            for (double entry : this.blocks[blockIndex]) {
                sum2 += entry * entry;
            }
        }
        return FastMath.sqrt(sum2);
    }

    public BlockRealMatrix getSubMatrix(int startRow, int endRow, int startColumn, int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
        BlockRealMatrix out = new BlockRealMatrix(endRow - startRow + 1, endColumn - startColumn + 1);
        int blockStartRow = startRow / 52;
        int rowsShift = startRow % 52;
        int blockStartColumn = startColumn / 52;
        int columnsShift = startColumn % 52;
        int pBlock = blockStartRow;
        for (int iBlock = 0; iBlock < out.blockRows; ++iBlock) {
            int iHeight = out.blockHeight(iBlock);
            int qBlock = blockStartColumn;
            for (int jBlock = 0; jBlock < out.blockColumns; ++jBlock) {
                int width2;
                int jWidth = out.blockWidth(jBlock);
                int outIndex = iBlock * out.blockColumns + jBlock;
                double[] outBlock = out.blocks[outIndex];
                int index = pBlock * this.blockColumns + qBlock;
                int width = this.blockWidth(qBlock);
                int heightExcess = iHeight + rowsShift - 52;
                int widthExcess = jWidth + columnsShift - 52;
                if (heightExcess > 0) {
                    if (widthExcess > 0) {
                        width2 = this.blockWidth(qBlock + 1);
                        this.copyBlockPart(this.blocks[index], width, rowsShift, 52, columnsShift, 52, outBlock, jWidth, 0, 0);
                        this.copyBlockPart(this.blocks[index + 1], width2, rowsShift, 52, 0, widthExcess, outBlock, jWidth, 0, jWidth - widthExcess);
                        this.copyBlockPart(this.blocks[index + this.blockColumns], width, 0, heightExcess, columnsShift, 52, outBlock, jWidth, iHeight - heightExcess, 0);
                        this.copyBlockPart(this.blocks[index + this.blockColumns + 1], width2, 0, heightExcess, 0, widthExcess, outBlock, jWidth, iHeight - heightExcess, jWidth - widthExcess);
                    } else {
                        this.copyBlockPart(this.blocks[index], width, rowsShift, 52, columnsShift, jWidth + columnsShift, outBlock, jWidth, 0, 0);
                        this.copyBlockPart(this.blocks[index + this.blockColumns], width, 0, heightExcess, columnsShift, jWidth + columnsShift, outBlock, jWidth, iHeight - heightExcess, 0);
                    }
                } else if (widthExcess > 0) {
                    width2 = this.blockWidth(qBlock + 1);
                    this.copyBlockPart(this.blocks[index], width, rowsShift, iHeight + rowsShift, columnsShift, 52, outBlock, jWidth, 0, 0);
                    this.copyBlockPart(this.blocks[index + 1], width2, rowsShift, iHeight + rowsShift, 0, widthExcess, outBlock, jWidth, 0, jWidth - widthExcess);
                } else {
                    this.copyBlockPart(this.blocks[index], width, rowsShift, iHeight + rowsShift, columnsShift, jWidth + columnsShift, outBlock, jWidth, 0, 0);
                }
                ++qBlock;
            }
            ++pBlock;
        }
        return out;
    }

    private void copyBlockPart(double[] srcBlock, int srcWidth, int srcStartRow, int srcEndRow, int srcStartColumn, int srcEndColumn, double[] dstBlock, int dstWidth, int dstStartRow, int dstStartColumn) {
        int length = srcEndColumn - srcStartColumn;
        int srcPos = srcStartRow * srcWidth + srcStartColumn;
        int dstPos = dstStartRow * dstWidth + dstStartColumn;
        for (int srcRow = srcStartRow; srcRow < srcEndRow; ++srcRow) {
            System.arraycopy(srcBlock, srcPos, dstBlock, dstPos, length);
            srcPos += srcWidth;
            dstPos += dstWidth;
        }
    }

    public void setSubMatrix(double[][] subMatrix, int row, int column) throws OutOfRangeException, NoDataException, NullArgumentException, DimensionMismatchException {
        MathUtils.checkNotNull(subMatrix);
        int refLength = subMatrix[0].length;
        if (refLength == 0) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN);
        }
        int endRow = row + subMatrix.length - 1;
        int endColumn = column + refLength - 1;
        MatrixUtils.checkSubMatrixIndex(this, row, endRow, column, endColumn);
        for (double[] subRow : subMatrix) {
            if (subRow.length == refLength) continue;
            throw new DimensionMismatchException(refLength, subRow.length);
        }
        int blockStartRow = row / 52;
        int blockEndRow = (endRow + 52) / 52;
        int blockStartColumn = column / 52;
        int blockEndColumn = (endColumn + 52) / 52;
        for (int iBlock = blockStartRow; iBlock < blockEndRow; ++iBlock) {
            int iHeight = this.blockHeight(iBlock);
            int firstRow = iBlock * 52;
            int iStart = FastMath.max(row, firstRow);
            int iEnd = FastMath.min(endRow + 1, firstRow + iHeight);
            for (int jBlock = blockStartColumn; jBlock < blockEndColumn; ++jBlock) {
                int jWidth = this.blockWidth(jBlock);
                int firstColumn = jBlock * 52;
                int jStart = FastMath.max(column, firstColumn);
                int jEnd = FastMath.min(endColumn + 1, firstColumn + jWidth);
                int jLength = jEnd - jStart;
                double[] block = this.blocks[iBlock * this.blockColumns + jBlock];
                for (int i = iStart; i < iEnd; ++i) {
                    System.arraycopy(subMatrix[i - row], jStart - column, block, (i - firstRow) * jWidth + (jStart - firstColumn), jLength);
                }
            }
        }
    }

    public BlockRealMatrix getRowMatrix(int row) throws OutOfRangeException {
        MatrixUtils.checkRowIndex(this, row);
        BlockRealMatrix out = new BlockRealMatrix(1, this.columns);
        int iBlock = row / 52;
        int iRow = row - iBlock * 52;
        int outBlockIndex = 0;
        int outIndex = 0;
        double[] outBlock = out.blocks[outBlockIndex];
        for (int jBlock = 0; jBlock < this.blockColumns; ++jBlock) {
            int jWidth = this.blockWidth(jBlock);
            double[] block = this.blocks[iBlock * this.blockColumns + jBlock];
            int available = outBlock.length - outIndex;
            if (jWidth > available) {
                System.arraycopy(block, iRow * jWidth, outBlock, outIndex, available);
                outBlock = out.blocks[++outBlockIndex];
                System.arraycopy(block, iRow * jWidth, outBlock, 0, jWidth - available);
                outIndex = jWidth - available;
                continue;
            }
            System.arraycopy(block, iRow * jWidth, outBlock, outIndex, jWidth);
            outIndex += jWidth;
        }
        return out;
    }

    public void setRowMatrix(int row, RealMatrix matrix) throws OutOfRangeException, MatrixDimensionMismatchException {
        try {
            this.setRowMatrix(row, (BlockRealMatrix)matrix);
        } catch (ClassCastException cce) {
            super.setRowMatrix(row, matrix);
        }
    }

    public void setRowMatrix(int row, BlockRealMatrix matrix) throws OutOfRangeException, MatrixDimensionMismatchException {
        MatrixUtils.checkRowIndex(this, row);
        int nCols = this.getColumnDimension();
        if (matrix.getRowDimension() != 1 || matrix.getColumnDimension() != nCols) {
            throw new MatrixDimensionMismatchException(matrix.getRowDimension(), matrix.getColumnDimension(), 1, nCols);
        }
        int iBlock = row / 52;
        int iRow = row - iBlock * 52;
        int mBlockIndex = 0;
        int mIndex = 0;
        double[] mBlock = matrix.blocks[mBlockIndex];
        for (int jBlock = 0; jBlock < this.blockColumns; ++jBlock) {
            int jWidth = this.blockWidth(jBlock);
            double[] block = this.blocks[iBlock * this.blockColumns + jBlock];
            int available = mBlock.length - mIndex;
            if (jWidth > available) {
                System.arraycopy(mBlock, mIndex, block, iRow * jWidth, available);
                mBlock = matrix.blocks[++mBlockIndex];
                System.arraycopy(mBlock, 0, block, iRow * jWidth, jWidth - available);
                mIndex = jWidth - available;
                continue;
            }
            System.arraycopy(mBlock, mIndex, block, iRow * jWidth, jWidth);
            mIndex += jWidth;
        }
    }

    public BlockRealMatrix getColumnMatrix(int column) throws OutOfRangeException {
        MatrixUtils.checkColumnIndex(this, column);
        BlockRealMatrix out = new BlockRealMatrix(this.rows, 1);
        int jBlock = column / 52;
        int jColumn = column - jBlock * 52;
        int jWidth = this.blockWidth(jBlock);
        int outBlockIndex = 0;
        int outIndex = 0;
        double[] outBlock = out.blocks[outBlockIndex];
        for (int iBlock = 0; iBlock < this.blockRows; ++iBlock) {
            int iHeight = this.blockHeight(iBlock);
            double[] block = this.blocks[iBlock * this.blockColumns + jBlock];
            for (int i = 0; i < iHeight; ++i) {
                if (outIndex >= outBlock.length) {
                    outBlock = out.blocks[++outBlockIndex];
                    outIndex = 0;
                }
                outBlock[outIndex++] = block[i * jWidth + jColumn];
            }
        }
        return out;
    }

    public void setColumnMatrix(int column, RealMatrix matrix) throws OutOfRangeException, MatrixDimensionMismatchException {
        try {
            this.setColumnMatrix(column, (BlockRealMatrix)matrix);
        } catch (ClassCastException cce) {
            super.setColumnMatrix(column, matrix);
        }
    }

    void setColumnMatrix(int column, BlockRealMatrix matrix) throws OutOfRangeException, MatrixDimensionMismatchException {
        MatrixUtils.checkColumnIndex(this, column);
        int nRows = this.getRowDimension();
        if (matrix.getRowDimension() != nRows || matrix.getColumnDimension() != 1) {
            throw new MatrixDimensionMismatchException(matrix.getRowDimension(), matrix.getColumnDimension(), nRows, 1);
        }
        int jBlock = column / 52;
        int jColumn = column - jBlock * 52;
        int jWidth = this.blockWidth(jBlock);
        int mBlockIndex = 0;
        int mIndex = 0;
        double[] mBlock = matrix.blocks[mBlockIndex];
        for (int iBlock = 0; iBlock < this.blockRows; ++iBlock) {
            int iHeight = this.blockHeight(iBlock);
            double[] block = this.blocks[iBlock * this.blockColumns + jBlock];
            for (int i = 0; i < iHeight; ++i) {
                if (mIndex >= mBlock.length) {
                    mBlock = matrix.blocks[++mBlockIndex];
                    mIndex = 0;
                }
                block[i * jWidth + jColumn] = mBlock[mIndex++];
            }
        }
    }

    public RealVector getRowVector(int row) throws OutOfRangeException {
        MatrixUtils.checkRowIndex(this, row);
        double[] outData = new double[this.columns];
        int iBlock = row / 52;
        int iRow = row - iBlock * 52;
        int outIndex = 0;
        for (int jBlock = 0; jBlock < this.blockColumns; ++jBlock) {
            int jWidth = this.blockWidth(jBlock);
            double[] block = this.blocks[iBlock * this.blockColumns + jBlock];
            System.arraycopy(block, iRow * jWidth, outData, outIndex, jWidth);
            outIndex += jWidth;
        }
        return new ArrayRealVector(outData, false);
    }

    public void setRowVector(int row, RealVector vector) throws OutOfRangeException, MatrixDimensionMismatchException {
        try {
            this.setRow(row, ((ArrayRealVector)vector).getDataRef());
        } catch (ClassCastException cce) {
            super.setRowVector(row, vector);
        }
    }

    public RealVector getColumnVector(int column) throws OutOfRangeException {
        MatrixUtils.checkColumnIndex(this, column);
        double[] outData = new double[this.rows];
        int jBlock = column / 52;
        int jColumn = column - jBlock * 52;
        int jWidth = this.blockWidth(jBlock);
        int outIndex = 0;
        for (int iBlock = 0; iBlock < this.blockRows; ++iBlock) {
            int iHeight = this.blockHeight(iBlock);
            double[] block = this.blocks[iBlock * this.blockColumns + jBlock];
            for (int i = 0; i < iHeight; ++i) {
                outData[outIndex++] = block[i * jWidth + jColumn];
            }
        }
        return new ArrayRealVector(outData, false);
    }

    public void setColumnVector(int column, RealVector vector) throws OutOfRangeException, MatrixDimensionMismatchException {
        try {
            this.setColumn(column, ((ArrayRealVector)vector).getDataRef());
        } catch (ClassCastException cce) {
            super.setColumnVector(column, vector);
        }
    }

    public double[] getRow(int row) throws OutOfRangeException {
        MatrixUtils.checkRowIndex(this, row);
        double[] out = new double[this.columns];
        int iBlock = row / 52;
        int iRow = row - iBlock * 52;
        int outIndex = 0;
        for (int jBlock = 0; jBlock < this.blockColumns; ++jBlock) {
            int jWidth = this.blockWidth(jBlock);
            double[] block = this.blocks[iBlock * this.blockColumns + jBlock];
            System.arraycopy(block, iRow * jWidth, out, outIndex, jWidth);
            outIndex += jWidth;
        }
        return out;
    }

    public void setRow(int row, double[] array) throws OutOfRangeException, MatrixDimensionMismatchException {
        MatrixUtils.checkRowIndex(this, row);
        int nCols = this.getColumnDimension();
        if (array.length != nCols) {
            throw new MatrixDimensionMismatchException(1, array.length, 1, nCols);
        }
        int iBlock = row / 52;
        int iRow = row - iBlock * 52;
        int outIndex = 0;
        for (int jBlock = 0; jBlock < this.blockColumns; ++jBlock) {
            int jWidth = this.blockWidth(jBlock);
            double[] block = this.blocks[iBlock * this.blockColumns + jBlock];
            System.arraycopy(array, outIndex, block, iRow * jWidth, jWidth);
            outIndex += jWidth;
        }
    }

    public double[] getColumn(int column) throws OutOfRangeException {
        MatrixUtils.checkColumnIndex(this, column);
        double[] out = new double[this.rows];
        int jBlock = column / 52;
        int jColumn = column - jBlock * 52;
        int jWidth = this.blockWidth(jBlock);
        int outIndex = 0;
        for (int iBlock = 0; iBlock < this.blockRows; ++iBlock) {
            int iHeight = this.blockHeight(iBlock);
            double[] block = this.blocks[iBlock * this.blockColumns + jBlock];
            for (int i = 0; i < iHeight; ++i) {
                out[outIndex++] = block[i * jWidth + jColumn];
            }
        }
        return out;
    }

    public void setColumn(int column, double[] array) throws OutOfRangeException, MatrixDimensionMismatchException {
        MatrixUtils.checkColumnIndex(this, column);
        int nRows = this.getRowDimension();
        if (array.length != nRows) {
            throw new MatrixDimensionMismatchException(array.length, 1, nRows, 1);
        }
        int jBlock = column / 52;
        int jColumn = column - jBlock * 52;
        int jWidth = this.blockWidth(jBlock);
        int outIndex = 0;
        for (int iBlock = 0; iBlock < this.blockRows; ++iBlock) {
            int iHeight = this.blockHeight(iBlock);
            double[] block = this.blocks[iBlock * this.blockColumns + jBlock];
            for (int i = 0; i < iHeight; ++i) {
                block[i * jWidth + jColumn] = array[outIndex++];
            }
        }
    }

    public double getEntry(int row, int column) throws OutOfRangeException {
        MatrixUtils.checkMatrixIndex(this, row, column);
        int iBlock = row / 52;
        int jBlock = column / 52;
        int k = (row - iBlock * 52) * this.blockWidth(jBlock) + (column - jBlock * 52);
        return this.blocks[iBlock * this.blockColumns + jBlock][k];
    }

    public void setEntry(int row, int column, double value) throws OutOfRangeException {
        MatrixUtils.checkMatrixIndex(this, row, column);
        int iBlock = row / 52;
        int jBlock = column / 52;
        int k = (row - iBlock * 52) * this.blockWidth(jBlock) + (column - jBlock * 52);
        this.blocks[iBlock * this.blockColumns + jBlock][k] = value;
    }

    public void addToEntry(int row, int column, double increment) throws OutOfRangeException {
        MatrixUtils.checkMatrixIndex(this, row, column);
        int iBlock = row / 52;
        int jBlock = column / 52;
        int k = (row - iBlock * 52) * this.blockWidth(jBlock) + (column - jBlock * 52);
        double[] dArray = this.blocks[iBlock * this.blockColumns + jBlock];
        int n = k;
        dArray[n] = dArray[n] + increment;
    }

    public void multiplyEntry(int row, int column, double factor) throws OutOfRangeException {
        MatrixUtils.checkMatrixIndex(this, row, column);
        int iBlock = row / 52;
        int jBlock = column / 52;
        int k = (row - iBlock * 52) * this.blockWidth(jBlock) + (column - jBlock * 52);
        double[] dArray = this.blocks[iBlock * this.blockColumns + jBlock];
        int n = k;
        dArray[n] = dArray[n] * factor;
    }

    public BlockRealMatrix transpose() {
        int nRows = this.getRowDimension();
        int nCols = this.getColumnDimension();
        BlockRealMatrix out = new BlockRealMatrix(nCols, nRows);
        int blockIndex = 0;
        for (int iBlock = 0; iBlock < this.blockColumns; ++iBlock) {
            for (int jBlock = 0; jBlock < this.blockRows; ++jBlock) {
                double[] outBlock = out.blocks[blockIndex];
                double[] tBlock = this.blocks[jBlock * this.blockColumns + iBlock];
                int pStart = iBlock * 52;
                int pEnd = FastMath.min(pStart + 52, this.columns);
                int qStart = jBlock * 52;
                int qEnd = FastMath.min(qStart + 52, this.rows);
                int k = 0;
                for (int p = pStart; p < pEnd; ++p) {
                    int lInc = pEnd - pStart;
                    int l = p - pStart;
                    for (int q = qStart; q < qEnd; ++q) {
                        outBlock[k] = tBlock[l];
                        ++k;
                        l += lInc;
                    }
                }
                ++blockIndex;
            }
        }
        return out;
    }

    public int getRowDimension() {
        return this.rows;
    }

    public int getColumnDimension() {
        return this.columns;
    }

    public double[] operate(double[] v) throws DimensionMismatchException {
        if (v.length != this.columns) {
            throw new DimensionMismatchException(v.length, this.columns);
        }
        double[] out = new double[this.rows];
        for (int iBlock = 0; iBlock < this.blockRows; ++iBlock) {
            int pStart = iBlock * 52;
            int pEnd = FastMath.min(pStart + 52, this.rows);
            for (int jBlock = 0; jBlock < this.blockColumns; ++jBlock) {
                double[] block = this.blocks[iBlock * this.blockColumns + jBlock];
                int qStart = jBlock * 52;
                int qEnd = FastMath.min(qStart + 52, this.columns);
                int k = 0;
                int p = pStart;
                while (p < pEnd) {
                    int q;
                    double sum = 0.0;
                    for (q = qStart; q < qEnd - 3; q += 4) {
                        sum += block[k] * v[q] + block[k + 1] * v[q + 1] + block[k + 2] * v[q + 2] + block[k + 3] * v[q + 3];
                        k += 4;
                    }
                    while (q < qEnd) {
                        sum += block[k++] * v[q++];
                    }
                    int n = p++;
                    out[n] = out[n] + sum;
                }
            }
        }
        return out;
    }

    public double[] preMultiply(double[] v) throws DimensionMismatchException {
        if (v.length != this.rows) {
            throw new DimensionMismatchException(v.length, this.rows);
        }
        double[] out = new double[this.columns];
        for (int jBlock = 0; jBlock < this.blockColumns; ++jBlock) {
            int jWidth = this.blockWidth(jBlock);
            int jWidth2 = jWidth + jWidth;
            int jWidth3 = jWidth2 + jWidth;
            int jWidth4 = jWidth3 + jWidth;
            int qStart = jBlock * 52;
            int qEnd = FastMath.min(qStart + 52, this.columns);
            for (int iBlock = 0; iBlock < this.blockRows; ++iBlock) {
                double[] block = this.blocks[iBlock * this.blockColumns + jBlock];
                int pStart = iBlock * 52;
                int pEnd = FastMath.min(pStart + 52, this.rows);
                int q = qStart;
                while (q < qEnd) {
                    int p;
                    int k = q - qStart;
                    double sum = 0.0;
                    for (p = pStart; p < pEnd - 3; p += 4) {
                        sum += block[k] * v[p] + block[k + jWidth] * v[p + 1] + block[k + jWidth2] * v[p + 2] + block[k + jWidth3] * v[p + 3];
                        k += jWidth4;
                    }
                    while (p < pEnd) {
                        sum += block[k] * v[p++];
                        k += jWidth;
                    }
                    int n = q++;
                    out[n] = out[n] + sum;
                }
            }
        }
        return out;
    }

    public double walkInRowOrder(RealMatrixChangingVisitor visitor) {
        visitor.start(this.rows, this.columns, 0, this.rows - 1, 0, this.columns - 1);
        for (int iBlock = 0; iBlock < this.blockRows; ++iBlock) {
            int pStart = iBlock * 52;
            int pEnd = FastMath.min(pStart + 52, this.rows);
            for (int p = pStart; p < pEnd; ++p) {
                for (int jBlock = 0; jBlock < this.blockColumns; ++jBlock) {
                    int jWidth = this.blockWidth(jBlock);
                    int qStart = jBlock * 52;
                    int qEnd = FastMath.min(qStart + 52, this.columns);
                    double[] block = this.blocks[iBlock * this.blockColumns + jBlock];
                    int k = (p - pStart) * jWidth;
                    for (int q = qStart; q < qEnd; ++q) {
                        block[k] = visitor.visit(p, q, block[k]);
                        ++k;
                    }
                }
            }
        }
        return visitor.end();
    }

    public double walkInRowOrder(RealMatrixPreservingVisitor visitor) {
        visitor.start(this.rows, this.columns, 0, this.rows - 1, 0, this.columns - 1);
        for (int iBlock = 0; iBlock < this.blockRows; ++iBlock) {
            int pStart = iBlock * 52;
            int pEnd = FastMath.min(pStart + 52, this.rows);
            for (int p = pStart; p < pEnd; ++p) {
                for (int jBlock = 0; jBlock < this.blockColumns; ++jBlock) {
                    int jWidth = this.blockWidth(jBlock);
                    int qStart = jBlock * 52;
                    int qEnd = FastMath.min(qStart + 52, this.columns);
                    double[] block = this.blocks[iBlock * this.blockColumns + jBlock];
                    int k = (p - pStart) * jWidth;
                    for (int q = qStart; q < qEnd; ++q) {
                        visitor.visit(p, q, block[k]);
                        ++k;
                    }
                }
            }
        }
        return visitor.end();
    }

    public double walkInRowOrder(RealMatrixChangingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
        visitor.start(this.rows, this.columns, startRow, endRow, startColumn, endColumn);
        for (int iBlock = startRow / 52; iBlock < 1 + endRow / 52; ++iBlock) {
            int p0 = iBlock * 52;
            int pStart = FastMath.max(startRow, p0);
            int pEnd = FastMath.min((iBlock + 1) * 52, 1 + endRow);
            for (int p = pStart; p < pEnd; ++p) {
                for (int jBlock = startColumn / 52; jBlock < 1 + endColumn / 52; ++jBlock) {
                    int jWidth = this.blockWidth(jBlock);
                    int q0 = jBlock * 52;
                    int qStart = FastMath.max(startColumn, q0);
                    int qEnd = FastMath.min((jBlock + 1) * 52, 1 + endColumn);
                    double[] block = this.blocks[iBlock * this.blockColumns + jBlock];
                    int k = (p - p0) * jWidth + qStart - q0;
                    for (int q = qStart; q < qEnd; ++q) {
                        block[k] = visitor.visit(p, q, block[k]);
                        ++k;
                    }
                }
            }
        }
        return visitor.end();
    }

    public double walkInRowOrder(RealMatrixPreservingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
        visitor.start(this.rows, this.columns, startRow, endRow, startColumn, endColumn);
        for (int iBlock = startRow / 52; iBlock < 1 + endRow / 52; ++iBlock) {
            int p0 = iBlock * 52;
            int pStart = FastMath.max(startRow, p0);
            int pEnd = FastMath.min((iBlock + 1) * 52, 1 + endRow);
            for (int p = pStart; p < pEnd; ++p) {
                for (int jBlock = startColumn / 52; jBlock < 1 + endColumn / 52; ++jBlock) {
                    int jWidth = this.blockWidth(jBlock);
                    int q0 = jBlock * 52;
                    int qStart = FastMath.max(startColumn, q0);
                    int qEnd = FastMath.min((jBlock + 1) * 52, 1 + endColumn);
                    double[] block = this.blocks[iBlock * this.blockColumns + jBlock];
                    int k = (p - p0) * jWidth + qStart - q0;
                    for (int q = qStart; q < qEnd; ++q) {
                        visitor.visit(p, q, block[k]);
                        ++k;
                    }
                }
            }
        }
        return visitor.end();
    }

    public double walkInOptimizedOrder(RealMatrixChangingVisitor visitor) {
        visitor.start(this.rows, this.columns, 0, this.rows - 1, 0, this.columns - 1);
        int blockIndex = 0;
        for (int iBlock = 0; iBlock < this.blockRows; ++iBlock) {
            int pStart = iBlock * 52;
            int pEnd = FastMath.min(pStart + 52, this.rows);
            for (int jBlock = 0; jBlock < this.blockColumns; ++jBlock) {
                int qStart = jBlock * 52;
                int qEnd = FastMath.min(qStart + 52, this.columns);
                double[] block = this.blocks[blockIndex];
                int k = 0;
                for (int p = pStart; p < pEnd; ++p) {
                    for (int q = qStart; q < qEnd; ++q) {
                        block[k] = visitor.visit(p, q, block[k]);
                        ++k;
                    }
                }
                ++blockIndex;
            }
        }
        return visitor.end();
    }

    public double walkInOptimizedOrder(RealMatrixPreservingVisitor visitor) {
        visitor.start(this.rows, this.columns, 0, this.rows - 1, 0, this.columns - 1);
        int blockIndex = 0;
        for (int iBlock = 0; iBlock < this.blockRows; ++iBlock) {
            int pStart = iBlock * 52;
            int pEnd = FastMath.min(pStart + 52, this.rows);
            for (int jBlock = 0; jBlock < this.blockColumns; ++jBlock) {
                int qStart = jBlock * 52;
                int qEnd = FastMath.min(qStart + 52, this.columns);
                double[] block = this.blocks[blockIndex];
                int k = 0;
                for (int p = pStart; p < pEnd; ++p) {
                    for (int q = qStart; q < qEnd; ++q) {
                        visitor.visit(p, q, block[k]);
                        ++k;
                    }
                }
                ++blockIndex;
            }
        }
        return visitor.end();
    }

    public double walkInOptimizedOrder(RealMatrixChangingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
        visitor.start(this.rows, this.columns, startRow, endRow, startColumn, endColumn);
        for (int iBlock = startRow / 52; iBlock < 1 + endRow / 52; ++iBlock) {
            int p0 = iBlock * 52;
            int pStart = FastMath.max(startRow, p0);
            int pEnd = FastMath.min((iBlock + 1) * 52, 1 + endRow);
            for (int jBlock = startColumn / 52; jBlock < 1 + endColumn / 52; ++jBlock) {
                int jWidth = this.blockWidth(jBlock);
                int q0 = jBlock * 52;
                int qStart = FastMath.max(startColumn, q0);
                int qEnd = FastMath.min((jBlock + 1) * 52, 1 + endColumn);
                double[] block = this.blocks[iBlock * this.blockColumns + jBlock];
                for (int p = pStart; p < pEnd; ++p) {
                    int k = (p - p0) * jWidth + qStart - q0;
                    for (int q = qStart; q < qEnd; ++q) {
                        block[k] = visitor.visit(p, q, block[k]);
                        ++k;
                    }
                }
            }
        }
        return visitor.end();
    }

    public double walkInOptimizedOrder(RealMatrixPreservingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
        visitor.start(this.rows, this.columns, startRow, endRow, startColumn, endColumn);
        for (int iBlock = startRow / 52; iBlock < 1 + endRow / 52; ++iBlock) {
            int p0 = iBlock * 52;
            int pStart = FastMath.max(startRow, p0);
            int pEnd = FastMath.min((iBlock + 1) * 52, 1 + endRow);
            for (int jBlock = startColumn / 52; jBlock < 1 + endColumn / 52; ++jBlock) {
                int jWidth = this.blockWidth(jBlock);
                int q0 = jBlock * 52;
                int qStart = FastMath.max(startColumn, q0);
                int qEnd = FastMath.min((jBlock + 1) * 52, 1 + endColumn);
                double[] block = this.blocks[iBlock * this.blockColumns + jBlock];
                for (int p = pStart; p < pEnd; ++p) {
                    int k = (p - p0) * jWidth + qStart - q0;
                    for (int q = qStart; q < qEnd; ++q) {
                        visitor.visit(p, q, block[k]);
                        ++k;
                    }
                }
            }
        }
        return visitor.end();
    }

    private int blockHeight(int blockRow) {
        return blockRow == this.blockRows - 1 ? this.rows - blockRow * 52 : 52;
    }

    private int blockWidth(int blockColumn) {
        return blockColumn == this.blockColumns - 1 ? this.columns - blockColumn * 52 : 52;
    }
}

