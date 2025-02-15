/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ui;

import java.awt.Graphics;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import org.fife.rsta.ui.SizeGripIcon;

public class ResizableFrameContentPane
extends JPanel {
    private static final long serialVersionUID = 1L;
    private SizeGripIcon gripIcon = new SizeGripIcon();

    public ResizableFrameContentPane() {
    }

    public ResizableFrameContentPane(LayoutManager layout) {
        super(layout);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.gripIcon.paintIcon(this, g, this.getX(), this.getY());
    }
}

