/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hpsf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;
import org.apache.poi.hpsf.ClassID;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.HPSFRuntimeException;
import org.apache.poi.hpsf.IllegalPropertySetDataException;
import org.apache.poi.hpsf.Property;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hpsf.WritingNotSupportedException;
import org.apache.poi.hpsf.wellknown.PropertyIDMap;
import org.apache.poi.util.CodePageUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LittleEndianByteArrayInputStream;
import org.apache.poi.util.LittleEndianOutputStream;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;

public class Section {
    private static final int MAX_RECORD_LENGTH = 100000;
    private static final POILogger LOG = POILogFactory.getLogger(Section.class);
    private Map<Long, String> dictionary;
    private ClassID formatID;
    private final ByteArrayOutputStream sectionBytes = new ByteArrayOutputStream();
    private final long _offset;
    private final Map<Long, Property> properties = new LinkedHashMap<Long, Property>();
    private transient boolean wasNull;

    public Section() {
        this._offset = -1L;
    }

    public Section(Section s) {
        this._offset = -1L;
        this.setFormatID(s.getFormatID());
        for (Property p : s.properties.values()) {
            this.properties.put(p.getID(), new Property(p));
        }
        this.setDictionary(s.getDictionary());
    }

    public Section(byte[] src, int offset) throws UnsupportedEncodingException {
        this.formatID = new ClassID(src, offset);
        int offFix = (int)LittleEndian.getUInt(src, offset + 16);
        if (src[offFix] == 0) {
            int i = 0;
            while (i < 3 && src[offFix] == 0) {
                ++i;
                ++offFix;
            }
            i = 0;
            while (i < 3 && (src[offFix + 3] != 0 || src[offFix + 7] != 0 || src[offFix + 11] != 0)) {
                ++i;
                --offFix;
            }
        }
        this._offset = offFix;
        LittleEndianByteArrayInputStream leis = new LittleEndianByteArrayInputStream(src, offFix);
        int size = (int)Math.min(leis.readUInt(), (long)src.length - this._offset);
        int propertyCount = (int)leis.readUInt();
        TreeBidiMap<Long, Long> offset2Id = new TreeBidiMap<Long, Long>();
        for (int i = 0; i < propertyCount; ++i) {
            long id = leis.readUInt();
            long off = leis.readUInt();
            offset2Id.put(off, id);
        }
        Long cpOffset = (Long)offset2Id.getKey(1L);
        int codepage = -1;
        if (cpOffset != null) {
            leis.setReadIndex(Math.toIntExact(this._offset + cpOffset));
            long type = leis.readUInt();
            if (type != 2L) {
                throw new HPSFRuntimeException("Value type of property ID 1 is not VT_I2 but " + type + ".");
            }
            codepage = leis.readUShort();
            this.setCodepage(codepage);
        }
        for (Map.Entry me : offset2Id.entrySet()) {
            long off = (Long)me.getKey();
            long id = (Long)me.getValue();
            if (id == 1L) continue;
            int pLen = Section.propLen(offset2Id, off, size);
            leis.setReadIndex(Math.toIntExact(this._offset + off));
            if (id == 0L) {
                leis.mark(100000);
                if (this.readDictionary(leis, pLen, codepage)) continue;
                leis.reset();
                try {
                    id = Math.max(31L, (Long)offset2Id.inverseBidiMap().lastKey()) + 1L;
                    this.setProperty(new Property(id, leis, pLen, codepage));
                } catch (RuntimeException e) {
                    LOG.log(3, "Dictionary fallback failed - ignoring property");
                }
                continue;
            }
            this.setProperty(new Property(id, leis, pLen, codepage));
        }
        this.sectionBytes.write(src, Math.toIntExact(this._offset), size);
        this.padSectionBytes();
    }

    private static int propLen(TreeBidiMap<Long, Long> offset2Id, Long entryOffset, long maxSize) {
        Long nextKey = offset2Id.nextKey(entryOffset);
        long begin = entryOffset;
        long end = nextKey != null ? nextKey : maxSize;
        return Math.toIntExact(end - begin);
    }

    public ClassID getFormatID() {
        return this.formatID;
    }

    public void setFormatID(ClassID formatID) {
        this.formatID = formatID;
    }

    public void setFormatID(byte[] formatID) {
        ClassID fid = this.getFormatID();
        if (fid == null) {
            fid = new ClassID();
            this.setFormatID(fid);
        }
        fid.setBytes(formatID);
    }

    public long getOffset() {
        return this._offset;
    }

    public int getPropertyCount() {
        return this.properties.size();
    }

    public Property[] getProperties() {
        return this.properties.values().toArray(new Property[0]);
    }

    public void setProperties(Property[] properties) {
        this.properties.clear();
        for (Property p : properties) {
            this.setProperty(p);
        }
    }

    public Object getProperty(long id) {
        this.wasNull = !this.properties.containsKey(id);
        return this.wasNull ? null : this.properties.get(id).getValue();
    }

    public void setProperty(int id, String value) {
        this.setProperty(id, 30L, value);
    }

