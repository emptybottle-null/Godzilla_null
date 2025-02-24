/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.dev;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ddf.EscherRecord;
import org.apache.poi.hssf.model.InternalWorkbook;
import org.apache.poi.hssf.record.DrawingGroupRecord;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.util.StringUtil;

public class BiffDrawingToXml {
    private static final String SHEET_NAME_PARAM = "-sheet-name";
    private static final String SHEET_INDEXES_PARAM = "-sheet-indexes";
    private static final String EXCLUDE_WORKBOOK_RECORDS = "-exclude-workbook";

    private static int getAttributeIndex(String attribute, String[] params) {
        for (int i = 0; i < params.length; ++i) {
            String param = params[i];
            if (!attribute.equals(param)) continue;
            return i;
        }
        return -1;
    }

    private static boolean isExcludeWorkbookRecords(String[] params) {
        return -1 != BiffDrawingToXml.getAttributeIndex(EXCLUDE_WORKBOOK_RECORDS, params);
    }

    private static List<Integer> getIndexesByName(String[] params, HSSFWorkbook workbook) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        int pos = BiffDrawingToXml.getAttributeIndex(SHEET_NAME_PARAM, params);
        if (-1 != pos) {
            if (pos >= params.length) {
                throw new IllegalArgumentException("sheet name param value was not specified");
            }
            String sheetName = params[pos + 1];
            int sheetPos = workbook.getSheetIndex(sheetName);
            if (-1 == sheetPos) {
                throw new IllegalArgumentException("specified sheet name has not been found in xls file");
            }
            list.add(sheetPos);
        }
        return list;
    }

    private static List<Integer> getIndexesByIdArray(String[] params) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        int pos = BiffDrawingToXml.getAttributeIndex(SHEET_INDEXES_PARAM, params);
        if (-1 != pos) {
            String[] sheets;
            if (pos >= params.length) {
                throw new IllegalArgumentException("sheet list value was not specified");
            }
            String sheetParam = params[pos + 1];
            for (String sheet : sheets = sheetParam.split(",")) {
                list.add(Integer.parseInt(sheet));
            }
        }
        return list;
    }

    private static List<Integer> getSheetsIndexes(String[] params, HSSFWorkbook workbook) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.addAll(BiffDrawingToXml.getIndexesByIdArray(params));
        list.addAll(BiffDrawingToXml.getIndexesByName(params, workbook));
        if (0 == list.size()) {
            int size = workbook.getNumberOfSheets();
            for (int i = 0; i < size; ++i) {
                list.add(i);
            }
        }
        return list;
    }

    private static String getInputFileName(String[] params) {
        return params[params.length - 1];
    }

    private static String getOutputFileName(String input) {
        if (input.contains("xls")) {
            return input.replace(".xls", ".xml");
        }
        return input + ".xml";
    }

    public static void main(String[] params) throws IOException {
        if (0 == params.length) {
            System.out.println("Usage: BiffDrawingToXml [options] inputWorkbook");
            System.out.println("Options:");
            System.out.println("  -exclude-workbook            exclude workbook-level records");
            System.out.println("  -sheet-indexes   <indexes>   output sheets with specified indexes");
            System.out.println("  -sheet-namek  <names>        output sheets with specified name");
            return;
        }
        String input = BiffDrawingToXml.getInputFileName(params);
        String output = BiffDrawingToXml.getOutputFileName(input);
        try (FileInputStream inp = new FileInputStream(input);
             FileOutputStream outputStream = new FileOutputStream(output);){
            BiffDrawingToXml.writeToFile(outputStream, inp, BiffDrawingToXml.isExcludeWorkbookRecords(params), params);
        }
    }

    public static void writeToFile(OutputStream fos, InputStream xlsWorkbook, boolean excludeWorkbookRecords, String[] params) throws IOException {
        try (HSSFWorkbook workbook = new HSSFWorkbook(xlsWorkbook);){
            InternalWorkbook internalWorkbook = workbook.getInternalWorkbook();
            DrawingGroupRecord r = (DrawingGroupRecord)internalWorkbook.findFirstRecordBySid((short)235);
            StringBuilder builder = new StringBuilder();
            builder.append("<workbook>\n");
            String tab = "\t";
            if (!excludeWorkbookRecords && r != null) {
                r.decode();
                List<EscherRecord> escherRecords = r.getEscherRecords();
                for (EscherRecord record : escherRecords) {
                    builder.append(record.toXml(tab));
                }
            }
            List<Integer> sheets = BiffDrawingToXml.getSheetsIndexes(params, workbook);
            for (Integer i : sheets) {
                HSSFPatriarch p = workbook.getSheetAt(i).getDrawingPatriarch();
                if (p == null) continue;
                builder.append(tab).append("<sheet").append(i).append(">\n");
                builder.append(p.getBoundAggregate().toXml(tab + "\t"));
                builder.append(tab).append("</sheet").append(i).append(">\n");
            }
            builder.append("</workbook>\n");
            fos.write(builder.toString().getBytes(StringUtil.UTF8));
        }
    }
}

