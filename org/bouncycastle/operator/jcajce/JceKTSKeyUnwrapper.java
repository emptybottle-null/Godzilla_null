/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.operator.jcajce;

import java.security.Key;
import java.security.PrivateKey;
import java.security.Provider;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import org.bouncycastle.asn1.cms.GenericHybridParameters;
import org.bouncycastle.asn1.cms.RsaKemParameters;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.util.DEROtherInfo;
import org.bouncycastle.jcajce.spec.KTSParameterSpec;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.AsymmetricKeyUnwrapper;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OperatorException;
import org.bouncycastle.operator.jcajce.JceGenericKey;
import org.bouncycastle.operator.jcajce.OperatorHelper;
import org.bouncycastle.util.Arrays;

public class JceKTSKeyUnwrapper
extends AsymmetricKeyUnwrapper {
    private OperatorHelper helper = new OperatorHelper(new DefaultJcaJceHelper());
    private Map extraMappings = new HashMap();
    private PrivateKey privKey;
    private byte[] partyUInfo;
    private byte[] partyVInfo;

    public JceKTSKeyUnwrapper(AlgorithmIdentifier algorithmIdentifier, PrivateKey privateKey, byte[] byArray, byte[] byArray2) {
        super(algorithmIdentifier);
        this.privKey = privateKey;
        this.partyUInfo = Arrays.clone(byArray);
        this.partyVInfo = Arrays.clone(byArray2);
    }

    public JceKTSKeyUnwrapper setProvider(Provider provider) {
        this.helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        return this;
    }

    public JceKTSKeyUnwrapper setProvider(String string) {
        this.helper = new OperatorHelper(new NamedJcaJceHelper(string));
        return this;
    }

    public GenericKey generateUnwrappedKey(AlgorithmIdentifier algorithmIdentifier, byte[] byArray) throws OperatorException {
        Key key;
        GenericHybridParameters genericHybridParameters = GenericHybridParameters.getInstance(this.getAlgorithmIdentifier().getParameters());
        Cipher cipher = this.helper.createAsymmetricWrapper(this.getAlgorithmIdentifier().getAlgorithm(), this.extraMappings);
        String string = this.helper.getWrappingAlgorithmName(genericHybridParameters.getDem().getAlgorithm());
        RsaKemParameters rsaKemParameters = RsaKemParameters.getInstance(genericHybridParameters.getKem().getParameters());
        int n = rsaKemParameters.getKeyLength().intValue() * 8;
        try {
            DEROtherInfo dEROtherInfo = new DEROtherInfo.Builder(genericHybridParameters.getDem(), this.partyUInfo, this.partyVInfo).build();
            KTSParameterSpec kTSParameterSpec = new KTSParameterSpec.Builder(string, n, dEROtherInfo.getEncoded()).withKdfAlgorithm(rsaKemParameters.getKeyDerivationFunction()).build();
            cipher.init(4, (Key)this.privKey, kTSParameterSpec);
            key = cipher.unwrap(byArray, this.helper.getKeyAlgorithmName(algorithmIdentifier.getAlgorithm()), 3);
        } catch (Exception exception) {
            throw new OperatorException("Unable to unwrap contents key: " + exception.getMessage(), exception);
        }
        return new JceGenericKey(algorithmIdentifier, key);
    }
}

