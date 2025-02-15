/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.prng.RandomGenerator;
import org.bouncycastle.crypto.tls.AbstractTlsContext;
import org.bouncycastle.crypto.tls.ByteQueue;
import org.bouncycastle.crypto.tls.ByteQueueInputStream;
import org.bouncycastle.crypto.tls.ByteQueueOutputStream;
import org.bouncycastle.crypto.tls.Certificate;
import org.bouncycastle.crypto.tls.MaxFragmentLength;
import org.bouncycastle.crypto.tls.ProtocolVersion;
import org.bouncycastle.crypto.tls.RecordStream;
import org.bouncycastle.crypto.tls.SecurityParameters;
import org.bouncycastle.crypto.tls.SessionParameters;
import org.bouncycastle.crypto.tls.SupplementalDataEntry;
import org.bouncycastle.crypto.tls.TlsContext;
import org.bouncycastle.crypto.tls.TlsExtensionsUtils;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsFatalAlertReceived;
import org.bouncycastle.crypto.tls.TlsHandshakeHash;
import org.bouncycastle.crypto.tls.TlsInputStream;
import org.bouncycastle.crypto.tls.TlsKeyExchange;
import org.bouncycastle.crypto.tls.TlsNoCloseNotifyException;
import org.bouncycastle.crypto.tls.TlsOutputStream;
import org.bouncycastle.crypto.tls.TlsPeer;
import org.bouncycastle.crypto.tls.TlsSession;
import org.bouncycastle.crypto.tls.TlsSessionImpl;
import org.bouncycastle.crypto.tls.TlsUtils;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;

public abstract class TlsProtocol {
    protected static final Integer EXT_RenegotiationInfo = Integers.valueOf(65281);
    protected static final Integer EXT_SessionTicket = Integers.valueOf(35);
    protected static final short CS_START = 0;
    protected static final short CS_CLIENT_HELLO = 1;
    protected static final short CS_SERVER_HELLO = 2;
    protected static final short CS_SERVER_SUPPLEMENTAL_DATA = 3;
    protected static final short CS_SERVER_CERTIFICATE = 4;
    protected static final short CS_CERTIFICATE_STATUS = 5;
    protected static final short CS_SERVER_KEY_EXCHANGE = 6;
    protected static final short CS_CERTIFICATE_REQUEST = 7;
    protected static final short CS_SERVER_HELLO_DONE = 8;
    protected static final short CS_CLIENT_SUPPLEMENTAL_DATA = 9;
    protected static final short CS_CLIENT_CERTIFICATE = 10;
    protected static final short CS_CLIENT_KEY_EXCHANGE = 11;
    protected static final short CS_CERTIFICATE_VERIFY = 12;
    protected static final short CS_CLIENT_FINISHED = 13;
    protected static final short CS_SERVER_SESSION_TICKET = 14;
    protected static final short CS_SERVER_FINISHED = 15;
    protected static final short CS_END = 16;
    protected static final short ADS_MODE_1_Nsub1 = 0;
    protected static final short ADS_MODE_0_N = 1;
    protected static final short ADS_MODE_0_N_FIRSTONLY = 2;
    private ByteQueue applicationDataQueue = new ByteQueue(0);
    private ByteQueue alertQueue = new ByteQueue(2);
    private ByteQueue handshakeQueue = new ByteQueue(0);
    RecordStream recordStream;
    protected SecureRandom secureRandom;
    private TlsInputStream tlsInputStream = null;
    private TlsOutputStream tlsOutputStream = null;
    private volatile boolean closed = false;
    private volatile boolean failedWithError = false;
    private volatile boolean appDataReady = false;
    private volatile boolean appDataSplitEnabled = true;
    private volatile int appDataSplitMode = 0;
    private byte[] expected_verify_data = null;
    protected TlsSession tlsSession = null;
    protected SessionParameters sessionParameters = null;
    protected SecurityParameters securityParameters = null;
    protected Certificate peerCertificate = null;
    protected int[] offeredCipherSuites = null;
    protected short[] offeredCompressionMethods = null;
    protected Hashtable clientExtensions = null;
    protected Hashtable serverExtensions = null;
    protected short connection_state = 0;
    protected boolean resumedSession = false;
    protected boolean receivedChangeCipherSpec = false;
    protected boolean secure_renegotiation = false;
    protected boolean allowCertificateStatus = false;
    protected boolean expectSessionTicket = false;
    protected boolean blocking;
    protected ByteQueueInputStream inputBuffers;
    protected ByteQueueOutputStream outputBuffer;

