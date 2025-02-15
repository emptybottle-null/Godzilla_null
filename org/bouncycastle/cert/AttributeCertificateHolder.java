/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert;

import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.Holder;
import org.bouncycastle.asn1.x509.IssuerSerial;
import org.bouncycastle.asn1.x509.ObjectDigestInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Selector;

public class AttributeCertificateHolder
implements Selector {
    private static DigestCalculatorProvider digestCalculatorProvider;
    final Holder holder;

    AttributeCertificateHolder(ASN1Sequence aSN1Sequence) {
        this.holder = Holder.getInstance(aSN1Sequence);
    }

    public AttributeCertificateHolder(X500Name x500Name, BigInteger bigInteger) {
        this.holder = new Holder(new IssuerSerial(this.generateGeneralNames(x500Name), new ASN1Integer(bigInteger)));
    }

    public AttributeCertificateHolder(X509CertificateHolder x509CertificateHolder) {
        this.holder = new Holder(new IssuerSerial(this.generateGeneralNames(x509CertificateHolder.getIssuer()), new ASN1Integer(x509CertificateHolder.getSerialNumber())));
    }

    public AttributeCertificateHolder(X500Name x500Name) {
        this.holder = new Holder(this.generateGeneralNames(x500Name));
    }

    public AttributeCertificateHolder(int n, ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1ObjectIdentifier aSN1ObjectIdentifier2, byte[] byArray) {
        this.holder = new Holder(new ObjectDigestInfo(n, aSN1ObjectIdentifier2, new AlgorithmIdentifier(aSN1ObjectIdentifier), Arrays.clone(byArray)));
    }

    public int getDigestedObjectType() {
        if (this.holder.getObjectDigestInfo() != null) {
            return this.holder.getObjectDigestInfo().getDigestedObjectType().getValue().intValue();
        }
        return -1;
    }

    public AlgorithmIdentifier getDigestAlgorithm() {
        if (this.holder.getObjectDigestInfo() != null) {
            return this.holder.getObjectDigestInfo().getDigestAlgorithm();
        }
        return null;
    }

    public byte[] getObjectDigest() {
        if (this.holder.getObjectDigestInfo() != null) {
            return this.holder.getObjectDigestInfo().getObjectDigest().getBytes();
        }
        return null;
    }

    public ASN1ObjectIdentifier getOtherObjectTypeID() {
        if (this.holder.getObjectDigestInfo() != null) {
            new ASN1ObjectIdentifier(this.holder.getObjectDigestInfo().getOtherObjectTypeID().getId());
        }
        return null;
    }

    private GeneralNames generateGeneralNames(X500Name x500Name) {
        return new GeneralNames(new GeneralName(x500Name));
    }

    private boolean matchesDN(X500Name x500Name, GeneralNames generalNames) {
        GeneralName[] generalNameArray = generalNames.getNames();
        for (int i = 0; i != generalNameArray.length; ++i) {
            GeneralName generalName = generalNameArray[i];
            if (generalName.getTagNo() != 4 || !X500Name.getInstance(generalName.getName()).equals(x500Name)) continue;
            return true;
        }
        return false;
    }

    private X500Name[] getPrincipals(GeneralName[] generalNameArray) {
        ArrayList<X500Name> arrayList = new ArrayList<X500Name>(generalNameArray.length);
        for (int i = 0; i != generalNameArray.length; ++i) {
            if (generalNameArray[i].getTagNo() != 4) continue;
            arrayList.add(X500Name.getInstance(generalNameArray[i].getName()));
        }
        return arrayList.toArray(new X500Name[arrayList.size()]);
    }

    public X500Name[] getEntityNames() {
        if (this.holder.getEntityName() != null) {
            return this.getPrincipals(this.holder.getEntityName().getNames());
        }
        return null;
    }

    public X500Name[] getIssuer() {
        if (this.holder.getBaseCertificateID() != null) {
            return this.getPrincipals(this.holder.getBaseCertificateID().getIssuer().getNames());
        }
        return null;
    }

    public BigInteger getSerialNumber() {
        if (this.holder.getBaseCertificateID() != null) {
            return this.holder.getBaseCertificateID().getSerial().getValue();
        }
        return null;
    }

    public Object clone() {
        return new AttributeCertificateHolder((ASN1Sequence)this.holder.toASN1Primitive());
    }

    public boolean match(Object object) {
        if (!(object instanceof X509CertificateHolder)) {
            return false;
        }
        X509CertificateHolder x509CertificateHolder = (X509CertificateHolder)object;
        if (this.holder.getBaseCertificateID() != null) {
            return this.holder.getBaseCertificateID().getSerial().getValue().equals(x509CertificateHolder.getSerialNumber()) && this.matchesDN(x509CertificateHolder.getIssuer(), this.holder.getBaseCertificateID().getIssuer());
        }
        if (this.holder.getEntityName() != null && this.matchesDN(x509CertificateHolder.getSubject(), this.holder.getEntityName())) {
            return true;
        }
        if (this.holder.getObjectDigestInfo() != null) {
            try {
                DigestCalculator digestCalculator = digestCalculatorProvider.get(this.holder.getObjectDigestInfo().getDigestAlgorithm());
                OutputStream outputStream = digestCalculator.getOutputStream();
                switch (this.getDigestedObjectType()) {
                    case 0: {
                        outputStream.write(x509CertificateHolder.getSubjectPublicKeyInfo().getEncoded());
                        break;
                    }
                    case 1: {
                        outputStream.write(x509CertificateHolder.getEncoded());
                    }
                }
                outputStream.close();
                if (!Arrays.areEqual(digestCalculator.getDigest(), this.getObjectDigest())) {
                    return false;
                }
            } catch (Exception exception) {
                return false;
            }
        }
        return false;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof AttributeCertificateHolder)) {
            return false;
        }
        AttributeCertificateHolder attributeCertificateHolder = (AttributeCertificateHolder)object;
        return this.holder.equals(attributeCertificateHolder.holder);
    }

    public int hashCode() {
        return this.holder.hashCode();
    }

    public static void setDigestCalculatorProvider(DigestCalculatorProvider digestCalculatorProvider) {
        AttributeCertificateHolder.digestCalculatorProvider = digestCalculatorProvider;
    }
}

