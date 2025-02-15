/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.dvcs;

import java.io.IOException;
import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.dvcs.DVCSObjectIdentifiers;
import org.bouncycastle.asn1.dvcs.DVCSRequestInformationBuilder;
import org.bouncycastle.asn1.dvcs.Data;
import org.bouncycastle.asn1.x509.ExtensionsGenerator;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.dvcs.DVCSException;
import org.bouncycastle.dvcs.DVCSRequest;

public abstract class DVCSRequestBuilder {
    private final ExtensionsGenerator extGenerator = new ExtensionsGenerator();
    private final CMSSignedDataGenerator signedDataGen = new CMSSignedDataGenerator();
    protected final DVCSRequestInformationBuilder requestInformationBuilder;

    protected DVCSRequestBuilder(DVCSRequestInformationBuilder dVCSRequestInformationBuilder) {
        this.requestInformationBuilder = dVCSRequestInformationBuilder;
    }

    public void setNonce(BigInteger bigInteger) {
        this.requestInformationBuilder.setNonce(bigInteger);
    }

    public void setRequester(GeneralName generalName) {
        this.requestInformationBuilder.setRequester(generalName);
    }

    public void setDVCS(GeneralName generalName) {
        this.requestInformationBuilder.setDVCS(generalName);
    }

    public void setDVCS(GeneralNames generalNames) {
        this.requestInformationBuilder.setDVCS(generalNames);
    }

    public void setDataLocations(GeneralName generalName) {
        this.requestInformationBuilder.setDataLocations(generalName);
    }

    public void setDataLocations(GeneralNames generalNames) {
        this.requestInformationBuilder.setDataLocations(generalNames);
    }

    public void addExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean bl, ASN1Encodable aSN1Encodable) throws DVCSException {
        try {
            this.extGenerator.addExtension(aSN1ObjectIdentifier, bl, aSN1Encodable);
        } catch (IOException iOException) {
            throw new DVCSException("cannot encode extension: " + iOException.getMessage(), iOException);
        }
    }

    protected DVCSRequest createDVCRequest(Data data) throws DVCSException {
        if (!this.extGenerator.isEmpty()) {
            this.requestInformationBuilder.setExtensions(this.extGenerator.generate());
        }
        org.bouncycastle.asn1.dvcs.DVCSRequest dVCSRequest = new org.bouncycastle.asn1.dvcs.DVCSRequest(this.requestInformationBuilder.build(), data);
        return new DVCSRequest(new ContentInfo(DVCSObjectIdentifiers.id_ct_DVCSRequestData, dVCSRequest));
    }
}

