/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package galaxy.jsp.encounter.cfg;

import galaxy.jsp.encounter.util.Utils;
import galaxy.jsp.encounter.xml.Options;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.HashMap;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Config {
    private static boolean validCell(XSSFCell cell) {
        if (cell == null) {
            return false;
        }
        return cell.getCellStyle().getFont().getBold();
    }

    private static String formatCellValue(String value) {
        if (value.length() > 0 && value.charAt(0) == '`') {
            value = value.substring(1);
        }
        if (value.length() > 0 && value.charAt(value.length() - 1) == '`') {
            value = value.substring(0, value.length() - 1);
        }
        return value;
    }

    public static Options ParseCfg(InputStream inputStream) throws Exception {
        Options options = new Options();
        XSSFWorkbook book = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = book.getSheetAt(0);
        int startRow = -1;
        int startCol = -1;
        block0: for (int rowIndex = 0; rowIndex < sheet.getPhysicalNumberOfRows(); ++rowIndex) {
            String value;
            XSSFCell cell;
            int colIndex;
            String k;
            HashMap<String, String> map;
            int maxColIndex;
            String cfg;
            XSSFRow row = sheet.getRow(rowIndex);
            if (startRow == -1) {
                for (int colIndex2 = 0; colIndex2 < row.getPhysicalNumberOfCells(); ++colIndex2) {
                    String s = row.getCell(colIndex2).getStringCellValue();
                    if (!"matrix".equalsIgnoreCase(s)) continue;
                    startRow = rowIndex + 1;
                    startCol = colIndex2;
                    continue block0;
                }
                continue;
            }
            int cfgIndex = startCol + 1;
            if (row == null || (cfg = row.getCell(cfgIndex).getStringCellValue()) == null || cfg.length() == 0) continue;
            if ("extTag".equals(cfg)) {
                if (!Config.validCell(row.getCell(++cfgIndex))) continue;
                String tagName = row.getCell(cfgIndex).getStringCellValue();
                maxColIndex = row.getPhysicalNumberOfCells();
                map = null;
                k = null;
                for (colIndex = cfgIndex + 1; colIndex < maxColIndex; ++colIndex) {
                    cell = row.getCell(colIndex);
                    if (!Config.validCell(cell)) continue;
                    value = Config.formatCellValue(cell.getStringCellValue());
                    if (map == null) {
                        map = new HashMap<String, String>();
                    }
                    if (k == null) {
                        k = value;
                        continue;
                    }
                    map.put(k, value);
                    k = null;
                }
                options.extTags.add(new ImmutablePair<String, Object>(tagName, map));
                continue;
            }
            Type type = Utils.GetFieldType(options, cfg);
            maxColIndex = row.getPhysicalNumberOfCells();
            map = null;
            k = null;
            for (colIndex = cfgIndex + 1; colIndex < maxColIndex; ++colIndex) {
                cell = row.getCell(colIndex);
                if (!Config.validCell(cell)) continue;
                value = Config.formatCellValue(cell.getStringCellValue());
                if (type == String.class) {
                    Utils.SetFieldValue(options, cfg, value);
                    continue;
                }
                if (type == Boolean.TYPE) {
                    Utils.SetFieldValue(options, cfg, "true".equalsIgnoreCase(value));
                    continue;
                }
                if (type == Integer.TYPE) {
                    Utils.SetFieldValue(options, cfg, Integer.valueOf(value));
                    continue;
                }
                if (!"java.util.Map<java.lang.String, java.lang.String>".equals(type.getTypeName())) continue;
                if (map == null) {
                    map = new HashMap();
                }
                if (k == null) {
                    k = value;
                    continue;
                }
                map.put(k, value);
                k = null;
            }
            if (!"java.util.Map<java.lang.String, java.lang.String>".equals(type.getTypeName())) continue;
            Utils.SetFieldValue(options, cfg, map);
        }
        return options;
    }
}

