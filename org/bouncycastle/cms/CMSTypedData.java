/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.cms.CMSProcessable;

public interface CMSTypedData
extends CMSProcessable {
    public ASN1ObjectIdentifier getContentType();
}

