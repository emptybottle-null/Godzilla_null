/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.tls.Certificate;
import org.bouncycastle.crypto.tls.CertificateRequest;
import org.bouncycastle.crypto.tls.CertificateStatus;
import org.bouncycastle.crypto.tls.CipherSuite;
import org.bouncycastle.crypto.tls.DTLSProtocol;
import org.bouncycastle.crypto.tls.DTLSRecordLayer;
import org.bouncycastle.crypto.tls.DTLSReliableHandshake;
import org.bouncycastle.crypto.tls.DTLSTransport;
import org.bouncycastle.crypto.tls.DatagramTransport;
import org.bouncycastle.crypto.tls.DigitallySigned;
import org.bouncycastle.crypto.tls.NewSessionTicket;
import org.bouncycastle.crypto.tls.ProtocolVersion;
import org.bouncycastle.crypto.tls.SecurityParameters;
import org.bouncycastle.crypto.tls.SessionParameters;
import org.bouncycastle.crypto.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.crypto.tls.TlsCredentials;
import org.bouncycastle.crypto.tls.TlsExtensionsUtils;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsHandshakeHash;
import org.bouncycastle.crypto.tls.TlsKeyExchange;
import org.bouncycastle.crypto.tls.TlsProtocol;
import org.bouncycastle.crypto.tls.TlsServer;
import org.bouncycastle.crypto.tls.TlsServerContextImpl;
import org.bouncycastle.crypto.tls.TlsSession;
import org.bouncycastle.crypto.tls.TlsSigner;
import org.bouncycastle.crypto.tls.TlsUtils;
import org.bouncycastle.crypto.util.PublicKeyFactory;
import org.bouncycastle.util.Arrays;

