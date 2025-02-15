/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert;

import java.math.BigInteger;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.ExtensionsGenerator;
import org.bouncycastle.asn1.x509.TBSCertList;
import org.bouncycastle.asn1.x509.Time;
import org.bouncycastle.asn1.x509.V2TBSCertListGenerator;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.cert.CertUtils;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.operator.ContentSigner;

public class X509v2CRLBuilder {
    private V2TBSCertListGenerator tbsGen = new V2TBSCertListGenerator();
    private ExtensionsGenerator extGenerator = new ExtensionsGenerator();

    public X509v2CRLBuilder(X500Name x500Name, Date date) {
        this.tbsGen.setIssuer(x500Name);
        this.tbsGen.setThisUpdate(new Time(date));
    }

    public X509v2CRLBuilder(X500Name x500Name, Date date, Locale locale) {
        this.tbsGen.setIssuer(x500Name);
        this.tbsGen.setThisUpdate(new Time(date, locale));
    }

    public X509v2CRLBuilder(X500Name x500Name, Time time) {
        this.tbsGen.setIssuer(x500Name);
        this.tbsGen.setThisUpdate(time);
    }

    public X509v2CRLBuilder setNextUpdate(Date date) {
        return this.setNextUpdate(new Time(date));
    }

    public X509v2CRLBuilder setNextUpdate(Date date, Locale locale) {
        return this.setNextUpdate(new Time(date, locale));
    }

    public X509v2CRLBuilder setNextUpdate(Time time) {
        this.tbsGen.setNextUpdate(time);
        return this;
    }

    public X509v2CRLBuilder addCRLEntry(BigInteger bigInteger, Date date, int n) {
        this.tbsGen.addCRLEntry(new ASN1Integer(bigInteger), new Time(date), n);
        return this;
    }

    public X509v2CRLBuilder addCRLEntry(BigInteger bigInteger, Date date, int n, Date date2) {
        this.tbsGen.addCRLEntry(new ASN1Integer(bigInteger), new Time(date), n, new ASN1GeneralizedTime(date2));
        return this;
    }

    public X509v2CRLBuilder addCRLEntry(BigInteger bigInteger, Date date, Extensions extensions) {
        this.tbsGen.addCRLEntry(new ASN1Integer(bigInteger), new Time(date), extensions);
        return this;
    }

    public X509v2CRLBuilder addCRL(X509CRLHolder x509CRLHolder) {
        TBSCertList tBSCertList = x509CRLHolder.toASN1Structure().getTBSCertList();
        if (tBSCertList != null) {
            Enumeration enumeration = tBSCertList.getRevokedCertificateEnumeration();
            while (enumeration.hasMoreElements()) {
                this.tbsGen.addCRLEntry(ASN1Sequence.getInstance(((ASN1Encodable)enumeration.nextElement()).toASN1Primitive()));
            }
        }
        return this;
    }

    public X509v2CRLBuilder addExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean bl, ASN1Encodable aSN1Encodable) throws CertIOException {
        CertUtils.addExtension(this.extGenerator, aSN1ObjectIdentifier, bl, aSN1Encodable);
        return this;
    }

    public X509v2CRLBuilder addExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean bl, byte[] byArray) throws CertIOException {
        this.extGenerator.addExtension(aSN1ObjectIdentifier, bl, byArray);
        return this;
    }

    public X509v2CRLBuilder addExtension(Extension extension) throws CertIOException {
        this.extGenerator.addExtension(extension);
        return this;
    }

    public X509CRLHolder build(ContentSigner contentSigner) {
        this.tbsGen.setSignature(contentSigner.getAlgorithmIdentifier());
        if (!this.extGenerator.isEmpty()) {
            this.tbsGen.setExtensions(this.extGenerator.generate());
        }
        return CertUtils.generateFullCRL(contentSigner, this.tbsGen.generateTBSCertList());
    }
}

