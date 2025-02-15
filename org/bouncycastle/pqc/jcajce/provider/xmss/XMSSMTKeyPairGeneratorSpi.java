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
import org.bouncycastle.pqc.crypto.xmss.XMSSMTKeyGenerationParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSMTKeyPairGenerator;
import org.bouncycastle.pqc.crypto.xmss.XMSSMTParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSMTPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSMTPublicKeyParameters;
import org.bouncycastle.pqc.jcajce.provider.xmss.BCXMSSMTPrivateKey;
import org.bouncycastle.pqc.jcajce.provider.xmss.BCXMSSMTPublicKey;
import org.bouncycastle.pqc.jcajce.spec.XMSSMTParameterSpec;

public class XMSSMTKeyPairGeneratorSpi
extends KeyPairGenerator {
    private XMSSMTKeyGenerationParameters param;
    private XMSSMTKeyPairGenerator engine = new XMSSMTKeyPairGenerator();
    private ASN1ObjectIdentifier treeDigest;
    private SecureRandom random = new SecureRandom();
    private boolean initialised = false;

    public XMSSMTKeyPairGeneratorSpi() {
        super("XMSSMT");
    }

    public void initialize(int n, SecureRandom secureRandom) {
        throw new IllegalArgumentException("use AlgorithmParameterSpec");
    }

    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        if (!(algorithmParameterSpec instanceof XMSSMTParameterSpec)) {
            throw new InvalidAlgorithmParameterException("parameter object not a XMSSMTParameterSpec");
        }
        XMSSMTParameterSpec xMSSMTParameterSpec = (XMSSMTParameterSpec)algorithmParameterSpec;
        if (xMSSMTParameterSpec.getTreeDigest().equals("SHA256")) {
            this.treeDigest = NISTObjectIdentifiers.id_sha256;
            this.param = new XMSSMTKeyGenerationParameters(new XMSSMTParameters(xMSSMTParameterSpec.getHeight(), xMSSMTParameterSpec.getLayers(), new SHA256Digest()), secureRandom);
        } else if (xMSSMTParameterSpec.getTreeDigest().equals("SHA512")) {
            this.treeDigest = NISTObjectIdentifiers.id_sha512;
            this.param = new XMSSMTKeyGenerationParameters(new XMSSMTParameters(xMSSMTParameterSpec.getHeight(), xMSSMTParameterSpec.getLayers(), new SHA512Digest()), secureRandom);
        } else if (xMSSMTParameterSpec.getTreeDigest().equals("SHAKE128")) {
            this.treeDigest = NISTObjectIdentifiers.id_shake128;
            this.param = new XMSSMTKeyGenerationParameters(new XMSSMTParameters(xMSSMTParameterSpec.getHeight(), xMSSMTParameterSpec.getLayers(), new SHAKEDigest(128)), secureRandom);
        } else if (xMSSMTParameterSpec.getTreeDigest().equals("SHAKE256")) {
            this.treeDigest = NISTObjectIdentifiers.id_shake256;
            this.param = new XMSSMTKeyGenerationParameters(new XMSSMTParameters(xMSSMTParameterSpec.getHeight(), xMSSMTParameterSpec.getLayers(), new SHAKEDigest(256)), secureRandom);
        }
        this.engine.init(this.param);
        this.initialised = true;
    }

    public KeyPair generateKeyPair() {
        if (!this.initialised) {
            this.param = new XMSSMTKeyGenerationParameters(new XMSSMTParameters(10, 20, new SHA512Digest()), this.random);
            this.engine.init(this.param);
            this.initialised = true;
        }
        AsymmetricCipherKeyPair asymmetricCipherKeyPair = this.engine.generateKeyPair();
        XMSSMTPublicKeyParameters xMSSMTPublicKeyParameters = (XMSSMTPublicKeyParameters)asymmetricCipherKeyPair.getPublic();
        XMSSMTPrivateKeyParameters xMSSMTPrivateKeyParameters = (XMSSMTPrivateKeyParameters)asymmetricCipherKeyPair.getPrivate();
        return new KeyPair(new BCXMSSMTPublicKey(this.treeDigest, xMSSMTPublicKeyParameters), new BCXMSSMTPrivateKey(this.treeDigest, xMSSMTPrivateKeyParameters));
    }
}

