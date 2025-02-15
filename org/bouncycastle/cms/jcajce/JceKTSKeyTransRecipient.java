/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms.jcajce;

import java.io.IOException;
import java.security.Key;
import java.security.PrivateKey;
import java.security.Provider;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.cms.IssuerAndSerialNumber;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.KeyTransRecipient;
import org.bouncycastle.cms.KeyTransRecipientId;
import org.bouncycastle.cms.jcajce.CMSUtils;
import org.bouncycastle.cms.jcajce.DefaultJcaJceExtHelper;
import org.bouncycastle.cms.jcajce.EnvelopedDataHelper;
import org.bouncycastle.cms.jcajce.NamedJcaJceExtHelper;
import org.bouncycastle.cms.jcajce.ProviderJcaJceExtHelper;
import org.bouncycastle.operator.OperatorException;
import org.bouncycastle.operator.jcajce.JceKTSKeyUnwrapper;
import org.bouncycastle.util.encoders.Hex;

public abstract class JceKTSKeyTransRecipient
implements KeyTransRecipient {
    private static final byte[] ANONYMOUS_SENDER = Hex.decode("0c14416e6f6e796d6f75732053656e64657220202020");
    private final byte[] partyVInfo;
    private PrivateKey recipientKey;
    protected EnvelopedDataHelper helper;
    protected EnvelopedDataHelper contentHelper;
    protected Map extraMappings;
    protected boolean validateKeySize;
    protected boolean unwrappedKeyMustBeEncodable;

    public JceKTSKeyTransRecipient(PrivateKey privateKey, byte[] byArray) {
        this.contentHelper = this.helper = new EnvelopedDataHelper(new DefaultJcaJceExtHelper());
        this.extraMappings = new HashMap();
        this.validateKeySize = false;
        this.recipientKey = privateKey;
        this.partyVInfo = byArray;
    }

    public JceKTSKeyTransRecipient setProvider(Provider provider) {
        this.contentHelper = this.helper = new EnvelopedDataHelper(new ProviderJcaJceExtHelper(provider));
        return this;
    }

    public JceKTSKeyTransRecipient setProvider(String string) {
        this.contentHelper = this.helper = new EnvelopedDataHelper(new NamedJcaJceExtHelper(string));
        return this;
    }

    public JceKTSKeyTransRecipient setAlgorithmMapping(ASN1ObjectIdentifier aSN1ObjectIdentifier, String string) {
        this.extraMappings.put(aSN1ObjectIdentifier, string);
        return this;
    }

    public JceKTSKeyTransRecipient setContentProvider(Provider provider) {
        this.contentHelper = CMSUtils.createContentHelper(provider);
        return this;
    }

    public JceKTSKeyTransRecipient setContentProvider(String string) {
        this.contentHelper = CMSUtils.createContentHelper(string);
        return this;
    }

    public JceKTSKeyTransRecipient setKeySizeValidation(boolean bl) {
        this.validateKeySize = bl;
        return this;
    }

    protected Key extractSecretKey(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, byte[] byArray) throws CMSException {
        JceKTSKeyUnwrapper jceKTSKeyUnwrapper = this.helper.createAsymmetricUnwrapper(algorithmIdentifier, this.recipientKey, ANONYMOUS_SENDER, this.partyVInfo);
        try {
            Key key = this.helper.getJceKey(algorithmIdentifier2.getAlgorithm(), jceKTSKeyUnwrapper.generateUnwrappedKey(algorithmIdentifier2, byArray));
            if (this.validateKeySize) {
                this.helper.keySizeCheck(algorithmIdentifier2, key);
            }
            return key;
        } catch (OperatorException operatorException) {
            throw new CMSException("exception unwrapping key: " + operatorException.getMessage(), operatorException);
        }
    }

    protected static byte[] getPartyVInfoFromRID(KeyTransRecipientId keyTransRecipientId) throws IOException {
        if (keyTransRecipientId.getSerialNumber() != null) {
            return new IssuerAndSerialNumber(keyTransRecipientId.getIssuer(), keyTransRecipientId.getSerialNumber()).getEncoded("DER");
        }
        return new DEROctetString(keyTransRecipientId.getSubjectKeyIdentifier()).getEncoded();
    }
}

