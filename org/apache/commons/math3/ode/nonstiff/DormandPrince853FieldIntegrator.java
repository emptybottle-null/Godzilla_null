/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.ode.nonstiff.DormandPrince853FieldStepInterpolator;
import org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaFieldIntegrator;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class DormandPrince853FieldIntegrator<T extends RealFieldElement<T>>
extends EmbeddedRungeKuttaFieldIntegrator<T> {
    private static final String METHOD_NAME = "Dormand-Prince 8 (5, 3)";
    private final T e1_01 = this.fraction(1.16092271E8, 8.84846592E9);
    private final T e1_06 = this.fraction(-1871647.0, 1527680.0);
    private final T e1_07 = this.fraction(-6.9799717E7, 1.4079366E8);
    private final T e1_08 = this.fraction(1.230164450203E12, 7.39113984E11);
    private final T e1_09 = this.fraction(-1.980813971228885E15, 5.654156025964544E15);
    private final T e1_10 = this.fraction(4.64500805E8, 1.389975552E9);
    private final T e1_11 = this.fraction(1.606764981773E12, 1.9613062656E13);
    private final T e1_12 = this.fraction(-137909.0, 6168960.0);
    private final T e2_01 = this.fraction(-364463.0, 1920240.0);
    private final T e2_06 = this.fraction(3399327.0, 763840.0);
    private final T e2_07 = this.fraction(6.6578432E7, 3.5198415E7);
    private final T e2_08 = this.fraction(-1.674902723E9, 2.887164E8);
    private final T e2_09 = this.fraction(-7.4684743568175E13, 1.76692375811392E14);
    private final T e2_10 = this.fraction(-734375.0, 4826304.0);
    private final T e2_11 = this.fraction(1.71414593E8, 8.512614E8);
    private final T e2_12 = this.fraction(69869.0, 3084480.0);

    public DormandPrince853FieldIntegrator(Field<T> field, double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance) {
        super(field, METHOD_NAME, 12, minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
    }

    public DormandPrince853FieldIntegrator(Field<T> field, double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance) {
        super(field, METHOD_NAME, 12, minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
    }

    @Override
    public T[] getC() {
        RealFieldElement sqrt6 = (RealFieldElement)((RealFieldElement)((RealFieldElement)this.getField().getOne()).multiply(6)).sqrt();
        RealFieldElement[] c = (RealFieldElement[])MathArrays.buildArray(this.getField(), 15);
        c[0] = (RealFieldElement)((RealFieldElement)sqrt6.add(-6.0)).divide(-67.5);
        c[1] = (RealFieldElement)((RealFieldElement)sqrt6.add(-6.0)).divide(-45.0);
        c[2] = (RealFieldElement)((RealFieldElement)sqrt6.add(-6.0)).divide(-30.0);
        c[3] = (RealFieldElement)((RealFieldElement)sqrt6.add(6.0)).divide(30.0);
        c[4] = this.fraction(1, 3);
        c[5] = this.fraction(1, 4);
        c[6] = this.fraction(4, 13);
        c[7] = this.fraction(127, 195);
        c[8] = this.fraction(3, 5);
        c[9] = this.fraction(6, 7);
        c[10] = (RealFieldElement)this.getField().getOne();
        c[11] = (RealFieldElement)this.getField().getOne();
        c[12] = this.fraction(1.0, 10.0);
        c[13] = this.fraction(1.0, 5.0);
        c[14] = this.fraction(7.0, 9.0);
        return c;
    }

    @Override
    public T[][] getA() {
        RealFieldElement sqrt6 = (RealFieldElement)((RealFieldElement)((RealFieldElement)this.getField().getOne()).multiply(6)).sqrt();
        RealFieldElement[][] a = (RealFieldElement[][])MathArrays.buildArray(this.getField(), 15, -1);
        for (int i = 0; i < a.length; ++i) {
            a[i] = (RealFieldElement[])MathArrays.buildArray(this.getField(), i + 1);
        }
        a[0][0] = (RealFieldElement)((RealFieldElement)sqrt6.add(-6.0)).divide(-67.5);
        a[1][0] = (RealFieldElement)((RealFieldElement)sqrt6.add(-6.0)).divide(-180.0);
        a[1][1] = (RealFieldElement)((RealFieldElement)sqrt6.add(-6.0)).divide(-60.0);
        a[2][0] = (RealFieldElement)((RealFieldElement)sqrt6.add(-6.0)).divide(-120.0);
        a[2][1] = (RealFieldElement)this.getField().getZero();
        a[2][2] = (RealFieldElement)((RealFieldElement)sqrt6.add(-6.0)).divide(-40.0);
        a[3][0] = (RealFieldElement)((RealFieldElement)((RealFieldElement)sqrt6.multiply(107)).add(462.0)).divide(3000.0);
        a[3][1] = (RealFieldElement)this.getField().getZero();
        a[3][2] = (RealFieldElement)((RealFieldElement)((RealFieldElement)sqrt6.multiply(197)).add(402.0)).divide(-1000.0);
        a[3][3] = (RealFieldElement)((RealFieldElement)((RealFieldElement)sqrt6.multiply(73)).add(168.0)).divide(375.0);
        a[4][0] = this.fraction(1, 27);
        a[4][1] = (RealFieldElement)this.getField().getZero();
        a[4][2] = (RealFieldElement)this.getField().getZero();
        a[4][3] = (RealFieldElement)((RealFieldElement)sqrt6.add(16.0)).divide(108.0);
        a[4][4] = (RealFieldElement)((RealFieldElement)sqrt6.add(-16.0)).divide(-108.0);
        a[5][0] = this.fraction(19, 512);
        a[5][1] = (RealFieldElement)this.getField().getZero();
        a[5][2] = (RealFieldElement)this.getField().getZero();
        a[5][3] = (RealFieldElement)((RealFieldElement)((RealFieldElement)sqrt6.multiply(23)).add(118.0)).divide(1024.0);
        a[5][4] = (RealFieldElement)((RealFieldElement)((RealFieldElement)sqrt6.multiply(-23)).add(118.0)).divide(1024.0);
        a[5][5] = this.fraction(-9, 512);
        a[6][0] = this.fraction(13772, 371293);
        a[6][1] = (RealFieldElement)this.getField().getZero();
        a[6][2] = (RealFieldElement)this.getField().getZero();
        a[6][3] = (RealFieldElement)((RealFieldElement)((RealFieldElement)sqrt6.multiply(4784)).add(51544.0)).divide(371293.0);
        a[6][4] = (RealFieldElement)((RealFieldElement)((RealFieldElement)sqrt6.multiply(-4784)).add(51544.0)).divide(371293.0);
        a[6][5] = this.fraction(-5688, 371293);
        a[6][6] = this.fraction(3072, 371293);
        a[7][0] = this.fraction(5.8656157643E10, 9.3983540625E10);
        a[7][1] = (RealFieldElement)this.getField().getZero();
        a[7][2] = (RealFieldElement)this.getField().getZero();
        a[7][3] = (RealFieldElement)((RealFieldElement)((RealFieldElement)sqrt6.multiply(-3.18801444819E11)).add(-1.324889724104E12)).divide(6.265569375E11);
        a[7][4] = (RealFieldElement)((RealFieldElement)((RealFieldElement)sqrt6.multiply(3.18801444819E11)).add(-1.324889724104E12)).divide(6.265569375E11);
        a[7][5] = this.fraction(9.6044563816E10, 3.480871875E9);
        a[7][6] = this.fraction(5.682451879168E12, 2.81950621875E11);
        a[7][7] = this.fraction(-1.65125654E8, 3796875.0);
        a[8][0] = this.fraction(8909899.0, 1.8653125E7);
        a[8][1] = (RealFieldElement)this.getField().getZero();
        a[8][2] = (RealFieldElement)this.getField().getZero();
        a[8][3] = (RealFieldElement)((RealFieldElement)((RealFieldElement)sqrt6.multiply(-1137963.0)).add(-4521408.0)).divide(2937500.0);
        a[8][4] = (RealFieldElement)((RealFieldElement)((RealFieldElement)sqrt6.multiply(1137963.0)).add(-4521408.0)).divide(2937500.0);
        a[8][5] = this.fraction(9.6663078E7, 4553125.0);
        a[8][6] = this.fraction(2.107245056E9, 1.37915625E8);
        a[8][7] = this.fraction(-4.913652016E9, 1.47609375E8);
        a[8][8] = this.fraction(-7.889427E7, 3.880452869E9);
        a[9][0] = this.fraction(-2.0401265806E10, 2.1769653311E10);
        a[9][1] = (RealFieldElement)this.getField().getZero();
        a[9][2] = (RealFieldElement)this.getField().getZero();
        a[9][3] = (RealFieldElement)((RealFieldElement)((RealFieldElement)sqrt6.multiply(94326.0)).add(354216.0)).divide(112847.0);
        a[9][4] = (RealFieldElement)((RealFieldElement)((RealFieldElement)sqrt6.multiply(-94326.0)).add(354216.0)).divide(112847.0);
        a[9][5] = this.fraction(-4.3306765128E10, 5.313852383E9);
        a[9][6] = this.fraction(-2.0866708358144E13, 1.126708119789E12);
        a[9][7] = this.fraction(1.488600343802E13, 6.54632330667E11);
        a[9][8] = this.fraction(3.5290686222309376E16, 1.4152473387134412E16);
        a[9][9] = this.fraction(-1.477884375E9, 4.85066827E8);
        a[10][0] = this.fraction(3.9815761E7, 1.7514443E7);
        a[10][1] = (RealFieldElement)this.getField().getZero();
        a[10][2] = (RealFieldElement)this.getField().getZero();
        a[10][3] = (RealFieldElement)((RealFieldElement)((RealFieldElement)sqrt6.multiply(-960905.0)).add(-3457480.0)).divide(551636.0);
        a[10][4] = (RealFieldElement)((RealFieldElement)((RealFieldElement)sqrt6.multiply(960905.0)).add(-3457480.0)).divide(551636.0);
        a[10][5] = this.fraction(-8.44554132E8, 4.7026969E7);
        a[10][6] = this.fraction(8.444996352E9, 3.02158619E8);
        a[10][7] = this.fraction(-2.509602342E9, 8.77790785E8);
        a[10][8] = this.fraction(-2.8388795297996248E16, 3.199510091356783E15);
        a[10][9] = this.fraction(2.2671625E8, 1.8341897E7);
        a[10][10] = this.fraction(1.371316744E9, 2.131383595E9);
        a[11][0] = this.fraction(104257.0, 1920240.0);
        a[11][1] = (RealFieldElement)this.getField().getZero();
        a[11][2] = (RealFieldElement)this.getField().getZero();
        a[11][3] = (RealFieldElement)this.getField().getZero();
        a[11][4] = (RealFieldElement)this.getField().getZero();
        a[11][5] = this.fraction(3399327.0, 763840.0);
        a[11][6] = this.fraction(6.6578432E7, 3.5198415E7);
        a[11][7] = this.fraction(-1.674902723E9, 2.887164E8);
        a[11][8] = this.fraction(5.4980371265625E13, 1.76692375811392E14);
        a[11][9] = this.fraction(-734375.0, 4826304.0);
        a[11][10] = this.fraction(1.71414593E8, 8.512614E8);
        a[11][11] = this.fraction(137909.0, 3084480.0);
        a[12][0] = this.fraction(1.3481885573E10, 2.4003E11);
        a[12][1] = (RealFieldElement)this.getField().getZero();
        a[12][2] = (RealFieldElement)this.getField().getZero();
        a[12][3] = (RealFieldElement)this.getField().getZero();
        a[12][4] = (RealFieldElement)this.getField().getZero();
        a[12][5] = (RealFieldElement)this.getField().getZero();
        a[12][6] = this.fraction(1.39418837528E11, 5.49975234375E11);
        a[12][7] = this.fraction(-1.1108320068443E13, 4.51119375E13);
        a[12][8] = this.fraction(-1.769651421925959E15, 1.424938514608E16);
        a[12][9] = this.fraction(5.7799439E7, 3.77055E8);
        a[12][10] = this.fraction(7.93322643029E11, 9.673425E13);
        a[12][11] = this.fraction(1.458939311E9, 1.9278E11);
        a[12][12] = this.fraction(-4149.0, 500000.0);
        a[13][0] = this.fraction(1.595561272731E12, 5.01202735E13);
        a[13][1] = (RealFieldElement)this.getField().getZero();
        a[13][2] = (RealFieldElement)this.getField().getZero();
        a[13][3] = (RealFieldElement)this.getField().getZero();
        a[13][4] = (RealFieldElement)this.getField().getZero();
        a[13][5] = this.fraction(9.75183916491E11, 3.445768803125E13);
        a[13][6] = this.fraction(3.8492013932672E13, 7.18912673015625E14);
        a[13][7] = this.fraction(-1.114881286517557E15, 2.02987107675E16);
        a[13][8] = (RealFieldElement)this.getField().getZero();
        a[13][9] = (RealFieldElement)this.getField().getZero();
        a[13][10] = this.fraction(-2.538710946863E12, 2.343122786125E16);
        a[13][11] = this.fraction(8.824659001E9, 2.306671678125E13);
        a[13][12] = this.fraction(-1.1518334563E10, 3.38311846125E13);
        a[13][13] = this.fraction(1.912306948E9, 1.3532473845E10);
        a[14][0] = this.fraction(-1.3613986967E10, 3.1741908048E10);
        a[14][1] = (RealFieldElement)this.getField().getZero();
        a[14][2] = (RealFieldElement)this.getField().getZero();
        a[14][3] = (RealFieldElement)this.getField().getZero();
        a[14][4] = (RealFieldElement)this.getField().getZero();
        a[14][5] = this.fraction(-4.755612631E9, 1.012344804E9);
        a[14][6] = this.fraction(4.2939257944576E13, 5.588559685701E12);
        a[14][7] = this.fraction(7.7881972900277E13, 1.9140370552944E13);
        a[14][8] = this.fraction(2.2719829234375E13, 6.3689648654052E13);
        a[14][9] = (RealFieldElement)this.getField().getZero();
        a[14][10] = (RealFieldElement)this.getField().getZero();
        a[14][11] = (RealFieldElement)this.getField().getZero();
        a[14][12] = this.fraction(-1.199007803E9, 8.57031517296E11);
        a[14][13] = this.fraction(1.57882067E11, 5.3564469831E10);
        a[14][14] = this.fraction(-2.90468882375E11, 3.1741908048E10);
        return a;
    }

    @Override
    public T[] getB() {
        RealFieldElement[] b = (RealFieldElement[])MathArrays.buildArray(this.getField(), 16);
        b[0] = this.fraction(104257, 1920240);
        b[1] = (RealFieldElement)this.getField().getZero();
        b[2] = (RealFieldElement)this.getField().getZero();
        b[3] = (RealFieldElement)this.getField().getZero();
        b[4] = (RealFieldElement)this.getField().getZero();
        b[5] = this.fraction(3399327.0, 763840.0);
        b[6] = this.fraction(6.6578432E7, 3.5198415E7);
        b[7] = this.fraction(-1.674902723E9, 2.887164E8);
        b[8] = this.fraction(5.4980371265625E13, 1.76692375811392E14);
        b[9] = this.fraction(-734375.0, 4826304.0);
        b[10] = this.fraction(1.71414593E8, 8.512614E8);
        b[11] = this.fraction(137909.0, 3084480.0);
        b[12] = (RealFieldElement)this.getField().getZero();
        b[13] = (RealFieldElement)this.getField().getZero();
        b[14] = (RealFieldElement)this.getField().getZero();
        b[15] = (RealFieldElement)this.getField().getZero();
        return b;
    }

    @Override
    protected DormandPrince853FieldStepInterpolator<T> createInterpolator(boolean forward, T[][] yDotK, FieldODEStateAndDerivative<T> globalPreviousState, FieldODEStateAndDerivative<T> globalCurrentState, FieldEquationsMapper<T> mapper) {
        return new DormandPrince853FieldStepInterpolator(this.getField(), forward, yDotK, globalPreviousState, globalCurrentState, globalPreviousState, globalCurrentState, mapper);
    }

    @Override
    public int getOrder() {
        return 8;
    }

    @Override
    protected T estimateError(T[][] yDotK, T[] y0, T[] y1, T h) {
        RealFieldElement error1 = (RealFieldElement)h.getField().getZero();
        RealFieldElement error2 = (RealFieldElement)h.getField().getZero();
        for (int j = 0; j < this.mainSetDimension; ++j) {
            RealFieldElement errSum1 = (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)yDotK[0][j].multiply(this.e1_01)).add(yDotK[5][j].multiply(this.e1_06))).add(yDotK[6][j].multiply(this.e1_07))).add(yDotK[7][j].multiply(this.e1_08))).add(yDotK[8][j].multiply(this.e1_09))).add(yDotK[9][j].multiply(this.e1_10))).add(yDotK[10][j].multiply(this.e1_11))).add(yDotK[11][j].multiply(this.e1_12));
            RealFieldElement errSum2 = (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)yDotK[0][j].multiply(this.e2_01)).add(yDotK[5][j].multiply(this.e2_06))).add(yDotK[6][j].multiply(this.e2_07))).add(yDotK[7][j].multiply(this.e2_08))).add(yDotK[8][j].multiply(this.e2_09))).add(yDotK[9][j].multiply(this.e2_10))).add(yDotK[10][j].multiply(this.e2_11))).add(yDotK[11][j].multiply(this.e2_12));
            RealFieldElement yScale = MathUtils.max((RealFieldElement)y0[j].abs(), (RealFieldElement)y1[j].abs());
            RealFieldElement tol = this.vecAbsoluteTolerance == null ? (RealFieldElement)((RealFieldElement)yScale.multiply(this.scalRelativeTolerance)).add(this.scalAbsoluteTolerance) : (RealFieldElement)((RealFieldElement)yScale.multiply(this.vecRelativeTolerance[j])).add(this.vecAbsoluteTolerance[j]);
            RealFieldElement ratio1 = errSum1.divide(tol);
            error1 = error1.add(ratio1.multiply(ratio1));
            RealFieldElement ratio2 = errSum2.divide(tol);
            error2 = error2.add(ratio2.multiply(ratio2));
        }
        RealFieldElement den = (RealFieldElement)error1.add(error2.multiply(0.01));
        if (den.getReal() <= 0.0) {
            den = (RealFieldElement)h.getField().getOne();
        }
        return (T)((RealFieldElement)((RealFieldElement)h.abs()).multiply(error1).divide(((RealFieldElement)den.multiply(this.mainSetDimension)).sqrt()));
    }
}

