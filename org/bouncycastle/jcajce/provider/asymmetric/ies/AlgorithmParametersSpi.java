/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.ies;

import java.io.IOException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.jce.spec.IESParameterSpec;

public class AlgorithmParametersSpi
extends java.security.AlgorithmParametersSpi {
    IESParameterSpec currentSpec;

    protected boolean isASN1FormatString(String string) {
        return string == null || string.equals("ASN.1");
    }

    protected AlgorithmParameterSpec engineGetParameterSpec(Class clazz) throws InvalidParameterSpecException {
        if (clazz == null) {
            throw new NullPointerException("argument to getParameterSpec must not be null");
        }
        return this.localEngineGetParameterSpec(clazz);
    }

    protected byte[] engineGetEncoded() {
        try {
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            if (this.currentSpec.getDerivationV() != null) {
                aSN1EncodableVector.add(new DERTaggedObject(false, 0, new DEROctetString(this.currentSpec.getDerivationV())));
            }
            if (this.currentSpec.getEncodingV() != null) {
                aSN1EncodableVector.add(new DERTaggedObject(false, 1, new DEROctetString(this.currentSpec.getEncodingV())));
            }
            aSN1EncodableVector.add(new ASN1Integer(this.currentSpec.getMacKeySize()));
            if (this.currentSpec.getNonce() != null) {
                ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
                aSN1EncodableVector2.add(new ASN1Integer(this.currentSpec.getCipherKeySize()));
                aSN1EncodableVector2.add(new ASN1Integer(this.currentSpec.getNonce()));
                aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
            }
            return new DERSequence(aSN1EncodableVector).getEncoded("DER");
        } catch (IOException iOException) {
            throw new RuntimeException("Error encoding IESParameters");
        }
    }

    protected byte[] engineGetEncoded(String string) {
        if (this.isASN1FormatString(string) || string.equalsIgnoreCase("X.509")) {
            return this.engineGetEncoded();
        }
        return null;
    }

    protected AlgorithmParameterSpec localEngineGetParameterSpec(Class clazz) throws InvalidParameterSpecException {
        if (clazz == IESParameterSpec.class || clazz == AlgorithmParameterSpec.class) {
            return this.currentSpec;
        }
        throw new InvalidParameterSpecException("unknown parameter spec passed to ElGamal parameters object.");
    }

    protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
        if (!(algorithmParameterSpec instanceof IESParameterSpec)) {
            throw new InvalidParameterSpecException("IESParameterSpec required to initialise a IES algorithm parameters object");
        }
        this.currentSpec = (IESParameterSpec)algorithmParameterSpec;
    }

    protected void engineInit(byte[] byArray) throws IOException {
        try {
            ASN1Sequence aSN1Sequence = (ASN1Sequence)ASN1Primitive.fromByteArray(byArray);
            if (aSN1Sequence.size() == 1) {
                this.currentSpec = new IESParameterSpec(null, null, ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0)).getValue().intValue());
            } else if (aSN1Sequence.size() == 2) {
                ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(aSN1Sequence.getObjectAt(0));
                this.currentSpec = aSN1TaggedObject.getTagNo() == 0 ? new IESParameterSpec(ASN1OctetString.getInstance(aSN1TaggedObject, false).getOctets(), null, ASN1Integer.getInstance(aSN1Sequence.getObjectAt(1)).getValue().intValue()) : new IESParameterSpec(null, ASN1OctetString.getInstance(aSN1TaggedObject, false).getOctets(), ASN1Integer.getInstance(aSN1Sequence.getObjectAt(1)).getValue().intValue());
            } else if (aSN1Sequence.size() == 3) {
                ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(aSN1Sequence.getObjectAt(0));
                ASN1TaggedObject aSN1TaggedObject2 = ASN1TaggedObject.getInstance(aSN1Sequence.getObjectAt(1));
                this.currentSpec = new IESParameterSpec(ASN1OctetString.getInstance(aSN1TaggedObject, false).getOctets(), ASN1OctetString.getInstance(aSN1TaggedObject2, false).getOctets(), ASN1Integer.getInstance(aSN1Sequence.getObjectAt(2)).getValue().intValue());
            } else if (aSN1Sequence.size() == 4) {
                ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(aSN1Sequence.getObjectAt(0));
                ASN1TaggedObject aSN1TaggedObject3 = ASN1TaggedObject.getInstance(aSN1Sequence.getObjectAt(1));
                ASN1Sequence aSN1Sequence2 = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(3));
                this.currentSpec = new IESParameterSpec(ASN1OctetString.getInstance(aSN1TaggedObject, false).getOctets(), ASN1OctetString.getInstance(aSN1TaggedObject3, false).getOctets(), ASN1Integer.getInstance(aSN1Sequence.getObjectAt(2)).getValue().intValue(), ASN1Integer.getInstance(aSN1Sequence2.getObjectAt(0)).getValue().intValue(), ASN1OctetString.getInstance(aSN1Sequence2.getObjectAt(1)).getOctets());
            }
        } catch (ClassCastException classCastException) {
            throw new IOException("Not a valid IES Parameter encoding.");
        } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            throw new IOException("Not a valid IES Parameter encoding.");
        }
    }

    protected void engineInit(byte[] byArray, String string) throws IOException {
        if (!this.isASN1FormatString(string) && !string.equalsIgnoreCase("X.509")) {
            throw new IOException("Unknown parameter format " + string);
        }
        this.engineInit(byArray);
    }

    protected String engineToString() {
        return "IES Parameters";
    }
}

