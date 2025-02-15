/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.binary;

import java.util.HashMap;
import java.util.Map;
import org.apache.poi.util.Internal;

@Internal
public enum XSSFBRecordType {
    BrtCellBlank(1),
    BrtCellRk(2),
    BrtCellError(3),
    BrtCellBool(4),
    BrtCellReal(5),
    BrtCellSt(6),
    BrtCellIsst(7),
    BrtFmlaString(8),
    BrtFmlaNum(9),
    BrtFmlaBool(10),
    BrtFmlaError(11),
    BrtRowHdr(0),
    BrtCellRString(62),
    BrtBeginSheet(129),
    BrtWsProp(147),
    BrtWsDim(148),
    BrtColInfo(60),
    BrtBeginSheetData(145),
    BrtEndSheetData(146),
    BrtHLink(494),
    BrtBeginHeaderFooter(479),
    BrtBeginCommentAuthors(630),
    BrtEndCommentAuthors(631),
    BrtCommentAuthor(632),
    BrtBeginComment(635),
    BrtCommentText(637),
    BrtEndComment(636),
    BrtXf(47),
    BrtFmt(44),
    BrtBeginFmts(615),
    BrtEndFmts(616),
    BrtBeginCellXFs(617),
    BrtEndCellXFs(618),
    BrtBeginCellStyleXFS(626),
    BrtEndCellStyleXFS(627),
    BrtSstItem(19),
    BrtBeginSst(159),
    BrtEndSst(160),
    BrtBundleSh(156),
    BrtAbsPath15(2071),
    Unimplemented(-1);

    private static final Map<Integer, XSSFBRecordType> TYPE_MAP;
    private final int id;

    private XSSFBRecordType(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static XSSFBRecordType lookup(int id) {
        XSSFBRecordType type = TYPE_MAP.get(id);
        if (type == null) {
            return Unimplemented;
        }
        return type;
    }

    static {
        TYPE_MAP = new HashMap<Integer, XSSFBRecordType>();
        for (XSSFBRecordType type : XSSFBRecordType.values()) {
            TYPE_MAP.put(type.getId(), type);
        }
    }
}

