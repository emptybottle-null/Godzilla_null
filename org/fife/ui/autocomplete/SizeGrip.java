/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.autocomplete;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.MouseInputAdapter;

class SizeGrip
extends JPanel {
    private Image osxSizeGrip;

    SizeGrip() {
        MouseHandler adapter = new MouseHandler();
        this.addMouseListener(adapter);
        this.addMouseMotionListener(adapter);
        this.setPreferredSize(new Dimension(16, 16));
    }

    @Override
    public void applyComponentOrientation(ComponentOrientation o) {
        this.possiblyFixCursor(o.isLeftToRight());
        super.applyComponentOrientation(o);
    }

    private Image createOSXSizeGrip() {
        ClassLoader cl = this.getClass().getClassLoader();
        URL url = cl.getResource("org/fife/ui/autocomplete/osx_sizegrip.png");
        if (url == null) {
            File f = new File("../AutoComplete/src/org/fife/ui/autocomplete/osx_sizegrip.png");
            if (f.isFile()) {
                try {
                    url = f.toURI().toURL();
                } catch (MalformedURLException mue) {
                    mue.printStackTrace();
                    return null;
                }
            } else {
                return null;
            }
        }
        BufferedImage image = null;
        try {
            image = ImageIO.read(url);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension dim = this.getSize();
        if (this.osxSizeGrip != null) {
            g.drawImage(this.osxSizeGrip, dim.width - 16, dim.height - 16, null);
            return;
        }
        Color c1 = UIManager.getColor("Label.disabledShadow");
        Color c2 = UIManager.getColor("Label.disabledForeground");
        ComponentOrientation orientation = this.getComponentOrientation();
        if (orientation.isLeftToRight()) {
            int width = dim.width -= 3;
            int height = dim.height -= 3;
            g.setColor(c1);
            g.fillRect(width - 9, height - 1, 3, 3);
            g.fillRect(width - 5, height - 1, 3, 3);
            g.fillRect(width - 1, height - 1, 3, 3);
            g.fillRect(width - 5, height - 5, 3, 3);
            g.fillRect(width - 1, height - 5, 3, 3);
            g.fillRect(width - 1, height - 9, 3, 3);
            g.setColor(c2);
            g.fillRect(width - 9, height - 1, 2, 2);
            g.fillRect(width - 5, height - 1, 2, 2);
            g.fillRect(width - 1, height - 1, 2, 2);
            g.fillRect(width - 5, height - 5, 2, 2);
            g.fillRect(width - 1, height - 5, 2, 2);
            g.fillRect(width - 1, height - 9, 2, 2);
        } else {
            int height = dim.height -= 3;
            g.setColor(c1);
            g.fillRect(10, height - 1, 3, 3);
            g.fillRect(6, height - 1, 3, 3);
            g.fillRect(2, height - 1, 3, 3);
            g.fillRect(6, height - 5, 3, 3);
            g.fillRect(2, height - 5, 3, 3);
            g.fillRect(2, height - 9, 3, 3);
            g.setColor(c2);
            g.fillRect(10, height - 1, 2, 2);
            g.fillRect(6, height - 1, 2, 2);
            g.fillRect(2, height - 1, 2, 2);
            g.fillRect(6, height - 5, 2, 2);
            g.fillRect(2, height - 5, 2, 2);
            g.fillRect(2, height - 9, 2, 2);
        }
    }

    protected void possiblyFixCursor(boolean ltr) {
        int cursor = 7;
        if (ltr) {
            cursor = 6;
        }
        if (cursor != this.getCursor().getType()) {
            this.setCursor(Cursor.getPredefinedCursor(cursor));
        }
    }

    @Override
    public void updateUI() {
        super.updateUI();
        if (System.getProperty("os.name").contains("OS X")) {
            if (this.osxSizeGrip == null) {
                this.osxSizeGrip = this.createOSXSizeGrip();
            }
        } else {
            this.osxSizeGrip = null;
        }
    }

    private class MouseHandler
    extends MouseInputAdapter {
        private Point origPos;

        private MouseHandler() {
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            Point newPos = e.getPoint();
            SwingUtilities.convertPointToScreen(newPos, SizeGrip.this);
            int xDelta = newPos.x - this.origPos.x;
            int yDelta = newPos.y - this.origPos.y;
            Window wind = SwingUtilities.getWindowAncestor(SizeGrip.this);
            if (wind != null) {
                if (SizeGrip.this.getComponentOrientation().isLeftToRight()) {
                    int w = wind.getWidth();
                    if (newPos.x >= wind.getX()) {
                        w += xDelta;
                    }
                    int h = wind.getHeight();
                    if (newPos.y >= wind.getY()) {
                        h += yDelta;
                    }
                    wind.setSize(w, h);
                } else {
                    int newW = Math.max(1, wind.getWidth() - xDelta);
                    int newH = Math.max(1, wind.getHeight() + yDelta);
                    wind.setBounds(newPos.x, wind.getY(), newW, newH);
                }
                wind.invalidate();
                wind.validate();
            }
            this.origPos.setLocation(newPos);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            this.origPos = e.getPoint();
            SwingUtilities.convertPointToScreen(this.origPos, SizeGrip.this);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            this.origPos = null;
        }
    }
}

