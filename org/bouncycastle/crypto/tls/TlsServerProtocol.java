/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.tls.AbstractTlsContext;
import org.bouncycastle.crypto.tls.Certificate;
import org.bouncycastle.crypto.tls.CertificateRequest;
import org.bouncycastle.crypto.tls.CertificateStatus;
import org.bouncycastle.crypto.tls.CipherSuite;
import org.bouncycastle.crypto.tls.DigitallySigned;
import org.bouncycastle.crypto.tls.NewSessionTicket;
import org.bouncycastle.crypto.tls.ProtocolVersion;
import org.bouncycastle.crypto.tls.SecurityParameters;
import org.bouncycastle.crypto.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.crypto.tls.TlsContext;
import org.bouncycastle.crypto.tls.TlsCredentials;
import org.bouncycastle.crypto.tls.TlsExtensionsUtils;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsHandshakeHash;
import org.bouncycastle.crypto.tls.TlsKeyExchange;
import org.bouncycastle.crypto.tls.TlsPeer;
import org.bouncycastle.crypto.tls.TlsProtocol;
import org.bouncycastle.crypto.tls.TlsServer;
import org.bouncycastle.crypto.tls.TlsServerContextImpl;
import org.bouncycastle.crypto.tls.TlsSigner;
import org.bouncycastle.crypto.tls.TlsUtils;
import org.bouncycastle.crypto.util.PublicKeyFactory;
import org.bouncycastle.util.Arrays;

