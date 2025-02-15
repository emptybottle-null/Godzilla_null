/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main;

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
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.main.STPercentage;
import org.w3c.dom.Node;

public interface CTRelativeRect
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTRelativeRect.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctrelativerecta4ebtype");

    public int getL();

    public STPercentage xgetL();

    public boolean isSetL();

    public void setL(int var1);

    public void xsetL(STPercentage var1);

    public void unsetL();

    public int getT();

    public STPercentage xgetT();

    public boolean isSetT();

    public void setT(int var1);

    public void xsetT(STPercentage var1);

    public void unsetT();

    public int getR();

    public STPercentage xgetR();

    public boolean isSetR();

    public void setR(int var1);

    public void xsetR(STPercentage var1);

    public void unsetR();

    public int getB();

    public STPercentage xgetB();

    public boolean isSetB();

    public void setB(int var1);

    public void xsetB(STPercentage var1);

    public void unsetB();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTRelativeRect.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTRelativeRect newInstance() {
            return (CTRelativeRect)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTRelativeRect newInstance(XmlOptions xmlOptions) {
            return (CTRelativeRect)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTRelativeRect parse(String string) throws XmlException {
            return (CTRelativeRect)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTRelativeRect parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTRelativeRect)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTRelativeRect parse(File file) throws XmlException, IOException {
            return (CTRelativeRect)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTRelativeRect parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRelativeRect)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTRelativeRect parse(URL uRL) throws XmlException, IOException {
            return (CTRelativeRect)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTRelativeRect parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRelativeRect)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTRelativeRect parse(InputStream inputStream) throws XmlException, IOException {
            return (CTRelativeRect)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTRelativeRect parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRelativeRect)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTRelativeRect parse(Reader reader) throws XmlException, IOException {
            return (CTRelativeRect)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTRelativeRect parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRelativeRect)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTRelativeRect parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTRelativeRect)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTRelativeRect parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTRelativeRect)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTRelativeRect parse(Node node) throws XmlException {
            return (CTRelativeRect)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTRelativeRect parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTRelativeRect)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTRelativeRect parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTRelativeRect)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTRelativeRect parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTRelativeRect)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

