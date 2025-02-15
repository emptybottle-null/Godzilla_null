/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.util.Arrays;

public class KTSParameterSpec
implements AlgorithmParameterSpec {
    private final String wrappingKeyAlgorithm;
    private final int keySizeInBits;
    private final AlgorithmParameterSpec parameterSpec;
    private final AlgorithmIdentifier kdfAlgorithm;
    private byte[] otherInfo;

    private KTSParameterSpec(String string, int n, AlgorithmParameterSpec algorithmParameterSpec, AlgorithmIdentifier algorithmIdentifier, byte[] byArray) {
        this.wrappingKeyAlgorithm = string;
        this.keySizeInBits = n;
        this.parameterSpec = algorithmParameterSpec;
        this.kdfAlgorithm = algorithmIdentifier;
        this.otherInfo = byArray;
    }

    public String getKeyAlgorithmName() {
        return this.wrappingKeyAlgorithm;
    }

    public int getKeySize() {
        return this.keySizeInBits;
    }

    public AlgorithmParameterSpec getParameterSpec() {
        return this.parameterSpec;
    }

    public AlgorithmIdentifier getKdfAlgorithm() {
        return this.kdfAlgorithm;
    }

    public byte[] getOtherInfo() {
        return Arrays.clone(this.otherInfo);
    }

    public static final class Builder {
        private final String algorithmName;
        private final int keySizeInBits;
        private AlgorithmParameterSpec parameterSpec;
        private AlgorithmIdentifier kdfAlgorithm;
        private byte[] otherInfo;

        public Builder(String string, int n) {
            this(string, n, null);
        }

        public Builder(String string, int n, byte[] byArray) {
            this.algorithmName = string;
            this.keySizeInBits = n;
            this.kdfAlgorithm = new AlgorithmIdentifier(X9ObjectIdentifiers.id_kdf_kdf3, new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha256));
            this.otherInfo = byArray == null ? new byte[]{} : Arrays.clone(byArray);
        }

        public Builder withParameterSpec(AlgorithmParameterSpec algorithmParameterSpec) {
            this.parameterSpec = algorithmParameterSpec;
            return this;
        }

        public Builder withKdfAlgorithm(AlgorithmIdentifier algorithmIdentifier) {
            this.kdfAlgorithm = algorithmIdentifier;
            return this;
        }

        public KTSParameterSpec build() {
            return new KTSParameterSpec(this.algorithmName, this.keySizeInBits, this.parameterSpec, this.kdfAlgorithm, this.otherInfo);
        }
    }
}

