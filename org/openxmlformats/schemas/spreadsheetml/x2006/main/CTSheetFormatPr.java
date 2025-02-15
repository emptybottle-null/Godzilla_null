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
import org.apache.xmlbeans.XmlDouble;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlUnsignedByte;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface CTSheetFormatPr
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTSheetFormatPr.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctsheetformatprdef7type");

    public long getBaseColWidth();

    public XmlUnsignedInt xgetBaseColWidth();

    public boolean isSetBaseColWidth();

    public void setBaseColWidth(long var1);

    public void xsetBaseColWidth(XmlUnsignedInt var1);

    public void unsetBaseColWidth();

    public double getDefaultColWidth();

    public XmlDouble xgetDefaultColWidth();

    public boolean isSetDefaultColWidth();

    public void setDefaultColWidth(double var1);

    public void xsetDefaultColWidth(XmlDouble var1);

    public void unsetDefaultColWidth();

    public double getDefaultRowHeight();

    public XmlDouble xgetDefaultRowHeight();

    public void setDefaultRowHeight(double var1);

    public void xsetDefaultRowHeight(XmlDouble var1);

    public boolean getCustomHeight();

    public XmlBoolean xgetCustomHeight();

    public boolean isSetCustomHeight();

    public void setCustomHeight(boolean var1);

    public void xsetCustomHeight(XmlBoolean var1);

    public void unsetCustomHeight();

    public boolean getZeroHeight();

    public XmlBoolean xgetZeroHeight();

    public boolean isSetZeroHeight();

    public void setZeroHeight(boolean var1);

    public void xsetZeroHeight(XmlBoolean var1);

    public void unsetZeroHeight();

    public boolean getThickTop();

    public XmlBoolean xgetThickTop();

    public boolean isSetThickTop();

    public void setThickTop(boolean var1);

    public void xsetThickTop(XmlBoolean var1);

    public void unsetThickTop();

    public boolean getThickBottom();

    public XmlBoolean xgetThickBottom();

    public boolean isSetThickBottom();

    public void setThickBottom(boolean var1);

    public void xsetThickBottom(XmlBoolean var1);

    public void unsetThickBottom();

    public short getOutlineLevelRow();

    public XmlUnsignedByte xgetOutlineLevelRow();

    public boolean isSetOutlineLevelRow();

    public void setOutlineLevelRow(short var1);

    public void xsetOutlineLevelRow(XmlUnsignedByte var1);

    public void unsetOutlineLevelRow();

    public short getOutlineLevelCol();

    public XmlUnsignedByte xgetOutlineLevelCol();

    public boolean isSetOutlineLevelCol();

    public void setOutlineLevelCol(short var1);

    public void xsetOutlineLevelCol(XmlUnsignedByte var1);

    public void unsetOutlineLevelCol();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTSheetFormatPr.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTSheetFormatPr newInstance() {
            return (CTSheetFormatPr)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTSheetFormatPr newInstance(XmlOptions xmlOptions) {
            return (CTSheetFormatPr)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTSheetFormatPr parse(String string) throws XmlException {
            return (CTSheetFormatPr)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTSheetFormatPr parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTSheetFormatPr)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTSheetFormatPr parse(File file) throws XmlException, IOException {
            return (CTSheetFormatPr)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTSheetFormatPr parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSheetFormatPr)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTSheetFormatPr parse(URL uRL) throws XmlException, IOException {
            return (CTSheetFormatPr)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTSheetFormatPr parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSheetFormatPr)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTSheetFormatPr parse(InputStream inputStream) throws XmlException, IOException {
            return (CTSheetFormatPr)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTSheetFormatPr parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSheetFormatPr)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTSheetFormatPr parse(Reader reader) throws XmlException, IOException {
            return (CTSheetFormatPr)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTSheetFormatPr parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSheetFormatPr)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTSheetFormatPr parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTSheetFormatPr)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTSheetFormatPr parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTSheetFormatPr)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTSheetFormatPr parse(Node node) throws XmlException {
            return (CTSheetFormatPr)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTSheetFormatPr parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTSheetFormatPr)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTSheetFormatPr parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTSheetFormatPr)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTSheetFormatPr parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTSheetFormatPr)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

