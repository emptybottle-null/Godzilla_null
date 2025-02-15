/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.dvcs;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.dvcs.DVCSRequestInformation;
import org.bouncycastle.asn1.dvcs.DVCSTime;
import org.bouncycastle.asn1.dvcs.ServiceType;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.PolicyInformation;
import org.bouncycastle.util.BigIntegers;

public class DVCSRequestInformationBuilder {
    private int version = 1;
    private final ServiceType service;
    private DVCSRequestInformation initialInfo;
    private BigInteger nonce;
    private DVCSTime requestTime;
    private GeneralNames requester;
    private PolicyInformation requestPolicy;
    private GeneralNames dvcs;
    private GeneralNames dataLocations;
    private Extensions extensions;
    private static final int DEFAULT_VERSION = 1;
    private static final int TAG_REQUESTER = 0;
    private static final int TAG_REQUEST_POLICY = 1;
    private static final int TAG_DVCS = 2;
    private static final int TAG_DATA_LOCATIONS = 3;
    private static final int TAG_EXTENSIONS = 4;

    public DVCSRequestInformationBuilder(ServiceType serviceType) {
        this.service = serviceType;
    }

    public DVCSRequestInformationBuilder(DVCSRequestInformation dVCSRequestInformation) {
        this.initialInfo = dVCSRequestInformation;
        this.service = dVCSRequestInformation.getService();
        this.version = dVCSRequestInformation.getVersion();
        this.nonce = dVCSRequestInformation.getNonce();
        this.requestTime = dVCSRequestInformation.getRequestTime();
        this.requestPolicy = dVCSRequestInformation.getRequestPolicy();
        this.dvcs = dVCSRequestInformation.getDVCS();
        this.dataLocations = dVCSRequestInformation.getDataLocations();
    }

    public DVCSRequestInformation build() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.version != 1) {
            aSN1EncodableVector.add(new ASN1Integer(this.version));
        }
        aSN1EncodableVector.add(this.service);
        if (this.nonce != null) {
            aSN1EncodableVector.add(new ASN1Integer(this.nonce));
        }
        if (this.requestTime != null) {
            aSN1EncodableVector.add(this.requestTime);
        }
        int[] nArray = new int[]{0, 1, 2, 3, 4};
        ASN1Encodable[] aSN1EncodableArray = new ASN1Encodable[]{this.requester, this.requestPolicy, this.dvcs, this.dataLocations, this.extensions};
        for (int i = 0; i < nArray.length; ++i) {
            int n = nArray[i];
            ASN1Encodable aSN1Encodable = aSN1EncodableArray[i];
            if (aSN1Encodable == null) continue;
            aSN1EncodableVector.add(new DERTaggedObject(false, n, aSN1Encodable));
        }
        return DVCSRequestInformation.getInstance(new DERSequence(aSN1EncodableVector));
    }

    public void setVersion(int n) {
        if (this.initialInfo != null) {
            throw new IllegalStateException("cannot change version in existing DVCSRequestInformation");
        }
        this.version = n;
    }

    public void setNonce(BigInteger bigInteger) {
        if (this.initialInfo != null) {
            if (this.initialInfo.getNonce() == null) {
                this.nonce = bigInteger;
            } else {
                byte[] byArray = this.initialInfo.getNonce().toByteArray();
                byte[] byArray2 = BigIntegers.asUnsignedByteArray(bigInteger);
                byte[] byArray3 = new byte[byArray.length + byArray2.length];
                System.arraycopy(byArray, 0, byArray3, 0, byArray.length);
                System.arraycopy(byArray2, 0, byArray3, byArray.length, byArray2.length);
                this.nonce = new BigInteger(byArray3);
            }
        }
        this.nonce = bigInteger;
    }

    public void setRequestTime(DVCSTime dVCSTime) {
        if (this.initialInfo != null) {
            throw new IllegalStateException("cannot change request time in existing DVCSRequestInformation");
        }
        this.requestTime = dVCSTime;
    }

    public void setRequester(GeneralName generalName) {
        this.setRequester(new GeneralNames(generalName));
    }

    public void setRequester(GeneralNames generalNames) {
        this.requester = generalNames;
    }

    public void setRequestPolicy(PolicyInformation policyInformation) {
        if (this.initialInfo != null) {
            throw new IllegalStateException("cannot change request policy in existing DVCSRequestInformation");
        }
        this.requestPolicy = policyInformation;
    }

    public void setDVCS(GeneralName generalName) {
        this.setDVCS(new GeneralNames(generalName));
    }

    public void setDVCS(GeneralNames generalNames) {
        this.dvcs = generalNames;
    }

    public void setDataLocations(GeneralName generalName) {
        this.setDataLocations(new GeneralNames(generalName));
    }

    public void setDataLocations(GeneralNames generalNames) {
        this.dataLocations = generalNames;
    }

    public void setExtensions(Extensions extensions) {
        if (this.initialInfo != null) {
            throw new IllegalStateException("cannot change extensions in existing DVCSRequestInformation");
        }
        this.extensions = extensions;
    }
}

