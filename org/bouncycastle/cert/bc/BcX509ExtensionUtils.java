/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.bc;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.AuthorityKeyIdentifier;
import org.bouncycastle.asn1.x509.SubjectKeyIdentifier;
import org.bouncycastle.cert.X509ExtensionUtils;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.util.SubjectPublicKeyInfoFactory;
import org.bouncycastle.operator.DigestCalculator;

public class BcX509ExtensionUtils
extends X509ExtensionUtils {
    public BcX509ExtensionUtils() {
        super(new SHA1DigestCalculator());
    }

    public BcX509ExtensionUtils(DigestCalculator digestCalculator) {
        super(digestCalculator);
    }

    public AuthorityKeyIdentifier createAuthorityKeyIdentifier(AsymmetricKeyParameter asymmetricKeyParameter) throws IOException {
        return super.createAuthorityKeyIdentifier(SubjectPublicKeyInfoFactory.createSubjectPublicKeyInfo(asymmetricKeyParameter));
    }

    public SubjectKeyIdentifier createSubjectKeyIdentifier(AsymmetricKeyParameter asymmetricKeyParameter) throws IOException {
        return super.createSubjectKeyIdentifier(SubjectPublicKeyInfoFactory.createSubjectPublicKeyInfo(asymmetricKeyParameter));
    }

    private static class SHA1DigestCalculator
    implements DigestCalculator {
        private ByteArrayOutputStream bOut = new ByteArrayOutputStream();

        private SHA1DigestCalculator() {
        }

        public AlgorithmIdentifier getAlgorithmIdentifier() {
            return new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1);
        }

        public OutputStream getOutputStream() {
            return this.bOut;
        }

        public byte[] getDigest() {
            byte[] byArray = this.bOut.toByteArray();
            this.bOut.reset();
            SHA1Digest sHA1Digest = new SHA1Digest();
            sHA1Digest.update(byArray, 0, byArray.length);
            byte[] byArray2 = new byte[sHA1Digest.getDigestSize()];
            sHA1Digest.doFinal(byArray2, 0);
            return byArray2;
        }
    }
}

