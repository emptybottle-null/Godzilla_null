/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.tsp;

import java.io.IOException;
import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Boolean;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.tsp.MessageImprint;
import org.bouncycastle.asn1.tsp.TimeStampReq;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.ExtensionsGenerator;
import org.bouncycastle.tsp.TSPIOException;
import org.bouncycastle.tsp.TSPUtil;
import org.bouncycastle.tsp.TimeStampRequest;

public class TimeStampRequestGenerator {
    private ASN1ObjectIdentifier reqPolicy;
    private ASN1Boolean certReq;
    private ExtensionsGenerator extGenerator = new ExtensionsGenerator();

    public void setReqPolicy(String string) {
        this.reqPolicy = new ASN1ObjectIdentifier(string);
    }

    public void setReqPolicy(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.reqPolicy = aSN1ObjectIdentifier;
    }

    public void setCertReq(boolean bl) {
        this.certReq = ASN1Boolean.getInstance(bl);
    }

    public void addExtension(String string, boolean bl, ASN1Encodable aSN1Encodable) throws IOException {
        this.addExtension(string, bl, aSN1Encodable.toASN1Primitive().getEncoded());
    }

    public void addExtension(String string, boolean bl, byte[] byArray) {
        this.extGenerator.addExtension(new ASN1ObjectIdentifier(string), bl, byArray);
    }

    public void addExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean bl, ASN1Encodable aSN1Encodable) throws TSPIOException {
        TSPUtil.addExtension(this.extGenerator, aSN1ObjectIdentifier, bl, aSN1Encodable);
    }

    public void addExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean bl, byte[] byArray) {
        this.extGenerator.addExtension(aSN1ObjectIdentifier, bl, byArray);
    }

    public TimeStampRequest generate(String string, byte[] byArray) {
        return this.generate(string, byArray, null);
    }

    public TimeStampRequest generate(String string, byte[] byArray, BigInteger bigInteger) {
        if (string == null) {
            throw new IllegalArgumentException("No digest algorithm specified");
        }
        ASN1ObjectIdentifier aSN1ObjectIdentifier = new ASN1ObjectIdentifier(string);
        AlgorithmIdentifier algorithmIdentifier = new AlgorithmIdentifier(aSN1ObjectIdentifier, DERNull.INSTANCE);
        MessageImprint messageImprint = new MessageImprint(algorithmIdentifier, byArray);
        Extensions extensions = null;
        if (!this.extGenerator.isEmpty()) {
            extensions = this.extGenerator.generate();
        }
        if (bigInteger != null) {
            return new TimeStampRequest(new TimeStampReq(messageImprint, this.reqPolicy, new ASN1Integer(bigInteger), this.certReq, extensions));
        }
        return new TimeStampRequest(new TimeStampReq(messageImprint, this.reqPolicy, null, this.certReq, extensions));
    }

    public TimeStampRequest generate(ASN1ObjectIdentifier aSN1ObjectIdentifier, byte[] byArray) {
        return this.generate(aSN1ObjectIdentifier.getId(), byArray);
    }

    public TimeStampRequest generate(ASN1ObjectIdentifier aSN1ObjectIdentifier, byte[] byArray, BigInteger bigInteger) {
        return this.generate(aSN1ObjectIdentifier.getId(), byArray, bigInteger);
    }
}

