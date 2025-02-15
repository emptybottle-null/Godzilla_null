/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ddf;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.poi.ddf.EscherBSERecord;
import org.apache.poi.ddf.EscherBitmapBlip;
import org.apache.poi.ddf.EscherChildAnchorRecord;
import org.apache.poi.ddf.EscherClientAnchorRecord;
import org.apache.poi.ddf.EscherClientDataRecord;
import org.apache.poi.ddf.EscherContainerRecord;
import org.apache.poi.ddf.EscherDgRecord;
import org.apache.poi.ddf.EscherDggRecord;
import org.apache.poi.ddf.EscherMetafileBlip;
import org.apache.poi.ddf.EscherOptRecord;
import org.apache.poi.ddf.EscherRecord;
import org.apache.poi.ddf.EscherSpRecord;
import org.apache.poi.ddf.EscherSpgrRecord;
import org.apache.poi.ddf.EscherSplitMenuColorsRecord;
import org.apache.poi.ddf.EscherTertiaryOptRecord;
import org.apache.poi.ddf.EscherTextboxRecord;
import org.apache.poi.ddf.UnknownEscherRecord;

public enum EscherRecordTypes {
    DGG_CONTAINER(61440, "DggContainer", null, EscherContainerRecord::new),
    BSTORE_CONTAINER(61441, "BStoreContainer", null, EscherContainerRecord::new),
    DG_CONTAINER(61442, "DgContainer", null, EscherContainerRecord::new),
    SPGR_CONTAINER(61443, "SpgrContainer", null, EscherContainerRecord::new),
    SP_CONTAINER(61444, "SpContainer", null, EscherContainerRecord::new),
    SOLVER_CONTAINER(61445, "SolverContainer", null, EscherContainerRecord::new),
    DGG(61446, "Dgg", "MsofbtDgg", EscherDggRecord::new),
    BSE(61447, "BSE", "MsofbtBSE", EscherBSERecord::new),
    DG(61448, "Dg", "MsofbtDg", EscherDgRecord::new),
    SPGR(61449, "Spgr", "MsofbtSpgr", EscherSpgrRecord::new),
    SP(61450, "Sp", "MsofbtSp", EscherSpRecord::new),
    OPT(61451, "Opt", "msofbtOPT", EscherOptRecord::new),
    TEXTBOX(61452, null, null, EscherTextboxRecord::new),
    CLIENT_TEXTBOX(61453, "ClientTextbox", "msofbtClientTextbox", EscherTextboxRecord::new),
    ANCHOR(61454, null, null, null),
    CHILD_ANCHOR(61455, "ChildAnchor", "MsofbtChildAnchor", EscherChildAnchorRecord::new),
    CLIENT_ANCHOR(61456, "ClientAnchor", "MsofbtClientAnchor", EscherClientAnchorRecord::new),
    CLIENT_DATA(61457, "ClientData", "MsofbtClientData", EscherClientDataRecord::new),
    CONNECTOR_RULE(61458, null, null, null),
    ALIGN_RULE(61459, null, null, null),
    ARC_RULE(61460, null, null, null),
    CLIENT_RULE(61461, null, null, null),
    CLSID(61462, null, null, null),
    CALLOUT_RULE(61463, null, null, null),
    BLIP_START(61464, "Blip", "msofbtBlip", null),
    BLIP_EMF(61466, "BlipEmf", null, EscherMetafileBlip::new),
    BLIP_WMF(61467, "BlipWmf", null, EscherMetafileBlip::new),
    BLIP_PICT(61468, "BlipPict", null, EscherMetafileBlip::new),
    BLIP_JPEG(61469, "BlipJpeg", null, EscherBitmapBlip::new),
    BLIP_PNG(61470, "BlipPng", null, EscherBitmapBlip::new),
    BLIP_DIB(61471, "BlipDib", null, EscherBitmapBlip::new),
    BLIP_END(61719, "Blip", "msofbtBlip", null),
    REGROUP_ITEMS(61720, null, null, null),
    SELECTION(61721, null, null, null),
    COLOR_MRU(61722, null, null, null),
    DELETED_PSPL(61725, null, null, null),
    SPLIT_MENU_COLORS(61726, "SplitMenuColors", "MsofbtSplitMenuColors", EscherSplitMenuColorsRecord::new),
    OLE_OBJECT(61727, null, null, null),
    COLOR_SCHEME(61728, null, null, null),
    USER_DEFINED(61730, "TertiaryOpt", null, EscherTertiaryOptRecord::new),
    UNKNOWN(65535, "unknown", "unknown", UnknownEscherRecord::new);

    public final short typeID;
    public final String recordName;
    public final String description;
    public final Supplier<? extends EscherRecord> constructor;
    private static final Map<Short, EscherRecordTypes> LOOKUP;

    private EscherRecordTypes(int typeID, String recordName, String description, Supplier<? extends EscherRecord> constructor) {
        this.typeID = (short)typeID;
        this.recordName = recordName;
        this.description = description;
        this.constructor = constructor;
    }

    private Short getTypeId() {
        return this.typeID;
    }

    public static EscherRecordTypes forTypeID(int typeID) {
        EscherRecordTypes rt = LOOKUP.get((short)typeID);
        return rt != null ? rt : UNKNOWN;
    }

    static {
        LOOKUP = Stream.of(EscherRecordTypes.values()).collect(Collectors.toMap(EscherRecordTypes::getTypeId, Function.identity()));
    }
}

