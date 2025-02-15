/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Hashtable;
import java.util.Vector;
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
import org.bouncycastle.crypto.tls.TlsAuthentication;
import org.bouncycastle.crypto.tls.TlsClient;
import org.bouncycastle.crypto.tls.TlsClientContextImpl;
import org.bouncycastle.crypto.tls.TlsCredentials;
import org.bouncycastle.crypto.tls.TlsExtensionsUtils;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsHandshakeHash;
import org.bouncycastle.crypto.tls.TlsKeyExchange;
import org.bouncycastle.crypto.tls.TlsProtocol;
import org.bouncycastle.crypto.tls.TlsSession;
import org.bouncycastle.crypto.tls.TlsSessionImpl;
import org.bouncycastle.crypto.tls.TlsSignerCredentials;
import org.bouncycastle.crypto.tls.TlsUtils;
import org.bouncycastle.util.Arrays;

public class DTLSClientProtocol
extends DTLSProtocol {
    public DTLSClientProtocol(SecureRandom secureRandom) {
        super(secureRandom);
    }

    public DTLSTransport connect(TlsClient tlsClient, DatagramTransport datagramTransport) throws IOException {
        Object object;
        if (tlsClient == null) {
            throw new IllegalArgumentException("'client' cannot be null");
        }
        if (datagramTransport == null) {
            throw new IllegalArgumentException("'transport' cannot be null");
        }
        SecurityParameters securityParameters = new SecurityParameters();
        securityParameters.entity = 1;
        ClientHandshakeState clientHandshakeState = new ClientHandshakeState();
        clientHandshakeState.client = tlsClient;
        clientHandshakeState.clientContext = new TlsClientContextImpl(this.secureRandom, securityParameters);
        securityParameters.clientRandom = TlsProtocol.createRandomBlock(tlsClient.shouldUseGMTUnixTime(), clientHandshakeState.clientContext.getNonceRandomGenerator());
        tlsClient.init(clientHandshakeState.clientContext);
        DTLSRecordLayer dTLSRecordLayer = new DTLSRecordLayer(datagramTransport, clientHandshakeState.clientContext, tlsClient, 22);
        TlsSession tlsSession = clientHandshakeState.client.getSessionToResume();
        if (tlsSession != null && tlsSession.isResumable() && (object = tlsSession.exportSessionParameters()) != null) {
            clientHandshakeState.tlsSession = tlsSession;
            clientHandshakeState.sessionParameters = object;
        }
        try {
            object = this.clientHandshake(clientHandshakeState, dTLSRecordLayer);
            return object;
        } catch (TlsFatalAlert tlsFatalAlert) {
            this.abortClientHandshake(clientHandshakeState, dTLSRecordLayer, tlsFatalAlert.getAlertDescription());
            throw tlsFatalAlert;
        } catch (IOException iOException) {
            this.abortClientHandshake(clientHandshakeState, dTLSRecordLayer, (short)80);
            throw iOException;
        } catch (RuntimeException runtimeException) {
            this.abortClientHandshake(clientHandshakeState, dTLSRecordLayer, (short)80);
            throw new TlsFatalAlert(80, (Throwable)runtimeException);
        } finally {
            securityParameters.clear();
        }
    }

    protected void abortClientHandshake(ClientHandshakeState clientHandshakeState, DTLSRecordLayer dTLSRecordLayer, short s) {
        dTLSRecordLayer.fail(s);
        this.invalidateSession(clientHandshakeState);
    }

    protected DTLSTransport clientHandshake(ClientHandshakeState clientHandshakeState, DTLSRecordLayer dTLSRecordLayer) throws IOException {
        Object object;
        Object object2;
        Object object3;
        Object object4;
        Object object5;
        Object object6;
        SecurityParameters securityParameters = clientHandshakeState.clientContext.getSecurityParameters();
        DTLSReliableHandshake dTLSReliableHandshake = new DTLSReliableHandshake(clientHandshakeState.clientContext, dTLSRecordLayer);
        byte[] byArray = this.generateClientHello(clientHandshakeState, clientHandshakeState.client);
        dTLSRecordLayer.setWriteVersion(ProtocolVersion.DTLSv10);
        dTLSReliableHandshake.sendMessage((short)1, byArray);
        DTLSReliableHandshake.Message message = dTLSReliableHandshake.receiveMessage();
        while (message.getType() == 3) {
            object6 = dTLSRecordLayer.getReadVersion();
            if (!((ProtocolVersion)object6).isEqualOrEarlierVersionOf((ProtocolVersion)(object5 = clientHandshakeState.clientContext.getClientVersion()))) {
                throw new TlsFatalAlert(47);
            }
            dTLSRecordLayer.setReadVersion(null);
            object4 = this.processHelloVerifyRequest(clientHandshakeState, message.getBody());
            object3 = DTLSClientProtocol.patchClientHelloWithCookie(byArray, object4);
            dTLSReliableHandshake.resetHandshakeMessagesDigest();
            dTLSReliableHandshake.sendMessage((short)1, (byte[])object3);
            message = dTLSReliableHandshake.receiveMessage();
        }
        if (message.getType() != 2) {
            throw new TlsFatalAlert(10);
        }
        object6 = dTLSRecordLayer.getReadVersion();
        this.reportServerVersion(clientHandshakeState, (ProtocolVersion)object6);
        dTLSRecordLayer.setWriteVersion((ProtocolVersion)object6);
        this.processServerHello(clientHandshakeState, message.getBody());
        dTLSReliableHandshake.notifyHelloComplete();
        DTLSClientProtocol.applyMaxFragmentLengthExtension(dTLSRecordLayer, securityParameters.maxFragmentLength);
        if (clientHandshakeState.resumedSession) {
            securityParameters.masterSecret = Arrays.clone(clientHandshakeState.sessionParameters.getMasterSecret());
            dTLSRecordLayer.initPendingEpoch(clientHandshakeState.client.getCipher());
            object6 = TlsUtils.calculateVerifyData(clientHandshakeState.clientContext, "server finished", TlsProtocol.getCurrentPRFHash(clientHandshakeState.clientContext, dTLSReliableHandshake.getHandshakeHash(), null));
            this.processFinished(dTLSReliableHandshake.receiveMessageBody((short)20), (byte[])object6);
            object5 = TlsUtils.calculateVerifyData(clientHandshakeState.clientContext, "client finished", TlsProtocol.getCurrentPRFHash(clientHandshakeState.clientContext, dTLSReliableHandshake.getHandshakeHash(), null));
            dTLSReliableHandshake.sendMessage((short)20, (byte[])object5);
            dTLSReliableHandshake.finish();
            clientHandshakeState.clientContext.setResumableSession(clientHandshakeState.tlsSession);
            clientHandshakeState.client.notifyHandshakeComplete();
            return new DTLSTransport(dTLSRecordLayer);
        }
        this.invalidateSession(clientHandshakeState);
        if (clientHandshakeState.selectedSessionID.length > 0) {
            clientHandshakeState.tlsSession = new TlsSessionImpl(clientHandshakeState.selectedSessionID, null);
        }
        if ((message = dTLSReliableHandshake.receiveMessage()).getType() == 23) {
            this.processServerSupplementalData(clientHandshakeState, message.getBody());
            message = dTLSReliableHandshake.receiveMessage();
        } else {
            clientHandshakeState.client.processServerSupplementalData(null);
        }
        clientHandshakeState.keyExchange = clientHandshakeState.client.getKeyExchange();
        clientHandshakeState.keyExchange.init(clientHandshakeState.clientContext);
        object6 = null;
        if (message.getType() == 11) {
            object6 = this.processServerCertificate(clientHandshakeState, message.getBody());
            message = dTLSReliableHandshake.receiveMessage();
        } else {
            clientHandshakeState.keyExchange.skipServerCredentials();
        }
        if (object6 == null || ((Certificate)object6).isEmpty()) {
            clientHandshakeState.allowCertificateStatus = false;
        }
        if (message.getType() == 22) {
            this.processCertificateStatus(clientHandshakeState, message.getBody());
            message = dTLSReliableHandshake.receiveMessage();
        }
        if (message.getType() == 12) {
            this.processServerKeyExchange(clientHandshakeState, message.getBody());
            message = dTLSReliableHandshake.receiveMessage();
        } else {
            clientHandshakeState.keyExchange.skipServerKeyExchange();
        }
        if (message.getType() == 13) {
            this.processCertificateRequest(clientHandshakeState, message.getBody());
            TlsUtils.trackHashAlgorithms(dTLSReliableHandshake.getHandshakeHash(), clientHandshakeState.certificateRequest.getSupportedSignatureAlgorithms());
            message = dTLSReliableHandshake.receiveMessage();
        }
        if (message.getType() == 14) {
            if (message.getBody().length != 0) {
                throw new TlsFatalAlert(50);
            }
        } else {
            throw new TlsFatalAlert(10);
        }
        dTLSReliableHandshake.getHandshakeHash().sealHashAlgorithms();
        object5 = clientHandshakeState.client.getClientSupplementalData();
        if (object5 != null) {
            object4 = DTLSClientProtocol.generateSupplementalData((Vector)object5);
            dTLSReliableHandshake.sendMessage((short)23, (byte[])object4);
        }
        if (clientHandshakeState.certificateRequest != null) {
            clientHandshakeState.clientCredentials = clientHandshakeState.authentication.getClientCredentials(clientHandshakeState.certificateRequest);
            object4 = null;
            if (clientHandshakeState.clientCredentials != null) {
                object4 = clientHandshakeState.clientCredentials.getCertificate();
            }
            if (object4 == null) {
                object4 = Certificate.EMPTY_CHAIN;
            }
            object3 = DTLSClientProtocol.generateCertificate((Certificate)object4);
            dTLSReliableHandshake.sendMessage((short)11, (byte[])object3);
        }
        if (clientHandshakeState.clientCredentials != null) {
            clientHandshakeState.keyExchange.processClientCredentials(clientHandshakeState.clientCredentials);
        } else {
            clientHandshakeState.keyExchange.skipClientCredentials();
        }
        object4 = this.generateClientKeyExchange(clientHandshakeState);
        dTLSReliableHandshake.sendMessage((short)16, (byte[])object4);
        object3 = dTLSReliableHandshake.prepareToFinish();
        securityParameters.sessionHash = TlsProtocol.getCurrentPRFHash(clientHandshakeState.clientContext, (TlsHandshakeHash)object3, null);
        TlsProtocol.establishMasterSecret(clientHandshakeState.clientContext, clientHandshakeState.keyExchange);
        dTLSRecordLayer.initPendingEpoch(clientHandshakeState.client.getCipher());
        if (clientHandshakeState.clientCredentials != null && clientHandshakeState.clientCredentials instanceof TlsSignerCredentials) {
            object2 = (TlsSignerCredentials)clientHandshakeState.clientCredentials;
            object = TlsUtils.getSignatureAndHashAlgorithm(clientHandshakeState.clientContext, (TlsSignerCredentials)object2);
            byte[] byArray2 = object == null ? securityParameters.getSessionHash() : object3.getFinalHash(((SignatureAndHashAlgorithm)object).getHash());
            byte[] byArray3 = object2.generateCertificateSignature(byArray2);
            DigitallySigned digitallySigned = new DigitallySigned((SignatureAndHashAlgorithm)object, byArray3);
            byte[] byArray4 = this.generateCertificateVerify(clientHandshakeState, digitallySigned);
            dTLSReliableHandshake.sendMessage((short)15, byArray4);
        }
        object2 = TlsUtils.calculateVerifyData(clientHandshakeState.clientContext, "client finished", TlsProtocol.getCurrentPRFHash(clientHandshakeState.clientContext, dTLSReliableHandshake.getHandshakeHash(), null));
        dTLSReliableHandshake.sendMessage((short)20, (byte[])object2);
        if (clientHandshakeState.expectSessionTicket) {
            message = dTLSReliableHandshake.receiveMessage();
            if (message.getType() == 4) {
                this.processNewSessionTicket(clientHandshakeState, message.getBody());
            } else {
                throw new TlsFatalAlert(10);
            }
        }
        object = TlsUtils.calculateVerifyData(clientHandshakeState.clientContext, "server finished", TlsProtocol.getCurrentPRFHash(clientHandshakeState.clientContext, dTLSReliableHandshake.getHandshakeHash(), null));
        this.processFinished(dTLSReliableHandshake.receiveMessageBody((short)20), (byte[])object);
        dTLSReliableHandshake.finish();
        if (clientHandshakeState.tlsSession != null) {
            clientHandshakeState.sessionParameters = new SessionParameters.Builder().setCipherSuite(securityParameters.getCipherSuite()).setCompressionAlgorithm(securityParameters.getCompressionAlgorithm()).setMasterSecret(securityParameters.getMasterSecret()).setPeerCertificate((Certificate)object6).setPSKIdentity(securityParameters.getPSKIdentity()).setSRPIdentity(securityParameters.getSRPIdentity()).setServerExtensions(clientHandshakeState.serverExtensions).build();
            clientHandshakeState.tlsSession = TlsUtils.importSession(clientHandshakeState.tlsSession.getSessionID(), clientHandshakeState.sessionParameters);
            clientHandshakeState.clientContext.setResumableSession(clientHandshakeState.tlsSession);
        }
        clientHandshakeState.client.notifyHandshakeComplete();
        return new DTLSTransport(dTLSRecordLayer);
    }

    protected byte[] generateCertificateVerify(ClientHandshakeState clientHandshakeState, DigitallySigned digitallySigned) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        digitallySigned.encode(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    protected byte[] generateClientHello(ClientHandshakeState clientHandshakeState, TlsClient tlsClient) throws IOException {
        boolean bl;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ProtocolVersion protocolVersion = tlsClient.getClientVersion();
        if (!protocolVersion.isDTLS()) {
            throw new TlsFatalAlert(80);
        }
        TlsClientContextImpl tlsClientContextImpl = clientHandshakeState.clientContext;
        tlsClientContextImpl.setClientVersion(protocolVersion);
        TlsUtils.writeVersion(protocolVersion, byteArrayOutputStream);
        SecurityParameters securityParameters = tlsClientContextImpl.getSecurityParameters();
        byteArrayOutputStream.write(securityParameters.getClientRandom());
        byte[] byArray = TlsUtils.EMPTY_BYTES;
        if (clientHandshakeState.tlsSession != null && ((byArray = clientHandshakeState.tlsSession.getSessionID()) == null || byArray.length > 32)) {
            byArray = TlsUtils.EMPTY_BYTES;
        }
        TlsUtils.writeOpaque8(byArray, byteArrayOutputStream);
        TlsUtils.writeOpaque8(TlsUtils.EMPTY_BYTES, byteArrayOutputStream);
        boolean bl2 = tlsClient.isFallback();
        clientHandshakeState.offeredCipherSuites = tlsClient.getCipherSuites();
        clientHandshakeState.clientExtensions = tlsClient.getClientExtensions();
        byte[] byArray2 = TlsUtils.getExtensionData(clientHandshakeState.clientExtensions, TlsProtocol.EXT_RenegotiationInfo);
        boolean bl3 = null == byArray2;
        boolean bl4 = bl = !Arrays.contains(clientHandshakeState.offeredCipherSuites, 255);
        if (bl3 && bl) {
            clientHandshakeState.offeredCipherSuites = Arrays.append(clientHandshakeState.offeredCipherSuites, 255);
        }
        if (bl2 && !Arrays.contains(clientHandshakeState.offeredCipherSuites, 22016)) {
            clientHandshakeState.offeredCipherSuites = Arrays.append(clientHandshakeState.offeredCipherSuites, 22016);
        }
        TlsUtils.writeUint16ArrayWithUint16Length(clientHandshakeState.offeredCipherSuites, byteArrayOutputStream);
        clientHandshakeState.offeredCompressionMethods = new short[]{0};
        TlsUtils.writeUint8ArrayWithUint8Length(clientHandshakeState.offeredCompressionMethods, byteArrayOutputStream);
        if (clientHandshakeState.clientExtensions != null) {
            TlsProtocol.writeExtensions(byteArrayOutputStream, clientHandshakeState.clientExtensions);
        }
        return byteArrayOutputStream.toByteArray();
    }

    protected byte[] generateClientKeyExchange(ClientHandshakeState clientHandshakeState) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        clientHandshakeState.keyExchange.generateClientKeyExchange(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    protected void invalidateSession(ClientHandshakeState clientHandshakeState) {
        if (clientHandshakeState.sessionParameters != null) {
            clientHandshakeState.sessionParameters.clear();
            clientHandshakeState.sessionParameters = null;
        }
        if (clientHandshakeState.tlsSession != null) {
            clientHandshakeState.tlsSession.invalidate();
            clientHandshakeState.tlsSession = null;
        }
    }

    protected void processCertificateRequest(ClientHandshakeState clientHandshakeState, byte[] byArray) throws IOException {
        if (clientHandshakeState.authentication == null) {
            throw new TlsFatalAlert(40);
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
        clientHandshakeState.certificateRequest = CertificateRequest.parse(clientHandshakeState.clientContext, byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        clientHandshakeState.keyExchange.validateCertificateRequest(clientHandshakeState.certificateRequest);
    }

    protected void processCertificateStatus(ClientHandshakeState clientHandshakeState, byte[] byArray) throws IOException {
        if (!clientHandshakeState.allowCertificateStatus) {
            throw new TlsFatalAlert(10);
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
        clientHandshakeState.certificateStatus = CertificateStatus.parse(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
    }

    protected byte[] processHelloVerifyRequest(ClientHandshakeState clientHandshakeState, byte[] byArray) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
        ProtocolVersion protocolVersion = TlsUtils.readVersion(byteArrayInputStream);
        byte[] byArray2 = TlsUtils.readOpaque8(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        if (!protocolVersion.isEqualOrEarlierVersionOf(clientHandshakeState.clientContext.getClientVersion())) {
            throw new TlsFatalAlert(47);
        }
        if (!ProtocolVersion.DTLSv12.isEqualOrEarlierVersionOf(protocolVersion) && byArray2.length > 32) {
            throw new TlsFatalAlert(47);
        }
        return byArray2;
    }

    protected void processNewSessionTicket(ClientHandshakeState clientHandshakeState, byte[] byArray) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
        NewSessionTicket newSessionTicket = NewSessionTicket.parse(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        clientHandshakeState.client.notifyNewSessionTicket(newSessionTicket);
    }

    protected Certificate processServerCertificate(ClientHandshakeState clientHandshakeState, byte[] byArray) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
        Certificate certificate = Certificate.parse(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        clientHandshakeState.keyExchange.processServerCertificate(certificate);
        clientHandshakeState.authentication = clientHandshakeState.client.getAuthentication();
        clientHandshakeState.authentication.notifyServerCertificate(certificate);
        return certificate;
    }

    protected void processServerHello(ClientHandshakeState clientHandshakeState, byte[] byArray) throws IOException {
        Serializable serializable;
        Object object;
        SecurityParameters securityParameters = clientHandshakeState.clientContext.getSecurityParameters();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
        ProtocolVersion protocolVersion = TlsUtils.readVersion(byteArrayInputStream);
        this.reportServerVersion(clientHandshakeState, protocolVersion);
        securityParameters.serverRandom = TlsUtils.readFully(32, (InputStream)byteArrayInputStream);
        clientHandshakeState.selectedSessionID = TlsUtils.readOpaque8(byteArrayInputStream);
        if (clientHandshakeState.selectedSessionID.length > 32) {
            throw new TlsFatalAlert(47);
        }
        clientHandshakeState.client.notifySessionID(clientHandshakeState.selectedSessionID);
        clientHandshakeState.resumedSession = clientHandshakeState.selectedSessionID.length > 0 && clientHandshakeState.tlsSession != null && Arrays.areEqual(clientHandshakeState.selectedSessionID, clientHandshakeState.tlsSession.getSessionID());
        int n = TlsUtils.readUint16(byteArrayInputStream);
        if (!Arrays.contains(clientHandshakeState.offeredCipherSuites, n) || n == 0 || CipherSuite.isSCSV(n) || !TlsUtils.isValidCipherSuiteForVersion(n, clientHandshakeState.clientContext.getServerVersion())) {
            throw new TlsFatalAlert(47);
        }
        DTLSClientProtocol.validateSelectedCipherSuite(n, (short)47);
        clientHandshakeState.client.notifySelectedCipherSuite(n);
        short s = TlsUtils.readUint8(byteArrayInputStream);
        if (!Arrays.contains(clientHandshakeState.offeredCompressionMethods, s)) {
            throw new TlsFatalAlert(47);
        }
        clientHandshakeState.client.notifySelectedCompressionMethod(s);
        clientHandshakeState.serverExtensions = TlsProtocol.readExtensions(byteArrayInputStream);
        if (clientHandshakeState.serverExtensions != null) {
            object = clientHandshakeState.serverExtensions.keys();
            while (object.hasMoreElements()) {
                serializable = (Integer)object.nextElement();
                if (serializable.equals(TlsProtocol.EXT_RenegotiationInfo)) continue;
                if (null == TlsUtils.getExtensionData(clientHandshakeState.clientExtensions, serializable)) {
                    throw new TlsFatalAlert(110);
                }
                if (!clientHandshakeState.resumedSession) continue;
            }
        }
        if ((object = (Object)TlsUtils.getExtensionData(clientHandshakeState.serverExtensions, TlsProtocol.EXT_RenegotiationInfo)) != null) {
            clientHandshakeState.secure_renegotiation = true;
            if (!Arrays.constantTimeAreEqual((byte[])object, TlsProtocol.createRenegotiationInfo(TlsUtils.EMPTY_BYTES))) {
                throw new TlsFatalAlert(40);
            }
        }
        clientHandshakeState.client.notifySecureRenegotiation(clientHandshakeState.secure_renegotiation);
        object = clientHandshakeState.clientExtensions;
        serializable = clientHandshakeState.serverExtensions;
        if (clientHandshakeState.resumedSession) {
            if (n != clientHandshakeState.sessionParameters.getCipherSuite() || s != clientHandshakeState.sessionParameters.getCompressionAlgorithm()) {
                throw new TlsFatalAlert(47);
            }
            object = null;
            serializable = clientHandshakeState.sessionParameters.readServerExtensions();
        }
        securityParameters.cipherSuite = n;
        securityParameters.compressionAlgorithm = s;
        if (serializable != null) {
            boolean bl = TlsExtensionsUtils.hasEncryptThenMACExtension((Hashtable)serializable);
            if (bl && !TlsUtils.isBlockCipherSuite(securityParameters.getCipherSuite())) {
                throw new TlsFatalAlert(47);
            }
            securityParameters.encryptThenMAC = bl;
            securityParameters.extendedMasterSecret = TlsExtensionsUtils.hasExtendedMasterSecretExtension((Hashtable)serializable);
            securityParameters.maxFragmentLength = DTLSClientProtocol.evaluateMaxFragmentLengthExtension(clientHandshakeState.resumedSession, (Hashtable)object, (Hashtable)serializable, (short)47);
            securityParameters.truncatedHMac = TlsExtensionsUtils.hasTruncatedHMacExtension((Hashtable)serializable);
            clientHandshakeState.allowCertificateStatus = !clientHandshakeState.resumedSession && TlsUtils.hasExpectedEmptyExtensionData((Hashtable)serializable, TlsExtensionsUtils.EXT_status_request, (short)47);
            boolean bl2 = clientHandshakeState.expectSessionTicket = !clientHandshakeState.resumedSession && TlsUtils.hasExpectedEmptyExtensionData((Hashtable)serializable, TlsProtocol.EXT_SessionTicket, (short)47);
        }
        if (object != null) {
            clientHandshakeState.client.processServerExtensions((Hashtable)serializable);
        }
        securityParameters.prfAlgorithm = TlsProtocol.getPRFAlgorithm(clientHandshakeState.clientContext, securityParameters.getCipherSuite());
        securityParameters.verifyDataLength = 12;
    }

    protected void processServerKeyExchange(ClientHandshakeState clientHandshakeState, byte[] byArray) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
        clientHandshakeState.keyExchange.processServerKeyExchange(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
    }

    protected void processServerSupplementalData(ClientHandshakeState clientHandshakeState, byte[] byArray) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
        Vector vector = TlsProtocol.readSupplementalDataMessage(byteArrayInputStream);
        clientHandshakeState.client.processServerSupplementalData(vector);
    }

    protected void reportServerVersion(ClientHandshakeState clientHandshakeState, ProtocolVersion protocolVersion) throws IOException {
        TlsClientContextImpl tlsClientContextImpl = clientHandshakeState.clientContext;
        ProtocolVersion protocolVersion2 = tlsClientContextImpl.getServerVersion();
        if (null == protocolVersion2) {
            tlsClientContextImpl.setServerVersion(protocolVersion);
            clientHandshakeState.client.notifyServerVersion(protocolVersion);
        } else if (!protocolVersion2.equals(protocolVersion)) {
            throw new TlsFatalAlert(47);
        }
    }

    protected static byte[] patchClientHelloWithCookie(byte[] byArray, byte[] byArray2) throws IOException {
        int n = 34;
        short s = TlsUtils.readUint8(byArray, n);
        int n2 = n + 1 + s;
        int n3 = n2 + 1;
        byte[] byArray3 = new byte[byArray.length + byArray2.length];
        System.arraycopy(byArray, 0, byArray3, 0, n2);
        TlsUtils.checkUint8(byArray2.length);
        TlsUtils.writeUint8(byArray2.length, byArray3, n2);
        System.arraycopy(byArray2, 0, byArray3, n3, byArray2.length);
        System.arraycopy(byArray, n3, byArray3, n3 + byArray2.length, byArray.length - n3);
        return byArray3;
    }

    protected static class ClientHandshakeState {
        TlsClient client = null;
        TlsClientContextImpl clientContext = null;
        TlsSession tlsSession = null;
        SessionParameters sessionParameters = null;
        SessionParameters.Builder sessionParametersBuilder = null;
        int[] offeredCipherSuites = null;
        short[] offeredCompressionMethods = null;
        Hashtable clientExtensions = null;
        Hashtable serverExtensions = null;
        byte[] selectedSessionID = null;
        boolean resumedSession = false;
        boolean secure_renegotiation = false;
        boolean allowCertificateStatus = false;
        boolean expectSessionTicket = false;
        TlsKeyExchange keyExchange = null;
        TlsAuthentication authentication = null;
        CertificateStatus certificateStatus = null;
        CertificateRequest certificateRequest = null;
        TlsCredentials clientCredentials = null;

        protected ClientHandshakeState() {
        }
    }
}

