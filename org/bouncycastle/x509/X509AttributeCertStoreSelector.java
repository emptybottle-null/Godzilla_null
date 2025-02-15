/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.x509;

import java.io.IOException;
import java.math.BigInteger;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.Target;
import org.bouncycastle.asn1.x509.TargetInformation;
import org.bouncycastle.asn1.x509.Targets;
import org.bouncycastle.asn1.x509.X509Extensions;
import org.bouncycastle.util.Selector;
import org.bouncycastle.x509.AttributeCertificateHolder;
import org.bouncycastle.x509.AttributeCertificateIssuer;
import org.bouncycastle.x509.X509AttributeCertificate;

public class X509AttributeCertStoreSelector
implements Selector {
    private AttributeCertificateHolder holder;
    private AttributeCertificateIssuer issuer;
    private BigInteger serialNumber;
    private Date attributeCertificateValid;
    private X509AttributeCertificate attributeCert;
    private Collection targetNames = new HashSet();
    private Collection targetGroups = new HashSet();

    public boolean match(Object object) {
        byte[] byArray;
        if (!(object instanceof X509AttributeCertificate)) {
            return false;
        }
        X509AttributeCertificate x509AttributeCertificate = (X509AttributeCertificate)object;
        if (this.attributeCert != null && !this.attributeCert.equals(x509AttributeCertificate)) {
            return false;
        }
        if (this.serialNumber != null && !x509AttributeCertificate.getSerialNumber().equals(this.serialNumber)) {
            return false;
        }
        if (this.holder != null && !x509AttributeCertificate.getHolder().equals(this.holder)) {
            return false;
        }
        if (this.issuer != null && !x509AttributeCertificate.getIssuer().equals(this.issuer)) {
            return false;
        }
        if (this.attributeCertificateValid != null) {
            try {
                x509AttributeCertificate.checkValidity(this.attributeCertificateValid);
            } catch (CertificateExpiredException certificateExpiredException) {
                return false;
            } catch (CertificateNotYetValidException certificateNotYetValidException) {
                return false;
            }
        }
        if (!(this.targetNames.isEmpty() && this.targetGroups.isEmpty() || (byArray = x509AttributeCertificate.getExtensionValue(X509Extensions.TargetInformation.getId())) == null)) {
            int n;
            Target[] targetArray;
            Targets targets;
            int n2;
            boolean bl;
            TargetInformation targetInformation;
            try {
                targetInformation = TargetInformation.getInstance(new ASN1InputStream(((DEROctetString)DEROctetString.fromByteArray(byArray)).getOctets()).readObject());
            } catch (IOException iOException) {
                return false;
            } catch (IllegalArgumentException illegalArgumentException) {
                return false;
            }
            Targets[] targetsArray = targetInformation.getTargetsObjects();
            if (!this.targetNames.isEmpty()) {
                bl = false;
                block6: for (n2 = 0; n2 < targetsArray.length; ++n2) {
                    targets = targetsArray[n2];
                    targetArray = targets.getTargets();
                    for (n = 0; n < targetArray.length; ++n) {
                        if (!this.targetNames.contains(GeneralName.getInstance(targetArray[n].getTargetName()))) continue;
                        bl = true;
                        continue block6;
                    }
                }
                if (!bl) {
                    return false;
                }
            }
            if (!this.targetGroups.isEmpty()) {
                bl = false;
                block8: for (n2 = 0; n2 < targetsArray.length; ++n2) {
                    targets = targetsArray[n2];
                    targetArray = targets.getTargets();
                    for (n = 0; n < targetArray.length; ++n) {
                        if (!this.targetGroups.contains(GeneralName.getInstance(targetArray[n].getTargetGroup()))) continue;
                        bl = true;
                        continue block8;
                    }
                }
                if (!bl) {
                    return false;
                }
            }
        }
        return true;
    }

    public Object clone() {
        X509AttributeCertStoreSelector x509AttributeCertStoreSelector = new X509AttributeCertStoreSelector();
        x509AttributeCertStoreSelector.attributeCert = this.attributeCert;
        x509AttributeCertStoreSelector.attributeCertificateValid = this.getAttributeCertificateValid();
        x509AttributeCertStoreSelector.holder = this.holder;
        x509AttributeCertStoreSelector.issuer = this.issuer;
        x509AttributeCertStoreSelector.serialNumber = this.serialNumber;
        x509AttributeCertStoreSelector.targetGroups = this.getTargetGroups();
        x509AttributeCertStoreSelector.targetNames = this.getTargetNames();
        return x509AttributeCertStoreSelector;
    }

    public X509AttributeCertificate getAttributeCert() {
        return this.attributeCert;
    }

    public void setAttributeCert(X509AttributeCertificate x509AttributeCertificate) {
        this.attributeCert = x509AttributeCertificate;
    }

    public Date getAttributeCertificateValid() {
        if (this.attributeCertificateValid != null) {
            return new Date(this.attributeCertificateValid.getTime());
        }
        return null;
    }

    public void setAttributeCertificateValid(Date date) {
        this.attributeCertificateValid = date != null ? new Date(date.getTime()) : null;
    }

    public AttributeCertificateHolder getHolder() {
        return this.holder;
    }

    public void setHolder(AttributeCertificateHolder attributeCertificateHolder) {
        this.holder = attributeCertificateHolder;
    }

    public AttributeCertificateIssuer getIssuer() {
        return this.issuer;
    }

    public void setIssuer(AttributeCertificateIssuer attributeCertificateIssuer) {
        this.issuer = attributeCertificateIssuer;
    }

    public BigInteger getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(BigInteger bigInteger) {
        this.serialNumber = bigInteger;
    }

    public void addTargetName(GeneralName generalName) {
        this.targetNames.add(generalName);
    }

    public void addTargetName(byte[] byArray) throws IOException {
        this.addTargetName(GeneralName.getInstance(ASN1Primitive.fromByteArray(byArray)));
    }

    public void setTargetNames(Collection collection) throws IOException {
        this.targetNames = this.extractGeneralNames(collection);
    }

    public Collection getTargetNames() {
        return Collections.unmodifiableCollection(this.targetNames);
    }

    public void addTargetGroup(GeneralName generalName) {
        this.targetGroups.add(generalName);
    }

    public void addTargetGroup(byte[] byArray) throws IOException {
        this.addTargetGroup(GeneralName.getInstance(ASN1Primitive.fromByteArray(byArray)));
    }

    public void setTargetGroups(Collection collection) throws IOException {
        this.targetGroups = this.extractGeneralNames(collection);
    }

    public Collection getTargetGroups() {
        return Collections.unmodifiableCollection(this.targetGroups);
    }

    private Set extractGeneralNames(Collection collection) throws IOException {
        if (collection == null || collection.isEmpty()) {
            return new HashSet();
        }
        HashSet hashSet = new HashSet();
        for (Object e : collection) {
            if (e instanceof GeneralName) {
                hashSet.add(e);
                continue;
            }
            hashSet.add(GeneralName.getInstance(ASN1Primitive.fromByteArray((byte[])e)));
        }
        return hashSet;
    }
}

