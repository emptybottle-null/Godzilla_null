/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.est;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.est.ESTAuth;
import org.bouncycastle.est.ESTException;
import org.bouncycastle.est.ESTHijacker;
import org.bouncycastle.est.ESTRequest;
import org.bouncycastle.est.ESTRequestBuilder;
import org.bouncycastle.est.ESTResponse;
import org.bouncycastle.est.HttpUtil;
import org.bouncycastle.est.Source;
import org.bouncycastle.operator.DefaultDigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;

public class HttpAuth
implements ESTAuth {
    private static final DigestAlgorithmIdentifierFinder digestAlgorithmIdentifierFinder = new DefaultDigestAlgorithmIdentifierFinder();
    private final String realm;
    private final String username;
    private final char[] password;
    private final SecureRandom nonceGenerator;
    private final DigestCalculatorProvider digestCalculatorProvider;
    private static final Set<String> validParts;

    public HttpAuth(String string, char[] cArray) {
        this(null, string, cArray, null, null);
    }

    public HttpAuth(String string, String string2, char[] cArray) {
        this(string, string2, cArray, null, null);
    }

    public HttpAuth(String string, char[] cArray, SecureRandom secureRandom, DigestCalculatorProvider digestCalculatorProvider) {
        this(null, string, cArray, secureRandom, digestCalculatorProvider);
    }

    public HttpAuth(String string, String string2, char[] cArray, SecureRandom secureRandom, DigestCalculatorProvider digestCalculatorProvider) {
        this.realm = string;
        this.username = string2;
        this.password = cArray;
        this.nonceGenerator = secureRandom;
        this.digestCalculatorProvider = digestCalculatorProvider;
    }

    public void applyAuth(ESTRequestBuilder eSTRequestBuilder) {
        eSTRequestBuilder.withHijacker(new ESTHijacker(){

            public ESTResponse hijack(ESTRequest eSTRequest, Source source) throws IOException {
                ESTResponse eSTResponse = new ESTResponse(eSTRequest, source);
                if (eSTResponse.getStatusCode() == 401) {
                    String string = eSTResponse.getHeader("WWW-Authenticate");
                    if (string == null) {
                        throw new ESTException("Status of 401 but no WWW-Authenticate header");
                    }
                    if ((string = Strings.toLowerCase(string)).startsWith("digest")) {
                        eSTResponse = HttpAuth.this.doDigestFunction(eSTResponse);
                    } else if (string.startsWith("basic")) {
                        eSTResponse.close();
                        Map<String, String> map = HttpUtil.splitCSL("Basic", eSTResponse.getHeader("WWW-Authenticate"));
                        if (HttpAuth.this.realm != null && !HttpAuth.this.realm.equals(map.get("realm"))) {
                            throw new ESTException("Supplied realm '" + HttpAuth.this.realm + "' does not match server realm '" + map.get("realm") + "'", null, 401, null);
                        }
                        ESTRequestBuilder eSTRequestBuilder = new ESTRequestBuilder(eSTRequest).withHijacker(null);
                        if (HttpAuth.this.realm != null && HttpAuth.this.realm.length() > 0) {
                            eSTRequestBuilder.setHeader("WWW-Authenticate", "Basic realm=\"" + HttpAuth.this.realm + "\"");
                        }
                        if (HttpAuth.this.username.contains(":")) {
                            throw new IllegalArgumentException("User must not contain a ':'");
                        }
                        String string2 = HttpAuth.this.username + ":" + new String(HttpAuth.this.password);
                        eSTRequestBuilder.setHeader("Authorization", "Basic " + Base64.toBase64String(string2.getBytes()));
                        eSTResponse = eSTRequest.getClient().doRequest(eSTRequestBuilder.build());
                    } else {
                        throw new ESTException("Unknown auth mode: " + string);
                    }
                    return eSTResponse;
                }
                return eSTResponse;
            }
        });
    }

    private ESTResponse doDigestFunction(ESTResponse eSTResponse) throws IOException {
        Object object;
        Object object2;
        Object object3;
        Object object4;
        Object object5;
        Object object6;
        Object object7;
        Object object8;
        String string3;
        eSTResponse.close();
        ESTRequest eSTRequest = eSTResponse.getOriginalRequest();
        Map<String, String> map = null;
        try {
            map = HttpUtil.splitCSL("Digest", eSTResponse.getHeader("WWW-Authenticate"));
        } catch (Throwable throwable) {
            throw new ESTException("Parsing WWW-Authentication header: " + throwable.getMessage(), throwable, eSTResponse.getStatusCode(), new ByteArrayInputStream(eSTResponse.getHeader("WWW-Authenticate").getBytes()));
        }
        String string2 = null;
        try {
            string2 = eSTRequest.getURL().toURI().getPath();
        } catch (Exception exception) {
            throw new IOException("unable to process URL in request: " + exception.getMessage());
        }
        for (String string3 : map.keySet()) {
            if (validParts.contains(string3)) continue;
            throw new ESTException("Unrecognised entry in WWW-Authenticate header: '" + string3 + "'");
        }
        String string4 = eSTRequest.getMethod();
        string3 = map.get("realm");
        String string5 = map.get("nonce");
        String string6 = map.get("opaque");
        String string7 = map.get("algorithm");
        String string8 = map.get("qop");
        ArrayList<String> arrayList = new ArrayList<String>();
        if (this.realm != null && !this.realm.equals(string3)) {
            throw new ESTException("Supplied realm '" + this.realm + "' does not match server realm '" + string3 + "'", null, 401, null);
        }
        if (string7 == null) {
            string7 = "MD5";
        }
        if (string7.length() == 0) {
            throw new ESTException("WWW-Authenticate no algorithm defined.");
        }
        string7 = Strings.toUpperCase(string7);
        if (string8 != null) {
            if (string8.length() == 0) {
                throw new ESTException("QoP value is empty.");
            }
            string8 = Strings.toLowerCase(string8);
            object8 = string8.split(",");
            for (int i = 0; i != ((String[])object8).length; ++i) {
                if (!object8[i].equals("auth") && !object8[i].equals("auth-int")) {
                    throw new ESTException("QoP value unknown: '" + i + "'");
                }
                object7 = object8[i].trim();
                if (arrayList.contains(object7)) continue;
                arrayList.add((String)object7);
            }
        } else {
            throw new ESTException("Qop is not defined in WWW-Authenticate header.");
        }
        object8 = this.lookupDigest(string7);
        if (object8 == null || ((AlgorithmIdentifier)object8).getAlgorithm() == null) {
            throw new IOException("auth digest algorithm unknown: " + string7);
        }
        DigestCalculator digestCalculator = this.getDigestCalculator(string7, (AlgorithmIdentifier)object8);
        object7 = digestCalculator.getOutputStream();
        String string9 = this.makeNonce(10);
        this.update((OutputStream)object7, this.username);
        this.update((OutputStream)object7, ":");
        this.update((OutputStream)object7, string3);
        this.update((OutputStream)object7, ":");
        this.update((OutputStream)object7, this.password);
        ((OutputStream)object7).close();
        byte[] byArray = digestCalculator.getDigest();
        if (string7.endsWith("-SESS")) {
            object6 = this.getDigestCalculator(string7, (AlgorithmIdentifier)object8);
            object5 = object6.getOutputStream();
            object4 = Hex.toHexString(byArray);
            this.update((OutputStream)object5, (String)object4);
            this.update((OutputStream)object5, ":");
            this.update((OutputStream)object5, string5);
            this.update((OutputStream)object5, ":");
            this.update((OutputStream)object5, string9);
            ((OutputStream)object5).close();
            byArray = object6.getDigest();
        }
        object6 = Hex.toHexString(byArray);
        object5 = this.getDigestCalculator(string7, (AlgorithmIdentifier)object8);
        object4 = object5.getOutputStream();
        if (((String)arrayList.get(0)).equals("auth-int")) {
            object3 = this.getDigestCalculator(string7, (AlgorithmIdentifier)object8);
            object2 = object3.getOutputStream();
            eSTRequest.writeData((OutputStream)object2);
            ((OutputStream)object2).close();
            object = object3.getDigest();
            this.update((OutputStream)object4, string4);
            this.update((OutputStream)object4, ":");
            this.update((OutputStream)object4, string2);
            this.update((OutputStream)object4, ":");
            this.update((OutputStream)object4, Hex.toHexString((byte[])object));
        } else if (((String)arrayList.get(0)).equals("auth")) {
            this.update((OutputStream)object4, string4);
            this.update((OutputStream)object4, ":");
            this.update((OutputStream)object4, string2);
        }
        ((OutputStream)object4).close();
        object3 = Hex.toHexString(object5.getDigest());
        object2 = this.getDigestCalculator(string7, (AlgorithmIdentifier)object8);
        object = object2.getOutputStream();
        if (arrayList.contains("missing")) {
            this.update((OutputStream)object, (String)object6);
            this.update((OutputStream)object, ":");
            this.update((OutputStream)object, string5);
            this.update((OutputStream)object, ":");
            this.update((OutputStream)object, (String)object3);
        } else {
            this.update((OutputStream)object, (String)object6);
            this.update((OutputStream)object, ":");
            this.update((OutputStream)object, string5);
            this.update((OutputStream)object, ":");
            this.update((OutputStream)object, "00000001");
            this.update((OutputStream)object, ":");
            this.update((OutputStream)object, string9);
            this.update((OutputStream)object, ":");
            if (((String)arrayList.get(0)).equals("auth-int")) {
                this.update((OutputStream)object, "auth-int");
            } else {
                this.update((OutputStream)object, "auth");
            }
            this.update((OutputStream)object, ":");
            this.update((OutputStream)object, (String)object3);
        }
        ((OutputStream)object).close();
        String string10 = Hex.toHexString(object2.getDigest());
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("username", this.username);
        hashMap.put("realm", string3);
        hashMap.put("nonce", string5);
        hashMap.put("uri", string2);
        hashMap.put("response", string10);
        if (((String)arrayList.get(0)).equals("auth-int")) {
            hashMap.put("qop", "auth-int");
            hashMap.put("nc", "00000001");
            hashMap.put("cnonce", string9);
        } else if (((String)arrayList.get(0)).equals("auth")) {
            hashMap.put("qop", "auth");
            hashMap.put("nc", "00000001");
            hashMap.put("cnonce", string9);
        }
        hashMap.put("algorithm", string7);
        if (string6 == null || string6.length() == 0) {
            hashMap.put("opaque", this.makeNonce(20));
        }
        ESTRequestBuilder eSTRequestBuilder = new ESTRequestBuilder(eSTRequest).withHijacker(null);
        eSTRequestBuilder.setHeader("Authorization", HttpUtil.mergeCSL("Digest", hashMap));
        return eSTRequest.getClient().doRequest(eSTRequestBuilder.build());
    }

    private DigestCalculator getDigestCalculator(String string, AlgorithmIdentifier algorithmIdentifier) throws IOException {
        DigestCalculator digestCalculator;
        try {
            digestCalculator = this.digestCalculatorProvider.get(algorithmIdentifier);
        } catch (OperatorCreationException operatorCreationException) {
            throw new IOException("cannot create digest calculator for " + string + ": " + operatorCreationException.getMessage());
        }
        return digestCalculator;
    }

    private AlgorithmIdentifier lookupDigest(String string) {
        if (string.endsWith("-SESS")) {
            string = string.substring(0, string.length() - "-SESS".length());
        }
        if (string.equals("SHA-512-256")) {
            return new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512_256, DERNull.INSTANCE);
        }
        return digestAlgorithmIdentifierFinder.find(string);
    }

    private void update(OutputStream outputStream, char[] cArray) throws IOException {
        outputStream.write(Strings.toUTF8ByteArray(cArray));
    }

    private void update(OutputStream outputStream, String string) throws IOException {
        outputStream.write(Strings.toUTF8ByteArray(string));
    }

    private String makeNonce(int n) {
        byte[] byArray = new byte[n];
        this.nonceGenerator.nextBytes(byArray);
        return Hex.toHexString(byArray);
    }

    static {
        HashSet<String> hashSet = new HashSet<String>();
        hashSet.add("realm");
        hashSet.add("nonce");
        hashSet.add("opaque");
        hashSet.add("algorithm");
        hashSet.add("qop");
        validParts = Collections.unmodifiableSet(hashSet);
    }
}

