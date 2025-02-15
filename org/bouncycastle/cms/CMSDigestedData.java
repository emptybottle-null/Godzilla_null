/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.cms.DigestedData;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSProcessable;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSUtils;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Encodable;

public class CMSDigestedData
implements Encodable {
    private ContentInfo contentInfo;
    private DigestedData digestedData;

    public CMSDigestedData(byte[] byArray) throws CMSException {
        this(CMSUtils.readContentInfo(byArray));
    }

    public CMSDigestedData(InputStream inputStream) throws CMSException {
        this(CMSUtils.readContentInfo(inputStream));
    }

    public CMSDigestedData(ContentInfo contentInfo) throws CMSException {
        this.contentInfo = contentInfo;
        try {
            this.digestedData = DigestedData.getInstance(contentInfo.getContent());
        } catch (ClassCastException classCastException) {
            throw new CMSException("Malformed content.", classCastException);
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new CMSException("Malformed content.", illegalArgumentException);
        }
    }

    public ASN1ObjectIdentifier getContentType() {
        return this.contentInfo.getContentType();
    }

    public AlgorithmIdentifier getDigestAlgorithm() {
        return this.digestedData.getDigestAlgorithm();
    }

    public CMSProcessable getDigestedContent() throws CMSException {
        ContentInfo contentInfo = this.digestedData.getEncapContentInfo();
        try {
            return new CMSProcessableByteArray(contentInfo.getContentType(), ((ASN1OctetString)contentInfo.getContent()).getOctets());
        } catch (Exception exception) {
            throw new CMSException("exception reading digested stream.", exception);
        }
    }

    public ContentInfo toASN1Structure() {
        return this.contentInfo;
    }

    public byte[] getEncoded() throws IOException {
        return this.contentInfo.getEncoded();
    }

    public boolean verify(DigestCalculatorProvider digestCalculatorProvider) throws CMSException {
        try {
            ContentInfo contentInfo = this.digestedData.getEncapContentInfo();
            DigestCalculator digestCalculator = digestCalculatorProvider.get(this.digestedData.getDigestAlgorithm());
            OutputStream outputStream = digestCalculator.getOutputStream();
            outputStream.write(((ASN1OctetString)contentInfo.getContent()).getOctets());
            return Arrays.areEqual(this.digestedData.getDigest(), digestCalculator.getDigest());
        } catch (OperatorCreationException operatorCreationException) {
            throw new CMSException("unable to create digest calculator: " + operatorCreationException.getMessage(), operatorCreationException);
        } catch (IOException iOException) {
            throw new CMSException("unable process content: " + iOException.getMessage(), iOException);
        }
    }
}

