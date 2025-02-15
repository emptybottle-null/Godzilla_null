/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.symmetric.util;

import java.security.InvalidAlgorithmParameterException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.SecretKey;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.digests.GOST3411Digest;
import org.bouncycastle.crypto.digests.MD2Digest;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import org.bouncycastle.crypto.digests.TigerDigest;
import org.bouncycastle.crypto.generators.OpenSSLPBEParametersGenerator;
import org.bouncycastle.crypto.generators.PKCS12ParametersGenerator;
import org.bouncycastle.crypto.generators.PKCS5S1ParametersGenerator;
import org.bouncycastle.crypto.generators.PKCS5S2ParametersGenerator;
import org.bouncycastle.crypto.params.DESParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.util.DigestFactory;
import org.bouncycastle.jcajce.provider.symmetric.util.BCPBEKey;

public interface PBE {
    public static final int MD5 = 0;
    public static final int SHA1 = 1;
    public static final int RIPEMD160 = 2;
    public static final int TIGER = 3;
    public static final int SHA256 = 4;
    public static final int MD2 = 5;
    public static final int GOST3411 = 6;
    public static final int SHA224 = 7;
    public static final int SHA384 = 8;
    public static final int SHA512 = 9;
    public static final int SHA3_224 = 10;
    public static final int SHA3_256 = 11;
    public static final int SHA3_384 = 12;
    public static final int SHA3_512 = 13;
    public static final int PKCS5S1 = 0;
    public static final int PKCS5S2 = 1;
    public static final int PKCS12 = 2;
    public static final int OPENSSL = 3;
    public static final int PKCS5S1_UTF8 = 4;
    public static final int PKCS5S2_UTF8 = 5;

    public static class Util {
        private static PBEParametersGenerator makePBEGenerator(int n, int n2) {
            PBEParametersGenerator pBEParametersGenerator;
            block34: {
                block36: {
                    block35: {
                        block33: {
                            if (n != 0 && n != 4) break block33;
                            switch (n2) {
                                case 5: {
                                    pBEParametersGenerator = new PKCS5S1ParametersGenerator(new MD2Digest());
                                    break block34;
                                }
                                case 0: {
                                    pBEParametersGenerator = new PKCS5S1ParametersGenerator(DigestFactory.createMD5());
                                    break block34;
                                }
                                case 1: {
                                    pBEParametersGenerator = new PKCS5S1ParametersGenerator(DigestFactory.createSHA1());
                                    break block34;
                                }
                                default: {
                                    throw new IllegalStateException("PKCS5 scheme 1 only supports MD2, MD5 and SHA1.");
                                }
                            }
                        }
                        if (n != 1 && n != 5) break block35;
                        switch (n2) {
                            case 5: {
                                pBEParametersGenerator = new PKCS5S2ParametersGenerator(new MD2Digest());
                                break block34;
                            }
                            case 0: {
                                pBEParametersGenerator = new PKCS5S2ParametersGenerator(DigestFactory.createMD5());
                                break block34;
                            }
                            case 1: {
                                pBEParametersGenerator = new PKCS5S2ParametersGenerator(DigestFactory.createSHA1());
                                break block34;
                            }
                            case 2: {
                                pBEParametersGenerator = new PKCS5S2ParametersGenerator(new RIPEMD160Digest());
                                break block34;
                            }
                            case 3: {
                                pBEParametersGenerator = new PKCS5S2ParametersGenerator(new TigerDigest());
                                break block34;
                            }
                            case 4: {
                                pBEParametersGenerator = new PKCS5S2ParametersGenerator(DigestFactory.createSHA256());
                                break block34;
                            }
                            case 6: {
                                pBEParametersGenerator = new PKCS5S2ParametersGenerator(new GOST3411Digest());
                                break block34;
                            }
                            case 7: {
                                pBEParametersGenerator = new PKCS5S2ParametersGenerator(DigestFactory.createSHA224());
                                break block34;
                            }
                            case 8: {
                                pBEParametersGenerator = new PKCS5S2ParametersGenerator(DigestFactory.createSHA384());
                                break block34;
                            }
                            case 9: {
                                pBEParametersGenerator = new PKCS5S2ParametersGenerator(DigestFactory.createSHA512());
                                break block34;
                            }
                            case 10: {
                                pBEParametersGenerator = new PKCS5S2ParametersGenerator(DigestFactory.createSHA3_224());
                                break block34;
                            }
                            case 11: {
                                pBEParametersGenerator = new PKCS5S2ParametersGenerator(DigestFactory.createSHA3_256());
                                break block34;
                            }
                            case 12: {
                                pBEParametersGenerator = new PKCS5S2ParametersGenerator(DigestFactory.createSHA3_384());
                                break block34;
                            }
                            case 13: {
                                pBEParametersGenerator = new PKCS5S2ParametersGenerator(DigestFactory.createSHA3_512());
                                break block34;
                            }
                            default: {
                                throw new IllegalStateException("unknown digest scheme for PBE PKCS5S2 encryption.");
                            }
                        }
                    }
                    if (n != 2) break block36;
                    switch (n2) {
                        case 5: {
                            pBEParametersGenerator = new PKCS12ParametersGenerator(new MD2Digest());
                            break block34;
                        }
                        case 0: {
                            pBEParametersGenerator = new PKCS12ParametersGenerator(DigestFactory.createMD5());
                            break block34;
                        }
                        case 1: {
                            pBEParametersGenerator = new PKCS12ParametersGenerator(DigestFactory.createSHA1());
                            break block34;
                        }
                        case 2: {
                            pBEParametersGenerator = new PKCS12ParametersGenerator(new RIPEMD160Digest());
                            break block34;
                        }
                        case 3: {
                            pBEParametersGenerator = new PKCS12ParametersGenerator(new TigerDigest());
                            break block34;
                        }
                        case 4: {
                            pBEParametersGenerator = new PKCS12ParametersGenerator(DigestFactory.createSHA256());
                            break block34;
                        }
                        case 6: {
                            pBEParametersGenerator = new PKCS12ParametersGenerator(new GOST3411Digest());
                            break block34;
                        }
                        case 7: {
                            pBEParametersGenerator = new PKCS12ParametersGenerator(DigestFactory.createSHA224());
                            break block34;
                        }
                        case 8: {
                            pBEParametersGenerator = new PKCS12ParametersGenerator(DigestFactory.createSHA384());
                            break block34;
                        }
                        case 9: {
                            pBEParametersGenerator = new PKCS12ParametersGenerator(DigestFactory.createSHA512());
                            break block34;
                        }
                        default: {
                            throw new IllegalStateException("unknown digest scheme for PBE encryption.");
                        }
                    }
                }
                pBEParametersGenerator = new OpenSSLPBEParametersGenerator();
            }
            return pBEParametersGenerator;
        }

