/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.poi.ss.util.CellRangeAddress;

public final class CellRangeUtil {
    public static final int NO_INTERSECTION = 1;
    public static final int OVERLAP = 2;
    public static final int INSIDE = 3;
    public static final int ENCLOSES = 4;

    private CellRangeUtil() {
    }

    public static int intersect(CellRangeAddress crA, CellRangeAddress crB) {
        int firstRow = crB.getFirstRow();
        int lastRow = crB.getLastRow();
        int firstCol = crB.getFirstColumn();
        int lastCol = crB.getLastColumn();
        if (CellRangeUtil.gt(crA.getFirstRow(), lastRow) || CellRangeUtil.lt(crA.getLastRow(), firstRow) || CellRangeUtil.gt(crA.getFirstColumn(), lastCol) || CellRangeUtil.lt(crA.getLastColumn(), firstCol)) {
            return 1;
        }
        if (CellRangeUtil.contains(crA, crB)) {
            return 3;
        }
        if (CellRangeUtil.contains(crB, crA)) {
            return 4;
        }
        return 2;
    }

    public static CellRangeAddress[] mergeCellRanges(CellRangeAddress[] cellRanges) {
        if (cellRanges.length < 1) {
            return new CellRangeAddress[0];
        }
        List<CellRangeAddress> list = CellRangeUtil.toList(cellRanges);
        List<CellRangeAddress> temp = CellRangeUtil.mergeCellRanges(list);
        return CellRangeUtil.toArray(temp);
    }

    private static List<CellRangeAddress> mergeCellRanges(List<CellRangeAddress> cellRangeList) {
        while (cellRangeList.size() > 1) {
            boolean somethingGotMerged = false;
            for (int i = 0; i < cellRangeList.size(); ++i) {
                CellRangeAddress range1 = cellRangeList.get(i);
                for (int j = i + 1; j < cellRangeList.size(); ++j) {
                    CellRangeAddress range2 = cellRangeList.get(j);
                    CellRangeAddress[] mergeResult = CellRangeUtil.mergeRanges(range1, range2);
                    if (mergeResult == null) continue;
                    somethingGotMerged = true;
                    range1 = mergeResult[0];
                    cellRangeList.set(i, mergeResult[0]);
                    cellRangeList.remove(j--);
                    for (int k = 1; k < mergeResult.length; ++k) {
                        cellRangeList.add(++j, mergeResult[k]);
                    }
                }
            }
            if (somethingGotMerged) continue;
            break;
        }
        return cellRangeList;
    }

    private static CellRangeAddress[] mergeRanges(CellRangeAddress range1, CellRangeAddress range2) {
        int x = CellRangeUtil.intersect(range1, range2);
        switch (x) {
            case 1: {
                if (CellRangeUtil.hasExactSharedBorder(range1, range2)) {
                    return new CellRangeAddress[]{CellRangeUtil.createEnclosingCellRange(range1, range2)};
                }
                return null;
            }
            case 2: {
                return null;
            }
            case 3: {
                return new CellRangeAddress[]{range1};
            }
            case 4: {
                return new CellRangeAddress[]{range2};
            }
        }
        throw new RuntimeException("unexpected intersection result (" + x + ")");
    }

    private static CellRangeAddress[] toArray(List<CellRangeAddress> temp) {
        CellRangeAddress[] result = new CellRangeAddress[temp.size()];
        temp.toArray(result);
        return result;
    }

    private static List<CellRangeAddress> toList(CellRangeAddress[] temp) {
        ArrayList<CellRangeAddress> result = new ArrayList<CellRangeAddress>(temp.length);
        Collections.addAll(result, temp);
        return result;
    }

    public static boolean contains(CellRangeAddress crA, CellRangeAddress crB) {
        return CellRangeUtil.le(crA.getFirstRow(), crB.getFirstRow()) && CellRangeUtil.ge(crA.getLastRow(), crB.getLastRow()) && CellRangeUtil.le(crA.getFirstColumn(), crB.getFirstColumn()) && CellRangeUtil.ge(crA.getLastColumn(), crB.getLastColumn());
    }

    public static boolean hasExactSharedBorder(CellRangeAddress crA, CellRangeAddress crB) {
        int oFirstRow = crB.getFirstRow();
        int oLastRow = crB.getLastRow();
        int oFirstCol = crB.getFirstColumn();
        int oLastCol = crB.getLastColumn();
        if (crA.getFirstRow() > 0 && crA.getFirstRow() - 1 == oLastRow || oFirstRow > 0 && oFirstRow - 1 == crA.getLastRow()) {
            return crA.getFirstColumn() == oFirstCol && crA.getLastColumn() == oLastCol;
        }
        if (crA.getFirstColumn() > 0 && crA.getFirstColumn() - 1 == oLastCol || oFirstCol > 0 && crA.getLastColumn() == oFirstCol - 1) {
            return crA.getFirstRow() == oFirstRow && crA.getLastRow() == oLastRow;
        }
        return false;
    }

    public static CellRangeAddress createEnclosingCellRange(CellRangeAddress crA, CellRangeAddress crB) {
        if (crB == null) {
            return crA.copy();
        }
        int minRow = CellRangeUtil.lt(crB.getFirstRow(), crA.getFirstRow()) ? crB.getFirstRow() : crA.getFirstRow();
        int maxRow = CellRangeUtil.gt(crB.getLastRow(), crA.getLastRow()) ? crB.getLastRow() : crA.getLastRow();
        int minCol = CellRangeUtil.lt(crB.getFirstColumn(), crA.getFirstColumn()) ? crB.getFirstColumn() : crA.getFirstColumn();
        int maxCol = CellRangeUtil.gt(crB.getLastColumn(), crA.getLastColumn()) ? crB.getLastColumn() : crA.getLastColumn();
        return new CellRangeAddress(minRow, maxRow, minCol, maxCol);
    }

    private static boolean lt(int a, int b) {
        return a == -1 ? false : (b == -1 ? true : a < b);
    }

    private static boolean le(int a, int b) {
        return a == b || CellRangeUtil.lt(a, b);
    }

    private static boolean gt(int a, int b) {
        return CellRangeUtil.lt(b, a);
    }

    private static boolean ge(int a, int b) {
        return !CellRangeUtil.lt(a, b);
    }
}

