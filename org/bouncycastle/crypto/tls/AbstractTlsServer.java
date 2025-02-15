/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.crypto.tls.AbstractTlsPeer;
import org.bouncycastle.crypto.tls.Certificate;
import org.bouncycastle.crypto.tls.CertificateRequest;
import org.bouncycastle.crypto.tls.CertificateStatus;
import org.bouncycastle.crypto.tls.DefaultTlsCipherFactory;
import org.bouncycastle.crypto.tls.MaxFragmentLength;
import org.bouncycastle.crypto.tls.NamedCurve;
import org.bouncycastle.crypto.tls.NewSessionTicket;
import org.bouncycastle.crypto.tls.ProtocolVersion;
import org.bouncycastle.crypto.tls.TlsCipher;
import org.bouncycastle.crypto.tls.TlsCipherFactory;
import org.bouncycastle.crypto.tls.TlsCompression;
import org.bouncycastle.crypto.tls.TlsECCUtils;
import org.bouncycastle.crypto.tls.TlsExtensionsUtils;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsNullCompression;
import org.bouncycastle.crypto.tls.TlsServer;
import org.bouncycastle.crypto.tls.TlsServerContext;
import org.bouncycastle.crypto.tls.TlsUtils;
import org.bouncycastle.util.Arrays;

