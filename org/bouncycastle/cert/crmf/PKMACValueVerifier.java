/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.crmf;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.asn1.cmp.PBMParameter;
import org.bouncycastle.asn1.crmf.PKMACValue;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.crmf.CRMFException;
import org.bouncycastle.cert.crmf.PKMACBuilder;
import org.bouncycastle.operator.MacCalculator;
import org.bouncycastle.util.Arrays;

class PKMACValueVerifier {
    private final PKMACBuilder builder;

    public PKMACValueVerifier(PKMACBuilder pKMACBuilder) {
        this.builder = pKMACBuilder;
    }

    public boolean isValid(PKMACValue pKMACValue, char[] cArray, SubjectPublicKeyInfo subjectPublicKeyInfo) throws CRMFException {
        this.builder.setParameters(PBMParameter.getInstance(pKMACValue.getAlgId().getParameters()));
        MacCalculator macCalculator = this.builder.build(cArray);
        OutputStream outputStream = macCalculator.getOutputStream();
        try {
            outputStream.write(subjectPublicKeyInfo.getEncoded("DER"));
            outputStream.close();
        } catch (IOException iOException) {
            throw new CRMFException("exception encoding mac input: " + iOException.getMessage(), iOException);
        }
        return Arrays.areEqual(macCalculator.getMac(), pKMACValue.getValue().getBytes());
    }
}

