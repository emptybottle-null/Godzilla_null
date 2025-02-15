/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.store;

import java.util.Iterator;
import java.util.Locale;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import org.apache.xmlbeans.impl.soap.Detail;
import org.apache.xmlbeans.impl.soap.DetailEntry;
import org.apache.xmlbeans.impl.soap.Name;
import org.apache.xmlbeans.impl.soap.Node;
import org.apache.xmlbeans.impl.soap.SOAPBody;
import org.apache.xmlbeans.impl.soap.SOAPBodyElement;
import org.apache.xmlbeans.impl.soap.SOAPElement;
import org.apache.xmlbeans.impl.soap.SOAPEnvelope;
import org.apache.xmlbeans.impl.soap.SOAPException;
import org.apache.xmlbeans.impl.soap.SOAPFault;
import org.apache.xmlbeans.impl.soap.SOAPHeader;
import org.apache.xmlbeans.impl.soap.SOAPHeaderElement;
import org.apache.xmlbeans.impl.soap.SOAPPart;
import org.apache.xmlbeans.impl.soap.Text;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public interface Saaj {
    public static final String SAAJ_IMPL = "SAAJ_IMPL";

    public void setCallback(SaajCallback var1);

    public Class identifyElement(QName var1, QName var2);

    public void soapNode_detachNode(Node var1);

    public void soapNode_recycleNode(Node var1);

    public String soapNode_getValue(Node var1);

    public void soapNode_setValue(Node var1, String var2);

    public SOAPElement soapNode_getParentElement(Node var1);

    public void soapNode_setParentElement(Node var1, SOAPElement var2);

    public void soapElement_removeContents(SOAPElement var1);

    public String soapElement_getEncodingStyle(SOAPElement var1);

    public void soapElement_setEncodingStyle(SOAPElement var1, String var2);

    public boolean soapElement_removeNamespaceDeclaration(SOAPElement var1, String var2);

    public Iterator soapElement_getAllAttributes(SOAPElement var1);

    public Iterator soapElement_getChildElements(SOAPElement var1);

    public Iterator soapElement_getNamespacePrefixes(SOAPElement var1);

    public SOAPElement soapElement_addAttribute(SOAPElement var1, Name var2, String var3) throws SOAPException;

    public SOAPElement soapElement_addChildElement(SOAPElement var1, SOAPElement var2) throws SOAPException;

    public SOAPElement soapElement_addChildElement(SOAPElement var1, Name var2) throws SOAPException;

    public SOAPElement soapElement_addChildElement(SOAPElement var1, String var2) throws SOAPException;

    public SOAPElement soapElement_addChildElement(SOAPElement var1, String var2, String var3) throws SOAPException;

    public SOAPElement soapElement_addChildElement(SOAPElement var1, String var2, String var3, String var4) throws SOAPException;

    public SOAPElement soapElement_addNamespaceDeclaration(SOAPElement var1, String var2, String var3);

    public SOAPElement soapElement_addTextNode(SOAPElement var1, String var2);

    public String soapElement_getAttributeValue(SOAPElement var1, Name var2);

    public Iterator soapElement_getChildElements(SOAPElement var1, Name var2);

    public Name soapElement_getElementName(SOAPElement var1);

    public String soapElement_getNamespaceURI(SOAPElement var1, String var2);

    public Iterator soapElement_getVisibleNamespacePrefixes(SOAPElement var1);

    public boolean soapElement_removeAttribute(SOAPElement var1, Name var2);

    public SOAPBody soapEnvelope_addBody(SOAPEnvelope var1) throws SOAPException;

    public SOAPBody soapEnvelope_getBody(SOAPEnvelope var1) throws SOAPException;

    public SOAPHeader soapEnvelope_getHeader(SOAPEnvelope var1) throws SOAPException;

    public SOAPHeader soapEnvelope_addHeader(SOAPEnvelope var1) throws SOAPException;

    public Name soapEnvelope_createName(SOAPEnvelope var1, String var2);

    public Name soapEnvelope_createName(SOAPEnvelope var1, String var2, String var3, String var4);

    public Iterator soapHeader_examineAllHeaderElements(SOAPHeader var1);

    public Iterator soapHeader_extractAllHeaderElements(SOAPHeader var1);

    public Iterator soapHeader_examineHeaderElements(SOAPHeader var1, String var2);

    public Iterator soapHeader_examineMustUnderstandHeaderElements(SOAPHeader var1, String var2);

    public Iterator soapHeader_extractHeaderElements(SOAPHeader var1, String var2);

    public SOAPHeaderElement soapHeader_addHeaderElement(SOAPHeader var1, Name var2);

    public void soapPart_removeAllMimeHeaders(SOAPPart var1);

    public void soapPart_removeMimeHeader(SOAPPart var1, String var2);

    public Iterator soapPart_getAllMimeHeaders(SOAPPart var1);

    public SOAPEnvelope soapPart_getEnvelope(SOAPPart var1);

    public Source soapPart_getContent(SOAPPart var1);

    public void soapPart_setContent(SOAPPart var1, Source var2);

    public String[] soapPart_getMimeHeader(SOAPPart var1, String var2);

    public void soapPart_addMimeHeader(SOAPPart var1, String var2, String var3);

    public void soapPart_setMimeHeader(SOAPPart var1, String var2, String var3);

    public Iterator soapPart_getMatchingMimeHeaders(SOAPPart var1, String[] var2);

    public Iterator soapPart_getNonMatchingMimeHeaders(SOAPPart var1, String[] var2);

    public boolean soapBody_hasFault(SOAPBody var1);

    public SOAPFault soapBody_addFault(SOAPBody var1) throws SOAPException;

    public SOAPFault soapBody_getFault(SOAPBody var1);

    public SOAPBodyElement soapBody_addBodyElement(SOAPBody var1, Name var2);

    public SOAPBodyElement soapBody_addDocument(SOAPBody var1, Document var2);

    public SOAPFault soapBody_addFault(SOAPBody var1, Name var2, String var3) throws SOAPException;

    public SOAPFault soapBody_addFault(SOAPBody var1, Name var2, String var3, Locale var4) throws SOAPException;

    public Detail soapFault_addDetail(SOAPFault var1) throws SOAPException;

    public Detail soapFault_getDetail(SOAPFault var1);

    public String soapFault_getFaultActor(SOAPFault var1);

    public String soapFault_getFaultCode(SOAPFault var1);

    public Name soapFault_getFaultCodeAsName(SOAPFault var1);

    public String soapFault_getFaultString(SOAPFault var1);

    public Locale soapFault_getFaultStringLocale(SOAPFault var1);

    public void soapFault_setFaultActor(SOAPFault var1, String var2);

    public void soapFault_setFaultCode(SOAPFault var1, Name var2) throws SOAPException;

    public void soapFault_setFaultCode(SOAPFault var1, String var2) throws SOAPException;

    public void soapFault_setFaultString(SOAPFault var1, String var2);

    public void soapFault_setFaultString(SOAPFault var1, String var2, Locale var3);

    public void soapHeaderElement_setMustUnderstand(SOAPHeaderElement var1, boolean var2);

    public boolean soapHeaderElement_getMustUnderstand(SOAPHeaderElement var1);

    public void soapHeaderElement_setActor(SOAPHeaderElement var1, String var2);

    public String soapHeaderElement_getActor(SOAPHeaderElement var1);

    public boolean soapText_isComment(Text var1);

    public DetailEntry detail_addDetailEntry(Detail var1, Name var2);

    public Iterator detail_getDetailEntries(Detail var1);

    public static interface SaajCallback {
        public void setSaajData(org.w3c.dom.Node var1, Object var2);

        public Object getSaajData(org.w3c.dom.Node var1);

        public Element createSoapElement(QName var1, QName var2);

        public Element importSoapElement(Document var1, Element var2, boolean var3, QName var4);
    }
}

