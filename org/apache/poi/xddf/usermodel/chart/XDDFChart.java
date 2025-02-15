/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.chart;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import javax.xml.namespace.QName;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.POIXMLFactory;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.XDDFShapeProperties;
import org.apache.poi.xddf.usermodel.chart.AxisCrosses;
import org.apache.poi.xddf.usermodel.chart.AxisPosition;
import org.apache.poi.xddf.usermodel.chart.ChartTypes;
import org.apache.poi.xddf.usermodel.chart.DisplayBlanks;
import org.apache.poi.xddf.usermodel.chart.XDDFArea3DChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFAreaChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFBar3DChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFBarChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFCategoryAxis;
import org.apache.poi.xddf.usermodel.chart.XDDFChartAxis;
import org.apache.poi.xddf.usermodel.chart.XDDFChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFChartLegend;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFDateAxis;
import org.apache.poi.xddf.usermodel.chart.XDDFDoughnutChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFLine3DChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFLineChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFManualLayout;
import org.apache.poi.xddf.usermodel.chart.XDDFNumericalDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFPie3DChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFPieChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFRadarChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFScatterChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFSeriesAxis;
import org.apache.poi.xddf.usermodel.chart.XDDFSurface3DChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFSurfaceChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFTitle;
import org.apache.poi.xddf.usermodel.chart.XDDFValueAxis;
import org.apache.poi.xddf.usermodel.chart.XDDFView3D;
import org.apache.poi.xddf.usermodel.text.TextContainer;
import org.apache.poi.xddf.usermodel.text.XDDFTextBody;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTArea3DChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAreaChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBar3DChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBarChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTCatAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDoughnutChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLineChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPie3DChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPieChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSurface;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSurface3DChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSurfaceChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTitle;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTValAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D;
import org.openxmlformats.schemas.drawingml.x2006.chart.ChartSpaceDocument;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;

