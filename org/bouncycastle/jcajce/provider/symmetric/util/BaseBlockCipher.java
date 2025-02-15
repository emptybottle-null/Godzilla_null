/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.symmetric.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.interfaces.PBEKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.RC2ParameterSpec;
import javax.crypto.spec.RC5ParameterSpec;
import org.bouncycastle.asn1.cms.GCMParameters;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.engines.DSTU7624Engine;
import org.bouncycastle.crypto.modes.AEADBlockCipher;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.modes.CCMBlockCipher;
import org.bouncycastle.crypto.modes.CFBBlockCipher;
import org.bouncycastle.crypto.modes.CTSBlockCipher;
import org.bouncycastle.crypto.modes.EAXBlockCipher;
import org.bouncycastle.crypto.modes.GCFBBlockCipher;
import org.bouncycastle.crypto.modes.GCMBlockCipher;
import org.bouncycastle.crypto.modes.GOFBBlockCipher;
import org.bouncycastle.crypto.modes.KCCMBlockCipher;
import org.bouncycastle.crypto.modes.KCTRBlockCipher;
import org.bouncycastle.crypto.modes.KGCMBlockCipher;
import org.bouncycastle.crypto.modes.OCBBlockCipher;
import org.bouncycastle.crypto.modes.OFBBlockCipher;
import org.bouncycastle.crypto.modes.OpenPGPCFBBlockCipher;
import org.bouncycastle.crypto.modes.PGPCFBBlockCipher;
import org.bouncycastle.crypto.modes.SICBlockCipher;
import org.bouncycastle.crypto.paddings.BlockCipherPadding;
import org.bouncycastle.crypto.paddings.ISO10126d2Padding;
import org.bouncycastle.crypto.paddings.ISO7816d4Padding;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.paddings.TBCPadding;
import org.bouncycastle.crypto.paddings.X923Padding;
import org.bouncycastle.crypto.paddings.ZeroBytePadding;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.params.ParametersWithSBox;
import org.bouncycastle.crypto.params.RC2Parameters;
import org.bouncycastle.crypto.params.RC5Parameters;
import org.bouncycastle.jcajce.PBKDF1Key;
import org.bouncycastle.jcajce.PBKDF1KeyWithParameters;
import org.bouncycastle.jcajce.PKCS12Key;
import org.bouncycastle.jcajce.PKCS12KeyWithParameters;
import org.bouncycastle.jcajce.provider.symmetric.util.BCPBEKey;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseWrapCipher;
import org.bouncycastle.jcajce.provider.symmetric.util.BlockCipherProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.ClassUtil;
import org.bouncycastle.jcajce.provider.symmetric.util.PBE;
import org.bouncycastle.jcajce.spec.AEADParameterSpec;
import org.bouncycastle.jcajce.spec.GOST28147ParameterSpec;
import org.bouncycastle.jcajce.spec.RepeatedSecretKeySpec;
import org.bouncycastle.util.Strings;

