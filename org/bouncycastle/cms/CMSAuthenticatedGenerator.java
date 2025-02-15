/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSAttributeTableGenerator;
import org.bouncycastle.cms.CMSEnvelopedGenerator;
import org.bouncycastle.util.Arrays;

public class CMSAuthenticatedGenerator
extends CMSEnvelopedGenerator {
    protected CMSAttributeTableGenerator authGen;
    protected CMSAttributeTableGenerator unauthGen;

    public void setAuthenticatedAttributeGenerator(CMSAttributeTableGenerator cMSAttributeTableGenerator) {
        this.authGen = cMSAttributeTableGenerator;
    }

    public void setUnauthenticatedAttributeGenerator(CMSAttributeTableGenerator cMSAttributeTableGenerator) {
        this.unauthGen = cMSAttributeTableGenerator;
    }

    protected Map getBaseParameters(ASN1ObjectIdentifier aSN1ObjectIdentifier, AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, byte[] byArray) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("contentType", aSN1ObjectIdentifier);
        hashMap.put("digestAlgID", algorithmIdentifier);
        hashMap.put("digest", Arrays.clone(byArray));
        hashMap.put("macAlgID", algorithmIdentifier2);
        return hashMap;
    }
}