public class TlsServerProtocol
extends TlsProtocol {
    protected TlsServer tlsServer = null;
    TlsServerContextImpl tlsServerContext = null;
    protected TlsKeyExchange keyExchange = null;
    protected TlsCredentials serverCredentials = null;
    protected CertificateRequest certificateRequest = null;
    protected short clientCertificateType = (short)-1;
    protected TlsHandshakeHash prepareFinishHash = null;

    public TlsServerProtocol(InputStream inputStream, OutputStream outputStream, SecureRandom secureRandom) {
        super(inputStream, outputStream, secureRandom);
    }

    public TlsServerProtocol(SecureRandom secureRandom) {
        super(secureRandom);
    }

    public void accept(TlsServer tlsServer) throws IOException {
        if (tlsServer == null) {
            throw new IllegalArgumentException("'tlsServer' cannot be null");
        }
        if (this.tlsServer != null) {
            throw new IllegalStateException("'accept' can only be called once");
        }
        this.tlsServer = tlsServer;
        this.securityParameters = new SecurityParameters();
        this.securityParameters.entity = 0;
        this.tlsServerContext = new TlsServerContextImpl(this.secureRandom, this.securityParameters);
        this.securityParameters.serverRandom = TlsServerProtocol.createRandomBlock(tlsServer.shouldUseGMTUnixTime(), this.tlsServerContext.getNonceRandomGenerator());
        this.tlsServer.init(this.tlsServerContext);
        this.recordStream.init(this.tlsServerContext);
        this.recordStream.setRestrictReadVersion(false);
        this.blockForHandshake();
    }

    protected void cleanupHandshake() {
        super.cleanupHandshake();
        this.keyExchange = null;
        this.serverCredentials = null;
        this.certificateRequest = null;
        this.prepareFinishHash = null;
    }

    protected TlsContext getContext() {
        return this.tlsServerContext;
    }

    AbstractTlsContext getContextAdmin() {
        return this.tlsServerContext;
    }

    protected TlsPeer getPeer() {
        return this.tlsServer;
    }

    /*
     * Unable to fully structure code
     */
    protected void handleHandshakeMessage(short var1_1, ByteArrayInputStream var2_2) throws IOException {
        block0 : switch (var1_1) {
            case 1: {
                switch (this.connection_state) {
                    case 0: {
                        this.receiveClientHelloMessage(var2_2);
                        this.connection_state = 1;
                        this.sendServerHelloMessage();
                        this.connection_state = (short)2;
                        this.recordStream.notifyHelloComplete();
                        var3_3 = this.tlsServer.getServerSupplementalData();
                        if (var3_3 != null) {
                            this.sendSupplementalDataMessage(var3_3);
                        }
                        this.connection_state = (short)3;
                        this.keyExchange = this.tlsServer.getKeyExchange();
                        this.keyExchange.init(this.getContext());
                        this.serverCredentials = this.tlsServer.getCredentials();
                        var4_4 = null;
                        if (this.serverCredentials == null) {
                            this.keyExchange.skipServerCredentials();
                        } else {
                            this.keyExchange.processServerCredentials(this.serverCredentials);
                            var4_4 = this.serverCredentials.getCertificate();
                            this.sendCertificateMessage(var4_4);
                        }
                        this.connection_state = (short)4;
                        if (var4_4 == null || var4_4.isEmpty()) {
                            this.allowCertificateStatus = false;
                        }
                        if (this.allowCertificateStatus && (var5_5 = this.tlsServer.getCertificateStatus()) != null) {
                            this.sendCertificateStatusMessage((CertificateStatus)var5_5);
                        }
                        this.connection_state = (short)5;
                        var5_5 = this.keyExchange.generateServerKeyExchange();
                        if (var5_5 != null) {
                            this.sendServerKeyExchangeMessage((byte[])var5_5);
                        }
                        this.connection_state = (short)6;
                        if (this.serverCredentials != null) {
                            this.certificateRequest = this.tlsServer.getCertificateRequest();
                            if (this.certificateRequest != null) {
                                if (TlsUtils.isTLSv12(this.getContext()) != (this.certificateRequest.getSupportedSignatureAlgorithms() != null)) {
                                    throw new TlsFatalAlert(80);
                                }
                                this.keyExchange.validateCertificateRequest(this.certificateRequest);
                                this.sendCertificateRequestMessage(this.certificateRequest);
                                TlsUtils.trackHashAlgorithms(this.recordStream.getHandshakeHash(), this.certificateRequest.getSupportedSignatureAlgorithms());
                            }
                        }
                        this.connection_state = (short)7;
                        this.sendServerHelloDoneMessage();
                        this.connection_state = (short)8;
                        this.recordStream.getHandshakeHash().sealHashAlgorithms();
                        break block0;
                    }
                    case 16: {
                        this.refuseRenegotiation();
                        break block0;
                    }
                }
                throw new TlsFatalAlert(10);
            }
            case 23: {
                switch (this.connection_state) {
                    case 8: {
                        this.tlsServer.processClientSupplementalData(TlsServerProtocol.readSupplementalDataMessage(var2_2));
                        this.connection_state = (short)9;
                        break block0;
                    }
                }
                throw new TlsFatalAlert(10);
            }
            case 11: {
                switch (this.connection_state) {
                    case 8: {
                        this.tlsServer.processClientSupplementalData(null);
                    }
                    case 9: {
                        if (this.certificateRequest == null) {
                            throw new TlsFatalAlert(10);
                        }
                        this.receiveCertificateMessage(var2_2);
                        this.connection_state = (short)10;
                        break block0;
                    }
                }
                throw new TlsFatalAlert(10);
            }
            case 16: {
                switch (this.connection_state) {
                    case 8: {
                        this.tlsServer.processClientSupplementalData(null);
                    }
                    case 9: {
                        if (this.certificateRequest != null) ** GOTO lbl77
                        this.keyExchange.skipClientCredentials();
                        ** GOTO lbl84
lbl77:
                        // 1 sources

                        if (TlsUtils.isTLSv12(this.getContext())) {
                            throw new TlsFatalAlert(10);
                        }
                        if (TlsUtils.isSSL(this.getContext())) {
                            if (this.peerCertificate == null) {
                                throw new TlsFatalAlert(10);
                            }
                        } else {
                            this.notifyClientCertificate(Certificate.EMPTY_CHAIN);
                        }
                    }
lbl84:
                    // 4 sources

                    case 10: {
                        this.receiveClientKeyExchangeMessage(var2_2);
                        this.connection_state = (short)11;
                        break block0;
                    }
                }
                throw new TlsFatalAlert(10);
            }
            case 15: {
                switch (this.connection_state) {
                    case 11: {
                        if (!this.expectCertificateVerifyMessage()) {
                            throw new TlsFatalAlert(10);
                        }
                        this.receiveCertificateVerifyMessage(var2_2);
                        this.connection_state = (short)12;
                        break block0;
                    }
                }
                throw new TlsFatalAlert(10);
            }
            case 20: {
                switch (this.connection_state) {
                    case 11: {
                        if (this.expectCertificateVerifyMessage()) {
                            throw new TlsFatalAlert(10);
                        }
                    }
                    case 12: {
                        this.processFinishedMessage(var2_2);
                        this.connection_state = (short)13;
                        if (this.expectSessionTicket) {
                            this.sendNewSessionTicketMessage(this.tlsServer.getNewSessionTicket());
                            this.sendChangeCipherSpecMessage();
                        }
                        this.connection_state = (short)14;
                        this.sendFinishedMessage();
                        this.connection_state = (short)15;
                        this.completeHandshake();
                        break block0;
                    }
                }
                throw new TlsFatalAlert(10);
            }
            default: {
                throw new TlsFatalAlert(10);
            }
        }
    }

    protected void handleAlertWarningMessage(short s) throws IOException {
        super.handleAlertWarningMessage(s);
        switch (s) {
            case 41: {
                if (TlsUtils.isSSL(this.getContext()) && this.certificateRequest != null) {
                    switch (this.connection_state) {
                        case 8: {
                            this.tlsServer.processClientSupplementalData(null);
                        }
                        case 9: {
                            this.notifyClientCertificate(Certificate.EMPTY_CHAIN);
                            this.connection_state = (short)10;
                            return;
                        }
                    }
                }
                throw new TlsFatalAlert(10);
            }
        }
    }

    protected void notifyClientCertificate(Certificate certificate) throws IOException {
        if (this.certificateRequest == null) {
            throw new IllegalStateException();
        }
        if (this.peerCertificate != null) {
            throw new TlsFatalAlert(10);
        }
        this.peerCertificate = certificate;
        if (certificate.isEmpty()) {
            this.keyExchange.skipClientCredentials();
        } else {
            this.clientCertificateType = TlsUtils.getClientCertificateType(certificate, this.serverCredentials.getCertificate());
            this.keyExchange.processClientCertificate(certificate);
        }
        this.tlsServer.notifyClientCertificate(certificate);
    }

    protected void receiveCertificateMessage(ByteArrayInputStream byteArrayInputStream) throws IOException {
        Certificate certificate = Certificate.parse(byteArrayInputStream);
        TlsServerProtocol.assertEmpty(byteArrayInputStream);
        this.notifyClientCertificate(certificate);
    }

    protected void receiveCertificateVerifyMessage(ByteArrayInputStream byteArrayInputStream) throws IOException {
        if (this.certificateRequest == null) {
            throw new IllegalStateException();
        }
        DigitallySigned digitallySigned = DigitallySigned.parse(this.getContext(), byteArrayInputStream);
        TlsServerProtocol.assertEmpty(byteArrayInputStream);
        try {
            byte[] byArray;
            SignatureAndHashAlgorithm signatureAndHashAlgorithm = digitallySigned.getAlgorithm();
            if (TlsUtils.isTLSv12(this.getContext())) {
                TlsUtils.verifySupportedSignatureAlgorithm(this.certificateRequest.getSupportedSignatureAlgorithms(), signatureAndHashAlgorithm);
                byArray = this.prepareFinishHash.getFinalHash(signatureAndHashAlgorithm.getHash());
            } else {
                byArray = this.securityParameters.getSessionHash();
            }
            org.bouncycastle.asn1.x509.Certificate certificate = this.peerCertificate.getCertificateAt(0);
            SubjectPublicKeyInfo subjectPublicKeyInfo = certificate.getSubjectPublicKeyInfo();
            AsymmetricKeyParameter asymmetricKeyParameter = PublicKeyFactory.createKey(subjectPublicKeyInfo);
            TlsSigner tlsSigner = TlsUtils.createTlsSigner(this.clientCertificateType);
            tlsSigner.init(this.getContext());
            if (!tlsSigner.verifyRawSignature(signatureAndHashAlgorithm, digitallySigned.getSignature(), asymmetricKeyParameter, byArray)) {
                throw new TlsFatalAlert(51);
            }
        } catch (TlsFatalAlert tlsFatalAlert) {
            throw tlsFatalAlert;
        } catch (Exception exception) {
            throw new TlsFatalAlert(51, (Throwable)exception);
        }
    }

    protected void receiveClientHelloMessage(ByteArrayInputStream byteArrayInputStream) throws IOException {
        byte[] byArray;
        ProtocolVersion protocolVersion = TlsUtils.readVersion(byteArrayInputStream);
        this.recordStream.setWriteVersion(protocolVersion);
        if (protocolVersion.isDTLS()) {
            throw new TlsFatalAlert(47);
        }
        byte[] byArray2 = TlsUtils.readFully(32, (InputStream)byteArrayInputStream);
        byte[] byArray3 = TlsUtils.readOpaque8(byteArrayInputStream);
        if (byArray3.length > 32) {
            throw new TlsFatalAlert(47);
        }
        int n = TlsUtils.readUint16(byteArrayInputStream);
        if (n < 2 || (n & 1) != 0) {
            throw new TlsFatalAlert(50);
        }
        this.offeredCipherSuites = TlsUtils.readUint16Array(n / 2, byteArrayInputStream);
        short s = TlsUtils.readUint8(byteArrayInputStream);
        if (s < 1) {
            throw new TlsFatalAlert(47);
        }
        this.offeredCompressionMethods = TlsUtils.readUint8Array(s, byteArrayInputStream);
        this.clientExtensions = TlsServerProtocol.readExtensions(byteArrayInputStream);
        this.securityParameters.extendedMasterSecret = TlsExtensionsUtils.hasExtendedMasterSecretExtension(this.clientExtensions);
        this.getContextAdmin().setClientVersion(protocolVersion);
        this.tlsServer.notifyClientVersion(protocolVersion);
        this.tlsServer.notifyFallback(Arrays.contains(this.offeredCipherSuites, 22016));
        this.securityParameters.clientRandom = byArray2;
        this.tlsServer.notifyOfferedCipherSuites(this.offeredCipherSuites);
        this.tlsServer.notifyOfferedCompressionMethods(this.offeredCompressionMethods);
        if (Arrays.contains(this.offeredCipherSuites, 255)) {
            this.secure_renegotiation = true;
        }
        if ((byArray = TlsUtils.getExtensionData(this.clientExtensions, EXT_RenegotiationInfo)) != null) {
            this.secure_renegotiation = true;
            if (!Arrays.constantTimeAreEqual(byArray, TlsServerProtocol.createRenegotiationInfo(TlsUtils.EMPTY_BYTES))) {
                throw new TlsFatalAlert(40);
            }
        }
        this.tlsServer.notifySecureRenegotiation(this.secure_renegotiation);
        if (this.clientExtensions != null) {
            TlsExtensionsUtils.getPaddingExtension(this.clientExtensions);
            this.tlsServer.processClientExtensions(this.clientExtensions);
        }
    }

    protected void receiveClientKeyExchangeMessage(ByteArrayInputStream byteArrayInputStream) throws IOException {
        this.keyExchange.processClientKeyExchange(byteArrayInputStream);
        TlsServerProtocol.assertEmpty(byteArrayInputStream);
        if (TlsUtils.isSSL(this.getContext())) {
            TlsServerProtocol.establishMasterSecret(this.getContext(), this.keyExchange);
        }
        this.prepareFinishHash = this.recordStream.prepareToFinish();
        this.securityParameters.sessionHash = TlsServerProtocol.getCurrentPRFHash(this.getContext(), this.prepareFinishHash, null);
        if (!TlsUtils.isSSL(this.getContext())) {
            TlsServerProtocol.establishMasterSecret(this.getContext(), this.keyExchange);
        }
        this.recordStream.setPendingConnectionState(this.getPeer().getCompression(), this.getPeer().getCipher());
        if (!this.expectSessionTicket) {
            this.sendChangeCipherSpecMessage();
        }
    }

    protected void sendCertificateRequestMessage(CertificateRequest certificateRequest) throws IOException {
        TlsProtocol.HandshakeMessage handshakeMessage = new TlsProtocol.HandshakeMessage(13);
        certificateRequest.encode(handshakeMessage);
        handshakeMessage.writeToRecordStream();
    }

    protected void sendCertificateStatusMessage(CertificateStatus certificateStatus) throws IOException {
        TlsProtocol.HandshakeMessage handshakeMessage = new TlsProtocol.HandshakeMessage(22);
        certificateStatus.encode(handshakeMessage);
        handshakeMessage.writeToRecordStream();
    }

    protected void sendNewSessionTicketMessage(NewSessionTicket newSessionTicket) throws IOException {
        if (newSessionTicket == null) {
            throw new TlsFatalAlert(80);
        }
        TlsProtocol.HandshakeMessage handshakeMessage = new TlsProtocol.HandshakeMessage(4);
        newSessionTicket.encode(handshakeMessage);
        handshakeMessage.writeToRecordStream();
    }

    protected void sendServerHelloMessage() throws IOException {
        TlsProtocol.HandshakeMessage handshakeMessage = new TlsProtocol.HandshakeMessage(2);
        ProtocolVersion protocolVersion = this.tlsServer.getServerVersion();
        if (!protocolVersion.isEqualOrEarlierVersionOf(this.getContext().getClientVersion())) {
            throw new TlsFatalAlert(80);
        }
        this.recordStream.setReadVersion(protocolVersion);
        this.recordStream.setWriteVersion(protocolVersion);
        this.recordStream.setRestrictReadVersion(true);
        this.getContextAdmin().setServerVersion(protocolVersion);
        TlsUtils.writeVersion(protocolVersion, handshakeMessage);
        handshakeMessage.write(this.securityParameters.serverRandom);
        TlsUtils.writeOpaque8(TlsUtils.EMPTY_BYTES, handshakeMessage);
        int n = this.tlsServer.getSelectedCipherSuite();
        if (!Arrays.contains(this.offeredCipherSuites, n) || n == 0 || CipherSuite.isSCSV(n) || !TlsUtils.isValidCipherSuiteForVersion(n, this.getContext().getServerVersion())) {
            throw new TlsFatalAlert(80);
        }
        this.securityParameters.cipherSuite = n;
        short s = this.tlsServer.getSelectedCompressionMethod();
        if (!Arrays.contains(this.offeredCompressionMethods, s)) {
            throw new TlsFatalAlert(80);
        }
        this.securityParameters.compressionAlgorithm = s;
        TlsUtils.writeUint16(n, handshakeMessage);
        TlsUtils.writeUint8(s, (OutputStream)handshakeMessage);
        this.serverExtensions = this.tlsServer.getServerExtensions();
        if (this.secure_renegotiation) {
            boolean bl;
            byte[] byArray = TlsUtils.getExtensionData(this.serverExtensions, EXT_RenegotiationInfo);
            boolean bl2 = bl = null == byArray;
            if (bl) {
                this.serverExtensions = TlsExtensionsUtils.ensureExtensionsInitialised(this.serverExtensions);
                this.serverExtensions.put(EXT_RenegotiationInfo, TlsServerProtocol.createRenegotiationInfo(TlsUtils.EMPTY_BYTES));
            }
        }
        if (this.securityParameters.extendedMasterSecret) {
            this.serverExtensions = TlsExtensionsUtils.ensureExtensionsInitialised(this.serverExtensions);
            TlsExtensionsUtils.addExtendedMasterSecretExtension(this.serverExtensions);
        }
        if (this.serverExtensions != null) {
            this.securityParameters.encryptThenMAC = TlsExtensionsUtils.hasEncryptThenMACExtension(this.serverExtensions);
            this.securityParameters.maxFragmentLength = this.processMaxFragmentLengthExtension(this.clientExtensions, this.serverExtensions, (short)80);
            this.securityParameters.truncatedHMac = TlsExtensionsUtils.hasTruncatedHMacExtension(this.serverExtensions);
            this.allowCertificateStatus = !this.resumedSession && TlsUtils.hasExpectedEmptyExtensionData(this.serverExtensions, TlsExtensionsUtils.EXT_status_request, (short)80);
            this.expectSessionTicket = !this.resumedSession && TlsUtils.hasExpectedEmptyExtensionData(this.serverExtensions, TlsProtocol.EXT_SessionTicket, (short)80);
            TlsServerProtocol.writeExtensions(handshakeMessage, this.serverExtensions);
        }
        this.securityParameters.prfAlgorithm = TlsServerProtocol.getPRFAlgorithm(this.getContext(), this.securityParameters.getCipherSuite());
        this.securityParameters.verifyDataLength = 12;
        this.applyMaxFragmentLengthExtension();
        handshakeMessage.writeToRecordStream();
    }

    protected void sendServerHelloDoneMessage() throws IOException {
        byte[] byArray = new byte[4];
        TlsUtils.writeUint8((short)14, byArray, 0);
        TlsUtils.writeUint24(0, byArray, 1);
        this.writeHandshakeMessage(byArray, 0, byArray.length);
    }

    protected void sendServerKeyExchangeMessage(byte[] byArray) throws IOException {
        TlsProtocol.HandshakeMessage handshakeMessage = new TlsProtocol.HandshakeMessage(12, byArray.length);
        handshakeMessage.write(byArray);
        handshakeMessage.writeToRecordStream();
    }

    protected boolean expectCertificateVerifyMessage() {
        return this.clientCertificateType >= 0 && TlsUtils.hasSigningCapability(this.clientCertificateType);
    }
}

