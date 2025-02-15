/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.x509;

import java.io.IOException;
import java.math.BigInteger;
import java.security.cert.CRLException;
import java.security.cert.X509CRLEntry;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.asn1.ASN1Enumerated;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.util.ASN1Dump;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.CRLReason;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.TBSCertList;
import org.bouncycastle.util.Strings;

class X509CRLEntryObject
extends X509CRLEntry {
    private TBSCertList.CRLEntry c;
    private X500Name certificateIssuer;
    private int hashValue;
    private boolean isHashValueSet;

    protected X509CRLEntryObject(TBSCertList.CRLEntry cRLEntry) {
        this.c = cRLEntry;
        this.certificateIssuer = null;
    }

    protected X509CRLEntryObject(TBSCertList.CRLEntry cRLEntry, boolean bl, X500Name x500Name) {
        this.c = cRLEntry;
        this.certificateIssuer = this.loadCertificateIssuer(bl, x500Name);
    }

    public boolean hasUnsupportedCriticalExtension() {
        Set set = this.getCriticalExtensionOIDs();
        return set != null && !set.isEmpty();
    }

    private X500Name loadCertificateIssuer(boolean bl, X500Name x500Name) {
        if (!bl) {
            return null;
        }
        Extension extension = this.getExtension(Extension.certificateIssuer);
        if (extension == null) {
            return x500Name;
        }
        try {
            GeneralName[] generalNameArray = GeneralNames.getInstance(extension.getParsedValue()).getNames();
            for (int i = 0; i < generalNameArray.length; ++i) {
                if (generalNameArray[i].getTagNo() != 4) continue;
                return X500Name.getInstance(generalNameArray[i].getName());
            }
            return null;
        } catch (Exception exception) {
            return null;
        }
    }

    public X500Principal getCertificateIssuer() {
        if (this.certificateIssuer == null) {
            return null;
        }
        try {
            return new X500Principal(this.certificateIssuer.getEncoded());
        } catch (IOException iOException) {
            return null;
        }
    }

    private Set getExtensionOIDs(boolean bl) {
        Extensions extensions = this.c.getExtensions();
        if (extensions != null) {
            HashSet<String> hashSet = new HashSet<String>();
            Enumeration enumeration = extensions.oids();
            while (enumeration.hasMoreElements()) {
                ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier)enumeration.nextElement();
                Extension extension = extensions.getExtension(aSN1ObjectIdentifier);
                if (bl != extension.isCritical()) continue;
                hashSet.add(aSN1ObjectIdentifier.getId());
            }
            return hashSet;
        }
        return null;
    }

    public Set getCriticalExtensionOIDs() {
        return this.getExtensionOIDs(true);
    }

    public Set getNonCriticalExtensionOIDs() {
        return this.getExtensionOIDs(false);
    }

    private Extension getExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        Extensions extensions = this.c.getExtensions();
        if (extensions != null) {
            return extensions.getExtension(aSN1ObjectIdentifier);
        }
        return null;
    }

    public byte[] getExtensionValue(String string) {
        Extension extension = this.getExtension(new ASN1ObjectIdentifier(string));
        if (extension != null) {
            try {
                return extension.getExtnValue().getEncoded();
            } catch (Exception exception) {
                throw new IllegalStateException("Exception encoding: " + exception.toString());
            }
        }
        return null;
    }

    public int hashCode() {
        if (!this.isHashValueSet) {
            this.hashValue = super.hashCode();
            this.isHashValueSet = true;
        }
        return this.hashValue;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof X509CRLEntryObject) {
            X509CRLEntryObject x509CRLEntryObject = (X509CRLEntryObject)object;
            return this.c.equals(x509CRLEntryObject.c);
        }
        return super.equals(this);
    }

    public byte[] getEncoded() throws CRLException {
        try {
            return this.c.getEncoded("DER");
        } catch (IOException iOException) {
            throw new CRLException(iOException.toString());
        }
    }

    public BigInteger getSerialNumber() {
        return this.c.getUserCertificate().getValue();
    }

    public Date getRevocationDate() {
        return this.c.getRevocationDate().getDate();
    }

    public boolean hasExtensions() {
        return this.c.getExtensions() != null;
    }

    public String toString() {
        Enumeration enumeration;
        StringBuffer stringBuffer = new StringBuffer();
        String string = Strings.lineSeparator();
        stringBuffer.append("      userCertificate: ").append(this.getSerialNumber()).append(string);
        stringBuffer.append("       revocationDate: ").append(this.getRevocationDate()).append(string);
        stringBuffer.append("       certificateIssuer: ").append(this.getCertificateIssuer()).append(string);
        Extensions extensions = this.c.getExtensions();
        if (extensions != null && (enumeration = extensions.oids()).hasMoreElements()) {
            stringBuffer.append("   crlEntryExtensions:").append(string);
            while (enumeration.hasMoreElements()) {
                ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier)enumeration.nextElement();
                Extension extension = extensions.getExtension(aSN1ObjectIdentifier);
                if (extension.getExtnValue() != null) {
                    byte[] byArray = extension.getExtnValue().getOctets();
                    ASN1InputStream aSN1InputStream = new ASN1InputStream(byArray);
                    stringBuffer.append("                       critical(").append(extension.isCritical()).append(") ");
                    try {
                        if (aSN1ObjectIdentifier.equals(Extension.reasonCode)) {
                            stringBuffer.append(CRLReason.getInstance(ASN1Enumerated.getInstance(aSN1InputStream.readObject()))).append(string);
                            continue;
                        }
                        if (aSN1ObjectIdentifier.equals(Extension.certificateIssuer)) {
                            stringBuffer.append("Certificate issuer: ").append(GeneralNames.getInstance(aSN1InputStream.readObject())).append(string);
                            continue;
                        }
                        stringBuffer.append(aSN1ObjectIdentifier.getId());
                        stringBuffer.append(" value = ").append(ASN1Dump.dumpAsString(aSN1InputStream.readObject())).append(string);
                    } catch (Exception exception) {
                        stringBuffer.append(aSN1ObjectIdentifier.getId());
                        stringBuffer.append(" value = ").append("*****").append(string);
                    }
                    continue;
                }
                stringBuffer.append(string);
            }
        }
        return stringBuffer.toString();
    }
}

