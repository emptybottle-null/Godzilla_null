/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.crypto.tls.DTLSHandshakeRetransmit;
import org.bouncycastle.crypto.tls.DTLSReassembler;
import org.bouncycastle.crypto.tls.DTLSRecordLayer;
import org.bouncycastle.crypto.tls.DeferredHash;
import org.bouncycastle.crypto.tls.TlsContext;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsHandshakeHash;
import org.bouncycastle.crypto.tls.TlsUtils;
import org.bouncycastle.util.Integers;

class DTLSReliableHandshake {
    private static final int MAX_RECEIVE_AHEAD = 16;
    private static final int MESSAGE_HEADER_LENGTH = 12;
    private DTLSRecordLayer recordLayer;
    private TlsHandshakeHash handshakeHash;
    private Hashtable currentInboundFlight = new Hashtable();
    private Hashtable previousInboundFlight = null;
    private Vector outboundFlight = new Vector();
    private boolean sending = true;
    private int message_seq = 0;
    private int next_receive_seq = 0;

    DTLSReliableHandshake(TlsContext tlsContext, DTLSRecordLayer dTLSRecordLayer) {
        this.recordLayer = dTLSRecordLayer;
        this.handshakeHash = new DeferredHash();
        this.handshakeHash.init(tlsContext);
    }

    void notifyHelloComplete() {
        this.handshakeHash = this.handshakeHash.notifyPRFDetermined();
    }

    TlsHandshakeHash getHandshakeHash() {
        return this.handshakeHash;
    }

    TlsHandshakeHash prepareToFinish() {
        TlsHandshakeHash tlsHandshakeHash = this.handshakeHash;
        this.handshakeHash = this.handshakeHash.stopTracking();
        return tlsHandshakeHash;
    }

    void sendMessage(short s, byte[] byArray) throws IOException {
        TlsUtils.checkUint24(byArray.length);
        if (!this.sending) {
            this.checkInboundFlight();
            this.sending = true;
            this.outboundFlight.removeAllElements();
        }
        Message message = new Message(this.message_seq++, s, byArray);
        this.outboundFlight.addElement(message);
        this.writeMessage(message);
        this.updateHandshakeMessagesDigest(message);
    }

    byte[] receiveMessageBody(short s) throws IOException {
        Message message = this.receiveMessage();
        if (message.getType() != s) {
            throw new TlsFatalAlert(10);
        }
        return message.getBody();
    }

    Message receiveMessage() throws IOException {
        if (this.sending) {
            this.sending = false;
            this.prepareInboundFlight(new Hashtable());
        }
        byte[] byArray = null;
        int n = 1000;
        while (true) {
            try {
                while (true) {
                    int n2;
                    Message message;
                    if ((message = this.getPendingMessage()) != null) {
                        return message;
                    }
                    int n3 = this.recordLayer.getReceiveLimit();
                    if (byArray == null || byArray.length < n3) {
                        byArray = new byte[n3];
                    }
                    if ((n2 = this.recordLayer.receive(byArray, 0, n3, n)) >= 0) {
                        boolean bl = this.processRecord(16, this.recordLayer.getReadEpoch(), byArray, 0, n2);
                        if (!bl) continue;
                        n = this.backOff(n);
                        continue;
                    }
                    break;
                }
            } catch (IOException iOException) {
                // empty catch block
            }
            this.resendOutboundFlight();
            n = this.backOff(n);
        }
    }

    void finish() {
        DTLSHandshakeRetransmit dTLSHandshakeRetransmit = null;
        if (!this.sending) {
            this.checkInboundFlight();
        } else {
            this.prepareInboundFlight(null);
            if (this.previousInboundFlight != null) {
                dTLSHandshakeRetransmit = new DTLSHandshakeRetransmit(){

                    public void receivedHandshakeRecord(int n, byte[] byArray, int n2, int n3) throws IOException {
                        DTLSReliableHandshake.this.processRecord(0, n, byArray, n2, n3);
                    }
                };
            }
        }
        this.recordLayer.handshakeSuccessful(dTLSHandshakeRetransmit);
    }

    void resetHandshakeMessagesDigest() {
        this.handshakeHash.reset();
    }

    private int backOff(int n) {
        return Math.min(n * 2, 60000);
    }

    private void checkInboundFlight() {
        Enumeration enumeration = this.currentInboundFlight.keys();
        while (enumeration.hasMoreElements()) {
            Integer n = (Integer)enumeration.nextElement();
            if (n < this.next_receive_seq) continue;
        }
    }

    private Message getPendingMessage() throws IOException {
        byte[] byArray;
        DTLSReassembler dTLSReassembler = (DTLSReassembler)this.currentInboundFlight.get(Integers.valueOf(this.next_receive_seq));
        if (dTLSReassembler != null && (byArray = dTLSReassembler.getBodyIfComplete()) != null) {
            this.previousInboundFlight = null;
            return this.updateHandshakeMessagesDigest(new Message(this.next_receive_seq++, dTLSReassembler.getMsgType(), byArray));
        }
        return null;
    }

    private void prepareInboundFlight(Hashtable hashtable) {
        DTLSReliableHandshake.resetAll(this.currentInboundFlight);
        this.previousInboundFlight = this.currentInboundFlight;
        this.currentInboundFlight = hashtable;
    }

