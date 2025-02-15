/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetViews
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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetViews;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDrawing;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHeaderFooter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTLegacyDrawing;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleObjects;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageSetup;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetFormatPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetProtection;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetViews;
import org.w3c.dom.Node;

public interface CTDialogsheet
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTDialogsheet.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctdialogsheet6f36type");

    public CTSheetPr getSheetPr();

    public boolean isSetSheetPr();

    public void setSheetPr(CTSheetPr var1);

    public CTSheetPr addNewSheetPr();

    public void unsetSheetPr();

    public CTSheetViews getSheetViews();

    public boolean isSetSheetViews();

    public void setSheetViews(CTSheetViews var1);

    public CTSheetViews addNewSheetViews();

    public void unsetSheetViews();

    public CTSheetFormatPr getSheetFormatPr();

    public boolean isSetSheetFormatPr();

    public void setSheetFormatPr(CTSheetFormatPr var1);

    public CTSheetFormatPr addNewSheetFormatPr();

    public void unsetSheetFormatPr();

    public CTSheetProtection getSheetProtection();

    public boolean isSetSheetProtection();

    public void setSheetProtection(CTSheetProtection var1);

    public CTSheetProtection addNewSheetProtection();

    public void unsetSheetProtection();

    public CTCustomSheetViews getCustomSheetViews();

    public boolean isSetCustomSheetViews();

    public void setCustomSheetViews(CTCustomSheetViews var1);

    public CTCustomSheetViews addNewCustomSheetViews();

    public void unsetCustomSheetViews();

    public CTPrintOptions getPrintOptions();

    public boolean isSetPrintOptions();

    public void setPrintOptions(CTPrintOptions var1);

    public CTPrintOptions addNewPrintOptions();

    public void unsetPrintOptions();

    public CTPageMargins getPageMargins();

    public boolean isSetPageMargins();

    public void setPageMargins(CTPageMargins var1);

    public CTPageMargins addNewPageMargins();

    public void unsetPageMargins();

    public CTPageSetup getPageSetup();

    public boolean isSetPageSetup();

    public void setPageSetup(CTPageSetup var1);

    public CTPageSetup addNewPageSetup();

    public void unsetPageSetup();

    public CTHeaderFooter getHeaderFooter();

    public boolean isSetHeaderFooter();

    public void setHeaderFooter(CTHeaderFooter var1);

    public CTHeaderFooter addNewHeaderFooter();

    public void unsetHeaderFooter();

    public CTDrawing getDrawing();

    public boolean isSetDrawing();

    public void setDrawing(CTDrawing var1);

    public CTDrawing addNewDrawing();

    public void unsetDrawing();

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

    public CTOleObjects getOleObjects();

    public boolean isSetOleObjects();

    public void setOleObjects(CTOleObjects var1);

    public CTOleObjects addNewOleObjects();

    public void unsetOleObjects();

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
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTDialogsheet.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTDialogsheet newInstance() {
            return (CTDialogsheet)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTDialogsheet newInstance(XmlOptions xmlOptions) {
            return (CTDialogsheet)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTDialogsheet parse(String string) throws XmlException {
            return (CTDialogsheet)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTDialogsheet parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTDialogsheet)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTDialogsheet parse(File file) throws XmlException, IOException {
            return (CTDialogsheet)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTDialogsheet parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDialogsheet)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTDialogsheet parse(URL uRL) throws XmlException, IOException {
            return (CTDialogsheet)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTDialogsheet parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDialogsheet)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTDialogsheet parse(InputStream inputStream) throws XmlException, IOException {
            return (CTDialogsheet)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTDialogsheet parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDialogsheet)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTDialogsheet parse(Reader reader) throws XmlException, IOException {
            return (CTDialogsheet)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTDialogsheet parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDialogsheet)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTDialogsheet parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTDialogsheet)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTDialogsheet parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTDialogsheet)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTDialogsheet parse(Node node) throws XmlException {
            return (CTDialogsheet)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTDialogsheet parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTDialogsheet)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTDialogsheet parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTDialogsheet)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTDialogsheet parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTDialogsheet)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

