/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import org.bouncycastle.cms.SignerId;
import org.bouncycastle.cms.SignerInformationVerifier;
import org.bouncycastle.operator.OperatorCreationException;

public interface SignerInformationVerifierProvider {
    public SignerInformationVerifier get(SignerId var1) throws OperatorCreationException;
}

