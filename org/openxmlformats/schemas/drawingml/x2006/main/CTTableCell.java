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
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;
import org.w3c.dom.Node;

public interface CTTableCell
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTTableCell.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cttablecell3ac1type");

    public CTTextBody getTxBody();

    public boolean isSetTxBody();

    public void setTxBody(CTTextBody var1);

    public CTTextBody addNewTxBody();

    public void unsetTxBody();

    public CTTableCellProperties getTcPr();

    public boolean isSetTcPr();

    public void setTcPr(CTTableCellProperties var1);

    public CTTableCellProperties addNewTcPr();

    public void unsetTcPr();

    public CTOfficeArtExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTOfficeArtExtensionList var1);

    public CTOfficeArtExtensionList addNewExtLst();

    public void unsetExtLst();

    public int getRowSpan();

    public XmlInt xgetRowSpan();

    public boolean isSetRowSpan();

    public void setRowSpan(int var1);

    public void xsetRowSpan(XmlInt var1);

    public void unsetRowSpan();

    public int getGridSpan();

    public XmlInt xgetGridSpan();

    public boolean isSetGridSpan();

    public void setGridSpan(int var1);

    public void xsetGridSpan(XmlInt var1);

    public void unsetGridSpan();

    public boolean getHMerge();

    public XmlBoolean xgetHMerge();

    public boolean isSetHMerge();

    public void setHMerge(boolean var1);

    public void xsetHMerge(XmlBoolean var1);

    public void unsetHMerge();

    public boolean getVMerge();

    public XmlBoolean xgetVMerge();

    public boolean isSetVMerge();

    public void setVMerge(boolean var1);

    public void xsetVMerge(XmlBoolean var1);

    public void unsetVMerge();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTTableCell.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTTableCell newInstance() {
            return (CTTableCell)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTTableCell newInstance(XmlOptions xmlOptions) {
            return (CTTableCell)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTTableCell parse(String string) throws XmlException {
            return (CTTableCell)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTTableCell parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTTableCell)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTTableCell parse(File file) throws XmlException, IOException {
            return (CTTableCell)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTTableCell parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableCell)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTTableCell parse(URL uRL) throws XmlException, IOException {
            return (CTTableCell)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTTableCell parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableCell)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTTableCell parse(InputStream inputStream) throws XmlException, IOException {
            return (CTTableCell)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTTableCell parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableCell)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTTableCell parse(Reader reader) throws XmlException, IOException {
            return (CTTableCell)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTTableCell parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableCell)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTTableCell parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTTableCell)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTTableCell parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTTableCell)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTTableCell parse(Node node) throws XmlException {
            return (CTTableCell)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTTableCell parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTTableCell)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTTableCell parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTTableCell)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTTableCell parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTTableCell)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

