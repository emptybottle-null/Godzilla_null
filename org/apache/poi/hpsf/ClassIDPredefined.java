/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hpsf;

import java.util.HashMap;
import java.util.Map;
import org.apache.poi.hpsf.ClassID;

public enum ClassIDPredefined {
    OLE_V1_PACKAGE("{0003000C-0000-0000-C000-000000000046}", ".bin", null),
    EXCEL_V3("{00030000-0000-0000-C000-000000000046}", ".xls", "application/vnd.ms-excel"),
    EXCEL_V3_CHART("{00030001-0000-0000-C000-000000000046}", null, null),
    EXCEL_V3_MACRO("{00030002-0000-0000-C000-000000000046}", null, null),
    EXCEL_V7("{00020810-0000-0000-C000-000000000046}", ".xls", "application/vnd.ms-excel"),
    EXCEL_V7_WORKBOOK("{00020841-0000-0000-C000-000000000046}", null, null),
    EXCEL_V7_CHART("{00020811-0000-0000-C000-000000000046}", null, null),
    EXCEL_V8("{00020820-0000-0000-C000-000000000046}", ".xls", "application/vnd.ms-excel"),
    EXCEL_V8_CHART("{00020821-0000-0000-C000-000000000046}", null, null),
    EXCEL_V11("{00020812-0000-0000-C000-000000000046}", ".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
    EXCEL_V12("{00020830-0000-0000-C000-000000000046}", ".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
    EXCEL_V12_MACRO("{00020832-0000-0000-C000-000000000046}", ".xlsm", "application/vnd.ms-excel.sheet.macroEnabled.12"),
    EXCEL_V12_XLSB("{00020833-0000-0000-C000-000000000046}", ".xlsb", "application/vnd.ms-excel.sheet.binary.macroEnabled.12"),
    EXCEL_V14("{00024500-0000-0000-C000-000000000046}", ".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
    EXCEL_V14_WORKBOOK("{000208D5-0000-0000-C000-000000000046}", null, null),
    EXCEL_V14_CHART("{00024505-0014-0000-C000-000000000046}", null, null),
    EXCEL_V14_ODS("{EABCECDB-CC1C-4A6F-B4E3-7F888A5ADFC8}", ".ods", "application/vnd.oasis.opendocument.spreadsheet"),
    WORD_V7("{00020900-0000-0000-C000-000000000046}", ".doc", "application/msword"),
    WORD_V8("{00020906-0000-0000-C000-000000000046}", ".doc", "application/msword"),
    WORD_V12("{F4754C9B-64F5-4B40-8AF4-679732AC0607}", ".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
    WORD_V12_MACRO("{18A06B6B-2F3F-4E2B-A611-52BE631B2D22}", ".docm", "application/vnd.ms-word.document.macroEnabled.12"),
    POWERPOINT_V7("{EA7BAE70-FB3B-11CD-A903-00AA00510EA3}", ".ppt", "application/vnd.ms-powerpoint"),
    POWERPOINT_V7_SLIDE("{EA7BAE71-FB3B-11CD-A903-00AA00510EA3}", null, null),
    POWERPOINT_V8("{64818D10-4F9B-11CF-86EA-00AA00B929E8}", ".ppt", "application/vnd.ms-powerpoint"),
    POWERPOINT_V8_TPL("{64818D11-4F9B-11CF-86EA-00AA00B929E8}", ".pot", "application/vnd.ms-powerpoint"),
    POWERPOINT_V12("{CF4F55F4-8F87-4D47-80BB-5808164BB3F8}", ".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation"),
    POWERPOINT_V12_MACRO("{DC020317-E6E2-4A62-B9FA-B3EFE16626F4}", ".pptm", "application/vnd.ms-powerpoint.presentation.macroEnabled.12"),
    PUBLISHER_V12("{0002123D-0000-0000-C000-000000000046}", ".pub", "application/x-mspublisher"),
    VISIO_V10("{00021A14-0000-0000-C000-000000000046}", ".vsd", "application/vnd.visio"),
    EQUATION_V3("{0002CE02-0000-0000-C000-000000000046}", null, null),
    PDF("{B801CA65-A1FC-11D0-85AD-444553540000}", ".pdf", "application/pdf"),
    TXT_ONLY("{5e941d80-bf96-11cd-b579-08002b30bfeb}", ".txt", "text/plain"),
    PAINT("{0003000A-0000-0000-C000-000000000046}", null, null),
    STD_MONIKER("{79EAC9D0-BAF9-11CE-8C82-00AA004BA90B}", null, null),
    URL_MONIKER("{79EAC9E0-BAF9-11CE-8C82-00AA004BA90B}", null, null),
    FILE_MONIKER("{00000303-0000-0000-C000-000000000046}", null, null),
    DOC_SUMMARY("{D5CDD502-2E9C-101B-9397-08002B2CF9AE}", null, null),
    USER_PROPERTIES("{D5CDD505-2E9C-101B-9397-08002B2CF9AE}", null, null),
    SUMMARY_PROPERTIES("{F29F85E0-4FF9-1068-AB91-08002B27B3D9}", null, null);

    private static final Map<String, ClassIDPredefined> LOOKUP;
    private final String externalForm;
    private ClassID classId;
    private final String fileExtension;
    private final String contentType;

    private ClassIDPredefined(String externalForm, String fileExtension, String contentType) {
        this.externalForm = externalForm;
        this.fileExtension = fileExtension;
        this.contentType = contentType;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ClassID getClassID() {
        ClassIDPredefined classIDPredefined = this;
        synchronized (classIDPredefined) {
            if (this.classId == null) {
                this.classId = new ClassID(this.externalForm);
            }
        }
        return this.classId;
    }

    public String getFileExtension() {
        return this.fileExtension;
    }

    public String getContentType() {
        return this.contentType;
    }

    public static ClassIDPredefined lookup(String externalForm) {
        return LOOKUP.get(externalForm);
    }

    public static ClassIDPredefined lookup(ClassID classID) {
        return classID == null ? null : LOOKUP.get(classID.toString());
    }

    public boolean equals(ClassID classID) {
        return this.getClassID().equals(classID);
    }

    static {
        LOOKUP = new HashMap<String, ClassIDPredefined>();
        for (ClassIDPredefined p : ClassIDPredefined.values()) {
            LOOKUP.put(p.externalForm, p);
        }
    }
}

