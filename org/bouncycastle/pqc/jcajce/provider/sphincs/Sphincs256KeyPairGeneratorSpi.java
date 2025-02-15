/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.jcajce.provider.sphincs;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.crypto.digests.SHA512tDigest;
import org.bouncycastle.pqc.crypto.sphincs.SPHINCS256KeyGenerationParameters;
import org.bouncycastle.pqc.crypto.sphincs.SPHINCS256KeyPairGenerator;
import org.bouncycastle.pqc.crypto.sphincs.SPHINCSPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.sphincs.SPHINCSPublicKeyParameters;
import org.bouncycastle.pqc.jcajce.provider.sphincs.BCSphincs256PrivateKey;
import org.bouncycastle.pqc.jcajce.provider.sphincs.BCSphincs256PublicKey;
import org.bouncycastle.pqc.jcajce.spec.SPHINCS256KeyGenParameterSpec;

public class Sphincs256KeyPairGeneratorSpi
extends KeyPairGenerator {
    ASN1ObjectIdentifier treeDigest = NISTObjectIdentifiers.id_sha512_256;
    SPHINCS256KeyGenerationParameters param;
    SPHINCS256KeyPairGenerator engine = new SPHINCS256KeyPairGenerator();
    SecureRandom random = new SecureRandom();
    boolean initialised = false;

    public Sphincs256KeyPairGeneratorSpi() {
        super("SPHINCS256");
    }

    public void initialize(int n, SecureRandom secureRandom) {
        throw new IllegalArgumentException("use AlgorithmParameterSpec");
    }

    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        if (!(algorithmParameterSpec instanceof SPHINCS256KeyGenParameterSpec)) {
            throw new InvalidAlgorithmParameterException("parameter object not a SPHINCS256KeyGenParameterSpec");
        }
        SPHINCS256KeyGenParameterSpec sPHINCS256KeyGenParameterSpec = (SPHINCS256KeyGenParameterSpec)algorithmParameterSpec;
        if (sPHINCS256KeyGenParameterSpec.getTreeDigest().equals("SHA512-256")) {
            this.treeDigest = NISTObjectIdentifiers.id_sha512_256;
            this.param = new SPHINCS256KeyGenerationParameters(secureRandom, new SHA512tDigest(256));
        } else if (sPHINCS256KeyGenParameterSpec.getTreeDigest().equals("SHA3-256")) {
            this.treeDigest = NISTObjectIdentifiers.id_sha3_256;
            this.param = new SPHINCS256KeyGenerationParameters(secureRandom, new SHA3Digest(256));
        }
        this.engine.init(this.param);
        this.initialised = true;
    }

    public KeyPair generateKeyPair() {
        if (!this.initialised) {
            this.param = new SPHINCS256KeyGenerationParameters(this.random, new SHA512tDigest(256));
            this.engine.init(this.param);
            this.initialised = true;
        }
        AsymmetricCipherKeyPair asymmetricCipherKeyPair = this.engine.generateKeyPair();
        SPHINCSPublicKeyParameters sPHINCSPublicKeyParameters = (SPHINCSPublicKeyParameters)asymmetricCipherKeyPair.getPublic();
        SPHINCSPrivateKeyParameters sPHINCSPrivateKeyParameters = (SPHINCSPrivateKeyParameters)asymmetricCipherKeyPair.getPrivate();
        return new KeyPair(new BCSphincs256PublicKey(this.treeDigest, sPHINCSPublicKeyParameters), new BCSphincs256PrivateKey(this.treeDigest, sPHINCSPrivateKeyParameters));
    }
}