    public void setProperty(int id, int value) {
        this.setProperty(id, 3L, value);
    }

    public void setProperty(int id, long value) {
        this.setProperty(id, 20L, value);
    }

    public void setProperty(int id, boolean value) {
        this.setProperty(id, 11L, value);
    }

    public void setProperty(int id, long variantType, Object value) {
        this.setProperty(new Property(id, variantType, value));
    }

    public void setProperty(Property p) {
        Property old = this.properties.get(p.getID());
        if (old == null || !old.equals(p)) {
            this.properties.put(p.getID(), p);
            this.sectionBytes.reset();
        }
    }

    public void setProperty(int id, Object value) {
        if (value instanceof String) {
            this.setProperty(id, (String)value);
        } else if (value instanceof Long) {
            this.setProperty(id, (Long)value);
        } else if (value instanceof Integer) {
            this.setProperty(id, (Integer)value);
        } else if (value instanceof Short) {
            this.setProperty(id, ((Short)value).intValue());
        } else if (value instanceof Boolean) {
            this.setProperty(id, (Boolean)value);
        } else if (value instanceof Date) {
            this.setProperty(id, 64L, value);
        } else {
            throw new HPSFRuntimeException("HPSF does not support properties of type " + value.getClass().getName() + ".");
        }
    }

    int getPropertyIntValue(long id) {
        Object o = this.getProperty(id);
        if (o == null) {
            return 0;
        }
        if (!(o instanceof Long) && !(o instanceof Integer)) {
            throw new HPSFRuntimeException("This property is not an integer type, but " + o.getClass().getName() + ".");
        }
        Number i = (Number)o;
        return i.intValue();
    }

    boolean getPropertyBooleanValue(int id) {
        Boolean b = (Boolean)this.getProperty(id);
        return b != null && b != false;
    }

    protected void setPropertyBooleanValue(int id, boolean value) {
        this.setProperty(id, 11L, value);
    }

    public int getSize() {
        int size = this.sectionBytes.size();
        if (size > 0) {
            return size;
        }
        try {
            return this.calcSize();
        } catch (HPSFRuntimeException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HPSFRuntimeException(ex);
        }
    }

    private int calcSize() throws WritingNotSupportedException, IOException {
        this.sectionBytes.reset();
        this.write(this.sectionBytes);
        this.padSectionBytes();
        return this.sectionBytes.size();
    }

    private void padSectionBytes() {
        byte[] padArray = new byte[]{0, 0, 0};
        int pad = 4 - (this.sectionBytes.size() & 3) & 3;
        this.sectionBytes.write(padArray, 0, pad);
    }

    public boolean wasNull() {
        return this.wasNull;
    }

    public String getPIDString(long pid) {
        PropertyIDMap dic = this.getDictionary();
        if (dic == null || !dic.containsKey(pid)) {
            ClassID fmt = this.getFormatID();
            if (SummaryInformation.FORMAT_ID.equals(fmt)) {
                dic = PropertyIDMap.getSummaryInformationProperties();
            } else if (DocumentSummaryInformation.FORMAT_ID[0].equals(fmt)) {
                dic = PropertyIDMap.getDocumentSummaryInformationProperties();
            }
        }
        return dic != null && dic.containsKey(pid) ? (String)dic.get(pid) : "[undefined]";
    }

    public void clear() {
        for (Property p : this.getProperties()) {
            this.removeProperty(p.getID());
        }
    }

    public boolean equals(Object o) {
        if (!(o instanceof Section)) {
            return false;
        }
        Section s = (Section)o;
        if (!s.getFormatID().equals(this.getFormatID())) {
            return false;
        }
        HashSet<Long> propIds = new HashSet<Long>(this.properties.keySet());
        propIds.addAll(s.properties.keySet());
        propIds.remove(0L);
        propIds.remove(1L);
        for (Long id : propIds) {
            Property p1 = this.properties.get(id);
            Property p2 = s.properties.get(id);
            if (p1 != null && p1.equals(p2)) continue;
            return false;
        }
        Map<Long, String> d1 = this.getDictionary();
        Map<Long, String> d2 = s.getDictionary();
        return d1 == null && d2 == null || d1 != null && d1.equals(d2);
    }

    public void removeProperty(long id) {
        if (this.properties.remove(id) != null) {
            this.sectionBytes.reset();
        }
    }

    public int write(OutputStream out) throws WritingNotSupportedException, IOException {
        if (this.sectionBytes.size() > 0) {
            this.sectionBytes.writeTo(out);
            return this.sectionBytes.size();
        }
        int codepage = this.getCodepage();
        if (codepage == -1) {
            String msg = "The codepage property is not set although a dictionary is present. Defaulting to ISO-8859-1.";
            LOG.log(5, msg);
            codepage = 1252;
        }
        int[][] offsets = new int[this.properties.size()][2];
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        LittleEndianOutputStream leos = new LittleEndianOutputStream(bos);
        leos.writeInt(-1);
        leos.writeInt(this.properties.size());
        int propCnt = 0;
        for (Property p : this.properties.values()) {
            leos.writeUInt(p.getID());
            offsets[propCnt++][0] = bos.size();
            leos.writeInt(-1);
        }
        propCnt = 0;
        for (Property p : this.properties.values()) {
            offsets[propCnt++][1] = bos.size();
            if (p.getID() != 0L) {
                p.write(bos, codepage);
                continue;
            }
            this.writeDictionary(bos, codepage);
        }
        byte[] result = bos.toByteArray();
        LittleEndian.putInt(result, 0, bos.size());
        for (int[] off : offsets) {
            LittleEndian.putUInt(result, off[0], off[1]);
        }
        out.write(result);
        return bos.size();
    }

