/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;
import javax.xml.namespace.QName;
import org.apache.commons.collections4.ListValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.poi.hpsf.ClassIDPredefined;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.POIXMLProperties;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.ooxml.util.PackageHelper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.Ole10Native;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.SheetNameFormatter;
import org.apache.poi.ss.formula.udf.AggregatingUDFFinder;
import org.apache.poi.ss.formula.udf.IndexedUDFFinder;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.apache.poi.ss.usermodel.Date1904Support;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.SheetVisibility;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.NotImplemented;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.util.Removal;
import org.apache.poi.xssf.XLSBUnsupportedException;
import org.apache.poi.xssf.model.CalculationChain;
import org.apache.poi.xssf.model.ExternalLinksTable;
import org.apache.poi.xssf.model.MapInfo;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.model.ThemesTable;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFDialogsheet;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFFactory;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFMap;
import org.apache.poi.xssf.usermodel.XSSFName;
import org.apache.poi.xssf.usermodel.XSSFPictureData;
import org.apache.poi.xssf.usermodel.XSSFPivotTable;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.poi.xssf.usermodel.XSSFWorkbookType;
import org.apache.poi.xssf.usermodel.helpers.XSSFFormulaUtils;
import org.apache.poi.xssf.usermodel.helpers.XSSFPasswordHelper;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBookView;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBookViews;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalcPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedNames;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotCache;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotCaches;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheets;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbookPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbookProtection;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCalcMode;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STSheetState;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.WorkbookDocument;

