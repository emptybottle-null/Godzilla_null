/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.io;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.DefaultHandler;

public class SAXEventRecorder
extends DefaultHandler
implements LexicalHandler,
DeclHandler,
DTDHandler,
Externalizable {
    public static final long serialVersionUID = 1L;
    private static final byte STRING = 0;
    private static final byte OBJECT = 1;
    private static final byte NULL = 2;
    private List<SAXEvent> events = new ArrayList<SAXEvent>();
    private Map<QName, List<String>> prefixMappings = new HashMap<QName, List<String>>();
    private static final String XMLNS = "xmlns";
    private static final String EMPTY_STRING = "";

    public void replay(ContentHandler handler) throws SAXException {
        block21: for (SAXEvent saxEvent : this.events) {
            switch (saxEvent.event) {
                case 1: {
                    handler.processingInstruction((String)saxEvent.getParm(0), (String)saxEvent.getParm(1));
                    continue block21;
                }
                case 2: {
                    handler.startPrefixMapping((String)saxEvent.getParm(0), (String)saxEvent.getParm(1));
                    continue block21;
                }
                case 3: {
                    handler.endPrefixMapping((String)saxEvent.getParm(0));
                    continue block21;
                }
                case 4: {
                    handler.startDocument();
                    continue block21;
                }
                case 5: {
                    handler.endDocument();
                    continue block21;
                }
                case 6: {
                    AttributesImpl attributes = new AttributesImpl();
                    List attParmList = (List)saxEvent.getParm(3);
                    if (attParmList != null) {
                        for (String[] attParms : attParmList) {
                            attributes.addAttribute(attParms[0], attParms[1], attParms[2], attParms[3], attParms[4]);
                        }
                    }
                    handler.startElement((String)saxEvent.getParm(0), (String)saxEvent.getParm(1), (String)saxEvent.getParm(2), attributes);
                    continue block21;
                }
                case 7: {
                    handler.endElement((String)saxEvent.getParm(0), (String)saxEvent.getParm(1), (String)saxEvent.getParm(2));
                    continue block21;
                }
                case 8: {
                    char[] chars = (char[])saxEvent.getParm(0);
                    int start = (Integer)saxEvent.getParm(1);
                    int end = (Integer)saxEvent.getParm(2);
                    handler.characters(chars, start, end);
                    continue block21;
                }
                case 9: {
                    ((LexicalHandler)((Object)handler)).startDTD((String)saxEvent.getParm(0), (String)saxEvent.getParm(1), (String)saxEvent.getParm(2));
                    continue block21;
                }
                case 10: {
                    ((LexicalHandler)((Object)handler)).endDTD();
                    continue block21;
                }
                case 11: {
                    ((LexicalHandler)((Object)handler)).startEntity((String)saxEvent.getParm(0));
                    continue block21;
                }
                case 12: {
                    ((LexicalHandler)((Object)handler)).endEntity((String)saxEvent.getParm(0));
                    continue block21;
                }
                case 13: {
                    ((LexicalHandler)((Object)handler)).startCDATA();
                    continue block21;
                }
                case 14: {
                    ((LexicalHandler)((Object)handler)).endCDATA();
                    continue block21;
                }
                case 15: {
                    char[] cchars = (char[])saxEvent.getParm(0);
                    int cstart = (Integer)saxEvent.getParm(1);
                    int cend = (Integer)saxEvent.getParm(2);
                    ((LexicalHandler)((Object)handler)).comment(cchars, cstart, cend);
                    continue block21;
                }
                case 16: {
                    ((DeclHandler)((Object)handler)).elementDecl((String)saxEvent.getParm(0), (String)saxEvent.getParm(1));
                    continue block21;
                }
                case 17: {
                    ((DeclHandler)((Object)handler)).attributeDecl((String)saxEvent.getParm(0), (String)saxEvent.getParm(1), (String)saxEvent.getParm(2), (String)saxEvent.getParm(3), (String)saxEvent.getParm(4));
                    continue block21;
                }
                case 18: {
                    ((DeclHandler)((Object)handler)).internalEntityDecl((String)saxEvent.getParm(0), (String)saxEvent.getParm(1));
                    continue block21;
                }
                case 19: {
                    ((DeclHandler)((Object)handler)).externalEntityDecl((String)saxEvent.getParm(0), (String)saxEvent.getParm(1), (String)saxEvent.getParm(2));
                    continue block21;
                }
            }
            throw new SAXException("Unrecognized event: " + saxEvent.event);
        }
    }

    @Override
    public void processingInstruction(String target, String data) throws SAXException {
        SAXEvent saxEvent = new SAXEvent(1);
        saxEvent.addParm(target);
        saxEvent.addParm(data);
        this.events.add(saxEvent);
    }

    @Override
    public void startPrefixMapping(String prefix, String uri) throws SAXException {
        SAXEvent saxEvent = new SAXEvent(2);
        saxEvent.addParm(prefix);
        saxEvent.addParm(uri);
        this.events.add(saxEvent);
    }

    @Override
    public void endPrefixMapping(String prefix) throws SAXException {
        SAXEvent saxEvent = new SAXEvent(3);
        saxEvent.addParm(prefix);
        this.events.add(saxEvent);
    }

    @Override
    public void startDocument() throws SAXException {
        SAXEvent saxEvent = new SAXEvent(4);
        this.events.add(saxEvent);
    }

    @Override
    public void endDocument() throws SAXException {
        SAXEvent saxEvent = new SAXEvent(5);
        this.events.add(saxEvent);
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qualifiedName, Attributes attributes) throws SAXException {
        SAXEvent saxEvent = new SAXEvent(6);
        saxEvent.addParm(namespaceURI);
        saxEvent.addParm(localName);
        saxEvent.addParm(qualifiedName);
        QName qName = namespaceURI != null ? new QName(localName, Namespace.get(namespaceURI)) : new QName(localName);
        if (attributes != null && attributes.getLength() > 0) {
            ArrayList<String[]> attParmList = new ArrayList<String[]>(attributes.getLength());
            for (int i = 0; i < attributes.getLength(); ++i) {
                String attLocalName = attributes.getLocalName(i);
                if (attLocalName.startsWith(XMLNS)) {
                    String prefix = attLocalName.length() > 5 ? attLocalName.substring(6) : EMPTY_STRING;
                    SAXEvent prefixEvent = new SAXEvent(2);
                    prefixEvent.addParm(prefix);
                    prefixEvent.addParm(attributes.getValue(i));
                    this.events.add(prefixEvent);
                    List<String> prefixes = this.prefixMappings.get(qName);
                    if (prefixes == null) {
                        prefixes = new ArrayList<String>();
                        this.prefixMappings.put(qName, prefixes);
                    }
                    prefixes.add(prefix);
                    continue;
                }
                String[] attParms = new String[]{attributes.getURI(i), attLocalName, attributes.getQName(i), attributes.getType(i), attributes.getValue(i)};
                attParmList.add(attParms);
            }
            saxEvent.addParm(attParmList);
        }
        this.events.add(saxEvent);
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        SAXEvent saxEvent = new SAXEvent(7);
        saxEvent.addParm(namespaceURI);
        saxEvent.addParm(localName);
        saxEvent.addParm(qName);
        this.events.add(saxEvent);
        QName elementName = namespaceURI != null ? new QName(localName, Namespace.get(namespaceURI)) : new QName(localName);
        List<String> prefixes = this.prefixMappings.get(elementName);
        if (prefixes != null) {
            for (String prefixe : prefixes) {
                SAXEvent prefixEvent = new SAXEvent(3);
                prefixEvent.addParm(prefixe);
                this.events.add(prefixEvent);
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int end) throws SAXException {
        SAXEvent saxEvent = new SAXEvent(8);
        saxEvent.addParm(ch);
        saxEvent.addParm(start);
        saxEvent.addParm(end);
        this.events.add(saxEvent);
    }

    @Override
    public void startDTD(String name, String publicId, String systemId) throws SAXException {
        SAXEvent saxEvent = new SAXEvent(9);
        saxEvent.addParm(name);
        saxEvent.addParm(publicId);
        saxEvent.addParm(systemId);
        this.events.add(saxEvent);
    }

    @Override
    public void endDTD() throws SAXException {
        SAXEvent saxEvent = new SAXEvent(10);
        this.events.add(saxEvent);
    }

    @Override
    public void startEntity(String name) throws SAXException {
        SAXEvent saxEvent = new SAXEvent(11);
        saxEvent.addParm(name);
        this.events.add(saxEvent);
    }

    @Override
    public void endEntity(String name) throws SAXException {
        SAXEvent saxEvent = new SAXEvent(12);
        saxEvent.addParm(name);
        this.events.add(saxEvent);
    }

    @Override
    public void startCDATA() throws SAXException {
        SAXEvent saxEvent = new SAXEvent(13);
        this.events.add(saxEvent);
    }

    @Override
    public void endCDATA() throws SAXException {
        SAXEvent saxEvent = new SAXEvent(14);
        this.events.add(saxEvent);
    }

    @Override
    public void comment(char[] ch, int start, int end) throws SAXException {
        SAXEvent saxEvent = new SAXEvent(15);
        saxEvent.addParm(ch);
        saxEvent.addParm(start);
        saxEvent.addParm(end);
        this.events.add(saxEvent);
    }

    @Override
    public void elementDecl(String name, String model) throws SAXException {
        SAXEvent saxEvent = new SAXEvent(16);
        saxEvent.addParm(name);
        saxEvent.addParm(model);
        this.events.add(saxEvent);
    }

    @Override
    public void attributeDecl(String eName, String aName, String type, String valueDefault, String value) throws SAXException {
        SAXEvent saxEvent = new SAXEvent(17);
        saxEvent.addParm(eName);
        saxEvent.addParm(aName);
        saxEvent.addParm(type);
        saxEvent.addParm(valueDefault);
        saxEvent.addParm(value);
        this.events.add(saxEvent);
    }

    @Override
    public void internalEntityDecl(String name, String value) throws SAXException {
        SAXEvent saxEvent = new SAXEvent(18);
        saxEvent.addParm(name);
        saxEvent.addParm(value);
        this.events.add(saxEvent);
    }

    @Override
    public void externalEntityDecl(String name, String publicId, String sysId) throws SAXException {
        SAXEvent saxEvent = new SAXEvent(19);
        saxEvent.addParm(name);
        saxEvent.addParm(publicId);
        saxEvent.addParm(sysId);
        this.events.add(saxEvent);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        if (this.events == null) {
            out.writeByte(2);
        } else {
            out.writeByte(1);
            out.writeObject(this.events);
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws ClassNotFoundException, IOException {
        if (in.readByte() != 2) {
            this.events = (List)in.readObject();
        }
    }

    static class SAXEvent
    implements Externalizable {
        public static final long serialVersionUID = 1L;
        static final byte PROCESSING_INSTRUCTION = 1;
        static final byte START_PREFIX_MAPPING = 2;
        static final byte END_PREFIX_MAPPING = 3;
        static final byte START_DOCUMENT = 4;
        static final byte END_DOCUMENT = 5;
        static final byte START_ELEMENT = 6;
        static final byte END_ELEMENT = 7;
        static final byte CHARACTERS = 8;
        static final byte START_DTD = 9;
        static final byte END_DTD = 10;
        static final byte START_ENTITY = 11;
        static final byte END_ENTITY = 12;
        static final byte START_CDATA = 13;
        static final byte END_CDATA = 14;
        static final byte COMMENT = 15;
        static final byte ELEMENT_DECL = 16;
        static final byte ATTRIBUTE_DECL = 17;
        static final byte INTERNAL_ENTITY_DECL = 18;
        static final byte EXTERNAL_ENTITY_DECL = 19;
        protected byte event;
        protected List<Object> parms;

        public SAXEvent() {
        }

        SAXEvent(byte event) {
            this.event = event;
        }

        void addParm(Object parm) {
            if (this.parms == null) {
                this.parms = new ArrayList<Object>(3);
            }
            this.parms.add(parm);
        }

        Object getParm(int index) {
            if (this.parms != null && index < this.parms.size()) {
                return this.parms.get(index);
            }
            return null;
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeByte(this.event);
            if (this.parms == null) {
                out.writeByte(2);
            } else {
                out.writeByte(1);
                out.writeObject(this.parms);
            }
        }

        @Override
        public void readExternal(ObjectInput in) throws ClassNotFoundException, IOException {
            this.event = in.readByte();
            if (in.readByte() != 2) {
                this.parms = (List)in.readObject();
            }
        }
    }
}

