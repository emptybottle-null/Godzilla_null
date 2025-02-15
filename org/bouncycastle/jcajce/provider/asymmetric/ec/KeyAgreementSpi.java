/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.ec;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.asn1.x9.X9IntegerConverter;
import org.bouncycastle.crypto.BasicAgreement;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.agreement.ECDHBasicAgreement;
import org.bouncycastle.crypto.agreement.ECDHCBasicAgreement;
import org.bouncycastle.crypto.agreement.ECMQVBasicAgreement;
import org.bouncycastle.crypto.agreement.kdf.ConcatenationKDFGenerator;
import org.bouncycastle.crypto.generators.KDF2BytesGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.MQVPrivateParameters;
import org.bouncycastle.crypto.params.MQVPublicParameters;
import org.bouncycastle.crypto.util.DigestFactory;
import org.bouncycastle.jcajce.provider.asymmetric.ec.ECUtils;
import org.bouncycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi;
import org.bouncycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.bouncycastle.jcajce.spec.MQVParameterSpec;
import org.bouncycastle.jcajce.spec.UserKeyingMaterialSpec;
import org.bouncycastle.jce.interfaces.ECPrivateKey;
import org.bouncycastle.jce.interfaces.ECPublicKey;
import org.bouncycastle.jce.interfaces.MQVPrivateKey;
import org.bouncycastle.jce.interfaces.MQVPublicKey;

