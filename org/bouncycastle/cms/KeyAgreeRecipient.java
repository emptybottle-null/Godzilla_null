/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.Recipient;
import org.bouncycastle.cms.RecipientOperator;

public interface KeyAgreeRecipient
extends Recipient {
    public RecipientOperator getRecipientOperator(AlgorithmIdentifier var1, AlgorithmIdentifier var2, SubjectPublicKeyInfo var3, ASN1OctetString var4, byte[] var5) throws CMSException;

    public AlgorithmIdentifier getPrivateKeyAlgorithmIdentifier();
}

