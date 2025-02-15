/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.app.beans;

import com.kitfox.svg.SVGCache;
import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.SVGUniverse;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.net.URI;
import javax.swing.JPanel;

public class SVGPanel
extends JPanel {
    public static final long serialVersionUID = 1L;
    public static final String PROP_AUTOSIZE = "PROP_AUTOSIZE";
    SVGUniverse svgUniverse = SVGCache.getSVGUniverse();
    private boolean antiAlias;
    URI svgURI;
    AffineTransform scaleXform = new AffineTransform();
    public static final int AUTOSIZE_NONE = 0;
    public static final int AUTOSIZE_HORIZ = 1;
    public static final int AUTOSIZE_VERT = 2;
    public static final int AUTOSIZE_BESTFIT = 3;
    public static final int AUTOSIZE_STRETCH = 4;
    private int autosize = 0;

    public SVGPanel() {
        this.initComponents();
    }

    public int getSVGHeight() {
        if (this.autosize == 2 || this.autosize == 4 || this.autosize == 3) {
            return this.getPreferredSize().height;
        }
        SVGDiagram diagram = this.svgUniverse.getDiagram(this.svgURI);
        if (diagram == null) {
            return 0;
        }
        return (int)diagram.getHeight();
    }

    public int getSVGWidth() {
        if (this.autosize == 1 || this.autosize == 4 || this.autosize == 3) {
            return this.getPreferredSize().width;
        }
        SVGDiagram diagram = this.svgUniverse.getDiagram(this.svgURI);
        if (diagram == null) {
            return 0;
        }
        return (int)diagram.getWidth();
    }

    @Override
    public void paintComponent(Graphics gg) {
        super.paintComponent(gg);
        Graphics2D g = (Graphics2D)gg.create();
        this.paintComponent(g);
        g.dispose();
    }

    private void paintComponent(Graphics2D g) {
        Object oldAliasHint = g.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, this.antiAlias ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF);
        SVGDiagram diagram = this.svgUniverse.getDiagram(this.svgURI);
        if (diagram == null) {
            return;
        }
        if (this.autosize == 0) {
            try {
                diagram.render(g);
                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, oldAliasHint);
            } catch (SVGException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        Dimension dim = this.getSize();
        int width = dim.width;
        int height = dim.height;
        double diaWidth = diagram.getWidth();
        double diaHeight = diagram.getHeight();
        double scaleW = 1.0;
        double scaleH = 1.0;
        if (this.autosize == 3) {
            scaleH = (double)height / diaHeight < (double)width / diaWidth ? (double)height / diaHeight : (double)width / diaWidth;
            scaleW = scaleH;
        } else if (this.autosize == 1) {
            scaleW = scaleH = (double)width / diaWidth;
        } else if (this.autosize == 2) {
            scaleW = scaleH = (double)height / diaHeight;
        } else if (this.autosize == 4) {
            scaleW = (double)width / diaWidth;
            scaleH = (double)height / diaHeight;
        }
        this.scaleXform.setToScale(scaleW, scaleH);
        AffineTransform oldXform = g.getTransform();
        g.transform(this.scaleXform);
        try {
            diagram.render(g);
        } catch (SVGException e) {
            throw new RuntimeException(e);
        }
        g.setTransform(oldXform);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, oldAliasHint);
    }

    public SVGUniverse getSvgUniverse() {
        return this.svgUniverse;
    }

    public void setSvgUniverse(SVGUniverse svgUniverse) {
        SVGUniverse old = this.svgUniverse;
        this.svgUniverse = svgUniverse;
        this.firePropertyChange("svgUniverse", old, svgUniverse);
    }

    public URI getSvgURI() {
        return this.svgURI;
    }

    public void setSvgURI(URI svgURI) {
        URI old = this.svgURI;
        this.svgURI = svgURI;
        this.firePropertyChange("svgURI", old, svgURI);
    }

    public void setSvgResourcePath(String resourcePath) throws SVGException {
        URI old = this.svgURI;
        try {
            this.svgURI = new URI(this.getClass().getResource(resourcePath).toString());
            this.firePropertyChange("svgURI", old, this.svgURI);
            this.repaint();
        } catch (Exception e) {
            throw new SVGException("Could not resolve path " + resourcePath, e);
        }
    }

    public boolean isScaleToFit() {
        return this.autosize == 4;
    }

    public void setScaleToFit(boolean scaleToFit) {
        this.setAutosize(4);
    }

    public boolean getUseAntiAlias() {
        return this.getAntiAlias();
    }

    public void setUseAntiAlias(boolean antiAlias) {
        this.setAntiAlias(antiAlias);
    }

    public boolean getAntiAlias() {
        return this.antiAlias;
    }

    public void setAntiAlias(boolean antiAlias) {
        boolean old = this.antiAlias;
        this.antiAlias = antiAlias;
        this.firePropertyChange("antiAlias", old, antiAlias);
    }

    public int getAutosize() {
        return this.autosize;
    }

    public void setAutosize(int autosize) {
        int oldAutosize = this.autosize;
        this.autosize = autosize;
        this.firePropertyChange(PROP_AUTOSIZE, oldAutosize, autosize);
    }

    private void initComponents() {
        this.setLayout(new BorderLayout());
    }
}

