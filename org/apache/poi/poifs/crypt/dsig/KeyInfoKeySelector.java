/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt.dsig;

import java.security.Key;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import javax.xml.crypto.AlgorithmMethod;
import javax.xml.crypto.KeySelector;
import javax.xml.crypto.KeySelectorException;
import javax.xml.crypto.KeySelectorResult;
import javax.xml.crypto.XMLCryptoContext;
import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;

public class KeyInfoKeySelector
extends KeySelector
implements KeySelectorResult {
    private static final POILogger LOG = POILogFactory.getLogger(KeyInfoKeySelector.class);
    private List<X509Certificate> certChain = new ArrayList<X509Certificate>();

    @Override
    public KeySelectorResult select(KeyInfo keyInfo, KeySelector.Purpose purpose, AlgorithmMethod method, XMLCryptoContext context) throws KeySelectorException {
        LOG.log(1, "select key");
        if (null == keyInfo) {
            throw new KeySelectorException("no ds:KeyInfo present");
        }
        List keyInfoContent = keyInfo.getContent();
        this.certChain.clear();
        for (XMLStructure keyInfoStructure : keyInfoContent) {
            if (!(keyInfoStructure instanceof X509Data)) continue;
            X509Data x509Data = (X509Data)keyInfoStructure;
            List x509DataList = x509Data.getContent();
            for (Object x509DataObject : x509DataList) {
                if (!(x509DataObject instanceof X509Certificate)) continue;
                X509Certificate certificate = (X509Certificate)x509DataObject;
                LOG.log(1, "certificate", certificate.getSubjectX500Principal());
                this.certChain.add(certificate);
            }
        }
        if (this.certChain.isEmpty()) {
            throw new KeySelectorException("No key found!");
        }
        return this;
    }

    @Override
    public Key getKey() {
        return this.certChain.isEmpty() ? null : this.certChain.get(0).getPublicKey();
    }

    public X509Certificate getSigner() {
        return this.certChain.isEmpty() ? null : this.certChain.get(0);
    }

    public List<X509Certificate> getCertChain() {
        return this.certChain;
    }
}

