/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.jcajce.provider.xmss;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.pqc.crypto.xmss.XMSSKeyGenerationParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSKeyPairGenerator;
import org.bouncycastle.pqc.crypto.xmss.XMSSParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSPublicKeyParameters;
import org.bouncycastle.pqc.jcajce.provider.xmss.BCXMSSPrivateKey;
import org.bouncycastle.pqc.jcajce.provider.xmss.BCXMSSPublicKey;
import org.bouncycastle.pqc.jcajce.spec.XMSSParameterSpec;

public class XMSSKeyPairGeneratorSpi
extends KeyPairGenerator {
    private XMSSKeyGenerationParameters param;
    private ASN1ObjectIdentifier treeDigest;
    private XMSSKeyPairGenerator engine = new XMSSKeyPairGenerator();
    private SecureRandom random = new SecureRandom();
    private boolean initialised = false;

    public XMSSKeyPairGeneratorSpi() {
        super("XMSS");
    }

    public void initialize(int n, SecureRandom secureRandom) {
        throw new IllegalArgumentException("use AlgorithmParameterSpec");
    }

    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        if (!(algorithmParameterSpec instanceof XMSSParameterSpec)) {
            throw new InvalidAlgorithmParameterException("parameter object not a XMSSParameterSpec");
        }
        XMSSParameterSpec xMSSParameterSpec = (XMSSParameterSpec)algorithmParameterSpec;
        if (xMSSParameterSpec.getTreeDigest().equals("SHA256")) {
            this.treeDigest = NISTObjectIdentifiers.id_sha256;
            this.param = new XMSSKeyGenerationParameters(new XMSSParameters(xMSSParameterSpec.getHeight(), new SHA256Digest()), secureRandom);
        } else if (xMSSParameterSpec.getTreeDigest().equals("SHA512")) {
            this.treeDigest = NISTObjectIdentifiers.id_sha512;
            this.param = new XMSSKeyGenerationParameters(new XMSSParameters(xMSSParameterSpec.getHeight(), new SHA512Digest()), secureRandom);
        } else if (xMSSParameterSpec.getTreeDigest().equals("SHAKE128")) {
            this.treeDigest = NISTObjectIdentifiers.id_shake128;
            this.param = new XMSSKeyGenerationParameters(new XMSSParameters(xMSSParameterSpec.getHeight(), new SHAKEDigest(128)), secureRandom);
        } else if (xMSSParameterSpec.getTreeDigest().equals("SHAKE256")) {
            this.treeDigest = NISTObjectIdentifiers.id_shake256;
            this.param = new XMSSKeyGenerationParameters(new XMSSParameters(xMSSParameterSpec.getHeight(), new SHAKEDigest(256)), secureRandom);
        }
        this.engine.init(this.param);
        this.initialised = true;
    }

    public KeyPair generateKeyPair() {
        if (!this.initialised) {
            this.param = new XMSSKeyGenerationParameters(new XMSSParameters(10, new SHA512Digest()), this.random);
            this.engine.init(this.param);
            this.initialised = true;
        }
        AsymmetricCipherKeyPair asymmetricCipherKeyPair = this.engine.generateKeyPair();
        XMSSPublicKeyParameters xMSSPublicKeyParameters = (XMSSPublicKeyParameters)asymmetricCipherKeyPair.getPublic();
        XMSSPrivateKeyParameters xMSSPrivateKeyParameters = (XMSSPrivateKeyParameters)asymmetricCipherKeyPair.getPrivate();
        return new KeyPair(new BCXMSSPublicKey(this.treeDigest, xMSSPublicKeyParameters), new BCXMSSPrivateKey(this.treeDigest, xMSSPrivateKeyParameters));
    }
}