public class DTLSServerProtocol
extends DTLSProtocol {
    protected boolean verifyRequests = true;

    public DTLSServerProtocol(SecureRandom secureRandom) {
        super(secureRandom);
    }

    public boolean getVerifyRequests() {
        return this.verifyRequests;
    }

    public void setVerifyRequests(boolean bl) {
        this.verifyRequests = bl;
    }

    public DTLSTransport accept(TlsServer tlsServer, DatagramTransport datagramTransport) throws IOException {
        if (tlsServer == null) {
            throw new IllegalArgumentException("'server' cannot be null");
        }
        if (datagramTransport == null) {
            throw new IllegalArgumentException("'transport' cannot be null");
        }
        SecurityParameters securityParameters = new SecurityParameters();
        securityParameters.entity = 0;
        ServerHandshakeState serverHandshakeState = new ServerHandshakeState();
        serverHandshakeState.server = tlsServer;
        serverHandshakeState.serverContext = new TlsServerContextImpl(this.secureRandom, securityParameters);
        securityParameters.serverRandom = TlsProtocol.createRandomBlock(tlsServer.shouldUseGMTUnixTime(), serverHandshakeState.serverContext.getNonceRandomGenerator());
        tlsServer.init(serverHandshakeState.serverContext);
        DTLSRecordLayer dTLSRecordLayer = new DTLSRecordLayer(datagramTransport, serverHandshakeState.serverContext, tlsServer, 22);
        try {
            DTLSTransport dTLSTransport = this.serverHandshake(serverHandshakeState, dTLSRecordLayer);
            return dTLSTransport;
        } catch (TlsFatalAlert tlsFatalAlert) {
            this.abortServerHandshake(serverHandshakeState, dTLSRecordLayer, tlsFatalAlert.getAlertDescription());
            throw tlsFatalAlert;
        } catch (IOException iOException) {
            this.abortServerHandshake(serverHandshakeState, dTLSRecordLayer, (short)80);
            throw iOException;
        } catch (RuntimeException runtimeException) {
            this.abortServerHandshake(serverHandshakeState, dTLSRecordLayer, (short)80);
            throw new TlsFatalAlert(80, (Throwable)runtimeException);
        } finally {
            securityParameters.clear();
        }
    }

    protected void abortServerHandshake(ServerHandshakeState serverHandshakeState, DTLSRecordLayer dTLSRecordLayer, short s) {
        dTLSRecordLayer.fail(s);
        this.invalidateSession(serverHandshakeState);
    }

    protected DTLSTransport serverHandshake(ServerHandshakeState serverHandshakeState, DTLSRecordLayer dTLSRecordLayer) throws IOException {
        Object object;
        byte[] byArray;
        Object object2;
        Object object3;
        SecurityParameters securityParameters = serverHandshakeState.serverContext.getSecurityParameters();
        DTLSReliableHandshake dTLSReliableHandshake = new DTLSReliableHandshake(serverHandshakeState.serverContext, dTLSRecordLayer);
        DTLSReliableHandshake.Message message = dTLSReliableHandshake.receiveMessage();
        if (message.getType() != 1) {
            throw new TlsFatalAlert(10);
        }
        this.processClientHello(serverHandshakeState, message.getBody());
        Object object4 = this.generateServerHello(serverHandshakeState);
        DTLSServerProtocol.applyMaxFragmentLengthExtension(dTLSRecordLayer, securityParameters.maxFragmentLength);
        Object object5 = serverHandshakeState.serverContext.getServerVersion();
        dTLSRecordLayer.setReadVersion((ProtocolVersion)object5);
        dTLSRecordLayer.setWriteVersion((ProtocolVersion)object5);
        dTLSReliableHandshake.sendMessage((short)2, (byte[])object4);
        dTLSReliableHandshake.notifyHelloComplete();
        object4 = serverHandshakeState.server.getServerSupplementalData();
        if (object4 != null) {
            object5 = DTLSServerProtocol.generateSupplementalData((Vector)object4);
            dTLSReliableHandshake.sendMessage((short)23, (byte[])object5);
        }
        serverHandshakeState.keyExchange = serverHandshakeState.server.getKeyExchange();
        serverHandshakeState.keyExchange.init(serverHandshakeState.serverContext);
        serverHandshakeState.serverCredentials = serverHandshakeState.server.getCredentials();
        object5 = null;
        if (serverHandshakeState.serverCredentials == null) {
            serverHandshakeState.keyExchange.skipServerCredentials();
        } else {
            serverHandshakeState.keyExchange.processServerCredentials(serverHandshakeState.serverCredentials);
            object5 = serverHandshakeState.serverCredentials.getCertificate();
            object3 = DTLSServerProtocol.generateCertificate((Certificate)object5);
            dTLSReliableHandshake.sendMessage((short)11, (byte[])object3);
        }
        if (object5 == null || ((Certificate)object5).isEmpty()) {
            serverHandshakeState.allowCertificateStatus = false;
        }
        if (serverHandshakeState.allowCertificateStatus && (object3 = (Object)serverHandshakeState.server.getCertificateStatus()) != null) {
            object2 = this.generateCertificateStatus(serverHandshakeState, (CertificateStatus)object3);
            dTLSReliableHandshake.sendMessage((short)22, (byte[])object2);
        }
        if ((object3 = serverHandshakeState.keyExchange.generateServerKeyExchange()) != null) {
            dTLSReliableHandshake.sendMessage((short)12, (byte[])object3);
        }
        if (serverHandshakeState.serverCredentials != null) {
            serverHandshakeState.certificateRequest = serverHandshakeState.server.getCertificateRequest();
            if (serverHandshakeState.certificateRequest != null) {
                if (TlsUtils.isTLSv12(serverHandshakeState.serverContext) != (serverHandshakeState.certificateRequest.getSupportedSignatureAlgorithms() != null)) {
                    throw new TlsFatalAlert(80);
                }
                serverHandshakeState.keyExchange.validateCertificateRequest(serverHandshakeState.certificateRequest);
                object2 = this.generateCertificateRequest(serverHandshakeState, serverHandshakeState.certificateRequest);
                dTLSReliableHandshake.sendMessage((short)13, (byte[])object2);
                TlsUtils.trackHashAlgorithms(dTLSReliableHandshake.getHandshakeHash(), serverHandshakeState.certificateRequest.getSupportedSignatureAlgorithms());
            }
        }
        dTLSReliableHandshake.sendMessage((short)14, TlsUtils.EMPTY_BYTES);
        dTLSReliableHandshake.getHandshakeHash().sealHashAlgorithms();
        message = dTLSReliableHandshake.receiveMessage();
        if (message.getType() == 23) {
            this.processClientSupplementalData(serverHandshakeState, message.getBody());
            message = dTLSReliableHandshake.receiveMessage();
        } else {
            serverHandshakeState.server.processClientSupplementalData(null);
        }
        if (serverHandshakeState.certificateRequest == null) {
            serverHandshakeState.keyExchange.skipClientCredentials();
        } else if (message.getType() == 11) {
            this.processClientCertificate(serverHandshakeState, message.getBody());
            message = dTLSReliableHandshake.receiveMessage();
        } else {
            if (TlsUtils.isTLSv12(serverHandshakeState.serverContext)) {
                throw new TlsFatalAlert(10);
            }
            this.notifyClientCertificate(serverHandshakeState, Certificate.EMPTY_CHAIN);
        }
        if (message.getType() != 16) {
            throw new TlsFatalAlert(10);
        }
        this.processClientKeyExchange(serverHandshakeState, message.getBody());
        object2 = dTLSReliableHandshake.prepareToFinish();
        securityParameters.sessionHash = TlsProtocol.getCurrentPRFHash(serverHandshakeState.serverContext, (TlsHandshakeHash)object2, null);
        TlsProtocol.establishMasterSecret(serverHandshakeState.serverContext, serverHandshakeState.keyExchange);
        dTLSRecordLayer.initPendingEpoch(serverHandshakeState.server.getCipher());
        if (this.expectCertificateVerifyMessage(serverHandshakeState)) {
            byArray = dTLSReliableHandshake.receiveMessageBody((short)15);
            this.processCertificateVerify(serverHandshakeState, byArray, (TlsHandshakeHash)object2);
        }
        byArray = TlsUtils.calculateVerifyData(serverHandshakeState.serverContext, "client finished", TlsProtocol.getCurrentPRFHash(serverHandshakeState.serverContext, dTLSReliableHandshake.getHandshakeHash(), null));
        this.processFinished(dTLSReliableHandshake.receiveMessageBody((short)20), byArray);
        if (serverHandshakeState.expectSessionTicket) {
            object = serverHandshakeState.server.getNewSessionTicket();
            byte[] byArray2 = this.generateNewSessionTicket(serverHandshakeState, (NewSessionTicket)object);
            dTLSReliableHandshake.sendMessage((short)4, byArray2);
        }
        object = TlsUtils.calculateVerifyData(serverHandshakeState.serverContext, "server finished", TlsProtocol.getCurrentPRFHash(serverHandshakeState.serverContext, dTLSReliableHandshake.getHandshakeHash(), null));
        dTLSReliableHandshake.sendMessage((short)20, (byte[])object);
        dTLSReliableHandshake.finish();
        serverHandshakeState.server.notifyHandshakeComplete();
        return new DTLSTransport(dTLSRecordLayer);
    }

    protected byte[] generateCertificateRequest(ServerHandshakeState serverHandshakeState, CertificateRequest certificateRequest) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        certificateRequest.encode(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    protected byte[] generateCertificateStatus(ServerHandshakeState serverHandshakeState, CertificateStatus certificateStatus) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        certificateStatus.encode(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    protected byte[] generateNewSessionTicket(ServerHandshakeState serverHandshakeState, NewSessionTicket newSessionTicket) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        newSessionTicket.encode(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    protected byte[] generateServerHello(ServerHandshakeState serverHandshakeState) throws IOException {
        SecurityParameters securityParameters = serverHandshakeState.serverContext.getSecurityParameters();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ProtocolVersion protocolVersion = serverHandshakeState.server.getServerVersion();
        if (!protocolVersion.isEqualOrEarlierVersionOf(serverHandshakeState.serverContext.getClientVersion())) {
            throw new TlsFatalAlert(80);
        }
        serverHandshakeState.serverContext.setServerVersion(protocolVersion);
        TlsUtils.writeVersion(serverHandshakeState.serverContext.getServerVersion(), byteArrayOutputStream);
        byteArrayOutputStream.write(securityParameters.getServerRandom());
        TlsUtils.writeOpaque8(TlsUtils.EMPTY_BYTES, byteArrayOutputStream);
        int n = serverHandshakeState.server.getSelectedCipherSuite();
        if (!Arrays.contains(serverHandshakeState.offeredCipherSuites, n) || n == 0 || CipherSuite.isSCSV(n) || !TlsUtils.isValidCipherSuiteForVersion(n, serverHandshakeState.serverContext.getServerVersion())) {
            throw new TlsFatalAlert(80);
        }
        DTLSServerProtocol.validateSelectedCipherSuite(n, (short)80);
        securityParameters.cipherSuite = n;
        short s = serverHandshakeState.server.getSelectedCompressionMethod();
        if (!Arrays.contains(serverHandshakeState.offeredCompressionMethods, s)) {
            throw new TlsFatalAlert(80);
        }
        securityParameters.compressionAlgorithm = s;
        TlsUtils.writeUint16(n, byteArrayOutputStream);
        TlsUtils.writeUint8(s, (OutputStream)byteArrayOutputStream);
        serverHandshakeState.serverExtensions = serverHandshakeState.server.getServerExtensions();
        if (serverHandshakeState.secure_renegotiation) {
            boolean bl;
            byte[] byArray = TlsUtils.getExtensionData(serverHandshakeState.serverExtensions, TlsProtocol.EXT_RenegotiationInfo);
            boolean bl2 = bl = null == byArray;
            if (bl) {
                serverHandshakeState.serverExtensions = TlsExtensionsUtils.ensureExtensionsInitialised(serverHandshakeState.serverExtensions);
                serverHandshakeState.serverExtensions.put(TlsProtocol.EXT_RenegotiationInfo, TlsProtocol.createRenegotiationInfo(TlsUtils.EMPTY_BYTES));
            }
        }
        if (securityParameters.extendedMasterSecret) {
            serverHandshakeState.serverExtensions = TlsExtensionsUtils.ensureExtensionsInitialised(serverHandshakeState.serverExtensions);
            TlsExtensionsUtils.addExtendedMasterSecretExtension(serverHandshakeState.serverExtensions);
        }
        if (serverHandshakeState.serverExtensions != null) {
            securityParameters.encryptThenMAC = TlsExtensionsUtils.hasEncryptThenMACExtension(serverHandshakeState.serverExtensions);
            securityParameters.maxFragmentLength = DTLSServerProtocol.evaluateMaxFragmentLengthExtension(serverHandshakeState.resumedSession, serverHandshakeState.clientExtensions, serverHandshakeState.serverExtensions, (short)80);
            securityParameters.truncatedHMac = TlsExtensionsUtils.hasTruncatedHMacExtension(serverHandshakeState.serverExtensions);
            serverHandshakeState.allowCertificateStatus = !serverHandshakeState.resumedSession && TlsUtils.hasExpectedEmptyExtensionData(serverHandshakeState.serverExtensions, TlsExtensionsUtils.EXT_status_request, (short)80);
            serverHandshakeState.expectSessionTicket = !serverHandshakeState.resumedSession && TlsUtils.hasExpectedEmptyExtensionData(serverHandshakeState.serverExtensions, TlsProtocol.EXT_SessionTicket, (short)80);
            TlsProtocol.writeExtensions(byteArrayOutputStream, serverHandshakeState.serverExtensions);
        }
        securityParameters.prfAlgorithm = TlsProtocol.getPRFAlgorithm(serverHandshakeState.serverContext, securityParameters.getCipherSuite());
        securityParameters.verifyDataLength = 12;
        return byteArrayOutputStream.toByteArray();
    }

    protected void invalidateSession(ServerHandshakeState serverHandshakeState) {
        if (serverHandshakeState.sessionParameters != null) {
            serverHandshakeState.sessionParameters.clear();
            serverHandshakeState.sessionParameters = null;
        }
        if (serverHandshakeState.tlsSession != null) {
            serverHandshakeState.tlsSession.invalidate();
            serverHandshakeState.tlsSession = null;
        }
    }

    protected void notifyClientCertificate(ServerHandshakeState serverHandshakeState, Certificate certificate) throws IOException {
        if (serverHandshakeState.certificateRequest == null) {
            throw new IllegalStateException();
        }
        if (serverHandshakeState.clientCertificate != null) {
            throw new TlsFatalAlert(10);
        }
        serverHandshakeState.clientCertificate = certificate;
        if (certificate.isEmpty()) {
            serverHandshakeState.keyExchange.skipClientCredentials();
        } else {
            serverHandshakeState.clientCertificateType = TlsUtils.getClientCertificateType(certificate, serverHandshakeState.serverCredentials.getCertificate());
            serverHandshakeState.keyExchange.processClientCertificate(certificate);
        }
        serverHandshakeState.server.notifyClientCertificate(certificate);
    }

    protected void processClientCertificate(ServerHandshakeState serverHandshakeState, byte[] byArray) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
        Certificate certificate = Certificate.parse(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        this.notifyClientCertificate(serverHandshakeState, certificate);
    }

    protected void processCertificateVerify(ServerHandshakeState serverHandshakeState, byte[] byArray, TlsHandshakeHash tlsHandshakeHash) throws IOException {
        if (serverHandshakeState.certificateRequest == null) {
            throw new IllegalStateException();
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
        TlsServerContextImpl tlsServerContextImpl = serverHandshakeState.serverContext;
        DigitallySigned digitallySigned = DigitallySigned.parse(tlsServerContextImpl, byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        try {
            byte[] byArray2;
            SignatureAndHashAlgorithm signatureAndHashAlgorithm = digitallySigned.getAlgorithm();
            if (TlsUtils.isTLSv12(tlsServerContextImpl)) {
                TlsUtils.verifySupportedSignatureAlgorithm(serverHandshakeState.certificateRequest.getSupportedSignatureAlgorithms(), signatureAndHashAlgorithm);
                byArray2 = tlsHandshakeHash.getFinalHash(signatureAndHashAlgorithm.getHash());
            } else {
                byArray2 = tlsServerContextImpl.getSecurityParameters().getSessionHash();
            }
            org.bouncycastle.asn1.x509.Certificate certificate = serverHandshakeState.clientCertificate.getCertificateAt(0);
            SubjectPublicKeyInfo subjectPublicKeyInfo = certificate.getSubjectPublicKeyInfo();
            AsymmetricKeyParameter asymmetricKeyParameter = PublicKeyFactory.createKey(subjectPublicKeyInfo);
            TlsSigner tlsSigner = TlsUtils.createTlsSigner(serverHandshakeState.clientCertificateType);
            tlsSigner.init(tlsServerContextImpl);
            if (!tlsSigner.verifyRawSignature(signatureAndHashAlgorithm, digitallySigned.getSignature(), asymmetricKeyParameter, byArray2)) {
                throw new TlsFatalAlert(51);
            }
        } catch (TlsFatalAlert tlsFatalAlert) {
            throw tlsFatalAlert;
        } catch (Exception exception) {
            throw new TlsFatalAlert(51, (Throwable)exception);
        }
    }

    protected void processClientHello(ServerHandshakeState serverHandshakeState, byte[] byArray) throws IOException {
        byte[] byArray2;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
        ProtocolVersion protocolVersion = TlsUtils.readVersion(byteArrayInputStream);
        if (!protocolVersion.isDTLS()) {
            throw new TlsFatalAlert(47);
        }
        byte[] byArray3 = TlsUtils.readFully(32, (InputStream)byteArrayInputStream);
        byte[] byArray4 = TlsUtils.readOpaque8(byteArrayInputStream);
        if (byArray4.length > 32) {
            throw new TlsFatalAlert(47);
        }
        byte[] byArray5 = TlsUtils.readOpaque8(byteArrayInputStream);
        int n = TlsUtils.readUint16(byteArrayInputStream);
        if (n < 2 || (n & 1) != 0) {
            throw new TlsFatalAlert(50);
        }
        serverHandshakeState.offeredCipherSuites = TlsUtils.readUint16Array(n / 2, byteArrayInputStream);
        short s = TlsUtils.readUint8(byteArrayInputStream);
        if (s < 1) {
            throw new TlsFatalAlert(47);
        }
        serverHandshakeState.offeredCompressionMethods = TlsUtils.readUint8Array(s, byteArrayInputStream);
        serverHandshakeState.clientExtensions = TlsProtocol.readExtensions(byteArrayInputStream);
        TlsServerContextImpl tlsServerContextImpl = serverHandshakeState.serverContext;
        SecurityParameters securityParameters = tlsServerContextImpl.getSecurityParameters();
        securityParameters.extendedMasterSecret = TlsExtensionsUtils.hasExtendedMasterSecretExtension(serverHandshakeState.clientExtensions);
        tlsServerContextImpl.setClientVersion(protocolVersion);
        serverHandshakeState.server.notifyClientVersion(protocolVersion);
        serverHandshakeState.server.notifyFallback(Arrays.contains(serverHandshakeState.offeredCipherSuites, 22016));
        securityParameters.clientRandom = byArray3;
        serverHandshakeState.server.notifyOfferedCipherSuites(serverHandshakeState.offeredCipherSuites);
        serverHandshakeState.server.notifyOfferedCompressionMethods(serverHandshakeState.offeredCompressionMethods);
        if (Arrays.contains(serverHandshakeState.offeredCipherSuites, 255)) {
            serverHandshakeState.secure_renegotiation = true;
        }
        if ((byArray2 = TlsUtils.getExtensionData(serverHandshakeState.clientExtensions, TlsProtocol.EXT_RenegotiationInfo)) != null) {
            serverHandshakeState.secure_renegotiation = true;
            if (!Arrays.constantTimeAreEqual(byArray2, TlsProtocol.createRenegotiationInfo(TlsUtils.EMPTY_BYTES))) {
                throw new TlsFatalAlert(40);
            }
        }
        serverHandshakeState.server.notifySecureRenegotiation(serverHandshakeState.secure_renegotiation);
        if (serverHandshakeState.clientExtensions != null) {
            TlsExtensionsUtils.getPaddingExtension(serverHandshakeState.clientExtensions);
            serverHandshakeState.server.processClientExtensions(serverHandshakeState.clientExtensions);
        }
    }

    protected void processClientKeyExchange(ServerHandshakeState serverHandshakeState, byte[] byArray) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
        serverHandshakeState.keyExchange.processClientKeyExchange(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
    }

    protected void processClientSupplementalData(ServerHandshakeState serverHandshakeState, byte[] byArray) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
        Vector vector = TlsProtocol.readSupplementalDataMessage(byteArrayInputStream);
        serverHandshakeState.server.processClientSupplementalData(vector);
    }

    protected boolean expectCertificateVerifyMessage(ServerHandshakeState serverHandshakeState) {
        return serverHandshakeState.clientCertificateType >= 0 && TlsUtils.hasSigningCapability(serverHandshakeState.clientCertificateType);
    }

    protected static class ServerHandshakeState {
        TlsServer server = null;
        TlsServerContextImpl serverContext = null;
        TlsSession tlsSession = null;
        SessionParameters sessionParameters = null;
        SessionParameters.Builder sessionParametersBuilder = null;
        int[] offeredCipherSuites = null;
        short[] offeredCompressionMethods = null;
        Hashtable clientExtensions = null;
        Hashtable serverExtensions = null;
        boolean resumedSession = false;
        boolean secure_renegotiation = false;
        boolean allowCertificateStatus = false;
        boolean expectSessionTicket = false;
        TlsKeyExchange keyExchange = null;
        TlsCredentials serverCredentials = null;
        CertificateRequest certificateRequest = null;
        short clientCertificateType = (short)-1;
        Certificate clientCertificate = null;

        protected ServerHandshakeState() {
        }
    }
}

