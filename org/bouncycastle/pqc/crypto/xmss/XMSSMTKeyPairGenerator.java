/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.xmss;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.pqc.crypto.xmss.BDS;
import org.bouncycastle.pqc.crypto.xmss.BDSStateMap;
import org.bouncycastle.pqc.crypto.xmss.OTSHashAddress;
import org.bouncycastle.pqc.crypto.xmss.XMSSMTKeyGenerationParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSMTParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSMTPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSMTPublicKeyParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSNode;
import org.bouncycastle.pqc.crypto.xmss.XMSSParameters;

public final class XMSSMTKeyPairGenerator {
    private XMSSMTParameters params;
    private XMSSParameters xmssParams;
    private SecureRandom prng;

    public void init(KeyGenerationParameters keyGenerationParameters) {
        XMSSMTKeyGenerationParameters xMSSMTKeyGenerationParameters = (XMSSMTKeyGenerationParameters)keyGenerationParameters;
        this.prng = xMSSMTKeyGenerationParameters.getRandom();
        this.params = xMSSMTKeyGenerationParameters.getParameters();
        this.xmssParams = this.params.getXMSSParameters();
    }

    public AsymmetricCipherKeyPair generateKeyPair() {
        XMSSMTPrivateKeyParameters xMSSMTPrivateKeyParameters = this.generatePrivateKey(new XMSSMTPrivateKeyParameters.Builder(this.params).build().getBDSState());
        this.xmssParams.getWOTSPlus().importKeys(new byte[this.params.getDigestSize()], xMSSMTPrivateKeyParameters.getPublicSeed());
        int n = this.params.getLayers() - 1;
        OTSHashAddress oTSHashAddress = (OTSHashAddress)((OTSHashAddress.Builder)new OTSHashAddress.Builder().withLayerAddress(n)).build();
        BDS bDS = new BDS(this.xmssParams, xMSSMTPrivateKeyParameters.getPublicSeed(), xMSSMTPrivateKeyParameters.getSecretKeySeed(), oTSHashAddress);
        XMSSNode xMSSNode = bDS.getRoot();
        xMSSMTPrivateKeyParameters.getBDSState().put(n, bDS);
        xMSSMTPrivateKeyParameters = new XMSSMTPrivateKeyParameters.Builder(this.params).withSecretKeySeed(xMSSMTPrivateKeyParameters.getSecretKeySeed()).withSecretKeyPRF(xMSSMTPrivateKeyParameters.getSecretKeyPRF()).withPublicSeed(xMSSMTPrivateKeyParameters.getPublicSeed()).withRoot(xMSSNode.getValue()).withBDSState(xMSSMTPrivateKeyParameters.getBDSState()).build();
        XMSSMTPublicKeyParameters xMSSMTPublicKeyParameters = new XMSSMTPublicKeyParameters.Builder(this.params).withRoot(xMSSNode.getValue()).withPublicSeed(xMSSMTPrivateKeyParameters.getPublicSeed()).build();
        return new AsymmetricCipherKeyPair(xMSSMTPublicKeyParameters, xMSSMTPrivateKeyParameters);
    }

    private XMSSMTPrivateKeyParameters generatePrivateKey(BDSStateMap bDSStateMap) {
        int n = this.params.getDigestSize();
        byte[] byArray = new byte[n];
        this.prng.nextBytes(byArray);
        byte[] byArray2 = new byte[n];
        this.prng.nextBytes(byArray2);
        byte[] byArray3 = new byte[n];
        this.prng.nextBytes(byArray3);
        XMSSMTPrivateKeyParameters xMSSMTPrivateKeyParameters = null;
        xMSSMTPrivateKeyParameters = new XMSSMTPrivateKeyParameters.Builder(this.params).withSecretKeySeed(byArray).withSecretKeyPRF(byArray2).withPublicSeed(byArray3).withBDSState(bDSStateMap).build();
        return xMSSMTPrivateKeyParameters;
    }
}

