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
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOutlinePr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageSetUpPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STRef;
import org.w3c.dom.Node;

public interface CTSheetPr
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTSheetPr.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctsheetpr3ae0type");

    public CTColor getTabColor();

    public boolean isSetTabColor();

    public void setTabColor(CTColor var1);

    public CTColor addNewTabColor();

    public void unsetTabColor();

    public CTOutlinePr getOutlinePr();

    public boolean isSetOutlinePr();

    public void setOutlinePr(CTOutlinePr var1);

    public CTOutlinePr addNewOutlinePr();

    public void unsetOutlinePr();

    public CTPageSetUpPr getPageSetUpPr();

    public boolean isSetPageSetUpPr();

    public void setPageSetUpPr(CTPageSetUpPr var1);

    public CTPageSetUpPr addNewPageSetUpPr();

    public void unsetPageSetUpPr();

    public boolean getSyncHorizontal();

    public XmlBoolean xgetSyncHorizontal();

    public boolean isSetSyncHorizontal();

    public void setSyncHorizontal(boolean var1);

    public void xsetSyncHorizontal(XmlBoolean var1);

    public void unsetSyncHorizontal();

    public boolean getSyncVertical();

    public XmlBoolean xgetSyncVertical();

    public boolean isSetSyncVertical();

    public void setSyncVertical(boolean var1);

    public void xsetSyncVertical(XmlBoolean var1);

    public void unsetSyncVertical();

    public String getSyncRef();

    public STRef xgetSyncRef();

    public boolean isSetSyncRef();

    public void setSyncRef(String var1);

    public void xsetSyncRef(STRef var1);

    public void unsetSyncRef();

    public boolean getTransitionEvaluation();

    public XmlBoolean xgetTransitionEvaluation();

    public boolean isSetTransitionEvaluation();

    public void setTransitionEvaluation(boolean var1);

    public void xsetTransitionEvaluation(XmlBoolean var1);

    public void unsetTransitionEvaluation();

    public boolean getTransitionEntry();

    public XmlBoolean xgetTransitionEntry();

    public boolean isSetTransitionEntry();

    public void setTransitionEntry(boolean var1);

    public void xsetTransitionEntry(XmlBoolean var1);

    public void unsetTransitionEntry();

    public boolean getPublished();

    public XmlBoolean xgetPublished();

    public boolean isSetPublished();

    public void setPublished(boolean var1);

    public void xsetPublished(XmlBoolean var1);

    public void unsetPublished();

    public String getCodeName();

    public XmlString xgetCodeName();

    public boolean isSetCodeName();

    public void setCodeName(String var1);

    public void xsetCodeName(XmlString var1);

    public void unsetCodeName();

    public boolean getFilterMode();

    public XmlBoolean xgetFilterMode();

    public boolean isSetFilterMode();

    public void setFilterMode(boolean var1);

    public void xsetFilterMode(XmlBoolean var1);

    public void unsetFilterMode();

    public boolean getEnableFormatConditionsCalculation();

    public XmlBoolean xgetEnableFormatConditionsCalculation();

    public boolean isSetEnableFormatConditionsCalculation();

    public void setEnableFormatConditionsCalculation(boolean var1);

    public void xsetEnableFormatConditionsCalculation(XmlBoolean var1);

    public void unsetEnableFormatConditionsCalculation();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTSheetPr.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTSheetPr newInstance() {
            return (CTSheetPr)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTSheetPr newInstance(XmlOptions xmlOptions) {
            return (CTSheetPr)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTSheetPr parse(String string) throws XmlException {
            return (CTSheetPr)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTSheetPr parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTSheetPr)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTSheetPr parse(File file) throws XmlException, IOException {
            return (CTSheetPr)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTSheetPr parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSheetPr)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTSheetPr parse(URL uRL) throws XmlException, IOException {
            return (CTSheetPr)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTSheetPr parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSheetPr)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTSheetPr parse(InputStream inputStream) throws XmlException, IOException {
            return (CTSheetPr)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTSheetPr parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSheetPr)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTSheetPr parse(Reader reader) throws XmlException, IOException {
            return (CTSheetPr)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTSheetPr parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSheetPr)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTSheetPr parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTSheetPr)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTSheetPr parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTSheetPr)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTSheetPr parse(Node node) throws XmlException {
            return (CTSheetPr)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTSheetPr parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTSheetPr)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTSheetPr parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTSheetPr)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTSheetPr parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTSheetPr)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

