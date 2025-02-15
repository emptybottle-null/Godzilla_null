/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import org.bouncycastle.crypto.tls.TlsSRPUtils;
import org.bouncycastle.crypto.tls.TlsUtils;
import org.bouncycastle.util.Arrays;

public class ServerSRPParams {
    protected BigInteger N;
    protected BigInteger g;
    protected BigInteger B;
    protected byte[] s;

    public ServerSRPParams(BigInteger bigInteger, BigInteger bigInteger2, byte[] byArray, BigInteger bigInteger3) {
        this.N = bigInteger;
        this.g = bigInteger2;
        this.s = Arrays.clone(byArray);
        this.B = bigInteger3;
    }

    public BigInteger getB() {
        return this.B;
    }

    public BigInteger getG() {
        return this.g;
    }

    public BigInteger getN() {
        return this.N;
    }

    public byte[] getS() {
        return this.s;
    }

    public void encode(OutputStream outputStream) throws IOException {
        TlsSRPUtils.writeSRPParameter(this.N, outputStream);
        TlsSRPUtils.writeSRPParameter(this.g, outputStream);
        TlsUtils.writeOpaque8(this.s, outputStream);
        TlsSRPUtils.writeSRPParameter(this.B, outputStream);
    }

    public static ServerSRPParams parse(InputStream inputStream) throws IOException {
        BigInteger bigInteger = TlsSRPUtils.readSRPParameter(inputStream);
        BigInteger bigInteger2 = TlsSRPUtils.readSRPParameter(inputStream);
        byte[] byArray = TlsUtils.readOpaque8(inputStream);
        BigInteger bigInteger3 = TlsSRPUtils.readSRPParameter(inputStream);
        return new ServerSRPParams(bigInteger, bigInteger2, byArray, bigInteger3);
    }
}

