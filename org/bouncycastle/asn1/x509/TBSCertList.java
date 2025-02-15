/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509;

import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.ASN1UTCTime;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.Time;

public class TBSCertList
extends ASN1Object {
    ASN1Integer version;
    AlgorithmIdentifier signature;
    X500Name issuer;
    Time thisUpdate;
    Time nextUpdate;
    ASN1Sequence revokedCertificates;
    Extensions crlExtensions;

    public static TBSCertList getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return TBSCertList.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public static TBSCertList getInstance(Object object) {
        if (object instanceof TBSCertList) {
            return (TBSCertList)object;
        }
        if (object != null) {
            return new TBSCertList(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public TBSCertList(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() < 3 || aSN1Sequence.size() > 7) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        int n = 0;
        this.version = aSN1Sequence.getObjectAt(n) instanceof ASN1Integer ? ASN1Integer.getInstance(aSN1Sequence.getObjectAt(n++)) : null;
        this.signature = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(n++));
        this.issuer = X500Name.getInstance(aSN1Sequence.getObjectAt(n++));
        this.thisUpdate = Time.getInstance(aSN1Sequence.getObjectAt(n++));
        if (n < aSN1Sequence.size() && (aSN1Sequence.getObjectAt(n) instanceof ASN1UTCTime || aSN1Sequence.getObjectAt(n) instanceof ASN1GeneralizedTime || aSN1Sequence.getObjectAt(n) instanceof Time)) {
            this.nextUpdate = Time.getInstance(aSN1Sequence.getObjectAt(n++));
        }
        if (n < aSN1Sequence.size() && !(aSN1Sequence.getObjectAt(n) instanceof ASN1TaggedObject)) {
            this.revokedCertificates = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(n++));
        }
        if (n < aSN1Sequence.size() && aSN1Sequence.getObjectAt(n) instanceof ASN1TaggedObject) {
            this.crlExtensions = Extensions.getInstance(ASN1Sequence.getInstance((ASN1TaggedObject)aSN1Sequence.getObjectAt(n), true));
        }
    }

    public int getVersionNumber() {
        if (this.version == null) {
            return 1;
        }
        return this.version.getValue().intValue() + 1;
    }

    public ASN1Integer getVersion() {
        return this.version;
    }

    public AlgorithmIdentifier getSignature() {
        return this.signature;
    }

    public X500Name getIssuer() {
        return this.issuer;
    }

    public Time getThisUpdate() {
        return this.thisUpdate;
    }

    public Time getNextUpdate() {
        return this.nextUpdate;
    }

    public CRLEntry[] getRevokedCertificates() {
        if (this.revokedCertificates == null) {
            return new CRLEntry[0];
        }
        CRLEntry[] cRLEntryArray = new CRLEntry[this.revokedCertificates.size()];
        for (int i = 0; i < cRLEntryArray.length; ++i) {
            cRLEntryArray[i] = CRLEntry.getInstance(this.revokedCertificates.getObjectAt(i));
        }
        return cRLEntryArray;
    }

    public Enumeration getRevokedCertificateEnumeration() {
        if (this.revokedCertificates == null) {
            return new EmptyEnumeration();
        }
        return new RevokedCertificatesEnumeration(this.revokedCertificates.getObjects());
    }

    public Extensions getExtensions() {
        return this.crlExtensions;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.version != null) {
            aSN1EncodableVector.add(this.version);
        }
        aSN1EncodableVector.add(this.signature);
        aSN1EncodableVector.add(this.issuer);
        aSN1EncodableVector.add(this.thisUpdate);
        if (this.nextUpdate != null) {
            aSN1EncodableVector.add(this.nextUpdate);
        }
        if (this.revokedCertificates != null) {
            aSN1EncodableVector.add(this.revokedCertificates);
        }
        if (this.crlExtensions != null) {
            aSN1EncodableVector.add(new DERTaggedObject(0, this.crlExtensions));
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public static class CRLEntry
    extends ASN1Object {
        ASN1Sequence seq;
        Extensions crlEntryExtensions;

        private CRLEntry(ASN1Sequence aSN1Sequence) {
            if (aSN1Sequence.size() < 2 || aSN1Sequence.size() > 3) {
                throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
            }
            this.seq = aSN1Sequence;
        }

        public static CRLEntry getInstance(Object object) {
            if (object instanceof CRLEntry) {
                return (CRLEntry)object;
            }
            if (object != null) {
                return new CRLEntry(ASN1Sequence.getInstance(object));
            }
            return null;
        }

        public ASN1Integer getUserCertificate() {
            return ASN1Integer.getInstance(this.seq.getObjectAt(0));
        }

        public Time getRevocationDate() {
            return Time.getInstance(this.seq.getObjectAt(1));
        }

        public Extensions getExtensions() {
            if (this.crlEntryExtensions == null && this.seq.size() == 3) {
                this.crlEntryExtensions = Extensions.getInstance(this.seq.getObjectAt(2));
            }
            return this.crlEntryExtensions;
        }

        public ASN1Primitive toASN1Primitive() {
            return this.seq;
        }

        public boolean hasExtensions() {
            return this.seq.size() == 3;
        }
    }

    private class EmptyEnumeration
    implements Enumeration {
        private EmptyEnumeration() {
        }

        public boolean hasMoreElements() {
            return false;
        }

        public Object nextElement() {
            throw new NoSuchElementException("Empty Enumeration");
        }
    }

    private class RevokedCertificatesEnumeration
    implements Enumeration {
        private final Enumeration en;

        RevokedCertificatesEnumeration(Enumeration enumeration) {
            this.en = enumeration;
        }

        public boolean hasMoreElements() {
            return this.en.hasMoreElements();
        }

        public Object nextElement() {
            return CRLEntry.getInstance(this.en.nextElement());
        }
    }
}

