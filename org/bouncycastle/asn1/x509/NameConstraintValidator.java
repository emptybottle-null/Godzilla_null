/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralSubtree;
import org.bouncycastle.asn1.x509.NameConstraintValidatorException;

public interface NameConstraintValidator {
    public void checkPermitted(GeneralName var1) throws NameConstraintValidatorException;

    public void checkExcluded(GeneralName var1) throws NameConstraintValidatorException;

    public void intersectPermittedSubtree(GeneralSubtree var1);

    public void intersectPermittedSubtree(GeneralSubtree[] var1);

    public void intersectEmptyPermittedSubtree(int var1);

    public void addExcludedSubtree(GeneralSubtree var1);
}

