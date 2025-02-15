/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import org.apache.xmlbeans.impl.common.SAXHelper;
import org.apache.xmlbeans.impl.common.Sax2Dom;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LoadSaveUtils {
    public static Document xmlText2GenericDom(InputStream is, Document emptyDoc) throws SAXException, ParserConfigurationException, IOException {
        SAXParser parser = SAXHelper.saxFactory().newSAXParser();
        Sax2Dom handler = new Sax2Dom(emptyDoc);
        parser.setProperty("http://xml.org/sax/properties/lexical-handler", handler);
        parser.parse(is, (DefaultHandler)handler);
        return (Document)handler.getDOM();
    }

    public static void xmlStreamReader2XmlText(XMLStreamReader xsr, OutputStream os) throws XMLStreamException {
        XMLStreamWriter xsw = XMLOutputFactory.newInstance().createXMLStreamWriter(os);
        while (xsr.hasNext()) {
            switch (xsr.getEventType()) {
                case 10: {
                    xsw.writeAttribute(xsr.getPrefix(), xsr.getNamespaceURI(), xsr.getLocalName(), xsr.getText());
                    break;
                }
                case 12: {
                    xsw.writeCData(xsr.getText());
                    break;
                }
                case 4: {
                    xsw.writeCharacters(xsr.getText());
                    break;
                }
                case 5: {
                    xsw.writeComment(xsr.getText());
                    break;
                }
                case 11: {
                    xsw.writeDTD(xsr.getText());
                    break;
                }
                case 8: {
                    xsw.writeEndDocument();
                    break;
                }
                case 2: {
                    xsw.writeEndElement();
                    break;
                }
                case 15: {
                    break;
                }
                case 9: {
                    xsw.writeEntityRef(xsr.getText());
                    break;
                }
                case 13: {
                    xsw.writeNamespace(xsr.getPrefix(), xsr.getNamespaceURI());
                    break;
                }
                case 14: {
                    break;
                }
                case 3: {
                    xsw.writeProcessingInstruction(xsr.getPITarget(), xsr.getPIData());
                    break;
                }
                case 6: {
                    xsw.writeCharacters(xsr.getText());
                    break;
                }
                case 7: {
                    xsw.writeStartDocument();
                    break;
                }
                case 1: {
                    xsw.writeStartElement(xsr.getPrefix() == null ? "" : xsr.getPrefix(), xsr.getLocalName(), xsr.getNamespaceURI());
                    int attrs = xsr.getAttributeCount();
                    for (int i = attrs - 1; i >= 0; --i) {
                        xsw.writeAttribute(xsr.getAttributePrefix(i) == null ? "" : xsr.getAttributePrefix(i), xsr.getAttributeNamespace(i), xsr.getAttributeLocalName(i), xsr.getAttributeValue(i));
                    }
                    int nses = xsr.getNamespaceCount();
                    for (int i = 0; i < nses; ++i) {
                        xsw.writeNamespace(xsr.getNamespacePrefix(i), xsr.getNamespaceURI(i));
                    }
                    break;
                }
            }
            xsr.next();
        }
        xsw.flush();
    }
}

