/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.engines.CamelliaEngine;
import org.bouncycastle.crypto.engines.DESedeEngine;
import org.bouncycastle.crypto.engines.RC4Engine;
import org.bouncycastle.crypto.engines.SEEDEngine;
import org.bouncycastle.crypto.modes.AEADBlockCipher;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.modes.CCMBlockCipher;
import org.bouncycastle.crypto.modes.GCMBlockCipher;
import org.bouncycastle.crypto.modes.OCBBlockCipher;
import org.bouncycastle.crypto.tls.AbstractTlsCipherFactory;
import org.bouncycastle.crypto.tls.Chacha20Poly1305;
import org.bouncycastle.crypto.tls.TlsAEADCipher;
import org.bouncycastle.crypto.tls.TlsBlockCipher;
import org.bouncycastle.crypto.tls.TlsCipher;
import org.bouncycastle.crypto.tls.TlsContext;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsNullCipher;
import org.bouncycastle.crypto.tls.TlsStreamCipher;
import org.bouncycastle.crypto.tls.TlsUtils;

public class DefaultTlsCipherFactory
extends AbstractTlsCipherFactory {
    public TlsCipher createCipher(TlsContext tlsContext, int n, int n2) throws IOException {
        switch (n) {
            case 7: {
                return this.createDESedeCipher(tlsContext, n2);
            }
            case 8: {
                return this.createAESCipher(tlsContext, 16, n2);
            }
            case 15: {
                return this.createCipher_AES_CCM(tlsContext, 16, 16);
            }
            case 16: {
                return this.createCipher_AES_CCM(tlsContext, 16, 8);
            }
            case 10: {
                return this.createCipher_AES_GCM(tlsContext, 16, 16);
            }
            case 103: {
                return this.createCipher_AES_OCB(tlsContext, 16, 12);
            }
            case 9: {
                return this.createAESCipher(tlsContext, 32, n2);
            }
            case 17: {
                return this.createCipher_AES_CCM(tlsContext, 32, 16);
            }
            case 18: {
                return this.createCipher_AES_CCM(tlsContext, 32, 8);
            }
            case 11: {
                return this.createCipher_AES_GCM(tlsContext, 32, 16);
            }
            case 104: {
                return this.createCipher_AES_OCB(tlsContext, 32, 12);
            }
            case 12: {
                return this.createCamelliaCipher(tlsContext, 16, n2);
            }
            case 19: {
                return this.createCipher_Camellia_GCM(tlsContext, 16, 16);
            }
            case 13: {
                return this.createCamelliaCipher(tlsContext, 32, n2);
            }
            case 20: {
                return this.createCipher_Camellia_GCM(tlsContext, 32, 16);
            }
            case 21: {
                return this.createChaCha20Poly1305(tlsContext);
            }
            case 0: {
                return this.createNullCipher(tlsContext, n2);
            }
            case 2: {
                return this.createRC4Cipher(tlsContext, 16, n2);
            }
            case 14: {
                return this.createSEEDCipher(tlsContext, n2);
            }
        }
        throw new TlsFatalAlert(80);
    }

    protected TlsBlockCipher createAESCipher(TlsContext tlsContext, int n, int n2) throws IOException {
        return new TlsBlockCipher(tlsContext, this.createAESBlockCipher(), this.createAESBlockCipher(), this.createHMACDigest(n2), this.createHMACDigest(n2), n);
    }

    protected TlsBlockCipher createCamelliaCipher(TlsContext tlsContext, int n, int n2) throws IOException {
        return new TlsBlockCipher(tlsContext, this.createCamelliaBlockCipher(), this.createCamelliaBlockCipher(), this.createHMACDigest(n2), this.createHMACDigest(n2), n);
    }

    protected TlsCipher createChaCha20Poly1305(TlsContext tlsContext) throws IOException {
        return new Chacha20Poly1305(tlsContext);
    }

    protected TlsAEADCipher createCipher_AES_CCM(TlsContext tlsContext, int n, int n2) throws IOException {
        return new TlsAEADCipher(tlsContext, this.createAEADBlockCipher_AES_CCM(), this.createAEADBlockCipher_AES_CCM(), n, n2);
    }

    protected TlsAEADCipher createCipher_AES_GCM(TlsContext tlsContext, int n, int n2) throws IOException {
        return new TlsAEADCipher(tlsContext, this.createAEADBlockCipher_AES_GCM(), this.createAEADBlockCipher_AES_GCM(), n, n2);
    }

    protected TlsAEADCipher createCipher_AES_OCB(TlsContext tlsContext, int n, int n2) throws IOException {
        return new TlsAEADCipher(tlsContext, this.createAEADBlockCipher_AES_OCB(), this.createAEADBlockCipher_AES_OCB(), n, n2, 2);
    }

    protected TlsAEADCipher createCipher_Camellia_GCM(TlsContext tlsContext, int n, int n2) throws IOException {
        return new TlsAEADCipher(tlsContext, this.createAEADBlockCipher_Camellia_GCM(), this.createAEADBlockCipher_Camellia_GCM(), n, n2);
    }

    protected TlsBlockCipher createDESedeCipher(TlsContext tlsContext, int n) throws IOException {
        return new TlsBlockCipher(tlsContext, this.createDESedeBlockCipher(), this.createDESedeBlockCipher(), this.createHMACDigest(n), this.createHMACDigest(n), 24);
    }

    protected TlsNullCipher createNullCipher(TlsContext tlsContext, int n) throws IOException {
        return new TlsNullCipher(tlsContext, this.createHMACDigest(n), this.createHMACDigest(n));
    }

    protected TlsStreamCipher createRC4Cipher(TlsContext tlsContext, int n, int n2) throws IOException {
        return new TlsStreamCipher(tlsContext, this.createRC4StreamCipher(), this.createRC4StreamCipher(), this.createHMACDigest(n2), this.createHMACDigest(n2), n, false);
    }

    protected TlsBlockCipher createSEEDCipher(TlsContext tlsContext, int n) throws IOException {
        return new TlsBlockCipher(tlsContext, this.createSEEDBlockCipher(), this.createSEEDBlockCipher(), this.createHMACDigest(n), this.createHMACDigest(n), 16);
    }

    protected BlockCipher createAESEngine() {
        return new AESEngine();
    }

    protected BlockCipher createCamelliaEngine() {
        return new CamelliaEngine();
    }

    protected BlockCipher createAESBlockCipher() {
        return new CBCBlockCipher(this.createAESEngine());
    }

    protected AEADBlockCipher createAEADBlockCipher_AES_CCM() {
        return new CCMBlockCipher(this.createAESEngine());
    }

    protected AEADBlockCipher createAEADBlockCipher_AES_GCM() {
        return new GCMBlockCipher(this.createAESEngine());
    }

    protected AEADBlockCipher createAEADBlockCipher_AES_OCB() {
        return new OCBBlockCipher(this.createAESEngine(), this.createAESEngine());
    }

    protected AEADBlockCipher createAEADBlockCipher_Camellia_GCM() {
        return new GCMBlockCipher(this.createCamelliaEngine());
    }

    protected BlockCipher createCamelliaBlockCipher() {
        return new CBCBlockCipher(this.createCamelliaEngine());
    }

    protected BlockCipher createDESedeBlockCipher() {
        return new CBCBlockCipher(new DESedeEngine());
    }

    protected StreamCipher createRC4StreamCipher() {
        return new RC4Engine();
    }

    protected BlockCipher createSEEDBlockCipher() {
        return new CBCBlockCipher(new SEEDEngine());
    }

    protected Digest createHMACDigest(int n) throws IOException {
        switch (n) {
            case 0: {
                return null;
            }
            case 1: {
                return TlsUtils.createHash((short)1);
            }
            case 2: {
                return TlsUtils.createHash((short)2);
            }
            case 3: {
                return TlsUtils.createHash((short)4);
            }
            case 4: {
                return TlsUtils.createHash((short)5);
            }
            case 5: {
                return TlsUtils.createHash((short)6);
            }
        }
        throw new TlsFatalAlert(80);
    }
}

