/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt.dsig.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.HashMap;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.poifs.crypt.dsig.SignatureConfig;
import org.apache.poi.poifs.crypt.dsig.services.RevocationData;
import org.apache.poi.poifs.crypt.dsig.services.TimeStampService;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cms.DefaultCMSSignatureAlgorithmNameGenerator;
import org.bouncycastle.cms.SignerId;
import org.bouncycastle.cms.SignerInformationVerifier;
import org.bouncycastle.cms.bc.BcRSASignerInfoVerifierBuilder;
import org.bouncycastle.operator.DefaultDigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DefaultSignatureAlgorithmIdentifierFinder;
import org.bouncycastle.operator.bc.BcDigestCalculatorProvider;
import org.bouncycastle.tsp.TimeStampRequest;
import org.bouncycastle.tsp.TimeStampRequestGenerator;
import org.bouncycastle.tsp.TimeStampResponse;
import org.bouncycastle.tsp.TimeStampToken;

public class TSPTimeStampService
implements TimeStampService {
    private static final POILogger LOG = POILogFactory.getLogger(TSPTimeStampService.class);
    private SignatureConfig signatureConfig;

    public ASN1ObjectIdentifier mapDigestAlgoToOID(HashAlgorithm digestAlgo) {
        switch (digestAlgo) {
            case sha1: {
                return X509ObjectIdentifiers.id_SHA1;
            }
            case sha256: {
                return NISTObjectIdentifiers.id_sha256;
            }
            case sha384: {
                return NISTObjectIdentifiers.id_sha384;
            }
            case sha512: {
                return NISTObjectIdentifiers.id_sha512;
            }
        }
        throw new IllegalArgumentException("unsupported digest algo: " + (Object)((Object)digestAlgo));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte[] timeStamp(byte[] data, RevocationData revocationData) throws Exception {
        ByteArrayOutputStream bos;
        String contentType;
        MessageDigest messageDigest = CryptoFunctions.getMessageDigest(this.signatureConfig.getTspDigestAlgo());
        byte[] digest = messageDigest.digest(data);
        BigInteger nonce = new BigInteger(128, new SecureRandom());
        TimeStampRequestGenerator requestGenerator = new TimeStampRequestGenerator();
        requestGenerator.setCertReq(true);
        String requestPolicy = this.signatureConfig.getTspRequestPolicy();
        if (requestPolicy != null) {
            requestGenerator.setReqPolicy(new ASN1ObjectIdentifier(requestPolicy));
        }
        ASN1ObjectIdentifier digestAlgoOid = this.mapDigestAlgoToOID(this.signatureConfig.getTspDigestAlgo());
        TimeStampRequest request = requestGenerator.generate(digestAlgoOid, digest, nonce);
        byte[] encodedRequest = request.getEncoded();
        Proxy proxy = Proxy.NO_PROXY;
        if (this.signatureConfig.getProxyUrl() != null) {
            URL proxyUrl = new URL(this.signatureConfig.getProxyUrl());
            String host = proxyUrl.getHost();
            int port = proxyUrl.getPort();
            proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(InetAddress.getByName(host), port == -1 ? 80 : port));
        }
        HttpURLConnection huc = (HttpURLConnection)new URL(this.signatureConfig.getTspUrl()).openConnection(proxy);
        try {
            if (this.signatureConfig.getTspUser() != null) {
                String userPassword = this.signatureConfig.getTspUser() + ":" + this.signatureConfig.getTspPass();
                String encoding = Base64.getEncoder().encodeToString(userPassword.getBytes(StandardCharsets.ISO_8859_1));
                huc.setRequestProperty("Authorization", "Basic " + encoding);
            }
            huc.setRequestMethod("POST");
            huc.setConnectTimeout(20000);
            huc.setReadTimeout(20000);
            huc.setDoOutput(true);
            huc.setRequestProperty("User-Agent", this.signatureConfig.getUserAgent());
            huc.setRequestProperty("Content-Type", this.signatureConfig.isTspOldProtocol() ? "application/timestamp-request" : "application/timestamp-query");
            OutputStream hucOut = huc.getOutputStream();
            hucOut.write(encodedRequest);
            huc.connect();
            int statusCode = huc.getResponseCode();
            if (statusCode != 200) {
                LOG.log(7, "Error contacting TSP server ", this.signatureConfig.getTspUrl() + ", had status code " + statusCode + "/" + huc.getResponseMessage());
                throw new IOException("Error contacting TSP server " + this.signatureConfig.getTspUrl() + ", had status code " + statusCode + "/" + huc.getResponseMessage());
            }
            contentType = huc.getHeaderField("Content-Type");
            if (null == contentType) {
                throw new RuntimeException("missing Content-Type header");
            }
            bos = new ByteArrayOutputStream();
            IOUtils.copy(huc.getInputStream(), bos);
            LOG.log(1, "response content: ", HexDump.dump(bos.toByteArray(), 0L, 0));
        } finally {
            huc.disconnect();
        }
        if (!contentType.startsWith(this.signatureConfig.isTspOldProtocol() ? "application/timestamp-response" : "application/timestamp-reply")) {
            throw new RuntimeException("invalid Content-Type: " + contentType + ": " + HexDump.dump(bos.toByteArray(), 0L, 0, 200));
        }
        if (bos.size() == 0) {
            throw new RuntimeException("Content-Length is zero");
        }
        TimeStampResponse timeStampResponse = new TimeStampResponse(bos.toByteArray());
        timeStampResponse.validate(request);
        if (0 != timeStampResponse.getStatus()) {
            LOG.log(1, "status: " + timeStampResponse.getStatus());
            LOG.log(1, "status string: " + timeStampResponse.getStatusString());
            PKIFailureInfo failInfo = timeStampResponse.getFailInfo();
            if (null != failInfo) {
                LOG.log(1, "fail info int value: " + failInfo.intValue());
                if (256 == failInfo.intValue()) {
                    LOG.log(1, "unaccepted policy");
                }
            }
            throw new RuntimeException("timestamp response status != 0: " + timeStampResponse.getStatus());
        }
        TimeStampToken timeStampToken = timeStampResponse.getTimeStampToken();
        SignerId signerId = timeStampToken.getSID();
        BigInteger signerCertSerialNumber = signerId.getSerialNumber();
        X500Name signerCertIssuer = signerId.getIssuer();
        LOG.log(1, "signer cert serial number: " + signerCertSerialNumber);
        LOG.log(1, "signer cert issuer: " + signerCertIssuer);
        Collection certificates = timeStampToken.getCertificates().getMatches(null);
        X509CertificateHolder signerCert = null;
        HashMap<X500Name, X509CertificateHolder> certificateMap = new HashMap<X500Name, X509CertificateHolder>();
        for (X509CertificateHolder certificate : certificates) {
            if (signerCertIssuer.equals(certificate.getIssuer()) && signerCertSerialNumber.equals(certificate.getSerialNumber())) {
                signerCert = certificate;
            }
            certificateMap.put(certificate.getSubject(), certificate);
        }
        if (signerCert == null) {
            throw new RuntimeException("TSP response token has no signer certificate");
        }
        ArrayList<X509Certificate> tspCertificateChain = new ArrayList<X509Certificate>();
        JcaX509CertificateConverter x509converter = new JcaX509CertificateConverter();
        x509converter.setProvider("BC");
        X509CertificateHolder certificate = signerCert;
        do {
            LOG.log(1, "adding to certificate chain: " + certificate.getSubject());
            tspCertificateChain.add(x509converter.getCertificate(certificate));
        } while (!certificate.getSubject().equals(certificate.getIssuer()) && null != (certificate = (X509CertificateHolder)certificateMap.get(certificate.getIssuer())));
        X509CertificateHolder holder = new X509CertificateHolder(((X509Certificate)tspCertificateChain.get(0)).getEncoded());
        DefaultCMSSignatureAlgorithmNameGenerator nameGen = new DefaultCMSSignatureAlgorithmNameGenerator();
        DefaultSignatureAlgorithmIdentifierFinder sigAlgoFinder = new DefaultSignatureAlgorithmIdentifierFinder();
        DefaultDigestAlgorithmIdentifierFinder hashAlgoFinder = new DefaultDigestAlgorithmIdentifierFinder();
        BcDigestCalculatorProvider calculator = new BcDigestCalculatorProvider();
        BcRSASignerInfoVerifierBuilder verifierBuilder = new BcRSASignerInfoVerifierBuilder(nameGen, sigAlgoFinder, hashAlgoFinder, calculator);
        SignerInformationVerifier verifier = verifierBuilder.build(holder);
        timeStampToken.validate(verifier);
        if (this.signatureConfig.getTspValidator() != null) {
            this.signatureConfig.getTspValidator().validate(tspCertificateChain, revocationData);
        }
        LOG.log(1, "time-stamp token time: " + timeStampToken.getTimeStampInfo().getGenTime());
        return timeStampToken.getEncoded();
    }

    @Override
    public void setSignatureConfig(SignatureConfig signatureConfig) {
        this.signatureConfig = signatureConfig;
    }
}

