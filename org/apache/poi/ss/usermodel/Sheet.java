/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.AutoFilter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellRange;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Footer;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.SheetConditionalFormatting;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.PaneInformation;

public interface Sheet
extends Iterable<Row> {
    public static final short LeftMargin = 0;
    public static final short RightMargin = 1;
    public static final short TopMargin = 2;
    public static final short BottomMargin = 3;
    public static final short HeaderMargin = 4;
    public static final short FooterMargin = 5;
    public static final byte PANE_LOWER_RIGHT = 0;
    public static final byte PANE_UPPER_RIGHT = 1;
    public static final byte PANE_LOWER_LEFT = 2;
    public static final byte PANE_UPPER_LEFT = 3;

    public Row createRow(int var1);

    public void removeRow(Row var1);

    public Row getRow(int var1);

    public int getPhysicalNumberOfRows();

    public int getFirstRowNum();

    public int getLastRowNum();

    public void setColumnHidden(int var1, boolean var2);

    public boolean isColumnHidden(int var1);

    public void setRightToLeft(boolean var1);

    public boolean isRightToLeft();

    public void setColumnWidth(int var1, int var2);

    public int getColumnWidth(int var1);

    public float getColumnWidthInPixels(int var1);

    public void setDefaultColumnWidth(int var1);

    public int getDefaultColumnWidth();

    public short getDefaultRowHeight();

    public float getDefaultRowHeightInPoints();

    public void setDefaultRowHeight(short var1);

    public void setDefaultRowHeightInPoints(float var1);

    public CellStyle getColumnStyle(int var1);

    public int addMergedRegion(CellRangeAddress var1);

    public int addMergedRegionUnsafe(CellRangeAddress var1);

    public void validateMergedRegions();

    public void setVerticallyCenter(boolean var1);

    public void setHorizontallyCenter(boolean var1);

    public boolean getHorizontallyCenter();

    public boolean getVerticallyCenter();

    public void removeMergedRegion(int var1);

    public void removeMergedRegions(Collection<Integer> var1);

    public int getNumMergedRegions();

    public CellRangeAddress getMergedRegion(int var1);

    public List<CellRangeAddress> getMergedRegions();

    public Iterator<Row> rowIterator();

    public void setForceFormulaRecalculation(boolean var1);

    public boolean getForceFormulaRecalculation();

    public void setAutobreaks(boolean var1);

    public void setDisplayGuts(boolean var1);

    public void setDisplayZeros(boolean var1);

    public boolean isDisplayZeros();

    public void setFitToPage(boolean var1);

    public void setRowSumsBelow(boolean var1);

    public void setRowSumsRight(boolean var1);

    public boolean getAutobreaks();

    public boolean getDisplayGuts();

    public boolean getFitToPage();

    public boolean getRowSumsBelow();

    public boolean getRowSumsRight();

    public boolean isPrintGridlines();

    public void setPrintGridlines(boolean var1);

    public boolean isPrintRowAndColumnHeadings();

    public void setPrintRowAndColumnHeadings(boolean var1);

    public PrintSetup getPrintSetup();

    public Header getHeader();

    public Footer getFooter();

    public void setSelected(boolean var1);

    public double getMargin(short var1);

    public void setMargin(short var1, double var2);

    public boolean getProtect();

    public void protectSheet(String var1);

    public boolean getScenarioProtect();

    public void setZoom(int var1);

    public short getTopRow();

    public short getLeftCol();

    public void showInPane(int var1, int var2);

    public void shiftRows(int var1, int var2, int var3);

    public void shiftRows(int var1, int var2, int var3, boolean var4, boolean var5);

    public void shiftColumns(int var1, int var2, int var3);

    public void createFreezePane(int var1, int var2, int var3, int var4);

    public void createFreezePane(int var1, int var2);

    public void createSplitPane(int var1, int var2, int var3, int var4, int var5);

    public PaneInformation getPaneInformation();

    public void setDisplayGridlines(boolean var1);

    public boolean isDisplayGridlines();

    public void setDisplayFormulas(boolean var1);

    public boolean isDisplayFormulas();

    public void setDisplayRowColHeadings(boolean var1);

    public boolean isDisplayRowColHeadings();

    public void setRowBreak(int var1);

    public boolean isRowBroken(int var1);

    public void removeRowBreak(int var1);

    public int[] getRowBreaks();

    public int[] getColumnBreaks();

    public void setColumnBreak(int var1);

    public boolean isColumnBroken(int var1);

    public void removeColumnBreak(int var1);

    public void setColumnGroupCollapsed(int var1, boolean var2);

    public void groupColumn(int var1, int var2);

    public void ungroupColumn(int var1, int var2);

    public void groupRow(int var1, int var2);

    public void ungroupRow(int var1, int var2);

    public void setRowGroupCollapsed(int var1, boolean var2);

    public void setDefaultColumnStyle(int var1, CellStyle var2);

    public void autoSizeColumn(int var1);

    public void autoSizeColumn(int var1, boolean var2);

    public Comment getCellComment(CellAddress var1);

    public Map<CellAddress, ? extends Comment> getCellComments();

    public Drawing<?> getDrawingPatriarch();

    public Drawing<?> createDrawingPatriarch();

    public Workbook getWorkbook();

    public String getSheetName();

    public boolean isSelected();

    public CellRange<? extends Cell> setArrayFormula(String var1, CellRangeAddress var2);

    public CellRange<? extends Cell> removeArrayFormula(Cell var1);

    public DataValidationHelper getDataValidationHelper();

    public List<? extends DataValidation> getDataValidations();

    public void addValidationData(DataValidation var1);

    public AutoFilter setAutoFilter(CellRangeAddress var1);

    public SheetConditionalFormatting getSheetConditionalFormatting();

    public CellRangeAddress getRepeatingRows();

    public CellRangeAddress getRepeatingColumns();

    public void setRepeatingRows(CellRangeAddress var1);

    public void setRepeatingColumns(CellRangeAddress var1);

    public int getColumnOutlineLevel(int var1);

    public Hyperlink getHyperlink(int var1, int var2);

    public Hyperlink getHyperlink(CellAddress var1);

    public List<? extends Hyperlink> getHyperlinkList();

    public CellAddress getActiveCell();

    public void setActiveCell(CellAddress var1);
}

