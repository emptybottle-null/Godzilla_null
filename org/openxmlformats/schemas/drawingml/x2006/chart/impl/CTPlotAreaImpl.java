/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.chart.CTBubbleChart
 *  org.openxmlformats.schemas.drawingml.x2006.chart.CTDTable
 *  org.openxmlformats.schemas.drawingml.x2006.chart.CTOfPieChart
 *  org.openxmlformats.schemas.drawingml.x2006.chart.CTStockChart
 */
package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTArea3DChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAreaChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBar3DChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBarChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBubbleChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTCatAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDTable;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDoughnutChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLayout;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLineChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTOfPieChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPie3DChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPieChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStockChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSurface3DChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSurfaceChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTValAx;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;

public class CTPlotAreaImpl
extends XmlComplexContentImpl
implements CTPlotArea {
    private static final long serialVersionUID = 1L;
    private static final QName LAYOUT$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "layout");
    private static final QName AREACHART$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "areaChart");
    private static final QName AREA3DCHART$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "area3DChart");
    private static final QName LINECHART$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "lineChart");
    private static final QName LINE3DCHART$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "line3DChart");
    private static final QName STOCKCHART$10 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "stockChart");
    private static final QName RADARCHART$12 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "radarChart");
    private static final QName SCATTERCHART$14 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "scatterChart");
    private static final QName PIECHART$16 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "pieChart");
    private static final QName PIE3DCHART$18 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "pie3DChart");
    private static final QName DOUGHNUTCHART$20 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "doughnutChart");
    private static final QName BARCHART$22 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "barChart");
    private static final QName BAR3DCHART$24 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "bar3DChart");
    private static final QName OFPIECHART$26 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "ofPieChart");
    private static final QName SURFACECHART$28 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "surfaceChart");
    private static final QName SURFACE3DCHART$30 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "surface3DChart");
    private static final QName BUBBLECHART$32 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "bubbleChart");
    private static final QName VALAX$34 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "valAx");
    private static final QName CATAX$36 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "catAx");
    private static final QName DATEAX$38 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "dateAx");
    private static final QName SERAX$40 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "serAx");
    private static final QName DTABLE$42 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "dTable");
    private static final QName SPPR$44 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "spPr");
    private static final QName EXTLST$46 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "extLst");

    public CTPlotAreaImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLayout getLayout() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLayout cTLayout = null;
            cTLayout = (CTLayout)((Object)this.get_store().find_element_user(LAYOUT$0, 0));
            if (cTLayout == null) {
                return null;
            }
            return cTLayout;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLayout() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LAYOUT$0) != 0;
        }
    }

    @Override
    public void setLayout(CTLayout cTLayout) {
        this.generatedSetterHelperImpl(cTLayout, LAYOUT$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLayout addNewLayout() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLayout cTLayout = null;
            cTLayout = (CTLayout)((Object)this.get_store().add_element_user(LAYOUT$0));
            return cTLayout;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLayout() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LAYOUT$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTAreaChart> getAreaChartList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class AreaChartList
            extends AbstractList<CTAreaChart> {
                AreaChartList() {
                }

                @Override
                public CTAreaChart get(int n) {
                    return CTPlotAreaImpl.this.getAreaChartArray(n);
                }

                @Override
                public CTAreaChart set(int n, CTAreaChart cTAreaChart) {
                    CTAreaChart cTAreaChart2 = CTPlotAreaImpl.this.getAreaChartArray(n);
                    CTPlotAreaImpl.this.setAreaChartArray(n, cTAreaChart);
                    return cTAreaChart2;
                }

                @Override
                public void add(int n, CTAreaChart cTAreaChart) {
                    CTPlotAreaImpl.this.insertNewAreaChart(n).set(cTAreaChart);
                }

                @Override
                public CTAreaChart remove(int n) {
                    CTAreaChart cTAreaChart = CTPlotAreaImpl.this.getAreaChartArray(n);
                    CTPlotAreaImpl.this.removeAreaChart(n);
                    return cTAreaChart;
                }

                @Override
                public int size() {
                    return CTPlotAreaImpl.this.sizeOfAreaChartArray();
                }
            }
            return new AreaChartList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTAreaChart[] getAreaChartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(AREACHART$2, arrayList);
            CTAreaChart[] cTAreaChartArray = new CTAreaChart[arrayList.size()];
            arrayList.toArray(cTAreaChartArray);
            return cTAreaChartArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAreaChart getAreaChartArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAreaChart cTAreaChart = null;
            cTAreaChart = (CTAreaChart)((Object)this.get_store().find_element_user(AREACHART$2, n));
            if (cTAreaChart == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTAreaChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfAreaChartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(AREACHART$2);
        }
    }

    @Override
    public void setAreaChartArray(CTAreaChart[] cTAreaChartArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTAreaChartArray, AREACHART$2);
    }

    @Override
    public void setAreaChartArray(int n, CTAreaChart cTAreaChart) {
        this.generatedSetterHelperImpl(cTAreaChart, AREACHART$2, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAreaChart insertNewAreaChart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAreaChart cTAreaChart = null;
            cTAreaChart = (CTAreaChart)((Object)this.get_store().insert_element_user(AREACHART$2, n));
            return cTAreaChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAreaChart addNewAreaChart() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAreaChart cTAreaChart = null;
            cTAreaChart = (CTAreaChart)((Object)this.get_store().add_element_user(AREACHART$2));
            return cTAreaChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAreaChart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(AREACHART$2, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTArea3DChart> getArea3DChartList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class Area3DChartList
            extends AbstractList<CTArea3DChart> {
                Area3DChartList() {
                }

                @Override
                public CTArea3DChart get(int n) {
                    return CTPlotAreaImpl.this.getArea3DChartArray(n);
                }

                @Override
                public CTArea3DChart set(int n, CTArea3DChart cTArea3DChart) {
                    CTArea3DChart cTArea3DChart2 = CTPlotAreaImpl.this.getArea3DChartArray(n);
                    CTPlotAreaImpl.this.setArea3DChartArray(n, cTArea3DChart);
                    return cTArea3DChart2;
                }

                @Override
                public void add(int n, CTArea3DChart cTArea3DChart) {
                    CTPlotAreaImpl.this.insertNewArea3DChart(n).set(cTArea3DChart);
                }

                @Override
                public CTArea3DChart remove(int n) {
                    CTArea3DChart cTArea3DChart = CTPlotAreaImpl.this.getArea3DChartArray(n);
                    CTPlotAreaImpl.this.removeArea3DChart(n);
                    return cTArea3DChart;
                }

                @Override
                public int size() {
                    return CTPlotAreaImpl.this.sizeOfArea3DChartArray();
                }
            }
            return new Area3DChartList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTArea3DChart[] getArea3DChartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(AREA3DCHART$4, arrayList);
            CTArea3DChart[] cTArea3DChartArray = new CTArea3DChart[arrayList.size()];
            arrayList.toArray(cTArea3DChartArray);
            return cTArea3DChartArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTArea3DChart getArea3DChartArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTArea3DChart cTArea3DChart = null;
            cTArea3DChart = (CTArea3DChart)((Object)this.get_store().find_element_user(AREA3DCHART$4, n));
            if (cTArea3DChart == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTArea3DChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfArea3DChartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(AREA3DCHART$4);
        }
    }

    @Override
    public void setArea3DChartArray(CTArea3DChart[] cTArea3DChartArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTArea3DChartArray, AREA3DCHART$4);
    }

    @Override
    public void setArea3DChartArray(int n, CTArea3DChart cTArea3DChart) {
        this.generatedSetterHelperImpl(cTArea3DChart, AREA3DCHART$4, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTArea3DChart insertNewArea3DChart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTArea3DChart cTArea3DChart = null;
            cTArea3DChart = (CTArea3DChart)((Object)this.get_store().insert_element_user(AREA3DCHART$4, n));
            return cTArea3DChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTArea3DChart addNewArea3DChart() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTArea3DChart cTArea3DChart = null;
            cTArea3DChart = (CTArea3DChart)((Object)this.get_store().add_element_user(AREA3DCHART$4));
            return cTArea3DChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeArea3DChart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(AREA3DCHART$4, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTLineChart> getLineChartList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class LineChartList
            extends AbstractList<CTLineChart> {
                LineChartList() {
                }

                @Override
                public CTLineChart get(int n) {
                    return CTPlotAreaImpl.this.getLineChartArray(n);
                }

                @Override
                public CTLineChart set(int n, CTLineChart cTLineChart) {
                    CTLineChart cTLineChart2 = CTPlotAreaImpl.this.getLineChartArray(n);
                    CTPlotAreaImpl.this.setLineChartArray(n, cTLineChart);
                    return cTLineChart2;
                }

                @Override
                public void add(int n, CTLineChart cTLineChart) {
                    CTPlotAreaImpl.this.insertNewLineChart(n).set(cTLineChart);
                }

                @Override
                public CTLineChart remove(int n) {
                    CTLineChart cTLineChart = CTPlotAreaImpl.this.getLineChartArray(n);
                    CTPlotAreaImpl.this.removeLineChart(n);
                    return cTLineChart;
                }

                @Override
                public int size() {
                    return CTPlotAreaImpl.this.sizeOfLineChartArray();
                }
            }
            return new LineChartList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTLineChart[] getLineChartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(LINECHART$6, arrayList);
            CTLineChart[] cTLineChartArray = new CTLineChart[arrayList.size()];
            arrayList.toArray(cTLineChartArray);
            return cTLineChartArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLineChart getLineChartArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLineChart cTLineChart = null;
            cTLineChart = (CTLineChart)((Object)this.get_store().find_element_user(LINECHART$6, n));
            if (cTLineChart == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTLineChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfLineChartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LINECHART$6);
        }
    }

    @Override
    public void setLineChartArray(CTLineChart[] cTLineChartArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTLineChartArray, LINECHART$6);
    }

    @Override
    public void setLineChartArray(int n, CTLineChart cTLineChart) {
        this.generatedSetterHelperImpl(cTLineChart, LINECHART$6, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLineChart insertNewLineChart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLineChart cTLineChart = null;
            cTLineChart = (CTLineChart)((Object)this.get_store().insert_element_user(LINECHART$6, n));
            return cTLineChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLineChart addNewLineChart() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLineChart cTLineChart = null;
            cTLineChart = (CTLineChart)((Object)this.get_store().add_element_user(LINECHART$6));
            return cTLineChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeLineChart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LINECHART$6, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTLine3DChart> getLine3DChartList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class Line3DChartList
            extends AbstractList<CTLine3DChart> {
                Line3DChartList() {
                }

                @Override
                public CTLine3DChart get(int n) {
                    return CTPlotAreaImpl.this.getLine3DChartArray(n);
                }

                @Override
                public CTLine3DChart set(int n, CTLine3DChart cTLine3DChart) {
                    CTLine3DChart cTLine3DChart2 = CTPlotAreaImpl.this.getLine3DChartArray(n);
                    CTPlotAreaImpl.this.setLine3DChartArray(n, cTLine3DChart);
                    return cTLine3DChart2;
                }

                @Override
                public void add(int n, CTLine3DChart cTLine3DChart) {
                    CTPlotAreaImpl.this.insertNewLine3DChart(n).set(cTLine3DChart);
                }

                @Override
                public CTLine3DChart remove(int n) {
                    CTLine3DChart cTLine3DChart = CTPlotAreaImpl.this.getLine3DChartArray(n);
                    CTPlotAreaImpl.this.removeLine3DChart(n);
                    return cTLine3DChart;
                }

                @Override
                public int size() {
                    return CTPlotAreaImpl.this.sizeOfLine3DChartArray();
                }
            }
            return new Line3DChartList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTLine3DChart[] getLine3DChartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(LINE3DCHART$8, arrayList);
            CTLine3DChart[] cTLine3DChartArray = new CTLine3DChart[arrayList.size()];
            arrayList.toArray(cTLine3DChartArray);
            return cTLine3DChartArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLine3DChart getLine3DChartArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLine3DChart cTLine3DChart = null;
            cTLine3DChart = (CTLine3DChart)((Object)this.get_store().find_element_user(LINE3DCHART$8, n));
            if (cTLine3DChart == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTLine3DChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfLine3DChartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LINE3DCHART$8);
        }
    }

    @Override
    public void setLine3DChartArray(CTLine3DChart[] cTLine3DChartArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTLine3DChartArray, LINE3DCHART$8);
    }

    @Override
    public void setLine3DChartArray(int n, CTLine3DChart cTLine3DChart) {
        this.generatedSetterHelperImpl(cTLine3DChart, LINE3DCHART$8, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLine3DChart insertNewLine3DChart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLine3DChart cTLine3DChart = null;
            cTLine3DChart = (CTLine3DChart)((Object)this.get_store().insert_element_user(LINE3DCHART$8, n));
            return cTLine3DChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLine3DChart addNewLine3DChart() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLine3DChart cTLine3DChart = null;
            cTLine3DChart = (CTLine3DChart)((Object)this.get_store().add_element_user(LINE3DCHART$8));
            return cTLine3DChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeLine3DChart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LINE3DCHART$8, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTStockChart> getStockChartList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class StockChartList
            extends AbstractList<CTStockChart> {
                StockChartList() {
                }

                @Override
                public CTStockChart get(int n) {
                    return CTPlotAreaImpl.this.getStockChartArray(n);
                }

                @Override
                public CTStockChart set(int n, CTStockChart cTStockChart) {
                    CTStockChart cTStockChart2 = CTPlotAreaImpl.this.getStockChartArray(n);
                    CTPlotAreaImpl.this.setStockChartArray(n, cTStockChart);
                    return cTStockChart2;
                }

                @Override
                public void add(int n, CTStockChart cTStockChart) {
                    CTPlotAreaImpl.this.insertNewStockChart(n).set((XmlObject)cTStockChart);
                }

                @Override
                public CTStockChart remove(int n) {
                    CTStockChart cTStockChart = CTPlotAreaImpl.this.getStockChartArray(n);
                    CTPlotAreaImpl.this.removeStockChart(n);
                    return cTStockChart;
                }

                @Override
                public int size() {
                    return CTPlotAreaImpl.this.sizeOfStockChartArray();
                }
            }
            return new StockChartList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTStockChart[] getStockChartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(STOCKCHART$10, arrayList);
            CTStockChart[] cTStockChartArray = new CTStockChart[arrayList.size()];
            arrayList.toArray(cTStockChartArray);
            return cTStockChartArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStockChart getStockChartArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStockChart cTStockChart = null;
            cTStockChart = (CTStockChart)this.get_store().find_element_user(STOCKCHART$10, n);
            if (cTStockChart == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTStockChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfStockChartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(STOCKCHART$10);
        }
    }

    @Override
    public void setStockChartArray(CTStockChart[] cTStockChartArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTStockChartArray, STOCKCHART$10);
    }

    @Override
    public void setStockChartArray(int n, CTStockChart cTStockChart) {
        this.generatedSetterHelperImpl((XmlObject)cTStockChart, STOCKCHART$10, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStockChart insertNewStockChart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStockChart cTStockChart = null;
            cTStockChart = (CTStockChart)this.get_store().insert_element_user(STOCKCHART$10, n);
            return cTStockChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStockChart addNewStockChart() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStockChart cTStockChart = null;
            cTStockChart = (CTStockChart)this.get_store().add_element_user(STOCKCHART$10);
            return cTStockChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeStockChart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(STOCKCHART$10, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTRadarChart> getRadarChartList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class RadarChartList
            extends AbstractList<CTRadarChart> {
                RadarChartList() {
                }

                @Override
                public CTRadarChart get(int n) {
                    return CTPlotAreaImpl.this.getRadarChartArray(n);
                }

                @Override
                public CTRadarChart set(int n, CTRadarChart cTRadarChart) {
                    CTRadarChart cTRadarChart2 = CTPlotAreaImpl.this.getRadarChartArray(n);
                    CTPlotAreaImpl.this.setRadarChartArray(n, cTRadarChart);
                    return cTRadarChart2;
                }

                @Override
                public void add(int n, CTRadarChart cTRadarChart) {
                    CTPlotAreaImpl.this.insertNewRadarChart(n).set(cTRadarChart);
                }

                @Override
                public CTRadarChart remove(int n) {
                    CTRadarChart cTRadarChart = CTPlotAreaImpl.this.getRadarChartArray(n);
                    CTPlotAreaImpl.this.removeRadarChart(n);
                    return cTRadarChart;
                }

                @Override
                public int size() {
                    return CTPlotAreaImpl.this.sizeOfRadarChartArray();
                }
            }
            return new RadarChartList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTRadarChart[] getRadarChartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(RADARCHART$12, arrayList);
            CTRadarChart[] cTRadarChartArray = new CTRadarChart[arrayList.size()];
            arrayList.toArray(cTRadarChartArray);
            return cTRadarChartArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRadarChart getRadarChartArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRadarChart cTRadarChart = null;
            cTRadarChart = (CTRadarChart)((Object)this.get_store().find_element_user(RADARCHART$12, n));
            if (cTRadarChart == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTRadarChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfRadarChartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(RADARCHART$12);
        }
    }

    @Override
    public void setRadarChartArray(CTRadarChart[] cTRadarChartArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTRadarChartArray, RADARCHART$12);
    }

    @Override
    public void setRadarChartArray(int n, CTRadarChart cTRadarChart) {
        this.generatedSetterHelperImpl(cTRadarChart, RADARCHART$12, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRadarChart insertNewRadarChart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRadarChart cTRadarChart = null;
            cTRadarChart = (CTRadarChart)((Object)this.get_store().insert_element_user(RADARCHART$12, n));
            return cTRadarChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRadarChart addNewRadarChart() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRadarChart cTRadarChart = null;
            cTRadarChart = (CTRadarChart)((Object)this.get_store().add_element_user(RADARCHART$12));
            return cTRadarChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeRadarChart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(RADARCHART$12, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTScatterChart> getScatterChartList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ScatterChartList
            extends AbstractList<CTScatterChart> {
                ScatterChartList() {
                }

                @Override
                public CTScatterChart get(int n) {
                    return CTPlotAreaImpl.this.getScatterChartArray(n);
                }

                @Override
                public CTScatterChart set(int n, CTScatterChart cTScatterChart) {
                    CTScatterChart cTScatterChart2 = CTPlotAreaImpl.this.getScatterChartArray(n);
                    CTPlotAreaImpl.this.setScatterChartArray(n, cTScatterChart);
                    return cTScatterChart2;
                }

                @Override
                public void add(int n, CTScatterChart cTScatterChart) {
                    CTPlotAreaImpl.this.insertNewScatterChart(n).set(cTScatterChart);
                }

                @Override
                public CTScatterChart remove(int n) {
                    CTScatterChart cTScatterChart = CTPlotAreaImpl.this.getScatterChartArray(n);
                    CTPlotAreaImpl.this.removeScatterChart(n);
                    return cTScatterChart;
                }

                @Override
                public int size() {
                    return CTPlotAreaImpl.this.sizeOfScatterChartArray();
                }
            }
            return new ScatterChartList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTScatterChart[] getScatterChartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SCATTERCHART$14, arrayList);
            CTScatterChart[] cTScatterChartArray = new CTScatterChart[arrayList.size()];
            arrayList.toArray(cTScatterChartArray);
            return cTScatterChartArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTScatterChart getScatterChartArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTScatterChart cTScatterChart = null;
            cTScatterChart = (CTScatterChart)((Object)this.get_store().find_element_user(SCATTERCHART$14, n));
            if (cTScatterChart == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTScatterChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfScatterChartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SCATTERCHART$14);
        }
    }

    @Override
    public void setScatterChartArray(CTScatterChart[] cTScatterChartArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTScatterChartArray, SCATTERCHART$14);
    }

    @Override
    public void setScatterChartArray(int n, CTScatterChart cTScatterChart) {
        this.generatedSetterHelperImpl(cTScatterChart, SCATTERCHART$14, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTScatterChart insertNewScatterChart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTScatterChart cTScatterChart = null;
            cTScatterChart = (CTScatterChart)((Object)this.get_store().insert_element_user(SCATTERCHART$14, n));
            return cTScatterChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTScatterChart addNewScatterChart() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTScatterChart cTScatterChart = null;
            cTScatterChart = (CTScatterChart)((Object)this.get_store().add_element_user(SCATTERCHART$14));
            return cTScatterChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeScatterChart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SCATTERCHART$14, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPieChart> getPieChartList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class PieChartList
            extends AbstractList<CTPieChart> {
                PieChartList() {
                }

                @Override
                public CTPieChart get(int n) {
                    return CTPlotAreaImpl.this.getPieChartArray(n);
                }

                @Override
                public CTPieChart set(int n, CTPieChart cTPieChart) {
                    CTPieChart cTPieChart2 = CTPlotAreaImpl.this.getPieChartArray(n);
                    CTPlotAreaImpl.this.setPieChartArray(n, cTPieChart);
                    return cTPieChart2;
                }

                @Override
                public void add(int n, CTPieChart cTPieChart) {
                    CTPlotAreaImpl.this.insertNewPieChart(n).set(cTPieChart);
                }

                @Override
                public CTPieChart remove(int n) {
                    CTPieChart cTPieChart = CTPlotAreaImpl.this.getPieChartArray(n);
                    CTPlotAreaImpl.this.removePieChart(n);
                    return cTPieChart;
                }

                @Override
                public int size() {
                    return CTPlotAreaImpl.this.sizeOfPieChartArray();
                }
            }
            return new PieChartList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPieChart[] getPieChartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(PIECHART$16, arrayList);
            CTPieChart[] cTPieChartArray = new CTPieChart[arrayList.size()];
            arrayList.toArray(cTPieChartArray);
            return cTPieChartArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPieChart getPieChartArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPieChart cTPieChart = null;
            cTPieChart = (CTPieChart)((Object)this.get_store().find_element_user(PIECHART$16, n));
            if (cTPieChart == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPieChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfPieChartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PIECHART$16);
        }
    }

    @Override
    public void setPieChartArray(CTPieChart[] cTPieChartArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTPieChartArray, PIECHART$16);
    }

    @Override
    public void setPieChartArray(int n, CTPieChart cTPieChart) {
        this.generatedSetterHelperImpl(cTPieChart, PIECHART$16, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPieChart insertNewPieChart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPieChart cTPieChart = null;
            cTPieChart = (CTPieChart)((Object)this.get_store().insert_element_user(PIECHART$16, n));
            return cTPieChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPieChart addNewPieChart() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPieChart cTPieChart = null;
            cTPieChart = (CTPieChart)((Object)this.get_store().add_element_user(PIECHART$16));
            return cTPieChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removePieChart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PIECHART$16, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPie3DChart> getPie3DChartList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class Pie3DChartList
            extends AbstractList<CTPie3DChart> {
                Pie3DChartList() {
                }

                @Override
                public CTPie3DChart get(int n) {
                    return CTPlotAreaImpl.this.getPie3DChartArray(n);
                }

                @Override
                public CTPie3DChart set(int n, CTPie3DChart cTPie3DChart) {
                    CTPie3DChart cTPie3DChart2 = CTPlotAreaImpl.this.getPie3DChartArray(n);
                    CTPlotAreaImpl.this.setPie3DChartArray(n, cTPie3DChart);
                    return cTPie3DChart2;
                }

                @Override
                public void add(int n, CTPie3DChart cTPie3DChart) {
                    CTPlotAreaImpl.this.insertNewPie3DChart(n).set(cTPie3DChart);
                }

                @Override
                public CTPie3DChart remove(int n) {
                    CTPie3DChart cTPie3DChart = CTPlotAreaImpl.this.getPie3DChartArray(n);
                    CTPlotAreaImpl.this.removePie3DChart(n);
                    return cTPie3DChart;
                }

                @Override
                public int size() {
                    return CTPlotAreaImpl.this.sizeOfPie3DChartArray();
                }
            }
            return new Pie3DChartList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPie3DChart[] getPie3DChartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(PIE3DCHART$18, arrayList);
            CTPie3DChart[] cTPie3DChartArray = new CTPie3DChart[arrayList.size()];
            arrayList.toArray(cTPie3DChartArray);
            return cTPie3DChartArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPie3DChart getPie3DChartArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPie3DChart cTPie3DChart = null;
            cTPie3DChart = (CTPie3DChart)((Object)this.get_store().find_element_user(PIE3DCHART$18, n));
            if (cTPie3DChart == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPie3DChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfPie3DChartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PIE3DCHART$18);
        }
    }

    @Override
    public void setPie3DChartArray(CTPie3DChart[] cTPie3DChartArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTPie3DChartArray, PIE3DCHART$18);
    }

    @Override
    public void setPie3DChartArray(int n, CTPie3DChart cTPie3DChart) {
        this.generatedSetterHelperImpl(cTPie3DChart, PIE3DCHART$18, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPie3DChart insertNewPie3DChart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPie3DChart cTPie3DChart = null;
            cTPie3DChart = (CTPie3DChart)((Object)this.get_store().insert_element_user(PIE3DCHART$18, n));
            return cTPie3DChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPie3DChart addNewPie3DChart() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPie3DChart cTPie3DChart = null;
            cTPie3DChart = (CTPie3DChart)((Object)this.get_store().add_element_user(PIE3DCHART$18));
            return cTPie3DChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removePie3DChart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PIE3DCHART$18, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTDoughnutChart> getDoughnutChartList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class DoughnutChartList
            extends AbstractList<CTDoughnutChart> {
                DoughnutChartList() {
                }

                @Override
                public CTDoughnutChart get(int n) {
                    return CTPlotAreaImpl.this.getDoughnutChartArray(n);
                }

                @Override
                public CTDoughnutChart set(int n, CTDoughnutChart cTDoughnutChart) {
                    CTDoughnutChart cTDoughnutChart2 = CTPlotAreaImpl.this.getDoughnutChartArray(n);
                    CTPlotAreaImpl.this.setDoughnutChartArray(n, cTDoughnutChart);
                    return cTDoughnutChart2;
                }

                @Override
                public void add(int n, CTDoughnutChart cTDoughnutChart) {
                    CTPlotAreaImpl.this.insertNewDoughnutChart(n).set(cTDoughnutChart);
                }

                @Override
                public CTDoughnutChart remove(int n) {
                    CTDoughnutChart cTDoughnutChart = CTPlotAreaImpl.this.getDoughnutChartArray(n);
                    CTPlotAreaImpl.this.removeDoughnutChart(n);
                    return cTDoughnutChart;
                }

                @Override
                public int size() {
                    return CTPlotAreaImpl.this.sizeOfDoughnutChartArray();
                }
            }
            return new DoughnutChartList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTDoughnutChart[] getDoughnutChartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(DOUGHNUTCHART$20, arrayList);
            CTDoughnutChart[] cTDoughnutChartArray = new CTDoughnutChart[arrayList.size()];
            arrayList.toArray(cTDoughnutChartArray);
            return cTDoughnutChartArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDoughnutChart getDoughnutChartArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDoughnutChart cTDoughnutChart = null;
            cTDoughnutChart = (CTDoughnutChart)((Object)this.get_store().find_element_user(DOUGHNUTCHART$20, n));
            if (cTDoughnutChart == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTDoughnutChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfDoughnutChartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DOUGHNUTCHART$20);
        }
    }

    @Override
    public void setDoughnutChartArray(CTDoughnutChart[] cTDoughnutChartArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTDoughnutChartArray, DOUGHNUTCHART$20);
    }

    @Override
    public void setDoughnutChartArray(int n, CTDoughnutChart cTDoughnutChart) {
        this.generatedSetterHelperImpl(cTDoughnutChart, DOUGHNUTCHART$20, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDoughnutChart insertNewDoughnutChart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDoughnutChart cTDoughnutChart = null;
            cTDoughnutChart = (CTDoughnutChart)((Object)this.get_store().insert_element_user(DOUGHNUTCHART$20, n));
            return cTDoughnutChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDoughnutChart addNewDoughnutChart() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDoughnutChart cTDoughnutChart = null;
            cTDoughnutChart = (CTDoughnutChart)((Object)this.get_store().add_element_user(DOUGHNUTCHART$20));
            return cTDoughnutChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeDoughnutChart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DOUGHNUTCHART$20, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTBarChart> getBarChartList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class BarChartList
            extends AbstractList<CTBarChart> {
                BarChartList() {
                }

                @Override
                public CTBarChart get(int n) {
                    return CTPlotAreaImpl.this.getBarChartArray(n);
                }

                @Override
                public CTBarChart set(int n, CTBarChart cTBarChart) {
                    CTBarChart cTBarChart2 = CTPlotAreaImpl.this.getBarChartArray(n);
                    CTPlotAreaImpl.this.setBarChartArray(n, cTBarChart);
                    return cTBarChart2;
                }

                @Override
                public void add(int n, CTBarChart cTBarChart) {
                    CTPlotAreaImpl.this.insertNewBarChart(n).set(cTBarChart);
                }

                @Override
                public CTBarChart remove(int n) {
                    CTBarChart cTBarChart = CTPlotAreaImpl.this.getBarChartArray(n);
                    CTPlotAreaImpl.this.removeBarChart(n);
                    return cTBarChart;
                }

                @Override
                public int size() {
                    return CTPlotAreaImpl.this.sizeOfBarChartArray();
                }
            }
            return new BarChartList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTBarChart[] getBarChartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(BARCHART$22, arrayList);
            CTBarChart[] cTBarChartArray = new CTBarChart[arrayList.size()];
            arrayList.toArray(cTBarChartArray);
            return cTBarChartArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBarChart getBarChartArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBarChart cTBarChart = null;
            cTBarChart = (CTBarChart)((Object)this.get_store().find_element_user(BARCHART$22, n));
            if (cTBarChart == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTBarChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfBarChartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BARCHART$22);
        }
    }

    @Override
    public void setBarChartArray(CTBarChart[] cTBarChartArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTBarChartArray, BARCHART$22);
    }

    @Override
    public void setBarChartArray(int n, CTBarChart cTBarChart) {
        this.generatedSetterHelperImpl(cTBarChart, BARCHART$22, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBarChart insertNewBarChart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBarChart cTBarChart = null;
            cTBarChart = (CTBarChart)((Object)this.get_store().insert_element_user(BARCHART$22, n));
            return cTBarChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBarChart addNewBarChart() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBarChart cTBarChart = null;
            cTBarChart = (CTBarChart)((Object)this.get_store().add_element_user(BARCHART$22));
            return cTBarChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeBarChart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BARCHART$22, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTBar3DChart> getBar3DChartList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class Bar3DChartList
            extends AbstractList<CTBar3DChart> {
                Bar3DChartList() {
                }

                @Override
                public CTBar3DChart get(int n) {
                    return CTPlotAreaImpl.this.getBar3DChartArray(n);
                }

                @Override
                public CTBar3DChart set(int n, CTBar3DChart cTBar3DChart) {
                    CTBar3DChart cTBar3DChart2 = CTPlotAreaImpl.this.getBar3DChartArray(n);
                    CTPlotAreaImpl.this.setBar3DChartArray(n, cTBar3DChart);
                    return cTBar3DChart2;
                }

                @Override
                public void add(int n, CTBar3DChart cTBar3DChart) {
                    CTPlotAreaImpl.this.insertNewBar3DChart(n).set(cTBar3DChart);
                }

                @Override
                public CTBar3DChart remove(int n) {
                    CTBar3DChart cTBar3DChart = CTPlotAreaImpl.this.getBar3DChartArray(n);
                    CTPlotAreaImpl.this.removeBar3DChart(n);
                    return cTBar3DChart;
                }

                @Override
                public int size() {
                    return CTPlotAreaImpl.this.sizeOfBar3DChartArray();
                }
            }
            return new Bar3DChartList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTBar3DChart[] getBar3DChartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(BAR3DCHART$24, arrayList);
            CTBar3DChart[] cTBar3DChartArray = new CTBar3DChart[arrayList.size()];
            arrayList.toArray(cTBar3DChartArray);
            return cTBar3DChartArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBar3DChart getBar3DChartArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBar3DChart cTBar3DChart = null;
            cTBar3DChart = (CTBar3DChart)((Object)this.get_store().find_element_user(BAR3DCHART$24, n));
            if (cTBar3DChart == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTBar3DChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfBar3DChartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BAR3DCHART$24);
        }
    }

    @Override
    public void setBar3DChartArray(CTBar3DChart[] cTBar3DChartArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTBar3DChartArray, BAR3DCHART$24);
    }

    @Override
    public void setBar3DChartArray(int n, CTBar3DChart cTBar3DChart) {
        this.generatedSetterHelperImpl(cTBar3DChart, BAR3DCHART$24, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBar3DChart insertNewBar3DChart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBar3DChart cTBar3DChart = null;
            cTBar3DChart = (CTBar3DChart)((Object)this.get_store().insert_element_user(BAR3DCHART$24, n));
            return cTBar3DChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBar3DChart addNewBar3DChart() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBar3DChart cTBar3DChart = null;
            cTBar3DChart = (CTBar3DChart)((Object)this.get_store().add_element_user(BAR3DCHART$24));
            return cTBar3DChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeBar3DChart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BAR3DCHART$24, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTOfPieChart> getOfPieChartList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class OfPieChartList
            extends AbstractList<CTOfPieChart> {
                OfPieChartList() {
                }

                @Override
                public CTOfPieChart get(int n) {
                    return CTPlotAreaImpl.this.getOfPieChartArray(n);
                }

                @Override
                public CTOfPieChart set(int n, CTOfPieChart cTOfPieChart) {
                    CTOfPieChart cTOfPieChart2 = CTPlotAreaImpl.this.getOfPieChartArray(n);
                    CTPlotAreaImpl.this.setOfPieChartArray(n, cTOfPieChart);
                    return cTOfPieChart2;
                }

                @Override
                public void add(int n, CTOfPieChart cTOfPieChart) {
                    CTPlotAreaImpl.this.insertNewOfPieChart(n).set((XmlObject)cTOfPieChart);
                }

                @Override
                public CTOfPieChart remove(int n) {
                    CTOfPieChart cTOfPieChart = CTPlotAreaImpl.this.getOfPieChartArray(n);
                    CTPlotAreaImpl.this.removeOfPieChart(n);
                    return cTOfPieChart;
                }

                @Override
                public int size() {
                    return CTPlotAreaImpl.this.sizeOfOfPieChartArray();
                }
            }
            return new OfPieChartList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTOfPieChart[] getOfPieChartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(OFPIECHART$26, arrayList);
            CTOfPieChart[] cTOfPieChartArray = new CTOfPieChart[arrayList.size()];
            arrayList.toArray(cTOfPieChartArray);
            return cTOfPieChartArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOfPieChart getOfPieChartArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOfPieChart cTOfPieChart = null;
            cTOfPieChart = (CTOfPieChart)this.get_store().find_element_user(OFPIECHART$26, n);
            if (cTOfPieChart == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTOfPieChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfOfPieChartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(OFPIECHART$26);
        }
    }

    @Override
    public void setOfPieChartArray(CTOfPieChart[] cTOfPieChartArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTOfPieChartArray, OFPIECHART$26);
    }

    @Override
    public void setOfPieChartArray(int n, CTOfPieChart cTOfPieChart) {
        this.generatedSetterHelperImpl((XmlObject)cTOfPieChart, OFPIECHART$26, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOfPieChart insertNewOfPieChart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOfPieChart cTOfPieChart = null;
            cTOfPieChart = (CTOfPieChart)this.get_store().insert_element_user(OFPIECHART$26, n);
            return cTOfPieChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOfPieChart addNewOfPieChart() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOfPieChart cTOfPieChart = null;
            cTOfPieChart = (CTOfPieChart)this.get_store().add_element_user(OFPIECHART$26);
            return cTOfPieChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeOfPieChart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(OFPIECHART$26, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTSurfaceChart> getSurfaceChartList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SurfaceChartList
            extends AbstractList<CTSurfaceChart> {
                SurfaceChartList() {
                }

                @Override
                public CTSurfaceChart get(int n) {
                    return CTPlotAreaImpl.this.getSurfaceChartArray(n);
                }

                @Override
                public CTSurfaceChart set(int n, CTSurfaceChart cTSurfaceChart) {
                    CTSurfaceChart cTSurfaceChart2 = CTPlotAreaImpl.this.getSurfaceChartArray(n);
                    CTPlotAreaImpl.this.setSurfaceChartArray(n, cTSurfaceChart);
                    return cTSurfaceChart2;
                }

                @Override
                public void add(int n, CTSurfaceChart cTSurfaceChart) {
                    CTPlotAreaImpl.this.insertNewSurfaceChart(n).set(cTSurfaceChart);
                }

                @Override
                public CTSurfaceChart remove(int n) {
                    CTSurfaceChart cTSurfaceChart = CTPlotAreaImpl.this.getSurfaceChartArray(n);
                    CTPlotAreaImpl.this.removeSurfaceChart(n);
                    return cTSurfaceChart;
                }

                @Override
                public int size() {
                    return CTPlotAreaImpl.this.sizeOfSurfaceChartArray();
                }
            }
            return new SurfaceChartList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTSurfaceChart[] getSurfaceChartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SURFACECHART$28, arrayList);
            CTSurfaceChart[] cTSurfaceChartArray = new CTSurfaceChart[arrayList.size()];
            arrayList.toArray(cTSurfaceChartArray);
            return cTSurfaceChartArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSurfaceChart getSurfaceChartArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSurfaceChart cTSurfaceChart = null;
            cTSurfaceChart = (CTSurfaceChart)((Object)this.get_store().find_element_user(SURFACECHART$28, n));
            if (cTSurfaceChart == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTSurfaceChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSurfaceChartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SURFACECHART$28);
        }
    }

    @Override
    public void setSurfaceChartArray(CTSurfaceChart[] cTSurfaceChartArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTSurfaceChartArray, SURFACECHART$28);
    }

    @Override
    public void setSurfaceChartArray(int n, CTSurfaceChart cTSurfaceChart) {
        this.generatedSetterHelperImpl(cTSurfaceChart, SURFACECHART$28, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSurfaceChart insertNewSurfaceChart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSurfaceChart cTSurfaceChart = null;
            cTSurfaceChart = (CTSurfaceChart)((Object)this.get_store().insert_element_user(SURFACECHART$28, n));
            return cTSurfaceChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSurfaceChart addNewSurfaceChart() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSurfaceChart cTSurfaceChart = null;
            cTSurfaceChart = (CTSurfaceChart)((Object)this.get_store().add_element_user(SURFACECHART$28));
            return cTSurfaceChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSurfaceChart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SURFACECHART$28, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTSurface3DChart> getSurface3DChartList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class Surface3DChartList
            extends AbstractList<CTSurface3DChart> {
                Surface3DChartList() {
                }

                @Override
                public CTSurface3DChart get(int n) {
                    return CTPlotAreaImpl.this.getSurface3DChartArray(n);
                }

                @Override
                public CTSurface3DChart set(int n, CTSurface3DChart cTSurface3DChart) {
                    CTSurface3DChart cTSurface3DChart2 = CTPlotAreaImpl.this.getSurface3DChartArray(n);
                    CTPlotAreaImpl.this.setSurface3DChartArray(n, cTSurface3DChart);
                    return cTSurface3DChart2;
                }

                @Override
                public void add(int n, CTSurface3DChart cTSurface3DChart) {
                    CTPlotAreaImpl.this.insertNewSurface3DChart(n).set(cTSurface3DChart);
                }

                @Override
                public CTSurface3DChart remove(int n) {
                    CTSurface3DChart cTSurface3DChart = CTPlotAreaImpl.this.getSurface3DChartArray(n);
                    CTPlotAreaImpl.this.removeSurface3DChart(n);
                    return cTSurface3DChart;
                }

                @Override
                public int size() {
                    return CTPlotAreaImpl.this.sizeOfSurface3DChartArray();
                }
            }
            return new Surface3DChartList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTSurface3DChart[] getSurface3DChartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SURFACE3DCHART$30, arrayList);
            CTSurface3DChart[] cTSurface3DChartArray = new CTSurface3DChart[arrayList.size()];
            arrayList.toArray(cTSurface3DChartArray);
            return cTSurface3DChartArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSurface3DChart getSurface3DChartArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSurface3DChart cTSurface3DChart = null;
            cTSurface3DChart = (CTSurface3DChart)((Object)this.get_store().find_element_user(SURFACE3DCHART$30, n));
            if (cTSurface3DChart == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTSurface3DChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSurface3DChartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SURFACE3DCHART$30);
        }
    }

    @Override
    public void setSurface3DChartArray(CTSurface3DChart[] cTSurface3DChartArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTSurface3DChartArray, SURFACE3DCHART$30);
    }

    @Override
    public void setSurface3DChartArray(int n, CTSurface3DChart cTSurface3DChart) {
        this.generatedSetterHelperImpl(cTSurface3DChart, SURFACE3DCHART$30, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSurface3DChart insertNewSurface3DChart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSurface3DChart cTSurface3DChart = null;
            cTSurface3DChart = (CTSurface3DChart)((Object)this.get_store().insert_element_user(SURFACE3DCHART$30, n));
            return cTSurface3DChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSurface3DChart addNewSurface3DChart() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSurface3DChart cTSurface3DChart = null;
            cTSurface3DChart = (CTSurface3DChart)((Object)this.get_store().add_element_user(SURFACE3DCHART$30));
            return cTSurface3DChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSurface3DChart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SURFACE3DCHART$30, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTBubbleChart> getBubbleChartList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class BubbleChartList
            extends AbstractList<CTBubbleChart> {
                BubbleChartList() {
                }

                @Override
                public CTBubbleChart get(int n) {
                    return CTPlotAreaImpl.this.getBubbleChartArray(n);
                }

                @Override
                public CTBubbleChart set(int n, CTBubbleChart cTBubbleChart) {
                    CTBubbleChart cTBubbleChart2 = CTPlotAreaImpl.this.getBubbleChartArray(n);
                    CTPlotAreaImpl.this.setBubbleChartArray(n, cTBubbleChart);
                    return cTBubbleChart2;
                }

                @Override
                public void add(int n, CTBubbleChart cTBubbleChart) {
                    CTPlotAreaImpl.this.insertNewBubbleChart(n).set((XmlObject)cTBubbleChart);
                }

                @Override
                public CTBubbleChart remove(int n) {
                    CTBubbleChart cTBubbleChart = CTPlotAreaImpl.this.getBubbleChartArray(n);
                    CTPlotAreaImpl.this.removeBubbleChart(n);
                    return cTBubbleChart;
                }

                @Override
                public int size() {
                    return CTPlotAreaImpl.this.sizeOfBubbleChartArray();
                }
            }
            return new BubbleChartList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTBubbleChart[] getBubbleChartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(BUBBLECHART$32, arrayList);
            CTBubbleChart[] cTBubbleChartArray = new CTBubbleChart[arrayList.size()];
            arrayList.toArray(cTBubbleChartArray);
            return cTBubbleChartArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBubbleChart getBubbleChartArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBubbleChart cTBubbleChart = null;
            cTBubbleChart = (CTBubbleChart)this.get_store().find_element_user(BUBBLECHART$32, n);
            if (cTBubbleChart == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTBubbleChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfBubbleChartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BUBBLECHART$32);
        }
    }

    @Override
    public void setBubbleChartArray(CTBubbleChart[] cTBubbleChartArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTBubbleChartArray, BUBBLECHART$32);
    }

    @Override
    public void setBubbleChartArray(int n, CTBubbleChart cTBubbleChart) {
        this.generatedSetterHelperImpl((XmlObject)cTBubbleChart, BUBBLECHART$32, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBubbleChart insertNewBubbleChart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBubbleChart cTBubbleChart = null;
            cTBubbleChart = (CTBubbleChart)this.get_store().insert_element_user(BUBBLECHART$32, n);
            return cTBubbleChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBubbleChart addNewBubbleChart() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBubbleChart cTBubbleChart = null;
            cTBubbleChart = (CTBubbleChart)this.get_store().add_element_user(BUBBLECHART$32);
            return cTBubbleChart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeBubbleChart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BUBBLECHART$32, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTValAx> getValAxList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ValAxList
            extends AbstractList<CTValAx> {
                ValAxList() {
                }

                @Override
                public CTValAx get(int n) {
                    return CTPlotAreaImpl.this.getValAxArray(n);
                }

                @Override
                public CTValAx set(int n, CTValAx cTValAx) {
                    CTValAx cTValAx2 = CTPlotAreaImpl.this.getValAxArray(n);
                    CTPlotAreaImpl.this.setValAxArray(n, cTValAx);
                    return cTValAx2;
                }

                @Override
                public void add(int n, CTValAx cTValAx) {
                    CTPlotAreaImpl.this.insertNewValAx(n).set(cTValAx);
                }

                @Override
                public CTValAx remove(int n) {
                    CTValAx cTValAx = CTPlotAreaImpl.this.getValAxArray(n);
                    CTPlotAreaImpl.this.removeValAx(n);
                    return cTValAx;
                }

                @Override
                public int size() {
                    return CTPlotAreaImpl.this.sizeOfValAxArray();
                }
            }
            return new ValAxList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTValAx[] getValAxArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(VALAX$34, arrayList);
            CTValAx[] cTValAxArray = new CTValAx[arrayList.size()];
            arrayList.toArray(cTValAxArray);
            return cTValAxArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTValAx getValAxArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTValAx cTValAx = null;
            cTValAx = (CTValAx)((Object)this.get_store().find_element_user(VALAX$34, n));
            if (cTValAx == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTValAx;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfValAxArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(VALAX$34);
        }
    }

    @Override
    public void setValAxArray(CTValAx[] cTValAxArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTValAxArray, VALAX$34);
    }

    @Override
    public void setValAxArray(int n, CTValAx cTValAx) {
        this.generatedSetterHelperImpl(cTValAx, VALAX$34, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTValAx insertNewValAx(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTValAx cTValAx = null;
            cTValAx = (CTValAx)((Object)this.get_store().insert_element_user(VALAX$34, n));
            return cTValAx;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTValAx addNewValAx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTValAx cTValAx = null;
            cTValAx = (CTValAx)((Object)this.get_store().add_element_user(VALAX$34));
            return cTValAx;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeValAx(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(VALAX$34, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTCatAx> getCatAxList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CatAxList
            extends AbstractList<CTCatAx> {
                CatAxList() {
                }

                @Override
                public CTCatAx get(int n) {
                    return CTPlotAreaImpl.this.getCatAxArray(n);
                }

                @Override
                public CTCatAx set(int n, CTCatAx cTCatAx) {
                    CTCatAx cTCatAx2 = CTPlotAreaImpl.this.getCatAxArray(n);
                    CTPlotAreaImpl.this.setCatAxArray(n, cTCatAx);
                    return cTCatAx2;
                }

                @Override
                public void add(int n, CTCatAx cTCatAx) {
                    CTPlotAreaImpl.this.insertNewCatAx(n).set(cTCatAx);
                }

                @Override
                public CTCatAx remove(int n) {
                    CTCatAx cTCatAx = CTPlotAreaImpl.this.getCatAxArray(n);
                    CTPlotAreaImpl.this.removeCatAx(n);
                    return cTCatAx;
                }

                @Override
                public int size() {
                    return CTPlotAreaImpl.this.sizeOfCatAxArray();
                }
            }
            return new CatAxList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTCatAx[] getCatAxArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CATAX$36, arrayList);
            CTCatAx[] cTCatAxArray = new CTCatAx[arrayList.size()];
            arrayList.toArray(cTCatAxArray);
            return cTCatAxArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCatAx getCatAxArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCatAx cTCatAx = null;
            cTCatAx = (CTCatAx)((Object)this.get_store().find_element_user(CATAX$36, n));
            if (cTCatAx == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTCatAx;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCatAxArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CATAX$36);
        }
    }

    @Override
    public void setCatAxArray(CTCatAx[] cTCatAxArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTCatAxArray, CATAX$36);
    }

    @Override
    public void setCatAxArray(int n, CTCatAx cTCatAx) {
        this.generatedSetterHelperImpl(cTCatAx, CATAX$36, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCatAx insertNewCatAx(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCatAx cTCatAx = null;
            cTCatAx = (CTCatAx)((Object)this.get_store().insert_element_user(CATAX$36, n));
            return cTCatAx;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCatAx addNewCatAx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCatAx cTCatAx = null;
            cTCatAx = (CTCatAx)((Object)this.get_store().add_element_user(CATAX$36));
            return cTCatAx;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCatAx(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CATAX$36, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTDateAx> getDateAxList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class DateAxList
            extends AbstractList<CTDateAx> {
                DateAxList() {
                }

                @Override
                public CTDateAx get(int n) {
                    return CTPlotAreaImpl.this.getDateAxArray(n);
                }

                @Override
                public CTDateAx set(int n, CTDateAx cTDateAx) {
                    CTDateAx cTDateAx2 = CTPlotAreaImpl.this.getDateAxArray(n);
                    CTPlotAreaImpl.this.setDateAxArray(n, cTDateAx);
                    return cTDateAx2;
                }

                @Override
                public void add(int n, CTDateAx cTDateAx) {
                    CTPlotAreaImpl.this.insertNewDateAx(n).set(cTDateAx);
                }

                @Override
                public CTDateAx remove(int n) {
                    CTDateAx cTDateAx = CTPlotAreaImpl.this.getDateAxArray(n);
                    CTPlotAreaImpl.this.removeDateAx(n);
                    return cTDateAx;
                }

                @Override
                public int size() {
                    return CTPlotAreaImpl.this.sizeOfDateAxArray();
                }
            }
            return new DateAxList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTDateAx[] getDateAxArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(DATEAX$38, arrayList);
            CTDateAx[] cTDateAxArray = new CTDateAx[arrayList.size()];
            arrayList.toArray(cTDateAxArray);
            return cTDateAxArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDateAx getDateAxArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDateAx cTDateAx = null;
            cTDateAx = (CTDateAx)((Object)this.get_store().find_element_user(DATEAX$38, n));
            if (cTDateAx == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTDateAx;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfDateAxArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DATEAX$38);
        }
    }

    @Override
    public void setDateAxArray(CTDateAx[] cTDateAxArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTDateAxArray, DATEAX$38);
    }

    @Override
    public void setDateAxArray(int n, CTDateAx cTDateAx) {
        this.generatedSetterHelperImpl(cTDateAx, DATEAX$38, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDateAx insertNewDateAx(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDateAx cTDateAx = null;
            cTDateAx = (CTDateAx)((Object)this.get_store().insert_element_user(DATEAX$38, n));
            return cTDateAx;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDateAx addNewDateAx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDateAx cTDateAx = null;
            cTDateAx = (CTDateAx)((Object)this.get_store().add_element_user(DATEAX$38));
            return cTDateAx;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeDateAx(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DATEAX$38, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTSerAx> getSerAxList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SerAxList
            extends AbstractList<CTSerAx> {
                SerAxList() {
                }

                @Override
                public CTSerAx get(int n) {
                    return CTPlotAreaImpl.this.getSerAxArray(n);
                }

                @Override
                public CTSerAx set(int n, CTSerAx cTSerAx) {
                    CTSerAx cTSerAx2 = CTPlotAreaImpl.this.getSerAxArray(n);
                    CTPlotAreaImpl.this.setSerAxArray(n, cTSerAx);
                    return cTSerAx2;
                }

                @Override
                public void add(int n, CTSerAx cTSerAx) {
                    CTPlotAreaImpl.this.insertNewSerAx(n).set(cTSerAx);
                }

                @Override
                public CTSerAx remove(int n) {
                    CTSerAx cTSerAx = CTPlotAreaImpl.this.getSerAxArray(n);
                    CTPlotAreaImpl.this.removeSerAx(n);
                    return cTSerAx;
                }

                @Override
                public int size() {
                    return CTPlotAreaImpl.this.sizeOfSerAxArray();
                }
            }
            return new SerAxList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTSerAx[] getSerAxArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SERAX$40, arrayList);
            CTSerAx[] cTSerAxArray = new CTSerAx[arrayList.size()];
            arrayList.toArray(cTSerAxArray);
            return cTSerAxArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSerAx getSerAxArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSerAx cTSerAx = null;
            cTSerAx = (CTSerAx)((Object)this.get_store().find_element_user(SERAX$40, n));
            if (cTSerAx == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTSerAx;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSerAxArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SERAX$40);
        }
    }

    @Override
    public void setSerAxArray(CTSerAx[] cTSerAxArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTSerAxArray, SERAX$40);
    }

    @Override
    public void setSerAxArray(int n, CTSerAx cTSerAx) {
        this.generatedSetterHelperImpl(cTSerAx, SERAX$40, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSerAx insertNewSerAx(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSerAx cTSerAx = null;
            cTSerAx = (CTSerAx)((Object)this.get_store().insert_element_user(SERAX$40, n));
            return cTSerAx;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSerAx addNewSerAx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSerAx cTSerAx = null;
            cTSerAx = (CTSerAx)((Object)this.get_store().add_element_user(SERAX$40));
            return cTSerAx;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSerAx(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SERAX$40, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDTable getDTable() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDTable cTDTable = null;
            cTDTable = (CTDTable)this.get_store().find_element_user(DTABLE$42, 0);
            if (cTDTable == null) {
                return null;
            }
            return cTDTable;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDTable() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DTABLE$42) != 0;
        }
    }

    @Override
    public void setDTable(CTDTable cTDTable) {
        this.generatedSetterHelperImpl((XmlObject)cTDTable, DTABLE$42, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDTable addNewDTable() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDTable cTDTable = null;
            cTDTable = (CTDTable)this.get_store().add_element_user(DTABLE$42);
            return cTDTable;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDTable() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DTABLE$42, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShapeProperties getSpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShapeProperties cTShapeProperties = null;
            cTShapeProperties = (CTShapeProperties)((Object)this.get_store().find_element_user(SPPR$44, 0));
            if (cTShapeProperties == null) {
                return null;
            }
            return cTShapeProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SPPR$44) != 0;
        }
    }

    @Override
    public void setSpPr(CTShapeProperties cTShapeProperties) {
        this.generatedSetterHelperImpl(cTShapeProperties, SPPR$44, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShapeProperties addNewSpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShapeProperties cTShapeProperties = null;
            cTShapeProperties = (CTShapeProperties)((Object)this.get_store().add_element_user(SPPR$44));
            return cTShapeProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SPPR$44, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExtensionList getExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExtensionList cTExtensionList = null;
            cTExtensionList = (CTExtensionList)((Object)this.get_store().find_element_user(EXTLST$46, 0));
            if (cTExtensionList == null) {
                return null;
            }
            return cTExtensionList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(EXTLST$46) != 0;
        }
    }

    @Override
    public void setExtLst(CTExtensionList cTExtensionList) {
        this.generatedSetterHelperImpl(cTExtensionList, EXTLST$46, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExtensionList addNewExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExtensionList cTExtensionList = null;
            cTExtensionList = (CTExtensionList)((Object)this.get_store().add_element_user(EXTLST$46));
            return cTExtensionList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(EXTLST$46, 0);
        }
    }
}

