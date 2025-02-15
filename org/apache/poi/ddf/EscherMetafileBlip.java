/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ddf;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;
import org.apache.poi.ddf.EscherBlipRecord;
import org.apache.poi.ddf.EscherRecordFactory;
import org.apache.poi.ddf.EscherRecordTypes;
import org.apache.poi.ddf.EscherSerializationListener;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;

public final class EscherMetafileBlip
extends EscherBlipRecord {
    private static final POILogger log = POILogFactory.getLogger(EscherMetafileBlip.class);
    private static final int MAX_RECORD_LENGTH = 100000000;
    public static final short RECORD_ID_EMF = EscherRecordTypes.BLIP_EMF.typeID;
    public static final short RECORD_ID_WMF = EscherRecordTypes.BLIP_WMF.typeID;
    public static final short RECORD_ID_PICT = EscherRecordTypes.BLIP_PICT.typeID;
    private static final int HEADER_SIZE = 8;
    private final byte[] field_1_UID = new byte[16];
    private final byte[] field_2_UID = new byte[16];
    private int field_2_cb;
    private int field_3_rcBounds_x1;
    private int field_3_rcBounds_y1;
    private int field_3_rcBounds_x2;
    private int field_3_rcBounds_y2;
    private int field_4_ptSize_w;
    private int field_4_ptSize_h;
    private int field_5_cbSave;
    private byte field_6_fCompression;
    private byte field_7_fFilter;
    private byte[] raw_pictureData;
    private byte[] remainingData;

    public EscherMetafileBlip() {
    }

    public EscherMetafileBlip(EscherMetafileBlip other) {
        super(other);
        System.arraycopy(other.field_1_UID, 0, this.field_1_UID, 0, this.field_1_UID.length);
        System.arraycopy(other.field_2_UID, 0, this.field_2_UID, 0, this.field_2_UID.length);
        this.field_2_cb = other.field_2_cb;
        this.field_3_rcBounds_x1 = other.field_3_rcBounds_x1;
        this.field_3_rcBounds_y1 = other.field_3_rcBounds_y1;
        this.field_3_rcBounds_x2 = other.field_3_rcBounds_x2;
        this.field_3_rcBounds_y2 = other.field_3_rcBounds_y2;
        this.field_4_ptSize_h = other.field_4_ptSize_h;
        this.field_4_ptSize_w = other.field_4_ptSize_w;
        this.field_5_cbSave = other.field_5_cbSave;
        this.field_6_fCompression = other.field_6_fCompression;
        this.field_7_fFilter = other.field_7_fFilter;
        this.raw_pictureData = other.raw_pictureData == null ? null : (byte[])other.raw_pictureData.clone();
        this.remainingData = other.remainingData == null ? null : (byte[])other.remainingData.clone();
    }

    @Override
    public int fillFields(byte[] data, int offset, EscherRecordFactory recordFactory) {
        int bytesAfterHeader = this.readHeader(data, offset);
        int pos = offset + 8;
        System.arraycopy(data, pos, this.field_1_UID, 0, 16);
        pos += 16;
        if ((this.getOptions() ^ this.getSignature()) == 16) {
            System.arraycopy(data, pos, this.field_2_UID, 0, 16);
            pos += 16;
        }
        this.field_2_cb = LittleEndian.getInt(data, pos);
        this.field_3_rcBounds_x1 = LittleEndian.getInt(data, pos += 4);
        this.field_3_rcBounds_y1 = LittleEndian.getInt(data, pos += 4);
        this.field_3_rcBounds_x2 = LittleEndian.getInt(data, pos += 4);
        this.field_3_rcBounds_y2 = LittleEndian.getInt(data, pos += 4);
        this.field_4_ptSize_w = LittleEndian.getInt(data, pos += 4);
        this.field_4_ptSize_h = LittleEndian.getInt(data, pos += 4);
        this.field_5_cbSave = LittleEndian.getInt(data, pos += 4);
        this.field_6_fCompression = data[pos += 4];
        this.field_7_fFilter = data[++pos];
        this.raw_pictureData = IOUtils.safelyAllocate(this.field_5_cbSave, 100000000);
        System.arraycopy(data, ++pos, this.raw_pictureData, 0, this.field_5_cbSave);
        pos += this.field_5_cbSave;
        if (this.field_6_fCompression == 0) {
            super.setPictureData(EscherMetafileBlip.inflatePictureData(this.raw_pictureData));
        } else {
            super.setPictureData(this.raw_pictureData);
        }
        int remaining = bytesAfterHeader - pos + offset + 8;
        if (remaining > 0) {
            this.remainingData = IOUtils.safelyAllocate(remaining, 100000000);
            System.arraycopy(data, pos, this.remainingData, 0, remaining);
        }
        return bytesAfterHeader + 8;
    }

    @Override
    public int serialize(int offset, byte[] data, EscherSerializationListener listener) {
        listener.beforeRecordSerialize(offset, this.getRecordId(), this);
        int pos = offset;
        LittleEndian.putShort(data, pos, this.getOptions());
        LittleEndian.putShort(data, pos += 2, this.getRecordId());
        LittleEndian.putInt(data, pos += 2, this.getRecordSize() - 8);
        System.arraycopy(this.field_1_UID, 0, data, pos += 4, this.field_1_UID.length);
        pos += this.field_1_UID.length;
        if ((this.getOptions() ^ this.getSignature()) == 16) {
            System.arraycopy(this.field_2_UID, 0, data, pos, this.field_2_UID.length);
            pos += this.field_2_UID.length;
        }
        LittleEndian.putInt(data, pos, this.field_2_cb);
        LittleEndian.putInt(data, pos += 4, this.field_3_rcBounds_x1);
        LittleEndian.putInt(data, pos += 4, this.field_3_rcBounds_y1);
        LittleEndian.putInt(data, pos += 4, this.field_3_rcBounds_x2);
        LittleEndian.putInt(data, pos += 4, this.field_3_rcBounds_y2);
        LittleEndian.putInt(data, pos += 4, this.field_4_ptSize_w);
        LittleEndian.putInt(data, pos += 4, this.field_4_ptSize_h);
        LittleEndian.putInt(data, pos += 4, this.field_5_cbSave);
        data[pos += 4] = this.field_6_fCompression;
        data[++pos] = this.field_7_fFilter;
        System.arraycopy(this.raw_pictureData, 0, data, ++pos, this.raw_pictureData.length);
        pos += this.raw_pictureData.length;
        if (this.remainingData != null) {
            System.arraycopy(this.remainingData, 0, data, pos, this.remainingData.length);
            pos += this.remainingData.length;
        }
        listener.afterRecordSerialize(offset + this.getRecordSize(), this.getRecordId(), this.getRecordSize(), this);
        return this.getRecordSize();
    }

    private static byte[] inflatePictureData(byte[] data) {
        try {
            int readBytes;
            InflaterInputStream in = new InflaterInputStream(new ByteArrayInputStream(data));
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[4096];
            while ((readBytes = in.read(buf)) > 0) {
                out.write(buf, 0, readBytes);
            }
            return out.toByteArray();
        } catch (IOException e) {
            log.log(5, "Possibly corrupt compression or non-compressed data", e);
            return data;
        }
    }

    @Override
    public int getRecordSize() {
        int size = 58 + this.raw_pictureData.length;
        if (this.remainingData != null) {
            size += this.remainingData.length;
        }
        if ((this.getOptions() ^ this.getSignature()) == 16) {
            size += this.field_2_UID.length;
        }
        return size;
    }

    public byte[] getUID() {
        return this.field_1_UID;
    }

    public void setUID(byte[] uid) {
        if (uid == null || uid.length != 16) {
            throw new IllegalArgumentException("uid must be byte[16]");
        }
        System.arraycopy(uid, 0, this.field_1_UID, 0, this.field_1_UID.length);
    }

    public byte[] getPrimaryUID() {
        return this.field_2_UID;
    }

    public void setPrimaryUID(byte[] primaryUID) {
        if (primaryUID == null || primaryUID.length != 16) {
            throw new IllegalArgumentException("primaryUID must be byte[16]");
        }
        System.arraycopy(primaryUID, 0, this.field_2_UID, 0, this.field_2_UID.length);
    }

    public int getUncompressedSize() {
        return this.field_2_cb;
    }

    public void setUncompressedSize(int uncompressedSize) {
        this.field_2_cb = uncompressedSize;
    }

    public Rectangle getBounds() {
        return new Rectangle(this.field_3_rcBounds_x1, this.field_3_rcBounds_y1, this.field_3_rcBounds_x2 - this.field_3_rcBounds_x1, this.field_3_rcBounds_y2 - this.field_3_rcBounds_y1);
    }

    public void setBounds(Rectangle bounds) {
        this.field_3_rcBounds_x1 = bounds.x;
        this.field_3_rcBounds_y1 = bounds.y;
        this.field_3_rcBounds_x2 = bounds.x + bounds.width;
        this.field_3_rcBounds_y2 = bounds.y + bounds.height;
    }

    public Dimension getSizeEMU() {
        return new Dimension(this.field_4_ptSize_w, this.field_4_ptSize_h);
    }

    public void setSizeEMU(Dimension sizeEMU) {
        this.field_4_ptSize_w = sizeEMU.width;
        this.field_4_ptSize_h = sizeEMU.height;
    }

    public int getCompressedSize() {
        return this.field_5_cbSave;
    }

    public void setCompressedSize(int compressedSize) {
        this.field_5_cbSave = compressedSize;
    }

    public boolean isCompressed() {
        return this.field_6_fCompression == 0;
    }

    public void setCompressed(boolean compressed) {
        this.field_6_fCompression = (byte)(compressed ? 0 : -2);
    }

    public byte getFilter() {
        return this.field_7_fFilter;
    }

    public void setFilter(byte filter) {
        this.field_7_fFilter = filter;
    }

    public byte[] getRemainingData() {
        return this.remainingData;
    }

    public short getSignature() {
        switch (EscherRecordTypes.forTypeID(this.getRecordId())) {
            case BLIP_EMF: {
                return 15680;
            }
            case BLIP_WMF: {
                return 8544;
            }
            case BLIP_PICT: {
                return 21536;
            }
        }
        if (log.check(5)) {
            log.log(5, "Unknown metafile: " + this.getRecordId());
        }
        return 0;
    }

    @Override
    public void setPictureData(byte[] pictureData) {
        super.setPictureData(pictureData);
        this.setUncompressedSize(pictureData.length);
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            DeflaterOutputStream dos = new DeflaterOutputStream(bos);
            dos.write(pictureData);
            dos.close();
            this.raw_pictureData = bos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Can't compress metafile picture data", e);
        }
        this.setCompressedSize(this.raw_pictureData.length);
        this.setCompressed(true);
    }

    @Override
    public Map<String, Supplier<?>> getGenericProperties() {
        LinkedHashMap m = new LinkedHashMap(super.getGenericProperties());
        m.put("uid", this::getUID);
        m.put("uncompressedSize", this::getUncompressedSize);
        m.put("bounds", this::getBounds);
        m.put("sizeInEMU", this::getSizeEMU);
        m.put("compressedSize", this::getCompressedSize);
        m.put("isCompressed", this::isCompressed);
        m.put("filter", this::getFilter);
        return Collections.unmodifiableMap(m);
    }

    @Override
    public EscherMetafileBlip copy() {
        return new EscherMetafileBlip(this);
    }
}

