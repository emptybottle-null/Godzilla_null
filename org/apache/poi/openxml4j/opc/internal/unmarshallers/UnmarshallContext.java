/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.openxml4j.opc.internal.unmarshallers;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePartName;

public final class UnmarshallContext {
    private OPCPackage _package;
    private PackagePartName partName;
    private ZipArchiveEntry zipEntry;

    public UnmarshallContext(OPCPackage targetPackage, PackagePartName partName) {
        this._package = targetPackage;
        this.partName = partName;
    }

    OPCPackage getPackage() {
        return this._package;
    }

    public void setPackage(OPCPackage container) {
        this._package = container;
    }

    PackagePartName getPartName() {
        return this.partName;
    }

    public void setPartName(PackagePartName partName) {
        this.partName = partName;
    }

    ZipArchiveEntry getZipEntry() {
        return this.zipEntry;
    }

    public void setZipEntry(ZipArchiveEntry zipEntry) {
        this.zipEntry = zipEntry;
    }
}

