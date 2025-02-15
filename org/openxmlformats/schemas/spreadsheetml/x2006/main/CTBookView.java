/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.STVisibility
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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STVisibility;
import org.w3c.dom.Node;

public interface CTBookView
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTBookView.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctbookviewf677type");

    public CTExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTExtensionList var1);

    public CTExtensionList addNewExtLst();

    public void unsetExtLst();

    public STVisibility.Enum getVisibility();

    public STVisibility xgetVisibility();

    public boolean isSetVisibility();

    public void setVisibility(STVisibility.Enum var1);

    public void xsetVisibility(STVisibility var1);

    public void unsetVisibility();

    public boolean getMinimized();

    public XmlBoolean xgetMinimized();

    public boolean isSetMinimized();

    public void setMinimized(boolean var1);

    public void xsetMinimized(XmlBoolean var1);

    public void unsetMinimized();

    public boolean getShowHorizontalScroll();

    public XmlBoolean xgetShowHorizontalScroll();

    public boolean isSetShowHorizontalScroll();

    public void setShowHorizontalScroll(boolean var1);

    public void xsetShowHorizontalScroll(XmlBoolean var1);

    public void unsetShowHorizontalScroll();

    public boolean getShowVerticalScroll();

    public XmlBoolean xgetShowVerticalScroll();

    public boolean isSetShowVerticalScroll();

    public void setShowVerticalScroll(boolean var1);

    public void xsetShowVerticalScroll(XmlBoolean var1);

    public void unsetShowVerticalScroll();

    public boolean getShowSheetTabs();

    public XmlBoolean xgetShowSheetTabs();

    public boolean isSetShowSheetTabs();

    public void setShowSheetTabs(boolean var1);

    public void xsetShowSheetTabs(XmlBoolean var1);

    public void unsetShowSheetTabs();

    public int getXWindow();

    public XmlInt xgetXWindow();

    public boolean isSetXWindow();

    public void setXWindow(int var1);

    public void xsetXWindow(XmlInt var1);

    public void unsetXWindow();

    public int getYWindow();

    public XmlInt xgetYWindow();

    public boolean isSetYWindow();

    public void setYWindow(int var1);

    public void xsetYWindow(XmlInt var1);

    public void unsetYWindow();

    public long getWindowWidth();

    public XmlUnsignedInt xgetWindowWidth();

    public boolean isSetWindowWidth();

    public void setWindowWidth(long var1);

    public void xsetWindowWidth(XmlUnsignedInt var1);

    public void unsetWindowWidth();

    public long getWindowHeight();

    public XmlUnsignedInt xgetWindowHeight();

    public boolean isSetWindowHeight();

    public void setWindowHeight(long var1);

    public void xsetWindowHeight(XmlUnsignedInt var1);

    public void unsetWindowHeight();

    public long getTabRatio();

    public XmlUnsignedInt xgetTabRatio();

    public boolean isSetTabRatio();

    public void setTabRatio(long var1);

    public void xsetTabRatio(XmlUnsignedInt var1);

    public void unsetTabRatio();

    public long getFirstSheet();

    public XmlUnsignedInt xgetFirstSheet();

    public boolean isSetFirstSheet();

    public void setFirstSheet(long var1);

    public void xsetFirstSheet(XmlUnsignedInt var1);

    public void unsetFirstSheet();

    public long getActiveTab();

    public XmlUnsignedInt xgetActiveTab();

    public boolean isSetActiveTab();

    public void setActiveTab(long var1);

    public void xsetActiveTab(XmlUnsignedInt var1);

    public void unsetActiveTab();

    public boolean getAutoFilterDateGrouping();

    public XmlBoolean xgetAutoFilterDateGrouping();

    public boolean isSetAutoFilterDateGrouping();

    public void setAutoFilterDateGrouping(boolean var1);

    public void xsetAutoFilterDateGrouping(XmlBoolean var1);

    public void unsetAutoFilterDateGrouping();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTBookView.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTBookView newInstance() {
            return (CTBookView)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTBookView newInstance(XmlOptions xmlOptions) {
            return (CTBookView)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTBookView parse(String string) throws XmlException {
            return (CTBookView)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTBookView parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTBookView)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTBookView parse(File file) throws XmlException, IOException {
            return (CTBookView)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTBookView parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBookView)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTBookView parse(URL uRL) throws XmlException, IOException {
            return (CTBookView)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTBookView parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBookView)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTBookView parse(InputStream inputStream) throws XmlException, IOException {
            return (CTBookView)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTBookView parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBookView)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTBookView parse(Reader reader) throws XmlException, IOException {
            return (CTBookView)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTBookView parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBookView)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTBookView parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTBookView)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTBookView parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTBookView)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTBookView parse(Node node) throws XmlException {
            return (CTBookView)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTBookView parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTBookView)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTBookView parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTBookView)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTBookView parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTBookView)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