public class KeyAgreementSpi
extends BaseAgreementSpi {
    private static final X9IntegerConverter converter = new X9IntegerConverter();
    private String kaAlgorithm;
    private ECDomainParameters parameters;
    private BasicAgreement agreement;
    private MQVParameterSpec mqvParameters;
    private BigInteger result;

    protected KeyAgreementSpi(String string, BasicAgreement basicAgreement, DerivationFunction derivationFunction) {
        super(string, derivationFunction);
        this.kaAlgorithm = string;
        this.agreement = basicAgreement;
    }

    protected byte[] bigIntToBytes(BigInteger bigInteger) {
        return converter.integerToBytes(bigInteger, converter.getByteLength(this.parameters.getCurve()));
    }

    protected Key engineDoPhase(Key key, boolean bl) throws InvalidKeyException, IllegalStateException {
        CipherParameters cipherParameters;
        if (this.parameters == null) {
            throw new IllegalStateException(this.kaAlgorithm + " not initialised.");
        }
        if (!bl) {
            throw new IllegalStateException(this.kaAlgorithm + " can only be between two parties.");
        }
        if (this.agreement instanceof ECMQVBasicAgreement) {
            Object object;
            if (!(key instanceof MQVPublicKey)) {
                object = (ECPublicKeyParameters)ECUtils.generatePublicKeyParameter((PublicKey)key);
                ECPublicKeyParameters eCPublicKeyParameters = (ECPublicKeyParameters)ECUtils.generatePublicKeyParameter(this.mqvParameters.getOtherPartyEphemeralKey());
                cipherParameters = new MQVPublicParameters((ECPublicKeyParameters)object, eCPublicKeyParameters);
            } else {
                object = (MQVPublicKey)key;
                ECPublicKeyParameters eCPublicKeyParameters = (ECPublicKeyParameters)ECUtils.generatePublicKeyParameter(object.getStaticKey());
                ECPublicKeyParameters eCPublicKeyParameters2 = (ECPublicKeyParameters)ECUtils.generatePublicKeyParameter(object.getEphemeralKey());
                cipherParameters = new MQVPublicParameters(eCPublicKeyParameters, eCPublicKeyParameters2);
            }
        } else {
            if (!(key instanceof PublicKey)) {
                throw new InvalidKeyException(this.kaAlgorithm + " key agreement requires " + KeyAgreementSpi.getSimpleName(ECPublicKey.class) + " for doPhase");
            }
            cipherParameters = ECUtils.generatePublicKeyParameter((PublicKey)key);
        }
        try {
            this.result = this.agreement.calculateAgreement(cipherParameters);
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
        if (algorithmParameterSpec != null && !(algorithmParameterSpec instanceof MQVParameterSpec) && !(algorithmParameterSpec instanceof UserKeyingMaterialSpec)) {
            throw new InvalidAlgorithmParameterException("No algorithm parameters supported");
        }
        this.initFromKey(key, algorithmParameterSpec);
    }

    protected void engineInit(Key key, SecureRandom secureRandom) throws InvalidKeyException {
        this.initFromKey(key, null);
    }

    private void initFromKey(Key key, AlgorithmParameterSpec algorithmParameterSpec) throws InvalidKeyException {
        if (this.agreement instanceof ECMQVBasicAgreement) {
            ECPublicKeyParameters eCPublicKeyParameters;
            ECPrivateKeyParameters eCPrivateKeyParameters;
            ECPrivateKeyParameters eCPrivateKeyParameters2;
            Object object;
            this.mqvParameters = null;
            if (!(key instanceof MQVPrivateKey) && !(algorithmParameterSpec instanceof MQVParameterSpec)) {
                throw new InvalidKeyException(this.kaAlgorithm + " key agreement requires " + KeyAgreementSpi.getSimpleName(MQVParameterSpec.class) + " for initialisation");
            }
            if (key instanceof MQVPrivateKey) {
                object = (MQVPrivateKey)key;
                eCPrivateKeyParameters2 = (ECPrivateKeyParameters)ECUtil.generatePrivateKeyParameter(object.getStaticPrivateKey());
                eCPrivateKeyParameters = (ECPrivateKeyParameters)ECUtil.generatePrivateKeyParameter(object.getEphemeralPrivateKey());
                eCPublicKeyParameters = null;
                if (object.getEphemeralPublicKey() != null) {
                    eCPublicKeyParameters = (ECPublicKeyParameters)ECUtils.generatePublicKeyParameter(object.getEphemeralPublicKey());
                }
            } else {
                object = (MQVParameterSpec)algorithmParameterSpec;
                eCPrivateKeyParameters2 = (ECPrivateKeyParameters)ECUtil.generatePrivateKeyParameter((PrivateKey)key);
                eCPrivateKeyParameters = (ECPrivateKeyParameters)ECUtil.generatePrivateKeyParameter(((MQVParameterSpec)object).getEphemeralPrivateKey());
                eCPublicKeyParameters = null;
                if (((MQVParameterSpec)object).getEphemeralPublicKey() != null) {
                    eCPublicKeyParameters = (ECPublicKeyParameters)ECUtils.generatePublicKeyParameter(((MQVParameterSpec)object).getEphemeralPublicKey());
                }
                this.mqvParameters = object;
                this.ukmParameters = ((MQVParameterSpec)object).getUserKeyingMaterial();
            }
            object = new MQVPrivateParameters(eCPrivateKeyParameters2, eCPrivateKeyParameters, eCPublicKeyParameters);
            this.parameters = eCPrivateKeyParameters2.getParameters();
            this.agreement.init((CipherParameters)object);
        } else {
            if (!(key instanceof PrivateKey)) {
                throw new InvalidKeyException(this.kaAlgorithm + " key agreement requires " + KeyAgreementSpi.getSimpleName(ECPrivateKey.class) + " for initialisation");
            }
            ECPrivateKeyParameters eCPrivateKeyParameters = (ECPrivateKeyParameters)ECUtil.generatePrivateKeyParameter((PrivateKey)key);
            this.parameters = eCPrivateKeyParameters.getParameters();
            this.ukmParameters = algorithmParameterSpec instanceof UserKeyingMaterialSpec ? ((UserKeyingMaterialSpec)algorithmParameterSpec).getUserKeyingMaterial() : null;
            this.agreement.init(eCPrivateKeyParameters);
        }
    }

    private static String getSimpleName(Class clazz) {
        String string = clazz.getName();
        return string.substring(string.lastIndexOf(46) + 1);
    }

    protected byte[] calcSecret() {
        return this.bigIntToBytes(this.result);
    }

    public static class CDHwithSHA1KDFAndSharedInfo
    extends KeyAgreementSpi {
        public CDHwithSHA1KDFAndSharedInfo() {
            super("ECCDHwithSHA1KDF", new ECDHCBasicAgreement(), new KDF2BytesGenerator(DigestFactory.createSHA1()));
        }
    }

    public static class CDHwithSHA224KDFAndSharedInfo
    extends KeyAgreementSpi {
        public CDHwithSHA224KDFAndSharedInfo() {
            super("ECCDHwithSHA224KDF", new ECDHCBasicAgreement(), new KDF2BytesGenerator(DigestFactory.createSHA224()));
        }
    }

    public static class CDHwithSHA256KDFAndSharedInfo
    extends KeyAgreementSpi {
        public CDHwithSHA256KDFAndSharedInfo() {
            super("ECCDHwithSHA256KDF", new ECDHCBasicAgreement(), new KDF2BytesGenerator(DigestFactory.createSHA256()));
        }
    }

    public static class CDHwithSHA384KDFAndSharedInfo
    extends KeyAgreementSpi {
        public CDHwithSHA384KDFAndSharedInfo() {
            super("ECCDHwithSHA384KDF", new ECDHCBasicAgreement(), new KDF2BytesGenerator(DigestFactory.createSHA384()));
        }
    }

    public static class CDHwithSHA512KDFAndSharedInfo
    extends KeyAgreementSpi {
        public CDHwithSHA512KDFAndSharedInfo() {
            super("ECCDHwithSHA512KDF", new ECDHCBasicAgreement(), new KDF2BytesGenerator(DigestFactory.createSHA512()));
        }
    }

    public static class DH
    extends KeyAgreementSpi {
        public DH() {
            super("ECDH", new ECDHBasicAgreement(), null);
        }
    }

    public static class DHC
    extends KeyAgreementSpi {
        public DHC() {
            super("ECDHC", new ECDHCBasicAgreement(), null);
        }
    }

    public static class DHwithSHA1CKDF
    extends KeyAgreementSpi {
        public DHwithSHA1CKDF() {
            super("ECDHwithSHA1CKDF", new ECDHCBasicAgreement(), new ConcatenationKDFGenerator(DigestFactory.createSHA1()));
        }
    }

    public static class DHwithSHA1KDF
    extends KeyAgreementSpi {
        public DHwithSHA1KDF() {
            super("ECDHwithSHA1KDF", new ECDHBasicAgreement(), new KDF2BytesGenerator(DigestFactory.createSHA1()));
        }
    }

    public static class DHwithSHA1KDFAndSharedInfo
    extends KeyAgreementSpi {
        public DHwithSHA1KDFAndSharedInfo() {
            super("ECDHwithSHA1KDF", new ECDHBasicAgreement(), new KDF2BytesGenerator(DigestFactory.createSHA1()));
        }
    }

    public static class DHwithSHA224KDFAndSharedInfo
    extends KeyAgreementSpi {
        public DHwithSHA224KDFAndSharedInfo() {
            super("ECDHwithSHA224KDF", new ECDHBasicAgreement(), new KDF2BytesGenerator(DigestFactory.createSHA224()));
        }
    }

    public static class DHwithSHA256CKDF
    extends KeyAgreementSpi {
        public DHwithSHA256CKDF() {
            super("ECDHwithSHA256CKDF", new ECDHCBasicAgreement(), new ConcatenationKDFGenerator(DigestFactory.createSHA256()));
        }
    }

    public static class DHwithSHA256KDFAndSharedInfo
    extends KeyAgreementSpi {
        public DHwithSHA256KDFAndSharedInfo() {
            super("ECDHwithSHA256KDF", new ECDHBasicAgreement(), new KDF2BytesGenerator(DigestFactory.createSHA256()));
        }
    }

    public static class DHwithSHA384CKDF
    extends KeyAgreementSpi {
        public DHwithSHA384CKDF() {
            super("ECDHwithSHA384CKDF", new ECDHCBasicAgreement(), new ConcatenationKDFGenerator(DigestFactory.createSHA384()));
        }
    }

    public static class DHwithSHA384KDFAndSharedInfo
    extends KeyAgreementSpi {
        public DHwithSHA384KDFAndSharedInfo() {
            super("ECDHwithSHA384KDF", new ECDHBasicAgreement(), new KDF2BytesGenerator(DigestFactory.createSHA384()));
        }
    }

    public static class DHwithSHA512CKDF
    extends KeyAgreementSpi {
        public DHwithSHA512CKDF() {
            super("ECDHwithSHA512CKDF", new ECDHCBasicAgreement(), new ConcatenationKDFGenerator(DigestFactory.createSHA512()));
        }
    }

    public static class DHwithSHA512KDFAndSharedInfo
    extends KeyAgreementSpi {
        public DHwithSHA512KDFAndSharedInfo() {
            super("ECDHwithSHA512KDF", new ECDHBasicAgreement(), new KDF2BytesGenerator(DigestFactory.createSHA512()));
        }
    }

    public static class MQV
    extends KeyAgreementSpi {
        public MQV() {
            super("ECMQV", new ECMQVBasicAgreement(), null);
        }
    }

    public static class MQVwithSHA1CKDF
    extends KeyAgreementSpi {
        public MQVwithSHA1CKDF() {
            super("ECMQVwithSHA1CKDF", new ECMQVBasicAgreement(), new ConcatenationKDFGenerator(DigestFactory.createSHA1()));
        }
    }

    public static class MQVwithSHA1KDFAndSharedInfo
    extends KeyAgreementSpi {
        public MQVwithSHA1KDFAndSharedInfo() {
            super("ECMQVwithSHA1KDF", new ECMQVBasicAgreement(), new KDF2BytesGenerator(DigestFactory.createSHA1()));
        }
    }

    public static class MQVwithSHA224CKDF
    extends KeyAgreementSpi {
        public MQVwithSHA224CKDF() {
            super("ECMQVwithSHA224CKDF", new ECMQVBasicAgreement(), new ConcatenationKDFGenerator(DigestFactory.createSHA224()));
        }
    }

    public static class MQVwithSHA224KDFAndSharedInfo
    extends KeyAgreementSpi {
        public MQVwithSHA224KDFAndSharedInfo() {
            super("ECMQVwithSHA224KDF", new ECMQVBasicAgreement(), new KDF2BytesGenerator(DigestFactory.createSHA224()));
        }
    }

    public static class MQVwithSHA256CKDF
    extends KeyAgreementSpi {
        public MQVwithSHA256CKDF() {
            super("ECMQVwithSHA256CKDF", new ECMQVBasicAgreement(), new ConcatenationKDFGenerator(DigestFactory.createSHA256()));
        }
    }

    public static class MQVwithSHA256KDFAndSharedInfo
    extends KeyAgreementSpi {
        public MQVwithSHA256KDFAndSharedInfo() {
            super("ECMQVwithSHA256KDF", new ECMQVBasicAgreement(), new KDF2BytesGenerator(DigestFactory.createSHA256()));
        }
    }

    public static class MQVwithSHA384CKDF
    extends KeyAgreementSpi {
        public MQVwithSHA384CKDF() {
            super("ECMQVwithSHA384CKDF", new ECMQVBasicAgreement(), new ConcatenationKDFGenerator(DigestFactory.createSHA384()));
        }
    }

    public static class MQVwithSHA384KDFAndSharedInfo
    extends KeyAgreementSpi {
        public MQVwithSHA384KDFAndSharedInfo() {
            super("ECMQVwithSHA384KDF", new ECMQVBasicAgreement(), new KDF2BytesGenerator(DigestFactory.createSHA384()));
        }
    }

    public static class MQVwithSHA512CKDF
    extends KeyAgreementSpi {
        public MQVwithSHA512CKDF() {
            super("ECMQVwithSHA512CKDF", new ECMQVBasicAgreement(), new ConcatenationKDFGenerator(DigestFactory.createSHA512()));
        }
    }

    public static class MQVwithSHA512KDFAndSharedInfo
    extends KeyAgreementSpi {
        public MQVwithSHA512KDFAndSharedInfo() {
            super("ECMQVwithSHA512KDF", new ECMQVBasicAgreement(), new KDF2BytesGenerator(DigestFactory.createSHA512()));
        }
    }
}

