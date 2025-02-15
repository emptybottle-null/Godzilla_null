/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cmc;

import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.asn1.x509.Extension;

class Utils {
    Utils() {
    }

    static BodyPartID[] toBodyPartIDArray(ASN1Sequence aSN1Sequence) {
        BodyPartID[] bodyPartIDArray = new BodyPartID[aSN1Sequence.size()];
        for (int i = 0; i != aSN1Sequence.size(); ++i) {
            bodyPartIDArray[i] = BodyPartID.getInstance(aSN1Sequence.getObjectAt(i));
        }
        return bodyPartIDArray;
    }

    static BodyPartID[] clone(BodyPartID[] bodyPartIDArray) {
        BodyPartID[] bodyPartIDArray2 = new BodyPartID[bodyPartIDArray.length];
        System.arraycopy(bodyPartIDArray, 0, bodyPartIDArray2, 0, bodyPartIDArray.length);
        return bodyPartIDArray2;
    }

    static Extension[] clone(Extension[] extensionArray) {
        Extension[] extensionArray2 = new Extension[extensionArray.length];
        System.arraycopy(extensionArray, 0, extensionArray2, 0, extensionArray.length);
        return extensionArray2;
    }
}

