/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.extractor;

import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.extractor.POITextExtractor;
import org.apache.poi.ooxml.extractor.POIXMLTextExtractor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.extractor.ExcelExtractor;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.xssf.binary.XSSFBCommentsTable;
import org.apache.poi.xssf.binary.XSSFBHyperlinksTable;
import org.apache.poi.xssf.binary.XSSFBSharedStringsTable;
import org.apache.poi.xssf.binary.XSSFBSheetHandler;
import org.apache.poi.xssf.binary.XSSFBStylesTable;
import org.apache.poi.xssf.eventusermodel.XSSFBReader;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.extractor.XSSFEventBasedExcelExtractor;
import org.apache.poi.xssf.model.SharedStrings;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.XmlException;
import org.xml.sax.SAXException;

public class XSSFBEventBasedExcelExtractor
extends XSSFEventBasedExcelExtractor
implements ExcelExtractor {
    private static final POILogger LOGGER = POILogFactory.getLogger(XSSFBEventBasedExcelExtractor.class);
    public static final XSSFRelation[] SUPPORTED_TYPES = new XSSFRelation[]{XSSFRelation.XLSB_BINARY_WORKBOOK};
    private boolean handleHyperlinksInCells;

    public XSSFBEventBasedExcelExtractor(String path) throws XmlException, OpenXML4JException, IOException {
        super(path);
    }

    public XSSFBEventBasedExcelExtractor(OPCPackage container) throws XmlException, OpenXML4JException, IOException {
        super(container);
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.err.println("Use:");
            System.err.println("  XSSFBEventBasedExcelExtractor <filename.xlsb>");
            System.exit(1);
        }
        XSSFBEventBasedExcelExtractor extractor = new XSSFBEventBasedExcelExtractor(args[0]);
        System.out.println(((POITextExtractor)extractor).getText());
        ((POIXMLTextExtractor)extractor).close();
    }

    public void setHandleHyperlinksInCells(boolean handleHyperlinksInCells) {
        this.handleHyperlinksInCells = handleHyperlinksInCells;
    }

    @Override
    public void setFormulasNotResults(boolean formulasNotResults) {
        throw new IllegalArgumentException("Not currently supported");
    }

    public void processSheet(XSSFSheetXMLHandler.SheetContentsHandler sheetContentsExtractor, XSSFBStylesTable styles, XSSFBCommentsTable comments, SharedStrings strings, InputStream sheetInputStream) throws IOException {
        DataFormatter formatter = this.getLocale() == null ? new DataFormatter() : new DataFormatter(this.getLocale());
        XSSFBSheetHandler xssfbSheetHandler = new XSSFBSheetHandler(sheetInputStream, styles, comments, strings, sheetContentsExtractor, formatter, this.getFormulasNotResults());
        xssfbSheetHandler.parse();
    }

    @Override
    public String getText() {
        try {
            XSSFBSharedStringsTable strings = new XSSFBSharedStringsTable(this.getPackage());
            XSSFBReader xssfbReader = new XSSFBReader(this.getPackage());
            XSSFBStylesTable styles = xssfbReader.getXSSFBStylesTable();
            XSSFBReader.SheetIterator iter = (XSSFBReader.SheetIterator)xssfbReader.getSheetsData();
            StringBuilder text = new StringBuilder(64);
            XSSFEventBasedExcelExtractor.SheetTextExtractor sheetExtractor = new XSSFEventBasedExcelExtractor.SheetTextExtractor(this);
            XSSFBHyperlinksTable hyperlinksTable = null;
            while (iter.hasNext()) {
                InputStream stream = iter.next();
                if (this.getIncludeSheetNames()) {
                    text.append(iter.getSheetName());
                    text.append('\n');
                }
                if (this.handleHyperlinksInCells) {
                    hyperlinksTable = new XSSFBHyperlinksTable(iter.getSheetPart());
                }
                XSSFBCommentsTable comments = this.getIncludeCellComments() ? iter.getXSSFBSheetComments() : null;
                this.processSheet((XSSFSheetXMLHandler.SheetContentsHandler)sheetExtractor, styles, comments, (SharedStrings)strings, stream);
                if (this.getIncludeHeadersFooters()) {
                    sheetExtractor.appendHeaderText(text);
                }
                sheetExtractor.appendCellText(text);
                if (this.getIncludeTextBoxes()) {
                    this.processShapes(iter.getShapes(), text);
                }
                if (this.getIncludeHeadersFooters()) {
                    sheetExtractor.appendFooterText(text);
                }
                sheetExtractor.reset();
                stream.close();
            }
            return text.toString();
        } catch (IOException | OpenXML4JException | SAXException e) {
            LOGGER.log(5, e);
            return null;
        }
    }
}

