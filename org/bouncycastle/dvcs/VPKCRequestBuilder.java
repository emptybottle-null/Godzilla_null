/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.dvcs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bouncycastle.asn1.dvcs.CertEtcToken;
import org.bouncycastle.asn1.dvcs.DVCSRequestInformationBuilder;
import org.bouncycastle.asn1.dvcs.DVCSTime;
import org.bouncycastle.asn1.dvcs.Data;
import org.bouncycastle.asn1.dvcs.ServiceType;
import org.bouncycastle.asn1.dvcs.TargetEtcChain;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.dvcs.DVCSException;
import org.bouncycastle.dvcs.DVCSRequest;
import org.bouncycastle.dvcs.DVCSRequestBuilder;
import org.bouncycastle.dvcs.TargetChain;

public class VPKCRequestBuilder
extends DVCSRequestBuilder {
    private List chains = new ArrayList();

    public VPKCRequestBuilder() {
        super(new DVCSRequestInformationBuilder(ServiceType.VPKC));
    }

    public void addTargetChain(X509CertificateHolder x509CertificateHolder) {
        this.chains.add(new TargetEtcChain(new CertEtcToken(0, x509CertificateHolder.toASN1Structure())));
    }

    public void addTargetChain(Extension extension) {
        this.chains.add(new TargetEtcChain(new CertEtcToken(extension)));
    }

    public void addTargetChain(TargetChain targetChain) {
        this.chains.add(targetChain.toASN1Structure());
    }

    public void setRequestTime(Date date) {
        this.requestInformationBuilder.setRequestTime(new DVCSTime(date));
    }

    public DVCSRequest build() throws DVCSException {
        Data data = new Data(this.chains.toArray(new TargetEtcChain[this.chains.size()]));
        return this.createDVCRequest(data);
    }
}

