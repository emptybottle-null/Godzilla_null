/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1OutputStream;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1SetParser;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.BERSet;
import org.bouncycastle.asn1.BERTaggedObject;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.DLSet;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Iterable;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class ASN1Set
extends ASN1Primitive
implements Iterable<ASN1Encodable> {
    private Vector set = new Vector();
    private boolean isSorted = false;

    public static ASN1Set getInstance(Object object) {
        ASN1Primitive aSN1Primitive;
        if (object == null || object instanceof ASN1Set) {
            return (ASN1Set)object;
        }
        if (object instanceof ASN1SetParser) {
            return ASN1Set.getInstance(((ASN1SetParser)object).toASN1Primitive());
        }
        if (object instanceof byte[]) {
            try {
                return ASN1Set.getInstance(ASN1Primitive.fromByteArray((byte[])object));
            } catch (IOException iOException) {
                throw new IllegalArgumentException("failed to construct set from byte[]: " + iOException.getMessage());
            }
        }
        if (object instanceof ASN1Encodable && (aSN1Primitive = ((ASN1Encodable)object).toASN1Primitive()) instanceof ASN1Set) {
            return (ASN1Set)aSN1Primitive;
        }
        throw new IllegalArgumentException("unknown object in getInstance: " + object.getClass().getName());
    }

    public static ASN1Set getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        if (bl) {
            if (!aSN1TaggedObject.isExplicit()) {
                throw new IllegalArgumentException("object implicit - explicit expected.");
            }
            return (ASN1Set)aSN1TaggedObject.getObject();
        }
        if (aSN1TaggedObject.isExplicit()) {
            if (aSN1TaggedObject instanceof BERTaggedObject) {
                return new BERSet(aSN1TaggedObject.getObject());
            }
            return new DLSet(aSN1TaggedObject.getObject());
        }
        if (aSN1TaggedObject.getObject() instanceof ASN1Set) {
            return (ASN1Set)aSN1TaggedObject.getObject();
        }
        if (aSN1TaggedObject.getObject() instanceof ASN1Sequence) {
            ASN1Sequence aSN1Sequence = (ASN1Sequence)aSN1TaggedObject.getObject();
            if (aSN1TaggedObject instanceof BERTaggedObject) {
                return new BERSet(aSN1Sequence.toArray());
            }
            return new DLSet(aSN1Sequence.toArray());
        }
        throw new IllegalArgumentException("unknown object in getInstance: " + aSN1TaggedObject.getClass().getName());
    }

    protected ASN1Set() {
    }

    protected ASN1Set(ASN1Encodable aSN1Encodable) {
        this.set.addElement(aSN1Encodable);
    }

    protected ASN1Set(ASN1EncodableVector aSN1EncodableVector, boolean bl) {
        for (int i = 0; i != aSN1EncodableVector.size(); ++i) {
            this.set.addElement(aSN1EncodableVector.get(i));
        }
        if (bl) {
            this.sort();
        }
    }

    protected ASN1Set(ASN1Encodable[] aSN1EncodableArray, boolean bl) {
        for (int i = 0; i != aSN1EncodableArray.length; ++i) {
            this.set.addElement(aSN1EncodableArray[i]);
        }
        if (bl) {
            this.sort();
        }
    }

    public Enumeration getObjects() {
        return this.set.elements();
    }

    public ASN1Encodable getObjectAt(int n) {
        return (ASN1Encodable)this.set.elementAt(n);
    }

    public int size() {
        return this.set.size();
    }

    public ASN1Encodable[] toArray() {
        ASN1Encodable[] aSN1EncodableArray = new ASN1Encodable[this.size()];
        for (int i = 0; i != this.size(); ++i) {
            aSN1EncodableArray[i] = this.getObjectAt(i);
        }
        return aSN1EncodableArray;
    }

    public ASN1SetParser parser() {
        final ASN1Set aSN1Set = this;
        return new ASN1SetParser(){
            private final int max;
            private int index;
            {
                this.max = ASN1Set.this.size();
            }

            public ASN1Encodable readObject() throws IOException {
                ASN1Encodable aSN1Encodable;
                if (this.index == this.max) {
                    return null;
                }
                if ((aSN1Encodable = ASN1Set.this.getObjectAt(this.index++)) instanceof ASN1Sequence) {
                    return ((ASN1Sequence)aSN1Encodable).parser();
                }
                if (aSN1Encodable instanceof ASN1Set) {
                    return ((ASN1Set)aSN1Encodable).parser();
                }
                return aSN1Encodable;
            }

            public ASN1Primitive getLoadedObject() {
                return aSN1Set;
            }

            public ASN1Primitive toASN1Primitive() {
                return aSN1Set;
            }
        };
    }

    @Override
    public int hashCode() {
        Enumeration enumeration = this.getObjects();
        int n = this.size();
        while (enumeration.hasMoreElements()) {
            ASN1Encodable aSN1Encodable = this.getNext(enumeration);
            n *= 17;
            n ^= aSN1Encodable.hashCode();
        }
        return n;
    }

    @Override
    ASN1Primitive toDERObject() {
        if (this.isSorted) {
            DERSet dERSet = new DERSet();
            dERSet.set = this.set;
            return dERSet;
        }
        Vector vector = new Vector();
        for (int i = 0; i != this.set.size(); ++i) {
            vector.addElement(this.set.elementAt(i));
        }
        DERSet dERSet = new DERSet();
        dERSet.set = vector;
        dERSet.sort();
        return dERSet;
    }

    @Override
    ASN1Primitive toDLObject() {
        DLSet dLSet = new DLSet();
        dLSet.set = this.set;
        return dLSet;
    }

    @Override
    boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof ASN1Set)) {
            return false;
        }
        ASN1Set aSN1Set = (ASN1Set)aSN1Primitive;
        if (this.size() != aSN1Set.size()) {
            return false;
        }
        Enumeration enumeration = this.getObjects();
        Enumeration enumeration2 = aSN1Set.getObjects();
        while (enumeration.hasMoreElements()) {
            ASN1Primitive aSN1Primitive2;
            ASN1Encodable aSN1Encodable = this.getNext(enumeration);
            ASN1Encodable aSN1Encodable2 = this.getNext(enumeration2);
            ASN1Primitive aSN1Primitive3 = aSN1Encodable.toASN1Primitive();
            if (aSN1Primitive3 == (aSN1Primitive2 = aSN1Encodable2.toASN1Primitive()) || aSN1Primitive3.equals(aSN1Primitive2)) continue;
            return false;
        }
        return true;
    }

    private ASN1Encodable getNext(Enumeration enumeration) {
        ASN1Encodable aSN1Encodable = (ASN1Encodable)enumeration.nextElement();
        if (aSN1Encodable == null) {
            return DERNull.INSTANCE;
        }
        return aSN1Encodable;
    }

    private boolean lessThanOrEqual(byte[] byArray, byte[] byArray2) {
        int n = Math.min(byArray.length, byArray2.length);
        for (int i = 0; i != n; ++i) {
            if (byArray[i] == byArray2[i]) continue;
            return (byArray[i] & 0xFF) < (byArray2[i] & 0xFF);
        }
        return n == byArray.length;
    }

    private byte[] getDEREncoded(ASN1Encodable aSN1Encodable) {
        try {
            return aSN1Encodable.toASN1Primitive().getEncoded("DER");
        } catch (IOException iOException) {
            throw new IllegalArgumentException("cannot encode object added to SET");
        }
    }

    protected void sort() {
        if (!this.isSorted) {
            this.isSorted = true;
            if (this.set.size() > 1) {
                boolean bl = true;
                int n = this.set.size() - 1;
                while (bl) {
                    int n2 = 0;
                    byte[] byArray = this.getDEREncoded((ASN1Encodable)this.set.elementAt(0));
                    bl = false;
                    for (int i = 0; i != n; ++i) {
                        byte[] byArray2 = this.getDEREncoded((ASN1Encodable)this.set.elementAt(i + 1));
                        if (this.lessThanOrEqual(byArray, byArray2)) {
                            byArray = byArray2;
                            continue;
                        }
                        Object e = this.set.elementAt(i);
                        this.set.setElementAt(this.set.elementAt(i + 1), i);
                        this.set.setElementAt(e, i + 1);
                        bl = true;
                        n2 = i;
                    }
                    n = n2;
                }
            }
        }
    }

    @Override
    boolean isConstructed() {
        return true;
    }

    @Override
    abstract void encode(ASN1OutputStream var1) throws IOException;

    public String toString() {
        return this.set.toString();
    }

    @Override
    public Iterator<ASN1Encodable> iterator() {
        return new Arrays.Iterator<ASN1Encodable>(this.toArray());
    }
}

