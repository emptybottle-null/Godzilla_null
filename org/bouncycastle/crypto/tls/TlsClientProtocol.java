/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.crypto.tls.AbstractTlsContext;
import org.bouncycastle.crypto.tls.Certificate;
import org.bouncycastle.crypto.tls.CertificateRequest;
import org.bouncycastle.crypto.tls.CertificateStatus;
import org.bouncycastle.crypto.tls.CipherSuite;
import org.bouncycastle.crypto.tls.DigitallySigned;
import org.bouncycastle.crypto.tls.NewSessionTicket;
import org.bouncycastle.crypto.tls.ProtocolVersion;
import org.bouncycastle.crypto.tls.SecurityParameters;
import org.bouncycastle.crypto.tls.SessionParameters;
import org.bouncycastle.crypto.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.crypto.tls.TlsAuthentication;
import org.bouncycastle.crypto.tls.TlsClient;
import org.bouncycastle.crypto.tls.TlsClientContextImpl;
import org.bouncycastle.crypto.tls.TlsContext;
import org.bouncycastle.crypto.tls.TlsCredentials;
import org.bouncycastle.crypto.tls.TlsExtensionsUtils;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsHandshakeHash;
import org.bouncycastle.crypto.tls.TlsKeyExchange;
import org.bouncycastle.crypto.tls.TlsPeer;
import org.bouncycastle.crypto.tls.TlsProtocol;
import org.bouncycastle.crypto.tls.TlsSession;
import org.bouncycastle.crypto.tls.TlsSessionImpl;
import org.bouncycastle.crypto.tls.TlsSignerCredentials;
import org.bouncycastle.crypto.tls.TlsUtils;
import org.bouncycastle.util.Arrays;

