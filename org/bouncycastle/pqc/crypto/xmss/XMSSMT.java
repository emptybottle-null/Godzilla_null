/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.xmss;

import java.security.SecureRandom;
import java.text.ParseException;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.pqc.crypto.xmss.XMSSMTKeyGenerationParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSMTKeyPairGenerator;
import org.bouncycastle.pqc.crypto.xmss.XMSSMTParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSMTPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSMTPublicKeyParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSMTSigner;
import org.bouncycastle.pqc.crypto.xmss.XMSSParameters;
import org.bouncycastle.util.Arrays;

public final class XMSSMT {
    private XMSSMTParameters params;
    private XMSSParameters xmssParams;
    private SecureRandom prng;
    private XMSSMTPrivateKeyParameters privateKey;
    private XMSSMTPublicKeyParameters publicKey;

    public XMSSMT(XMSSMTParameters xMSSMTParameters, SecureRandom secureRandom) {
        if (xMSSMTParameters == null) {
            throw new NullPointerException("params == null");
        }
        this.params = xMSSMTParameters;
        this.xmssParams = xMSSMTParameters.getXMSSParameters();
        this.prng = secureRandom;
        this.privateKey = new XMSSMTPrivateKeyParameters.Builder(xMSSMTParameters).build();
        this.publicKey = new XMSSMTPublicKeyParameters.Builder(xMSSMTParameters).build();
    }

    public void generateKeys() {
        XMSSMTKeyPairGenerator xMSSMTKeyPairGenerator = new XMSSMTKeyPairGenerator();
        xMSSMTKeyPairGenerator.init(new XMSSMTKeyGenerationParameters(this.getParams(), this.prng));
        AsymmetricCipherKeyPair asymmetricCipherKeyPair = xMSSMTKeyPairGenerator.generateKeyPair();
        this.privateKey = (XMSSMTPrivateKeyParameters)asymmetricCipherKeyPair.getPrivate();
        this.publicKey = (XMSSMTPublicKeyParameters)asymmetricCipherKeyPair.getPublic();
        this.importState(this.privateKey, this.publicKey);
    }

    private void importState(XMSSMTPrivateKeyParameters xMSSMTPrivateKeyParameters, XMSSMTPublicKeyParameters xMSSMTPublicKeyParameters) {
        this.xmssParams.getWOTSPlus().importKeys(new byte[this.params.getDigestSize()], this.privateKey.getPublicSeed());
        this.privateKey = xMSSMTPrivateKeyParameters;
        this.publicKey = xMSSMTPublicKeyParameters;
    }

    public void importState(byte[] byArray, byte[] byArray2) {
        if (byArray == null) {
            throw new NullPointerException("privateKey == null");
        }
        if (byArray2 == null) {
            throw new NullPointerException("publicKey == null");
        }
        XMSSMTPrivateKeyParameters xMSSMTPrivateKeyParameters = new XMSSMTPrivateKeyParameters.Builder(this.params).withPrivateKey(byArray, this.xmssParams).build();
        XMSSMTPublicKeyParameters xMSSMTPublicKeyParameters = new XMSSMTPublicKeyParameters.Builder(this.params).withPublicKey(byArray2).build();
        if (!Arrays.areEqual(xMSSMTPrivateKeyParameters.getRoot(), xMSSMTPublicKeyParameters.getRoot())) {
            throw new IllegalStateException("root of private key and public key do not match");
        }
        if (!Arrays.areEqual(xMSSMTPrivateKeyParameters.getPublicSeed(), xMSSMTPublicKeyParameters.getPublicSeed())) {
            throw new IllegalStateException("public seed of private key and public key do not match");
        }
        this.xmssParams.getWOTSPlus().importKeys(new byte[this.params.getDigestSize()], xMSSMTPrivateKeyParameters.getPublicSeed());
        this.privateKey = xMSSMTPrivateKeyParameters;
        this.publicKey = xMSSMTPublicKeyParameters;
    }

    public byte[] sign(byte[] byArray) {
        if (byArray == null) {
            throw new NullPointerException("message == null");
        }
        XMSSMTSigner xMSSMTSigner = new XMSSMTSigner();
        xMSSMTSigner.init(true, this.privateKey);
        byte[] byArray2 = xMSSMTSigner.generateSignature(byArray);
        this.privateKey = (XMSSMTPrivateKeyParameters)xMSSMTSigner.getUpdatedPrivateKey();
        this.importState(this.privateKey, this.publicKey);
        return byArray2;
    }

    public boolean verifySignature(byte[] byArray, byte[] byArray2, byte[] byArray3) throws ParseException {
        if (byArray == null) {
            throw new NullPointerException("message == null");
        }
        if (byArray2 == null) {
            throw new NullPointerException("signature == null");
        }
        if (byArray3 == null) {
            throw new NullPointerException("publicKey == null");
        }
        XMSSMTSigner xMSSMTSigner = new XMSSMTSigner();
        xMSSMTSigner.init(false, new XMSSMTPublicKeyParameters.Builder(this.getParams()).withPublicKey(byArray3).build());
        return xMSSMTSigner.verifySignature(byArray, byArray2);
    }

    public byte[] exportPrivateKey() {
        return this.privateKey.toByteArray();
    }

    public byte[] exportPublicKey() {
        return this.publicKey.toByteArray();
    }

    public XMSSMTParameters getParams() {
        return this.params;
    }

    public byte[] getPublicSeed() {
        return this.privateKey.getPublicSeed();
    }

    protected XMSSParameters getXMSS() {
        return this.xmssParams;
    }
}

