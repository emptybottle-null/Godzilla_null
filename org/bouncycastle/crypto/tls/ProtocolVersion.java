/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsUtils;
import org.bouncycastle.util.Strings;

public final class ProtocolVersion {
    public static final ProtocolVersion SSLv3 = new ProtocolVersion(768, "SSL 3.0");
    public static final ProtocolVersion TLSv10 = new ProtocolVersion(769, "TLS 1.0");
    public static final ProtocolVersion TLSv11 = new ProtocolVersion(770, "TLS 1.1");
    public static final ProtocolVersion TLSv12 = new ProtocolVersion(771, "TLS 1.2");
    public static final ProtocolVersion DTLSv10 = new ProtocolVersion(65279, "DTLS 1.0");
    public static final ProtocolVersion DTLSv12 = new ProtocolVersion(65277, "DTLS 1.2");
    private int version;
    private String name;

    private ProtocolVersion(int n, String string) {
        this.version = n & 0xFFFF;
        this.name = string;
    }

    public int getFullVersion() {
        return this.version;
    }

    public int getMajorVersion() {
        return this.version >> 8;
    }

    public int getMinorVersion() {
        return this.version & 0xFF;
    }

    public boolean isDTLS() {
        return this.getMajorVersion() == 254;
    }

    public boolean isSSL() {
        return this == SSLv3;
    }

    public boolean isTLS() {
        return this.getMajorVersion() == 3;
    }

    public ProtocolVersion getEquivalentTLSVersion() {
        if (!this.isDTLS()) {
            return this;
        }
        if (this == DTLSv10) {
            return TLSv11;
        }
        return TLSv12;
    }

    public boolean isEqualOrEarlierVersionOf(ProtocolVersion protocolVersion) {
        if (this.getMajorVersion() != protocolVersion.getMajorVersion()) {
            return false;
        }
        int n = protocolVersion.getMinorVersion() - this.getMinorVersion();
        return this.isDTLS() ? n <= 0 : n >= 0;
    }

    public boolean isLaterVersionOf(ProtocolVersion protocolVersion) {
        if (this.getMajorVersion() != protocolVersion.getMajorVersion()) {
            return false;
        }
        int n = protocolVersion.getMinorVersion() - this.getMinorVersion();
        return this.isDTLS() ? n > 0 : n < 0;
    }

    public boolean equals(Object object) {
        return this == object || object instanceof ProtocolVersion && this.equals((ProtocolVersion)object);
    }

    public boolean equals(ProtocolVersion protocolVersion) {
        return protocolVersion != null && this.version == protocolVersion.version;
    }

    public int hashCode() {
        return this.version;
    }

    public static ProtocolVersion get(int n, int n2) throws IOException {
        switch (n) {
            case 3: {
                switch (n2) {
                    case 0: {
                        return SSLv3;
                    }
                    case 1: {
                        return TLSv10;
                    }
                    case 2: {
                        return TLSv11;
                    }
                    case 3: {
                        return TLSv12;
                    }
                }
                return ProtocolVersion.getUnknownVersion(n, n2, "TLS");
            }
            case 254: {
                switch (n2) {
                    case 255: {
                        return DTLSv10;
                    }
                    case 254: {
                        throw new TlsFatalAlert(47);
                    }
                    case 253: {
                        return DTLSv12;
                    }
                }
                return ProtocolVersion.getUnknownVersion(n, n2, "DTLS");
            }
        }
        throw new TlsFatalAlert(47);
    }

    public String toString() {
        return this.name;
    }

    private static ProtocolVersion getUnknownVersion(int n, int n2, String string) throws IOException {
        TlsUtils.checkUint8(n);
        TlsUtils.checkUint8(n2);
        int n3 = n << 8 | n2;
        String string2 = Strings.toUpperCase(Integer.toHexString(0x10000 | n3).substring(1));
        return new ProtocolVersion(n3, string + " 0x" + string2);
    }
}

