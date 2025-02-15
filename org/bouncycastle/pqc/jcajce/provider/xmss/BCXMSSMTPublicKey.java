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
import org.bouncycastle.pqc.asn1.XMSSMTKeyParams;
import org.bouncycastle.pqc.asn1.XMSSPublicKey;
import org.bouncycastle.pqc.crypto.xmss.XMSSMTParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSMTPublicKeyParameters;
import org.bouncycastle.pqc.jcajce.interfaces.XMSSMTKey;
import org.bouncycastle.pqc.jcajce.provider.xmss.DigestUtil;
import org.bouncycastle.util.Arrays;

public class BCXMSSMTPublicKey
implements PublicKey,
XMSSMTKey {
    private final ASN1ObjectIdentifier treeDigest;
    private final XMSSMTPublicKeyParameters keyParams;

    public BCXMSSMTPublicKey(ASN1ObjectIdentifier aSN1ObjectIdentifier, XMSSMTPublicKeyParameters xMSSMTPublicKeyParameters) {
        this.treeDigest = aSN1ObjectIdentifier;
        this.keyParams = xMSSMTPublicKeyParameters;
    }

    public BCXMSSMTPublicKey(SubjectPublicKeyInfo subjectPublicKeyInfo) throws IOException {
        XMSSMTKeyParams xMSSMTKeyParams = XMSSMTKeyParams.getInstance(subjectPublicKeyInfo.getAlgorithm().getParameters());
        this.treeDigest = xMSSMTKeyParams.getTreeDigest().getAlgorithm();
        XMSSPublicKey xMSSPublicKey = XMSSPublicKey.getInstance(subjectPublicKeyInfo.parsePublicKey());
        this.keyParams = new XMSSMTPublicKeyParameters.Builder(new XMSSMTParameters(xMSSMTKeyParams.getHeight(), xMSSMTKeyParams.getLayers(), DigestUtil.getDigest(this.treeDigest))).withPublicSeed(xMSSPublicKey.getPublicSeed()).withRoot(xMSSPublicKey.getRoot()).build();
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof BCXMSSMTPublicKey) {
            BCXMSSMTPublicKey bCXMSSMTPublicKey = (BCXMSSMTPublicKey)object;
            return this.treeDigest.equals(bCXMSSMTPublicKey.treeDigest) && Arrays.areEqual(this.keyParams.toByteArray(), bCXMSSMTPublicKey.keyParams.toByteArray());
        }
        return false;
    }

    public int hashCode() {
        return this.treeDigest.hashCode() + 37 * Arrays.hashCode(this.keyParams.toByteArray());
    }

    public final String getAlgorithm() {
        return "XMSSMT";
    }

    public byte[] getEncoded() {
        try {
            AlgorithmIdentifier algorithmIdentifier = new AlgorithmIdentifier(PQCObjectIdentifiers.xmss_mt, new XMSSMTKeyParams(this.keyParams.getParameters().getHeight(), this.keyParams.getParameters().getLayers(), new AlgorithmIdentifier(this.treeDigest)));
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

    public int getHeight() {
        return this.keyParams.getParameters().getHeight();
    }

    public int getLayers() {
        return this.keyParams.getParameters().getLayers();
    }

    public String getTreeDigest() {
        return DigestUtil.getXMSSDigestName(this.treeDigest);
    }
}

