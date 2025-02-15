/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.zip;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.EntryStreamOffsets;
import org.apache.commons.compress.archivers.zip.ExtraFieldParsingBehavior;
import org.apache.commons.compress.archivers.zip.ExtraFieldUtils;
import org.apache.commons.compress.archivers.zip.GeneralPurposeBit;
import org.apache.commons.compress.archivers.zip.UnparseableExtraFieldData;
import org.apache.commons.compress.archivers.zip.UnrecognizedExtraField;
import org.apache.commons.compress.archivers.zip.ZipExtraField;
import org.apache.commons.compress.archivers.zip.ZipShort;

public class ZipArchiveEntry
extends ZipEntry
implements ArchiveEntry,
EntryStreamOffsets {
    public static final int PLATFORM_UNIX = 3;
    public static final int PLATFORM_FAT = 0;
    public static final int CRC_UNKNOWN = -1;
    private static final int SHORT_MASK = 65535;
    private static final int SHORT_SHIFT = 16;
    private static final byte[] EMPTY = new byte[0];
    private int method = -1;
    private long size = -1L;
    private int internalAttributes = 0;
    private int versionRequired;
    private int versionMadeBy;
    private int platform = 0;
    private int rawFlag;
    private long externalAttributes = 0L;
    private int alignment = 0;
    private ZipExtraField[] extraFields;
    private UnparseableExtraFieldData unparseableExtra = null;
    private String name = null;
    private byte[] rawName = null;
    private GeneralPurposeBit gpb = new GeneralPurposeBit();
    private static final ZipExtraField[] noExtraFields = new ZipExtraField[0];
    private long localHeaderOffset = -1L;
    private long dataOffset = -1L;
    private boolean isStreamContiguous = false;
    private NameSource nameSource = NameSource.NAME;
    private CommentSource commentSource = CommentSource.COMMENT;

    public ZipArchiveEntry(String name) {
        super(name);
        this.setName(name);
    }

    public ZipArchiveEntry(ZipEntry entry) throws ZipException {
        super(entry);
        this.setName(entry.getName());
        byte[] extra = entry.getExtra();
        if (extra != null) {
            this.setExtraFields(ExtraFieldUtils.parse(extra, true, ExtraFieldParsingMode.BEST_EFFORT));
        } else {
            this.setExtra();
        }
        this.setMethod(entry.getMethod());
        this.size = entry.getSize();
    }

    public ZipArchiveEntry(ZipArchiveEntry entry) throws ZipException {
        this((ZipEntry)entry);
        this.setInternalAttributes(entry.getInternalAttributes());
        this.setExternalAttributes(entry.getExternalAttributes());
        this.setExtraFields(this.getAllExtraFieldsNoCopy());
        this.setPlatform(entry.getPlatform());
        GeneralPurposeBit other = entry.getGeneralPurposeBit();
        this.setGeneralPurposeBit(other == null ? null : (GeneralPurposeBit)other.clone());
    }

    protected ZipArchiveEntry() {
        this("");
    }

    public ZipArchiveEntry(File inputFile, String entryName) {
        this(inputFile.isDirectory() && !entryName.endsWith("/") ? entryName + "/" : entryName);
        if (inputFile.isFile()) {
            this.setSize(inputFile.length());
        }
        this.setTime(inputFile.lastModified());
    }

    @Override
    public Object clone() {
        ZipArchiveEntry e = (ZipArchiveEntry)super.clone();
        e.setInternalAttributes(this.getInternalAttributes());
        e.setExternalAttributes(this.getExternalAttributes());
        e.setExtraFields(this.getAllExtraFieldsNoCopy());
        return e;
    }

    @Override
    public int getMethod() {
        return this.method;
    }

    @Override
    public void setMethod(int method) {
        if (method < 0) {
            throw new IllegalArgumentException("ZIP compression method can not be negative: " + method);
        }
        this.method = method;
    }

    public int getInternalAttributes() {
        return this.internalAttributes;
    }

    public void setInternalAttributes(int value) {
        this.internalAttributes = value;
    }

    public long getExternalAttributes() {
        return this.externalAttributes;
    }

    public void setExternalAttributes(long value) {
        this.externalAttributes = value;
    }

    public void setUnixMode(int mode) {
        this.setExternalAttributes(mode << 16 | ((mode & 0x80) == 0 ? 1 : 0) | (this.isDirectory() ? 16 : 0));
        this.platform = 3;
    }

    public int getUnixMode() {
        return this.platform != 3 ? 0 : (int)(this.getExternalAttributes() >> 16 & 0xFFFFL);
    }

    public boolean isUnixSymlink() {
        return (this.getUnixMode() & 0xF000) == 40960;
    }

    public int getPlatform() {
        return this.platform;
    }

    protected void setPlatform(int platform) {
        this.platform = platform;
    }

    protected int getAlignment() {
        return this.alignment;
    }

    public void setAlignment(int alignment) {
        if ((alignment & alignment - 1) != 0 || alignment > 65535) {
            throw new IllegalArgumentException("Invalid value for alignment, must be power of two and no bigger than 65535 but is " + alignment);
        }
        this.alignment = alignment;
    }

    public void setExtraFields(ZipExtraField[] fields) {
        this.unparseableExtra = null;
        ArrayList<ZipExtraField> newFields = new ArrayList<ZipExtraField>();
        if (fields != null) {
            for (ZipExtraField field : fields) {
                if (field instanceof UnparseableExtraFieldData) {
                    this.unparseableExtra = (UnparseableExtraFieldData)field;
                    continue;
                }
                newFields.add(field);
            }
        }
        this.extraFields = newFields.toArray(noExtraFields);
        this.setExtra();
    }

    public ZipExtraField[] getExtraFields() {
        return this.getParseableExtraFields();
    }

    public ZipExtraField[] getExtraFields(boolean includeUnparseable) {
        return includeUnparseable ? this.getAllExtraFields() : this.getParseableExtraFields();
    }

    public ZipExtraField[] getExtraFields(ExtraFieldParsingBehavior parsingBehavior) throws ZipException {
        if (parsingBehavior == ExtraFieldParsingMode.BEST_EFFORT) {
            return this.getExtraFields(true);
        }
        if (parsingBehavior == ExtraFieldParsingMode.ONLY_PARSEABLE_LENIENT) {
            return this.getExtraFields(false);
        }
        byte[] local = this.getExtra();
        ArrayList<ZipExtraField> localFields = new ArrayList<ZipExtraField>(Arrays.asList(ExtraFieldUtils.parse(local, true, parsingBehavior)));
        byte[] central = this.getCentralDirectoryExtra();
        ArrayList<ZipExtraField> centralFields = new ArrayList<ZipExtraField>(Arrays.asList(ExtraFieldUtils.parse(central, false, parsingBehavior)));
        ArrayList<ZipExtraField> merged = new ArrayList<ZipExtraField>();
        for (ZipExtraField l : localFields) {
            ZipExtraField c = null;
            c = l instanceof UnparseableExtraFieldData ? this.findUnparseable(centralFields) : this.findMatching(l.getHeaderId(), centralFields);
            if (c != null) {
                byte[] cd = c.getCentralDirectoryData();
                if (cd != null && cd.length > 0) {
                    l.parseFromCentralDirectoryData(cd, 0, cd.length);
                }
                centralFields.remove(c);
            }
            merged.add(l);
        }
        merged.addAll(centralFields);
        return merged.toArray(noExtraFields);
    }

    private ZipExtraField[] getParseableExtraFieldsNoCopy() {
        if (this.extraFields == null) {
            return noExtraFields;
        }
        return this.extraFields;
    }

    private ZipExtraField[] getParseableExtraFields() {
        ZipExtraField[] parseableExtraFields = this.getParseableExtraFieldsNoCopy();
        return parseableExtraFields == this.extraFields ? Arrays.copyOf(parseableExtraFields, parseableExtraFields.length) : parseableExtraFields;
    }

    private ZipExtraField[] getAllExtraFieldsNoCopy() {
        if (this.extraFields == null) {
            return this.getUnparseableOnly();
        }
        return this.unparseableExtra != null ? this.getMergedFields() : this.extraFields;
    }

    private ZipExtraField[] getMergedFields() {
        ZipExtraField[] zipExtraFields = Arrays.copyOf(this.extraFields, this.extraFields.length + 1);
        zipExtraFields[this.extraFields.length] = this.unparseableExtra;
        return zipExtraFields;
    }

    private ZipExtraField[] getUnparseableOnly() {
        ZipExtraField[] zipExtraFieldArray;
        if (this.unparseableExtra == null) {
            zipExtraFieldArray = noExtraFields;
        } else {
            ZipExtraField[] zipExtraFieldArray2 = new ZipExtraField[1];
            zipExtraFieldArray = zipExtraFieldArray2;
            zipExtraFieldArray2[0] = this.unparseableExtra;
        }
        return zipExtraFieldArray;
    }

    private ZipExtraField[] getAllExtraFields() {
        ZipExtraField[] allExtraFieldsNoCopy = this.getAllExtraFieldsNoCopy();
        return allExtraFieldsNoCopy == this.extraFields ? Arrays.copyOf(allExtraFieldsNoCopy, allExtraFieldsNoCopy.length) : allExtraFieldsNoCopy;
    }

    private ZipExtraField findUnparseable(List<ZipExtraField> fs) {
        for (ZipExtraField f : fs) {
            if (!(f instanceof UnparseableExtraFieldData)) continue;
            return f;
        }
        return null;
    }

    private ZipExtraField findMatching(ZipShort headerId, List<ZipExtraField> fs) {
        for (ZipExtraField f : fs) {
            if (!headerId.equals(f.getHeaderId())) continue;
            return f;
        }
        return null;
    }

    public void addExtraField(ZipExtraField ze) {
        if (ze instanceof UnparseableExtraFieldData) {
            this.unparseableExtra = (UnparseableExtraFieldData)ze;
        } else if (this.extraFields == null) {
            this.extraFields = new ZipExtraField[]{ze};
        } else {
            if (this.getExtraField(ze.getHeaderId()) != null) {
                this.removeExtraField(ze.getHeaderId());
            }
            ZipExtraField[] zipExtraFields = Arrays.copyOf(this.extraFields, this.extraFields.length + 1);
            zipExtraFields[zipExtraFields.length - 1] = ze;
            this.extraFields = zipExtraFields;
        }
        this.setExtra();
    }

    public void addAsFirstExtraField(ZipExtraField ze) {
        if (ze instanceof UnparseableExtraFieldData) {
            this.unparseableExtra = (UnparseableExtraFieldData)ze;
        } else {
            if (this.getExtraField(ze.getHeaderId()) != null) {
                this.removeExtraField(ze.getHeaderId());
            }
            ZipExtraField[] copy = this.extraFields;
            int newLen = this.extraFields != null ? this.extraFields.length + 1 : 1;
            this.extraFields = new ZipExtraField[newLen];
            this.extraFields[0] = ze;
            if (copy != null) {
                System.arraycopy(copy, 0, this.extraFields, 1, this.extraFields.length - 1);
            }
        }
        this.setExtra();
    }

    public void removeExtraField(ZipShort type) {
        if (this.extraFields == null) {
            throw new NoSuchElementException();
        }
        ArrayList<ZipExtraField> newResult = new ArrayList<ZipExtraField>();
        for (ZipExtraField extraField : this.extraFields) {
            if (type.equals(extraField.getHeaderId())) continue;
            newResult.add(extraField);
        }
        if (this.extraFields.length == newResult.size()) {
            throw new NoSuchElementException();
        }
        this.extraFields = newResult.toArray(noExtraFields);
        this.setExtra();
    }

    public void removeUnparseableExtraFieldData() {
        if (this.unparseableExtra == null) {
            throw new NoSuchElementException();
        }
        this.unparseableExtra = null;
        this.setExtra();
    }

    public ZipExtraField getExtraField(ZipShort type) {
        if (this.extraFields != null) {
            for (ZipExtraField extraField : this.extraFields) {
                if (!type.equals(extraField.getHeaderId())) continue;
                return extraField;
            }
        }
        return null;
    }

    public UnparseableExtraFieldData getUnparseableExtraFieldData() {
        return this.unparseableExtra;
    }

    @Override
    public void setExtra(byte[] extra) throws RuntimeException {
        try {
            ZipExtraField[] local = ExtraFieldUtils.parse(extra, true, ExtraFieldParsingMode.BEST_EFFORT);
            this.mergeExtraFields(local, true);
        } catch (ZipException e) {
            throw new RuntimeException("Error parsing extra fields for entry: " + this.getName() + " - " + e.getMessage(), e);
        }
    }

    protected void setExtra() {
        super.setExtra(ExtraFieldUtils.mergeLocalFileDataData(this.getAllExtraFieldsNoCopy()));
    }

    public void setCentralDirectoryExtra(byte[] b) {
        try {
            ZipExtraField[] central = ExtraFieldUtils.parse(b, false, ExtraFieldParsingMode.BEST_EFFORT);
            this.mergeExtraFields(central, false);
        } catch (ZipException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public byte[] getLocalFileDataExtra() {
        byte[] extra = this.getExtra();
        return extra != null ? extra : EMPTY;
    }

    public byte[] getCentralDirectoryExtra() {
        return ExtraFieldUtils.mergeCentralDirectoryData(this.getAllExtraFieldsNoCopy());
    }

    @Override
    public String getName() {
        return this.name == null ? super.getName() : this.name;
    }

    @Override
    public boolean isDirectory() {
        String n = this.getName();
        return n != null && n.endsWith("/");
    }

    protected void setName(String name) {
        if (name != null && this.getPlatform() == 0 && !name.contains("/")) {
            name = name.replace('\\', '/');
        }
        this.name = name;
    }

    @Override
    public long getSize() {
        return this.size;
    }

    @Override
    public void setSize(long size) {
        if (size < 0L) {
            throw new IllegalArgumentException("Invalid entry size");
        }
        this.size = size;
    }

    protected void setName(String name, byte[] rawName) {
        this.setName(name);
        this.rawName = rawName;
    }

    public byte[] getRawName() {
        if (this.rawName != null) {
            return Arrays.copyOf(this.rawName, this.rawName.length);
        }
        return null;
    }

    protected long getLocalHeaderOffset() {
        return this.localHeaderOffset;
    }

    protected void setLocalHeaderOffset(long localHeaderOffset) {
        this.localHeaderOffset = localHeaderOffset;
    }

    @Override
    public long getDataOffset() {
        return this.dataOffset;
    }

    protected void setDataOffset(long dataOffset) {
        this.dataOffset = dataOffset;
    }

    @Override
    public boolean isStreamContiguous() {
        return this.isStreamContiguous;
    }

    protected void setStreamContiguous(boolean isStreamContiguous) {
        this.isStreamContiguous = isStreamContiguous;
    }

    @Override
    public int hashCode() {
        String n = this.getName();
        return (n == null ? "" : n).hashCode();
    }

    public GeneralPurposeBit getGeneralPurposeBit() {
        return this.gpb;
    }

    public void setGeneralPurposeBit(GeneralPurposeBit b) {
        this.gpb = b;
    }

    private void mergeExtraFields(ZipExtraField[] f, boolean local) {
        if (this.extraFields == null) {
            this.setExtraFields(f);
        } else {
            for (ZipExtraField element : f) {
                ZipExtraField existing = element instanceof UnparseableExtraFieldData ? this.unparseableExtra : this.getExtraField(element.getHeaderId());
                if (existing == null) {
                    this.addExtraField(element);
                    continue;
                }
                byte[] b = local ? element.getLocalFileDataData() : element.getCentralDirectoryData();
                try {
                    if (local) {
                        existing.parseFromLocalFileData(b, 0, b.length);
                        continue;
                    }
                    existing.parseFromCentralDirectoryData(b, 0, b.length);
                } catch (ZipException ex) {
                    UnrecognizedExtraField u = new UnrecognizedExtraField();
                    u.setHeaderId(existing.getHeaderId());
                    if (local) {
                        u.setLocalFileDataData(b);
                        u.setCentralDirectoryData(existing.getCentralDirectoryData());
                    } else {
                        u.setLocalFileDataData(existing.getLocalFileDataData());
                        u.setCentralDirectoryData(b);
                    }
                    this.removeExtraField(existing.getHeaderId());
                    this.addExtraField(u);
                }
            }
            this.setExtra();
        }
    }

    @Override
    public Date getLastModifiedDate() {
        return new Date(this.getTime());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        ZipArchiveEntry other = (ZipArchiveEntry)obj;
        String myName = this.getName();
        String otherName = other.getName();
        if (myName == null ? otherName != null : !myName.equals(otherName)) {
            return false;
        }
        String myComment = this.getComment();
        String otherComment = other.getComment();
        if (myComment == null) {
            myComment = "";
        }
        if (otherComment == null) {
            otherComment = "";
        }
        return this.getTime() == other.getTime() && myComment.equals(otherComment) && this.getInternalAttributes() == other.getInternalAttributes() && this.getPlatform() == other.getPlatform() && this.getExternalAttributes() == other.getExternalAttributes() && this.getMethod() == other.getMethod() && this.getSize() == other.getSize() && this.getCrc() == other.getCrc() && this.getCompressedSize() == other.getCompressedSize() && Arrays.equals(this.getCentralDirectoryExtra(), other.getCentralDirectoryExtra()) && Arrays.equals(this.getLocalFileDataExtra(), other.getLocalFileDataExtra()) && this.localHeaderOffset == other.localHeaderOffset && this.dataOffset == other.dataOffset && this.gpb.equals(other.gpb);
    }

    public void setVersionMadeBy(int versionMadeBy) {
        this.versionMadeBy = versionMadeBy;
    }

    public void setVersionRequired(int versionRequired) {
        this.versionRequired = versionRequired;
    }

    public int getVersionRequired() {
        return this.versionRequired;
    }

    public int getVersionMadeBy() {
        return this.versionMadeBy;
    }

    public int getRawFlag() {
        return this.rawFlag;
    }

    public void setRawFlag(int rawFlag) {
        this.rawFlag = rawFlag;
    }

    public NameSource getNameSource() {
        return this.nameSource;
    }

    public void setNameSource(NameSource nameSource) {
        this.nameSource = nameSource;
    }

    public CommentSource getCommentSource() {
        return this.commentSource;
    }

    public void setCommentSource(CommentSource commentSource) {
        this.commentSource = commentSource;
    }

    public static enum ExtraFieldParsingMode implements ExtraFieldParsingBehavior
    {
        BEST_EFFORT(ExtraFieldUtils.UnparseableExtraField.READ){

            @Override
            public ZipExtraField fill(ZipExtraField field, byte[] data, int off, int len, boolean local) {
                return ExtraFieldParsingMode.fillAndMakeUnrecognizedOnError(field, data, off, len, local);
            }
        }
        ,
        STRICT_FOR_KNOW_EXTRA_FIELDS(ExtraFieldUtils.UnparseableExtraField.READ),
        ONLY_PARSEABLE_LENIENT(ExtraFieldUtils.UnparseableExtraField.SKIP){

            @Override
            public ZipExtraField fill(ZipExtraField field, byte[] data, int off, int len, boolean local) {
                return ExtraFieldParsingMode.fillAndMakeUnrecognizedOnError(field, data, off, len, local);
            }
        }
        ,
        ONLY_PARSEABLE_STRICT(ExtraFieldUtils.UnparseableExtraField.SKIP),
        DRACONIC(ExtraFieldUtils.UnparseableExtraField.THROW);

        private final ExtraFieldUtils.UnparseableExtraField onUnparseableData;

        private ExtraFieldParsingMode(ExtraFieldUtils.UnparseableExtraField onUnparseableData) {
            this.onUnparseableData = onUnparseableData;
        }

        @Override
        public ZipExtraField onUnparseableExtraField(byte[] data, int off, int len, boolean local, int claimedLength) throws ZipException {
            return this.onUnparseableData.onUnparseableExtraField(data, off, len, local, claimedLength);
        }

        @Override
        public ZipExtraField createExtraField(ZipShort headerId) throws ZipException, InstantiationException, IllegalAccessException {
            return ExtraFieldUtils.createExtraField(headerId);
        }

        @Override
        public ZipExtraField fill(ZipExtraField field, byte[] data, int off, int len, boolean local) throws ZipException {
            return ExtraFieldUtils.fillExtraField(field, data, off, len, local);
        }

        private static ZipExtraField fillAndMakeUnrecognizedOnError(ZipExtraField field, byte[] data, int off, int len, boolean local) {
            try {
                return ExtraFieldUtils.fillExtraField(field, data, off, len, local);
            } catch (ZipException ex) {
                UnrecognizedExtraField u = new UnrecognizedExtraField();
                u.setHeaderId(field.getHeaderId());
                if (local) {
                    u.setLocalFileDataData(Arrays.copyOfRange(data, off, off + len));
                } else {
                    u.setCentralDirectoryData(Arrays.copyOfRange(data, off, off + len));
                }
                return u;
            }
        }
    }

    public static enum CommentSource {
        COMMENT,
        UNICODE_EXTRA_FIELD;

    }

    public static enum NameSource {
        NAME,
        NAME_WITH_EFS_FLAG,
        UNICODE_EXTRA_FIELD;

    }
}

