/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import java.util.Map;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.cms.CMSAttributeTableGenerator;

public class SimpleAttributeTableGenerator
implements CMSAttributeTableGenerator {
    private final AttributeTable attributes;

    public SimpleAttributeTableGenerator(AttributeTable attributeTable) {
        this.attributes = attributeTable;
    }

    public AttributeTable getAttributes(Map map) {
        return this.attributes;
    }
}

