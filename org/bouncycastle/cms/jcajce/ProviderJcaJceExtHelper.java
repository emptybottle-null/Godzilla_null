/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms.jcajce;

import java.security.PrivateKey;
import java.security.Provider;
import javax.crypto.SecretKey;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.jcajce.JcaJceExtHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.SymmetricKeyUnwrapper;
import org.bouncycastle.operator.jcajce.JceAsymmetricKeyUnwrapper;
import org.bouncycastle.operator.jcajce.JceKTSKeyUnwrapper;
import org.bouncycastle.operator.jcajce.JceSymmetricKeyUnwrapper;

class ProviderJcaJceExtHelper
extends ProviderJcaJceHelper
implements JcaJceExtHelper {
    public ProviderJcaJceExtHelper(Provider provider) {
        super(provider);
    }

    public JceAsymmetricKeyUnwrapper createAsymmetricUnwrapper(AlgorithmIdentifier algorithmIdentifier, PrivateKey privateKey) {
        return new JceAsymmetricKeyUnwrapper(algorithmIdentifier, privateKey).setProvider(this.provider);
    }

    public JceKTSKeyUnwrapper createAsymmetricUnwrapper(AlgorithmIdentifier algorithmIdentifier, PrivateKey privateKey, byte[] byArray, byte[] byArray2) {
        return new JceKTSKeyUnwrapper(algorithmIdentifier, privateKey, byArray, byArray2).setProvider(this.provider);
    }

    public SymmetricKeyUnwrapper createSymmetricUnwrapper(AlgorithmIdentifier algorithmIdentifier, SecretKey secretKey) {
        return new JceSymmetricKeyUnwrapper(algorithmIdentifier, secretKey).setProvider(this.provider);
    }
}

