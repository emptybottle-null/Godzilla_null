/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.openxml4j.opc.internal.marshallers;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.StreamHelper;
import org.apache.poi.openxml4j.opc.internal.ZipHelper;
import org.apache.poi.openxml4j.opc.internal.marshallers.PackagePropertiesMarshaller;

public final class ZipPackagePropertiesMarshaller
extends PackagePropertiesMarshaller {
    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean marshall(PackagePart part, OutputStream out) throws OpenXML4JException {
        boolean bl;
        if (!(out instanceof ZipArchiveOutputStream)) {
            throw new IllegalArgumentException("ZipOutputStream expected!");
        }
        ZipArchiveOutputStream zos = (ZipArchiveOutputStream)out;
        ZipArchiveEntry ctEntry = new ZipArchiveEntry(ZipHelper.getZipItemNameFromOPCName(part.getPartName().getURI().toString()));
        zos.putArchiveEntry(ctEntry);
        try {
            super.marshall(part, out);
            bl = StreamHelper.saveXmlInStream(this.xmlDoc, out);
        } catch (Throwable throwable) {
            try {
                zos.closeArchiveEntry();
                throw throwable;
            } catch (IOException e) {
                throw new OpenXML4JException(e.getLocalizedMessage(), e);
            }
        }
        zos.closeArchiveEntry();
        return bl;
    }
}

