/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.common;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

public final class XmlReaderToWriter {
    private XmlReaderToWriter() {
    }

    public static void writeAll(XMLStreamReader xmlr, XMLStreamWriter writer) throws XMLStreamException {
        while (xmlr.hasNext()) {
            XmlReaderToWriter.write(xmlr, writer);
            xmlr.next();
        }
        XmlReaderToWriter.write(xmlr, writer);
        writer.flush();
    }

    public static void write(XMLStreamReader xmlr, XMLStreamWriter writer) throws XMLStreamException {
        switch (xmlr.getEventType()) {
            case 1: {
                int i;
                String localName = xmlr.getLocalName();
                String namespaceURI = xmlr.getNamespaceURI();
                if (namespaceURI != null && namespaceURI.length() > 0) {
                    String prefix = xmlr.getPrefix();
                    if (prefix != null) {
                        writer.writeStartElement(prefix, localName, namespaceURI);
                    } else {
                        writer.writeStartElement(namespaceURI, localName);
                    }
                } else {
                    writer.writeStartElement(localName);
                }
                int len = xmlr.getNamespaceCount();
                for (i = 0; i < len; ++i) {
                    writer.writeNamespace(xmlr.getNamespacePrefix(i), xmlr.getNamespaceURI(i));
                }
                len = xmlr.getAttributeCount();
                for (i = 0; i < len; ++i) {
                    String attUri = xmlr.getAttributeNamespace(i);
                    if (attUri != null) {
                        writer.writeAttribute(attUri, xmlr.getAttributeLocalName(i), xmlr.getAttributeValue(i));
                        continue;
                    }
                    writer.writeAttribute(xmlr.getAttributeLocalName(i), xmlr.getAttributeValue(i));
                }
                break;
            }
            case 2: {
                writer.writeEndElement();
                break;
            }
            case 4: 
            case 6: {
                writer.writeCharacters(xmlr.getTextCharacters(), xmlr.getTextStart(), xmlr.getTextLength());
                break;
            }
            case 3: {
                writer.writeProcessingInstruction(xmlr.getPITarget(), xmlr.getPIData());
                break;
            }
            case 12: {
                writer.writeCData(xmlr.getText());
                break;
            }
            case 5: {
                writer.writeComment(xmlr.getText());
                break;
            }
            case 9: {
                writer.writeEntityRef(xmlr.getLocalName());
                break;
            }
            case 7: {
                String encoding = xmlr.getCharacterEncodingScheme();
                String version = xmlr.getVersion();
                if (encoding != null && version != null) {
                    writer.writeStartDocument(encoding, version);
                    break;
                }
                if (version == null) break;
                writer.writeStartDocument(xmlr.getVersion());
                break;
            }
            case 8: {
                writer.writeEndDocument();
                break;
            }
            case 11: {
                writer.writeDTD(xmlr.getText());
            }
        }
    }
}

