/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.jcajce.provider.xmss;

import java.io.IOException;
import java.security.PrivateKey;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.pqc.asn1.PQCObjectIdentifiers;
import org.bouncycastle.pqc.asn1.XMSSKeyParams;
import org.bouncycastle.pqc.asn1.XMSSPrivateKey;
import org.bouncycastle.pqc.crypto.xmss.BDS;
import org.bouncycastle.pqc.crypto.xmss.XMSSParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSUtil;
import org.bouncycastle.pqc.jcajce.interfaces.XMSSKey;
import org.bouncycastle.pqc.jcajce.provider.xmss.DigestUtil;
import org.bouncycastle.util.Arrays;

public class BCXMSSPrivateKey
implements PrivateKey,
XMSSKey {
    private final XMSSPrivateKeyParameters keyParams;
    private final ASN1ObjectIdentifier treeDigest;

    public BCXMSSPrivateKey(ASN1ObjectIdentifier aSN1ObjectIdentifier, XMSSPrivateKeyParameters xMSSPrivateKeyParameters) {
        this.treeDigest = aSN1ObjectIdentifier;
        this.keyParams = xMSSPrivateKeyParameters;
    }

    public BCXMSSPrivateKey(PrivateKeyInfo privateKeyInfo) throws IOException {
        XMSSKeyParams xMSSKeyParams = XMSSKeyParams.getInstance(privateKeyInfo.getPrivateKeyAlgorithm().getParameters());
        this.treeDigest = xMSSKeyParams.getTreeDigest().getAlgorithm();
        XMSSPrivateKey xMSSPrivateKey = XMSSPrivateKey.getInstance(privateKeyInfo.parsePrivateKey());
        try {
            XMSSPrivateKeyParameters.Builder builder = new XMSSPrivateKeyParameters.Builder(new XMSSParameters(xMSSKeyParams.getHeight(), DigestUtil.getDigest(this.treeDigest))).withIndex(xMSSPrivateKey.getIndex()).withSecretKeySeed(xMSSPrivateKey.getSecretKeySeed()).withSecretKeyPRF(xMSSPrivateKey.getSecretKeyPRF()).withPublicSeed(xMSSPrivateKey.getPublicSeed()).withRoot(xMSSPrivateKey.getRoot());
            if (xMSSPrivateKey.getBdsState() != null) {
                builder.withBDSState((BDS)XMSSUtil.deserialize(xMSSPrivateKey.getBdsState()));
            }
            this.keyParams = builder.build();
        } catch (ClassNotFoundException classNotFoundException) {
            throw new IOException("ClassNotFoundException processing BDS state: " + classNotFoundException.getMessage());
        }
    }

    public String getAlgorithm() {
        return "XMSS";
    }

    public String getFormat() {
        return "PKCS#8";
    }

    public byte[] getEncoded() {
        try {
            AlgorithmIdentifier algorithmIdentifier = new AlgorithmIdentifier(PQCObjectIdentifiers.xmss, new XMSSKeyParams(this.keyParams.getParameters().getHeight(), new AlgorithmIdentifier(this.treeDigest)));
            PrivateKeyInfo privateKeyInfo = new PrivateKeyInfo(algorithmIdentifier, this.createKeyStructure());
            return privateKeyInfo.getEncoded();
        } catch (IOException iOException) {
            return null;
        }
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof BCXMSSPrivateKey) {
            BCXMSSPrivateKey bCXMSSPrivateKey = (BCXMSSPrivateKey)object;
            return this.treeDigest.equals(bCXMSSPrivateKey.treeDigest) && Arrays.areEqual(this.keyParams.toByteArray(), bCXMSSPrivateKey.keyParams.toByteArray());
        }
        return false;
    }

    public int hashCode() {
        return this.treeDigest.hashCode() + 37 * Arrays.hashCode(this.keyParams.toByteArray());
    }

    CipherParameters getKeyParams() {
        return this.keyParams;
    }

    private XMSSPrivateKey createKeyStructure() {
        byte[] byArray = this.keyParams.toByteArray();
        int n = this.keyParams.getParameters().getDigestSize();
        int n2 = this.keyParams.getParameters().getHeight();
        int n3 = 4;
        int n4 = n;
        int n5 = n;
        int n6 = n;
        int n7 = n;
        int n8 = 0;
        int n9 = (int)XMSSUtil.bytesToXBigEndian(byArray, n8, n3);
        if (!XMSSUtil.isIndexValid(n2, n9)) {
            throw new IllegalArgumentException("index out of bounds");
        }
        byte[] byArray2 = XMSSUtil.extractBytesAtOffset(byArray, n8 += n3, n4);
        byte[] byArray3 = XMSSUtil.extractBytesAtOffset(byArray, n8 += n4, n5);
        byte[] byArray4 = XMSSUtil.extractBytesAtOffset(byArray, n8 += n5, n6);
        byte[] byArray5 = XMSSUtil.extractBytesAtOffset(byArray, n8 += n6, n7);
        byte[] byArray6 = XMSSUtil.extractBytesAtOffset(byArray, n8 += n7, byArray.length - n8);
        return new XMSSPrivateKey(n9, byArray2, byArray3, byArray4, byArray5, byArray6);
    }

    ASN1ObjectIdentifier getTreeDigestOID() {
        return this.treeDigest;
    }

    public int getHeight() {
        return this.keyParams.getParameters().getHeight();
    }

    public String getTreeDigest() {
        return DigestUtil.getXMSSDigestName(this.treeDigest);
    }
}

