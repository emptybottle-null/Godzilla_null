/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.Recipient;
import org.bouncycastle.cms.RecipientOperator;

public interface KEKRecipient
extends Recipient {
    public RecipientOperator getRecipientOperator(AlgorithmIdentifier var1, AlgorithmIdentifier var2, byte[] var3) throws CMSException;
}

