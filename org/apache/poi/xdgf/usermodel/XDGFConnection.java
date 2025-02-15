/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xdgf.usermodel;

import com.microsoft.schemas.office.visio.x2012.main.ConnectType;
import org.apache.poi.xdgf.usermodel.XDGFCell;
import org.apache.poi.xdgf.usermodel.XDGFShape;

public class XDGFConnection {
    public static final int visConnectFromError = -1;
    public static final int visFromNone = 0;
    public static final int visLeftEdge = 1;
    public static final int visCenterEdge = 2;
    public static final int visRightEdge = 3;
    public static final int visBottomEdge = 4;
    public static final int visMiddleEdge = 5;
    public static final int visTopEdge = 6;
    public static final int visBeginX = 7;
    public static final int visBeginY = 8;
    public static final int visBegin = 9;
    public static final int visEndX = 10;
    public static final int visEndY = 11;
    public static final int visEnd = 12;
    public static final int visFromAngle = 13;
    public static final int visFromPin = 14;
    public static final int visConnectToError = -1;
    public static final int visToNone = 0;
    public static final int visGuideX = 1;
    public static final int visGuideY = 2;
    public static final int visWholeShape = 3;
    public static final int visGuideIntersect = 4;
    public static final int visToAngle = 7;
    private ConnectType _connect;
    private XDGFShape _from;
    private XDGFShape _to;

    public XDGFConnection(ConnectType connect, XDGFShape from, XDGFShape to) {
        this._connect = connect;
        this._from = from;
        this._to = to;
    }

    public XDGFShape getFromShape() {
        return this._from;
    }

    public XDGFCell getFromCell() {
        return this._from.getCell(this._connect.getFromCell());
    }

    public String getFromCellName() {
        return this._connect.getFromCell();
    }

    public XDGFShape getToShape() {
        return this._to;
    }

    public String getToCellName() {
        return this._connect.getToCell();
    }

    public Integer getFromPart() {
        if (this._connect.isSetFromPart()) {
            return this._connect.getFromPart();
        }
        return null;
    }

    public Integer getToPart() {
        if (this._connect.isSetToPart()) {
            return this._connect.getToPart();
        }
        return null;
    }
}

