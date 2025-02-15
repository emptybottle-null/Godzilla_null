/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.jcajce.interfaces;

import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.cert.Certificate;

public interface StateAwareSignature {
    public void initVerify(PublicKey var1) throws InvalidKeyException;

    public void initVerify(Certificate var1) throws InvalidKeyException;

    public void initSign(PrivateKey var1) throws InvalidKeyException;

    public void initSign(PrivateKey var1, SecureRandom var2) throws InvalidKeyException;

    public byte[] sign() throws SignatureException;

    public int sign(byte[] var1, int var2, int var3) throws SignatureException;

    public boolean verify(byte[] var1) throws SignatureException;

    public boolean verify(byte[] var1, int var2, int var3) throws SignatureException;

    public void update(byte var1) throws SignatureException;

    public void update(byte[] var1) throws SignatureException;

    public void update(byte[] var1, int var2, int var3) throws SignatureException;

    public void update(ByteBuffer var1) throws SignatureException;

    public String getAlgorithm();

    public PrivateKey getUpdatedPrivateKey();
}

