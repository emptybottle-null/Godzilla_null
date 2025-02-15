/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.sphincs;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.pqc.crypto.sphincs.HashFunctions;
import org.bouncycastle.pqc.crypto.sphincs.SPHINCS256KeyGenerationParameters;
import org.bouncycastle.pqc.crypto.sphincs.SPHINCSPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.sphincs.SPHINCSPublicKeyParameters;
import org.bouncycastle.pqc.crypto.sphincs.Tree;

public class SPHINCS256KeyPairGenerator
implements AsymmetricCipherKeyPairGenerator {
    private SecureRandom random;
    private Digest treeDigest;

    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.random = keyGenerationParameters.getRandom();
        this.treeDigest = ((SPHINCS256KeyGenerationParameters)keyGenerationParameters).getTreeDigest();
    }

    public AsymmetricCipherKeyPair generateKeyPair() {
        Tree.leafaddr leafaddr2 = new Tree.leafaddr();
        byte[] byArray = new byte[1088];
        this.random.nextBytes(byArray);
        byte[] byArray2 = new byte[1056];
        System.arraycopy(byArray, 32, byArray2, 0, 1024);
        leafaddr2.level = 11;
        leafaddr2.subtree = 0L;
        leafaddr2.subleaf = 0L;
        HashFunctions hashFunctions = new HashFunctions(this.treeDigest);
        Tree.treehash(hashFunctions, byArray2, 1024, 5, byArray, leafaddr2, byArray2, 0);
        return new AsymmetricCipherKeyPair(new SPHINCSPublicKeyParameters(byArray2), new SPHINCSPrivateKeyParameters(byArray));
    }
}

