/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.crypto.tls.DeferredHash;
import org.bouncycastle.crypto.tls.ProtocolVersion;
import org.bouncycastle.crypto.tls.TlsCipher;
import org.bouncycastle.crypto.tls.TlsCompression;
import org.bouncycastle.crypto.tls.TlsContext;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsHandshakeHash;
import org.bouncycastle.crypto.tls.TlsNullCipher;
import org.bouncycastle.crypto.tls.TlsNullCompression;
import org.bouncycastle.crypto.tls.TlsProtocol;
import org.bouncycastle.crypto.tls.TlsUtils;
import org.bouncycastle.util.io.SimpleOutputStream;

class RecordStream {
    private static int DEFAULT_PLAINTEXT_LIMIT = 16384;
    static final int TLS_HEADER_SIZE = 5;
    static final int TLS_HEADER_TYPE_OFFSET = 0;
    static final int TLS_HEADER_VERSION_OFFSET = 1;
    static final int TLS_HEADER_LENGTH_OFFSET = 3;
    private TlsProtocol handler;
    private InputStream input;
    private OutputStream output;
    private TlsCompression pendingCompression = null;
    private TlsCompression readCompression = null;
    private TlsCompression writeCompression = null;
    private TlsCipher pendingCipher = null;
    private TlsCipher readCipher = null;
    private TlsCipher writeCipher = null;
    private SequenceNumber readSeqNo = new SequenceNumber();
    private SequenceNumber writeSeqNo = new SequenceNumber();
    private ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    private TlsHandshakeHash handshakeHash = null;
    private SimpleOutputStream handshakeHashUpdater = new SimpleOutputStream(){

        public void write(byte[] byArray, int n, int n2) throws IOException {
            RecordStream.this.handshakeHash.update(byArray, n, n2);
        }
    };
    private ProtocolVersion readVersion = null;
    private ProtocolVersion writeVersion = null;
    private boolean restrictReadVersion = true;
    private int plaintextLimit;
    private int compressedLimit;
    private int ciphertextLimit;

    RecordStream(TlsProtocol tlsProtocol, InputStream inputStream, OutputStream outputStream) {
        this.handler = tlsProtocol;
        this.input = inputStream;
        this.output = outputStream;
        this.writeCompression = this.readCompression = new TlsNullCompression();
    }

    void init(TlsContext tlsContext) {
        this.writeCipher = this.readCipher = new TlsNullCipher(tlsContext);
        this.handshakeHash = new DeferredHash();
        this.handshakeHash.init(tlsContext);
        this.setPlaintextLimit(DEFAULT_PLAINTEXT_LIMIT);
    }

    int getPlaintextLimit() {
        return this.plaintextLimit;
    }

    void setPlaintextLimit(int n) {
        this.plaintextLimit = n;
        this.compressedLimit = this.plaintextLimit + 1024;
        this.ciphertextLimit = this.compressedLimit + 1024;
    }

    ProtocolVersion getReadVersion() {
        return this.readVersion;
    }

    void setReadVersion(ProtocolVersion protocolVersion) {
        this.readVersion = protocolVersion;
    }

    void setWriteVersion(ProtocolVersion protocolVersion) {
        this.writeVersion = protocolVersion;
    }

    void setRestrictReadVersion(boolean bl) {
        this.restrictReadVersion = bl;
    }

    void setPendingConnectionState(TlsCompression tlsCompression, TlsCipher tlsCipher) {
        this.pendingCompression = tlsCompression;
        this.pendingCipher = tlsCipher;
    }

    void sentWriteCipherSpec() throws IOException {
        if (this.pendingCompression == null || this.pendingCipher == null) {
            throw new TlsFatalAlert(40);
        }
        this.writeCompression = this.pendingCompression;
        this.writeCipher = this.pendingCipher;
        this.writeSeqNo = new SequenceNumber();
    }

    void receivedReadCipherSpec() throws IOException {
        if (this.pendingCompression == null || this.pendingCipher == null) {
            throw new TlsFatalAlert(40);
        }
        this.readCompression = this.pendingCompression;
        this.readCipher = this.pendingCipher;
        this.readSeqNo = new SequenceNumber();
    }

    void finaliseHandshake() throws IOException {
        if (this.readCompression != this.pendingCompression || this.writeCompression != this.pendingCompression || this.readCipher != this.pendingCipher || this.writeCipher != this.pendingCipher) {
            throw new TlsFatalAlert(40);
        }
        this.pendingCompression = null;
        this.pendingCipher = null;
    }

    void checkRecordHeader(byte[] byArray) throws IOException {
        int n;
        short s = TlsUtils.readUint8(byArray, 0);
        RecordStream.checkType(s, (short)10);
        if (!this.restrictReadVersion) {
            n = TlsUtils.readVersionRaw(byArray, 1);
            if ((n & 0xFFFFFF00) != 768) {
                throw new TlsFatalAlert(47);
            }
        } else {
            ProtocolVersion protocolVersion = TlsUtils.readVersion(byArray, 1);
            if (this.readVersion != null && !protocolVersion.equals(this.readVersion)) {
                throw new TlsFatalAlert(47);
            }
        }
        n = TlsUtils.readUint16(byArray, 3);
        RecordStream.checkLength(n, this.ciphertextLimit, (short)22);
    }

