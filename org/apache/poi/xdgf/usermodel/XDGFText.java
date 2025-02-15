/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xdgf.usermodel;

import com.microsoft.schemas.office.visio.x2012.main.TextType;
import com.microsoft.schemas.office.visio.x2012.main.impl.TextTypeImpl;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import org.apache.poi.util.Internal;
import org.apache.poi.xdgf.usermodel.XDGFShape;

public class XDGFText {
    TextType _text;
    XDGFShape _parent;

    public XDGFText(TextType text, XDGFShape parent) {
        this._text = text;
        this._parent = parent;
    }

    @Internal
    TextType getXmlObject() {
        return this._text;
    }

    public String getTextContent() {
        return ((TextTypeImpl)this._text).getStringValue();
    }

    public Rectangle2D.Double getTextBounds() {
        double txtPinX = this._parent.getTxtPinX();
        double txtPinY = this._parent.getTxtPinY();
        double txtLocPinX = this._parent.getTxtLocPinX();
        double txtLocPinY = this._parent.getTxtLocPinY();
        double txtWidth = this._parent.getTxtWidth();
        double txtHeight = this._parent.getTxtHeight();
        double x = txtPinX - txtLocPinX;
        double y = txtPinY - txtLocPinY;
        return new Rectangle2D.Double(x, y, txtWidth, txtHeight);
    }

    public Path2D.Double getBoundsAsPath() {
        Rectangle2D.Double rect = this.getTextBounds();
        double w = rect.getWidth();
        double h = rect.getHeight();
        Path2D.Double bounds = new Path2D.Double();
        bounds.moveTo(0.0, 0.0);
        bounds.lineTo(w, 0.0);
        bounds.lineTo(w, h);
        bounds.lineTo(0.0, h);
        bounds.lineTo(0.0, 0.0);
        return bounds;
    }

    public Point2D.Double getTextCenter() {
        return new Point2D.Double(this._parent.getTxtLocPinX(), this._parent.getTxtLocPinY());
    }

    public void draw(Graphics2D graphics) {
        Double txtAngle;
        String textContent = this.getTextContent();
        if (textContent.length() == 0) {
            return;
        }
        Rectangle2D.Double bounds = this.getTextBounds();
        String[] lines = textContent.trim().split("\n");
        FontRenderContext frc = graphics.getFontRenderContext();
        Font font = graphics.getFont();
        AffineTransform oldTr = graphics.getTransform();
        Boolean flipX = this._parent.getFlipX();
        Boolean flipY = this._parent.getFlipY();
        if (flipY == null || !this._parent.getFlipY().booleanValue()) {
            graphics.translate(bounds.x, bounds.y);
            graphics.scale(1.0, -1.0);
            graphics.translate(0.0, -bounds.height + graphics.getFontMetrics().getMaxCharBounds(graphics).getHeight());
        }
        if (flipX != null && this._parent.getFlipX().booleanValue()) {
            graphics.scale(-1.0, 1.0);
            graphics.translate(-bounds.width, 0.0);
        }
        if ((txtAngle = this._parent.getTxtAngle()) != null && Math.abs(txtAngle) > 0.01) {
            graphics.rotate(txtAngle);
        }
        float nextY = 0.0f;
        for (String line : lines) {
            if (line.length() == 0) continue;
            TextLayout layout = new TextLayout(line, font, frc);
            if (layout.isLeftToRight()) {
                layout.draw(graphics, 0.0f, nextY);
            } else {
                layout.draw(graphics, (float)(bounds.width - (double)layout.getAdvance()), nextY);
            }
            nextY += layout.getAscent() + layout.getDescent() + layout.getLeading();
        }
        graphics.setTransform(oldTr);
    }
}

