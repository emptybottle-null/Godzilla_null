/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.crmf;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.asn1.crmf.CRMFObjectIdentifiers;
import org.bouncycastle.cert.crmf.Control;

public class AuthenticatorControl
implements Control {
    private static final ASN1ObjectIdentifier type = CRMFObjectIdentifiers.id_regCtrl_authenticator;
    private final DERUTF8String token;

    public AuthenticatorControl(DERUTF8String dERUTF8String) {
        this.token = dERUTF8String;
    }

    public AuthenticatorControl(String string) {
        this.token = new DERUTF8String(string);
    }

    public ASN1ObjectIdentifier getType() {
        return type;
    }

    public ASN1Encodable getValue() {
        return this.token;
    }
}

