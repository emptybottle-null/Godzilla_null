/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hpsf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.poi.EmptyFileException;
import org.apache.poi.hpsf.ClassID;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.HPSFRuntimeException;
import org.apache.poi.hpsf.MissingSectionException;
import org.apache.poi.hpsf.NoFormatIDException;
import org.apache.poi.hpsf.NoPropertySetStreamException;
import org.apache.poi.hpsf.NoSingleSectionException;
import org.apache.poi.hpsf.Property;
import org.apache.poi.hpsf.Section;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hpsf.WritingNotSupportedException;
import org.apache.poi.hpsf.wellknown.PropertyIDMap;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.Entry;
import org.apache.poi.util.CodePageUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LittleEndianByteArrayInputStream;
import org.apache.poi.util.LittleEndianOutputStream;
import org.apache.poi.util.NotImplemented;
import org.apache.poi.util.Removal;

public class PropertySet {
    public static final int OS_WIN16 = 0;
    public static final int OS_MACINTOSH = 1;
    public static final int OS_WIN32 = 2;
    static final int BYTE_ORDER_ASSERTION = 65534;
    static final int FORMAT_ASSERTION = 0;
    static final int OFFSET_HEADER = 28;
    private int byteOrder;
    private int format;
    private int osVersion;
    private ClassID classID;
    private final List<Section> sections = new ArrayList<Section>();

    public PropertySet() {
        this.byteOrder = 65534;
        this.format = 0;
        this.osVersion = 133636;
        this.classID = new ClassID();
        this.addSection(new Section());
    }

    public PropertySet(InputStream stream) throws NoPropertySetStreamException, IOException {
        if (!PropertySet.isPropertySetStream(stream)) {
            throw new NoPropertySetStreamException();
        }
        byte[] buffer = IOUtils.toByteArray(stream);
        this.init(buffer, 0, buffer.length);
    }

    public PropertySet(byte[] stream, int offset, int length) throws NoPropertySetStreamException, UnsupportedEncodingException {
        if (!PropertySet.isPropertySetStream(stream, offset, length)) {
            throw new NoPropertySetStreamException();
        }
        this.init(stream, offset, length);
    }

    public PropertySet(byte[] stream) throws NoPropertySetStreamException, UnsupportedEncodingException {
        this(stream, 0, stream.length);
    }

    public PropertySet(PropertySet ps) {
        this.setByteOrder(ps.getByteOrder());
        this.setFormat(ps.getFormat());
        this.setOSVersion(ps.getOSVersion());
        this.setClassID(ps.getClassID());
        for (Section section : ps.getSections()) {
            this.sections.add(new Section(section));
        }
    }

    public int getByteOrder() {
        return this.byteOrder;
    }

    public void setByteOrder(int byteOrder) {
        this.byteOrder = byteOrder;
    }

    public int getFormat() {
        return this.format;
    }

    public void setFormat(int format) {
        this.format = format;
    }

    public int getOSVersion() {
        return this.osVersion;
    }

    public void setOSVersion(int osVersion) {
        this.osVersion = osVersion;
    }

    public ClassID getClassID() {
        return this.classID;
    }

    public void setClassID(ClassID classID) {
        this.classID = classID;
    }

    public int getSectionCount() {
        return this.sections.size();
    }

    public List<Section> getSections() {
        return Collections.unmodifiableList(this.sections);
    }

    public void addSection(Section section) {
        this.sections.add(section);
    }

    public void clearSections() {
        this.sections.clear();
    }

    public PropertyIDMap getPropertySetIDMap() {
        return null;
    }

    public static boolean isPropertySetStream(InputStream stream) throws IOException {
        int BUFFER_SIZE = 50;
        try {
            byte[] buffer = IOUtils.peekFirstNBytes(stream, 50);
            return PropertySet.isPropertySetStream(buffer, 0, buffer.length);
        } catch (EmptyFileException e) {
            return false;
        }
    }

