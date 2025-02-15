/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList
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
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellProtection;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFill;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTNumFmt;
import org.w3c.dom.Node;

public interface CTDxf
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTDxf.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctdxfa3b1type");

    public CTFont getFont();

    public boolean isSetFont();

    public void setFont(CTFont var1);

    public CTFont addNewFont();

    public void unsetFont();

    public CTNumFmt getNumFmt();

    public boolean isSetNumFmt();

    public void setNumFmt(CTNumFmt var1);

    public CTNumFmt addNewNumFmt();

    public void unsetNumFmt();

    public CTFill getFill();

    public boolean isSetFill();

    public void setFill(CTFill var1);

    public CTFill addNewFill();

    public void unsetFill();

    public CTCellAlignment getAlignment();

    public boolean isSetAlignment();

    public void setAlignment(CTCellAlignment var1);

    public CTCellAlignment addNewAlignment();

    public void unsetAlignment();

    public CTBorder getBorder();

    public boolean isSetBorder();

    public void setBorder(CTBorder var1);

    public CTBorder addNewBorder();

    public void unsetBorder();

    public CTCellProtection getProtection();

    public boolean isSetProtection();

    public void setProtection(CTCellProtection var1);

    public CTCellProtection addNewProtection();

    public void unsetProtection();

    public CTExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTExtensionList var1);

    public CTExtensionList addNewExtLst();

    public void unsetExtLst();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTDxf.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTDxf newInstance() {
            return (CTDxf)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTDxf newInstance(XmlOptions xmlOptions) {
            return (CTDxf)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTDxf parse(String string) throws XmlException {
            return (CTDxf)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTDxf parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTDxf)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTDxf parse(File file) throws XmlException, IOException {
            return (CTDxf)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTDxf parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDxf)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTDxf parse(URL uRL) throws XmlException, IOException {
            return (CTDxf)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTDxf parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDxf)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTDxf parse(InputStream inputStream) throws XmlException, IOException {
            return (CTDxf)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTDxf parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDxf)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTDxf parse(Reader reader) throws XmlException, IOException {
            return (CTDxf)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTDxf parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDxf)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTDxf parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTDxf)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTDxf parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTDxf)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTDxf parse(Node node) throws XmlException {
            return (CTDxf)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTDxf parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTDxf)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTDxf parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTDxf)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTDxf parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTDxf)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

