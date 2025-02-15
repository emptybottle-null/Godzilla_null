/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;

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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.w3c.dom.Node;

public interface CTTblCellMar
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTTblCellMar.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cttblcellmar66eatype");

    public CTTblWidth getTop();

    public boolean isSetTop();

    public void setTop(CTTblWidth var1);

    public CTTblWidth addNewTop();

    public void unsetTop();

    public CTTblWidth getLeft();

    public boolean isSetLeft();

    public void setLeft(CTTblWidth var1);

    public CTTblWidth addNewLeft();

    public void unsetLeft();

    public CTTblWidth getBottom();

    public boolean isSetBottom();

    public void setBottom(CTTblWidth var1);

    public CTTblWidth addNewBottom();

    public void unsetBottom();

    public CTTblWidth getRight();

    public boolean isSetRight();

    public void setRight(CTTblWidth var1);

    public CTTblWidth addNewRight();

    public void unsetRight();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTTblCellMar.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTTblCellMar newInstance() {
            return (CTTblCellMar)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTTblCellMar newInstance(XmlOptions xmlOptions) {
            return (CTTblCellMar)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTTblCellMar parse(String string) throws XmlException {
            return (CTTblCellMar)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTTblCellMar parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTTblCellMar)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTTblCellMar parse(File file) throws XmlException, IOException {
            return (CTTblCellMar)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTTblCellMar parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTblCellMar)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTTblCellMar parse(URL uRL) throws XmlException, IOException {
            return (CTTblCellMar)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTTblCellMar parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTblCellMar)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTTblCellMar parse(InputStream inputStream) throws XmlException, IOException {
            return (CTTblCellMar)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTTblCellMar parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTblCellMar)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTTblCellMar parse(Reader reader) throws XmlException, IOException {
            return (CTTblCellMar)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTTblCellMar parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTblCellMar)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTTblCellMar parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTTblCellMar)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTTblCellMar parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTTblCellMar)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTTblCellMar parse(Node node) throws XmlException {
            return (CTTblCellMar)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTTblCellMar parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTTblCellMar)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTTblCellMar parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTTblCellMar)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTTblCellMar parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTTblCellMar)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

