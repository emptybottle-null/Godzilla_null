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
import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndLength;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndType;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndWidth;
import org.w3c.dom.Node;

public interface CTLineEndProperties
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTLineEndProperties.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctlineendproperties8acbtype");

    public STLineEndType.Enum getType();

    public STLineEndType xgetType();

    public boolean isSetType();

    public void setType(STLineEndType.Enum var1);

    public void xsetType(STLineEndType var1);

    public void unsetType();

    public STLineEndWidth.Enum getW();

    public STLineEndWidth xgetW();

    public boolean isSetW();

    public void setW(STLineEndWidth.Enum var1);

    public void xsetW(STLineEndWidth var1);

    public void unsetW();

    public STLineEndLength.Enum getLen();

    public STLineEndLength xgetLen();

    public boolean isSetLen();

    public void setLen(STLineEndLength.Enum var1);

    public void xsetLen(STLineEndLength var1);

    public void unsetLen();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTLineEndProperties.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTLineEndProperties newInstance() {
            return (CTLineEndProperties)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTLineEndProperties newInstance(XmlOptions xmlOptions) {
            return (CTLineEndProperties)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTLineEndProperties parse(String string) throws XmlException {
            return (CTLineEndProperties)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTLineEndProperties parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTLineEndProperties)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTLineEndProperties parse(File file) throws XmlException, IOException {
            return (CTLineEndProperties)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTLineEndProperties parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLineEndProperties)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTLineEndProperties parse(URL uRL) throws XmlException, IOException {
            return (CTLineEndProperties)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTLineEndProperties parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLineEndProperties)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTLineEndProperties parse(InputStream inputStream) throws XmlException, IOException {
            return (CTLineEndProperties)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTLineEndProperties parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLineEndProperties)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTLineEndProperties parse(Reader reader) throws XmlException, IOException {
            return (CTLineEndProperties)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTLineEndProperties parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLineEndProperties)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTLineEndProperties parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTLineEndProperties)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTLineEndProperties parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTLineEndProperties)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTLineEndProperties parse(Node node) throws XmlException {
            return (CTLineEndProperties)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTLineEndProperties parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTLineEndProperties)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTLineEndProperties parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTLineEndProperties)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTLineEndProperties parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTLineEndProperties)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

