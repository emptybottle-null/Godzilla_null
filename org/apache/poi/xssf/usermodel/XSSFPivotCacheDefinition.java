/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import javax.xml.namespace.QName;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.Internal;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCacheField;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCacheFields;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotCacheDefinition;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheetSource;

public class XSSFPivotCacheDefinition
extends POIXMLDocumentPart {
    private CTPivotCacheDefinition ctPivotCacheDefinition;

    public XSSFPivotCacheDefinition() {
        this.ctPivotCacheDefinition = CTPivotCacheDefinition.Factory.newInstance();
        this.createDefaultValues();
    }

    protected XSSFPivotCacheDefinition(PackagePart part) throws IOException {
        super(part);
        this.readFrom(part.getInputStream());
    }

    public void readFrom(InputStream is) throws IOException {
        try {
            XmlOptions options = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            options.setLoadReplaceDocumentElement(null);
            this.ctPivotCacheDefinition = CTPivotCacheDefinition.Factory.parse(is, options);
        } catch (XmlException e) {
            throw new IOException(e.getLocalizedMessage(), e);
        }
    }

    @Internal
    public CTPivotCacheDefinition getCTPivotCacheDefinition() {
        return this.ctPivotCacheDefinition;
    }

    private void createDefaultValues() {
        this.ctPivotCacheDefinition.setCreatedVersion((short)3);
        this.ctPivotCacheDefinition.setMinRefreshableVersion((short)3);
        this.ctPivotCacheDefinition.setRefreshedVersion((short)3);
        this.ctPivotCacheDefinition.setRefreshedBy("Apache POI");
        this.ctPivotCacheDefinition.setRefreshedDate(new Date().getTime());
        this.ctPivotCacheDefinition.setRefreshOnLoad(true);
    }

    @Override
    protected void commit() throws IOException {
        PackagePart part = this.getPackagePart();
        OutputStream out = part.getOutputStream();
        XmlOptions xmlOptions = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        xmlOptions.setSaveSyntheticDocumentElement(new QName(CTPivotCacheDefinition.type.getName().getNamespaceURI(), "pivotCacheDefinition"));
        this.ctPivotCacheDefinition.save(out, xmlOptions);
        out.close();
    }

    public AreaReference getPivotArea(Workbook wb) throws IllegalArgumentException {
        CTWorksheetSource wsSource = this.ctPivotCacheDefinition.getCacheSource().getWorksheetSource();
        String ref = wsSource.getRef();
        String name = wsSource.getName();
        if (ref == null && name == null) {
            throw new IllegalArgumentException("Pivot cache must reference an area, named range, or table.");
        }
        if (ref != null) {
            return new AreaReference(ref, SpreadsheetVersion.EXCEL2007);
        }
        assert (name != null);
        Name range = wb.getName(name);
        if (range != null) {
            return new AreaReference(range.getRefersToFormula(), SpreadsheetVersion.EXCEL2007);
        }
        XSSFSheet sheet = (XSSFSheet)wb.getSheet(wsSource.getSheet());
        for (XSSFTable table : sheet.getTables()) {
            if (!name.equals(table.getName())) continue;
            return new AreaReference(table.getStartCellReference(), table.getEndCellReference(), SpreadsheetVersion.EXCEL2007);
        }
        throw new IllegalArgumentException("Name '" + name + "' was not found.");
    }

    protected void createCacheFields(Sheet sheet) {
        AreaReference ar = this.getPivotArea(sheet.getWorkbook());
        CellReference firstCell = ar.getFirstCell();
        CellReference lastCell = ar.getLastCell();
        int columnStart = firstCell.getCol();
        short columnEnd = lastCell.getCol();
        Row row = sheet.getRow(firstCell.getRow());
        CTCacheFields cFields = this.ctPivotCacheDefinition.getCacheFields() != null ? this.ctPivotCacheDefinition.getCacheFields() : this.ctPivotCacheDefinition.addNewCacheFields();
        for (int i = columnStart; i <= columnEnd; ++i) {
            CTCacheField cf = cFields.addNewCacheField();
            if (i == columnEnd) {
                cFields.setCount(cFields.sizeOfCacheFieldArray());
            }
            cf.setNumFmtId(0L);
            cf.setName(row.getCell(i).getStringCellValue());
            cf.addNewSharedItems();
        }
    }
}

