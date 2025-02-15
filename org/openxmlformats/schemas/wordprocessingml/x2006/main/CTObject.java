/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTControl
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
import java.math.BigInteger;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTControl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPictureBase;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTwipsMeasure;
import org.w3c.dom.Node;

public interface CTObject
extends CTPictureBase {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTObject.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctobject47c9type");

    public CTControl getControl();

    public boolean isSetControl();

    public void setControl(CTControl var1);

    public CTControl addNewControl();

    public void unsetControl();

    public BigInteger getDxaOrig();

    public STTwipsMeasure xgetDxaOrig();

    public boolean isSetDxaOrig();

    public void setDxaOrig(BigInteger var1);

    public void xsetDxaOrig(STTwipsMeasure var1);

    public void unsetDxaOrig();

    public BigInteger getDyaOrig();

    public STTwipsMeasure xgetDyaOrig();

    public boolean isSetDyaOrig();

    public void setDyaOrig(BigInteger var1);

    public void xsetDyaOrig(STTwipsMeasure var1);

    public void unsetDyaOrig();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTObject.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTObject newInstance() {
            return (CTObject)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTObject newInstance(XmlOptions xmlOptions) {
            return (CTObject)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTObject parse(String string) throws XmlException {
            return (CTObject)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTObject parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTObject)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTObject parse(File file) throws XmlException, IOException {
            return (CTObject)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTObject parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTObject)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTObject parse(URL uRL) throws XmlException, IOException {
            return (CTObject)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTObject parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTObject)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTObject parse(InputStream inputStream) throws XmlException, IOException {
            return (CTObject)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTObject parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTObject)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTObject parse(Reader reader) throws XmlException, IOException {
            return (CTObject)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTObject parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTObject)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTObject parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTObject)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTObject parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTObject)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTObject parse(Node node) throws XmlException {
            return (CTObject)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTObject parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTObject)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTObject parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTObject)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTObject parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTObject)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

