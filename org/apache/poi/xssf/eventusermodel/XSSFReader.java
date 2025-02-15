/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.eventusermodel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackageRelationshipCollection;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.util.XMLHelper;
import org.apache.poi.xssf.model.CommentsTable;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.model.ThemesTable;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.xmlbeans.XmlException;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class XSSFReader {
    private static final Set<String> WORKSHEET_RELS = Collections.unmodifiableSet(new HashSet<String>(Arrays.asList(XSSFRelation.WORKSHEET.getRelation(), XSSFRelation.CHARTSHEET.getRelation())));
    private static final POILogger LOGGER = POILogFactory.getLogger(XSSFReader.class);
    protected OPCPackage pkg;
    protected PackagePart workbookPart;

    public XSSFReader(OPCPackage pkg) throws IOException, OpenXML4JException {
        this.pkg = pkg;
        PackageRelationship coreDocRelationship = this.pkg.getRelationshipsByType("http://schemas.openxmlformats.org/officeDocument/2006/relationships/officeDocument").getRelationship(0);
        if (coreDocRelationship == null) {
            if (this.pkg.getRelationshipsByType("http://purl.oclc.org/ooxml/officeDocument/relationships/officeDocument").getRelationship(0) != null) {
                throw new POIXMLException("Strict OOXML isn't currently supported, please see bug #57699");
            }
            throw new POIXMLException("OOXML file structure broken/invalid - no core document found!");
        }
        this.workbookPart = this.pkg.getPart(coreDocRelationship);
    }

    public SharedStringsTable getSharedStringsTable() throws IOException, InvalidFormatException {
        ArrayList<PackagePart> parts = this.pkg.getPartsByContentType(XSSFRelation.SHARED_STRINGS.getContentType());
        return parts.size() == 0 ? null : new SharedStringsTable(parts.get(0));
    }

    public StylesTable getStylesTable() throws IOException, InvalidFormatException {
        ArrayList<PackagePart> parts = this.pkg.getPartsByContentType(XSSFRelation.STYLES.getContentType());
        if (parts.size() == 0) {
            return null;
        }
        StylesTable styles = new StylesTable(parts.get(0));
        parts = this.pkg.getPartsByContentType(XSSFRelation.THEME.getContentType());
        if (parts.size() != 0) {
            styles.setTheme(new ThemesTable(parts.get(0)));
        }
        return styles;
    }

    public InputStream getSharedStringsData() throws IOException, InvalidFormatException {
        return XSSFRelation.SHARED_STRINGS.getContents(this.workbookPart);
    }

    public InputStream getStylesData() throws IOException, InvalidFormatException {
        return XSSFRelation.STYLES.getContents(this.workbookPart);
    }

    public InputStream getThemesData() throws IOException, InvalidFormatException {
        return XSSFRelation.THEME.getContents(this.workbookPart);
    }

    public InputStream getWorkbookData() throws IOException, InvalidFormatException {
        return this.workbookPart.getInputStream();
    }

    public InputStream getSheet(String relId) throws IOException, InvalidFormatException {
        PackageRelationship rel = this.workbookPart.getRelationship(relId);
        if (rel == null) {
            throw new IllegalArgumentException("No Sheet found with r:id " + relId);
        }
        PackagePartName relName = PackagingURIHelper.createPartName(rel.getTargetURI());
        PackagePart sheet = this.pkg.getPart(relName);
        if (sheet == null) {
            throw new IllegalArgumentException("No data found for Sheet with r:id " + relId);
        }
        return sheet.getInputStream();
    }

    public Iterator<InputStream> getSheetsData() throws IOException, InvalidFormatException {
        return new SheetIterator(this.workbookPart);
    }

    private static class XMLSheetRefReader
    extends DefaultHandler {
        private static final String SHEET = "sheet";
        private static final String ID = "id";
        private static final String NAME = "name";
        private final List<XSSFSheetRef> sheetRefs = new LinkedList<XSSFSheetRef>();

        private XMLSheetRefReader() {
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException {
            if (localName.equalsIgnoreCase(SHEET)) {
                String name = null;
                String id = null;
                for (int i = 0; i < attrs.getLength(); ++i) {
                    String attrName = attrs.getLocalName(i);
                    if (attrName.equalsIgnoreCase(NAME)) {
                        name = attrs.getValue(i);
                    } else if (attrName.equalsIgnoreCase(ID)) {
                        id = attrs.getValue(i);
                    }
                    if (name == null || id == null) continue;
                    this.sheetRefs.add(new XSSFSheetRef(id, name));
                    break;
                }
            }
        }

        List<XSSFSheetRef> getSheetRefs() {
            return Collections.unmodifiableList(this.sheetRefs);
        }
    }

    protected static final class XSSFSheetRef {
        private final String id;
        private final String name;

        public XSSFSheetRef(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return this.id;
        }

        public String getName() {
            return this.name;
        }
    }

    public static class SheetIterator
    implements Iterator<InputStream> {
        private final Map<String, PackagePart> sheetMap;
        XSSFSheetRef xssfSheetRef;
        final Iterator<XSSFSheetRef> sheetIterator;

        SheetIterator(PackagePart wb) throws IOException {
            try {
                this.sheetMap = new HashMap<String, PackagePart>();
                OPCPackage pkg = wb.getPackage();
                Set<String> worksheetRels = this.getSheetRelationships();
                for (PackageRelationship rel : wb.getRelationships()) {
                    String relType = rel.getRelationshipType();
                    if (!worksheetRels.contains(relType)) continue;
                    PackagePartName relName = PackagingURIHelper.createPartName(rel.getTargetURI());
                    this.sheetMap.put(rel.getId(), pkg.getPart(relName));
                }
                this.sheetIterator = this.createSheetIteratorFromWB(wb);
            } catch (InvalidFormatException e) {
                throw new POIXMLException(e);
            }
        }

        Iterator<XSSFSheetRef> createSheetIteratorFromWB(PackagePart wb) throws IOException {
            XMLReader xmlReader;
            XMLSheetRefReader xmlSheetRefReader = new XMLSheetRefReader();
            try {
                xmlReader = XMLHelper.newXMLReader();
            } catch (ParserConfigurationException | SAXException e) {
                throw new POIXMLException(e);
            }
            xmlReader.setContentHandler(xmlSheetRefReader);
            try {
                xmlReader.parse(new InputSource(wb.getInputStream()));
            } catch (SAXException e) {
                throw new POIXMLException(e);
            }
            ArrayList<XSSFSheetRef> validSheets = new ArrayList<XSSFSheetRef>();
            for (XSSFSheetRef xssfSheetRef : xmlSheetRefReader.getSheetRefs()) {
                String sheetId = xssfSheetRef.getId();
                if (sheetId == null || sheetId.length() <= 0) continue;
                validSheets.add(xssfSheetRef);
            }
            return validSheets.iterator();
        }

        Set<String> getSheetRelationships() {
            return WORKSHEET_RELS;
        }

        @Override
        public boolean hasNext() {
            return this.sheetIterator.hasNext();
        }

        @Override
        public InputStream next() {
            this.xssfSheetRef = this.sheetIterator.next();
            String sheetId = this.xssfSheetRef.getId();
            try {
                PackagePart sheetPkg = this.sheetMap.get(sheetId);
                return sheetPkg.getInputStream();
            } catch (IOException e) {
                throw new POIXMLException(e);
            }
        }

        public String getSheetName() {
            return this.xssfSheetRef.getName();
        }

        public CommentsTable getSheetComments() {
            PackagePart sheetPkg = this.getSheetPart();
            try {
                PackageRelationshipCollection commentsList = sheetPkg.getRelationshipsByType(XSSFRelation.SHEET_COMMENTS.getRelation());
                if (commentsList.size() > 0) {
                    PackageRelationship comments = commentsList.getRelationship(0);
                    PackagePartName commentsName = PackagingURIHelper.createPartName(comments.getTargetURI());
                    PackagePart commentsPart = sheetPkg.getPackage().getPart(commentsName);
                    return new CommentsTable(commentsPart);
                }
            } catch (IOException | InvalidFormatException e) {
                LOGGER.log(5, e);
                return null;
            }
            return null;
        }

        public List<XSSFShape> getShapes() {
            PackagePart sheetPkg = this.getSheetPart();
            LinkedList<XSSFShape> shapes = new LinkedList<XSSFShape>();
            try {
                PackageRelationshipCollection drawingsList = sheetPkg.getRelationshipsByType(XSSFRelation.DRAWINGS.getRelation());
                for (int i = 0; i < drawingsList.size(); ++i) {
                    PackageRelationship drawings = drawingsList.getRelationship(i);
                    PackagePartName drawingsName = PackagingURIHelper.createPartName(drawings.getTargetURI());
                    PackagePart drawingsPart = sheetPkg.getPackage().getPart(drawingsName);
                    if (drawingsPart == null) {
                        LOGGER.log(5, "Missing drawing: " + drawingsName + ". Skipping it.");
                        continue;
                    }
                    XSSFDrawing drawing = new XSSFDrawing(drawingsPart);
                    shapes.addAll(drawing.getShapes());
                }
            } catch (IOException | InvalidFormatException | XmlException e) {
                LOGGER.log(5, e);
                return null;
            }
            return shapes;
        }

        public PackagePart getSheetPart() {
            String sheetId = this.xssfSheetRef.getId();
            return this.sheetMap.get(sheetId);
        }

        @Override
        public void remove() {
            throw new IllegalStateException("Not supported");
        }
    }
}