public abstract class XDDFChart
extends POIXMLDocumentPart
implements TextContainer {
    public static final int DEFAULT_WIDTH = 500000;
    public static final int DEFAULT_HEIGHT = 500000;
    public static final int DEFAULT_X = 10;
    public static final int DEFAULT_Y = 10;
    private XSSFWorkbook workbook;
    private int chartIndex = 0;
    private POIXMLDocumentPart documentPart = null;
    protected List<XDDFChartAxis> axes = new ArrayList<XDDFChartAxis>();
    protected final CTChartSpace chartSpace;
    protected final CTChart chart;
    private long seriesCount = 0L;

    protected XDDFChart() {
        this.chartSpace = CTChartSpace.Factory.newInstance();
        this.chart = this.chartSpace.addNewChart();
        this.chart.addNewPlotArea();
    }

    protected XDDFChart(PackagePart part) throws IOException, XmlException {
        super(part);
        this.chartSpace = ChartSpaceDocument.Factory.parse(part.getInputStream(), POIXMLTypeLoader.DEFAULT_XML_OPTIONS).getChartSpace();
        this.chart = this.chartSpace.getChart();
    }

    @Internal
    public CTChartSpace getCTChartSpace() {
        return this.chartSpace;
    }

    @Internal
    public CTChart getCTChart() {
        return this.chart;
    }

    @Internal
    protected CTPlotArea getCTPlotArea() {
        return this.chart.getPlotArea();
    }

    public void clear() {
        this.axes.clear();
        this.seriesCount = 0L;
        if (this.workbook != null) {
            this.workbook.removeSheetAt(0);
            this.workbook.createSheet();
        }
        this.chart.set(CTChart.Factory.newInstance());
        this.chart.addNewPlotArea();
    }

    public boolean isPlotOnlyVisibleCells() {
        if (this.chart.isSetPlotVisOnly()) {
            return this.chart.getPlotVisOnly().getVal();
        }
        return false;
    }

    public void setPlotOnlyVisibleCells(boolean only) {
        if (!this.chart.isSetPlotVisOnly()) {
            this.chart.setPlotVisOnly(CTBoolean.Factory.newInstance());
        }
        this.chart.getPlotVisOnly().setVal(only);
    }

    public void setFloor(int thickness) {
        if (!this.chart.isSetFloor()) {
            this.chart.setFloor(CTSurface.Factory.newInstance());
        }
        this.chart.getFloor().getThickness().setVal(thickness);
    }

    public void setBackWall(int thickness) {
        if (!this.chart.isSetBackWall()) {
            this.chart.setBackWall(CTSurface.Factory.newInstance());
        }
        this.chart.getBackWall().getThickness().setVal(thickness);
    }

    public void setSideWall(int thickness) {
        if (!this.chart.isSetSideWall()) {
            this.chart.setSideWall(CTSurface.Factory.newInstance());
        }
        this.chart.getSideWall().getThickness().setVal(thickness);
    }

    public void setAutoTitleDeleted(boolean deleted) {
        if (!this.chart.isSetAutoTitleDeleted()) {
            this.chart.setAutoTitleDeleted(CTBoolean.Factory.newInstance());
        }
        this.chart.getAutoTitleDeleted().setVal(deleted);
    }

    public void displayBlanksAs(DisplayBlanks as) {
        if (as == null) {
            if (this.chart.isSetDispBlanksAs()) {
                this.chart.unsetDispBlanksAs();
            }
        } else if (this.chart.isSetDispBlanksAs()) {
            this.chart.getDispBlanksAs().setVal(as.underlying);
        } else {
            this.chart.addNewDispBlanksAs().setVal(as.underlying);
        }
    }

    public Boolean getTitleOverlay() {
        CTTitle title;
        if (this.chart.isSetTitle() && (title = this.chart.getTitle()).isSetOverlay()) {
            return title.getOverlay().getVal();
        }
        return null;
    }

    public void setTitleOverlay(boolean overlay) {
        if (!this.chart.isSetTitle()) {
            this.chart.addNewTitle();
        }
        new XDDFTitle(this, this.chart.getTitle()).setOverlay(overlay);
    }

    public void setTitleText(String text) {
        if (!this.chart.isSetTitle()) {
            this.chart.addNewTitle();
        }
        new XDDFTitle(this, this.chart.getTitle()).setText(text);
    }

    public XDDFTitle getTitle() {
        if (this.chart.isSetTitle()) {
            return new XDDFTitle(this, this.chart.getTitle());
        }
        return null;
    }

    public XDDFView3D getOrAddView3D() {
        CTView3D view3D = this.chart.isSetView3D() ? this.chart.getView3D() : this.chart.addNewView3D();
        return new XDDFView3D(view3D);
    }

    public XDDFTextBody getFormattedTitle() {
        if (!this.chart.isSetTitle()) {
            return null;
        }
        return new XDDFTitle(this, this.chart.getTitle()).getBody();
    }

    @Override
    public <R> Optional<R> findDefinedParagraphProperty(Function<CTTextParagraphProperties, Boolean> isSet, Function<CTTextParagraphProperties, R> getter) {
        return Optional.empty();
    }

    @Override
    public <R> Optional<R> findDefinedRunProperty(Function<CTTextCharacterProperties, Boolean> isSet, Function<CTTextCharacterProperties, R> getter) {
        return Optional.empty();
    }

    public XDDFShapeProperties getOrAddShapeProperties() {
        CTPlotArea plotArea = this.getCTPlotArea();
        CTShapeProperties properties = plotArea.isSetSpPr() ? plotArea.getSpPr() : plotArea.addNewSpPr();
        return new XDDFShapeProperties(properties);
    }

    public void deleteShapeProperties() {
        if (this.getCTPlotArea().isSetSpPr()) {
            this.getCTPlotArea().unsetSpPr();
        }
    }

    public XDDFChartLegend getOrAddLegend() {
        return new XDDFChartLegend(this.chart);
    }

    public void deleteLegend() {
        if (this.chart.isSetLegend()) {
            this.chart.unsetLegend();
        }
    }

    public XDDFManualLayout getOrAddManualLayout() {
        return new XDDFManualLayout(this.getCTPlotArea());
    }

    protected long incrementSeriesCount() {
        return this.seriesCount++;
    }

    public void plot(XDDFChartData data) {
        XSSFSheet sheet = this.getSheet();
        for (int idx = 0; idx < data.getSeriesCount(); ++idx) {
            XDDFChartData.Series series = data.getSeries(idx);
            series.plot();
            XDDFDataSource<?> categoryDS = series.getCategoryData();
            XDDFNumericalDataSource<? extends Number> valuesDS = series.getValuesData();
            if (categoryDS.isCellRange() || valuesDS.isCellRange() || categoryDS.isLiteral() || valuesDS.isLiteral()) continue;
            this.fillSheet(sheet, categoryDS, valuesDS);
        }
    }

    public List<XDDFChartData> getChartSeries() {
        XmlObject surfaceChart;
        XmlObject pieChart;
        XmlObject lineChart;
        XmlObject barChart;
        XmlObject areaChart;
        int i;
        LinkedList<XDDFChartData> series = new LinkedList<XDDFChartData>();
        CTPlotArea plotArea = this.getCTPlotArea();
        Map<Long, XDDFChartAxis> categories = this.getCategoryAxes();
        Map<Long, XDDFValueAxis> values = this.getValueAxes();
        for (i = 0; i < plotArea.sizeOfAreaChartArray(); ++i) {
            areaChart = plotArea.getAreaChartArray(i);
            series.add(new XDDFAreaChartData(this, (CTAreaChart)areaChart, categories, values));
        }
        for (i = 0; i < plotArea.sizeOfArea3DChartArray(); ++i) {
            areaChart = plotArea.getArea3DChartArray(i);
            series.add(new XDDFArea3DChartData(this, (CTArea3DChart)areaChart, categories, values));
        }
        for (i = 0; i < plotArea.sizeOfBarChartArray(); ++i) {
            barChart = plotArea.getBarChartArray(i);
            series.add(new XDDFBarChartData(this, (CTBarChart)barChart, categories, values));
        }
        for (i = 0; i < plotArea.sizeOfBar3DChartArray(); ++i) {
            barChart = plotArea.getBar3DChartArray(i);
            series.add(new XDDFBar3DChartData(this, (CTBar3DChart)barChart, categories, values));
        }
        for (i = 0; i < plotArea.sizeOfDoughnutChartArray(); ++i) {
            CTDoughnutChart doughnutChart = plotArea.getDoughnutChartArray(i);
            series.add(new XDDFDoughnutChartData(this, doughnutChart));
        }
        for (i = 0; i < plotArea.sizeOfLineChartArray(); ++i) {
            lineChart = plotArea.getLineChartArray(i);
            series.add(new XDDFLineChartData(this, (CTLineChart)lineChart, categories, values));
        }
        for (i = 0; i < plotArea.sizeOfLine3DChartArray(); ++i) {
            lineChart = plotArea.getLine3DChartArray(i);
            series.add(new XDDFLine3DChartData(this, (CTLine3DChart)lineChart, categories, values));
        }
        for (i = 0; i < plotArea.sizeOfPieChartArray(); ++i) {
            pieChart = plotArea.getPieChartArray(i);
            series.add(new XDDFPieChartData(this, (CTPieChart)pieChart));
        }
        for (i = 0; i < plotArea.sizeOfPie3DChartArray(); ++i) {
            pieChart = plotArea.getPie3DChartArray(i);
            series.add(new XDDFPie3DChartData(this, (CTPie3DChart)pieChart));
        }
        for (i = 0; i < plotArea.sizeOfRadarChartArray(); ++i) {
            CTRadarChart radarChart = plotArea.getRadarChartArray(i);
            series.add(new XDDFRadarChartData(this, radarChart, categories, values));
        }
        for (i = 0; i < plotArea.sizeOfScatterChartArray(); ++i) {
            CTScatterChart scatterChart = plotArea.getScatterChartArray(i);
            series.add(new XDDFScatterChartData(this, scatterChart, categories, values));
        }
        for (i = 0; i < plotArea.sizeOfSurfaceChartArray(); ++i) {
            surfaceChart = plotArea.getSurfaceChartArray(i);
            series.add(new XDDFSurfaceChartData(this, (CTSurfaceChart)surfaceChart, categories, values));
        }
        for (i = 0; i < plotArea.sizeOfSurface3DChartArray(); ++i) {
            surfaceChart = plotArea.getSurface3DChartArray(i);
            series.add(new XDDFSurface3DChartData(this, (CTSurface3DChart)surfaceChart, categories, values));
        }
        this.seriesCount = series.size();
        return series;
    }

    public void clearChartSeries() {
        int i;
        CTPlotArea plotArea = this.getCTPlotArea();
        for (i = plotArea.sizeOfAreaChartArray(); i > 0; --i) {
            plotArea.removeAreaChart(i - 1);
        }
        for (i = plotArea.sizeOfArea3DChartArray(); i > 0; --i) {
            plotArea.removeArea3DChart(i - 1);
        }
        for (i = plotArea.sizeOfBarChartArray(); i > 0; --i) {
            plotArea.removeBarChart(i - 1);
        }
        for (i = plotArea.sizeOfBar3DChartArray(); i > 0; --i) {
            plotArea.removeBar3DChart(i - 1);
        }
        for (i = plotArea.sizeOfBubbleChartArray(); i > 0; --i) {
            plotArea.removeBubbleChart(i - 1);
        }
        for (i = plotArea.sizeOfDoughnutChartArray(); i > 0; --i) {
            plotArea.removeDoughnutChart(i - 1);
        }
        for (i = plotArea.sizeOfLineChartArray(); i > 0; --i) {
            plotArea.removeLineChart(i - 1);
        }
        for (i = plotArea.sizeOfLine3DChartArray(); i > 0; --i) {
            plotArea.removeLine3DChart(i - 1);
        }
        for (i = plotArea.sizeOfOfPieChartArray(); i > 0; --i) {
            plotArea.removeOfPieChart(i - 1);
        }
        for (i = plotArea.sizeOfPieChartArray(); i > 0; --i) {
            plotArea.removePieChart(i - 1);
        }
        for (i = plotArea.sizeOfPie3DChartArray(); i > 0; --i) {
            plotArea.removePie3DChart(i - 1);
        }
        for (i = plotArea.sizeOfRadarChartArray(); i > 0; --i) {
            plotArea.removeRadarChart(i - 1);
        }
        for (i = plotArea.sizeOfScatterChartArray(); i > 0; --i) {
            plotArea.removeScatterChart(i - 1);
        }
        for (i = plotArea.sizeOfStockChartArray(); i > 0; --i) {
            plotArea.removeStockChart(i - 1);
        }
        for (i = plotArea.sizeOfSurfaceChartArray(); i > 0; --i) {
            plotArea.removeSurfaceChart(i - 1);
        }
        for (i = plotArea.sizeOfSurface3DChartArray(); i > 0; --i) {
            plotArea.removeSurface3DChart(i - 1);
        }
    }

    private Map<Long, XDDFChartAxis> getCategoryAxes() {
        CTPlotArea plotArea = this.getCTPlotArea();
        int sizeOfArray = plotArea.sizeOfCatAxArray();
        HashMap<Long, XDDFChartAxis> axes = new HashMap<Long, XDDFChartAxis>(sizeOfArray);
        for (int i = 0; i < sizeOfArray; ++i) {
            CTCatAx category = plotArea.getCatAxArray(i);
            axes.put(category.getAxId().getVal(), new XDDFCategoryAxis(category));
        }
        return axes;
    }

    private Map<Long, XDDFValueAxis> getValueAxes() {
        CTPlotArea plotArea = this.getCTPlotArea();
        int sizeOfArray = plotArea.sizeOfValAxArray();
        HashMap<Long, XDDFValueAxis> axes = new HashMap<Long, XDDFValueAxis>(sizeOfArray);
        for (int i = 0; i < sizeOfArray; ++i) {
            CTValAx values = plotArea.getValAxArray(i);
            axes.put(values.getAxId().getVal(), new XDDFValueAxis(values));
        }
        return axes;
    }

    public XDDFValueAxis createValueAxis(AxisPosition pos) {
        XDDFValueAxis valueAxis = new XDDFValueAxis(this.getCTPlotArea(), pos);
        this.addAxis(valueAxis);
        return valueAxis;
    }

    public XDDFSeriesAxis createSeriesAxis(AxisPosition pos) {
        XDDFSeriesAxis seriesAxis = new XDDFSeriesAxis(this.getCTPlotArea(), pos);
        this.addAxis(seriesAxis);
        return seriesAxis;
    }

    public XDDFCategoryAxis createCategoryAxis(AxisPosition pos) {
        XDDFCategoryAxis categoryAxis = new XDDFCategoryAxis(this.getCTPlotArea(), pos);
        this.addAxis(categoryAxis);
        return categoryAxis;
    }

    public XDDFDateAxis createDateAxis(AxisPosition pos) {
        XDDFDateAxis dateAxis = new XDDFDateAxis(this.getCTPlotArea(), pos);
        this.addAxis(dateAxis);
        return dateAxis;
    }

    private void addAxis(XDDFChartAxis newAxis) {
        if (this.axes.size() == 1) {
            XDDFChartAxis axis = this.axes.get(0);
            axis.crossAxis(newAxis);
            newAxis.crossAxis(axis);
            axis.setCrosses(AxisCrosses.AUTO_ZERO);
            newAxis.setCrosses(AxisCrosses.AUTO_ZERO);
        }
        this.axes.add(newAxis);
    }

    public XDDFChartData createData(ChartTypes type, XDDFChartAxis category, XDDFValueAxis values) {
        Map<Long, XDDFChartAxis> categories = null;
        Map<Long, XDDFValueAxis> mapValues = null;
        if (ChartTypes.PIE != type && ChartTypes.PIE3D != type) {
            categories = Collections.singletonMap(category.getId(), category);
            mapValues = Collections.singletonMap(values.getId(), values);
        }
        CTPlotArea plotArea = this.getCTPlotArea();
        switch (type) {
            case AREA: {
                return new XDDFAreaChartData(this, plotArea.addNewAreaChart(), categories, mapValues);
            }
            case AREA3D: {
                return new XDDFArea3DChartData(this, plotArea.addNewArea3DChart(), categories, mapValues);
            }
            case BAR: {
                return new XDDFBarChartData(this, plotArea.addNewBarChart(), categories, mapValues);
            }
            case BAR3D: {
                return new XDDFBar3DChartData(this, plotArea.addNewBar3DChart(), categories, mapValues);
            }
            case DOUGHNUT: {
                return new XDDFDoughnutChartData(this, plotArea.addNewDoughnutChart());
            }
            case LINE: {
                return new XDDFLineChartData(this, plotArea.addNewLineChart(), categories, mapValues);
            }
            case LINE3D: {
                return new XDDFLine3DChartData(this, plotArea.addNewLine3DChart(), categories, mapValues);
            }
            case PIE: {
                return new XDDFPieChartData(this, plotArea.addNewPieChart());
            }
            case PIE3D: {
                return new XDDFPie3DChartData(this, plotArea.addNewPie3DChart());
            }
            case RADAR: {
                return new XDDFRadarChartData(this, plotArea.addNewRadarChart(), categories, mapValues);
            }
            case SCATTER: {
                return new XDDFScatterChartData(this, plotArea.addNewScatterChart(), categories, mapValues);
            }
            case SURFACE: {
                return new XDDFSurfaceChartData(this, plotArea.addNewSurfaceChart(), categories, mapValues);
            }
            case SURFACE3D: {
                return new XDDFSurface3DChartData(this, plotArea.addNewSurface3DChart(), categories, mapValues);
            }
        }
        return null;
    }

    public List<? extends XDDFChartAxis> getAxes() {
        if (this.axes.isEmpty() && this.hasAxes()) {
            this.parseAxes();
        }
        return this.axes;
    }

    private boolean hasAxes() {
        CTPlotArea ctPlotArea = this.getCTPlotArea();
        int totalAxisCount = ctPlotArea.sizeOfValAxArray() + ctPlotArea.sizeOfCatAxArray() + ctPlotArea.sizeOfDateAxArray() + ctPlotArea.sizeOfSerAxArray();
        return totalAxisCount > 0;
    }

    private void parseAxes() {
        for (CTCatAx cTCatAx : this.getCTPlotArea().getCatAxArray()) {
            this.axes.add(new XDDFCategoryAxis(cTCatAx));
        }
        for (XmlObject xmlObject : this.getCTPlotArea().getDateAxArray()) {
            this.axes.add(new XDDFDateAxis((CTDateAx)xmlObject));
        }
        for (XmlObject xmlObject : this.getCTPlotArea().getSerAxArray()) {
            this.axes.add(new XDDFSeriesAxis((CTSerAx)xmlObject));
        }
        for (XmlObject xmlObject : this.getCTPlotArea().getValAxArray()) {
            this.axes.add(new XDDFValueAxis((CTValAx)xmlObject));
        }
    }

    public void setValueRange(int axisIndex, Double minimum, Double maximum, Double majorUnit, Double minorUnit) {
        XDDFChartAxis axis = this.getAxes().get(axisIndex);
        if (axis == null) {
            return;
        }
        if (minimum != null) {
            axis.setMinimum(minimum);
        }
        if (maximum != null) {
            axis.setMaximum(maximum);
        }
        if (majorUnit != null) {
            axis.setMajorUnit(majorUnit);
        }
        if (minorUnit != null) {
            axis.setMinorUnit(minorUnit);
        }
    }

    public PackageRelationship createRelationshipInChart(POIXMLRelation chartRelation, POIXMLFactory chartFactory, int chartIndex) {
        this.documentPart = this.createRelationship(chartRelation, chartFactory, chartIndex, true).getDocumentPart();
        return this.addRelation(null, chartRelation, this.documentPart).getRelationship();
    }

    private PackagePart createWorksheetPart(POIXMLRelation chartRelation, POIXMLRelation chartWorkbookRelation, POIXMLFactory chartFactory) throws InvalidFormatException {
        PackageRelationship xlsx = this.createRelationshipInChart(chartWorkbookRelation, chartFactory, this.chartIndex);
        this.setExternalId(xlsx.getId());
        return this.getTargetPart(xlsx);
    }

    public void saveWorkbook(XSSFWorkbook workbook) throws IOException, InvalidFormatException {
        PackagePart worksheetPart = this.getWorksheetPart();
        if (worksheetPart == null) {
            POIXMLRelation chartRelation = this.getChartRelation();
            POIXMLRelation chartWorkbookRelation = this.getChartWorkbookRelation();
            POIXMLFactory chartFactory = this.getChartFactory();
            if (chartRelation != null && chartWorkbookRelation != null && chartFactory != null) {
                worksheetPart = this.createWorksheetPart(chartRelation, chartWorkbookRelation, chartFactory);
            } else {
                throw new InvalidFormatException("unable to determine chart relations");
            }
        }
        try (OutputStream xlsOut = worksheetPart.getOutputStream();){
            this.setWorksheetPartCommitted();
            workbook.write(xlsOut);
        }
    }

    protected abstract POIXMLRelation getChartRelation();

    protected abstract POIXMLRelation getChartWorkbookRelation();

    protected abstract POIXMLFactory getChartFactory();

    protected void fillSheet(XSSFSheet sheet, XDDFDataSource<?> categoryData, XDDFNumericalDataSource<?> valuesData) {
        int numOfPoints = categoryData.getPointCount();
        for (int i = 0; i < numOfPoints; ++i) {
            Number value;
            XSSFRow row = this.getRow(sheet, i + 1);
            Object category = categoryData.getPointAt(i);
            if (category != null) {
                this.getCell(row, categoryData.getColIndex()).setCellValue(category.toString());
            }
            if ((value = (Number)valuesData.getPointAt(i)) == null) continue;
            this.getCell(row, valuesData.getColIndex()).setCellValue(value.doubleValue());
        }
    }

    private XSSFRow getRow(XSSFSheet sheet, int index) {
        XSSFRow row = sheet.getRow(index);
        if (row == null) {
            return sheet.createRow(index);
        }
        return row;
    }

    private XSSFCell getCell(XSSFRow row, int index) {
        XSSFCell cell = row.getCell(index);
        if (cell == null) {
            return row.createCell(index);
        }
        return cell;
    }

    public void importContent(XDDFChart other) {
        this.chart.set(other.chart);
    }

    @Override
    protected void commit() throws IOException {
        XmlOptions xmlOptions = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        xmlOptions.setSaveSyntheticDocumentElement(new QName(CTChartSpace.type.getName().getNamespaceURI(), "chartSpace", "c"));
        if (this.workbook != null) {
            try {
                this.saveWorkbook(this.workbook);
            } catch (InvalidFormatException e) {
                throw new POIXMLException(e);
            }
        }
        PackagePart part = this.getPackagePart();
        try (OutputStream out = part.getOutputStream();){
            this.chartSpace.save(out, xmlOptions);
        }
    }

    public CellReference setSheetTitle(String title, int column) {
        XSSFSheet sheet = this.getSheet();
        XSSFRow row = this.getRow(sheet, 0);
        XSSFCell cell = this.getCell(row, column);
        cell.setCellValue(title);
        return new CellReference(sheet.getSheetName(), 0, column, true, true);
    }

    public String formatRange(CellRangeAddress range) {
        XSSFSheet sheet = this.getSheet();
        return sheet == null ? null : range.formatAsString(sheet.getSheetName(), true);
    }

    private XSSFSheet getSheet() {
        XSSFSheet sheet = null;
        try {
            sheet = this.getWorkbook().getSheetAt(0);
        } catch (IOException | InvalidFormatException exception) {
            // empty catch block
        }
        return sheet;
    }

    private PackagePart getWorksheetPart() throws InvalidFormatException {
        for (POIXMLDocumentPart.RelationPart part : this.getRelationParts()) {
            if (!"http://schemas.openxmlformats.org/officeDocument/2006/relationships/package".equals(part.getRelationship().getRelationshipType())) continue;
            return this.getTargetPart(part.getRelationship());
        }
        return null;
    }

    private void setWorksheetPartCommitted() throws InvalidFormatException {
        for (POIXMLDocumentPart.RelationPart part : this.getRelationParts()) {
            if (!"http://schemas.openxmlformats.org/officeDocument/2006/relationships/package".equals(part.getRelationship().getRelationshipType())) continue;
            ((POIXMLDocumentPart)part.getDocumentPart()).setCommitted(true);
            break;
        }
    }

    public XSSFWorkbook getWorkbook() throws IOException, InvalidFormatException {
        if (this.workbook == null) {
            try {
                PackagePart worksheetPart = this.getWorksheetPart();
                if (worksheetPart == null) {
                    this.workbook = new XSSFWorkbook();
                    this.workbook.createSheet();
                } else {
                    this.workbook = new XSSFWorkbook(worksheetPart.getInputStream());
                }
            } catch (NotOfficeXmlFileException e) {
                this.workbook = new XSSFWorkbook();
                this.workbook.createSheet();
            }
        }
        return this.workbook;
    }

    public void setWorkbook(XSSFWorkbook workbook) {
        this.workbook = workbook;
    }

    public void setExternalId(String id) {
        this.getCTChartSpace().addNewExternalData().setId(id);
    }

    protected int getChartIndex() {
        return this.chartIndex;
    }

    public void setChartIndex(int chartIndex) {
        this.chartIndex = chartIndex;
    }
}

