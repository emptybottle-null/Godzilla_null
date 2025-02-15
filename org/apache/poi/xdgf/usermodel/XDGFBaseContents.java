/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xdgf.usermodel;

import com.microsoft.schemas.office.visio.x2012.main.ConnectType;
import com.microsoft.schemas.office.visio.x2012.main.PageContentsType;
import com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.util.Internal;
import org.apache.poi.xdgf.exceptions.XDGFException;
import org.apache.poi.xdgf.usermodel.XDGFConnection;
import org.apache.poi.xdgf.usermodel.XDGFShape;
import org.apache.poi.xdgf.usermodel.shape.ShapeRenderer;
import org.apache.poi.xdgf.usermodel.shape.ShapeVisitor;
import org.apache.poi.xdgf.usermodel.shape.exceptions.StopVisiting;
import org.apache.poi.xdgf.xml.XDGFXMLDocumentPart;
import org.apache.xmlbeans.XmlObject;

public class XDGFBaseContents
extends XDGFXMLDocumentPart {
    protected PageContentsType _pageContents;
    protected List<XDGFShape> _toplevelShapes = new ArrayList<XDGFShape>();
    protected Map<Long, XDGFShape> _shapes = new HashMap<Long, XDGFShape>();
    protected List<XDGFConnection> _connections = new ArrayList<XDGFConnection>();

    public XDGFBaseContents(PackagePart part) {
        super(part);
    }

    @Internal
    public PageContentsType getXmlObject() {
        return this._pageContents;
    }

    @Override
    protected void onDocumentRead() {
        if (this._pageContents.isSetShapes()) {
            for (XmlObject xmlObject : this._pageContents.getShapes().getShapeArray()) {
                XDGFShape shape = new XDGFShape((ShapeSheetType)xmlObject, this, this._document);
                this._toplevelShapes.add(shape);
                this.addToShapeIndex(shape);
            }
        }
        if (this._pageContents.isSetConnects()) {
            for (XmlObject xmlObject : this._pageContents.getConnects().getConnectArray()) {
                XDGFShape from = this._shapes.get(xmlObject.getFromSheet());
                XDGFShape to = this._shapes.get(xmlObject.getToSheet());
                if (from == null) {
                    throw new POIXMLException(this + "; Connect; Invalid from id: " + xmlObject.getFromSheet());
                }
                if (to == null) {
                    throw new POIXMLException(this + "; Connect; Invalid to id: " + xmlObject.getToSheet());
                }
                this._connections.add(new XDGFConnection((ConnectType)xmlObject, from, to));
            }
        }
    }

    protected void addToShapeIndex(XDGFShape shape) {
        this._shapes.put(shape.getID(), shape);
        List<XDGFShape> shapes = shape.getShapes();
        if (shapes == null) {
            return;
        }
        for (XDGFShape subshape : shapes) {
            this.addToShapeIndex(subshape);
        }
    }

    public void draw(Graphics2D graphics) {
        this.visitShapes(new ShapeRenderer(graphics));
    }

    public XDGFShape getShapeById(long id) {
        return this._shapes.get(id);
    }

    public Map<Long, XDGFShape> getShapesMap() {
        return Collections.unmodifiableMap(this._shapes);
    }

    public Collection<XDGFShape> getShapes() {
        return this._shapes.values();
    }

    public List<XDGFShape> getTopLevelShapes() {
        return Collections.unmodifiableList(this._toplevelShapes);
    }

    public List<XDGFConnection> getConnections() {
        return Collections.unmodifiableList(this._connections);
    }

    @Override
    public String toString() {
        return this.getPackagePart().getPartName().toString();
    }

    public void visitShapes(ShapeVisitor visitor) {
        try {
            for (XDGFShape shape : this._toplevelShapes) {
                shape.visitShapes(visitor, new AffineTransform(), 0);
            }
        } catch (StopVisiting stopVisiting) {
        } catch (POIXMLException e) {
            throw XDGFException.wrap(this, e);
        }
    }
}

