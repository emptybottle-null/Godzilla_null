/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xdgf.usermodel.shape;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import org.apache.poi.xdgf.usermodel.XDGFShape;
import org.apache.poi.xdgf.usermodel.shape.ShapeRenderer;
import org.apache.poi.xdgf.usermodel.shape.ShapeVisitorAcceptor;

public class ShapeDebuggerRenderer
extends ShapeRenderer {
    ShapeVisitorAcceptor _debugAcceptor;

    public ShapeDebuggerRenderer() {
    }

    public ShapeDebuggerRenderer(Graphics2D g) {
        super(g);
    }

    public void setDebugAcceptor(ShapeVisitorAcceptor acceptor) {
        this._debugAcceptor = acceptor;
    }

    @Override
    protected Path2D drawPath(XDGFShape shape) {
        Path2D path = super.drawPath(shape);
        if (this._debugAcceptor == null || this._debugAcceptor.accept(shape)) {
            Font f = this._graphics.getFont();
            this._graphics.scale(1.0, -1.0);
            this._graphics.setFont(f.deriveFont(0.05f));
            String shapeId = "" + shape.getID();
            float shapeOffset = -0.1f;
            if (shape.hasMasterShape()) {
                shapeId = shapeId + " MS:" + shape.getMasterShape().getID();
                shapeOffset -= 0.15f;
            }
            this._graphics.drawString(shapeId, shapeOffset, 0.0f);
            this._graphics.setFont(f);
            this._graphics.scale(1.0, -1.0);
        }
        return path;
    }
}

