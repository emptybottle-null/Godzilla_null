/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.ec.CustomNamedCurves;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;

public class ECNamedCurveTable {
    public static ECNamedCurveParameterSpec getParameterSpec(String string) {
        X9ECParameters x9ECParameters = CustomNamedCurves.getByName(string);
        if (x9ECParameters == null) {
            try {
                x9ECParameters = CustomNamedCurves.getByOID(new ASN1ObjectIdentifier(string));
            } catch (IllegalArgumentException illegalArgumentException) {
                // empty catch block
            }
            if (x9ECParameters == null && (x9ECParameters = org.bouncycastle.asn1.x9.ECNamedCurveTable.getByName(string)) == null) {
                try {
                    x9ECParameters = org.bouncycastle.asn1.x9.ECNamedCurveTable.getByOID(new ASN1ObjectIdentifier(string));
                } catch (IllegalArgumentException illegalArgumentException) {
                    // empty catch block
                }
            }
        }
        if (x9ECParameters == null) {
            return null;
        }
        return new ECNamedCurveParameterSpec(string, x9ECParameters.getCurve(), x9ECParameters.getG(), x9ECParameters.getN(), x9ECParameters.getH(), x9ECParameters.getSeed());
    }

    public static Enumeration getNames() {
        return org.bouncycastle.asn1.x9.ECNamedCurveTable.getNames();
    }
}

