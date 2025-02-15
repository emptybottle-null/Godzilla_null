/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.linear;

import java.io.Serializable;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.AbstractFieldMatrix;
import org.apache.commons.math3.linear.ArrayFieldVector;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.linear.FieldMatrixChangingVisitor;
import org.apache.commons.math3.linear.FieldMatrixPreservingVisitor;
import org.apache.commons.math3.linear.FieldVector;
import org.apache.commons.math3.linear.MatrixDimensionMismatchException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class BlockFieldMatrix<T extends FieldElement<T>>
extends AbstractFieldMatrix<T>
implements Serializable {
    public static final int BLOCK_SIZE = 36;
    private static final long serialVersionUID = -4602336630143123183L;
    private final T[][] blocks;
    private final int rows;
    private final int columns;
    private final int blockRows;
    private final int blockColumns;

    public BlockFieldMatrix(Field<T> field, int rows, int columns) throws NotStrictlyPositiveException {
        super(field, rows, columns);
        this.rows = rows;
        this.columns = columns;
        this.blockRows = (rows + 36 - 1) / 36;
        this.blockColumns = (columns + 36 - 1) / 36;
        this.blocks = BlockFieldMatrix.createBlocksLayout(field, (int)rows, (int)columns);
    }

    public BlockFieldMatrix(T[][] rawData) throws DimensionMismatchException {
        this(rawData.length, rawData[0].length, BlockFieldMatrix.toBlocksLayout(rawData), false);
    }

    public BlockFieldMatrix(int rows, int columns, T[][] blockData, boolean copyArray) throws DimensionMismatchException, NotStrictlyPositiveException {
        super(BlockFieldMatrix.extractField(blockData), rows, columns);
        this.rows = rows;
        this.columns = columns;
        this.blockRows = (rows + 36 - 1) / 36;
        this.blockColumns = (columns + 36 - 1) / 36;
        this.blocks = copyArray ? (FieldElement[][])MathArrays.buildArray(this.getField(), this.blockRows * this.blockColumns, -1) : blockData;
        int index = 0;
        for (int iBlock = 0; iBlock < this.blockRows; ++iBlock) {
            int iHeight = this.blockHeight(iBlock);
            int jBlock = 0;
            while (jBlock < this.blockColumns) {
                if (blockData[index].length != iHeight * this.blockWidth(jBlock)) {
                    throw new DimensionMismatchException(blockData[index].length, iHeight * this.blockWidth(jBlock));
                }
                if (copyArray) {
                    this.blocks[index] = (FieldElement[])blockData[index].clone();
                }
                ++jBlock;
                ++index;
            }
        }
    }

    public static <T extends FieldElement<T>> T[][] toBlocksLayout(T[][] rawData) throws DimensionMismatchException {
        int rows = rawData.length;
        int columns = rawData[0].length;
        int blockRows = (rows + 36 - 1) / 36;
        int blockColumns = (columns + 36 - 1) / 36;
        for (int i = 0; i < rawData.length; ++i) {
            int length = rawData[i].length;
            if (length == columns) continue;
            throw new DimensionMismatchException(columns, length);
        }
        Field field = BlockFieldMatrix.extractField(rawData);
        FieldElement[][] blocks = (FieldElement[][])MathArrays.buildArray(field, blockRows * blockColumns, -1);
        int blockIndex = 0;
        for (int iBlock = 0; iBlock < blockRows; ++iBlock) {
            int pStart = iBlock * 36;
            int pEnd = FastMath.min(pStart + 36, rows);
            int iHeight = pEnd - pStart;
            for (int jBlock = 0; jBlock < blockColumns; ++jBlock) {
                int qStart = jBlock * 36;
                int qEnd = FastMath.min(qStart + 36, columns);
                int jWidth = qEnd - qStart;
                FieldElement[] block = (FieldElement[])MathArrays.buildArray(field, iHeight * jWidth);
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

    public static <T extends FieldElement<T>> T[][] createBlocksLayout(Field<T> field, int rows, int columns) {
        int blockRows = (rows + 36 - 1) / 36;
        int blockColumns = (columns + 36 - 1) / 36;
        FieldElement[][] blocks = (FieldElement[][])MathArrays.buildArray(field, blockRows * blockColumns, -1);
        int blockIndex = 0;
        for (int iBlock = 0; iBlock < blockRows; ++iBlock) {
            int pStart = iBlock * 36;
            int pEnd = FastMath.min(pStart + 36, rows);
            int iHeight = pEnd - pStart;
            for (int jBlock = 0; jBlock < blockColumns; ++jBlock) {
                int qStart = jBlock * 36;
                int qEnd = FastMath.min(qStart + 36, columns);
                int jWidth = qEnd - qStart;
                blocks[blockIndex] = (FieldElement[])MathArrays.buildArray(field, iHeight * jWidth);
                ++blockIndex;
            }
        }
        return blocks;
    }

    @Override
    public FieldMatrix<T> createMatrix(int rowDimension, int columnDimension) throws NotStrictlyPositiveException {
        return new BlockFieldMatrix(this.getField(), rowDimension, columnDimension);
    }

    @Override
    public FieldMatrix<T> copy() {
        BlockFieldMatrix copied = new BlockFieldMatrix(this.getField(), this.rows, this.columns);
        for (int i = 0; i < this.blocks.length; ++i) {
            System.arraycopy(this.blocks[i], 0, copied.blocks[i], 0, this.blocks[i].length);
        }
        return copied;
    }

    @Override
    public FieldMatrix<T> add(FieldMatrix<T> m) throws MatrixDimensionMismatchException {
        try {
            return this.add((BlockFieldMatrix)m);
        } catch (ClassCastException cce) {
            this.checkAdditionCompatible(m);
            BlockFieldMatrix out = new BlockFieldMatrix(this.getField(), this.rows, this.columns);
            int blockIndex = 0;
            for (int iBlock = 0; iBlock < out.blockRows; ++iBlock) {
                for (int jBlock = 0; jBlock < out.blockColumns; ++jBlock) {
                    T[] outBlock = out.blocks[blockIndex];
                    T[] tBlock = this.blocks[blockIndex];
                    int pStart = iBlock * 36;
                    int pEnd = FastMath.min(pStart + 36, this.rows);
                    int qStart = jBlock * 36;
                    int qEnd = FastMath.min(qStart + 36, this.columns);
                    int k = 0;
                    for (int p = pStart; p < pEnd; ++p) {
                        for (int q = qStart; q < qEnd; ++q) {
                            outBlock[k] = (FieldElement)tBlock[k].add(m.getEntry(p, q));
                            ++k;
                        }
                    }
                    ++blockIndex;
                }
            }
            return out;
        }
    }

    @Override
    public BlockFieldMatrix<T> add(BlockFieldMatrix<T> m) throws MatrixDimensionMismatchException {
        this.checkAdditionCompatible(m);
        BlockFieldMatrix out = new BlockFieldMatrix(this.getField(), this.rows, this.columns);
        for (int blockIndex = 0; blockIndex < out.blocks.length; ++blockIndex) {
            T[] outBlock = out.blocks[blockIndex];
            T[] tBlock = this.blocks[blockIndex];
            T[] mBlock = m.blocks[blockIndex];
            for (int k = 0; k < outBlock.length; ++k) {
                outBlock[k] = (FieldElement)tBlock[k].add(mBlock[k]);
            }
        }
        return out;
    }

    @Override
    public FieldMatrix<T> subtract(FieldMatrix<T> m) throws MatrixDimensionMismatchException {
        try {
            return this.subtract((BlockFieldMatrix)m);
        } catch (ClassCastException cce) {
            this.checkSubtractionCompatible(m);
            BlockFieldMatrix out = new BlockFieldMatrix(this.getField(), this.rows, this.columns);
            int blockIndex = 0;
            for (int iBlock = 0; iBlock < out.blockRows; ++iBlock) {
                for (int jBlock = 0; jBlock < out.blockColumns; ++jBlock) {
                    T[] outBlock = out.blocks[blockIndex];
                    T[] tBlock = this.blocks[blockIndex];
                    int pStart = iBlock * 36;
                    int pEnd = FastMath.min(pStart + 36, this.rows);
                    int qStart = jBlock * 36;
                    int qEnd = FastMath.min(qStart + 36, this.columns);
                    int k = 0;
                    for (int p = pStart; p < pEnd; ++p) {
                        for (int q = qStart; q < qEnd; ++q) {
                            outBlock[k] = (FieldElement)tBlock[k].subtract(m.getEntry(p, q));
                            ++k;
                        }
                    }
                    ++blockIndex;
                }
            }
            return out;
        }
    }

    @Override
    public BlockFieldMatrix<T> subtract(BlockFieldMatrix<T> m) throws MatrixDimensionMismatchException {
        this.checkSubtractionCompatible(m);
        BlockFieldMatrix out = new BlockFieldMatrix(this.getField(), this.rows, this.columns);
        for (int blockIndex = 0; blockIndex < out.blocks.length; ++blockIndex) {
            T[] outBlock = out.blocks[blockIndex];
            T[] tBlock = this.blocks[blockIndex];
            T[] mBlock = m.blocks[blockIndex];
            for (int k = 0; k < outBlock.length; ++k) {
                outBlock[k] = (FieldElement)tBlock[k].subtract(mBlock[k]);
            }
        }
        return out;
    }

    @Override
    public FieldMatrix<T> scalarAdd(T d) {
        BlockFieldMatrix out = new BlockFieldMatrix(this.getField(), this.rows, this.columns);
        for (int blockIndex = 0; blockIndex < out.blocks.length; ++blockIndex) {
            T[] outBlock = out.blocks[blockIndex];
            T[] tBlock = this.blocks[blockIndex];
            for (int k = 0; k < outBlock.length; ++k) {
                outBlock[k] = (FieldElement)tBlock[k].add(d);
            }
        }
        return out;
    }

    @Override
    public FieldMatrix<T> scalarMultiply(T d) {
        BlockFieldMatrix out = new BlockFieldMatrix(this.getField(), this.rows, this.columns);
        for (int blockIndex = 0; blockIndex < out.blocks.length; ++blockIndex) {
            T[] outBlock = out.blocks[blockIndex];
            T[] tBlock = this.blocks[blockIndex];
            for (int k = 0; k < outBlock.length; ++k) {
                outBlock[k] = (FieldElement)tBlock[k].multiply(d);
            }
        }
        return out;
    }

    @Override
    public FieldMatrix<T> multiply(FieldMatrix<T> m) throws DimensionMismatchException {
        try {
            return this.multiply((BlockFieldMatrix)m);
        } catch (ClassCastException cce) {
            this.checkMultiplicationCompatible(m);
            BlockFieldMatrix out = new BlockFieldMatrix(this.getField(), this.rows, m.getColumnDimension());
            FieldElement zero = (FieldElement)this.getField().getZero();
            int blockIndex = 0;
            for (int iBlock = 0; iBlock < out.blockRows; ++iBlock) {
                int pStart = iBlock * 36;
                int pEnd = FastMath.min(pStart + 36, this.rows);
                for (int jBlock = 0; jBlock < out.blockColumns; ++jBlock) {
                    int qStart = jBlock * 36;
                    int qEnd = FastMath.min(qStart + 36, m.getColumnDimension());
                    T[] outBlock = out.blocks[blockIndex];
                    for (int kBlock = 0; kBlock < this.blockColumns; ++kBlock) {
                        int kWidth = this.blockWidth(kBlock);
                        T[] tBlock = this.blocks[iBlock * this.blockColumns + kBlock];
                        int rStart = kBlock * 36;
                        int k = 0;
                        for (int p = pStart; p < pEnd; ++p) {
                            int lStart = (p - pStart) * kWidth;
                            int lEnd = lStart + kWidth;
                            for (int q = qStart; q < qEnd; ++q) {
                                FieldElement sum = zero;
                                int r = rStart;
                                for (int l = lStart; l < lEnd; ++l) {
                                    sum = (FieldElement)sum.add(tBlock[l].multiply(m.getEntry(r, q)));
                                    ++r;
                                }
                                outBlock[k] = outBlock[k].add((FieldElement)sum);
                                ++k;
                            }
                        }
                    }
                    ++blockIndex;
                }
            }
            return out;
        }
    }

    @Override
    public BlockFieldMatrix<T> multiply(BlockFieldMatrix<T> m) throws DimensionMismatchException {
        this.checkMultiplicationCompatible(m);
        BlockFieldMatrix out = new BlockFieldMatrix(this.getField(), this.rows, m.columns);
        FieldElement zero = (FieldElement)this.getField().getZero();
        int blockIndex = 0;
        for (int iBlock = 0; iBlock < out.blockRows; ++iBlock) {
            int pStart = iBlock * 36;
            int pEnd = FastMath.min(pStart + 36, this.rows);
            for (int jBlock = 0; jBlock < out.blockColumns; ++jBlock) {
                int jWidth = super.blockWidth(jBlock);
                int jWidth2 = jWidth + jWidth;
                int jWidth3 = jWidth2 + jWidth;
                int jWidth4 = jWidth3 + jWidth;
                T[] outBlock = out.blocks[blockIndex];
                for (int kBlock = 0; kBlock < this.blockColumns; ++kBlock) {
                    int kWidth = this.blockWidth(kBlock);
                    T[] tBlock = this.blocks[iBlock * this.blockColumns + kBlock];
                    T[] mBlock = m.blocks[kBlock * m.blockColumns + jBlock];
                    int k = 0;
                    for (int p = pStart; p < pEnd; ++p) {
                        int lStart = (p - pStart) * kWidth;
                        int lEnd = lStart + kWidth;
                        for (int nStart = 0; nStart < jWidth; ++nStart) {
                            FieldElement sum = zero;
                            int l = lStart;
                            int n = nStart;
                            while (l < lEnd - 3) {
                                sum = (FieldElement)((FieldElement)((FieldElement)((FieldElement)sum.add(tBlock[l].multiply(mBlock[n]))).add(tBlock[l + 1].multiply(mBlock[n + jWidth]))).add(tBlock[l + 2].multiply(mBlock[n + jWidth2]))).add(tBlock[l + 3].multiply(mBlock[n + jWidth3]));
                                l += 4;
                                n += jWidth4;
                            }
                            while (l < lEnd) {
                                sum = (FieldElement)sum.add(tBlock[l++].multiply(mBlock[n]));
                                n += jWidth;
                            }
                            outBlock[k] = outBlock[k].add((FieldElement)sum);
                            ++k;
                        }
                    }
                }
                ++blockIndex;
            }
        }
        return out;
    }

    @Override
    public T[][] getData() {
        FieldElement[][] data = (FieldElement[][])MathArrays.buildArray(this.getField(), this.getRowDimension(), this.getColumnDimension());
        int lastColumns = this.columns - (this.blockColumns - 1) * 36;
        for (int iBlock = 0; iBlock < this.blockRows; ++iBlock) {
            int pStart = iBlock * 36;
            int pEnd = FastMath.min(pStart + 36, this.rows);
            int regularPos = 0;
            int lastPos = 0;
            for (int p = pStart; p < pEnd; ++p) {
                FieldElement[] dataP = data[p];
                int blockIndex = iBlock * this.blockColumns;
                int dataPos = 0;
                for (int jBlock = 0; jBlock < this.blockColumns - 1; ++jBlock) {
                    System.arraycopy(this.blocks[blockIndex++], regularPos, dataP, dataPos, 36);
                    dataPos += 36;
                }
                System.arraycopy(this.blocks[blockIndex], lastPos, dataP, dataPos, lastColumns);
                regularPos += 36;
                lastPos += lastColumns;
            }
        }
        return data;
    }

    @Override
    public FieldMatrix<T> getSubMatrix(int startRow, int endRow, int startColumn, int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        this.checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
        BlockFieldMatrix out = new BlockFieldMatrix(this.getField(), endRow - startRow + 1, endColumn - startColumn + 1);
        int blockStartRow = startRow / 36;
        int rowsShift = startRow % 36;
        int blockStartColumn = startColumn / 36;
        int columnsShift = startColumn % 36;
        int pBlock = blockStartRow;
        for (int iBlock = 0; iBlock < out.blockRows; ++iBlock) {
            int iHeight = super.blockHeight(iBlock);
            int qBlock = blockStartColumn;
            for (int jBlock = 0; jBlock < out.blockColumns; ++jBlock) {
                int width2;
                int jWidth = super.blockWidth(jBlock);
                int outIndex = iBlock * out.blockColumns + jBlock;
                T[] outBlock = out.blocks[outIndex];
                int index = pBlock * this.blockColumns + qBlock;
                int width = this.blockWidth(qBlock);
                int heightExcess = iHeight + rowsShift - 36;
                int widthExcess = jWidth + columnsShift - 36;
                if (heightExcess > 0) {
                    if (widthExcess > 0) {
                        width2 = this.blockWidth(qBlock + 1);
                        this.copyBlockPart((FieldElement[])this.blocks[index], width, rowsShift, 36, columnsShift, 36, (FieldElement[])outBlock, jWidth, 0, 0);
                        this.copyBlockPart((FieldElement[])this.blocks[index + 1], width2, rowsShift, 36, 0, widthExcess, (FieldElement[])outBlock, jWidth, 0, jWidth - widthExcess);
                        this.copyBlockPart((FieldElement[])this.blocks[index + this.blockColumns], width, 0, heightExcess, columnsShift, 36, (FieldElement[])outBlock, jWidth, iHeight - heightExcess, 0);
                        this.copyBlockPart((FieldElement[])this.blocks[index + this.blockColumns + 1], width2, 0, heightExcess, 0, widthExcess, (FieldElement[])outBlock, jWidth, iHeight - heightExcess, jWidth - widthExcess);
                    } else {
                        this.copyBlockPart((FieldElement[])this.blocks[index], width, rowsShift, 36, columnsShift, jWidth + columnsShift, (FieldElement[])outBlock, jWidth, 0, 0);
                        this.copyBlockPart((FieldElement[])this.blocks[index + this.blockColumns], width, 0, heightExcess, columnsShift, jWidth + columnsShift, (FieldElement[])outBlock, jWidth, iHeight - heightExcess, 0);
                    }
                } else if (widthExcess > 0) {
                    width2 = this.blockWidth(qBlock + 1);
                    this.copyBlockPart((FieldElement[])this.blocks[index], width, rowsShift, iHeight + rowsShift, columnsShift, 36, (FieldElement[])outBlock, jWidth, 0, 0);
                    this.copyBlockPart((FieldElement[])this.blocks[index + 1], width2, rowsShift, iHeight + rowsShift, 0, widthExcess, (FieldElement[])outBlock, jWidth, 0, jWidth - widthExcess);
                } else {
                    this.copyBlockPart((FieldElement[])this.blocks[index], width, rowsShift, iHeight + rowsShift, columnsShift, jWidth + columnsShift, (FieldElement[])outBlock, jWidth, 0, 0);
                }
                ++qBlock;
            }
            ++pBlock;
        }
        return out;
    }

    private void copyBlockPart(T[] srcBlock, int srcWidth, int srcStartRow, int srcEndRow, int srcStartColumn, int srcEndColumn, T[] dstBlock, int dstWidth, int dstStartRow, int dstStartColumn) {
        int length = srcEndColumn - srcStartColumn;
        int srcPos = srcStartRow * srcWidth + srcStartColumn;
        int dstPos = dstStartRow * dstWidth + dstStartColumn;
        for (int srcRow = srcStartRow; srcRow < srcEndRow; ++srcRow) {
            System.arraycopy(srcBlock, srcPos, dstBlock, dstPos, length);
            srcPos += srcWidth;
            dstPos += dstWidth;
        }
    }

    @Override
    public void setSubMatrix(T[][] subMatrix, int row, int column) throws DimensionMismatchException, OutOfRangeException, NoDataException, NullArgumentException {
        MathUtils.checkNotNull(subMatrix);
        int refLength = subMatrix[0].length;
        if (refLength == 0) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN);
        }
        int endRow = row + subMatrix.length - 1;
        int endColumn = column + refLength - 1;
        this.checkSubMatrixIndex(row, endRow, column, endColumn);
        for (T[] subRow : subMatrix) {
            if (subRow.length == refLength) continue;
            throw new DimensionMismatchException(refLength, subRow.length);
        }
        int blockStartRow = row / 36;
        int blockEndRow = (endRow + 36) / 36;
        int blockStartColumn = column / 36;
        int blockEndColumn = (endColumn + 36) / 36;
        for (int iBlock = blockStartRow; iBlock < blockEndRow; ++iBlock) {
            int iHeight = this.blockHeight(iBlock);
            int firstRow = iBlock * 36;
            int iStart = FastMath.max(row, firstRow);
            int iEnd = FastMath.min(endRow + 1, firstRow + iHeight);
            for (int jBlock = blockStartColumn; jBlock < blockEndColumn; ++jBlock) {
                int jWidth = this.blockWidth(jBlock);
                int firstColumn = jBlock * 36;
                int jStart = FastMath.max(column, firstColumn);
                int jEnd = FastMath.min(endColumn + 1, firstColumn + jWidth);
                int jLength = jEnd - jStart;
                T[] block = this.blocks[iBlock * this.blockColumns + jBlock];
                for (int i = iStart; i < iEnd; ++i) {
                    System.arraycopy(subMatrix[i - row], jStart - column, block, (i - firstRow) * jWidth + (jStart - firstColumn), jLength);
                }
            }
        }
    }

    @Override
    public FieldMatrix<T> getRowMatrix(int row) throws OutOfRangeException {
        this.checkRowIndex(row);
        BlockFieldMatrix out = new BlockFieldMatrix(this.getField(), 1, this.columns);
        int iBlock = row / 36;
        int iRow = row - iBlock * 36;
        int outBlockIndex = 0;
        int outIndex = 0;
        T[] outBlock = out.blocks[outBlockIndex];
        for (int jBlock = 0; jBlock < this.blockColumns; ++jBlock) {
            int jWidth = this.blockWidth(jBlock);
            T[] block = this.blocks[iBlock * this.blockColumns + jBlock];
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

    @Override
    public void setRowMatrix(int row, FieldMatrix<T> matrix) throws MatrixDimensionMismatchException, OutOfRangeException {
        try {
            this.setRowMatrix(row, (BlockFieldMatrix)matrix);
        } catch (ClassCastException cce) {
            super.setRowMatrix(row, matrix);
        }
    }

    @Override
    public void setRowMatrix(int row, BlockFieldMatrix<T> matrix) throws MatrixDimensionMismatchException, OutOfRangeException {
        this.checkRowIndex(row);
        int nCols = this.getColumnDimension();
        if (matrix.getRowDimension() != 1 || matrix.getColumnDimension() != nCols) {
            throw new MatrixDimensionMismatchException(matrix.getRowDimension(), matrix.getColumnDimension(), 1, nCols);
        }
        int iBlock = row / 36;
        int iRow = row - iBlock * 36;
        int mBlockIndex = 0;
        int mIndex = 0;
        T[] mBlock = matrix.blocks[mBlockIndex];
        for (int jBlock = 0; jBlock < this.blockColumns; ++jBlock) {
            int jWidth = this.blockWidth(jBlock);
            T[] block = this.blocks[iBlock * this.blockColumns + jBlock];
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

    @Override
    public FieldMatrix<T> getColumnMatrix(int column) throws OutOfRangeException {
        this.checkColumnIndex(column);
        BlockFieldMatrix out = new BlockFieldMatrix(this.getField(), this.rows, 1);
        int jBlock = column / 36;
        int jColumn = column - jBlock * 36;
        int jWidth = this.blockWidth(jBlock);
        int outBlockIndex = 0;
        int outIndex = 0;
        T[] outBlock = out.blocks[outBlockIndex];
        for (int iBlock = 0; iBlock < this.blockRows; ++iBlock) {
            int iHeight = this.blockHeight(iBlock);
            T[] block = this.blocks[iBlock * this.blockColumns + jBlock];
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

    @Override
    public void setColumnMatrix(int column, FieldMatrix<T> matrix) throws MatrixDimensionMismatchException, OutOfRangeException {
        try {
            this.setColumnMatrix(column, (BlockFieldMatrix)matrix);
        } catch (ClassCastException cce) {
            super.setColumnMatrix(column, matrix);
        }
    }

    @Override
    void setColumnMatrix(int column, BlockFieldMatrix<T> matrix) throws MatrixDimensionMismatchException, OutOfRangeException {
        this.checkColumnIndex(column);
        int nRows = this.getRowDimension();
        if (matrix.getRowDimension() != nRows || matrix.getColumnDimension() != 1) {
            throw new MatrixDimensionMismatchException(matrix.getRowDimension(), matrix.getColumnDimension(), nRows, 1);
        }
        int jBlock = column / 36;
        int jColumn = column - jBlock * 36;
        int jWidth = this.blockWidth(jBlock);
        int mBlockIndex = 0;
        int mIndex = 0;
        T[] mBlock = matrix.blocks[mBlockIndex];
        for (int iBlock = 0; iBlock < this.blockRows; ++iBlock) {
            int iHeight = this.blockHeight(iBlock);
            T[] block = this.blocks[iBlock * this.blockColumns + jBlock];
            for (int i = 0; i < iHeight; ++i) {
                if (mIndex >= mBlock.length) {
                    mBlock = matrix.blocks[++mBlockIndex];
                    mIndex = 0;
                }
                block[i * jWidth + jColumn] = mBlock[mIndex++];
            }
        }
    }

    @Override
    public FieldVector<T> getRowVector(int row) throws OutOfRangeException {
        this.checkRowIndex(row);
        FieldElement[] outData = (FieldElement[])MathArrays.buildArray(this.getField(), this.columns);
        int iBlock = row / 36;
        int iRow = row - iBlock * 36;
        int outIndex = 0;
        for (int jBlock = 0; jBlock < this.blockColumns; ++jBlock) {
            int jWidth = this.blockWidth(jBlock);
            T[] block = this.blocks[iBlock * this.blockColumns + jBlock];
            System.arraycopy(block, iRow * jWidth, outData, outIndex, jWidth);
            outIndex += jWidth;
        }
        return new ArrayFieldVector(this.getField(), outData, false);
    }

    @Override
    public void setRowVector(int row, FieldVector<T> vector) throws MatrixDimensionMismatchException, OutOfRangeException {
        try {
            this.setRow(row, ((ArrayFieldVector)vector).getDataRef());
        } catch (ClassCastException cce) {
            super.setRowVector(row, vector);
        }
    }

    @Override
    public FieldVector<T> getColumnVector(int column) throws OutOfRangeException {
        this.checkColumnIndex(column);
        FieldElement[] outData = (FieldElement[])MathArrays.buildArray(this.getField(), this.rows);
        int jBlock = column / 36;
        int jColumn = column - jBlock * 36;
        int jWidth = this.blockWidth(jBlock);
        int outIndex = 0;
        for (int iBlock = 0; iBlock < this.blockRows; ++iBlock) {
            int iHeight = this.blockHeight(iBlock);
            T[] block = this.blocks[iBlock * this.blockColumns + jBlock];
            for (int i = 0; i < iHeight; ++i) {
                outData[outIndex++] = block[i * jWidth + jColumn];
            }
        }
        return new ArrayFieldVector(this.getField(), outData, false);
    }

    @Override
    public void setColumnVector(int column, FieldVector<T> vector) throws OutOfRangeException, MatrixDimensionMismatchException {
        try {
            this.setColumn(column, ((ArrayFieldVector)vector).getDataRef());
        } catch (ClassCastException cce) {
            super.setColumnVector(column, vector);
        }
    }

    @Override
    public T[] getRow(int row) throws OutOfRangeException {
        this.checkRowIndex(row);
        FieldElement[] out = (FieldElement[])MathArrays.buildArray(this.getField(), this.columns);
        int iBlock = row / 36;
        int iRow = row - iBlock * 36;
        int outIndex = 0;
        for (int jBlock = 0; jBlock < this.blockColumns; ++jBlock) {
            int jWidth = this.blockWidth(jBlock);
            T[] block = this.blocks[iBlock * this.blockColumns + jBlock];
            System.arraycopy(block, iRow * jWidth, out, outIndex, jWidth);
            outIndex += jWidth;
        }
        return out;
    }

    @Override
    public void setRow(int row, T[] array) throws OutOfRangeException, MatrixDimensionMismatchException {
        this.checkRowIndex(row);
        int nCols = this.getColumnDimension();
        if (array.length != nCols) {
            throw new MatrixDimensionMismatchException(1, array.length, 1, nCols);
        }
        int iBlock = row / 36;
        int iRow = row - iBlock * 36;
        int outIndex = 0;
        for (int jBlock = 0; jBlock < this.blockColumns; ++jBlock) {
            int jWidth = this.blockWidth(jBlock);
            T[] block = this.blocks[iBlock * this.blockColumns + jBlock];
            System.arraycopy(array, outIndex, block, iRow * jWidth, jWidth);
            outIndex += jWidth;
        }
    }

    @Override
    public T[] getColumn(int column) throws OutOfRangeException {
        this.checkColumnIndex(column);
        FieldElement[] out = (FieldElement[])MathArrays.buildArray(this.getField(), this.rows);
        int jBlock = column / 36;
        int jColumn = column - jBlock * 36;
        int jWidth = this.blockWidth(jBlock);
        int outIndex = 0;
        for (int iBlock = 0; iBlock < this.blockRows; ++iBlock) {
            int iHeight = this.blockHeight(iBlock);
            T[] block = this.blocks[iBlock * this.blockColumns + jBlock];
            for (int i = 0; i < iHeight; ++i) {
                out[outIndex++] = block[i * jWidth + jColumn];
            }
        }
        return out;
    }

    @Override
    public void setColumn(int column, T[] array) throws MatrixDimensionMismatchException, OutOfRangeException {
        this.checkColumnIndex(column);
        int nRows = this.getRowDimension();
        if (array.length != nRows) {
            throw new MatrixDimensionMismatchException(array.length, 1, nRows, 1);
        }
        int jBlock = column / 36;
        int jColumn = column - jBlock * 36;
        int jWidth = this.blockWidth(jBlock);
        int outIndex = 0;
        for (int iBlock = 0; iBlock < this.blockRows; ++iBlock) {
            int iHeight = this.blockHeight(iBlock);
            T[] block = this.blocks[iBlock * this.blockColumns + jBlock];
            for (int i = 0; i < iHeight; ++i) {
                block[i * jWidth + jColumn] = array[outIndex++];
            }
        }
    }

    @Override
    public T getEntry(int row, int column) throws OutOfRangeException {
        this.checkRowIndex(row);
        this.checkColumnIndex(column);
        int iBlock = row / 36;
        int jBlock = column / 36;
        int k = (row - iBlock * 36) * this.blockWidth(jBlock) + (column - jBlock * 36);
        return this.blocks[iBlock * this.blockColumns + jBlock][k];
    }

    @Override
    public void setEntry(int row, int column, T value) throws OutOfRangeException {
        this.checkRowIndex(row);
        this.checkColumnIndex(column);
        int iBlock = row / 36;
        int jBlock = column / 36;
        int k = (row - iBlock * 36) * this.blockWidth(jBlock) + (column - jBlock * 36);
        this.blocks[iBlock * this.blockColumns + jBlock][k] = value;
    }

    @Override
    public void addToEntry(int row, int column, T increment) throws OutOfRangeException {
        this.checkRowIndex(row);
        this.checkColumnIndex(column);
        int iBlock = row / 36;
        int jBlock = column / 36;
        int k = (row - iBlock * 36) * this.blockWidth(jBlock) + (column - jBlock * 36);
        T[] blockIJ = this.blocks[iBlock * this.blockColumns + jBlock];
        blockIJ[k] = (FieldElement)blockIJ[k].add(increment);
    }

    @Override
    public void multiplyEntry(int row, int column, T factor) throws OutOfRangeException {
        this.checkRowIndex(row);
        this.checkColumnIndex(column);
        int iBlock = row / 36;
        int jBlock = column / 36;
        int k = (row - iBlock * 36) * this.blockWidth(jBlock) + (column - jBlock * 36);
        T[] blockIJ = this.blocks[iBlock * this.blockColumns + jBlock];
        blockIJ[k] = (FieldElement)blockIJ[k].multiply(factor);
    }

    @Override
    public FieldMatrix<T> transpose() {
        int nRows = this.getRowDimension();
        int nCols = this.getColumnDimension();
        BlockFieldMatrix out = new BlockFieldMatrix(this.getField(), nCols, nRows);
        int blockIndex = 0;
        for (int iBlock = 0; iBlock < this.blockColumns; ++iBlock) {
            for (int jBlock = 0; jBlock < this.blockRows; ++jBlock) {
                T[] outBlock = out.blocks[blockIndex];
                T[] tBlock = this.blocks[jBlock * this.blockColumns + iBlock];
                int pStart = iBlock * 36;
                int pEnd = FastMath.min(pStart + 36, this.columns);
                int qStart = jBlock * 36;
                int qEnd = FastMath.min(qStart + 36, this.rows);
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

    @Override
    public int getRowDimension() {
        return this.rows;
    }

    @Override
    public int getColumnDimension() {
        return this.columns;
    }

    @Override
    public T[] operate(T[] v) throws DimensionMismatchException {
        if (v.length != this.columns) {
            throw new DimensionMismatchException(v.length, this.columns);
        }
        FieldElement[] out = (FieldElement[])MathArrays.buildArray(this.getField(), this.rows);
        FieldElement zero = (FieldElement)this.getField().getZero();
        for (int iBlock = 0; iBlock < this.blockRows; ++iBlock) {
            int pStart = iBlock * 36;
            int pEnd = FastMath.min(pStart + 36, this.rows);
            for (int jBlock = 0; jBlock < this.blockColumns; ++jBlock) {
                T[] block = this.blocks[iBlock * this.blockColumns + jBlock];
                int qStart = jBlock * 36;
                int qEnd = FastMath.min(qStart + 36, this.columns);
                int k = 0;
                for (int p = pStart; p < pEnd; ++p) {
                    int q;
                    FieldElement sum = zero;
                    for (q = qStart; q < qEnd - 3; q += 4) {
                        sum = (FieldElement)((FieldElement)((FieldElement)((FieldElement)sum.add(block[k].multiply(v[q]))).add(block[k + 1].multiply(v[q + 1]))).add(block[k + 2].multiply(v[q + 2]))).add(block[k + 3].multiply(v[q + 3]));
                        k += 4;
                    }
                    while (q < qEnd) {
                        sum = (FieldElement)sum.add(block[k++].multiply(v[q++]));
                    }
                    out[p] = out[p].add(sum);
                }
            }
        }
        return out;
    }

    @Override
    public T[] preMultiply(T[] v) throws DimensionMismatchException {
        if (v.length != this.rows) {
            throw new DimensionMismatchException(v.length, this.rows);
        }
        FieldElement[] out = (FieldElement[])MathArrays.buildArray(this.getField(), this.columns);
        FieldElement zero = (FieldElement)this.getField().getZero();
        for (int jBlock = 0; jBlock < this.blockColumns; ++jBlock) {
            int jWidth = this.blockWidth(jBlock);
            int jWidth2 = jWidth + jWidth;
            int jWidth3 = jWidth2 + jWidth;
            int jWidth4 = jWidth3 + jWidth;
            int qStart = jBlock * 36;
            int qEnd = FastMath.min(qStart + 36, this.columns);
            for (int iBlock = 0; iBlock < this.blockRows; ++iBlock) {
                T[] block = this.blocks[iBlock * this.blockColumns + jBlock];
                int pStart = iBlock * 36;
                int pEnd = FastMath.min(pStart + 36, this.rows);
                for (int q = qStart; q < qEnd; ++q) {
                    int p;
                    int k = q - qStart;
                    FieldElement sum = zero;
                    for (p = pStart; p < pEnd - 3; p += 4) {
                        sum = (FieldElement)((FieldElement)((FieldElement)((FieldElement)sum.add(block[k].multiply(v[p]))).add(block[k + jWidth].multiply(v[p + 1]))).add(block[k + jWidth2].multiply(v[p + 2]))).add(block[k + jWidth3].multiply(v[p + 3]));
                        k += jWidth4;
                    }
                    while (p < pEnd) {
                        sum = (FieldElement)sum.add(block[k].multiply(v[p++]));
                        k += jWidth;
                    }
                    out[q] = out[q].add(sum);
                }
            }
        }
        return out;
    }

    @Override
    public T walkInRowOrder(FieldMatrixChangingVisitor<T> visitor) {
        visitor.start(this.rows, this.columns, 0, this.rows - 1, 0, this.columns - 1);
        for (int iBlock = 0; iBlock < this.blockRows; ++iBlock) {
            int pStart = iBlock * 36;
            int pEnd = FastMath.min(pStart + 36, this.rows);
            for (int p = pStart; p < pEnd; ++p) {
                for (int jBlock = 0; jBlock < this.blockColumns; ++jBlock) {
                    int jWidth = this.blockWidth(jBlock);
                    int qStart = jBlock * 36;
                    int qEnd = FastMath.min(qStart + 36, this.columns);
                    T[] block = this.blocks[iBlock * this.blockColumns + jBlock];
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

    @Override
    public T walkInRowOrder(FieldMatrixPreservingVisitor<T> visitor) {
        visitor.start(this.rows, this.columns, 0, this.rows - 1, 0, this.columns - 1);
        for (int iBlock = 0; iBlock < this.blockRows; ++iBlock) {
            int pStart = iBlock * 36;
            int pEnd = FastMath.min(pStart + 36, this.rows);
            for (int p = pStart; p < pEnd; ++p) {
                for (int jBlock = 0; jBlock < this.blockColumns; ++jBlock) {
                    int jWidth = this.blockWidth(jBlock);
                    int qStart = jBlock * 36;
                    int qEnd = FastMath.min(qStart + 36, this.columns);
                    T[] block = this.blocks[iBlock * this.blockColumns + jBlock];
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

    @Override
    public T walkInRowOrder(FieldMatrixChangingVisitor<T> visitor, int startRow, int endRow, int startColumn, int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        this.checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
        visitor.start(this.rows, this.columns, startRow, endRow, startColumn, endColumn);
        for (int iBlock = startRow / 36; iBlock < 1 + endRow / 36; ++iBlock) {
            int p0 = iBlock * 36;
            int pStart = FastMath.max(startRow, p0);
            int pEnd = FastMath.min((iBlock + 1) * 36, 1 + endRow);
            for (int p = pStart; p < pEnd; ++p) {
                for (int jBlock = startColumn / 36; jBlock < 1 + endColumn / 36; ++jBlock) {
                    int jWidth = this.blockWidth(jBlock);
                    int q0 = jBlock * 36;
                    int qStart = FastMath.max(startColumn, q0);
                    int qEnd = FastMath.min((jBlock + 1) * 36, 1 + endColumn);
                    T[] block = this.blocks[iBlock * this.blockColumns + jBlock];
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

    @Override
    public T walkInRowOrder(FieldMatrixPreservingVisitor<T> visitor, int startRow, int endRow, int startColumn, int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        this.checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
        visitor.start(this.rows, this.columns, startRow, endRow, startColumn, endColumn);
        for (int iBlock = startRow / 36; iBlock < 1 + endRow / 36; ++iBlock) {
            int p0 = iBlock * 36;
            int pStart = FastMath.max(startRow, p0);
            int pEnd = FastMath.min((iBlock + 1) * 36, 1 + endRow);
            for (int p = pStart; p < pEnd; ++p) {
                for (int jBlock = startColumn / 36; jBlock < 1 + endColumn / 36; ++jBlock) {
                    int jWidth = this.blockWidth(jBlock);
                    int q0 = jBlock * 36;
                    int qStart = FastMath.max(startColumn, q0);
                    int qEnd = FastMath.min((jBlock + 1) * 36, 1 + endColumn);
                    T[] block = this.blocks[iBlock * this.blockColumns + jBlock];
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

    @Override
    public T walkInOptimizedOrder(FieldMatrixChangingVisitor<T> visitor) {
        visitor.start(this.rows, this.columns, 0, this.rows - 1, 0, this.columns - 1);
        int blockIndex = 0;
        for (int iBlock = 0; iBlock < this.blockRows; ++iBlock) {
            int pStart = iBlock * 36;
            int pEnd = FastMath.min(pStart + 36, this.rows);
            for (int jBlock = 0; jBlock < this.blockColumns; ++jBlock) {
                int qStart = jBlock * 36;
                int qEnd = FastMath.min(qStart + 36, this.columns);
                T[] block = this.blocks[blockIndex];
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

    @Override
    public T walkInOptimizedOrder(FieldMatrixPreservingVisitor<T> visitor) {
        visitor.start(this.rows, this.columns, 0, this.rows - 1, 0, this.columns - 1);
        int blockIndex = 0;
        for (int iBlock = 0; iBlock < this.blockRows; ++iBlock) {
            int pStart = iBlock * 36;
            int pEnd = FastMath.min(pStart + 36, this.rows);
            for (int jBlock = 0; jBlock < this.blockColumns; ++jBlock) {
                int qStart = jBlock * 36;
                int qEnd = FastMath.min(qStart + 36, this.columns);
                T[] block = this.blocks[blockIndex];
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

    @Override
    public T walkInOptimizedOrder(FieldMatrixChangingVisitor<T> visitor, int startRow, int endRow, int startColumn, int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        this.checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
        visitor.start(this.rows, this.columns, startRow, endRow, startColumn, endColumn);
        for (int iBlock = startRow / 36; iBlock < 1 + endRow / 36; ++iBlock) {
            int p0 = iBlock * 36;
            int pStart = FastMath.max(startRow, p0);
            int pEnd = FastMath.min((iBlock + 1) * 36, 1 + endRow);
            for (int jBlock = startColumn / 36; jBlock < 1 + endColumn / 36; ++jBlock) {
                int jWidth = this.blockWidth(jBlock);
                int q0 = jBlock * 36;
                int qStart = FastMath.max(startColumn, q0);
                int qEnd = FastMath.min((jBlock + 1) * 36, 1 + endColumn);
                T[] block = this.blocks[iBlock * this.blockColumns + jBlock];
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

    @Override
    public T walkInOptimizedOrder(FieldMatrixPreservingVisitor<T> visitor, int startRow, int endRow, int startColumn, int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        this.checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
        visitor.start(this.rows, this.columns, startRow, endRow, startColumn, endColumn);
        for (int iBlock = startRow / 36; iBlock < 1 + endRow / 36; ++iBlock) {
            int p0 = iBlock * 36;
            int pStart = FastMath.max(startRow, p0);
            int pEnd = FastMath.min((iBlock + 1) * 36, 1 + endRow);
            for (int jBlock = startColumn / 36; jBlock < 1 + endColumn / 36; ++jBlock) {
                int jWidth = this.blockWidth(jBlock);
                int q0 = jBlock * 36;
                int qStart = FastMath.max(startColumn, q0);
                int qEnd = FastMath.min((jBlock + 1) * 36, 1 + endColumn);
                T[] block = this.blocks[iBlock * this.blockColumns + jBlock];
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
        return blockRow == this.blockRows - 1 ? this.rows - blockRow * 36 : 36;
    }

    private int blockWidth(int blockColumn) {
        return blockColumn == this.blockColumns - 1 ? this.columns - blockColumn * 36 : 36;
    }
}