    public static boolean isPropertySetStream(byte[] src, int offset, int length) {
        LittleEndianByteArrayInputStream leis = new LittleEndianByteArrayInputStream(src, offset, length);
        try {
            int byteOrder = leis.readUShort();
            if (byteOrder != 65534) {
                return false;
            }
            int format = leis.readUShort();
            if (format != 0) {
                return false;
            }
            long osVersion = leis.readUInt();
            if (leis.skip(16L) != 16L) {
                return false;
            }
            long sectionCount = leis.readUInt();
            return sectionCount >= 0L;
        } catch (RuntimeException e) {
            return false;
        }
    }

    private void init(byte[] src, int offset, int length) throws UnsupportedEncodingException {
        int o = offset;
        this.byteOrder = LittleEndian.getUShort(src, o);
        this.format = LittleEndian.getUShort(src, o += 2);
        this.osVersion = (int)LittleEndian.getUInt(src, o += 2);
        this.classID = new ClassID(src, o += 4);
        int sectionCount = LittleEndian.getInt(src, o += 16);
        o += 4;
        if (sectionCount < 0) {
            throw new HPSFRuntimeException("Section count " + sectionCount + " is negative.");
        }
        for (int i = 0; i < sectionCount; ++i) {
            Section s = new Section(src, o);
            o += 20;
            this.sections.add(s);
        }
    }

    public void write(OutputStream out) throws IOException, WritingNotSupportedException {
        out.write(this.toBytes());
        out.close();
    }

