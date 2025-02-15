/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STUnsignedShortHex;
import org.w3c.dom.Node;

public interface CTWorkbookProtection
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTWorkbookProtection.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctworkbookprotection56bctype");

    public byte[] getWorkbookPassword();

    public STUnsignedShortHex xgetWorkbookPassword();

    public boolean isSetWorkbookPassword();

    public void setWorkbookPassword(byte[] var1);

    public void xsetWorkbookPassword(STUnsignedShortHex var1);

    public void unsetWorkbookPassword();

    public byte[] getRevisionsPassword();

    public STUnsignedShortHex xgetRevisionsPassword();

    public boolean isSetRevisionsPassword();

    public void setRevisionsPassword(byte[] var1);

    public void xsetRevisionsPassword(STUnsignedShortHex var1);

    public void unsetRevisionsPassword();

    public boolean getLockStructure();

    public XmlBoolean xgetLockStructure();

    public boolean isSetLockStructure();

    public void setLockStructure(boolean var1);

    public void xsetLockStructure(XmlBoolean var1);

    public void unsetLockStructure();

    public boolean getLockWindows();

    public XmlBoolean xgetLockWindows();

    public boolean isSetLockWindows();

    public void setLockWindows(boolean var1);

    public void xsetLockWindows(XmlBoolean var1);

    public void unsetLockWindows();

    public boolean getLockRevision();

    public XmlBoolean xgetLockRevision();

    public boolean isSetLockRevision();

    public void setLockRevision(boolean var1);

    public void xsetLockRevision(XmlBoolean var1);

    public void unsetLockRevision();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTWorkbookProtection.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTWorkbookProtection newInstance() {
            return (CTWorkbookProtection)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTWorkbookProtection newInstance(XmlOptions xmlOptions) {
            return (CTWorkbookProtection)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTWorkbookProtection parse(String string) throws XmlException {
            return (CTWorkbookProtection)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTWorkbookProtection parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTWorkbookProtection)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTWorkbookProtection parse(File file) throws XmlException, IOException {
            return (CTWorkbookProtection)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTWorkbookProtection parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTWorkbookProtection)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTWorkbookProtection parse(URL uRL) throws XmlException, IOException {
            return (CTWorkbookProtection)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTWorkbookProtection parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTWorkbookProtection)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTWorkbookProtection parse(InputStream inputStream) throws XmlException, IOException {
            return (CTWorkbookProtection)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTWorkbookProtection parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTWorkbookProtection)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTWorkbookProtection parse(Reader reader) throws XmlException, IOException {
            return (CTWorkbookProtection)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTWorkbookProtection parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTWorkbookProtection)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTWorkbookProtection parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTWorkbookProtection)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTWorkbookProtection parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTWorkbookProtection)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTWorkbookProtection parse(Node node) throws XmlException {
            return (CTWorkbookProtection)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTWorkbookProtection parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTWorkbookProtection)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTWorkbookProtection parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTWorkbookProtection)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTWorkbookProtection parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTWorkbookProtection)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
        }

        @Deprecated
        public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return Factory.getTypeLoader().newValidatingXMLInputStream(xMLInputStream, type, null);
        }

        @Deprecated
        public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return Factory.getTypeLoader().newValidatingXMLInputStream(xMLInputStream, type, xmlOptions);
        }

        private Factory() {
        }
    }
}

