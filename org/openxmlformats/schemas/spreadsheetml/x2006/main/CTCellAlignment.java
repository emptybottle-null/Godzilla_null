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
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STHorizontalAlignment;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STVerticalAlignment;
import org.w3c.dom.Node;

public interface CTCellAlignment
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTCellAlignment.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctcellalignmentb580type");

    public STHorizontalAlignment.Enum getHorizontal();

    public STHorizontalAlignment xgetHorizontal();

    public boolean isSetHorizontal();

    public void setHorizontal(STHorizontalAlignment.Enum var1);

    public void xsetHorizontal(STHorizontalAlignment var1);

    public void unsetHorizontal();

    public STVerticalAlignment.Enum getVertical();

    public STVerticalAlignment xgetVertical();

    public boolean isSetVertical();

    public void setVertical(STVerticalAlignment.Enum var1);

    public void xsetVertical(STVerticalAlignment var1);

    public void unsetVertical();

    public long getTextRotation();

    public XmlUnsignedInt xgetTextRotation();

    public boolean isSetTextRotation();

    public void setTextRotation(long var1);

    public void xsetTextRotation(XmlUnsignedInt var1);

    public void unsetTextRotation();

    public boolean getWrapText();

    public XmlBoolean xgetWrapText();

    public boolean isSetWrapText();

    public void setWrapText(boolean var1);

    public void xsetWrapText(XmlBoolean var1);

    public void unsetWrapText();

    public long getIndent();

    public XmlUnsignedInt xgetIndent();

    public boolean isSetIndent();

    public void setIndent(long var1);

    public void xsetIndent(XmlUnsignedInt var1);

    public void unsetIndent();

    public int getRelativeIndent();

    public XmlInt xgetRelativeIndent();

    public boolean isSetRelativeIndent();

    public void setRelativeIndent(int var1);

    public void xsetRelativeIndent(XmlInt var1);

    public void unsetRelativeIndent();

    public boolean getJustifyLastLine();

    public XmlBoolean xgetJustifyLastLine();

    public boolean isSetJustifyLastLine();

    public void setJustifyLastLine(boolean var1);

    public void xsetJustifyLastLine(XmlBoolean var1);

    public void unsetJustifyLastLine();

    public boolean getShrinkToFit();

    public XmlBoolean xgetShrinkToFit();

    public boolean isSetShrinkToFit();

    public void setShrinkToFit(boolean var1);

    public void xsetShrinkToFit(XmlBoolean var1);

    public void unsetShrinkToFit();

    public long getReadingOrder();

    public XmlUnsignedInt xgetReadingOrder();

    public boolean isSetReadingOrder();

    public void setReadingOrder(long var1);

    public void xsetReadingOrder(XmlUnsignedInt var1);

    public void unsetReadingOrder();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTCellAlignment.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTCellAlignment newInstance() {
            return (CTCellAlignment)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTCellAlignment newInstance(XmlOptions xmlOptions) {
            return (CTCellAlignment)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTCellAlignment parse(String string) throws XmlException {
            return (CTCellAlignment)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTCellAlignment parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTCellAlignment)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTCellAlignment parse(File file) throws XmlException, IOException {
            return (CTCellAlignment)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTCellAlignment parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCellAlignment)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTCellAlignment parse(URL uRL) throws XmlException, IOException {
            return (CTCellAlignment)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTCellAlignment parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCellAlignment)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTCellAlignment parse(InputStream inputStream) throws XmlException, IOException {
            return (CTCellAlignment)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTCellAlignment parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCellAlignment)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTCellAlignment parse(Reader reader) throws XmlException, IOException {
            return (CTCellAlignment)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTCellAlignment parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCellAlignment)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTCellAlignment parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTCellAlignment)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTCellAlignment parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTCellAlignment)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTCellAlignment parse(Node node) throws XmlException {
            return (CTCellAlignment)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTCellAlignment parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTCellAlignment)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTCellAlignment parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTCellAlignment)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTCellAlignment parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTCellAlignment)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

