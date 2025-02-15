/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import org.apache.poi.ss.formula.FormulaParseException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.Removal;

public interface Cell {
    public int getColumnIndex();

    public int getRowIndex();

    public Sheet getSheet();

    public Row getRow();

    @Deprecated
    @Removal(version="5.0")
    public void setCellType(CellType var1);

    public void setBlank();

    public CellType getCellType();

    @Deprecated
    @Removal(version="4.2")
    public CellType getCellTypeEnum();

    public CellType getCachedFormulaResultType();

    @Deprecated
    @Removal(version="4.2")
    public CellType getCachedFormulaResultTypeEnum();

    public void setCellValue(double var1);

    public void setCellValue(Date var1);

    public void setCellValue(LocalDateTime var1);

    default public void setCellValue(LocalDate value) {
        this.setCellValue(value == null ? null : value.atStartOfDay());
    }

    public void setCellValue(Calendar var1);

    public void setCellValue(RichTextString var1);

    public void setCellValue(String var1);

    public void setCellFormula(String var1) throws FormulaParseException, IllegalStateException;

    public void removeFormula() throws IllegalStateException;

    public String getCellFormula();

    public double getNumericCellValue();

    public Date getDateCellValue();

    public LocalDateTime getLocalDateTimeCellValue();

    public RichTextString getRichStringCellValue();

    public String getStringCellValue();

    public void setCellValue(boolean var1);

    public void setCellErrorValue(byte var1);

    public boolean getBooleanCellValue();

    public byte getErrorCellValue();

    public void setCellStyle(CellStyle var1);

    public CellStyle getCellStyle();

    public void setAsActiveCell();

    public CellAddress getAddress();

    public void setCellComment(Comment var1);

    public Comment getCellComment();

    public void removeCellComment();

    public Hyperlink getHyperlink();

    public void setHyperlink(Hyperlink var1);

    public void removeHyperlink();

    public CellRangeAddress getArrayFormulaRange();

    public boolean isPartOfArrayFormulaGroup();
}

