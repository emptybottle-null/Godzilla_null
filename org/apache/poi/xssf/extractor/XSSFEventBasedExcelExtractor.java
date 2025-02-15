/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.extractor;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.poi.extractor.POITextExtractor;
import org.apache.poi.ooxml.POIXMLProperties;
import org.apache.poi.ooxml.extractor.POIXMLTextExtractor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.extractor.ExcelExtractor;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.util.XMLHelper;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.model.Comments;
import org.apache.poi.xssf.model.CommentsTable;
import org.apache.poi.xssf.model.SharedStrings;
import org.apache.poi.xssf.model.Styles;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSimpleShape;
import org.apache.xmlbeans.XmlException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class XSSFEventBasedExcelExtractor
extends POIXMLTextExtractor
implements ExcelExtractor {
    private static final POILogger LOGGER = POILogFactory.getLogger(XSSFEventBasedExcelExtractor.class);
    protected OPCPackage container;
    protected POIXMLProperties properties;
    protected Locale locale;
    protected boolean includeTextBoxes = true;
    protected boolean includeSheetNames = true;
    protected boolean includeCellComments;
    protected boolean includeHeadersFooters = true;
    protected boolean formulasNotResults;
    protected boolean concatenatePhoneticRuns = true;

    public XSSFEventBasedExcelExtractor(String path) throws XmlException, OpenXML4JException, IOException {
        this(OPCPackage.open(path));
    }

    public XSSFEventBasedExcelExtractor(OPCPackage container) throws XmlException, OpenXML4JException, IOException {
        super(null);
        this.container = container;
        this.properties = new POIXMLProperties(container);
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.err.println("Use:");
            System.err.println("  XSSFEventBasedExcelExtractor <filename.xlsx>");
            System.exit(1);
        }
        XSSFEventBasedExcelExtractor extractor = new XSSFEventBasedExcelExtractor(args[0]);
        System.out.println(((POITextExtractor)extractor).getText());
        ((POIXMLTextExtractor)extractor).close();
    }

    @Override
    public void setIncludeSheetNames(boolean includeSheetNames) {
        this.includeSheetNames = includeSheetNames;
    }

    public boolean getIncludeSheetNames() {
        return this.includeSheetNames;
    }

    @Override
    public void setFormulasNotResults(boolean formulasNotResults) {
        this.formulasNotResults = formulasNotResults;
    }

    public boolean getFormulasNotResults() {
        return this.formulasNotResults;
    }

    @Override
    public void setIncludeHeadersFooters(boolean includeHeadersFooters) {
        this.includeHeadersFooters = includeHeadersFooters;
    }

    public boolean getIncludeHeadersFooters() {
        return this.includeHeadersFooters;
    }

    public void setIncludeTextBoxes(boolean includeTextBoxes) {
        this.includeTextBoxes = includeTextBoxes;
    }

    public boolean getIncludeTextBoxes() {
        return this.includeTextBoxes;
    }

    @Override
    public void setIncludeCellComments(boolean includeCellComments) {
        this.includeCellComments = includeCellComments;
    }

    public boolean getIncludeCellComments() {
        return this.includeCellComments;
    }

    public void setConcatenatePhoneticRuns(boolean concatenatePhoneticRuns) {
        this.concatenatePhoneticRuns = concatenatePhoneticRuns;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return this.locale;
    }

    @Override
    public OPCPackage getPackage() {
        return this.container;
    }

    @Override
    public POIXMLProperties.CoreProperties getCoreProperties() {
        return this.properties.getCoreProperties();
    }

    @Override
    public POIXMLProperties.ExtendedProperties getExtendedProperties() {
        return this.properties.getExtendedProperties();
    }

    @Override
    public POIXMLProperties.CustomProperties getCustomProperties() {
        return this.properties.getCustomProperties();
    }

    public void processSheet(XSSFSheetXMLHandler.SheetContentsHandler sheetContentsExtractor, Styles styles, Comments comments, SharedStrings strings, InputStream sheetInputStream) throws IOException, SAXException {
        DataFormatter formatter = this.locale == null ? new DataFormatter() : new DataFormatter(this.locale);
        InputSource sheetSource = new InputSource(sheetInputStream);
        try {
            XMLReader sheetParser = XMLHelper.newXMLReader();
            XSSFSheetXMLHandler handler = new XSSFSheetXMLHandler(styles, comments, strings, sheetContentsExtractor, formatter, this.formulasNotResults);
            sheetParser.setContentHandler(handler);
            sheetParser.parse(sheetSource);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("SAX parser appears to be broken - " + e.getMessage());
        }
    }

    protected SharedStrings createSharedStringsTable(XSSFReader xssfReader, OPCPackage container) throws IOException, SAXException {
        return new ReadOnlySharedStringsTable(container, this.concatenatePhoneticRuns);
    }

    @Override
    public String getText() {
        try {
            XSSFReader xssfReader = new XSSFReader(this.container);
            SharedStrings strings = this.createSharedStringsTable(xssfReader, this.container);
            StylesTable styles = xssfReader.getStylesTable();
            XSSFReader.SheetIterator iter = (XSSFReader.SheetIterator)xssfReader.getSheetsData();
            StringBuilder text = new StringBuilder(64);
            SheetTextExtractor sheetExtractor = new SheetTextExtractor();
            while (iter.hasNext()) {
                InputStream stream = iter.next();
                if (this.includeSheetNames) {
                    text.append(iter.getSheetName());
                    text.append('\n');
                }
                CommentsTable comments = this.includeCellComments ? iter.getSheetComments() : null;
                this.processSheet(sheetExtractor, styles, comments, strings, stream);
                if (this.includeHeadersFooters) {
                    sheetExtractor.appendHeaderText(text);
                }
                sheetExtractor.appendCellText(text);
                if (this.includeTextBoxes) {
                    this.processShapes(iter.getShapes(), text);
                }
                if (this.includeHeadersFooters) {
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

    void processShapes(List<XSSFShape> shapes, StringBuilder text) {
        if (shapes == null) {
            return;
        }
        for (XSSFShape shape : shapes) {
            String sText;
            if (!(shape instanceof XSSFSimpleShape) || (sText = ((XSSFSimpleShape)shape).getText()) == null || sText.length() <= 0) continue;
            text.append(sText).append('\n');
        }
    }

    @Override
    public void close() throws IOException {
        if (this.container != null) {
            this.container.close();
            this.container = null;
        }
        super.close();
    }

    protected class SheetTextExtractor
    implements XSSFSheetXMLHandler.SheetContentsHandler {
        private final StringBuilder output = new StringBuilder(64);
        private boolean firstCellOfRow = true;
        private final Map<String, String> headerFooterMap;

        protected SheetTextExtractor() {
            this.headerFooterMap = XSSFEventBasedExcelExtractor.this.includeHeadersFooters ? new HashMap() : null;
        }

        @Override
        public void startRow(int rowNum) {
            this.firstCellOfRow = true;
        }

        @Override
        public void endRow(int rowNum) {
            this.output.append('\n');
        }

        @Override
        public void cell(String cellRef, String formattedValue, XSSFComment comment) {
            if (this.firstCellOfRow) {
                this.firstCellOfRow = false;
            } else {
                this.output.append('\t');
            }
            if (formattedValue != null) {
                XSSFEventBasedExcelExtractor.this.checkMaxTextSize(this.output, formattedValue);
                this.output.append(formattedValue);
            }
            if (XSSFEventBasedExcelExtractor.this.includeCellComments && comment != null) {
                String commentText = comment.getString().getString().replace('\n', ' ');
                this.output.append(formattedValue != null ? " Comment by " : "Comment by ");
                XSSFEventBasedExcelExtractor.this.checkMaxTextSize(this.output, commentText);
                if (commentText.startsWith(comment.getAuthor() + ": ")) {
                    this.output.append(commentText);
                } else {
                    this.output.append(comment.getAuthor()).append(": ").append(commentText);
                }
            }
        }

        @Override
        public void headerFooter(String text, boolean isHeader, String tagName) {
            if (this.headerFooterMap != null) {
                this.headerFooterMap.put(tagName, text);
            }
        }

        private void appendHeaderFooterText(StringBuilder buffer, String name) {
            String text = this.headerFooterMap.get(name);
            if (text != null && text.length() > 0) {
                text = this.handleHeaderFooterDelimiter(text, "&L");
                text = this.handleHeaderFooterDelimiter(text, "&C");
                text = this.handleHeaderFooterDelimiter(text, "&R");
                buffer.append(text).append('\n');
            }
        }

        private String handleHeaderFooterDelimiter(String text, String delimiter) {
            int index = text.indexOf(delimiter);
            if (index == 0) {
                text = text.substring(2);
            } else if (index > 0) {
                text = text.substring(0, index) + "\t" + text.substring(index + 2);
            }
            return text;
        }

        void appendHeaderText(StringBuilder buffer) {
            this.appendHeaderFooterText(buffer, "firstHeader");
            this.appendHeaderFooterText(buffer, "oddHeader");
            this.appendHeaderFooterText(buffer, "evenHeader");
        }

        void appendFooterText(StringBuilder buffer) {
            this.appendHeaderFooterText(buffer, "firstFooter");
            this.appendHeaderFooterText(buffer, "oddFooter");
            this.appendHeaderFooterText(buffer, "evenFooter");
        }

        void appendCellText(StringBuilder buffer) {
            XSSFEventBasedExcelExtractor.this.checkMaxTextSize(buffer, this.output.toString());
            buffer.append((CharSequence)this.output);
        }

        void reset() {
            this.output.setLength(0);
            this.firstCellOfRow = true;
            if (this.headerFooterMap != null) {
                this.headerFooterMap.clear();
            }
        }
    }
}

