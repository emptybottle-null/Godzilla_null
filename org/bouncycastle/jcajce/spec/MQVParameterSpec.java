/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.spec;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.util.Arrays;

public class MQVParameterSpec
implements AlgorithmParameterSpec {
    private final PublicKey ephemeralPublicKey;
    private final PrivateKey ephemeralPrivateKey;
    private final PublicKey otherPartyEphemeralKey;
    private final byte[] userKeyingMaterial;

    public MQVParameterSpec(PublicKey publicKey, PrivateKey privateKey, PublicKey publicKey2, byte[] byArray) {
        this.ephemeralPublicKey = publicKey;
        this.ephemeralPrivateKey = privateKey;
        this.otherPartyEphemeralKey = publicKey2;
        this.userKeyingMaterial = Arrays.clone(byArray);
    }

    public MQVParameterSpec(PublicKey publicKey, PrivateKey privateKey, PublicKey publicKey2) {
        this(publicKey, privateKey, publicKey2, null);
    }

    public MQVParameterSpec(KeyPair keyPair, PublicKey publicKey, byte[] byArray) {
        this(keyPair.getPublic(), keyPair.getPrivate(), publicKey, byArray);
    }

    public MQVParameterSpec(PrivateKey privateKey, PublicKey publicKey, byte[] byArray) {
        this(null, privateKey, publicKey, byArray);
    }

    public MQVParameterSpec(KeyPair keyPair, PublicKey publicKey) {
        this(keyPair.getPublic(), keyPair.getPrivate(), publicKey, null);
    }

    public MQVParameterSpec(PrivateKey privateKey, PublicKey publicKey) {
        this(null, privateKey, publicKey, null);
    }

    public PrivateKey getEphemeralPrivateKey() {
        return this.ephemeralPrivateKey;
    }

    public PublicKey getEphemeralPublicKey() {
        return this.ephemeralPublicKey;
    }

    public PublicKey getOtherPartyEphemeralKey() {
        return this.otherPartyEphemeralKey;
    }

    public byte[] getUserKeyingMaterial() {
        return Arrays.clone(this.userKeyingMaterial);
    }
}

