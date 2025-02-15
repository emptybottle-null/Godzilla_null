/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.ocsp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.cert.X509CertificateHolder;

class OCSPUtils {
    static final X509CertificateHolder[] EMPTY_CERTS = new X509CertificateHolder[0];
    static Set EMPTY_SET = Collections.unmodifiableSet(new HashSet());
    static List EMPTY_LIST = Collections.unmodifiableList(new ArrayList());

    OCSPUtils() {
    }

    static Date extractDate(ASN1GeneralizedTime aSN1GeneralizedTime) {
        try {
            return aSN1GeneralizedTime.getDate();
        } catch (Exception exception) {
            throw new IllegalStateException("exception processing GeneralizedTime: " + exception.getMessage());
        }
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
}

