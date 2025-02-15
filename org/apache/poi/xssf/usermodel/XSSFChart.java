/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.ooxml.POIXMLFactory;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.ss.usermodel.Chart;
import org.apache.poi.ss.usermodel.charts.AxisPosition;
import org.apache.poi.ss.usermodel.charts.ChartAxis;
import org.apache.poi.ss.usermodel.charts.ChartAxisFactory;
import org.apache.poi.ss.usermodel.charts.ChartData;
import org.apache.poi.util.Removal;
import org.apache.poi.xddf.usermodel.chart.XDDFChart;
import org.apache.poi.xssf.usermodel.XSSFGraphicFrame;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.charts.XSSFCategoryAxis;
import org.apache.poi.xssf.usermodel.charts.XSSFChartAxis;
import org.apache.poi.xssf.usermodel.charts.XSSFChartDataFactory;
import org.apache.poi.xssf.usermodel.charts.XSSFChartLegend;
import org.apache.poi.xssf.usermodel.charts.XSSFDateAxis;
import org.apache.poi.xssf.usermodel.charts.XSSFManualLayout;
import org.apache.poi.xssf.usermodel.charts.XSSFValueAxis;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTCatAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPageMargins;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPrintSettings;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrRef;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTitle;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTValAx;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public final class XSSFChart
extends XDDFChart
implements Chart,
ChartAxisFactory {
    private XSSFGraphicFrame frame;
    @Deprecated
    @Removal(version="4.2")
    List<XSSFChartAxis> axis = new ArrayList<XSSFChartAxis>();

    protected XSSFChart() {
        this.createChart();
    }

    protected XSSFChart(PackagePart part) throws IOException, XmlException {
        super(part);
    }

    @Override
    protected POIXMLRelation getChartRelation() {
        return null;
    }

    @Override
    protected POIXMLRelation getChartWorkbookRelation() {
        return null;
    }

    @Override
    protected POIXMLFactory getChartFactory() {
        return null;
    }

    private void createChart() {
        CTPlotArea plotArea = this.getCTPlotArea();
        plotArea.addNewLayout();
        this.chart.addNewPlotVisOnly().setVal(true);
        CTPrintSettings printSettings = this.chartSpace.addNewPrintSettings();
        printSettings.addNewHeaderFooter();
        CTPageMargins pageMargins = printSettings.addNewPageMargins();
        pageMargins.setB(0.75);
        pageMargins.setL(0.7);
        pageMargins.setR(0.7);
        pageMargins.setT(0.75);
        pageMargins.setHeader(0.3);
        pageMargins.setFooter(0.3);
        printSettings.addNewPageSetup();
    }

    @Override
    protected void commit() throws IOException {
        XmlOptions xmlOptions = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        xmlOptions.setSaveSyntheticDocumentElement(new QName(CTChartSpace.type.getName().getNamespaceURI(), "chartSpace", "c"));
        PackagePart part = this.getPackagePart();
        try (OutputStream out = part.getOutputStream();){
            this.chartSpace.save(out, xmlOptions);
        }
    }

    public XSSFGraphicFrame getGraphicFrame() {
        return this.frame;
    }

    protected void setGraphicFrame(XSSFGraphicFrame frame) {
        this.frame = frame;
    }

    @Override
    @Deprecated
    @Removal(version="4.2")
    public XSSFChartDataFactory getChartDataFactory() {
        return XSSFChartDataFactory.getInstance();
    }

    @Override
    @Deprecated
    @Removal(version="4.2")
    public XSSFChart getChartAxisFactory() {
        return this;
    }

    @Override
    @Deprecated
    @Removal(version="4.2")
    public void plot(ChartData data, ChartAxis ... chartAxis) {
        data.fillChart(this, chartAxis);
    }

    @Override
    @Deprecated
    @Removal(version="4.2")
    public XSSFValueAxis createValueAxis(AxisPosition pos) {
        long id = this.axis.size() + 1;
        XSSFValueAxis valueAxis = new XSSFValueAxis(this, id, pos);
        if (this.axis.size() == 1) {
            ChartAxis ax = this.axis.get(0);
            ax.crossAxis(valueAxis);
            valueAxis.crossAxis(ax);
        }
        this.axis.add(valueAxis);
        return valueAxis;
    }

    @Override
    @Deprecated
    @Removal(version="4.2")
    public XSSFCategoryAxis createCategoryAxis(AxisPosition pos) {
        long id = this.axis.size() + 1;
        XSSFCategoryAxis categoryAxis = new XSSFCategoryAxis(this, id, pos);
        if (this.axis.size() == 1) {
            ChartAxis ax = this.axis.get(0);
            ax.crossAxis(categoryAxis);
            categoryAxis.crossAxis(ax);
        }
        this.axis.add(categoryAxis);
        return categoryAxis;
    }

    @Override
    @Deprecated
    @Removal(version="4.2")
    public XSSFDateAxis createDateAxis(AxisPosition pos) {
        long id = this.axis.size() + 1;
        XSSFDateAxis dateAxis = new XSSFDateAxis(this, id, pos);
        if (this.axis.size() == 1) {
            ChartAxis ax = this.axis.get(0);
            ax.crossAxis(dateAxis);
            dateAxis.crossAxis(ax);
        }
        this.axis.add(dateAxis);
        return dateAxis;
    }

    @Deprecated
    @Removal(version="4.2")
    public List<? extends XSSFChartAxis> getAxis() {
        if (this.axis.isEmpty() && this.hasAxis()) {
            this.parseAxis();
        }
        return this.axis;
    }

    @Override
    @Deprecated
    @Removal(version="4.2")
    public XSSFManualLayout getManualLayout() {
        return new XSSFManualLayout(this);
    }

    public XSSFRichTextString getTitleText() {
        XmlObject[] t;
        if (!this.chart.isSetTitle()) {
            return null;
        }
        CTTitle title = this.chart.getTitle();
        StringBuilder text = new StringBuilder(64);
        for (XmlObject element : t = title.selectPath("declare namespace a='http://schemas.openxmlformats.org/drawingml/2006/main' .//a:t")) {
            NodeList kids = element.getDomNode().getChildNodes();
            int count = kids.getLength();
            for (int n = 0; n < count; ++n) {
                Node kid = kids.item(n);
                if (!(kid instanceof Text)) continue;
                text.append(kid.getNodeValue());
            }
        }
        return new XSSFRichTextString(text.toString());
    }

    public String getTitleFormula() {
        if (!this.chart.isSetTitle()) {
            return null;
        }
        CTTitle title = this.chart.getTitle();
        if (!title.isSetTx()) {
            return null;
        }
        CTTx tx = title.getTx();
        if (!tx.isSetStrRef()) {
            return null;
        }
        return tx.getStrRef().getF();
    }

    public void setTitleFormula(String formula) {
        CTTitle ctTitle = this.chart.isSetTitle() ? this.chart.getTitle() : this.chart.addNewTitle();
        CTTx tx = ctTitle.isSetTx() ? ctTitle.getTx() : ctTitle.addNewTx();
        if (tx.isSetRich()) {
            tx.unsetRich();
        }
        CTStrRef strRef = tx.isSetStrRef() ? tx.getStrRef() : tx.addNewStrRef();
        strRef.setF(formula);
    }

    @Override
    @Deprecated
    @Removal(version="4.2")
    public XSSFChartLegend getOrCreateLegend() {
        return new XSSFChartLegend(this);
    }

    @Deprecated
    @Removal(version="4.2")
    private boolean hasAxis() {
        CTPlotArea ctPlotArea = this.chart.getPlotArea();
        int totalAxisCount = ctPlotArea.sizeOfValAxArray() + ctPlotArea.sizeOfCatAxArray() + ctPlotArea.sizeOfDateAxArray() + ctPlotArea.sizeOfSerAxArray();
        return totalAxisCount > 0;
    }

    @Deprecated
    @Removal(version="4.2")
    private void parseAxis() {
        this.parseCategoryAxis();
        this.parseDateAxis();
        this.parseValueAxis();
    }

    @Deprecated
    @Removal(version="4.2")
    private void parseCategoryAxis() {
        for (CTCatAx catAx : this.chart.getPlotArea().getCatAxArray()) {
            this.axis.add(new XSSFCategoryAxis(this, catAx));
        }
    }

    @Deprecated
    @Removal(version="4.2")
    private void parseDateAxis() {
        for (CTDateAx dateAx : this.chart.getPlotArea().getDateAxArray()) {
            this.axis.add(new XSSFDateAxis(this, dateAx));
        }
    }

    @Deprecated
    @Removal(version="4.2")
    private void parseValueAxis() {
        for (CTValAx valAx : this.chart.getPlotArea().getValAxArray()) {
            this.axis.add(new XSSFValueAxis(this, valAx));
        }
    }
}

