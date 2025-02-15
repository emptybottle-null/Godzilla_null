/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg;

import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.SVGRoot;
import com.kitfox.svg.SVGUniverse;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SVGDiagram
implements Serializable {
    public static final long serialVersionUID = 0L;
    final HashMap<String, SVGElement> idMap = new HashMap();
    SVGRoot root;
    final SVGUniverse universe;
    private Rectangle deviceViewport = new Rectangle(100, 100);
    protected boolean ignoreClipHeuristic = false;
    final URI xmlBase;

    public SVGDiagram(URI xmlBase, SVGUniverse universe) {
        this.universe = universe;
        this.xmlBase = xmlBase;
    }

    public void render(Graphics2D g) throws SVGException {
        this.root.renderToViewport(g);
    }

    public List<List<SVGElement>> pick(Point2D point, List<List<SVGElement>> retVec) throws SVGException {
        return this.pick(point, false, retVec);
    }

    public List<List<SVGElement>> pick(Point2D point, boolean boundingBox, List<List<SVGElement>> retVec) throws SVGException {
        if (retVec == null) {
            retVec = new ArrayList<List<SVGElement>>();
        }
        this.root.pick(point, boundingBox, retVec);
        return retVec;
    }

    public List<List<SVGElement>> pick(Rectangle2D pickArea, List<List<SVGElement>> retVec) throws SVGException {
        return this.pick(pickArea, false, retVec);
    }

    public List<List<SVGElement>> pick(Rectangle2D pickArea, boolean boundingBox, List<List<SVGElement>> retVec) throws SVGException {
        if (retVec == null) {
            retVec = new ArrayList<List<SVGElement>>();
        }
        this.root.pick(pickArea, new AffineTransform(), boundingBox, retVec);
        return retVec;
    }

    public SVGUniverse getUniverse() {
        return this.universe;
    }

    public URI getXMLBase() {
        return this.xmlBase;
    }

    public float getWidth() {
        if (this.root == null) {
            return 0.0f;
        }
        return this.root.getDeviceWidth();
    }

    public float getHeight() {
        if (this.root == null) {
            return 0.0f;
        }
        return this.root.getDeviceHeight();
    }

    public Rectangle2D getViewRect(Rectangle2D rect) {
        if (this.root != null) {
            return this.root.getDeviceRect(rect);
        }
        return rect;
    }

    public Rectangle2D getViewRect() {
        return this.getViewRect(new Rectangle2D.Double());
    }

    public SVGElement getElement(String name) {
        return this.idMap.get(name);
    }

    public void setElement(String name, SVGElement node) {
        this.idMap.put(name, node);
    }

    public void removeElement(String name) {
        this.idMap.remove(name);
    }

    public SVGRoot getRoot() {
        return this.root;
    }

    public void setRoot(SVGRoot root) {
        this.root = root;
        root.setDiagram(this);
    }

    public boolean ignoringClipHeuristic() {
        return this.ignoreClipHeuristic;
    }

    public void setIgnoringClipHeuristic(boolean ignoreClipHeuristic) {
        this.ignoreClipHeuristic = ignoreClipHeuristic;
    }

    public void updateTime(double curTime) throws SVGException {
        if (this.root == null) {
            return;
        }
        this.root.updateTime(curTime);
    }

    public Rectangle getDeviceViewport() {
        return this.deviceViewport;
    }

    public void setDeviceViewport(Rectangle deviceViewport) {
        this.deviceViewport.setBounds(deviceViewport);
        if (this.root != null) {
            try {
                this.root.build();
            } catch (SVGException ex) {
                Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, "Could not build document", ex);
            }
        }
    }
}

