/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.chart.CTDPt
 *  org.openxmlformats.schemas.drawingml.x2006.chart.CTTrendline
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
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDLbls;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDPt;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTMarker;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTrendline;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.w3c.dom.Node;

public interface CTScatterSer
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTScatterSer.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctscatterser2f7atype");

    public CTUnsignedInt getIdx();

    public void setIdx(CTUnsignedInt var1);

    public CTUnsignedInt addNewIdx();

    public CTUnsignedInt getOrder();

    public void setOrder(CTUnsignedInt var1);

    public CTUnsignedInt addNewOrder();

    public CTSerTx getTx();

    public boolean isSetTx();

    public void setTx(CTSerTx var1);

    public CTSerTx addNewTx();

    public void unsetTx();

    public CTShapeProperties getSpPr();

    public boolean isSetSpPr();

    public void setSpPr(CTShapeProperties var1);

    public CTShapeProperties addNewSpPr();

    public void unsetSpPr();

    public CTMarker getMarker();

    public boolean isSetMarker();

    public void setMarker(CTMarker var1);

    public CTMarker addNewMarker();

    public void unsetMarker();

    public List<CTDPt> getDPtList();

    @Deprecated
    public CTDPt[] getDPtArray();

    public CTDPt getDPtArray(int var1);

    public int sizeOfDPtArray();

    public void setDPtArray(CTDPt[] var1);

    public void setDPtArray(int var1, CTDPt var2);

    public CTDPt insertNewDPt(int var1);

    public CTDPt addNewDPt();

    public void removeDPt(int var1);

    public CTDLbls getDLbls();

    public boolean isSetDLbls();

    public void setDLbls(CTDLbls var1);

    public CTDLbls addNewDLbls();

    public void unsetDLbls();

    public List<CTTrendline> getTrendlineList();

    @Deprecated
    public CTTrendline[] getTrendlineArray();

    public CTTrendline getTrendlineArray(int var1);

    public int sizeOfTrendlineArray();

    public void setTrendlineArray(CTTrendline[] var1);

    public void setTrendlineArray(int var1, CTTrendline var2);

    public CTTrendline insertNewTrendline(int var1);

    public CTTrendline addNewTrendline();

    public void removeTrendline(int var1);

    public List<CTErrBars> getErrBarsList();

    @Deprecated
    public CTErrBars[] getErrBarsArray();

    public CTErrBars getErrBarsArray(int var1);

    public int sizeOfErrBarsArray();

    public void setErrBarsArray(CTErrBars[] var1);

    public void setErrBarsArray(int var1, CTErrBars var2);

    public CTErrBars insertNewErrBars(int var1);

    public CTErrBars addNewErrBars();

    public void removeErrBars(int var1);

    public CTAxDataSource getXVal();

    public boolean isSetXVal();

    public void setXVal(CTAxDataSource var1);

    public CTAxDataSource addNewXVal();

    public void unsetXVal();

    public CTNumDataSource getYVal();

    public boolean isSetYVal();

    public void setYVal(CTNumDataSource var1);

    public CTNumDataSource addNewYVal();

    public void unsetYVal();

    public CTBoolean getSmooth();

    public boolean isSetSmooth();

    public void setSmooth(CTBoolean var1);

    public CTBoolean addNewSmooth();

    public void unsetSmooth();

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
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTScatterSer.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTScatterSer newInstance() {
            return (CTScatterSer)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTScatterSer newInstance(XmlOptions xmlOptions) {
            return (CTScatterSer)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTScatterSer parse(String string) throws XmlException {
            return (CTScatterSer)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTScatterSer parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTScatterSer)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTScatterSer parse(File file) throws XmlException, IOException {
            return (CTScatterSer)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTScatterSer parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTScatterSer)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTScatterSer parse(URL uRL) throws XmlException, IOException {
            return (CTScatterSer)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTScatterSer parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTScatterSer)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTScatterSer parse(InputStream inputStream) throws XmlException, IOException {
            return (CTScatterSer)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTScatterSer parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTScatterSer)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTScatterSer parse(Reader reader) throws XmlException, IOException {
            return (CTScatterSer)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTScatterSer parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTScatterSer)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTScatterSer parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTScatterSer)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTScatterSer parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTScatterSer)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTScatterSer parse(Node node) throws XmlException {
            return (CTScatterSer)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTScatterSer parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTScatterSer)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTScatterSer parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTScatterSer)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTScatterSer parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTScatterSer)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

