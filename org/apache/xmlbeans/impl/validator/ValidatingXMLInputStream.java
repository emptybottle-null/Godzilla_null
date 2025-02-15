/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.validator;

import java.util.AbstractCollection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XMLStreamValidationException;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.common.GenericXmlInputStream;
import org.apache.xmlbeans.impl.common.ValidatorListener;
import org.apache.xmlbeans.impl.common.XMLNameHelper;
import org.apache.xmlbeans.impl.common.XmlWhitespace;
import org.apache.xmlbeans.impl.schema.BuiltinSchemaTypeSystem;
import org.apache.xmlbeans.impl.validator.Validator;
import org.apache.xmlbeans.xml.stream.Attribute;
import org.apache.xmlbeans.xml.stream.AttributeIterator;
import org.apache.xmlbeans.xml.stream.CharacterData;
import org.apache.xmlbeans.xml.stream.StartElement;
import org.apache.xmlbeans.xml.stream.XMLEvent;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLName;
import org.apache.xmlbeans.xml.stream.XMLStreamException;

public final class ValidatingXMLInputStream
extends GenericXmlInputStream
implements ValidatorListener.Event {
    private XMLStreamValidationException _exception;
    private XMLInputStream _source;
    private Validator _validator;
    private StringBuffer _text = new StringBuffer();
    private boolean _finished;
    private String _xsiType;
    private String _xsiNil;
    private String _xsiLoc;
    private String _xsiNoLoc;
    private XMLName _name;
    private StartElement _startElement;

    public ValidatingXMLInputStream(XMLInputStream xis, SchemaTypeLoader typeLoader, SchemaType sType, XmlOptions options) throws XMLStreamException {
        this._source = xis;
        options = XmlOptions.maskNull(options);
        SchemaType type = (SchemaType)options.get("DOCUMENT_TYPE");
        if (type == null) {
            type = sType;
        }
        if (type == null) {
            SchemaType docType;
            type = BuiltinSchemaTypeSystem.ST_ANY_TYPE;
            if ((xis = xis.getSubStream()).skip(2) && (docType = typeLoader.findDocumentType(XMLNameHelper.getQName(xis.next().getName()))) != null) {
                type = docType;
            }
            xis.close();
        }
        this._validator = new Validator(type, null, typeLoader, options, new ExceptionXmlErrorListener());
        this.nextEvent(1);
    }

    @Override
    protected XMLEvent nextEvent() throws XMLStreamException {
        XMLEvent e = this._source.next();
        if (e == null) {
            if (!this._finished) {
                this.flushText();
                this.nextEvent(2);
                this._finished = true;
            }
        } else {
            switch (e.getType()) {
                case 16: 
                case 64: {
                    CharacterData cd = (CharacterData)e;
                    if (!cd.hasContent()) break;
                    this._text.append(cd.getContent());
                    break;
                }
                case 2: {
                    String local;
                    XMLName attrName;
                    Attribute attr;
                    StartElement se = (StartElement)e;
                    this.flushText();
                    this._startElement = se;
                    AttributeIterator attrs = se.getAttributes();
                    while (attrs.hasNext()) {
                        attr = attrs.next();
                        attrName = attr.getName();
                        if (!"http://www.w3.org/2001/XMLSchema-instance".equals(attrName.getNamespaceUri())) continue;
                        local = attrName.getLocalName();
                        if (local.equals("type")) {
                            this._xsiType = attr.getValue();
                            continue;
                        }
                        if (local.equals("nil")) {
                            this._xsiNil = attr.getValue();
                            continue;
                        }
                        if (local.equals("schemaLocation")) {
                            this._xsiLoc = attr.getValue();
                            continue;
                        }
                        if (!local.equals("noNamespaceSchemaLocation")) continue;
                        this._xsiNoLoc = attr.getValue();
                    }
                    this._name = e.getName();
                    this.nextEvent(1);
                    attrs = se.getAttributes();
                    while (attrs.hasNext()) {
                        attr = attrs.next();
                        attrName = attr.getName();
                        if ("http://www.w3.org/2001/XMLSchema-instance".equals(attrName.getNamespaceUri()) && ((local = attrName.getLocalName()).equals("type") || local.equals("nil") || local.equals("schemaLocation") || local.equals("noNamespaceSchemaLocation"))) continue;
                        this._text.append(attr.getValue());
                        this._name = attr.getName();
                        this.nextEvent(4);
                    }
                    this.clearText();
                    this._startElement = null;
                    break;
                }
                case 4: {
                    this.flushText();
                    this.nextEvent(2);
                }
            }
        }
        return e;
    }

    private void clearText() {
        this._text.delete(0, this._text.length());
    }

    private void flushText() throws XMLStreamException {
        if (this._text.length() > 0) {
            this.nextEvent(3);
            this.clearText();
        }
    }

    @Override
    public String getNamespaceForPrefix(String prefix) {
        if (this._startElement == null) {
            return null;
        }
        Map map = this._startElement.getNamespaceMap();
        if (map == null) {
            return null;
        }
        return (String)map.get(prefix);
    }

    @Override
    public XmlCursor getLocationAsCursor() {
        return null;
    }

    @Override
    public Location getLocation() {
        try {
            final org.apache.xmlbeans.xml.stream.Location xeLoc = this._source.peek().getLocation();
            if (xeLoc == null) {
                return null;
            }
            Location loc = new Location(){

                @Override
                public int getLineNumber() {
                    return xeLoc.getLineNumber();
                }

                @Override
                public int getColumnNumber() {
                    return xeLoc.getColumnNumber();
                }

                @Override
                public int getCharacterOffset() {
                    return -1;
                }

                @Override
                public String getPublicId() {
                    return xeLoc.getPublicId();
                }

                @Override
                public String getSystemId() {
                    return xeLoc.getSystemId();
                }
            };
            return loc;
        } catch (XMLStreamException e) {
            return null;
        }
    }

    @Override
    public String getXsiType() {
        return this._xsiType;
    }

    @Override
    public String getXsiNil() {
        return this._xsiNil;
    }

    @Override
    public String getXsiLoc() {
        return this._xsiLoc;
    }

    @Override
    public String getXsiNoLoc() {
        return this._xsiNoLoc;
    }

    @Override
    public QName getName() {
        return XMLNameHelper.getQName(this._name);
    }

    @Override
    public String getText() {
        return this._text.toString();
    }

    @Override
    public String getText(int wsr) {
        return XmlWhitespace.collapse(this._text.toString(), wsr);
    }

    @Override
    public boolean textIsWhitespace() {
        block3: for (int i = 0; i < this._text.length(); ++i) {
            switch (this._text.charAt(i)) {
                case '\t': 
                case '\n': 
                case '\r': 
                case ' ': {
                    continue block3;
                }
                default: {
                    return false;
                }
            }
        }
        return true;
    }

    private void nextEvent(int kind) throws XMLStreamException {
        assert (this._exception == null);
        this._validator.nextEvent(kind, this);
        if (this._exception != null) {
            throw this._exception;
        }
    }

    private final class ExceptionXmlErrorListener
    extends AbstractCollection {
        private ExceptionXmlErrorListener() {
        }

        @Override
        public boolean add(Object o) {
            assert (ValidatingXMLInputStream.this._exception == null);
            ValidatingXMLInputStream.this._exception = new XMLStreamValidationException((XmlError)o);
            return false;
        }

        @Override
        public Iterator iterator() {
            return Collections.EMPTY_LIST.iterator();
        }

        @Override
        public int size() {
            return 0;
        }
    }
}

