/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt.dsig.facets;

import javax.xml.crypto.MarshalException;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.XmlException;
import org.etsi.uri.x01903.v13.QualifyingPropertiesType;
import org.etsi.uri.x01903.v13.UnsignedPropertiesType;
import org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Office2010SignatureFacet
extends SignatureFacet {
    @Override
    public void postSign(Document document) throws MarshalException {
        UnsignedSignaturePropertiesType unsignedSigProps;
        QualifyingPropertiesType qualProps;
        NodeList nl = document.getElementsByTagNameNS("http://uri.etsi.org/01903/v1.3.2#", "QualifyingProperties");
        if (nl.getLength() != 1) {
            throw new MarshalException("no XAdES-BES extension present");
        }
        try {
            qualProps = QualifyingPropertiesType.Factory.parse(nl.item(0), POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        } catch (XmlException e) {
            throw new MarshalException(e);
        }
        UnsignedPropertiesType unsignedProps = qualProps.getUnsignedProperties();
        if (unsignedProps == null) {
            unsignedProps = qualProps.addNewUnsignedProperties();
        }
        if ((unsignedSigProps = unsignedProps.getUnsignedSignatureProperties()) == null) {
            unsignedProps.addNewUnsignedSignatureProperties();
        }
        Node n = document.importNode(qualProps.getDomNode().getFirstChild(), true);
        nl.item(0).getParentNode().replaceChild(n, nl.item(0));
    }
}