        public static CipherParameters makePBEParameters(byte[] byArray, int n, int n2, int n3, int n4, AlgorithmParameterSpec algorithmParameterSpec, String string) throws InvalidAlgorithmParameterException {
            if (algorithmParameterSpec == null || !(algorithmParameterSpec instanceof PBEParameterSpec)) {
                throw new InvalidAlgorithmParameterException("Need a PBEParameter spec with a PBE key.");
            }
            PBEParameterSpec pBEParameterSpec = (PBEParameterSpec)algorithmParameterSpec;
            PBEParametersGenerator pBEParametersGenerator = Util.makePBEGenerator(n, n2);
            byte[] byArray2 = byArray;
            pBEParametersGenerator.init(byArray2, pBEParameterSpec.getSalt(), pBEParameterSpec.getIterationCount());
            CipherParameters cipherParameters = n4 != 0 ? pBEParametersGenerator.generateDerivedParameters(n3, n4) : pBEParametersGenerator.generateDerivedParameters(n3);
            if (string.startsWith("DES")) {
                if (cipherParameters instanceof ParametersWithIV) {
                    KeyParameter keyParameter = (KeyParameter)((ParametersWithIV)cipherParameters).getParameters();
                    DESParameters.setOddParity(keyParameter.getKey());
                } else {
                    KeyParameter keyParameter = (KeyParameter)cipherParameters;
                    DESParameters.setOddParity(keyParameter.getKey());
                }
            }
            return cipherParameters;
        }

