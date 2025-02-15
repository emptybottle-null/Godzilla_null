/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import org.bouncycastle.crypto.tls.ByteQueue;
import org.bouncycastle.crypto.tls.DTLSEpoch;
import org.bouncycastle.crypto.tls.DTLSHandshakeRetransmit;
import org.bouncycastle.crypto.tls.DatagramTransport;
import org.bouncycastle.crypto.tls.ProtocolVersion;
import org.bouncycastle.crypto.tls.TlsCipher;
import org.bouncycastle.crypto.tls.TlsContext;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsNullCipher;
import org.bouncycastle.crypto.tls.TlsPeer;
import org.bouncycastle.crypto.tls.TlsUtils;

class DTLSRecordLayer
implements DatagramTransport {
    private static final int RECORD_HEADER_LENGTH = 13;
    private static final int MAX_FRAGMENT_LENGTH = 16384;
    private static final long TCP_MSL = 120000L;
    private static final long RETRANSMIT_TIMEOUT = 240000L;
    private final DatagramTransport transport;
    private final TlsContext context;
    private final TlsPeer peer;
    private final ByteQueue recordQueue = new ByteQueue();
    private volatile boolean closed = false;
    private volatile boolean failed = false;
    private volatile ProtocolVersion readVersion = null;
    private volatile ProtocolVersion writeVersion = null;
    private volatile boolean inHandshake;
    private volatile int plaintextLimit;
    private DTLSEpoch currentEpoch;
    private DTLSEpoch pendingEpoch;
    private DTLSEpoch readEpoch;
    private DTLSEpoch writeEpoch;
    private DTLSHandshakeRetransmit retransmit = null;
    private DTLSEpoch retransmitEpoch = null;
    private long retransmitExpiry = 0L;

    DTLSRecordLayer(DatagramTransport datagramTransport, TlsContext tlsContext, TlsPeer tlsPeer, short s) {
        this.transport = datagramTransport;
        this.context = tlsContext;
        this.peer = tlsPeer;
        this.inHandshake = true;
        this.currentEpoch = new DTLSEpoch(0, new TlsNullCipher(tlsContext));
        this.pendingEpoch = null;
        this.readEpoch = this.currentEpoch;
        this.writeEpoch = this.currentEpoch;
        this.setPlaintextLimit(16384);
    }

    void setPlaintextLimit(int n) {
        this.plaintextLimit = n;
    }

    int getReadEpoch() {
        return this.readEpoch.getEpoch();
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

    void initPendingEpoch(TlsCipher tlsCipher) {
        if (this.pendingEpoch != null) {
            throw new IllegalStateException();
        }
        this.pendingEpoch = new DTLSEpoch(this.writeEpoch.getEpoch() + 1, tlsCipher);
    }

    void handshakeSuccessful(DTLSHandshakeRetransmit dTLSHandshakeRetransmit) {
        if (this.readEpoch == this.currentEpoch || this.writeEpoch == this.currentEpoch) {
            throw new IllegalStateException();
        }
        if (dTLSHandshakeRetransmit != null) {
            this.retransmit = dTLSHandshakeRetransmit;
            this.retransmitEpoch = this.currentEpoch;
            this.retransmitExpiry = System.currentTimeMillis() + 240000L;
        }
        this.inHandshake = false;
        this.currentEpoch = this.pendingEpoch;
        this.pendingEpoch = null;
    }

    void resetWriteEpoch() {
        this.writeEpoch = this.retransmitEpoch != null ? this.retransmitEpoch : this.currentEpoch;
    }

    public int getReceiveLimit() throws IOException {
        return Math.min(this.plaintextLimit, this.readEpoch.getCipher().getPlaintextLimit(this.transport.getReceiveLimit() - 13));
    }

    public int getSendLimit() throws IOException {
        return Math.min(this.plaintextLimit, this.writeEpoch.getCipher().getPlaintextLimit(this.transport.getSendLimit() - 13));
    }

    /*
     * Exception decompiling
     */
    public int receive(byte[] var1_1, int var2_2, int var3_3, int var4_4) throws IOException {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [12[UNCONDITIONALDOLOOP]], but top level block is 0[TRYBLOCK]
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:538)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
         *     at org.benf.cfr.reader.Main.main(Main.java:54)
         *     at async.DecompilerRunnable.cfrDecompilation(DecompilerRunnable.java:348)
         *     at async.DecompilerRunnable.call(DecompilerRunnable.java:309)
         *     at async.DecompilerRunnable.call(DecompilerRunnable.java:31)
         *     at java.util.concurrent.FutureTask.run(FutureTask.java:266)
         *     at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
         *     at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
         *     at java.lang.Thread.run(Thread.java:750)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    public void send(byte[] byArray, int n, int n2) throws IOException {
        short s = 23;
        if (this.inHandshake || this.writeEpoch == this.retransmitEpoch) {
            s = 22;
            short s2 = TlsUtils.readUint8(byArray, n);
            if (s2 == 20) {
                DTLSEpoch dTLSEpoch = null;
                if (this.inHandshake) {
                    dTLSEpoch = this.pendingEpoch;
                } else if (this.writeEpoch == this.retransmitEpoch) {
                    dTLSEpoch = this.currentEpoch;
                }
                if (dTLSEpoch == null) {
                    throw new IllegalStateException();
                }
                byte[] byArray2 = new byte[]{1};
                this.sendRecord((short)20, byArray2, 0, byArray2.length);
                this.writeEpoch = dTLSEpoch;
            }
        }
        this.sendRecord(s, byArray, n, n2);
    }

    public void close() throws IOException {
        if (!this.closed) {
            if (this.inHandshake) {
                this.warn((short)90, "User canceled handshake");
            }
            this.closeTransport();
        }
    }

    void fail(short s) {
        if (!this.closed) {
            try {
                this.raiseAlert((short)2, s, null, null);
            } catch (Exception exception) {
                // empty catch block
            }
            this.failed = true;
            this.closeTransport();
        }
    }

    void failed() {
        if (!this.closed) {
            this.failed = true;
            this.closeTransport();
        }
    }

    void warn(short s, String string) throws IOException {
        this.raiseAlert((short)1, s, string, null);
    }

    private void closeTransport() {
        if (!this.closed) {
            try {
                if (!this.failed) {
                    this.warn((short)0, null);
                }
                this.transport.close();
            } catch (Exception exception) {
                // empty catch block
            }
            this.closed = true;
        }
    }

    private void raiseAlert(short s, short s2, String string, Throwable throwable) throws IOException {
        this.peer.notifyAlertRaised(s, s2, string, throwable);
        byte[] byArray = new byte[]{(byte)s, (byte)s2};
        this.sendRecord((short)21, byArray, 0, 2);
    }

    private int receiveRecord(byte[] byArray, int n, int n2, int n3) throws IOException {
        int n4;
        int n5;
        if (this.recordQueue.available() > 0) {
            int n6 = 0;
            if (this.recordQueue.available() >= 13) {
                byte[] byArray2 = new byte[2];
                this.recordQueue.read(byArray2, 0, 2, 11);
                n6 = TlsUtils.readUint16(byArray2, 0);
            }
            int n7 = Math.min(this.recordQueue.available(), 13 + n6);
            this.recordQueue.removeData(byArray, n, n7, 0);
            return n7;
        }
        int n8 = this.transport.receive(byArray, n, n2, n3);
        if (n8 >= 13 && n8 > (n5 = 13 + (n4 = TlsUtils.readUint16(byArray, n + 11)))) {
            this.recordQueue.addData(byArray, n + n5, n8 - n5);
            n8 = n5;
        }
        return n8;
    }

    private void sendRecord(short s, byte[] byArray, int n, int n2) throws IOException {
        if (this.writeVersion == null) {
            return;
        }
        if (n2 > this.plaintextLimit) {
            throw new TlsFatalAlert(80);
        }
        if (n2 < 1 && s != 23) {
            throw new TlsFatalAlert(80);
        }
        int n3 = this.writeEpoch.getEpoch();
        long l = this.writeEpoch.allocateSequenceNumber();
        byte[] byArray2 = this.writeEpoch.getCipher().encodePlaintext(DTLSRecordLayer.getMacSequenceNumber(n3, l), s, byArray, n, n2);
        byte[] byArray3 = new byte[byArray2.length + 13];
        TlsUtils.writeUint8(s, byArray3, 0);
        TlsUtils.writeVersion(this.writeVersion, byArray3, 1);
        TlsUtils.writeUint16(n3, byArray3, 3);
        TlsUtils.writeUint48(l, byArray3, 5);
        TlsUtils.writeUint16(byArray2.length, byArray3, 11);
        System.arraycopy(byArray2, 0, byArray3, 13, byArray2.length);
        this.transport.send(byArray3, 0, byArray3.length);
    }

    private static long getMacSequenceNumber(int n, long l) {
        return ((long)n & 0xFFFFFFFFL) << 48 | l;
    }
}

