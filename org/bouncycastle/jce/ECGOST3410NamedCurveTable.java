/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.cryptopro.ECGOST3410NamedCurves;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;

public class ECGOST3410NamedCurveTable {
    public static ECNamedCurveParameterSpec getParameterSpec(String string) {
        ECDomainParameters eCDomainParameters = ECGOST3410NamedCurves.getByName(string);
        if (eCDomainParameters == null) {
            try {
                eCDomainParameters = ECGOST3410NamedCurves.getByOID(new ASN1ObjectIdentifier(string));
            } catch (IllegalArgumentException illegalArgumentException) {
                return null;
            }
        }
        if (eCDomainParameters == null) {
            return null;
        }
        return new ECNamedCurveParameterSpec(string, eCDomainParameters.getCurve(), eCDomainParameters.getG(), eCDomainParameters.getN(), eCDomainParameters.getH(), eCDomainParameters.getSeed());
    }

    public static Enumeration getNames() {
        return ECGOST3410NamedCurves.getNames();
    }
}

