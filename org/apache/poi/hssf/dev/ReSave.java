/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.dev;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ReSave {
    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void main(String[] args) throws Exception {
        boolean initDrawing = false;
        boolean saveToMemory = false;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        for (String filename : args) {
            if (filename.equals("-dg")) {
                initDrawing = true;
                continue;
            }
            if (filename.equals("-bos")) {
                saveToMemory = true;
                continue;
            }
            System.out.print("reading " + filename + "...");
            FileInputStream is = new FileInputStream(filename);
            HSSFWorkbook wb = new HSSFWorkbook(is);
            try {
                OutputStream os;
                System.out.println("done");
                for (int i = 0; i < wb.getNumberOfSheets(); ++i) {
                    HSSFSheet sheet = wb.getSheetAt(i);
                    if (!initDrawing) continue;
                    sheet.getDrawingPatriarch();
                }
                if (saveToMemory) {
                    bos.reset();
                    os = bos;
                } else {
                    String outputFile = filename.replace(".xls", "-saved.xls");
                    System.out.print("saving to " + outputFile + "...");
                    os = new FileOutputStream(outputFile);
                }
                try {
                    wb.write(os);
                } finally {
                    ((OutputStream)os).close();
                }
                System.out.println("done");
            } finally {
                wb.close();
                is.close();
            }
        }
    }
}

