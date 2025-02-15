/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import org.bouncycastle.crypto.params.DHParameters;
import org.bouncycastle.crypto.params.DHPublicKeyParameters;
import org.bouncycastle.crypto.tls.TlsDHUtils;

public class ServerDHParams {
    protected DHPublicKeyParameters publicKey;

    public ServerDHParams(DHPublicKeyParameters dHPublicKeyParameters) {
        if (dHPublicKeyParameters == null) {
            throw new IllegalArgumentException("'publicKey' cannot be null");
        }
        this.publicKey = dHPublicKeyParameters;
    }

    public DHPublicKeyParameters getPublicKey() {
        return this.publicKey;
    }

    public void encode(OutputStream outputStream) throws IOException {
        DHParameters dHParameters = this.publicKey.getParameters();
        BigInteger bigInteger = this.publicKey.getY();
        TlsDHUtils.writeDHParameter(dHParameters.getP(), outputStream);
        TlsDHUtils.writeDHParameter(dHParameters.getG(), outputStream);
        TlsDHUtils.writeDHParameter(bigInteger, outputStream);
    }

    public static ServerDHParams parse(InputStream inputStream) throws IOException {
        BigInteger bigInteger = TlsDHUtils.readDHParameter(inputStream);
        BigInteger bigInteger2 = TlsDHUtils.readDHParameter(inputStream);
        BigInteger bigInteger3 = TlsDHUtils.readDHParameter(inputStream);
        return new ServerDHParams(TlsDHUtils.validateDHPublicKey(new DHPublicKeyParameters(bigInteger3, new DHParameters(bigInteger, bigInteger2))));
    }
}

