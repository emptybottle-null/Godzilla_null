/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.util;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.util.DerUtil;

public class DEROtherInfo {
    private final DERSequence sequence;

    private DEROtherInfo(DERSequence dERSequence) {
        this.sequence = dERSequence;
    }

    public byte[] getEncoded() throws IOException {
        return this.sequence.getEncoded();
    }

    public static final class Builder {
        private final AlgorithmIdentifier algorithmID;
        private final ASN1OctetString partyUVInfo;
        private final ASN1OctetString partyVInfo;
        private ASN1TaggedObject suppPubInfo;
        private ASN1TaggedObject suppPrivInfo;

        public Builder(AlgorithmIdentifier algorithmIdentifier, byte[] byArray, byte[] byArray2) {
            this.algorithmID = algorithmIdentifier;
            this.partyUVInfo = DerUtil.getOctetString(byArray);
            this.partyVInfo = DerUtil.getOctetString(byArray2);
        }

        public Builder withSuppPubInfo(byte[] byArray) {
            this.suppPubInfo = new DERTaggedObject(false, 0, DerUtil.getOctetString(byArray));
            return this;
        }

        public Builder withSuppPrivInfo(byte[] byArray) {
            this.suppPrivInfo = new DERTaggedObject(false, 1, DerUtil.getOctetString(byArray));
            return this;
        }

        public DEROtherInfo build() {
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            aSN1EncodableVector.add(this.algorithmID);
            aSN1EncodableVector.add(this.partyUVInfo);
            aSN1EncodableVector.add(this.partyVInfo);
            if (this.suppPubInfo != null) {
                aSN1EncodableVector.add(this.suppPubInfo);
            }
            if (this.suppPrivInfo != null) {
                aSN1EncodableVector.add(this.suppPrivInfo);
            }
            return new DEROtherInfo(new DERSequence(aSN1EncodableVector));
        }
    }
}

