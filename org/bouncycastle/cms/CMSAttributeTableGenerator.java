/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import java.util.Map;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.cms.CMSAttributeTableGenerationException;

public interface CMSAttributeTableGenerator {
    public static final String CONTENT_TYPE = "contentType";
    public static final String DIGEST = "digest";
    public static final String SIGNATURE = "encryptedDigest";
    public static final String DIGEST_ALGORITHM_IDENTIFIER = "digestAlgID";
    public static final String MAC_ALGORITHM_IDENTIFIER = "macAlgID";
    public static final String SIGNATURE_ALGORITHM_IDENTIFIER = "signatureAlgID";

    public AttributeTable getAttributes(Map var1) throws CMSAttributeTableGenerationException;
}

