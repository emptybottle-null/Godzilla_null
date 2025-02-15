/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.usermodel;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.ddf.EscherComplexProperty;
import org.apache.poi.ddf.EscherContainerRecord;
import org.apache.poi.ddf.EscherDgRecord;
import org.apache.poi.ddf.EscherOptRecord;
import org.apache.poi.ddf.EscherProperty;
import org.apache.poi.ddf.EscherSpRecord;
import org.apache.poi.ddf.EscherSpgrRecord;
import org.apache.poi.hssf.model.DrawingManager2;
import org.apache.poi.hssf.record.CommonObjectDataSubRecord;
import org.apache.poi.hssf.record.EmbeddedObjectRefSubRecord;
import org.apache.poi.hssf.record.EndSubRecord;
import org.apache.poi.hssf.record.EscherAggregate;
import org.apache.poi.hssf.record.FtCfSubRecord;
import org.apache.poi.hssf.record.FtPioGrbitSubRecord;
import org.apache.poi.hssf.record.NoteRecord;
import org.apache.poi.hssf.record.ObjRecord;
import org.apache.poi.hssf.usermodel.HSSFAnchor;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFCombobox;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFObjectData;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFPolygon;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFShapeContainer;
import org.apache.poi.hssf.usermodel.HSSFShapeFactory;
import org.apache.poi.hssf.usermodel.HSSFShapeGroup;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFSimpleShape;
import org.apache.poi.hssf.usermodel.HSSFTextbox;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.Internal;
import org.apache.poi.util.StringUtil;

