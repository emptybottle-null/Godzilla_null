/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.extractor;

import java.io.IOException;
import java.util.Iterator;
import java.util.Locale;
import org.apache.poi.extractor.POITextExtractor;
import org.apache.poi.ooxml.extractor.POIXMLTextExtractor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.extractor.ExcelExtractor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.HeaderFooter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFSimpleShape;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.XmlException;

public class XSSFExcelExtractor
extends POIXMLTextExtractor
implements ExcelExtractor {
    public static final XSSFRelation[] SUPPORTED_TYPES = new XSSFRelation[]{XSSFRelation.WORKBOOK, XSSFRelation.MACRO_TEMPLATE_WORKBOOK, XSSFRelation.MACRO_ADDIN_WORKBOOK, XSSFRelation.TEMPLATE_WORKBOOK, XSSFRelation.MACROS_WORKBOOK};
    private Locale locale;
    private XSSFWorkbook workbook;
    private boolean includeSheetNames = true;
    private boolean formulasNotResults;
    private boolean includeCellComments;
    private boolean includeHeadersFooters = true;
    private boolean includeTextBoxes = true;

    public XSSFExcelExtractor(OPCPackage container) throws XmlException, OpenXML4JException, IOException {
        this(new XSSFWorkbook(container));
    }

    public XSSFExcelExtractor(XSSFWorkbook workbook) {
        super(workbook);
        this.workbook = workbook;
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.err.println("Use:");
            System.err.println("  XSSFExcelExtractor <filename.xlsx>");
            System.exit(1);
        }
        try (OPCPackage pkg = OPCPackage.create(args[0]);
             XSSFExcelExtractor extractor = new XSSFExcelExtractor(pkg);){
            System.out.println(((POITextExtractor)extractor).getText());
        }
    }

    @Override
    public void setIncludeSheetNames(boolean includeSheetNames) {
        this.includeSheetNames = includeSheetNames;
    }

    @Override
    public void setFormulasNotResults(boolean formulasNotResults) {
        this.formulasNotResults = formulasNotResults;
    }

    @Override
    public void setIncludeCellComments(boolean includeCellComments) {
        this.includeCellComments = includeCellComments;
    }

    @Override
    public void setIncludeHeadersFooters(boolean includeHeadersFooters) {
        this.includeHeadersFooters = includeHeadersFooters;
    }

    public void setIncludeTextBoxes(boolean includeTextBoxes) {
        this.includeTextBoxes = includeTextBoxes;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public String getText() {
        DataFormatter formatter = this.locale == null ? new DataFormatter() : new DataFormatter(this.locale);
        StringBuilder text = new StringBuilder(64);
        for (Sheet sh : this.workbook) {
            XSSFDrawing drawing;
            XSSFSheet sheet = (XSSFSheet)sh;
            if (this.includeSheetNames) {
                text.append(sheet.getSheetName()).append("\n");
            }
            if (this.includeHeadersFooters) {
                text.append(this.extractHeaderFooter(sheet.getFirstHeader()));
                text.append(this.extractHeaderFooter(sheet.getOddHeader()));
                text.append(this.extractHeaderFooter(sheet.getEvenHeader()));
            }
            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row rawR;
                Row row = rawR = iterator.next();
                Iterator<Cell> ri = row.cellIterator();
                while (ri.hasNext()) {
                    Cell cell = ri.next();
                    if (cell.getCellType() == CellType.FORMULA) {
                        if (this.formulasNotResults) {
                            String contents = cell.getCellFormula();
                            this.checkMaxTextSize(text, contents);
                            text.append(contents);
                        } else if (cell.getCachedFormulaResultType() == CellType.STRING) {
                            this.handleStringCell(text, cell);
                        } else {
                            this.handleNonStringCell(text, cell, formatter);
                        }
                    } else if (cell.getCellType() == CellType.STRING) {
                        this.handleStringCell(text, cell);
                    } else {
                        this.handleNonStringCell(text, cell, formatter);
                    }
                    Comment comment = cell.getCellComment();
                    if (this.includeCellComments && comment != null) {
                        String commentText = comment.getString().getString().replace('\n', ' ');
                        this.checkMaxTextSize(text, commentText);
                        text.append(" Comment by ").append(comment.getAuthor()).append(": ").append(commentText);
                    }
                    if (!ri.hasNext()) continue;
                    text.append("\t");
                }
                text.append("\n");
            }
            if (this.includeTextBoxes && (drawing = sheet.getDrawingPatriarch()) != null) {
                for (XSSFShape shape : drawing.getShapes()) {
                    String boxText;
                    if (!(shape instanceof XSSFSimpleShape) || (boxText = ((XSSFSimpleShape)shape).getText()).length() <= 0) continue;
                    text.append(boxText);
                    text.append('\n');
                }
            }
            if (!this.includeHeadersFooters) continue;
            text.append(this.extractHeaderFooter(sheet.getFirstFooter()));
            text.append(this.extractHeaderFooter(sheet.getOddFooter()));
            text.append(this.extractHeaderFooter(sheet.getEvenFooter()));
        }
        return text.toString();
    }

    private void handleStringCell(StringBuilder text, Cell cell) {
        String contents = cell.getRichStringCellValue().getString();
        this.checkMaxTextSize(text, contents);
        text.append(contents);
    }

    private void handleNonStringCell(StringBuilder text, Cell cell, DataFormatter formatter) {
        CellStyle cs;
        CellType type = cell.getCellType();
        if (type == CellType.FORMULA) {
            type = cell.getCachedFormulaResultType();
        }
        if (type == CellType.NUMERIC && (cs = cell.getCellStyle()) != null && cs.getDataFormatString() != null) {
            String contents = formatter.formatRawCellContents(cell.getNumericCellValue(), cs.getDataFormat(), cs.getDataFormatString());
            this.checkMaxTextSize(text, contents);
            text.append(contents);
            return;
        }
        String contents = ((XSSFCell)cell).getRawValue();
        if (contents != null) {
            this.checkMaxTextSize(text, contents);
            text.append(contents);
        }
    }

    private String extractHeaderFooter(HeaderFooter hf) {
        return org.apache.poi.hssf.extractor.ExcelExtractor._extractHeaderFooter(hf);
    }
}

