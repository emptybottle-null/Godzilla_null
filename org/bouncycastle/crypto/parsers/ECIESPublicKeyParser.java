/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.parsers;

import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.crypto.KeyParser;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.util.io.Streams;

public class ECIESPublicKeyParser
implements KeyParser {
    private ECDomainParameters ecParams;

    public ECIESPublicKeyParser(ECDomainParameters eCDomainParameters) {
        this.ecParams = eCDomainParameters;
    }

    public AsymmetricKeyParameter readKey(InputStream inputStream) throws IOException {
        byte[] byArray;
        int n = inputStream.read();
        switch (n) {
            case 0: {
                throw new IOException("Sender's public key invalid.");
            }
            case 2: 
            case 3: {
                byArray = new byte[1 + (this.ecParams.getCurve().getFieldSize() + 7) / 8];
                break;
            }
            case 4: 
            case 6: 
            case 7: {
                byArray = new byte[1 + 2 * ((this.ecParams.getCurve().getFieldSize() + 7) / 8)];
                break;
            }
            default: {
                throw new IOException("Sender's public key has invalid point encoding 0x" + Integer.toString(n, 16));
            }
        }
        byArray[0] = (byte)n;
        Streams.readFully(inputStream, byArray, 1, byArray.length - 1);
        return new ECPublicKeyParameters(this.ecParams.getCurve().decodePoint(byArray), this.ecParams);
    }
}

