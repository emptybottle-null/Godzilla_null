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
import org.bouncycastle.pqc.asn1.XMSSMTKeyParams;
import org.bouncycastle.pqc.asn1.XMSSMTPrivateKey;
import org.bouncycastle.pqc.asn1.XMSSPrivateKey;
import org.bouncycastle.pqc.crypto.xmss.BDSStateMap;
import org.bouncycastle.pqc.crypto.xmss.XMSSMTParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSMTPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSUtil;
import org.bouncycastle.pqc.jcajce.interfaces.XMSSMTKey;
import org.bouncycastle.pqc.jcajce.provider.xmss.DigestUtil;
import org.bouncycastle.util.Arrays;

public class BCXMSSMTPrivateKey
implements PrivateKey,
XMSSMTKey {
    private final ASN1ObjectIdentifier treeDigest;
    private final XMSSMTPrivateKeyParameters keyParams;

    public BCXMSSMTPrivateKey(ASN1ObjectIdentifier aSN1ObjectIdentifier, XMSSMTPrivateKeyParameters xMSSMTPrivateKeyParameters) {
        this.treeDigest = aSN1ObjectIdentifier;
        this.keyParams = xMSSMTPrivateKeyParameters;
    }

    public BCXMSSMTPrivateKey(PrivateKeyInfo privateKeyInfo) throws IOException {
        XMSSMTKeyParams xMSSMTKeyParams = XMSSMTKeyParams.getInstance(privateKeyInfo.getPrivateKeyAlgorithm().getParameters());
        this.treeDigest = xMSSMTKeyParams.getTreeDigest().getAlgorithm();
        XMSSPrivateKey xMSSPrivateKey = XMSSPrivateKey.getInstance(privateKeyInfo.parsePrivateKey());
        try {
            XMSSMTPrivateKeyParameters.Builder builder = new XMSSMTPrivateKeyParameters.Builder(new XMSSMTParameters(xMSSMTKeyParams.getHeight(), xMSSMTKeyParams.getLayers(), DigestUtil.getDigest(this.treeDigest))).withIndex(xMSSPrivateKey.getIndex()).withSecretKeySeed(xMSSPrivateKey.getSecretKeySeed()).withSecretKeyPRF(xMSSPrivateKey.getSecretKeyPRF()).withPublicSeed(xMSSPrivateKey.getPublicSeed()).withRoot(xMSSPrivateKey.getRoot());
            if (xMSSPrivateKey.getBdsState() != null) {
                builder.withBDSState((BDSStateMap)XMSSUtil.deserialize(xMSSPrivateKey.getBdsState()));
            }
            this.keyParams = builder.build();
        } catch (ClassNotFoundException classNotFoundException) {
            throw new IOException("ClassNotFoundException processing BDS state: " + classNotFoundException.getMessage());
        }
    }

    public String getAlgorithm() {
        return "XMSSMT";
    }

    public String getFormat() {
        return "PKCS#8";
    }

    public byte[] getEncoded() {
        try {
            AlgorithmIdentifier algorithmIdentifier = new AlgorithmIdentifier(PQCObjectIdentifiers.xmss_mt, new XMSSMTKeyParams(this.keyParams.getParameters().getHeight(), this.keyParams.getParameters().getLayers(), new AlgorithmIdentifier(this.treeDigest)));
            PrivateKeyInfo privateKeyInfo = new PrivateKeyInfo(algorithmIdentifier, this.createKeyStructure());
            return privateKeyInfo.getEncoded();
        } catch (IOException iOException) {
            return null;
        }
    }

    CipherParameters getKeyParams() {
        return this.keyParams;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof BCXMSSMTPrivateKey) {
            BCXMSSMTPrivateKey bCXMSSMTPrivateKey = (BCXMSSMTPrivateKey)object;
            return this.treeDigest.equals(bCXMSSMTPrivateKey.treeDigest) && Arrays.areEqual(this.keyParams.toByteArray(), bCXMSSMTPrivateKey.keyParams.toByteArray());
        }
        return false;
    }

    public int hashCode() {
        return this.treeDigest.hashCode() + 37 * Arrays.hashCode(this.keyParams.toByteArray());
    }

    private XMSSMTPrivateKey createKeyStructure() {
        byte[] byArray = this.keyParams.toByteArray();
        int n = this.keyParams.getParameters().getDigestSize();
        int n2 = this.keyParams.getParameters().getHeight();
        int n3 = (n2 + 7) / 8;
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
        return new XMSSMTPrivateKey(n9, byArray2, byArray3, byArray4, byArray5, byArray6);
    }

    ASN1ObjectIdentifier getTreeDigestOID() {
        return this.treeDigest;
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

