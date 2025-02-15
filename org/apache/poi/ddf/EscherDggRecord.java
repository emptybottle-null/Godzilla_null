/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ddf;

import com.zaxxer.sparsebits.SparseBitSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.ddf.EscherDgRecord;
import org.apache.poi.ddf.EscherRecord;
import org.apache.poi.ddf.EscherRecordFactory;
import org.apache.poi.ddf.EscherRecordTypes;
import org.apache.poi.ddf.EscherSerializationListener;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.RecordFormatException;

public final class EscherDggRecord
extends EscherRecord {
    public static final short RECORD_ID = EscherRecordTypes.DGG.typeID;
    private int field_1_shapeIdMax;
    private int field_3_numShapesSaved;
    private int field_4_drawingsSaved;
    private final List<FileIdCluster> field_5_fileIdClusters = new ArrayList<FileIdCluster>();
    private int maxDgId;

    public EscherDggRecord() {
    }

    public EscherDggRecord(EscherDggRecord other) {
        super(other);
        this.field_1_shapeIdMax = other.field_1_shapeIdMax;
        this.field_3_numShapesSaved = other.field_3_numShapesSaved;
        this.field_4_drawingsSaved = other.field_4_drawingsSaved;
        other.field_5_fileIdClusters.stream().map(FileIdCluster::new).forEach(this.field_5_fileIdClusters::add);
        this.maxDgId = other.maxDgId;
    }

    @Override
    public int fillFields(byte[] data, int offset, EscherRecordFactory recordFactory) {
        int bytesRemaining = this.readHeader(data, offset);
        int pos = offset + 8;
        int size = 0;
        this.field_1_shapeIdMax = LittleEndian.getInt(data, pos + size);
        size += 4;
        this.field_3_numShapesSaved = LittleEndian.getInt(data, pos + (size += 4));
        this.field_4_drawingsSaved = LittleEndian.getInt(data, pos + (size += 4));
        this.field_5_fileIdClusters.clear();
        int numIdClusters = (bytesRemaining - (size += 4)) / 8;
        for (int i = 0; i < numIdClusters; ++i) {
            int drawingGroupId = LittleEndian.getInt(data, pos + size);
            int numShapeIdsUsed = LittleEndian.getInt(data, pos + size + 4);
            FileIdCluster fic = new FileIdCluster(drawingGroupId, numShapeIdsUsed);
            this.field_5_fileIdClusters.add(fic);
            this.maxDgId = Math.max(this.maxDgId, drawingGroupId);
            size += 8;
        }
        if ((bytesRemaining -= size) != 0) {
            throw new RecordFormatException("Expecting no remaining data but got " + bytesRemaining + " byte(s).");
        }
        return 8 + size;
    }

    @Override
    public int serialize(int offset, byte[] data, EscherSerializationListener listener) {
        listener.beforeRecordSerialize(offset, this.getRecordId(), this);
        int pos = offset;
        LittleEndian.putShort(data, pos, this.getOptions());
        LittleEndian.putShort(data, pos += 2, this.getRecordId());
        int remainingBytes = this.getRecordSize() - 8;
        LittleEndian.putInt(data, pos += 2, remainingBytes);
        LittleEndian.putInt(data, pos += 4, this.field_1_shapeIdMax);
        LittleEndian.putInt(data, pos += 4, this.getNumIdClusters());
        LittleEndian.putInt(data, pos += 4, this.field_3_numShapesSaved);
        LittleEndian.putInt(data, pos += 4, this.field_4_drawingsSaved);
        pos += 4;
        for (FileIdCluster fic : this.field_5_fileIdClusters) {
            LittleEndian.putInt(data, pos, fic.getDrawingGroupId());
            LittleEndian.putInt(data, pos += 4, fic.getNumShapeIdsUsed());
            pos += 4;
        }
        listener.afterRecordSerialize(pos, this.getRecordId(), this.getRecordSize(), this);
        return this.getRecordSize();
    }

    @Override
    public int getRecordSize() {
        return 24 + 8 * this.field_5_fileIdClusters.size();
    }

    @Override
    public short getRecordId() {
        return RECORD_ID;
    }

    @Override
    public String getRecordName() {
        return EscherRecordTypes.DGG.recordName;
    }

    public int getShapeIdMax() {
        return this.field_1_shapeIdMax;
    }

    public void setShapeIdMax(int shapeIdMax) {
        this.field_1_shapeIdMax = shapeIdMax;
    }

    public int getNumIdClusters() {
        return this.field_5_fileIdClusters.isEmpty() ? 0 : this.field_5_fileIdClusters.size() + 1;
    }

    public int getNumShapesSaved() {
        return this.field_3_numShapesSaved;
    }

    public void setNumShapesSaved(int numShapesSaved) {
        this.field_3_numShapesSaved = numShapesSaved;
    }

    public int getDrawingsSaved() {
        return this.field_4_drawingsSaved;
    }

    public void setDrawingsSaved(int drawingsSaved) {
        this.field_4_drawingsSaved = drawingsSaved;
    }

    public int getMaxDrawingGroupId() {
        return this.maxDgId;
    }

    public FileIdCluster[] getFileIdClusters() {
        return this.field_5_fileIdClusters.toArray(new FileIdCluster[0]);
    }

    public void setFileIdClusters(FileIdCluster[] fileIdClusters) {
        this.field_5_fileIdClusters.clear();
        if (fileIdClusters != null) {
            this.field_5_fileIdClusters.addAll(Arrays.asList(fileIdClusters));
        }
    }

    public FileIdCluster addCluster(int dgId, int numShapedUsed) {
        return this.addCluster(dgId, numShapedUsed, true);
    }

    public FileIdCluster addCluster(int dgId, int numShapedUsed, boolean sort) {
        FileIdCluster ficNew = new FileIdCluster(dgId, numShapedUsed);
        this.field_5_fileIdClusters.add(ficNew);
        this.maxDgId = Math.min(this.maxDgId, dgId);
        if (sort) {
            this.sortCluster();
        }
        return ficNew;
    }

    private void sortCluster() {
        this.field_5_fileIdClusters.sort((x$0, x$1) -> FileIdCluster.compareFileIdCluster((FileIdCluster)x$0, (FileIdCluster)x$1));
    }

    public short findNewDrawingGroupId() {
        SparseBitSet bs = new SparseBitSet();
        bs.set(0);
        for (FileIdCluster fic : this.field_5_fileIdClusters) {
            bs.set(fic.getDrawingGroupId());
        }
        return (short)bs.nextClearBit(0);
    }

    public int allocateShapeId(EscherDgRecord dg, boolean sort) {
        short drawingGroupId = dg.getDrawingGroupId();
        ++this.field_3_numShapesSaved;
        FileIdCluster ficAdd = null;
        int index = 1;
        for (FileIdCluster fic : this.field_5_fileIdClusters) {
            if (fic.getDrawingGroupId() == drawingGroupId && fic.getNumShapeIdsUsed() < 1024) {
                ficAdd = fic;
                break;
            }
            ++index;
        }
        if (ficAdd == null) {
            ficAdd = this.addCluster(drawingGroupId, 0, sort);
            this.maxDgId = Math.max(this.maxDgId, drawingGroupId);
        }
        int shapeId = index * 1024 + ficAdd.getNumShapeIdsUsed();
        ficAdd.incrementUsedShapeId();
        dg.setNumShapes(dg.getNumShapes() + 1);
        dg.setLastMSOSPID(shapeId);
        this.field_1_shapeIdMax = Math.max(this.field_1_shapeIdMax, shapeId + 1);
        return shapeId;
    }

    @Override
    public Enum getGenericRecordType() {
        return EscherRecordTypes.DGG;
    }

    @Override
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", () -> super.getGenericProperties(), "fileIdClusters", () -> this.field_5_fileIdClusters, "shapeIdMax", this::getShapeIdMax, "numIdClusters", this::getNumIdClusters, "numShapesSaved", this::getNumShapesSaved, "drawingsSaved", this::getDrawingsSaved);
    }

    @Override
    public EscherDggRecord copy() {
        return new EscherDggRecord(this);
    }

    public static class FileIdCluster
    implements GenericRecord {
        private int field_1_drawingGroupId;
        private int field_2_numShapeIdsUsed;

        public FileIdCluster(FileIdCluster other) {
            this.field_1_drawingGroupId = other.field_1_drawingGroupId;
            this.field_2_numShapeIdsUsed = other.field_2_numShapeIdsUsed;
        }

        public FileIdCluster(int drawingGroupId, int numShapeIdsUsed) {
            this.field_1_drawingGroupId = drawingGroupId;
            this.field_2_numShapeIdsUsed = numShapeIdsUsed;
        }

        public int getDrawingGroupId() {
            return this.field_1_drawingGroupId;
        }

        public int getNumShapeIdsUsed() {
            return this.field_2_numShapeIdsUsed;
        }

        private void incrementUsedShapeId() {
            ++this.field_2_numShapeIdsUsed;
        }

        private static int compareFileIdCluster(FileIdCluster f1, FileIdCluster f2) {
            int dgDif = f1.getDrawingGroupId() - f2.getDrawingGroupId();
            int cntDif = f2.getNumShapeIdsUsed() - f1.getNumShapeIdsUsed();
            return dgDif != 0 ? dgDif : cntDif;
        }

        @Override
        public Map<String, Supplier<?>> getGenericProperties() {
            return GenericRecordUtil.getGenericProperties("drawingGroupId", this::getDrawingGroupId, "numShapeIdUsed", this::getNumShapeIdsUsed);
        }
    }
}

