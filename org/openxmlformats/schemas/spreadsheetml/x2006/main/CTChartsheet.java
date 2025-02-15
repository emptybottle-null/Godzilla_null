/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheetPr
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheetProtection
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheetViews
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCsPageSetup
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetViews
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetBackgroundPicture
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItems
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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheetPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheetProtection;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheetViews;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCsPageSetup;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetViews;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDrawing;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHeaderFooter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTLegacyDrawing;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetBackgroundPicture;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItems;
import org.w3c.dom.Node;

public interface CTChartsheet
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTChartsheet.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctchartsheetf68atype");

    public CTChartsheetPr getSheetPr();

    public boolean isSetSheetPr();

    public void setSheetPr(CTChartsheetPr var1);

    public CTChartsheetPr addNewSheetPr();

    public void unsetSheetPr();

    public CTChartsheetViews getSheetViews();

    public void setSheetViews(CTChartsheetViews var1);

    public CTChartsheetViews addNewSheetViews();

    public CTChartsheetProtection getSheetProtection();

    public boolean isSetSheetProtection();

    public void setSheetProtection(CTChartsheetProtection var1);

    public CTChartsheetProtection addNewSheetProtection();

    public void unsetSheetProtection();

    public CTCustomChartsheetViews getCustomSheetViews();

    public boolean isSetCustomSheetViews();

    public void setCustomSheetViews(CTCustomChartsheetViews var1);

    public CTCustomChartsheetViews addNewCustomSheetViews();

    public void unsetCustomSheetViews();

    public CTPageMargins getPageMargins();

    public boolean isSetPageMargins();

    public void setPageMargins(CTPageMargins var1);

    public CTPageMargins addNewPageMargins();

    public void unsetPageMargins();

    public CTCsPageSetup getPageSetup();

    public boolean isSetPageSetup();

    public void setPageSetup(CTCsPageSetup var1);

    public CTCsPageSetup addNewPageSetup();

    public void unsetPageSetup();

    public CTHeaderFooter getHeaderFooter();

    public boolean isSetHeaderFooter();

    public void setHeaderFooter(CTHeaderFooter var1);

    public CTHeaderFooter addNewHeaderFooter();

    public void unsetHeaderFooter();

    public CTDrawing getDrawing();

    public void setDrawing(CTDrawing var1);

    public CTDrawing addNewDrawing();

    public CTLegacyDrawing getLegacyDrawing();

    public boolean isSetLegacyDrawing();

    public void setLegacyDrawing(CTLegacyDrawing var1);

    public CTLegacyDrawing addNewLegacyDrawing();

    public void unsetLegacyDrawing();

    public CTLegacyDrawing getLegacyDrawingHF();

    public boolean isSetLegacyDrawingHF();

    public void setLegacyDrawingHF(CTLegacyDrawing var1);

    public CTLegacyDrawing addNewLegacyDrawingHF();

    public void unsetLegacyDrawingHF();

    public CTSheetBackgroundPicture getPicture();

    public boolean isSetPicture();

    public void setPicture(CTSheetBackgroundPicture var1);

    public CTSheetBackgroundPicture addNewPicture();

    public void unsetPicture();

    public CTWebPublishItems getWebPublishItems();

    public boolean isSetWebPublishItems();

    public void setWebPublishItems(CTWebPublishItems var1);

    public CTWebPublishItems addNewWebPublishItems();

    public void unsetWebPublishItems();

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
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTChartsheet.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTChartsheet newInstance() {
            return (CTChartsheet)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTChartsheet newInstance(XmlOptions xmlOptions) {
            return (CTChartsheet)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTChartsheet parse(String string) throws XmlException {
            return (CTChartsheet)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTChartsheet parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTChartsheet)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTChartsheet parse(File file) throws XmlException, IOException {
            return (CTChartsheet)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTChartsheet parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTChartsheet)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTChartsheet parse(URL uRL) throws XmlException, IOException {
            return (CTChartsheet)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTChartsheet parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTChartsheet)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTChartsheet parse(InputStream inputStream) throws XmlException, IOException {
            return (CTChartsheet)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTChartsheet parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTChartsheet)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTChartsheet parse(Reader reader) throws XmlException, IOException {
            return (CTChartsheet)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTChartsheet parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTChartsheet)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTChartsheet parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTChartsheet)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTChartsheet parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTChartsheet)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTChartsheet parse(Node node) throws XmlException {
            return (CTChartsheet)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTChartsheet parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTChartsheet)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTChartsheet parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTChartsheet)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTChartsheet parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTChartsheet)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

