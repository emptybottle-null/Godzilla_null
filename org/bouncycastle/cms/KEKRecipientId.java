/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import org.bouncycastle.cms.KEKRecipientInformation;
import org.bouncycastle.cms.RecipientId;
import org.bouncycastle.util.Arrays;

public class KEKRecipientId
extends RecipientId {
    private byte[] keyIdentifier;

    public KEKRecipientId(byte[] byArray) {
        super(1);
        this.keyIdentifier = byArray;
    }

    public int hashCode() {
        return Arrays.hashCode(this.keyIdentifier);
    }

    public boolean equals(Object object) {
        if (!(object instanceof KEKRecipientId)) {
            return false;
        }
        KEKRecipientId kEKRecipientId = (KEKRecipientId)object;
        return Arrays.areEqual(this.keyIdentifier, kEKRecipientId.keyIdentifier);
    }

    public byte[] getKeyIdentifier() {
        return Arrays.clone(this.keyIdentifier);
    }

    public Object clone() {
        return new KEKRecipientId(this.keyIdentifier);
    }

    public boolean match(Object object) {
        if (object instanceof byte[]) {
            return Arrays.areEqual(this.keyIdentifier, (byte[])object);
        }
        if (object instanceof KEKRecipientInformation) {
            return ((KEKRecipientInformation)object).getRID().equals(this);
        }
        return false;
    }
}

