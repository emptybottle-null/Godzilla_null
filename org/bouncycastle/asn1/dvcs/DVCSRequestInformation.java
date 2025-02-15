/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.dvcs;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.dvcs.DVCSTime;
import org.bouncycastle.asn1.dvcs.ServiceType;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.PolicyInformation;

public class DVCSRequestInformation
extends ASN1Object {
    private int version = 1;
    private ServiceType service;
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

    private DVCSRequestInformation(ASN1Sequence aSN1Sequence) {
        ASN1Encodable aSN1Encodable;
        int n = 0;
        if (aSN1Sequence.getObjectAt(0) instanceof ASN1Integer) {
            aSN1Encodable = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(n++));
            this.version = aSN1Encodable.getValue().intValue();
        } else {
            this.version = 1;
        }
        this.service = ServiceType.getInstance(aSN1Sequence.getObjectAt(n++));
        while (n < aSN1Sequence.size()) {
            aSN1Encodable = aSN1Sequence.getObjectAt(n);
            if (aSN1Encodable instanceof ASN1Integer) {
                this.nonce = ASN1Integer.getInstance(aSN1Encodable).getValue();
            } else if (aSN1Encodable instanceof ASN1GeneralizedTime) {
                this.requestTime = DVCSTime.getInstance(aSN1Encodable);
            } else if (aSN1Encodable instanceof ASN1TaggedObject) {
                ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(aSN1Encodable);
                int n2 = aSN1TaggedObject.getTagNo();
                switch (n2) {
                    case 0: {
                        this.requester = GeneralNames.getInstance(aSN1TaggedObject, false);
                        break;
                    }
                    case 1: {
                        this.requestPolicy = PolicyInformation.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, false));
                        break;
                    }
                    case 2: {
                        this.dvcs = GeneralNames.getInstance(aSN1TaggedObject, false);
                        break;
                    }
                    case 3: {
                        this.dataLocations = GeneralNames.getInstance(aSN1TaggedObject, false);
                        break;
                    }
                    case 4: {
                        this.extensions = Extensions.getInstance(aSN1TaggedObject, false);
                        break;
                    }
                    default: {
                        throw new IllegalArgumentException("unknown tag number encountered: " + n2);
                    }
                }
            } else {
                this.requestTime = DVCSTime.getInstance(aSN1Encodable);
            }
            ++n;
        }
    }

    public static DVCSRequestInformation getInstance(Object object) {
        if (object instanceof DVCSRequestInformation) {
            return (DVCSRequestInformation)object;
        }
        if (object != null) {
            return new DVCSRequestInformation(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public static DVCSRequestInformation getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return DVCSRequestInformation.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public ASN1Primitive toASN1Primitive() {
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
        return new DERSequence(aSN1EncodableVector);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("DVCSRequestInformation {\n");
        if (this.version != 1) {
            stringBuffer.append("version: " + this.version + "\n");
        }
        stringBuffer.append("service: " + this.service + "\n");
        if (this.nonce != null) {
            stringBuffer.append("nonce: " + this.nonce + "\n");
        }
        if (this.requestTime != null) {
            stringBuffer.append("requestTime: " + this.requestTime + "\n");
        }
        if (this.requester != null) {
            stringBuffer.append("requester: " + this.requester + "\n");
        }
        if (this.requestPolicy != null) {
            stringBuffer.append("requestPolicy: " + this.requestPolicy + "\n");
        }
        if (this.dvcs != null) {
            stringBuffer.append("dvcs: " + this.dvcs + "\n");
        }
        if (this.dataLocations != null) {
            stringBuffer.append("dataLocations: " + this.dataLocations + "\n");
        }
        if (this.extensions != null) {
            stringBuffer.append("extensions: " + this.extensions + "\n");
        }
        stringBuffer.append("}\n");
        return stringBuffer.toString();
    }

    public int getVersion() {
        return this.version;
    }

    public ServiceType getService() {
        return this.service;
    }

    public BigInteger getNonce() {
        return this.nonce;
    }

    public DVCSTime getRequestTime() {
        return this.requestTime;
    }

    public GeneralNames getRequester() {
        return this.requester;
    }

    public PolicyInformation getRequestPolicy() {
        return this.requestPolicy;
    }

    public GeneralNames getDVCS() {
        return this.dvcs;
    }

    public GeneralNames getDataLocations() {
        return this.dataLocations;
    }

    public Extensions getExtensions() {
        return this.extensions;
    }
}

