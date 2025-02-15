/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomWorkbookViews
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileRecoveryPr
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileSharing
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileVersion
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFunctionGroups
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleSize
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSmartTagPr
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSmartTagTypes
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishObjects
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishing
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.List;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBookViews;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalcPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomWorkbookViews;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedNames;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalReferences;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileRecoveryPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileSharing;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileVersion;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFunctionGroups;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleSize;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotCaches;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheets;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSmartTagPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSmartTagTypes;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishObjects;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishing;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbookPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbookProtection;
import org.w3c.dom.Node;

public interface CTWorkbook
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTWorkbook.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctworkbook83c3type");

    public CTFileVersion getFileVersion();

    public boolean isSetFileVersion();

    public void setFileVersion(CTFileVersion var1);

    public CTFileVersion addNewFileVersion();

    public void unsetFileVersion();

    public CTFileSharing getFileSharing();

    public boolean isSetFileSharing();

    public void setFileSharing(CTFileSharing var1);

    public CTFileSharing addNewFileSharing();

    public void unsetFileSharing();

    public CTWorkbookPr getWorkbookPr();

    public boolean isSetWorkbookPr();

    public void setWorkbookPr(CTWorkbookPr var1);

    public CTWorkbookPr addNewWorkbookPr();

    public void unsetWorkbookPr();

    public CTWorkbookProtection getWorkbookProtection();

    public boolean isSetWorkbookProtection();

    public void setWorkbookProtection(CTWorkbookProtection var1);

    public CTWorkbookProtection addNewWorkbookProtection();

    public void unsetWorkbookProtection();

    public CTBookViews getBookViews();

    public boolean isSetBookViews();

    public void setBookViews(CTBookViews var1);

    public CTBookViews addNewBookViews();

    public void unsetBookViews();

    public CTSheets getSheets();

    public void setSheets(CTSheets var1);

    public CTSheets addNewSheets();

    public CTFunctionGroups getFunctionGroups();

    public boolean isSetFunctionGroups();

    public void setFunctionGroups(CTFunctionGroups var1);

    public CTFunctionGroups addNewFunctionGroups();

    public void unsetFunctionGroups();

    public CTExternalReferences getExternalReferences();

    public boolean isSetExternalReferences();

    public void setExternalReferences(CTExternalReferences var1);

    public CTExternalReferences addNewExternalReferences();

    public void unsetExternalReferences();

    public CTDefinedNames getDefinedNames();

    public boolean isSetDefinedNames();

    public void setDefinedNames(CTDefinedNames var1);

    public CTDefinedNames addNewDefinedNames();

    public void unsetDefinedNames();

    public CTCalcPr getCalcPr();

    public boolean isSetCalcPr();

    public void setCalcPr(CTCalcPr var1);

    public CTCalcPr addNewCalcPr();

    public void unsetCalcPr();

    public CTOleSize getOleSize();

    public boolean isSetOleSize();

    public void setOleSize(CTOleSize var1);

    public CTOleSize addNewOleSize();

    public void unsetOleSize();

    public CTCustomWorkbookViews getCustomWorkbookViews();

    public boolean isSetCustomWorkbookViews();

    public void setCustomWorkbookViews(CTCustomWorkbookViews var1);

    public CTCustomWorkbookViews addNewCustomWorkbookViews();

    public void unsetCustomWorkbookViews();

    public CTPivotCaches getPivotCaches();

    public boolean isSetPivotCaches();

    public void setPivotCaches(CTPivotCaches var1);

    public CTPivotCaches addNewPivotCaches();

    public void unsetPivotCaches();

    public CTSmartTagPr getSmartTagPr();

    public boolean isSetSmartTagPr();

    public void setSmartTagPr(CTSmartTagPr var1);

    public CTSmartTagPr addNewSmartTagPr();

    public void unsetSmartTagPr();

    public CTSmartTagTypes getSmartTagTypes();

    public boolean isSetSmartTagTypes();

    public void setSmartTagTypes(CTSmartTagTypes var1);

    public CTSmartTagTypes addNewSmartTagTypes();

    public void unsetSmartTagTypes();

    public CTWebPublishing getWebPublishing();

    public boolean isSetWebPublishing();

    public void setWebPublishing(CTWebPublishing var1);

    public CTWebPublishing addNewWebPublishing();

    public void unsetWebPublishing();

    public List<CTFileRecoveryPr> getFileRecoveryPrList();

    @Deprecated
    public CTFileRecoveryPr[] getFileRecoveryPrArray();

    public CTFileRecoveryPr getFileRecoveryPrArray(int var1);

    public int sizeOfFileRecoveryPrArray();

    public void setFileRecoveryPrArray(CTFileRecoveryPr[] var1);

    public void setFileRecoveryPrArray(int var1, CTFileRecoveryPr var2);

    public CTFileRecoveryPr insertNewFileRecoveryPr(int var1);

    public CTFileRecoveryPr addNewFileRecoveryPr();

    public void removeFileRecoveryPr(int var1);

    public CTWebPublishObjects getWebPublishObjects();

    public boolean isSetWebPublishObjects();

    public void setWebPublishObjects(CTWebPublishObjects var1);

    public CTWebPublishObjects addNewWebPublishObjects();

    public void unsetWebPublishObjects();

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
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTWorkbook.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTWorkbook newInstance() {
            return (CTWorkbook)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTWorkbook newInstance(XmlOptions xmlOptions) {
            return (CTWorkbook)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTWorkbook parse(String string) throws XmlException {
            return (CTWorkbook)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTWorkbook parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTWorkbook)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTWorkbook parse(File file) throws XmlException, IOException {
            return (CTWorkbook)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTWorkbook parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTWorkbook)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTWorkbook parse(URL uRL) throws XmlException, IOException {
            return (CTWorkbook)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTWorkbook parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTWorkbook)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTWorkbook parse(InputStream inputStream) throws XmlException, IOException {
            return (CTWorkbook)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTWorkbook parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTWorkbook)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTWorkbook parse(Reader reader) throws XmlException, IOException {
            return (CTWorkbook)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTWorkbook parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTWorkbook)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTWorkbook parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTWorkbook)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTWorkbook parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTWorkbook)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTWorkbook parse(Node node) throws XmlException {
            return (CTWorkbook)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTWorkbook parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTWorkbook)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTWorkbook parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTWorkbook)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTWorkbook parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTWorkbook)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

