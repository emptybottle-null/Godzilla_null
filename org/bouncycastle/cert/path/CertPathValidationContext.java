/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.path;

import java.util.HashSet;
import java.util.Set;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.util.Memoable;

public class CertPathValidationContext
implements Memoable {
    private Set criticalExtensions;
    private Set handledExtensions = new HashSet();
    private boolean endEntity;
    private int index;

    public CertPathValidationContext(Set set) {
        this.criticalExtensions = set;
    }

    public void addHandledExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.handledExtensions.add(aSN1ObjectIdentifier);
    }

    public void setIsEndEntity(boolean bl) {
        this.endEntity = bl;
    }

    public Set getUnhandledCriticalExtensionOIDs() {
        HashSet hashSet = new HashSet(this.criticalExtensions);
        hashSet.removeAll(this.handledExtensions);
        return hashSet;
    }

    public boolean isEndEntity() {
        return this.endEntity;
    }

    public Memoable copy() {
        return null;
    }

    public void reset(Memoable memoable) {
    }
}