public class XSSFWorkbook
extends POIXMLDocument
implements Workbook,
Date1904Support {
    private static final Pattern COMMA_PATTERN = Pattern.compile(",");
    @Deprecated
    @Removal(version="4.1")
    public static final float DEFAULT_CHARACTER_WIDTH = 7.0017f;
    private static final int MAX_SENSITIVE_SHEET_NAME_LEN = 31;
    public static final int PICTURE_TYPE_GIF = 8;
    public static final int PICTURE_TYPE_TIFF = 9;
    public static final int PICTURE_TYPE_EPS = 10;
    public static final int PICTURE_TYPE_BMP = 11;
    public static final int PICTURE_TYPE_WPG = 12;
    private CTWorkbook workbook;
    private List<XSSFSheet> sheets;
    private ListValuedMap<String, XSSFName> namedRangesByName;
    private List<XSSFName> namedRanges;
    private SharedStringsTable sharedStringSource;
    private StylesTable stylesSource;
    private IndexedUDFFinder _udfFinder = new IndexedUDFFinder(AggregatingUDFFinder.DEFAULT);
    private CalculationChain calcChain;
    private List<ExternalLinksTable> externalLinks;
    private MapInfo mapInfo;
    private XSSFDataFormat formatter;
    private Row.MissingCellPolicy _missingCellPolicy = Row.MissingCellPolicy.RETURN_NULL_AND_BLANK;
    private boolean cellFormulaValidation = true;
    private List<XSSFPictureData> pictures;
    private static POILogger logger = POILogFactory.getLogger(XSSFWorkbook.class);
    private XSSFCreationHelper _creationHelper;
    private List<XSSFPivotTable> pivotTables;
    private List<CTPivotCache> pivotCaches;
    private final XSSFFactory xssfFactory;

    public XSSFWorkbook() {
        this(XSSFWorkbookType.XLSX);
    }

    public XSSFWorkbook(XSSFFactory factory) {
        this(XSSFWorkbookType.XLSX, factory);
    }

    public XSSFWorkbook(XSSFWorkbookType workbookType) {
        this(workbookType, null);
    }

    private XSSFWorkbook(XSSFWorkbookType workbookType, XSSFFactory factory) {
        super(XSSFWorkbook.newPackage(workbookType));
        this.xssfFactory = factory == null ? XSSFFactory.getInstance() : factory;
        this.onWorkbookCreate();
    }

    public XSSFWorkbook(OPCPackage pkg) throws IOException {
        super(pkg);
        this.xssfFactory = XSSFFactory.getInstance();
        this.beforeDocumentRead();
        this.load(this.xssfFactory);
        this.setBookViewsIfMissing();
    }

    public XSSFWorkbook(InputStream is) throws IOException {
        this(PackageHelper.open(is));
    }

    public XSSFWorkbook(File file) throws IOException, InvalidFormatException {
        this(OPCPackage.open(file));
    }

    public XSSFWorkbook(String path) throws IOException {
        this(XSSFWorkbook.openPackage(path));
    }

    public XSSFWorkbook(PackagePart part) throws IOException {
        this(part.getInputStream());
    }

    protected void beforeDocumentRead() {
        if (this.getCorePart().getContentType().equals(XSSFRelation.XLSB_BINARY_WORKBOOK.getContentType())) {
            throw new XLSBUnsupportedException();
        }
        this.pivotTables = new ArrayList<XSSFPivotTable>();
        this.pivotCaches = new ArrayList<CTPivotCache>();
    }

    @Override
    protected void onDocumentRead() throws IOException {
        try {
            boolean packageReadOnly;
            WorkbookDocument doc = WorkbookDocument.Factory.parse(this.getPackagePart().getInputStream(), POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            this.workbook = doc.getWorkbook();
            ThemesTable theme = null;
            HashMap<String, XSSFSheet> shIdMap = new HashMap<String, XSSFSheet>();
            HashMap<String, ExternalLinksTable> elIdMap = new HashMap<String, ExternalLinksTable>();
            for (POIXMLDocumentPart.RelationPart rp : this.getRelationParts()) {
                Object p = rp.getDocumentPart();
                if (p instanceof SharedStringsTable) {
                    this.sharedStringSource = (SharedStringsTable)p;
                    continue;
                }
                if (p instanceof StylesTable) {
                    this.stylesSource = (StylesTable)p;
                    continue;
                }
                if (p instanceof ThemesTable) {
                    theme = (ThemesTable)p;
                    continue;
                }
                if (p instanceof CalculationChain) {
                    this.calcChain = (CalculationChain)p;
                    continue;
                }
                if (p instanceof MapInfo) {
                    this.mapInfo = (MapInfo)p;
                    continue;
                }
                if (p instanceof XSSFSheet) {
                    shIdMap.put(rp.getRelationship().getId(), (XSSFSheet)p);
                    continue;
                }
                if (!(p instanceof ExternalLinksTable)) continue;
                elIdMap.put(rp.getRelationship().getId(), (ExternalLinksTable)p);
            }
            boolean bl = packageReadOnly = this.getPackage().getPackageAccess() == PackageAccess.READ;
            if (this.stylesSource == null) {
                this.stylesSource = packageReadOnly ? new StylesTable() : (StylesTable)this.createRelationship(XSSFRelation.STYLES, this.xssfFactory);
            }
            this.stylesSource.setWorkbook(this);
            this.stylesSource.setTheme(theme);
            if (this.sharedStringSource == null) {
                this.sharedStringSource = packageReadOnly ? new SharedStringsTable() : (SharedStringsTable)this.createRelationship(XSSFRelation.SHARED_STRINGS, this.xssfFactory);
            }
            this.sheets = new ArrayList<XSSFSheet>(shIdMap.size());
            for (CTSheet cTSheet : this.workbook.getSheets().getSheetArray()) {
                this.parseSheet(shIdMap, cTSheet);
            }
            this.externalLinks = new ArrayList<ExternalLinksTable>(elIdMap.size());
            if (this.workbook.isSetExternalReferences()) {
                for (XmlObject xmlObject : this.workbook.getExternalReferences().getExternalReferenceArray()) {
                    ExternalLinksTable el = (ExternalLinksTable)elIdMap.get(xmlObject.getId());
                    if (el == null) {
                        logger.log(5, "ExternalLinksTable with r:id " + xmlObject.getId() + " was defined, but didn't exist in package, skipping");
                        continue;
                    }
                    this.externalLinks.add(el);
                }
            }
            this.reprocessNamedRanges();
        } catch (XmlException e) {
            throw new POIXMLException(e);
        }
    }

    public void parseSheet(Map<String, XSSFSheet> shIdMap, CTSheet ctSheet) {
        XSSFSheet sh = shIdMap.get(ctSheet.getId());
        if (sh == null) {
            logger.log(5, "Sheet with name " + ctSheet.getName() + " and r:id " + ctSheet.getId() + " was defined, but didn't exist in package, skipping");
            return;
        }
        sh.sheet = ctSheet;
        sh.onDocumentRead();
        this.sheets.add(sh);
    }

    private void onWorkbookCreate() {
        this.workbook = CTWorkbook.Factory.newInstance();
        CTWorkbookPr workbookPr = this.workbook.addNewWorkbookPr();
        workbookPr.setDate1904(false);
        this.setBookViewsIfMissing();
        this.workbook.addNewSheets();
        POIXMLProperties.ExtendedProperties expProps = this.getProperties().getExtendedProperties();
        expProps.getUnderlyingProperties().setApplication("Apache POI");
        this.sharedStringSource = (SharedStringsTable)this.createRelationship(XSSFRelation.SHARED_STRINGS, this.xssfFactory);
        this.stylesSource = (StylesTable)this.createRelationship(XSSFRelation.STYLES, this.xssfFactory);
        this.stylesSource.setWorkbook(this);
        this.namedRanges = new ArrayList<XSSFName>();
        this.namedRangesByName = new ArrayListValuedHashMap<String, XSSFName>();
        this.sheets = new ArrayList<XSSFSheet>();
        this.pivotTables = new ArrayList<XSSFPivotTable>();
    }

    private void setBookViewsIfMissing() {
        if (!this.workbook.isSetBookViews()) {
            CTBookViews bvs = this.workbook.addNewBookViews();
            CTBookView bv = bvs.addNewWorkbookView();
            bv.setActiveTab(0L);
        }
    }

    protected static OPCPackage newPackage(XSSFWorkbookType workbookType) {
        OPCPackage pkg = null;
        try {
            pkg = OPCPackage.create(new ByteArrayOutputStream());
            PackagePartName corePartName = PackagingURIHelper.createPartName(XSSFRelation.WORKBOOK.getDefaultFileName());
            pkg.addRelationship(corePartName, TargetMode.INTERNAL, "http://schemas.openxmlformats.org/officeDocument/2006/relationships/officeDocument");
            pkg.createPart(corePartName, workbookType.getContentType());
            pkg.getPackageProperties().setCreatorProperty("Apache POI");
        } catch (Exception e) {
            IOUtils.closeQuietly(pkg);
            throw new POIXMLException(e);
        }
        return pkg;
    }

    @Internal
    public CTWorkbook getCTWorkbook() {
        return this.workbook;
    }

    @Override
    public int addPicture(byte[] pictureData, int format) {
        int imageNumber = this.getAllPictures().size() + 1;
        XSSFPictureData img = (XSSFPictureData)this.createRelationship(XSSFPictureData.RELATIONS[format], this.xssfFactory, imageNumber, true).getDocumentPart();
        try (OutputStream out = img.getPackagePart().getOutputStream();){
            out.write(pictureData);
        } catch (IOException e) {
            throw new POIXMLException(e);
        }
        this.pictures.add(img);
        return imageNumber - 1;
    }

    public int addPicture(InputStream is, int format) throws IOException {
        int imageNumber = this.getAllPictures().size() + 1;
        XSSFPictureData img = (XSSFPictureData)this.createRelationship(XSSFPictureData.RELATIONS[format], this.xssfFactory, imageNumber, true).getDocumentPart();
        try (OutputStream out = img.getPackagePart().getOutputStream();){
            IOUtils.copy(is, out);
        }
        this.pictures.add(img);
        return imageNumber - 1;
    }

    @Override
    public XSSFSheet cloneSheet(int sheetNum) {
        return this.cloneSheet(sheetNum, null);
    }

    @Override
    public void close() throws IOException {
        try {
            super.close();
        } finally {
            IOUtils.closeQuietly(this.sharedStringSource);
        }
    }

    public XSSFSheet cloneSheet(int sheetNum, String newName) {
        this.validateSheetIndex(sheetNum);
        XSSFSheet srcSheet = this.sheets.get(sheetNum);
        if (newName == null) {
            String srcName = srcSheet.getSheetName();
            newName = this.getUniqueSheetName(srcName);
        } else {
            this.validateSheetName(newName);
        }
        XSSFSheet clonedSheet = this.createSheet(newName);
        List<POIXMLDocumentPart.RelationPart> rels = srcSheet.getRelationParts();
        XSSFDrawing dg = null;
        for (POIXMLDocumentPart.RelationPart rp : rels) {
            Object r = rp.getDocumentPart();
            if (r instanceof XSSFDrawing) {
                dg = (XSSFDrawing)r;
                continue;
            }
            XSSFWorkbook.addRelation(rp, clonedSheet);
        }
        try {
            for (Object pr : srcSheet.getPackagePart().getRelationships()) {
                if (((PackageRelationship)pr).getTargetMode() != TargetMode.EXTERNAL) continue;
                clonedSheet.getPackagePart().addExternalRelationship(((PackageRelationship)pr).getTargetURI().toASCIIString(), ((PackageRelationship)pr).getRelationshipType(), ((PackageRelationship)pr).getId());
            }
        } catch (InvalidFormatException e) {
            throw new POIXMLException("Failed to clone sheet", e);
        }
        try {
            Object pr;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            pr = null;
            try {
                srcSheet.write(out);
                ByteArrayInputStream bis = new ByteArrayInputStream(out.toByteArray());
                Object object = null;
                try {
                    clonedSheet.read(bis);
                } catch (Throwable throwable) {
                    object = throwable;
                    throw throwable;
                } finally {
                    if (bis != null) {
                        if (object != null) {
                            try {
                                bis.close();
                            } catch (Throwable throwable) {
                                ((Throwable)object).addSuppressed(throwable);
                            }
                        } else {
                            bis.close();
                        }
                    }
                }
            } catch (Throwable bis) {
                pr = bis;
                throw bis;
            } finally {
                if (out != null) {
                    if (pr != null) {
                        try {
                            out.close();
                        } catch (Throwable bis) {
                            ((Throwable)pr).addSuppressed(bis);
                        }
                    } else {
                        out.close();
                    }
                }
            }
        } catch (IOException e) {
            throw new POIXMLException("Failed to clone sheet", e);
        }
        CTWorksheet ct = clonedSheet.getCTWorksheet();
        if (ct.isSetLegacyDrawing()) {
            logger.log(5, "Cloning sheets with comments is not yet supported.");
            ct.unsetLegacyDrawing();
        }
        if (ct.isSetPageSetup()) {
            logger.log(5, "Cloning sheets with page setup is not yet supported.");
            ct.unsetPageSetup();
        }
        clonedSheet.setSelected(false);
        if (dg != null) {
            if (ct.isSetDrawing()) {
                ct.unsetDrawing();
            }
            XSSFDrawing clonedDg = clonedSheet.createDrawingPatriarch();
            clonedDg.getCTDrawing().set(dg.getCTDrawing().copy());
            List<POIXMLDocumentPart.RelationPart> srcRels = srcSheet.createDrawingPatriarch().getRelationParts();
            for (POIXMLDocumentPart.RelationPart rp : srcRels) {
                XSSFWorkbook.addRelation(rp, clonedDg);
            }
        }
        return clonedSheet;
    }

    private static void addRelation(POIXMLDocumentPart.RelationPart rp, POIXMLDocumentPart target) {
        PackageRelationship rel = rp.getRelationship();
        if (rel.getTargetMode() == TargetMode.EXTERNAL) {
            target.getPackagePart().addRelationship(rel.getTargetURI(), rel.getTargetMode(), rel.getRelationshipType(), rel.getId());
        } else {
            XSSFRelation xssfRel = XSSFRelation.getInstance(rel.getRelationshipType());
            if (xssfRel == null) {
                throw new POIXMLException("Can't clone sheet - unknown relation type found: " + rel.getRelationshipType());
            }
            target.addRelation(rel.getId(), xssfRel, (POIXMLDocumentPart)rp.getDocumentPart());
        }
    }

    private String getUniqueSheetName(String srcName) {
        String index;
        String name;
        int uniqueIndex = 2;
        String baseName = srcName;
        int bracketPos = srcName.lastIndexOf(40);
        if (bracketPos > 0 && srcName.endsWith(")")) {
            String suffix = srcName.substring(bracketPos + 1, srcName.length() - ")".length());
            try {
                uniqueIndex = Integer.parseInt(suffix.trim());
                baseName = srcName.substring(0, bracketPos).trim();
            } catch (NumberFormatException numberFormatException) {
                // empty catch block
            }
        }
        do {
            int n = ++uniqueIndex;
            ++uniqueIndex;
            index = Integer.toString(n);
        } while (this.getSheetIndex(name = baseName.length() + index.length() + 2 < 31 ? baseName + " (" + index + ")" : baseName.substring(0, 31 - index.length() - 2) + "(" + index + ")") != -1);
        return name;
    }

    @Override
    public XSSFCellStyle createCellStyle() {
        return this.stylesSource.createCellStyle();
    }

    @Override
    public XSSFDataFormat createDataFormat() {
        if (this.formatter == null) {
            this.formatter = new XSSFDataFormat(this.stylesSource);
        }
        return this.formatter;
    }

    @Override
    public XSSFFont createFont() {
        XSSFFont font = new XSSFFont();
        font.registerTo(this.stylesSource);
        return font;
    }

    @Override
    public XSSFName createName() {
        CTDefinedName ctName = CTDefinedName.Factory.newInstance();
        ctName.setName("");
        return this.createAndStoreName(ctName);
    }

    private XSSFName createAndStoreName(CTDefinedName ctName) {
        XSSFName name = new XSSFName(ctName, this);
        this.namedRanges.add(name);
        this.namedRangesByName.put(ctName.getName().toLowerCase(Locale.ENGLISH), name);
        return name;
    }

    @Override
    public XSSFSheet createSheet() {
        String sheetname = "Sheet" + this.sheets.size();
        int idx = 0;
        while (this.getSheet(sheetname) != null) {
            sheetname = "Sheet" + idx;
            ++idx;
        }
        return this.createSheet(sheetname);
    }

    /*
     * Could not resolve type clashes
     * Unable to fully structure code
     */
    @Override
    public XSSFSheet createSheet(String sheetname) {
        if (sheetname == null) {
            throw new IllegalArgumentException("sheetName must not be null");
        }
        this.validateSheetName(sheetname);
        if (sheetname.length() > 31) {
            sheetname = sheetname.substring(0, 31);
        }
        WorkbookUtil.validateSheetName(sheetname);
        sheet = this.addSheet(sheetname);
        sheetNumber = 1;
        block0: while (true) lbl-1000:
        // 3 sources

        {
            for (Object sh : this.sheets) {
                sheetNumber = (int)Math.max(sh.sheet.getSheetId() + 1L, (long)sheetNumber);
            }
            sheetName = XSSFRelation.WORKSHEET.getFileName(sheetNumber);
            for (POIXMLDocumentPart relation : this.getRelations()) {
                if (relation.getPackagePart() == null || !sheetName.equals(relation.getPackagePart().getPartName().getName())) continue;
                ++sheetNumber;
                continue block0;
                ** continue;
            }
            break;
        }
        rp = this.createRelationship(XSSFRelation.WORKSHEET, this.xssfFactory, sheetNumber, false);
        wrapper = (XSSFSheet)rp.getDocumentPart();
        wrapper.sheet = sheet;
        sheet.setId(rp.getRelationship().getId());
        sheet.setSheetId(sheetNumber);
        if (this.sheets.isEmpty()) {
            wrapper.setSelected(true);
        }
        this.sheets.add(wrapper);
        return wrapper;
    }

    private void validateSheetName(String sheetName) throws IllegalArgumentException {
        if (this.containsSheet(sheetName, this.sheets.size())) {
            throw new IllegalArgumentException("The workbook already contains a sheet named '" + sheetName + "'");
        }
    }

    protected XSSFDialogsheet createDialogsheet(String sheetname, CTDialogsheet dialogsheet) {
        XSSFSheet sheet = this.createSheet(sheetname);
        return new XSSFDialogsheet(sheet);
    }

    private CTSheet addSheet(String sheetname) {
        CTSheet sheet = this.workbook.getSheets().addNewSheet();
        sheet.setName(sheetname);
        return sheet;
    }

    @Override
    public XSSFFont findFont(boolean bold, short color, short fontHeight, String name, boolean italic, boolean strikeout, short typeOffset, byte underline) {
        return this.stylesSource.findFont(bold, color, fontHeight, name, italic, strikeout, typeOffset, underline);
    }

    @Override
    public int getActiveSheetIndex() {
        return (int)this.workbook.getBookViews().getWorkbookViewArray(0).getActiveTab();
    }

    public List<XSSFPictureData> getAllPictures() {
        if (this.pictures == null) {
            List<PackagePart> mediaParts = this.getPackage().getPartsByName(Pattern.compile("/xl/media/.*?"));
            this.pictures = new ArrayList<XSSFPictureData>(mediaParts.size());
            for (PackagePart part : mediaParts) {
                this.pictures.add(new XSSFPictureData(part));
            }
        }
        return this.pictures;
    }

    @Override
    public XSSFCellStyle getCellStyleAt(int idx) {
        return this.stylesSource.getStyleAt(idx);
    }

    @Override
    public XSSFFont getFontAt(short idx) {
        return this.stylesSource.getFontAt(idx);
    }

    @Override
    public XSSFFont getFontAt(int idx) {
        return this.stylesSource.getFontAt(idx);
    }

    @Override
    public XSSFName getName(String name) {
        List<XSSFName> list = this.getNames(name);
        if (list.isEmpty()) {
            return null;
        }
        return (XSSFName)list.iterator().next();
    }

    public List<XSSFName> getNames(String name) {
        return Collections.unmodifiableList(this.namedRangesByName.get((Object)name.toLowerCase(Locale.ENGLISH)));
    }

    @Override
    @Deprecated
    public XSSFName getNameAt(int nameIndex) {
        int nNames = this.namedRanges.size();
        if (nNames < 1) {
            throw new IllegalStateException("There are no defined names in this workbook");
        }
        if (nameIndex < 0 || nameIndex > nNames) {
            throw new IllegalArgumentException("Specified name index " + nameIndex + " is outside the allowable range (0.." + (nNames - 1) + ").");
        }
        return this.namedRanges.get(nameIndex);
    }

    public List<XSSFName> getAllNames() {
        return Collections.unmodifiableList(this.namedRanges);
    }

    @Override
    @Deprecated
    public int getNameIndex(String name) {
        XSSFName nm = this.getName(name);
        if (nm != null) {
            return this.namedRanges.indexOf(nm);
        }
        return -1;
    }

    @Override
    public int getNumCellStyles() {
        return this.stylesSource.getNumCellStyles();
    }

    @Override
    public short getNumberOfFonts() {
        return (short)this.getNumberOfFontsAsInt();
    }

    @Override
    public int getNumberOfFontsAsInt() {
        return (short)this.stylesSource.getFonts().size();
    }

    @Override
    public int getNumberOfNames() {
        return this.namedRanges.size();
    }

    @Override
    public int getNumberOfSheets() {
        return this.sheets.size();
    }

    @Override
    public String getPrintArea(int sheetIndex) {
        XSSFName name = this.getBuiltInName("_xlnm.Print_Area", sheetIndex);
        if (name == null) {
            return null;
        }
        return name.getRefersToFormula();
    }

    @Override
    public XSSFSheet getSheet(String name) {
        for (XSSFSheet sheet : this.sheets) {
            if (!name.equalsIgnoreCase(sheet.getSheetName())) continue;
            return sheet;
        }
        return null;
    }

    @Override
    public XSSFSheet getSheetAt(int index) {
        this.validateSheetIndex(index);
        return this.sheets.get(index);
    }

    @Override
    public int getSheetIndex(String name) {
        int idx = 0;
        for (XSSFSheet sh : this.sheets) {
            if (name.equalsIgnoreCase(sh.getSheetName())) {
                return idx;
            }
            ++idx;
        }
        return -1;
    }

    @Override
    public int getSheetIndex(Sheet sheet) {
        int idx = 0;
        for (XSSFSheet sh : this.sheets) {
            if (sh == sheet) {
                return idx;
            }
            ++idx;
        }
        return -1;
    }

    @Override
    public String getSheetName(int sheetIx) {
        this.validateSheetIndex(sheetIx);
        return this.sheets.get(sheetIx).getSheetName();
    }

    @Override
    public Iterator<Sheet> sheetIterator() {
        return new SheetIterator<Sheet>();
    }

    @Override
    public Iterator<Sheet> iterator() {
        return this.sheetIterator();
    }

    public boolean isMacroEnabled() {
        return this.getPackagePart().getContentType().equals(XSSFRelation.MACROS_WORKBOOK.getContentType());
    }

    @Override
    @Deprecated
    public void removeName(int nameIndex) {
        this.removeName(this.getNameAt(nameIndex));
    }

    @Override
    @Deprecated
    public void removeName(String name) {
        Collection names = this.namedRangesByName.get((Object)name.toLowerCase(Locale.ENGLISH));
        if (names.isEmpty()) {
            throw new IllegalArgumentException("Named range was not found: " + name);
        }
        this.removeName((Name)names.get(0));
    }

    @Override
    public void removeName(Name name) {
        if (!this.namedRangesByName.removeMapping(name.getNameName().toLowerCase(Locale.ENGLISH), name) || !this.namedRanges.remove(name)) {
            throw new IllegalArgumentException("Name was not found: " + name);
        }
    }

    void updateName(XSSFName name, String oldName) {
        if (!this.namedRangesByName.removeMapping(oldName.toLowerCase(Locale.ENGLISH), name)) {
            throw new IllegalArgumentException("Name was not found: " + name);
        }
        this.namedRangesByName.put(name.getNameName().toLowerCase(Locale.ENGLISH), name);
    }

    @Override
    public void removePrintArea(int sheetIndex) {
        XSSFName name = this.getBuiltInName("_xlnm.Print_Area", sheetIndex);
        if (name != null) {
            this.removeName(name);
        }
    }

    @Override
    public void removeSheetAt(int index) {
        int active;
        this.validateSheetIndex(index);
        this.onSheetDelete(index);
        XSSFSheet sheet = this.getSheetAt(index);
        this.removeRelation(sheet);
        this.sheets.remove(index);
        if (this.sheets.size() == 0) {
            return;
        }
        int newSheetIndex = index;
        if (newSheetIndex >= this.sheets.size()) {
            newSheetIndex = this.sheets.size() - 1;
        }
        if ((active = this.getActiveSheetIndex()) == index) {
            this.setActiveSheet(newSheetIndex);
        } else if (active > index) {
            this.setActiveSheet(active - 1);
        }
    }

    private void onSheetDelete(int index) {
        XSSFSheet sheet = this.getSheetAt(index);
        sheet.onSheetDelete();
        this.workbook.getSheets().removeSheet(index);
        if (this.calcChain != null) {
            this.removeRelation(this.calcChain);
            this.calcChain = null;
        }
        ArrayList<XSSFName> toRemove = new ArrayList<XSSFName>();
        for (XSSFName nm : this.namedRanges) {
            CTDefinedName ct = nm.getCTName();
            if (!ct.isSetLocalSheetId()) continue;
            if (ct.getLocalSheetId() == (long)index) {
                toRemove.add(nm);
                continue;
            }
            if (ct.getLocalSheetId() <= (long)index) continue;
            ct.setLocalSheetId(ct.getLocalSheetId() - 1L);
        }
        for (XSSFName nm : toRemove) {
            this.removeName(nm);
        }
    }

    @Override
    public Row.MissingCellPolicy getMissingCellPolicy() {
        return this._missingCellPolicy;
    }

    @Override
    public void setMissingCellPolicy(Row.MissingCellPolicy missingCellPolicy) {
        this._missingCellPolicy = missingCellPolicy;
    }

    @Override
    public void setActiveSheet(int index) {
        this.validateSheetIndex(index);
        for (CTBookView arrayBook : this.workbook.getBookViews().getWorkbookViewArray()) {
            arrayBook.setActiveTab(index);
        }
    }

    private void validateSheetIndex(int index) {
        int lastSheetIx = this.sheets.size() - 1;
        if (index < 0 || index > lastSheetIx) {
            String range = "(0.." + lastSheetIx + ")";
            if (lastSheetIx == -1) {
                range = "(no sheets)";
            }
            throw new IllegalArgumentException("Sheet index (" + index + ") is out of range " + range);
        }
    }

    @Override
    public int getFirstVisibleTab() {
        CTBookViews bookViews = this.workbook.getBookViews();
        CTBookView bookView = bookViews.getWorkbookViewArray(0);
        return (short)bookView.getFirstSheet();
    }

    @Override
    public void setFirstVisibleTab(int index) {
        CTBookViews bookViews = this.workbook.getBookViews();
        CTBookView bookView = bookViews.getWorkbookViewArray(0);
        bookView.setFirstSheet(index);
    }

    @Override
    public void setPrintArea(int sheetIndex, String reference) {
        XSSFName name = this.getBuiltInName("_xlnm.Print_Area", sheetIndex);
        if (name == null) {
            name = this.createBuiltInName("_xlnm.Print_Area", sheetIndex);
        }
        String[] parts = COMMA_PATTERN.split(reference);
        StringBuilder sb = new StringBuilder(32);
        for (int i = 0; i < parts.length; ++i) {
            if (i > 0) {
                sb.append(',');
            }
            SheetNameFormatter.appendFormat(sb, this.getSheetName(sheetIndex));
            sb.append('!');
            sb.append(parts[i]);
        }
        name.setRefersToFormula(sb.toString());
    }

    @Override
    public void setPrintArea(int sheetIndex, int startColumn, int endColumn, int startRow, int endRow) {
        String reference = XSSFWorkbook.getReferencePrintArea(this.getSheetName(sheetIndex), startColumn, endColumn, startRow, endRow);
        this.setPrintArea(sheetIndex, reference);
    }

    private static String getReferencePrintArea(String sheetName, int startC, int endC, int startR, int endR) {
        CellReference colRef = new CellReference(sheetName, startR, startC, true, true);
        CellReference colRef2 = new CellReference(sheetName, endR, endC, true, true);
        return "$" + colRef.getCellRefParts()[2] + "$" + colRef.getCellRefParts()[1] + ":$" + colRef2.getCellRefParts()[2] + "$" + colRef2.getCellRefParts()[1];
    }

    XSSFName getBuiltInName(String builtInCode, int sheetNumber) {
        for (XSSFName name : this.namedRangesByName.get((Object)builtInCode.toLowerCase(Locale.ENGLISH))) {
            if (name.getSheetIndex() != sheetNumber) continue;
            return name;
        }
        return null;
    }

    XSSFName createBuiltInName(String builtInName, int sheetNumber) {
        this.validateSheetIndex(sheetNumber);
        CTDefinedNames names = this.workbook.getDefinedNames() == null ? this.workbook.addNewDefinedNames() : this.workbook.getDefinedNames();
        CTDefinedName nameRecord = names.addNewDefinedName();
        nameRecord.setName(builtInName);
        nameRecord.setLocalSheetId(sheetNumber);
        if (this.getBuiltInName(builtInName, sheetNumber) != null) {
            throw new POIXMLException("Builtin (" + builtInName + ") already exists for sheet (" + sheetNumber + ")");
        }
        return this.createAndStoreName(nameRecord);
    }

    @Override
    public void setSelectedTab(int index) {
        int idx = 0;
        for (XSSFSheet sh : this.sheets) {
            sh.setSelected(idx == index);
            ++idx;
        }
    }

    @Override
    public void setSheetName(int sheetIndex, String sheetname) {
        if (sheetname == null) {
            throw new IllegalArgumentException("sheetName must not be null");
        }
        this.validateSheetIndex(sheetIndex);
        String oldSheetName = this.getSheetName(sheetIndex);
        if (sheetname.length() > 31) {
            sheetname = sheetname.substring(0, 31);
        }
        WorkbookUtil.validateSheetName(sheetname);
        if (sheetname.equals(oldSheetName)) {
            return;
        }
        if (this.containsSheet(sheetname, sheetIndex)) {
            throw new IllegalArgumentException("The workbook already contains a sheet of this name");
        }
        XSSFFormulaUtils utils = new XSSFFormulaUtils(this);
        utils.updateSheetName(sheetIndex, oldSheetName, sheetname);
        this.workbook.getSheets().getSheetArray(sheetIndex).setName(sheetname);
    }

    @Override
    public void setSheetOrder(String sheetname, int pos) {
        int idx = this.getSheetIndex(sheetname);
        this.sheets.add(pos, this.sheets.remove(idx));
        CTSheets ct = this.workbook.getSheets();
        XmlObject cts = ct.getSheetArray(idx).copy();
        this.workbook.getSheets().removeSheet(idx);
        CTSheet newcts = ct.insertNewSheet(pos);
        newcts.set(cts);
        CTSheet[] sheetArray = ct.getSheetArray();
        for (int i = 0; i < sheetArray.length; ++i) {
            this.sheets.get((int)i).sheet = sheetArray[i];
        }
        this.updateNamedRangesAfterSheetReorder(idx, pos);
        this.updateActiveSheetAfterSheetReorder(idx, pos);
    }

    private void updateNamedRangesAfterSheetReorder(int oldIndex, int newIndex) {
        for (XSSFName name : this.namedRanges) {
            int i = name.getSheetIndex();
            if (i == -1) continue;
            if (i == oldIndex) {
                name.setSheetIndex(newIndex);
                continue;
            }
            if (newIndex <= i && i < oldIndex) {
                name.setSheetIndex(i + 1);
                continue;
            }
            if (oldIndex >= i || i > newIndex) continue;
            name.setSheetIndex(i - 1);
        }
    }

    private void updateActiveSheetAfterSheetReorder(int oldIndex, int newIndex) {
        int active = this.getActiveSheetIndex();
        if (active == oldIndex) {
            this.setActiveSheet(newIndex);
        } else if (!(active < oldIndex && active < newIndex || active > oldIndex && active > newIndex)) {
            if (newIndex > oldIndex) {
                this.setActiveSheet(active - 1);
            } else {
                this.setActiveSheet(active + 1);
            }
        }
    }

    private void saveNamedRanges() {
        if (this.namedRanges.size() > 0) {
            CTDefinedNames names = CTDefinedNames.Factory.newInstance();
            CTDefinedName[] nr = new CTDefinedName[this.namedRanges.size()];
            int i = 0;
            for (XSSFName name : this.namedRanges) {
                nr[i] = name.getCTName();
                ++i;
            }
            names.setDefinedNameArray(nr);
            if (this.workbook.isSetDefinedNames()) {
                this.workbook.unsetDefinedNames();
            }
            this.workbook.setDefinedNames(names);
            this.reprocessNamedRanges();
        } else if (this.workbook.isSetDefinedNames()) {
            this.workbook.unsetDefinedNames();
        }
    }

    private void reprocessNamedRanges() {
        this.namedRangesByName = new ArrayListValuedHashMap<String, XSSFName>();
        this.namedRanges = new ArrayList<XSSFName>();
        if (this.workbook.isSetDefinedNames()) {
            for (CTDefinedName ctName : this.workbook.getDefinedNames().getDefinedNameArray()) {
                this.createAndStoreName(ctName);
            }
        }
    }

    private void saveCalculationChain() {
        int count;
        if (this.calcChain != null && (count = this.calcChain.getCTCalcChain().sizeOfCArray()) == 0) {
            this.removeRelation(this.calcChain);
            this.calcChain = null;
        }
    }

    @Override
    protected void commit() throws IOException {
        this.saveNamedRanges();
        this.saveCalculationChain();
        XmlOptions xmlOptions = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        xmlOptions.setSaveSyntheticDocumentElement(new QName(CTWorkbook.type.getName().getNamespaceURI(), "workbook"));
        PackagePart part = this.getPackagePart();
        try (OutputStream out = part.getOutputStream();){
            this.workbook.save(out, xmlOptions);
        }
    }

    @Internal
    public SharedStringsTable getSharedStringSource() {
        return this.sharedStringSource;
    }

    public StylesTable getStylesSource() {
        return this.stylesSource;
    }

    public ThemesTable getTheme() {
        if (this.stylesSource == null) {
            return null;
        }
        return this.stylesSource.getTheme();
    }

    @Override
    public XSSFCreationHelper getCreationHelper() {
        if (this._creationHelper == null) {
            this._creationHelper = new XSSFCreationHelper(this);
        }
        return this._creationHelper;
    }

    private boolean containsSheet(String name, int excludeSheetIdx) {
        CTSheet[] ctSheetArray = this.workbook.getSheets().getSheetArray();
        if (name.length() > 31) {
            name = name.substring(0, 31);
        }
        for (int i = 0; i < ctSheetArray.length; ++i) {
            String ctName = ctSheetArray[i].getName();
            if (ctName.length() > 31) {
                ctName = ctName.substring(0, 31);
            }
            if (excludeSheetIdx == i || !name.equalsIgnoreCase(ctName)) continue;
            return true;
        }
        return false;
    }

    @Override
    @Internal
    public boolean isDate1904() {
        CTWorkbookPr workbookPr = this.workbook.getWorkbookPr();
        return workbookPr != null && workbookPr.getDate1904();
    }

    @Override
    public List<PackagePart> getAllEmbeddedParts() throws OpenXML4JException {
        LinkedList<PackagePart> embedds = new LinkedList<PackagePart>();
        for (XSSFSheet sheet : this.sheets) {
            for (PackageRelationship rel : sheet.getPackagePart().getRelationshipsByType(XSSFRelation.OLEEMBEDDINGS.getRelation())) {
                embedds.add(sheet.getPackagePart().getRelatedPart(rel));
            }
            for (PackageRelationship rel : sheet.getPackagePart().getRelationshipsByType(XSSFRelation.PACKEMBEDDINGS.getRelation())) {
                embedds.add(sheet.getPackagePart().getRelatedPart(rel));
            }
        }
        return embedds;
    }

    @Override
    @NotImplemented
    public boolean isHidden() {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    @NotImplemented
    public void setHidden(boolean hiddenFlag) {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public boolean isSheetHidden(int sheetIx) {
        this.validateSheetIndex(sheetIx);
        CTSheet ctSheet = this.sheets.get((int)sheetIx).sheet;
        return ctSheet.getState() == STSheetState.HIDDEN;
    }

    @Override
    public boolean isSheetVeryHidden(int sheetIx) {
        this.validateSheetIndex(sheetIx);
        CTSheet ctSheet = this.sheets.get((int)sheetIx).sheet;
        return ctSheet.getState() == STSheetState.VERY_HIDDEN;
    }

    @Override
    public SheetVisibility getSheetVisibility(int sheetIx) {
        this.validateSheetIndex(sheetIx);
        CTSheet ctSheet = this.sheets.get((int)sheetIx).sheet;
        STSheetState.Enum state = ctSheet.getState();
        if (state == STSheetState.VISIBLE) {
            return SheetVisibility.VISIBLE;
        }
        if (state == STSheetState.HIDDEN) {
            return SheetVisibility.HIDDEN;
        }
        if (state == STSheetState.VERY_HIDDEN) {
            return SheetVisibility.VERY_HIDDEN;
        }
        throw new IllegalArgumentException("This should never happen");
    }

    @Override
    public void setSheetHidden(int sheetIx, boolean hidden) {
        this.setSheetVisibility(sheetIx, hidden ? SheetVisibility.HIDDEN : SheetVisibility.VISIBLE);
    }

    @Override
    public void setSheetVisibility(int sheetIx, SheetVisibility visibility) {
        this.validateSheetIndex(sheetIx);
        CTSheet ctSheet = this.sheets.get((int)sheetIx).sheet;
        switch (visibility) {
            case VISIBLE: {
                ctSheet.setState(STSheetState.VISIBLE);
                break;
            }
            case HIDDEN: {
                ctSheet.setState(STSheetState.HIDDEN);
                break;
            }
            case VERY_HIDDEN: {
                ctSheet.setState(STSheetState.VERY_HIDDEN);
                break;
            }
            default: {
                throw new IllegalArgumentException("This should never happen");
            }
        }
    }

    protected void onDeleteFormula(XSSFCell cell) {
        if (this.calcChain != null) {
            int sheetId = (int)cell.getSheet().sheet.getSheetId();
            this.calcChain.removeItem(sheetId, cell.getReference());
        }
    }

    @Internal
    public CalculationChain getCalculationChain() {
        return this.calcChain;
    }

    @Internal
    public List<ExternalLinksTable> getExternalLinksTable() {
        return this.externalLinks;
    }

    public Collection<XSSFMap> getCustomXMLMappings() {
        return this.mapInfo == null ? new ArrayList() : this.mapInfo.getAllXSSFMaps();
    }

    @Internal
    public MapInfo getMapInfo() {
        return this.mapInfo;
    }

    @Override
    @NotImplemented
    public int linkExternalWorkbook(String name, Workbook workbook) {
        throw new RuntimeException("Not Implemented - see bug #57184");
    }

    public boolean isStructureLocked() {
        return this.workbookProtectionPresent() && this.workbook.getWorkbookProtection().getLockStructure();
    }

    public boolean isWindowsLocked() {
        return this.workbookProtectionPresent() && this.workbook.getWorkbookProtection().getLockWindows();
    }

    public boolean isRevisionLocked() {
        return this.workbookProtectionPresent() && this.workbook.getWorkbookProtection().getLockRevision();
    }

    public void lockStructure() {
        this.safeGetWorkbookProtection().setLockStructure(true);
    }

    public void unLockStructure() {
        this.safeGetWorkbookProtection().setLockStructure(false);
    }

    public void lockWindows() {
        this.safeGetWorkbookProtection().setLockWindows(true);
    }

    public void unLockWindows() {
        this.safeGetWorkbookProtection().setLockWindows(false);
    }

    public void lockRevision() {
        this.safeGetWorkbookProtection().setLockRevision(true);
    }

    public void unLockRevision() {
        this.safeGetWorkbookProtection().setLockRevision(false);
    }

    public void setWorkbookPassword(String password, HashAlgorithm hashAlgo) {
        if (password == null && !this.workbookProtectionPresent()) {
            return;
        }
        XSSFPasswordHelper.setPassword(this.safeGetWorkbookProtection(), password, hashAlgo, "workbook");
    }

    public boolean validateWorkbookPassword(String password) {
        if (!this.workbookProtectionPresent()) {
            return password == null;
        }
        return XSSFPasswordHelper.validatePassword(this.safeGetWorkbookProtection(), password, "workbook");
    }

    public void setRevisionsPassword(String password, HashAlgorithm hashAlgo) {
        if (password == null && !this.workbookProtectionPresent()) {
            return;
        }
        XSSFPasswordHelper.setPassword(this.safeGetWorkbookProtection(), password, hashAlgo, "revisions");
    }

    public boolean validateRevisionsPassword(String password) {
        if (!this.workbookProtectionPresent()) {
            return password == null;
        }
        return XSSFPasswordHelper.validatePassword(this.safeGetWorkbookProtection(), password, "revisions");
    }

    public void unLock() {
        if (this.workbookProtectionPresent()) {
            this.workbook.unsetWorkbookProtection();
        }
    }

    private boolean workbookProtectionPresent() {
        return this.workbook.isSetWorkbookProtection();
    }

    private CTWorkbookProtection safeGetWorkbookProtection() {
        if (!this.workbookProtectionPresent()) {
            return this.workbook.addNewWorkbookProtection();
        }
        return this.workbook.getWorkbookProtection();
    }

    UDFFinder getUDFFinder() {
        return this._udfFinder;
    }

    @Override
    public void addToolPack(UDFFinder toopack) {
        this._udfFinder.add(toopack);
    }

    @Override
    public void setForceFormulaRecalculation(boolean value) {
        CTWorkbook ctWorkbook = this.getCTWorkbook();
        CTCalcPr calcPr = ctWorkbook.isSetCalcPr() ? ctWorkbook.getCalcPr() : ctWorkbook.addNewCalcPr();
        calcPr.setFullCalcOnLoad(value);
        if (value && calcPr.getCalcMode() == STCalcMode.MANUAL) {
            calcPr.setCalcMode(STCalcMode.AUTO);
        }
    }

    @Override
    public boolean getForceFormulaRecalculation() {
        CTWorkbook ctWorkbook = this.getCTWorkbook();
        CTCalcPr calcPr = ctWorkbook.getCalcPr();
        return calcPr != null && calcPr.isSetFullCalcOnLoad() && calcPr.getFullCalcOnLoad();
    }

    protected CTPivotCache addPivotCache(String rId) {
        CTWorkbook ctWorkbook = this.getCTWorkbook();
        CTPivotCaches caches = ctWorkbook.isSetPivotCaches() ? ctWorkbook.getPivotCaches() : ctWorkbook.addNewPivotCaches();
        CTPivotCache cache = caches.addNewPivotCache();
        int tableId = this.getPivotTables().size() + 1;
        cache.setCacheId(tableId);
        cache.setId(rId);
        if (this.pivotCaches == null) {
            this.pivotCaches = new ArrayList<CTPivotCache>();
        }
        this.pivotCaches.add(cache);
        return cache;
    }

    public List<XSSFPivotTable> getPivotTables() {
        return this.pivotTables;
    }

    protected void setPivotTables(List<XSSFPivotTable> pivotTables) {
        this.pivotTables = pivotTables;
    }

    public XSSFWorkbookType getWorkbookType() {
        return this.isMacroEnabled() ? XSSFWorkbookType.XLSM : XSSFWorkbookType.XLSX;
    }

    public void setWorkbookType(XSSFWorkbookType type) {
        try {
            this.getPackagePart().setContentType(type.getContentType());
        } catch (InvalidFormatException e) {
            throw new POIXMLException(e);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setVBAProject(InputStream vbaProjectStream) throws IOException {
        OutputStream outputStream;
        PackagePartName ppName;
        if (!this.isMacroEnabled()) {
            this.setWorkbookType(XSSFWorkbookType.XLSM);
        }
        try {
            ppName = PackagingURIHelper.createPartName(XSSFRelation.VBA_MACROS.getDefaultFileName());
        } catch (InvalidFormatException e) {
            throw new POIXMLException(e);
        }
        OPCPackage opc = this.getPackage();
        if (!opc.containPart(ppName)) {
            POIXMLDocumentPart relationship = this.createRelationship(XSSFRelation.VBA_MACROS, this.xssfFactory);
            outputStream = relationship.getPackagePart().getOutputStream();
        } else {
            PackagePart part = opc.getPart(ppName);
            outputStream = part.getOutputStream();
        }
        try {
            IOUtils.copy(vbaProjectStream, outputStream);
        } finally {
            IOUtils.closeQuietly(outputStream);
        }
    }

    public void setVBAProject(XSSFWorkbook macroWorkbook) throws IOException, InvalidFormatException {
        if (!macroWorkbook.isMacroEnabled()) {
            return;
        }
        InputStream vbaProjectStream = XSSFRelation.VBA_MACROS.getContents(macroWorkbook.getCorePart());
        if (vbaProjectStream != null) {
            this.setVBAProject(vbaProjectStream);
        }
    }

    @Override
    public SpreadsheetVersion getSpreadsheetVersion() {
        return SpreadsheetVersion.EXCEL2007;
    }

    public XSSFTable getTable(String name) {
        if (name != null && this.sheets != null) {
            for (XSSFSheet sheet : this.sheets) {
                for (XSSFTable tbl : sheet.getTables()) {
                    if (!name.equalsIgnoreCase(tbl.getName())) continue;
                    return tbl;
                }
            }
        }
        return null;
    }

    @Override
    public int addOlePackage(byte[] oleData, String label, String fileName, String command) throws IOException {
        PackagePartName pnOLE;
        OPCPackage opc = this.getPackage();
        int oleId = 0;
        do {
            try {
                pnOLE = PackagingURIHelper.createPartName("/xl/embeddings/oleObject" + ++oleId + ".bin");
            } catch (InvalidFormatException e) {
                throw new IOException("ole object name not recognized", e);
            }
        } while (opc.containPart(pnOLE));
        PackagePart pp = opc.createPart(pnOLE, "application/vnd.openxmlformats-officedocument.oleObject");
        Ole10Native ole10 = new Ole10Native(label, fileName, command, oleData);
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream(oleData.length + 500);){
            ole10.writeOut(bos);
            try (POIFSFileSystem poifs = new POIFSFileSystem();){
                DirectoryNode root = poifs.getRoot();
                root.createDocument("\u0001Ole10Native", new ByteArrayInputStream(bos.toByteArray()));
                root.setStorageClsid(ClassIDPredefined.OLE_V1_PACKAGE.getClassID());
                try (OutputStream os = pp.getOutputStream();){
                    poifs.writeFilesystem(os);
                }
            }
        }
        return oleId;
    }

    public void setCellFormulaValidation(boolean value) {
        this.cellFormulaValidation = value;
    }

    public boolean getCellFormulaValidation() {
        return this.cellFormulaValidation;
    }

    private final class SheetIterator<T extends Sheet>
    implements Iterator<T> {
        private final Iterator<T> it;

        public SheetIterator() {
            this.it = XSSFWorkbook.this.sheets.iterator();
        }

        @Override
        public boolean hasNext() {
            return this.it.hasNext();
        }

        @Override
        public T next() throws NoSuchElementException {
            return (T)((Sheet)this.it.next());
        }

        @Override
        public void remove() throws IllegalStateException {
            throw new UnsupportedOperationException("remove method not supported on XSSFWorkbook.iterator(). Use Sheet.removeSheetAt(int) instead.");
        }
    }
}

