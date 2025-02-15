/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.tools;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.TreeSet;
import org.bouncycastle.asn1.x9.ECNamedCurveTable;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.ec.CustomNamedCurves;
import org.bouncycastle.math.ec.ECAlgorithms;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.util.Integers;

public class TraceOptimizer {
    private static final BigInteger ONE = BigInteger.valueOf(1L);
    private static final SecureRandom R = new SecureRandom();

    public static void main(String[] stringArray) {
        TreeSet treeSet = new TreeSet(TraceOptimizer.enumToList(ECNamedCurveTable.getNames()));
        treeSet.addAll(TraceOptimizer.enumToList(CustomNamedCurves.getNames()));
        for (String string : treeSet) {
            X9ECParameters x9ECParameters = CustomNamedCurves.getByName(string);
            if (x9ECParameters == null) {
                x9ECParameters = ECNamedCurveTable.getByName(string);
            }
            if (x9ECParameters == null || !ECAlgorithms.isF2mCurve(x9ECParameters.getCurve())) continue;
            System.out.print(string + ":");
            TraceOptimizer.implPrintNonZeroTraceBits(x9ECParameters);
        }
    }

    public static void printNonZeroTraceBits(X9ECParameters x9ECParameters) {
        if (!ECAlgorithms.isF2mCurve(x9ECParameters.getCurve())) {
            throw new IllegalArgumentException("Trace only defined over characteristic-2 fields");
        }
        TraceOptimizer.implPrintNonZeroTraceBits(x9ECParameters);
    }

    public static void implPrintNonZeroTraceBits(X9ECParameters x9ECParameters) {
        int n;
        ECFieldElement eCFieldElement;
        BigInteger bigInteger;
        int n2;
        ECCurve eCCurve = x9ECParameters.getCurve();
        int n3 = eCCurve.getFieldSize();
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (n2 = 0; n2 < n3; ++n2) {
            bigInteger = ONE.shiftLeft(n2);
            eCFieldElement = eCCurve.fromBigInteger(bigInteger);
            n = TraceOptimizer.calculateTrace(eCFieldElement);
            if (n == 0) continue;
            arrayList.add(Integers.valueOf(n2));
            System.out.print(" " + n2);
        }
        System.out.println();
        for (n2 = 0; n2 < 1000; ++n2) {
            bigInteger = new BigInteger(n3, R);
            eCFieldElement = eCCurve.fromBigInteger(bigInteger);
            n = TraceOptimizer.calculateTrace(eCFieldElement);
            int n4 = 0;
            for (int i = 0; i < arrayList.size(); ++i) {
                int n5 = (Integer)arrayList.get(i);
                if (!bigInteger.testBit(n5)) continue;
                n4 ^= 1;
            }
            if (n == n4) continue;
            throw new IllegalStateException("Optimized-trace sanity check failed");
        }
    }

    private static int calculateTrace(ECFieldElement eCFieldElement) {
        int n = eCFieldElement.getFieldSize();
        ECFieldElement eCFieldElement2 = eCFieldElement;
        for (int i = 1; i < n; ++i) {
            eCFieldElement = eCFieldElement.square();
            eCFieldElement2 = eCFieldElement2.add(eCFieldElement);
        }
        BigInteger bigInteger = eCFieldElement2.toBigInteger();
        if (bigInteger.bitLength() > 1) {
            throw new IllegalStateException();
        }
        return bigInteger.intValue();
    }

    private static ArrayList enumToList(Enumeration enumeration) {
        ArrayList arrayList = new ArrayList();
        while (enumeration.hasMoreElements()) {
            arrayList.add(enumeration.nextElement());
        }
        return arrayList;
    }
}

