/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCnf
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;

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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCnf;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHeight;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.w3c.dom.Node;

public interface CTTrPrBase
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTTrPrBase.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cttrprbase5d77type");

    public List<CTCnf> getCnfStyleList();

    @Deprecated
    public CTCnf[] getCnfStyleArray();

    public CTCnf getCnfStyleArray(int var1);

    public int sizeOfCnfStyleArray();

    public void setCnfStyleArray(CTCnf[] var1);

    public void setCnfStyleArray(int var1, CTCnf var2);

    public CTCnf insertNewCnfStyle(int var1);

    public CTCnf addNewCnfStyle();

    public void removeCnfStyle(int var1);

    public List<CTDecimalNumber> getDivIdList();

    @Deprecated
    public CTDecimalNumber[] getDivIdArray();

    public CTDecimalNumber getDivIdArray(int var1);

    public int sizeOfDivIdArray();

    public void setDivIdArray(CTDecimalNumber[] var1);

    public void setDivIdArray(int var1, CTDecimalNumber var2);

    public CTDecimalNumber insertNewDivId(int var1);

    public CTDecimalNumber addNewDivId();

    public void removeDivId(int var1);

    public List<CTDecimalNumber> getGridBeforeList();

    @Deprecated
    public CTDecimalNumber[] getGridBeforeArray();

    public CTDecimalNumber getGridBeforeArray(int var1);

    public int sizeOfGridBeforeArray();

    public void setGridBeforeArray(CTDecimalNumber[] var1);

    public void setGridBeforeArray(int var1, CTDecimalNumber var2);

    public CTDecimalNumber insertNewGridBefore(int var1);

    public CTDecimalNumber addNewGridBefore();

    public void removeGridBefore(int var1);

    public List<CTDecimalNumber> getGridAfterList();

    @Deprecated
    public CTDecimalNumber[] getGridAfterArray();

    public CTDecimalNumber getGridAfterArray(int var1);

    public int sizeOfGridAfterArray();

    public void setGridAfterArray(CTDecimalNumber[] var1);

    public void setGridAfterArray(int var1, CTDecimalNumber var2);

    public CTDecimalNumber insertNewGridAfter(int var1);

    public CTDecimalNumber addNewGridAfter();

    public void removeGridAfter(int var1);

    public List<CTTblWidth> getWBeforeList();

    @Deprecated
    public CTTblWidth[] getWBeforeArray();

    public CTTblWidth getWBeforeArray(int var1);

    public int sizeOfWBeforeArray();

    public void setWBeforeArray(CTTblWidth[] var1);

    public void setWBeforeArray(int var1, CTTblWidth var2);

    public CTTblWidth insertNewWBefore(int var1);

    public CTTblWidth addNewWBefore();

    public void removeWBefore(int var1);

    public List<CTTblWidth> getWAfterList();

    @Deprecated
    public CTTblWidth[] getWAfterArray();

    public CTTblWidth getWAfterArray(int var1);

    public int sizeOfWAfterArray();

    public void setWAfterArray(CTTblWidth[] var1);

    public void setWAfterArray(int var1, CTTblWidth var2);

    public CTTblWidth insertNewWAfter(int var1);

    public CTTblWidth addNewWAfter();

    public void removeWAfter(int var1);

    public List<CTOnOff> getCantSplitList();

    @Deprecated
    public CTOnOff[] getCantSplitArray();

    public CTOnOff getCantSplitArray(int var1);

    public int sizeOfCantSplitArray();

    public void setCantSplitArray(CTOnOff[] var1);

    public void setCantSplitArray(int var1, CTOnOff var2);

    public CTOnOff insertNewCantSplit(int var1);

    public CTOnOff addNewCantSplit();

    public void removeCantSplit(int var1);

    public List<CTHeight> getTrHeightList();

    @Deprecated
    public CTHeight[] getTrHeightArray();

    public CTHeight getTrHeightArray(int var1);

    public int sizeOfTrHeightArray();

    public void setTrHeightArray(CTHeight[] var1);

    public void setTrHeightArray(int var1, CTHeight var2);

    public CTHeight insertNewTrHeight(int var1);

    public CTHeight addNewTrHeight();

    public void removeTrHeight(int var1);

    public List<CTOnOff> getTblHeaderList();

    @Deprecated
    public CTOnOff[] getTblHeaderArray();

    public CTOnOff getTblHeaderArray(int var1);

    public int sizeOfTblHeaderArray();

    public void setTblHeaderArray(CTOnOff[] var1);

    public void setTblHeaderArray(int var1, CTOnOff var2);

    public CTOnOff insertNewTblHeader(int var1);

    public CTOnOff addNewTblHeader();

    public void removeTblHeader(int var1);

    public List<CTTblWidth> getTblCellSpacingList();

    @Deprecated
    public CTTblWidth[] getTblCellSpacingArray();

    public CTTblWidth getTblCellSpacingArray(int var1);

    public int sizeOfTblCellSpacingArray();

    public void setTblCellSpacingArray(CTTblWidth[] var1);

    public void setTblCellSpacingArray(int var1, CTTblWidth var2);

    public CTTblWidth insertNewTblCellSpacing(int var1);

    public CTTblWidth addNewTblCellSpacing();

    public void removeTblCellSpacing(int var1);

    public List<CTJc> getJcList();

    @Deprecated
    public CTJc[] getJcArray();

    public CTJc getJcArray(int var1);

    public int sizeOfJcArray();

    public void setJcArray(CTJc[] var1);

    public void setJcArray(int var1, CTJc var2);

    public CTJc insertNewJc(int var1);

    public CTJc addNewJc();

    public void removeJc(int var1);

    public List<CTOnOff> getHiddenList();

    @Deprecated
    public CTOnOff[] getHiddenArray();

    public CTOnOff getHiddenArray(int var1);

    public int sizeOfHiddenArray();

    public void setHiddenArray(CTOnOff[] var1);

    public void setHiddenArray(int var1, CTOnOff var2);

    public CTOnOff insertNewHidden(int var1);

    public CTOnOff addNewHidden();

    public void removeHidden(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTTrPrBase.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTTrPrBase newInstance() {
            return (CTTrPrBase)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTTrPrBase newInstance(XmlOptions xmlOptions) {
            return (CTTrPrBase)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTTrPrBase parse(String string) throws XmlException {
            return (CTTrPrBase)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTTrPrBase parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTTrPrBase)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTTrPrBase parse(File file) throws XmlException, IOException {
            return (CTTrPrBase)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTTrPrBase parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTrPrBase)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTTrPrBase parse(URL uRL) throws XmlException, IOException {
            return (CTTrPrBase)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTTrPrBase parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTrPrBase)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTTrPrBase parse(InputStream inputStream) throws XmlException, IOException {
            return (CTTrPrBase)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTTrPrBase parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTrPrBase)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTTrPrBase parse(Reader reader) throws XmlException, IOException {
            return (CTTrPrBase)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTTrPrBase parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTrPrBase)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTTrPrBase parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTTrPrBase)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTTrPrBase parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTTrPrBase)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTTrPrBase parse(Node node) throws XmlException {
            return (CTTrPrBase)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTTrPrBase parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTTrPrBase)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTTrPrBase parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTTrPrBase)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTTrPrBase parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTTrPrBase)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

