/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.dane;

import org.bouncycastle.cert.dane.DANEEntry;
import org.bouncycastle.util.Selector;

public class DANEEntrySelector
implements Selector {
    private final String domainName;

    DANEEntrySelector(String string) {
        this.domainName = string;
    }

    public boolean match(Object object) {
        DANEEntry dANEEntry = (DANEEntry)object;
        return dANEEntry.getDomainName().equals(this.domainName);
    }

    public Object clone() {
        return this;
    }

    public String getDomainName() {
        return this.domainName;
    }
}

