/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.BEROctetString;
import org.bouncycastle.asn1.BERSet;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.cms.AuthenticatedData;
import org.bouncycastle.asn1.cms.CMSObjectIdentifiers;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSAuthenticatedData;
import org.bouncycastle.cms.CMSAuthenticatedGenerator;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSTypedData;
import org.bouncycastle.cms.DefaultAuthenticatedAttributeTableGenerator;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.MacCalculator;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.util.io.TeeOutputStream;

public class CMSAuthenticatedDataGenerator
extends CMSAuthenticatedGenerator {
    public CMSAuthenticatedData generate(CMSTypedData cMSTypedData, MacCalculator macCalculator) throws CMSException {
        return this.generate(cMSTypedData, macCalculator, null);
    }

    public CMSAuthenticatedData generate(CMSTypedData cMSTypedData, MacCalculator macCalculator, final DigestCalculator digestCalculator) throws CMSException {
        Object object;
        Object object22;
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (Object object22 : this.recipientInfoGenerators) {
            aSN1EncodableVector.add(object22.generate(macCalculator.getKey()));
        }
        if (digestCalculator != null) {
            DEROctetString dEROctetString;
            Object object3;
            BEROctetString bEROctetString;
            Object object4;
            try {
                object22 = new ByteArrayOutputStream();
                object4 = new TeeOutputStream(digestCalculator.getOutputStream(), (OutputStream)object22);
                cMSTypedData.write((OutputStream)object4);
                ((OutputStream)object4).close();
                bEROctetString = new BEROctetString(((ByteArrayOutputStream)object22).toByteArray());
            } catch (IOException iOException) {
                throw new CMSException("unable to perform digest calculation: " + iOException.getMessage(), iOException);
            }
            object22 = this.getBaseParameters(cMSTypedData.getContentType(), digestCalculator.getAlgorithmIdentifier(), macCalculator.getAlgorithmIdentifier(), digestCalculator.getDigest());
            if (this.authGen == null) {
                this.authGen = new DefaultAuthenticatedAttributeTableGenerator();
            }
            object4 = new DERSet(this.authGen.getAttributes(Collections.unmodifiableMap(object22)).toASN1EncodableVector());
            try {
                object3 = macCalculator.getOutputStream();
                ((OutputStream)object3).write(((ASN1Object)object4).getEncoded("DER"));
                ((OutputStream)object3).close();
                dEROctetString = new DEROctetString(macCalculator.getMac());
            } catch (IOException iOException) {
                throw new CMSException("exception decoding algorithm parameters.", iOException);
            }
            object3 = this.unauthGen != null ? new BERSet(this.unauthGen.getAttributes(Collections.unmodifiableMap(object22)).toASN1EncodableVector()) : null;
            ContentInfo contentInfo = new ContentInfo(CMSObjectIdentifiers.data, bEROctetString);
            object = new AuthenticatedData(this.originatorInfo, new DERSet(aSN1EncodableVector), macCalculator.getAlgorithmIdentifier(), digestCalculator.getAlgorithmIdentifier(), contentInfo, (ASN1Set)object4, dEROctetString, (ASN1Set)object3);
        } else {
            DEROctetString dEROctetString;
            BEROctetString bEROctetString;
            Object object5;
            try {
                object22 = new ByteArrayOutputStream();
                object5 = new TeeOutputStream((OutputStream)object22, macCalculator.getOutputStream());
                cMSTypedData.write((OutputStream)object5);
                ((OutputStream)object5).close();
                bEROctetString = new BEROctetString(((ByteArrayOutputStream)object22).toByteArray());
                dEROctetString = new DEROctetString(macCalculator.getMac());
            } catch (IOException iOException) {
                throw new CMSException("exception decoding algorithm parameters.", iOException);
            }
            object22 = this.unauthGen != null ? new BERSet(this.unauthGen.getAttributes(new HashMap()).toASN1EncodableVector()) : null;
            object5 = new ContentInfo(CMSObjectIdentifiers.data, bEROctetString);
            object = new AuthenticatedData(this.originatorInfo, new DERSet(aSN1EncodableVector), macCalculator.getAlgorithmIdentifier(), null, (ContentInfo)object5, null, dEROctetString, (ASN1Set)object22);
        }
        object22 = new ContentInfo(CMSObjectIdentifiers.authenticatedData, (ASN1Encodable)object);
        return new CMSAuthenticatedData((ContentInfo)object22, new DigestCalculatorProvider(){

            public DigestCalculator get(AlgorithmIdentifier algorithmIdentifier) throws OperatorCreationException {
                return digestCalculator;
            }
        });
    }
}