public class BaseBlockCipher
extends BaseWrapCipher
implements PBE {
    private static final Class gcmSpecClass = ClassUtil.loadClass(BaseBlockCipher.class, "javax.crypto.spec.GCMParameterSpec");
    private Class[] availableSpecs = new Class[]{RC2ParameterSpec.class, RC5ParameterSpec.class, gcmSpecClass, GOST28147ParameterSpec.class, IvParameterSpec.class, PBEParameterSpec.class};
    private BlockCipher baseEngine;
    private BlockCipherProvider engineProvider;
    private GenericBlockCipher cipher;
    private ParametersWithIV ivParam;
    private AEADParameters aeadParams;
    private int keySizeInBits;
    private int scheme = -1;
    private int digest;
    private int ivLength = 0;
    private boolean padded;
    private boolean fixedIv = true;
    private PBEParameterSpec pbeSpec = null;
    private String pbeAlgorithm = null;
    private String modeName = null;

    protected BaseBlockCipher(BlockCipher blockCipher) {
        this.baseEngine = blockCipher;
        this.cipher = new BufferedGenericBlockCipher(blockCipher);
    }

    protected BaseBlockCipher(BlockCipher blockCipher, int n, int n2, int n3, int n4) {
        this.baseEngine = blockCipher;
        this.scheme = n;
        this.digest = n2;
        this.keySizeInBits = n3;
        this.ivLength = n4;
        this.cipher = new BufferedGenericBlockCipher(blockCipher);
    }

    protected BaseBlockCipher(BlockCipherProvider blockCipherProvider) {
        this.baseEngine = blockCipherProvider.get();
        this.engineProvider = blockCipherProvider;
        this.cipher = new BufferedGenericBlockCipher(blockCipherProvider.get());
    }

    protected BaseBlockCipher(AEADBlockCipher aEADBlockCipher) {
        this.baseEngine = aEADBlockCipher.getUnderlyingCipher();
        this.ivLength = this.baseEngine.getBlockSize();
        this.cipher = new AEADGenericBlockCipher(aEADBlockCipher);
    }

    protected BaseBlockCipher(AEADBlockCipher aEADBlockCipher, boolean bl, int n) {
        this.baseEngine = aEADBlockCipher.getUnderlyingCipher();
        this.fixedIv = bl;
        this.ivLength = n;
        this.cipher = new AEADGenericBlockCipher(aEADBlockCipher);
    }

    protected BaseBlockCipher(BlockCipher blockCipher, int n) {
        this.baseEngine = blockCipher;
        this.cipher = new BufferedGenericBlockCipher(blockCipher);
        this.ivLength = n / 8;
    }

    protected BaseBlockCipher(BufferedBlockCipher bufferedBlockCipher, int n) {
        this.baseEngine = bufferedBlockCipher.getUnderlyingCipher();
        this.cipher = new BufferedGenericBlockCipher(bufferedBlockCipher);
        this.ivLength = n / 8;
    }

    protected int engineGetBlockSize() {
        return this.baseEngine.getBlockSize();
    }

    protected byte[] engineGetIV() {
        if (this.aeadParams != null) {
            return this.aeadParams.getNonce();
        }
        return this.ivParam != null ? this.ivParam.getIV() : null;
    }

    protected int engineGetKeySize(Key key) {
        return key.getEncoded().length * 8;
    }

    protected int engineGetOutputSize(int n) {
        return this.cipher.getOutputSize(n);
    }

    protected AlgorithmParameters engineGetParameters() {
        if (this.engineParams == null) {
            if (this.pbeSpec != null) {
                try {
                    this.engineParams = this.createParametersInstance(this.pbeAlgorithm);
                    this.engineParams.init(this.pbeSpec);
                } catch (Exception exception) {
                    return null;
                }
            }
            if (this.aeadParams != null) {
                try {
                    this.engineParams = this.createParametersInstance("GCM");
                    this.engineParams.init(new GCMParameters(this.aeadParams.getNonce(), this.aeadParams.getMacSize() / 8).getEncoded());
                } catch (Exception exception) {
                    throw new RuntimeException(exception.toString());
                }
            }
            if (this.ivParam != null) {
                String string = this.cipher.getUnderlyingCipher().getAlgorithmName();
                if (string.indexOf(47) >= 0) {
                    string = string.substring(0, string.indexOf(47));
                }
                try {
                    this.engineParams = this.createParametersInstance(string);
                    this.engineParams.init(new IvParameterSpec(this.ivParam.getIV()));
                } catch (Exception exception) {
                    throw new RuntimeException(exception.toString());
                }
            }
        }
        return this.engineParams;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected void engineSetMode(String string) throws NoSuchAlgorithmException {
        this.modeName = Strings.toUpperCase(string);
        if (this.modeName.equals("ECB")) {
            this.ivLength = 0;
            this.cipher = new BufferedGenericBlockCipher(this.baseEngine);
            return;
        } else if (this.modeName.equals("CBC")) {
            this.ivLength = this.baseEngine.getBlockSize();
            this.cipher = new BufferedGenericBlockCipher(new CBCBlockCipher(this.baseEngine));
            return;
        } else if (this.modeName.startsWith("OFB")) {
            this.ivLength = this.baseEngine.getBlockSize();
            if (this.modeName.length() != 3) {
                int n = Integer.parseInt(this.modeName.substring(3));
                this.cipher = new BufferedGenericBlockCipher(new OFBBlockCipher(this.baseEngine, n));
                return;
            } else {
                this.cipher = new BufferedGenericBlockCipher(new OFBBlockCipher(this.baseEngine, 8 * this.baseEngine.getBlockSize()));
            }
            return;
        } else if (this.modeName.startsWith("CFB")) {
            this.ivLength = this.baseEngine.getBlockSize();
            if (this.modeName.length() != 3) {
                int n = Integer.parseInt(this.modeName.substring(3));
                this.cipher = new BufferedGenericBlockCipher(new CFBBlockCipher(this.baseEngine, n));
                return;
            } else {
                this.cipher = new BufferedGenericBlockCipher(new CFBBlockCipher(this.baseEngine, 8 * this.baseEngine.getBlockSize()));
            }
            return;
        } else if (this.modeName.startsWith("PGP")) {
            boolean bl = this.modeName.equalsIgnoreCase("PGPCFBwithIV");
            this.ivLength = this.baseEngine.getBlockSize();
            this.cipher = new BufferedGenericBlockCipher(new PGPCFBBlockCipher(this.baseEngine, bl));
            return;
        } else if (this.modeName.equalsIgnoreCase("OpenPGPCFB")) {
            this.ivLength = 0;
            this.cipher = new BufferedGenericBlockCipher(new OpenPGPCFBBlockCipher(this.baseEngine));
            return;
        } else if (this.modeName.startsWith("SIC")) {
            this.ivLength = this.baseEngine.getBlockSize();
            if (this.ivLength < 16) {
                throw new IllegalArgumentException("Warning: SIC-Mode can become a twotime-pad if the blocksize of the cipher is too small. Use a cipher with a block size of at least 128 bits (e.g. AES)");
            }
            this.fixedIv = false;
            this.cipher = new BufferedGenericBlockCipher(new BufferedBlockCipher(new SICBlockCipher(this.baseEngine)));
            return;
        } else if (this.modeName.startsWith("CTR")) {
            this.ivLength = this.baseEngine.getBlockSize();
            this.fixedIv = false;
            this.cipher = this.baseEngine instanceof DSTU7624Engine ? new BufferedGenericBlockCipher(new BufferedBlockCipher(new KCTRBlockCipher(this.baseEngine))) : new BufferedGenericBlockCipher(new BufferedBlockCipher(new SICBlockCipher(this.baseEngine)));
            return;
        } else if (this.modeName.startsWith("GOFB")) {
            this.ivLength = this.baseEngine.getBlockSize();
            this.cipher = new BufferedGenericBlockCipher(new BufferedBlockCipher(new GOFBBlockCipher(this.baseEngine)));
            return;
        } else if (this.modeName.startsWith("GCFB")) {
            this.ivLength = this.baseEngine.getBlockSize();
            this.cipher = new BufferedGenericBlockCipher(new BufferedBlockCipher(new GCFBBlockCipher(this.baseEngine)));
            return;
        } else if (this.modeName.startsWith("CTS")) {
            this.ivLength = this.baseEngine.getBlockSize();
            this.cipher = new BufferedGenericBlockCipher(new CTSBlockCipher(new CBCBlockCipher(this.baseEngine)));
            return;
        } else if (this.modeName.startsWith("CCM")) {
            this.ivLength = 13;
            this.cipher = this.baseEngine instanceof DSTU7624Engine ? new AEADGenericBlockCipher(new KCCMBlockCipher(this.baseEngine)) : new AEADGenericBlockCipher(new CCMBlockCipher(this.baseEngine));
            return;
        } else if (this.modeName.startsWith("OCB")) {
            if (this.engineProvider == null) throw new NoSuchAlgorithmException("can't support mode " + string);
            this.ivLength = 15;
            this.cipher = new AEADGenericBlockCipher(new OCBBlockCipher(this.baseEngine, this.engineProvider.get()));
            return;
        } else if (this.modeName.startsWith("EAX")) {
            this.ivLength = this.baseEngine.getBlockSize();
            this.cipher = new AEADGenericBlockCipher(new EAXBlockCipher(this.baseEngine));
            return;
        } else {
            if (!this.modeName.startsWith("GCM")) throw new NoSuchAlgorithmException("can't support mode " + string);
            this.ivLength = this.baseEngine.getBlockSize();
            this.cipher = this.baseEngine instanceof DSTU7624Engine ? new AEADGenericBlockCipher(new KGCMBlockCipher(this.baseEngine)) : new AEADGenericBlockCipher(new GCMBlockCipher(this.baseEngine));
        }
    }

    protected void engineSetPadding(String string) throws NoSuchPaddingException {
        String string2 = Strings.toUpperCase(string);
        if (string2.equals("NOPADDING")) {
            if (this.cipher.wrapOnNoPadding()) {
                this.cipher = new BufferedGenericBlockCipher(new BufferedBlockCipher(this.cipher.getUnderlyingCipher()));
            }
        } else if (string2.equals("WITHCTS")) {
            this.cipher = new BufferedGenericBlockCipher(new CTSBlockCipher(this.cipher.getUnderlyingCipher()));
        } else {
            this.padded = true;
            if (this.isAEADModeName(this.modeName)) {
                throw new NoSuchPaddingException("Only NoPadding can be used with AEAD modes.");
            }
            if (string2.equals("PKCS5PADDING") || string2.equals("PKCS7PADDING")) {
                this.cipher = new BufferedGenericBlockCipher(this.cipher.getUnderlyingCipher());
            } else if (string2.equals("ZEROBYTEPADDING")) {
                this.cipher = new BufferedGenericBlockCipher(this.cipher.getUnderlyingCipher(), new ZeroBytePadding());
            } else if (string2.equals("ISO10126PADDING") || string2.equals("ISO10126-2PADDING")) {
                this.cipher = new BufferedGenericBlockCipher(this.cipher.getUnderlyingCipher(), new ISO10126d2Padding());
            } else if (string2.equals("X9.23PADDING") || string2.equals("X923PADDING")) {
                this.cipher = new BufferedGenericBlockCipher(this.cipher.getUnderlyingCipher(), new X923Padding());
            } else if (string2.equals("ISO7816-4PADDING") || string2.equals("ISO9797-1PADDING")) {
                this.cipher = new BufferedGenericBlockCipher(this.cipher.getUnderlyingCipher(), new ISO7816d4Padding());
            } else if (string2.equals("TBCPADDING")) {
                this.cipher = new BufferedGenericBlockCipher(this.cipher.getUnderlyingCipher(), new TBCPadding());
            } else {
                throw new NoSuchPaddingException("Padding " + string + " unknown.");
            }
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected void engineInit(int n, Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        Object object;
        Object object2;
        Object object3;
        this.pbeSpec = null;
        this.pbeAlgorithm = null;
        this.engineParams = null;
        this.aeadParams = null;
        if (!(key instanceof SecretKey)) {
            throw new InvalidKeyException("Key for algorithm " + (key != null ? key.getAlgorithm() : null) + " not suitable for symmetric enryption.");
        }
        if (algorithmParameterSpec == null && this.baseEngine.getAlgorithmName().startsWith("RC5-64")) {
            throw new InvalidAlgorithmParameterException("RC5 requires an RC5ParametersSpec to be passed in.");
        }
        if (this.scheme == 2 || key instanceof PKCS12Key) {
            try {
                object3 = (SecretKey)key;
            } catch (Exception exception) {
                throw new InvalidKeyException("PKCS12 requires a SecretKey/PBEKey");
            }
            if (algorithmParameterSpec instanceof PBEParameterSpec) {
                this.pbeSpec = (PBEParameterSpec)algorithmParameterSpec;
            }
            if (object3 instanceof PBEKey && this.pbeSpec == null) {
                object2 = (PBEKey)object3;
                if (object2.getSalt() == null) {
                    throw new InvalidAlgorithmParameterException("PBEKey requires parameters to specify salt");
                }
                this.pbeSpec = new PBEParameterSpec(object2.getSalt(), object2.getIterationCount());
            }
            if (this.pbeSpec == null && !(object3 instanceof PBEKey)) {
                throw new InvalidKeyException("Algorithm requires a PBE key");
            }
            if (key instanceof BCPBEKey) {
                object2 = ((BCPBEKey)key).getParam();
                if (object2 instanceof ParametersWithIV) {
                    object = object2;
                } else {
                    if (object2 != null) throw new InvalidKeyException("Algorithm requires a PBE key suitable for PKCS12");
                    object = PBE.Util.makePBEParameters(object3.getEncoded(), 2, this.digest, this.keySizeInBits, this.ivLength * 8, this.pbeSpec, this.cipher.getAlgorithmName());
                }
            } else {
                object = PBE.Util.makePBEParameters(object3.getEncoded(), 2, this.digest, this.keySizeInBits, this.ivLength * 8, this.pbeSpec, this.cipher.getAlgorithmName());
            }
            if (object instanceof ParametersWithIV) {
                this.ivParam = (ParametersWithIV)object;
            }
        } else if (key instanceof PBKDF1Key) {
            object3 = (PBKDF1Key)key;
            if (algorithmParameterSpec instanceof PBEParameterSpec) {
                this.pbeSpec = (PBEParameterSpec)algorithmParameterSpec;
            }
            if (object3 instanceof PBKDF1KeyWithParameters && this.pbeSpec == null) {
                this.pbeSpec = new PBEParameterSpec(((PBKDF1KeyWithParameters)object3).getSalt(), ((PBKDF1KeyWithParameters)object3).getIterationCount());
            }
            if ((object = PBE.Util.makePBEParameters(((PBKDF1Key)object3).getEncoded(), 0, this.digest, this.keySizeInBits, this.ivLength * 8, this.pbeSpec, this.cipher.getAlgorithmName())) instanceof ParametersWithIV) {
                this.ivParam = (ParametersWithIV)object;
            }
        } else if (key instanceof BCPBEKey) {
            object3 = (BCPBEKey)key;
            this.pbeAlgorithm = ((BCPBEKey)object3).getOID() != null ? ((BCPBEKey)object3).getOID().getId() : ((BCPBEKey)object3).getAlgorithm();
            if (((BCPBEKey)object3).getParam() != null) {
                object = this.adjustParameters(algorithmParameterSpec, ((BCPBEKey)object3).getParam());
            } else {
                if (!(algorithmParameterSpec instanceof PBEParameterSpec)) throw new InvalidAlgorithmParameterException("PBE requires PBE parameters to be set.");
                this.pbeSpec = (PBEParameterSpec)algorithmParameterSpec;
                object = PBE.Util.makePBEParameters((BCPBEKey)object3, algorithmParameterSpec, this.cipher.getUnderlyingCipher().getAlgorithmName());
            }
            if (object instanceof ParametersWithIV) {
                this.ivParam = (ParametersWithIV)object;
            }
        } else if (key instanceof PBEKey) {
            object3 = (PBEKey)key;
            this.pbeSpec = (PBEParameterSpec)algorithmParameterSpec;
            if (object3 instanceof PKCS12KeyWithParameters && this.pbeSpec == null) {
                this.pbeSpec = new PBEParameterSpec(object3.getSalt(), object3.getIterationCount());
            }
            if ((object = PBE.Util.makePBEParameters(object3.getEncoded(), this.scheme, this.digest, this.keySizeInBits, this.ivLength * 8, this.pbeSpec, this.cipher.getAlgorithmName())) instanceof ParametersWithIV) {
                this.ivParam = (ParametersWithIV)object;
            }
        } else if (!(key instanceof RepeatedSecretKeySpec)) {
            if (this.scheme == 0 || this.scheme == 4 || this.scheme == 1 || this.scheme == 5) {
                throw new InvalidKeyException("Algorithm requires a PBE key");
            }
            object = new KeyParameter(key.getEncoded());
        } else {
            object = null;
        }
        if (algorithmParameterSpec instanceof AEADParameterSpec) {
            if (!this.isAEADModeName(this.modeName) && !(this.cipher instanceof AEADGenericBlockCipher)) {
                throw new InvalidAlgorithmParameterException("AEADParameterSpec can only be used with AEAD modes.");
            }
            object3 = (AEADParameterSpec)algorithmParameterSpec;
            object2 = object instanceof ParametersWithIV ? (KeyParameter)((ParametersWithIV)object).getParameters() : (KeyParameter)object;
            this.aeadParams = new AEADParameters((KeyParameter)object2, ((AEADParameterSpec)object3).getMacSizeInBits(), ((AEADParameterSpec)object3).getNonce(), ((AEADParameterSpec)object3).getAssociatedData());
            object = this.aeadParams;
        } else if (algorithmParameterSpec instanceof IvParameterSpec) {
            if (this.ivLength != 0) {
                object3 = (IvParameterSpec)algorithmParameterSpec;
                if (((IvParameterSpec)object3).getIV().length != this.ivLength && !(this.cipher instanceof AEADGenericBlockCipher) && this.fixedIv) {
                    throw new InvalidAlgorithmParameterException("IV must be " + this.ivLength + " bytes long.");
                }
                object = object instanceof ParametersWithIV ? new ParametersWithIV(((ParametersWithIV)object).getParameters(), ((IvParameterSpec)object3).getIV()) : new ParametersWithIV((CipherParameters)object, ((IvParameterSpec)object3).getIV());
                this.ivParam = (ParametersWithIV)object;
            } else if (this.modeName != null && this.modeName.equals("ECB")) {
                throw new InvalidAlgorithmParameterException("ECB mode does not use an IV");
            }
        } else if (algorithmParameterSpec instanceof GOST28147ParameterSpec) {
            object3 = (GOST28147ParameterSpec)algorithmParameterSpec;
            object = new ParametersWithSBox(new KeyParameter(key.getEncoded()), ((GOST28147ParameterSpec)algorithmParameterSpec).getSbox());
            if (((GOST28147ParameterSpec)object3).getIV() != null && this.ivLength != 0) {
                object = object instanceof ParametersWithIV ? new ParametersWithIV(((ParametersWithIV)object).getParameters(), ((GOST28147ParameterSpec)object3).getIV()) : new ParametersWithIV((CipherParameters)object, ((GOST28147ParameterSpec)object3).getIV());
                this.ivParam = (ParametersWithIV)object;
            }
        } else if (algorithmParameterSpec instanceof RC2ParameterSpec) {
            object3 = (RC2ParameterSpec)algorithmParameterSpec;
            object = new RC2Parameters(key.getEncoded(), ((RC2ParameterSpec)algorithmParameterSpec).getEffectiveKeyBits());
            if (((RC2ParameterSpec)object3).getIV() != null && this.ivLength != 0) {
                object = object instanceof ParametersWithIV ? new ParametersWithIV(((ParametersWithIV)object).getParameters(), ((RC2ParameterSpec)object3).getIV()) : new ParametersWithIV((CipherParameters)object, ((RC2ParameterSpec)object3).getIV());
                this.ivParam = (ParametersWithIV)object;
            }
        } else if (algorithmParameterSpec instanceof RC5ParameterSpec) {
            object3 = (RC5ParameterSpec)algorithmParameterSpec;
            object = new RC5Parameters(key.getEncoded(), ((RC5ParameterSpec)algorithmParameterSpec).getRounds());
            if (!this.baseEngine.getAlgorithmName().startsWith("RC5")) throw new InvalidAlgorithmParameterException("RC5 parameters passed to a cipher that is not RC5.");
            if (this.baseEngine.getAlgorithmName().equals("RC5-32")) {
                if (((RC5ParameterSpec)object3).getWordSize() != 32) {
                    throw new InvalidAlgorithmParameterException("RC5 already set up for a word size of 32 not " + ((RC5ParameterSpec)object3).getWordSize() + ".");
                }
            } else if (this.baseEngine.getAlgorithmName().equals("RC5-64") && ((RC5ParameterSpec)object3).getWordSize() != 64) {
                throw new InvalidAlgorithmParameterException("RC5 already set up for a word size of 64 not " + ((RC5ParameterSpec)object3).getWordSize() + ".");
            }
            if (((RC5ParameterSpec)object3).getIV() != null && this.ivLength != 0) {
                object = object instanceof ParametersWithIV ? new ParametersWithIV(((ParametersWithIV)object).getParameters(), ((RC5ParameterSpec)object3).getIV()) : new ParametersWithIV((CipherParameters)object, ((RC5ParameterSpec)object3).getIV());
                this.ivParam = (ParametersWithIV)object;
            }
        } else if (gcmSpecClass != null && gcmSpecClass.isInstance(algorithmParameterSpec)) {
            if (!this.isAEADModeName(this.modeName) && !(this.cipher instanceof AEADGenericBlockCipher)) {
                throw new InvalidAlgorithmParameterException("GCMParameterSpec can only be used with AEAD modes.");
            }
            try {
                object3 = gcmSpecClass.getDeclaredMethod("getTLen", new Class[0]);
                object2 = gcmSpecClass.getDeclaredMethod("getIV", new Class[0]);
                KeyParameter keyParameter = object instanceof ParametersWithIV ? (KeyParameter)((ParametersWithIV)object).getParameters() : (KeyParameter)object;
                this.aeadParams = new AEADParameters(keyParameter, (Integer)((Method)object3).invoke(algorithmParameterSpec, new Object[0]), (byte[])((Method)object2).invoke(algorithmParameterSpec, new Object[0]));
                object = this.aeadParams;
            } catch (Exception exception) {
                throw new InvalidAlgorithmParameterException("Cannot process GCMParameterSpec.");
            }
        } else if (algorithmParameterSpec != null && !(algorithmParameterSpec instanceof PBEParameterSpec)) {
            throw new InvalidAlgorithmParameterException("unknown parameter type.");
        }
        if (this.ivLength != 0 && !(object instanceof ParametersWithIV) && !(object instanceof AEADParameters)) {
            object3 = secureRandom;
            if (object3 == null) {
                object3 = new SecureRandom();
            }
            if (n == 1 || n == 3) {
                object2 = new byte[this.ivLength];
                ((SecureRandom)object3).nextBytes((byte[])object2);
                object = new ParametersWithIV((CipherParameters)object, (byte[])object2);
                this.ivParam = (ParametersWithIV)object;
            } else if (this.cipher.getUnderlyingCipher().getAlgorithmName().indexOf("PGPCFB") < 0) {
                throw new InvalidAlgorithmParameterException("no IV set when one expected");
            }
        }
        if (secureRandom != null && this.padded) {
            object = new ParametersWithRandom((CipherParameters)object, secureRandom);
        }
        try {
            switch (n) {
                case 1: 
                case 3: {
                    this.cipher.init(true, (CipherParameters)object);
                    break;
                }
                case 2: 
                case 4: {
                    this.cipher.init(false, (CipherParameters)object);
                    break;
                }
                default: {
                    throw new InvalidParameterException("unknown opmode " + n + " passed");
                }
            }
            if (!(this.cipher instanceof AEADGenericBlockCipher) || this.aeadParams != null) return;
            object3 = ((AEADGenericBlockCipher)this.cipher).cipher;
            this.aeadParams = new AEADParameters((KeyParameter)this.ivParam.getParameters(), object3.getMac().length * 8, this.ivParam.getIV());
            return;
        } catch (Exception exception) {
            throw new InvalidKeyOrParametersException(exception.getMessage(), exception);
        }
    }

    private CipherParameters adjustParameters(AlgorithmParameterSpec algorithmParameterSpec, CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithIV) {
            CipherParameters cipherParameters2 = ((ParametersWithIV)cipherParameters).getParameters();
            if (algorithmParameterSpec instanceof IvParameterSpec) {
                IvParameterSpec ivParameterSpec = (IvParameterSpec)algorithmParameterSpec;
                this.ivParam = new ParametersWithIV(cipherParameters2, ivParameterSpec.getIV());
                cipherParameters = this.ivParam;
            } else if (algorithmParameterSpec instanceof GOST28147ParameterSpec) {
                GOST28147ParameterSpec gOST28147ParameterSpec = (GOST28147ParameterSpec)algorithmParameterSpec;
                cipherParameters = new ParametersWithSBox(cipherParameters, gOST28147ParameterSpec.getSbox());
                if (gOST28147ParameterSpec.getIV() != null && this.ivLength != 0) {
                    this.ivParam = new ParametersWithIV(cipherParameters2, gOST28147ParameterSpec.getIV());
                    cipherParameters = this.ivParam;
                }
            }
        } else if (algorithmParameterSpec instanceof IvParameterSpec) {
            IvParameterSpec ivParameterSpec = (IvParameterSpec)algorithmParameterSpec;
            this.ivParam = new ParametersWithIV(cipherParameters, ivParameterSpec.getIV());
            cipherParameters = this.ivParam;
        } else if (algorithmParameterSpec instanceof GOST28147ParameterSpec) {
            GOST28147ParameterSpec gOST28147ParameterSpec = (GOST28147ParameterSpec)algorithmParameterSpec;
            cipherParameters = new ParametersWithSBox(cipherParameters, gOST28147ParameterSpec.getSbox());
            if (gOST28147ParameterSpec.getIV() != null && this.ivLength != 0) {
                cipherParameters = new ParametersWithIV(cipherParameters, gOST28147ParameterSpec.getIV());
            }
        }
        return cipherParameters;
    }

    protected void engineInit(int n, Key key, AlgorithmParameters algorithmParameters, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        AlgorithmParameterSpec algorithmParameterSpec = null;
        if (algorithmParameters != null) {
            for (int i = 0; i != this.availableSpecs.length; ++i) {
                if (this.availableSpecs[i] == null) continue;
                try {
                    algorithmParameterSpec = (AlgorithmParameterSpec)algorithmParameters.getParameterSpec(this.availableSpecs[i]);
                    break;
                } catch (Exception exception) {
                    // empty catch block
                }
            }
            if (algorithmParameterSpec == null) {
                throw new InvalidAlgorithmParameterException("can't handle parameter " + algorithmParameters.toString());
            }
        }
        this.engineInit(n, key, algorithmParameterSpec, secureRandom);
        this.engineParams = algorithmParameters;
    }

    protected void engineInit(int n, Key key, SecureRandom secureRandom) throws InvalidKeyException {
        try {
            this.engineInit(n, key, (AlgorithmParameterSpec)null, secureRandom);
        } catch (InvalidAlgorithmParameterException invalidAlgorithmParameterException) {
            throw new InvalidKeyException(invalidAlgorithmParameterException.getMessage());
        }
    }

    protected void engineUpdateAAD(byte[] byArray, int n, int n2) {
        this.cipher.updateAAD(byArray, n, n2);
    }

    protected void engineUpdateAAD(ByteBuffer byteBuffer) {
        int n = byteBuffer.arrayOffset() + byteBuffer.position();
        int n2 = byteBuffer.limit() - byteBuffer.position();
        this.engineUpdateAAD(byteBuffer.array(), n, n2);
    }

    protected byte[] engineUpdate(byte[] byArray, int n, int n2) {
        int n3 = this.cipher.getUpdateOutputSize(n2);
        if (n3 > 0) {
            byte[] byArray2 = new byte[n3];
            int n4 = this.cipher.processBytes(byArray, n, n2, byArray2, 0);
            if (n4 == 0) {
                return null;
            }
            if (n4 != byArray2.length) {
                byte[] byArray3 = new byte[n4];
                System.arraycopy(byArray2, 0, byArray3, 0, n4);
                return byArray3;
            }
            return byArray2;
        }
        this.cipher.processBytes(byArray, n, n2, null, 0);
        return null;
    }

    protected int engineUpdate(byte[] byArray, int n, int n2, byte[] byArray2, int n3) throws ShortBufferException {
        if (n3 + this.cipher.getUpdateOutputSize(n2) > byArray2.length) {
            throw new ShortBufferException("output buffer too short for input.");
        }
        try {
            return this.cipher.processBytes(byArray, n, n2, byArray2, n3);
        } catch (DataLengthException dataLengthException) {
            throw new IllegalStateException(dataLengthException.toString());
        }
    }

    protected byte[] engineDoFinal(byte[] byArray, int n, int n2) throws IllegalBlockSizeException, BadPaddingException {
        int n3 = 0;
        byte[] byArray2 = new byte[this.engineGetOutputSize(n2)];
        if (n2 != 0) {
            n3 = this.cipher.processBytes(byArray, n, n2, byArray2, 0);
        }
        try {
            n3 += this.cipher.doFinal(byArray2, n3);
        } catch (DataLengthException dataLengthException) {
            throw new IllegalBlockSizeException(dataLengthException.getMessage());
        }
        if (n3 == byArray2.length) {
            return byArray2;
        }
        byte[] byArray3 = new byte[n3];
        System.arraycopy(byArray2, 0, byArray3, 0, n3);
        return byArray3;
    }

    protected int engineDoFinal(byte[] byArray, int n, int n2, byte[] byArray2, int n3) throws IllegalBlockSizeException, BadPaddingException, ShortBufferException {
        int n4 = 0;
        if (n3 + this.engineGetOutputSize(n2) > byArray2.length) {
            throw new ShortBufferException("output buffer too short for input.");
        }
        try {
            if (n2 != 0) {
                n4 = this.cipher.processBytes(byArray, n, n2, byArray2, n3);
            }
            return n4 + this.cipher.doFinal(byArray2, n3 + n4);
        } catch (OutputLengthException outputLengthException) {
            throw new IllegalBlockSizeException(outputLengthException.getMessage());
        } catch (DataLengthException dataLengthException) {
            throw new IllegalBlockSizeException(dataLengthException.getMessage());
        }
    }

    private boolean isAEADModeName(String string) {
        return "CCM".equals(string) || "EAX".equals(string) || "GCM".equals(string) || "OCB".equals(string);
    }

    private static class AEADGenericBlockCipher
    implements GenericBlockCipher {
        private static final Constructor aeadBadTagConstructor;
        private AEADBlockCipher cipher;

        private static Constructor findExceptionConstructor(Class clazz) {
            try {
                return clazz.getConstructor(String.class);
            } catch (Exception exception) {
                return null;
            }
        }

        AEADGenericBlockCipher(AEADBlockCipher aEADBlockCipher) {
            this.cipher = aEADBlockCipher;
        }

        public void init(boolean bl, CipherParameters cipherParameters) throws IllegalArgumentException {
            this.cipher.init(bl, cipherParameters);
        }

        public String getAlgorithmName() {
            return this.cipher.getUnderlyingCipher().getAlgorithmName();
        }

        public boolean wrapOnNoPadding() {
            return false;
        }

        public BlockCipher getUnderlyingCipher() {
            return this.cipher.getUnderlyingCipher();
        }

        public int getOutputSize(int n) {
            return this.cipher.getOutputSize(n);
        }

        public int getUpdateOutputSize(int n) {
            return this.cipher.getUpdateOutputSize(n);
        }

        public void updateAAD(byte[] byArray, int n, int n2) {
            this.cipher.processAADBytes(byArray, n, n2);
        }

        public int processByte(byte by, byte[] byArray, int n) throws DataLengthException {
            return this.cipher.processByte(by, byArray, n);
        }

        public int processBytes(byte[] byArray, int n, int n2, byte[] byArray2, int n3) throws DataLengthException {
            return this.cipher.processBytes(byArray, n, n2, byArray2, n3);
        }

        public int doFinal(byte[] byArray, int n) throws IllegalStateException, BadPaddingException {
            try {
                return this.cipher.doFinal(byArray, n);
            } catch (InvalidCipherTextException invalidCipherTextException) {
                if (aeadBadTagConstructor != null) {
                    BadPaddingException badPaddingException = null;
                    try {
                        badPaddingException = (BadPaddingException)aeadBadTagConstructor.newInstance(invalidCipherTextException.getMessage());
                    } catch (Exception exception) {
                        // empty catch block
                    }
                    if (badPaddingException != null) {
                        throw badPaddingException;
                    }
                }
                throw new BadPaddingException(invalidCipherTextException.getMessage());
            }
        }

        static {
            Class clazz = ClassUtil.loadClass(BaseBlockCipher.class, "javax.crypto.AEADBadTagException");
            aeadBadTagConstructor = clazz != null ? AEADGenericBlockCipher.findExceptionConstructor(clazz) : null;
        }
    }

    private static class BufferedGenericBlockCipher
    implements GenericBlockCipher {
        private BufferedBlockCipher cipher;

        BufferedGenericBlockCipher(BufferedBlockCipher bufferedBlockCipher) {
            this.cipher = bufferedBlockCipher;
        }

        BufferedGenericBlockCipher(BlockCipher blockCipher) {
            this.cipher = new PaddedBufferedBlockCipher(blockCipher);
        }

        BufferedGenericBlockCipher(BlockCipher blockCipher, BlockCipherPadding blockCipherPadding) {
            this.cipher = new PaddedBufferedBlockCipher(blockCipher, blockCipherPadding);
        }

        public void init(boolean bl, CipherParameters cipherParameters) throws IllegalArgumentException {
            this.cipher.init(bl, cipherParameters);
        }

        public boolean wrapOnNoPadding() {
            return !(this.cipher instanceof CTSBlockCipher);
        }

        public String getAlgorithmName() {
            return this.cipher.getUnderlyingCipher().getAlgorithmName();
        }

        public BlockCipher getUnderlyingCipher() {
            return this.cipher.getUnderlyingCipher();
        }

        public int getOutputSize(int n) {
            return this.cipher.getOutputSize(n);
        }

        public int getUpdateOutputSize(int n) {
            return this.cipher.getUpdateOutputSize(n);
        }

        public void updateAAD(byte[] byArray, int n, int n2) {
            throw new UnsupportedOperationException("AAD is not supported in the current mode.");
        }

        public int processByte(byte by, byte[] byArray, int n) throws DataLengthException {
            return this.cipher.processByte(by, byArray, n);
        }

        public int processBytes(byte[] byArray, int n, int n2, byte[] byArray2, int n3) throws DataLengthException {
            return this.cipher.processBytes(byArray, n, n2, byArray2, n3);
        }

        public int doFinal(byte[] byArray, int n) throws IllegalStateException, BadPaddingException {
            try {
                return this.cipher.doFinal(byArray, n);
            } catch (InvalidCipherTextException invalidCipherTextException) {
                throw new BadPaddingException(invalidCipherTextException.getMessage());
            }
        }
    }

    private static interface GenericBlockCipher {
        public void init(boolean var1, CipherParameters var2) throws IllegalArgumentException;

        public boolean wrapOnNoPadding();

        public String getAlgorithmName();

        public BlockCipher getUnderlyingCipher();

        public int getOutputSize(int var1);

        public int getUpdateOutputSize(int var1);

        public void updateAAD(byte[] var1, int var2, int var3);

        public int processByte(byte var1, byte[] var2, int var3) throws DataLengthException;

        public int processBytes(byte[] var1, int var2, int var3, byte[] var4, int var5) throws DataLengthException;

        public int doFinal(byte[] var1, int var2) throws IllegalStateException, BadPaddingException;
    }

    private static class InvalidKeyOrParametersException
    extends InvalidKeyException {
        private final Throwable cause;

        InvalidKeyOrParametersException(String string, Throwable throwable) {
            super(string);
            this.cause = throwable;
        }

        public Throwable getCause() {
            return this.cause;
        }
    }
}

