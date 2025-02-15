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
import org.bouncycastle.cms.SignerId;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.util.Iterable;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class SignerInformationStore
implements Iterable<SignerInformation> {
    private List all = new ArrayList();
    private Map table = new HashMap();

    public SignerInformationStore(SignerInformation signerInformation) {
        this.all = new ArrayList(1);
        this.all.add(signerInformation);
        SignerId signerId = signerInformation.getSID();
        this.table.put(signerId, this.all);
    }

    public SignerInformationStore(Collection<SignerInformation> collection) {
        for (SignerInformation signerInformation : collection) {
            SignerId signerId = signerInformation.getSID();
            ArrayList<SignerInformation> arrayList = (ArrayList<SignerInformation>)this.table.get(signerId);
            if (arrayList == null) {
                arrayList = new ArrayList<SignerInformation>(1);
                this.table.put(signerId, arrayList);
            }
            arrayList.add(signerInformation);
        }
        this.all = new ArrayList<SignerInformation>(collection);
    }

    public SignerInformation get(SignerId signerId) {
        Collection<SignerInformation> collection = this.getSigners(signerId);
        return collection.size() == 0 ? null : collection.iterator().next();
    }

    public int size() {
        return this.all.size();
    }

    public Collection<SignerInformation> getSigners() {
        return new ArrayList<SignerInformation>(this.all);
    }

    public Collection<SignerInformation> getSigners(SignerId signerId) {
        if (signerId.getIssuer() != null && signerId.getSubjectKeyIdentifier() != null) {
            Collection<SignerInformation> collection;
            ArrayList<SignerInformation> arrayList = new ArrayList<SignerInformation>();
            Collection<SignerInformation> collection2 = this.getSigners(new SignerId(signerId.getIssuer(), signerId.getSerialNumber()));
            if (collection2 != null) {
                arrayList.addAll(collection2);
            }
            if ((collection = this.getSigners(new SignerId(signerId.getSubjectKeyIdentifier()))) != null) {
                arrayList.addAll(collection);
            }
            return arrayList;
        }
        ArrayList arrayList = (ArrayList)this.table.get(signerId);
        return arrayList == null ? new ArrayList<SignerInformation>() : new ArrayList(arrayList);
    }

    @Override
    public Iterator<SignerInformation> iterator() {
        return this.getSigners().iterator();
    }
}

