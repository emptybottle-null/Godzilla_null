/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;

public class RSAKeyParameters
extends AsymmetricKeyParameter {
    private static final BigInteger ONE = BigInteger.valueOf(1L);
    private BigInteger modulus;
    private BigInteger exponent;

    public RSAKeyParameters(boolean bl, BigInteger bigInteger, BigInteger bigInteger2) {
        super(bl);
        if (!bl && (bigInteger2.intValue() & 1) == 0) {
            throw new IllegalArgumentException("RSA publicExponent is even");
        }
        this.modulus = this.validate(bigInteger);
        this.exponent = bigInteger2;
    }

    private BigInteger validate(BigInteger bigInteger) {
        if ((bigInteger.intValue() & 1) == 0) {
            throw new IllegalArgumentException("RSA modulus is even");
        }
        if (!bigInteger.gcd(new BigInteger("1451887755777639901511587432083070202422614380984889313550570919659315177065956574359078912654149167643992684236991305777574330831666511589145701059710742276692757882915756220901998212975756543223550490431013061082131040808010565293748926901442915057819663730454818359472391642885328171302299245556663073719855")).equals(ONE)) {
            throw new IllegalArgumentException("RSA modulus has a small prime factor");
        }
        return bigInteger;
    }

    public BigInteger getModulus() {
        return this.modulus;
    }

    public BigInteger getExponent() {
        return this.exponent;
    }
}

