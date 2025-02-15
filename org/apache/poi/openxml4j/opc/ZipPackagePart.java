/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.openxml4j.opc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.ZipPackage;
import org.apache.poi.openxml4j.opc.internal.ContentType;
import org.apache.poi.openxml4j.opc.internal.marshallers.ZipPartMarshaller;
import org.apache.poi.util.NotImplemented;

public class ZipPackagePart
extends PackagePart {
    private ZipArchiveEntry zipEntry;

    public ZipPackagePart(OPCPackage container, ZipArchiveEntry zipEntry, PackagePartName partName, String contentType) throws InvalidFormatException {
        this(container, zipEntry, partName, contentType, true);
    }

    ZipPackagePart(OPCPackage container, ZipArchiveEntry zipEntry, PackagePartName partName, String contentType, boolean loadRelationships) throws InvalidFormatException {
        super(container, partName, new ContentType(contentType), loadRelationships);
        this.zipEntry = zipEntry;
    }

    public ZipArchiveEntry getZipArchive() {
        return this.zipEntry;
    }

    @Override
    protected InputStream getInputStreamImpl() throws IOException {
        return ((ZipPackage)this._container).getZipArchive().getInputStream(this.zipEntry);
    }

    @Override
    protected OutputStream getOutputStreamImpl() {
        return null;
    }

    @Override
    public long getSize() {
        return this.zipEntry.getSize();
    }

    @Override
    public boolean save(OutputStream os) throws OpenXML4JException {
        return new ZipPartMarshaller().marshall(this, os);
    }

    @Override
    @NotImplemented
    public boolean load(InputStream ios) {
        throw new InvalidOperationException("Method not implemented !");
    }

    @Override
    @NotImplemented
    public void close() {
        throw new InvalidOperationException("Method not implemented !");
    }

    @Override
    @NotImplemented
    public void flush() {
        throw new InvalidOperationException("Method not implemented !");
    }
}

