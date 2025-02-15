/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.chart.CTDLbl
 *  org.openxmlformats.schemas.drawingml.x2006.chart.CTDLblPos
 */
package org.openxmlformats.schemas.drawingml.x2006.chart;

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
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChartLines;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDLbl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDLblPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumFmt;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;
import org.w3c.dom.Node;

public interface CTDLbls
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTDLbls.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctdlblsb585type");

    public List<CTDLbl> getDLblList();

    @Deprecated
    public CTDLbl[] getDLblArray();

    public CTDLbl getDLblArray(int var1);

    public int sizeOfDLblArray();

    public void setDLblArray(CTDLbl[] var1);

    public void setDLblArray(int var1, CTDLbl var2);

    public CTDLbl insertNewDLbl(int var1);

    public CTDLbl addNewDLbl();

    public void removeDLbl(int var1);

    public CTBoolean getDelete();

    public boolean isSetDelete();

    public void setDelete(CTBoolean var1);

    public CTBoolean addNewDelete();

    public void unsetDelete();

    public CTNumFmt getNumFmt();

    public boolean isSetNumFmt();

    public void setNumFmt(CTNumFmt var1);

    public CTNumFmt addNewNumFmt();

    public void unsetNumFmt();

    public CTShapeProperties getSpPr();

    public boolean isSetSpPr();

    public void setSpPr(CTShapeProperties var1);

    public CTShapeProperties addNewSpPr();

    public void unsetSpPr();

    public CTTextBody getTxPr();

    public boolean isSetTxPr();

    public void setTxPr(CTTextBody var1);

    public CTTextBody addNewTxPr();

    public void unsetTxPr();

    public CTDLblPos getDLblPos();

    public boolean isSetDLblPos();

    public void setDLblPos(CTDLblPos var1);

    public CTDLblPos addNewDLblPos();

    public void unsetDLblPos();

    public CTBoolean getShowLegendKey();

    public boolean isSetShowLegendKey();

    public void setShowLegendKey(CTBoolean var1);

    public CTBoolean addNewShowLegendKey();

    public void unsetShowLegendKey();

    public CTBoolean getShowVal();

    public boolean isSetShowVal();

    public void setShowVal(CTBoolean var1);

    public CTBoolean addNewShowVal();

    public void unsetShowVal();

    public CTBoolean getShowCatName();

    public boolean isSetShowCatName();

    public void setShowCatName(CTBoolean var1);

    public CTBoolean addNewShowCatName();

    public void unsetShowCatName();

    public CTBoolean getShowSerName();

    public boolean isSetShowSerName();

    public void setShowSerName(CTBoolean var1);

    public CTBoolean addNewShowSerName();

    public void unsetShowSerName();

    public CTBoolean getShowPercent();

    public boolean isSetShowPercent();

    public void setShowPercent(CTBoolean var1);

    public CTBoolean addNewShowPercent();

    public void unsetShowPercent();

    public CTBoolean getShowBubbleSize();

    public boolean isSetShowBubbleSize();

    public void setShowBubbleSize(CTBoolean var1);

    public CTBoolean addNewShowBubbleSize();

    public void unsetShowBubbleSize();

    public String getSeparator();

    public XmlString xgetSeparator();

    public boolean isSetSeparator();

    public void setSeparator(String var1);

    public void xsetSeparator(XmlString var1);

    public void unsetSeparator();

    public CTBoolean getShowLeaderLines();

    public boolean isSetShowLeaderLines();

    public void setShowLeaderLines(CTBoolean var1);

    public CTBoolean addNewShowLeaderLines();

    public void unsetShowLeaderLines();

    public CTChartLines getLeaderLines();

    public boolean isSetLeaderLines();

    public void setLeaderLines(CTChartLines var1);

    public CTChartLines addNewLeaderLines();

    public void unsetLeaderLines();

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
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTDLbls.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTDLbls newInstance() {
            return (CTDLbls)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTDLbls newInstance(XmlOptions xmlOptions) {
            return (CTDLbls)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTDLbls parse(String string) throws XmlException {
            return (CTDLbls)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTDLbls parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTDLbls)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTDLbls parse(File file) throws XmlException, IOException {
            return (CTDLbls)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTDLbls parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDLbls)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTDLbls parse(URL uRL) throws XmlException, IOException {
            return (CTDLbls)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTDLbls parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDLbls)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTDLbls parse(InputStream inputStream) throws XmlException, IOException {
            return (CTDLbls)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTDLbls parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDLbls)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTDLbls parse(Reader reader) throws XmlException, IOException {
            return (CTDLbls)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTDLbls parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDLbls)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTDLbls parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTDLbls)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTDLbls parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTDLbls)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTDLbls parse(Node node) throws XmlException {
            return (CTDLbls)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTDLbls parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTDLbls)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTDLbls parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTDLbls)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTDLbls parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTDLbls)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

