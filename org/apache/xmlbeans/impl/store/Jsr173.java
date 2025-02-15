/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.store;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.XmlDocumentProperties;
import org.apache.xmlbeans.XmlLineNumber;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.store.CharUtil;
import org.apache.xmlbeans.impl.store.Cur;
import org.apache.xmlbeans.impl.store.Locale;
import org.w3c.dom.Node;

public class Jsr173 {
    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Node nodeFromStream(XMLStreamReader xs) {
        if (!(xs instanceof Jsr173GateWay)) {
            return null;
        }
        Jsr173GateWay gw = (Jsr173GateWay)((Object)xs);
        Locale l = gw._l;
        if (l.noSync()) {
            l.enter();
            try {
                Node node = Jsr173.nodeFromStreamImpl(gw);
                return node;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            Node node;
            l.enter();
            try {
                node = Jsr173.nodeFromStreamImpl(gw);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return node;
        }
    }

    public static Node nodeFromStreamImpl(Jsr173GateWay gw) {
        Cur c = gw._xs.getStreamCur();
        return c.isNode() ? (Node)((Object)c.getDom()) : (Node)null;
    }

    public static XMLStreamReader newXmlStreamReader(Cur c, Object src, int off, int cch) {
        XMLStreamReaderForString xs = new XMLStreamReaderForString(c, src, off, cch);
        if (c._locale.noSync()) {
            return new UnsyncedJsr173(c._locale, xs);
        }
        return new SyncedJsr173(c._locale, xs);
    }

    public static XMLStreamReader newXmlStreamReader(Cur c, XmlOptions options) {
        XMLStreamReaderBase xs;
        boolean inner = (options = XmlOptions.maskNull(options)).hasOption("SAVE_INNER") && !options.hasOption("SAVE_OUTER");
        int k = c.kind();
        if (k == 0 || k < 0) {
            xs = new XMLStreamReaderForString(c, c.getChars(-1), c._offSrc, c._cchSrc);
        } else if (inner) {
            if (!c.hasAttrs() && !c.hasChildren()) {
                xs = new XMLStreamReaderForString(c, c.getFirstChars(), c._offSrc, c._cchSrc);
            } else {
                assert (c.isContainer());
                xs = new XMLStreamReaderForNode(c, true);
            }
        } else {
            xs = new XMLStreamReaderForNode(c, false);
        }
        if (c._locale.noSync()) {
            return new UnsyncedJsr173(c._locale, xs);
        }
        return new SyncedJsr173(c._locale, xs);
    }

    private static final class UnsyncedJsr173
    extends Jsr173GateWay
    implements XMLStreamReader,
    Location,
    NamespaceContext {
        public UnsyncedJsr173(Locale l, XMLStreamReaderBase xs) {
            super(l, xs);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public Object getProperty(String name) {
            try {
                this._l.enter();
                Object object = this._xs.getProperty(name);
                return object;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public int next() throws XMLStreamException {
            try {
                this._l.enter();
                int n = this._xs.next();
                return n;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void require(int type, String namespaceURI, String localName) throws XMLStreamException {
            try {
                this._l.enter();
                this._xs.require(type, namespaceURI, localName);
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getElementText() throws XMLStreamException {
            try {
                this._l.enter();
                String string = this._xs.getElementText();
                return string;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public int nextTag() throws XMLStreamException {
            try {
                this._l.enter();
                int n = this._xs.nextTag();
                return n;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean hasNext() throws XMLStreamException {
            try {
                this._l.enter();
                boolean bl = this._xs.hasNext();
                return bl;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void close() throws XMLStreamException {
            try {
                this._l.enter();
                this._xs.close();
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getNamespaceURI(String prefix) {
            try {
                this._l.enter();
                String string = this._xs.getNamespaceURI(prefix);
                return string;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean isStartElement() {
            try {
                this._l.enter();
                boolean bl = this._xs.isStartElement();
                return bl;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean isEndElement() {
            try {
                this._l.enter();
                boolean bl = this._xs.isEndElement();
                return bl;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean isCharacters() {
            try {
                this._l.enter();
                boolean bl = this._xs.isCharacters();
                return bl;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean isWhiteSpace() {
            try {
                this._l.enter();
                boolean bl = this._xs.isWhiteSpace();
                return bl;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getAttributeValue(String namespaceURI, String localName) {
            try {
                this._l.enter();
                String string = this._xs.getAttributeValue(namespaceURI, localName);
                return string;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public int getAttributeCount() {
            try {
                this._l.enter();
                int n = this._xs.getAttributeCount();
                return n;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public QName getAttributeName(int index) {
            try {
                this._l.enter();
                QName qName = this._xs.getAttributeName(index);
                return qName;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getAttributeNamespace(int index) {
            try {
                this._l.enter();
                String string = this._xs.getAttributeNamespace(index);
                return string;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getAttributeLocalName(int index) {
            try {
                this._l.enter();
                String string = this._xs.getAttributeLocalName(index);
                return string;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getAttributePrefix(int index) {
            try {
                this._l.enter();
                String string = this._xs.getAttributePrefix(index);
                return string;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getAttributeType(int index) {
            try {
                this._l.enter();
                String string = this._xs.getAttributeType(index);
                return string;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getAttributeValue(int index) {
            try {
                this._l.enter();
                String string = this._xs.getAttributeValue(index);
                return string;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean isAttributeSpecified(int index) {
            try {
                this._l.enter();
                boolean bl = this._xs.isAttributeSpecified(index);
                return bl;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public int getNamespaceCount() {
            try {
                this._l.enter();
                int n = this._xs.getNamespaceCount();
                return n;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getNamespacePrefix(int index) {
            try {
                this._l.enter();
                String string = this._xs.getNamespacePrefix(index);
                return string;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getNamespaceURI(int index) {
            try {
                this._l.enter();
                String string = this._xs.getNamespaceURI(index);
                return string;
            } finally {
                this._l.exit();
            }
        }

        @Override
        public NamespaceContext getNamespaceContext() {
            return this;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public int getEventType() {
            try {
                this._l.enter();
                int n = this._xs.getEventType();
                return n;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getText() {
            try {
                this._l.enter();
                String string = this._xs.getText();
                return string;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public char[] getTextCharacters() {
            try {
                this._l.enter();
                char[] cArray = this._xs.getTextCharacters();
                return cArray;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public int getTextCharacters(int sourceStart, char[] target, int targetStart, int length) throws XMLStreamException {
            try {
                this._l.enter();
                int n = this._xs.getTextCharacters(sourceStart, target, targetStart, length);
                return n;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public int getTextStart() {
            try {
                this._l.enter();
                int n = this._xs.getTextStart();
                return n;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public int getTextLength() {
            try {
                this._l.enter();
                int n = this._xs.getTextLength();
                return n;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getEncoding() {
            try {
                this._l.enter();
                String string = this._xs.getEncoding();
                return string;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean hasText() {
            try {
                this._l.enter();
                boolean bl = this._xs.hasText();
                return bl;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public Location getLocation() {
            try {
                this._l.enter();
                Location location = this._xs.getLocation();
                return location;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public QName getName() {
            try {
                this._l.enter();
                QName qName = this._xs.getName();
                return qName;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getLocalName() {
            try {
                this._l.enter();
                String string = this._xs.getLocalName();
                return string;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean hasName() {
            try {
                this._l.enter();
                boolean bl = this._xs.hasName();
                return bl;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getNamespaceURI() {
            try {
                this._l.enter();
                String string = this._xs.getNamespaceURI();
                return string;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getPrefix() {
            try {
                this._l.enter();
                String string = this._xs.getPrefix();
                return string;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getVersion() {
            try {
                this._l.enter();
                String string = this._xs.getVersion();
                return string;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean isStandalone() {
            try {
                this._l.enter();
                boolean bl = this._xs.isStandalone();
                return bl;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean standaloneSet() {
            try {
                this._l.enter();
                boolean bl = this._xs.standaloneSet();
                return bl;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getCharacterEncodingScheme() {
            try {
                this._l.enter();
                String string = this._xs.getCharacterEncodingScheme();
                return string;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getPITarget() {
            try {
                this._l.enter();
                String string = this._xs.getPITarget();
                return string;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getPIData() {
            try {
                this._l.enter();
                String string = this._xs.getPIData();
                return string;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getPrefix(String namespaceURI) {
            try {
                this._l.enter();
                String string = this._xs.getPrefix(namespaceURI);
                return string;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public Iterator getPrefixes(String namespaceURI) {
            try {
                this._l.enter();
                Iterator iterator = this._xs.getPrefixes(namespaceURI);
                return iterator;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public int getCharacterOffset() {
            try {
                this._l.enter();
                int n = this._xs.getCharacterOffset();
                return n;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public int getColumnNumber() {
            try {
                this._l.enter();
                int n = this._xs.getColumnNumber();
                return n;
            } finally {
                this._l.exit();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public int getLineNumber() {
            Locale locale = this._l;
            synchronized (locale) {
                int n;
                this._l.enter();
                try {
                    n = this._xs.getLineNumber();
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return n;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        public String getLocationURI() {
            Locale locale = this._l;
            synchronized (locale) {
                String string;
                this._l.enter();
                try {
                    string = this._xs.getLocationURI();
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return string;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getPublicId() {
            Locale locale = this._l;
            synchronized (locale) {
                String string;
                this._l.enter();
                try {
                    string = this._xs.getPublicId();
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return string;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getSystemId() {
            Locale locale = this._l;
            synchronized (locale) {
                String string;
                this._l.enter();
                try {
                    string = this._xs.getSystemId();
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return string;
            }
        }
    }

    private static final class SyncedJsr173
    extends Jsr173GateWay
    implements XMLStreamReader,
    Location,
    NamespaceContext {
        public SyncedJsr173(Locale l, XMLStreamReaderBase xs) {
            super(l, xs);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public Object getProperty(String name) {
            Locale locale = this._l;
            synchronized (locale) {
                Object object;
                this._l.enter();
                try {
                    object = this._xs.getProperty(name);
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return object;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public int next() throws XMLStreamException {
            Locale locale = this._l;
            synchronized (locale) {
                int n;
                this._l.enter();
                try {
                    n = this._xs.next();
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return n;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void require(int type, String namespaceURI, String localName) throws XMLStreamException {
            Locale locale = this._l;
            synchronized (locale) {
                this._l.enter();
                try {
                    this._xs.require(type, namespaceURI, localName);
                } finally {
                    this._l.exit();
                }
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getElementText() throws XMLStreamException {
            Locale locale = this._l;
            synchronized (locale) {
                String string;
                this._l.enter();
                try {
                    string = this._xs.getElementText();
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return string;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public int nextTag() throws XMLStreamException {
            Locale locale = this._l;
            synchronized (locale) {
                int n;
                this._l.enter();
                try {
                    n = this._xs.nextTag();
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return n;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean hasNext() throws XMLStreamException {
            Locale locale = this._l;
            synchronized (locale) {
                boolean bl;
                this._l.enter();
                try {
                    bl = this._xs.hasNext();
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return bl;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void close() throws XMLStreamException {
            Locale locale = this._l;
            synchronized (locale) {
                this._l.enter();
                try {
                    this._xs.close();
                } finally {
                    this._l.exit();
                }
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getNamespaceURI(String prefix) {
            Locale locale = this._l;
            synchronized (locale) {
                String string;
                this._l.enter();
                try {
                    string = this._xs.getNamespaceURI(prefix);
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return string;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean isStartElement() {
            Locale locale = this._l;
            synchronized (locale) {
                boolean bl;
                this._l.enter();
                try {
                    bl = this._xs.isStartElement();
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return bl;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean isEndElement() {
            Locale locale = this._l;
            synchronized (locale) {
                boolean bl;
                this._l.enter();
                try {
                    bl = this._xs.isEndElement();
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return bl;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean isCharacters() {
            Locale locale = this._l;
            synchronized (locale) {
                boolean bl;
                this._l.enter();
                try {
                    bl = this._xs.isCharacters();
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return bl;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean isWhiteSpace() {
            Locale locale = this._l;
            synchronized (locale) {
                boolean bl;
                this._l.enter();
                try {
                    bl = this._xs.isWhiteSpace();
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return bl;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getAttributeValue(String namespaceURI, String localName) {
            Locale locale = this._l;
            synchronized (locale) {
                String string;
                this._l.enter();
                try {
                    string = this._xs.getAttributeValue(namespaceURI, localName);
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return string;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public int getAttributeCount() {
            Locale locale = this._l;
            synchronized (locale) {
                int n;
                this._l.enter();
                try {
                    n = this._xs.getAttributeCount();
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return n;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public QName getAttributeName(int index) {
            Locale locale = this._l;
            synchronized (locale) {
                QName qName;
                this._l.enter();
                try {
                    qName = this._xs.getAttributeName(index);
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return qName;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getAttributeNamespace(int index) {
            Locale locale = this._l;
            synchronized (locale) {
                String string;
                this._l.enter();
                try {
                    string = this._xs.getAttributeNamespace(index);
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return string;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getAttributeLocalName(int index) {
            Locale locale = this._l;
            synchronized (locale) {
                String string;
                this._l.enter();
                try {
                    string = this._xs.getAttributeLocalName(index);
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return string;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getAttributePrefix(int index) {
            Locale locale = this._l;
            synchronized (locale) {
                String string;
                this._l.enter();
                try {
                    string = this._xs.getAttributePrefix(index);
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return string;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getAttributeType(int index) {
            Locale locale = this._l;
            synchronized (locale) {
                String string;
                this._l.enter();
                try {
                    string = this._xs.getAttributeType(index);
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return string;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getAttributeValue(int index) {
            Locale locale = this._l;
            synchronized (locale) {
                String string;
                this._l.enter();
                try {
                    string = this._xs.getAttributeValue(index);
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return string;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean isAttributeSpecified(int index) {
            Locale locale = this._l;
            synchronized (locale) {
                boolean bl;
                this._l.enter();
                try {
                    bl = this._xs.isAttributeSpecified(index);
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return bl;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public int getNamespaceCount() {
            Locale locale = this._l;
            synchronized (locale) {
                int n;
                this._l.enter();
                try {
                    n = this._xs.getNamespaceCount();
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return n;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getNamespacePrefix(int index) {
            Locale locale = this._l;
            synchronized (locale) {
                String string;
                this._l.enter();
                try {
                    string = this._xs.getNamespacePrefix(index);
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return string;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getNamespaceURI(int index) {
            Locale locale = this._l;
            synchronized (locale) {
                String string;
                this._l.enter();
                try {
                    string = this._xs.getNamespaceURI(index);
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return string;
            }
        }

        @Override
        public NamespaceContext getNamespaceContext() {
            return this;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public int getEventType() {
            Locale locale = this._l;
            synchronized (locale) {
                int n;
                this._l.enter();
                try {
                    n = this._xs.getEventType();
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return n;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getText() {
            Locale locale = this._l;
            synchronized (locale) {
                String string;
                this._l.enter();
                try {
                    string = this._xs.getText();
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return string;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public char[] getTextCharacters() {
            Locale locale = this._l;
            synchronized (locale) {
                char[] cArray;
                this._l.enter();
                try {
                    cArray = this._xs.getTextCharacters();
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return cArray;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public int getTextCharacters(int sourceStart, char[] target, int targetStart, int length) throws XMLStreamException {
            Locale locale = this._l;
            synchronized (locale) {
                int n;
                this._l.enter();
                try {
                    n = this._xs.getTextCharacters(sourceStart, target, targetStart, length);
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return n;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public int getTextStart() {
            Locale locale = this._l;
            synchronized (locale) {
                int n;
                this._l.enter();
                try {
                    n = this._xs.getTextStart();
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return n;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public int getTextLength() {
            Locale locale = this._l;
            synchronized (locale) {
                int n;
                this._l.enter();
                try {
                    n = this._xs.getTextLength();
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return n;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getEncoding() {
            Locale locale = this._l;
            synchronized (locale) {
                String string;
                this._l.enter();
                try {
                    string = this._xs.getEncoding();
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return string;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean hasText() {
            Locale locale = this._l;
            synchronized (locale) {
                boolean bl;
                this._l.enter();
                try {
                    bl = this._xs.hasText();
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return bl;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public Location getLocation() {
            Locale locale = this._l;
            synchronized (locale) {
                Location location;
                this._l.enter();
                try {
                    location = this._xs.getLocation();
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return location;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public QName getName() {
            Locale locale = this._l;
            synchronized (locale) {
                QName qName;
                this._l.enter();
                try {
                    qName = this._xs.getName();
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return qName;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getLocalName() {
            Locale locale = this._l;
            synchronized (locale) {
                String string;
                this._l.enter();
                try {
                    string = this._xs.getLocalName();
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return string;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean hasName() {
            Locale locale = this._l;
            synchronized (locale) {
                boolean bl;
                this._l.enter();
                try {
                    bl = this._xs.hasName();
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return bl;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getNamespaceURI() {
            Locale locale = this._l;
            synchronized (locale) {
                String string;
                this._l.enter();
                try {
                    string = this._xs.getNamespaceURI();
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return string;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getPrefix() {
            Locale locale = this._l;
            synchronized (locale) {
                String string;
                this._l.enter();
                try {
                    string = this._xs.getPrefix();
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return string;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getVersion() {
            Locale locale = this._l;
            synchronized (locale) {
                String string;
                this._l.enter();
                try {
                    string = this._xs.getVersion();
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return string;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean isStandalone() {
            Locale locale = this._l;
            synchronized (locale) {
                boolean bl;
                this._l.enter();
                try {
                    bl = this._xs.isStandalone();
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return bl;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean standaloneSet() {
            Locale locale = this._l;
            synchronized (locale) {
                boolean bl;
                this._l.enter();
                try {
                    bl = this._xs.standaloneSet();
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return bl;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getCharacterEncodingScheme() {
            Locale locale = this._l;
            synchronized (locale) {
                String string;
                this._l.enter();
                try {
                    string = this._xs.getCharacterEncodingScheme();
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return string;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getPITarget() {
            Locale locale = this._l;
            synchronized (locale) {
                String string;
                this._l.enter();
                try {
                    string = this._xs.getPITarget();
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return string;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getPIData() {
            Locale locale = this._l;
            synchronized (locale) {
                String string;
                this._l.enter();
                try {
                    string = this._xs.getPIData();
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return string;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getPrefix(String namespaceURI) {
            Locale locale = this._l;
            synchronized (locale) {
                String string;
                this._l.enter();
                try {
                    string = this._xs.getPrefix(namespaceURI);
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return string;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public Iterator getPrefixes(String namespaceURI) {
            Locale locale = this._l;
            synchronized (locale) {
                Iterator iterator;
                this._l.enter();
                try {
                    iterator = this._xs.getPrefixes(namespaceURI);
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return iterator;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public int getCharacterOffset() {
            Locale locale = this._l;
            synchronized (locale) {
                int n;
                this._l.enter();
                try {
                    n = this._xs.getCharacterOffset();
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return n;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public int getColumnNumber() {
            Locale locale = this._l;
            synchronized (locale) {
                int n;
                this._l.enter();
                try {
                    n = this._xs.getColumnNumber();
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return n;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public int getLineNumber() {
            Locale locale = this._l;
            synchronized (locale) {
                int n;
                this._l.enter();
                try {
                    n = this._xs.getLineNumber();
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return n;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        public String getLocationURI() {
            Locale locale = this._l;
            synchronized (locale) {
                String string;
                this._l.enter();
                try {
                    string = this._xs.getLocationURI();
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return string;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getPublicId() {
            Locale locale = this._l;
            synchronized (locale) {
                String string;
                this._l.enter();
                try {
                    string = this._xs.getPublicId();
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return string;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getSystemId() {
            Locale locale = this._l;
            synchronized (locale) {
                String string;
                this._l.enter();
                try {
                    string = this._xs.getSystemId();
                    this._l.exit();
                } catch (Throwable throwable) {
                    this._l.exit();
                    throw throwable;
                }
                return string;
            }
        }
    }

    private static abstract class Jsr173GateWay {
        Locale _l;
        XMLStreamReaderBase _xs;

        public Jsr173GateWay(Locale l, XMLStreamReaderBase xs) {
            this._l = l;
            this._xs = xs;
        }
    }

    private static final class XMLStreamReaderForString
    extends XMLStreamReaderBase {
        private Cur _cur;
        private Object _src;
        private int _off;
        private int _cch;

        XMLStreamReaderForString(Cur c, Object src, int off, int cch) {
            super(c);
            this._src = src;
            this._off = off;
            this._cch = cch;
            this._cur = c;
        }

        @Override
        protected Cur getStreamCur() {
            return this._cur;
        }

        @Override
        public String getText() {
            this.checkChanged();
            return CharUtil.getString(this._src, this._off, this._cch);
        }

        @Override
        public char[] getTextCharacters() {
            this.checkChanged();
            char[] chars = new char[this._cch];
            CharUtil.getChars(chars, 0, this._src, this._off, this._cch);
            return chars;
        }

        @Override
        public int getTextStart() {
            this.checkChanged();
            return this._off;
        }

        @Override
        public int getTextLength() {
            this.checkChanged();
            return this._cch;
        }

        @Override
        public int getTextCharacters(int sourceStart, char[] target, int targetStart, int length) {
            this.checkChanged();
            if (length < 0) {
                throw new IndexOutOfBoundsException();
            }
            if (sourceStart > this._cch) {
                throw new IndexOutOfBoundsException();
            }
            if (sourceStart + length > this._cch) {
                length = this._cch - sourceStart;
            }
            CharUtil.getChars(target, targetStart, this._src, this._off + sourceStart, length);
            return length;
        }

        @Override
        public int getEventType() {
            this.checkChanged();
            return 4;
        }

        @Override
        public boolean hasName() {
            this.checkChanged();
            return false;
        }

        @Override
        public boolean hasNext() {
            this.checkChanged();
            return false;
        }

        @Override
        public boolean hasText() {
            this.checkChanged();
            return true;
        }

        @Override
        public boolean isCharacters() {
            this.checkChanged();
            return true;
        }

        @Override
        public boolean isEndElement() {
            this.checkChanged();
            return false;
        }

        @Override
        public boolean isStartElement() {
            this.checkChanged();
            return false;
        }

        @Override
        public int getAttributeCount() {
            throw new IllegalStateException();
        }

        @Override
        public String getAttributeLocalName(int index) {
            throw new IllegalStateException();
        }

        @Override
        public QName getAttributeName(int index) {
            throw new IllegalStateException();
        }

        @Override
        public String getAttributeNamespace(int index) {
            throw new IllegalStateException();
        }

        @Override
        public String getAttributePrefix(int index) {
            throw new IllegalStateException();
        }

        @Override
        public String getAttributeType(int index) {
            throw new IllegalStateException();
        }

        @Override
        public String getAttributeValue(int index) {
            throw new IllegalStateException();
        }

        @Override
        public String getAttributeValue(String namespaceURI, String localName) {
            throw new IllegalStateException();
        }

        @Override
        public String getElementText() {
            throw new IllegalStateException();
        }

        @Override
        public String getLocalName() {
            throw new IllegalStateException();
        }

        @Override
        public QName getName() {
            throw new IllegalStateException();
        }

        @Override
        public int getNamespaceCount() {
            throw new IllegalStateException();
        }

        @Override
        public String getNamespacePrefix(int index) {
            throw new IllegalStateException();
        }

        @Override
        public String getNamespaceURI(int index) {
            throw new IllegalStateException();
        }

        @Override
        public String getNamespaceURI() {
            throw new IllegalStateException();
        }

        @Override
        public String getPIData() {
            throw new IllegalStateException();
        }

        @Override
        public String getPITarget() {
            throw new IllegalStateException();
        }

        @Override
        public String getPrefix() {
            throw new IllegalStateException();
        }

        @Override
        public boolean isAttributeSpecified(int index) {
            throw new IllegalStateException();
        }

        @Override
        public int next() {
            throw new IllegalStateException();
        }

        @Override
        public int nextTag() {
            throw new IllegalStateException();
        }

        @Override
        public String getPublicId() {
            throw new IllegalStateException();
        }

        @Override
        public String getSystemId() {
            throw new IllegalStateException();
        }
    }

    private static abstract class XMLStreamReaderBase
    implements XMLStreamReader,
    NamespaceContext,
    Location {
        private Locale _locale;
        private long _version;
        String _uri;
        int _line = -1;
        int _column = -1;
        int _offset = -1;

        XMLStreamReaderBase(Cur c) {
            this._locale = c._locale;
            this._version = this._locale.version();
        }

        protected final void checkChanged() {
            if (this._version != this._locale.version()) {
                throw new ConcurrentModificationException("Document changed while streaming");
            }
        }

        @Override
        public void close() throws XMLStreamException {
            this.checkChanged();
        }

        @Override
        public boolean isWhiteSpace() {
            this.checkChanged();
            String s = this.getText();
            return this._locale.getCharUtil().isWhiteSpace(s, 0, s.length());
        }

        @Override
        public Location getLocation() {
            this.checkChanged();
            Cur c = this.getStreamCur();
            XmlLineNumber ln = (XmlLineNumber)c.getBookmark(XmlLineNumber.class);
            this._uri = null;
            if (ln != null) {
                this._line = ln.getLine();
                this._column = ln.getColumn();
                this._offset = ln.getOffset();
            } else {
                this._line = -1;
                this._column = -1;
                this._offset = -1;
            }
            return this;
        }

        @Override
        public Object getProperty(String name) {
            this.checkChanged();
            if (name == null) {
                throw new IllegalArgumentException("Property name is null");
            }
            return null;
        }

        @Override
        public String getCharacterEncodingScheme() {
            this.checkChanged();
            XmlDocumentProperties props = Locale.getDocProps(this.getStreamCur(), false);
            return props == null ? null : props.getEncoding();
        }

        @Override
        public String getEncoding() {
            return null;
        }

        @Override
        public String getVersion() {
            this.checkChanged();
            XmlDocumentProperties props = Locale.getDocProps(this.getStreamCur(), false);
            return props == null ? null : props.getVersion();
        }

        @Override
        public boolean isStandalone() {
            this.checkChanged();
            XmlDocumentProperties props = Locale.getDocProps(this.getStreamCur(), false);
            return props == null ? false : props.getStandalone();
        }

        @Override
        public boolean standaloneSet() {
            this.checkChanged();
            return false;
        }

        @Override
        public void require(int type, String namespaceURI, String localName) throws XMLStreamException {
            this.checkChanged();
            if (type != this.getEventType()) {
                throw new XMLStreamException();
            }
            if (namespaceURI != null && !this.getNamespaceURI().equals(namespaceURI)) {
                throw new XMLStreamException();
            }
            if (localName != null && !this.getLocalName().equals(localName)) {
                throw new XMLStreamException();
            }
        }

        @Override
        public int getCharacterOffset() {
            return this._offset;
        }

        @Override
        public int getColumnNumber() {
            return this._column;
        }

        @Override
        public int getLineNumber() {
            return this._line;
        }

        public String getLocationURI() {
            return this._uri;
        }

        @Override
        public String getPublicId() {
            return null;
        }

        @Override
        public String getSystemId() {
            return null;
        }

        @Override
        public NamespaceContext getNamespaceContext() {
            throw new RuntimeException("This version of getNamespaceContext should not be called");
        }

        @Override
        public String getNamespaceURI(String prefix) {
            this.checkChanged();
            Cur c = this.getStreamCur();
            c.push();
            if (!c.isContainer()) {
                c.toParent();
            }
            String ns = c.namespaceForPrefix(prefix, true);
            c.pop();
            return ns;
        }

        @Override
        public String getPrefix(String namespaceURI) {
            this.checkChanged();
            Cur c = this.getStreamCur();
            c.push();
            if (!c.isContainer()) {
                c.toParent();
            }
            String prefix = c.prefixForNamespace(namespaceURI, null, false);
            c.pop();
            return prefix;
        }

        @Override
        public Iterator getPrefixes(String namespaceURI) {
            this.checkChanged();
            HashMap<String, String> map = new HashMap<String, String>();
            map.put(namespaceURI, this.getPrefix(namespaceURI));
            return map.values().iterator();
        }

        protected abstract Cur getStreamCur();
    }

    private static final class XMLStreamReaderForNode
    extends XMLStreamReaderBase {
        private boolean _wholeDoc;
        private boolean _done;
        private Cur _cur;
        private Cur _end;
        private boolean _srcFetched;
        private Object _src;
        private int _offSrc;
        private int _cchSrc;
        private boolean _textFetched;
        private char[] _chars;
        private int _offChars;
        private int _cchChars;

        public XMLStreamReaderForNode(Cur c, boolean inner) {
            super(c);
            assert (c.isContainer() || c.isComment() || c.isProcinst() || c.isAttr());
            if (inner) {
                assert (c.isContainer());
                this._cur = c.weakCur(this);
                if (!this._cur.toFirstAttr()) {
                    this._cur.next();
                }
                this._end = c.weakCur(this);
                this._end.toEnd();
            } else {
                this._cur = c.weakCur(this);
                if (c.isRoot()) {
                    this._wholeDoc = true;
                } else {
                    this._end = c.weakCur(this);
                    if (c.isAttr()) {
                        if (!this._end.toNextAttr()) {
                            this._end.toParent();
                            this._end.next();
                        }
                    } else {
                        this._end.skip();
                    }
                }
            }
            if (!this._wholeDoc) {
                this._cur.push();
                try {
                    this.next();
                } catch (XMLStreamException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
                this._cur.pop();
            }
            assert (this._wholeDoc || !this._cur.isSamePos(this._end));
        }

        @Override
        protected Cur getStreamCur() {
            return this._cur;
        }

        @Override
        public boolean hasNext() throws XMLStreamException {
            this.checkChanged();
            return !this._done;
        }

        @Override
        public int getEventType() {
            switch (this._cur.kind()) {
                case 1: {
                    return 7;
                }
                case -1: {
                    return 8;
                }
                case 2: {
                    return 1;
                }
                case -2: {
                    return 2;
                }
                case 3: {
                    return this._cur.isXmlns() ? 13 : 10;
                }
                case 0: {
                    return 4;
                }
                case 4: {
                    return 5;
                }
                case 5: {
                    return 3;
                }
            }
            throw new IllegalStateException();
        }

        @Override
        public int next() throws XMLStreamException {
            this.checkChanged();
            if (!this.hasNext()) {
                throw new IllegalStateException("No next event in stream");
            }
            int kind = this._cur.kind();
            if (kind == -1) {
                assert (this._wholeDoc);
                this._done = true;
            } else {
                if (kind == 3) {
                    if (!this._cur.toNextAttr()) {
                        this._cur.toParent();
                        this._cur.next();
                    }
                } else if (kind == 4 || kind == 5) {
                    this._cur.skip();
                } else if (kind == 1) {
                    if (!this._cur.toFirstAttr()) {
                        this._cur.next();
                    }
                } else {
                    this._cur.next();
                }
                assert (this._wholeDoc || this._end != null);
                this._done = this._wholeDoc ? this._cur.kind() == -1 : this._cur.isSamePos(this._end);
            }
            this._textFetched = false;
            this._srcFetched = false;
            return this.getEventType();
        }

        @Override
        public String getText() {
            this.checkChanged();
            int k = this._cur.kind();
            if (k == 4) {
                return this._cur.getValueAsString();
            }
            if (k == 0) {
                return this._cur.getCharsAsString(-1);
            }
            throw new IllegalStateException();
        }

        @Override
        public boolean isStartElement() {
            return this.getEventType() == 1;
        }

        @Override
        public boolean isEndElement() {
            return this.getEventType() == 2;
        }

        @Override
        public boolean isCharacters() {
            return this.getEventType() == 4;
        }

        @Override
        public String getElementText() throws XMLStreamException {
            this.checkChanged();
            if (!this.isStartElement()) {
                throw new IllegalStateException();
            }
            StringBuffer sb = new StringBuffer();
            while (true) {
                if (!this.hasNext()) {
                    throw new XMLStreamException();
                }
                int e = this.next();
                if (e == 2) break;
                if (e == 1) {
                    throw new XMLStreamException();
                }
                if (e == 5 || e == 3) continue;
                sb.append(this.getText());
            }
            return sb.toString();
        }

        @Override
        public int nextTag() throws XMLStreamException {
            this.checkChanged();
            while (!this.isStartElement() && !this.isEndElement()) {
                if (!this.isWhiteSpace()) {
                    throw new XMLStreamException();
                }
                if (!this.hasNext()) {
                    throw new XMLStreamException();
                }
                this.next();
            }
            return this.getEventType();
        }

        private static boolean matchAttr(Cur c, String uri, String local) {
            assert (c.isNormalAttr());
            QName name = c.getName();
            return name.getLocalPart().equals(local) && (uri == null || name.getNamespaceURI().equals(uri));
        }

        private static Cur toAttr(Cur c, String uri, String local) {
            if (uri == null || local == null || local.length() == 0) {
                throw new IllegalArgumentException();
            }
            Cur ca = c.tempCur();
            boolean match = false;
            if (c.isElem()) {
                if (ca.toFirstAttr()) {
                    do {
                        if (!ca.isNormalAttr() || !XMLStreamReaderForNode.matchAttr(ca, uri, local)) continue;
                        match = true;
                        break;
                    } while (ca.toNextSibling());
                }
            } else if (c.isNormalAttr()) {
                match = XMLStreamReaderForNode.matchAttr(c, uri, local);
            } else {
                throw new IllegalStateException();
            }
            if (!match) {
                ca.release();
                ca = null;
            }
            return ca;
        }

        @Override
        public String getAttributeValue(String uri, String local) {
            Cur ca = XMLStreamReaderForNode.toAttr(this._cur, uri, local);
            String value = null;
            if (ca != null) {
                value = ca.getValueAsString();
                ca.release();
            }
            return value;
        }

        private static Cur toAttr(Cur c, int i) {
            if (i < 0) {
                throw new IndexOutOfBoundsException("Attribute index is negative");
            }
            Cur ca = c.tempCur();
            boolean match = false;
            if (c.isElem()) {
                if (ca.toFirstAttr()) {
                    do {
                        if (!ca.isNormalAttr() || i-- != 0) continue;
                        match = true;
                        break;
                    } while (ca.toNextSibling());
                }
            } else if (c.isNormalAttr()) {
                match = i == 0;
            } else {
                throw new IllegalStateException();
            }
            if (!match) {
                ca.release();
                throw new IndexOutOfBoundsException("Attribute index is too large");
            }
            return ca;
        }

        @Override
        public int getAttributeCount() {
            int n = 0;
            if (this._cur.isElem()) {
                Cur ca = this._cur.tempCur();
                if (ca.toFirstAttr()) {
                    do {
                        if (!ca.isNormalAttr()) continue;
                        ++n;
                    } while (ca.toNextSibling());
                }
                ca.release();
            } else if (this._cur.isNormalAttr()) {
                ++n;
            } else {
                throw new IllegalStateException();
            }
            return n;
        }

        @Override
        public QName getAttributeName(int index) {
            Cur ca = XMLStreamReaderForNode.toAttr(this._cur, index);
            QName name = ca.getName();
            ca.release();
            return name;
        }

        @Override
        public String getAttributeNamespace(int index) {
            return this.getAttributeName(index).getNamespaceURI();
        }

        @Override
        public String getAttributeLocalName(int index) {
            return this.getAttributeName(index).getLocalPart();
        }

        @Override
        public String getAttributePrefix(int index) {
            return this.getAttributeName(index).getPrefix();
        }

        @Override
        public String getAttributeType(int index) {
            XMLStreamReaderForNode.toAttr(this._cur, index).release();
            return "CDATA";
        }

        @Override
        public String getAttributeValue(int index) {
            Cur ca = XMLStreamReaderForNode.toAttr(this._cur, index);
            String value = null;
            if (ca != null) {
                value = ca.getValueAsString();
                ca.release();
            }
            return value;
        }

        @Override
        public boolean isAttributeSpecified(int index) {
            Cur ca = XMLStreamReaderForNode.toAttr(this._cur, index);
            ca.release();
            return false;
        }

        @Override
        public int getNamespaceCount() {
            int n = 0;
            if (this._cur.isElem() || this._cur.kind() == -2) {
                Cur ca = this._cur.tempCur();
                if (this._cur.kind() == -2) {
                    ca.toParent();
                }
                if (ca.toFirstAttr()) {
                    do {
                        if (!ca.isXmlns()) continue;
                        ++n;
                    } while (ca.toNextSibling());
                }
                ca.release();
            } else if (this._cur.isXmlns()) {
                ++n;
            } else {
                throw new IllegalStateException();
            }
            return n;
        }

        private static Cur toXmlns(Cur c, int i) {
            if (i < 0) {
                throw new IndexOutOfBoundsException("Namespace index is negative");
            }
            Cur ca = c.tempCur();
            boolean match = false;
            if (c.isElem() || c.kind() == -2) {
                if (c.kind() == -2) {
                    ca.toParent();
                }
                if (ca.toFirstAttr()) {
                    do {
                        if (!ca.isXmlns() || i-- != 0) continue;
                        match = true;
                        break;
                    } while (ca.toNextSibling());
                }
            } else if (c.isXmlns()) {
                match = i == 0;
            } else {
                throw new IllegalStateException();
            }
            if (!match) {
                ca.release();
                throw new IndexOutOfBoundsException("Namespace index is too large");
            }
            return ca;
        }

        @Override
        public String getNamespacePrefix(int index) {
            Cur ca = XMLStreamReaderForNode.toXmlns(this._cur, index);
            String prefix = ca.getXmlnsPrefix();
            ca.release();
            return prefix;
        }

        @Override
        public String getNamespaceURI(int index) {
            Cur ca = XMLStreamReaderForNode.toXmlns(this._cur, index);
            String uri = ca.getXmlnsUri();
            ca.release();
            return uri;
        }

        private void fetchChars() {
            if (!this._textFetched) {
                int k = this._cur.kind();
                Cur cText = null;
                if (k == 4) {
                    cText = this._cur.tempCur();
                    cText.next();
                } else if (k == 0) {
                    cText = this._cur;
                } else {
                    throw new IllegalStateException();
                }
                Object src = cText.getChars(-1);
                this.ensureCharBufLen(cText._cchSrc);
                this._offChars = 0;
                this._cchChars = cText._cchSrc;
                CharUtil.getChars(this._chars, 0, src, cText._offSrc, this._cchChars);
                if (cText != this._cur) {
                    cText.release();
                }
                this._textFetched = true;
            }
        }

        private void ensureCharBufLen(int cch) {
            if (this._chars == null || this._chars.length < cch) {
                int l;
                for (l = 256; l < cch; l *= 2) {
                }
                this._chars = new char[l];
            }
        }

        @Override
        public char[] getTextCharacters() {
            this.checkChanged();
            this.fetchChars();
            return this._chars;
        }

        @Override
        public int getTextStart() {
            this.checkChanged();
            this.fetchChars();
            return this._offChars;
        }

        @Override
        public int getTextLength() {
            this.checkChanged();
            this.fetchChars();
            return this._cchChars;
        }

        @Override
        public int getTextCharacters(int sourceStart, char[] target, int targetStart, int length) throws XMLStreamException {
            if (length < 0) {
                throw new IndexOutOfBoundsException();
            }
            if (targetStart < 0 || targetStart >= target.length) {
                throw new IndexOutOfBoundsException();
            }
            if (targetStart + length > target.length) {
                throw new IndexOutOfBoundsException();
            }
            if (!this._srcFetched) {
                int k = this._cur.kind();
                Cur cText = null;
                if (k == 4) {
                    cText = this._cur.tempCur();
                    cText.next();
                } else if (k == 0) {
                    cText = this._cur;
                } else {
                    throw new IllegalStateException();
                }
                this._src = cText.getChars(-1);
                this._offSrc = cText._offSrc;
                this._cchSrc = cText._cchSrc;
                if (cText != this._cur) {
                    cText.release();
                }
                this._srcFetched = true;
            }
            if (sourceStart > this._cchSrc) {
                throw new IndexOutOfBoundsException();
            }
            if (sourceStart + length > this._cchSrc) {
                length = this._cchSrc - sourceStart;
            }
            CharUtil.getChars(target, targetStart, this._src, this._offSrc, length);
            return length;
        }

        @Override
        public boolean hasText() {
            int k = this._cur.kind();
            return k == 4 || k == 0;
        }

        @Override
        public boolean hasName() {
            int k = this._cur.kind();
            return k == 2 || k == -2;
        }

        @Override
        public QName getName() {
            if (!this.hasName()) {
                throw new IllegalStateException();
            }
            return this._cur.getName();
        }

        @Override
        public String getNamespaceURI() {
            return this.getName().getNamespaceURI();
        }

        @Override
        public String getLocalName() {
            return this.getName().getLocalPart();
        }

        @Override
        public String getPrefix() {
            return this.getName().getPrefix();
        }

        @Override
        public String getPITarget() {
            return this._cur.kind() == 5 ? this._cur.getName().getLocalPart() : null;
        }

        @Override
        public String getPIData() {
            return this._cur.kind() == 5 ? this._cur.getValueAsString() : null;
        }
    }
}

