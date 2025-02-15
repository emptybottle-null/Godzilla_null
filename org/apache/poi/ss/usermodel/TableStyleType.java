/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DifferentialStyleProvider;
import org.apache.poi.ss.usermodel.Table;
import org.apache.poi.ss.usermodel.TableStyleInfo;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressBase;
import org.apache.poi.ss.util.CellReference;

public enum TableStyleType {
    wholeTable{

        @Override
        public CellRangeAddressBase getRange(Table table, CellReference cell) {
            return new CellRangeAddress(table.getStartRowIndex(), table.getEndRowIndex(), table.getStartColIndex(), table.getEndColIndex());
        }
    }
    ,
    pageFieldLabels,
    pageFieldValues,
    firstColumnStripe{

        @Override
        public CellRangeAddressBase getRange(Table table, CellReference cell) {
            TableStyleInfo info = table.getStyle();
            if (!info.isShowColumnStripes()) {
                return null;
            }
            DifferentialStyleProvider c1Style = info.getStyle().getStyle(firstColumnStripe);
            DifferentialStyleProvider c2Style = info.getStyle().getStyle(secondColumnStripe);
            int c1Stripe = c1Style == null ? 1 : Math.max(1, c1Style.getStripeSize());
            int c2Stripe = c2Style == null ? 1 : Math.max(1, c2Style.getStripeSize());
            int firstStart = table.getStartColIndex();
            int secondStart = firstStart + c1Stripe;
            short c = cell.getCol();
            while (firstStart <= c) {
                if (c <= secondStart - 1) {
                    return new CellRangeAddress(table.getStartRowIndex(), table.getEndRowIndex(), firstStart, secondStart - 1);
                }
                firstStart = secondStart + c2Stripe;
                secondStart = firstStart + c1Stripe;
            }
            return null;
        }
    }
    ,
    secondColumnStripe{

        @Override
        public CellRangeAddressBase getRange(Table table, CellReference cell) {
            TableStyleInfo info = table.getStyle();
            if (!info.isShowColumnStripes()) {
                return null;
            }
            DifferentialStyleProvider c1Style = info.getStyle().getStyle(firstColumnStripe);
            DifferentialStyleProvider c2Style = info.getStyle().getStyle(secondColumnStripe);
            int c1Stripe = c1Style == null ? 1 : Math.max(1, c1Style.getStripeSize());
            int c2Stripe = c2Style == null ? 1 : Math.max(1, c2Style.getStripeSize());
            int firstStart = table.getStartColIndex();
            int secondStart = firstStart + c1Stripe;
            short c = cell.getCol();
            while (firstStart <= c) {
                if (c >= secondStart && c <= secondStart + c2Stripe - 1) {
                    return new CellRangeAddress(table.getStartRowIndex(), table.getEndRowIndex(), secondStart, secondStart + c2Stripe - 1);
                }
                firstStart = secondStart + c2Stripe;
                secondStart = firstStart + c1Stripe;
            }
            return null;
        }
    }
    ,
    firstRowStripe{

        @Override
        public CellRangeAddressBase getRange(Table table, CellReference cell) {
            TableStyleInfo info = table.getStyle();
            if (!info.isShowRowStripes()) {
                return null;
            }
            DifferentialStyleProvider c1Style = info.getStyle().getStyle(firstRowStripe);
            DifferentialStyleProvider c2Style = info.getStyle().getStyle(secondRowStripe);
            int c1Stripe = c1Style == null ? 1 : Math.max(1, c1Style.getStripeSize());
            int c2Stripe = c2Style == null ? 1 : Math.max(1, c2Style.getStripeSize());
            int firstStart = table.getStartRowIndex() + table.getHeaderRowCount();
            int secondStart = firstStart + c1Stripe;
            int c = cell.getRow();
            while (firstStart <= c) {
                if (c <= secondStart - 1) {
                    return new CellRangeAddress(firstStart, secondStart - 1, table.getStartColIndex(), table.getEndColIndex());
                }
                firstStart = secondStart + c2Stripe;
                secondStart = firstStart + c1Stripe;
            }
            return null;
        }
    }
    ,
    secondRowStripe{

        @Override
        public CellRangeAddressBase getRange(Table table, CellReference cell) {
            TableStyleInfo info = table.getStyle();
            if (!info.isShowRowStripes()) {
                return null;
            }
            DifferentialStyleProvider c1Style = info.getStyle().getStyle(firstRowStripe);
            DifferentialStyleProvider c2Style = info.getStyle().getStyle(secondRowStripe);
            int c1Stripe = c1Style == null ? 1 : Math.max(1, c1Style.getStripeSize());
            int c2Stripe = c2Style == null ? 1 : Math.max(1, c2Style.getStripeSize());
            int firstStart = table.getStartRowIndex() + table.getHeaderRowCount();
            int secondStart = firstStart + c1Stripe;
            int c = cell.getRow();
            while (firstStart <= c) {
                if (c >= secondStart && c <= secondStart + c2Stripe - 1) {
                    return new CellRangeAddress(secondStart, secondStart + c2Stripe - 1, table.getStartColIndex(), table.getEndColIndex());
                }
                firstStart = secondStart + c2Stripe;
                secondStart = firstStart + c1Stripe;
            }
            return null;
        }
    }
    ,
    lastColumn{

        @Override
        public CellRangeAddressBase getRange(Table table, CellReference cell) {
            if (!table.getStyle().isShowLastColumn()) {
                return null;
            }
            return new CellRangeAddress(table.getStartRowIndex(), table.getEndRowIndex(), table.getEndColIndex(), table.getEndColIndex());
        }
    }
    ,
    firstColumn{

        @Override
        public CellRangeAddressBase getRange(Table table, CellReference cell) {
            if (!table.getStyle().isShowFirstColumn()) {
                return null;
            }
            return new CellRangeAddress(table.getStartRowIndex(), table.getEndRowIndex(), table.getStartColIndex(), table.getStartColIndex());
        }
    }
    ,
    headerRow{

        @Override
        public CellRangeAddressBase getRange(Table table, CellReference cell) {
            if (table.getHeaderRowCount() < 1) {
                return null;
            }
            return new CellRangeAddress(table.getStartRowIndex(), table.getStartRowIndex() + table.getHeaderRowCount() - 1, table.getStartColIndex(), table.getEndColIndex());
        }
    }
    ,
    totalRow{

        @Override
        public CellRangeAddressBase getRange(Table table, CellReference cell) {
            if (table.getTotalsRowCount() < 1) {
                return null;
            }
            return new CellRangeAddress(table.getEndRowIndex() - table.getTotalsRowCount() + 1, table.getEndRowIndex(), table.getStartColIndex(), table.getEndColIndex());
        }
    }
    ,
    firstHeaderCell{

        @Override
        public CellRangeAddressBase getRange(Table table, CellReference cell) {
            if (table.getHeaderRowCount() < 1) {
                return null;
            }
            return new CellRangeAddress(table.getStartRowIndex(), table.getStartRowIndex(), table.getStartColIndex(), table.getStartColIndex());
        }
    }
    ,
    lastHeaderCell{

        @Override
        public CellRangeAddressBase getRange(Table table, CellReference cell) {
            if (table.getHeaderRowCount() < 1) {
                return null;
            }
            return new CellRangeAddress(table.getStartRowIndex(), table.getStartRowIndex(), table.getEndColIndex(), table.getEndColIndex());
        }
    }
    ,
    firstTotalCell{

        @Override
        public CellRangeAddressBase getRange(Table table, CellReference cell) {
            if (table.getTotalsRowCount() < 1) {
                return null;
            }
            return new CellRangeAddress(table.getEndRowIndex() - table.getTotalsRowCount() + 1, table.getEndRowIndex(), table.getStartColIndex(), table.getStartColIndex());
        }
    }
    ,
    lastTotalCell{

        @Override
        public CellRangeAddressBase getRange(Table table, CellReference cell) {
            if (table.getTotalsRowCount() < 1) {
                return null;
            }
            return new CellRangeAddress(table.getEndRowIndex() - table.getTotalsRowCount() + 1, table.getEndRowIndex(), table.getEndColIndex(), table.getEndColIndex());
        }
    }
    ,
    firstSubtotalColumn,
    secondSubtotalColumn,
    thirdSubtotalColumn,
    blankRow,
    firstSubtotalRow,
    secondSubtotalRow,
    thirdSubtotalRow,
    firstColumnSubheading,
    secondColumnSubheading,
    thirdColumnSubheading,
    firstRowSubheading,
    secondRowSubheading,
    thirdRowSubheading;


    public CellRangeAddressBase appliesTo(Table table, Cell cell) {
        if (cell == null) {
            return null;
        }
        return this.appliesTo(table, new CellReference(cell.getSheet().getSheetName(), cell.getRowIndex(), cell.getColumnIndex(), true, true));
    }

    public CellRangeAddressBase appliesTo(Table table, CellReference cell) {
        if (table == null || cell == null) {
            return null;
        }
        if (!cell.getSheetName().equals(table.getSheetName())) {
            return null;
        }
        if (!table.contains(cell)) {
            return null;
        }
        CellRangeAddressBase range = this.getRange(table, cell);
        if (range != null && range.isInRange(cell.getRow(), cell.getCol())) {
            return range;
        }
        return null;
    }

    public final CellRangeAddressBase getRange(Table table, Cell cell) {
        if (cell == null) {
            return null;
        }
        return this.getRange(table, new CellReference(cell.getSheet().getSheetName(), cell.getRowIndex(), cell.getColumnIndex(), true, true));
    }

    public CellRangeAddressBase getRange(Table table, CellReference cell) {
        return null;
    }
}

