/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.usermodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ddf.DefaultEscherRecordFactory;
import org.apache.poi.ddf.EscherBoolProperty;
import org.apache.poi.ddf.EscherChildAnchorRecord;
import org.apache.poi.ddf.EscherClientAnchorRecord;
import org.apache.poi.ddf.EscherClientDataRecord;
import org.apache.poi.ddf.EscherContainerRecord;
import org.apache.poi.ddf.EscherOptRecord;
import org.apache.poi.ddf.EscherPropertyTypes;
import org.apache.poi.ddf.EscherRecord;
import org.apache.poi.ddf.EscherRecordTypes;
import org.apache.poi.ddf.EscherSpRecord;
import org.apache.poi.ddf.EscherSpgrRecord;
import org.apache.poi.hssf.record.CommonObjectDataSubRecord;
import org.apache.poi.hssf.record.EndSubRecord;
import org.apache.poi.hssf.record.EscherAggregate;
import org.apache.poi.hssf.record.GroupMarkerSubRecord;
import org.apache.poi.hssf.record.ObjRecord;
import org.apache.poi.hssf.usermodel.HSSFAnchor;
import org.apache.poi.hssf.usermodel.HSSFChildAnchor;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFPolygon;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFShapeContainer;
import org.apache.poi.hssf.usermodel.HSSFSimpleShape;
import org.apache.poi.hssf.usermodel.HSSFTextbox;

