/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.eac;

import java.io.OutputStream;
import org.bouncycastle.asn1.DERApplicationSpecific;
import org.bouncycastle.asn1.eac.CVCertificate;
import org.bouncycastle.asn1.eac.CertificateBody;
import org.bouncycastle.asn1.eac.CertificateHolderAuthorization;
import org.bouncycastle.asn1.eac.CertificateHolderReference;
import org.bouncycastle.asn1.eac.CertificationAuthorityReference;
import org.bouncycastle.asn1.eac.PackedDate;
import org.bouncycastle.asn1.eac.PublicKeyDataObject;
import org.bouncycastle.eac.EACCertificateHolder;
import org.bouncycastle.eac.EACException;
import org.bouncycastle.eac.operator.EACSigner;

public class EACCertificateBuilder {
    private static final byte[] ZeroArray = new byte[]{0};
    private PublicKeyDataObject publicKey;
    private CertificateHolderAuthorization certificateHolderAuthorization;
    private PackedDate certificateEffectiveDate;
    private PackedDate certificateExpirationDate;
    private CertificateHolderReference certificateHolderReference;
    private CertificationAuthorityReference certificationAuthorityReference;

    public EACCertificateBuilder(CertificationAuthorityReference certificationAuthorityReference, PublicKeyDataObject publicKeyDataObject, CertificateHolderReference certificateHolderReference, CertificateHolderAuthorization certificateHolderAuthorization, PackedDate packedDate, PackedDate packedDate2) {
        this.certificationAuthorityReference = certificationAuthorityReference;
        this.publicKey = publicKeyDataObject;
        this.certificateHolderReference = certificateHolderReference;
        this.certificateHolderAuthorization = certificateHolderAuthorization;
        this.certificateEffectiveDate = packedDate;
        this.certificateExpirationDate = packedDate2;
    }

    private CertificateBody buildBody() {
        DERApplicationSpecific dERApplicationSpecific = new DERApplicationSpecific(41, ZeroArray);
        CertificateBody certificateBody = new CertificateBody(dERApplicationSpecific, this.certificationAuthorityReference, this.publicKey, this.certificateHolderReference, this.certificateHolderAuthorization, this.certificateEffectiveDate, this.certificateExpirationDate);
        return certificateBody;
    }

    public EACCertificateHolder build(EACSigner eACSigner) throws EACException {
        try {
            CertificateBody certificateBody = this.buildBody();
            OutputStream outputStream = eACSigner.getOutputStream();
            outputStream.write(certificateBody.getEncoded("DER"));
            outputStream.close();
            return new EACCertificateHolder(new CVCertificate(certificateBody, eACSigner.getSignature()));
        } catch (Exception exception) {
            throw new EACException("unable to process signature: " + exception.getMessage(), exception);
        }
    }
}

