/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.openxml4j.opc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.poi.UnsupportedFileFormatException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.openxml4j.exceptions.ODFNotOfficeXmlFileException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JRuntimeException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartCollection;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.openxml4j.opc.ZipPackagePart;
import org.apache.poi.openxml4j.opc.internal.ContentTypeManager;
import org.apache.poi.openxml4j.opc.internal.FileHelper;
import org.apache.poi.openxml4j.opc.internal.MemoryPackagePart;
import org.apache.poi.openxml4j.opc.internal.PartMarshaller;
import org.apache.poi.openxml4j.opc.internal.ZipContentTypeManager;
import org.apache.poi.openxml4j.opc.internal.ZipHelper;
import org.apache.poi.openxml4j.opc.internal.marshallers.ZipPartMarshaller;
import org.apache.poi.openxml4j.util.ZipArchiveThresholdInputStream;
import org.apache.poi.openxml4j.util.ZipEntrySource;
import org.apache.poi.openxml4j.util.ZipFileZipEntrySource;
import org.apache.poi.openxml4j.util.ZipInputStreamZipEntrySource;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.util.TempFile;

public final class ZipPackage
extends OPCPackage {
    private static final String MIMETYPE = "mimetype";
    private static final String SETTINGS_XML = "settings.xml";
    private static final POILogger LOG = POILogFactory.getLogger(ZipPackage.class);
    private final ZipEntrySource zipArchive;

    public ZipPackage() {
        super(defaultPackageAccess);
        this.zipArchive = null;
        try {
            this.contentTypeManager = new ZipContentTypeManager(null, this);
        } catch (InvalidFormatException e) {
            LOG.log(5, "Could not parse ZipPackage", e);
        }
    }

    ZipPackage(InputStream in, PackageAccess access) throws IOException {
        super(access);
        ZipArchiveThresholdInputStream zis = ZipHelper.openZipStream(in);
        try {
            this.zipArchive = new ZipInputStreamZipEntrySource(zis);
        } catch (IOException e) {
            IOUtils.closeQuietly(zis);
            throw e;
        }
    }

    ZipPackage(String path, PackageAccess access) throws InvalidOperationException {
        this(new File(path), access);
    }

    ZipPackage(File file, PackageAccess access) throws InvalidOperationException {
        super(access);
        ZipEntrySource ze;
        try {
            ZipSecureFile zipFile = ZipHelper.openZipFile(file);
            ze = new ZipFileZipEntrySource(zipFile);
        } catch (IOException e) {
            if (access == PackageAccess.WRITE) {
                throw new InvalidOperationException("Can't open the specified file: '" + file + "'", e);
            }
            LOG.log(7, "Error in zip file " + file + " - falling back to stream processing (i.e. ignoring zip central directory)");
            ze = ZipPackage.openZipEntrySourceStream(file);
        }
        this.zipArchive = ze;
    }

    private static ZipEntrySource openZipEntrySourceStream(File file) throws InvalidOperationException {
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new InvalidOperationException("Can't open the specified file input stream from file: '" + file + "'", e);
        }
        try {
            return ZipPackage.openZipEntrySourceStream(fis);
        } catch (UnsupportedFileFormatException | InvalidOperationException e) {
            IOUtils.closeQuietly(fis);
            throw e;
        } catch (Exception e) {
            IOUtils.closeQuietly(fis);
            throw new InvalidOperationException("Failed to read the file input stream from file: '" + file + "'", e);
        }
    }

    private static ZipEntrySource openZipEntrySourceStream(FileInputStream fis) throws InvalidOperationException {
        ZipArchiveThresholdInputStream zis;
        try {
            zis = ZipHelper.openZipStream(fis);
        } catch (IOException e) {
            throw new InvalidOperationException("Could not open the file input stream", e);
        }
        try {
            return ZipPackage.openZipEntrySourceStream(zis);
        } catch (UnsupportedFileFormatException | InvalidOperationException e) {
            IOUtils.closeQuietly(zis);
            throw e;
        } catch (Exception e) {
            IOUtils.closeQuietly(zis);
            throw new InvalidOperationException("Failed to read the zip entry source stream", e);
        }
    }

    private static ZipEntrySource openZipEntrySourceStream(ZipArchiveThresholdInputStream zis) throws InvalidOperationException {
        try {
            return new ZipInputStreamZipEntrySource(zis);
        } catch (IOException e) {
            throw new InvalidOperationException("Could not open the specified zip entry source stream", e);
        }
    }

    ZipPackage(ZipEntrySource zipEntry, PackageAccess access) {
        super(access);
        this.zipArchive = zipEntry;
    }

    @Override
    protected PackagePartCollection getPartsImpl() throws InvalidFormatException {
        PackagePartCollection newPartList = new PackagePartCollection();
        if (this.zipArchive == null) {
            return newPartList;
        }
        ZipArchiveEntry contentTypeEntry = this.zipArchive.getEntry("[Content_Types].xml");
        if (contentTypeEntry != null) {
            if (this.contentTypeManager != null) {
                throw new InvalidFormatException("ContentTypeManager can only be created once. This must be a cyclic relation?");
            }
            try {
                this.contentTypeManager = new ZipContentTypeManager(this.zipArchive.getInputStream(contentTypeEntry), this);
            } catch (IOException e) {
                throw new InvalidFormatException(e.getMessage(), e);
            }
        } else {
            boolean hasSettingsXML;
            boolean hasMimetype = this.zipArchive.getEntry(MIMETYPE) != null;
            boolean bl = hasSettingsXML = this.zipArchive.getEntry(SETTINGS_XML) != null;
            if (hasMimetype && hasSettingsXML) {
                throw new ODFNotOfficeXmlFileException("The supplied data appears to be in ODF (Open Document) Format. Formats like these (eg ODS, ODP) are not supported, try Apache ODFToolkit");
            }
            if (!this.zipArchive.getEntries().hasMoreElements()) {
                throw new NotOfficeXmlFileException("No valid entries or contents found, this is not a valid OOXML (Office Open XML) file");
            }
            throw new InvalidFormatException("Package should contain a content type part [M1.13]");
        }
        List entries = Collections.list(this.zipArchive.getEntries()).stream().map(zae -> new EntryTriple((ZipArchiveEntry)zae, this.contentTypeManager)).filter(mm -> mm.partName != null).sorted().collect(Collectors.toList());
        for (EntryTriple et : entries) {
            et.register(newPartList);
        }
        return newPartList;
    }

    @Override
    protected PackagePart createPartImpl(PackagePartName partName, String contentType, boolean loadRelationships) {
        if (contentType == null) {
            throw new IllegalArgumentException("contentType");
        }
        if (partName == null) {
            throw new IllegalArgumentException("partName");
        }
        try {
            return new MemoryPackagePart((OPCPackage)this, partName, contentType, loadRelationships);
        } catch (InvalidFormatException e) {
            LOG.log(5, e);
            return null;
        }
    }

    @Override
    protected void removePartImpl(PackagePartName partName) {
        if (partName == null) {
            throw new IllegalArgumentException("partUri");
        }
    }

    @Override
    protected void flushImpl() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    protected void closeImpl() throws IOException {
        this.flush();
        if (this.originalPackagePath == null || this.originalPackagePath.isEmpty()) {
            return;
        }
        File targetFile = new File(this.originalPackagePath);
        if (!targetFile.exists()) {
            throw new InvalidOperationException("Can't close a package not previously open with the open() method !");
        }
        String tempFileName = this.generateTempFileName(FileHelper.getDirectory(targetFile));
        File tempFile = TempFile.createTempFile(tempFileName, ".tmp");
        boolean success = false;
        try {
            this.save(tempFile);
            success = true;
        } catch (Throwable throwable) {
            IOUtils.closeQuietly(this.zipArchive);
            try {
                if (success) {
                    FileHelper.copyFile(tempFile, targetFile);
                }
                if (tempFile.delete()) throw throwable;
            } catch (Throwable throwable2) {
                if (tempFile.delete()) throw throwable2;
                LOG.log(5, "The temporary file: '" + targetFile.getAbsolutePath() + "' cannot be deleted ! Make sure that no other application use it.");
                throw throwable2;
            }
            LOG.log(5, "The temporary file: '" + targetFile.getAbsolutePath() + "' cannot be deleted ! Make sure that no other application use it.");
            throw throwable;
        }
        IOUtils.closeQuietly(this.zipArchive);
        try {
            if (success) {
                FileHelper.copyFile(tempFile, targetFile);
            }
            if (tempFile.delete()) return;
        } catch (Throwable throwable) {
            if (tempFile.delete()) throw throwable;
            LOG.log(5, "The temporary file: '" + targetFile.getAbsolutePath() + "' cannot be deleted ! Make sure that no other application use it.");
            throw throwable;
        }
        LOG.log(5, "The temporary file: '" + targetFile.getAbsolutePath() + "' cannot be deleted ! Make sure that no other application use it.");
    }

    private synchronized String generateTempFileName(File directory) {
        File tmpFilename;
        while ((tmpFilename = new File(directory.getAbsoluteFile() + File.separator + "OpenXML4J" + System.nanoTime())).exists()) {
        }
        return FileHelper.getFilename(tmpFilename.getAbsoluteFile());
    }

    @Override
    protected void revertImpl() {
        try {
            if (this.zipArchive != null) {
                this.zipArchive.close();
            }
        } catch (IOException iOException) {
            // empty catch block
        }
    }

    @Override
    public void saveImpl(OutputStream outputStream) {
        this.throwExceptionIfReadOnly();
        ZipArchiveOutputStream zos = outputStream instanceof ZipArchiveOutputStream ? (ZipArchiveOutputStream)outputStream : new ZipArchiveOutputStream(outputStream);
        try {
            if (this.getPartsByRelationshipType("http://schemas.openxmlformats.org/package/2006/relationships/metadata/core-properties").size() == 0 && this.getPartsByRelationshipType("http://schemas.openxmlformats.org/officedocument/2006/relationships/metadata/core-properties").size() == 0) {
                LOG.log(1, "Save core properties part");
                this.getPackageProperties();
                this.addPackagePart(this.packageProperties);
                this.relationships.addRelationship(this.packageProperties.getPartName().getURI(), TargetMode.INTERNAL, "http://schemas.openxmlformats.org/package/2006/relationships/metadata/core-properties", null);
                if (!this.contentTypeManager.isContentTypeRegister("application/vnd.openxmlformats-package.core-properties+xml")) {
                    this.contentTypeManager.addContentType(this.packageProperties.getPartName(), "application/vnd.openxmlformats-package.core-properties+xml");
                }
            }
            LOG.log(1, "Save content types part");
            this.contentTypeManager.save(zos);
            LOG.log(1, "Save package relationships");
            ZipPartMarshaller.marshallRelationshipPart(this.getRelationships(), PackagingURIHelper.PACKAGE_RELATIONSHIPS_ROOT_PART_NAME, zos);
            for (PackagePart part : this.getParts()) {
                if (part.isRelationshipPart()) continue;
                PackagePartName ppn = part.getPartName();
                LOG.log(1, "Save part '" + ZipHelper.getZipItemNameFromOPCName(ppn.getName()) + "'");
                PartMarshaller marshaller = (PartMarshaller)this.partMarshallers.get(part._contentType);
                PartMarshaller pm = marshaller != null ? marshaller : this.defaultPartMarshaller;
                if (pm.marshall(part, zos)) continue;
                String errMsg = "The part " + ppn.getURI() + " failed to be saved in the stream with marshaller ";
                throw new OpenXML4JException(errMsg + pm);
            }
            zos.finish();
        } catch (OpenXML4JRuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new OpenXML4JRuntimeException("Fail to save: an error occurs while saving the package : " + e.getMessage(), e);
        }
    }

    public ZipEntrySource getZipArchive() {
        return this.zipArchive;
    }

    private class EntryTriple
    implements Comparable<EntryTriple> {
        final ZipArchiveEntry zipArchiveEntry;
        final PackagePartName partName;
        final String contentType;

        EntryTriple(ZipArchiveEntry zipArchiveEntry, ContentTypeManager contentTypeManager) {
            this.zipArchiveEntry = zipArchiveEntry;
            String entryName = zipArchiveEntry.getName();
            PackagePartName ppn = null;
            try {
                ppn = "[Content_Types].xml".equalsIgnoreCase(entryName) ? null : PackagingURIHelper.createPartName(ZipHelper.getOPCNameFromZipItemName(entryName));
            } catch (Exception e) {
                LOG.log(5, "Entry " + entryName + " is not valid, so this part won't be add to the package.", e);
            }
            this.partName = ppn;
            this.contentType = ppn == null ? null : contentTypeManager.getContentType(this.partName);
        }

        void register(PackagePartCollection partList) throws InvalidFormatException {
            if (this.contentType == null) {
                throw new InvalidFormatException("The part " + this.partName.getURI().getPath() + " does not have any content type ! Rule: Package require content types when retrieving a part from a package. [M.1.14]");
            }
            if (partList.containsKey(this.partName)) {
                throw new InvalidFormatException("A part with the name '" + this.partName + "' already exist : Packages shall not contain equivalent part names and package implementers shall neither create nor recognize packages with equivalent part names. [M1.12]");
            }
            try {
                partList.put(this.partName, new ZipPackagePart(ZipPackage.this, this.zipArchiveEntry, this.partName, this.contentType, false));
            } catch (InvalidOperationException e) {
                throw new InvalidFormatException(e.getMessage(), e);
            }
        }

        @Override
        public int compareTo(EntryTriple o) {
            int contentTypeOrder1 = "application/vnd.openxmlformats-package.relationships+xml".equals(this.contentType) ? -1 : 1;
            int contentTypeOrder2 = "application/vnd.openxmlformats-package.relationships+xml".equals(o.contentType) ? -1 : 1;
            int cmpCT = Integer.compare(contentTypeOrder1, contentTypeOrder2);
            return cmpCT != 0 ? cmpCT : this.partName.compareTo(o.partName);
        }
    }
}

