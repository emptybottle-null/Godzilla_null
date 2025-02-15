/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Boolean;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;

public class Extension
extends ASN1Object {
    public static final ASN1ObjectIdentifier subjectDirectoryAttributes = new ASN1ObjectIdentifier("2.5.29.9").intern();
    public static final ASN1ObjectIdentifier subjectKeyIdentifier = new ASN1ObjectIdentifier("2.5.29.14").intern();
    public static final ASN1ObjectIdentifier keyUsage = new ASN1ObjectIdentifier("2.5.29.15").intern();
    public static final ASN1ObjectIdentifier privateKeyUsagePeriod = new ASN1ObjectIdentifier("2.5.29.16").intern();
    public static final ASN1ObjectIdentifier subjectAlternativeName = new ASN1ObjectIdentifier("2.5.29.17").intern();
    public static final ASN1ObjectIdentifier issuerAlternativeName = new ASN1ObjectIdentifier("2.5.29.18").intern();
    public static final ASN1ObjectIdentifier basicConstraints = new ASN1ObjectIdentifier("2.5.29.19").intern();
    public static final ASN1ObjectIdentifier cRLNumber = new ASN1ObjectIdentifier("2.5.29.20").intern();
    public static final ASN1ObjectIdentifier reasonCode = new ASN1ObjectIdentifier("2.5.29.21").intern();
    public static final ASN1ObjectIdentifier instructionCode = new ASN1ObjectIdentifier("2.5.29.23").intern();
    public static final ASN1ObjectIdentifier invalidityDate = new ASN1ObjectIdentifier("2.5.29.24").intern();
    public static final ASN1ObjectIdentifier deltaCRLIndicator = new ASN1ObjectIdentifier("2.5.29.27").intern();
    public static final ASN1ObjectIdentifier issuingDistributionPoint = new ASN1ObjectIdentifier("2.5.29.28").intern();
    public static final ASN1ObjectIdentifier certificateIssuer = new ASN1ObjectIdentifier("2.5.29.29").intern();
    public static final ASN1ObjectIdentifier nameConstraints = new ASN1ObjectIdentifier("2.5.29.30").intern();
    public static final ASN1ObjectIdentifier cRLDistributionPoints = new ASN1ObjectIdentifier("2.5.29.31").intern();
    public static final ASN1ObjectIdentifier certificatePolicies = new ASN1ObjectIdentifier("2.5.29.32").intern();
    public static final ASN1ObjectIdentifier policyMappings = new ASN1ObjectIdentifier("2.5.29.33").intern();
    public static final ASN1ObjectIdentifier authorityKeyIdentifier = new ASN1ObjectIdentifier("2.5.29.35").intern();
    public static final ASN1ObjectIdentifier policyConstraints = new ASN1ObjectIdentifier("2.5.29.36").intern();
    public static final ASN1ObjectIdentifier extendedKeyUsage = new ASN1ObjectIdentifier("2.5.29.37").intern();
    public static final ASN1ObjectIdentifier freshestCRL = new ASN1ObjectIdentifier("2.5.29.46").intern();
    public static final ASN1ObjectIdentifier inhibitAnyPolicy = new ASN1ObjectIdentifier("2.5.29.54").intern();
    public static final ASN1ObjectIdentifier authorityInfoAccess = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.1.1").intern();
    public static final ASN1ObjectIdentifier subjectInfoAccess = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.1.11").intern();
    public static final ASN1ObjectIdentifier logoType = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.1.12").intern();
    public static final ASN1ObjectIdentifier biometricInfo = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.1.2").intern();
    public static final ASN1ObjectIdentifier qCStatements = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.1.3").intern();
    public static final ASN1ObjectIdentifier auditIdentity = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.1.4").intern();
    public static final ASN1ObjectIdentifier noRevAvail = new ASN1ObjectIdentifier("2.5.29.56").intern();
    public static final ASN1ObjectIdentifier targetInformation = new ASN1ObjectIdentifier("2.5.29.55").intern();
    public static final ASN1ObjectIdentifier expiredCertsOnCRL = new ASN1ObjectIdentifier("2.5.29.60").intern();
    private ASN1ObjectIdentifier extnId;
    private boolean critical;
    private ASN1OctetString value;

    public Extension(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Boolean aSN1Boolean, ASN1OctetString aSN1OctetString) {
        this(aSN1ObjectIdentifier, aSN1Boolean.isTrue(), aSN1OctetString);
    }

    public Extension(ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean bl, byte[] byArray) {
        this(aSN1ObjectIdentifier, bl, (ASN1OctetString)new DEROctetString(byArray));
    }

    public Extension(ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean bl, ASN1OctetString aSN1OctetString) {
        this.extnId = aSN1ObjectIdentifier;
        this.critical = bl;
        this.value = aSN1OctetString;
    }

    private Extension(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() == 2) {
            this.extnId = ASN1ObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
            this.critical = false;
            this.value = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(1));
        } else if (aSN1Sequence.size() == 3) {
            this.extnId = ASN1ObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
            this.critical = ASN1Boolean.getInstance(aSN1Sequence.getObjectAt(1)).isTrue();
            this.value = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(2));
        } else {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
    }

    public static Extension getInstance(Object object) {
        if (object instanceof Extension) {
            return (Extension)object;
        }
        if (object != null) {
            return new Extension(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public ASN1ObjectIdentifier getExtnId() {
        return this.extnId;
    }

    public boolean isCritical() {
        return this.critical;
    }

    public ASN1OctetString getExtnValue() {
        return this.value;
    }

    public ASN1Encodable getParsedValue() {
        return Extension.convertValueToObject(this);
    }

    public int hashCode() {
        if (this.isCritical()) {
            return this.getExtnValue().hashCode() ^ this.getExtnId().hashCode();
        }
        return ~(this.getExtnValue().hashCode() ^ this.getExtnId().hashCode());
    }

    public boolean equals(Object object) {
        if (!(object instanceof Extension)) {
            return false;
        }
        Extension extension = (Extension)object;
        return extension.getExtnId().equals(this.getExtnId()) && extension.getExtnValue().equals(this.getExtnValue()) && extension.isCritical() == this.isCritical();
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.extnId);
        if (this.critical) {
            aSN1EncodableVector.add(ASN1Boolean.getInstance(true));
        }
        aSN1EncodableVector.add(this.value);
        return new DERSequence(aSN1EncodableVector);
    }

    private static ASN1Primitive convertValueToObject(Extension extension) throws IllegalArgumentException {
        try {
            return ASN1Primitive.fromByteArray(extension.getExtnValue().getOctets());
        } catch (IOException iOException) {
            throw new IllegalArgumentException("can't convert extension: " + iOException);
        }
    }
}

