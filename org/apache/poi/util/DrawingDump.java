/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.util;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Sheet;

public final class DrawingDump {
    private DrawingDump() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void main(String[] args) throws IOException {
        OutputStreamWriter osw = new OutputStreamWriter((OutputStream)System.out, Charset.defaultCharset());
        PrintWriter pw = new PrintWriter(osw);
        POIFSFileSystem fs = new POIFSFileSystem(new File(args[0]));
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        try {
            pw.println("Drawing group:");
            wb.dumpDrawingGroupRecords(true);
            int i = 1;
            for (Sheet sheet : wb) {
                pw.println("Sheet " + i + "(" + sheet.getSheetName() + "):");
                ((HSSFSheet)sheet).dumpDrawingRecords(true, pw);
            }
        } finally {
            wb.close();
            fs.close();
        }
    }
}

