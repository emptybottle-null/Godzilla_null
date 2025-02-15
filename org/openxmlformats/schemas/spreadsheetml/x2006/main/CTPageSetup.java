/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.STPrintError
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
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellComments;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STOrientation;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STPageOrder;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STPrintError;
import org.w3c.dom.Node;

public interface CTPageSetup
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTPageSetup.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctpagesetup534dtype");

    public long getPaperSize();

    public XmlUnsignedInt xgetPaperSize();

    public boolean isSetPaperSize();

    public void setPaperSize(long var1);

    public void xsetPaperSize(XmlUnsignedInt var1);

    public void unsetPaperSize();

    public long getScale();

    public XmlUnsignedInt xgetScale();

    public boolean isSetScale();

    public void setScale(long var1);

    public void xsetScale(XmlUnsignedInt var1);

    public void unsetScale();

    public long getFirstPageNumber();

    public XmlUnsignedInt xgetFirstPageNumber();

    public boolean isSetFirstPageNumber();

    public void setFirstPageNumber(long var1);

    public void xsetFirstPageNumber(XmlUnsignedInt var1);

    public void unsetFirstPageNumber();

    public long getFitToWidth();

    public XmlUnsignedInt xgetFitToWidth();

    public boolean isSetFitToWidth();

    public void setFitToWidth(long var1);

    public void xsetFitToWidth(XmlUnsignedInt var1);

    public void unsetFitToWidth();

    public long getFitToHeight();

    public XmlUnsignedInt xgetFitToHeight();

    public boolean isSetFitToHeight();

    public void setFitToHeight(long var1);

    public void xsetFitToHeight(XmlUnsignedInt var1);

    public void unsetFitToHeight();

    public STPageOrder.Enum getPageOrder();

    public STPageOrder xgetPageOrder();

    public boolean isSetPageOrder();

    public void setPageOrder(STPageOrder.Enum var1);

    public void xsetPageOrder(STPageOrder var1);

    public void unsetPageOrder();

    public STOrientation.Enum getOrientation();

    public STOrientation xgetOrientation();

    public boolean isSetOrientation();

    public void setOrientation(STOrientation.Enum var1);

    public void xsetOrientation(STOrientation var1);

    public void unsetOrientation();

    public boolean getUsePrinterDefaults();

    public XmlBoolean xgetUsePrinterDefaults();

    public boolean isSetUsePrinterDefaults();

    public void setUsePrinterDefaults(boolean var1);

    public void xsetUsePrinterDefaults(XmlBoolean var1);

    public void unsetUsePrinterDefaults();

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

    public STCellComments.Enum getCellComments();

    public STCellComments xgetCellComments();

    public boolean isSetCellComments();

    public void setCellComments(STCellComments.Enum var1);

    public void xsetCellComments(STCellComments var1);

    public void unsetCellComments();

    public boolean getUseFirstPageNumber();

    public XmlBoolean xgetUseFirstPageNumber();

    public boolean isSetUseFirstPageNumber();

    public void setUseFirstPageNumber(boolean var1);

    public void xsetUseFirstPageNumber(XmlBoolean var1);

    public void unsetUseFirstPageNumber();

    public STPrintError.Enum getErrors();

    public STPrintError xgetErrors();

    public boolean isSetErrors();

    public void setErrors(STPrintError.Enum var1);

    public void xsetErrors(STPrintError var1);

    public void unsetErrors();

    public long getHorizontalDpi();

    public XmlUnsignedInt xgetHorizontalDpi();

    public boolean isSetHorizontalDpi();

    public void setHorizontalDpi(long var1);

    public void xsetHorizontalDpi(XmlUnsignedInt var1);

    public void unsetHorizontalDpi();

    public long getVerticalDpi();

    public XmlUnsignedInt xgetVerticalDpi();

    public boolean isSetVerticalDpi();

    public void setVerticalDpi(long var1);

    public void xsetVerticalDpi(XmlUnsignedInt var1);

    public void unsetVerticalDpi();

    public long getCopies();

    public XmlUnsignedInt xgetCopies();

    public boolean isSetCopies();

    public void setCopies(long var1);

    public void xsetCopies(XmlUnsignedInt var1);

    public void unsetCopies();

    public String getId();

    public STRelationshipId xgetId();

    public boolean isSetId();

    public void setId(String var1);

    public void xsetId(STRelationshipId var1);

    public void unsetId();

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