    boolean readRecord() throws IOException {
        int n;
        byte[] byArray = TlsUtils.readAllOrNothing(5, this.input);
        if (byArray == null) {
            return false;
        }
        short s = TlsUtils.readUint8(byArray, 0);
        RecordStream.checkType(s, (short)10);
        if (!this.restrictReadVersion) {
            n = TlsUtils.readVersionRaw(byArray, 1);
            if ((n & 0xFFFFFF00) != 768) {
                throw new TlsFatalAlert(47);
            }
        } else {
            ProtocolVersion protocolVersion = TlsUtils.readVersion(byArray, 1);
            if (this.readVersion == null) {
                this.readVersion = protocolVersion;
            } else if (!protocolVersion.equals(this.readVersion)) {
                throw new TlsFatalAlert(47);
            }
        }
        n = TlsUtils.readUint16(byArray, 3);
        RecordStream.checkLength(n, this.ciphertextLimit, (short)22);
        byte[] byArray2 = this.decodeAndVerify(s, this.input, n);
        this.handler.processRecord(s, byArray2, 0, byArray2.length);
        return true;
    }

    byte[] decodeAndVerify(short s, InputStream inputStream, int n) throws IOException {
        byte[] byArray = TlsUtils.readFully(n, inputStream);
        long l = this.readSeqNo.nextValue((short)10);
        byte[] byArray2 = this.readCipher.decodeCiphertext(l, s, byArray, 0, byArray.length);
        RecordStream.checkLength(byArray2.length, this.compressedLimit, (short)22);
        OutputStream outputStream = this.readCompression.decompress(this.buffer);
        if (outputStream != this.buffer) {
            outputStream.write(byArray2, 0, byArray2.length);
            outputStream.flush();
            byArray2 = this.getBufferContents();
        }
        RecordStream.checkLength(byArray2.length, this.plaintextLimit, (short)30);
        if (byArray2.length < 1 && s != 23) {
            throw new TlsFatalAlert(47);
        }
        return byArray2;
    }

    void writeRecord(short s, byte[] byArray, int n, int n2) throws IOException {
        byte[] byArray2;
        byte[] byArray3;
        if (this.writeVersion == null) {
            return;
        }
        RecordStream.checkType(s, (short)80);
        RecordStream.checkLength(n2, this.plaintextLimit, (short)80);
        if (n2 < 1 && s != 23) {
            throw new TlsFatalAlert(80);
        }
        OutputStream outputStream = this.writeCompression.compress(this.buffer);
        long l = this.writeSeqNo.nextValue((short)80);
        if (outputStream == this.buffer) {
            byArray3 = this.writeCipher.encodePlaintext(l, s, byArray, n, n2);
        } else {
            outputStream.write(byArray, n, n2);
            outputStream.flush();
            byArray2 = this.getBufferContents();
            RecordStream.checkLength(byArray2.length, n2 + 1024, (short)80);
            byArray3 = this.writeCipher.encodePlaintext(l, s, byArray2, 0, byArray2.length);
        }
        RecordStream.checkLength(byArray3.length, this.ciphertextLimit, (short)80);
        byArray2 = new byte[byArray3.length + 5];
        TlsUtils.writeUint8(s, byArray2, 0);
        TlsUtils.writeVersion(this.writeVersion, byArray2, 1);
        TlsUtils.writeUint16(byArray3.length, byArray2, 3);
        System.arraycopy(byArray3, 0, byArray2, 5, byArray3.length);
        this.output.write(byArray2);
        this.output.flush();
    }

    void notifyHelloComplete() {
        this.handshakeHash = this.handshakeHash.notifyPRFDetermined();
    }

    TlsHandshakeHash getHandshakeHash() {
        return this.handshakeHash;
    }

    OutputStream getHandshakeHashUpdater() {
        return this.handshakeHashUpdater;
    }

    TlsHandshakeHash prepareToFinish() {
        TlsHandshakeHash tlsHandshakeHash = this.handshakeHash;
        this.handshakeHash = this.handshakeHash.stopTracking();
        return tlsHandshakeHash;
    }

    void safeClose() {
        try {
            this.input.close();
        } catch (IOException iOException) {
            // empty catch block
        }
        try {
            this.output.close();
        } catch (IOException iOException) {
            // empty catch block
        }
    }

    void flush() throws IOException {
        this.output.flush();
    }

    private byte[] getBufferContents() {
        byte[] byArray = this.buffer.toByteArray();
        this.buffer.reset();
        return byArray;
    }

    private static void checkType(short s, short s2) throws IOException {
        switch (s) {
            case 20: 
            case 21: 
            case 22: 
            case 23: {
                break;
            }
            default: {
                throw new TlsFatalAlert(s2);
            }
        }
    }

    private static void checkLength(int n, int n2, short s) throws IOException {
        if (n > n2) {
            throw new TlsFatalAlert(s);
        }
    }

    private static class SequenceNumber {
        private long value = 0L;
        private boolean exhausted = false;

        private SequenceNumber() {
        }

        synchronized long nextValue(short s) throws TlsFatalAlert {
            if (this.exhausted) {
                throw new TlsFatalAlert(s);
            }
            long l = this.value++;
            if (this.value == 0L) {
                this.exhausted = true;
            }
            return l;
        }
    }
}