    private byte[] toBytes() throws WritingNotSupportedException, IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        LittleEndianOutputStream leos = new LittleEndianOutputStream(bos);
        int nrSections = this.getSectionCount();
        leos.writeShort(this.getByteOrder());
        leos.writeShort(this.getFormat());
        leos.writeInt(this.getOSVersion());
        PropertySet.putClassId(bos, this.getClassID());
        leos.writeInt(nrSections);
        assert (bos.size() == 28);
        int[][] offsets = new int[this.getSectionCount()][2];
        int secCnt = 0;
        for (Section section : this.getSections()) {
            ClassID formatID = section.getFormatID();
            if (formatID == null) {
                throw new NoFormatIDException();
            }
            PropertySet.putClassId(bos, formatID);
            offsets[secCnt++][0] = bos.size();
            leos.writeInt(-1);
        }
        secCnt = 0;
        for (Section section : this.getSections()) {
            offsets[secCnt++][1] = bos.size();
            section.write(bos);
        }
        byte[] result = bos.toByteArray();
        for (int[] off : offsets) {
            LittleEndian.putInt(result, off[0], off[1]);
        }
        return result;
    }

    public void write(DirectoryEntry dir, String name) throws WritingNotSupportedException, IOException {
        if (dir.hasEntry(name)) {
            Entry e = dir.getEntry(name);
            e.delete();
        }
        dir.createDocument(name, this.toInputStream());
    }

    public InputStream toInputStream() throws WritingNotSupportedException, IOException {
        return new ByteArrayInputStream(this.toBytes());
    }

    String getPropertyStringValue(int propertyId) {
        Object propertyValue = this.getProperty(propertyId);
        return PropertySet.getPropertyStringValue(propertyValue);
    }

    public static String getPropertyStringValue(Object propertyValue) {
        if (propertyValue == null) {
            return null;
        }
        if (propertyValue instanceof String) {
            return (String)propertyValue;
        }
        if (propertyValue instanceof byte[]) {
            byte[] b = (byte[])propertyValue;
            switch (b.length) {
                case 0: {
                    return "";
                }
                case 1: {
                    return Byte.toString(b[0]);
                }
                case 2: {
                    return Integer.toString(LittleEndian.getUShort(b));
                }
                case 4: {
                    return Long.toString(LittleEndian.getUInt(b));
                }
            }
            try {
                return CodePageUtil.getStringFromCodePage(b, 1252);
            } catch (UnsupportedEncodingException e) {
                return "";
            }
        }
        return propertyValue.toString();
    }

    public boolean isSummaryInformation() {
        return !this.sections.isEmpty() && PropertySet.matchesSummary(this.getFirstSection().getFormatID(), SummaryInformation.FORMAT_ID);
    }

    public boolean isDocumentSummaryInformation() {
        return !this.sections.isEmpty() && PropertySet.matchesSummary(this.getFirstSection().getFormatID(), DocumentSummaryInformation.FORMAT_ID);
    }

    static boolean matchesSummary(ClassID actual, ClassID ... expected) {
        for (ClassID sum : expected) {
            if (!sum.equals(actual) && !sum.equalsInverted(actual)) continue;
            return true;
        }
        return false;
    }

    public Property[] getProperties() throws NoSingleSectionException {
        return this.getFirstSection().getProperties();
    }

    protected Object getProperty(int id) throws NoSingleSectionException {
        return this.getFirstSection().getProperty(id);
    }

    boolean getPropertyBooleanValue(int id) throws NoSingleSectionException {
        return this.getFirstSection().getPropertyBooleanValue(id);
    }

    int getPropertyIntValue(int id) throws NoSingleSectionException {
        return this.getFirstSection().getPropertyIntValue(id);
    }

    public boolean wasNull() throws NoSingleSectionException {
        return this.getFirstSection().wasNull();
    }

    public Section getFirstSection() {
        if (this.sections.isEmpty()) {
            throw new MissingSectionException("Property set does not contain any sections.");
        }
        return this.sections.get(0);
    }

    @Deprecated
    @Removal(version="5.0.0")
    public Section getSingleSection() {
        int sectionCount = this.getSectionCount();
        if (sectionCount != 1) {
            throw new NoSingleSectionException("Property set contains " + sectionCount + " sections.");
        }
        return this.sections.get(0);
    }

    public boolean equals(Object o) {
        if (!(o instanceof PropertySet)) {
            return false;
        }
        PropertySet ps = (PropertySet)o;
        int byteOrder1 = ps.getByteOrder();
        int byteOrder2 = this.getByteOrder();
        ClassID classID1 = ps.getClassID();
        ClassID classID2 = this.getClassID();
        int format1 = ps.getFormat();
        int format2 = this.getFormat();
        int osVersion1 = ps.getOSVersion();
        int osVersion2 = this.getOSVersion();
        int sectionCount1 = ps.getSectionCount();
        int sectionCount2 = this.getSectionCount();
        if (byteOrder1 != byteOrder2 || !classID1.equals(classID2) || format1 != format2 || osVersion1 != osVersion2 || sectionCount1 != sectionCount2) {
            return false;
        }
        return this.getSections().containsAll(ps.getSections());
    }

    @NotImplemented
    public int hashCode() {
        throw new UnsupportedOperationException("FIXME: Not yet implemented.");
    }

    public String toString() {
        StringBuilder b = new StringBuilder();
        int sectionCount = this.getSectionCount();
        b.append(this.getClass().getName());
        b.append('[');
        b.append("byteOrder: ");
        b.append(this.getByteOrder());
        b.append(", classID: ");
        b.append(this.getClassID());
        b.append(", format: ");
        b.append(this.getFormat());
        b.append(", OSVersion: ");
        b.append(this.getOSVersion());
        b.append(", sectionCount: ");
        b.append(sectionCount);
        b.append(", sections: [\n");
        for (Section section : this.getSections()) {
            b.append(section.toString(this.getPropertySetIDMap()));
        }
        b.append(']');
        b.append(']');
        return b.toString();
    }

    void remove1stProperty(long id) {
        this.getFirstSection().removeProperty(id);
    }

    void set1stProperty(long id, String value) {
        this.getFirstSection().setProperty((int)id, value);
    }

    void set1stProperty(long id, int value) {
        this.getFirstSection().setProperty((int)id, value);
    }

    void set1stProperty(long id, boolean value) {
        this.getFirstSection().setProperty((int)id, value);
    }

    void set1stProperty(long id, byte[] value) {
        this.getFirstSection().setProperty((int)id, value);
    }

    private static void putClassId(ByteArrayOutputStream out, ClassID n) {
        byte[] b = new byte[16];
        n.write(b, 0);
        out.write(b, 0, b.length);
    }
}

