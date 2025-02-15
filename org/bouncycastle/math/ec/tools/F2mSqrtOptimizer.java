/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.tools;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.TreeSet;
import org.bouncycastle.asn1.x9.ECNamedCurveTable;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.ec.CustomNamedCurves;
import org.bouncycastle.math.ec.ECAlgorithms;
import org.bouncycastle.math.ec.ECFieldElement;

public class F2mSqrtOptimizer {
    public static void main(String[] stringArray) {
        TreeSet treeSet = new TreeSet(F2mSqrtOptimizer.enumToList(ECNamedCurveTable.getNames()));
        treeSet.addAll(F2mSqrtOptimizer.enumToList(CustomNamedCurves.getNames()));
        for (String string : treeSet) {
            X9ECParameters x9ECParameters = CustomNamedCurves.getByName(string);
            if (x9ECParameters == null) {
                x9ECParameters = ECNamedCurveTable.getByName(string);
            }
            if (x9ECParameters == null || !ECAlgorithms.isF2mCurve(x9ECParameters.getCurve())) continue;
            System.out.print(string + ":");
            F2mSqrtOptimizer.implPrintRootZ(x9ECParameters);
        }
    }

    public static void printRootZ(X9ECParameters x9ECParameters) {
        if (!ECAlgorithms.isF2mCurve(x9ECParameters.getCurve())) {
            throw new IllegalArgumentException("Sqrt optimization only defined over characteristic-2 fields");
        }
        F2mSqrtOptimizer.implPrintRootZ(x9ECParameters);
    }

    private static void implPrintRootZ(X9ECParameters x9ECParameters) {
        ECFieldElement eCFieldElement = x9ECParameters.getCurve().fromBigInteger(BigInteger.valueOf(2L));
        ECFieldElement eCFieldElement2 = eCFieldElement.sqrt();
        System.out.println(eCFieldElement2.toBigInteger().toString(16).toUpperCase());
        if (!eCFieldElement2.square().equals(eCFieldElement)) {
            throw new IllegalStateException("Optimized-sqrt sanity check failed");
        }
    }

    private static ArrayList enumToList(Enumeration enumeration) {
        ArrayList arrayList = new ArrayList();
        while (enumeration.hasMoreElements()) {
            arrayList.add(enumeration.nextElement());
        }
        return arrayList;
    }
}

