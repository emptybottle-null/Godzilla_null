/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.ecgost12;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.asn1.x9.X9IntegerConverter;
import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.agreement.ECVKOAgreement;
import org.bouncycastle.crypto.digests.GOST3411_2012_256Digest;
import org.bouncycastle.crypto.digests.GOST3411_2012_512Digest;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithUKM;
import org.bouncycastle.jcajce.provider.asymmetric.ecgost12.BCECGOST3410_2012PublicKey;
import org.bouncycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi;
import org.bouncycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.bouncycastle.jcajce.spec.UserKeyingMaterialSpec;
import org.bouncycastle.jce.interfaces.ECPrivateKey;
import org.bouncycastle.jce.interfaces.ECPublicKey;

public class KeyAgreementSpi
extends BaseAgreementSpi {
    private static final X9IntegerConverter converter = new X9IntegerConverter();
    private String kaAlgorithm;
    private ECDomainParameters parameters;
    private ECVKOAgreement agreement;
    private byte[] result;

    protected KeyAgreementSpi(String string, ECVKOAgreement eCVKOAgreement, DerivationFunction derivationFunction) {
        super(string, derivationFunction);
        this.kaAlgorithm = string;
        this.agreement = eCVKOAgreement;
    }

    protected Key engineDoPhase(Key key, boolean bl) throws InvalidKeyException, IllegalStateException {
        if (this.parameters == null) {
            throw new IllegalStateException(this.kaAlgorithm + " not initialised.");
        }
        if (!bl) {
            throw new IllegalStateException(this.kaAlgorithm + " can only be between two parties.");
        }
        if (!(key instanceof PublicKey)) {
            throw new InvalidKeyException(this.kaAlgorithm + " key agreement requires " + KeyAgreementSpi.getSimpleName(ECPublicKey.class) + " for doPhase");
        }
        AsymmetricKeyParameter asymmetricKeyParameter = KeyAgreementSpi.generatePublicKeyParameter((PublicKey)key);
        try {
            this.result = this.agreement.calculateAgreement(asymmetricKeyParameter);
        } catch (Exception exception) {
            throw new InvalidKeyException("calculation failed: " + exception.getMessage()){

                public Throwable getCause() {
                    return exception;
                }
            };
        }
        return null;
    }

    protected void engineInit(Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        if (algorithmParameterSpec != null && !(algorithmParameterSpec instanceof UserKeyingMaterialSpec)) {
            throw new InvalidAlgorithmParameterException("No algorithm parameters supported");
        }
        this.initFromKey(key, algorithmParameterSpec);
    }

    protected void engineInit(Key key, SecureRandom secureRandom) throws InvalidKeyException {
        this.initFromKey(key, null);
    }

    private void initFromKey(Key key, AlgorithmParameterSpec algorithmParameterSpec) throws InvalidKeyException {
        if (!(key instanceof PrivateKey)) {
            throw new InvalidKeyException(this.kaAlgorithm + " key agreement requires " + KeyAgreementSpi.getSimpleName(ECPrivateKey.class) + " for initialisation");
        }
        ECPrivateKeyParameters eCPrivateKeyParameters = (ECPrivateKeyParameters)ECUtil.generatePrivateKeyParameter((PrivateKey)key);
        this.parameters = eCPrivateKeyParameters.getParameters();
        this.ukmParameters = algorithmParameterSpec instanceof UserKeyingMaterialSpec ? ((UserKeyingMaterialSpec)algorithmParameterSpec).getUserKeyingMaterial() : null;
        this.agreement.init(new ParametersWithUKM(eCPrivateKeyParameters, this.ukmParameters));
    }

    private static String getSimpleName(Class clazz) {
        String string = clazz.getName();
        return string.substring(string.lastIndexOf(46) + 1);
    }

    static AsymmetricKeyParameter generatePublicKeyParameter(PublicKey publicKey) throws InvalidKeyException {
        return publicKey instanceof BCECGOST3410_2012PublicKey ? ((BCECGOST3410_2012PublicKey)publicKey).engineGetKeyParameters() : ECUtil.generatePublicKeyParameter(publicKey);
    }

    protected byte[] calcSecret() {
        return this.result;
    }

    public static class ECVKO256
    extends KeyAgreementSpi {
        public ECVKO256() {
            super("ECGOST3410-2012-256", new ECVKOAgreement(new GOST3411_2012_256Digest()), null);
        }
    }

    public static class ECVKO512
    extends KeyAgreementSpi {
        public ECVKO512() {
            super("ECGOST3410-2012-512", new ECVKOAgreement(new GOST3411_2012_512Digest()), null);
        }
    }
}