public abstract class AbstractTlsServer
extends AbstractTlsPeer
implements TlsServer {
    protected TlsCipherFactory cipherFactory;
    protected TlsServerContext context;
    protected ProtocolVersion clientVersion;
    protected int[] offeredCipherSuites;
    protected short[] offeredCompressionMethods;
    protected Hashtable clientExtensions;
    protected boolean encryptThenMACOffered;
    protected short maxFragmentLengthOffered;
    protected boolean truncatedHMacOffered;
    protected Vector supportedSignatureAlgorithms;
    protected boolean eccCipherSuitesOffered;
    protected int[] namedCurves;
    protected short[] clientECPointFormats;
    protected short[] serverECPointFormats;
    protected ProtocolVersion serverVersion;
    protected int selectedCipherSuite;
    protected short selectedCompressionMethod;
    protected Hashtable serverExtensions;

    public AbstractTlsServer() {
        this(new DefaultTlsCipherFactory());
    }

    public AbstractTlsServer(TlsCipherFactory tlsCipherFactory) {
        this.cipherFactory = tlsCipherFactory;
    }

    protected boolean allowEncryptThenMAC() {
        return true;
    }

    protected boolean allowTruncatedHMac() {
        return false;
    }

    protected Hashtable checkServerExtensions() {
        this.serverExtensions = TlsExtensionsUtils.ensureExtensionsInitialised(this.serverExtensions);
        return this.serverExtensions;
    }

    protected abstract int[] getCipherSuites();

    protected short[] getCompressionMethods() {
        return new short[]{0};
    }

    protected ProtocolVersion getMaximumVersion() {
        return ProtocolVersion.TLSv11;
    }

    protected ProtocolVersion getMinimumVersion() {
        return ProtocolVersion.TLSv10;
    }

    protected boolean supportsClientECCCapabilities(int[] nArray, short[] sArray) {
        if (nArray == null) {
            return TlsECCUtils.hasAnySupportedNamedCurves();
        }
        for (int i = 0; i < nArray.length; ++i) {
            int n = nArray[i];
            if (!NamedCurve.isValid(n) || NamedCurve.refersToASpecificNamedCurve(n) && !TlsECCUtils.isSupportedNamedCurve(n)) continue;
            return true;
        }
        return false;
    }

    public void init(TlsServerContext tlsServerContext) {
        this.context = tlsServerContext;
    }

    public void notifyClientVersion(ProtocolVersion protocolVersion) throws IOException {
        this.clientVersion = protocolVersion;
    }

    public void notifyFallback(boolean bl) throws IOException {
        if (bl && this.getMaximumVersion().isLaterVersionOf(this.clientVersion)) {
            throw new TlsFatalAlert(86);
        }
    }

    public void notifyOfferedCipherSuites(int[] nArray) throws IOException {
        this.offeredCipherSuites = nArray;
        this.eccCipherSuitesOffered = TlsECCUtils.containsECCCipherSuites(this.offeredCipherSuites);
    }

    public void notifyOfferedCompressionMethods(short[] sArray) throws IOException {
        this.offeredCompressionMethods = sArray;
    }

    public void processClientExtensions(Hashtable hashtable) throws IOException {
        this.clientExtensions = hashtable;
        if (hashtable != null) {
            this.encryptThenMACOffered = TlsExtensionsUtils.hasEncryptThenMACExtension(hashtable);
            this.maxFragmentLengthOffered = TlsExtensionsUtils.getMaxFragmentLengthExtension(hashtable);
            if (this.maxFragmentLengthOffered >= 0 && !MaxFragmentLength.isValid(this.maxFragmentLengthOffered)) {
                throw new TlsFatalAlert(47);
            }
            this.truncatedHMacOffered = TlsExtensionsUtils.hasTruncatedHMacExtension(hashtable);
            this.supportedSignatureAlgorithms = TlsUtils.getSignatureAlgorithmsExtension(hashtable);
            if (this.supportedSignatureAlgorithms != null && !TlsUtils.isSignatureAlgorithmsExtensionAllowed(this.clientVersion)) {
                throw new TlsFatalAlert(47);
            }
            this.namedCurves = TlsECCUtils.getSupportedEllipticCurvesExtension(hashtable);
            this.clientECPointFormats = TlsECCUtils.getSupportedPointFormatsExtension(hashtable);
        }
    }

    public ProtocolVersion getServerVersion() throws IOException {
        if (this.getMinimumVersion().isEqualOrEarlierVersionOf(this.clientVersion)) {
            ProtocolVersion protocolVersion = this.getMaximumVersion();
            if (this.clientVersion.isEqualOrEarlierVersionOf(protocolVersion)) {
                this.serverVersion = this.clientVersion;
                return this.serverVersion;
            }
            if (this.clientVersion.isLaterVersionOf(protocolVersion)) {
                this.serverVersion = protocolVersion;
                return this.serverVersion;
            }
        }
        throw new TlsFatalAlert(70);
    }

    public int getSelectedCipherSuite() throws IOException {
        Vector vector = TlsUtils.getUsableSignatureAlgorithms(this.supportedSignatureAlgorithms);
        boolean bl = this.supportsClientECCCapabilities(this.namedCurves, this.clientECPointFormats);
        int[] nArray = this.getCipherSuites();
        for (int i = 0; i < nArray.length; ++i) {
            int n = nArray[i];
            if (!Arrays.contains(this.offeredCipherSuites, n) || !bl && TlsECCUtils.isECCCipherSuite(n) || !TlsUtils.isValidCipherSuiteForVersion(n, this.serverVersion) || !TlsUtils.isValidCipherSuiteForSignatureAlgorithms(n, vector)) continue;
            this.selectedCipherSuite = n;
            return this.selectedCipherSuite;
        }
        throw new TlsFatalAlert(40);
    }

    public short getSelectedCompressionMethod() throws IOException {
        short[] sArray = this.getCompressionMethods();
        for (int i = 0; i < sArray.length; ++i) {
            if (!Arrays.contains(this.offeredCompressionMethods, sArray[i])) continue;
            this.selectedCompressionMethod = sArray[i];
            return this.selectedCompressionMethod;
        }
        throw new TlsFatalAlert(40);
    }

    public Hashtable getServerExtensions() throws IOException {
        if (this.encryptThenMACOffered && this.allowEncryptThenMAC() && TlsUtils.isBlockCipherSuite(this.selectedCipherSuite)) {
            TlsExtensionsUtils.addEncryptThenMACExtension(this.checkServerExtensions());
        }
        if (this.maxFragmentLengthOffered >= 0 && MaxFragmentLength.isValid(this.maxFragmentLengthOffered)) {
            TlsExtensionsUtils.addMaxFragmentLengthExtension(this.checkServerExtensions(), this.maxFragmentLengthOffered);
        }
        if (this.truncatedHMacOffered && this.allowTruncatedHMac()) {
            TlsExtensionsUtils.addTruncatedHMacExtension(this.checkServerExtensions());
        }
        if (this.clientECPointFormats != null && TlsECCUtils.isECCCipherSuite(this.selectedCipherSuite)) {
            this.serverECPointFormats = new short[]{0, 1, 2};
            TlsECCUtils.addSupportedPointFormatsExtension(this.checkServerExtensions(), this.serverECPointFormats);
        }
        return this.serverExtensions;
    }

    public Vector getServerSupplementalData() throws IOException {
        return null;
    }

    public CertificateStatus getCertificateStatus() throws IOException {
        return null;
    }

    public CertificateRequest getCertificateRequest() throws IOException {
        return null;
    }

    public void processClientSupplementalData(Vector vector) throws IOException {
        if (vector != null) {
            throw new TlsFatalAlert(10);
        }
    }

    public void notifyClientCertificate(Certificate certificate) throws IOException {
        throw new TlsFatalAlert(80);
    }

    public TlsCompression getCompression() throws IOException {
        switch (this.selectedCompressionMethod) {
            case 0: {
                return new TlsNullCompression();
            }
        }
        throw new TlsFatalAlert(80);
    }

    public TlsCipher getCipher() throws IOException {
        int n = TlsUtils.getEncryptionAlgorithm(this.selectedCipherSuite);
        int n2 = TlsUtils.getMACAlgorithm(this.selectedCipherSuite);
        return this.cipherFactory.createCipher(this.context, n, n2);
    }

    public NewSessionTicket getNewSessionTicket() throws IOException {
        return new NewSessionTicket(0L, TlsUtils.EMPTY_BYTES);
    }
}

