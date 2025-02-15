/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.usermodel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.sl.usermodel.SlideShowFactory;
import org.apache.poi.util.Internal;
import org.apache.poi.xslf.usermodel.XMLSlideShow;

@Internal
public class XSLFSlideShowFactory
extends SlideShowFactory {
    public static XMLSlideShow create(OPCPackage pkg) throws IOException {
        try {
            return new XMLSlideShow(pkg);
        } catch (IllegalArgumentException ioe) {
            pkg.revert();
            throw ioe;
        }
    }

    public static XMLSlideShow createSlideShow(OPCPackage pkg) throws IOException {
        try {
            return new XMLSlideShow(pkg);
        } catch (IllegalArgumentException ioe) {
            pkg.revert();
            throw ioe;
        }
    }

    public static XMLSlideShow createSlideShow(File file, boolean readOnly) throws IOException {
        try {
            OPCPackage pkg = OPCPackage.open(file, readOnly ? PackageAccess.READ : PackageAccess.READ_WRITE);
            return XSLFSlideShowFactory.createSlideShow(pkg);
        } catch (InvalidFormatException e) {
            throw new IOException(e);
        }
    }

    public static XMLSlideShow createSlideShow(InputStream stream) throws IOException {
        try {
            OPCPackage pkg = OPCPackage.open(stream);
            return XSLFSlideShowFactory.createSlideShow(pkg);
        } catch (InvalidFormatException e) {
            throw new IOException(e);
        }
    }

    static {
        SlideShowFactory.createXslfByFile = XSLFSlideShowFactory::createSlideShow;
        SlideShowFactory.createXslfByStream = XSLFSlideShowFactory::createSlideShow;
    }
}

