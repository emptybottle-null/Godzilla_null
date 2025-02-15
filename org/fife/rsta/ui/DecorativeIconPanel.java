/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ToolTipManager;
import org.fife.ui.autocomplete.EmptyIcon;

public class DecorativeIconPanel
extends JPanel {
    private static final int DEFAULT_WIDTH = 8;
    private JLabel iconLabel;
    private boolean showIcon;
    private String tip;
    private EmptyIcon emptyIcon;

    public DecorativeIconPanel() {
        this(8);
    }

    public DecorativeIconPanel(int iconWidth) {
        this.setLayout(new BorderLayout());
        this.emptyIcon = new EmptyIcon(iconWidth);
        this.iconLabel = new JLabel(this.emptyIcon){

            @Override
            public String getToolTipText(MouseEvent e) {
                return DecorativeIconPanel.this.showIcon ? DecorativeIconPanel.this.tip : null;
            }
        };
        this.iconLabel.setVerticalAlignment(1);
        ToolTipManager.sharedInstance().registerComponent(this.iconLabel);
        this.add((Component)this.iconLabel, "North");
    }

    public Icon getIcon() {
        return this.iconLabel.getIcon();
    }

    public boolean getShowIcon() {
        return this.showIcon;
    }

    @Override
    public String getToolTipText() {
        return this.tip;
    }

    @Override
    protected void paintChildren(Graphics g) {
        if (this.showIcon) {
            super.paintChildren(g);
        }
    }

    public void setIcon(Icon icon) {
        if (icon == null) {
            icon = this.emptyIcon;
        }
        this.iconLabel.setIcon(icon);
    }

    public void setShowIcon(boolean show) {
        if (show != this.showIcon) {
            this.showIcon = show;
            this.repaint();
        }
    }

    @Override
    public void setToolTipText(String tip) {
        this.tip = tip;
    }
}