public class TlsClientProtocol
extends TlsProtocol {
    protected TlsClient tlsClient = null;
    TlsClientContextImpl tlsClientContext = null;
    protected byte[] selectedSessionID = null;
    protected TlsKeyExchange keyExchange = null;
    protected TlsAuthentication authentication = null;
    protected CertificateStatus certificateStatus = null;
    protected CertificateRequest certificateRequest = null;

    public TlsClientProtocol(InputStream inputStream, OutputStream outputStream, SecureRandom secureRandom) {
        super(inputStream, outputStream, secureRandom);
    }

    public TlsClientProtocol(SecureRandom secureRandom) {
        super(secureRandom);
    }

    public void connect(TlsClient tlsClient) throws IOException {
        SessionParameters sessionParameters;
        if (tlsClient == null) {
            throw new IllegalArgumentException("'tlsClient' cannot be null");
        }
        if (this.tlsClient != null) {
            throw new IllegalStateException("'connect' can only be called once");
        }
        this.tlsClient = tlsClient;
        this.securityParameters = new SecurityParameters();
        this.securityParameters.entity = 1;
        this.tlsClientContext = new TlsClientContextImpl(this.secureRandom, this.securityParameters);
        this.securityParameters.clientRandom = TlsClientProtocol.createRandomBlock(tlsClient.shouldUseGMTUnixTime(), this.tlsClientContext.getNonceRandomGenerator());
        this.tlsClient.init(this.tlsClientContext);
        this.recordStream.init(this.tlsClientContext);
        TlsSession tlsSession = tlsClient.getSessionToResume();
        if (tlsSession != null && tlsSession.isResumable() && (sessionParameters = tlsSession.exportSessionParameters()) != null) {
            this.tlsSession = tlsSession;
            this.sessionParameters = sessionParameters;
        }
        this.sendClientHelloMessage();
        this.connection_state = 1;
        this.blockForHandshake();
    }

    protected void cleanupHandshake() {
        super.cleanupHandshake();
        this.selectedSessionID = null;
        this.keyExchange = null;
        this.authentication = null;
        this.certificateStatus = null;
        this.certificateRequest = null;
    }

    protected TlsContext getContext() {
        return this.tlsClientContext;
    }

    AbstractTlsContext getContextAdmin() {
        return this.tlsClientContext;
    }

    protected TlsPeer getPeer() {
        return this.tlsClient;
    }

    protected void handleHandshakeMessage(short s, ByteArrayInputStream byteArrayInputStream) throws IOException {
        if (this.resumedSession) {
            if (s != 20 || this.connection_state != 2) {
                throw new TlsFatalAlert(10);
            }
            this.processFinishedMessage(byteArrayInputStream);
            this.connection_state = (short)15;
            this.sendFinishedMessage();
            this.connection_state = (short)13;
            this.completeHandshake();
            return;
        }
        block0 : switch (s) {
            case 11: {
                switch (this.connection_state) {
                    case 2: {
                        this.handleSupplementalData(null);
                    }
                    case 3: {
                        this.peerCertificate = Certificate.parse(byteArrayInputStream);
                        TlsClientProtocol.assertEmpty(byteArrayInputStream);
                        if (this.peerCertificate == null || this.peerCertificate.isEmpty()) {
                            this.allowCertificateStatus = false;
                        }
                        this.keyExchange.processServerCertificate(this.peerCertificate);
                        this.authentication = this.tlsClient.getAuthentication();
                        this.authentication.notifyServerCertificate(this.peerCertificate);
                        break;
                    }
                    default: {
                        throw new TlsFatalAlert(10);
                    }
                }
                this.connection_state = (short)4;
                break;
            }
            case 22: {
                switch (this.connection_state) {
                    case 4: {
                        if (!this.allowCertificateStatus) {
                            throw new TlsFatalAlert(10);
                        }
                        this.certificateStatus = CertificateStatus.parse(byteArrayInputStream);
                        TlsClientProtocol.assertEmpty(byteArrayInputStream);
                        this.connection_state = (short)5;
                        break block0;
                    }
                }
                throw new TlsFatalAlert(10);
            }
            case 20: {
                switch (this.connection_state) {
                    case 13: {
                        if (this.expectSessionTicket) {
                            throw new TlsFatalAlert(10);
                        }
                    }
                    case 14: {
                        this.processFinishedMessage(byteArrayInputStream);
                        this.connection_state = (short)15;
                        this.completeHandshake();
                        break block0;
                    }
                }
                throw new TlsFatalAlert(10);
            }
            case 2: {
                switch (this.connection_state) {
                    case 1: {
                        this.receiveServerHelloMessage(byteArrayInputStream);
                        this.connection_state = (short)2;
                        this.recordStream.notifyHelloComplete();
                        this.applyMaxFragmentLengthExtension();
                        if (this.resumedSession) {
                            this.securityParameters.masterSecret = Arrays.clone(this.sessionParameters.getMasterSecret());
                            this.recordStream.setPendingConnectionState(this.getPeer().getCompression(), this.getPeer().getCipher());
                            this.sendChangeCipherSpecMessage();
                            break block0;
                        }
                        this.invalidateSession();
                        if (this.selectedSessionID.length <= 0) break block0;
                        this.tlsSession = new TlsSessionImpl(this.selectedSessionID, null);
                        break block0;
                    }
                    default: {
                        throw new TlsFatalAlert(10);
                    }
                }
            }
            case 23: {
                switch (this.connection_state) {
                    case 2: {
                        this.handleSupplementalData(TlsClientProtocol.readSupplementalDataMessage(byteArrayInputStream));
                        break block0;
                    }
                }
                throw new TlsFatalAlert(10);
            }
            case 14: {
                switch (this.connection_state) {
                    case 2: {
                        this.handleSupplementalData(null);
                    }
                    case 3: {
                        this.keyExchange.skipServerCredentials();
                        this.authentication = null;
                    }
                    case 4: 
                    case 5: {
                        this.keyExchange.skipServerKeyExchange();
                    }
                    case 6: 
                    case 7: {
                        TlsClientProtocol.assertEmpty(byteArrayInputStream);
                        this.connection_state = (short)8;
                        this.recordStream.getHandshakeHash().sealHashAlgorithms();
                        Vector vector = this.tlsClient.getClientSupplementalData();
                        if (vector != null) {
                            this.sendSupplementalDataMessage(vector);
                        }
                        this.connection_state = (short)9;
                        TlsCredentials tlsCredentials = null;
                        if (this.certificateRequest == null) {
                            this.keyExchange.skipClientCredentials();
                        } else {
                            tlsCredentials = this.authentication.getClientCredentials(this.certificateRequest);
                            if (tlsCredentials == null) {
                                this.keyExchange.skipClientCredentials();
                                this.sendCertificateMessage(Certificate.EMPTY_CHAIN);
                            } else {
                                this.keyExchange.processClientCredentials(tlsCredentials);
                                this.sendCertificateMessage(tlsCredentials.getCertificate());
                            }
                        }
                        this.connection_state = (short)10;
                        this.sendClientKeyExchangeMessage();
                        this.connection_state = (short)11;
                        if (TlsUtils.isSSL(this.getContext())) {
                            TlsClientProtocol.establishMasterSecret(this.getContext(), this.keyExchange);
                        }
                        TlsHandshakeHash tlsHandshakeHash = this.recordStream.prepareToFinish();
                        this.securityParameters.sessionHash = TlsClientProtocol.getCurrentPRFHash(this.getContext(), tlsHandshakeHash, null);
                        if (!TlsUtils.isSSL(this.getContext())) {
                            TlsClientProtocol.establishMasterSecret(this.getContext(), this.keyExchange);
                        }
                        this.recordStream.setPendingConnectionState(this.getPeer().getCompression(), this.getPeer().getCipher());
                        if (tlsCredentials != null && tlsCredentials instanceof TlsSignerCredentials) {
                            TlsSignerCredentials tlsSignerCredentials = (TlsSignerCredentials)tlsCredentials;
                            SignatureAndHashAlgorithm signatureAndHashAlgorithm = TlsUtils.getSignatureAndHashAlgorithm(this.getContext(), tlsSignerCredentials);
                            byte[] byArray = signatureAndHashAlgorithm == null ? this.securityParameters.getSessionHash() : tlsHandshakeHash.getFinalHash(signatureAndHashAlgorithm.getHash());
                            byte[] byArray2 = tlsSignerCredentials.generateCertificateSignature(byArray);
                            DigitallySigned digitallySigned = new DigitallySigned(signatureAndHashAlgorithm, byArray2);
                            this.sendCertificateVerifyMessage(digitallySigned);
                            this.connection_state = (short)12;
                        }
                        this.sendChangeCipherSpecMessage();
                        this.sendFinishedMessage();
                        break;
                    }
                    default: {
                        throw new TlsFatalAlert(10);
                    }
                }
                this.connection_state = (short)13;
                break;
            }
            case 12: {
                switch (this.connection_state) {
                    case 2: {
                        this.handleSupplementalData(null);
                    }
                    case 3: {
                        this.keyExchange.skipServerCredentials();
                        this.authentication = null;
                    }
                    case 4: 
                    case 5: {
                        this.keyExchange.processServerKeyExchange(byteArrayInputStream);
                        TlsClientProtocol.assertEmpty(byteArrayInputStream);
                        break;
                    }
                    default: {
                        throw new TlsFatalAlert(10);
                    }
                }
                this.connection_state = (short)6;
                break;
            }
            case 13: {
                switch (this.connection_state) {
                    case 4: 
                    case 5: {
                        this.keyExchange.skipServerKeyExchange();
                    }
                    case 6: {
                        if (this.authentication == null) {
                            throw new TlsFatalAlert(40);
                        }
                        this.certificateRequest = CertificateRequest.parse(this.getContext(), byteArrayInputStream);
                        TlsClientProtocol.assertEmpty(byteArrayInputStream);
                        this.keyExchange.validateCertificateRequest(this.certificateRequest);
                        TlsUtils.trackHashAlgorithms(this.recordStream.getHandshakeHash(), this.certificateRequest.getSupportedSignatureAlgorithms());
                        break;
                    }
                    default: {
                        throw new TlsFatalAlert(10);
                    }
                }
                this.connection_state = (short)7;
                break;
            }
            case 4: {
                switch (this.connection_state) {
                    case 13: {
                        if (!this.expectSessionTicket) {
                            throw new TlsFatalAlert(10);
                        }
                        this.invalidateSession();
                        this.receiveNewSessionTicketMessage(byteArrayInputStream);
                        break;
                    }
                    default: {
                        throw new TlsFatalAlert(10);
                    }
                }
                this.connection_state = (short)14;
                break;
            }
            case 0: {
                TlsClientProtocol.assertEmpty(byteArrayInputStream);
                if (this.connection_state != 16) break;
                this.refuseRenegotiation();
                break;
            }
            default: {
                throw new TlsFatalAlert(10);
            }
        }
    }

    protected void handleSupplementalData(Vector vector) throws IOException {
        this.tlsClient.processServerSupplementalData(vector);
        this.connection_state = (short)3;
        this.keyExchange = this.tlsClient.getKeyExchange();
        this.keyExchange.init(this.getContext());
    }

    protected void receiveNewSessionTicketMessage(ByteArrayInputStream byteArrayInputStream) throws IOException {
        NewSessionTicket newSessionTicket = NewSessionTicket.parse(byteArrayInputStream);
        TlsClientProtocol.assertEmpty(byteArrayInputStream);
        this.tlsClient.notifyNewSessionTicket(newSessionTicket);
    }

    protected void receiveServerHelloMessage(ByteArrayInputStream byteArrayInputStream) throws IOException {
        Serializable serializable;
        Object object;
        ProtocolVersion protocolVersion = TlsUtils.readVersion(byteArrayInputStream);
        if (protocolVersion.isDTLS()) {
            throw new TlsFatalAlert(47);
        }
        if (!protocolVersion.equals(this.recordStream.getReadVersion())) {
            throw new TlsFatalAlert(47);
        }
        ProtocolVersion protocolVersion2 = this.getContext().getClientVersion();
        if (!protocolVersion.isEqualOrEarlierVersionOf(protocolVersion2)) {
            throw new TlsFatalAlert(47);
        }
        this.recordStream.setWriteVersion(protocolVersion);
        this.getContextAdmin().setServerVersion(protocolVersion);
        this.tlsClient.notifyServerVersion(protocolVersion);
        this.securityParameters.serverRandom = TlsUtils.readFully(32, (InputStream)byteArrayInputStream);
        this.selectedSessionID = TlsUtils.readOpaque8(byteArrayInputStream);
        if (this.selectedSessionID.length > 32) {
            throw new TlsFatalAlert(47);
        }
        this.tlsClient.notifySessionID(this.selectedSessionID);
        this.resumedSession = this.selectedSessionID.length > 0 && this.tlsSession != null && Arrays.areEqual(this.selectedSessionID, this.tlsSession.getSessionID());
        int n = TlsUtils.readUint16(byteArrayInputStream);
        if (!Arrays.contains(this.offeredCipherSuites, n) || n == 0 || CipherSuite.isSCSV(n) || !TlsUtils.isValidCipherSuiteForVersion(n, this.getContext().getServerVersion())) {
            throw new TlsFatalAlert(47);
        }
        this.tlsClient.notifySelectedCipherSuite(n);
        short s = TlsUtils.readUint8(byteArrayInputStream);
        if (!Arrays.contains(this.offeredCompressionMethods, s)) {
            throw new TlsFatalAlert(47);
        }
        this.tlsClient.notifySelectedCompressionMethod(s);
        this.serverExtensions = TlsClientProtocol.readExtensions(byteArrayInputStream);
        if (this.serverExtensions != null) {
            object = this.serverExtensions.keys();
            while (object.hasMoreElements()) {
                serializable = (Integer)object.nextElement();
                if (serializable.equals(EXT_RenegotiationInfo)) continue;
                if (null == TlsUtils.getExtensionData(this.clientExtensions, serializable)) {
                    throw new TlsFatalAlert(110);
                }
                if (!this.resumedSession) continue;
            }
        }
        if ((object = (Object)TlsUtils.getExtensionData(this.serverExtensions, EXT_RenegotiationInfo)) != null) {
            this.secure_renegotiation = true;
            if (!Arrays.constantTimeAreEqual((byte[])object, TlsClientProtocol.createRenegotiationInfo(TlsUtils.EMPTY_BYTES))) {
                throw new TlsFatalAlert(40);
            }
        }
        this.tlsClient.notifySecureRenegotiation(this.secure_renegotiation);
        object = this.clientExtensions;
        serializable = this.serverExtensions;
        if (this.resumedSession) {
            if (n != this.sessionParameters.getCipherSuite() || s != this.sessionParameters.getCompressionAlgorithm()) {
                throw new TlsFatalAlert(47);
            }
            object = null;
            serializable = this.sessionParameters.readServerExtensions();
        }
        this.securityParameters.cipherSuite = n;
        this.securityParameters.compressionAlgorithm = s;
        if (serializable != null) {
            boolean bl = TlsExtensionsUtils.hasEncryptThenMACExtension((Hashtable)serializable);
            if (bl && !TlsUtils.isBlockCipherSuite(n)) {
                throw new TlsFatalAlert(47);
            }
            this.securityParameters.encryptThenMAC = bl;
            this.securityParameters.extendedMasterSecret = TlsExtensionsUtils.hasExtendedMasterSecretExtension((Hashtable)serializable);
            this.securityParameters.maxFragmentLength = this.processMaxFragmentLengthExtension((Hashtable)object, (Hashtable)serializable, (short)47);
            this.securityParameters.truncatedHMac = TlsExtensionsUtils.hasTruncatedHMacExtension((Hashtable)serializable);
            this.allowCertificateStatus = !this.resumedSession && TlsUtils.hasExpectedEmptyExtensionData((Hashtable)serializable, TlsExtensionsUtils.EXT_status_request, (short)47);
            boolean bl2 = this.expectSessionTicket = !this.resumedSession && TlsUtils.hasExpectedEmptyExtensionData((Hashtable)serializable, TlsProtocol.EXT_SessionTicket, (short)47);
        }
        if (object != null) {
            this.tlsClient.processServerExtensions((Hashtable)serializable);
        }
        this.securityParameters.prfAlgorithm = TlsClientProtocol.getPRFAlgorithm(this.getContext(), this.securityParameters.getCipherSuite());
        this.securityParameters.verifyDataLength = 12;
    }

    protected void sendCertificateVerifyMessage(DigitallySigned digitallySigned) throws IOException {
        TlsProtocol.HandshakeMessage handshakeMessage = new TlsProtocol.HandshakeMessage(this, 15);
        digitallySigned.encode(handshakeMessage);
        handshakeMessage.writeToRecordStream();
    }

    protected void sendClientHelloMessage() throws IOException {
        boolean bl;
        this.recordStream.setWriteVersion(this.tlsClient.getClientHelloRecordLayerVersion());
        ProtocolVersion protocolVersion = this.tlsClient.getClientVersion();
        if (protocolVersion.isDTLS()) {
            throw new TlsFatalAlert(80);
        }
        this.getContextAdmin().setClientVersion(protocolVersion);
        byte[] byArray = TlsUtils.EMPTY_BYTES;
        if (this.tlsSession != null && ((byArray = this.tlsSession.getSessionID()) == null || byArray.length > 32)) {
            byArray = TlsUtils.EMPTY_BYTES;
        }
        boolean bl2 = this.tlsClient.isFallback();
        this.offeredCipherSuites = this.tlsClient.getCipherSuites();
        this.offeredCompressionMethods = this.tlsClient.getCompressionMethods();
        if (!(byArray.length <= 0 || this.sessionParameters == null || Arrays.contains(this.offeredCipherSuites, this.sessionParameters.getCipherSuite()) && Arrays.contains(this.offeredCompressionMethods, this.sessionParameters.getCompressionAlgorithm()))) {
            byArray = TlsUtils.EMPTY_BYTES;
        }
        this.clientExtensions = this.tlsClient.getClientExtensions();
        TlsProtocol.HandshakeMessage handshakeMessage = new TlsProtocol.HandshakeMessage(this, 1);
        TlsUtils.writeVersion(protocolVersion, handshakeMessage);
        handshakeMessage.write(this.securityParameters.getClientRandom());
        TlsUtils.writeOpaque8(byArray, handshakeMessage);
        byte[] byArray2 = TlsUtils.getExtensionData(this.clientExtensions, EXT_RenegotiationInfo);
        boolean bl3 = null == byArray2;
        boolean bl4 = bl = !Arrays.contains(this.offeredCipherSuites, 255);
        if (bl3 && bl) {
            this.offeredCipherSuites = Arrays.append(this.offeredCipherSuites, 255);
        }
        if (bl2 && !Arrays.contains(this.offeredCipherSuites, 22016)) {
            this.offeredCipherSuites = Arrays.append(this.offeredCipherSuites, 22016);
        }
        TlsUtils.writeUint16ArrayWithUint16Length(this.offeredCipherSuites, handshakeMessage);
        TlsUtils.writeUint8ArrayWithUint8Length(this.offeredCompressionMethods, handshakeMessage);
        if (this.clientExtensions != null) {
            TlsClientProtocol.writeExtensions(handshakeMessage, this.clientExtensions);
        }
        handshakeMessage.writeToRecordStream();
    }

    protected void sendClientKeyExchangeMessage() throws IOException {
        TlsProtocol.HandshakeMessage handshakeMessage = new TlsProtocol.HandshakeMessage(this, 16);
        this.keyExchange.generateClientKeyExchange(handshakeMessage);
        handshakeMessage.writeToRecordStream();
    }
}