    public TlsProtocol(InputStream inputStream, OutputStream outputStream, SecureRandom secureRandom) {
        this.blocking = true;
        this.recordStream = new RecordStream(this, inputStream, outputStream);
        this.secureRandom = secureRandom;
    }

    public TlsProtocol(SecureRandom secureRandom) {
        this.blocking = false;
        this.inputBuffers = new ByteQueueInputStream();
        this.outputBuffer = new ByteQueueOutputStream();
        this.recordStream = new RecordStream(this, this.inputBuffers, this.outputBuffer);
        this.secureRandom = secureRandom;
    }

    protected abstract TlsContext getContext();

    abstract AbstractTlsContext getContextAdmin();

    protected abstract TlsPeer getPeer();

    protected void handleAlertMessage(short s, short s2) throws IOException {
        this.getPeer().notifyAlertReceived(s, s2);
        if (s != 1) {
            this.handleFailure();
            throw new TlsFatalAlertReceived(s2);
        }
        this.handleAlertWarningMessage(s2);
    }

    protected void handleAlertWarningMessage(short s) throws IOException {
        if (s == 0) {
            if (!this.appDataReady) {
                throw new TlsFatalAlert(40);
            }
            this.handleClose(false);
        }
    }

    protected void handleChangeCipherSpecMessage() throws IOException {
    }

    protected void handleClose(boolean bl) throws IOException {
        if (!this.closed) {
            this.closed = true;
            if (bl && !this.appDataReady) {
                this.raiseAlertWarning((short)90, "User canceled handshake");
            }
            this.raiseAlertWarning((short)0, "Connection closed");
            this.recordStream.safeClose();
            if (!this.appDataReady) {
                this.cleanupHandshake();
            }
        }
    }

    protected void handleException(short s, String string, Throwable throwable) throws IOException {
        if (!this.closed) {
            this.raiseAlertFatal(s, string, throwable);
            this.handleFailure();
        }
    }

    protected void handleFailure() {
        this.closed = true;
        this.failedWithError = true;
        this.invalidateSession();
        this.recordStream.safeClose();
        if (!this.appDataReady) {
            this.cleanupHandshake();
        }
    }

    protected abstract void handleHandshakeMessage(short var1, ByteArrayInputStream var2) throws IOException;

    protected void applyMaxFragmentLengthExtension() throws IOException {
        if (this.securityParameters.maxFragmentLength >= 0) {
            if (!MaxFragmentLength.isValid(this.securityParameters.maxFragmentLength)) {
                throw new TlsFatalAlert(80);
            }
            int n = 1 << 8 + this.securityParameters.maxFragmentLength;
            this.recordStream.setPlaintextLimit(n);
        }
    }

    protected void checkReceivedChangeCipherSpec(boolean bl) throws IOException {
        if (bl != this.receivedChangeCipherSpec) {
            throw new TlsFatalAlert(10);
        }
    }

    protected void cleanupHandshake() {
        if (this.expected_verify_data != null) {
            Arrays.fill(this.expected_verify_data, (byte)0);
            this.expected_verify_data = null;
        }
        this.securityParameters.clear();
        this.peerCertificate = null;
        this.offeredCipherSuites = null;
        this.offeredCompressionMethods = null;
        this.clientExtensions = null;
        this.serverExtensions = null;
        this.resumedSession = false;
        this.receivedChangeCipherSpec = false;
        this.secure_renegotiation = false;
        this.allowCertificateStatus = false;
        this.expectSessionTicket = false;
    }

