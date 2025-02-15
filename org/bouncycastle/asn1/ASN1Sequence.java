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
import org.bouncycastle.asn1.ASN1SequenceParser;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.BERSequence;
import org.bouncycastle.asn1.BERTaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DLSequence;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Iterable;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class ASN1Sequence
extends ASN1Primitive
implements Iterable<ASN1Encodable> {
    protected Vector seq = new Vector();

    public static ASN1Sequence getInstance(Object object) {
        ASN1Primitive aSN1Primitive;
        if (object == null || object instanceof ASN1Sequence) {
            return (ASN1Sequence)object;
        }
        if (object instanceof ASN1SequenceParser) {
            return ASN1Sequence.getInstance(((ASN1SequenceParser)object).toASN1Primitive());
        }
        if (object instanceof byte[]) {
            try {
                return ASN1Sequence.getInstance(ASN1Sequence.fromByteArray((byte[])object));
            } catch (IOException iOException) {
                throw new IllegalArgumentException("failed to construct sequence from byte[]: " + iOException.getMessage());
            }
        }
        if (object instanceof ASN1Encodable && (aSN1Primitive = ((ASN1Encodable)object).toASN1Primitive()) instanceof ASN1Sequence) {
            return (ASN1Sequence)aSN1Primitive;
        }
        throw new IllegalArgumentException("unknown object in getInstance: " + object.getClass().getName());
    }

    public static ASN1Sequence getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        if (bl) {
            if (!aSN1TaggedObject.isExplicit()) {
                throw new IllegalArgumentException("object implicit - explicit expected.");
            }
            return ASN1Sequence.getInstance(aSN1TaggedObject.getObject().toASN1Primitive());
        }
        if (aSN1TaggedObject.isExplicit()) {
            if (aSN1TaggedObject instanceof BERTaggedObject) {
                return new BERSequence(aSN1TaggedObject.getObject());
            }
            return new DLSequence(aSN1TaggedObject.getObject());
        }
        if (aSN1TaggedObject.getObject() instanceof ASN1Sequence) {
            return (ASN1Sequence)aSN1TaggedObject.getObject();
        }
        throw new IllegalArgumentException("unknown object in getInstance: " + aSN1TaggedObject.getClass().getName());
    }

    protected ASN1Sequence() {
    }

    protected ASN1Sequence(ASN1Encodable aSN1Encodable) {
        this.seq.addElement(aSN1Encodable);
    }

    protected ASN1Sequence(ASN1EncodableVector aSN1EncodableVector) {
        for (int i = 0; i != aSN1EncodableVector.size(); ++i) {
            this.seq.addElement(aSN1EncodableVector.get(i));
        }
    }

    protected ASN1Sequence(ASN1Encodable[] aSN1EncodableArray) {
        for (int i = 0; i != aSN1EncodableArray.length; ++i) {
            this.seq.addElement(aSN1EncodableArray[i]);
        }
    }

    public ASN1Encodable[] toArray() {
        ASN1Encodable[] aSN1EncodableArray = new ASN1Encodable[this.size()];
        for (int i = 0; i != this.size(); ++i) {
            aSN1EncodableArray[i] = this.getObjectAt(i);
        }
        return aSN1EncodableArray;
    }

    public Enumeration getObjects() {
        return this.seq.elements();
    }

    public ASN1SequenceParser parser() {
        final ASN1Sequence aSN1Sequence = this;
        return new ASN1SequenceParser(){
            private final int max;
            private int index;
            {
                this.max = ASN1Sequence.this.size();
            }

            public ASN1Encodable readObject() throws IOException {
                ASN1Encodable aSN1Encodable;
                if (this.index == this.max) {
                    return null;
                }
                if ((aSN1Encodable = ASN1Sequence.this.getObjectAt(this.index++)) instanceof ASN1Sequence) {
                    return ((ASN1Sequence)aSN1Encodable).parser();
                }
                if (aSN1Encodable instanceof ASN1Set) {
                    return ((ASN1Set)aSN1Encodable).parser();
                }
                return aSN1Encodable;
            }

            public ASN1Primitive getLoadedObject() {
                return aSN1Sequence;
            }

            public ASN1Primitive toASN1Primitive() {
                return aSN1Sequence;
            }
        };
    }

    public ASN1Encodable getObjectAt(int n) {
        return (ASN1Encodable)this.seq.elementAt(n);
    }

    public int size() {
        return this.seq.size();
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
    boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof ASN1Sequence)) {
            return false;
        }
        ASN1Sequence aSN1Sequence = (ASN1Sequence)aSN1Primitive;
        if (this.size() != aSN1Sequence.size()) {
            return false;
        }
        Enumeration enumeration = this.getObjects();
        Enumeration enumeration2 = aSN1Sequence.getObjects();
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
        return aSN1Encodable;
    }

    @Override
    ASN1Primitive toDERObject() {
        DERSequence dERSequence = new DERSequence();
        dERSequence.seq = this.seq;
        return dERSequence;
    }

    @Override
    ASN1Primitive toDLObject() {
        DLSequence dLSequence = new DLSequence();
        dLSequence.seq = this.seq;
        return dLSequence;
    }

    @Override
    boolean isConstructed() {
        return true;
    }

    @Override
    abstract void encode(ASN1OutputStream var1) throws IOException;

    public String toString() {
        return this.seq.toString();
    }

    @Override
    public Iterator<ASN1Encodable> iterator() {
        return new Arrays.Iterator<ASN1Encodable>(this.toArray());
    }
}