public class HSSFShapeGroup
extends HSSFShape
implements HSSFShapeContainer {
    private final List<HSSFShape> shapes = new ArrayList<HSSFShape>();
    private EscherSpgrRecord _spgrRecord;

    public HSSFShapeGroup(EscherContainerRecord spgrContainer, ObjRecord objRecord) {
        super(spgrContainer, objRecord);
        EscherContainerRecord spContainer = spgrContainer.getChildContainers().get(0);
        this._spgrRecord = (EscherSpgrRecord)spContainer.getChild(0);
        for (EscherRecord ch : spContainer.getChildRecords()) {
            switch (EscherRecordTypes.forTypeID(ch.getRecordId())) {
                case SPGR: {
                    break;
                }
                case CLIENT_ANCHOR: {
                    this.anchor = new HSSFClientAnchor((EscherClientAnchorRecord)ch);
                    break;
                }
                case CHILD_ANCHOR: {
                    this.anchor = new HSSFChildAnchor((EscherChildAnchorRecord)ch);
                    break;
                }
            }
        }
    }

    public HSSFShapeGroup(HSSFShape parent, HSSFAnchor anchor) {
        super(parent, anchor);
        this._spgrRecord = (EscherSpgrRecord)((EscherContainerRecord)this.getEscherContainer().getChild(0)).getChildById(EscherSpgrRecord.RECORD_ID);
    }

    @Override
    protected EscherContainerRecord createSpContainer() {
        EscherContainerRecord spgrContainer = new EscherContainerRecord();
        EscherContainerRecord spContainer = new EscherContainerRecord();
        EscherSpgrRecord spgr = new EscherSpgrRecord();
        EscherSpRecord sp = new EscherSpRecord();
        EscherOptRecord opt = new EscherOptRecord();
        EscherClientDataRecord clientData = new EscherClientDataRecord();
        spgrContainer.setRecordId(EscherContainerRecord.SPGR_CONTAINER);
        spgrContainer.setOptions((short)15);
        spContainer.setRecordId(EscherContainerRecord.SP_CONTAINER);
        spContainer.setOptions((short)15);
        spgr.setRecordId(EscherSpgrRecord.RECORD_ID);
        spgr.setOptions((short)1);
        spgr.setRectX1(0);
        spgr.setRectY1(0);
        spgr.setRectX2(1023);
        spgr.setRectY2(255);
        sp.setRecordId(EscherSpRecord.RECORD_ID);
        sp.setOptions((short)2);
        if (this.getAnchor() instanceof HSSFClientAnchor) {
            sp.setFlags(513);
        } else {
            sp.setFlags(515);
        }
        opt.setRecordId(EscherOptRecord.RECORD_ID);
        opt.setOptions((short)35);
        opt.addEscherProperty(new EscherBoolProperty(EscherPropertyTypes.PROTECTION__LOCKAGAINSTGROUPING, 262148));
        opt.addEscherProperty(new EscherBoolProperty(EscherPropertyTypes.GROUPSHAPE__FLAGS, 524288));
        EscherRecord anchor = this.getAnchor().getEscherAnchor();
        clientData.setRecordId(EscherClientDataRecord.RECORD_ID);
        clientData.setOptions((short)0);
        spgrContainer.addChildRecord(spContainer);
        spContainer.addChildRecord(spgr);
        spContainer.addChildRecord(sp);
        spContainer.addChildRecord(opt);
        spContainer.addChildRecord(anchor);
        spContainer.addChildRecord(clientData);
        return spgrContainer;
    }

    @Override
    protected ObjRecord createObjRecord() {
        ObjRecord obj = new ObjRecord();
        CommonObjectDataSubRecord cmo = new CommonObjectDataSubRecord();
        cmo.setObjectType((short)0);
        cmo.setLocked(true);
        cmo.setPrintable(true);
        cmo.setAutofill(true);
        cmo.setAutoline(true);
        GroupMarkerSubRecord gmo = new GroupMarkerSubRecord();
        EndSubRecord end = new EndSubRecord();
        obj.addSubRecord(cmo);
        obj.addSubRecord(gmo);
        obj.addSubRecord(end);
        return obj;
    }

    @Override
    protected void afterRemove(HSSFPatriarch patriarch) {
        patriarch.getBoundAggregate().removeShapeToObjRecord((EscherRecord)this.getEscherContainer().getChildContainers().get(0).getChildById(EscherClientDataRecord.RECORD_ID));
        for (int i = 0; i < this.shapes.size(); ++i) {
            HSSFShape shape = this.shapes.get(i);
            this.removeShape(shape);
            shape.afterRemove(this.getPatriarch());
        }
        this.shapes.clear();
    }

    private void onCreate(HSSFShape shape) {
        if (this.getPatriarch() != null) {
            EscherContainerRecord spContainer = shape.getEscherContainer();
            int shapeId = this.getPatriarch().newShapeId();
            shape.setShapeId(shapeId);
            this.getEscherContainer().addChildRecord(spContainer);
            shape.afterInsert(this.getPatriarch());
            EscherSpRecord sp = shape instanceof HSSFShapeGroup ? (EscherSpRecord)shape.getEscherContainer().getChildContainers().get(0).getChildById(EscherSpRecord.RECORD_ID) : (EscherSpRecord)shape.getEscherContainer().getChildById(EscherSpRecord.RECORD_ID);
            sp.setFlags(sp.getFlags() | 2);
        }
    }

    public HSSFShapeGroup createGroup(HSSFChildAnchor anchor) {
        HSSFShapeGroup group = new HSSFShapeGroup(this, anchor);
        group.setParent(this);
        group.setAnchor(anchor);
        this.shapes.add(group);
        this.onCreate(group);
        return group;
    }

    @Override
    public void addShape(HSSFShape shape) {
        shape.setPatriarch(this.getPatriarch());
        shape.setParent(this);
        this.shapes.add(shape);
    }

    public HSSFSimpleShape createShape(HSSFChildAnchor anchor) {
        HSSFSimpleShape shape = new HSSFSimpleShape(this, anchor);
        shape.setParent(this);
        shape.setAnchor(anchor);
        this.shapes.add(shape);
        this.onCreate(shape);
        EscherSpRecord sp = (EscherSpRecord)shape.getEscherContainer().getChildById(EscherSpRecord.RECORD_ID);
        if (shape.getAnchor().isHorizontallyFlipped()) {
            sp.setFlags(sp.getFlags() | 0x40);
        }
        if (shape.getAnchor().isVerticallyFlipped()) {
            sp.setFlags(sp.getFlags() | 0x80);
        }
        return shape;
    }

    public HSSFTextbox createTextbox(HSSFChildAnchor anchor) {
        HSSFTextbox shape = new HSSFTextbox(this, anchor);
        shape.setParent(this);
        shape.setAnchor(anchor);
        this.shapes.add(shape);
        this.onCreate(shape);
        return shape;
    }

    public HSSFPolygon createPolygon(HSSFChildAnchor anchor) {
        HSSFPolygon shape = new HSSFPolygon(this, anchor);
        shape.setParent(this);
        shape.setAnchor(anchor);
        this.shapes.add(shape);
        this.onCreate(shape);
        return shape;
    }

    public HSSFPicture createPicture(HSSFChildAnchor anchor, int pictureIndex) {
        HSSFPicture shape = new HSSFPicture(this, anchor);
        shape.setParent(this);
        shape.setAnchor(anchor);
        shape.setPictureIndex(pictureIndex);
        this.shapes.add(shape);
        this.onCreate(shape);
        EscherSpRecord sp = (EscherSpRecord)shape.getEscherContainer().getChildById(EscherSpRecord.RECORD_ID);
        if (shape.getAnchor().isHorizontallyFlipped()) {
            sp.setFlags(sp.getFlags() | 0x40);
        }
        if (shape.getAnchor().isVerticallyFlipped()) {
            sp.setFlags(sp.getFlags() | 0x80);
        }
        return shape;
    }

    @Override
    public List<HSSFShape> getChildren() {
        return Collections.unmodifiableList(this.shapes);
    }

    @Override
    public void setCoordinates(int x1, int y1, int x2, int y2) {
        this._spgrRecord.setRectX1(x1);
        this._spgrRecord.setRectX2(x2);
        this._spgrRecord.setRectY1(y1);
        this._spgrRecord.setRectY2(y2);
    }

    @Override
    public void clear() {
        ArrayList<HSSFShape> copy = new ArrayList<HSSFShape>(this.shapes);
        for (HSSFShape shape : copy) {
            this.removeShape(shape);
        }
    }

    @Override
    public int getX1() {
        return this._spgrRecord.getRectX1();
    }

    @Override
    public int getY1() {
        return this._spgrRecord.getRectY1();
    }

    @Override
    public int getX2() {
        return this._spgrRecord.getRectX2();
    }

    @Override
    public int getY2() {
        return this._spgrRecord.getRectY2();
    }

    @Override
    public int countOfAllChildren() {
        int count = this.shapes.size();
        for (HSSFShape shape : this.shapes) {
            count += shape.countOfAllChildren();
        }
        return count;
    }

    @Override
    void afterInsert(HSSFPatriarch patriarch) {
        EscherAggregate agg = patriarch.getBoundAggregate();
        EscherContainerRecord containerRecord = (EscherContainerRecord)this.getEscherContainer().getChildById(EscherContainerRecord.SP_CONTAINER);
        agg.associateShapeToObjRecord((EscherRecord)containerRecord.getChildById(EscherClientDataRecord.RECORD_ID), this.getObjRecord());
    }

    @Override
    void setShapeId(int shapeId) {
        EscherContainerRecord containerRecord = (EscherContainerRecord)this.getEscherContainer().getChildById(EscherContainerRecord.SP_CONTAINER);
        EscherSpRecord spRecord = (EscherSpRecord)containerRecord.getChildById(EscherSpRecord.RECORD_ID);
        spRecord.setShapeId(shapeId);
        CommonObjectDataSubRecord cod = (CommonObjectDataSubRecord)this.getObjRecord().getSubRecords().get(0);
        cod.setObjectId((short)(shapeId % 1024));
    }

    @Override
    int getShapeId() {
        EscherContainerRecord containerRecord = (EscherContainerRecord)this.getEscherContainer().getChildById(EscherContainerRecord.SP_CONTAINER);
        return ((EscherSpRecord)containerRecord.getChildById(EscherSpRecord.RECORD_ID)).getShapeId();
    }

    @Override
    protected HSSFShape cloneShape() {
        throw new IllegalStateException("Use method cloneShape(HSSFPatriarch patriarch)");
    }

    protected HSSFShape cloneShape(HSSFPatriarch patriarch) {
        EscherContainerRecord spgrContainer = new EscherContainerRecord();
        spgrContainer.setRecordId(EscherContainerRecord.SPGR_CONTAINER);
        spgrContainer.setOptions((short)15);
        EscherContainerRecord spContainer = new EscherContainerRecord();
        EscherContainerRecord cont = (EscherContainerRecord)this.getEscherContainer().getChildById(EscherContainerRecord.SP_CONTAINER);
        byte[] inSp = cont.serialize();
        spContainer.fillFields(inSp, 0, new DefaultEscherRecordFactory());
        spgrContainer.addChildRecord(spContainer);
        ObjRecord obj = null;
        if (null != this.getObjRecord()) {
            obj = (ObjRecord)this.getObjRecord().cloneViaReserialise();
        }
        HSSFShapeGroup group = new HSSFShapeGroup(spgrContainer, obj);
        group.setPatriarch(patriarch);
        for (HSSFShape shape : this.getChildren()) {
            HSSFShape newShape = shape instanceof HSSFShapeGroup ? ((HSSFShapeGroup)shape).cloneShape(patriarch) : shape.cloneShape();
            group.addShape(newShape);
            group.onCreate(newShape);
        }
        return group;
    }

    @Override
    public boolean removeShape(HSSFShape shape) {
        boolean isRemoved = this.getEscherContainer().removeChildRecord(shape.getEscherContainer());
        if (isRemoved) {
            shape.afterRemove(this.getPatriarch());
            this.shapes.remove(shape);
        }
        return isRemoved;
    }

    @Override
    public Iterator<HSSFShape> iterator() {
        return this.shapes.iterator();
    }
}

