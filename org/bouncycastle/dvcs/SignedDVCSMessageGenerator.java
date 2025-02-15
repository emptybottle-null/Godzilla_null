/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.dvcs;

import java.io.IOException;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.dvcs.DVCSException;
import org.bouncycastle.dvcs.DVCSMessage;

public class SignedDVCSMessageGenerator {
    private final CMSSignedDataGenerator signedDataGen;

    public SignedDVCSMessageGenerator(CMSSignedDataGenerator cMSSignedDataGenerator) {
        this.signedDataGen = cMSSignedDataGenerator;
    }

    public CMSSignedData build(DVCSMessage dVCSMessage) throws DVCSException {
        try {
            byte[] byArray = dVCSMessage.getContent().toASN1Primitive().getEncoded("DER");
            return this.signedDataGen.generate(new CMSProcessableByteArray(dVCSMessage.getContentType(), byArray), true);
        } catch (CMSException cMSException) {
            throw new DVCSException("Could not sign DVCS request", cMSException);
        } catch (IOException iOException) {
            throw new DVCSException("Could not encode DVCS request", iOException);
        }
    }
}

