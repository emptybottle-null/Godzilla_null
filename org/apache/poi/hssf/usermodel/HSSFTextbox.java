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
import org.apache.poi.hssf.usermodel.HSSFSimpleShape;

public class HSSFTextbox
extends HSSFSimpleShape {
    public static final short OBJECT_TYPE_TEXT = 6;
    public static final short HORIZONTAL_ALIGNMENT_LEFT = 1;
    public static final short HORIZONTAL_ALIGNMENT_CENTERED = 2;
    public static final short HORIZONTAL_ALIGNMENT_RIGHT = 3;
    public static final short HORIZONTAL_ALIGNMENT_JUSTIFIED = 4;
    public static final short HORIZONTAL_ALIGNMENT_DISTRIBUTED = 7;
    public static final short VERTICAL_ALIGNMENT_TOP = 1;
    public static final short VERTICAL_ALIGNMENT_CENTER = 2;
    public static final short VERTICAL_ALIGNMENT_BOTTOM = 3;
    public static final short VERTICAL_ALIGNMENT_JUSTIFY = 4;
    public static final short VERTICAL_ALIGNMENT_DISTRIBUTED = 7;

    public HSSFTextbox(EscherContainerRecord spContainer, ObjRecord objRecord, TextObjectRecord textObjectRecord) {
        super(spContainer, objRecord, textObjectRecord);
    }

    public HSSFTextbox(HSSFShape parent, HSSFAnchor anchor) {
        super(parent, anchor);
        this.setHorizontalAlignment((short)1);
        this.setVerticalAlignment((short)1);
        this.setString(new HSSFRichTextString(""));
    }

    @Override
    protected ObjRecord createObjRecord() {
        ObjRecord obj = new ObjRecord();
        CommonObjectDataSubRecord c = new CommonObjectDataSubRecord();
        c.setObjectType((short)6);
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
    protected EscherContainerRecord createSpContainer() {
        EscherContainerRecord spContainer = new EscherContainerRecord();
        EscherSpRecord sp = new EscherSpRecord();
        EscherOptRecord opt = new EscherOptRecord();
        EscherClientDataRecord clientData = new EscherClientDataRecord();
        EscherTextboxRecord escherTextbox = new EscherTextboxRecord();
        spContainer.setRecordId(EscherContainerRecord.SP_CONTAINER);
        spContainer.setOptions((short)15);
        sp.setRecordId(EscherSpRecord.RECORD_ID);
        sp.setOptions((short)3234);
        sp.setFlags(2560);
        opt.setRecordId(EscherOptRecord.RECORD_ID);
        opt.addEscherProperty(new EscherSimpleProperty(EscherPropertyTypes.TEXT__TEXTID, 0));
        opt.addEscherProperty(new EscherSimpleProperty(EscherPropertyTypes.TEXT__WRAPTEXT, 0));
        opt.addEscherProperty(new EscherSimpleProperty(EscherPropertyTypes.TEXT__ANCHORTEXT, 0));
        opt.addEscherProperty(new EscherSimpleProperty(EscherPropertyTypes.GROUPSHAPE__FLAGS, 524288));
        opt.addEscherProperty(new EscherSimpleProperty(EscherPropertyTypes.TEXT__TEXTLEFT, 0));
        opt.addEscherProperty(new EscherSimpleProperty(EscherPropertyTypes.TEXT__TEXTRIGHT, 0));
        opt.addEscherProperty(new EscherSimpleProperty(EscherPropertyTypes.TEXT__TEXTTOP, 0));
        opt.addEscherProperty(new EscherSimpleProperty(EscherPropertyTypes.TEXT__TEXTBOTTOM, 0));
        opt.setEscherProperty(new EscherSimpleProperty(EscherPropertyTypes.LINESTYLE__LINEDASHING, 0));
        opt.setEscherProperty(new EscherBoolProperty(EscherPropertyTypes.LINESTYLE__NOLINEDRAWDASH, 524296));
        opt.setEscherProperty(new EscherSimpleProperty(EscherPropertyTypes.LINESTYLE__LINEWIDTH, 9525));
        opt.setEscherProperty(new EscherRGBProperty(EscherPropertyTypes.FILL__FILLCOLOR, 0x8000009));
        opt.setEscherProperty(new EscherRGBProperty(EscherPropertyTypes.LINESTYLE__COLOR, 0x8000040));
        opt.setEscherProperty(new EscherBoolProperty(EscherPropertyTypes.FILL__NOFILLHITTEST, 65536));
        opt.setEscherProperty(new EscherBoolProperty(EscherPropertyTypes.GROUPSHAPE__FLAGS, 524288));
        EscherRecord anchor = this.getAnchor().getEscherAnchor();
        clientData.setRecordId(EscherClientDataRecord.RECORD_ID);
        clientData.setOptions((short)0);
        escherTextbox.setRecordId(EscherTextboxRecord.RECORD_ID);
        escherTextbox.setOptions((short)0);
        spContainer.addChildRecord(sp);
        spContainer.addChildRecord(opt);
        spContainer.addChildRecord(anchor);
        spContainer.addChildRecord(clientData);
        spContainer.addChildRecord(escherTextbox);
        return spContainer;
    }

    @Override
    void afterInsert(HSSFPatriarch patriarch) {
        EscherAggregate agg = patriarch.getBoundAggregate();
        agg.associateShapeToObjRecord((EscherRecord)this.getEscherContainer().getChildById(EscherClientDataRecord.RECORD_ID), this.getObjRecord());
        if (this.getTextObjectRecord() != null) {
            agg.associateShapeToObjRecord((EscherRecord)this.getEscherContainer().getChildById(EscherTextboxRecord.RECORD_ID), this.getTextObjectRecord());
        }
    }

    public int getMarginLeft() {
        EscherSimpleProperty property = (EscherSimpleProperty)this.getOptRecord().lookup(EscherPropertyTypes.TEXT__TEXTLEFT);
        return property == null ? 0 : property.getPropertyValue();
    }

    public void setMarginLeft(int marginLeft) {
        this.setPropertyValue(new EscherSimpleProperty(EscherPropertyTypes.TEXT__TEXTLEFT, marginLeft));
    }

    public int getMarginRight() {
        EscherSimpleProperty property = (EscherSimpleProperty)this.getOptRecord().lookup(EscherPropertyTypes.TEXT__TEXTRIGHT);
        return property == null ? 0 : property.getPropertyValue();
    }

    public void setMarginRight(int marginRight) {
        this.setPropertyValue(new EscherSimpleProperty(EscherPropertyTypes.TEXT__TEXTRIGHT, marginRight));
    }

    public int getMarginTop() {
        EscherSimpleProperty property = (EscherSimpleProperty)this.getOptRecord().lookup(EscherPropertyTypes.TEXT__TEXTTOP);
        return property == null ? 0 : property.getPropertyValue();
    }

    public void setMarginTop(int marginTop) {
        this.setPropertyValue(new EscherSimpleProperty(EscherPropertyTypes.TEXT__TEXTTOP, marginTop));
    }

    public int getMarginBottom() {
        EscherSimpleProperty property = (EscherSimpleProperty)this.getOptRecord().lookup(EscherPropertyTypes.TEXT__TEXTBOTTOM);
        return property == null ? 0 : property.getPropertyValue();
    }

    public void setMarginBottom(int marginBottom) {
        this.setPropertyValue(new EscherSimpleProperty(EscherPropertyTypes.TEXT__TEXTBOTTOM, marginBottom));
    }

    public short getHorizontalAlignment() {
        return (short)this.getTextObjectRecord().getHorizontalTextAlignment();
    }

    public void setHorizontalAlignment(short align) {
        this.getTextObjectRecord().setHorizontalTextAlignment(align);
    }

    public short getVerticalAlignment() {
        return (short)this.getTextObjectRecord().getVerticalTextAlignment();
    }

    public void setVerticalAlignment(short align) {
        this.getTextObjectRecord().setVerticalTextAlignment(align);
    }

    @Override
    public void setShapeType(int shapeType) {
        throw new IllegalStateException("Shape type can not be changed in " + this.getClass().getSimpleName());
    }

    @Override
    protected HSSFShape cloneShape() {
        TextObjectRecord txo = this.getTextObjectRecord() == null ? null : (TextObjectRecord)this.getTextObjectRecord().cloneViaReserialise();
        EscherContainerRecord spContainer = new EscherContainerRecord();
        byte[] inSp = this.getEscherContainer().serialize();
        spContainer.fillFields(inSp, 0, new DefaultEscherRecordFactory());
        ObjRecord obj = (ObjRecord)this.getObjRecord().cloneViaReserialise();
        return new HSSFTextbox(spContainer, obj, txo);
    }

    @Override
    protected void afterRemove(HSSFPatriarch patriarch) {
        patriarch.getBoundAggregate().removeShapeToObjRecord((EscherRecord)this.getEscherContainer().getChildById(EscherClientDataRecord.RECORD_ID));
        patriarch.getBoundAggregate().removeShapeToObjRecord((EscherRecord)this.getEscherContainer().getChildById(EscherTextboxRecord.RECORD_ID));
    }
}

