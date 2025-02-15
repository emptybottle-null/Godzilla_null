/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Exception;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1OutputStream;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.BEROctetString;
import org.bouncycastle.asn1.BERSequence;
import org.bouncycastle.asn1.StreamUtil;

public class BERTaggedObject
extends ASN1TaggedObject {
    public BERTaggedObject(int n, ASN1Encodable aSN1Encodable) {
        super(true, n, aSN1Encodable);
    }

    public BERTaggedObject(boolean bl, int n, ASN1Encodable aSN1Encodable) {
        super(bl, n, aSN1Encodable);
    }

    public BERTaggedObject(int n) {
        super(false, n, new BERSequence());
    }

    boolean isConstructed() {
        if (!this.empty) {
            if (this.explicit) {
                return true;
            }
            ASN1Primitive aSN1Primitive = this.obj.toASN1Primitive().toDERObject();
            return aSN1Primitive.isConstructed();
        }
        return true;
    }

    int encodedLength() throws IOException {
        if (!this.empty) {
            ASN1Primitive aSN1Primitive = this.obj.toASN1Primitive();
            int n = aSN1Primitive.encodedLength();
            if (this.explicit) {
                return StreamUtil.calculateTagLength(this.tagNo) + StreamUtil.calculateBodyLength(n) + n;
            }
            return StreamUtil.calculateTagLength(this.tagNo) + --n;
        }
        return StreamUtil.calculateTagLength(this.tagNo) + 1;
    }

    void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.writeTag(160, this.tagNo);
        aSN1OutputStream.write(128);
        if (!this.empty) {
            if (!this.explicit) {
                Enumeration enumeration;
                if (this.obj instanceof ASN1OctetString) {
                    if (this.obj instanceof BEROctetString) {
                        enumeration = ((BEROctetString)this.obj).getObjects();
                    } else {
                        ASN1OctetString aSN1OctetString = (ASN1OctetString)this.obj;
                        BEROctetString bEROctetString = new BEROctetString(aSN1OctetString.getOctets());
                        enumeration = bEROctetString.getObjects();
                    }
                } else if (this.obj instanceof ASN1Sequence) {
                    enumeration = ((ASN1Sequence)this.obj).getObjects();
                } else if (this.obj instanceof ASN1Set) {
                    enumeration = ((ASN1Set)this.obj).getObjects();
                } else {
                    throw new ASN1Exception("not implemented: " + this.obj.getClass().getName());
                }
                while (enumeration.hasMoreElements()) {
                    aSN1OutputStream.writeObject((ASN1Encodable)enumeration.nextElement());
                }
            } else {
                aSN1OutputStream.writeObject(this.obj);
            }
        }
        aSN1OutputStream.write(0);
        aSN1OutputStream.write(0);
    }
}

