/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTCell3D
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellBorderStyle
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
import org.openxmlformats.schemas.drawingml.x2006.main.CTCell3D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrixReference;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellBorderStyle;
import org.w3c.dom.Node;

public interface CTTableStyleCellStyle
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTTableStyleCellStyle.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cttablestylecellstyle1fddtype");

    public CTTableCellBorderStyle getTcBdr();

    public boolean isSetTcBdr();

    public void setTcBdr(CTTableCellBorderStyle var1);

    public CTTableCellBorderStyle addNewTcBdr();

    public void unsetTcBdr();

    public CTFillProperties getFill();

    public boolean isSetFill();

    public void setFill(CTFillProperties var1);

    public CTFillProperties addNewFill();

    public void unsetFill();

    public CTStyleMatrixReference getFillRef();

    public boolean isSetFillRef();

    public void setFillRef(CTStyleMatrixReference var1);

    public CTStyleMatrixReference addNewFillRef();

    public void unsetFillRef();

    public CTCell3D getCell3D();

    public boolean isSetCell3D();

    public void setCell3D(CTCell3D var1);

    public CTCell3D addNewCell3D();

    public void unsetCell3D();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTTableStyleCellStyle.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTTableStyleCellStyle newInstance() {
            return (CTTableStyleCellStyle)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTTableStyleCellStyle newInstance(XmlOptions xmlOptions) {
            return (CTTableStyleCellStyle)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTTableStyleCellStyle parse(String string) throws XmlException {
            return (CTTableStyleCellStyle)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTTableStyleCellStyle parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTTableStyleCellStyle)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTTableStyleCellStyle parse(File file) throws XmlException, IOException {
            return (CTTableStyleCellStyle)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTTableStyleCellStyle parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableStyleCellStyle)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTTableStyleCellStyle parse(URL uRL) throws XmlException, IOException {
            return (CTTableStyleCellStyle)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTTableStyleCellStyle parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableStyleCellStyle)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTTableStyleCellStyle parse(InputStream inputStream) throws XmlException, IOException {
            return (CTTableStyleCellStyle)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTTableStyleCellStyle parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableStyleCellStyle)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTTableStyleCellStyle parse(Reader reader) throws XmlException, IOException {
            return (CTTableStyleCellStyle)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTTableStyleCellStyle parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableStyleCellStyle)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTTableStyleCellStyle parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTTableStyleCellStyle)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTTableStyleCellStyle parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTTableStyleCellStyle)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTTableStyleCellStyle parse(Node node) throws XmlException {
            return (CTTableStyleCellStyle)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTTableStyleCellStyle parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTTableStyleCellStyle)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTTableStyleCellStyle parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTTableStyleCellStyle)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTTableStyleCellStyle parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTTableStyleCellStyle)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

