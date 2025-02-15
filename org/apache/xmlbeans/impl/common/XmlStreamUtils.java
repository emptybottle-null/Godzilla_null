/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.common;

import javax.xml.stream.XMLStreamReader;

public final class XmlStreamUtils {
    public static String printEvent(XMLStreamReader xmlr) {
        StringBuilder b = new StringBuilder();
        b.append("EVENT:[" + xmlr.getLocation().getLineNumber() + "][" + xmlr.getLocation().getColumnNumber() + "] ");
        b.append(XmlStreamUtils.getName(xmlr.getEventType()));
        b.append(" [");
        switch (xmlr.getEventType()) {
            case 1: {
                int i;
                b.append("<");
                XmlStreamUtils.printName(xmlr, b);
                for (i = 0; i < xmlr.getNamespaceCount(); ++i) {
                    b.append(" ");
                    String n = xmlr.getNamespacePrefix(i);
                    if ("xmlns".equals(n)) {
                        b.append("xmlns=\"" + xmlr.getNamespaceURI(i) + "\"");
                        continue;
                    }
                    b.append("xmlns:" + n);
                    b.append("=\"");
                    b.append(xmlr.getNamespaceURI(i));
                    b.append("\"");
                }
                for (i = 0; i < xmlr.getAttributeCount(); ++i) {
                    b.append(" ");
                    XmlStreamUtils.printName(xmlr.getAttributePrefix(i), xmlr.getAttributeNamespace(i), xmlr.getAttributeLocalName(i), b);
                    b.append("=\"");
                    b.append(xmlr.getAttributeValue(i));
                    b.append("\"");
                }
                b.append(">");
                break;
            }
            case 2: {
                b.append("</");
                XmlStreamUtils.printName(xmlr, b);
                for (int i = 0; i < xmlr.getNamespaceCount(); ++i) {
                    b.append(" ");
                    String n = xmlr.getNamespacePrefix(i);
                    if ("xmlns".equals(n)) {
                        b.append("xmlns=\"" + xmlr.getNamespaceURI(i) + "\"");
                        continue;
                    }
                    b.append("xmlns:" + n);
                    b.append("=\"");
                    b.append(xmlr.getNamespaceURI(i));
                    b.append("\"");
                }
                b.append(">");
                break;
            }
            case 4: 
            case 6: {
                int start = xmlr.getTextStart();
                int length = xmlr.getTextLength();
                b.append(new String(xmlr.getTextCharacters(), start, length));
                break;
            }
            case 3: {
                String data;
                String target = xmlr.getPITarget();
                if (target == null) {
                    target = "";
                }
                if ((data = xmlr.getPIData()) == null) {
                    data = "";
                }
                b.append("<?");
                b.append(target + " " + data);
                b.append("?>");
                break;
            }
            case 12: {
                b.append("<![CDATA[");
                if (xmlr.hasText()) {
                    b.append(xmlr.getText());
                }
                b.append("]]>");
                break;
            }
            case 5: {
                b.append("<!--");
                if (xmlr.hasText()) {
                    b.append(xmlr.getText());
                }
                b.append("-->");
                break;
            }
            case 9: {
                b.append(xmlr.getLocalName() + "=");
                if (!xmlr.hasText()) break;
                b.append("[" + xmlr.getText() + "]");
                break;
            }
            case 7: {
                b.append("<?xml");
                b.append(" version='" + xmlr.getVersion() + "'");
                b.append(" encoding='" + xmlr.getCharacterEncodingScheme() + "'");
                if (xmlr.isStandalone()) {
                    b.append(" standalone='yes'");
                } else {
                    b.append(" standalone='no'");
                }
                b.append("?>");
            }
        }
        b.append("]");
        return b.toString();
    }

    private static void printName(String prefix, String uri, String localName, StringBuilder b) {
        if (uri != null && !"".equals(uri)) {
            b.append("['" + uri + "']:");
        }
        if (prefix != null && !"".equals(prefix)) {
            b.append(prefix + ":");
        }
        if (localName != null) {
            b.append(localName);
        }
    }

    private static void printName(XMLStreamReader xmlr, StringBuilder b) {
        if (xmlr.hasName()) {
            String prefix = xmlr.getPrefix();
            String uri = xmlr.getNamespaceURI();
            String localName = xmlr.getLocalName();
            XmlStreamUtils.printName(prefix, uri, localName, b);
        }
    }

    public static String getName(int eventType) {
        switch (eventType) {
            case 1: {
                return "START_ELEMENT";
            }
            case 2: {
                return "END_ELEMENT";
            }
            case 3: {
                return "PROCESSING_INSTRUCTION";
            }
            case 4: {
                return "CHARACTERS";
            }
            case 6: {
                return "SPACE";
            }
            case 5: {
                return "COMMENT";
            }
            case 7: {
                return "START_DOCUMENT";
            }
            case 8: {
                return "END_DOCUMENT";
            }
            case 9: {
                return "ENTITY_REFERENCE";
            }
            case 10: {
                return "ATTRIBUTE";
            }
            case 11: {
                return "DTD";
            }
            case 12: {
                return "CDATA";
            }
            case 13: {
                return "NAMESPACE";
            }
        }
        return "UNKNOWN_EVENT_TYPE";
    }

    public static int getType(String val) {
        if (val.equals("START_ELEMENT")) {
            return 1;
        }
        if (val.equals("SPACE")) {
            return 6;
        }
        if (val.equals("END_ELEMENT")) {
            return 2;
        }
        if (val.equals("PROCESSING_INSTRUCTION")) {
            return 3;
        }
        if (val.equals("CHARACTERS")) {
            return 4;
        }
        if (val.equals("COMMENT")) {
            return 5;
        }
        if (val.equals("START_DOCUMENT")) {
            return 7;
        }
        if (val.equals("END_DOCUMENT")) {
            return 8;
        }
        if (val.equals("ATTRIBUTE")) {
            return 10;
        }
        if (val.equals("DTD")) {
            return 11;
        }
        if (val.equals("CDATA")) {
            return 12;
        }
        if (val.equals("NAMESPACE")) {
            return 13;
        }
        return -1;
    }
}

