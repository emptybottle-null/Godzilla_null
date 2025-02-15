/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DEROutputStream;
import org.bouncycastle.asn1.pkcs.ContentInfo;
import org.bouncycastle.asn1.pkcs.MacData;
import org.bouncycastle.asn1.pkcs.Pfx;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.DigestInfo;

public class PKCS12Util {
    public static byte[] convertToDefiniteLength(byte[] byArray) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DEROutputStream dEROutputStream = new DEROutputStream(byteArrayOutputStream);
        Pfx pfx = Pfx.getInstance(byArray);
        byteArrayOutputStream.reset();
        dEROutputStream.writeObject(pfx);
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] convertToDefiniteLength(byte[] byArray, char[] cArray, String string) throws IOException {
        Pfx pfx = Pfx.getInstance(byArray);
        ContentInfo contentInfo = pfx.getAuthSafe();
        ASN1OctetString aSN1OctetString = ASN1OctetString.getInstance(contentInfo.getContent());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DEROutputStream dEROutputStream = new DEROutputStream(byteArrayOutputStream);
        ASN1InputStream aSN1InputStream = new ASN1InputStream(aSN1OctetString.getOctets());
        ASN1Primitive aSN1Primitive = aSN1InputStream.readObject();
        dEROutputStream.writeObject(aSN1Primitive);
        contentInfo = new ContentInfo(contentInfo.getContentType(), new DEROctetString(byteArrayOutputStream.toByteArray()));
        MacData macData = pfx.getMacData();
        try {
            int n = macData.getIterationCount().intValue();
            byte[] byArray2 = ASN1OctetString.getInstance(contentInfo.getContent()).getOctets();
            byte[] byArray3 = PKCS12Util.calculatePbeMac(macData.getMac().getAlgorithmId().getAlgorithm(), macData.getSalt(), n, cArray, byArray2, string);
            AlgorithmIdentifier algorithmIdentifier = new AlgorithmIdentifier(macData.getMac().getAlgorithmId().getAlgorithm(), DERNull.INSTANCE);
            DigestInfo digestInfo = new DigestInfo(algorithmIdentifier, byArray3);
            macData = new MacData(digestInfo, macData.getSalt(), n);
        } catch (Exception exception) {
            throw new IOException("error constructing MAC: " + exception.toString());
        }
        pfx = new Pfx(contentInfo, macData);
        byteArrayOutputStream.reset();
        dEROutputStream.writeObject(pfx);
        return byteArrayOutputStream.toByteArray();
    }

    private static byte[] calculatePbeMac(ASN1ObjectIdentifier aSN1ObjectIdentifier, byte[] byArray, int n, char[] cArray, byte[] byArray2, String string) throws Exception {
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(aSN1ObjectIdentifier.getId(), string);
        PBEParameterSpec pBEParameterSpec = new PBEParameterSpec(byArray, n);
        PBEKeySpec pBEKeySpec = new PBEKeySpec(cArray);
        SecretKey secretKey = secretKeyFactory.generateSecret(pBEKeySpec);
        Mac mac = Mac.getInstance(aSN1ObjectIdentifier.getId(), string);
        mac.init(secretKey, pBEParameterSpec);
        mac.update(byArray2);
        return mac.doFinal();
    }
}

