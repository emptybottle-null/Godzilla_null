/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Exception;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.BERApplicationSpecificParser;
import org.bouncycastle.asn1.BERFactory;
import org.bouncycastle.asn1.BEROctetStringParser;
import org.bouncycastle.asn1.BERSequenceParser;
import org.bouncycastle.asn1.BERSetParser;
import org.bouncycastle.asn1.BERTaggedObject;
import org.bouncycastle.asn1.BERTaggedObjectParser;
import org.bouncycastle.asn1.DERApplicationSpecific;
import org.bouncycastle.asn1.DERExternalParser;
import org.bouncycastle.asn1.DERFactory;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DEROctetStringParser;
import org.bouncycastle.asn1.DERSequenceParser;
import org.bouncycastle.asn1.DERSetParser;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.DefiniteLengthInputStream;
import org.bouncycastle.asn1.InMemoryRepresentable;
import org.bouncycastle.asn1.IndefiniteLengthInputStream;
import org.bouncycastle.asn1.StreamUtil;

public class ASN1StreamParser {
    private final InputStream _in;
    private final int _limit;
    private final byte[][] tmpBuffers;

    public ASN1StreamParser(InputStream inputStream) {
        this(inputStream, StreamUtil.findLimit(inputStream));
    }

    public ASN1StreamParser(InputStream inputStream, int n) {
        this._in = inputStream;
        this._limit = n;
        this.tmpBuffers = new byte[11][];
    }

    public ASN1StreamParser(byte[] byArray) {
        this(new ByteArrayInputStream(byArray), byArray.length);
    }

    ASN1Encodable readIndef(int n) throws IOException {
        switch (n) {
            case 8: {
                return new DERExternalParser(this);
            }
            case 4: {
                return new BEROctetStringParser(this);
            }
            case 16: {
                return new BERSequenceParser(this);
            }
            case 17: {
                return new BERSetParser(this);
            }
        }
        throw new ASN1Exception("unknown BER object encountered: 0x" + Integer.toHexString(n));
    }

    ASN1Encodable readImplicit(boolean bl, int n) throws IOException {
        if (this._in instanceof IndefiniteLengthInputStream) {
            if (!bl) {
                throw new IOException("indefinite-length primitive encoding encountered");
            }
            return this.readIndef(n);
        }
        if (bl) {
            switch (n) {
                case 17: {
                    return new DERSetParser(this);
                }
                case 16: {
                    return new DERSequenceParser(this);
                }
                case 4: {
                    return new BEROctetStringParser(this);
                }
            }
        } else {
            switch (n) {
                case 17: {
                    throw new ASN1Exception("sequences must use constructed encoding (see X.690 8.9.1/8.10.1)");
                }
                case 16: {
                    throw new ASN1Exception("sets must use constructed encoding (see X.690 8.11.1/8.12.1)");
                }
                case 4: {
                    return new DEROctetStringParser((DefiniteLengthInputStream)this._in);
                }
            }
        }
        throw new ASN1Exception("implicit tagging not implemented");
    }

    ASN1Primitive readTaggedObject(boolean bl, int n) throws IOException {
        if (!bl) {
            DefiniteLengthInputStream definiteLengthInputStream = (DefiniteLengthInputStream)this._in;
            return new DERTaggedObject(false, n, new DEROctetString(definiteLengthInputStream.toByteArray()));
        }
        ASN1EncodableVector aSN1EncodableVector = this.readVector();
        if (this._in instanceof IndefiniteLengthInputStream) {
            return aSN1EncodableVector.size() == 1 ? new BERTaggedObject(true, n, aSN1EncodableVector.get(0)) : new BERTaggedObject(false, n, BERFactory.createSequence(aSN1EncodableVector));
        }
        return aSN1EncodableVector.size() == 1 ? new DERTaggedObject(true, n, aSN1EncodableVector.get(0)) : new DERTaggedObject(false, n, DERFactory.createSequence(aSN1EncodableVector));
    }

    public ASN1Encodable readObject() throws IOException {
        int n = this._in.read();
        if (n == -1) {
            return null;
        }
        this.set00Check(false);
        int n2 = ASN1InputStream.readTagNumber(this._in, n);
        boolean bl = (n & 0x20) != 0;
        int n3 = ASN1InputStream.readLength(this._in, this._limit);
        if (n3 < 0) {
            if (!bl) {
                throw new IOException("indefinite-length primitive encoding encountered");
            }
            IndefiniteLengthInputStream indefiniteLengthInputStream = new IndefiniteLengthInputStream(this._in, this._limit);
            ASN1StreamParser aSN1StreamParser = new ASN1StreamParser(indefiniteLengthInputStream, this._limit);
            if ((n & 0x40) != 0) {
                return new BERApplicationSpecificParser(n2, aSN1StreamParser);
            }
            if ((n & 0x80) != 0) {
                return new BERTaggedObjectParser(true, n2, aSN1StreamParser);
            }
            return aSN1StreamParser.readIndef(n2);
        }
        DefiniteLengthInputStream definiteLengthInputStream = new DefiniteLengthInputStream(this._in, n3);
        if ((n & 0x40) != 0) {
            return new DERApplicationSpecific(bl, n2, definiteLengthInputStream.toByteArray());
        }
        if ((n & 0x80) != 0) {
            return new BERTaggedObjectParser(bl, n2, new ASN1StreamParser(definiteLengthInputStream));
        }
        if (bl) {
            switch (n2) {
                case 4: {
                    return new BEROctetStringParser(new ASN1StreamParser(definiteLengthInputStream));
                }
                case 16: {
                    return new DERSequenceParser(new ASN1StreamParser(definiteLengthInputStream));
                }
                case 17: {
                    return new DERSetParser(new ASN1StreamParser(definiteLengthInputStream));
                }
                case 8: {
                    return new DERExternalParser(new ASN1StreamParser(definiteLengthInputStream));
                }
            }
            throw new IOException("unknown tag " + n2 + " encountered");
        }
        switch (n2) {
            case 4: {
                return new DEROctetStringParser(definiteLengthInputStream);
            }
        }
        try {
            return ASN1InputStream.createPrimitiveDERObject(n2, definiteLengthInputStream, this.tmpBuffers);
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new ASN1Exception("corrupted stream detected", illegalArgumentException);
        }
    }

    private void set00Check(boolean bl) {
        if (this._in instanceof IndefiniteLengthInputStream) {
            ((IndefiniteLengthInputStream)this._in).setEofOn00(bl);
        }
    }

    ASN1EncodableVector readVector() throws IOException {
        ASN1Encodable aSN1Encodable;
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        while ((aSN1Encodable = this.readObject()) != null) {
            if (aSN1Encodable instanceof InMemoryRepresentable) {
                aSN1EncodableVector.add(((InMemoryRepresentable)((Object)aSN1Encodable)).getLoadedObject());
                continue;
            }
            aSN1EncodableVector.add(aSN1Encodable.toASN1Primitive());
        }
        return aSN1EncodableVector;
    }
}

