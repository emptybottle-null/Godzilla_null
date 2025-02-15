/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableFormula
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.STTotalsRowFunction
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
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableFormula;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTXmlColumnPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STDxfId;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STTotalsRowFunction;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring;
import org.w3c.dom.Node;

public interface CTTableColumn
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTTableColumn.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cttablecolumn08a3type");

    public CTTableFormula getCalculatedColumnFormula();

    public boolean isSetCalculatedColumnFormula();

    public void setCalculatedColumnFormula(CTTableFormula var1);

    public CTTableFormula addNewCalculatedColumnFormula();

    public void unsetCalculatedColumnFormula();

    public CTTableFormula getTotalsRowFormula();

    public boolean isSetTotalsRowFormula();

    public void setTotalsRowFormula(CTTableFormula var1);

    public CTTableFormula addNewTotalsRowFormula();

    public void unsetTotalsRowFormula();

    public CTXmlColumnPr getXmlColumnPr();

    public boolean isSetXmlColumnPr();

    public void setXmlColumnPr(CTXmlColumnPr var1);

    public CTXmlColumnPr addNewXmlColumnPr();

    public void unsetXmlColumnPr();

    public CTExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTExtensionList var1);

    public CTExtensionList addNewExtLst();

    public void unsetExtLst();

    public long getId();

    public XmlUnsignedInt xgetId();

    public void setId(long var1);

    public void xsetId(XmlUnsignedInt var1);

    public String getUniqueName();

    public STXstring xgetUniqueName();

    public boolean isSetUniqueName();

    public void setUniqueName(String var1);

    public void xsetUniqueName(STXstring var1);

    public void unsetUniqueName();

    public String getName();

    public STXstring xgetName();

    public void setName(String var1);

    public void xsetName(STXstring var1);

    public STTotalsRowFunction.Enum getTotalsRowFunction();

    public STTotalsRowFunction xgetTotalsRowFunction();

    public boolean isSetTotalsRowFunction();

    public void setTotalsRowFunction(STTotalsRowFunction.Enum var1);

    public void xsetTotalsRowFunction(STTotalsRowFunction var1);

    public void unsetTotalsRowFunction();

    public String getTotalsRowLabel();

    public STXstring xgetTotalsRowLabel();

    public boolean isSetTotalsRowLabel();

    public void setTotalsRowLabel(String var1);

    public void xsetTotalsRowLabel(STXstring var1);

    public void unsetTotalsRowLabel();

    public long getQueryTableFieldId();

    public XmlUnsignedInt xgetQueryTableFieldId();

    public boolean isSetQueryTableFieldId();

    public void setQueryTableFieldId(long var1);

    public void xsetQueryTableFieldId(XmlUnsignedInt var1);

    public void unsetQueryTableFieldId();

    public long getHeaderRowDxfId();

    public STDxfId xgetHeaderRowDxfId();

    public boolean isSetHeaderRowDxfId();

    public void setHeaderRowDxfId(long var1);

    public void xsetHeaderRowDxfId(STDxfId var1);

    public void unsetHeaderRowDxfId();

    public long getDataDxfId();

    public STDxfId xgetDataDxfId();

    public boolean isSetDataDxfId();

    public void setDataDxfId(long var1);

    public void xsetDataDxfId(STDxfId var1);

    public void unsetDataDxfId();

    public long getTotalsRowDxfId();

    public STDxfId xgetTotalsRowDxfId();

    public boolean isSetTotalsRowDxfId();

    public void setTotalsRowDxfId(long var1);

    public void xsetTotalsRowDxfId(STDxfId var1);

    public void unsetTotalsRowDxfId();

    public String getHeaderRowCellStyle();

    public STXstring xgetHeaderRowCellStyle();

    public boolean isSetHeaderRowCellStyle();

    public void setHeaderRowCellStyle(String var1);

    public void xsetHeaderRowCellStyle(STXstring var1);

    public void unsetHeaderRowCellStyle();

    public String getDataCellStyle();

    public STXstring xgetDataCellStyle();

    public boolean isSetDataCellStyle();

    public void setDataCellStyle(String var1);

    public void xsetDataCellStyle(STXstring var1);

    public void unsetDataCellStyle();

    public String getTotalsRowCellStyle();

    public STXstring xgetTotalsRowCellStyle();

    public boolean isSetTotalsRowCellStyle();

    public void setTotalsRowCellStyle(String var1);

    public void xsetTotalsRowCellStyle(STXstring var1);

    public void unsetTotalsRowCellStyle();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTTableColumn.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTTableColumn newInstance() {
            return (CTTableColumn)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTTableColumn newInstance(XmlOptions xmlOptions) {
            return (CTTableColumn)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTTableColumn parse(String string) throws XmlException {
            return (CTTableColumn)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTTableColumn parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTTableColumn)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTTableColumn parse(File file) throws XmlException, IOException {
            return (CTTableColumn)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTTableColumn parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableColumn)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTTableColumn parse(URL uRL) throws XmlException, IOException {
            return (CTTableColumn)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTTableColumn parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableColumn)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTTableColumn parse(InputStream inputStream) throws XmlException, IOException {
            return (CTTableColumn)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTTableColumn parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableColumn)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTTableColumn parse(Reader reader) throws XmlException, IOException {
            return (CTTableColumn)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTTableColumn parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableColumn)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTTableColumn parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTTableColumn)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTTableColumn parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTTableColumn)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTTableColumn parse(Node node) throws XmlException {
            return (CTTableColumn)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTTableColumn parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTTableColumn)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTTableColumn parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTTableColumn)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTTableColumn parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTTableColumn)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

