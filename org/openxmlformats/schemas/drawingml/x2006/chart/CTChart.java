/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.chart.CTPivotFmts
 */
package org.openxmlformats.schemas.drawingml.x2006.chart;

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
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDispBlanksAs;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLegend;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPivotFmts;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSurface;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTitle;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D;
import org.w3c.dom.Node;

public interface CTChart
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTChart.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctchartc108type");

    public CTTitle getTitle();

    public boolean isSetTitle();

    public void setTitle(CTTitle var1);

    public CTTitle addNewTitle();

    public void unsetTitle();

    public CTBoolean getAutoTitleDeleted();

    public boolean isSetAutoTitleDeleted();

    public void setAutoTitleDeleted(CTBoolean var1);

    public CTBoolean addNewAutoTitleDeleted();

    public void unsetAutoTitleDeleted();

    public CTPivotFmts getPivotFmts();

    public boolean isSetPivotFmts();

    public void setPivotFmts(CTPivotFmts var1);

    public CTPivotFmts addNewPivotFmts();

    public void unsetPivotFmts();

    public CTView3D getView3D();

    public boolean isSetView3D();

    public void setView3D(CTView3D var1);

    public CTView3D addNewView3D();

    public void unsetView3D();

    public CTSurface getFloor();

    public boolean isSetFloor();

    public void setFloor(CTSurface var1);

    public CTSurface addNewFloor();

    public void unsetFloor();

    public CTSurface getSideWall();

    public boolean isSetSideWall();

    public void setSideWall(CTSurface var1);

    public CTSurface addNewSideWall();

    public void unsetSideWall();

    public CTSurface getBackWall();

    public boolean isSetBackWall();

    public void setBackWall(CTSurface var1);

    public CTSurface addNewBackWall();

    public void unsetBackWall();

    public CTPlotArea getPlotArea();

    public void setPlotArea(CTPlotArea var1);

    public CTPlotArea addNewPlotArea();

    public CTLegend getLegend();

    public boolean isSetLegend();

    public void setLegend(CTLegend var1);

    public CTLegend addNewLegend();

    public void unsetLegend();

    public CTBoolean getPlotVisOnly();

    public boolean isSetPlotVisOnly();

    public void setPlotVisOnly(CTBoolean var1);

    public CTBoolean addNewPlotVisOnly();

    public void unsetPlotVisOnly();

    public CTDispBlanksAs getDispBlanksAs();

    public boolean isSetDispBlanksAs();

    public void setDispBlanksAs(CTDispBlanksAs var1);

    public CTDispBlanksAs addNewDispBlanksAs();

    public void unsetDispBlanksAs();

    public CTBoolean getShowDLblsOverMax();

    public boolean isSetShowDLblsOverMax();

    public void setShowDLblsOverMax(CTBoolean var1);

    public CTBoolean addNewShowDLblsOverMax();

    public void unsetShowDLblsOverMax();

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
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTChart.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTChart newInstance() {
            return (CTChart)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTChart newInstance(XmlOptions xmlOptions) {
            return (CTChart)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTChart parse(String string) throws XmlException {
            return (CTChart)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTChart parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTChart)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTChart parse(File file) throws XmlException, IOException {
            return (CTChart)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTChart parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTChart)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTChart parse(URL uRL) throws XmlException, IOException {
            return (CTChart)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTChart parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTChart)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTChart parse(InputStream inputStream) throws XmlException, IOException {
            return (CTChart)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTChart parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTChart)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTChart parse(Reader reader) throws XmlException, IOException {
            return (CTChart)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTChart parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTChart)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTChart parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTChart)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTChart parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTChart)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTChart parse(Node node) throws XmlException {
            return (CTChart)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTChart parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTChart)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTChart parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTChart)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTChart parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTChart)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

