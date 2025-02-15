/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.cmp;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.cmp.PKIBody;
import org.bouncycastle.asn1.cmp.PKIHeader;
import org.bouncycastle.asn1.cmp.PKIMessage;
import org.bouncycastle.cert.CertIOException;

public class GeneralPKIMessage {
    private final PKIMessage pkiMessage;

    private static PKIMessage parseBytes(byte[] byArray) throws IOException {
        try {
            return PKIMessage.getInstance(ASN1Primitive.fromByteArray(byArray));
        } catch (ClassCastException classCastException) {
            throw new CertIOException("malformed data: " + classCastException.getMessage(), classCastException);
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new CertIOException("malformed data: " + illegalArgumentException.getMessage(), illegalArgumentException);
        }
    }

    public GeneralPKIMessage(byte[] byArray) throws IOException {
        this(GeneralPKIMessage.parseBytes(byArray));
    }

    public GeneralPKIMessage(PKIMessage pKIMessage) {
        this.pkiMessage = pKIMessage;
    }

    public PKIHeader getHeader() {
        return this.pkiMessage.getHeader();
    }

    public PKIBody getBody() {
        return this.pkiMessage.getBody();
    }

    public boolean hasProtection() {
        return this.pkiMessage.getHeader().getProtectionAlg() != null;
    }

    public PKIMessage toASN1Structure() {
        return this.pkiMessage;
    }
}

