/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert;

import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DEROutputStream;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.AttributeCertificate;
import org.bouncycastle.asn1.x509.AttributeCertificateInfo;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.asn1.x509.CertificateList;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.ExtensionsGenerator;
import org.bouncycastle.asn1.x509.TBSCertList;
import org.bouncycastle.asn1.x509.TBSCertificate;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.cert.X509AttributeCertificateHolder;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.operator.ContentSigner;

class CertUtils {
    private static Set EMPTY_SET = Collections.unmodifiableSet(new HashSet());
    private static List EMPTY_LIST = Collections.unmodifiableList(new ArrayList());

    CertUtils() {
    }

    static ASN1Primitive parseNonEmptyASN1(byte[] byArray) throws IOException {
        ASN1Primitive aSN1Primitive = ASN1Primitive.fromByteArray(byArray);
        if (aSN1Primitive == null) {
            throw new IOException("no content found");
        }
        return aSN1Primitive;
    }

    static X509CertificateHolder generateFullCert(ContentSigner contentSigner, TBSCertificate tBSCertificate) {
        try {
            return new X509CertificateHolder(CertUtils.generateStructure(tBSCertificate, contentSigner.getAlgorithmIdentifier(), CertUtils.generateSig(contentSigner, tBSCertificate)));
        } catch (IOException iOException) {
            throw new IllegalStateException("cannot produce certificate signature");
        }
    }

    static X509AttributeCertificateHolder generateFullAttrCert(ContentSigner contentSigner, AttributeCertificateInfo attributeCertificateInfo) {
        try {
            return new X509AttributeCertificateHolder(CertUtils.generateAttrStructure(attributeCertificateInfo, contentSigner.getAlgorithmIdentifier(), CertUtils.generateSig(contentSigner, attributeCertificateInfo)));
        } catch (IOException iOException) {
            throw new IllegalStateException("cannot produce attribute certificate signature");
        }
    }

    static X509CRLHolder generateFullCRL(ContentSigner contentSigner, TBSCertList tBSCertList) {
        try {
            return new X509CRLHolder(CertUtils.generateCRLStructure(tBSCertList, contentSigner.getAlgorithmIdentifier(), CertUtils.generateSig(contentSigner, tBSCertList)));
        } catch (IOException iOException) {
            throw new IllegalStateException("cannot produce certificate signature");
        }
    }

    private static byte[] generateSig(ContentSigner contentSigner, ASN1Encodable aSN1Encodable) throws IOException {
        OutputStream outputStream = contentSigner.getOutputStream();
        DEROutputStream dEROutputStream = new DEROutputStream(outputStream);
        dEROutputStream.writeObject(aSN1Encodable);
        outputStream.close();
        return contentSigner.getSignature();
    }

    private static Certificate generateStructure(TBSCertificate tBSCertificate, AlgorithmIdentifier algorithmIdentifier, byte[] byArray) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(tBSCertificate);
        aSN1EncodableVector.add(algorithmIdentifier);
        aSN1EncodableVector.add(new DERBitString(byArray));
        return Certificate.getInstance(new DERSequence(aSN1EncodableVector));
    }

    private static AttributeCertificate generateAttrStructure(AttributeCertificateInfo attributeCertificateInfo, AlgorithmIdentifier algorithmIdentifier, byte[] byArray) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(attributeCertificateInfo);
        aSN1EncodableVector.add(algorithmIdentifier);
        aSN1EncodableVector.add(new DERBitString(byArray));
        return AttributeCertificate.getInstance(new DERSequence(aSN1EncodableVector));
    }

    private static CertificateList generateCRLStructure(TBSCertList tBSCertList, AlgorithmIdentifier algorithmIdentifier, byte[] byArray) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(tBSCertList);
        aSN1EncodableVector.add(algorithmIdentifier);
        aSN1EncodableVector.add(new DERBitString(byArray));
        return CertificateList.getInstance(new DERSequence(aSN1EncodableVector));
    }

    static Set getCriticalExtensionOIDs(Extensions extensions) {
        if (extensions == null) {
            return EMPTY_SET;
        }
        return Collections.unmodifiableSet(new HashSet<ASN1ObjectIdentifier>(Arrays.asList(extensions.getCriticalExtensionOIDs())));
    }

    static Set getNonCriticalExtensionOIDs(Extensions extensions) {
        if (extensions == null) {
            return EMPTY_SET;
        }
        return Collections.unmodifiableSet(new HashSet<ASN1ObjectIdentifier>(Arrays.asList(extensions.getNonCriticalExtensionOIDs())));
    }

    static List getExtensionOIDs(Extensions extensions) {
        if (extensions == null) {
            return EMPTY_LIST;
        }
        return Collections.unmodifiableList(Arrays.asList(extensions.getExtensionOIDs()));
    }

    static void addExtension(ExtensionsGenerator extensionsGenerator, ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean bl, ASN1Encodable aSN1Encodable) throws CertIOException {
        try {
            extensionsGenerator.addExtension(aSN1ObjectIdentifier, bl, aSN1Encodable);
        } catch (IOException iOException) {
            throw new CertIOException("cannot encode extension: " + iOException.getMessage(), iOException);
        }
    }

    static DERBitString booleanToBitString(boolean[] blArray) {
        int n;
        byte[] byArray = new byte[(blArray.length + 7) / 8];
        for (n = 0; n != blArray.length; ++n) {
            int n2 = n / 8;
            byArray[n2] = (byte)(byArray[n2] | (blArray[n] ? 1 << 7 - n % 8 : 0));
        }
        n = blArray.length % 8;
        if (n == 0) {
            return new DERBitString(byArray);
        }
        return new DERBitString(byArray, 8 - n);
    }

    static boolean[] bitStringToBoolean(DERBitString dERBitString) {
        if (dERBitString != null) {
            byte[] byArray = dERBitString.getBytes();
            boolean[] blArray = new boolean[byArray.length * 8 - dERBitString.getPadBits()];
            for (int i = 0; i != blArray.length; ++i) {
                blArray[i] = (byArray[i / 8] & 128 >>> i % 8) != 0;
            }
            return blArray;
        }
        return null;
    }

    static Date recoverDate(ASN1GeneralizedTime aSN1GeneralizedTime) {
        try {
            return aSN1GeneralizedTime.getDate();
        } catch (ParseException parseException) {
            throw new IllegalStateException("unable to recover date: " + parseException.getMessage());
        }
    }

    static boolean isAlgIdEqual(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2) {
        if (!algorithmIdentifier.getAlgorithm().equals(algorithmIdentifier2.getAlgorithm())) {
            return false;
        }
        if (algorithmIdentifier.getParameters() == null) {
            return algorithmIdentifier2.getParameters() == null || algorithmIdentifier2.getParameters().equals(DERNull.INSTANCE);
        }
        if (algorithmIdentifier2.getParameters() == null) {
            return algorithmIdentifier.getParameters() == null || algorithmIdentifier.getParameters().equals(DERNull.INSTANCE);
        }
        return algorithmIdentifier.getParameters().equals(algorithmIdentifier2.getParameters());
    }
}

