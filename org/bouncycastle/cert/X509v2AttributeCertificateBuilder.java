/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert;

import java.math.BigInteger;
import java.util.Date;
import java.util.Locale;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.x509.AttCertIssuer;
import org.bouncycastle.asn1.x509.Attribute;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.ExtensionsGenerator;
import org.bouncycastle.asn1.x509.V2AttributeCertificateInfoGenerator;
import org.bouncycastle.cert.AttributeCertificateHolder;
import org.bouncycastle.cert.AttributeCertificateIssuer;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.cert.CertUtils;
import org.bouncycastle.cert.X509AttributeCertificateHolder;
import org.bouncycastle.operator.ContentSigner;

public class X509v2AttributeCertificateBuilder {
    private V2AttributeCertificateInfoGenerator acInfoGen = new V2AttributeCertificateInfoGenerator();
    private ExtensionsGenerator extGenerator = new ExtensionsGenerator();

    public X509v2AttributeCertificateBuilder(AttributeCertificateHolder attributeCertificateHolder, AttributeCertificateIssuer attributeCertificateIssuer, BigInteger bigInteger, Date date, Date date2) {
        this.acInfoGen.setHolder(attributeCertificateHolder.holder);
        this.acInfoGen.setIssuer(AttCertIssuer.getInstance(attributeCertificateIssuer.form));
        this.acInfoGen.setSerialNumber(new ASN1Integer(bigInteger));
        this.acInfoGen.setStartDate(new ASN1GeneralizedTime(date));
        this.acInfoGen.setEndDate(new ASN1GeneralizedTime(date2));
    }

    public X509v2AttributeCertificateBuilder(AttributeCertificateHolder attributeCertificateHolder, AttributeCertificateIssuer attributeCertificateIssuer, BigInteger bigInteger, Date date, Date date2, Locale locale) {
        this.acInfoGen.setHolder(attributeCertificateHolder.holder);
        this.acInfoGen.setIssuer(AttCertIssuer.getInstance(attributeCertificateIssuer.form));
        this.acInfoGen.setSerialNumber(new ASN1Integer(bigInteger));
        this.acInfoGen.setStartDate(new ASN1GeneralizedTime(date, locale));
        this.acInfoGen.setEndDate(new ASN1GeneralizedTime(date2, locale));
    }

    public X509v2AttributeCertificateBuilder addAttribute(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.acInfoGen.addAttribute(new Attribute(aSN1ObjectIdentifier, new DERSet(aSN1Encodable)));
        return this;
    }

    public X509v2AttributeCertificateBuilder addAttribute(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable[] aSN1EncodableArray) {
        this.acInfoGen.addAttribute(new Attribute(aSN1ObjectIdentifier, new DERSet(aSN1EncodableArray)));
        return this;
    }

    public void setIssuerUniqueId(boolean[] blArray) {
        this.acInfoGen.setIssuerUniqueID(CertUtils.booleanToBitString(blArray));
    }

    public X509v2AttributeCertificateBuilder addExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean bl, ASN1Encodable aSN1Encodable) throws CertIOException {
        CertUtils.addExtension(this.extGenerator, aSN1ObjectIdentifier, bl, aSN1Encodable);
        return this;
    }

    public X509v2AttributeCertificateBuilder addExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean bl, byte[] byArray) throws CertIOException {
        this.extGenerator.addExtension(aSN1ObjectIdentifier, bl, byArray);
        return this;
    }

    public X509v2AttributeCertificateBuilder addExtension(Extension extension) throws CertIOException {
        this.extGenerator.addExtension(extension);
        return this;
    }

    public X509AttributeCertificateHolder build(ContentSigner contentSigner) {
        this.acInfoGen.setSignature(contentSigner.getAlgorithmIdentifier());
        if (!this.extGenerator.isEmpty()) {
            this.acInfoGen.setExtensions(this.extGenerator.generate());
        }
        return CertUtils.generateFullAttrCert(contentSigner, this.acInfoGen.generateAttributeCertificateInfo());
    }
}