    protected void blockForHandshake() throws IOException {
        if (this.blocking) {
            while (this.connection_state != 16) {
                if (this.closed) {
                    throw new TlsFatalAlert(80);
                }
                this.safeReadRecord();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void completeHandshake() throws IOException {
        try {
            this.connection_state = (short)16;
            this.alertQueue.shrink();
            this.handshakeQueue.shrink();
            this.recordStream.finaliseHandshake();
            boolean bl = this.appDataSplitEnabled = !TlsUtils.isTLSv11(this.getContext());
            if (!this.appDataReady) {
                this.appDataReady = true;
                if (this.blocking) {
                    this.tlsInputStream = new TlsInputStream(this);
                    this.tlsOutputStream = new TlsOutputStream(this);
                }
            }
            if (this.tlsSession != null) {
                if (this.sessionParameters == null) {
                    this.sessionParameters = new SessionParameters.Builder().setCipherSuite(this.securityParameters.getCipherSuite()).setCompressionAlgorithm(this.securityParameters.getCompressionAlgorithm()).setMasterSecret(this.securityParameters.getMasterSecret()).setPeerCertificate(this.peerCertificate).setPSKIdentity(this.securityParameters.getPSKIdentity()).setSRPIdentity(this.securityParameters.getSRPIdentity()).setServerExtensions(this.serverExtensions).build();
                    this.tlsSession = new TlsSessionImpl(this.tlsSession.getSessionID(), this.sessionParameters);
                }
                this.getContextAdmin().setResumableSession(this.tlsSession);
            }
            this.getPeer().notifyHandshakeComplete();
        } finally {
            this.cleanupHandshake();
        }
    }

    protected void processRecord(short s, byte[] byArray, int n, int n2) throws IOException {
        switch (s) {
            case 21: {
                this.alertQueue.addData(byArray, n, n2);
                this.processAlertQueue();
                break;
            }
            case 23: {
                if (!this.appDataReady) {
                    throw new TlsFatalAlert(10);
                }
                this.applicationDataQueue.addData(byArray, n, n2);
                this.processApplicationDataQueue();
                break;
            }
            case 20: {
                this.processChangeCipherSpec(byArray, n, n2);
                break;
            }
            case 22: {
                if (this.handshakeQueue.available() > 0) {
                    this.handshakeQueue.addData(byArray, n, n2);
                    this.processHandshakeQueue(this.handshakeQueue);
                    break;
                }
                ByteQueue byteQueue = new ByteQueue(byArray, n, n2);
                this.processHandshakeQueue(byteQueue);
                int n3 = byteQueue.available();
                if (n3 <= 0) break;
                this.handshakeQueue.addData(byArray, n + n2 - n3, n3);
                break;
            }
            default: {
                throw new TlsFatalAlert(80);
            }
        }
    }

    private void processHandshakeQueue(ByteQueue byteQueue) throws IOException {
        while (byteQueue.available() >= 4) {
            Object object;
            byte[] byArray = new byte[4];
            byteQueue.read(byArray, 0, 4, 0);
            short s = TlsUtils.readUint8(byArray, 0);
            int n = TlsUtils.readUint24(byArray, 1);
            int n2 = 4 + n;
            if (byteQueue.available() < n2) break;
            this.checkReceivedChangeCipherSpec(this.connection_state == 16 || s == 20);
            switch (s) {
                case 0: {
                    break;
                }
                case 20: {
                    object = this.getContext();
                    if (this.expected_verify_data == null && object.getSecurityParameters().getMasterSecret() != null) {
                        this.expected_verify_data = this.createVerifyData(!object.isServer());
                    }
                }
                default: {
                    byteQueue.copyTo(this.recordStream.getHandshakeHashUpdater(), n2);
                }
            }
            byteQueue.removeData(4);
            object = byteQueue.readFrom(n);
            this.handleHandshakeMessage(s, (ByteArrayInputStream)object);
        }
    }

    private void processApplicationDataQueue() {
    }

    private void processAlertQueue() throws IOException {
        while (this.alertQueue.available() >= 2) {
            byte[] byArray = this.alertQueue.removeData(2, 0);
            short s = byArray[0];
            short s2 = byArray[1];
            this.handleAlertMessage(s, s2);
        }
    }

    private void processChangeCipherSpec(byte[] byArray, int n, int n2) throws IOException {
        for (int i = 0; i < n2; ++i) {
            short s = TlsUtils.readUint8(byArray, n + i);
            if (s != 1) {
                throw new TlsFatalAlert(50);
            }
            if (this.receivedChangeCipherSpec || this.alertQueue.available() > 0 || this.handshakeQueue.available() > 0) {
                throw new TlsFatalAlert(10);
            }
            this.recordStream.receivedReadCipherSpec();
            this.receivedChangeCipherSpec = true;
            this.handleChangeCipherSpecMessage();
        }
    }

    protected int applicationDataAvailable() {
        return this.applicationDataQueue.available();
    }

    protected int readApplicationData(byte[] byArray, int n, int n2) throws IOException {
        if (n2 < 1) {
            return 0;
        }
        while (this.applicationDataQueue.available() == 0) {
            if (this.closed) {
                if (this.failedWithError) {
                    throw new IOException("Cannot read application data on failed TLS connection");
                }
                if (!this.appDataReady) {
                    throw new IllegalStateException("Cannot read application data until initial handshake completed.");
                }
                return -1;
            }
            this.safeReadRecord();
        }
        n2 = Math.min(n2, this.applicationDataQueue.available());
        this.applicationDataQueue.removeData(byArray, n, n2, 0);
        return n2;
    }

    protected void safeCheckRecordHeader(byte[] byArray) throws IOException {
        try {
            this.recordStream.checkRecordHeader(byArray);
        } catch (TlsFatalAlert tlsFatalAlert) {
            this.handleException(tlsFatalAlert.getAlertDescription(), "Failed to read record", tlsFatalAlert);
            throw tlsFatalAlert;
        } catch (IOException iOException) {
            this.handleException((short)80, "Failed to read record", iOException);
            throw iOException;
        } catch (RuntimeException runtimeException) {
            this.handleException((short)80, "Failed to read record", runtimeException);
            throw new TlsFatalAlert(80, (Throwable)runtimeException);
        }
    }

    protected void safeReadRecord() throws IOException {
        try {
            if (this.recordStream.readRecord()) {
                return;
            }
            if (!this.appDataReady) {
                throw new TlsFatalAlert(40);
            }
        } catch (TlsFatalAlertReceived tlsFatalAlertReceived) {
            throw tlsFatalAlertReceived;
        } catch (TlsFatalAlert tlsFatalAlert) {
            this.handleException(tlsFatalAlert.getAlertDescription(), "Failed to read record", tlsFatalAlert);
            throw tlsFatalAlert;
        } catch (IOException iOException) {
            this.handleException((short)80, "Failed to read record", iOException);
            throw iOException;
        } catch (RuntimeException runtimeException) {
            this.handleException((short)80, "Failed to read record", runtimeException);
            throw new TlsFatalAlert(80, (Throwable)runtimeException);
        }
        this.handleFailure();
        throw new TlsNoCloseNotifyException();
    }

    protected void safeWriteRecord(short s, byte[] byArray, int n, int n2) throws IOException {
        try {
            this.recordStream.writeRecord(s, byArray, n, n2);
        } catch (TlsFatalAlert tlsFatalAlert) {
            this.handleException(tlsFatalAlert.getAlertDescription(), "Failed to write record", tlsFatalAlert);
            throw tlsFatalAlert;
        } catch (IOException iOException) {
            this.handleException((short)80, "Failed to write record", iOException);
            throw iOException;
        } catch (RuntimeException runtimeException) {
            this.handleException((short)80, "Failed to write record", runtimeException);
            throw new TlsFatalAlert(80, (Throwable)runtimeException);
        }
    }

    protected void writeData(byte[] byArray, int n, int n2) throws IOException {
        if (this.closed) {
            throw new IOException("Cannot write application data on closed/failed TLS connection");
        }
        while (n2 > 0) {
            if (this.appDataSplitEnabled) {
                switch (this.appDataSplitMode) {
                    case 2: {
                        this.appDataSplitEnabled = false;
                    }
                    case 1: {
                        this.safeWriteRecord((short)23, TlsUtils.EMPTY_BYTES, 0, 0);
                        break;
                    }
                    default: {
                        this.safeWriteRecord((short)23, byArray, n, 1);
                        ++n;
                        --n2;
                    }
                }
            }
            if (n2 <= 0) continue;
            int n3 = Math.min(n2, this.recordStream.getPlaintextLimit());
            this.safeWriteRecord((short)23, byArray, n, n3);
            n += n3;
            n2 -= n3;
        }
    }

    protected void setAppDataSplitMode(int n) {
        if (n < 0 || n > 2) {
            throw new IllegalArgumentException("Illegal appDataSplitMode mode: " + n);
        }
        this.appDataSplitMode = n;
    }

    protected void writeHandshakeMessage(byte[] byArray, int n, int n2) throws IOException {
        int n3;
        if (n2 < 4) {
            throw new TlsFatalAlert(80);
        }
        short s = TlsUtils.readUint8(byArray, n);
        if (s != 0) {
            this.recordStream.getHandshakeHashUpdater().write(byArray, n, n2);
        }
        int n4 = 0;
        do {
            n3 = Math.min(n2 - n4, this.recordStream.getPlaintextLimit());
            this.safeWriteRecord((short)22, byArray, n + n4, n3);
        } while ((n4 += n3) < n2);
    }

    public OutputStream getOutputStream() {
        if (!this.blocking) {
            throw new IllegalStateException("Cannot use OutputStream in non-blocking mode! Use offerOutput() instead.");
        }
        return this.tlsOutputStream;
    }

    public InputStream getInputStream() {
        if (!this.blocking) {
            throw new IllegalStateException("Cannot use InputStream in non-blocking mode! Use offerInput() instead.");
        }
        return this.tlsInputStream;
    }

    public void closeInput() throws IOException {
        if (this.blocking) {
            throw new IllegalStateException("Cannot use closeInput() in blocking mode!");
        }
        if (this.closed) {
            return;
        }
        if (this.inputBuffers.available() > 0) {
            throw new EOFException();
        }
        if (!this.appDataReady) {
            throw new TlsFatalAlert(40);
        }
        throw new TlsNoCloseNotifyException();
    }

    public void offerInput(byte[] byArray) throws IOException {
        if (this.blocking) {
            throw new IllegalStateException("Cannot use offerInput() in blocking mode! Use getInputStream() instead.");
        }
        if (this.closed) {
            throw new IOException("Connection is closed, cannot accept any more input");
        }
        this.inputBuffers.addBytes(byArray);
        while (this.inputBuffers.available() >= 5) {
            byte[] byArray2 = new byte[5];
            this.inputBuffers.peek(byArray2);
            int n = TlsUtils.readUint16(byArray2, 3) + 5;
            if (this.inputBuffers.available() < n) {
                this.safeCheckRecordHeader(byArray2);
                break;
            }
            this.safeReadRecord();
            if (!this.closed) continue;
            if (this.connection_state == 16) break;
            throw new TlsFatalAlert(80);
        }
    }

    public int getAvailableInputBytes() {
        if (this.blocking) {
            throw new IllegalStateException("Cannot use getAvailableInputBytes() in blocking mode! Use getInputStream().available() instead.");
        }
        return this.applicationDataAvailable();
    }

    public int readInput(byte[] byArray, int n, int n2) {
        if (this.blocking) {
            throw new IllegalStateException("Cannot use readInput() in blocking mode! Use getInputStream() instead.");
        }
        try {
            return this.readApplicationData(byArray, n, Math.min(n2, this.applicationDataAvailable()));
        } catch (IOException iOException) {
            throw new RuntimeException(iOException.toString());
        }
    }

    public void offerOutput(byte[] byArray, int n, int n2) throws IOException {
        if (this.blocking) {
            throw new IllegalStateException("Cannot use offerOutput() in blocking mode! Use getOutputStream() instead.");
        }
        if (!this.appDataReady) {
            throw new IOException("Application data cannot be sent until the handshake is complete!");
        }
        this.writeData(byArray, n, n2);
    }

    public int getAvailableOutputBytes() {
        if (this.blocking) {
            throw new IllegalStateException("Cannot use getAvailableOutputBytes() in blocking mode! Use getOutputStream() instead.");
        }
        return this.outputBuffer.getBuffer().available();
    }

    public int readOutput(byte[] byArray, int n, int n2) {
        if (this.blocking) {
            throw new IllegalStateException("Cannot use readOutput() in blocking mode! Use getOutputStream() instead.");
        }
        int n3 = Math.min(this.getAvailableOutputBytes(), n2);
        this.outputBuffer.getBuffer().removeData(byArray, n, n3, 0);
        return n3;
    }

    protected void invalidateSession() {
        if (this.sessionParameters != null) {
            this.sessionParameters.clear();
            this.sessionParameters = null;
        }
        if (this.tlsSession != null) {
            this.tlsSession.invalidate();
            this.tlsSession = null;
        }
    }

    protected void processFinishedMessage(ByteArrayInputStream byteArrayInputStream) throws IOException {
        if (this.expected_verify_data == null) {
            throw new TlsFatalAlert(80);
        }
        byte[] byArray = TlsUtils.readFully(this.expected_verify_data.length, (InputStream)byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        if (!Arrays.constantTimeAreEqual(this.expected_verify_data, byArray)) {
            throw new TlsFatalAlert(51);
        }
    }

    protected void raiseAlertFatal(short s, String string, Throwable throwable) throws IOException {
        this.getPeer().notifyAlertRaised((short)2, s, string, throwable);
        byte[] byArray = new byte[]{2, (byte)s};
        try {
            this.recordStream.writeRecord((short)21, byArray, 0, 2);
        } catch (Exception exception) {
            // empty catch block
        }
    }

    protected void raiseAlertWarning(short s, String string) throws IOException {
        this.getPeer().notifyAlertRaised((short)1, s, string, null);
        byte[] byArray = new byte[]{1, (byte)s};
        this.safeWriteRecord((short)21, byArray, 0, 2);
    }

    protected void sendCertificateMessage(Certificate certificate) throws IOException {
        ProtocolVersion protocolVersion;
        Object object;
        if (certificate == null) {
            certificate = Certificate.EMPTY_CHAIN;
        }
        if (certificate.isEmpty() && !(object = this.getContext()).isServer() && (protocolVersion = this.getContext().getServerVersion()).isSSL()) {
            String string = protocolVersion.toString() + " client didn't provide credentials";
            this.raiseAlertWarning((short)41, string);
            return;
        }
        object = new HandshakeMessage(11);
        certificate.encode((OutputStream)object);
        ((HandshakeMessage)object).writeToRecordStream();
    }

    protected void sendChangeCipherSpecMessage() throws IOException {
        byte[] byArray = new byte[]{1};
        this.safeWriteRecord((short)20, byArray, 0, byArray.length);
        this.recordStream.sentWriteCipherSpec();
    }

    protected void sendFinishedMessage() throws IOException {
        byte[] byArray = this.createVerifyData(this.getContext().isServer());
        HandshakeMessage handshakeMessage = new HandshakeMessage(20, byArray.length);
        handshakeMessage.write(byArray);
        handshakeMessage.writeToRecordStream();
    }

    protected void sendSupplementalDataMessage(Vector vector) throws IOException {
        HandshakeMessage handshakeMessage = new HandshakeMessage(23);
        TlsProtocol.writeSupplementalData(handshakeMessage, vector);
        handshakeMessage.writeToRecordStream();
    }

    protected byte[] createVerifyData(boolean bl) {
        TlsContext tlsContext = this.getContext();
        String string = bl ? "server finished" : "client finished";
        byte[] byArray = bl ? TlsUtils.SSL_SERVER : TlsUtils.SSL_CLIENT;
        byte[] byArray2 = TlsProtocol.getCurrentPRFHash(tlsContext, this.recordStream.getHandshakeHash(), byArray);
        return TlsUtils.calculateVerifyData(tlsContext, string, byArray2);
    }

    public void close() throws IOException {
        this.handleClose(true);
    }

    protected void flush() throws IOException {
        this.recordStream.flush();
    }

    public boolean isClosed() {
        return this.closed;
    }

    protected short processMaxFragmentLengthExtension(Hashtable hashtable, Hashtable hashtable2, short s) throws IOException {
        short s2 = TlsExtensionsUtils.getMaxFragmentLengthExtension(hashtable2);
        if (s2 >= 0 && (!MaxFragmentLength.isValid(s2) || !this.resumedSession && s2 != TlsExtensionsUtils.getMaxFragmentLengthExtension(hashtable))) {
            throw new TlsFatalAlert(s);
        }
        return s2;
    }

    protected void refuseRenegotiation() throws IOException {
        if (TlsUtils.isSSL(this.getContext())) {
            throw new TlsFatalAlert(40);
        }
        this.raiseAlertWarning((short)100, "Renegotiation not supported");
    }

    protected static void assertEmpty(ByteArrayInputStream byteArrayInputStream) throws IOException {
        if (byteArrayInputStream.available() > 0) {
            throw new TlsFatalAlert(50);
        }
    }

    protected static byte[] createRandomBlock(boolean bl, RandomGenerator randomGenerator) {
        byte[] byArray = new byte[32];
        randomGenerator.nextBytes(byArray);
        if (bl) {
            TlsUtils.writeGMTUnixTime(byArray, 0);
        }
        return byArray;
    }

    protected static byte[] createRenegotiationInfo(byte[] byArray) throws IOException {
        return TlsUtils.encodeOpaque8(byArray);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected static void establishMasterSecret(TlsContext tlsContext, TlsKeyExchange tlsKeyExchange) throws IOException {
        byte[] byArray = tlsKeyExchange.generatePremasterSecret();
        try {
            tlsContext.getSecurityParameters().masterSecret = TlsUtils.calculateMasterSecret(tlsContext, byArray);
        } finally {
            if (byArray != null) {
                Arrays.fill(byArray, (byte)0);
            }
        }
    }

    protected static byte[] getCurrentPRFHash(TlsContext tlsContext, TlsHandshakeHash tlsHandshakeHash, byte[] byArray) {
        Digest digest = tlsHandshakeHash.forkPRFHash();
        if (byArray != null && TlsUtils.isSSL(tlsContext)) {
            digest.update(byArray, 0, byArray.length);
        }
        byte[] byArray2 = new byte[digest.getDigestSize()];
        digest.doFinal(byArray2, 0);
        return byArray2;
    }

    protected static Hashtable readExtensions(ByteArrayInputStream byteArrayInputStream) throws IOException {
        if (byteArrayInputStream.available() < 1) {
            return null;
        }
        byte[] byArray = TlsUtils.readOpaque16(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(byArray);
        Hashtable<Integer, byte[]> hashtable = new Hashtable<Integer, byte[]>();
        while (byteArrayInputStream2.available() > 0) {
            byte[] byArray2;
            Integer n = Integers.valueOf(TlsUtils.readUint16(byteArrayInputStream2));
            if (null == hashtable.put(n, byArray2 = TlsUtils.readOpaque16(byteArrayInputStream2))) continue;
            throw new TlsFatalAlert(47);
        }
        return hashtable;
    }

    protected static Vector readSupplementalDataMessage(ByteArrayInputStream byteArrayInputStream) throws IOException {
        byte[] byArray = TlsUtils.readOpaque24(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(byArray);
        Vector<SupplementalDataEntry> vector = new Vector<SupplementalDataEntry>();
        while (byteArrayInputStream2.available() > 0) {
            int n = TlsUtils.readUint16(byteArrayInputStream2);
            byte[] byArray2 = TlsUtils.readOpaque16(byteArrayInputStream2);
            vector.addElement(new SupplementalDataEntry(n, byArray2));
        }
        return vector;
    }

    protected static void writeExtensions(OutputStream outputStream, Hashtable hashtable) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        TlsProtocol.writeSelectedExtensions(byteArrayOutputStream, hashtable, true);
        TlsProtocol.writeSelectedExtensions(byteArrayOutputStream, hashtable, false);
        byte[] byArray = byteArrayOutputStream.toByteArray();
        TlsUtils.writeOpaque16(byArray, outputStream);
    }

    protected static void writeSelectedExtensions(OutputStream outputStream, Hashtable hashtable, boolean bl) throws IOException {
        Enumeration enumeration = hashtable.keys();
        while (enumeration.hasMoreElements()) {
            Integer n = (Integer)enumeration.nextElement();
            int n2 = n;
            byte[] byArray = (byte[])hashtable.get(n);
            if (bl != (byArray.length == 0)) continue;
            TlsUtils.checkUint16(n2);
            TlsUtils.writeUint16(n2, outputStream);
            TlsUtils.writeOpaque16(byArray, outputStream);
        }
    }

    protected static void writeSupplementalData(OutputStream outputStream, Vector vector) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i = 0; i < vector.size(); ++i) {
            SupplementalDataEntry supplementalDataEntry = (SupplementalDataEntry)vector.elementAt(i);
            int n = supplementalDataEntry.getDataType();
            TlsUtils.checkUint16(n);
            TlsUtils.writeUint16(n, byteArrayOutputStream);
            TlsUtils.writeOpaque16(supplementalDataEntry.getData(), byteArrayOutputStream);
        }
        byte[] byArray = byteArrayOutputStream.toByteArray();
        TlsUtils.writeOpaque24(byArray, outputStream);
    }

    protected static int getPRFAlgorithm(TlsContext tlsContext, int n) throws IOException {
        boolean bl = TlsUtils.isTLSv12(tlsContext);
        switch (n) {
            case 59: 
            case 60: 
            case 61: 
            case 62: 
            case 63: 
            case 64: 
            case 103: 
            case 104: 
            case 105: 
            case 106: 
            case 107: 
            case 108: 
            case 109: 
            case 156: 
            case 158: 
            case 160: 
            case 162: 
            case 164: 
            case 166: 
            case 168: 
            case 170: 
            case 172: 
            case 186: 
            case 187: 
            case 188: 
            case 189: 
            case 190: 
            case 191: 
            case 192: 
            case 193: 
            case 194: 
            case 195: 
            case 196: 
            case 197: 
            case 49187: 
            case 49189: 
            case 49191: 
            case 49193: 
            case 49195: 
            case 49197: 
            case 49199: 
            case 49201: 
            case 49266: 
            case 49268: 
            case 49270: 
            case 49272: 
            case 49274: 
            case 49276: 
            case 49278: 
            case 49280: 
            case 49282: 
            case 49284: 
            case 49286: 
            case 49288: 
            case 49290: 
            case 49292: 
            case 49294: 
            case 49296: 
            case 49298: 
            case 49308: 
            case 49309: 
            case 49310: 
            case 49311: 
            case 49312: 
            case 49313: 
            case 49314: 
            case 49315: 
            case 49316: 
            case 49317: 
            case 49318: 
            case 49319: 
            case 49320: 
            case 49321: 
            case 49322: 
            case 49323: 
            case 49324: 
            case 49325: 
            case 49326: 
            case 49327: 
            case 52392: 
            case 52393: 
            case 52394: 
            case 52395: 
            case 52396: 
            case 52397: 
            case 52398: 
            case 65280: 
            case 65281: 
            case 65282: 
            case 65283: 
            case 65284: 
            case 65285: 
            case 65296: 
            case 65297: 
            case 65298: 
            case 65299: 
            case 65300: 
            case 65301: {
                if (bl) {
                    return 1;
                }
                throw new TlsFatalAlert(47);
            }
            case 157: 
            case 159: 
            case 161: 
            case 163: 
            case 165: 
            case 167: 
            case 169: 
            case 171: 
            case 173: 
            case 49188: 
            case 49190: 
            case 49192: 
            case 49194: 
            case 49196: 
            case 49198: 
            case 49200: 
            case 49202: 
            case 49267: 
            case 49269: 
            case 49271: 
            case 49273: 
            case 49275: 
            case 49277: 
            case 49279: 
            case 49281: 
            case 49283: 
            case 49285: 
            case 49287: 
            case 49289: 
            case 49291: 
            case 49293: 
            case 49295: 
            case 49297: 
            case 49299: {
                if (bl) {
                    return 2;
                }
                throw new TlsFatalAlert(47);
            }
            case 175: 
            case 177: 
            case 179: 
            case 181: 
            case 183: 
            case 185: 
            case 49208: 
            case 49211: 
            case 49301: 
            case 49303: 
            case 49305: 
            case 49307: {
                if (bl) {
                    return 2;
                }
                return 0;
            }
        }
        if (bl) {
            return 1;
        }
        return 0;
    }

    class HandshakeMessage
    extends ByteArrayOutputStream {
        HandshakeMessage(short s) throws IOException {
            this(s, 60);
        }

        HandshakeMessage(short s, int n) throws IOException {
            super(n + 4);
            TlsUtils.writeUint8(s, (OutputStream)this);
            this.count += 3;
        }

        void writeToRecordStream() throws IOException {
            int n = this.count - 4;
            TlsUtils.checkUint24(n);
            TlsUtils.writeUint24(n, this.buf, 1);
            TlsProtocol.this.writeHandshakeMessage(this.buf, 0, this.count);
            this.buf = null;
        }
    }
}

