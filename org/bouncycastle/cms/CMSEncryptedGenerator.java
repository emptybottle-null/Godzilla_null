/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import org.bouncycastle.cms.CMSAttributeTableGenerator;

public class CMSEncryptedGenerator {
    protected CMSAttributeTableGenerator unprotectedAttributeGenerator = null;

    protected CMSEncryptedGenerator() {
    }

    public void setUnprotectedAttributeGenerator(CMSAttributeTableGenerator cMSAttributeTableGenerator) {
        this.unprotectedAttributeGenerator = cMSAttributeTableGenerator;
    }
}

