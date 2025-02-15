/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import org.bouncycastle.crypto.tls.Certificate;
import org.bouncycastle.crypto.tls.TlsProtocol;
import org.bouncycastle.util.Arrays;

public final class SessionParameters {
    private int cipherSuite;
    private short compressionAlgorithm;
    private byte[] masterSecret;
    private Certificate peerCertificate;
    private byte[] pskIdentity = null;
    private byte[] srpIdentity = null;
    private byte[] encodedServerExtensions;

    private SessionParameters(int n, short s, byte[] byArray, Certificate certificate, byte[] byArray2, byte[] byArray3, byte[] byArray4) {
        this.cipherSuite = n;
        this.compressionAlgorithm = s;
        this.masterSecret = Arrays.clone(byArray);
        this.peerCertificate = certificate;
        this.pskIdentity = Arrays.clone(byArray2);
        this.srpIdentity = Arrays.clone(byArray3);
        this.encodedServerExtensions = byArray4;
    }

    public void clear() {
        if (this.masterSecret != null) {
            Arrays.fill(this.masterSecret, (byte)0);
        }
    }

    public SessionParameters copy() {
        return new SessionParameters(this.cipherSuite, this.compressionAlgorithm, this.masterSecret, this.peerCertificate, this.pskIdentity, this.srpIdentity, this.encodedServerExtensions);
    }

    public int getCipherSuite() {
        return this.cipherSuite;
    }

    public short getCompressionAlgorithm() {
        return this.compressionAlgorithm;
    }

    public byte[] getMasterSecret() {
        return this.masterSecret;
    }

    public Certificate getPeerCertificate() {
        return this.peerCertificate;
    }

    public byte[] getPskIdentity() {
        return this.pskIdentity;
    }

    public byte[] getPSKIdentity() {
        return this.pskIdentity;
    }

    public byte[] getSRPIdentity() {
        return this.srpIdentity;
    }

    public Hashtable readServerExtensions() throws IOException {
        if (this.encodedServerExtensions == null) {
            return null;
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.encodedServerExtensions);
        return TlsProtocol.readExtensions(byteArrayInputStream);
    }

    public static final class Builder {
        private int cipherSuite = -1;
        private short compressionAlgorithm = (short)-1;
        private byte[] masterSecret = null;
        private Certificate peerCertificate = null;
        private byte[] pskIdentity = null;
        private byte[] srpIdentity = null;
        private byte[] encodedServerExtensions = null;

        public SessionParameters build() {
            this.validate(this.cipherSuite >= 0, "cipherSuite");
            this.validate(this.compressionAlgorithm >= 0, "compressionAlgorithm");
            this.validate(this.masterSecret != null, "masterSecret");
            return new SessionParameters(this.cipherSuite, this.compressionAlgorithm, this.masterSecret, this.peerCertificate, this.pskIdentity, this.srpIdentity, this.encodedServerExtensions);
        }

        public Builder setCipherSuite(int n) {
            this.cipherSuite = n;
            return this;
        }

        public Builder setCompressionAlgorithm(short s) {
            this.compressionAlgorithm = s;
            return this;
        }

        public Builder setMasterSecret(byte[] byArray) {
            this.masterSecret = byArray;
            return this;
        }

        public Builder setPeerCertificate(Certificate certificate) {
            this.peerCertificate = certificate;
            return this;
        }

        public Builder setPskIdentity(byte[] byArray) {
            this.pskIdentity = byArray;
            return this;
        }

        public Builder setPSKIdentity(byte[] byArray) {
            this.pskIdentity = byArray;
            return this;
        }

        public Builder setSRPIdentity(byte[] byArray) {
            this.srpIdentity = byArray;
            return this;
        }

        public Builder setServerExtensions(Hashtable hashtable) throws IOException {
            if (hashtable == null) {
                this.encodedServerExtensions = null;
            } else {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                TlsProtocol.writeExtensions(byteArrayOutputStream, hashtable);
                this.encodedServerExtensions = byteArrayOutputStream.toByteArray();
            }
            return this;
        }

        private void validate(boolean bl, String string) {
            if (!bl) {
                throw new IllegalStateException("Required session parameter '" + string + "' not configured");
            }
        }
    }
}

