/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.office.visio.x2012.main;

import com.microsoft.schemas.office.visio.x2012.main.PageSheetType;
import com.microsoft.schemas.office.visio.x2012.main.RelType;
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
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface PageType
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(PageType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("pagetype2fcatype");

    public PageSheetType getPageSheet();

    public boolean isSetPageSheet();

    public void setPageSheet(PageSheetType var1);

    public PageSheetType addNewPageSheet();

    public void unsetPageSheet();

    public RelType getRel();

    public void setRel(RelType var1);

    public RelType addNewRel();

    public long getID();

    public XmlUnsignedInt xgetID();

    public void setID(long var1);

    public void xsetID(XmlUnsignedInt var1);

    public String getName();

    public XmlString xgetName();

    public boolean isSetName();

    public void setName(String var1);

    public void xsetName(XmlString var1);

    public void unsetName();

    public String getNameU();

    public XmlString xgetNameU();

    public boolean isSetNameU();

    public void setNameU(String var1);

    public void xsetNameU(XmlString var1);

    public void unsetNameU();

    public boolean getIsCustomName();

    public XmlBoolean xgetIsCustomName();

    public boolean isSetIsCustomName();

    public void setIsCustomName(boolean var1);

    public void xsetIsCustomName(XmlBoolean var1);

    public void unsetIsCustomName();

    public boolean getIsCustomNameU();

    public XmlBoolean xgetIsCustomNameU();

    public boolean isSetIsCustomNameU();

    public void setIsCustomNameU(boolean var1);

    public void xsetIsCustomNameU(XmlBoolean var1);

    public void unsetIsCustomNameU();

    public boolean getBackground();

    public XmlBoolean xgetBackground();

    public boolean isSetBackground();

    public void setBackground(boolean var1);

    public void xsetBackground(XmlBoolean var1);

    public void unsetBackground();

    public long getBackPage();

    public XmlUnsignedInt xgetBackPage();

    public boolean isSetBackPage();

    public void setBackPage(long var1);

    public void xsetBackPage(XmlUnsignedInt var1);

    public void unsetBackPage();

    public double getViewScale();

    public XmlDouble xgetViewScale();

    public boolean isSetViewScale();

    public void setViewScale(double var1);

    public void xsetViewScale(XmlDouble var1);

    public void unsetViewScale();

    public double getViewCenterX();

    public XmlDouble xgetViewCenterX();

    public boolean isSetViewCenterX();

    public void setViewCenterX(double var1);

    public void xsetViewCenterX(XmlDouble var1);

    public void unsetViewCenterX();

    public double getViewCenterY();

    public XmlDouble xgetViewCenterY();

    public boolean isSetViewCenterY();

    public void setViewCenterY(double var1);

    public void xsetViewCenterY(XmlDouble var1);

    public void unsetViewCenterY();

    public long getReviewerID();

    public XmlUnsignedInt xgetReviewerID();

    public boolean isSetReviewerID();

    public void setReviewerID(long var1);

    public void xsetReviewerID(XmlUnsignedInt var1);

    public void unsetReviewerID();

    public long getAssociatedPage();

    public XmlUnsignedInt xgetAssociatedPage();

    public boolean isSetAssociatedPage();

    public void setAssociatedPage(long var1);

    public void xsetAssociatedPage(XmlUnsignedInt var1);

    public void unsetAssociatedPage();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(PageType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static PageType newInstance() {
            return (PageType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static PageType newInstance(XmlOptions xmlOptions) {
            return (PageType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static PageType parse(String string) throws XmlException {
            return (PageType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static PageType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (PageType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static PageType parse(File file) throws XmlException, IOException {
            return (PageType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static PageType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (PageType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static PageType parse(URL uRL) throws XmlException, IOException {
            return (PageType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static PageType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (PageType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static PageType parse(InputStream inputStream) throws XmlException, IOException {
            return (PageType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static PageType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (PageType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static PageType parse(Reader reader) throws XmlException, IOException {
            return (PageType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static PageType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (PageType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static PageType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (PageType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static PageType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (PageType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static PageType parse(Node node) throws XmlException {
            return (PageType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static PageType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (PageType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static PageType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (PageType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static PageType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (PageType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