    private boolean readDictionary(LittleEndianByteArrayInputStream leis, int length, int codepage) {
        HashMap<Long, String> dic = new HashMap<Long, String>();
        long nrEntries = leis.readUInt();
        long id = -1L;
        boolean isCorrupted = false;
        int i = 0;
        while ((long)i < nrEntries) {
            int cp;
            String errMsg = "The property set's dictionary contains bogus data. All dictionary entries starting with the one with ID " + id + " will be ignored.";
            id = leis.readUInt();
            long sLength = leis.readUInt();
            int nrBytes = Math.toIntExact((sLength - 1L) * (long)((cp = codepage == -1 ? 1252 : codepage) == 1200 ? 2 : 1));
            if (nrBytes > 0xFFFFFF) {
                LOG.log(5, errMsg);
                isCorrupted = true;
                break;
            }
            try {
                byte[] buf = IOUtils.safelyAllocate(nrBytes, 100000);
                leis.readFully(buf, 0, nrBytes);
                String str = CodePageUtil.getStringFromCodePage(buf, 0, nrBytes, cp);
                int pad = 1;
                if (cp == 1200) {
                    pad = 2 + (4 - (nrBytes + 2 & 3) & 3);
                }
                IOUtils.skipFully(leis, pad);
                dic.put(id, str);
            } catch (IOException | RuntimeException ex) {
                LOG.log(5, errMsg, ex);
                isCorrupted = true;
                break;
            }
            ++i;
        }
        this.setDictionary(dic);
        return !isCorrupted;
    }

    private void writeDictionary(OutputStream out, int codepage) throws IOException {
        byte[] padding = new byte[4];
        Map<Long, String> dic = this.getDictionary();
        LittleEndian.putUInt(dic.size(), out);
        int length = 4;
        for (Map.Entry<Long, String> ls : dic.entrySet()) {
            LittleEndian.putUInt(ls.getKey(), out);
            length += 4;
            String value = ls.getValue() + "\u0000";
            byte[] bytes = CodePageUtil.getBytesInCodePage(value, codepage);
            int len = codepage == 1200 ? value.length() : bytes.length;
            LittleEndian.putUInt(len, out);
            length += 4;
            out.write(bytes);
            int pad = codepage == 1200 ? 4 - ((length += bytes.length) & 3) & 3 : 0;
            out.write(padding, 0, pad);
            length += pad;
        }
        int pad = 4 - (length & 3) & 3;
        out.write(padding, 0, pad);
    }

    public void setDictionary(Map<Long, String> dictionary) throws IllegalPropertySetDataException {
        if (dictionary != null) {
            if (this.dictionary == null) {
                this.dictionary = new TreeMap<Long, String>();
            }
            this.dictionary.putAll(dictionary);
            int cp = this.getCodepage();
            if (cp == -1) {
                this.setCodepage(1252);
            }
            this.setProperty(0, -1L, dictionary);
        } else {
            this.removeProperty(0L);
            this.dictionary = null;
        }
    }

    public int hashCode() {
        return Arrays.deepHashCode(new Object[]{this.getFormatID(), this.getProperties()});
    }

    public String toString() {
        return this.toString(null);
    }

    public String toString(PropertyIDMap idMap) {
        StringBuilder b = new StringBuilder();
        Property[] pa = this.getProperties();
        b.append("\n\n\n");
        b.append(this.getClass().getName());
        b.append('[');
        b.append("formatID: ");
        b.append(this.getFormatID());
        b.append(", offset: ");
        b.append(this.getOffset());
        b.append(", propertyCount: ");
        b.append(this.getPropertyCount());
        b.append(", size: ");
        b.append(this.getSize());
        b.append(", properties: [\n");
        int codepage = this.getCodepage();
        if (codepage == -1) {
            codepage = 1252;
        }
        for (Property p : pa) {
            b.append(p.toString(codepage, idMap));
            b.append(",\n");
        }
        b.append(']');
        b.append(']');
        return b.toString();
    }

    public Map<Long, String> getDictionary() {
        if (this.dictionary == null) {
            this.dictionary = (Map)this.getProperty(0L);
        }
        return this.dictionary;
    }

    public int getCodepage() {
        Integer codepage = (Integer)this.getProperty(1L);
        return codepage == null ? -1 : codepage;
    }

    public void setCodepage(int codepage) {
        this.setProperty(1, 2L, codepage);
    }
}

