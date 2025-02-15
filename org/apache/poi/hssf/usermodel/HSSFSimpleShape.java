/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.usermodel;

import org.apache.poi.ddf.DefaultEscherRecordFactory;
import org.apache.poi.ddf.EscherBoolProperty;
import org.apache.poi.ddf.EscherClientDataRecord;
import org.apache.poi.ddf.EscherContainerRecord;
import org.apache.poi.ddf.EscherOptRecord;
import org.apache.poi.ddf.EscherPropertyTypes;
import org.apache.poi.ddf.EscherRGBProperty;
import org.apache.poi.ddf.EscherRecord;
import org.apache.poi.ddf.EscherShapePathProperty;
import org.apache.poi.ddf.EscherSimpleProperty;
import org.apache.poi.ddf.EscherSpRecord;
import org.apache.poi.ddf.EscherTextboxRecord;
import org.apache.poi.hssf.record.CommonObjectDataSubRecord;
import org.apache.poi.hssf.record.EndSubRecord;
import org.apache.poi.hssf.record.EscherAggregate;
import org.apache.poi.hssf.record.ObjRecord;
import org.apache.poi.hssf.record.TextObjectRecord;
import org.apache.poi.hssf.usermodel.HSSFAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.SimpleShape;

public class HSSFSimpleShape
extends HSSFShape
implements SimpleShape {
    public static final short OBJECT_TYPE_LINE = 20;
    public static final short OBJECT_TYPE_RECTANGLE = 1;
    public static final short OBJECT_TYPE_OVAL = 3;
    public static final short OBJECT_TYPE_ARC = 19;
    public static final short OBJECT_TYPE_PICTURE = 75;
    public static final short OBJECT_TYPE_COMBO_BOX = 201;
    public static final short OBJECT_TYPE_COMMENT = 202;
    public static final short OBJECT_TYPE_MICROSOFT_OFFICE_DRAWING = 30;
    public static final int WRAP_SQUARE = 0;
    public static final int WRAP_BY_POINTS = 1;
    public static final int WRAP_NONE = 2;
    private TextObjectRecord _textObjectRecord;

    public HSSFSimpleShape(EscherContainerRecord spContainer, ObjRecord objRecord, TextObjectRecord textObjectRecord) {
        super(spContainer, objRecord);
        this._textObjectRecord = textObjectRecord;
    }

    public HSSFSimpleShape(EscherContainerRecord spContainer, ObjRecord objRecord) {
        super(spContainer, objRecord);
    }

    public HSSFSimpleShape(HSSFShape parent, HSSFAnchor anchor) {
        super(parent, anchor);
        this._textObjectRecord = this.createTextObjRecord();
    }

    protected TextObjectRecord getTextObjectRecord() {
        return this._textObjectRecord;
    }

    protected TextObjectRecord createTextObjRecord() {
        TextObjectRecord obj = new TextObjectRecord();
        obj.setHorizontalTextAlignment(2);
        obj.setVerticalTextAlignment(2);
        obj.setTextLocked(true);
        obj.setTextOrientation(0);
        obj.setStr(new HSSFRichTextString(""));
        return obj;
    }

    @Override
    protected EscherContainerRecord createSpContainer() {
        EscherContainerRecord spContainer = new EscherContainerRecord();
        spContainer.setRecordId(EscherContainerRecord.SP_CONTAINER);
        spContainer.setOptions((short)15);
        EscherSpRecord sp = new EscherSpRecord();
        sp.setRecordId(EscherSpRecord.RECORD_ID);
        sp.setFlags(2560);
        sp.setVersion((short)2);
        EscherClientDataRecord clientData = new EscherClientDataRecord();
        clientData.setRecordId(EscherClientDataRecord.RECORD_ID);
        clientData.setOptions((short)0);
        EscherOptRecord optRecord = new EscherOptRecord();
        optRecord.setEscherProperty(new EscherSimpleProperty(EscherPropertyTypes.LINESTYLE__LINEDASHING, 0));
        optRecord.setEscherProperty(new EscherBoolProperty(EscherPropertyTypes.LINESTYLE__NOLINEDRAWDASH, 524296));
        optRecord.setEscherProperty(new EscherRGBProperty(EscherPropertyTypes.FILL__FILLCOLOR, 0x8000009));
        optRecord.setEscherProperty(new EscherRGBProperty(EscherPropertyTypes.LINESTYLE__COLOR, 0x8000040));
        optRecord.setEscherProperty(new EscherBoolProperty(EscherPropertyTypes.FILL__NOFILLHITTEST, 65536));
        optRecord.setEscherProperty(new EscherBoolProperty(EscherPropertyTypes.LINESTYLE__NOLINEDRAWDASH, 524296));
        optRecord.setEscherProperty(new EscherShapePathProperty(EscherPropertyTypes.GEOMETRY__SHAPEPATH, 4));
        optRecord.setEscherProperty(new EscherBoolProperty(EscherPropertyTypes.GROUPSHAPE__FLAGS, 524288));
        optRecord.setRecordId(EscherOptRecord.RECORD_ID);
        EscherTextboxRecord escherTextbox = new EscherTextboxRecord();
        escherTextbox.setRecordId(EscherTextboxRecord.RECORD_ID);
        escherTextbox.setOptions((short)0);
        spContainer.addChildRecord(sp);
        spContainer.addChildRecord(optRecord);
        spContainer.addChildRecord(this.getAnchor().getEscherAnchor());
        spContainer.addChildRecord(clientData);
        spContainer.addChildRecord(escherTextbox);
        return spContainer;
    }

    @Override
    protected ObjRecord createObjRecord() {
        ObjRecord obj = new ObjRecord();
        CommonObjectDataSubRecord c = new CommonObjectDataSubRecord();
        c.setLocked(true);
        c.setPrintable(true);
        c.setAutofill(true);
        c.setAutoline(true);
        EndSubRecord e = new EndSubRecord();
        obj.addSubRecord(c);
        obj.addSubRecord(e);
        return obj;
    }

    @Override
    protected void afterRemove(HSSFPatriarch patriarch) {
        patriarch.getBoundAggregate().removeShapeToObjRecord((EscherRecord)this.getEscherContainer().getChildById(EscherClientDataRecord.RECORD_ID));
        if (null != this.getEscherContainer().getChildById(EscherTextboxRecord.RECORD_ID)) {
            patriarch.getBoundAggregate().removeShapeToObjRecord((EscherRecord)this.getEscherContainer().getChildById(EscherTextboxRecord.RECORD_ID));
        }
    }

    public HSSFRichTextString getString() {
        return this._textObjectRecord.getStr();
    }

    public void setString(RichTextString string) {
        if (this.getShapeType() == 0 || this.getShapeType() == 20) {
            throw new IllegalStateException("Cannot set text for shape type: " + this.getShapeType());
        }
        HSSFRichTextString rtr = (HSSFRichTextString)string;
        if (rtr.numFormattingRuns() == 0) {
            rtr.applyFont((short)0);
        }
        TextObjectRecord txo = this.getOrCreateTextObjRecord();
        txo.setStr(rtr);
        if (string.getString() != null) {
            this.setPropertyValue(new EscherSimpleProperty(EscherPropertyTypes.TEXT__TEXTID, string.getString().hashCode()));
        }
    }

    @Override
    void afterInsert(HSSFPatriarch patriarch) {
        EscherAggregate agg = patriarch.getBoundAggregate();
        agg.associateShapeToObjRecord((EscherRecord)this.getEscherContainer().getChildById(EscherClientDataRecord.RECORD_ID), this.getObjRecord());
        if (null != this.getTextObjectRecord()) {
            agg.associateShapeToObjRecord((EscherRecord)this.getEscherContainer().getChildById(EscherTextboxRecord.RECORD_ID), this.getTextObjectRecord());
        }
    }

    @Override
    protected HSSFShape cloneShape() {
        TextObjectRecord txo = null;
        EscherContainerRecord spContainer = new EscherContainerRecord();
        byte[] inSp = this.getEscherContainer().serialize();
        spContainer.fillFields(inSp, 0, new DefaultEscherRecordFactory());
        ObjRecord obj = (ObjRecord)this.getObjRecord().cloneViaReserialise();
        if (this.getTextObjectRecord() != null && this.getString() != null && null != this.getString().getString()) {
            txo = (TextObjectRecord)this.getTextObjectRecord().cloneViaReserialise();
        }
        return new HSSFSimpleShape(spContainer, obj, txo);
    }

    public int getShapeType() {
        EscherSpRecord spRecord = (EscherSpRecord)this.getEscherContainer().getChildById(EscherSpRecord.RECORD_ID);
        return spRecord.getShapeType();
    }

    public int getWrapText() {
        EscherSimpleProperty property = (EscherSimpleProperty)this.getOptRecord().lookup(EscherPropertyTypes.TEXT__WRAPTEXT);
        return null == property ? 0 : property.getPropertyValue();
    }

    public void setWrapText(int value) {
        this.setPropertyValue(new EscherSimpleProperty(EscherPropertyTypes.TEXT__WRAPTEXT, false, false, value));
    }

    public void setShapeType(int value) {
        CommonObjectDataSubRecord cod = (CommonObjectDataSubRecord)this.getObjRecord().getSubRecords().get(0);
        cod.setObjectType((short)30);
        EscherSpRecord spRecord = (EscherSpRecord)this.getEscherContainer().getChildById(EscherSpRecord.RECORD_ID);
        spRecord.setShapeType((short)value);
    }

    private TextObjectRecord getOrCreateTextObjRecord() {
        EscherTextboxRecord escherTextbox;
        if (this.getTextObjectRecord() == null) {
            this._textObjectRecord = this.createTextObjRecord();
        }
        if (null == (escherTextbox = (EscherTextboxRecord)this.getEscherContainer().getChildById(EscherTextboxRecord.RECORD_ID))) {
            escherTextbox = new EscherTextboxRecord();
            escherTextbox.setRecordId(EscherTextboxRecord.RECORD_ID);
            escherTextbox.setOptions((short)0);
            this.getEscherContainer().addChildRecord(escherTextbox);
            this.getPatriarch().getBoundAggregate().associateShapeToObjRecord(escherTextbox, this._textObjectRecord);
        }
        return this._textObjectRecord;
    }

    @Override
    public int getShapeId() {
        return super.getShapeId();
    }
}

