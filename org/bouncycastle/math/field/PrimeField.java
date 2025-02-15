/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.field;

import java.math.BigInteger;
import org.bouncycastle.math.field.FiniteField;

class PrimeField
implements FiniteField {
    protected final BigInteger characteristic;

    PrimeField(BigInteger bigInteger) {
        this.characteristic = bigInteger;
    }

    public BigInteger getCharacteristic() {
        return this.characteristic;
    }

    public int getDimension() {
        return 1;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof PrimeField)) {
            return false;
        }
        PrimeField primeField = (PrimeField)object;
        return this.characteristic.equals(primeField.characteristic);
    }

    public int hashCode() {
        return this.characteristic.hashCode();
    }
}

