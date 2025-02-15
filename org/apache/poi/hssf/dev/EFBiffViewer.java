/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.dev;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.hssf.dev.BiffViewer;
import org.apache.poi.hssf.eventusermodel.HSSFEventFactory;
import org.apache.poi.hssf.eventusermodel.HSSFRequest;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class EFBiffViewer {
    String file;

    public void run() throws IOException {
        try (POIFSFileSystem fs = new POIFSFileSystem(new File(this.file), true);
             InputStream din = BiffViewer.getPOIFSInputStream(fs);){
            HSSFRequest req = new HSSFRequest();
            req.addListenerForAllRecords(System.out::println);
            HSSFEventFactory factory = new HSSFEventFactory();
            factory.processEvents(req, din);
        }
    }

    public void setFile(String file) {
        this.file = file;
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 1 && !args[0].equals("--help")) {
            EFBiffViewer viewer = new EFBiffViewer();
            viewer.setFile(args[0]);
            viewer.run();
        } else {
            System.out.println("EFBiffViewer");
            System.out.println("Outputs biffview of records based on HSSFEventFactory");
            System.out.println("usage: java org.apache.poi.hssf.dev.EBBiffViewer filename");
        }
    }
}

