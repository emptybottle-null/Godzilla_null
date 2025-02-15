/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.math.BigInteger;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.agreement.srp.SRP6VerifierGenerator;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.SRP6GroupParameters;
import org.bouncycastle.crypto.tls.TlsSRPIdentityManager;
import org.bouncycastle.crypto.tls.TlsSRPLoginParameters;
import org.bouncycastle.crypto.tls.TlsUtils;
import org.bouncycastle.util.Strings;

public class SimulatedTlsSRPIdentityManager
implements TlsSRPIdentityManager {
    private static final byte[] PREFIX_PASSWORD = Strings.toByteArray("password");
    private static final byte[] PREFIX_SALT = Strings.toByteArray("salt");
    protected SRP6GroupParameters group;
    protected SRP6VerifierGenerator verifierGenerator;
    protected Mac mac;

    public static SimulatedTlsSRPIdentityManager getRFC5054Default(SRP6GroupParameters sRP6GroupParameters, byte[] byArray) {
        SRP6VerifierGenerator sRP6VerifierGenerator = new SRP6VerifierGenerator();
        sRP6VerifierGenerator.init(sRP6GroupParameters, TlsUtils.createHash((short)2));
        HMac hMac = new HMac(TlsUtils.createHash((short)2));
        hMac.init(new KeyParameter(byArray));
        return new SimulatedTlsSRPIdentityManager(sRP6GroupParameters, sRP6VerifierGenerator, hMac);
    }

    public SimulatedTlsSRPIdentityManager(SRP6GroupParameters sRP6GroupParameters, SRP6VerifierGenerator sRP6VerifierGenerator, Mac mac) {
        this.group = sRP6GroupParameters;
        this.verifierGenerator = sRP6VerifierGenerator;
        this.mac = mac;
    }

    public TlsSRPLoginParameters getLoginParameters(byte[] byArray) {
        this.mac.update(PREFIX_SALT, 0, PREFIX_SALT.length);
        this.mac.update(byArray, 0, byArray.length);
        byte[] byArray2 = new byte[this.mac.getMacSize()];
        this.mac.doFinal(byArray2, 0);
        this.mac.update(PREFIX_PASSWORD, 0, PREFIX_PASSWORD.length);
        this.mac.update(byArray, 0, byArray.length);
        byte[] byArray3 = new byte[this.mac.getMacSize()];
        this.mac.doFinal(byArray3, 0);
        BigInteger bigInteger = this.verifierGenerator.generateVerifier(byArray2, byArray, byArray3);
        return new TlsSRPLoginParameters(this.group, bigInteger, byArray2);
    }
}

