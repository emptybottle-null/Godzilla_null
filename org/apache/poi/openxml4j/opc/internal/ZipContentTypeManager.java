/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.openxml4j.opc.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.StreamHelper;
import org.apache.poi.openxml4j.opc.internal.ContentTypeManager;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.w3c.dom.Document;

public class ZipContentTypeManager
extends ContentTypeManager {
    private static final POILogger logger = POILogFactory.getLogger(ZipContentTypeManager.class);

    public ZipContentTypeManager(InputStream in, OPCPackage pkg) throws InvalidFormatException {
        super(in, pkg);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean saveImpl(Document content, OutputStream out) {
        boolean bl;
        ZipArchiveOutputStream zos = out instanceof ZipArchiveOutputStream ? (ZipArchiveOutputStream)out : new ZipArchiveOutputStream(out);
        ZipArchiveEntry partEntry = new ZipArchiveEntry("[Content_Types].xml");
        zos.putArchiveEntry(partEntry);
        try {
            bl = StreamHelper.saveXmlInStream(content, zos);
        } catch (Throwable throwable) {
            try {
                zos.closeArchiveEntry();
                throw throwable;
            } catch (IOException ioe) {
                logger.log(7, "Cannot write: [Content_Types].xml in Zip !", ioe);
                return false;
            }
        }
        zos.closeArchiveEntry();
        return bl;
    }
}

