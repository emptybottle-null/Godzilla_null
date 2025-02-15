/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.crypto.tls.AbstractTlsPeer;
import org.bouncycastle.crypto.tls.DefaultTlsCipherFactory;
import org.bouncycastle.crypto.tls.NewSessionTicket;
import org.bouncycastle.crypto.tls.ProtocolVersion;
import org.bouncycastle.crypto.tls.TlsCipher;
import org.bouncycastle.crypto.tls.TlsCipherFactory;
import org.bouncycastle.crypto.tls.TlsClient;
import org.bouncycastle.crypto.tls.TlsClientContext;
import org.bouncycastle.crypto.tls.TlsCompression;
import org.bouncycastle.crypto.tls.TlsECCUtils;
import org.bouncycastle.crypto.tls.TlsExtensionsUtils;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsNullCompression;
import org.bouncycastle.crypto.tls.TlsSession;
import org.bouncycastle.crypto.tls.TlsUtils;

public abstract class AbstractTlsClient
extends AbstractTlsPeer
implements TlsClient {
    protected TlsCipherFactory cipherFactory;
    protected TlsClientContext context;
    protected Vector supportedSignatureAlgorithms;
    protected int[] namedCurves;
    protected short[] clientECPointFormats;
    protected short[] serverECPointFormats;
    protected int selectedCipherSuite;
    protected short selectedCompressionMethod;

    public AbstractTlsClient() {
        this(new DefaultTlsCipherFactory());
    }

    public AbstractTlsClient(TlsCipherFactory tlsCipherFactory) {
        this.cipherFactory = tlsCipherFactory;
    }

    protected boolean allowUnexpectedServerExtension(Integer n, byte[] byArray) throws IOException {
        switch (n) {
            case 10: {
                TlsECCUtils.readSupportedEllipticCurvesExtension(byArray);
                return true;
            }
        }
        return false;
    }

    protected void checkForUnexpectedServerExtension(Hashtable hashtable, Integer n) throws IOException {
        byte[] byArray = TlsUtils.getExtensionData(hashtable, n);
        if (byArray != null && !this.allowUnexpectedServerExtension(n, byArray)) {
            throw new TlsFatalAlert(47);
        }
    }

    public void init(TlsClientContext tlsClientContext) {
        this.context = tlsClientContext;
    }

    public TlsSession getSessionToResume() {
        return null;
    }

    public ProtocolVersion getClientHelloRecordLayerVersion() {
        return this.getClientVersion();
    }

    public ProtocolVersion getClientVersion() {
        return ProtocolVersion.TLSv12;
    }

    public boolean isFallback() {
        return false;
    }

    public Hashtable getClientExtensions() throws IOException {
        Hashtable hashtable = null;
        ProtocolVersion protocolVersion = this.context.getClientVersion();
        if (TlsUtils.isSignatureAlgorithmsExtensionAllowed(protocolVersion)) {
            this.supportedSignatureAlgorithms = TlsUtils.getDefaultSupportedSignatureAlgorithms();
            hashtable = TlsExtensionsUtils.ensureExtensionsInitialised(hashtable);
            TlsUtils.addSignatureAlgorithmsExtension(hashtable, this.supportedSignatureAlgorithms);
        }
        if (TlsECCUtils.containsECCCipherSuites(this.getCipherSuites())) {
            this.namedCurves = new int[]{23, 24};
            this.clientECPointFormats = new short[]{0, 1, 2};
            hashtable = TlsExtensionsUtils.ensureExtensionsInitialised(hashtable);
            TlsECCUtils.addSupportedEllipticCurvesExtension(hashtable, this.namedCurves);
            TlsECCUtils.addSupportedPointFormatsExtension(hashtable, this.clientECPointFormats);
        }
        return hashtable;
    }

    public ProtocolVersion getMinimumVersion() {
        return ProtocolVersion.TLSv10;
    }

    public void notifyServerVersion(ProtocolVersion protocolVersion) throws IOException {
        if (!this.getMinimumVersion().isEqualOrEarlierVersionOf(protocolVersion)) {
            throw new TlsFatalAlert(70);
        }
    }

    public short[] getCompressionMethods() {
        return new short[]{0};
    }

    public void notifySessionID(byte[] byArray) {
    }

    public void notifySelectedCipherSuite(int n) {
        this.selectedCipherSuite = n;
    }

    public void notifySelectedCompressionMethod(short s) {
        this.selectedCompressionMethod = s;
    }

    public void processServerExtensions(Hashtable hashtable) throws IOException {
        if (hashtable != null) {
            this.checkForUnexpectedServerExtension(hashtable, TlsUtils.EXT_signature_algorithms);
            this.checkForUnexpectedServerExtension(hashtable, TlsECCUtils.EXT_elliptic_curves);
            if (TlsECCUtils.isECCCipherSuite(this.selectedCipherSuite)) {
                this.serverECPointFormats = TlsECCUtils.getSupportedPointFormatsExtension(hashtable);
            } else {
                this.checkForUnexpectedServerExtension(hashtable, TlsECCUtils.EXT_ec_point_formats);
            }
            this.checkForUnexpectedServerExtension(hashtable, TlsExtensionsUtils.EXT_padding);
        }
    }

    public void processServerSupplementalData(Vector vector) throws IOException {
        if (vector != null) {
            throw new TlsFatalAlert(10);
        }
    }

    public Vector getClientSupplementalData() throws IOException {
        return null;
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

    public void notifyNewSessionTicket(NewSessionTicket newSessionTicket) throws IOException {
    }
}

