/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.usermodel;

import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.Internal;

@Internal
public class HSSFWorkbookFactory
extends WorkbookFactory {
    public static HSSFWorkbook createWorkbook() {
        return new HSSFWorkbook();
    }

    public static HSSFWorkbook createWorkbook(POIFSFileSystem fs) throws IOException {
        return new HSSFWorkbook(fs);
    }

    public static HSSFWorkbook createWorkbook(DirectoryNode root) throws IOException {
        return new HSSFWorkbook(root, true);
    }

    static {
        WorkbookFactory.createHssfFromScratch = HSSFWorkbookFactory::createWorkbook;
        WorkbookFactory.createHssfByNode = HSSFWorkbookFactory::createWorkbook;
    }
}