    private boolean processRecord(int n, int n2, byte[] byArray, int n3, int n4) throws IOException {
        int n5;
        int n6;
        boolean bl = false;
        while (n4 >= 12 && n4 >= (n6 = (n5 = TlsUtils.readUint24(byArray, n3 + 9)) + 12)) {
            int n7;
            int n8 = TlsUtils.readUint24(byArray, n3 + 1);
            int n9 = TlsUtils.readUint24(byArray, n3 + 6);
            if (n9 + n5 > n8) break;
            short s = TlsUtils.readUint8(byArray, n3 + 0);
            int n10 = n7 = s == 20 ? 1 : 0;
            if (n2 != n7) break;
            int n11 = TlsUtils.readUint16(byArray, n3 + 4);
            if (n11 < this.next_receive_seq + n) {
                DTLSReassembler dTLSReassembler;
                if (n11 >= this.next_receive_seq) {
                    dTLSReassembler = (DTLSReassembler)this.currentInboundFlight.get(Integers.valueOf(n11));
                    if (dTLSReassembler == null) {
                        dTLSReassembler = new DTLSReassembler(s, n8);
                        this.currentInboundFlight.put(Integers.valueOf(n11), dTLSReassembler);
                    }
                    dTLSReassembler.contributeFragment(s, n8, byArray, n3 + 12, n9, n5);
                } else if (this.previousInboundFlight != null && (dTLSReassembler = (DTLSReassembler)this.previousInboundFlight.get(Integers.valueOf(n11))) != null) {
                    dTLSReassembler.contributeFragment(s, n8, byArray, n3 + 12, n9, n5);
                    bl = true;
                }
            }
            n3 += n6;
            n4 -= n6;
        }
        int n12 = n5 = bl && DTLSReliableHandshake.checkAll(this.previousInboundFlight) ? 1 : 0;
        if (n5 != 0) {
            this.resendOutboundFlight();
            DTLSReliableHandshake.resetAll(this.previousInboundFlight);
        }
        return n5 != 0;
    }

    private void resendOutboundFlight() throws IOException {
        this.recordLayer.resetWriteEpoch();
        for (int i = 0; i < this.outboundFlight.size(); ++i) {
            this.writeMessage((Message)this.outboundFlight.elementAt(i));
        }
    }

    private Message updateHandshakeMessagesDigest(Message message) throws IOException {
        if (message.getType() != 0) {
            byte[] byArray = message.getBody();
            byte[] byArray2 = new byte[12];
            TlsUtils.writeUint8(message.getType(), byArray2, 0);
            TlsUtils.writeUint24(byArray.length, byArray2, 1);
            TlsUtils.writeUint16(message.getSeq(), byArray2, 4);
            TlsUtils.writeUint24(0, byArray2, 6);
            TlsUtils.writeUint24(byArray.length, byArray2, 9);
            this.handshakeHash.update(byArray2, 0, byArray2.length);
            this.handshakeHash.update(byArray, 0, byArray.length);
        }
        return message;
    }

    private void writeMessage(Message message) throws IOException {
        int n;
        int n2 = this.recordLayer.getSendLimit();
        int n3 = n2 - 12;
        if (n3 < 1) {
            throw new TlsFatalAlert(80);
        }
        int n4 = message.getBody().length;
        int n5 = 0;
        do {
            n = Math.min(n4 - n5, n3);
            this.writeHandshakeFragment(message, n5, n);
        } while ((n5 += n) < n4);
    }

    private void writeHandshakeFragment(Message message, int n, int n2) throws IOException {
        RecordLayerBuffer recordLayerBuffer = new RecordLayerBuffer(12 + n2);
        TlsUtils.writeUint8(message.getType(), (OutputStream)recordLayerBuffer);
        TlsUtils.writeUint24(message.getBody().length, recordLayerBuffer);
        TlsUtils.writeUint16(message.getSeq(), recordLayerBuffer);
        TlsUtils.writeUint24(n, recordLayerBuffer);
        TlsUtils.writeUint24(n2, recordLayerBuffer);
        recordLayerBuffer.write(message.getBody(), n, n2);
        recordLayerBuffer.sendToRecordLayer(this.recordLayer);
    }

    private static boolean checkAll(Hashtable hashtable) {
        Enumeration enumeration = hashtable.elements();
        while (enumeration.hasMoreElements()) {
            if (((DTLSReassembler)enumeration.nextElement()).getBodyIfComplete() != null) continue;
            return false;
        }
        return true;
    }

    private static void resetAll(Hashtable hashtable) {
        Enumeration enumeration = hashtable.elements();
        while (enumeration.hasMoreElements()) {
            ((DTLSReassembler)enumeration.nextElement()).reset();
        }
    }

    static class Message {
        private final int message_seq;
        private final short msg_type;
        private final byte[] body;

        private Message(int n, short s, byte[] byArray) {
            this.message_seq = n;
            this.msg_type = s;
            this.body = byArray;
        }

        public int getSeq() {
            return this.message_seq;
        }

        public short getType() {
            return this.msg_type;
        }

        public byte[] getBody() {
            return this.body;
        }
    }

    static class RecordLayerBuffer
    extends ByteArrayOutputStream {
        RecordLayerBuffer(int n) {
            super(n);
        }

        void sendToRecordLayer(DTLSRecordLayer dTLSRecordLayer) throws IOException {
            dTLSRecordLayer.send(this.buf, 0, this.count);
            this.buf = null;
        }
    }
}

