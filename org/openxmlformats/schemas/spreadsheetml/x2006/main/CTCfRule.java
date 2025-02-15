/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.STTimePeriod
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
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColorScale;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBar;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconSet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCfType;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STConditionalFormattingOperator;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STDxfId;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STFormula;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STTimePeriod;
import org.w3c.dom.Node;

public interface CTCfRule
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTCfRule.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctcfrule3548type");

    public List<String> getFormulaList();

    @Deprecated
    public String[] getFormulaArray();

    public String getFormulaArray(int var1);

    public List<STFormula> xgetFormulaList();

    @Deprecated
    public STFormula[] xgetFormulaArray();

    public STFormula xgetFormulaArray(int var1);

    public int sizeOfFormulaArray();

    public void setFormulaArray(String[] var1);

    public void setFormulaArray(int var1, String var2);

    public void xsetFormulaArray(STFormula[] var1);

    public void xsetFormulaArray(int var1, STFormula var2);

    public void insertFormula(int var1, String var2);

    public void addFormula(String var1);

    public STFormula insertNewFormula(int var1);

    public STFormula addNewFormula();

    public void removeFormula(int var1);

    public CTColorScale getColorScale();

    public boolean isSetColorScale();

    public void setColorScale(CTColorScale var1);

    public CTColorScale addNewColorScale();

    public void unsetColorScale();

    public CTDataBar getDataBar();

    public boolean isSetDataBar();

    public void setDataBar(CTDataBar var1);

    public CTDataBar addNewDataBar();

    public void unsetDataBar();

    public CTIconSet getIconSet();

    public boolean isSetIconSet();

    public void setIconSet(CTIconSet var1);

    public CTIconSet addNewIconSet();

    public void unsetIconSet();

    public CTExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTExtensionList var1);

    public CTExtensionList addNewExtLst();

    public void unsetExtLst();

    public STCfType.Enum getType();

    public STCfType xgetType();

    public boolean isSetType();

    public void setType(STCfType.Enum var1);

    public void xsetType(STCfType var1);

    public void unsetType();

    public long getDxfId();

    public STDxfId xgetDxfId();

    public boolean isSetDxfId();

    public void setDxfId(long var1);

    public void xsetDxfId(STDxfId var1);

    public void unsetDxfId();

    public int getPriority();

    public XmlInt xgetPriority();

    public void setPriority(int var1);

    public void xsetPriority(XmlInt var1);

    public boolean getStopIfTrue();

    public XmlBoolean xgetStopIfTrue();

    public boolean isSetStopIfTrue();

    public void setStopIfTrue(boolean var1);

    public void xsetStopIfTrue(XmlBoolean var1);

    public void unsetStopIfTrue();

    public boolean getAboveAverage();

    public XmlBoolean xgetAboveAverage();

    public boolean isSetAboveAverage();

    public void setAboveAverage(boolean var1);

    public void xsetAboveAverage(XmlBoolean var1);

    public void unsetAboveAverage();

    public boolean getPercent();

    public XmlBoolean xgetPercent();

    public boolean isSetPercent();

    public void setPercent(boolean var1);

    public void xsetPercent(XmlBoolean var1);

    public void unsetPercent();

    public boolean getBottom();

    public XmlBoolean xgetBottom();

    public boolean isSetBottom();

    public void setBottom(boolean var1);

    public void xsetBottom(XmlBoolean var1);

    public void unsetBottom();

    public STConditionalFormattingOperator.Enum getOperator();

    public STConditionalFormattingOperator xgetOperator();

    public boolean isSetOperator();

    public void setOperator(STConditionalFormattingOperator.Enum var1);

    public void xsetOperator(STConditionalFormattingOperator var1);

    public void unsetOperator();

    public String getText();

    public XmlString xgetText();

    public boolean isSetText();

    public void setText(String var1);

    public void xsetText(XmlString var1);

    public void unsetText();

    public STTimePeriod.Enum getTimePeriod();

    public STTimePeriod xgetTimePeriod();

    public boolean isSetTimePeriod();

    public void setTimePeriod(STTimePeriod.Enum var1);

    public void xsetTimePeriod(STTimePeriod var1);

    public void unsetTimePeriod();

    public long getRank();

    public XmlUnsignedInt xgetRank();

    public boolean isSetRank();

    public void setRank(long var1);

    public void xsetRank(XmlUnsignedInt var1);

    public void unsetRank();

    public int getStdDev();

    public XmlInt xgetStdDev();

    public boolean isSetStdDev();

    public void setStdDev(int var1);

    public void xsetStdDev(XmlInt var1);

    public void unsetStdDev();

    public boolean getEqualAverage();

    public XmlBoolean xgetEqualAverage();

    public boolean isSetEqualAverage();

    public void setEqualAverage(boolean var1);

    public void xsetEqualAverage(XmlBoolean var1);

    public void unsetEqualAverage();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTCfRule.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTCfRule newInstance() {
            return (CTCfRule)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTCfRule newInstance(XmlOptions xmlOptions) {
            return (CTCfRule)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTCfRule parse(String string) throws XmlException {
            return (CTCfRule)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTCfRule parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTCfRule)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTCfRule parse(File file) throws XmlException, IOException {
            return (CTCfRule)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTCfRule parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCfRule)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTCfRule parse(URL uRL) throws XmlException, IOException {
            return (CTCfRule)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTCfRule parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCfRule)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTCfRule parse(InputStream inputStream) throws XmlException, IOException {
            return (CTCfRule)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTCfRule parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCfRule)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTCfRule parse(Reader reader) throws XmlException, IOException {
            return (CTCfRule)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTCfRule parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCfRule)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTCfRule parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTCfRule)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTCfRule parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTCfRule)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTCfRule parse(Node node) throws XmlException {
            return (CTCfRule)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTCfRule parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTCfRule)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTCfRule parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTCfRule)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTCfRule parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTCfRule)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

