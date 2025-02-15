/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.dvcs;

import org.bouncycastle.asn1.x509.DigestInfo;

public class MessageImprint {
    private final DigestInfo messageImprint;

    public MessageImprint(DigestInfo digestInfo) {
        this.messageImprint = digestInfo;
    }

    public DigestInfo toASN1Structure() {
        return this.messageImprint;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof MessageImprint) {
            return this.messageImprint.equals(((MessageImprint)object).messageImprint);
        }
        return false;
    }

    public int hashCode() {
        return this.messageImprint.hashCode();
    }
}

