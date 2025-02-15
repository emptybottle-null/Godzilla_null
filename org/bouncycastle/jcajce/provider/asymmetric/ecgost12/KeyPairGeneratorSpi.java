/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.ecgost12;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECParameterSpec;
import org.bouncycastle.asn1.cryptopro.ECGOST3410NamedCurves;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.ecgost12.BCECGOST3410_2012PrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ecgost12.BCECGOST3410_2012PublicKey;
import org.bouncycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveGenParameterSpec;
import org.bouncycastle.jce.spec.ECNamedCurveSpec;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;

public class KeyPairGeneratorSpi
extends KeyPairGenerator {
    Object ecParams = null;
    ECKeyPairGenerator engine = new ECKeyPairGenerator();
    String algorithm = "ECGOST3410-2012";
    ECKeyGenerationParameters param;
    int strength = 239;
    SecureRandom random = null;
    boolean initialised = false;

    public KeyPairGeneratorSpi() {
        super("ECGOST3410-2012");
    }

    public void initialize(int n, SecureRandom secureRandom) {
        this.strength = n;
        this.random = secureRandom;
        if (this.ecParams != null) {
            try {
                this.initialize((ECGenParameterSpec)this.ecParams, secureRandom);
            } catch (InvalidAlgorithmParameterException invalidAlgorithmParameterException) {
                throw new InvalidParameterException("key size not configurable.");
            }
        } else {
            throw new InvalidParameterException("unknown key size.");
        }
    }

    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        if (algorithmParameterSpec instanceof org.bouncycastle.jce.spec.ECParameterSpec) {
            org.bouncycastle.jce.spec.ECParameterSpec eCParameterSpec = (org.bouncycastle.jce.spec.ECParameterSpec)algorithmParameterSpec;
            this.ecParams = algorithmParameterSpec;
            this.param = new ECKeyGenerationParameters(new ECDomainParameters(eCParameterSpec.getCurve(), eCParameterSpec.getG(), eCParameterSpec.getN(), eCParameterSpec.getH()), secureRandom);
            this.engine.init(this.param);
            this.initialised = true;
        } else if (algorithmParameterSpec instanceof ECParameterSpec) {
            ECParameterSpec eCParameterSpec = (ECParameterSpec)algorithmParameterSpec;
            this.ecParams = algorithmParameterSpec;
            ECCurve eCCurve = EC5Util.convertCurve(eCParameterSpec.getCurve());
            ECPoint eCPoint = EC5Util.convertPoint(eCCurve, eCParameterSpec.getGenerator(), false);
            this.param = new ECKeyGenerationParameters(new ECDomainParameters(eCCurve, eCPoint, eCParameterSpec.getOrder(), BigInteger.valueOf(eCParameterSpec.getCofactor())), secureRandom);
            this.engine.init(this.param);
            this.initialised = true;
        } else if (algorithmParameterSpec instanceof ECGenParameterSpec || algorithmParameterSpec instanceof ECNamedCurveGenParameterSpec) {
            String string = algorithmParameterSpec instanceof ECGenParameterSpec ? ((ECGenParameterSpec)algorithmParameterSpec).getName() : ((ECNamedCurveGenParameterSpec)algorithmParameterSpec).getName();
            ECDomainParameters eCDomainParameters = ECGOST3410NamedCurves.getByName(string);
            if (eCDomainParameters == null) {
                throw new InvalidAlgorithmParameterException("unknown curve name: " + string);
            }
            this.ecParams = new ECNamedCurveSpec(string, eCDomainParameters.getCurve(), eCDomainParameters.getG(), eCDomainParameters.getN(), eCDomainParameters.getH(), eCDomainParameters.getSeed());
            ECParameterSpec eCParameterSpec = (ECParameterSpec)this.ecParams;
            ECCurve eCCurve = EC5Util.convertCurve(eCParameterSpec.getCurve());
            ECPoint eCPoint = EC5Util.convertPoint(eCCurve, eCParameterSpec.getGenerator(), false);
            this.param = new ECKeyGenerationParameters(new ECDomainParameters(eCCurve, eCPoint, eCParameterSpec.getOrder(), BigInteger.valueOf(eCParameterSpec.getCofactor())), secureRandom);
            this.engine.init(this.param);
            this.initialised = true;
        } else if (algorithmParameterSpec == null && BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa() != null) {
            org.bouncycastle.jce.spec.ECParameterSpec eCParameterSpec = BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa();
            this.ecParams = algorithmParameterSpec;
            this.param = new ECKeyGenerationParameters(new ECDomainParameters(eCParameterSpec.getCurve(), eCParameterSpec.getG(), eCParameterSpec.getN(), eCParameterSpec.getH()), secureRandom);
            this.engine.init(this.param);
            this.initialised = true;
        } else {
            if (algorithmParameterSpec == null && BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa() == null) {
                throw new InvalidAlgorithmParameterException("null parameter passed but no implicitCA set");
            }
            throw new InvalidAlgorithmParameterException("parameter object not a ECParameterSpec: " + algorithmParameterSpec.getClass().getName());
        }
    }

    public KeyPair generateKeyPair() {
        if (!this.initialised) {
            throw new IllegalStateException("EC Key Pair Generator not initialised");
        }
        AsymmetricCipherKeyPair asymmetricCipherKeyPair = this.engine.generateKeyPair();
        ECPublicKeyParameters eCPublicKeyParameters = (ECPublicKeyParameters)asymmetricCipherKeyPair.getPublic();
        ECPrivateKeyParameters eCPrivateKeyParameters = (ECPrivateKeyParameters)asymmetricCipherKeyPair.getPrivate();
        if (this.ecParams instanceof org.bouncycastle.jce.spec.ECParameterSpec) {
            org.bouncycastle.jce.spec.ECParameterSpec eCParameterSpec = (org.bouncycastle.jce.spec.ECParameterSpec)this.ecParams;
            BCECGOST3410_2012PublicKey bCECGOST3410_2012PublicKey = new BCECGOST3410_2012PublicKey(this.algorithm, eCPublicKeyParameters, eCParameterSpec);
            return new KeyPair(bCECGOST3410_2012PublicKey, new BCECGOST3410_2012PrivateKey(this.algorithm, eCPrivateKeyParameters, bCECGOST3410_2012PublicKey, eCParameterSpec));
        }
        if (this.ecParams == null) {
            return new KeyPair(new BCECGOST3410_2012PublicKey(this.algorithm, eCPublicKeyParameters), new BCECGOST3410_2012PrivateKey(this.algorithm, eCPrivateKeyParameters));
        }
        ECParameterSpec eCParameterSpec = (ECParameterSpec)this.ecParams;
        BCECGOST3410_2012PublicKey bCECGOST3410_2012PublicKey = new BCECGOST3410_2012PublicKey(this.algorithm, eCPublicKeyParameters, eCParameterSpec);
        return new KeyPair(bCECGOST3410_2012PublicKey, new BCECGOST3410_2012PrivateKey(this.algorithm, eCPrivateKeyParameters, bCECGOST3410_2012PublicKey, eCParameterSpec));
    }
}

