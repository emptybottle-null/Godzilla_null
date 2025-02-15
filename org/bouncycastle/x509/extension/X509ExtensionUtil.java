/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.x509.extension;

import java.io.IOException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1String;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.X509Extension;
import org.bouncycastle.util.Integers;

public class X509ExtensionUtil {
    public static ASN1Primitive fromExtensionValue(byte[] byArray) throws IOException {
        ASN1OctetString aSN1OctetString = (ASN1OctetString)ASN1Primitive.fromByteArray(byArray);
        return ASN1Primitive.fromByteArray(aSN1OctetString.getOctets());
    }

    public static Collection getIssuerAlternativeNames(X509Certificate x509Certificate) throws CertificateParsingException {
        byte[] byArray = x509Certificate.getExtensionValue(X509Extension.issuerAlternativeName.getId());
        return X509ExtensionUtil.getAlternativeNames(byArray);
    }

    public static Collection getSubjectAlternativeNames(X509Certificate x509Certificate) throws CertificateParsingException {
        byte[] byArray = x509Certificate.getExtensionValue(X509Extension.subjectAlternativeName.getId());
        return X509ExtensionUtil.getAlternativeNames(byArray);
    }

    private static Collection getAlternativeNames(byte[] byArray) throws CertificateParsingException {
        if (byArray == null) {
            return Collections.EMPTY_LIST;
        }
        try {
            ArrayList arrayList = new ArrayList();
            Enumeration enumeration = DERSequence.getInstance(X509ExtensionUtil.fromExtensionValue(byArray)).getObjects();
            while (enumeration.hasMoreElements()) {
                GeneralName generalName = GeneralName.getInstance(enumeration.nextElement());
                ArrayList<Object> arrayList2 = new ArrayList<Object>();
                arrayList2.add(Integers.valueOf(generalName.getTagNo()));
                switch (generalName.getTagNo()) {
                    case 0: 
                    case 3: 
                    case 5: {
                        arrayList2.add(generalName.getName().toASN1Primitive());
                        break;
                    }
                    case 4: {
                        arrayList2.add(X500Name.getInstance(generalName.getName()).toString());
                        break;
                    }
                    case 1: 
                    case 2: 
                    case 6: {
                        arrayList2.add(((ASN1String)((Object)generalName.getName())).getString());
                        break;
                    }
                    case 8: {
                        arrayList2.add(ASN1ObjectIdentifier.getInstance(generalName.getName()).getId());
                        break;
                    }
                    case 7: {
                        arrayList2.add(DEROctetString.getInstance(generalName.getName()).getOctets());
                        break;
                    }
                    default: {
                        throw new IOException("Bad tag number: " + generalName.getTagNo());
                    }
                }
                arrayList.add(arrayList2);
            }
            return Collections.unmodifiableCollection(arrayList);
        } catch (Exception exception) {
            throw new CertificateParsingException(exception.getMessage());
        }
    }
}

