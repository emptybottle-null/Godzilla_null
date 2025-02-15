/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.openssl;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.DSAParameter;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.cert.X509AttributeCertificateHolder;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.openssl.PEMEncryptor;
import org.bouncycastle.openssl.X509TrustedCertificateBlock;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCS8EncryptedPrivateKeyInfo;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.io.pem.PemGenerationException;
import org.bouncycastle.util.io.pem.PemHeader;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemObjectGenerator;

public class MiscPEMGenerator
implements PemObjectGenerator {
    private static final ASN1ObjectIdentifier[] dsaOids = new ASN1ObjectIdentifier[]{X9ObjectIdentifiers.id_dsa, OIWObjectIdentifiers.dsaWithSHA1};
    private static final byte[] hexEncodingTable = new byte[]{48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70};
    private final Object obj;
    private final PEMEncryptor encryptor;

    public MiscPEMGenerator(Object object) {
        this.obj = object;
        this.encryptor = null;
    }

    public MiscPEMGenerator(Object object, PEMEncryptor pEMEncryptor) {
        this.obj = object;
        this.encryptor = pEMEncryptor;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private PemObject createPemObject(Object object) throws IOException {
        Object object2;
        Object object3;
        Object object4;
        Object object5;
        byte[] byArray;
        String string;
        if (object instanceof PemObject) {
            return (PemObject)object;
        }
        if (object instanceof PemObjectGenerator) {
            return ((PemObjectGenerator)object).generate();
        }
        if (object instanceof X509CertificateHolder) {
            string = "CERTIFICATE";
            byArray = ((X509CertificateHolder)object).getEncoded();
        } else if (object instanceof X509CRLHolder) {
            string = "X509 CRL";
            byArray = ((X509CRLHolder)object).getEncoded();
        } else if (object instanceof X509TrustedCertificateBlock) {
            string = "TRUSTED CERTIFICATE";
            byArray = ((X509TrustedCertificateBlock)object).getEncoded();
        } else if (object instanceof PrivateKeyInfo) {
            object5 = (PrivateKeyInfo)object;
            object4 = ((PrivateKeyInfo)object5).getPrivateKeyAlgorithm().getAlgorithm();
            if (((ASN1Primitive)object4).equals(PKCSObjectIdentifiers.rsaEncryption)) {
                string = "RSA PRIVATE KEY";
                byArray = ((PrivateKeyInfo)object5).parsePrivateKey().toASN1Primitive().getEncoded();
            } else if (((ASN1Primitive)object4).equals(dsaOids[0]) || ((ASN1Primitive)object4).equals(dsaOids[1])) {
                string = "DSA PRIVATE KEY";
                object3 = DSAParameter.getInstance(((PrivateKeyInfo)object5).getPrivateKeyAlgorithm().getParameters());
                object2 = new ASN1EncodableVector();
                ((ASN1EncodableVector)object2).add(new ASN1Integer(0L));
                ((ASN1EncodableVector)object2).add(new ASN1Integer(((DSAParameter)object3).getP()));
                ((ASN1EncodableVector)object2).add(new ASN1Integer(((DSAParameter)object3).getQ()));
                ((ASN1EncodableVector)object2).add(new ASN1Integer(((DSAParameter)object3).getG()));
                BigInteger bigInteger = ASN1Integer.getInstance(((PrivateKeyInfo)object5).parsePrivateKey()).getValue();
                BigInteger bigInteger2 = ((DSAParameter)object3).getG().modPow(bigInteger, ((DSAParameter)object3).getP());
                ((ASN1EncodableVector)object2).add(new ASN1Integer(bigInteger2));
                ((ASN1EncodableVector)object2).add(new ASN1Integer(bigInteger));
                byArray = new DERSequence((ASN1EncodableVector)object2).getEncoded();
            } else {
                if (!((ASN1Primitive)object4).equals(X9ObjectIdentifiers.id_ecPublicKey)) throw new IOException("Cannot identify private key");
                string = "EC PRIVATE KEY";
                byArray = ((PrivateKeyInfo)object5).parsePrivateKey().toASN1Primitive().getEncoded();
            }
        } else if (object instanceof SubjectPublicKeyInfo) {
            string = "PUBLIC KEY";
            byArray = ((SubjectPublicKeyInfo)object).getEncoded();
        } else if (object instanceof X509AttributeCertificateHolder) {
            string = "ATTRIBUTE CERTIFICATE";
            byArray = ((X509AttributeCertificateHolder)object).getEncoded();
        } else if (object instanceof PKCS10CertificationRequest) {
            string = "CERTIFICATE REQUEST";
            byArray = ((PKCS10CertificationRequest)object).getEncoded();
        } else if (object instanceof PKCS8EncryptedPrivateKeyInfo) {
            string = "ENCRYPTED PRIVATE KEY";
            byArray = ((PKCS8EncryptedPrivateKeyInfo)object).getEncoded();
        } else {
            if (!(object instanceof ContentInfo)) throw new PemGenerationException("unknown object passed - can't encode.");
            string = "PKCS7";
            byArray = ((ContentInfo)object).getEncoded();
        }
        if (this.encryptor == null) return new PemObject(string, byArray);
        object5 = Strings.toUpperCase(this.encryptor.getAlgorithm());
        if (((String)object5).equals("DESEDE")) {
            object5 = "DES-EDE3-CBC";
        }
        object4 = this.encryptor.getIV();
        object3 = this.encryptor.encrypt(byArray);
        object2 = new ArrayList<PemHeader>(2);
        object2.add(new PemHeader("Proc-Type", "4,ENCRYPTED"));
        object2.add(new PemHeader("DEK-Info", (String)object5 + "," + this.getHexEncoded((byte[])object4)));
        return new PemObject(string, (List)object2, (byte[])object3);
    }

    private String getHexEncoded(byte[] byArray) throws IOException {
        char[] cArray = new char[byArray.length * 2];
        for (int i = 0; i != byArray.length; ++i) {
            int n = byArray[i] & 0xFF;
            cArray[2 * i] = (char)hexEncodingTable[n >>> 4];
            cArray[2 * i + 1] = (char)hexEncodingTable[n & 0xF];
        }
        return new String(cArray);
    }

    public PemObject generate() throws PemGenerationException {
        try {
            return this.createPemObject(this.obj);
        } catch (IOException iOException) {
            throw new PemGenerationException("encoding exception: " + iOException.getMessage(), iOException);
        }
    }
}

