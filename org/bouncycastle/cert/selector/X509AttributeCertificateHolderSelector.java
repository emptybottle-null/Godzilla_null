/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.selector;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.Target;
import org.bouncycastle.asn1.x509.TargetInformation;
import org.bouncycastle.asn1.x509.Targets;
import org.bouncycastle.cert.AttributeCertificateHolder;
import org.bouncycastle.cert.AttributeCertificateIssuer;
import org.bouncycastle.cert.X509AttributeCertificateHolder;
import org.bouncycastle.util.Selector;

public class X509AttributeCertificateHolderSelector
implements Selector {
    private final AttributeCertificateHolder holder;
    private final AttributeCertificateIssuer issuer;
    private final BigInteger serialNumber;
    private final Date attributeCertificateValid;
    private final X509AttributeCertificateHolder attributeCert;
    private final Collection targetNames;
    private final Collection targetGroups;

    X509AttributeCertificateHolderSelector(AttributeCertificateHolder attributeCertificateHolder, AttributeCertificateIssuer attributeCertificateIssuer, BigInteger bigInteger, Date date, X509AttributeCertificateHolder x509AttributeCertificateHolder, Collection collection, Collection collection2) {
        this.holder = attributeCertificateHolder;
        this.issuer = attributeCertificateIssuer;
        this.serialNumber = bigInteger;
        this.attributeCertificateValid = date;
        this.attributeCert = x509AttributeCertificateHolder;
        this.targetNames = collection;
        this.targetGroups = collection2;
    }

    public boolean match(Object object) {
        Extension extension;
        if (!(object instanceof X509AttributeCertificateHolder)) {
            return false;
        }
        X509AttributeCertificateHolder x509AttributeCertificateHolder = (X509AttributeCertificateHolder)object;
        if (this.attributeCert != null && !this.attributeCert.equals(x509AttributeCertificateHolder)) {
            return false;
        }
        if (this.serialNumber != null && !x509AttributeCertificateHolder.getSerialNumber().equals(this.serialNumber)) {
            return false;
        }
        if (this.holder != null && !x509AttributeCertificateHolder.getHolder().equals(this.holder)) {
            return false;
        }
        if (this.issuer != null && !x509AttributeCertificateHolder.getIssuer().equals(this.issuer)) {
            return false;
        }
        if (this.attributeCertificateValid != null && !x509AttributeCertificateHolder.isValidOn(this.attributeCertificateValid)) {
            return false;
        }
        if (!(this.targetNames.isEmpty() && this.targetGroups.isEmpty() || (extension = x509AttributeCertificateHolder.getExtension(Extension.targetInformation)) == null)) {
            int n;
            Target[] targetArray;
            Targets targets;
            int n2;
            boolean bl;
            TargetInformation targetInformation;
            try {
                targetInformation = TargetInformation.getInstance(extension.getParsedValue());
            } catch (IllegalArgumentException illegalArgumentException) {
                return false;
            }
            Targets[] targetsArray = targetInformation.getTargetsObjects();
            if (!this.targetNames.isEmpty()) {
                bl = false;
                block2: for (n2 = 0; n2 < targetsArray.length; ++n2) {
                    targets = targetsArray[n2];
                    targetArray = targets.getTargets();
                    for (n = 0; n < targetArray.length; ++n) {
                        if (!this.targetNames.contains(GeneralName.getInstance(targetArray[n].getTargetName()))) continue;
                        bl = true;
                        continue block2;
                    }
                }
                if (!bl) {
                    return false;
                }
            }
            if (!this.targetGroups.isEmpty()) {
                bl = false;
                block4: for (n2 = 0; n2 < targetsArray.length; ++n2) {
                    targets = targetsArray[n2];
                    targetArray = targets.getTargets();
                    for (n = 0; n < targetArray.length; ++n) {
                        if (!this.targetGroups.contains(GeneralName.getInstance(targetArray[n].getTargetGroup()))) continue;
                        bl = true;
                        continue block4;
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
        X509AttributeCertificateHolderSelector x509AttributeCertificateHolderSelector = new X509AttributeCertificateHolderSelector(this.holder, this.issuer, this.serialNumber, this.attributeCertificateValid, this.attributeCert, this.targetNames, this.targetGroups);
        return x509AttributeCertificateHolderSelector;
    }

    public X509AttributeCertificateHolder getAttributeCert() {
        return this.attributeCert;
    }

    public Date getAttributeCertificateValid() {
        if (this.attributeCertificateValid != null) {
            return new Date(this.attributeCertificateValid.getTime());
        }
        return null;
    }

    public AttributeCertificateHolder getHolder() {
        return this.holder;
    }

    public AttributeCertificateIssuer getIssuer() {
        return this.issuer;
    }

    public BigInteger getSerialNumber() {
        return this.serialNumber;
    }

    public Collection getTargetNames() {
        return this.targetNames;
    }

    public Collection getTargetGroups() {
        return this.targetGroups;
    }
}

