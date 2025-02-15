/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.STObjects
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.STUpdateLinks
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
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STObjects;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STUpdateLinks;
import org.w3c.dom.Node;

public interface CTWorkbookPr
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTWorkbookPr.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctworkbookpr03a5type");

    public boolean getDate1904();

    public XmlBoolean xgetDate1904();

    public boolean isSetDate1904();

    public void setDate1904(boolean var1);

    public void xsetDate1904(XmlBoolean var1);

    public void unsetDate1904();

    public STObjects.Enum getShowObjects();

    public STObjects xgetShowObjects();

    public boolean isSetShowObjects();

    public void setShowObjects(STObjects.Enum var1);

    public void xsetShowObjects(STObjects var1);

    public void unsetShowObjects();

    public boolean getShowBorderUnselectedTables();

    public XmlBoolean xgetShowBorderUnselectedTables();

    public boolean isSetShowBorderUnselectedTables();

    public void setShowBorderUnselectedTables(boolean var1);

    public void xsetShowBorderUnselectedTables(XmlBoolean var1);

    public void unsetShowBorderUnselectedTables();

    public boolean getFilterPrivacy();

    public XmlBoolean xgetFilterPrivacy();

    public boolean isSetFilterPrivacy();

    public void setFilterPrivacy(boolean var1);

    public void xsetFilterPrivacy(XmlBoolean var1);

    public void unsetFilterPrivacy();

    public boolean getPromptedSolutions();

    public XmlBoolean xgetPromptedSolutions();

    public boolean isSetPromptedSolutions();

    public void setPromptedSolutions(boolean var1);

    public void xsetPromptedSolutions(XmlBoolean var1);

    public void unsetPromptedSolutions();

    public boolean getShowInkAnnotation();

    public XmlBoolean xgetShowInkAnnotation();

    public boolean isSetShowInkAnnotation();

    public void setShowInkAnnotation(boolean var1);

    public void xsetShowInkAnnotation(XmlBoolean var1);

    public void unsetShowInkAnnotation();

    public boolean getBackupFile();

    public XmlBoolean xgetBackupFile();

    public boolean isSetBackupFile();

    public void setBackupFile(boolean var1);

    public void xsetBackupFile(XmlBoolean var1);

    public void unsetBackupFile();

    public boolean getSaveExternalLinkValues();

    public XmlBoolean xgetSaveExternalLinkValues();

    public boolean isSetSaveExternalLinkValues();

    public void setSaveExternalLinkValues(boolean var1);

    public void xsetSaveExternalLinkValues(XmlBoolean var1);

    public void unsetSaveExternalLinkValues();

    public STUpdateLinks.Enum getUpdateLinks();

    public STUpdateLinks xgetUpdateLinks();

    public boolean isSetUpdateLinks();

    public void setUpdateLinks(STUpdateLinks.Enum var1);

    public void xsetUpdateLinks(STUpdateLinks var1);

    public void unsetUpdateLinks();

    public String getCodeName();

    public XmlString xgetCodeName();

    public boolean isSetCodeName();

    public void setCodeName(String var1);

    public void xsetCodeName(XmlString var1);

    public void unsetCodeName();

    public boolean getHidePivotFieldList();

    public XmlBoolean xgetHidePivotFieldList();

    public boolean isSetHidePivotFieldList();

    public void setHidePivotFieldList(boolean var1);

    public void xsetHidePivotFieldList(XmlBoolean var1);

    public void unsetHidePivotFieldList();

    public boolean getShowPivotChartFilter();

    public XmlBoolean xgetShowPivotChartFilter();

    public boolean isSetShowPivotChartFilter();

    public void setShowPivotChartFilter(boolean var1);

    public void xsetShowPivotChartFilter(XmlBoolean var1);

    public void unsetShowPivotChartFilter();

    public boolean getAllowRefreshQuery();

    public XmlBoolean xgetAllowRefreshQuery();

    public boolean isSetAllowRefreshQuery();

    public void setAllowRefreshQuery(boolean var1);

    public void xsetAllowRefreshQuery(XmlBoolean var1);

    public void unsetAllowRefreshQuery();

    public boolean getPublishItems();

    public XmlBoolean xgetPublishItems();

    public boolean isSetPublishItems();

    public void setPublishItems(boolean var1);

    public void xsetPublishItems(XmlBoolean var1);

    public void unsetPublishItems();

    public boolean getCheckCompatibility();

    public XmlBoolean xgetCheckCompatibility();

    public boolean isSetCheckCompatibility();

    public void setCheckCompatibility(boolean var1);

    public void xsetCheckCompatibility(XmlBoolean var1);

    public void unsetCheckCompatibility();

    public boolean getAutoCompressPictures();

    public XmlBoolean xgetAutoCompressPictures();

    public boolean isSetAutoCompressPictures();

    public void setAutoCompressPictures(boolean var1);

    public void xsetAutoCompressPictures(XmlBoolean var1);

    public void unsetAutoCompressPictures();

    public boolean getRefreshAllConnections();

    public XmlBoolean xgetRefreshAllConnections();

    public boolean isSetRefreshAllConnections();

    public void setRefreshAllConnections(boolean var1);

    public void xsetRefreshAllConnections(XmlBoolean var1);

    public void unsetRefreshAllConnections();

    public long getDefaultThemeVersion();

    public XmlUnsignedInt xgetDefaultThemeVersion();

    public boolean isSetDefaultThemeVersion();

    public void setDefaultThemeVersion(long var1);

    public void xsetDefaultThemeVersion(XmlUnsignedInt var1);

    public void unsetDefaultThemeVersion();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTWorkbookPr.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTWorkbookPr newInstance() {
            return (CTWorkbookPr)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTWorkbookPr newInstance(XmlOptions xmlOptions) {
            return (CTWorkbookPr)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTWorkbookPr parse(String string) throws XmlException {
            return (CTWorkbookPr)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTWorkbookPr parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTWorkbookPr)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTWorkbookPr parse(File file) throws XmlException, IOException {
            return (CTWorkbookPr)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTWorkbookPr parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTWorkbookPr)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTWorkbookPr parse(URL uRL) throws XmlException, IOException {
            return (CTWorkbookPr)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTWorkbookPr parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTWorkbookPr)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTWorkbookPr parse(InputStream inputStream) throws XmlException, IOException {
            return (CTWorkbookPr)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTWorkbookPr parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTWorkbookPr)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTWorkbookPr parse(Reader reader) throws XmlException, IOException {
            return (CTWorkbookPr)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTWorkbookPr parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTWorkbookPr)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTWorkbookPr parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTWorkbookPr)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTWorkbookPr parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTWorkbookPr)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTWorkbookPr parse(Node node) throws XmlException {
            return (CTWorkbookPr)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTWorkbookPr parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTWorkbookPr)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTWorkbookPr parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTWorkbookPr)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTWorkbookPr parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTWorkbookPr)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

