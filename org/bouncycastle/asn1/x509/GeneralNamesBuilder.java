/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509;

import java.util.Vector;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;

public class GeneralNamesBuilder {
    private Vector names = new Vector();

    public GeneralNamesBuilder addNames(GeneralNames generalNames) {
        GeneralName[] generalNameArray = generalNames.getNames();
        for (int i = 0; i != generalNameArray.length; ++i) {
            this.names.addElement(generalNameArray[i]);
        }
        return this;
    }

    public GeneralNamesBuilder addName(GeneralName generalName) {
        this.names.addElement(generalName);
        return this;
    }

    public GeneralNames build() {
        GeneralName[] generalNameArray = new GeneralName[this.names.size()];
        for (int i = 0; i != generalNameArray.length; ++i) {
            generalNameArray[i] = (GeneralName)this.names.elementAt(i);
        }
        return new GeneralNames(generalNameArray);
    }
}

