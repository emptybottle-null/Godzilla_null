/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.Extensions;

public class ExtensionsGenerator {
    private Hashtable extensions = new Hashtable();
    private Vector extOrdering = new Vector();

    public void reset() {
        this.extensions = new Hashtable();
        this.extOrdering = new Vector();
    }

    public void addExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean bl, ASN1Encodable aSN1Encodable) throws IOException {
        this.addExtension(aSN1ObjectIdentifier, bl, aSN1Encodable.toASN1Primitive().getEncoded("DER"));
    }

    public void addExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean bl, byte[] byArray) {
        if (this.extensions.containsKey(aSN1ObjectIdentifier)) {
            throw new IllegalArgumentException("extension " + aSN1ObjectIdentifier + " already added");
        }
        this.extOrdering.addElement(aSN1ObjectIdentifier);
        this.extensions.put(aSN1ObjectIdentifier, new Extension(aSN1ObjectIdentifier, bl, (ASN1OctetString)new DEROctetString(byArray)));
    }

    public void addExtension(Extension extension) {
        if (this.extensions.containsKey(extension.getExtnId())) {
            throw new IllegalArgumentException("extension " + extension.getExtnId() + " already added");
        }
        this.extOrdering.addElement(extension.getExtnId());
        this.extensions.put(extension.getExtnId(), extension);
    }

    public boolean isEmpty() {
        return this.extOrdering.isEmpty();
    }

    public Extensions generate() {
        Extension[] extensionArray = new Extension[this.extOrdering.size()];
        for (int i = 0; i != this.extOrdering.size(); ++i) {
            extensionArray[i] = (Extension)this.extensions.get(this.extOrdering.elementAt(i));
        }
        return new Extensions(extensionArray);
    }
}

