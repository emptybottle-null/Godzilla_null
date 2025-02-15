/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.chart.STPageSetupOrientation
 */
package org.openxmlformats.schemas.drawingml.x2006.chart;

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
import org.openxmlformats.schemas.drawingml.x2006.chart.STPageSetupOrientation;
import org.w3c.dom.Node;

public interface CTPageSetup
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTPageSetup.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctpagesetupdb38type");

    public long getPaperSize();

    public XmlUnsignedInt xgetPaperSize();

    public boolean isSetPaperSize();

    public void setPaperSize(long var1);

    public void xsetPaperSize(XmlUnsignedInt var1);

    public void unsetPaperSize();

    public long getFirstPageNumber();

    public XmlUnsignedInt xgetFirstPageNumber();

    public boolean isSetFirstPageNumber();

    public void setFirstPageNumber(long var1);

    public void xsetFirstPageNumber(XmlUnsignedInt var1);

    public void unsetFirstPageNumber();

    public STPageSetupOrientation.Enum getOrientation();

    public STPageSetupOrientation xgetOrientation();

    public boolean isSetOrientation();

    public void setOrientation(STPageSetupOrientation.Enum var1);

    public void xsetOrientation(STPageSetupOrientation var1);

    public void unsetOrientation();

    public boolean getBlackAndWhite();

    public XmlBoolean xgetBlackAndWhite();

    public boolean isSetBlackAndWhite();

    public void setBlackAndWhite(boolean var1);

    public void xsetBlackAndWhite(XmlBoolean var1);

    public void unsetBlackAndWhite();

    public boolean getDraft();

    public XmlBoolean xgetDraft();

    public boolean isSetDraft();

    public void setDraft(boolean var1);

    public void xsetDraft(XmlBoolean var1);

    public void unsetDraft();

    public boolean getUseFirstPageNumber();

    public XmlBoolean xgetUseFirstPageNumber();

    public boolean isSetUseFirstPageNumber();

    public void setUseFirstPageNumber(boolean var1);

    public void xsetUseFirstPageNumber(XmlBoolean var1);

    public void unsetUseFirstPageNumber();

    public int getHorizontalDpi();

    public XmlInt xgetHorizontalDpi();

    public boolean isSetHorizontalDpi();

    public void setHorizontalDpi(int var1);

    public void xsetHorizontalDpi(XmlInt var1);

    public void unsetHorizontalDpi();

    public int getVerticalDpi();

    public XmlInt xgetVerticalDpi();

    public boolean isSetVerticalDpi();

    public void setVerticalDpi(int var1);

    public void xsetVerticalDpi(XmlInt var1);

    public void unsetVerticalDpi();

    public long getCopies();

    public XmlUnsignedInt xgetCopies();

    public boolean isSetCopies();

    public void setCopies(long var1);

    public void xsetCopies(XmlUnsignedInt var1);

    public void unsetCopies();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTPageSetup.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTPageSetup newInstance() {
            return (CTPageSetup)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTPageSetup newInstance(XmlOptions xmlOptions) {
            return (CTPageSetup)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTPageSetup parse(String string) throws XmlException {
            return (CTPageSetup)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTPageSetup parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTPageSetup)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTPageSetup parse(File file) throws XmlException, IOException {
            return (CTPageSetup)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTPageSetup parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPageSetup)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTPageSetup parse(URL uRL) throws XmlException, IOException {
            return (CTPageSetup)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTPageSetup parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPageSetup)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTPageSetup parse(InputStream inputStream) throws XmlException, IOException {
            return (CTPageSetup)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTPageSetup parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPageSetup)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTPageSetup parse(Reader reader) throws XmlException, IOException {
            return (CTPageSetup)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTPageSetup parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPageSetup)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTPageSetup parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTPageSetup)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTPageSetup parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTPageSetup)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTPageSetup parse(Node node) throws XmlException {
            return (CTPageSetup)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTPageSetup parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTPageSetup)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTPageSetup parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTPageSetup)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTPageSetup parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTPageSetup)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

