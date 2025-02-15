/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.tsp;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.tsp.TimeStampReq;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.tsp.TSPException;
import org.bouncycastle.tsp.TSPUtil;
import org.bouncycastle.tsp.TSPValidationException;

public class TimeStampRequest {
    private static Set EMPTY_SET = Collections.unmodifiableSet(new HashSet());
    private TimeStampReq req;
    private Extensions extensions;

    public TimeStampRequest(TimeStampReq timeStampReq) {
        this.req = timeStampReq;
        this.extensions = timeStampReq.getExtensions();
    }

    public TimeStampRequest(byte[] byArray) throws IOException {
        this(new ByteArrayInputStream(byArray));
    }

    public TimeStampRequest(InputStream inputStream) throws IOException {
        this(TimeStampRequest.loadRequest(inputStream));
    }

    private static TimeStampReq loadRequest(InputStream inputStream) throws IOException {
        try {
            return TimeStampReq.getInstance(new ASN1InputStream(inputStream).readObject());
        } catch (ClassCastException classCastException) {
            throw new IOException("malformed request: " + classCastException);
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new IOException("malformed request: " + illegalArgumentException);
        }
    }

    public int getVersion() {
        return this.req.getVersion().getValue().intValue();
    }

    public ASN1ObjectIdentifier getMessageImprintAlgOID() {
        return this.req.getMessageImprint().getHashAlgorithm().getAlgorithm();
    }

    public byte[] getMessageImprintDigest() {
        return this.req.getMessageImprint().getHashedMessage();
    }

    public ASN1ObjectIdentifier getReqPolicy() {
        if (this.req.getReqPolicy() != null) {
            return this.req.getReqPolicy();
        }
        return null;
    }

    public BigInteger getNonce() {
        if (this.req.getNonce() != null) {
            return this.req.getNonce().getValue();
        }
        return null;
    }

    public boolean getCertReq() {
        if (this.req.getCertReq() != null) {
            return this.req.getCertReq().isTrue();
        }
        return false;
    }

    public void validate(Set set, Set set2, Set set3) throws TSPException {
        int n;
        set = this.convert(set);
        set2 = this.convert(set2);
        set3 = this.convert(set3);
        if (!set.contains(this.getMessageImprintAlgOID())) {
            throw new TSPValidationException("request contains unknown algorithm", 128);
        }
        if (set2 != null && this.getReqPolicy() != null && !set2.contains(this.getReqPolicy())) {
            throw new TSPValidationException("request contains unknown policy", 256);
        }
        if (this.getExtensions() != null && set3 != null) {
            Enumeration enumeration = this.getExtensions().oids();
            while (enumeration.hasMoreElements()) {
                ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier)enumeration.nextElement();
                if (set3.contains(aSN1ObjectIdentifier)) continue;
                throw new TSPValidationException("request contains unknown extension", 0x800000);
            }
        }
        if ((n = TSPUtil.getDigestLength(this.getMessageImprintAlgOID().getId())) != this.getMessageImprintDigest().length) {
            throw new TSPValidationException("imprint digest the wrong length", 4);
        }
    }

    public byte[] getEncoded() throws IOException {
        return this.req.getEncoded();
    }

    Extensions getExtensions() {
        return this.extensions;
    }

    public boolean hasExtensions() {
        return this.extensions != null;
    }

    public Extension getExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        if (this.extensions != null) {
            return this.extensions.getExtension(aSN1ObjectIdentifier);
        }
        return null;
    }

    public List getExtensionOIDs() {
        return TSPUtil.getExtensionOIDs(this.extensions);
    }

    public Set getNonCriticalExtensionOIDs() {
        if (this.extensions == null) {
            return EMPTY_SET;
        }
        return Collections.unmodifiableSet(new HashSet<ASN1ObjectIdentifier>(Arrays.asList(this.extensions.getNonCriticalExtensionOIDs())));
    }

    public Set getCriticalExtensionOIDs() {
        if (this.extensions == null) {
            return EMPTY_SET;
        }
        return Collections.unmodifiableSet(new HashSet<ASN1ObjectIdentifier>(Arrays.asList(this.extensions.getCriticalExtensionOIDs())));
    }

    private Set convert(Set set) {
        if (set == null) {
            return set;
        }
        HashSet<ASN1ObjectIdentifier> hashSet = new HashSet<ASN1ObjectIdentifier>(set.size());
        for (Object e : set) {
            if (e instanceof String) {
                hashSet.add(new ASN1ObjectIdentifier((String)e));
                continue;
            }
            hashSet.add((ASN1ObjectIdentifier)e);
        }
        return hashSet;
    }
}

