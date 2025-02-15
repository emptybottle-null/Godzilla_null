/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.openxml4j.opc;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JRuntimeException;
import org.apache.poi.openxml4j.exceptions.PartAlreadyExistsException;
import org.apache.poi.openxml4j.opc.ContentTypes;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartCollection;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageProperties;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackageRelationshipCollection;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.openxml4j.opc.RelationshipSource;
import org.apache.poi.openxml4j.opc.StreamHelper;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.openxml4j.opc.ZipPackage;
import org.apache.poi.openxml4j.opc.internal.ContentType;
import org.apache.poi.openxml4j.opc.internal.ContentTypeManager;
import org.apache.poi.openxml4j.opc.internal.PackagePropertiesPart;
import org.apache.poi.openxml4j.opc.internal.PartMarshaller;
import org.apache.poi.openxml4j.opc.internal.PartUnmarshaller;
import org.apache.poi.openxml4j.opc.internal.ZipContentTypeManager;
import org.apache.poi.openxml4j.opc.internal.marshallers.DefaultMarshaller;
import org.apache.poi.openxml4j.opc.internal.marshallers.ZipPackagePropertiesMarshaller;
import org.apache.poi.openxml4j.opc.internal.unmarshallers.PackagePropertiesUnmarshaller;
import org.apache.poi.openxml4j.opc.internal.unmarshallers.UnmarshallContext;
import org.apache.poi.openxml4j.util.ZipEntrySource;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.NotImplemented;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;

