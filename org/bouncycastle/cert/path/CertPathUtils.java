/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.path;

import java.util.HashSet;
import java.util.Set;
import org.bouncycastle.cert.X509CertificateHolder;

class CertPathUtils {
    CertPathUtils() {
    }

    static Set getCriticalExtensionsOIDs(X509CertificateHolder[] x509CertificateHolderArray) {
        HashSet hashSet = new HashSet();
        for (int i = 0; i != x509CertificateHolderArray.length; ++i) {
            hashSet.addAll(x509CertificateHolderArray[i].getCriticalExtensionOIDs());
        }
        return hashSet;
    }
}

