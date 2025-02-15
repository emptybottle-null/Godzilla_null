/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pkcs;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DLSequence;
import org.bouncycastle.asn1.pkcs.AuthenticatedSafe;
import org.bouncycastle.asn1.pkcs.ContentInfo;
import org.bouncycastle.asn1.pkcs.MacData;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.Pfx;
import org.bouncycastle.cms.CMSEncryptedDataGenerator;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.operator.OutputEncryptor;
import org.bouncycastle.pkcs.MacDataGenerator;
import org.bouncycastle.pkcs.PKCS12MacCalculatorBuilder;
import org.bouncycastle.pkcs.PKCS12PfxPdu;
import org.bouncycastle.pkcs.PKCS12SafeBag;
import org.bouncycastle.pkcs.PKCSException;
import org.bouncycastle.pkcs.PKCSIOException;

public class PKCS12PfxPduBuilder {
    private ASN1EncodableVector dataVector = new ASN1EncodableVector();

    public PKCS12PfxPduBuilder addData(PKCS12SafeBag pKCS12SafeBag) throws IOException {
        this.dataVector.add(new ContentInfo(PKCSObjectIdentifiers.data, new DEROctetString(new DLSequence(pKCS12SafeBag.toASN1Structure()).getEncoded())));
        return this;
    }

    public PKCS12PfxPduBuilder addEncryptedData(OutputEncryptor outputEncryptor, PKCS12SafeBag pKCS12SafeBag) throws IOException {
        return this.addEncryptedData(outputEncryptor, new DERSequence(pKCS12SafeBag.toASN1Structure()));
    }

    public PKCS12PfxPduBuilder addEncryptedData(OutputEncryptor outputEncryptor, PKCS12SafeBag[] pKCS12SafeBagArray) throws IOException {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (int i = 0; i != pKCS12SafeBagArray.length; ++i) {
            aSN1EncodableVector.add(pKCS12SafeBagArray[i].toASN1Structure());
        }
        return this.addEncryptedData(outputEncryptor, new DLSequence(aSN1EncodableVector));
    }

    private PKCS12PfxPduBuilder addEncryptedData(OutputEncryptor outputEncryptor, ASN1Sequence aSN1Sequence) throws IOException {
        CMSEncryptedDataGenerator cMSEncryptedDataGenerator = new CMSEncryptedDataGenerator();
        try {
            this.dataVector.add(cMSEncryptedDataGenerator.generate(new CMSProcessableByteArray(aSN1Sequence.getEncoded()), outputEncryptor).toASN1Structure());
        } catch (CMSException cMSException) {
            throw new PKCSIOException(cMSException.getMessage(), cMSException.getCause());
        }
        return this;
    }

    public PKCS12PfxPdu build(PKCS12MacCalculatorBuilder pKCS12MacCalculatorBuilder, char[] cArray) throws PKCSException {
        Object object;
        byte[] byArray;
        AuthenticatedSafe authenticatedSafe = AuthenticatedSafe.getInstance(new DLSequence(this.dataVector));
        try {
            byArray = authenticatedSafe.getEncoded();
        } catch (IOException iOException) {
            throw new PKCSException("unable to encode AuthenticatedSafe: " + iOException.getMessage(), iOException);
        }
        ContentInfo contentInfo = new ContentInfo(PKCSObjectIdentifiers.data, new DEROctetString(byArray));
        MacData macData = null;
        if (pKCS12MacCalculatorBuilder != null) {
            object = new MacDataGenerator(pKCS12MacCalculatorBuilder);
            macData = ((MacDataGenerator)object).build(cArray, byArray);
        }
        object = new Pfx(contentInfo, macData);
        return new PKCS12PfxPdu((Pfx)object);
    }
}

