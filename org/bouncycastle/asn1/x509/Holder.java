/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.IssuerSerial;
import org.bouncycastle.asn1.x509.ObjectDigestInfo;

public class Holder
extends ASN1Object {
    public static final int V1_CERTIFICATE_HOLDER = 0;
    public static final int V2_CERTIFICATE_HOLDER = 1;
    IssuerSerial baseCertificateID;
    GeneralNames entityName;
    ObjectDigestInfo objectDigestInfo;
    private int version = 1;

    public static Holder getInstance(Object object) {
        if (object instanceof Holder) {
            return (Holder)object;
        }
        if (object instanceof ASN1TaggedObject) {
            return new Holder(ASN1TaggedObject.getInstance(object));
        }
        if (object != null) {
            return new Holder(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    private Holder(ASN1TaggedObject aSN1TaggedObject) {
        switch (aSN1TaggedObject.getTagNo()) {
            case 0: {
                this.baseCertificateID = IssuerSerial.getInstance(aSN1TaggedObject, true);
                break;
            }
            case 1: {
                this.entityName = GeneralNames.getInstance(aSN1TaggedObject, true);
                break;
            }
            default: {
                throw new IllegalArgumentException("unknown tag in Holder");
            }
        }
        this.version = 0;
    }

    private Holder(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() > 3) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        block5: for (int i = 0; i != aSN1Sequence.size(); ++i) {
            ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(aSN1Sequence.getObjectAt(i));
            switch (aSN1TaggedObject.getTagNo()) {
                case 0: {
                    this.baseCertificateID = IssuerSerial.getInstance(aSN1TaggedObject, false);
                    continue block5;
                }
                case 1: {
                    this.entityName = GeneralNames.getInstance(aSN1TaggedObject, false);
                    continue block5;
                }
                case 2: {
                    this.objectDigestInfo = ObjectDigestInfo.getInstance(aSN1TaggedObject, false);
                    continue block5;
                }
                default: {
                    throw new IllegalArgumentException("unknown tag in Holder");
                }
            }
        }
        this.version = 1;
    }

    public Holder(IssuerSerial issuerSerial) {
        this(issuerSerial, 1);
    }

    public Holder(IssuerSerial issuerSerial, int n) {
        this.baseCertificateID = issuerSerial;
        this.version = n;
    }

    public int getVersion() {
        return this.version;
    }

    public Holder(GeneralNames generalNames) {
        this(generalNames, 1);
    }

    public Holder(GeneralNames generalNames, int n) {
        this.entityName = generalNames;
        this.version = n;
    }

    public Holder(ObjectDigestInfo objectDigestInfo) {
        this.objectDigestInfo = objectDigestInfo;
    }

    public IssuerSerial getBaseCertificateID() {
        return this.baseCertificateID;
    }

    public GeneralNames getEntityName() {
        return this.entityName;
    }

    public ObjectDigestInfo getObjectDigestInfo() {
        return this.objectDigestInfo;
    }

    public ASN1Primitive toASN1Primitive() {
        if (this.version == 1) {
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            if (this.baseCertificateID != null) {
                aSN1EncodableVector.add(new DERTaggedObject(false, 0, this.baseCertificateID));
            }
            if (this.entityName != null) {
                aSN1EncodableVector.add(new DERTaggedObject(false, 1, this.entityName));
            }
            if (this.objectDigestInfo != null) {
                aSN1EncodableVector.add(new DERTaggedObject(false, 2, this.objectDigestInfo));
            }
            return new DERSequence(aSN1EncodableVector);
        }
        if (this.entityName != null) {
            return new DERTaggedObject(true, 1, this.entityName);
        }
        return new DERTaggedObject(true, 0, this.baseCertificateID);
    }
}

