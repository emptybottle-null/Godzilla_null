/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.regression;

import java.util.Arrays;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.regression.ModelSpecificationException;
import org.apache.commons.math3.stat.regression.RegressionResults;
import org.apache.commons.math3.stat.regression.UpdatingMultipleLinearRegression;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.Precision;

public class MillerUpdatingRegression
implements UpdatingMultipleLinearRegression {
    private final int nvars;
    private final double[] d;
    private final double[] rhs;
    private final double[] r;
    private final double[] tol;
    private final double[] rss;
    private final int[] vorder;
    private final double[] work_tolset;
    private long nobs = 0L;
    private double sserr = 0.0;
    private boolean rss_set = false;
    private boolean tol_set = false;
    private final boolean[] lindep;
    private final double[] x_sing;
    private final double[] work_sing;
    private double sumy = 0.0;
    private double sumsqy = 0.0;
    private boolean hasIntercept;
    private final double epsilon;

    private MillerUpdatingRegression() {
        this(-1, false, Double.NaN);
    }

    public MillerUpdatingRegression(int numberOfVariables, boolean includeConstant, double errorTolerance) throws ModelSpecificationException {
        if (numberOfVariables < 1) {
            throw new ModelSpecificationException(LocalizedFormats.NO_REGRESSORS, new Object[0]);
        }
        this.nvars = includeConstant ? numberOfVariables + 1 : numberOfVariables;
        this.hasIntercept = includeConstant;
        this.nobs = 0L;
        this.d = new double[this.nvars];
        this.rhs = new double[this.nvars];
        this.r = new double[this.nvars * (this.nvars - 1) / 2];
        this.tol = new double[this.nvars];
        this.rss = new double[this.nvars];
        this.vorder = new int[this.nvars];
        this.x_sing = new double[this.nvars];
        this.work_sing = new double[this.nvars];
        this.work_tolset = new double[this.nvars];
        this.lindep = new boolean[this.nvars];
        for (int i = 0; i < this.nvars; ++i) {
            this.vorder[i] = i;
        }
        this.epsilon = errorTolerance > 0.0 ? errorTolerance : -errorTolerance;
    }

    public MillerUpdatingRegression(int numberOfVariables, boolean includeConstant) throws ModelSpecificationException {
        this(numberOfVariables, includeConstant, Precision.EPSILON);
    }

    public boolean hasIntercept() {
        return this.hasIntercept;
    }

    public long getN() {
        return this.nobs;
    }

    public void addObservation(double[] x, double y) throws ModelSpecificationException {
        if (!this.hasIntercept && x.length != this.nvars || this.hasIntercept && x.length + 1 != this.nvars) {
            throw new ModelSpecificationException(LocalizedFormats.INVALID_REGRESSION_OBSERVATION, x.length, this.nvars);
        }
        if (!this.hasIntercept) {
            this.include(MathArrays.copyOf(x, x.length), 1.0, y);
        } else {
            double[] tmp = new double[x.length + 1];
            System.arraycopy(x, 0, tmp, 1, x.length);
            tmp[0] = 1.0;
            this.include(tmp, 1.0, y);
        }
        ++this.nobs;
    }

    public void addObservations(double[][] x, double[] y) throws ModelSpecificationException {
        if (x == null || y == null || x.length != y.length) {
            throw new ModelSpecificationException(LocalizedFormats.DIMENSIONS_MISMATCH_SIMPLE, x == null ? 0 : x.length, y == null ? 0 : y.length);
        }
        if (x.length == 0) {
            throw new ModelSpecificationException(LocalizedFormats.NO_DATA, new Object[0]);
        }
        if (x[0].length + 1 > x.length) {
            throw new ModelSpecificationException(LocalizedFormats.NOT_ENOUGH_DATA_FOR_NUMBER_OF_PREDICTORS, x.length, x[0].length);
        }
        for (int i = 0; i < x.length; ++i) {
            this.addObservation(x[i], y[i]);
        }
    }

    private void include(double[] x, double wi, double yi) {
        int nextr = 0;
        double w = wi;
        double y = yi;
        this.rss_set = false;
        this.sumy = this.smartAdd(yi, this.sumy);
        this.sumsqy = this.smartAdd(this.sumsqy, yi * yi);
        for (int i = 0; i < x.length; ++i) {
            double xk;
            double dpi;
            if (w == 0.0) {
                return;
            }
            double xi = x[i];
            if (xi == 0.0) {
                nextr += this.nvars - i - 1;
                continue;
            }
            double di = this.d[i];
            double wxi = w * xi;
            double _w = w;
            if (di != 0.0) {
                dpi = this.smartAdd(di, wxi * xi);
                double tmp = wxi * xi / di;
                if (FastMath.abs(tmp) > Precision.EPSILON) {
                    w = di * w / dpi;
                }
            } else {
                dpi = wxi * xi;
                w = 0.0;
            }
            this.d[i] = dpi;
            for (int k = i + 1; k < this.nvars; ++k) {
                xk = x[k];
                x[k] = this.smartAdd(xk, -xi * this.r[nextr]);
                this.r[nextr] = di != 0.0 ? this.smartAdd(di * this.r[nextr], _w * xi * xk) / dpi : xk / xi;
                ++nextr;
            }
            xk = y;
            y = this.smartAdd(xk, -xi * this.rhs[i]);
            this.rhs[i] = di != 0.0 ? this.smartAdd(di * this.rhs[i], wxi * xk) / dpi : xk / xi;
        }
        this.sserr = this.smartAdd(this.sserr, w * y * y);
    }

    private double smartAdd(double a, double b) {
        double _b;
        double _a = FastMath.abs(a);
        if (_a > (_b = FastMath.abs(b))) {
            double eps = _a * Precision.EPSILON;
            if (_b > eps) {
                return a + b;
            }
            return a;
        }
        double eps = _b * Precision.EPSILON;
        if (_a > eps) {
            return a + b;
        }
        return b;
    }

    public void clear() {
        Arrays.fill(this.d, 0.0);
        Arrays.fill(this.rhs, 0.0);
        Arrays.fill(this.r, 0.0);
        Arrays.fill(this.tol, 0.0);
        Arrays.fill(this.rss, 0.0);
        Arrays.fill(this.work_tolset, 0.0);
        Arrays.fill(this.work_sing, 0.0);
        Arrays.fill(this.x_sing, 0.0);
        Arrays.fill(this.lindep, false);
        for (int i = 0; i < this.nvars; ++i) {
            this.vorder[i] = i;
        }
        this.nobs = 0L;
        this.sserr = 0.0;
        this.sumy = 0.0;
        this.sumsqy = 0.0;
        this.rss_set = false;
        this.tol_set = false;
    }

    private void tolset() {
        double eps = this.epsilon;
        for (int i = 0; i < this.nvars; ++i) {
            this.work_tolset[i] = FastMath.sqrt(this.d[i]);
        }
        this.tol[0] = eps * this.work_tolset[0];
        for (int col = 1; col < this.nvars; ++col) {
            int pos = col - 1;
            double total = this.work_tolset[col];
            for (int row = 0; row < col; ++row) {
                total += FastMath.abs(this.r[pos]) * this.work_tolset[row];
                pos += this.nvars - row - 2;
            }
            this.tol[col] = eps * total;
        }
        this.tol_set = true;
    }

    private double[] regcf(int nreq) throws ModelSpecificationException {
        int i;
        if (nreq < 1) {
            throw new ModelSpecificationException(LocalizedFormats.NO_REGRESSORS, new Object[0]);
        }
        if (nreq > this.nvars) {
            throw new ModelSpecificationException(LocalizedFormats.TOO_MANY_REGRESSORS, nreq, this.nvars);
        }
        if (!this.tol_set) {
            this.tolset();
        }
        double[] ret = new double[nreq];
        boolean rankProblem = false;
        for (i = nreq - 1; i > -1; --i) {
            if (FastMath.sqrt(this.d[i]) < this.tol[i]) {
                ret[i] = 0.0;
                this.d[i] = 0.0;
                rankProblem = true;
                continue;
            }
            ret[i] = this.rhs[i];
            int nextr = i * (this.nvars + this.nvars - i - 1) / 2;
            for (int j = i + 1; j < nreq; ++j) {
                ret[i] = this.smartAdd(ret[i], -this.r[nextr] * ret[j]);
                ++nextr;
            }
        }
        if (rankProblem) {
            for (i = 0; i < nreq; ++i) {
                if (!this.lindep[i]) continue;
                ret[i] = Double.NaN;
            }
        }
        return ret;
    }

    private void singcheck() {
        for (int i = 0; i < this.nvars; ++i) {
            this.work_sing[i] = FastMath.sqrt(this.d[i]);
        }
        for (int col = 0; col < this.nvars; ++col) {
            double temp = this.tol[col];
            int pos = col - 1;
            for (int row = 0; row < col - 1; ++row) {
                if (FastMath.abs(this.r[pos]) * this.work_sing[row] < temp) {
                    this.r[pos] = 0.0;
                }
                pos += this.nvars - row - 2;
            }
            this.lindep[col] = false;
            if (!(this.work_sing[col] < temp)) continue;
            this.lindep[col] = true;
            if (col < this.nvars - 1) {
                Arrays.fill(this.x_sing, 0.0);
                int _pi = col * (this.nvars + this.nvars - col - 1) / 2;
                int _xi = col + 1;
                while (_xi < this.nvars) {
                    this.x_sing[_xi] = this.r[_pi];
                    this.r[_pi] = 0.0;
                    ++_xi;
                    ++_pi;
                }
                double y = this.rhs[col];
                double weight = this.d[col];
                this.d[col] = 0.0;
                this.rhs[col] = 0.0;
                this.include(this.x_sing, weight, y);
                continue;
            }
            this.sserr += this.d[col] * this.rhs[col] * this.rhs[col];
        }
    }

    private void ss() {
        double total = this.sserr;
        this.rss[this.nvars - 1] = this.sserr;
        for (int i = this.nvars - 1; i > 0; --i) {
            this.rss[i - 1] = total += this.d[i] * this.rhs[i] * this.rhs[i];
        }
        this.rss_set = true;
    }

    private double[] cov(int nreq) {
        if (this.nobs <= (long)nreq) {
            return null;
        }
        double rnk = 0.0;
        for (int i = 0; i < nreq; ++i) {
            if (this.lindep[i]) continue;
            rnk += 1.0;
        }
        double var = this.rss[nreq - 1] / ((double)this.nobs - rnk);
        double[] rinv = new double[nreq * (nreq - 1) / 2];
        this.inverse(rinv, nreq);
        double[] covmat = new double[nreq * (nreq + 1) / 2];
        Arrays.fill(covmat, Double.NaN);
        int start = 0;
        double total = 0.0;
        for (int row = 0; row < nreq; ++row) {
            int pos2 = start;
            if (!this.lindep[row]) {
                for (int col = row; col < nreq; ++col) {
                    if (!this.lindep[col]) {
                        int pos1 = start + col - row;
                        total = row == col ? 1.0 / this.d[col] : rinv[pos1 - 1] / this.d[col];
                        for (int k = col + 1; k < nreq; ++k) {
                            if (!this.lindep[k]) {
                                total += rinv[pos1] * rinv[pos2] / this.d[k];
                            }
                            ++pos1;
                            ++pos2;
                        }
                        covmat[(col + 1) * col / 2 + row] = total * var;
                        continue;
                    }
                    pos2 += nreq - col - 1;
                }
            }
            start += nreq - row - 1;
        }
        return covmat;
    }

    private void inverse(double[] rinv, int nreq) {
        int pos = nreq * (nreq - 1) / 2 - 1;
        int pos1 = -1;
        int pos2 = -1;
        double total = 0.0;
        Arrays.fill(rinv, Double.NaN);
        for (int row = nreq - 1; row > 0; --row) {
            if (!this.lindep[row]) {
                int start = (row - 1) * (this.nvars + this.nvars - row) / 2;
                for (int col = nreq; col > row; --col) {
                    pos1 = start;
                    pos2 = pos;
                    total = 0.0;
                    for (int k = row; k < col - 1; ++k) {
                        pos2 += nreq - k - 1;
                        if (!this.lindep[k]) {
                            total += -this.r[pos1] * rinv[pos2];
                        }
                        ++pos1;
                    }
                    rinv[pos] = total - this.r[pos1];
                    --pos;
                }
                continue;
            }
            pos -= nreq - row;
        }
    }

    public double[] getPartialCorrelations(int in) {
        int row;
        int pos;
        double[] output = new double[(this.nvars - in + 1) * (this.nvars - in) / 2];
        int rms_off = -in;
        int wrk_off = -(in + 1);
        double[] rms = new double[this.nvars - in];
        double[] work = new double[this.nvars - in - 1];
        int offXX = (this.nvars - in) * (this.nvars - in - 1) / 2;
        if (in < -1 || in >= this.nvars) {
            return null;
        }
        int nvm = this.nvars - 1;
        int base_pos = this.r.length - (nvm - in) * (nvm - in + 1) / 2;
        if (this.d[in] > 0.0) {
            rms[in + rms_off] = 1.0 / FastMath.sqrt(this.d[in]);
        }
        for (int col = in + 1; col < this.nvars; ++col) {
            pos = base_pos + col - 1 - in;
            double sumxx = this.d[col];
            for (row = in; row < col; ++row) {
                sumxx += this.d[row] * this.r[pos] * this.r[pos];
                pos += this.nvars - row - 2;
            }
            rms[col + rms_off] = sumxx > 0.0 ? 1.0 / FastMath.sqrt(sumxx) : 0.0;
        }
        double sumyy = this.sserr;
        for (int row2 = in; row2 < this.nvars; ++row2) {
            sumyy += this.d[row2] * this.rhs[row2] * this.rhs[row2];
        }
        if (sumyy > 0.0) {
            sumyy = 1.0 / FastMath.sqrt(sumyy);
        }
        pos = 0;
        for (int col1 = in; col1 < this.nvars; ++col1) {
            int pos2;
            double sumxy = 0.0;
            Arrays.fill(work, 0.0);
            int pos1 = base_pos + col1 - in - 1;
            for (row = in; row < col1; ++row) {
                pos2 = pos1 + 1;
                for (int col2 = col1 + 1; col2 < this.nvars; ++col2) {
                    int n = col2 + wrk_off;
                    work[n] = work[n] + this.d[row] * this.r[pos1] * this.r[pos2];
                    ++pos2;
                }
                sumxy += this.d[row] * this.r[pos1] * this.rhs[row];
                pos1 += this.nvars - row - 2;
            }
            pos2 = pos1 + 1;
            for (int col2 = col1 + 1; col2 < this.nvars; ++col2) {
                int n = col2 + wrk_off;
                work[n] = work[n] + this.d[col1] * this.r[pos2];
                ++pos2;
                output[(col2 - 1 - in) * (col2 - in) / 2 + col1 - in] = work[col2 + wrk_off] * rms[col1 + rms_off] * rms[col2 + rms_off];
                ++pos;
            }
            output[col1 + rms_off + offXX] = (sumxy += this.d[col1] * this.rhs[col1]) * rms[col1 + rms_off] * sumyy;
        }
        return output;
    }

    private void vmove(int from, int to) {
        int inc;
        int first;
        boolean bSkipTo40 = false;
        if (from == to) {
            return;
        }
        if (!this.rss_set) {
            this.ss();
        }
        int count = 0;
        if (from < to) {
            first = from;
            inc = 1;
            count = to - from;
        } else {
            first = from - 1;
            inc = -1;
            count = from - to;
        }
        int m = first;
        for (int idx = 0; idx < count; ++idx) {
            double X;
            int m1 = m * (this.nvars + this.nvars - m - 1) / 2;
            int m2 = m1 + this.nvars - m - 1;
            int mp1 = m + 1;
            double d1 = this.d[m];
            double d2 = this.d[mp1];
            if (d1 > this.epsilon || d2 > this.epsilon) {
                int col;
                X = this.r[m1];
                if (FastMath.abs(X) * FastMath.sqrt(d1) < this.tol[mp1]) {
                    X = 0.0;
                }
                if (d1 < this.epsilon || FastMath.abs(X) < this.epsilon) {
                    this.d[m] = d2;
                    this.d[mp1] = d1;
                    this.r[m1] = 0.0;
                    for (col = m + 2; col < this.nvars; ++col) {
                        X = this.r[++m1];
                        this.r[m1] = this.r[m2];
                        this.r[m2] = X;
                        ++m2;
                    }
                    X = this.rhs[m];
                    this.rhs[m] = this.rhs[mp1];
                    this.rhs[mp1] = X;
                    bSkipTo40 = true;
                } else if (d2 < this.epsilon) {
                    this.d[m] = d1 * X * X;
                    this.r[m1] = 1.0 / X;
                    int _i = m1 + 1;
                    while (_i < m1 + this.nvars - m - 1) {
                        int n = _i++;
                        this.r[n] = this.r[n] / X;
                    }
                    int n = m;
                    this.rhs[n] = this.rhs[n] / X;
                    bSkipTo40 = true;
                }
                if (!bSkipTo40) {
                    double Y;
                    double d1new = d2 + d1 * X * X;
                    double cbar = d2 / d1new;
                    double sbar = X * d1 / d1new;
                    double d2new = d1 * cbar;
                    this.d[m] = d1new;
                    this.d[mp1] = d2new;
                    this.r[m1] = sbar;
                    for (col = m + 2; col < this.nvars; ++col) {
                        Y = this.r[++m1];
                        this.r[m1] = cbar * this.r[m2] + sbar * Y;
                        this.r[m2] = Y - X * this.r[m2];
                        ++m2;
                    }
                    Y = this.rhs[m];
                    this.rhs[m] = cbar * this.rhs[mp1] + sbar * Y;
                    this.rhs[mp1] = Y - X * this.rhs[mp1];
                }
            }
            if (m > 0) {
                int pos = m;
                for (int row = 0; row < m; ++row) {
                    X = this.r[pos];
                    this.r[pos] = this.r[pos - 1];
                    this.r[pos - 1] = X;
                    pos += this.nvars - row - 2;
                }
            }
            m1 = this.vorder[m];
            this.vorder[m] = this.vorder[mp1];
            this.vorder[mp1] = m1;
            X = this.tol[m];
            this.tol[m] = this.tol[mp1];
            this.tol[mp1] = X;
            this.rss[m] = this.rss[mp1] + this.d[mp1] * this.rhs[mp1] * this.rhs[mp1];
            m += inc;
        }
    }

    private int reorderRegressors(int[] list, int pos1) {
        if (list.length < 1 || list.length > this.nvars + 1 - pos1) {
            return -1;
        }
        int next = pos1;
        block0: for (int i = pos1; i < this.nvars; ++i) {
            int l = this.vorder[i];
            for (int j = 0; j < list.length; ++j) {
                if (l != list[j] || i <= next) continue;
                this.vmove(i, next);
                if (++next < list.length + pos1) continue block0;
                return 0;
            }
        }
        return 0;
    }

    public double getDiagonalOfHatMatrix(double[] row_data) {
        double[] xrow;
        double[] wk = new double[this.nvars];
        if (row_data.length > this.nvars) {
            return Double.NaN;
        }
        if (this.hasIntercept) {
            xrow = new double[row_data.length + 1];
            xrow[0] = 1.0;
            System.arraycopy(row_data, 0, xrow, 1, row_data.length);
        } else {
            xrow = row_data;
        }
        double hii = 0.0;
        for (int col = 0; col < xrow.length; ++col) {
            if (FastMath.sqrt(this.d[col]) < this.tol[col]) {
                wk[col] = 0.0;
                continue;
            }
            int pos = col - 1;
            double total = xrow[col];
            for (int row = 0; row < col; ++row) {
                total = this.smartAdd(total, -wk[row] * this.r[pos]);
                pos += this.nvars - row - 2;
            }
            wk[col] = total;
            hii = this.smartAdd(hii, total * total / this.d[col]);
        }
        return hii;
    }

    public int[] getOrderOfRegressors() {
        return MathArrays.copyOf(this.vorder);
    }

    public RegressionResults regress() throws ModelSpecificationException {
        return this.regress(this.nvars);
    }

    public RegressionResults regress(int numberOfRegressors) throws ModelSpecificationException {
        if (this.nobs <= (long)numberOfRegressors) {
            throw new ModelSpecificationException(LocalizedFormats.NOT_ENOUGH_DATA_FOR_NUMBER_OF_PREDICTORS, this.nobs, numberOfRegressors);
        }
        if (numberOfRegressors > this.nvars) {
            throw new ModelSpecificationException(LocalizedFormats.TOO_MANY_REGRESSORS, numberOfRegressors, this.nvars);
        }
        this.tolset();
        this.singcheck();
        double[] beta = this.regcf(numberOfRegressors);
        this.ss();
        double[] cov = this.cov(numberOfRegressors);
        int rnk = 0;
        for (int i = 0; i < this.lindep.length; ++i) {
            if (this.lindep[i]) continue;
            ++rnk;
        }
        boolean needsReorder = false;
        for (int i = 0; i < numberOfRegressors; ++i) {
            if (this.vorder[i] == i) continue;
            needsReorder = true;
            break;
        }
        if (!needsReorder) {
            return new RegressionResults(beta, new double[][]{cov}, true, this.nobs, rnk, this.sumy, this.sumsqy, this.sserr, this.hasIntercept, false);
        }
        double[] betaNew = new double[beta.length];
        double[] covNew = new double[cov.length];
        int[] newIndices = new int[beta.length];
        for (int i = 0; i < this.nvars; ++i) {
            for (int j = 0; j < numberOfRegressors; ++j) {
                if (this.vorder[j] != i) continue;
                betaNew[i] = beta[j];
                newIndices[i] = j;
            }
        }
        int idx1 = 0;
        for (int i = 0; i < beta.length; ++i) {
            int _i = newIndices[i];
            int j = 0;
            while (j <= i) {
                int _j = newIndices[j];
                int idx2 = _i > _j ? _i * (_i + 1) / 2 + _j : _j * (_j + 1) / 2 + _i;
                covNew[idx1] = cov[idx2];
                ++j;
                ++idx1;
            }
        }
        return new RegressionResults(betaNew, new double[][]{covNew}, true, this.nobs, rnk, this.sumy, this.sumsqy, this.sserr, this.hasIntercept, false);
    }

    public RegressionResults regress(int[] variablesToInclude) throws ModelSpecificationException {
        int[] series;
        if (variablesToInclude.length > this.nvars) {
            throw new ModelSpecificationException(LocalizedFormats.TOO_MANY_REGRESSORS, variablesToInclude.length, this.nvars);
        }
        if (this.nobs <= (long)this.nvars) {
            throw new ModelSpecificationException(LocalizedFormats.NOT_ENOUGH_DATA_FOR_NUMBER_OF_PREDICTORS, this.nobs, this.nvars);
        }
        Arrays.sort(variablesToInclude);
        int iExclude = 0;
        for (int i = 0; i < variablesToInclude.length; ++i) {
            if (i >= this.nvars) {
                throw new ModelSpecificationException(LocalizedFormats.INDEX_LARGER_THAN_MAX, i, this.nvars);
            }
            if (i <= 0 || variablesToInclude[i] != variablesToInclude[i - 1]) continue;
            variablesToInclude[i] = -1;
            ++iExclude;
        }
        if (iExclude > 0) {
            int j = 0;
            series = new int[variablesToInclude.length - iExclude];
            for (int i = 0; i < variablesToInclude.length; ++i) {
                if (variablesToInclude[i] <= -1) continue;
                series[j] = variablesToInclude[i];
                ++j;
            }
        } else {
            series = variablesToInclude;
        }
        this.reorderRegressors(series, 0);
        this.tolset();
        this.singcheck();
        double[] beta = this.regcf(series.length);
        this.ss();
        double[] cov = this.cov(series.length);
        int rnk = 0;
        for (int i = 0; i < this.lindep.length; ++i) {
            if (this.lindep[i]) continue;
            ++rnk;
        }
        boolean needsReorder = false;
        for (int i = 0; i < this.nvars; ++i) {
            if (this.vorder[i] == series[i]) continue;
            needsReorder = true;
            break;
        }
        if (!needsReorder) {
            return new RegressionResults(beta, new double[][]{cov}, true, this.nobs, rnk, this.sumy, this.sumsqy, this.sserr, this.hasIntercept, false);
        }
        double[] betaNew = new double[beta.length];
        int[] newIndices = new int[beta.length];
        for (int i = 0; i < series.length; ++i) {
            for (int j = 0; j < this.vorder.length; ++j) {
                if (this.vorder[j] != series[i]) continue;
                betaNew[i] = beta[j];
                newIndices[i] = j;
            }
        }
        double[] covNew = new double[cov.length];
        int idx1 = 0;
        for (int i = 0; i < beta.length; ++i) {
            int _i = newIndices[i];
            int j = 0;
            while (j <= i) {
                int _j = newIndices[j];
                int idx2 = _i > _j ? _i * (_i + 1) / 2 + _j : _j * (_j + 1) / 2 + _i;
                covNew[idx1] = cov[idx2];
                ++j;
                ++idx1;
            }
        }
        return new RegressionResults(betaNew, new double[][]{covNew}, true, this.nobs, rnk, this.sumy, this.sumsqy, this.sserr, this.hasIntercept, false);
    }
}

