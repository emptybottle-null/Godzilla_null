/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.xmss;

import java.security.SecureRandom;
import java.text.ParseException;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.pqc.crypto.xmss.OTSHashAddress;
import org.bouncycastle.pqc.crypto.xmss.WOTSPlus;
import org.bouncycastle.pqc.crypto.xmss.WOTSPlusSignature;
import org.bouncycastle.pqc.crypto.xmss.XMSSKeyGenerationParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSKeyPairGenerator;
import org.bouncycastle.pqc.crypto.xmss.XMSSParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSPublicKeyParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSSigner;
import org.bouncycastle.util.Arrays;

public class XMSS {
    private final XMSSParameters params;
    private WOTSPlus wotsPlus;
    private SecureRandom prng;
    private XMSSPrivateKeyParameters privateKey;
    private XMSSPublicKeyParameters publicKey;

    public XMSS(XMSSParameters xMSSParameters, SecureRandom secureRandom) {
        if (xMSSParameters == null) {
            throw new NullPointerException("params == null");
        }
        this.params = xMSSParameters;
        this.wotsPlus = xMSSParameters.getWOTSPlus();
        this.prng = secureRandom;
    }

    public void generateKeys() {
        XMSSKeyPairGenerator xMSSKeyPairGenerator = new XMSSKeyPairGenerator();
        xMSSKeyPairGenerator.init(new XMSSKeyGenerationParameters(this.getParams(), this.prng));
        AsymmetricCipherKeyPair asymmetricCipherKeyPair = xMSSKeyPairGenerator.generateKeyPair();
        this.privateKey = (XMSSPrivateKeyParameters)asymmetricCipherKeyPair.getPrivate();
        this.publicKey = (XMSSPublicKeyParameters)asymmetricCipherKeyPair.getPublic();
        this.wotsPlus.importKeys(new byte[this.params.getDigestSize()], this.privateKey.getPublicSeed());
    }

    void importState(XMSSPrivateKeyParameters xMSSPrivateKeyParameters, XMSSPublicKeyParameters xMSSPublicKeyParameters) {
        if (!Arrays.areEqual(xMSSPrivateKeyParameters.getRoot(), xMSSPublicKeyParameters.getRoot())) {
            throw new IllegalStateException("root of private key and public key do not match");
        }
        if (!Arrays.areEqual(xMSSPrivateKeyParameters.getPublicSeed(), xMSSPublicKeyParameters.getPublicSeed())) {
            throw new IllegalStateException("public seed of private key and public key do not match");
        }
        this.privateKey = xMSSPrivateKeyParameters;
        this.publicKey = xMSSPublicKeyParameters;
        this.wotsPlus.importKeys(new byte[this.params.getDigestSize()], this.privateKey.getPublicSeed());
    }

    public void importState(byte[] byArray, byte[] byArray2) {
        if (byArray == null) {
            throw new NullPointerException("privateKey == null");
        }
        if (byArray2 == null) {
            throw new NullPointerException("publicKey == null");
        }
        XMSSPrivateKeyParameters xMSSPrivateKeyParameters = new XMSSPrivateKeyParameters.Builder(this.params).withPrivateKey(byArray, this.getParams()).build();
        XMSSPublicKeyParameters xMSSPublicKeyParameters = new XMSSPublicKeyParameters.Builder(this.params).withPublicKey(byArray2).build();
        if (!Arrays.areEqual(xMSSPrivateKeyParameters.getRoot(), xMSSPublicKeyParameters.getRoot())) {
            throw new IllegalStateException("root of private key and public key do not match");
        }
        if (!Arrays.areEqual(xMSSPrivateKeyParameters.getPublicSeed(), xMSSPublicKeyParameters.getPublicSeed())) {
            throw new IllegalStateException("public seed of private key and public key do not match");
        }
        this.privateKey = xMSSPrivateKeyParameters;
        this.publicKey = xMSSPublicKeyParameters;
        this.wotsPlus.importKeys(new byte[this.params.getDigestSize()], this.privateKey.getPublicSeed());
    }

    public byte[] sign(byte[] byArray) {
        if (byArray == null) {
            throw new NullPointerException("message == null");
        }
        XMSSSigner xMSSSigner = new XMSSSigner();
        xMSSSigner.init(true, this.privateKey);
        byte[] byArray2 = xMSSSigner.generateSignature(byArray);
        this.privateKey = (XMSSPrivateKeyParameters)xMSSSigner.getUpdatedPrivateKey();
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
        XMSSSigner xMSSSigner = new XMSSSigner();
        xMSSSigner.init(false, new XMSSPublicKeyParameters.Builder(this.getParams()).withPublicKey(byArray3).build());
        return xMSSSigner.verifySignature(byArray, byArray2);
    }

    public byte[] exportPrivateKey() {
        return this.privateKey.toByteArray();
    }

    public byte[] exportPublicKey() {
        return this.publicKey.toByteArray();
    }

    protected WOTSPlusSignature wotsSign(byte[] byArray, OTSHashAddress oTSHashAddress) {
        if (byArray.length != this.params.getDigestSize()) {
            throw new IllegalArgumentException("size of messageDigest needs to be equal to size of digest");
        }
        if (oTSHashAddress == null) {
            throw new NullPointerException("otsHashAddress == null");
        }
        this.wotsPlus.importKeys(this.wotsPlus.getWOTSPlusSecretKey(this.privateKey.getSecretKeySeed(), oTSHashAddress), this.getPublicSeed());
        return this.wotsPlus.sign(byArray, oTSHashAddress);
    }

    public XMSSParameters getParams() {
        return this.params;
    }

    protected WOTSPlus getWOTSPlus() {
        return this.wotsPlus;
    }

    public byte[] getRoot() {
        return this.privateKey.getRoot();
    }

    protected void setRoot(byte[] byArray) {
        this.privateKey = new XMSSPrivateKeyParameters.Builder(this.params).withSecretKeySeed(this.privateKey.getSecretKeySeed()).withSecretKeyPRF(this.privateKey.getSecretKeyPRF()).withPublicSeed(this.getPublicSeed()).withRoot(byArray).withBDSState(this.privateKey.getBDSState()).build();
        this.publicKey = new XMSSPublicKeyParameters.Builder(this.params).withRoot(byArray).withPublicSeed(this.getPublicSeed()).build();
    }

    public int getIndex() {
        return this.privateKey.getIndex();
    }

    protected void setIndex(int n) {
        this.privateKey = new XMSSPrivateKeyParameters.Builder(this.params).withSecretKeySeed(this.privateKey.getSecretKeySeed()).withSecretKeyPRF(this.privateKey.getSecretKeyPRF()).withPublicSeed(this.privateKey.getPublicSeed()).withRoot(this.privateKey.getRoot()).withBDSState(this.privateKey.getBDSState()).build();
    }

    public byte[] getPublicSeed() {
        return this.privateKey.getPublicSeed();
    }

    protected void setPublicSeed(byte[] byArray) {
        this.privateKey = new XMSSPrivateKeyParameters.Builder(this.params).withSecretKeySeed(this.privateKey.getSecretKeySeed()).withSecretKeyPRF(this.privateKey.getSecretKeyPRF()).withPublicSeed(byArray).withRoot(this.getRoot()).withBDSState(this.privateKey.getBDSState()).build();
        this.publicKey = new XMSSPublicKeyParameters.Builder(this.params).withRoot(this.getRoot()).withPublicSeed(byArray).build();
        this.wotsPlus.importKeys(new byte[this.params.getDigestSize()], byArray);
    }

    public XMSSPrivateKeyParameters getPrivateKey() {
        return this.privateKey;
    }
}

