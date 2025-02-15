/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.tsp.cms;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1String;
import org.bouncycastle.asn1.cms.Attributes;
import org.bouncycastle.asn1.cms.MetaData;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.operator.DigestCalculator;

class MetaDataUtil {
    private final MetaData metaData;

    MetaDataUtil(MetaData metaData) {
        this.metaData = metaData;
    }

    void initialiseMessageImprintDigestCalculator(DigestCalculator digestCalculator) throws CMSException {
        if (this.metaData != null && this.metaData.isHashProtected()) {
            try {
                digestCalculator.getOutputStream().write(this.metaData.getEncoded("DER"));
            } catch (IOException iOException) {
                throw new CMSException("unable to initialise calculator from metaData: " + iOException.getMessage(), iOException);
            }
        }
    }

    String getFileName() {
        if (this.metaData != null) {
            return this.convertString(this.metaData.getFileName());
        }
        return null;
    }

    String getMediaType() {
        if (this.metaData != null) {
            return this.convertString(this.metaData.getMediaType());
        }
        return null;
    }

    Attributes getOtherMetaData() {
        if (this.metaData != null) {
            return this.metaData.getOtherMetaData();
        }
        return null;
    }

    private String convertString(ASN1String aSN1String) {
        if (aSN1String != null) {
            return aSN1String.toString();
        }
        return null;
    }
}

