/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.usermodel;

import java.util.List;
import java.util.Map;
import org.apache.poi.ddf.EscherClientDataRecord;
import org.apache.poi.ddf.EscherContainerRecord;
import org.apache.poi.ddf.EscherOptRecord;
import org.apache.poi.ddf.EscherPropertyTypes;
import org.apache.poi.ddf.EscherRecord;
import org.apache.poi.ddf.EscherRecordTypes;
import org.apache.poi.hssf.record.CommonObjectDataSubRecord;
import org.apache.poi.hssf.record.EmbeddedObjectRefSubRecord;
import org.apache.poi.hssf.record.EscherAggregate;
import org.apache.poi.hssf.record.ObjRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.SubRecord;
import org.apache.poi.hssf.record.TextObjectRecord;
import org.apache.poi.hssf.usermodel.HSSFCombobox;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFObjectData;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFPolygon;
import org.apache.poi.hssf.usermodel.HSSFShapeContainer;
import org.apache.poi.hssf.usermodel.HSSFShapeGroup;
import org.apache.poi.hssf.usermodel.HSSFSimpleShape;
import org.apache.poi.hssf.usermodel.HSSFTextbox;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.util.RecordFormatException;

public class HSSFShapeFactory {
    public static void createShapeTree(EscherContainerRecord container, EscherAggregate agg, HSSFShapeContainer out, DirectoryNode root) {
        if (container.getRecordId() == EscherContainerRecord.SPGR_CONTAINER) {
            ObjRecord obj = null;
            EscherClientDataRecord clientData = (EscherClientDataRecord)((EscherContainerRecord)container.getChild(0)).getChildById(EscherClientDataRecord.RECORD_ID);
            if (null != clientData) {
                obj = (ObjRecord)agg.getShapeToObjMapping().get(clientData);
            }
            HSSFShapeGroup group = new HSSFShapeGroup(container, obj);
            List<EscherContainerRecord> children = container.getChildContainers();
            if (children.size() > 1) {
                children.subList(1, children.size()).forEach(c -> HSSFShapeFactory.createShapeTree(c, agg, group, root));
            }
            out.addShape(group);
        } else if (container.getRecordId() == EscherContainerRecord.SP_CONTAINER) {
            HSSFSimpleShape shape;
            Map<EscherRecord, Record> shapeToObj = agg.getShapeToObjMapping();
            ObjRecord objRecord = null;
            TextObjectRecord txtRecord = null;
            for (EscherRecord record : container) {
                switch (EscherRecordTypes.forTypeID(record.getRecordId())) {
                    case CLIENT_DATA: {
                        objRecord = (ObjRecord)shapeToObj.get(record);
                        break;
                    }
                    case CLIENT_TEXTBOX: {
                        txtRecord = (TextObjectRecord)shapeToObj.get(record);
                        break;
                    }
                }
            }
            if (objRecord == null) {
                throw new RecordFormatException("EscherClientDataRecord can't be found.");
            }
            if (HSSFShapeFactory.isEmbeddedObject(objRecord)) {
                HSSFObjectData objectData = new HSSFObjectData(container, objRecord, root);
                out.addShape(objectData);
                return;
            }
            CommonObjectDataSubRecord cmo = (CommonObjectDataSubRecord)objRecord.getSubRecords().get(0);
            switch (cmo.getObjectType()) {
                case 8: {
                    shape = new HSSFPicture(container, objRecord);
                    break;
                }
                case 2: {
                    shape = new HSSFSimpleShape(container, objRecord, txtRecord);
                    break;
                }
                case 1: {
                    shape = new HSSFSimpleShape(container, objRecord);
                    break;
                }
                case 20: {
                    shape = new HSSFCombobox(container, objRecord);
                    break;
                }
                case 30: {
                    EscherOptRecord optRecord = (EscherOptRecord)container.getChildById(EscherOptRecord.RECORD_ID);
                    if (optRecord == null) {
                        shape = new HSSFSimpleShape(container, objRecord, txtRecord);
                        break;
                    }
                    Object property = optRecord.lookup(EscherPropertyTypes.GEOMETRY__VERTICES);
                    if (null != property) {
                        shape = new HSSFPolygon(container, objRecord, txtRecord);
                        break;
                    }
                    shape = new HSSFSimpleShape(container, objRecord, txtRecord);
                    break;
                }
                case 6: {
                    shape = new HSSFTextbox(container, objRecord, txtRecord);
                    break;
                }
                case 25: {
                    shape = new HSSFComment(container, objRecord, txtRecord, agg.getNoteRecordByObj(objRecord));
                    break;
                }
                default: {
                    shape = new HSSFSimpleShape(container, objRecord, txtRecord);
                }
            }
            out.addShape(shape);
        }
    }

    private static boolean isEmbeddedObject(ObjRecord obj) {
        for (SubRecord sub : obj.getSubRecords()) {
            if (!(sub instanceof EmbeddedObjectRefSubRecord)) continue;
            return true;
        }
        return false;
    }
}