public final class HSSFPatriarch
implements HSSFShapeContainer,
Drawing<HSSFShape> {
    private final List<HSSFShape> _shapes = new ArrayList<HSSFShape>();
    private final EscherSpgrRecord _spgrRecord;
    private final EscherContainerRecord _mainSpgrContainer;
    private EscherAggregate _boundAggregate;
    private final HSSFSheet _sheet;

    HSSFPatriarch(HSSFSheet sheet, EscherAggregate boundAggregate) {
        this._sheet = sheet;
        this._boundAggregate = boundAggregate;
        this._mainSpgrContainer = this._boundAggregate.getEscherContainer().getChildContainers().get(0);
        EscherContainerRecord spContainer = (EscherContainerRecord)this._boundAggregate.getEscherContainer().getChildContainers().get(0).getChild(0);
        this._spgrRecord = (EscherSpgrRecord)spContainer.getChildById(EscherSpgrRecord.RECORD_ID);
        this.buildShapeTree();
    }

    static HSSFPatriarch createPatriarch(HSSFPatriarch patriarch, HSSFSheet sheet) {
        HSSFPatriarch newPatriarch = new HSSFPatriarch(sheet, new EscherAggregate(true));
        newPatriarch.afterCreate();
        for (HSSFShape shape : patriarch.getChildren()) {
            HSSFShape newShape = shape instanceof HSSFShapeGroup ? ((HSSFShapeGroup)shape).cloneShape(newPatriarch) : shape.cloneShape();
            newPatriarch.onCreate(newShape);
            newPatriarch.addShape(newShape);
        }
        return newPatriarch;
    }

    protected void preSerialize() {
        Map<Integer, NoteRecord> tailRecords = this._boundAggregate.getTailRecords();
        HashSet<String> coordinates = new HashSet<String>(tailRecords.size());
        for (NoteRecord rec : tailRecords.values()) {
            String noteRef = new CellReference(rec.getRow(), rec.getColumn(), true, true).formatAsString();
            if (coordinates.contains(noteRef)) {
                throw new IllegalStateException("found multiple cell comments for cell " + noteRef);
            }
            coordinates.add(noteRef);
        }
    }

    @Override
    public boolean removeShape(HSSFShape shape) {
        boolean isRemoved = this._mainSpgrContainer.removeChildRecord(shape.getEscherContainer());
        if (isRemoved) {
            shape.afterRemove(this);
            this._shapes.remove(shape);
        }
        return isRemoved;
    }

    void afterCreate() {
        DrawingManager2 drawingManager = this._sheet.getWorkbook().getWorkbook().getDrawingManager();
        short dgId = drawingManager.findNewDrawingGroupId();
        this._boundAggregate.setDgId(dgId);
        this._boundAggregate.setMainSpRecordId(this.newShapeId());
        drawingManager.incrementDrawingsSaved();
    }

    public HSSFShapeGroup createGroup(HSSFClientAnchor anchor) {
        HSSFShapeGroup group = new HSSFShapeGroup(null, anchor);
        this.addShape(group);
        this.onCreate(group);
        return group;
    }

    public HSSFSimpleShape createSimpleShape(HSSFClientAnchor anchor) {
        HSSFSimpleShape shape = new HSSFSimpleShape(null, anchor);
        this.addShape(shape);
        this.onCreate(shape);
        return shape;
    }

    public HSSFPicture createPicture(HSSFClientAnchor anchor, int pictureIndex) {
        HSSFPicture shape = new HSSFPicture(null, anchor);
        shape.setPictureIndex(pictureIndex);
        this.addShape(shape);
        this.onCreate(shape);
        return shape;
    }

    @Override
    public HSSFPicture createPicture(ClientAnchor anchor, int pictureIndex) {
        return this.createPicture((HSSFClientAnchor)anchor, pictureIndex);
    }

    @Override
    public HSSFObjectData createObjectData(ClientAnchor anchor, int storageId, int pictureIndex) {
        DirectoryEntry oleRoot;
        ObjRecord obj = new ObjRecord();
        CommonObjectDataSubRecord ftCmo = new CommonObjectDataSubRecord();
        ftCmo.setObjectType((short)8);
        ftCmo.setLocked(true);
        ftCmo.setPrintable(true);
        ftCmo.setAutofill(true);
        ftCmo.setAutoline(true);
        ftCmo.setReserved1(0);
        ftCmo.setReserved2(0);
        ftCmo.setReserved3(0);
        obj.addSubRecord(ftCmo);
        FtCfSubRecord ftCf = new FtCfSubRecord();
        HSSFPictureData pictData = this.getSheet().getWorkbook().getAllPictures().get(pictureIndex - 1);
        switch (pictData.getFormat()) {
            case 2: 
            case 3: {
                ftCf.setFlags((short)2);
                break;
            }
            case 4: 
            case 5: 
            case 6: 
            case 7: {
                ftCf.setFlags((short)9);
                break;
            }
            default: {
                throw new IllegalStateException("Invalid picture type: " + pictData.getFormat());
            }
        }
        obj.addSubRecord(ftCf);
        FtPioGrbitSubRecord ftPioGrbit = new FtPioGrbitSubRecord();
        ftPioGrbit.setFlagByBit(1, true);
        obj.addSubRecord(ftPioGrbit);
        EmbeddedObjectRefSubRecord ftPictFmla = new EmbeddedObjectRefSubRecord();
        ftPictFmla.setUnknownFormulaData(new byte[]{2, 0, 0, 0, 0});
        ftPictFmla.setOleClassname("Paket");
        ftPictFmla.setStorageId(storageId);
        obj.addSubRecord(ftPictFmla);
        obj.addSubRecord(new EndSubRecord());
        String entryName = "MBD" + HexDump.toHex(storageId);
        try {
            DirectoryNode dn = this._sheet.getWorkbook().getDirectory();
            if (dn == null) {
                throw new FileNotFoundException();
            }
            oleRoot = (DirectoryEntry)dn.getEntry(entryName);
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("trying to add ole shape without actually adding data first - use HSSFWorkbook.addOlePackage first", e);
        }
        HSSFPicture shape = new HSSFPicture(null, (HSSFClientAnchor)anchor);
        shape.setPictureIndex(pictureIndex);
        EscherContainerRecord spContainer = shape.getEscherContainer();
        EscherSpRecord spRecord = (EscherSpRecord)spContainer.getChildById(EscherSpRecord.RECORD_ID);
        spRecord.setFlags(spRecord.getFlags() | 0x10);
        HSSFObjectData oleShape = new HSSFObjectData(spContainer, obj, oleRoot);
        this.addShape(oleShape);
        this.onCreate(oleShape);
        return oleShape;
    }

    public HSSFPolygon createPolygon(HSSFClientAnchor anchor) {
        HSSFPolygon shape = new HSSFPolygon(null, anchor);
        this.addShape(shape);
        this.onCreate(shape);
        return shape;
    }

    public HSSFTextbox createTextbox(HSSFClientAnchor anchor) {
        HSSFTextbox shape = new HSSFTextbox(null, anchor);
        this.addShape(shape);
        this.onCreate(shape);
        return shape;
    }

    public HSSFComment createComment(HSSFAnchor anchor) {
        HSSFComment shape = new HSSFComment(null, anchor);
        this.addShape(shape);
        this.onCreate(shape);
        return shape;
    }

    HSSFSimpleShape createComboBox(HSSFAnchor anchor) {
        HSSFCombobox shape = new HSSFCombobox(null, anchor);
        this.addShape(shape);
        this.onCreate(shape);
        return shape;
    }

    @Override
    public HSSFComment createCellComment(ClientAnchor anchor) {
        return this.createComment((HSSFAnchor)((Object)anchor));
    }

    @Override
    public List<HSSFShape> getChildren() {
        return Collections.unmodifiableList(this._shapes);
    }

    @Override
    @Internal
    public void addShape(HSSFShape shape) {
        shape.setPatriarch(this);
        this._shapes.add(shape);
    }

    private void onCreate(HSSFShape shape) {
        EscherContainerRecord spgrContainer = this._boundAggregate.getEscherContainer().getChildContainers().get(0);
        EscherContainerRecord spContainer = shape.getEscherContainer();
        int shapeId = this.newShapeId();
        shape.setShapeId(shapeId);
        spgrContainer.addChildRecord(spContainer);
        shape.afterInsert(this);
        this.setFlipFlags(shape);
    }

    public int countOfAllChildren() {
        int count = this._shapes.size();
        for (HSSFShape shape : this._shapes) {
            count += shape.countOfAllChildren();
        }
        return count;
    }

    @Override
    public void setCoordinates(int x1, int y1, int x2, int y2) {
        this._spgrRecord.setRectY1(y1);
        this._spgrRecord.setRectY2(y2);
        this._spgrRecord.setRectX1(x1);
        this._spgrRecord.setRectX2(x2);
    }

    @Override
    public void clear() {
        ArrayList<HSSFShape> copy = new ArrayList<HSSFShape>(this._shapes);
        for (HSSFShape shape : copy) {
            this.removeShape(shape);
        }
    }

    int newShapeId() {
        DrawingManager2 dm = this._sheet.getWorkbook().getWorkbook().getDrawingManager();
        EscherDgRecord dg = (EscherDgRecord)this._boundAggregate.getEscherContainer().getChildById(EscherDgRecord.RECORD_ID);
        return dm.allocateShapeId(dg);
    }

    public boolean containsChart() {
        EscherOptRecord optRecord = (EscherOptRecord)this._boundAggregate.findFirstWithId(EscherOptRecord.RECORD_ID);
        if (optRecord == null) {
            return false;
        }
        for (EscherProperty prop : optRecord.getEscherProperties()) {
            EscherComplexProperty cp;
            String str;
            if (prop.getPropertyNumber() != 896 || !prop.isComplex() || !(str = StringUtil.getFromUnicodeLE((cp = (EscherComplexProperty)prop).getComplexData())).equals("Chart 1\u0000")) continue;
            return true;
        }
        return false;
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

    @Internal
    public EscherAggregate getBoundAggregate() {
        return this._boundAggregate;
    }

    @Override
    public HSSFClientAnchor createAnchor(int dx1, int dy1, int dx2, int dy2, int col1, int row1, int col2, int row2) {
        return new HSSFClientAnchor(dx1, dy1, dx2, dy2, (short)col1, row1, (short)col2, row2);
    }

    void buildShapeTree() {
        EscherContainerRecord dgContainer = this._boundAggregate.getEscherContainer();
        if (dgContainer == null) {
            return;
        }
        EscherContainerRecord spgrConrainer = dgContainer.getChildContainers().get(0);
        List<EscherContainerRecord> spgrChildren = spgrConrainer.getChildContainers();
        for (int i = 0; i < spgrChildren.size(); ++i) {
            EscherContainerRecord spContainer = spgrChildren.get(i);
            if (i == 0) continue;
            HSSFShapeFactory.createShapeTree(spContainer, this._boundAggregate, this, this._sheet.getWorkbook().getDirectory());
        }
    }

    private void setFlipFlags(HSSFShape shape) {
        EscherSpRecord sp = (EscherSpRecord)shape.getEscherContainer().getChildById(EscherSpRecord.RECORD_ID);
        if (shape.getAnchor().isHorizontallyFlipped()) {
            sp.setFlags(sp.getFlags() | 0x40);
        }
        if (shape.getAnchor().isVerticallyFlipped()) {
            sp.setFlags(sp.getFlags() | 0x80);
        }
    }

    @Override
    public Iterator<HSSFShape> iterator() {
        return this._shapes.iterator();
    }

    protected HSSFSheet getSheet() {
        return this._sheet;
    }
}

