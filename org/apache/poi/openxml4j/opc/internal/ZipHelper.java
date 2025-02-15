/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.openxml4j.opc.internal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.openxml4j.exceptions.OLE2NotOfficeXmlFileException;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.ZipPackage;
import org.apache.poi.openxml4j.util.ZipArchiveThresholdInputStream;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.util.Internal;

@Internal
public final class ZipHelper {
    private static final String FORWARD_SLASH = "/";

    private ZipHelper() {
    }

    public static ZipArchiveEntry getCorePropertiesZipEntry(ZipPackage pkg) {
        PackageRelationship corePropsRel = pkg.getRelationshipsByType("http://schemas.openxmlformats.org/package/2006/relationships/metadata/core-properties").getRelationship(0);
        if (corePropsRel == null) {
            return null;
        }
        return new ZipArchiveEntry(corePropsRel.getTargetURI().getPath());
    }

    public static String getOPCNameFromZipItemName(String zipItemName) {
        if (zipItemName == null) {
            throw new IllegalArgumentException("zipItemName cannot be null");
        }
        if (zipItemName.startsWith(FORWARD_SLASH)) {
            return zipItemName;
        }
        return FORWARD_SLASH + zipItemName;
    }

    public static String getZipItemNameFromOPCName(String opcItemName) {
        if (opcItemName == null) {
            throw new IllegalArgumentException("opcItemName cannot be null");
        }
        String retVal = opcItemName;
        while (retVal.startsWith(FORWARD_SLASH)) {
            retVal = retVal.substring(1);
        }
        return retVal;
    }

    public static URI getZipURIFromOPCName(String opcItemName) {
        if (opcItemName == null) {
            throw new IllegalArgumentException("opcItemName");
        }
        String retVal = opcItemName;
        while (retVal.startsWith(FORWARD_SLASH)) {
            retVal = retVal.substring(1);
        }
        try {
            return new URI(retVal);
        } catch (URISyntaxException e) {
            return null;
        }
    }

    private static void verifyZipHeader(InputStream stream) throws NotOfficeXmlFileException, IOException {
        InputStream is = FileMagic.prepareToCheckMagic(stream);
        FileMagic fm = FileMagic.valueOf(is);
        switch (fm) {
            case OLE2: {
                throw new OLE2NotOfficeXmlFileException("The supplied data appears to be in the OLE2 Format. You are calling the part of POI that deals with OOXML (Office Open XML) Documents. You need to call a different part of POI to process this data (eg HSSF instead of XSSF)");
            }
            case XML: {
                throw new NotOfficeXmlFileException("The supplied data appears to be a raw XML file. Formats such as Office 2003 XML are not supported");
            }
        }
    }

    public static ZipArchiveThresholdInputStream openZipStream(InputStream stream) throws IOException {
        InputStream checkedStream = FileMagic.prepareToCheckMagic(stream);
        ZipHelper.verifyZipHeader(checkedStream);
        return new ZipArchiveThresholdInputStream(new ZipArchiveInputStream(checkedStream));
    }

    public static ZipSecureFile openZipFile(File file) throws IOException, NotOfficeXmlFileException {
        if (!file.exists()) {
            throw new FileNotFoundException("File does not exist");
        }
        if (file.isDirectory()) {
            throw new IOException("File is a directory");
        }
        try (FileInputStream input = new FileInputStream(file);){
            ZipHelper.verifyZipHeader(input);
        }
        return new ZipSecureFile(file);
    }

    public static ZipSecureFile openZipFile(String path) throws IOException {
        return ZipHelper.openZipFile(new File(path));
    }
}

