/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ddf.DefaultEscherRecordFactory;
import org.apache.poi.ddf.EscherClientDataRecord;
import org.apache.poi.ddf.EscherContainerRecord;
import org.apache.poi.ddf.EscherDgRecord;
import org.apache.poi.ddf.EscherRecord;
import org.apache.poi.ddf.EscherSerializationListener;
import org.apache.poi.ddf.EscherSpRecord;
import org.apache.poi.ddf.EscherSpgrRecord;
import org.apache.poi.ddf.EscherTextboxRecord;
import org.apache.poi.hssf.record.AbstractEscherHolderRecord;
import org.apache.poi.hssf.record.CommonObjectDataSubRecord;
import org.apache.poi.hssf.record.ContinueRecord;
import org.apache.poi.hssf.record.DrawingRecord;
import org.apache.poi.hssf.record.NoteRecord;
import org.apache.poi.hssf.record.ObjRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.RecordBase;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.RecordFormatException;

public final class EscherAggregate
extends AbstractEscherHolderRecord {
    public static final short sid = 9876;
    private static final int MAX_RECORD_LENGTH = 100000000;
    public static final short ST_MIN = 0;
    public static final short ST_NOT_PRIMATIVE = 0;
    public static final short ST_RECTANGLE = 1;
    public static final short ST_ROUNDRECTANGLE = 2;
    public static final short ST_ELLIPSE = 3;
    public static final short ST_DIAMOND = 4;
    public static final short ST_ISOCELESTRIANGLE = 5;
    public static final short ST_RIGHTTRIANGLE = 6;
    public static final short ST_PARALLELOGRAM = 7;
    public static final short ST_TRAPEZOID = 8;
    public static final short ST_HEXAGON = 9;
    public static final short ST_OCTAGON = 10;
    public static final short ST_PLUS = 11;
    public static final short ST_STAR = 12;
    public static final short ST_ARROW = 13;
    public static final short ST_THICKARROW = 14;
    public static final short ST_HOMEPLATE = 15;
    public static final short ST_CUBE = 16;
    public static final short ST_BALLOON = 17;
    public static final short ST_SEAL = 18;
    public static final short ST_ARC = 19;
    public static final short ST_LINE = 20;
    public static final short ST_PLAQUE = 21;
    public static final short ST_CAN = 22;
    public static final short ST_DONUT = 23;
    public static final short ST_TEXTSIMPLE = 24;
    public static final short ST_TEXTOCTAGON = 25;
    public static final short ST_TEXTHEXAGON = 26;
    public static final short ST_TEXTCURVE = 27;
    public static final short ST_TEXTWAVE = 28;
    public static final short ST_TEXTRING = 29;
    public static final short ST_TEXTONCURVE = 30;
    public static final short ST_TEXTONRING = 31;
    public static final short ST_STRAIGHTCONNECTOR1 = 32;
    public static final short ST_BENTCONNECTOR2 = 33;
    public static final short ST_BENTCONNECTOR3 = 34;
    public static final short ST_BENTCONNECTOR4 = 35;
    public static final short ST_BENTCONNECTOR5 = 36;
    public static final short ST_CURVEDCONNECTOR2 = 37;
    public static final short ST_CURVEDCONNECTOR3 = 38;
    public static final short ST_CURVEDCONNECTOR4 = 39;
    public static final short ST_CURVEDCONNECTOR5 = 40;
    public static final short ST_CALLOUT1 = 41;
    public static final short ST_CALLOUT2 = 42;
    public static final short ST_CALLOUT3 = 43;
    public static final short ST_ACCENTCALLOUT1 = 44;
    public static final short ST_ACCENTCALLOUT2 = 45;
    public static final short ST_ACCENTCALLOUT3 = 46;
    public static final short ST_BORDERCALLOUT1 = 47;
    public static final short ST_BORDERCALLOUT2 = 48;
    public static final short ST_BORDERCALLOUT3 = 49;
    public static final short ST_ACCENTBORDERCALLOUT1 = 50;
    public static final short ST_ACCENTBORDERCALLOUT2 = 51;
    public static final short ST_ACCENTBORDERCALLOUT3 = 52;
    public static final short ST_RIBBON = 53;
    public static final short ST_RIBBON2 = 54;
    public static final short ST_CHEVRON = 55;
    public static final short ST_PENTAGON = 56;
    public static final short ST_NOSMOKING = 57;
    public static final short ST_SEAL8 = 58;
    public static final short ST_SEAL16 = 59;
    public static final short ST_SEAL32 = 60;
    public static final short ST_WEDGERECTCALLOUT = 61;
    public static final short ST_WEDGERRECTCALLOUT = 62;
    public static final short ST_WEDGEELLIPSECALLOUT = 63;
    public static final short ST_WAVE = 64;
    public static final short ST_FOLDEDCORNER = 65;
    public static final short ST_LEFTARROW = 66;
    public static final short ST_DOWNARROW = 67;
    public static final short ST_UPARROW = 68;
    public static final short ST_LEFTRIGHTARROW = 69;
    public static final short ST_UPDOWNARROW = 70;
    public static final short ST_IRREGULARSEAL1 = 71;
    public static final short ST_IRREGULARSEAL2 = 72;
    public static final short ST_LIGHTNINGBOLT = 73;
    public static final short ST_HEART = 74;
    public static final short ST_PICTUREFRAME = 75;
    public static final short ST_QUADARROW = 76;
    public static final short ST_LEFTARROWCALLOUT = 77;
    public static final short ST_RIGHTARROWCALLOUT = 78;
    public static final short ST_UPARROWCALLOUT = 79;
    public static final short ST_DOWNARROWCALLOUT = 80;
    public static final short ST_LEFTRIGHTARROWCALLOUT = 81;
    public static final short ST_UPDOWNARROWCALLOUT = 82;
    public static final short ST_QUADARROWCALLOUT = 83;
    public static final short ST_BEVEL = 84;
    public static final short ST_LEFTBRACKET = 85;
    public static final short ST_RIGHTBRACKET = 86;
    public static final short ST_LEFTBRACE = 87;
    public static final short ST_RIGHTBRACE = 88;
    public static final short ST_LEFTUPARROW = 89;
    public static final short ST_BENTUPARROW = 90;
    public static final short ST_BENTARROW = 91;
    public static final short ST_SEAL24 = 92;
    public static final short ST_STRIPEDRIGHTARROW = 93;
    public static final short ST_NOTCHEDRIGHTARROW = 94;
    public static final short ST_BLOCKARC = 95;
    public static final short ST_SMILEYFACE = 96;
    public static final short ST_VERTICALSCROLL = 97;
    public static final short ST_HORIZONTALSCROLL = 98;
    public static final short ST_CIRCULARARROW = 99;
    public static final short ST_NOTCHEDCIRCULARARROW = 100;
    public static final short ST_UTURNARROW = 101;
    public static final short ST_CURVEDRIGHTARROW = 102;
    public static final short ST_CURVEDLEFTARROW = 103;
    public static final short ST_CURVEDUPARROW = 104;
    public static final short ST_CURVEDDOWNARROW = 105;
    public static final short ST_CLOUDCALLOUT = 106;
    public static final short ST_ELLIPSERIBBON = 107;
    public static final short ST_ELLIPSERIBBON2 = 108;
    public static final short ST_FLOWCHARTPROCESS = 109;
    public static final short ST_FLOWCHARTDECISION = 110;
    public static final short ST_FLOWCHARTINPUTOUTPUT = 111;
    public static final short ST_FLOWCHARTPREDEFINEDPROCESS = 112;
    public static final short ST_FLOWCHARTINTERNALSTORAGE = 113;
    public static final short ST_FLOWCHARTDOCUMENT = 114;
    public static final short ST_FLOWCHARTMULTIDOCUMENT = 115;
    public static final short ST_FLOWCHARTTERMINATOR = 116;
    public static final short ST_FLOWCHARTPREPARATION = 117;
    public static final short ST_FLOWCHARTMANUALINPUT = 118;
    public static final short ST_FLOWCHARTMANUALOPERATION = 119;
    public static final short ST_FLOWCHARTCONNECTOR = 120;
    public static final short ST_FLOWCHARTPUNCHEDCARD = 121;
    public static final short ST_FLOWCHARTPUNCHEDTAPE = 122;
    public static final short ST_FLOWCHARTSUMMINGJUNCTION = 123;
    public static final short ST_FLOWCHARTOR = 124;
    public static final short ST_FLOWCHARTCOLLATE = 125;
    public static final short ST_FLOWCHARTSORT = 126;
    public static final short ST_FLOWCHARTEXTRACT = 127;
    public static final short ST_FLOWCHARTMERGE = 128;
    public static final short ST_FLOWCHARTOFFLINESTORAGE = 129;
    public static final short ST_FLOWCHARTONLINESTORAGE = 130;
    public static final short ST_FLOWCHARTMAGNETICTAPE = 131;
    public static final short ST_FLOWCHARTMAGNETICDISK = 132;
    public static final short ST_FLOWCHARTMAGNETICDRUM = 133;
    public static final short ST_FLOWCHARTDISPLAY = 134;
    public static final short ST_FLOWCHARTDELAY = 135;
    public static final short ST_TEXTPLAINTEXT = 136;
    public static final short ST_TEXTSTOP = 137;
    public static final short ST_TEXTTRIANGLE = 138;
    public static final short ST_TEXTTRIANGLEINVERTED = 139;
    public static final short ST_TEXTCHEVRON = 140;
    public static final short ST_TEXTCHEVRONINVERTED = 141;
    public static final short ST_TEXTRINGINSIDE = 142;
    public static final short ST_TEXTRINGOUTSIDE = 143;
    public static final short ST_TEXTARCHUPCURVE = 144;
    public static final short ST_TEXTARCHDOWNCURVE = 145;
    public static final short ST_TEXTCIRCLECURVE = 146;
    public static final short ST_TEXTBUTTONCURVE = 147;
    public static final short ST_TEXTARCHUPPOUR = 148;
    public static final short ST_TEXTARCHDOWNPOUR = 149;
    public static final short ST_TEXTCIRCLEPOUR = 150;
    public static final short ST_TEXTBUTTONPOUR = 151;
    public static final short ST_TEXTCURVEUP = 152;
    public static final short ST_TEXTCURVEDOWN = 153;
    public static final short ST_TEXTCASCADEUP = 154;
    public static final short ST_TEXTCASCADEDOWN = 155;
    public static final short ST_TEXTWAVE1 = 156;
    public static final short ST_TEXTWAVE2 = 157;
    public static final short ST_TEXTWAVE3 = 158;
    public static final short ST_TEXTWAVE4 = 159;
    public static final short ST_TEXTINFLATE = 160;
    public static final short ST_TEXTDEFLATE = 161;
    public static final short ST_TEXTINFLATEBOTTOM = 162;
    public static final short ST_TEXTDEFLATEBOTTOM = 163;
    public static final short ST_TEXTINFLATETOP = 164;
    public static final short ST_TEXTDEFLATETOP = 165;
    public static final short ST_TEXTDEFLATEINFLATE = 166;
    public static final short ST_TEXTDEFLATEINFLATEDEFLATE = 167;
    public static final short ST_TEXTFADERIGHT = 168;
    public static final short ST_TEXTFADELEFT = 169;
    public static final short ST_TEXTFADEUP = 170;
    public static final short ST_TEXTFADEDOWN = 171;
    public static final short ST_TEXTSLANTUP = 172;
    public static final short ST_TEXTSLANTDOWN = 173;
    public static final short ST_TEXTCANUP = 174;
    public static final short ST_TEXTCANDOWN = 175;
    public static final short ST_FLOWCHARTALTERNATEPROCESS = 176;
    public static final short ST_FLOWCHARTOFFPAGECONNECTOR = 177;
    public static final short ST_CALLOUT90 = 178;
    public static final short ST_ACCENTCALLOUT90 = 179;
    public static final short ST_BORDERCALLOUT90 = 180;
    public static final short ST_ACCENTBORDERCALLOUT90 = 181;
    public static final short ST_LEFTRIGHTUPARROW = 182;
    public static final short ST_SUN = 183;
    public static final short ST_MOON = 184;
    public static final short ST_BRACKETPAIR = 185;
    public static final short ST_BRACEPAIR = 186;
    public static final short ST_SEAL4 = 187;
    public static final short ST_DOUBLEWAVE = 188;
    public static final short ST_ACTIONBUTTONBLANK = 189;
    public static final short ST_ACTIONBUTTONHOME = 190;
    public static final short ST_ACTIONBUTTONHELP = 191;
    public static final short ST_ACTIONBUTTONINFORMATION = 192;
    public static final short ST_ACTIONBUTTONFORWARDNEXT = 193;
    public static final short ST_ACTIONBUTTONBACKPREVIOUS = 194;
    public static final short ST_ACTIONBUTTONEND = 195;
    public static final short ST_ACTIONBUTTONBEGINNING = 196;
    public static final short ST_ACTIONBUTTONRETURN = 197;
    public static final short ST_ACTIONBUTTONDOCUMENT = 198;
    public static final short ST_ACTIONBUTTONSOUND = 199;
    public static final short ST_ACTIONBUTTONMOVIE = 200;
    public static final short ST_HOSTCONTROL = 201;
    public static final short ST_TEXTBOX = 202;
    public static final short ST_NIL = 4095;
    private final Map<EscherRecord, Record> shapeToObj = new HashMap<EscherRecord, Record>();
    private final Map<Integer, NoteRecord> tailRec = new LinkedHashMap<Integer, NoteRecord>();

    public EscherAggregate(boolean createDefaultTree) {
        if (createDefaultTree) {
            this.buildBaseTree();
        }
    }

    public EscherAggregate(EscherAggregate other) {
        super(other);
        this.shapeToObj.putAll(other.shapeToObj);
        this.tailRec.putAll(other.tailRec);
    }

    @Override
    public short getSid() {
        return 9876;
    }

    @Override
    public String toString() {
        String nl = System.getProperty("line.separtor");
        StringBuilder result = new StringBuilder();
        result.append('[').append(this.getRecordName()).append(']').append(nl);
        for (EscherRecord escherRecord : this.getEscherRecords()) {
            result.append(escherRecord);
        }
        result.append("[/").append(this.getRecordName()).append(']').append(nl);
        return result.toString();
    }

    public String toXml(String tab) {
        StringBuilder builder = new StringBuilder();
        builder.append(tab).append("<").append(this.getRecordName()).append(">\n");
        for (EscherRecord escherRecord : this.getEscherRecords()) {
            builder.append(escherRecord.toXml(tab + "\t"));
        }
        builder.append(tab).append("</").append(this.getRecordName()).append(">\n");
        return builder.toString();
    }

    public static EscherAggregate createAggregate(List<RecordBase> records, int locFirstDrawingRecord) {
        EscherAggregate agg = new EscherAggregate(false);
        ShapeCollector recordFactory = new ShapeCollector();
        ArrayList<Record> objectRecords = new ArrayList<Record>();
        int nextIdx = locFirstDrawingRecord;
        block6: for (RecordBase rb : records.subList(locFirstDrawingRecord, records.size())) {
            ++nextIdx;
            switch (EscherAggregate.sid(rb)) {
                case 236: {
                    recordFactory.addBytes(((DrawingRecord)rb).getRecordData());
                    continue block6;
                }
                case 60: {
                    recordFactory.addBytes(((ContinueRecord)rb).getData());
                    continue block6;
                }
                case 93: 
                case 438: {
                    objectRecords.add((Record)rb);
                    continue block6;
                }
                case 28: {
                    NoteRecord r = (NoteRecord)rb;
                    agg.tailRec.put(r.getShapeId(), r);
                    continue block6;
                }
            }
            --nextIdx;
            break;
        }
        records.set(locFirstDrawingRecord, agg);
        if (locFirstDrawingRecord + 1 <= nextIdx) {
            records.subList(locFirstDrawingRecord + 1, nextIdx).clear();
        }
        Iterator<EscherRecord> shapeIter = recordFactory.parse(agg).iterator();
        objectRecords.forEach(or -> agg.shapeToObj.put((EscherRecord)shapeIter.next(), (Record)or));
        return agg;
    }

    @Override
    public int serialize(int offset, byte[] data) {
        List<EscherRecord> records = this.getEscherRecords();
        int size = this.getEscherRecordSize(records);
        byte[] buffer = new byte[size];
        final ArrayList<Integer> spEndingOffsets = new ArrayList<Integer>();
        final ArrayList shapes = new ArrayList();
        int pos = 0;
        Iterator<EscherRecord> iterator = records.iterator();
        while (iterator.hasNext()) {
            EscherRecord record;
            EscherRecord e = record = iterator.next();
            pos += e.serialize(pos, buffer, new EscherSerializationListener(){

                @Override
                public void beforeRecordSerialize(int offset, short recordId, EscherRecord record) {
                }

                @Override
                public void afterRecordSerialize(int offset, short recordId, int size, EscherRecord record) {
                    if (recordId == EscherClientDataRecord.RECORD_ID || recordId == EscherTextboxRecord.RECORD_ID) {
                        spEndingOffsets.add(offset);
                        shapes.add(record);
                    }
                }
            });
        }
        shapes.add(0, null);
        spEndingOffsets.add(0, 0);
        pos = offset;
        int writtenEscherBytes = 0;
        boolean isFirst = true;
        int endOffset = 0;
        for (int i = 1; i < shapes.size(); ++i) {
            int startOffset = endOffset;
            endOffset = (Integer)spEndingOffsets.get(i);
            byte[] drawingData = Arrays.copyOfRange(buffer, startOffset, endOffset);
            pos += this.writeDataIntoDrawingRecord(drawingData, writtenEscherBytes, pos, data, isFirst);
            writtenEscherBytes += drawingData.length;
            Record obj = this.shapeToObj.get(shapes.get(i));
            pos += obj.serialize(pos, data);
            isFirst = false;
        }
        if (endOffset < buffer.length - 1) {
            byte[] drawingData = Arrays.copyOfRange(buffer, endOffset, buffer.length);
            pos += this.writeDataIntoDrawingRecord(drawingData, writtenEscherBytes, pos, data, isFirst);
        }
        for (NoteRecord noteRecord : this.tailRec.values()) {
            pos += noteRecord.serialize(pos, data);
        }
        int bytesWritten = pos - offset;
        if (bytesWritten != this.getRecordSize()) {
            throw new RecordFormatException(bytesWritten + " bytes written but getRecordSize() reports " + this.getRecordSize());
        }
        return bytesWritten;
    }

    private int writeDataIntoDrawingRecord(byte[] drawingData, int writtenEscherBytes, int pos, byte[] data, boolean isFirst) {
        int temp = 0;
        boolean useDrawingRecord = isFirst || writtenEscherBytes + drawingData.length <= 8224;
        for (int j = 0; j < drawingData.length; j += 8224) {
            byte[] buf = Arrays.copyOfRange(drawingData, j, Math.min(j + 8224, drawingData.length));
            StandardRecord drawing = useDrawingRecord ? new DrawingRecord(buf) : new ContinueRecord(buf);
            temp += ((RecordBase)drawing).serialize(pos + temp, data);
            useDrawingRecord = false;
        }
        return temp;
    }

    private int getEscherRecordSize(List<EscherRecord> records) {
        int size = 0;
        for (EscherRecord record : records) {
            size += record.getRecordSize();
        }
        return size;
    }

    @Override
    public int getRecordSize() {
        int continueRecordsHeadersSize = 0;
        List<EscherRecord> records = this.getEscherRecords();
        int rawEscherSize = this.getEscherRecordSize(records);
        byte[] buffer = IOUtils.safelyAllocate(rawEscherSize, 100000000);
        final ArrayList<Integer> spEndingOffsets = new ArrayList<Integer>();
        int pos = 0;
        for (EscherRecord e : records) {
            pos += e.serialize(pos, buffer, new EscherSerializationListener(){

                @Override
                public void beforeRecordSerialize(int offset, short recordId, EscherRecord record) {
                }

                @Override
                public void afterRecordSerialize(int offset, short recordId, int size, EscherRecord record) {
                    if (recordId == EscherClientDataRecord.RECORD_ID || recordId == EscherTextboxRecord.RECORD_ID) {
                        spEndingOffsets.add(offset);
                    }
                }
            });
        }
        spEndingOffsets.add(0, 0);
        for (int i = 1; i < spEndingOffsets.size(); ++i) {
            if (i == spEndingOffsets.size() - 1 && (Integer)spEndingOffsets.get(i) < pos) {
                continueRecordsHeadersSize += 4;
            }
            if ((Integer)spEndingOffsets.get(i) - (Integer)spEndingOffsets.get(i - 1) <= 8224) continue;
            continueRecordsHeadersSize += ((Integer)spEndingOffsets.get(i) - (Integer)spEndingOffsets.get(i - 1)) / 8224 * 4;
        }
        int drawingRecordSize = rawEscherSize + this.shapeToObj.size() * 4;
        if (rawEscherSize != 0 && spEndingOffsets.size() == 1) {
            continueRecordsHeadersSize += 4;
        }
        int objRecordSize = 0;
        for (Record r : this.shapeToObj.values()) {
            objRecordSize += r.getRecordSize();
        }
        int tailRecordSize = 0;
        for (NoteRecord noteRecord : this.tailRec.values()) {
            tailRecordSize += noteRecord.getRecordSize();
        }
        return drawingRecordSize + objRecordSize + tailRecordSize + continueRecordsHeadersSize;
    }

    public void associateShapeToObjRecord(EscherRecord r, Record objRecord) {
        this.shapeToObj.put(r, objRecord);
    }

    public void removeShapeToObjRecord(EscherRecord rec) {
        this.shapeToObj.remove(rec);
    }

    @Override
    protected String getRecordName() {
        return "ESCHERAGGREGATE";
    }

    private void buildBaseTree() {
        EscherContainerRecord dgContainer = new EscherContainerRecord();
        EscherContainerRecord spgrContainer = new EscherContainerRecord();
        EscherContainerRecord spContainer1 = new EscherContainerRecord();
        EscherSpgrRecord spgr = new EscherSpgrRecord();
        EscherSpRecord sp1 = new EscherSpRecord();
        dgContainer.setRecordId(EscherContainerRecord.DG_CONTAINER);
        dgContainer.setOptions((short)15);
        EscherDgRecord dg = new EscherDgRecord();
        dg.setRecordId(EscherDgRecord.RECORD_ID);
        int dgId = 1;
        dg.setOptions((short)(dgId << 4));
        dg.setNumShapes(0);
        dg.setLastMSOSPID(1024);
        spgrContainer.setRecordId(EscherContainerRecord.SPGR_CONTAINER);
        spgrContainer.setOptions((short)15);
        spContainer1.setRecordId(EscherContainerRecord.SP_CONTAINER);
        spContainer1.setOptions((short)15);
        spgr.setRecordId(EscherSpgrRecord.RECORD_ID);
        spgr.setOptions((short)1);
        spgr.setRectX1(0);
        spgr.setRectY1(0);
        spgr.setRectX2(1023);
        spgr.setRectY2(255);
        sp1.setRecordId(EscherSpRecord.RECORD_ID);
        sp1.setOptions((short)2);
        sp1.setVersion((short)2);
        sp1.setShapeId(-1);
        sp1.setFlags(5);
        dgContainer.addChildRecord(dg);
        dgContainer.addChildRecord(spgrContainer);
        spgrContainer.addChildRecord(spContainer1);
        spContainer1.addChildRecord(spgr);
        spContainer1.addChildRecord(sp1);
        this.addEscherRecord(dgContainer);
    }

    public void setDgId(short dgId) {
        EscherContainerRecord dgContainer = this.getEscherContainer();
        EscherDgRecord dg = (EscherDgRecord)dgContainer.getChildById(EscherDgRecord.RECORD_ID);
        if (dg != null) {
            dg.setOptions((short)(dgId << 4));
        }
    }

    public void setMainSpRecordId(int shapeId) {
        EscherContainerRecord spContainer;
        EscherSpRecord sp;
        EscherContainerRecord dgContainer = this.getEscherContainer();
        EscherContainerRecord spgrContainer = (EscherContainerRecord)dgContainer.getChildById(EscherContainerRecord.SPGR_CONTAINER);
        if (spgrContainer != null && (sp = (EscherSpRecord)(spContainer = (EscherContainerRecord)spgrContainer.getChild(0)).getChildById(EscherSpRecord.RECORD_ID)) != null) {
            sp.setShapeId(shapeId);
        }
    }

    private static short sid(RecordBase record) {
        return record instanceof Record ? ((Record)record).getSid() : (short)-1;
    }

    public Map<EscherRecord, Record> getShapeToObjMapping() {
        return Collections.unmodifiableMap(this.shapeToObj);
    }

    public Map<Integer, NoteRecord> getTailRecords() {
        return Collections.unmodifiableMap(this.tailRec);
    }

    public NoteRecord getNoteRecordByObj(ObjRecord obj) {
        CommonObjectDataSubRecord cod = (CommonObjectDataSubRecord)obj.getSubRecords().get(0);
        return this.tailRec.get(cod.getObjectId());
    }

    public void addTailRecord(NoteRecord note) {
        this.tailRec.put(note.getShapeId(), note);
    }

    public void removeTailRecord(NoteRecord note) {
        this.tailRec.remove(note.getShapeId());
    }

    @Override
    public EscherAggregate copy() {
        return new EscherAggregate(this);
    }

    private static class ShapeCollector
    extends DefaultEscherRecordFactory {
        final List<EscherRecord> objShapes = new ArrayList<EscherRecord>();
        final ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        private ShapeCollector() {
        }

        void addBytes(byte[] data) {
            try {
                this.buffer.write(data);
            } catch (IOException e) {
                throw new RuntimeException("Couldn't get data from drawing/continue records", e);
            }
        }

        @Override
        public EscherRecord createRecord(byte[] data, int offset) {
            EscherRecord r = super.createRecord(data, offset);
            short rid = r.getRecordId();
            if (rid == EscherClientDataRecord.RECORD_ID || rid == EscherTextboxRecord.RECORD_ID) {
                this.objShapes.add(r);
            }
            return r;
        }

        List<EscherRecord> parse(EscherAggregate agg) {
            int bytesRead;
            byte[] buf = this.buffer.toByteArray();
            for (int pos = 0; pos < buf.length; pos += bytesRead) {
                EscherRecord r = this.createRecord(buf, pos);
                bytesRead = r.fillFields(buf, pos, this);
                agg.addEscherRecord(r);
            }
            return this.objShapes;
        }
    }
}

