/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.random;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.MathParseException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.random.RandomVectorGenerator;
import org.apache.commons.math3.util.FastMath;

public class SobolSequenceGenerator
implements RandomVectorGenerator {
    private static final int BITS = 52;
    private static final double SCALE = FastMath.pow(2.0, 52);
    private static final int MAX_DIMENSION = 1000;
    private static final String RESOURCE_NAME = "/assets/org/apache/commons/math3/random/new-joe-kuo-6.1000";
    private static final String FILE_CHARSET = "US-ASCII";
    private final int dimension;
    private int count = 0;
    private final long[][] direction;
    private final long[] x;

    public SobolSequenceGenerator(int dimension) throws OutOfRangeException {
        if (dimension < 1 || dimension > 1000) {
            throw new OutOfRangeException(dimension, (Number)1, 1000);
        }
        InputStream is = this.getClass().getResourceAsStream(RESOURCE_NAME);
        if (is == null) {
            throw new MathInternalError();
        }
        this.dimension = dimension;
        this.direction = new long[dimension][53];
        this.x = new long[dimension];
        try {
            this.initFromStream(is);
        } catch (IOException e) {
            throw new MathInternalError();
        } catch (MathParseException e) {
            throw new MathInternalError();
        } finally {
            try {
                is.close();
            } catch (IOException iOException) {}
        }
    }

    public SobolSequenceGenerator(int dimension, InputStream is) throws NotStrictlyPositiveException, MathParseException, IOException {
        if (dimension < 1) {
            throw new NotStrictlyPositiveException(dimension);
        }
        this.dimension = dimension;
        this.direction = new long[dimension][53];
        this.x = new long[dimension];
        int lastDimension = this.initFromStream(is);
        if (lastDimension < dimension) {
            throw new OutOfRangeException(dimension, (Number)1, lastDimension);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private int initFromStream(InputStream is) throws MathParseException, IOException {
        for (int i = 1; i <= 52; ++i) {
            this.direction[0][i] = 1L << 52 - i;
        }
        Charset charset = Charset.forName(FILE_CHARSET);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, charset));
        int dim = -1;
        try {
            reader.readLine();
            int lineNumber = 2;
            int index = 1;
            String line = null;
            while ((line = reader.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, " ");
                try {
                    int s;
                    dim = Integer.parseInt(st.nextToken());
                    if (dim >= 2 && dim <= this.dimension) {
                        s = Integer.parseInt(st.nextToken());
                        int a = Integer.parseInt(st.nextToken());
                        int[] m = new int[s + 1];
                        for (int i = 1; i <= s; ++i) {
                            m[i] = Integer.parseInt(st.nextToken());
                        }
                        this.initDirectionVector(index++, a, m);
                    }
                    if (dim > this.dimension) {
                        s = dim;
                        return s;
                    }
                } catch (NoSuchElementException e) {
                    throw new MathParseException(line, lineNumber);
                } catch (NumberFormatException e) {
                    throw new MathParseException(line, lineNumber);
                }
                ++lineNumber;
            }
        } finally {
            reader.close();
        }
        return dim;
    }

    private void initDirectionVector(int d, int a, int[] m) {
        int i;
        int s = m.length - 1;
        for (i = 1; i <= s; ++i) {
            this.direction[d][i] = (long)m[i] << 52 - i;
        }
        for (i = s + 1; i <= 52; ++i) {
            this.direction[d][i] = this.direction[d][i - s] ^ this.direction[d][i - s] >> s;
            for (int k = 1; k <= s - 1; ++k) {
                long[] lArray = this.direction[d];
                int n = i;
                lArray[n] = lArray[n] ^ (long)(a >> s - 1 - k & 1) * this.direction[d][i - k];
            }
        }
    }

    public double[] nextVector() {
        double[] v = new double[this.dimension];
        if (this.count == 0) {
            ++this.count;
            return v;
        }
        int c = 1;
        int value = this.count - 1;
        while ((value & 1) == 1) {
            value >>= 1;
            ++c;
        }
        for (int i = 0; i < this.dimension; ++i) {
            int n = i;
            this.x[n] = this.x[n] ^ this.direction[i][c];
            v[i] = (double)this.x[i] / SCALE;
        }
        ++this.count;
        return v;
    }

    public double[] skipTo(int index) throws NotPositiveException {
        if (index == 0) {
            Arrays.fill(this.x, 0L);
        } else {
            int i = index - 1;
            long grayCode = i ^ i >> 1;
            for (int j = 0; j < this.dimension; ++j) {
                long shift;
                long result = 0L;
                for (int k = 1; k <= 52 && (shift = grayCode >> k - 1) != 0L; ++k) {
                    long ik = shift & 1L;
                    result ^= ik * this.direction[j][k];
                }
                this.x[j] = result;
            }
        }
        this.count = index;
        return this.nextVector();
    }

    public int getNextIndex() {
        return this.count;
    }
}