public abstract class OPCPackage
implements RelationshipSource,
Closeable {
    private static final POILogger logger = POILogFactory.getLogger(OPCPackage.class);
    protected static final PackageAccess defaultPackageAccess = PackageAccess.READ_WRITE;
    private final PackageAccess packageAccess;
    private PackagePartCollection partList;
    protected PackageRelationshipCollection relationships;
    protected final Map<ContentType, PartMarshaller> partMarshallers = new HashMap<ContentType, PartMarshaller>(5);
    protected final PartMarshaller defaultPartMarshaller = new DefaultMarshaller();
    protected final Map<ContentType, PartUnmarshaller> partUnmarshallers = new HashMap<ContentType, PartUnmarshaller>(2);
    protected PackagePropertiesPart packageProperties;
    protected ContentTypeManager contentTypeManager;
    protected boolean isDirty;
    protected String originalPackagePath;
    protected OutputStream output;

    OPCPackage(PackageAccess access) {
        if (this.getClass() != ZipPackage.class) {
            throw new IllegalArgumentException("PackageBase may not be subclassed");
        }
        this.packageAccess = access;
        ContentType contentType = OPCPackage.newCorePropertiesPart();
        this.partUnmarshallers.put(contentType, new PackagePropertiesUnmarshaller());
        this.partMarshallers.put(contentType, new ZipPackagePropertiesMarshaller());
    }

    private static ContentType newCorePropertiesPart() {
        try {
            return new ContentType("application/vnd.openxmlformats-package.core-properties+xml");
        } catch (InvalidFormatException e) {
            throw new OpenXML4JRuntimeException("Package.init() : this exception should never happen, if you read this message please send a mail to the developers team. : " + e.getMessage(), e);
        }
    }

    public static OPCPackage open(String path) throws InvalidFormatException {
        return OPCPackage.open(path, defaultPackageAccess);
    }

    public static OPCPackage open(File file) throws InvalidFormatException {
        return OPCPackage.open(file, defaultPackageAccess);
    }

    public static OPCPackage open(ZipEntrySource zipEntry) throws InvalidFormatException {
        ZipPackage pack = new ZipPackage(zipEntry, PackageAccess.READ);
        try {
            if (pack.partList == null) {
                pack.getParts();
            }
            return pack;
        } catch (RuntimeException | InvalidFormatException e) {
            IOUtils.closeQuietly(pack);
            throw e;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static OPCPackage open(String path, PackageAccess access) throws InvalidFormatException, InvalidOperationException {
        if (path == null || path.trim().isEmpty()) {
            throw new IllegalArgumentException("'path' must be given");
        }
        File file = new File(path);
        if (file.exists() && file.isDirectory()) {
            throw new IllegalArgumentException("path must not be a directory");
        }
        ZipPackage pack = new ZipPackage(path, access);
        boolean success = false;
        if (pack.partList == null && access != PackageAccess.WRITE) {
            try {
                pack.getParts();
                success = true;
            } finally {
                if (!success) {
                    IOUtils.closeQuietly(pack);
                }
            }
        }
        pack.originalPackagePath = new File(path).getAbsolutePath();
        return pack;
    }

    public static OPCPackage open(File file, PackageAccess access) throws InvalidFormatException {
        if (file == null) {
            throw new IllegalArgumentException("'file' must be given");
        }
        if (file.exists() && file.isDirectory()) {
            throw new IllegalArgumentException("file must not be a directory");
        }
        ZipPackage pack = new ZipPackage(file, access);
        try {
            if (pack.partList == null && access != PackageAccess.WRITE) {
                pack.getParts();
            }
            pack.originalPackagePath = file.getAbsolutePath();
            return pack;
        } catch (RuntimeException | InvalidFormatException e) {
            IOUtils.closeQuietly(pack);
            throw e;
        }
    }

    public static OPCPackage open(InputStream in) throws InvalidFormatException, IOException {
        ZipPackage pack = new ZipPackage(in, PackageAccess.READ_WRITE);
        try {
            if (pack.partList == null) {
                pack.getParts();
            }
        } catch (RuntimeException | InvalidFormatException e) {
            IOUtils.closeQuietly(pack);
            throw e;
        }
        return pack;
    }

    public static OPCPackage openOrCreate(File file) throws InvalidFormatException {
        if (file.exists()) {
            return OPCPackage.open(file.getAbsolutePath());
        }
        return OPCPackage.create(file);
    }

    public static OPCPackage create(String path) {
        return OPCPackage.create(new File(path));
    }

    public static OPCPackage create(File file) {
        if (file == null || file.exists() && file.isDirectory()) {
            throw new IllegalArgumentException("file");
        }
        if (file.exists()) {
            throw new InvalidOperationException("This package (or file) already exists : use the open() method or delete the file.");
        }
        ZipPackage pkg = new ZipPackage();
        pkg.originalPackagePath = file.getAbsolutePath();
        OPCPackage.configurePackage(pkg);
        return pkg;
    }

    public static OPCPackage create(OutputStream output) {
        ZipPackage pkg = new ZipPackage();
        pkg.originalPackagePath = null;
        pkg.output = output;
        OPCPackage.configurePackage(pkg);
        return pkg;
    }

    private static void configurePackage(OPCPackage pkg) {
        try {
            pkg.contentTypeManager = new ZipContentTypeManager(null, pkg);
            pkg.contentTypeManager.addContentType(PackagingURIHelper.createPartName(PackagingURIHelper.PACKAGE_RELATIONSHIPS_ROOT_URI), "application/vnd.openxmlformats-package.relationships+xml");
            pkg.contentTypeManager.addContentType(PackagingURIHelper.createPartName("/default.xml"), "application/xml");
            pkg.packageProperties = new PackagePropertiesPart(pkg, PackagingURIHelper.CORE_PROPERTIES_PART_NAME);
            pkg.packageProperties.setCreatorProperty("Generated by Apache POI OpenXML4J");
            pkg.packageProperties.setCreatedProperty(Optional.of(new Date()));
        } catch (InvalidFormatException e) {
            throw new IllegalStateException(e);
        }
    }

    public void flush() {
        this.throwExceptionIfReadOnly();
        if (this.packageProperties != null) {
            this.packageProperties.flush();
        }
        this.flushImpl();
    }

    @Override
    public void close() throws IOException {
        if (this.packageAccess == PackageAccess.READ) {
            logger.log(5, "The close() method is intended to SAVE a package. This package is open in READ ONLY mode, use the revert() method instead !");
            this.revert();
            return;
        }
        if (this.contentTypeManager == null) {
            logger.log(5, "Unable to call close() on a package that hasn't been fully opened yet");
            this.revert();
            return;
        }
        if (this.originalPackagePath != null && !this.originalPackagePath.trim().isEmpty()) {
            File targetFile = new File(this.originalPackagePath);
            if (!targetFile.exists() || !this.originalPackagePath.equalsIgnoreCase(targetFile.getAbsolutePath())) {
                this.save(targetFile);
            } else {
                this.closeImpl();
            }
        } else if (this.output != null) {
            this.save(this.output);
            this.output.close();
        }
        this.revert();
        this.contentTypeManager.clearAll();
    }

    public void revert() {
        this.revertImpl();
    }

    public void addThumbnail(String path) throws IOException {
        if (path == null || path.isEmpty()) {
            throw new IllegalArgumentException("path");
        }
        String name = path.substring(path.lastIndexOf(File.separatorChar) + 1);
        try (FileInputStream is = new FileInputStream(path);){
            this.addThumbnail(name, is);
        }
    }

    public void addThumbnail(String filename, InputStream data) throws IOException {
        PackagePartName thumbnailPartName;
        if (filename == null || filename.isEmpty()) {
            throw new IllegalArgumentException("filename");
        }
        String contentType = ContentTypes.getContentTypeFromFileExtension(filename);
        try {
            thumbnailPartName = PackagingURIHelper.createPartName("/docProps/" + filename);
        } catch (InvalidFormatException e) {
            String partName = "/docProps/thumbnail" + filename.substring(filename.lastIndexOf(".") + 1);
            try {
                thumbnailPartName = PackagingURIHelper.createPartName(partName);
            } catch (InvalidFormatException e2) {
                throw new InvalidOperationException("Can't add a thumbnail file named '" + filename + "'", e2);
            }
        }
        if (this.getPart(thumbnailPartName) != null) {
            throw new InvalidOperationException("You already add a thumbnail named '" + filename + "'");
        }
        PackagePart thumbnailPart = this.createPart(thumbnailPartName, contentType, false);
        this.addRelationship(thumbnailPartName, TargetMode.INTERNAL, "http://schemas.openxmlformats.org/package/2006/relationships/metadata/thumbnail");
        StreamHelper.copyStream(data, thumbnailPart.getOutputStream());
    }

    void throwExceptionIfReadOnly() throws InvalidOperationException {
        if (this.packageAccess == PackageAccess.READ) {
            throw new InvalidOperationException("Operation not allowed, document open in read only mode!");
        }
    }

    void throwExceptionIfWriteOnly() throws InvalidOperationException {
        if (this.packageAccess == PackageAccess.WRITE) {
            throw new InvalidOperationException("Operation not allowed, document open in write only mode!");
        }
    }

    public PackageProperties getPackageProperties() throws InvalidFormatException {
        this.throwExceptionIfWriteOnly();
        if (this.packageProperties == null) {
            this.packageProperties = new PackagePropertiesPart(this, PackagingURIHelper.CORE_PROPERTIES_PART_NAME);
        }
        return this.packageProperties;
    }

    public PackagePart getPart(PackagePartName partName) {
        this.throwExceptionIfWriteOnly();
        if (partName == null) {
            throw new IllegalArgumentException("partName");
        }
        if (this.partList == null) {
            try {
                this.getParts();
            } catch (InvalidFormatException e) {
                return null;
            }
        }
        return this.partList.get(partName);
    }

    public ArrayList<PackagePart> getPartsByContentType(String contentType) {
        ArrayList<PackagePart> retArr = new ArrayList<PackagePart>();
        for (PackagePart part : this.partList.sortedValues()) {
            if (!part.getContentType().equals(contentType)) continue;
            retArr.add(part);
        }
        return retArr;
    }

    public ArrayList<PackagePart> getPartsByRelationshipType(String relationshipType) {
        if (relationshipType == null) {
            throw new IllegalArgumentException("relationshipType");
        }
        ArrayList<PackagePart> retArr = new ArrayList<PackagePart>();
        for (PackageRelationship rel : this.getRelationshipsByType(relationshipType)) {
            PackagePart part = this.getPart(rel);
            if (part == null) continue;
            retArr.add(part);
        }
        Collections.sort(retArr);
        return retArr;
    }

    public List<PackagePart> getPartsByName(Pattern namePattern) {
        if (namePattern == null) {
            throw new IllegalArgumentException("name pattern must not be null");
        }
        Matcher matcher = namePattern.matcher("");
        ArrayList<PackagePart> result = new ArrayList<PackagePart>();
        for (PackagePart part : this.partList.sortedValues()) {
            PackagePartName partName = part.getPartName();
            if (!matcher.reset(partName.getName()).matches()) continue;
            result.add(part);
        }
        return result;
    }

    public PackagePart getPart(PackageRelationship partRel) {
        PackagePart retPart = null;
        this.ensureRelationships();
        for (PackageRelationship rel : this.relationships) {
            if (!rel.getRelationshipType().equals(partRel.getRelationshipType())) continue;
            try {
                retPart = this.getPart(PackagingURIHelper.createPartName(rel.getTargetURI()));
                break;
            } catch (InvalidFormatException e) {
            }
        }
        return retPart;
    }

    public ArrayList<PackagePart> getParts() throws InvalidFormatException {
        this.throwExceptionIfWriteOnly();
        if (this.partList == null) {
            boolean hasCorePropertiesPart = false;
            boolean needCorePropertiesPart = true;
            this.partList = this.getPartsImpl();
            for (PackagePart part : new ArrayList<PackagePart>(this.partList.sortedValues())) {
                PartUnmarshaller partUnmarshaller;
                part.loadRelationships();
                if ("application/vnd.openxmlformats-package.core-properties+xml".equals(part.getContentType())) {
                    if (!hasCorePropertiesPart) {
                        hasCorePropertiesPart = true;
                    } else {
                        logger.log(5, "OPC Compliance error [M4.1]: there is more than one core properties relationship in the package! POI will use only the first, but other software may reject this file.");
                    }
                }
                if ((partUnmarshaller = this.partUnmarshallers.get(part._contentType)) == null) continue;
                UnmarshallContext context = new UnmarshallContext(this, part._partName);
                try {
                    PackagePart unmarshallPart = partUnmarshaller.unmarshall(context, part.getInputStream());
                    this.partList.remove(part.getPartName());
                    this.partList.put(unmarshallPart._partName, unmarshallPart);
                    if (!(unmarshallPart instanceof PackagePropertiesPart) || !hasCorePropertiesPart || !needCorePropertiesPart) continue;
                    this.packageProperties = (PackagePropertiesPart)unmarshallPart;
                    needCorePropertiesPart = false;
                } catch (IOException ioe) {
                    logger.log(5, "Unmarshall operation : IOException for " + part._partName);
                } catch (InvalidOperationException invoe) {
                    throw new InvalidFormatException(invoe.getMessage(), invoe);
                }
            }
        }
        return new ArrayList<PackagePart>(this.partList.sortedValues());
    }

    public PackagePart createPart(PackagePartName partName, String contentType) {
        return this.createPart(partName, contentType, true);
    }

    PackagePart createPart(PackagePartName partName, String contentType, boolean loadRelationships) {
        this.throwExceptionIfReadOnly();
        if (partName == null) {
            throw new IllegalArgumentException("partName");
        }
        if (contentType == null || contentType.isEmpty()) {
            throw new IllegalArgumentException("contentType");
        }
        if (this.partList.containsKey(partName) && !this.partList.get(partName).isDeleted()) {
            throw new PartAlreadyExistsException("A part with the name '" + partName.getName() + "' already exists : Packages shall not contain equivalent part names and package implementers shall neither create nor recognize packages with equivalent part names. [M1.12]");
        }
        if (contentType.equals("application/vnd.openxmlformats-package.core-properties+xml") && this.packageProperties != null) {
            throw new InvalidOperationException("OPC Compliance error [M4.1]: you try to add more than one core properties relationship in the package !");
        }
        PackagePart part = this.createPartImpl(partName, contentType, loadRelationships);
        try {
            PackagePartName ppn = PackagingURIHelper.createPartName("/.xml");
            this.contentTypeManager.addContentType(ppn, "application/xml");
            ppn = PackagingURIHelper.createPartName("/.rels");
            this.contentTypeManager.addContentType(ppn, "application/vnd.openxmlformats-package.relationships+xml");
        } catch (InvalidFormatException e) {
            throw new InvalidOperationException("unable to create default content-type entries.", e);
        }
        this.contentTypeManager.addContentType(partName, contentType);
        this.partList.put(partName, part);
        this.isDirty = true;
        return part;
    }

    public PackagePart createPart(PackagePartName partName, String contentType, ByteArrayOutputStream content) {
        PackagePart addedPart = this.createPart(partName, contentType);
        if (addedPart == null) {
            return null;
        }
        if (content != null) {
            try {
                OutputStream partOutput = addedPart.getOutputStream();
                if (partOutput == null) {
                    return null;
                }
                partOutput.write(content.toByteArray(), 0, content.size());
                partOutput.close();
            } catch (IOException ioe) {
                return null;
            }
        } else {
            return null;
        }
        return addedPart;
    }

    protected PackagePart addPackagePart(PackagePart part) {
        this.throwExceptionIfReadOnly();
        if (part == null) {
            throw new IllegalArgumentException("part");
        }
        if (this.partList.containsKey(part._partName)) {
            if (!this.partList.get(part._partName).isDeleted()) {
                throw new InvalidOperationException("A part with the name '" + part._partName.getName() + "' already exists : Packages shall not contain equivalent part names and package implementers shall neither create nor recognize packages with equivalent part names. [M1.12]");
            }
            part.setDeleted(false);
            this.partList.remove(part._partName);
        }
        this.partList.put(part._partName, part);
        this.isDirty = true;
        return part;
    }

    public void removePart(PackagePart part) {
        if (part != null) {
            this.removePart(part.getPartName());
        }
    }

    public void removePart(PackagePartName partName) {
        this.throwExceptionIfReadOnly();
        if (partName == null || !this.containPart(partName)) {
            throw new IllegalArgumentException("partName");
        }
        if (this.partList.containsKey(partName)) {
            this.partList.get(partName).setDeleted(true);
            this.removePartImpl(partName);
            this.partList.remove(partName);
        } else {
            this.removePartImpl(partName);
        }
        this.contentTypeManager.removeContentType(partName);
        if (partName.isRelationshipPartURI()) {
            PackagePart part;
            PackagePartName sourcePartName;
            URI sourceURI = PackagingURIHelper.getSourcePartUriFromRelationshipPartUri(partName.getURI());
            try {
                sourcePartName = PackagingURIHelper.createPartName(sourceURI);
            } catch (InvalidFormatException e) {
                logger.log(7, "Part name URI '" + sourceURI + "' is not valid ! This message is not intended to be displayed !");
                return;
            }
            if (sourcePartName.getURI().equals(PackagingURIHelper.PACKAGE_ROOT_URI)) {
                this.clearRelationships();
            } else if (this.containPart(sourcePartName) && (part = this.getPart(sourcePartName)) != null) {
                part.clearRelationships();
            }
        }
        this.isDirty = true;
    }

    public void removePartRecursive(PackagePartName partName) throws InvalidFormatException {
        PackagePart relPart = this.partList.get(PackagingURIHelper.getRelationshipPartName(partName));
        PackagePart partToRemove = this.partList.get(partName);
        if (relPart != null) {
            PackageRelationshipCollection partRels = new PackageRelationshipCollection(partToRemove);
            for (PackageRelationship rel : partRels) {
                PackagePartName partNameToRemove = PackagingURIHelper.createPartName(PackagingURIHelper.resolvePartUri(rel.getSourceURI(), rel.getTargetURI()));
                this.removePart(partNameToRemove);
            }
            this.removePart(relPart._partName);
        }
        this.removePart(partToRemove._partName);
    }

    public void deletePart(PackagePartName partName) {
        if (partName == null) {
            throw new IllegalArgumentException("partName");
        }
        this.removePart(partName);
        this.removePart(PackagingURIHelper.getRelationshipPartName(partName));
    }

    public void deletePartRecursive(PackagePartName partName) {
        if (partName == null || !this.containPart(partName)) {
            throw new IllegalArgumentException("partName");
        }
        PackagePart partToDelete = this.getPart(partName);
        this.removePart(partName);
        try {
            for (PackageRelationship relationship : partToDelete.getRelationships()) {
                PackagePartName targetPartName = PackagingURIHelper.createPartName(PackagingURIHelper.resolvePartUri(partName.getURI(), relationship.getTargetURI()));
                this.deletePartRecursive(targetPartName);
            }
        } catch (InvalidFormatException e) {
            logger.log(5, "An exception occurs while deleting part '" + partName.getName() + "'. Some parts may remain in the package. - " + e.getMessage());
            return;
        }
        PackagePartName relationshipPartName = PackagingURIHelper.getRelationshipPartName(partName);
        if (relationshipPartName != null && this.containPart(relationshipPartName)) {
            this.removePart(relationshipPartName);
        }
    }

    public boolean containPart(PackagePartName partName) {
        return this.getPart(partName) != null;
    }

    @Override
    public PackageRelationship addRelationship(PackagePartName targetPartName, TargetMode targetMode, String relationshipType, String relID) {
        if (relationshipType.equals("http://schemas.openxmlformats.org/package/2006/relationships/metadata/core-properties") && this.packageProperties != null) {
            throw new InvalidOperationException("OPC Compliance error [M4.1]: can't add another core properties part ! Use the built-in package method instead.");
        }
        if (targetPartName.isRelationshipPartURI()) {
            throw new InvalidOperationException("Rule M1.25: The Relationships part shall not have relationships to any other part.");
        }
        this.ensureRelationships();
        PackageRelationship retRel = this.relationships.addRelationship(targetPartName.getURI(), targetMode, relationshipType, relID);
        this.isDirty = true;
        return retRel;
    }

    @Override
    public PackageRelationship addRelationship(PackagePartName targetPartName, TargetMode targetMode, String relationshipType) {
        return this.addRelationship(targetPartName, targetMode, relationshipType, null);
    }

    @Override
    public PackageRelationship addExternalRelationship(String target, String relationshipType) {
        return this.addExternalRelationship(target, relationshipType, null);
    }

    @Override
    public PackageRelationship addExternalRelationship(String target, String relationshipType, String id) {
        URI targetURI;
        if (target == null) {
            throw new IllegalArgumentException("target");
        }
        if (relationshipType == null) {
            throw new IllegalArgumentException("relationshipType");
        }
        try {
            targetURI = new URI(target);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid target - " + e);
        }
        this.ensureRelationships();
        PackageRelationship retRel = this.relationships.addRelationship(targetURI, TargetMode.EXTERNAL, relationshipType, id);
        this.isDirty = true;
        return retRel;
    }

    @Override
    public void removeRelationship(String id) {
        if (this.relationships != null) {
            this.relationships.removeRelationship(id);
            this.isDirty = true;
        }
    }

    @Override
    public PackageRelationshipCollection getRelationships() {
        return this.getRelationshipsHelper(null);
    }

    @Override
    public PackageRelationshipCollection getRelationshipsByType(String relationshipType) {
        this.throwExceptionIfWriteOnly();
        if (relationshipType == null) {
            throw new IllegalArgumentException("relationshipType");
        }
        return this.getRelationshipsHelper(relationshipType);
    }

    private PackageRelationshipCollection getRelationshipsHelper(String id) {
        this.throwExceptionIfWriteOnly();
        this.ensureRelationships();
        return this.relationships.getRelationships(id);
    }

    @Override
    public void clearRelationships() {
        if (this.relationships != null) {
            this.relationships.clear();
            this.isDirty = true;
        }
    }

    public void ensureRelationships() {
        if (this.relationships == null) {
            try {
                this.relationships = new PackageRelationshipCollection(this);
            } catch (InvalidFormatException e) {
                this.relationships = new PackageRelationshipCollection();
            }
        }
    }

    @Override
    public PackageRelationship getRelationship(String id) {
        return this.relationships.getRelationshipByID(id);
    }

    @Override
    public boolean hasRelationships() {
        return this.relationships.size() > 0;
    }

    @Override
    public boolean isRelationshipExists(PackageRelationship rel) {
        for (PackageRelationship r : this.relationships) {
            if (r != rel) continue;
            return true;
        }
        return false;
    }

    public void addMarshaller(String contentType, PartMarshaller marshaller) {
        try {
            this.partMarshallers.put(new ContentType(contentType), marshaller);
        } catch (InvalidFormatException e) {
            logger.log(5, "The specified content type is not valid: '" + e.getMessage() + "'. The marshaller will not be added !");
        }
    }

    public void addUnmarshaller(String contentType, PartUnmarshaller unmarshaller) {
        try {
            this.partUnmarshallers.put(new ContentType(contentType), unmarshaller);
        } catch (InvalidFormatException e) {
            logger.log(5, "The specified content type is not valid: '" + e.getMessage() + "'. The unmarshaller will not be added !");
        }
    }

    public void removeMarshaller(String contentType) {
        try {
            this.partMarshallers.remove(new ContentType(contentType));
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUnmarshaller(String contentType) {
        try {
            this.partUnmarshallers.remove(new ContentType(contentType));
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public PackageAccess getPackageAccess() {
        return this.packageAccess;
    }

    @NotImplemented
    public boolean validatePackage(OPCPackage pkg) throws InvalidFormatException {
        throw new InvalidOperationException("Not implemented yet !!!");
    }

    public void save(File targetFile) throws IOException {
        if (targetFile == null) {
            throw new IllegalArgumentException("targetFile");
        }
        this.throwExceptionIfReadOnly();
        if (targetFile.exists() && targetFile.getAbsolutePath().equals(this.originalPackagePath)) {
            throw new InvalidOperationException("You can't call save(File) to save to the currently open file. To save to the current file, please just call close()");
        }
        try (FileOutputStream fos = new FileOutputStream(targetFile);){
            this.save(fos);
        }
    }

    public void save(OutputStream outputStream) throws IOException {
        this.throwExceptionIfReadOnly();
        this.saveImpl(outputStream);
    }

    protected abstract PackagePart createPartImpl(PackagePartName var1, String var2, boolean var3);

    protected abstract void removePartImpl(PackagePartName var1);

    protected abstract void flushImpl();

    protected abstract void closeImpl() throws IOException;

    protected abstract void revertImpl();

    protected abstract void saveImpl(OutputStream var1) throws IOException;

    protected abstract PackagePartCollection getPartsImpl() throws InvalidFormatException;

    public boolean replaceContentType(String oldContentType, String newContentType) {
        boolean success = false;
        ArrayList<PackagePart> list = this.getPartsByContentType(oldContentType);
        for (PackagePart packagePart : list) {
            if (!packagePart.getContentType().equals(oldContentType)) continue;
            PackagePartName partName = packagePart.getPartName();
            this.contentTypeManager.addContentType(partName, newContentType);
            try {
                packagePart.setContentType(newContentType);
            } catch (InvalidFormatException e) {
                throw new OpenXML4JRuntimeException("invalid content type - " + newContentType, e);
            }
            success = true;
            this.isDirty = true;
        }
        return success;
    }

    public void registerPartAndContentType(PackagePart part) {
        this.addPackagePart(part);
        this.contentTypeManager.addContentType(part.getPartName(), part.getContentType());
        this.isDirty = true;
    }

    public void unregisterPartAndContentType(PackagePartName partName) {
        this.removePart(partName);
        this.contentTypeManager.removeContentType(partName);
        this.isDirty = true;
    }

    public int getUnusedPartIndex(String nameTemplate) throws InvalidFormatException {
        return this.partList.getUnusedPartIndex(nameTemplate);
    }
}

