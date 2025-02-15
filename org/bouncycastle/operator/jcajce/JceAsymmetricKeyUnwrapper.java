/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.operator.jcajce;

import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.ProviderException;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.AsymmetricKeyUnwrapper;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OperatorException;
import org.bouncycastle.operator.jcajce.JceGenericKey;
import org.bouncycastle.operator.jcajce.OperatorHelper;

public class JceAsymmetricKeyUnwrapper
extends AsymmetricKeyUnwrapper {
    private OperatorHelper helper = new OperatorHelper(new DefaultJcaJceHelper());
    private Map extraMappings = new HashMap();
    private PrivateKey privKey;
    private boolean unwrappedKeyMustBeEncodable;

    public JceAsymmetricKeyUnwrapper(AlgorithmIdentifier algorithmIdentifier, PrivateKey privateKey) {
        super(algorithmIdentifier);
        this.privKey = privateKey;
    }

    public JceAsymmetricKeyUnwrapper setProvider(Provider provider) {
        this.helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        return this;
    }

    public JceAsymmetricKeyUnwrapper setProvider(String string) {
        this.helper = new OperatorHelper(new NamedJcaJceHelper(string));
        return this;
    }

    public JceAsymmetricKeyUnwrapper setMustProduceEncodableUnwrappedKey(boolean bl) {
        this.unwrappedKeyMustBeEncodable = bl;
        return this;
    }

    public JceAsymmetricKeyUnwrapper setAlgorithmMapping(ASN1ObjectIdentifier aSN1ObjectIdentifier, String string) {
        this.extraMappings.put(aSN1ObjectIdentifier, string);
        return this;
    }

    public GenericKey generateUnwrappedKey(AlgorithmIdentifier algorithmIdentifier, byte[] byArray) throws OperatorException {
        try {
            Cipher cipher;
            Key key;
            block15: {
                key = null;
                cipher = this.helper.createAsymmetricWrapper(this.getAlgorithmIdentifier().getAlgorithm(), this.extraMappings);
                AlgorithmParameters algorithmParameters = this.helper.createAlgorithmParameters(this.getAlgorithmIdentifier());
                try {
                    if (algorithmParameters != null) {
                        cipher.init(4, (Key)this.privKey, algorithmParameters);
                    } else {
                        cipher.init(4, this.privKey);
                    }
                    key = cipher.unwrap(byArray, this.helper.getKeyAlgorithmName(algorithmIdentifier.getAlgorithm()), 3);
                    if (!this.unwrappedKeyMustBeEncodable) break block15;
                    try {
                        byte[] byArray2 = key.getEncoded();
                        if (byArray2 == null || byArray2.length == 0) {
                            key = null;
                        }
                    } catch (Exception exception) {
                        key = null;
                    }
                } catch (GeneralSecurityException generalSecurityException) {
                } catch (IllegalStateException illegalStateException) {
                } catch (UnsupportedOperationException unsupportedOperationException) {
                } catch (ProviderException providerException) {
                    // empty catch block
                }
            }
            if (key == null) {
                cipher.init(2, this.privKey);
                key = new SecretKeySpec(cipher.doFinal(byArray), algorithmIdentifier.getAlgorithm().getId());
            }
            return new JceGenericKey(algorithmIdentifier, key);
        } catch (InvalidKeyException invalidKeyException) {
            throw new OperatorException("key invalid: " + invalidKeyException.getMessage(), invalidKeyException);
        } catch (IllegalBlockSizeException illegalBlockSizeException) {
            throw new OperatorException("illegal blocksize: " + illegalBlockSizeException.getMessage(), illegalBlockSizeException);
        } catch (BadPaddingException badPaddingException) {
            throw new OperatorException("bad padding: " + badPaddingException.getMessage(), badPaddingException);
        }
    }
}

