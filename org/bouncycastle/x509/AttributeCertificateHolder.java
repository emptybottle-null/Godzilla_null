/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.x509;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.Principal;
import java.security.cert.CertSelector;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.Holder;
import org.bouncycastle.asn1.x509.IssuerSerial;
import org.bouncycastle.asn1.x509.ObjectDigestInfo;
import org.bouncycastle.jce.PrincipalUtil;
import org.bouncycastle.jce.X509Principal;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Selector;
import org.bouncycastle.x509.X509Util;

public class AttributeCertificateHolder
implements CertSelector,
Selector {
    final Holder holder;

    AttributeCertificateHolder(ASN1Sequence aSN1Sequence) {
        this.holder = Holder.getInstance(aSN1Sequence);
    }

    public AttributeCertificateHolder(X509Principal x509Principal, BigInteger bigInteger) {
        this.holder = new Holder(new IssuerSerial(GeneralNames.getInstance(new DERSequence(new GeneralName(x509Principal))), new ASN1Integer(bigInteger)));
    }

    public AttributeCertificateHolder(X500Principal x500Principal, BigInteger bigInteger) {
        this(X509Util.convertPrincipal(x500Principal), bigInteger);
    }

    public AttributeCertificateHolder(X509Certificate x509Certificate) throws CertificateParsingException {
        X509Principal x509Principal;
        try {
            x509Principal = PrincipalUtil.getIssuerX509Principal(x509Certificate);
        } catch (Exception exception) {
            throw new CertificateParsingException(exception.getMessage());
        }
        this.holder = new Holder(new IssuerSerial(this.generateGeneralNames(x509Principal), new ASN1Integer(x509Certificate.getSerialNumber())));
    }

    public AttributeCertificateHolder(X509Principal x509Principal) {
        this.holder = new Holder(this.generateGeneralNames(x509Principal));
    }

    public AttributeCertificateHolder(X500Principal x500Principal) {
        this(X509Util.convertPrincipal(x500Principal));
    }

    public AttributeCertificateHolder(int n, String string, String string2, byte[] byArray) {
        this.holder = new Holder(new ObjectDigestInfo(n, new ASN1ObjectIdentifier(string2), new AlgorithmIdentifier(new ASN1ObjectIdentifier(string)), Arrays.clone(byArray)));
    }

    public int getDigestedObjectType() {
        if (this.holder.getObjectDigestInfo() != null) {
            return this.holder.getObjectDigestInfo().getDigestedObjectType().getValue().intValue();
        }
        return -1;
    }

    public String getDigestAlgorithm() {
        if (this.holder.getObjectDigestInfo() != null) {
            return this.holder.getObjectDigestInfo().getDigestAlgorithm().getAlgorithm().getId();
        }
        return null;
    }

    public byte[] getObjectDigest() {
        if (this.holder.getObjectDigestInfo() != null) {
            return this.holder.getObjectDigestInfo().getObjectDigest().getBytes();
        }
        return null;
    }

    public String getOtherObjectTypeID() {
        if (this.holder.getObjectDigestInfo() != null) {
            this.holder.getObjectDigestInfo().getOtherObjectTypeID().getId();
        }
        return null;
    }

    private GeneralNames generateGeneralNames(X509Principal x509Principal) {
        return GeneralNames.getInstance(new DERSequence(new GeneralName(x509Principal)));
    }

    private boolean matchesDN(X509Principal x509Principal, GeneralNames generalNames) {
        GeneralName[] generalNameArray = generalNames.getNames();
        for (int i = 0; i != generalNameArray.length; ++i) {
            GeneralName generalName = generalNameArray[i];
            if (generalName.getTagNo() != 4) continue;
            try {
                if (!new X509Principal(generalName.getName().toASN1Primitive().getEncoded()).equals(x509Principal)) continue;
                return true;
            } catch (IOException iOException) {
                // empty catch block
            }
        }
        return false;
    }

    private Object[] getNames(GeneralName[] generalNameArray) {
        ArrayList<X500Principal> arrayList = new ArrayList<X500Principal>(generalNameArray.length);
        for (int i = 0; i != generalNameArray.length; ++i) {
            if (generalNameArray[i].getTagNo() != 4) continue;
            try {
                arrayList.add(new X500Principal(generalNameArray[i].getName().toASN1Primitive().getEncoded()));
                continue;
            } catch (IOException iOException) {
                throw new RuntimeException("badly formed Name object");
            }
        }
        return arrayList.toArray(new Object[arrayList.size()]);
    }

    private Principal[] getPrincipals(GeneralNames generalNames) {
        Object[] objectArray = this.getNames(generalNames.getNames());
        ArrayList<Object> arrayList = new ArrayList<Object>();
        for (int i = 0; i != objectArray.length; ++i) {
            if (!(objectArray[i] instanceof Principal)) continue;
            arrayList.add(objectArray[i]);
        }
        return arrayList.toArray(new Principal[arrayList.size()]);
    }

    public Principal[] getEntityNames() {
        if (this.holder.getEntityName() != null) {
            return this.getPrincipals(this.holder.getEntityName());
        }
        return null;
    }

    public Principal[] getIssuer() {
        if (this.holder.getBaseCertificateID() != null) {
            return this.getPrincipals(this.holder.getBaseCertificateID().getIssuer());
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

    public boolean match(Certificate certificate) {
        block12: {
            if (!(certificate instanceof X509Certificate)) {
                return false;
            }
            X509Certificate x509Certificate = (X509Certificate)certificate;
            try {
                if (this.holder.getBaseCertificateID() != null) {
                    return this.holder.getBaseCertificateID().getSerial().getValue().equals(x509Certificate.getSerialNumber()) && this.matchesDN(PrincipalUtil.getIssuerX509Principal(x509Certificate), this.holder.getBaseCertificateID().getIssuer());
                }
                if (this.holder.getEntityName() != null && this.matchesDN(PrincipalUtil.getSubjectX509Principal(x509Certificate), this.holder.getEntityName())) {
                    return true;
                }
                if (this.holder.getObjectDigestInfo() == null) break block12;
                MessageDigest messageDigest = null;
                try {
                    messageDigest = MessageDigest.getInstance(this.getDigestAlgorithm(), "BC");
                } catch (Exception exception) {
                    return false;
                }
                switch (this.getDigestedObjectType()) {
                    case 0: {
                        messageDigest.update(certificate.getPublicKey().getEncoded());
                        break;
                    }
                    case 1: {
                        messageDigest.update(certificate.getEncoded());
                    }
                }
                if (!Arrays.areEqual(messageDigest.digest(), this.getObjectDigest())) {
                    return false;
                }
            } catch (CertificateEncodingException certificateEncodingException) {
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

    public boolean match(Object object) {
        if (!(object instanceof X509Certificate)) {
            return false;
        }
        return this.match((Certificate)object);
    }
}

