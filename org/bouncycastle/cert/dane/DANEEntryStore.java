/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.dane;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.dane.DANEEntry;
import org.bouncycastle.util.CollectionStore;
import org.bouncycastle.util.Selector;
import org.bouncycastle.util.Store;
import org.bouncycastle.util.StoreException;

public class DANEEntryStore
implements Store {
    private final Map entries;

    DANEEntryStore(List list) {
        HashMap<String, DANEEntry> hashMap = new HashMap<String, DANEEntry>();
        for (DANEEntry dANEEntry : list) {
            hashMap.put(dANEEntry.getDomainName(), dANEEntry);
        }
        this.entries = Collections.unmodifiableMap(hashMap);
    }

    public Collection getMatches(Selector selector) throws StoreException {
        if (selector == null) {
            return this.entries.values();
        }
        ArrayList arrayList = new ArrayList();
        for (Object v : this.entries.values()) {
            if (!selector.match(v)) continue;
            arrayList.add(v);
        }
        return Collections.unmodifiableList(arrayList);
    }

    public Store toCertificateStore() {
        Collection collection = this.getMatches((Selector)null);
        ArrayList<X509CertificateHolder> arrayList = new ArrayList<X509CertificateHolder>(collection.size());
        for (DANEEntry dANEEntry : collection) {
            arrayList.add(dANEEntry.getCertificate());
        }
        return new CollectionStore(arrayList);
    }
}

