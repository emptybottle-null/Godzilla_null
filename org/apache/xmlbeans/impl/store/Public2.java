/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.store;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.QNameSet;
import org.apache.xmlbeans.SchemaField;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.store.Cur;
import org.apache.xmlbeans.impl.store.Cursor;
import org.apache.xmlbeans.impl.store.DomImpl;
import org.apache.xmlbeans.impl.store.Jsr173;
import org.apache.xmlbeans.impl.store.Locale;
import org.apache.xmlbeans.impl.store.Path;
import org.apache.xmlbeans.impl.store.Saaj;
import org.apache.xmlbeans.impl.store.Saver;
import org.apache.xmlbeans.impl.store.Xobj;
import org.apache.xmlbeans.impl.values.NamespaceManager;
import org.apache.xmlbeans.impl.values.TypeStore;
import org.apache.xmlbeans.impl.values.TypeStoreUser;
import org.apache.xmlbeans.impl.values.TypeStoreVisitor;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public final class Public2 {
    private static Locale newLocale(Saaj saaj) {
        XmlOptions options = null;
        if (saaj != null) {
            options = new XmlOptions();
            options.put((Object)"SAAJ_IMPL", saaj);
        }
        return Locale.getLocale(null, options);
    }

    private static Locale newLocale() {
        return Locale.getLocale(null, null);
    }

    public static void setSync(Document doc, boolean sync) {
        assert (doc instanceof DomImpl.Dom);
        Locale l = ((DomImpl.Dom)((Object)doc)).locale();
        l._noSync = !sync;
    }

    public static String compilePath(String path, XmlOptions options) {
        return Path.compilePath(path, options);
    }

    public static DOMImplementation getDomImplementation() {
        return Public2.newLocale();
    }

    public static DOMImplementation getDomImplementation(Saaj saaj) {
        return Public2.newLocale(saaj);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Document parse(String s) throws XmlException {
        DomImpl.Dom d;
        Locale l = Public2.newLocale();
        if (l.noSync()) {
            l.enter();
            try {
                d = l.load(s);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                d = l.load(s);
            } finally {
                l.exit();
            }
        }
        return (Document)((Object)d);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Document parse(String s, XmlOptions options) throws XmlException {
        DomImpl.Dom d;
        Locale l = Public2.newLocale();
        if (l.noSync()) {
            l.enter();
            try {
                d = l.load(s, options);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                d = l.load(s, options);
            } finally {
                l.exit();
            }
        }
        return (Document)((Object)d);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Document parse(String s, Saaj saaj) throws XmlException {
        DomImpl.Dom d;
        Locale l = Public2.newLocale(saaj);
        if (l.noSync()) {
            l.enter();
            try {
                d = l.load(s);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                d = l.load(s);
            } finally {
                l.exit();
            }
        }
        return (Document)((Object)d);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Document parse(InputStream is, XmlOptions options) throws XmlException, IOException {
        DomImpl.Dom d;
        Locale l = Public2.newLocale();
        if (l.noSync()) {
            l.enter();
            try {
                d = l.load(is, options);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                d = l.load(is, options);
            } finally {
                l.exit();
            }
        }
        return (Document)((Object)d);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Document parse(InputStream is, Saaj saaj) throws XmlException, IOException {
        DomImpl.Dom d;
        Locale l = Public2.newLocale(saaj);
        if (l.noSync()) {
            l.enter();
            try {
                d = l.load(is);
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            l.enter();
            try {
                d = l.load(is);
            } finally {
                l.exit();
            }
        }
        return (Document)((Object)d);
    }

    public static Node getNode(XMLStreamReader s) {
        return Jsr173.nodeFromStream(s);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static XMLStreamReader getStream(Node n) {
        assert (n instanceof DomImpl.Dom);
        DomImpl.Dom d = (DomImpl.Dom)((Object)n);
        Locale l = d.locale();
        if (l.noSync()) {
            l.enter();
            try {
                XMLStreamReader xMLStreamReader = DomImpl.getXmlStreamReader(d);
                return xMLStreamReader;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            XMLStreamReader xMLStreamReader;
            l.enter();
            try {
                xMLStreamReader = DomImpl.getXmlStreamReader(d);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return xMLStreamReader;
        }
    }

    public static String save(Node n) {
        return Public2.save(n, null);
    }

    public static void save(Node n, OutputStream os, XmlOptions options) throws IOException {
        XmlCursor c = Public2.getCursor(n);
        c.save(os, options);
        c.dispose();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static String save(Node n, XmlOptions options) {
        assert (n instanceof DomImpl.Dom);
        DomImpl.Dom d = (DomImpl.Dom)((Object)n);
        Locale l = d.locale();
        if (l.noSync()) {
            l.enter();
            try {
                String string = Public2.saveImpl(d, options);
                return string;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            String string;
            l.enter();
            try {
                string = Public2.saveImpl(d, options);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return string;
        }
    }

    private static String saveImpl(DomImpl.Dom d, XmlOptions options) {
        Cur c = d.tempCur();
        String s = new Saver.TextSaver(c, options, null).saveToString();
        c.release();
        return s;
    }

    public static String save(XmlCursor c) {
        return Public2.save(c, null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static String save(XmlCursor xc, XmlOptions options) {
        Cursor cursor = (Cursor)xc;
        Locale l = cursor.locale();
        if (l.noSync()) {
            l.enter();
            try {
                String string = Public2.saveImpl(cursor, options);
                return string;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            String string;
            l.enter();
            try {
                string = Public2.saveImpl(cursor, options);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return string;
        }
    }

    private static String saveImpl(Cursor cursor, XmlOptions options) {
        Cur c = cursor.tempCur();
        String s = new Saver.TextSaver(c, options, null).saveToString();
        c.release();
        return s;
    }

    public static XmlCursor newStore() {
        return Public2.newStore(null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static XmlCursor newStore(Saaj saaj) {
        Locale l = Public2.newLocale(saaj);
        if (l.noSync()) {
            l.enter();
            try {
                XmlCursor xmlCursor = Public2._newStore(l);
                return xmlCursor;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            XmlCursor xmlCursor;
            l.enter();
            try {
                xmlCursor = Public2._newStore(l);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return xmlCursor;
        }
    }

    public static XmlCursor _newStore(Locale l) {
        Cur c = l.tempCur();
        c.createRoot();
        Cursor cursor = new Cursor(c);
        c.release();
        return cursor;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static XmlCursor getCursor(Node n) {
        assert (n instanceof DomImpl.Dom);
        DomImpl.Dom d = (DomImpl.Dom)((Object)n);
        Locale l = d.locale();
        if (l.noSync()) {
            l.enter();
            try {
                XmlCursor xmlCursor = DomImpl.getXmlCursor(d);
                return xmlCursor;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            XmlCursor xmlCursor;
            l.enter();
            try {
                xmlCursor = DomImpl.getXmlCursor(d);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return xmlCursor;
        }
    }

    public static void dump(PrintStream o, DomImpl.Dom d) {
        d.dump(o);
    }

    public static void dump(PrintStream o, Node n) {
        Public2.dump(o, (DomImpl.Dom)((Object)n));
    }

    public static void dump(PrintStream o, XmlCursor c) {
        ((Cursor)c).dump(o);
    }

    public static void dump(PrintStream o, XmlObject x) {
        XmlCursor xc = x.newCursor();
        Node n = xc.getDomNode();
        DomImpl.Dom d = (DomImpl.Dom)((Object)n);
        xc.dispose();
        Public2.dump(o, d);
    }

    public static void dump(DomImpl.Dom d) {
        Public2.dump(System.out, d);
    }

    public static void dump(Node n) {
        Public2.dump(System.out, n);
    }

    public static void dump(XmlCursor c) {
        Public2.dump(System.out, c);
    }

    public static void dump(XmlObject x) {
        Public2.dump(System.out, x);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void test() throws Exception {
        Xobj x = (Xobj)((Object)Public2.parse("<a>XY</a>"));
        Locale l = x._locale;
        l.enter();
        try {
            Cur c = x.tempCur();
            c.next();
            Cur c2 = c.tempCur();
            c2.next();
            Cur c3 = c2.tempCur();
            c3.nextChars(1);
            Cur c4 = c3.tempCur();
            c4.nextChars(1);
            c.dump();
            c.moveNodeContents(c, true);
            c.dump();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            l.exit();
        }
    }

    private static class TestTypeStoreUser
    implements TypeStoreUser {
        private String _value;

        TestTypeStoreUser(String value) {
            this._value = value;
        }

        @Override
        public void attach_store(TypeStore store) {
        }

        @Override
        public SchemaType get_schema_type() {
            throw new RuntimeException("Not impl");
        }

        @Override
        public TypeStore get_store() {
            throw new RuntimeException("Not impl");
        }

        @Override
        public void invalidate_value() {
        }

        @Override
        public boolean uses_invalidate_value() {
            throw new RuntimeException("Not impl");
        }

        @Override
        public String build_text(NamespaceManager nsm) {
            return this._value;
        }

        @Override
        public boolean build_nil() {
            throw new RuntimeException("Not impl");
        }

        @Override
        public void invalidate_nilvalue() {
            throw new RuntimeException("Not impl");
        }

        @Override
        public void invalidate_element_order() {
            throw new RuntimeException("Not impl");
        }

        @Override
        public void validate_now() {
            throw new RuntimeException("Not impl");
        }

        @Override
        public void disconnect_store() {
            throw new RuntimeException("Not impl");
        }

        @Override
        public TypeStoreUser create_element_user(QName eltName, QName xsiType) {
            return new TestTypeStoreUser("ELEM");
        }

        @Override
        public TypeStoreUser create_attribute_user(QName attrName) {
            throw new RuntimeException("Not impl");
        }

        @Override
        public String get_default_element_text(QName eltName) {
            throw new RuntimeException("Not impl");
        }

        @Override
        public String get_default_attribute_text(QName attrName) {
            throw new RuntimeException("Not impl");
        }

        @Override
        public SchemaType get_element_type(QName eltName, QName xsiType) {
            throw new RuntimeException("Not impl");
        }

        @Override
        public SchemaType get_attribute_type(QName attrName) {
            throw new RuntimeException("Not impl");
        }

        @Override
        public int get_elementflags(QName eltName) {
            throw new RuntimeException("Not impl");
        }

        @Override
        public int get_attributeflags(QName attrName) {
            throw new RuntimeException("Not impl");
        }

        @Override
        public SchemaField get_attribute_field(QName attrName) {
            throw new RuntimeException("Not impl");
        }

        @Override
        public boolean is_child_element_order_sensitive() {
            throw new RuntimeException("Not impl");
        }

        @Override
        public QNameSet get_element_ending_delimiters(QName eltname) {
            throw new RuntimeException("Not impl");
        }

        @Override
        public TypeStoreVisitor new_visitor() {
            throw new RuntimeException("Not impl");
        }
    }
}

