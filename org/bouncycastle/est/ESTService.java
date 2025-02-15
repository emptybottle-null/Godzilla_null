/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.est;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Pattern;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERPrintableString;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.est.CsrAttrs;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cmc.CMCException;
import org.bouncycastle.cmc.SimplePKIResponse;
import org.bouncycastle.est.CACertsResponse;
import org.bouncycastle.est.CSRAttributesResponse;
import org.bouncycastle.est.CSRRequestResponse;
import org.bouncycastle.est.ESTAuth;
import org.bouncycastle.est.ESTClient;
import org.bouncycastle.est.ESTClientProvider;
import org.bouncycastle.est.ESTException;
import org.bouncycastle.est.ESTRequest;
import org.bouncycastle.est.ESTRequestBuilder;
import org.bouncycastle.est.ESTResponse;
import org.bouncycastle.est.ESTSourceConnectionListener;
import org.bouncycastle.est.EnrollmentResponse;
import org.bouncycastle.est.Source;
import org.bouncycastle.est.TLSUniqueProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCS10CertificationRequestBuilder;
import org.bouncycastle.util.Selector;
import org.bouncycastle.util.Store;
import org.bouncycastle.util.encoders.Base64;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ESTService {
    protected static final String CACERTS = "/cacerts";
    protected static final String SIMPLE_ENROLL = "/simpleenroll";
    protected static final String SIMPLE_REENROLL = "/simplereenroll";
    protected static final String FULLCMC = "/fullcmc";
    protected static final String SERVERGEN = "/serverkeygen";
    protected static final String CSRATTRS = "/csrattrs";
    protected static final Set<String> illegalParts = new HashSet<String>();
    private final String server;
    private final ESTClientProvider clientProvider;
    private static final Pattern pathInvalid;

    ESTService(String string, String string2, ESTClientProvider eSTClientProvider) {
        string = this.verifyServer(string);
        if (string2 != null) {
            string2 = this.verifyLabel(string2);
            this.server = "https://" + string + "/.well-known/est/" + string2;
        } else {
            this.server = "https://" + string + "/.well-known/est";
        }
        this.clientProvider = eSTClientProvider;
    }

    public static X509CertificateHolder[] storeToArray(Store<X509CertificateHolder> store) {
        return ESTService.storeToArray(store, null);
    }

    public static X509CertificateHolder[] storeToArray(Store<X509CertificateHolder> store, Selector<X509CertificateHolder> selector) {
        Collection<X509CertificateHolder> collection = store.getMatches(selector);
        return collection.toArray(new X509CertificateHolder[collection.size()]);
    }

    /*
     * Unable to fully structure code
     */
    public CACertsResponse getCACerts() throws Exception {
        var1_1 = null;
        var2_2 = null;
        var3_3 = null;
        var4_4 = null;
        var5_5 = false;
        try {
            var4_4 = new URL(this.server + "/cacerts");
            var6_6 = this.clientProvider.makeClient();
            var7_9 = new ESTRequestBuilder("GET", var4_4).withClient(var6_6).build();
            var1_1 = var6_6.doRequest(var7_9);
            var8_10 = null;
            var9_11 = null;
            if (var1_1.getStatusCode() == 200) {
                if (!"application/pkcs7-mime".equals(var1_1.getHeaders().getFirstValue("Content-Type"))) {
                    var10_12 = var1_1.getHeaders().getFirstValue("Content-Type") != null ? " got " + var1_1.getHeaders().getFirstValue("Content-Type") : " but was not present.";
                    throw new ESTException("Response : " + var4_4.toString() + "Expecting application/pkcs7-mime " + var10_12, null, var1_1.getStatusCode(), var1_1.getInputStream());
                }
                try {
                    if (var1_1.getContentLength() == null || var1_1.getContentLength() <= 0L) ** GOTO lbl28
                    var10_13 = new ASN1InputStream(var1_1.getInputStream());
                    var11_15 = new SimplePKIResponse(ContentInfo.getInstance((ASN1Sequence)var10_13.readObject()));
                    var8_10 = var11_15.getCertificates();
                    var9_11 = var11_15.getCRLs();
                } catch (Throwable var10_14) {
                    throw new ESTException("Decoding CACerts: " + var4_4.toString() + " " + var10_14.getMessage(), var10_14, var1_1.getStatusCode(), var1_1.getInputStream());
                }
            } else if (var1_1.getStatusCode() != 204) {
                throw new ESTException("Get CACerts: " + var4_4.toString(), null, var1_1.getStatusCode(), var1_1.getInputStream());
            }
lbl28:
            // 4 sources

            var3_3 = new CACertsResponse(var8_10, var9_11, var7_9, var1_1.getSource(), this.clientProvider.isTrusted());
        } catch (Throwable var6_8) {
            var5_5 = true;
            if (var6_8 instanceof ESTException) {
                throw (ESTException)var6_8;
            }
            throw new ESTException(var6_8.getMessage(), var6_8);
        } finally {
            if (var1_1 != null) {
                try {
                    var1_1.close();
                } catch (Exception var6_7) {
                    var2_2 = var6_7;
                }
            }
        }
        if (var2_2 != null) {
            if (var2_2 instanceof ESTException) {
                throw var2_2;
            }
            throw new ESTException("Get CACerts: " + var4_4.toString(), (Throwable)var2_2, var1_1.getStatusCode(), null);
        }
        return var3_3;
    }

    public EnrollmentResponse simpleEnroll(EnrollmentResponse enrollmentResponse) throws Exception {
        if (!this.clientProvider.isTrusted()) {
            throw new IllegalStateException("No trust anchors.");
        }
        ESTResponse eSTResponse = null;
        try {
            ESTClient eSTClient = this.clientProvider.makeClient();
            eSTResponse = eSTClient.doRequest(new ESTRequestBuilder(enrollmentResponse.getRequestToRetry()).withClient(eSTClient).build());
            EnrollmentResponse enrollmentResponse2 = this.handleEnrollResponse(eSTResponse);
            return enrollmentResponse2;
        } catch (Throwable throwable) {
            if (throwable instanceof ESTException) {
                throw (ESTException)throwable;
            }
            throw new ESTException(throwable.getMessage(), throwable);
        } finally {
            if (eSTResponse != null) {
                eSTResponse.close();
            }
        }
    }

    public EnrollmentResponse simpleEnroll(boolean bl, PKCS10CertificationRequest pKCS10CertificationRequest, ESTAuth eSTAuth) throws IOException {
        if (!this.clientProvider.isTrusted()) {
            throw new IllegalStateException("No trust anchors.");
        }
        ESTResponse eSTResponse = null;
        try {
            byte[] byArray = this.annotateRequest(pKCS10CertificationRequest.getEncoded()).getBytes();
            URL uRL = new URL(this.server + (bl ? SIMPLE_REENROLL : SIMPLE_ENROLL));
            ESTClient eSTClient = this.clientProvider.makeClient();
            ESTRequestBuilder eSTRequestBuilder = new ESTRequestBuilder("POST", uRL).withData(byArray).withClient(eSTClient);
            eSTRequestBuilder.addHeader("Content-Type", "application/pkcs10");
            eSTRequestBuilder.addHeader("Content-Length", "" + byArray.length);
            eSTRequestBuilder.addHeader("Content-Transfer-Encoding", "base64");
            if (eSTAuth != null) {
                eSTAuth.applyAuth(eSTRequestBuilder);
            }
            eSTResponse = eSTClient.doRequest(eSTRequestBuilder.build());
            EnrollmentResponse enrollmentResponse = this.handleEnrollResponse(eSTResponse);
            return enrollmentResponse;
        } catch (Throwable throwable) {
            if (throwable instanceof ESTException) {
                throw (ESTException)throwable;
            }
            throw new ESTException(throwable.getMessage(), throwable);
        } finally {
            if (eSTResponse != null) {
                eSTResponse.close();
            }
        }
    }

    public EnrollmentResponse simpleEnrollPoP(boolean bl, final PKCS10CertificationRequestBuilder pKCS10CertificationRequestBuilder, final ContentSigner contentSigner, ESTAuth eSTAuth) throws IOException {
        if (!this.clientProvider.isTrusted()) {
            throw new IllegalStateException("No trust anchors.");
        }
        ESTResponse eSTResponse = null;
        try {
            URL uRL = new URL(this.server + (bl ? SIMPLE_REENROLL : SIMPLE_ENROLL));
            ESTClient eSTClient = this.clientProvider.makeClient();
            ESTRequestBuilder eSTRequestBuilder = new ESTRequestBuilder("POST", uRL).withClient(eSTClient).withConnectionListener(new ESTSourceConnectionListener(){

                public ESTRequest onConnection(Source source, ESTRequest eSTRequest) throws IOException {
                    if (source instanceof TLSUniqueProvider && ((TLSUniqueProvider)((Object)source)).isTLSUniqueAvailable()) {
                        PKCS10CertificationRequestBuilder pKCS10CertificationRequestBuilder2 = new PKCS10CertificationRequestBuilder(pKCS10CertificationRequestBuilder);
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        byte[] byArray = ((TLSUniqueProvider)((Object)source)).getTLSUnique();
                        pKCS10CertificationRequestBuilder2.setAttribute(PKCSObjectIdentifiers.pkcs_9_at_challengePassword, new DERPrintableString(Base64.toBase64String(byArray)));
                        byteArrayOutputStream.write(ESTService.this.annotateRequest(pKCS10CertificationRequestBuilder2.build(contentSigner).getEncoded()).getBytes());
                        byteArrayOutputStream.flush();
                        ESTRequestBuilder eSTRequestBuilder = new ESTRequestBuilder(eSTRequest).withData(byteArrayOutputStream.toByteArray());
                        eSTRequestBuilder.setHeader("Content-Type", "application/pkcs10");
                        eSTRequestBuilder.setHeader("Content-Transfer-Encoding", "base64");
                        eSTRequestBuilder.setHeader("Content-Length", Long.toString(byteArrayOutputStream.size()));
                        return eSTRequestBuilder.build();
                    }
                    throw new IOException("Source does not supply TLS unique.");
                }
            });
            if (eSTAuth != null) {
                eSTAuth.applyAuth(eSTRequestBuilder);
            }
            eSTResponse = eSTClient.doRequest(eSTRequestBuilder.build());
            EnrollmentResponse enrollmentResponse = this.handleEnrollResponse(eSTResponse);
            return enrollmentResponse;
        } catch (Throwable throwable) {
            if (throwable instanceof ESTException) {
                throw (ESTException)throwable;
            }
            throw new ESTException(throwable.getMessage(), throwable);
        } finally {
            if (eSTResponse != null) {
                eSTResponse.close();
            }
        }
    }

    protected EnrollmentResponse handleEnrollResponse(ESTResponse eSTResponse) throws IOException {
        ESTRequest eSTRequest = eSTResponse.getOriginalRequest();
        Store<X509CertificateHolder> store = null;
        if (eSTResponse.getStatusCode() == 202) {
            String string = eSTResponse.getHeader("Retry-After");
            if (string == null) {
                throw new ESTException("Got Status 202 but not Retry-After header from: " + eSTRequest.getURL().toString());
            }
            long l = -1L;
            try {
                l = System.currentTimeMillis() + Long.parseLong(string) * 1000L;
            } catch (NumberFormatException numberFormatException) {
                try {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
                    simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
                    l = simpleDateFormat.parse(string).getTime();
                } catch (Exception exception) {
                    throw new ESTException("Unable to parse Retry-After header:" + eSTRequest.getURL().toString() + " " + exception.getMessage(), null, eSTResponse.getStatusCode(), eSTResponse.getInputStream());
                }
            }
            return new EnrollmentResponse(null, l, eSTRequest, eSTResponse.getSource());
        }
        if (eSTResponse.getStatusCode() == 200) {
            ASN1InputStream aSN1InputStream = new ASN1InputStream(eSTResponse.getInputStream());
            SimplePKIResponse simplePKIResponse = null;
            try {
                simplePKIResponse = new SimplePKIResponse(ContentInfo.getInstance(aSN1InputStream.readObject()));
            } catch (CMCException cMCException) {
                throw new ESTException(cMCException.getMessage(), cMCException.getCause());
            }
            store = simplePKIResponse.getCertificates();
            return new EnrollmentResponse(store, -1L, null, eSTResponse.getSource());
        }
        throw new ESTException("Simple Enroll: " + eSTRequest.getURL().toString(), null, eSTResponse.getStatusCode(), eSTResponse.getInputStream());
    }

    /*
     * Unable to fully structure code
     */
    public CSRRequestResponse getCSRAttributes() throws ESTException {
        if (!this.clientProvider.isTrusted()) {
            throw new IllegalStateException("No trust anchors.");
        }
        var1_1 = null;
        var2_2 = null;
        var3_3 = null;
        var4_4 = null;
        try {
            var4_4 = new URL(this.server + "/csrattrs");
            var5_5 = this.clientProvider.makeClient();
            var6_8 = new ESTRequestBuilder("GET", var4_4).withClient(var5_5).build();
            var1_1 = var5_5.doRequest(var6_8);
            switch (var1_1.getStatusCode()) {
                case 200: {
                    try {
                        if (var1_1.getContentLength() != null && var1_1.getContentLength() > 0L) {
                            var7_9 = new ASN1InputStream(var1_1.getInputStream());
                            var8_11 = (ASN1Sequence)var7_9.readObject();
                            var2_2 = new CSRAttributesResponse(CsrAttrs.getInstance(var8_11));
                            ** break;
                        }
lbl20:
                        // 3 sources

                        break;
                    } catch (Throwable var7_10) {
                        throw new ESTException("Decoding CACerts: " + var4_4.toString() + " " + var7_10.getMessage(), var7_10, var1_1.getStatusCode(), var1_1.getInputStream());
                    }
                }
                case 204: {
                    var2_2 = null;
                    ** break;
lbl26:
                    // 1 sources

                    break;
                }
                case 404: {
                    var2_2 = null;
                    ** break;
lbl30:
                    // 1 sources

                    break;
                }
                default: {
                    throw new ESTException("CSR Attribute request: " + var6_8.getURL().toString(), null, var1_1.getStatusCode(), var1_1.getInputStream());
                }
            }
        } catch (Throwable var5_7) {
            if (var5_7 instanceof ESTException) {
                throw (ESTException)var5_7;
            }
            throw new ESTException(var5_7.getMessage(), var5_7);
        } finally {
            if (var1_1 != null) {
                try {
                    var1_1.close();
                } catch (Exception var5_6) {
                    var3_3 = var5_6;
                }
            }
        }
        if (var3_3 != null) {
            if (var3_3 instanceof ESTException) {
                throw (ESTException)var3_3;
            }
            throw new ESTException(var3_3.getMessage(), (Throwable)var3_3, var1_1.getStatusCode(), null);
        }
        return new CSRRequestResponse(var2_2, var1_1.getSource());
    }

    private String annotateRequest(byte[] byArray) {
        int n = 0;
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        do {
            if (n + 48 < byArray.length) {
                printWriter.print(Base64.toBase64String(byArray, n, 48));
                n += 48;
            } else {
                printWriter.print(Base64.toBase64String(byArray, n, byArray.length - n));
                n = byArray.length;
            }
            printWriter.print('\n');
        } while (n < byArray.length);
        printWriter.flush();
        return stringWriter.toString();
    }

    private String verifyLabel(String string) {
        while (string.endsWith("/") && string.length() > 0) {
            string = string.substring(0, string.length() - 1);
        }
        while (string.startsWith("/") && string.length() > 0) {
            string = string.substring(1);
        }
        if (string.length() == 0) {
            throw new IllegalArgumentException("Label set but after trimming '/' is not zero length string.");
        }
        if (!pathInvalid.matcher(string).matches()) {
            throw new IllegalArgumentException("Server path " + string + " contains invalid characters");
        }
        if (illegalParts.contains(string)) {
            throw new IllegalArgumentException("Label " + string + " is a reserved path segment.");
        }
        return string;
    }

    private String verifyServer(String string) {
        try {
            while (string.endsWith("/") && string.length() > 0) {
                string = string.substring(0, string.length() - 1);
            }
            if (string.contains("://")) {
                throw new IllegalArgumentException("Server contains scheme, must only be <dnsname/ipaddress>:port, https:// will be added arbitrarily.");
            }
            URL uRL = new URL("https://" + string);
            if (uRL.getPath().length() == 0 || uRL.getPath().equals("/")) {
                return string;
            }
            throw new IllegalArgumentException("Server contains path, must only be <dnsname/ipaddress>:port, a path of '/.well-known/est/<label>' will be added arbitrarily.");
        } catch (Exception exception) {
            if (exception instanceof IllegalArgumentException) {
                throw (IllegalArgumentException)exception;
            }
            throw new IllegalArgumentException("Scheme and host is invalid: " + exception.getMessage(), exception);
        }
    }

    static {
        illegalParts.add(CACERTS.substring(1));
        illegalParts.add(SIMPLE_ENROLL.substring(1));
        illegalParts.add(SIMPLE_REENROLL.substring(1));
        illegalParts.add(FULLCMC.substring(1));
        illegalParts.add(SERVERGEN.substring(1));
        illegalParts.add(CSRATTRS.substring(1));
        pathInvalid = Pattern.compile("^[0-9a-zA-Z_\\-.~!$&'()*+,;=]+");
    }
}

