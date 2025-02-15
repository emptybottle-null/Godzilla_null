/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt.dsig;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import javax.xml.crypto.Data;
import javax.xml.crypto.OctetStreamData;
import javax.xml.crypto.URIDereferencer;
import javax.xml.crypto.URIReference;
import javax.xml.crypto.URIReferenceException;
import javax.xml.crypto.XMLCryptoContext;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.poifs.crypt.dsig.SignatureConfig;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;

public class OOXMLURIDereferencer
implements URIDereferencer,
SignatureConfig.SignatureConfigurable {
    private static final POILogger LOG = POILogFactory.getLogger(OOXMLURIDereferencer.class);
    private SignatureConfig signatureConfig;
    private URIDereferencer baseUriDereferencer;

    @Override
    public void setSignatureConfig(SignatureConfig signatureConfig) {
        this.signatureConfig = signatureConfig;
    }

    @Override
    public Data dereference(URIReference uriReference, XMLCryptoContext context) throws URIReferenceException {
        InputStream dataStream;
        URI uri;
        if (this.baseUriDereferencer == null) {
            this.baseUriDereferencer = this.signatureConfig.getSignatureFactory().getURIDereferencer();
        }
        if (null == uriReference) {
            throw new NullPointerException("URIReference cannot be null");
        }
        if (null == context) {
            throw new NullPointerException("XMLCryptoContext cannot be null");
        }
        try {
            uri = new URI(uriReference.getURI());
        } catch (URISyntaxException e) {
            throw new URIReferenceException("could not URL decode the uri: " + uriReference.getURI(), e);
        }
        PackagePart part = this.findPart(uri);
        if (part == null) {
            LOG.log(1, "cannot resolve, delegating to base DOM URI dereferencer", uri);
            return this.baseUriDereferencer.dereference(uriReference, context);
        }
        try {
            dataStream = part.getInputStream();
            if (part.getPartName().toString().endsWith(".rels")) {
                int ch;
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                while ((ch = dataStream.read()) != -1) {
                    if (ch == 10 || ch == 13) continue;
                    bos.write(ch);
                }
                dataStream = new ByteArrayInputStream(bos.toByteArray());
            }
        } catch (IOException e) {
            throw new URIReferenceException("I/O error: " + e.getMessage(), e);
        }
        return new OctetStreamData(dataStream, uri.toString(), null);
    }

    private PackagePart findPart(URI uri) {
        PackagePartName ppn;
        LOG.log(1, "dereference", uri);
        String path = uri.getPath();
        if (path == null || path.isEmpty()) {
            LOG.log(1, "illegal part name (expected)", uri);
            return null;
        }
        try {
            ppn = PackagingURIHelper.createPartName(path);
        } catch (InvalidFormatException e) {
            LOG.log(5, "illegal part name (not expected)", uri);
            return null;
        }
        return this.signatureConfig.getOpcPackage().getPart(ppn);
    }
}

