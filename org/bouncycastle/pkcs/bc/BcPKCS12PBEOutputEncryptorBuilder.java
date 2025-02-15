/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pkcs.bc;

import java.io.OutputStream;
import java.security.SecureRandom;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.pkcs.PKCS12PBEParams;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.generators.PKCS12ParametersGenerator;
import org.bouncycastle.crypto.io.CipherOutputStream;
import org.bouncycastle.crypto.paddings.PKCS7Padding;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OutputEncryptor;
import org.bouncycastle.pkcs.bc.PKCS12PBEUtils;

public class BcPKCS12PBEOutputEncryptorBuilder {
    private ExtendedDigest digest;
    private BufferedBlockCipher engine;
    private ASN1ObjectIdentifier algorithm;
    private SecureRandom random;
    private int iterationCount = 1024;

    public BcPKCS12PBEOutputEncryptorBuilder(ASN1ObjectIdentifier aSN1ObjectIdentifier, BlockCipher blockCipher) {
        this(aSN1ObjectIdentifier, blockCipher, new SHA1Digest());
    }

    public BcPKCS12PBEOutputEncryptorBuilder(ASN1ObjectIdentifier aSN1ObjectIdentifier, BlockCipher blockCipher, ExtendedDigest extendedDigest) {
        this.algorithm = aSN1ObjectIdentifier;
        this.engine = new PaddedBufferedBlockCipher(blockCipher, new PKCS7Padding());
        this.digest = extendedDigest;
    }

    public BcPKCS12PBEOutputEncryptorBuilder setIterationCount(int n) {
        this.iterationCount = n;
        return this;
    }

    public OutputEncryptor build(final char[] cArray) {
        if (this.random == null) {
            this.random = new SecureRandom();
        }
        byte[] byArray = new byte[20];
        this.random.nextBytes(byArray);
        final PKCS12PBEParams pKCS12PBEParams = new PKCS12PBEParams(byArray, this.iterationCount);
        CipherParameters cipherParameters = PKCS12PBEUtils.createCipherParameters(this.algorithm, this.digest, this.engine.getBlockSize(), pKCS12PBEParams, cArray);
        this.engine.init(true, cipherParameters);
        return new OutputEncryptor(){

            public AlgorithmIdentifier getAlgorithmIdentifier() {
                return new AlgorithmIdentifier(BcPKCS12PBEOutputEncryptorBuilder.this.algorithm, pKCS12PBEParams);
            }

            public OutputStream getOutputStream(OutputStream outputStream) {
                return new CipherOutputStream(outputStream, BcPKCS12PBEOutputEncryptorBuilder.this.engine);
            }

            public GenericKey getKey() {
                return new GenericKey(new AlgorithmIdentifier(BcPKCS12PBEOutputEncryptorBuilder.this.algorithm, pKCS12PBEParams), PKCS12ParametersGenerator.PKCS12PasswordToBytes(cArray));
            }
        };
    }
}

