/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.path;

import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.path.CertPathValidationContext;
import org.bouncycastle.cert.path.CertPathValidationException;
import org.bouncycastle.util.Memoable;

public interface CertPathValidation
extends Memoable {
    public void validate(CertPathValidationContext var1, X509CertificateHolder var2) throws CertPathValidationException;
}

