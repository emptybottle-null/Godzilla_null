/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cms.KeyTransRecipientId;
import org.bouncycastle.cms.Recipient;
import org.bouncycastle.cms.RecipientId;
import org.bouncycastle.cms.RecipientInformation;
import org.bouncycastle.util.Iterable;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class RecipientInformationStore
implements Iterable<RecipientInformation> {
    private final List all;
    private final Map table = new HashMap();

    public RecipientInformationStore(RecipientInformation recipientInformation) {
        this.all = new ArrayList(1);
        this.all.add(recipientInformation);
        RecipientId recipientId = recipientInformation.getRID();
        this.table.put(recipientId, this.all);
    }

    public RecipientInformationStore(Collection<RecipientInformation> collection) {
        for (RecipientInformation recipientInformation : collection) {
            RecipientId recipientId = recipientInformation.getRID();
            ArrayList<RecipientInformation> arrayList = (ArrayList<RecipientInformation>)this.table.get(recipientId);
            if (arrayList == null) {
                arrayList = new ArrayList<RecipientInformation>(1);
                this.table.put(recipientId, arrayList);
            }
            arrayList.add(recipientInformation);
        }
        this.all = new ArrayList<RecipientInformation>(collection);
    }

    public RecipientInformation get(RecipientId recipientId) {
        Collection<Recipient> collection = this.getRecipients(recipientId);
        return collection.size() == 0 ? null : (RecipientInformation)((Object)collection.iterator().next());
    }

    public int size() {
        return this.all.size();
    }

    public Collection<RecipientInformation> getRecipients() {
        return new ArrayList<RecipientInformation>(this.all);
    }

    public Collection<Recipient> getRecipients(RecipientId recipientId) {
        Cloneable cloneable;
        if (recipientId instanceof KeyTransRecipientId) {
            cloneable = (KeyTransRecipientId)recipientId;
            X500Name x500Name = ((KeyTransRecipientId)cloneable).getIssuer();
            byte[] byArray = ((KeyTransRecipientId)cloneable).getSubjectKeyIdentifier();
            if (x500Name != null && byArray != null) {
                Collection<Recipient> collection;
                ArrayList<Recipient> arrayList = new ArrayList<Recipient>();
                Collection<Recipient> collection2 = this.getRecipients(new KeyTransRecipientId(x500Name, ((KeyTransRecipientId)cloneable).getSerialNumber()));
                if (collection2 != null) {
                    arrayList.addAll(collection2);
                }
                if ((collection = this.getRecipients(new KeyTransRecipientId(byArray))) != null) {
                    arrayList.addAll(collection);
                }
                return arrayList;
            }
        }
        return (cloneable = (ArrayList)this.table.get(recipientId)) == null ? new ArrayList<Recipient>() : new ArrayList(cloneable);
    }

    @Override
    public Iterator<RecipientInformation> iterator() {
        return this.getRecipients().iterator();
    }
}

