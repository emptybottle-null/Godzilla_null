/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.tsp.cms;

import java.net.URI;
import org.bouncycastle.asn1.ASN1Boolean;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.asn1.cms.Attributes;
import org.bouncycastle.asn1.cms.MetaData;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.tsp.cms.MetaDataUtil;

public class CMSTimeStampedGenerator {
    protected MetaData metaData;
    protected URI dataUri;

    public void setDataUri(URI uRI) {
        this.dataUri = uRI;
    }

    public void setMetaData(boolean bl, String string, String string2) {
        this.setMetaData(bl, string, string2, null);
    }

    public void setMetaData(boolean bl, String string, String string2, Attributes attributes) {
        DERUTF8String dERUTF8String = null;
        if (string != null) {
            dERUTF8String = new DERUTF8String(string);
        }
        DERIA5String dERIA5String = null;
        if (string2 != null) {
            dERIA5String = new DERIA5String(string2);
        }
        this.setMetaData(bl, dERUTF8String, dERIA5String, attributes);
    }

    private void setMetaData(boolean bl, DERUTF8String dERUTF8String, DERIA5String dERIA5String, Attributes attributes) {
        this.metaData = new MetaData(ASN1Boolean.getInstance(bl), dERUTF8String, dERIA5String, attributes);
    }

    public void initialiseMessageImprintDigestCalculator(DigestCalculator digestCalculator) throws CMSException {
        MetaDataUtil metaDataUtil = new MetaDataUtil(this.metaData);
        metaDataUtil.initialiseMessageImprintDigestCalculator(digestCalculator);
    }
}

