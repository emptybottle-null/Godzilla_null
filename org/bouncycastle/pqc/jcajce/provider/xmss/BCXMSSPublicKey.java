/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.jcajce.provider.xmss;

import java.io.IOException;
import java.security.PublicKey;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.pqc.asn1.PQCObjectIdentifiers;
import org.bouncycastle.pqc.asn1.XMSSKeyParams;
import org.bouncycastle.pqc.asn1.XMSSPublicKey;
import org.bouncycastle.pqc.crypto.xmss.XMSSParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSPublicKeyParameters;
import org.bouncycastle.pqc.jcajce.interfaces.XMSSKey;
import org.bouncycastle.pqc.jcajce.provider.xmss.DigestUtil;
import org.bouncycastle.util.Arrays;

public class BCXMSSPublicKey
implements PublicKey,
XMSSKey {
    private final XMSSPublicKeyParameters keyParams;
    private final ASN1ObjectIdentifier treeDigest;

    public BCXMSSPublicKey(ASN1ObjectIdentifier aSN1ObjectIdentifier, XMSSPublicKeyParameters xMSSPublicKeyParameters) {
        this.treeDigest = aSN1ObjectIdentifier;
        this.keyParams = xMSSPublicKeyParameters;
    }

    public BCXMSSPublicKey(SubjectPublicKeyInfo subjectPublicKeyInfo) throws IOException {
        XMSSKeyParams xMSSKeyParams = XMSSKeyParams.getInstance(subjectPublicKeyInfo.getAlgorithm().getParameters());
        this.treeDigest = xMSSKeyParams.getTreeDigest().getAlgorithm();
        XMSSPublicKey xMSSPublicKey = XMSSPublicKey.getInstance(subjectPublicKeyInfo.parsePublicKey());
        this.keyParams = new XMSSPublicKeyParameters.Builder(new XMSSParameters(xMSSKeyParams.getHeight(), DigestUtil.getDigest(this.treeDigest))).withPublicSeed(xMSSPublicKey.getPublicSeed()).withRoot(xMSSPublicKey.getRoot()).build();
    }

    public final String getAlgorithm() {
        return "XMSS";
    }

    public byte[] getEncoded() {
        try {
            AlgorithmIdentifier algorithmIdentifier = new AlgorithmIdentifier(PQCObjectIdentifiers.xmss, new XMSSKeyParams(this.keyParams.getParameters().getHeight(), new AlgorithmIdentifier(this.treeDigest)));
            SubjectPublicKeyInfo subjectPublicKeyInfo = new SubjectPublicKeyInfo(algorithmIdentifier, new XMSSPublicKey(this.keyParams.getPublicSeed(), this.keyParams.getRoot()));
            return subjectPublicKeyInfo.getEncoded();
        } catch (IOException iOException) {
            return null;
        }
    }

    public String getFormat() {
        return "X.509";
    }

    CipherParameters getKeyParams() {
        return this.keyParams;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof BCXMSSPublicKey) {
            BCXMSSPublicKey bCXMSSPublicKey = (BCXMSSPublicKey)object;
            return this.treeDigest.equals(bCXMSSPublicKey.treeDigest) && Arrays.areEqual(this.keyParams.toByteArray(), bCXMSSPublicKey.keyParams.toByteArray());
        }
        return false;
    }

    public int hashCode() {
        return this.treeDigest.hashCode() + 37 * Arrays.hashCode(this.keyParams.toByteArray());
    }

    public int getHeight() {
        return this.keyParams.getParameters().getHeight();
    }

    public String getTreeDigest() {
        return DigestUtil.getXMSSDigestName(this.treeDigest);
    }
}

