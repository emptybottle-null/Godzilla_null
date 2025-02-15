/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.openxml4j.opc.ZipPackage;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XSSFWorkbookFactory
extends WorkbookFactory {
    public static XSSFWorkbook createWorkbook() {
        return new XSSFWorkbook();
    }

    public static XSSFWorkbook create(OPCPackage pkg) throws IOException {
        return XSSFWorkbookFactory.createWorkbook(pkg);
    }

    public static XSSFWorkbook createWorkbook(ZipPackage pkg) throws IOException {
        return XSSFWorkbookFactory.createWorkbook((OPCPackage)pkg);
    }

    public static XSSFWorkbook createWorkbook(OPCPackage pkg) throws IOException {
        try {
            return new XSSFWorkbook(pkg);
        } catch (RuntimeException ioe) {
            pkg.revert();
            throw ioe;
        }
    }

    public static XSSFWorkbook createWorkbook(File file, boolean readOnly) throws IOException {
        try {
            OPCPackage pkg = OPCPackage.open(file, readOnly ? PackageAccess.READ : PackageAccess.READ_WRITE);
            return XSSFWorkbookFactory.createWorkbook(pkg);
        } catch (InvalidFormatException e) {
            throw new IOException(e);
        }
    }

    public static XSSFWorkbook createWorkbook(InputStream stream) throws IOException {
        try {
            OPCPackage pkg = OPCPackage.open(stream);
            return XSSFWorkbookFactory.createWorkbook(pkg);
        } catch (InvalidFormatException e) {
            throw new IOException(e);
        }
    }

    static {
        WorkbookFactory.createXssfFromScratch = XSSFWorkbookFactory::createWorkbook;
        WorkbookFactory.createXssfByStream = XSSFWorkbookFactory::createWorkbook;
        WorkbookFactory.createXssfByPackage = o -> XSSFWorkbookFactory.createWorkbook((OPCPackage)o);
        WorkbookFactory.createXssfByFile = XSSFWorkbookFactory::createWorkbook;
    }
}