        public static CipherParameters makePBEParameters(BCPBEKey bCPBEKey, AlgorithmParameterSpec algorithmParameterSpec, String string) {
            if (algorithmParameterSpec == null || !(algorithmParameterSpec instanceof PBEParameterSpec)) {
                throw new IllegalArgumentException("Need a PBEParameter spec with a PBE key.");
            }
            PBEParameterSpec pBEParameterSpec = (PBEParameterSpec)algorithmParameterSpec;
            PBEParametersGenerator pBEParametersGenerator = Util.makePBEGenerator(bCPBEKey.getType(), bCPBEKey.getDigest());
            byte[] byArray = bCPBEKey.getEncoded();
            if (bCPBEKey.shouldTryWrongPKCS12()) {
                byArray = new byte[2];
            }
            pBEParametersGenerator.init(byArray, pBEParameterSpec.getSalt(), pBEParameterSpec.getIterationCount());
            CipherParameters cipherParameters = bCPBEKey.getIvSize() != 0 ? pBEParametersGenerator.generateDerivedParameters(bCPBEKey.getKeySize(), bCPBEKey.getIvSize()) : pBEParametersGenerator.generateDerivedParameters(bCPBEKey.getKeySize());
            if (string.startsWith("DES")) {
                if (cipherParameters instanceof ParametersWithIV) {
                    KeyParameter keyParameter = (KeyParameter)((ParametersWithIV)cipherParameters).getParameters();
                    DESParameters.setOddParity(keyParameter.getKey());
                } else {
                    KeyParameter keyParameter = (KeyParameter)cipherParameters;
                    DESParameters.setOddParity(keyParameter.getKey());
                }
            }
            return cipherParameters;
        }

        public static CipherParameters makePBEMacParameters(BCPBEKey bCPBEKey, AlgorithmParameterSpec algorithmParameterSpec) {
            if (algorithmParameterSpec == null || !(algorithmParameterSpec instanceof PBEParameterSpec)) {
                throw new IllegalArgumentException("Need a PBEParameter spec with a PBE key.");
            }
            PBEParameterSpec pBEParameterSpec = (PBEParameterSpec)algorithmParameterSpec;
            PBEParametersGenerator pBEParametersGenerator = Util.makePBEGenerator(bCPBEKey.getType(), bCPBEKey.getDigest());
            byte[] byArray = bCPBEKey.getEncoded();
            pBEParametersGenerator.init(byArray, pBEParameterSpec.getSalt(), pBEParameterSpec.getIterationCount());
            CipherParameters cipherParameters = pBEParametersGenerator.generateDerivedMacParameters(bCPBEKey.getKeySize());
            return cipherParameters;
        }

        public static CipherParameters makePBEMacParameters(PBEKeySpec pBEKeySpec, int n, int n2, int n3) {
            PBEParametersGenerator pBEParametersGenerator = Util.makePBEGenerator(n, n2);
            byte[] byArray = Util.convertPassword(n, pBEKeySpec);
            pBEParametersGenerator.init(byArray, pBEKeySpec.getSalt(), pBEKeySpec.getIterationCount());
            CipherParameters cipherParameters = pBEParametersGenerator.generateDerivedMacParameters(n3);
            for (int i = 0; i != byArray.length; ++i) {
                byArray[i] = 0;
            }
            return cipherParameters;
        }

        public static CipherParameters makePBEParameters(PBEKeySpec pBEKeySpec, int n, int n2, int n3, int n4) {
            PBEParametersGenerator pBEParametersGenerator = Util.makePBEGenerator(n, n2);
            byte[] byArray = Util.convertPassword(n, pBEKeySpec);
            pBEParametersGenerator.init(byArray, pBEKeySpec.getSalt(), pBEKeySpec.getIterationCount());
            CipherParameters cipherParameters = n4 != 0 ? pBEParametersGenerator.generateDerivedParameters(n3, n4) : pBEParametersGenerator.generateDerivedParameters(n3);
            for (int i = 0; i != byArray.length; ++i) {
                byArray[i] = 0;
            }
            return cipherParameters;
        }

        public static CipherParameters makePBEMacParameters(SecretKey secretKey, int n, int n2, int n3, PBEParameterSpec pBEParameterSpec) {
            PBEParametersGenerator pBEParametersGenerator = Util.makePBEGenerator(n, n2);
            byte[] byArray = secretKey.getEncoded();
            pBEParametersGenerator.init(secretKey.getEncoded(), pBEParameterSpec.getSalt(), pBEParameterSpec.getIterationCount());
            CipherParameters cipherParameters = pBEParametersGenerator.generateDerivedMacParameters(n3);
            for (int i = 0; i != byArray.length; ++i) {
                byArray[i] = 0;
            }
            return cipherParameters;
        }

        private static byte[] convertPassword(int n, PBEKeySpec pBEKeySpec) {
            byte[] byArray = n == 2 ? PBEParametersGenerator.PKCS12PasswordToBytes(pBEKeySpec.getPassword()) : (n == 5 || n == 4 ? PBEParametersGenerator.PKCS5PasswordToUTF8Bytes(pBEKeySpec.getPassword()) : PBEParametersGenerator.PKCS5PasswordToBytes(pBEKeySpec.getPassword()));
            return byArray;
        }
    }
}

